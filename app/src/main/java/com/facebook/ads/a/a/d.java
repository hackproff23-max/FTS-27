package com.facebook.ads.a.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.drive.DriveFile;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d extends a {
    private static final String a = d.class.getSimpleName();
    private final Context b;
    private final Uri c;

    public d(Context context, Uri uri) {
        this.b = context;
        this.c = uri;
    }

    @Override // com.facebook.ads.a.a.a
    public com.facebook.ads.a.b.a a() {
        return com.facebook.ads.a.b.a.OPEN_LINK;
    }

    @Override // com.facebook.ads.a.a.a
    public void a(Map map) {
        a(this.b, this.c);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.c.getQueryParameter(ShareConstants.WEB_DIALOG_PARAM_LINK)));
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        try {
            this.b.startActivity(intent);
        } catch (Exception e) {
            Log.d(a, "Failed to open market url: " + this.c.toString(), e);
        }
    }
}
