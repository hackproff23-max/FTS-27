package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fb implements Runnable {
    private final int li;
    private final int lj;
    protected final gu mo;
    private final Handler tr;
    private final long ts;
    private long tt;
    private gv.a tu;
    protected boolean tv;
    protected boolean tw;

    protected final class a extends AsyncTask<Void, Void, Boolean> {
        private final WebView tx;
        private Bitmap ty;

        public a(WebView webView) {
            this.tx = webView;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Boolean bool) {
            fb.c(fb.this);
            if (bool.booleanValue() || fb.this.cH() || fb.this.tt <= 0) {
                fb.this.tw = bool.booleanValue();
                fb.this.tu.a(fb.this.mo);
            } else if (fb.this.tt > 0) {
                if (gr.v(2)) {
                    gr.S("Ad not detected, scheduling another run.");
                }
                fb.this.tr.postDelayed(fb.this, fb.this.ts);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public synchronized Boolean doInBackground(Void... voidArr) {
            boolean zValueOf;
            int width = this.ty.getWidth();
            int height = this.ty.getHeight();
            if (width == 0 || height == 0) {
                zValueOf = false;
            } else {
                int i = 0;
                for (int i2 = 0; i2 < width; i2 += 10) {
                    for (int i3 = 0; i3 < height; i3 += 10) {
                        if (this.ty.getPixel(i2, i3) != 0) {
                            i++;
                        }
                    }
                }
                zValueOf = Boolean.valueOf(((double) i) / (((double) (width * height)) / 100.0d) > 0.1d);
            }
            return zValueOf;
        }

        @Override // android.os.AsyncTask
        protected synchronized void onPreExecute() {
            this.ty = Bitmap.createBitmap(fb.this.li, fb.this.lj, Bitmap.Config.ARGB_8888);
            this.tx.setVisibility(0);
            this.tx.measure(View.MeasureSpec.makeMeasureSpec(fb.this.li, 0), View.MeasureSpec.makeMeasureSpec(fb.this.lj, 0));
            this.tx.layout(0, 0, fb.this.li, fb.this.lj);
            this.tx.draw(new Canvas(this.ty));
            this.tx.invalidate();
        }
    }

    public fb(gv.a aVar, gu guVar, int i, int i2) {
        this(aVar, guVar, i, i2, 200L, 50L);
    }

    public fb(gv.a aVar, gu guVar, int i, int i2, long j, long j2) {
        this.ts = j;
        this.tt = j2;
        this.tr = new Handler(Looper.getMainLooper());
        this.mo = guVar;
        this.tu = aVar;
        this.tv = false;
        this.tw = false;
        this.lj = i2;
        this.li = i;
    }

    static /* synthetic */ long c(fb fbVar) {
        long j = fbVar.tt - 1;
        fbVar.tt = j;
        return j;
    }

    public void a(fj fjVar, gz gzVar) {
        this.mo.setWebViewClient(gzVar);
        this.mo.loadDataWithBaseURL(TextUtils.isEmpty(fjVar.sg) ? null : gi.L(fjVar.sg), fjVar.tU, "text/html", "UTF-8", null);
    }

    public void b(fj fjVar) {
        a(fjVar, new gz(this, this.mo, fjVar.ud));
    }

    public void cF() {
        this.tr.postDelayed(this, this.ts);
    }

    public synchronized void cG() {
        this.tv = true;
    }

    public synchronized boolean cH() {
        return this.tv;
    }

    public boolean cI() {
        return this.tw;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mo == null || cH()) {
            this.tu.a(this.mo);
        } else {
            new a(this.mo).execute(new Void[0]);
        }
    }
}
