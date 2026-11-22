package com.facebook.ads.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

/* JADX INFO: loaded from: classes.dex */
public class w extends AsyncTask {
    private static final String a = w.class.getSimpleName();
    private final ImageView b;
    private x c;

    public w(ImageView imageView) {
        this.b = imageView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap doInBackground(String... strArr) {
        Bitmap bitmapDecodeByteArray;
        Exception e;
        String str = strArr[0];
        try {
            HttpEntity entity = p.b().execute(new HttpGet(str)).getEntity();
            byte[] byteArray = EntityUtils.toByteArray(entity);
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            try {
                entity.consumeContent();
            } catch (Exception e2) {
                e = e2;
                Log.e(a, "Error downloading image: " + str, e);
            }
        } catch (Exception e3) {
            bitmapDecodeByteArray = null;
            e = e3;
        }
        return bitmapDecodeByteArray;
    }

    public w a(x xVar) {
        this.c = xVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Bitmap bitmap) {
        this.b.setImageBitmap(bitmap);
        if (this.c != null) {
            this.c.d();
        }
    }
}
