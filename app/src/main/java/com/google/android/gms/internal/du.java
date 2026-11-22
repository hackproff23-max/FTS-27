package com.google.android.gms.internal;

import android.R;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class du extends FrameLayout implements View.OnClickListener {
    private final Activity nB;
    private final ImageButton sx;

    public du(Activity activity, int i) {
        super(activity);
        this.nB = activity;
        setOnClickListener(this);
        this.sx = new ImageButton(activity);
        this.sx.setImageResource(R.drawable.btn_dialog);
        this.sx.setBackgroundColor(0);
        this.sx.setOnClickListener(this);
        this.sx.setPadding(0, 0, 0, 0);
        this.sx.setContentDescription("Interstitial close button");
        int iA = gq.a(activity, i);
        addView(this.sx, new FrameLayout.LayoutParams(iA, iA, 17));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.nB.finish();
    }

    public void q(boolean z) {
        this.sx.setVisibility(z ? 4 : 0);
    }
}
