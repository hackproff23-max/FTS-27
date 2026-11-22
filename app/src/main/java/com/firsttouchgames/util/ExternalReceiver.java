package com.firsttouchgames.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.firsttouchgames.fts15.PushNotifications;

/* JADX INFO: loaded from: classes.dex */
public class ExternalReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i("ExternalReceiver", "onReceive");
        Bundle extras = intent.getExtras();
        StringBuilder payload = new StringBuilder();
        for (String key : extras.keySet()) {
            payload.append(String.format("%s=%s", key, extras.getString(key)) + '\n');
        }
        if (payload.toString().indexOf("default=") >= 0) {
            PushNotifications.SendNotification(context, payload.toString());
        }
    }
}
