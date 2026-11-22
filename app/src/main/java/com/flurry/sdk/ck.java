package com.flurry.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class ck implements gx<Bitmap> {
    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, Bitmap bitmap) throws IOException {
        if (outputStream == null || bitmap == null) {
        } else {
            throw new UnsupportedOperationException("Serialization for bitmaps is not yet implemented");
        }
    }

    @Override // com.flurry.sdk.gx
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bitmap b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return BitmapFactory.decodeStream(inputStream);
    }
}
