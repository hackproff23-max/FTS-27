package com.firsttouchgames.fts15;

import android.app.Activity;

/* JADX INFO: loaded from: classes.dex */
public class EmailManager extends Activity {
    static MainActivity mActivity;

    public static void SetActivity(MainActivity activity) {
        mActivity = activity;
    }

    public static void EmailAttachment(String sSubject, String sBody, String sFileName) {
        mActivity.EmailAttachment(sSubject, sBody, sFileName);
    }
}
