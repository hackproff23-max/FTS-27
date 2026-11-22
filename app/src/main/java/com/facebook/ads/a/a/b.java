package com.facebook.ads.a.a;

import android.content.Context;
import android.net.Uri;
import com.mopub.mobileads.BaseVideoPlayerActivity;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static a a(Context context, Uri uri) {
        String authority = uri.getAuthority();
        String queryParameter = uri.getQueryParameter(BaseVideoPlayerActivity.VIDEO_URL);
        if ("store".equals(authority)) {
            return queryParameter != null ? new e(context, uri) : new c(context, uri);
        }
        if ("open_link".equals(authority)) {
            return new d(context, uri);
        }
        return null;
    }
}
