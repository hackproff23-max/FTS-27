package com.flurry.sdk;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.flurry.android.impl.ads.protocol.v13.AdViewType;
import com.flurry.android.impl.ads.protocol.v13.NativeAsset;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class w extends o {
    private static final String a = w.class.getSimpleName();
    private GestureDetector b;
    private a c;
    private List<Integer> d;
    private List<String> e;
    private boolean f;
    private WeakReference<View> g;
    private Rect h;
    private int i;

    enum a {
        INIT,
        READY
    }

    public w(Context context, String str) {
        super(context, null, str);
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = new WeakReference<>(null);
        this.h = new Rect();
        this.i = 0;
        this.b = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() { // from class: com.flurry.sdk.w.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                View view = (View) w.this.g.get();
                if (view != null) {
                    Log.i(w.a, "On item clicked" + view.getClass());
                    w.this.A();
                    w.this.B();
                    return false;
                }
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return true;
            }
        });
        this.c = a.INIT;
    }

    @Override // com.flurry.sdk.o, com.flurry.sdk.r
    public void a() {
        super.a();
    }

    @Override // com.flurry.sdk.o
    protected void r() {
        View view;
        super.r();
        if (a.READY.equals(this.c) && (view = this.g.get()) != null) {
            b(view);
            if (!this.f) {
                long jWidth = view.getGlobalVisibleRect(this.h) ? this.h.width() * this.h.height() : 0L;
                if (jWidth > 0) {
                    boolean z = this.h.top == 0 && this.h.left == 0;
                    if (jWidth >= view.getHeight() * view.getWidth() * 0.5f && !z) {
                        int i = this.i + 1;
                        this.i = i;
                        if (i >= 10) {
                            A();
                            return;
                        }
                        return;
                    }
                    this.i = 0;
                    return;
                }
                this.i = 0;
            }
        }
    }

    @Override // com.flurry.sdk.o
    protected void a(d dVar) {
        super.a(dVar);
        if (d.a.kOnFetched.equals(dVar.b)) {
            ap apVarN = n();
            if (apVarN == null) {
                cq.a(this, av.kMissingAdController);
                return;
            }
            AdUnit adUnitA = apVarN.a();
            if (adUnitA == null) {
                cq.a(this, av.kInvalidAdUnit);
            } else {
                if (!AdViewType.NATIVE.equals(adUnitA.adViewType)) {
                    cq.a(this, av.kIncorrectClassForAdSpace);
                    return;
                }
                p();
                synchronized (this) {
                    this.c = a.READY;
                }
            }
        }
    }

    public void a(List<Integer> list) {
        this.d = list;
    }

    public List<Integer> s() {
        return this.d;
    }

    public List<String> t() {
        return this.e;
    }

    public boolean u() {
        boolean zEquals;
        synchronized (this) {
            zEquals = a.READY.equals(this.c);
        }
        return zEquals;
    }

    public void v() {
        synchronized (this) {
            if (a.INIT.equals(this.c)) {
                h().a(this, i(), j());
            } else if (a.READY.equals(this.c)) {
                cq.a(this);
            }
        }
    }

    public void a(View view) {
        w();
        this.g = new WeakReference<>(view);
    }

    public void w() {
        View view = this.g.get();
        if (view != null) {
            view.setOnTouchListener(null);
            this.g.clear();
        }
    }

    public int x() {
        if (a.READY.equals(this.c)) {
            return k().h().style;
        }
        return 0;
    }

    public List<NativeAsset> y() {
        return !a.READY.equals(this.c) ? Collections.emptyList() : new ArrayList(k().i());
    }

    private void b(View view) {
        if (view != null) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.flurry.sdk.w.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    w.this.b.onTouchEvent(motionEvent);
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void A() {
        if (!this.f) {
            Log.i(a, "Impression logged");
            co.a(aw.EV_NATIVE_IMPRESSION, Collections.emptyMap(), e(), this, k(), 0);
            this.f = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void B() {
        Log.i(a, "Click logged");
        co.a(aw.EV_CLICKED, Collections.emptyMap(), e(), this, k(), 0);
    }
}
