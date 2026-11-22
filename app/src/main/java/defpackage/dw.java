package defpackage;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.millennialmedia.android.MMException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class dw extends dt {
    private static final bm a = d.a(dw.class);

    public dw(String str, String str2, String str3, String str4, String str5, String str6, bi biVar, String str7, short s, short s2, String str8, String str9, String str10, String str11, byte[] bArr, String str12, String str13, cv cvVar) {
        super((short) 514);
        if (a.b()) {
            a.b("PDXQueryBegin()");
        }
        a("uid", str, (short) 193);
        a("pdx_version", str2, (short) 193);
        a("client_version", str3, (short) 193);
        a("script_version", str4, (short) 193);
        a("language", str5, (short) 193);
        a("region", str6, (short) 193);
        a("device_codec", biVar.a());
        a("dictation_language", str7, (short) 193);
        a("lcd_width", s);
        a("lcd_height", s2);
        if (str8 == null) {
            a("carrier", new byte[0], (short) 5);
        } else {
            a("carrier", str8, (short) 193);
        }
        a("phone_model", str9, (short) 193);
        a("phone_number", str10, (short) 193);
        a("original_session_id", str11, (short) 22);
        if (bArr != null) {
            a("new_session_id", dm.a(bArr), (short) 193);
        }
        a("application", str12, (short) 22);
        a("nmaid", str12, (short) 22);
        a("command", str13, (short) 22);
        if (cvVar != null) {
            Enumeration enumerationA = cvVar.a();
            while (enumerationA.hasMoreElements()) {
                String str14 = (String) enumerationA.nextElement();
                switch (((cq) cvVar).b(str14).c()) {
                    case 4:
                        a(str14, cvVar.e(str14), (short) 4);
                        break;
                    case 5:
                        a(str14, (byte[]) null, (short) 5);
                        break;
                    case 16:
                        a.e("PDXQueryBegin() Sequence not accepted in optionalKeys");
                        break;
                    case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                        a(str14, cvVar.g(str14), (short) 22);
                        break;
                    case DownloaderService.STATUS_RUNNING /* 192 */:
                        a(str14, cvVar.d(str14));
                        break;
                    case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                        a(str14, cvVar.f(str14), (short) 193);
                        break;
                    case 224:
                        a.e("PDXQueryBegin() Dictionary not accepted in optionalKeys");
                        break;
                }
            }
        }
    }
}
