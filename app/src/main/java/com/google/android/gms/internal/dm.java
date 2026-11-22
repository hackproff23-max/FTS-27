package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dm {
    public static boolean a(Context context, Cdo cdo, dv dvVar) {
        if (cdo == null) {
            gr.W("No intent data for launcher overlay.");
            return false;
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(cdo.rH)) {
            gr.W("Open GMSG did not contain a URL.");
            return false;
        }
        if (TextUtils.isEmpty(cdo.mimeType)) {
            intent.setData(Uri.parse(cdo.rH));
        } else {
            intent.setDataAndType(Uri.parse(cdo.rH), cdo.mimeType);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(cdo.packageName)) {
            intent.setPackage(cdo.packageName);
        }
        if (!TextUtils.isEmpty(cdo.rI)) {
            String[] strArrSplit = cdo.rI.split("/", 2);
            if (strArrSplit.length < 2) {
                gr.W("Could not parse component name from open GMSG: " + cdo.rI);
                return false;
            }
            intent.setClassName(strArrSplit[0], strArrSplit[1]);
        }
        try {
            gr.V("Launching an intent: " + intent);
            context.startActivity(intent);
            dvVar.af();
            return true;
        } catch (ActivityNotFoundException e) {
            gr.W(e.getMessage());
            return false;
        }
    }
}
