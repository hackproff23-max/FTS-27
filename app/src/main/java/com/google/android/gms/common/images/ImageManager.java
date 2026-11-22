package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.jc;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.kj;
import com.google.android.gms.internal.ll;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class ImageManager {
    private static final Object Lu = new Object();
    private static HashSet<Uri> Lv = new HashSet<>();
    private static ImageManager Lw;
    private static ImageManager Lx;
    private final jc LA;
    private final Map<com.google.android.gms.common.images.a, ImageReceiver> LB;
    private final Map<Uri, ImageReceiver> LC;
    private final Map<Uri, Long> LD;
    private final b Lz;
    private final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService Ly = Executors.newFixedThreadPool(4);

    private final class ImageReceiver extends ResultReceiver {
        private final ArrayList<com.google.android.gms.common.images.a> LE;
        private final Uri mUri;

        ImageReceiver(Uri uri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = uri;
            this.LE = new ArrayList<>();
        }

        public void b(com.google.android.gms.common.images.a aVar) {
            je.aU("ImageReceiver.addImageRequest() must be called in the main thread");
            this.LE.add(aVar);
        }

        public void c(com.google.android.gms.common.images.a aVar) {
            je.aU("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.LE.remove(aVar);
        }

        public void hg() {
            Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }

        @Override // android.os.ResultReceiver
        public void onReceiveResult(int resultCode, Bundle resultData) {
            ImageManager.this.Ly.execute(ImageManager.this.new c(this.mUri, (ParcelFileDescriptor) resultData.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class a {
        static int a(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }

    private static final class b extends kj<com.google.android.gms.common.images.a.C0038a, Bitmap> {
        public b(Context context) {
            super(I(context));
        }

        private static int I(Context context) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            return (int) (((((context.getApplicationInfo().flags & AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START) != 0) && ll.ig()) ? a.a(activityManager) : activityManager.getMemoryClass()) * AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START * 0.33f);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.kj
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(com.google.android.gms.common.images.a.C0038a c0038a, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.kj
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void entryRemoved(boolean z, com.google.android.gms.common.images.a.C0038a c0038a, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, c0038a, bitmap, bitmap2);
        }
    }

    private final class c implements Runnable {
        private final ParcelFileDescriptor LG;
        private final Uri mUri;

        public c(Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.mUri = uri;
            this.LG = parcelFileDescriptor;
        }

        @Override // java.lang.Runnable
        public void run() {
            je.aV("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmapDecodeFileDescriptor = null;
            if (this.LG != null) {
                try {
                    bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(this.LG.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, e);
                    z = true;
                }
                try {
                    this.LG.close();
                } catch (IOException e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            ImageManager.this.mHandler.post(ImageManager.this.new f(this.mUri, bitmapDecodeFileDescriptor, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
        }
    }

    private final class d implements Runnable {
        private final com.google.android.gms.common.images.a LH;

        public d(com.google.android.gms.common.images.a aVar) {
            this.LH = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            je.aU("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.LB.get(this.LH);
            if (imageReceiver != null) {
                ImageManager.this.LB.remove(this.LH);
                imageReceiver.c(this.LH);
            }
            com.google.android.gms.common.images.a.C0038a c0038a = this.LH.LJ;
            if (c0038a.uri == null) {
                this.LH.a(ImageManager.this.mContext, ImageManager.this.LA, true);
                return;
            }
            Bitmap bitmapA = ImageManager.this.a(c0038a);
            if (bitmapA != null) {
                this.LH.a(ImageManager.this.mContext, bitmapA, true);
                return;
            }
            Long l = (Long) ImageManager.this.LD.get(c0038a.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.LH.a(ImageManager.this.mContext, ImageManager.this.LA, true);
                    return;
                }
                ImageManager.this.LD.remove(c0038a.uri);
            }
            this.LH.a(ImageManager.this.mContext, ImageManager.this.LA);
            ImageReceiver imageReceiver2 = (ImageReceiver) ImageManager.this.LC.get(c0038a.uri);
            if (imageReceiver2 == null) {
                imageReceiver2 = ImageManager.this.new ImageReceiver(c0038a.uri);
                ImageManager.this.LC.put(c0038a.uri, imageReceiver2);
            }
            imageReceiver2.b(this.LH);
            if (!(this.LH instanceof com.google.android.gms.common.images.a.c)) {
                ImageManager.this.LB.put(this.LH, imageReceiver2);
            }
            synchronized (ImageManager.Lu) {
                if (!ImageManager.Lv.contains(c0038a.uri)) {
                    ImageManager.Lv.add(c0038a.uri);
                    imageReceiver2.hg();
                }
            }
        }
    }

    private static final class e implements ComponentCallbacks2 {
        private final b Lz;

        public e(b bVar) {
            this.Lz = bVar;
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            this.Lz.evictAll();
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int level) {
            if (level >= 60) {
                this.Lz.evictAll();
            } else if (level >= 20) {
                this.Lz.trimToSize(this.Lz.size() / 2);
            }
        }
    }

    private final class f implements Runnable {
        private boolean LI;
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch mr;

        public f(Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.LI = z;
            this.mr = countDownLatch;
        }

        private void a(ImageReceiver imageReceiver, boolean z) {
            ArrayList arrayList = imageReceiver.LE;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                com.google.android.gms.common.images.a aVar = (com.google.android.gms.common.images.a) arrayList.get(i);
                if (z) {
                    aVar.a(ImageManager.this.mContext, this.mBitmap, false);
                } else {
                    ImageManager.this.LD.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    aVar.a(ImageManager.this.mContext, ImageManager.this.LA, false);
                }
                if (!(aVar instanceof com.google.android.gms.common.images.a.c)) {
                    ImageManager.this.LB.remove(aVar);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            je.aU("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (ImageManager.this.Lz != null) {
                if (this.LI) {
                    ImageManager.this.Lz.evictAll();
                    System.gc();
                    this.LI = false;
                    ImageManager.this.mHandler.post(this);
                    return;
                }
                if (z) {
                    ImageManager.this.Lz.put(new com.google.android.gms.common.images.a.C0038a(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) ImageManager.this.LC.remove(this.mUri);
            if (imageReceiver != null) {
                a(imageReceiver, z);
            }
            this.mr.countDown();
            synchronized (ImageManager.Lu) {
                ImageManager.Lv.remove(this.mUri);
            }
        }
    }

    private ImageManager(Context context, boolean withMemoryCache) {
        this.mContext = context.getApplicationContext();
        if (withMemoryCache) {
            this.Lz = new b(this.mContext);
            if (ll.ij()) {
                hd();
            }
        } else {
            this.Lz = null;
        }
        this.LA = new jc();
        this.LB = new HashMap();
        this.LC = new HashMap();
        this.LD = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap a(com.google.android.gms.common.images.a.C0038a c0038a) {
        if (this.Lz == null) {
            return null;
        }
        return this.Lz.get(c0038a);
    }

    public static ImageManager c(Context context, boolean z) {
        if (z) {
            if (Lx == null) {
                Lx = new ImageManager(context, true);
            }
            return Lx;
        }
        if (Lw == null) {
            Lw = new ImageManager(context, false);
        }
        return Lw;
    }

    public static ImageManager create(Context context) {
        return c(context, false);
    }

    private void hd() {
        this.mContext.registerComponentCallbacks(new e(this.Lz));
    }

    public void a(com.google.android.gms.common.images.a aVar) {
        je.aU("ImageManager.loadImage() must be called in the main thread");
        new d(aVar).run();
    }

    public void loadImage(ImageView imageView, int resId) {
        a(new com.google.android.gms.common.images.a.b(imageView, resId));
    }

    public void loadImage(ImageView imageView, Uri uri) {
        a(new com.google.android.gms.common.images.a.b(imageView, uri));
    }

    public void loadImage(ImageView imageView, Uri uri, int defaultResId) {
        com.google.android.gms.common.images.a.b bVar = new com.google.android.gms.common.images.a.b(imageView, uri);
        bVar.az(defaultResId);
        a(bVar);
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri) {
        a(new com.google.android.gms.common.images.a.c(listener, uri));
    }

    public void loadImage(OnImageLoadedListener listener, Uri uri, int defaultResId) {
        com.google.android.gms.common.images.a.c cVar = new com.google.android.gms.common.images.a.c(listener, uri);
        cVar.az(defaultResId);
        a(cVar);
    }
}
