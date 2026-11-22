package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.internal.iz;
import com.google.android.gms.internal.ja;
import com.google.android.gms.internal.jb;
import com.google.android.gms.internal.jc;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jv;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    final C0038a LJ;
    protected int LL;
    protected ImageManager.OnImageLoadedListener LN;
    protected int LR;
    protected int LK = 0;
    protected boolean LM = false;
    private boolean LO = true;
    private boolean LP = false;
    private boolean LQ = true;

    /* JADX INFO: renamed from: com.google.android.gms.common.images.a$a, reason: collision with other inner class name */
    static final class C0038a {
        public final Uri uri;

        public C0038a(Uri uri) {
            this.uri = uri;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0038a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return jv.equal(((C0038a) obj).uri, this.uri);
        }

        public int hashCode() {
            return jv.hashCode(this.uri);
        }
    }

    public static final class b extends a {
        private WeakReference<ImageView> LS;

        public b(ImageView imageView, int i) {
            super(null, i);
            je.f(imageView);
            this.LS = new WeakReference<>(imageView);
        }

        public b(ImageView imageView, Uri uri) {
            super(uri, 0);
            je.f(imageView);
            this.LS = new WeakReference<>(imageView);
        }

        private void a(ImageView imageView, Drawable drawable, boolean z, boolean z2, boolean z3) {
            boolean z4 = (z2 || z3) ? false : true;
            if (z4 && (imageView instanceof jb)) {
                int iHj = ((jb) imageView).hj();
                if (this.LL != 0 && iHj == this.LL) {
                    return;
                }
            }
            boolean zB = b(z, z2);
            Drawable drawableNewDrawable = (!this.LM || drawable == null) ? drawable : drawable.getConstantState().newDrawable();
            if (zB) {
                drawableNewDrawable = a(imageView.getDrawable(), drawableNewDrawable);
            }
            imageView.setImageDrawable(drawableNewDrawable);
            if (imageView instanceof jb) {
                jb jbVar = (jb) imageView;
                jbVar.g(z3 ? this.LJ.uri : null);
                jbVar.aB(z4 ? this.LL : 0);
            }
            if (zB) {
                ((iz) drawableNewDrawable).startTransition(250);
            }
        }

        @Override // com.google.android.gms.common.images.a
        protected void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageView imageView = this.LS.get();
            if (imageView != null) {
                a(imageView, drawable, z, z2, z3);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageView imageView = this.LS.get();
            ImageView imageView2 = ((b) obj).LS.get();
            return (imageView2 == null || imageView == null || !jv.equal(imageView2, imageView)) ? false : true;
        }

        public int hashCode() {
            return 0;
        }
    }

    public static final class c extends a {
        private WeakReference<ImageManager.OnImageLoadedListener> LT;

        public c(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
            super(uri, 0);
            je.f(onImageLoadedListener);
            this.LT = new WeakReference<>(onImageLoadedListener);
        }

        @Override // com.google.android.gms.common.images.a
        protected void a(Drawable drawable, boolean z, boolean z2, boolean z3) {
            ImageManager.OnImageLoadedListener onImageLoadedListener;
            if (z2 || (onImageLoadedListener = this.LT.get()) == null) {
                return;
            }
            onImageLoadedListener.onImageLoaded(this.LJ.uri, drawable, z3);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            ImageManager.OnImageLoadedListener onImageLoadedListener = this.LT.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = cVar.LT.get();
            return onImageLoadedListener2 != null && onImageLoadedListener != null && jv.equal(onImageLoadedListener2, onImageLoadedListener) && jv.equal(cVar.LJ, this.LJ);
        }

        public int hashCode() {
            return jv.hashCode(this.LJ);
        }
    }

    public a(Uri uri, int i) {
        this.LL = 0;
        this.LJ = new C0038a(uri);
        this.LL = i;
    }

    private Drawable a(Context context, jc jcVar, int i) {
        Resources resources = context.getResources();
        if (this.LR <= 0) {
            return resources.getDrawable(i);
        }
        jc.a aVar = new jc.a(i, this.LR);
        Drawable drawable = jcVar.get(aVar);
        if (drawable != null) {
            return drawable;
        }
        Drawable drawable2 = resources.getDrawable(i);
        if ((this.LR & 1) != 0) {
            drawable2 = a(resources, drawable2);
        }
        jcVar.put(aVar, drawable2);
        return drawable2;
    }

    protected Drawable a(Resources resources, Drawable drawable) {
        return ja.a(resources, drawable);
    }

    protected iz a(Drawable drawable, Drawable drawable2) {
        if (drawable == null) {
            drawable = null;
        } else if (drawable instanceof iz) {
            drawable = ((iz) drawable).hh();
        }
        return new iz(drawable, drawable2);
    }

    void a(Context context, Bitmap bitmap, boolean z) {
        je.f(bitmap);
        if ((this.LR & 1) != 0) {
            bitmap = ja.a(bitmap);
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        if (this.LN != null) {
            this.LN.onImageLoaded(this.LJ.uri, bitmapDrawable, true);
        }
        a(bitmapDrawable, z, false, true);
    }

    void a(Context context, jc jcVar) {
        if (this.LQ) {
            a(this.LK != 0 ? a(context, jcVar, this.LK) : null, false, true, false);
        }
    }

    void a(Context context, jc jcVar, boolean z) {
        Drawable drawableA = this.LL != 0 ? a(context, jcVar, this.LL) : null;
        if (this.LN != null) {
            this.LN.onImageLoaded(this.LJ.uri, drawableA, false);
        }
        a(drawableA, z, false, false);
    }

    protected abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);

    public void az(int i) {
        this.LL = i;
    }

    protected boolean b(boolean z, boolean z2) {
        return this.LO && !z2 && (!z || this.LP);
    }
}
