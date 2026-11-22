package com.flurry.sdk;

import android.text.TextUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class as {
    private String c;
    private boolean d;
    private long e;
    private Map<String, String> f;
    private static final String b = as.class.getSimpleName();
    static final List<String> a = Arrays.asList("requested", "filled", "unfilled", "rendered", "clicked", "prepared", "adunitMerged", "sendUrlStatusResult", "videoStart", "videoFirstQuartile", "videoMidpoint", "videoThirdQuartile", UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, "videoProgressed", "sentToUrl", "adClosed", "adWillClose", "renderFailed", "requestAdComponents", "urlVerified", "capExhausted", "capNotExhausted");

    public static class a implements gx<as> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, as asVar) throws IOException {
            if (outputStream != null && asVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.as.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeUTF(asVar.c);
                dataOutputStream.writeBoolean(asVar.d);
                dataOutputStream.writeLong(asVar.e);
                dataOutputStream.writeShort(asVar.f.size());
                for (Map.Entry entry : asVar.f.entrySet()) {
                    dataOutputStream.writeUTF((String) entry.getKey());
                    dataOutputStream.writeUTF((String) entry.getValue());
                }
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public as b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.as.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            as asVar = new as();
            asVar.c = dataInputStream.readUTF();
            asVar.d = dataInputStream.readBoolean();
            asVar.e = dataInputStream.readLong();
            asVar.f = new HashMap();
            short s = dataInputStream.readShort();
            for (short s2 = 0; s2 < s; s2 = (short) (s2 + 1)) {
                asVar.f.put(dataInputStream.readUTF(), dataInputStream.readUTF());
            }
            return asVar;
        }
    }

    private as() {
    }

    public as(String str, boolean z, long j, Map<String, String> map) {
        if (!a.contains(str)) {
            gd.a(b, "AdEvent initialized with unrecognized type: " + str);
        }
        this.c = str;
        this.d = z;
        this.e = j;
        if (map == null) {
            this.f = new HashMap();
        } else {
            this.f = map;
        }
    }

    public String a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }

    public long c() {
        return this.e;
    }

    public Map<String, String> d() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof as)) {
            return false;
        }
        as asVar = (as) obj;
        if (TextUtils.equals(this.c, asVar.c) && this.d == asVar.d && this.e == asVar.e) {
            if (this.f == asVar.f) {
                return true;
            }
            if (this.f != null && this.f.equals(asVar.f)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.c != null ? 17 ^ this.c.hashCode() : 17;
        if (this.d) {
            iHashCode ^= 1;
        }
        int i = (int) (((long) iHashCode) ^ this.e);
        if (this.f != null) {
            return i ^ this.f.hashCode();
        }
        return i;
    }
}
