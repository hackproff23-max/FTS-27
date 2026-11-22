package com.firsttouchgames.fts15;

import android.content.Context;
import android.content.res.AssetManager;

/* JADX INFO: loaded from: classes.dex */
public class JNI {
    public static native void AddTouch(float f, float f2, int i);

    public static native void SafeModeResetCurrentSeason();

    public static native void SafeModeResetCustomData();

    public static native void SafeModeSoccerInit();

    public static native void SafeModeSupportRestore(String str);

    public static native void SetScreen(int i, int i2);

    public static native void SetScreenSize(float f);

    public static native void appDestroyed();

    public static native void appPaused();

    public static native void appResumed();

    public static native void areConnectedToFacebook();

    public static native void areConnectedToGoogle();

    public static native void backButtonPressed();

    public static native void getAssetManager(AssetManager assetManager);

    public static native void getJNIEnv(Context context);

    public static native void rewardUser(int i);

    public static native void setDeviceModel(String str);

    public static native void setExpansionPath(String str);

    public static native void setExpansionProgress(int i);

    public static native void setExpansionRequired(boolean z);

    public static native void setExternalFilesDir(String str);

    public static native void step();
}
