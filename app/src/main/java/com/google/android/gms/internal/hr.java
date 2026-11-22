package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.CRC32;

/* JADX INFO: loaded from: classes.dex */
public class hr implements SafeParcelable {
    public static final hs CREATOR = new hs();
    final int CK;
    final hf Dw;
    final long Dx;
    final int Dy;
    final hd Dz;
    public final String pc;

    hr(int i, hf hfVar, long j, int i2, String str, hd hdVar) {
        this.CK = i;
        this.Dw = hfVar;
        this.Dx = j;
        this.Dy = i2;
        this.pc = str;
        this.Dz = hdVar;
    }

    public hr(hf hfVar, long j, int i) {
        this(1, hfVar, j, i, (String) null, (hd) null);
    }

    public hr(hf hfVar, long j, int i, String str, hd hdVar) {
        this(1, hfVar, j, i, str, hdVar);
    }

    public hr(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexApi.AppIndexingLink> list) {
        this(1, a(str, intent), System.currentTimeMillis(), 0, (String) null, a(intent, str2, uri, str3, list).fD());
    }

    public static hd.a a(Intent intent, String str, Uri uri, String str2, List<AppIndexApi.AppIndexingLink> list) {
        String string;
        hd.a aVar = new hd.a();
        aVar.a(av(str));
        if (uri != null) {
            aVar.a(f(uri));
        }
        if (list != null) {
            aVar.a(b(list));
        }
        String action = intent.getAction();
        if (action != null) {
            aVar.a(i("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            aVar.a(i("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            aVar.a(i("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (extras != null && (string = extras.getString("intent_extra_data_key")) != null) {
            aVar.a(i("intent_extra_data", string));
        }
        return aVar.ar(str2).F(true);
    }

    public static hf a(String str, Intent intent) {
        return h(str, g(intent));
    }

    private static hh av(String str) {
        return new hh(str, new hp.a("title").Q(1).H(true).au("name").fG(), "text1");
    }

    private static hh b(List<AppIndexApi.AppIndexingLink> list) {
        mv.a aVar = new mv.a();
        mv.a.C0109a[] c0109aArr = new mv.a.C0109a[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= c0109aArr.length) {
                aVar.afu = c0109aArr;
                return new hh(qw.f(aVar), new hp.a("outlinks").G(true).au(".private:outLinks").at("blob").fG());
            }
            c0109aArr[i2] = new mv.a.C0109a();
            AppIndexApi.AppIndexingLink appIndexingLink = list.get(i2);
            c0109aArr[i2].afw = appIndexingLink.appIndexingUrl.toString();
            c0109aArr[i2].viewId = appIndexingLink.viewId;
            if (appIndexingLink.webUrl != null) {
                c0109aArr[i2].afx = appIndexingLink.webUrl.toString();
            }
            i = i2 + 1;
        }
    }

    private static hh f(Uri uri) {
        return new hh(uri.toString(), new hp.a("web_url").Q(4).G(true).au("url").fG());
    }

    private static String g(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static hf h(String str, String str2) {
        return new hf(str, "", str2);
    }

    private static hh i(String str, String str2) {
        return new hh(str2, new hp.a(str).G(true).fG(), str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        hs hsVar = CREATOR;
        return 0;
    }

    public String toString() {
        return String.format("UsageInfo[documentId=%s, timestamp=%d, usageType=%d]", this.Dw, Long.valueOf(this.Dx), Integer.valueOf(this.Dy));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        hs hsVar = CREATOR;
        hs.a(this, dest, flags);
    }
}
