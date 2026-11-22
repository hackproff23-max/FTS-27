package com.mopub.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public final class HttpResponses {
    private HttpResponses() {
    }

    public static Bitmap asBitmap(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        byte[] bytes = downloadResponse.getByteArray();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static JSONObject asJsonObject(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        try {
            String responseString = asResponseString(downloadResponse);
            JSONTokener tokener = new JSONTokener(responseString);
            return new JSONObject(tokener);
        } catch (Exception e) {
            return null;
        }
    }

    public static String asResponseString(DownloadResponse downloadResponse) {
        if (downloadResponse == null) {
            return null;
        }
        try {
            return new String(downloadResponse.getByteArray(), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }
}
