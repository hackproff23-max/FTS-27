package com.google.android.gms.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.internal.dw;
import com.google.android.gms.internal.dx;
import com.google.android.gms.internal.gr;

/* JADX INFO: loaded from: classes.dex */
public final class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private dx lf;

    private void X() {
        if (this.lf != null) {
            try {
                this.lf.X();
            } catch (RemoteException e) {
                gr.d("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.lf = dw.b(this);
        if (this.lf == null) {
            gr.W("Could not create ad overlay.");
            finish();
            return;
        }
        try {
            this.lf.onCreate(savedInstanceState);
        } catch (RemoteException e) {
            gr.d("Could not forward onCreate to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        try {
            if (this.lf != null) {
                this.lf.onDestroy();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        try {
            if (this.lf != null) {
                this.lf.onPause();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.lf != null) {
                this.lf.onRestart();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            if (this.lf != null) {
                this.lf.onResume();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        try {
            if (this.lf != null) {
                this.lf.onSaveInstanceState(outState);
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            if (this.lf != null) {
                this.lf.onStart();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            if (this.lf != null) {
                this.lf.onStop();
            }
        } catch (RemoteException e) {
            gr.d("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        X();
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        super.setContentView(view);
        X();
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        X();
    }
}
