package com.millennialmedia.android;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.ServerProtocol;
import com.mopub.common.Constants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMMedia extends MMJSObject {
    private static final String PATH = "path";
    private static final String TAG = "BridgeMMMedia";
    private static Object pickerActivityObject;
    MediaScannerConnection mediaScanner;
    private String IS_SOURCE_TYPE_AVAILABLE = "isSourceTypeAvailable";
    private String AVAILABLE_SOURCE_TYPES = "availableSourceTypes";
    private String GET_PICTURE = "getPicture";
    private String WRITE_TO_PHOTO_LIBRARY = "writeToPhotoLibrary";
    private String PLAY_VIDEO = UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO;
    private String STOP_AUDIO = "stopAudio";
    private String GET_DEVICE_VOLUME = "getDeviceVolume";
    private String PLAY_AUDIO = "playAudio";
    private String PLAY_SOUND = "playSound";

    BridgeMMMedia() {
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (this.IS_SOURCE_TYPE_AVAILABLE.equals(name)) {
            MMJSResponse response = isSourceTypeAvailable(arguments);
            return response;
        }
        if (this.AVAILABLE_SOURCE_TYPES.equals(name)) {
            MMJSResponse response2 = availableSourceTypes(arguments);
            return response2;
        }
        if (this.GET_PICTURE.equals(name)) {
            MMJSResponse response3 = getPicture(arguments);
            return response3;
        }
        if (this.WRITE_TO_PHOTO_LIBRARY.equals(name)) {
            MMJSResponse response4 = writeToPhotoLibrary(arguments);
            return response4;
        }
        if (this.PLAY_VIDEO.equals(name)) {
            MMJSResponse response5 = playVideo(arguments);
            return response5;
        }
        if (this.STOP_AUDIO.equals(name)) {
            MMJSResponse response6 = stopAudio(arguments);
            return response6;
        }
        if (this.GET_DEVICE_VOLUME.equals(name)) {
            MMJSResponse response7 = getDeviceVolume(arguments);
            return response7;
        }
        if (this.PLAY_AUDIO.equals(name)) {
            MMJSResponse response8 = playAudio(arguments);
            return response8;
        }
        if (!this.PLAY_SOUND.equals(name)) {
            return null;
        }
        MMJSResponse response9 = playSound(arguments);
        return response9;
    }

    private boolean isCameraAvailable() {
        Context context = this.contextRef.get();
        if (context == null || context.getPackageManager().checkPermission("android.permission.CAMERA", context.getPackageName()) != 0) {
            return false;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 65536);
        return list.size() > 0;
    }

    private boolean isPictureChooserAvailable() {
        Context context = this.contextRef.get();
        if (context == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 65536);
        return list.size() > 0;
    }

    public MMJSResponse isSourceTypeAvailable(Map<String, String> arguments) {
        String type = arguments.get("sourceType");
        if (type != null) {
            if (type.equalsIgnoreCase("Camera") && isCameraAvailable()) {
                return MMJSResponse.responseWithSuccess(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            }
            if (type.equalsIgnoreCase("Photo Library") && isPictureChooserAvailable()) {
                return MMJSResponse.responseWithSuccess(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            }
        }
        return MMJSResponse.responseWithError("false");
    }

    public MMJSResponse availableSourceTypes(Map<String, String> arguments) {
        JSONArray jsonArray = new JSONArray();
        if (isCameraAvailable()) {
            jsonArray.put("Camera");
        }
        if (isPictureChooserAvailable()) {
            jsonArray.put("Photo Library");
        }
        MMJSResponse response = new MMJSResponse();
        response.result = 1;
        response.response = jsonArray;
        return response;
    }

    static class PickerActivity extends MMBaseActivity {
        private Uri fileUri;
        boolean hasRequestedPic = false;

        PickerActivity() {
        }

        @Override // com.millennialmedia.android.MMBaseActivity
        public Object onRetainNonConfigurationInstance() {
            super.onRetainNonConfigurationInstance();
            Bundle outState = new Bundle();
            outState.putBoolean("hasRequestedPic", this.hasRequestedPic);
            return outState;
        }

        @Override // com.millennialmedia.android.MMBaseActivity
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getLastNonConfigurationInstance() != null) {
                Bundle inState = (Bundle) getLastNonConfigurationInstance();
                this.hasRequestedPic = inState.getBoolean("hasRequestedPic");
            }
            if (!this.hasRequestedPic) {
                if (getIntent().getStringExtra(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY).equalsIgnoreCase("Camera")) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    this.fileUri = getIntent().getData();
                    intent.putExtra("return-data", true);
                    this.hasRequestedPic = true;
                    startActivityForResult(intent, 0);
                    return;
                }
                Intent intent2 = new Intent();
                intent2.setType("image/*");
                intent2.setAction("android.intent.action.GET_CONTENT");
                this.hasRequestedPic = true;
                startActivityForResult(intent2, 0);
            }
        }

        /* JADX WARN: Code duplicated, block: B:23:0x0077 A[Catch: Exception -> 0x00a5, TRY_LEAVE, TryCatch #14 {Exception -> 0x00a5, blocks: (B:21:0x0072, B:23:0x0077), top: B:133:0x0072, outer: #0 }] */
        @Override // com.millennialmedia.android.MMBaseActivity
        protected void onActivityResult(int requestCode, int resultCode, Intent data) throws Throwable {
            Cursor cursor;
            int index;
            super.onActivityResult(requestCode, resultCode, data);
            if (data != null) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    Uri contentUri = data.getData();
                    if (contentUri == null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                            try {
                                try {
                                    File file = new File(getIntent().getData().getPath());
                                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                                    byte[] bitmapdata = bos.toByteArray();
                                    ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                                    OutputStream out2 = new FileOutputStream(file);
                                    try {
                                        byte[] buf = new byte[1024];
                                        while (true) {
                                            int len = bs.read(buf);
                                            if (len <= 0) {
                                                break;
                                            } else {
                                                out2.write(buf, 0, len);
                                            }
                                        }
                                        if (0 != 0) {
                                            try {
                                                in.close();
                                            } catch (Exception e) {
                                                try {
                                                    MMLog.e(BridgeMMMedia.TAG, "Error closing file", e);
                                                } catch (Exception e2) {
                                                    e = e2;
                                                    MMLog.e(BridgeMMMedia.TAG, "Error with picture: ", e);
                                                }
                                            }
                                        }
                                        if (out2 != null) {
                                            out2.close();
                                        }
                                    } catch (Exception e3) {
                                        e = e3;
                                        out = out2;
                                        MMLog.e(BridgeMMMedia.TAG, "Problem getting bitmap from data", e);
                                        if (0 != 0) {
                                            try {
                                                in.close();
                                                if (out != null) {
                                                    out.close();
                                                }
                                            } catch (Exception e4) {
                                                MMLog.e(BridgeMMMedia.TAG, "Error closing file", e4);
                                            }
                                        } else if (out != null) {
                                            out.close();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        out = out2;
                                        if (0 != 0) {
                                            try {
                                                in.close();
                                            } catch (Exception e5) {
                                                MMLog.e(BridgeMMMedia.TAG, "Error closing file", e5);
                                                throw th;
                                            }
                                        }
                                        if (out != null) {
                                            out.close();
                                        }
                                        throw th;
                                    }
                                } catch (Exception e6) {
                                    e = e6;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    } else if (contentUri != null) {
                        String[] proj = {"_data"};
                        ContentResolver resolver = getContentResolver();
                        if (resolver != null && (cursor = resolver.query(contentUri, proj, null, null, null)) != null && (index = cursor.getColumnIndex("_data")) != -1) {
                            cursor.moveToFirst();
                            File chosenFile = new File(cursor.getString(index));
                            cursor.close();
                            try {
                                try {
                                    File file2 = new File(getIntent().getData().getPath());
                                    InputStream in2 = new FileInputStream(chosenFile);
                                    try {
                                        OutputStream out3 = new FileOutputStream(file2);
                                        try {
                                            byte[] buf2 = new byte[1024];
                                            while (true) {
                                                int len2 = in2.read(buf2);
                                                if (len2 <= 0) {
                                                    break;
                                                } else {
                                                    out3.write(buf2, 0, len2);
                                                }
                                            }
                                            if (in2 != null) {
                                                try {
                                                    in2.close();
                                                } catch (Exception e7) {
                                                    try {
                                                        MMLog.e(BridgeMMMedia.TAG, "Error closing file", e7);
                                                        out = out3;
                                                        in = in2;
                                                    } catch (Exception e8) {
                                                        e = e8;
                                                        MMLog.e(BridgeMMMedia.TAG, "Error with picture: ", e);
                                                    }
                                                }
                                            }
                                            if (out3 != null) {
                                                out3.close();
                                            }
                                            out = out3;
                                            in = in2;
                                        } catch (Exception e9) {
                                            e = e9;
                                            out = out3;
                                            in = in2;
                                            MMLog.e(BridgeMMMedia.TAG, "Error copying image", e);
                                            if (in != null) {
                                                try {
                                                    in.close();
                                                } catch (Exception e10) {
                                                    MMLog.e(BridgeMMMedia.TAG, "Error closing file", e10);
                                                }
                                            }
                                            if (out != null) {
                                                out.close();
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            out = out3;
                                            in = in2;
                                            if (in != null) {
                                                try {
                                                    in.close();
                                                } catch (Exception e11) {
                                                    MMLog.e(BridgeMMMedia.TAG, "Error closing file", e11);
                                                    throw th;
                                                }
                                            }
                                            if (out != null) {
                                                out.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e12) {
                                        e = e12;
                                        in = in2;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        in = in2;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } catch (Exception e13) {
                                e = e13;
                            }
                        }
                    }
                } catch (Exception e14) {
                    e = e14;
                }
            }
            synchronized (BridgeMMMedia.pickerActivityObject) {
                BridgeMMMedia.pickerActivityObject.notify();
            }
            finish();
        }
    }

    static Bitmap resizeImage(Bitmap image, String contentMode, int toW, int toH, int quality) {
        float horizontalRatio = toW / image.getWidth();
        float verticalRatio = toH / image.getHeight();
        if (contentMode.equals("Center")) {
            return centerOfImage(image, toW, toH);
        }
        if (contentMode.equals("ScaleToAspectFit")) {
            float ratio = Math.min(horizontalRatio, verticalRatio);
            int newW = (int) (image.getWidth() * ratio);
            int newH = (int) (image.getHeight() * ratio);
            return resizeImage(image, newW, newH, quality);
        }
        if (contentMode.equals("ScaleToAspectFill")) {
            float ratio2 = Math.max(horizontalRatio, verticalRatio);
            int newW2 = (int) (image.getWidth() * ratio2);
            int newH2 = (int) (image.getHeight() * ratio2);
            return cropImage(resizeImage(image, newW2, newH2, quality), 0, 0, toW, toH);
        }
        return resizeImage(image, toW, toH, quality);
    }

    private static Bitmap resizeImage(Bitmap image, int newW, int newH, int quality) {
        return Bitmap.createScaledBitmap(image, newW, newH, true);
    }

    private static Bitmap centerOfImage(Bitmap image, int width, int height) {
        float x = (image.getWidth() - width) / 2;
        float y = (image.getHeight() - height) / 2;
        return cropImage(image, (int) x, (int) y, width, height);
    }

    private static Bitmap cropImage(Bitmap bitmap, int left, int top, int width, int height) {
        return Bitmap.createBitmap(bitmap, left, top, width, height);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00ae A[Catch: Exception -> 0x014e, TryCatch #0 {Exception -> 0x014e, blocks: (B:28:0x00a9, B:30:0x00ae, B:32:0x00b3), top: B:95:0x00a9 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00b3 A[Catch: Exception -> 0x014e, TRY_LEAVE, TryCatch #0 {Exception -> 0x014e, blocks: (B:28:0x00a9, B:30:0x00ae, B:32:0x00b3), top: B:95:0x00a9 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00ea A[Catch: IOException -> 0x00ee, TRY_LEAVE, TryCatch #4 {IOException -> 0x00ee, blocks: (B:42:0x00e5, B:44:0x00ea), top: B:101:0x00e5 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0104 A[Catch: IOException -> 0x0108, TRY_LEAVE, TryCatch #6 {IOException -> 0x0108, blocks: (B:50:0x00ff, B:52:0x0104), top: B:105:0x00ff }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0136 A[Catch: Exception -> 0x0140, TryCatch #5 {Exception -> 0x0140, blocks: (B:63:0x0131, B:65:0x0136, B:67:0x013b), top: B:103:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x013b A[Catch: Exception -> 0x0140, TRY_LEAVE, TryCatch #5 {Exception -> 0x0140, blocks: (B:63:0x0131, B:65:0x0136, B:67:0x013b), top: B:103:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x0164 A[Catch: Exception -> 0x016d, TryCatch #1 {Exception -> 0x016d, blocks: (B:75:0x015f, B:77:0x0164, B:79:0x0169), top: B:97:0x015f }] */
    /* JADX WARN: Code duplicated, block: B:79:0x0169 A[Catch: Exception -> 0x016d, TRY_LEAVE, TryCatch #1 {Exception -> 0x016d, blocks: (B:75:0x015f, B:77:0x0164, B:79:0x0169), top: B:97:0x015f }] */
    private static final byte[] scaleBitmapToBytes(File file, int w, int h, String contentMode) throws Throwable {
        FileInputStream fis = null;
        FileInputStream fis2 = null;
        Bitmap scaledBitmap = null;
        try {
            try {
                FileInputStream fis3 = new FileInputStream(file);
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(fis3, null, options);
                    int height = options.outHeight;
                    int width = options.outWidth;
                    int inSampleSize = 1;
                    if (height > h || width > w) {
                        if (width > height) {
                            inSampleSize = Math.round(height / h);
                        } else {
                            inSampleSize = Math.round(width / w);
                        }
                    }
                    FileInputStream fis4 = new FileInputStream(file);
                    try {
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = inSampleSize;
                        scaledBitmap = BitmapFactory.decodeStream(fis4, null, options);
                        if (fis3 != null) {
                            try {
                                fis3.close();
                            } catch (IOException e) {
                                MMLog.e(TAG, "Error closing file", e);
                            }
                        }
                        if (fis4 != null) {
                            fis4.close();
                        }
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        fis2 = fis4;
                        fis = fis3;
                        MMLog.e(TAG, "Can't find file to scale bitmap", e);
                        if (fis != null) {
                            try {
                                fis.close();
                                if (fis2 != null) {
                                    fis2.close();
                                }
                            } catch (IOException e3) {
                                MMLog.e(TAG, "Error closing file", e3);
                            }
                        } else if (fis2 != null) {
                            fis2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fis2 = fis4;
                        fis = fis3;
                        if (fis != null) {
                            try {
                                fis.close();
                                if (fis2 != null) {
                                    fis2.close();
                                }
                            } catch (IOException e4) {
                                MMLog.e(TAG, "Error closing file", e4);
                                throw th;
                            }
                        } else if (fis2 != null) {
                            fis2.close();
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                    fis = fis3;
                } catch (Throwable th2) {
                    th = th2;
                    fis = fis3;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
            }
            byte[] contents = null;
            if (scaledBitmap != null) {
                Bitmap finalBitmap = resizeImage(scaledBitmap, contentMode, w, h, 1);
                ByteArrayOutputStream baos = null;
                try {
                    try {
                        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                        try {
                            if (contentMode.equals("")) {
                                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                            } else {
                                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
                            }
                            contents = baos2.toByteArray();
                            if (scaledBitmap != null) {
                                try {
                                    scaledBitmap.recycle();
                                    if (finalBitmap != null) {
                                        finalBitmap.recycle();
                                    }
                                    if (baos2 != null) {
                                        baos2.close();
                                    }
                                } catch (Exception e7) {
                                    MMLog.e(TAG, "Error closing file", e7);
                                }
                            } else {
                                if (finalBitmap != null) {
                                    finalBitmap.recycle();
                                }
                                if (baos2 != null) {
                                    baos2.close();
                                }
                            }
                        } catch (Exception e8) {
                            e = e8;
                            baos = baos2;
                            contents = null;
                            MMLog.e(TAG, "Error scaling bitmap", e);
                            if (scaledBitmap != null) {
                                try {
                                    scaledBitmap.recycle();
                                    if (finalBitmap != null) {
                                        finalBitmap.recycle();
                                    }
                                    if (baos != null) {
                                        baos.close();
                                    }
                                } catch (Exception e9) {
                                    MMLog.e(TAG, "Error closing file", e9);
                                }
                            } else {
                                if (finalBitmap != null) {
                                    finalBitmap.recycle();
                                }
                                if (baos != null) {
                                    baos.close();
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            baos = baos2;
                            if (scaledBitmap != null) {
                                try {
                                    scaledBitmap.recycle();
                                    if (finalBitmap != null) {
                                        finalBitmap.recycle();
                                    }
                                    if (baos != null) {
                                        baos.close();
                                    }
                                } catch (Exception e10) {
                                    MMLog.e(TAG, "Error closing file", e10);
                                    throw th;
                                }
                            } else {
                                if (finalBitmap != null) {
                                    finalBitmap.recycle();
                                }
                                if (baos != null) {
                                    baos.close();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e11) {
                        e = e11;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }
            return contents;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    /* JADX WARN: Code duplicated, block: B:54:0x0101  */
    public synchronized MMJSResponse getPicture(Map<String, String> arguments) {
        MMJSResponse response;
        Context context = this.contextRef.get();
        String type = arguments.get("sourceType");
        String height = arguments.get("constrainHeight");
        String width = arguments.get("constrainWidth");
        String contentMode = arguments.get("contentMode");
        if (contentMode == null) {
            contentMode = "";
        }
        if (height == null || width == null) {
            response = MMJSResponse.responseWithError("Missing constrainHeight and/or constrainWidth");
        } else {
            int h = (int) Float.parseFloat(height);
            int w = (int) Float.parseFloat(width);
            if (h * w > 360000) {
                response = MMJSResponse.responseWithError("constrainHeight * constrainWidth > 360000");
            } else if (context != null && type != null) {
                File file = new File(AdCache.getInternalCacheDirectory(context), "tmp_mm_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                if ((type.equalsIgnoreCase("Camera") && isCameraAvailable()) || ((type.equalsIgnoreCase("Photo Library") || type.equalsIgnoreCase("PhotoLibrary")) && isPictureChooserAvailable())) {
                    try {
                        try {
                            pickerActivityObject = new Object();
                            synchronized (pickerActivityObject) {
                                Utils.IntentUtils.startPickerActivity(context, file, type);
                                pickerActivityObject.wait();
                            }
                            pickerActivityObject = null;
                        } catch (Exception e) {
                            MMLog.e(TAG, "Error with pickerActivity synchronization", e);
                            pickerActivityObject = null;
                        }
                        if (file != null && file.exists() && file.length() > 0) {
                            byte[] contents = scaleBitmapToBytes(file, w, h, contentMode);
                            file.delete();
                            if (contents != null) {
                                response = new MMJSResponse();
                                response.result = 1;
                                response.dataResponse = contents;
                            } else {
                                response = null;
                            }
                        } else {
                            response = null;
                        }
                    } catch (Throwable th) {
                        pickerActivityObject = null;
                        throw th;
                    }
                } else {
                    response = null;
                }
            } else {
                response = null;
            }
        }
        return response;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x004d A[Catch: all -> 0x0096, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0031, B:11:0x0039, B:13:0x0045, B:16:0x004d, B:18:0x005d, B:19:0x0079, B:21:0x0086, B:22:0x008d), top: B:28:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x005d A[Catch: all -> 0x0096, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0031, B:11:0x0039, B:13:0x0045, B:16:0x004d, B:18:0x005d, B:19:0x0079, B:21:0x0086, B:22:0x008d), top: B:28:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:19:0x0079 A[Catch: all -> 0x0096, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0031, B:11:0x0039, B:13:0x0045, B:16:0x004d, B:18:0x005d, B:19:0x0079, B:21:0x0086, B:22:0x008d), top: B:28:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0086 A[Catch: all -> 0x0096, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0031, B:11:0x0039, B:13:0x0045, B:16:0x004d, B:18:0x005d, B:19:0x0079, B:21:0x0086, B:22:0x008d), top: B:28:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x008d A[Catch: all -> 0x0096, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0013, B:7:0x0019, B:9:0x0031, B:11:0x0039, B:13:0x0045, B:16:0x004d, B:18:0x005d, B:19:0x0079, B:21:0x0086, B:22:0x008d), top: B:28:0x0001 }] */
    public synchronized MMJSResponse writeToPhotoLibrary(Map<String, String> arguments) {
        MMJSResponse mMJSResponseResponseWithSuccess;
        File source;
        Context context = this.contextRef.get();
        String pathString = arguments.get(PATH);
        if (context != null && !TextUtils.isEmpty(pathString)) {
            Uri path = Uri.parse(arguments.get(PATH));
            String name = path.getLastPathSegment();
            String scheme = path.getScheme();
            if (scheme != null && scheme.equals(Constants.HTTP)) {
                String url = path.toString();
                if (!AdCache.downloadComponentExternalStorage(url, "Pictures", name, context)) {
                    mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError("Failed to download");
                } else {
                    source = AdCache.getDownloadFile(context, "Pictures", path.getLastPathSegment());
                    if (!source.exists()) {
                        mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError("No file at " + source.getAbsolutePath());
                    } else {
                        scanMedia(source.getAbsolutePath());
                        if (!AdCache.isExternalMounted()) {
                            mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError("Storage not mounted, cannot add image to photo library photo library");
                        } else {
                            mMJSResponseResponseWithSuccess = MMJSResponse.responseWithSuccess("Image saved to photo library");
                        }
                    }
                }
            } else {
                source = AdCache.getDownloadFile(context, "Pictures", path.getLastPathSegment());
                if (!source.exists()) {
                    mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError("No file at " + source.getAbsolutePath());
                } else {
                    scanMedia(source.getAbsolutePath());
                    if (!AdCache.isExternalMounted()) {
                        mMJSResponseResponseWithSuccess = MMJSResponse.responseWithError("Storage not mounted, cannot add image to photo library photo library");
                    } else {
                        mMJSResponseResponseWithSuccess = MMJSResponse.responseWithSuccess("Image saved to photo library");
                    }
                }
            }
        } else {
            mMJSResponseResponseWithSuccess = null;
        }
        return mMJSResponseResponseWithSuccess;
    }

    private void scanMedia(final String path) {
        Context context = this.contextRef.get();
        if (context != null) {
            this.mediaScanner = new MediaScannerConnection(context.getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient() { // from class: com.millennialmedia.android.BridgeMMMedia.1
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String path2, Uri uri) {
                    if (uri == null) {
                        MMLog.d(BridgeMMMedia.TAG, "Failed to scan " + path2);
                    }
                }

                @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
                public void onMediaScannerConnected() {
                    if (BridgeMMMedia.this.mediaScanner != null) {
                        BridgeMMMedia.this.mediaScanner.scanFile(path, null);
                    }
                }
            });
            if (this.mediaScanner != null) {
                this.mediaScanner.connect();
            }
        }
    }

    public MMJSResponse playVideo(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        String path = arguments.get(PATH);
        if (context != null && path != null) {
            if (path.startsWith(Constants.HTTP)) {
                Utils.IntentUtils.startVideoPlayerActivityWithData(context, path);
                return MMJSResponse.responseWithSuccess(path);
            }
            File file = AdCache.getDownloadFile(context, path);
            if (file.exists()) {
                Utils.IntentUtils.startVideoPlayerActivityWithData(context, file);
                return MMJSResponse.responseWithSuccess(file.getName());
            }
        }
        return null;
    }

    public MMJSResponse playAudio(Map<String, String> arguments) {
        Audio audio;
        Context context = this.contextRef.get();
        String path = arguments.get(PATH);
        if (context == null || path == null || (audio = Audio.sharedAudio(context)) == null) {
            return null;
        }
        if (audio.isPlaying()) {
            return MMJSResponse.responseWithError("Audio already playing.");
        }
        if (path.startsWith(Constants.HTTP)) {
            return audio.playAudio(Uri.parse(path), Boolean.parseBoolean(arguments.get("repeat")));
        }
        File file = AdCache.getDownloadFile(context, path);
        if (file.exists()) {
            return audio.playAudio(Uri.fromFile(file), Boolean.parseBoolean(arguments.get("repeat")));
        }
        return null;
    }

    public MMJSResponse playSound(Map<String, String> arguments) {
        Audio audio;
        if (this.contextRef == null) {
            return null;
        }
        Context context = this.contextRef.get();
        String path = arguments.get(PATH);
        if (context != null && path != null) {
            File file = AdCache.getDownloadFile(context, path);
            if (file.exists() && (audio = Audio.sharedAudio(this.contextRef.get())) != null) {
                return audio.playSound(file);
            }
        }
        return null;
    }

    public MMJSResponse stopAudio(Map<String, String> arguments) {
        Audio audio;
        if (this.contextRef == null || (audio = Audio.sharedAudio(this.contextRef.get())) == null) {
            return null;
        }
        return audio.stop();
    }

    public MMJSResponse getDeviceVolume(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        if (context == null) {
            return MMJSResponse.responseWithError("no volume available");
        }
        int volume = MMSDK.getMediaVolume(context);
        MMJSResponse response = MMJSResponse.responseWithSuccess();
        response.response = Integer.valueOf(volume);
        return response;
    }

    static class Audio implements MediaPlayer.OnCompletionListener {
        private static final int MAX_SOUNDS = 4;
        private static Audio sharedInstance;
        private OnLoadCompleteListener completionListener;
        CopyOnWriteArrayList<MediaPlayer.OnCompletionListener> completionListeners;
        private WeakReference<Context> contextRef;
        private MediaPlayer mediaPlayer;
        CopyOnWriteArrayList<PeriodicListener> periodicListeners;
        Runnable periodicUpdater = new Runnable() { // from class: com.millennialmedia.android.BridgeMMMedia.Audio.1
            @Override // java.lang.Runnable
            public void run() {
                if (Audio.this.mediaPlayer != null) {
                    if (Audio.this.mediaPlayer.isPlaying()) {
                        int currentPositionMillis = Audio.this.mediaPlayer.getCurrentPosition();
                        if (Audio.this.periodicListeners != null) {
                            for (PeriodicListener listener : Audio.this.periodicListeners) {
                                listener.onUpdate(currentPositionMillis);
                            }
                        }
                    }
                    MMSDK.runOnUiThreadDelayed(this, 500L);
                }
            }
        };
        private SoundPool soundPool;

        interface PeriodicListener {
            void onUpdate(int i);
        }

        private Audio() {
        }

        private Audio(Context context) {
            this.contextRef = new WeakReference<>(context.getApplicationContext());
        }

        boolean addCompletionListener(MediaPlayer.OnCompletionListener listener) {
            if (this.completionListeners == null) {
                this.completionListeners = new CopyOnWriteArrayList<>();
            }
            if (this.completionListeners.contains(listener)) {
                return false;
            }
            return this.completionListeners.add(listener);
        }

        boolean removeCompletionListener(MediaPlayer.OnCompletionListener listener) {
            if (this.completionListeners != null) {
                return this.completionListeners.remove(listener);
            }
            return false;
        }

        boolean addPeriodicListener(PeriodicListener listener) {
            if (this.periodicListeners == null) {
                this.periodicListeners = new CopyOnWriteArrayList<>();
            }
            if (this.periodicListeners.contains(listener)) {
                return false;
            }
            return this.periodicListeners.add(listener);
        }

        boolean removePeriodicListener(PeriodicListener listener) {
            if (this.periodicListeners != null) {
                return this.periodicListeners.remove(listener);
            }
            return false;
        }

        static synchronized Audio sharedAudio(Context context) {
            if (sharedInstance == null) {
                sharedInstance = new Audio(context);
            }
            return sharedInstance;
        }

        synchronized boolean isPlaying() {
            return this.mediaPlayer != null && this.mediaPlayer.isPlaying();
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public synchronized void onCompletion(MediaPlayer mp) {
            if (this.completionListeners != null) {
                for (MediaPlayer.OnCompletionListener listener : this.completionListeners) {
                    listener.onCompletion(this.mediaPlayer);
                }
            }
            if (this.mediaPlayer != null) {
                this.mediaPlayer.release();
                this.mediaPlayer = null;
            }
        }

        synchronized MMJSResponse playAudio(Uri uri, boolean loop) {
            try {
                if (this.mediaPlayer != null) {
                    this.mediaPlayer.release();
                    this.mediaPlayer = null;
                }
                this.mediaPlayer = MediaPlayer.create(this.contextRef.get(), uri);
                this.mediaPlayer.setLooping(loop);
                this.mediaPlayer.start();
                this.mediaPlayer.setOnCompletionListener(this);
                MMSDK.runOnUiThread(this.periodicUpdater);
            } catch (Exception e) {
                MMLog.e(BridgeMMMedia.TAG, "Exception in playAudio method", e.getCause());
            }
            return MMJSResponse.responseWithSuccess("Audio playback started");
        }

        synchronized MMJSResponse playSound(File file) {
            try {
                if (this.soundPool == null) {
                    this.soundPool = new SoundPool(4, 3, 0);
                    this.completionListener = new OnLoadCompleteListener(this.soundPool) { // from class: com.millennialmedia.android.BridgeMMMedia.Audio.2
                        @Override // com.millennialmedia.android.BridgeMMMedia.Audio.OnLoadCompleteListener
                        public synchronized void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                            if (soundPool != null) {
                                Context context = (Context) Audio.this.contextRef.get();
                                if (context != null) {
                                    AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                                    float streamVolume = (audioManager.getStreamVolume(3) + 0.0f) / audioManager.getStreamMaxVolume(3);
                                    soundPool.play(sampleId, streamVolume, streamVolume, 1, 0, 1.0f);
                                }
                            }
                        }
                    };
                }
                this.completionListener.testSample(this.soundPool.load(file.getAbsolutePath(), 1));
            } catch (Exception e) {
            }
            return MMJSResponse.responseWithSuccess("Sound playback started");
        }

        synchronized MMJSResponse stop() {
            if (this.mediaPlayer != null) {
                onCompletion(this.mediaPlayer);
            }
            if (this.soundPool != null) {
                this.soundPool.release();
                this.soundPool = null;
            }
            if (this.completionListener != null) {
                this.completionListener.release();
                this.completionListener = null;
            }
            return MMJSResponse.responseWithSuccess("Audio stopped");
        }

        private abstract class OnLoadCompleteListener {
            private static final int TEST_PERIOD_MS = 100;
            private ArrayList<Integer> sampleIds = new ArrayList<>();
            private SoundPool soundPool;
            private Timer timer;

            abstract void onLoadComplete(SoundPool soundPool, int i, int i2);

            public OnLoadCompleteListener(SoundPool soundPool) {
                this.soundPool = soundPool;
            }

            synchronized void testSample(int sampleId) {
                this.sampleIds.add(Integer.valueOf(sampleId));
                if (this.sampleIds.size() == 1) {
                    this.timer = new Timer();
                    this.timer.scheduleAtFixedRate(new TimerTask() { // from class: com.millennialmedia.android.BridgeMMMedia.Audio.OnLoadCompleteListener.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            ArrayList<Integer> completedOnes = new ArrayList<>();
                            for (Integer sampleId2 : OnLoadCompleteListener.this.sampleIds) {
                                int streamId = OnLoadCompleteListener.this.soundPool.play(sampleId2.intValue(), 0.0f, 0.0f, 0, 0, 1.0f);
                                if (streamId != 0) {
                                    OnLoadCompleteListener.this.soundPool.stop(streamId);
                                    OnLoadCompleteListener.this.onLoadComplete(OnLoadCompleteListener.this.soundPool, sampleId2.intValue(), 0);
                                    completedOnes.add(sampleId2);
                                }
                            }
                            OnLoadCompleteListener.this.sampleIds.removeAll(completedOnes);
                            if (OnLoadCompleteListener.this.sampleIds.size() == 0) {
                                OnLoadCompleteListener.this.timer.cancel();
                                OnLoadCompleteListener.this.timer.purge();
                            }
                        }
                    }, 0L, 100L);
                }
            }

            synchronized void release() {
                if (this.timer != null) {
                    this.timer.cancel();
                    this.timer.purge();
                }
            }
        }
    }
}
