package com.chartboost.sdk.impl;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class am extends LinearLayout {
    private ai a;
    private LinearLayout b;
    private bk c;
    private TextView d;
    private bl e;
    private int f;

    public am(Context context, ai aiVar) {
        super(context);
        this.f = ExploreByTouchHelper.INVALID_ID;
        this.a = aiVar;
        a(context);
    }

    private void a(Context context) {
        Context context2 = getContext();
        int iRound = Math.round(getContext().getResources().getDisplayMetrics().density * 8.0f);
        setOrientation(1);
        setGravity(17);
        this.b = new LinearLayout(context2);
        this.b.setGravity(17);
        this.b.setOrientation(0);
        this.b.setPadding(iRound, iRound, iRound, iRound);
        this.c = new bk(context2);
        this.c.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.c.setPadding(0, 0, iRound, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.a.a(layoutParams, this.a.y, 1.0f);
        this.d = new TextView(getContext());
        this.d.setTextColor(-1);
        this.d.setTypeface(null, 1);
        this.d.setGravity(17);
        this.d.setTextSize(2, com.chartboost.sdk.f.a(context) ? 26.0f : 16.0f);
        this.b.addView(this.c, layoutParams);
        this.b.addView(this.d, new LinearLayout.LayoutParams(-2, -2));
        this.e = new bl(getContext()) { // from class: com.chartboost.sdk.impl.am.1
            @Override // com.chartboost.sdk.impl.bl
            protected void a(MotionEvent motionEvent) {
                am.this.e.setEnabled(false);
                am.this.a.e().i();
            }
        };
        this.e.setPadding(0, 0, 0, iRound);
        this.e.a(ImageView.ScaleType.FIT_CENTER);
        this.e.setPadding(iRound, iRound, iRound, iRound);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        this.a.a(layoutParams2, this.a.x, 1.0f);
        if (this.a.y.e()) {
            this.c.a(this.a.y);
        }
        if (this.a.x.e()) {
            this.e.a(this.a.x);
        }
        addView(this.b, new LinearLayout.LayoutParams(-2, -2));
        addView(this.e, layoutParams2);
        a();
    }

    public void a(boolean z) {
        setBackgroundColor(z ? ViewCompat.MEASURED_STATE_MASK : this.f);
    }

    public void a(String str, int i) {
        this.d.setText(str);
        this.f = i;
        a(this.a.r());
    }

    public void a() {
        a(this.a.r());
    }
}
