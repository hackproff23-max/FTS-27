package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private String kU = "googleads.g.doubleclick.net";
    private String kV = "/pagead/ads";
    private String kW = "ad.doubleclick.net";
    private String[] kX = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private g kY;

    public k(g gVar) {
        this.kY = gVar;
    }

    private Uri a(Uri uri, Context context, String str, boolean z) throws l {
        try {
            boolean zA = a(uri);
            if (zA) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new l("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new l("Query parameter already exists: ms");
            }
            String strA = z ? this.kY.a(context, str) : this.kY.a(context);
            return zA ? b(uri, "dc_ms", strA) : a(uri, "ms", strA);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    private Uri a(Uri uri, String str, String str2) throws UnsupportedOperationException {
        String string = uri.toString();
        int iIndexOf = string.indexOf("&adurl");
        if (iIndexOf == -1) {
            iIndexOf = string.indexOf("?adurl");
        }
        return iIndexOf != -1 ? Uri.parse(string.substring(0, iIndexOf + 1) + str + "=" + str2 + "&" + string.substring(iIndexOf + 1)) : uri.buildUpon().appendQueryParameter(str, str2).build();
    }

    private Uri b(Uri uri, String str, String str2) {
        String string = uri.toString();
        int iIndexOf = string.indexOf(";adurl");
        if (iIndexOf != -1) {
            return Uri.parse(string.substring(0, iIndexOf + 1) + str + "=" + str2 + ";" + string.substring(iIndexOf + 1));
        }
        String encodedPath = uri.getEncodedPath();
        int iIndexOf2 = string.indexOf(encodedPath);
        return Uri.parse(string.substring(0, encodedPath.length() + iIndexOf2) + ";" + str + "=" + str2 + ";" + string.substring(encodedPath.length() + iIndexOf2));
    }

    public g C() {
        return this.kY;
    }

    public Uri a(Uri uri, Context context) throws l {
        try {
            return a(uri, context, uri.getQueryParameter("ai"), true);
        } catch (UnsupportedOperationException e) {
            throw new l("Provided Uri is not in a valid state");
        }
    }

    public void a(MotionEvent motionEvent) {
        this.kY.a(motionEvent);
    }

    public boolean a(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.kW);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean b(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String str : this.kX) {
                if (host.endsWith(str)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
