package com.chartboost.sdk;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.impl.bb;
import com.chartboost.sdk.impl.bh;

/* JADX INFO: loaded from: classes.dex */
public final class CBImpressionActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (bh.c()) {
            getWindow().setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        }
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("paramFullscreen", false)) {
            getWindow().addFlags(1024);
        }
        getWindow().setWindowAnimations(0);
        setContentView(new RelativeLayout(this));
        Chartboost.a(this);
        Chartboost.createSurfaceView(this);
        bb.d();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        Chartboost.a();
        Chartboost.a((Activity) this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Chartboost.a(k.a(this));
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Chartboost.b(k.a(this));
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Chartboost.b();
        Chartboost.c(k.a(this));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Chartboost.b((Activity) this);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!Chartboost.d()) {
            super.onBackPressed();
        }
    }
}
