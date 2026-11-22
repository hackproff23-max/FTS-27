package com.facebook.share.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AppCall;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.internal.GraphUtil;
import com.facebook.internal.NativeAppCallAttachmentStore;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.LikeView;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ShareInternalUtility {
    private static final String MY_ACTION_FORMAT = "me/%s";
    private static final String MY_FEED = "me/feed";
    private static final String MY_OBJECTS_FORMAT = "me/objects/%s";
    private static final String MY_PHOTOS = "me/photos";
    private static final String MY_STAGING_RESOURCES = "me/staging_resources";
    private static final String MY_VIDEOS = "me/videos";
    private static final String OBJECT_PARAM = "object";
    private static final String PICTURE_PARAM = "picture";
    private static final String STAGING_PARAM = "file";

    public static void invokeCallbackWithException(FacebookCallback<Sharer.Result> callback, Exception exception) {
        if (exception instanceof FacebookException) {
            invokeOnErrorCallback(callback, (FacebookException) exception);
        } else {
            invokeCallbackWithError(callback, "Error preparing share content: " + exception.getLocalizedMessage());
        }
    }

    public static void invokeCallbackWithError(FacebookCallback<Sharer.Result> callback, String error) {
        invokeOnErrorCallback(callback, error);
    }

    public static void invokeCallbackWithResults(FacebookCallback<Sharer.Result> callback, String postId, GraphResponse graphResponse) {
        FacebookRequestError requestError = graphResponse.getError();
        if (requestError != null) {
            String errorMessage = requestError.getErrorMessage();
            if (Utility.isNullOrEmpty(errorMessage)) {
                errorMessage = "Unexpected error sharing.";
            }
            invokeOnErrorCallback(callback, graphResponse, errorMessage);
            return;
        }
        invokeOnSuccessCallback(callback, postId);
    }

    public static boolean getNativeDialogDidComplete(Bundle result) {
        return result.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY) ? result.getBoolean(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY) : result.getBoolean(NativeProtocol.EXTRA_DIALOG_COMPLETE_KEY, false);
    }

    public static String getNativeDialogCompletionGesture(Bundle result) {
        return result.containsKey(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY) ? result.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY) : result.getString(NativeProtocol.EXTRA_DIALOG_COMPLETION_GESTURE_KEY);
    }

    public static String getShareDialogPostId(Bundle result) {
        if (result.containsKey(ShareConstants.RESULT_POST_ID)) {
            return result.getString(ShareConstants.RESULT_POST_ID);
        }
        if (result.containsKey(ShareConstants.EXTRA_RESULT_POST_ID)) {
            return result.getString(ShareConstants.EXTRA_RESULT_POST_ID);
        }
        return result.getString(ShareConstants.WEB_DIALOG_RESULT_PARAM_POST_ID);
    }

    public static boolean handleActivityResult(int requestCode, int resultCode, Intent data, ResultProcessor resultProcessor) {
        AppCall appCall = getAppCallFromActivityResult(requestCode, resultCode, data);
        if (appCall == null) {
            return false;
        }
        NativeAppCallAttachmentStore.cleanupAttachmentsForCall(appCall.getCallId());
        if (resultProcessor == null) {
            return true;
        }
        FacebookException exception = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getErrorDataFromResultIntent(data));
        if (exception != null) {
            if (exception instanceof FacebookOperationCanceledException) {
                resultProcessor.onCancel(appCall);
                return true;
            }
            resultProcessor.onError(appCall, exception);
            return true;
        }
        Bundle results = NativeProtocol.getSuccessResultsFromIntent(data);
        resultProcessor.onSuccess(appCall, results);
        return true;
    }

    public static ResultProcessor getShareResultProcessor(final FacebookCallback<Sharer.Result> callback) {
        return new ResultProcessor(callback) { // from class: com.facebook.share.internal.ShareInternalUtility.1
            @Override // com.facebook.share.internal.ResultProcessor
            public void onSuccess(AppCall appCall, Bundle results) {
                if (results != null) {
                    String gesture = ShareInternalUtility.getNativeDialogCompletionGesture(results);
                    if (gesture == null || "post".equalsIgnoreCase(gesture)) {
                        String postId = ShareInternalUtility.getShareDialogPostId(results);
                        ShareInternalUtility.invokeOnSuccessCallback(callback, postId);
                    } else if ("cancel".equalsIgnoreCase(gesture)) {
                        ShareInternalUtility.invokeOnCancelCallback(callback);
                    } else {
                        ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) callback, new FacebookException(NativeProtocol.ERROR_UNKNOWN_ERROR));
                    }
                }
            }

            @Override // com.facebook.share.internal.ResultProcessor
            public void onCancel(AppCall appCall) {
                ShareInternalUtility.invokeOnCancelCallback(callback);
            }

            @Override // com.facebook.share.internal.ResultProcessor
            public void onError(AppCall appCall, FacebookException error) {
                ShareInternalUtility.invokeOnErrorCallback((FacebookCallback<Sharer.Result>) callback, error);
            }
        };
    }

    private static AppCall getAppCallFromActivityResult(int requestCode, int resultCode, Intent data) {
        UUID callId = NativeProtocol.getCallIdFromIntent(data);
        if (callId == null) {
            return null;
        }
        return AppCall.finishPendingCall(callId, requestCode);
    }

    public static void registerStaticShareCallback(final int requestCode) {
        CallbackManagerImpl.registerStaticCallback(requestCode, new CallbackManagerImpl.Callback() { // from class: com.facebook.share.internal.ShareInternalUtility.2
            @Override // com.facebook.internal.CallbackManagerImpl.Callback
            public boolean onActivityResult(int resultCode, Intent data) {
                return ShareInternalUtility.handleActivityResult(requestCode, resultCode, data, ShareInternalUtility.getShareResultProcessor(null));
            }
        });
    }

    public static void registerSharerCallback(final int requestCode, CallbackManager callbackManager, final FacebookCallback<Sharer.Result> callback) {
        if (!(callbackManager instanceof CallbackManagerImpl)) {
            throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
        }
        ((CallbackManagerImpl) callbackManager).registerCallback(requestCode, new CallbackManagerImpl.Callback() { // from class: com.facebook.share.internal.ShareInternalUtility.3
            @Override // com.facebook.internal.CallbackManagerImpl.Callback
            public boolean onActivityResult(int resultCode, Intent data) {
                return ShareInternalUtility.handleActivityResult(requestCode, resultCode, data, ShareInternalUtility.getShareResultProcessor(callback));
            }
        });
    }

    public static List<String> getPhotoUrls(SharePhotoContent photoContent, final UUID appCallId) {
        List<SharePhoto> photos;
        if (photoContent == null || (photos = photoContent.getPhotos()) == null) {
            return null;
        }
        List<NativeAppCallAttachmentStore.Attachment> attachments = Utility.map(photos, new Utility.Mapper<SharePhoto, NativeAppCallAttachmentStore.Attachment>() { // from class: com.facebook.share.internal.ShareInternalUtility.4
            @Override // com.facebook.internal.Utility.Mapper
            public NativeAppCallAttachmentStore.Attachment apply(SharePhoto item) {
                return ShareInternalUtility.getAttachment(appCallId, item);
            }
        });
        List<String> map = Utility.map(attachments, new Utility.Mapper<NativeAppCallAttachmentStore.Attachment, String>() { // from class: com.facebook.share.internal.ShareInternalUtility.5
            @Override // com.facebook.internal.Utility.Mapper
            public String apply(NativeAppCallAttachmentStore.Attachment item) {
                return item.getAttachmentUrl();
            }
        });
        NativeAppCallAttachmentStore.addAttachments(attachments);
        return map;
    }

    public static JSONObject toJSONObjectForCall(final UUID callId, ShareOpenGraphAction action) throws JSONException {
        final ArrayList<NativeAppCallAttachmentStore.Attachment> attachments = new ArrayList<>();
        JSONObject actionJSON = OpenGraphJSONUtility.toJSONObject(action, new OpenGraphJSONUtility.PhotoJSONProcessor() { // from class: com.facebook.share.internal.ShareInternalUtility.6
            @Override // com.facebook.share.internal.OpenGraphJSONUtility.PhotoJSONProcessor
            public JSONObject toJSONObject(SharePhoto photo) {
                NativeAppCallAttachmentStore.Attachment attachment = ShareInternalUtility.getAttachment(callId, photo);
                if (attachment == null) {
                    return null;
                }
                attachments.add(attachment);
                JSONObject photoJSONObject = new JSONObject();
                try {
                    photoJSONObject.put("url", attachment.getAttachmentUrl());
                    if (photo.getUserGenerated()) {
                        photoJSONObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, true);
                        return photoJSONObject;
                    }
                    return photoJSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
        });
        NativeAppCallAttachmentStore.addAttachments(attachments);
        return actionJSON;
    }

    public static JSONObject toJSONObjectForWeb(ShareOpenGraphContent shareOpenGraphContent) throws JSONException {
        ShareOpenGraphAction action = shareOpenGraphContent.getAction();
        return OpenGraphJSONUtility.toJSONObject(action, new OpenGraphJSONUtility.PhotoJSONProcessor() { // from class: com.facebook.share.internal.ShareInternalUtility.7
            @Override // com.facebook.share.internal.OpenGraphJSONUtility.PhotoJSONProcessor
            public JSONObject toJSONObject(SharePhoto photo) {
                Uri photoUri = photo.getImageUrl();
                JSONObject photoJSONObject = new JSONObject();
                try {
                    photoJSONObject.put("url", photoUri.toString());
                    return photoJSONObject;
                } catch (JSONException e) {
                    throw new FacebookException("Unable to attach images", e);
                }
            }
        });
    }

    public static JSONArray removeNamespacesFromOGJsonArray(JSONArray jsonArray, boolean requireNamespace) throws JSONException {
        JSONArray newArray = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONArray) {
                value = removeNamespacesFromOGJsonArray((JSONArray) value, requireNamespace);
            } else if (value instanceof JSONObject) {
                value = removeNamespacesFromOGJsonObject((JSONObject) value, requireNamespace);
            }
            newArray.put(value);
        }
        return newArray;
    }

    public static JSONObject removeNamespacesFromOGJsonObject(JSONObject jsonObject, boolean requireNamespace) {
        if (jsonObject == null) {
            return null;
        }
        try {
            JSONObject newJsonObject = new JSONObject();
            JSONObject data = new JSONObject();
            JSONArray names = jsonObject.names();
            for (int i = 0; i < names.length(); i++) {
                String key = names.getString(i);
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    value = removeNamespacesFromOGJsonObject((JSONObject) value, true);
                } else if (value instanceof JSONArray) {
                    value = removeNamespacesFromOGJsonArray((JSONArray) value, true);
                }
                Pair<String, String> fieldNameAndNamespace = getFieldNameAndNamespaceFromFullName(key);
                String namespace = (String) fieldNameAndNamespace.first;
                String fieldName = (String) fieldNameAndNamespace.second;
                if (requireNamespace) {
                    if (namespace != null && namespace.equals("fbsdk")) {
                        newJsonObject.put(key, value);
                    } else if (namespace == null || namespace.equals("og")) {
                        newJsonObject.put(fieldName, value);
                    } else {
                        data.put(fieldName, value);
                    }
                } else {
                    newJsonObject.put(fieldName, value);
                }
            }
            if (data.length() > 0) {
                newJsonObject.put("data", data);
                return newJsonObject;
            }
            return newJsonObject;
        } catch (JSONException e) {
            throw new FacebookException("Failed to create json object from share content");
        }
    }

    public static Pair<String, String> getFieldNameAndNamespaceFromFullName(String fullName) {
        String fieldName;
        String namespace = null;
        int index = fullName.indexOf(58);
        if (index != -1 && fullName.length() > index + 1) {
            namespace = fullName.substring(0, index);
            fieldName = fullName.substring(index + 1);
        } else {
            fieldName = fullName;
        }
        return new Pair<>(namespace, fieldName);
    }

    private ShareInternalUtility() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NativeAppCallAttachmentStore.Attachment getAttachment(UUID callId, SharePhoto photo) {
        Bitmap bitmap = photo.getBitmap();
        Uri photoUri = photo.getImageUrl();
        if (bitmap != null) {
            NativeAppCallAttachmentStore.Attachment attachment = NativeAppCallAttachmentStore.createAttachment(callId, bitmap);
            return attachment;
        }
        if (photoUri == null) {
            return null;
        }
        NativeAppCallAttachmentStore.Attachment attachment2 = NativeAppCallAttachmentStore.createAttachment(callId, photoUri);
        return attachment2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeOnCancelCallback(FacebookCallback<Sharer.Result> callback) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, null);
        if (callback != null) {
            callback.onCancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeOnSuccessCallback(FacebookCallback<Sharer.Result> callback, String postId) {
        logShareResult(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED, null);
        if (callback != null) {
            callback.onSuccess(new Sharer.Result(postId));
        }
    }

    private static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, GraphResponse response, String message) {
        logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookGraphResponseException(response, message));
        }
    }

    private static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, String message) {
        logShareResult("error", message);
        if (callback != null) {
            callback.onError(new FacebookException(message));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void invokeOnErrorCallback(FacebookCallback<Sharer.Result> callback, FacebookException ex) {
        logShareResult("error", ex.getMessage());
        if (callback != null) {
            callback.onError(ex);
        }
    }

    private static void logShareResult(String shareOutcome, String errorMessage) {
        Context context = FacebookSdk.getApplicationContext();
        AppEventsLogger logger = AppEventsLogger.newLogger(context);
        Bundle parameters = new Bundle();
        parameters.putString(AnalyticsEvents.PARAMETER_SHARE_OUTCOME, shareOutcome);
        if (errorMessage != null) {
            parameters.putString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, errorMessage);
        }
        logger.logSdkEvent(AnalyticsEvents.EVENT_SHARE_RESULT, null, parameters);
    }

    public static GraphRequest newPostOpenGraphObjectRequest(AccessToken accessToken, JSONObject openGraphObject, GraphRequest.Callback callback) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        }
        if (Utility.isNullOrEmpty(openGraphObject.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY))) {
            throw new FacebookException("openGraphObject must have non-null 'type' property");
        }
        if (Utility.isNullOrEmpty(openGraphObject.optString("title"))) {
            throw new FacebookException("openGraphObject must have non-null 'title' property");
        }
        String path = String.format(MY_OBJECTS_FORMAT, openGraphObject.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY));
        Bundle bundle = new Bundle();
        bundle.putString(OBJECT_PARAM, openGraphObject.toString());
        return new GraphRequest(accessToken, path, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newPostOpenGraphObjectRequest(AccessToken accessToken, String type, String title, String imageUrl, String url, String description, JSONObject objectProperties, GraphRequest.Callback callback) {
        JSONObject openGraphObject = GraphUtil.createOpenGraphObjectForPost(type, title, imageUrl, url, description, objectProperties, null);
        return newPostOpenGraphObjectRequest(accessToken, openGraphObject, callback);
    }

    public static GraphRequest newPostOpenGraphActionRequest(AccessToken accessToken, JSONObject openGraphAction, GraphRequest.Callback callback) {
        if (openGraphAction == null) {
            throw new FacebookException("openGraphAction cannot be null");
        }
        String type = openGraphAction.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        if (Utility.isNullOrEmpty(type)) {
            throw new FacebookException("openGraphAction must have non-null 'type' property");
        }
        String path = String.format(MY_ACTION_FORMAT, type);
        return GraphRequest.newPostRequest(accessToken, path, openGraphAction, callback);
    }

    public static GraphRequest newUpdateOpenGraphObjectRequest(AccessToken accessToken, JSONObject openGraphObject, GraphRequest.Callback callback) {
        if (openGraphObject == null) {
            throw new FacebookException("openGraphObject cannot be null");
        }
        String path = openGraphObject.optString("id");
        if (path == null) {
            throw new FacebookException("openGraphObject must have an id");
        }
        Bundle bundle = new Bundle();
        bundle.putString(OBJECT_PARAM, openGraphObject.toString());
        return new GraphRequest(accessToken, path, bundle, HttpMethod.POST, callback);
    }

    public static GraphRequest newUpdateOpenGraphObjectRequest(AccessToken accessToken, String id, String title, String imageUrl, String url, String description, JSONObject objectProperties, GraphRequest.Callback callback) {
        JSONObject openGraphObject = GraphUtil.createOpenGraphObjectForPost(null, title, imageUrl, url, description, objectProperties, id);
        return newUpdateOpenGraphObjectRequest(accessToken, openGraphObject, callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, Bitmap image, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", image);
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY);
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", descriptor);
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadPhotoRequest(AccessToken accessToken, Uri photoUri, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(photoUri)) {
            return newUploadPhotoRequest(accessToken, new File(photoUri.getPath()), callback);
        }
        if (!Utility.isContentUri(photoUri)) {
            throw new FacebookException("The photo Uri must be either a file:// or content:// Uri");
        }
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", photoUri);
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadVideoRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY);
        Bundle parameters = new Bundle(1);
        parameters.putParcelable(file.getName(), descriptor);
        return new GraphRequest(accessToken, MY_VIDEOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadVideoRequest(AccessToken accessToken, Uri videoUri, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(videoUri)) {
            return newUploadVideoRequest(accessToken, new File(videoUri.getPath()), callback);
        }
        if (!Utility.isContentUri(videoUri)) {
            throw new FacebookException("The video Uri must be either a file:// or content:// Uri");
        }
        Bundle parameters = new Bundle(1);
        parameters.putParcelable("picture", videoUri);
        return new GraphRequest(accessToken, MY_PHOTOS, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, GraphRequest.Callback callback) {
        return newStatusUpdateRequest(accessToken, message, (String) null, (List<String>) null, callback);
    }

    private static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, String placeId, List<String> tagIds, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle();
        parameters.putString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, message);
        if (placeId != null) {
            parameters.putString("place", placeId);
        }
        if (tagIds != null && tagIds.size() > 0) {
            String tags = TextUtils.join(",", tagIds);
            parameters.putString("tags", tags);
        }
        return new GraphRequest(accessToken, MY_FEED, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newStatusUpdateRequest(AccessToken accessToken, String message, JSONObject place, List<JSONObject> tags, GraphRequest.Callback callback) {
        List<String> tagIds = null;
        if (tags != null) {
            tagIds = new ArrayList<>(tags.size());
            for (JSONObject tag : tags) {
                tagIds.add(tag.optString("id"));
            }
        }
        String placeId = place == null ? null : place.optString("id");
        return newStatusUpdateRequest(accessToken, message, placeId, tagIds, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Bitmap image, GraphRequest.Callback callback) {
        Bundle parameters = new Bundle(1);
        parameters.putParcelable(STAGING_PARAM, image);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, File file, GraphRequest.Callback callback) throws FileNotFoundException {
        ParcelFileDescriptor descriptor = ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY);
        GraphRequest.ParcelableResourceWithMimeType<ParcelFileDescriptor> resourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType<>(descriptor, "image/png");
        Bundle parameters = new Bundle(1);
        parameters.putParcelable(STAGING_PARAM, resourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
    }

    public static GraphRequest newUploadStagingResourceWithImageRequest(AccessToken accessToken, Uri imageUri, GraphRequest.Callback callback) throws FileNotFoundException {
        if (Utility.isFileUri(imageUri)) {
            return newUploadStagingResourceWithImageRequest(accessToken, new File(imageUri.getPath()), callback);
        }
        if (!Utility.isContentUri(imageUri)) {
            throw new FacebookException("The image Uri must be either a file:// or content:// Uri");
        }
        GraphRequest.ParcelableResourceWithMimeType<Uri> resourceWithMimeType = new GraphRequest.ParcelableResourceWithMimeType<>(imageUri, "image/png");
        Bundle parameters = new Bundle(1);
        parameters.putParcelable(STAGING_PARAM, resourceWithMimeType);
        return new GraphRequest(accessToken, MY_STAGING_RESOURCES, parameters, HttpMethod.POST, callback);
    }

    @Nullable
    public static LikeView.ObjectType getMostSpecificObjectType(LikeView.ObjectType objectType1, LikeView.ObjectType objectType2) {
        if (objectType1 != objectType2) {
            if (objectType1 == LikeView.ObjectType.UNKNOWN) {
                return objectType2;
            }
            if (objectType2 != LikeView.ObjectType.UNKNOWN) {
                return null;
            }
            return objectType1;
        }
        return objectType1;
    }
}
