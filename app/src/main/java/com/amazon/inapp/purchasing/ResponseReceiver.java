package com.amazon.inapp.purchasing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ImplementationFactory.getResponseHandler().handleResponse(context, intent);
    }
}
