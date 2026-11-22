package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ax extends com.chartboost.sdk.f {
    protected com.chartboost.sdk.Libraries.j k;
    protected com.chartboost.sdk.Libraries.j l;
    private List<com.chartboost.sdk.Libraries.e.a> m;
    private com.chartboost.sdk.Libraries.j n;
    private com.chartboost.sdk.Libraries.j o;
    private com.chartboost.sdk.Libraries.j p;
    private com.chartboost.sdk.Libraries.j q;
    private com.chartboost.sdk.Libraries.j r;
    private com.chartboost.sdk.Libraries.j s;
    private com.chartboost.sdk.Libraries.j t;
    private Set<com.chartboost.sdk.Libraries.j> u;
    private int v;
    private com.chartboost.sdk.Libraries.e.a w;
    private int x;
    private int y;
    private String z;

    public class a extends com.chartboost.sdk.f.a {
        private bl c;
        private bk d;
        private TextView e;
        private RelativeLayout f;
        private ListView g;
        private C0012a h;

        private a(Context context) {
            super(context);
            setBackgroundColor(-1);
            this.d = new bk(context);
            this.c = new bl(context) { // from class: com.chartboost.sdk.impl.ax.a.1
                @Override // com.chartboost.sdk.impl.bl
                protected void a(MotionEvent motionEvent) {
                    ax.this.h();
                }
            };
            this.e = new TextView(context);
            this.e.setBackgroundColor(ax.this.x);
            this.e.setText(ax.this.z);
            this.e.setTextColor(ax.this.y);
            this.e.setTextSize(2, c() ? 30.0f : 18.0f);
            this.e.setGravity(17);
            this.g = new ListView(context);
            this.g.setBackgroundColor(-1);
            this.g.setDividerHeight(0);
            a(this.g);
            addView(this.g);
            this.d.setFocusable(false);
            this.c.setFocusable(false);
            this.c.setClickable(true);
            this.d.setScaleType(ImageView.ScaleType.CENTER_CROP);
            this.c.a(ImageView.ScaleType.FIT_CENTER);
            this.f = new RelativeLayout(context);
            this.f.addView(this.d, new RelativeLayout.LayoutParams(-1, -1));
            this.f.addView(this.e, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            a(this.f);
            this.h = new C0012a(context);
        }

        @Override // com.chartboost.sdk.f.a
        protected void a(int i, int i2) {
            com.chartboost.sdk.Libraries.j jVar;
            com.chartboost.sdk.Libraries.j jVar2;
            int iA;
            Context context = getContext();
            com.chartboost.sdk.Libraries.f fVarC = CBUtility.c();
            if (fVarC.b() && ax.this.q.e()) {
                jVar = ax.this.q;
            } else if (!fVarC.c() || !ax.this.r.e()) {
                jVar = ax.this.t.e() ? ax.this.t : null;
            } else {
                jVar = ax.this.r;
            }
            if (jVar != null) {
                ax.this.v = jVar.i();
                if (jVar.h() < i) {
                    ax.this.v = Math.round(ax.this.v * (i / jVar.h()));
                }
                this.e.setVisibility(8);
                this.d.a(jVar);
            } else {
                ax.this.v = CBUtility.a(c() ? 80 : 40, context);
                this.e.setVisibility(0);
            }
            if (ax.this.w.c()) {
                ax.this.v = CBUtility.a(ax.this.w.k(), context);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, ax.this.v);
            if (!ax.this.o.e() || !fVarC.b()) {
                if (!ax.this.p.e() || !fVarC.c()) {
                    jVar2 = ax.this.n.e() ? ax.this.n : null;
                } else {
                    jVar2 = ax.this.p;
                }
            } else {
                jVar2 = ax.this.o;
            }
            if (jVar2 != null) {
                this.c.a(jVar2, layoutParams2);
                iA = CBUtility.a(c() ? 14 : 7, context);
            } else {
                this.c.a("X");
                this.c.a().setTextSize(2, c() ? 26.0f : 16.0f);
                this.c.a().setTextColor(ax.this.y);
                this.c.a().setTypeface(Typeface.SANS_SERIF, 1);
                layoutParams2.width = ax.this.v / 2;
                layoutParams2.height = ax.this.v / 3;
                iA = CBUtility.a(c() ? 30 : 20, context);
            }
            int iRound = Math.round((ax.this.v - layoutParams2.height) / 2.0f);
            layoutParams2.rightMargin = iA;
            layoutParams2.topMargin = iRound;
            layoutParams2.addRule(11);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.addRule(3, this.f.getId());
            this.g.setAdapter((ListAdapter) this.h);
            this.g.setLayoutParams(layoutParams);
            this.c.setLayoutParams(layoutParams2);
            this.f.setLayoutParams(layoutParams3);
        }

        @Override // com.chartboost.sdk.f.a
        public void b() {
            super.b();
            this.c = null;
            this.d = null;
            this.g = null;
        }

        /* JADX INFO: renamed from: com.chartboost.sdk.impl.ax$a$a, reason: collision with other inner class name */
        public class C0012a extends ArrayAdapter<com.chartboost.sdk.Libraries.e.a> {
            private Context b;

            public C0012a(Context context) {
                super(context, 0, ax.this.m);
                this.b = context;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public View getView(final int position, View convertView, ViewGroup parent) {
                AbsListView.LayoutParams layoutParams;
                final com.chartboost.sdk.Libraries.e.a item = getItem(position);
                com.chartboost.sdk.Libraries.e.a aVarA = item.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                aq aqVar = null;
                b[] bVarArrValues = b.values();
                for (int i = 0; i < bVarArrValues.length; i++) {
                    if (aVarA.equals(bVarArrValues[i].e)) {
                        try {
                            aqVar = (aq) bVarArrValues[i].f.getConstructor(ax.class, Context.class).newInstance(ax.this, this.b);
                            break;
                        } catch (Exception e) {
                            CBLogging.b(this, "error in more apps list", e);
                        }
                    }
                }
                if (aqVar == null) {
                    return new View(getContext());
                }
                aqVar.a(item, position);
                ViewGroup.LayoutParams layoutParams2 = aqVar.getLayoutParams();
                if (layoutParams2 != null && (layoutParams2 instanceof AbsListView.LayoutParams)) {
                    layoutParams = (AbsListView.LayoutParams) layoutParams2;
                    layoutParams.width = -1;
                    layoutParams.height = aqVar.a();
                } else {
                    layoutParams = new AbsListView.LayoutParams(-1, aqVar.a());
                }
                aqVar.setLayoutParams(layoutParams);
                aqVar.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.ax.a.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        String strE = item.e("deep-link");
                        if (TextUtils.isEmpty(strE) || !bc.a(strE)) {
                            strE = item.e(ShareConstants.WEB_DIALOG_PARAM_LINK);
                        }
                        if (ax.this.a(strE, item)) {
                            com.chartboost.sdk.Tracking.a.a(ax.this.f.q().e(), item.e("location"), item.e("ad_id"), position);
                        }
                    }
                });
                return aqVar;
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            public int getCount() {
                return ax.this.m.size();
            }

            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.chartboost.sdk.Libraries.e.a getItem(int i) {
                return (com.chartboost.sdk.Libraries.e.a) ax.this.m.get(i);
            }

            @Override // android.widget.BaseAdapter, android.widget.Adapter
            public int getItemViewType(int position) {
                com.chartboost.sdk.Libraries.e.a aVarA = getItem(position).a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
                b[] bVarArrValues = b.values();
                for (int i = 0; i < bVarArrValues.length; i++) {
                    if (aVarA.equals(bVarArrValues[i].e)) {
                        return i;
                    }
                }
                return 0;
            }

            @Override // android.widget.BaseAdapter, android.widget.Adapter
            public int getViewTypeCount() {
                return b.values().length;
            }
        }
    }

    public ax(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.m = new ArrayList();
        this.t = new com.chartboost.sdk.Libraries.j(this);
        this.r = new com.chartboost.sdk.Libraries.j(this);
        this.q = new com.chartboost.sdk.Libraries.j(this);
        this.s = new com.chartboost.sdk.Libraries.j(this);
        this.n = new com.chartboost.sdk.Libraries.j(this);
        this.p = new com.chartboost.sdk.Libraries.j(this);
        this.o = new com.chartboost.sdk.Libraries.j(this);
        this.l = new com.chartboost.sdk.Libraries.j(this);
        this.k = new com.chartboost.sdk.Libraries.j(this);
    }

    @Override // com.chartboost.sdk.f
    protected com.chartboost.sdk.f.a b(Context context) {
        return new a(context);
    }

    @Override // com.chartboost.sdk.f
    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        if (!super.a(aVar)) {
            return false;
        }
        com.chartboost.sdk.Libraries.e.a aVarA = aVar.a("cells");
        if (aVarA.b()) {
            a(CBError.CBImpressionError.INVALID_RESPONSE);
            return false;
        }
        this.u = new HashSet();
        for (int i = 0; i < aVarA.o(); i++) {
            com.chartboost.sdk.Libraries.e.a aVarC = aVarA.c(i);
            this.m.add(aVarC);
            com.chartboost.sdk.Libraries.e.a aVarA2 = aVarC.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
            if (aVarA2.equals("regular")) {
                com.chartboost.sdk.Libraries.e.a aVarA3 = aVarC.a("assets");
                if (aVarA3.c()) {
                    a(aVarA3, "icon");
                }
            } else if (aVarA2.equals("featured")) {
                com.chartboost.sdk.Libraries.e.a aVarA4 = aVarC.a("assets");
                if (aVarA4.c()) {
                    a(aVarA4, "portrait");
                    a(aVarA4, "landscape");
                }
            } else if (aVarA2.equals("webview")) {
            }
        }
        this.n.a(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE);
        this.p.a("close-landscape");
        this.o.a("close-portrait");
        this.t.a("header-center");
        this.q.a("header-portrait");
        this.r.a("header-landscape");
        this.s.a("header-tile");
        this.l.a("play-button");
        this.k.a("install-button");
        this.w = this.e.a("header-height");
        if (this.w.c()) {
            this.v = this.w.k();
        } else {
            this.v = a(com.chartboost.sdk.b.l()) ? 80 : 40;
        }
        this.x = this.e.c("background-color") ? a(this.e.e("background-color")) : -14571545;
        this.z = this.e.c("header-text") ? this.e.e("header-text") : "More Free Games";
        this.y = this.e.c("text-color") ? a(this.e.e("text-color")) : -1;
        return true;
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        if (!aVar.b(str)) {
            com.chartboost.sdk.Libraries.j jVar = new com.chartboost.sdk.Libraries.j(this);
            this.u.add(jVar);
            jVar.a(aVar, str, new Bundle());
        }
    }

    @Override // com.chartboost.sdk.f
    public void d() {
        super.d();
        this.m = null;
        Iterator<com.chartboost.sdk.Libraries.j> it = this.u.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        this.u.clear();
        this.n.d();
        this.p.d();
        this.o.d();
        this.t.d();
        this.s.d();
        this.q.d();
        this.r.d();
        this.l.d();
        this.k.d();
    }

    private enum b {
        FEATURED("featured", ar.class),
        REGULAR("regular", as.class),
        WEBVIEW("webview", au.class),
        VIDEO(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, at.class);

        private String e;
        private Class<? extends aq> f;

        b(String str, Class cls) {
            this.e = str;
            this.f = cls;
        }
    }
}
