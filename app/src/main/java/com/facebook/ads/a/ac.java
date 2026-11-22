package com.facebook.ads.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.ImpressionListener;
import com.facebook.ads.NativeAd;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ac implements Ad {
    private static final String CLICK = "com.facebook.ads.native.click";
    private static final String IMPRESSION = "com.facebook.ads.native.impression";
    private static final String SEPARATOR = ":";
    private static final String TAG = ac.class.getSimpleName();
    private static WeakHashMap viewMapping = new WeakHashMap();
    private aa adDataModel;
    private AdListener adListener;
    private k adRequestController;
    private View adView;
    private b broadcastReceiver;
    private final Context context;
    private a eventHandler;
    private ab handler;
    private ImpressionListener impListener;
    private View.OnTouchListener touchListener;
    private int viewabilityThreshold;
    private List clickListeners = new ArrayList();
    private final String uniqueId = UUID.randomUUID().toString();

    private class a implements View.OnClickListener, View.OnTouchListener {
        private int b;
        private int c;
        private int d;
        private int e;
        private float f;
        private float g;
        private int h;
        private int i;
        private boolean j;

        private a() {
        }

        public Map a() {
            HashMap map = new HashMap();
            map.put("clickX", Integer.valueOf(this.b));
            map.put("clickY", Integer.valueOf(this.c));
            map.put("width", Integer.valueOf(this.d));
            map.put("height", Integer.valueOf(this.e));
            map.put("adPositionX", Float.valueOf(this.f));
            map.put("adPositionY", Float.valueOf(this.g));
            map.put("visibleWidth", Integer.valueOf(this.i));
            map.put("visibleHeight", Integer.valueOf(this.h));
            return map;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (ac.this.adListener != null) {
                ac.this.adListener.onAdClicked(ac.this);
            }
            if (!this.j) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            ac.this.adDataModel.a(ac.this.context, a(), false);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && ac.this.adView != null) {
                this.d = ac.this.adView.getWidth();
                this.e = ac.this.adView.getHeight();
                int[] iArr = new int[2];
                ac.this.adView.getLocationInWindow(iArr);
                this.f = iArr[0];
                this.g = iArr[1];
                Rect rect = new Rect();
                ac.this.adView.getGlobalVisibleRect(rect);
                this.i = rect.width();
                this.h = rect.height();
                int[] iArr2 = new int[2];
                view.getLocationInWindow(iArr2);
                this.b = (((int) motionEvent.getX()) + iArr2[0]) - iArr[0];
                this.c = (iArr2[1] + ((int) motionEvent.getY())) - iArr[1];
                this.j = true;
            }
            if (ac.this.touchListener != null) {
                return ac.this.touchListener.onTouch(view, motionEvent);
            }
            return false;
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        public void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.facebook.ads.native.impression:" + ac.this.uniqueId);
            intentFilter.addAction("com.facebook.ads.native.click:" + ac.this.uniqueId);
            LocalBroadcastManager.getInstance(ac.this.context).registerReceiver(this, intentFilter);
        }

        public void b() {
            try {
                LocalBroadcastManager.getInstance(ac.this.context).unregisterReceiver(this);
            } catch (Exception e) {
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str = intent.getAction().split(":")[0];
            if (ac.IMPRESSION.equals(str)) {
                ac.this.handler.d();
            } else if (ac.CLICK.equals(str)) {
                ac.this.adDataModel.a(context, null, true);
            }
        }
    }

    private class c implements f.a {
        private c() {
        }

        @Override // com.facebook.ads.a.f.a
        public boolean a() {
            return ac.this.isViewOnScreen();
        }

        @Override // com.facebook.ads.a.f.a
        public void b() {
            if (ac.this.adRequestController != null) {
                ac.this.adRequestController.a("on imp sent");
            }
        }

        @Override // com.facebook.ads.a.f.a
        public void c() {
            if (ac.this.impListener != null) {
                ac.this.impListener.onLoggingImpression(ac.this);
            }
        }

        @Override // com.facebook.ads.a.f.a
        public boolean d() {
            return false;
        }
    }

    public ac(Context context, String str, AdSize adSize, n nVar, boolean z) {
        this.context = context;
        this.adRequestController = new k(this.context, str, adSize, nVar, z, o.NATIVE, 1, new j.b() { // from class: com.facebook.ads.a.ac.1
            @Override // com.facebook.ads.a.j.b
            public void a(AdError adError) {
                if (ac.this.adListener != null) {
                    ac.this.adListener.onError(ac.this, adError);
                }
            }

            @Override // com.facebook.ads.a.j.b
            public void a(l lVar) {
                if (lVar.d() != null && !(lVar.d() instanceof aa)) {
                    if (ac.this.adListener != null) {
                        ac.this.adListener.onError(ac.this, AdError.INTERNAL_ERROR);
                    }
                    if (ac.this.adRequestController != null) {
                        ac.this.adRequestController.a("on internal error");
                        return;
                    }
                    return;
                }
                aa aaVar = (aa) lVar.d();
                if (aaVar != null) {
                    ac.this.adDataModel = aaVar;
                    ac.this.registerManualLogReceiver();
                    if (ac.this.adListener != null) {
                        ac.this.adListener.onAdLoaded(ac.this);
                    }
                    ac.this.viewabilityThreshold = lVar.c();
                    return;
                }
                if (ac.this.adListener != null) {
                    ac.this.adListener.onError(ac.this, lVar.e() != null ? lVar.e() : AdError.INTERNAL_ERROR);
                }
                if (ac.this.adRequestController != null) {
                    ac.this.adRequestController.a("on no fill");
                }
            }
        });
    }

    private void addListener(View view) {
        this.clickListeners.add(view);
        view.setOnClickListener(this.eventHandler);
        view.setOnTouchListener(this.eventHandler);
    }

    private void collectAllSubviews(List list, View view) {
        list.add(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                collectAllSubviews(list, viewGroup.getChildAt(i));
            }
        }
    }

    private void detachListeners() {
        for (View view : this.clickListeners) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
        }
        this.clickListeners.clear();
    }

    private void ensureAdRequestController() {
        if (this.adRequestController == null) {
            RuntimeException runtimeException = new RuntimeException("No request controller available, has the NativeAd been destroyed?");
            com.facebook.ads.a.c.a(com.facebook.ads.a.b.a(runtimeException));
            throw runtimeException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isViewOnScreen() {
        return isAdLoaded() && p.a(this.context, this.adView, this.adView.getWidth(), this.adView.getHeight(), this.viewabilityThreshold);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerManualLogReceiver() {
        if (this.adDataModel == null || !this.adDataModel.j()) {
            return;
        }
        this.broadcastReceiver = new b();
        this.broadcastReceiver.a();
        this.handler = new ab(new c() { // from class: com.facebook.ads.a.ac.2
            @Override // com.facebook.ads.a.ac.c, com.facebook.ads.a.f.a
            public boolean a() {
                return true;
            }

            @Override // com.facebook.ads.a.ac.c, com.facebook.ads.a.f.a
            public boolean d() {
                return true;
            }
        }, 1000L, this.adDataModel, this.context);
    }

    @Override // com.facebook.ads.Ad
    public void destroy() {
        if (this.adRequestController != null) {
            this.adRequestController.c();
            this.adRequestController = null;
        }
        detachListeners();
        if (this.handler != null) {
            this.handler.c();
            this.handler = null;
        }
        if (this.adView != null) {
            viewMapping.remove(this.adView);
            this.adView = null;
        }
        if (this.broadcastReceiver != null) {
            this.broadcastReceiver.b();
            this.broadcastReceiver = null;
        }
    }

    void disableAutoRefresh() {
        this.adRequestController.a();
    }

    public String getAdBody() {
        if (isAdLoaded()) {
            return this.adDataModel.f();
        }
        return null;
    }

    public String getAdCallToAction() {
        if (isAdLoaded()) {
            return this.adDataModel.g();
        }
        return null;
    }

    NativeAd.Image getAdChoicesIcon() {
        if (isAdLoaded()) {
            return this.adDataModel.k();
        }
        return null;
    }

    String getAdChoicesLinkUrl() {
        if (isAdLoaded()) {
            return this.adDataModel.l();
        }
        return null;
    }

    public NativeAd.Image getAdCoverImage() {
        if (isAdLoaded()) {
            return this.adDataModel.d();
        }
        return null;
    }

    public NativeAd.Image getAdIcon() {
        if (isAdLoaded()) {
            return this.adDataModel.c();
        }
        return null;
    }

    public String getAdSocialContext() {
        if (isAdLoaded()) {
            return this.adDataModel.h();
        }
        return null;
    }

    public NativeAd.Rating getAdStarRating() {
        if (isAdLoaded()) {
            return this.adDataModel.i();
        }
        return null;
    }

    public String getAdTitle() {
        if (isAdLoaded()) {
            return this.adDataModel.e();
        }
        return null;
    }

    public String getId() {
        return this.uniqueId;
    }

    public boolean isAdLoaded() {
        return this.adDataModel != null;
    }

    @Override // com.facebook.ads.Ad
    public void loadAd() {
        ensureAdRequestController();
        this.adRequestController.b();
    }

    protected void onWindowVisibilityChanged(int i) {
        if (this.adRequestController != null) {
            this.adRequestController.a(i);
        }
        if (this.handler != null) {
            if (i == 0) {
                this.handler.b();
            } else {
                this.handler.c();
            }
        }
    }

    public void registerViewForInteraction(View view) {
        ArrayList arrayList = new ArrayList();
        collectAllSubviews(arrayList, view);
        registerViewForInteraction(view, arrayList);
    }

    public void registerViewForInteraction(View view, List list) {
        if (view == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Must provide a View");
            com.facebook.ads.a.c.a(com.facebook.ads.a.b.a(illegalArgumentException));
            throw illegalArgumentException;
        }
        if (list == null || list.size() == 0) {
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException("Invalid set of clickable views");
            com.facebook.ads.a.c.a(com.facebook.ads.a.b.a(illegalArgumentException2));
            throw illegalArgumentException2;
        }
        if (!isAdLoaded()) {
            Log.e(TAG, "Ad not loaded");
            return;
        }
        if (this.adView != null) {
            Log.w(TAG, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
            unregisterView();
        }
        if (viewMapping.containsKey(view)) {
            Log.w(TAG, "View already registered with a NativeAd. Auto unregistering and proceeding.");
            ((ac) ((WeakReference) viewMapping.get(view)).get()).unregisterView();
        }
        this.eventHandler = new a();
        this.adView = view;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            addListener((View) it.next());
        }
        this.handler = new ab(new c(), 1000L, this.adDataModel, this.context);
        this.handler.d();
        viewMapping.put(view, new WeakReference(this));
    }

    public void setAdListener(AdListener adListener) {
        this.adListener = adListener;
    }

    public void setImpressionListener(ImpressionListener impressionListener) {
        this.impListener = impressionListener;
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.touchListener = onTouchListener;
    }

    public void unregisterView() {
        if (this.adView == null) {
            return;
        }
        if (!viewMapping.containsKey(this.adView) || ((WeakReference) viewMapping.get(this.adView)).get() != this) {
            IllegalStateException illegalStateException = new IllegalStateException("View not registered with this NativeAd");
            com.facebook.ads.a.c.a(com.facebook.ads.a.b.a(illegalStateException));
            throw illegalStateException;
        }
        viewMapping.remove(this.adView);
        detachListeners();
        this.handler.c();
        this.handler = null;
        this.adView = null;
    }
}
