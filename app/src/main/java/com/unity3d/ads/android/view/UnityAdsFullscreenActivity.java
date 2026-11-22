package com.unity3d.ads.android.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsFullscreenActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        UnityAdsDeviceLog.entered();
        super.onCreate(bundle);
        UnityAds.changeActivity(this);
    }

    @Override // android.app.Activity
    public void onStart() {
        UnityAdsDeviceLog.entered();
        super.onStart();
    }

    @Override // android.app.Activity
    public void onRestart() {
        UnityAdsDeviceLog.entered();
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onResume() {
        UnityAdsDeviceLog.entered();
        super.onResume();
        UnityAds.changeActivity(this);
        UnityAds.checkMainview();
    }

    @Override // android.app.Activity
    public void onPause() {
        UnityAdsDeviceLog.entered();
        super.onPause();
    }

    @Override // android.app.Activity
    public void onStop() {
        UnityAdsDeviceLog.entered();
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        UnityAdsDeviceLog.entered();
        super.onDestroy();
        UnityAds.handleFullscreenDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }
}
