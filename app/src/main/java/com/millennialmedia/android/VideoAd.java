package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.AnalyticsEvents;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.Externalizable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class VideoAd extends CachedAd implements Parcelable, Externalizable {
    public static final Parcelable.Creator<VideoAd> CREATOR = new Parcelable.Creator<VideoAd>() { // from class: com.millennialmedia.android.VideoAd.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoAd createFromParcel(Parcel in) {
            return new VideoAd(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VideoAd[] newArray(int size) {
            return new VideoAd[size];
        }
    };
    private static final String TAG = "VideoAd";
    static final String VIDEO_FILE_ID = "video.dat";
    static final long serialVersionUID = 2679125946930815832L;
    ArrayList<VideoLogEvent> activities;
    ArrayList<VideoImage> buttons;
    String[] cacheComplete;
    String[] cacheFailed;
    String cacheMissURL;
    DTOCachedVideo cachedVideoDto;
    long closeDelayMillis;
    long contentLength;
    long duration;
    String[] endActivity;
    String endOverlayURL;
    String onCompletionUrl;
    boolean reloadNonEndOverlayOnRestart;
    boolean showControls;
    boolean showCountdown;
    String[] startActivity;
    boolean stayInPlayer;
    boolean usingInternalStorage;
    String[] videoError;
    private String videoFileId;
    String webOverlayURL;

    public VideoAd() {
        this.buttons = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    VideoAd(Parcel in) {
        super(in);
        this.buttons = new ArrayList<>();
        this.activities = new ArrayList<>();
        try {
            int len = in.readInt();
            this.startActivity = new String[len];
            in.readStringArray(this.startActivity);
            int len2 = in.readInt();
            this.endActivity = new String[len2];
            in.readStringArray(this.endActivity);
            boolean[] boolArray = new boolean[5];
            in.readBooleanArray(boolArray);
            this.showControls = boolArray[0];
            this.stayInPlayer = boolArray[1];
            this.showCountdown = boolArray[2];
            this.reloadNonEndOverlayOnRestart = boolArray[3];
            this.usingInternalStorage = boolArray[4];
            this.onCompletionUrl = in.readString();
            this.webOverlayURL = in.readString();
            this.endOverlayURL = in.readString();
            this.cacheMissURL = in.readString();
            this.videoFileId = in.readString();
            this.duration = in.readLong();
            this.contentLength = in.readLong();
            this.closeDelayMillis = in.readLong();
            this.buttons = in.readArrayList(VideoImage.class.getClassLoader());
            this.activities = in.readArrayList(VideoLogEvent.class.getClassLoader());
            int len3 = in.readInt();
            this.cacheComplete = new String[len3];
            in.readStringArray(this.cacheComplete);
            int len4 = in.readInt();
            this.cacheFailed = new String[len4];
            in.readStringArray(this.cacheFailed);
            int len5 = in.readInt();
            this.videoError = new String[len5];
            in.readStringArray(this.videoError);
        } catch (Exception e) {
            MMLog.e(TAG, "Exception with videoad parcel creation: ", e);
        }
    }

    VideoAd(String jsonString) {
        JSONObject videoObject;
        this.buttons = new ArrayList<>();
        this.activities = new ArrayList<>();
        if (jsonString != null) {
            JSONObject jsonAdObject = null;
            try {
                JSONObject jsonAdObject2 = new JSONObject(jsonString);
                jsonAdObject = jsonAdObject2;
            } catch (JSONException e) {
                MMLog.e(TAG, "VideoAd json exception: ", e);
            }
            if (jsonAdObject != null && (videoObject = jsonAdObject.optJSONObject(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) != null) {
                deserializeFromObj(videoObject);
            }
        }
    }

    @Override // com.millennialmedia.android.CachedAd
    protected void deserializeFromObj(JSONObject videoObject) {
        super.deserializeFromObj(videoObject);
        JSONArray jsonArray = videoObject.optJSONArray("startActivity");
        this.webOverlayURL = videoObject.optString("overlayURL", null);
        this.endOverlayURL = videoObject.optString("endURL", null);
        this.cacheMissURL = videoObject.optString("cacheMissURL", null);
        this.videoFileId = videoObject.optString("videoFileId", null);
        if (jsonArray != null) {
            this.startActivity = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                this.startActivity[i] = jsonArray.optString(i);
            }
        } else {
            this.startActivity = new String[0];
        }
        JSONArray jsonArray2 = videoObject.optJSONArray("endActivity");
        if (jsonArray2 != null) {
            this.endActivity = new String[jsonArray2.length()];
            for (int i2 = 0; i2 < jsonArray2.length(); i2++) {
                this.endActivity[i2] = jsonArray2.optString(i2);
            }
        } else {
            this.endActivity = new String[0];
        }
        JSONArray jsonArray3 = videoObject.optJSONArray("cacheComplete");
        if (jsonArray3 != null) {
            this.cacheComplete = new String[jsonArray3.length()];
            for (int i3 = 0; i3 < jsonArray3.length(); i3++) {
                this.cacheComplete[i3] = jsonArray3.optString(i3);
            }
        } else {
            this.cacheComplete = new String[0];
        }
        JSONArray jsonArray4 = videoObject.optJSONArray("cacheFailed");
        if (jsonArray4 != null) {
            this.cacheFailed = new String[jsonArray4.length()];
            for (int i4 = 0; i4 < jsonArray4.length(); i4++) {
                this.cacheFailed[i4] = jsonArray4.optString(i4);
            }
        } else {
            this.cacheFailed = new String[0];
        }
        JSONArray jsonArray5 = videoObject.optJSONArray(UnityAdsConstants.UNITY_ADS_ANALYTICS_EVENTTYPE_VIDEOERROR);
        if (jsonArray5 != null) {
            this.videoError = new String[jsonArray5.length()];
            for (int i5 = 0; i5 < jsonArray5.length(); i5++) {
                this.videoError[i5] = jsonArray5.optString(i5);
            }
        } else {
            this.videoError = new String[0];
        }
        this.showControls = videoObject.optBoolean("showVideoPlayerControls");
        this.showCountdown = videoObject.optBoolean("showCountdownHUD");
        this.reloadNonEndOverlayOnRestart = videoObject.optBoolean("reloadOverlayOnRestart");
        JSONObject jsonObject = videoObject.optJSONObject("onCompletion");
        if (jsonObject != null) {
            this.onCompletionUrl = jsonObject.optString("url", null);
            this.stayInPlayer = jsonObject.optBoolean("stayInPlayer");
        }
        this.duration = (long) (videoObject.optDouble("duration", 0.0d) * 1000.0d);
        this.contentLength = videoObject.optLong("contentLength");
        this.closeDelayMillis = videoObject.optLong("closeAfterDelay");
        JSONArray jsonArray6 = videoObject.optJSONArray("buttons");
        if (jsonArray6 != null) {
            for (int i6 = 0; i6 < jsonArray6.length(); i6++) {
                JSONObject jsonObject2 = jsonArray6.optJSONObject(i6);
                if (jsonObject2 != null) {
                    VideoImage vb = new VideoImage(jsonObject2);
                    this.buttons.add(vb);
                }
            }
        }
        JSONArray jsonArray7 = videoObject.optJSONArray("log");
        if (jsonArray7 != null) {
            for (int i7 = 0; i7 < jsonArray7.length(); i7++) {
                JSONObject jsonObject3 = jsonArray7.optJSONObject(i7);
                if (jsonObject3 != null) {
                    VideoLogEvent videoLogEvent = new VideoLogEvent(jsonObject3);
                    this.activities.add(videoLogEvent);
                }
            }
        }
    }

    @Override // com.millennialmedia.android.CachedAd
    int getType() {
        return 1;
    }

    @Override // com.millennialmedia.android.CachedAd
    String getTypeString() {
        return "Video";
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean saveAssets(Context context) {
        return true;
    }

    static boolean downloadVideoFile(Context context, String contentUrl, String videoFileId) {
        boolean success = AdCache.downloadComponentExternalStorage(contentUrl, videoFileId + VIDEO_FILE_ID, context);
        MMLog.v(TAG, String.format("Caching completed successfully (" + videoFileId + VIDEO_FILE_ID + ")? %b", Boolean.valueOf(success)));
        return success;
    }

    static boolean hasVideoFile(Context context, String videoFileId) {
        return AdCache.hasDownloadFile(context, videoFileId + VIDEO_FILE_ID);
    }

    static Uri getVideoUri(Context context, String videoFileId) {
        return Uri.fromFile(AdCache.getDownloadFile(context, videoFileId + VIDEO_FILE_ID));
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean download(Context context) {
        boolean success = AdCache.downloadComponentExternalStorage(this.contentUrl, this.videoFileId + VIDEO_FILE_ID, context);
        if (success) {
            for (int i = 0; i < this.buttons.size(); i++) {
                VideoImage button = this.buttons.get(i);
                success = AdCache.downloadComponentInternalCache(button.imageUrl, getId() + button.getImageName(), context);
                if (!success) {
                    break;
                }
            }
        }
        if (!success) {
            if (this.downloadAllOrNothing) {
                delete(context);
            }
            HttpGetRequest.log(this.cacheFailed);
        } else if (success) {
            if (this.acid != null && this.acid.length() > 0) {
                AdCache.cachedVideoWasAdded(context, this.acid);
            }
            HttpGetRequest.log(this.cacheComplete);
        }
        MMLog.v(TAG, String.format("Caching completed successfully? %b", Boolean.valueOf(success)));
        return success;
    }

    @Override // com.millennialmedia.android.CachedAd
    void delete(Context context) {
        super.delete(context);
        handleSharedVideoFile(context);
        AdCache.cachedVideoWasRemoved(context, this.acid);
        MMLog.v(TAG, String.format("Ad %s was deleted.", getId()));
    }

    private void handleSharedVideoFile(Context context) {
        AdCache.iterateCachedAds(context, 2, new VideoIterator(context, this));
    }

    private static class VideoIterator extends AdCache.Iterator {
        private WeakReference<Context> contextRef;
        boolean hasSharedVideoFile = false;
        private WeakReference<VideoAd> videoAdRef;

        public VideoIterator(Context context, VideoAd videoAd) {
            this.videoAdRef = new WeakReference<>(videoAd);
            this.contextRef = new WeakReference<>(context);
        }

        @Override // com.millennialmedia.android.AdCache.Iterator
        boolean callback(CachedAd cachedAd) {
            if (cachedAd != null && (cachedAd instanceof VideoAd)) {
                VideoAd videoAd = (VideoAd) cachedAd;
                VideoAd requestingVideoAd = this.videoAdRef.get();
                if (requestingVideoAd != null && videoAd.videoFileId.equals(requestingVideoAd.videoFileId)) {
                    this.hasSharedVideoFile = true;
                }
            }
            return super.callback(cachedAd);
        }

        @Override // com.millennialmedia.android.AdCache.Iterator
        void done() {
            Context context;
            if (!this.hasSharedVideoFile && (context = this.contextRef.get()) != null) {
                deleteVideoFile(context);
            }
            super.done();
        }

        void deleteVideoFile(Context context) {
            VideoAd requestingVideoAd = this.videoAdRef.get();
            if (requestingVideoAd != null && AdCache.deleteFile(context, requestingVideoAd.videoFileId + VideoAd.VIDEO_FILE_ID)) {
                MMLog.v(VideoAd.TAG, String.format("VideoAd video file %s was deleted.", requestingVideoAd.videoFileId));
            }
        }
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean isOnDisk(Context context) {
        boolean existsInFilesystem = false;
        int numExpectedButtons = this.buttons.size() + 1;
        File internalDir = AdCache.getInternalCacheDirectory(context);
        File externalDir = AdCache.getExternalCacheDirectory(context);
        if (internalDir == null || externalDir == null) {
            return false;
        }
        String[] listInternal = internalDir.list(new VideoFilenameFilter(this));
        if (listInternal != null && listInternal.length >= numExpectedButtons) {
            existsInFilesystem = true;
        }
        if (existsInFilesystem && externalDir != null) {
            File videoFile = new File(externalDir, this.videoFileId + VIDEO_FILE_ID);
            if (!videoFile.exists()) {
                return false;
            }
            for (VideoImage button : this.buttons) {
                File imageFile = new File(internalDir, getId() + button.getImageName());
                if (!imageFile.exists()) {
                    return false;
                }
            }
            return existsInFilesystem;
        }
        return existsInFilesystem;
    }

    private static class VideoFilenameFilter implements FilenameFilter {
        private WeakReference<VideoAd> videoAdRef;

        public VideoFilenameFilter(VideoAd videoAd) {
            this.videoAdRef = new WeakReference<>(videoAd);
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File dir, String filename) {
            String id;
            VideoAd videoAd = this.videoAdRef.get();
            if (videoAd == null || (id = videoAd.getId()) == null) {
                return false;
            }
            return filename.startsWith(id);
        }
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean canShow(Context context, MMAdImpl adImpl, boolean checkDeferredViewStart) {
        if (checkDeferredViewStart) {
            return !isExpired() && isOnDisk(context) && HandShake.sharedHandShake(context).canDisplayCachedAd(adImpl.adType, this.deferredViewStart);
        }
        return !isExpired() && isOnDisk(context);
    }

    @Override // com.millennialmedia.android.CachedAd
    void show(Context context, long adImplInternalId) {
        Utils.IntentUtils.startCachedVideoPlayerActivity(context, getVideoExtrasIntent(context, adImplInternalId));
    }

    Intent getVideoExtrasIntent(Context context, long adImplInternalId) {
        Intent intent = new Intent();
        intent.putExtra("videoId", getId());
        if (adImplInternalId != -4) {
            intent.putExtra("internalId", adImplInternalId);
        }
        String externalVideoAd = AdCache.getExternalCacheDirectory(context).getAbsolutePath() + File.separator + this.videoFileId + VIDEO_FILE_ID;
        intent.setData(Uri.parse(externalVideoAd));
        return intent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.millennialmedia.android.CachedAd, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.startActivity.length);
        dest.writeStringArray(this.startActivity);
        dest.writeInt(this.endActivity.length);
        dest.writeStringArray(this.endActivity);
        dest.writeBooleanArray(new boolean[]{this.showControls, this.stayInPlayer, this.showCountdown, this.reloadNonEndOverlayOnRestart, this.usingInternalStorage});
        dest.writeString(this.onCompletionUrl);
        dest.writeString(this.endOverlayURL);
        dest.writeString(this.webOverlayURL);
        dest.writeString(this.cacheMissURL);
        dest.writeString(this.videoFileId);
        dest.writeLong(this.duration);
        dest.writeLong(this.contentLength);
        dest.writeLong(this.closeDelayMillis);
        dest.writeList(this.buttons);
        dest.writeList(this.activities);
        dest.writeInt(this.cacheComplete.length);
        dest.writeStringArray(this.cacheComplete);
        dest.writeInt(this.cacheFailed.length);
        dest.writeStringArray(this.cacheFailed);
        dest.writeInt(this.videoError.length);
        dest.writeStringArray(this.videoError);
    }

    @Override // com.millennialmedia.android.CachedAd, java.io.Externalizable
    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        super.readExternal(input);
        this.showControls = input.readBoolean();
        this.onCompletionUrl = (String) input.readObject();
        this.webOverlayURL = (String) input.readObject();
        this.endOverlayURL = (String) input.readObject();
        this.cacheMissURL = (String) input.readObject();
        this.videoFileId = (String) input.readObject();
        this.stayInPlayer = input.readBoolean();
        this.showCountdown = input.readBoolean();
        this.reloadNonEndOverlayOnRestart = input.readBoolean();
        int count = input.readInt();
        this.startActivity = new String[count];
        for (int i = 0; i < count; i++) {
            this.startActivity[i] = (String) input.readObject();
        }
        int count2 = input.readInt();
        this.endActivity = new String[count2];
        for (int i2 = 0; i2 < count2; i2++) {
            this.endActivity[i2] = (String) input.readObject();
        }
        this.duration = input.readLong();
        this.usingInternalStorage = input.readBoolean();
        this.contentLength = input.readLong();
        this.closeDelayMillis = input.readLong();
        int count3 = input.readInt();
        this.cacheComplete = new String[count3];
        for (int i3 = 0; i3 < count3; i3++) {
            this.cacheComplete[i3] = (String) input.readObject();
        }
        int count4 = input.readInt();
        this.cacheFailed = new String[count4];
        for (int i4 = 0; i4 < count4; i4++) {
            this.cacheFailed[i4] = (String) input.readObject();
        }
        int count5 = input.readInt();
        this.videoError = new String[count5];
        for (int i5 = 0; i5 < count5; i5++) {
            this.videoError[i5] = (String) input.readObject();
        }
        this.buttons.clear();
        int count6 = input.readInt();
        for (int i6 = 0; i6 < count6; i6++) {
            VideoImage videoImage = (VideoImage) input.readObject();
            this.buttons.add(videoImage);
        }
        this.activities.clear();
        int count7 = input.readInt();
        for (int i7 = 0; i7 < count7; i7++) {
            VideoLogEvent videoLogEvent = (VideoLogEvent) input.readObject();
            this.activities.add(videoLogEvent);
        }
    }

    @Override // com.millennialmedia.android.CachedAd, java.io.Externalizable
    public void writeExternal(ObjectOutput output) throws IOException {
        super.writeExternal(output);
        output.writeBoolean(this.showControls);
        output.writeObject(this.onCompletionUrl);
        output.writeObject(this.webOverlayURL);
        output.writeObject(this.endOverlayURL);
        output.writeObject(this.cacheMissURL);
        output.writeObject(this.videoFileId);
        output.writeBoolean(this.stayInPlayer);
        output.writeBoolean(this.showCountdown);
        output.writeBoolean(this.reloadNonEndOverlayOnRestart);
        output.writeInt(this.startActivity.length);
        String[] arr$ = this.startActivity;
        for (String sa : arr$) {
            output.writeObject(sa);
        }
        output.writeInt(this.endActivity.length);
        String[] arr$2 = this.endActivity;
        for (String ea : arr$2) {
            output.writeObject(ea);
        }
        output.writeLong(this.duration);
        output.writeBoolean(this.usingInternalStorage);
        output.writeLong(this.contentLength);
        output.writeLong(this.closeDelayMillis);
        output.writeInt(this.cacheComplete.length);
        String[] arr$3 = this.cacheComplete;
        for (String cc : arr$3) {
            output.writeObject(cc);
        }
        output.writeInt(this.cacheFailed.length);
        String[] arr$4 = this.cacheFailed;
        for (String cf : arr$4) {
            output.writeObject(cf);
        }
        output.writeInt(this.videoError.length);
        String[] arr$5 = this.videoError;
        for (String ve : arr$5) {
            output.writeObject(ve);
        }
        output.writeInt(this.buttons.size());
        for (VideoImage button : this.buttons) {
            output.writeObject(button);
        }
        output.writeInt(this.activities.size());
        for (VideoLogEvent logEvent : this.activities) {
            output.writeObject(logEvent);
        }
    }

    void logBeginEvent() {
        if (this.startActivity != null) {
            MMLog.d(TAG, "Cached video begin event logged");
            for (int i = 0; i < this.startActivity.length; i++) {
                MMSDK.Event.logEvent(this.startActivity[i]);
            }
        }
    }

    void logEndEvent() {
        if (this.endActivity != null) {
            MMLog.d(TAG, "Cached video end event logged");
            for (int i = 0; i < this.endActivity.length; i++) {
                MMSDK.Event.logEvent(this.endActivity[i]);
            }
        }
    }

    static void playAd(Context context, String id, HttpRedirection.RedirectionListenerImpl listener) {
        if (id != null) {
            VideoAd videoAd = (VideoAd) AdCache.load(context, id);
            if (videoAd != null && videoAd.canShow(context, null, false)) {
                listener.updateLastVideoViewedTime();
                MMLog.v(TAG, String.format("mmvideo: attempting to play video %s", id));
                videoAd.show(context, listener.creatorAdImplInternalId);
                listener.startingVideo();
                return;
            }
            MMLog.w(TAG, String.format("mmvideo: Ad %s cannot be shown at this time.", id));
        }
    }

    void setDtoCachedVideo(DTOCachedVideo dto) {
        this.cachedVideoDto = dto;
    }

    boolean hasEndCard() {
        for (VideoImage button : this.buttons) {
            if (button.isLeaveBehind) {
                return true;
            }
        }
        return false;
    }
}
