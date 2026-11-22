package com.facebook.ads.a.a;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.a.ad;
import com.facebook.ads.a.ag;
import com.facebook.ads.a.p;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public abstract com.facebook.ads.a.b.a a();

    protected void a(Context context, Uri uri) {
        String queryParameter = uri.getQueryParameter("native_click_report_url");
        if (ag.a(queryParameter)) {
            return;
        }
        new ad().execute(queryParameter);
        p.a(context, "Click logged");
    }

    public abstract void a(Map map);
}
