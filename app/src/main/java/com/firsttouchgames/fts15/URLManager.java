package com.firsttouchgames.fts15;

/* JADX INFO: loaded from: classes.dex */
public class URLManager {
    static MainActivity mActivity;

    public static void SetActivity(MainActivity activity) {
        mActivity = activity;
    }

    public static void OpenWebpage(String url, boolean bChooser) {
        mActivity.OpenWebpage(url, bChooser);
    }
}
