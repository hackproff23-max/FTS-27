package com.flurry.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.flurry.android.impl.ads.protocol.v13.NativeAsset;
import com.flurry.android.impl.ads.protocol.v13.NativeAssetType;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class m {
    private static final String a = m.class.getSimpleName();

    public String a(NativeAsset nativeAsset, int i) {
        File fileA = i.a().l().a(i, nativeAsset.value);
        return fileA == null ? nativeAsset.value : "file://" + fileA.getAbsolutePath();
    }

    public View a(Context context, NativeAsset nativeAsset, int i) {
        View imageView = null;
        if (context != null && nativeAsset != null) {
            switch (nativeAsset.type) {
                case STRING:
                    imageView = new TextView(context);
                    break;
                case IMAGE:
                    imageView = new ImageView(context);
                    break;
            }
            a(nativeAsset, imageView, i);
        }
        return imageView;
    }

    public void a(NativeAsset nativeAsset, View view, int i) {
        if (nativeAsset != null && view != null) {
            switch (nativeAsset.type) {
                case STRING:
                    a(nativeAsset, view);
                    break;
                case IMAGE:
                    b(nativeAsset, view, i);
                    break;
            }
        }
    }

    private void a(NativeAsset nativeAsset, View view) {
        if (nativeAsset != null && NativeAssetType.STRING.equals(nativeAsset.type)) {
            if (view == null || !(view instanceof TextView)) {
                gd.e(a, "The view must be an instance of TextView in order to load the asset");
            } else {
                ((TextView) view).setText(nativeAsset.value);
            }
        }
    }

    private void b(NativeAsset nativeAsset, View view, int i) {
        if (nativeAsset != null && !TextUtils.isEmpty(nativeAsset.value) && NativeAssetType.IMAGE.equals(nativeAsset.type)) {
            if (view == null || !(view instanceof ImageView)) {
                gd.e(a, "The view must be an instance of ImageView in order to load the asset");
                return;
            }
            final ImageView imageView = (ImageView) view;
            File fileA = i.a().l().a(i, nativeAsset.value);
            if (fileA == null) {
                gd.a(3, a, "Cached asset not available for image:" + nativeAsset.value);
                gk gkVar = new gk();
                gkVar.a(nativeAsset.value);
                gkVar.a(40000);
                gkVar.a(gl.a.kGet);
                gkVar.b(new ck());
                gkVar.a((gk.a) new gk.a<Void, Bitmap>() { // from class: com.flurry.sdk.m.1
                    @Override // com.flurry.sdk.gk.a
                    public void a(gk<Void, Bitmap> gkVar2, final Bitmap bitmap) {
                        gd.a(3, m.a, "Image request -- HTTP status code is:" + gkVar2.e());
                        if (gkVar2.c()) {
                            fp.a().a(new hq() { // from class: com.flurry.sdk.m.1.1
                                @Override // com.flurry.sdk.hq
                                public void safeRun() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                });
                fn.a().a(this, gkVar);
                return;
            }
            gd.a(3, a, "Cached asset present for image:" + nativeAsset.value);
            final String absolutePath = fileA.getAbsolutePath();
            fp.a().a(new hq() { // from class: com.flurry.sdk.m.2
                @Override // com.flurry.sdk.hq
                public void safeRun() {
                    imageView.setImageBitmap(BitmapFactory.decodeFile(absolutePath));
                }
            });
        }
    }
}
