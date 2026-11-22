package com.millennialmedia.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class MMActivity extends Activity {
    private static final String TAG = "MMActivity";
    long creatorAdImplInternalId;
    private MMBaseActivity mmBaseActivity;
    GestureDetector tapDetector;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = null;
        Intent intent = getIntent();
        this.creatorAdImplInternalId = intent.getLongExtra("internalId", -4L);
        try {
            className = getIntent().getStringExtra("class");
            this.mmBaseActivity = (MMBaseActivity) Class.forName(className).newInstance();
            this.mmBaseActivity.activity = this;
            this.mmBaseActivity.onCreate(savedInstanceState);
            this.tapDetector = new GestureDetector(getApplicationContext(), new InterstitialGestureListener(this));
        } catch (Exception e) {
            MMLog.e(TAG, String.format("Could not start activity for %s. ", className), e);
            e.printStackTrace();
            finish();
        }
    }

    void onCreateSuper(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onDestroy();
        } else {
            super.onDestroy();
        }
    }

    void onDestroySuper() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onStart() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onStart();
        } else {
            super.onStart();
        }
    }

    void onStartSuper() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onStop();
        } else {
            super.onStop();
        }
    }

    void onStopSuper() {
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onRestart();
        } else {
            super.onRestart();
        }
    }

    void onRestartSuper() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onResume();
        } else {
            super.onResume();
        }
    }

    void onResumeSuper() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onPause();
        } else {
            super.onPause();
        }
    }

    void onPauseSuper() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onSaveInstanceState(outState);
        } else {
            super.onSaveInstanceState(outState);
        }
    }

    void onSaveInstanceStateSuper(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onRestoreInstanceState(savedInstanceState);
        } else {
            super.onRestoreInstanceState(savedInstanceState);
        }
    }

    void onRestoreInstanceStateSuper(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onWindowFocusChanged(hasFocus);
        } else {
            super.onWindowFocusChanged(hasFocus);
        }
    }

    void onConfigurationChangedSuper(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onConfigurationChanged(newConfig);
        } else {
            super.onConfigurationChanged(newConfig);
        }
    }

    void onWindowFocusChangedSuper(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        return this.mmBaseActivity != null ? this.mmBaseActivity.onRetainNonConfigurationInstance() : super.onRetainNonConfigurationInstance();
    }

    Object onRetainNonConfigurationInstanceSuper() {
        return super.onRetainNonConfigurationInstance();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    void onActivityResultSuper(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.mmBaseActivity != null ? this.mmBaseActivity.onKeyDown(keyCode, event) : super.onKeyDown(keyCode, event);
    }

    boolean onKeyDownSuper(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.tapDetector != null) {
            this.tapDetector.onTouchEvent(ev);
        }
        return this.mmBaseActivity != null ? this.mmBaseActivity.dispatchTouchEvent(ev) : super.dispatchTouchEvent(ev);
    }

    private static class InterstitialGestureListener extends GestureDetector.SimpleOnGestureListener {
        WeakReference<MMActivity> mmActivityRef;

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent e) {
            MMActivity mmActivity = this.mmActivityRef.get();
            if (mmActivity != null) {
                MMSDK.Event.adSingleTap(MMAdImplController.getAdImplWithId(mmActivity.creatorAdImplInternalId));
                return false;
            }
            return false;
        }

        public InterstitialGestureListener(MMActivity mmActivity) {
            this.mmActivityRef = new WeakReference<>(mmActivity);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.mmBaseActivity != null) {
            this.mmBaseActivity.finish();
        } else {
            MMSDK.Event.overlayClosed(MMAdImplController.getAdImplWithId(this.creatorAdImplInternalId));
            super.finish();
        }
    }

    public void finishSuper() {
        MMSDK.Event.overlayClosed(MMAdImplController.getAdImplWithId(this.creatorAdImplInternalId));
        super.finish();
    }

    boolean dispatchTouchEventSuper(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    protected MMBaseActivity getWrappedActivity() {
        return this.mmBaseActivity;
    }
}
