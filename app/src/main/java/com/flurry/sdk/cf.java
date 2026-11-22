package com.flurry.sdk;

import android.text.TextUtils;
import android.util.Pair;
import android.widget.Toast;
import com.facebook.GraphResponse;
import com.flurry.android.impl.ads.protocol.v13.SdkLogRequest;
import com.flurry.android.impl.ads.protocol.v13.SdkLogResponse;
import com.google.ads.AdRequest;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class cf extends gp {
    private static final String a = cf.class.getSimpleName();
    private final gh<SdkLogRequest> b;
    private final gh<SdkLogResponse> g;

    public cf() {
        this(null);
    }

    public cf(gp.a aVar) {
        super(AdRequest.LOGTAG, cf.class.getSimpleName());
        this.b = new gh<>("sdk log request", new cl(SdkLogRequest.class));
        this.g = new gh<>("sdk log response", new cl(SdkLogResponse.class));
        this.f = "AdData_";
        a(aVar);
    }

    public void a(SdkLogRequest sdkLogRequest, String str, String str2, String str3) {
        byte[] bArrA;
        if (sdkLogRequest == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            gd.a(6, this.c, "Ad log that has to be sent is EMPTY or NULL");
            return;
        }
        try {
            bArrA = this.b.a(sdkLogRequest);
        } catch (Exception e) {
            gd.a(5, this.c, "Failed to encode sdk log request: " + e);
            bArrA = null;
        }
        if (bArrA != null) {
            b(a(bArrA, str), str2, str3);
        }
    }

    protected byte[] a(byte[] bArr, String str) {
        byte[] bytes = str.getBytes();
        byte[] bArrArray = ByteBuffer.allocate(4).putInt(bytes.length).array();
        byte[] bArr2 = new byte[bArrArray.length + bytes.length + bArr.length];
        for (int i = 0; i < bArr2.length; i++) {
            if (i < bArrArray.length) {
                bArr2[i] = bArrArray[i];
            } else if (i >= bArrArray.length && i < bytes.length + bArrArray.length) {
                bArr2[i] = bytes[i - 4];
            } else {
                bArr2[i] = bArr[(i - 4) - bytes.length];
            }
        }
        return bArr2;
    }

    protected Pair<String, byte[]> a(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[bArr.length - 4];
        for (int i = 0; i < bArr.length; i++) {
            if (i < 4) {
                bArr2[i] = bArr[i];
            } else {
                bArr3[i - 4] = bArr[i];
            }
        }
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = new byte[bArr3.length - i2];
        for (int i3 = 0; i3 < bArr3.length; i3++) {
            if (i3 < i2) {
                bArr4[i3] = bArr3[i3];
            } else {
                bArr5[i3 - i2] = bArr3[i3];
            }
        }
        return new Pair<>(new String(bArr4), bArr5);
    }

    @Override // com.flurry.sdk.gp
    protected void a(byte[] bArr, final String str, final String str2) {
        try {
            Pair<String, byte[]> pairA = a(bArr);
            String str3 = (String) pairA.first;
            byte[] bArr2 = (byte[]) pairA.second;
            gd.a(4, this.c, "FlurryAdLogsManager: start upload data with id = " + str + " to " + str3);
            gk gkVar = new gk();
            gkVar.a(str3);
            gkVar.a(100000);
            gkVar.a(gl.a.kPost);
            gkVar.a("Content-Type", "application/x-flurry");
            gkVar.a("Accept", "application/x-flurry");
            gkVar.a("FM-Checksum", Integer.toString(gh.c(bArr2)));
            gkVar.a((gx) new gt());
            gkVar.b(new gt());
            gkVar.a(bArr2);
            gkVar.a((gk.a) new gk.a<byte[], byte[]>() { // from class: com.flurry.sdk.cf.1
                @Override // com.flurry.sdk.gk.a
                public void a(gk<byte[], byte[]> gkVar2, byte[] bArr3) {
                    SdkLogResponse sdkLogResponse;
                    int iE = gkVar2.e();
                    if (gkVar2.c() && bArr3 != null) {
                        try {
                            sdkLogResponse = (SdkLogResponse) cf.this.g.d(bArr3);
                        } catch (Exception e) {
                            gd.a(5, cf.a, "Failed to decode sdk log response: " + e);
                            sdkLogResponse = null;
                        }
                    } else {
                        sdkLogResponse = null;
                    }
                    if (sdkLogResponse != null && sdkLogResponse.result.equals(GraphResponse.SUCCESS_KEY)) {
                        gd.a(5, cf.this.c, "FlurryAdLogsManager: ad report " + str + " sent. HTTP response: " + iE);
                        if (gd.c() <= 3 && gd.d()) {
                            fp.a().a(new Runnable() { // from class: com.flurry.sdk.cf.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Toast.makeText(fp.a().c(), "Ad log report sent", 0).show();
                                }
                            });
                        }
                        cf.this.a(str, str2, iE);
                        cf.this.e();
                        return;
                    }
                    if (sdkLogResponse != null) {
                        Iterator<String> it = sdkLogResponse.errors.iterator();
                        while (it.hasNext()) {
                            gd.a(6, cf.this.c, it.next());
                        }
                    }
                    cf.this.b(str, str2);
                }
            });
            fn.a().a(this, gkVar);
        } catch (Exception e) {
            gd.a(6, this.c, "Internal ERROR! Report is corrupt!");
            c(str, str2);
        }
    }
}
