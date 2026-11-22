package com.google.android.vending.expansion.downloader.impl;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class CustomNotificationFactory {
    public static DownloadNotification.ICustomNotification createCustomNotification() {
        return Build.VERSION.SDK_INT > 13 ? new V14CustomNotification() : new V3CustomNotification();
    }
}
