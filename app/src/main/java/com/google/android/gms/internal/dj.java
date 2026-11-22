package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class dj {
    private final Context mContext;
    private final gu mo;
    private final Map<String, String> rd;

    public dj(gu guVar, Map<String, String> map) {
        this.mo = guVar;
        this.rd = map;
        this.mContext = guVar.dI();
    }

    String B(String str) {
        return Uri.parse(str).getLastPathSegment();
    }

    DownloadManager.Request b(String str, String str2) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
        if (ll.ig()) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
        } else {
            request.setShowRunningNotification(true);
        }
        return request;
    }

    public void execute() {
        if (!new bl(this.mContext).bq()) {
            gr.W("Store picture feature is not supported on this device.");
            return;
        }
        if (TextUtils.isEmpty(this.rd.get("iurl"))) {
            gr.W("Image url cannot be empty.");
            return;
        }
        final String str = this.rd.get("iurl");
        if (!URLUtil.isValidUrl(str)) {
            gr.W("Invalid image url:" + str);
            return;
        }
        final String strB = B(str);
        if (!gi.N(strB)) {
            gr.W("Image type not recognized:");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(ga.c(R.string.store_picture_title, "Save image"));
        builder.setMessage(ga.c(R.string.store_picture_message, "Allow Ad to store image in Picture gallery?"));
        builder.setPositiveButton(ga.c(R.string.accept, "Accept"), new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.dj.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                try {
                    ((DownloadManager) dj.this.mContext.getSystemService("download")).enqueue(dj.this.b(str, strB));
                } catch (IllegalStateException e) {
                    gr.U("Could not store picture.");
                }
            }
        });
        builder.setNegativeButton(ga.c(R.string.decline, "Decline"), new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.dj.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dj.this.mo.b("onStorePictureCanceled", new JSONObject());
            }
        });
        builder.create().show();
    }
}
