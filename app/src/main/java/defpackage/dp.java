package defpackage;

import android.os.Build;
import com.facebook.appevents.AppEventsConstants;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class dp extends Cdo implements eh {
    private static final bm g = d.a(dp.class);
    private static String k = Build.VERSION.RELEASE;
    private bn h;
    private dk i;
    private ef j;

    public dp(dl dlVar, dk dkVar, Vector vector) {
        super(dlVar, dkVar, vector);
        this.i = dkVar;
        this.h = dlVar.c_();
        this.a = dlVar.h();
        this.a.a((di) this);
        this.j = null;
        bn bnVar = this.h;
        bn.a aVar = new bn.a((byte) 0, null);
        Thread threadCurrentThread = Thread.currentThread();
        this.h.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    private void a(byte b) {
        Vector vector;
        if (b != this.j.a || (vector = (Vector) ((dl) g()).i()) == null || vector.size() <= 0) {
            return;
        }
        g.b("clearResLogsToServer() before clean the log vector tranId[" + ((int) b) + "] log list size [" + vector.size() + "]");
        int i = 0;
        while (i < vector.size()) {
            ef.a aVar = (ef.a) vector.elementAt(i);
            if (aVar.e()) {
                vector.removeElement(aVar);
            } else {
                i++;
            }
        }
        g.b("clearResLogsToServer() after clean the log vector tranId[" + ((int) b) + "] log list size [" + vector.size() + "]");
    }

    @Override // defpackage.eh
    public final bj.j a(String str, cv cvVar) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (cvVar == null) {
            throw new NullPointerException("dict can not be null");
        }
        return new dr(str, (cq) cvVar);
    }

    @Override // defpackage.eh
    public final bj.j a(String str, cv cvVar, cx cxVar) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (cvVar == null) {
            throw new NullPointerException("tts_dict can not be null");
        }
        if (cxVar == null) {
            throw new NullPointerException("audioSink can not be null");
        }
        return new ed(str, this.a, (cq) cvVar, cxVar);
    }

    @Override // defpackage.eh
    public final bj.j a(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (str2 == null) {
            throw new NullPointerException("text can not be null");
        }
        return new ee(str, str2);
    }

    @Override // defpackage.eh
    public final cx a(String str) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        return new dq(str, this.a, this.h);
    }

    @Override // defpackage.eh
    public final eg a(ej ejVar, String str, String str2, String str3, String str4, String str5, cv cvVar) throws dj {
        ef efVar;
        String str6 = ejVar == null ? "commandListener is invalid; " : "";
        if (str == null || str.equals("")) {
            str6 = str6 + "cmd should be non-null; ";
        }
        if (str2 == null || str2.equals("")) {
            str6 = str6 + "scriptVersion should be non-null; ";
        }
        if (str3 == null || str3.equals("")) {
            str6 = str6 + "dictationLanguage should be non-null; ";
        }
        if (str5 == null || str5.equals("")) {
            str6 = str6 + "phoneModel should be non-null; ";
        }
        if (30000 <= 0) {
            str6 = str6 + "commandTimeout is invalid; ";
        }
        if (!str6.equals("")) {
            g.e("NMASResourceImpl.createCommand() " + str6);
            throw new IllegalArgumentException(str6);
        }
        synchronized (this.e) {
            if (this.j != null) {
                this.j.e();
            }
            dm dmVar = this.a;
            dm.d();
            if (this.d == 0) {
                bn bnVar = this.h;
                bn.a aVar = new bn.a((byte) 0, null);
                Thread threadCurrentThread = Thread.currentThread();
                this.h.a();
                bnVar.a(aVar, this, threadCurrentThread);
            }
            this.f = this.a.h();
            this.j = new ef(this.h, ejVar, str, ((dl) this.c).b, this.a, ((dl) this.c).d(), AppEventsConstants.EVENT_PARAM_VALUE_YES, k, str2, "enus", "ne", ((dl) this.c).e(), str3, str4, str5, ((dl) this.c).d(), "", cvVar, this, this.i, this.f);
            efVar = this.j;
        }
        return efVar;
    }

    @Override // defpackage.Cdo, defpackage.di
    public final void a() throws dj {
        g.b("freeResource() disconnectTimeout:0");
        if (this.j != null) {
            this.j.e();
        }
        synchronized (this.e) {
            if (this.d != 2) {
                throw new dj("the resource was unloaded. ");
            }
            this.d = 0;
            Integer num = new Integer(0);
            bn bnVar = this.h;
            bn.a aVar = new bn.a((byte) 3, num);
            Thread threadCurrentThread = Thread.currentThread();
            this.h.a();
            bnVar.a(aVar, this, threadCurrentThread);
        }
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void a(byte b, short s) {
        if (b != this.f) {
            return;
        }
        if (this.j != null) {
            this.j.e();
        }
        super.a(b, s);
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void a(byte b, byte[] bArr) {
        dt dtVarA = du.a(bArr);
        switch (dtVarA.e()) {
            case 29185:
                a(b);
                if (this.j != null) {
                    this.j.a((ea) dtVarA, b);
                }
                break;
            case 29186:
                if (this.j != null) {
                    this.j.a((dy) dtVarA, b);
                }
                break;
            case 29187:
            case 29188:
            default:
                g.e("Session.parseXModeMsgBcpData() Unknown command: " + ((int) dtVarA.e()) + ". ");
                break;
            case 29189:
                a(b);
                if (this.j != null) {
                    this.j.a((eb) dtVarA, b);
                }
                break;
        }
    }

    @Override // defpackage.Cdo, bn.b
    public final void a(Object obj, Object obj2) {
        switch (((bn.a) obj).a) {
            case 0:
                super.a(this);
                break;
            case 1:
            case 2:
            default:
                super.a(obj, obj2);
                break;
            case 3:
                this.a.b(this);
                dk dkVar = this.i;
                break;
        }
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void a(short s) {
        g.b("onSessionDisconnected() reasonCode:" + ((int) s));
        if (this.j != null) {
            this.j.a(s);
        }
        super.a(s);
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void a(byte[] bArr) {
        super.a(bArr);
        if (this.j != null) {
            this.j.a(bArr);
        }
    }

    @Override // defpackage.eh
    public final bj.j b(String str, cv cvVar) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (cvVar == null) {
            throw new NullPointerException("dict can not be null");
        }
        return new ec(str, (cq) cvVar, (byte) 2);
    }

    @Override // defpackage.eh
    public final bj.j c(String str, cv cvVar) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (cvVar == null) {
            throw new NullPointerException("dict can not be null");
        }
        return new ec(str, (cq) cvVar, (byte) 3);
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void c() {
        this.j.f();
    }

    @Override // defpackage.eh
    public final bj.j d(String str, cv cvVar) {
        if (str == null) {
            throw new NullPointerException("name can not be null");
        }
        if (cvVar == null) {
            throw new NullPointerException("dict can not be null");
        }
        return new ec(str, (cq) cvVar, (byte) 1);
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void d() {
        this.j.b();
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void e() {
        this.j.c();
    }

    @Override // defpackage.Cdo, defpackage.dn
    public final void f() {
        this.j.d();
    }

    @Override // defpackage.Cdo
    public final long h() {
        return super.h();
    }

    @Override // defpackage.eh
    public final cv j() {
        return new cq();
    }

    @Override // defpackage.eh
    public final cw k() {
        return new ct();
    }
}
