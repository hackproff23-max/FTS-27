package com.facebook.ads;

import android.content.Context;
import android.widget.ImageView;
import com.facebook.ads.a.ac;
import com.facebook.ads.a.b;
import com.facebook.ads.a.c;
import com.facebook.ads.a.n;
import com.facebook.ads.a.w;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NativeAd extends ac {
    private volatile boolean loadRequested;

    public static class Image {
        private final int height;
        private final String url;
        private final int width;

        private Image(String str, int i, int i2) {
            this.url = str;
            this.width = i;
            this.height = i2;
        }

        public static Image fromJSONObject(JSONObject jSONObject) {
            String strOptString;
            if (jSONObject == null || (strOptString = jSONObject.optString("url")) == null) {
                return null;
            }
            return new Image(strOptString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0));
        }

        public int getHeight() {
            return this.height;
        }

        public String getUrl() {
            return this.url;
        }

        public int getWidth() {
            return this.width;
        }
    }

    public static class Rating {
        private final double scale;
        private final double value;

        private Rating(double d, double d2) {
            this.value = d;
            this.scale = d2;
        }

        public static Rating fromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            double dOptDouble = jSONObject.optDouble("value", 0.0d);
            double dOptDouble2 = jSONObject.optDouble("scale", 0.0d);
            if (dOptDouble == 0.0d || dOptDouble2 == 0.0d) {
                return null;
            }
            return new Rating(dOptDouble, dOptDouble2);
        }

        public double getScale() {
            return this.scale;
        }

        public double getValue() {
            return this.value;
        }
    }

    public NativeAd(Context context, String str) {
        super(context, str, AdSize.INTERSTITIAL, n.NATIVE_UNKNOWN, false);
    }

    public static void downloadAndDisplayImage(Image image, ImageView imageView) {
        new w(imageView).execute(image.getUrl());
    }

    @Override // com.facebook.ads.a.ac, com.facebook.ads.Ad
    public void loadAd() {
        if (this.loadRequested) {
            IllegalStateException illegalStateException = new IllegalStateException("Ad already loaded");
            c.a(b.a(illegalStateException));
            throw illegalStateException;
        }
        this.loadRequested = true;
        super.loadAd();
    }
}
