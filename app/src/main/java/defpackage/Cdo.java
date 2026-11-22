package defpackage;

import com.facebook.ads.InterstitialAd;
import com.nuance.nmsp.client.sdk.oem.b;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: renamed from: do, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public class Cdo implements bn.b, di, dn {
    private static final bm g = d.a(Cdo.class);
    private static long o = 1;
    protected dm a;
    protected df c;
    protected byte f;
    private dk h;
    private dn i;
    private bi j;
    private bi k;
    private bn m;
    private long p;
    protected int d = 0;
    private byte l = 5;
    protected Object e = new Object();
    private Hashtable n = new Hashtable();
    private Hashtable q = new Hashtable();
    public Vector b = new Vector();

    public Cdo(dl dlVar, dk dkVar, Vector vector) {
        int i = 0;
        this.f = (byte) 0;
        this.c = dlVar;
        this.a = dlVar.h();
        this.h = dkVar;
        this.j = dlVar.e();
        this.k = dlVar.f();
        this.m = dlVar.c_();
        this.f = this.a.h();
        this.p = this.a.g();
        if (vector == null) {
            return;
        }
        while (true) {
            int i2 = i;
            if (i2 >= vector.size()) {
                return;
            }
            this.b.addElement(((cz) vector.elementAt(i2)).clone());
            i = i2 + 1;
        }
    }

    private static cz.a a(String str) {
        if (str.equalsIgnoreCase("sdk")) {
            return cz.a.a;
        }
        if (str.equalsIgnoreCase("nmsp")) {
            return cz.a.b;
        }
        if (str.equalsIgnoreCase("app")) {
            return cz.a.c;
        }
        if (str.equalsIgnoreCase("nss")) {
            return cz.a.d;
        }
        if (str.equalsIgnoreCase("slog")) {
            return cz.a.e;
        }
        if (str.equalsIgnoreCase("nsslog")) {
            return cz.a.f;
        }
        if (str.equalsIgnoreCase("gwlog")) {
            return cz.a.g;
        }
        if (str.equalsIgnoreCase("svsp")) {
            return cz.a.h;
        }
        if (str.equalsIgnoreCase("sip")) {
            return cz.a.i;
        }
        if (str.equalsIgnoreCase("sdp")) {
            return cz.a.j;
        }
        return null;
    }

    private void a(Vector vector, long j) {
        if (this.d != 2) {
            dk dkVar = this.h;
        }
        byte[] bArr = new byte[0];
        this.n.put(new Long(j), new Byte((byte) 2));
        this.q.put(new Long(j), vector);
        String str = "<gp><rid>" + j + "</rid>";
        for (int i = 0; i < vector.size(); i++) {
            cz czVar = (cz) vector.elementAt(i);
            if (czVar.d() == cz.a.c || czVar.d() == cz.a.b || czVar.d() == cz.a.d || czVar.d() == cz.a.h) {
                str = str + "<" + czVar.d() + " p=\"" + czVar.a() + "\"/>";
            }
        }
        this.a.a((short) 2583, "SEND_BCP_GET_PARAMS", (str + "</gp>").getBytes(), bArr, this.f, j, this.i, false);
    }

    public static long i() {
        return 0L;
    }

    @Override // defpackage.di
    public void a() throws dj {
        g.b("freeResource, disconnect timeout: 0");
        synchronized (this.e) {
            if (this.d != 2) {
                g.e("ResourceException the resource was unloaded. ");
                throw new dj("the resource was unloaded. ");
            }
            this.d = 0;
            Integer num = new Integer(0);
            bn bnVar = this.m;
            bn.a aVar = new bn.a((byte) 3, num);
            Thread threadCurrentThread = Thread.currentThread();
            this.m.a();
            bnVar.a(aVar, this, threadCurrentThread);
        }
    }

    @Override // defpackage.dn
    public final void a(byte b, long j, short s, short s2, short s3) {
        Byte b2;
        g.b("onBcpResponse, TID: " + ((int) b) + ", RID: " + j + ", status code: " + ((int) s) + " , request state: " + ((int) s2) + ", completion cause: " + ((int) s3));
        if (s == 200 || (b2 = (Byte) this.n.get(new Long(j))) == null) {
            return;
        }
        switch (b2.byteValue()) {
            case 1:
                this.n.remove(new Long(j));
                dk dkVar = this.h;
                break;
            case 2:
                this.n.remove(new Long(j));
                dk dkVar2 = this.h;
                break;
        }
    }

    @Override // defpackage.dn
    public final void a(byte b, long j, short s, byte[] bArr) {
        String strSubstring;
        int iIndexOf;
        g.b("onBcpSetParamsComplete, TID: " + ((int) b) + ", RID: " + j + ", status code: " + ((int) s));
        if (this.n.remove(new Long(j)) == null) {
            g.d("onBcpSetParamsComplete, RID: " + j + " already removed!");
            return;
        }
        Vector vector = new Vector();
        if (bArr != null) {
            String str = new String(bArr);
            int i = str.startsWith(";") ? 1 : 0;
            while (true) {
                int iIndexOf2 = str.indexOf(";", i);
                if (iIndexOf2 == -1) {
                    break;
                }
                String strSubstring2 = str.substring(i, iIndexOf2);
                int iIndexOf3 = strSubstring2.indexOf(".");
                if (iIndexOf3 != -1) {
                    vector.addElement(new cz(strSubstring2.substring(iIndexOf3 + 1), a(strSubstring2.substring(0, iIndexOf3))));
                }
                i = iIndexOf2 + 1;
            }
            if (i < str.length() && (iIndexOf = (strSubstring = str.substring(i)).indexOf(".")) != -1) {
                vector.addElement(new cz(strSubstring.substring(iIndexOf + 1), a(strSubstring.substring(0, iIndexOf))));
            }
        }
        if (s == 200 || s == 201) {
            dk dkVar = this.h;
        } else {
            dk dkVar2 = this.h;
        }
    }

    @Override // defpackage.dn
    public void a(byte b, short s) {
        g.b("onBcpEvent, TID: " + ((int) b) + ", event code: " + ((int) s));
        if (this.d == 2) {
            this.d = 0;
            Enumeration enumerationKeys = this.n.keys();
            while (enumerationKeys.hasMoreElements()) {
                switch (((Byte) this.n.remove((Long) enumerationKeys.nextElement())).byteValue()) {
                    case 1:
                        dk dkVar = this.h;
                        break;
                    case 2:
                        dk dkVar2 = this.h;
                        break;
                }
            }
            dk dkVar3 = this.h;
            this.a.b(this);
        }
    }

    @Override // defpackage.dn
    public void a(byte b, byte[] bArr) {
    }

    protected final void a(dn dnVar) {
        g.b("loadResource");
        if (new b(this.b).a()) {
            this.j = bx.c(this.j);
            this.k = bx.c(this.k);
            ((dl) this.c).a(this.j);
            ((dl) this.c).b(this.k);
        }
        this.i = dnVar;
        this.a.a(dnVar);
        if (this.d == 0) {
            if (this.a.f() != null) {
                this.d = 2;
            } else {
                this.a.a(this.j, this.k);
                this.d = 1;
            }
            long jH = h();
            String str = "<lr><rid>" + jH + "</rid>";
            switch (this.l) {
                case 1:
                    str = str + "<nr9><reco/></nr9>";
                    break;
                case 2:
                    str = str + "<nr9><tts/></nr9>";
                    break;
                case 3:
                    str = str + "<oper></oper>";
                    break;
                case 4:
                    str = (str + "<dict>") + "</dict>";
                    break;
                case 5:
                    return;
            }
            this.a.a((short) 2599, "SEND_BCP_LOAD_RESOURCE", (str + "</lr>").getBytes(), null, this.f, jH, dnVar, false);
        }
    }

    @Override // bn.b
    public void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 1:
                Object[] objArr = (Object[]) aVar.b;
                Vector vector = (Vector) objArr[0];
                long jLongValue = ((Long) objArr[1]).longValue();
                if (this.d != 2) {
                    dk dkVar = this.h;
                } else {
                    boolean z = true;
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[4];
                    this.n.put(new Long(jLongValue), new Byte((byte) 1));
                    int i = 0;
                    int i2 = 1;
                    String str = "<sp><rid>" + jLongValue + "</rid>";
                    while (true) {
                        int i3 = i;
                        if (i3 < vector.size()) {
                            cz czVar = (cz) vector.elementAt(i3);
                            if (czVar.d() == cz.a.c || czVar.d() == cz.a.b || czVar.d() == cz.a.d) {
                                z = false;
                                str = str + "<" + czVar.d() + " p=\"" + czVar.a() + "\" v=\"" + bx.a(new String(czVar.b())) + "\"/>";
                            } else {
                                if (czVar.d() == cz.a.h) {
                                    z = false;
                                }
                                str = str + "<" + czVar.d().toString() + " p=\"" + czVar.a() + "\" v=\"" + i2 + "\"/>";
                                byte[] bArrB = czVar.b();
                                bu.a(bArrB.length, bArr, 0);
                                byteArrayOutputStream.write(bArr, 0, 4);
                                byteArrayOutputStream.write(bArrB, 0, bArrB.length);
                                i2++;
                            }
                            i = i3 + 1;
                        } else {
                            this.a.a((short) 2581, "SEND_BCP_SET_PARAMS", (str + "</sp>").getBytes(), byteArrayOutputStream.toByteArray(), this.f, jLongValue, this.i, z);
                            try {
                                byteArrayOutputStream.close();
                            } catch (Exception e) {
                                return;
                            }
                        }
                    }
                }
                break;
            case 2:
                Object[] objArr2 = (Object[]) aVar.b;
                a((Vector) objArr2[0], ((Long) objArr2[1]).longValue());
                break;
            case 3:
                this.a.a(this.f, ((Integer) aVar.b).intValue(), this.i);
                dk dkVar2 = this.h;
                break;
            case 4:
                int iIntValue = ((Integer) aVar.b).intValue();
                long jH = h();
                this.a.a((short) 2608, "SEND_BCP_FREE_RESOURCE_ID", ((((((("<fr><rid>" + jH + "</rid>") + "<n>1</n>") + "<resids>") + "<res1><id>" + this.p + "</id>") + "<timeout>" + iIntValue + "<timeout></res1>") + "</resids>") + "</fr>").getBytes(), null, this.f, jH, this.i, true);
                break;
        }
    }

    @Override // defpackage.dn
    public void a(short s) {
        if (g.b()) {
            g.b("onSessionDisconnected, reason code: " + ((int) s));
        }
        Enumeration enumerationKeys = this.n.keys();
        while (enumerationKeys.hasMoreElements()) {
            switch (((Byte) this.n.remove((Long) enumerationKeys.nextElement())).byteValue()) {
                case 1:
                    dk dkVar = this.h;
                    break;
                case 2:
                    dk dkVar2 = this.h;
                    break;
            }
        }
        synchronized (this.e) {
            if (this.d == 2) {
                dk dkVar3 = this.h;
            }
            this.d = 0;
        }
    }

    @Override // defpackage.dn
    public void a(byte[] bArr) {
        if (g.b()) {
            g.b("onSessionConnected, SID: " + bArr);
        }
        synchronized (this.e) {
            this.d = 2;
        }
    }

    @Override // defpackage.dn
    public final void b() {
        dk dkVar = this.h;
    }

    @Override // defpackage.dn
    public final void b(byte b, long j, short s, byte[] bArr) {
        int i;
        int i2 = 0;
        g.b("onBcpGetParamsComplete, TID: " + ((int) b) + ", RID: " + j);
        if (this.n.remove(new Long(j)) == null) {
            g.d("onBcpGetParamsComplete, RID: " + j + " already removed!");
            this.q.remove(new Long(j));
            return;
        }
        Vector vector = (Vector) this.q.remove(new Long(j));
        if (vector == null) {
            g.e("Could not find the grammars associated with RID: " + j);
            return;
        }
        String str = new String(bArr);
        if (str.startsWith(";")) {
            i2 = 1;
            i = 0;
        } else {
            i = 0;
        }
        while (true) {
            int iIndexOf = str.indexOf(";", i2);
            if (iIndexOf == -1) {
                break;
            }
            String strSubstring = str.substring(i2, iIndexOf);
            if (strSubstring.indexOf(InterstitialAd.SEPARATOR) == -1) {
                cz czVar = (cz) vector.elementAt(i);
                vector.setElementAt(new cz(czVar.a(), strSubstring.getBytes(), czVar.d()), i);
            }
            i++;
            i2 = iIndexOf + 1;
        }
        if (i2 < str.length()) {
            String strSubstring2 = str.substring(i2);
            if (strSubstring2.indexOf(InterstitialAd.SEPARATOR) == -1) {
                cz czVar2 = (cz) vector.elementAt(i);
                vector.setElementAt(new cz(czVar2.a(), strSubstring2.getBytes(), czVar2.d()), i);
            }
        }
        if (s == 200 || s == 201) {
            dk dkVar = this.h;
        } else {
            dk dkVar2 = this.h;
        }
    }

    @Override // defpackage.dn
    public void c() {
    }

    @Override // defpackage.dn
    public void d() {
    }

    @Override // defpackage.dn
    public void e() {
    }

    @Override // defpackage.dn
    public void f() {
    }

    public final df g() {
        return this.c;
    }

    protected synchronized long h() {
        long j;
        j = o;
        o = 1 + j;
        if (o == Long.MIN_VALUE) {
            o = 1L;
        }
        return j;
    }
}
