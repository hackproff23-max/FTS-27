package defpackage;

import com.facebook.ads.InterstitialAd;
import com.nuance.nmsp.client.sdk.oem.b;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class ef implements bn.b, eg {
    private static final bm b = d.a(ef.class);
    protected byte a;
    private dp c;
    private dk d;
    private ej e;
    private dm f;
    private ce g;
    private short h;
    private bn i;
    private bq j;
    private long k;
    private boolean l;
    private String m;
    private boolean n = false;
    private a o;
    private Object p;

    public static class a {
        private int a;
        private String b;
        private String c;
        private ef d;
        private boolean e;

        protected a(int i, ef efVar) {
            this.a = i;
            if (efVar.f.f() != null) {
                this.b = dm.a(efVar.f.f());
            } else {
                this.b = "";
            }
            this.c = "INTERNAL_ERROR";
            this.d = efVar;
            this.e = false;
        }

        static /* synthetic */ void a(a aVar, String str) {
            if (aVar.c.equals("INTERNAL_ERROR")) {
                aVar.c = str;
            } else {
                aVar.c += InterstitialAd.SEPARATOR + str;
            }
            ef.a(aVar.d, aVar);
        }

        protected final int a() {
            return this.a;
        }

        protected final void a(eg.a aVar) {
            if (this.c.equals("INTERNAL_ERROR")) {
                this.c = aVar.toString();
            } else {
                this.c += InterstitialAd.SEPARATOR + aVar.toString();
            }
            ef.a(this.d, this);
        }

        protected final void a(String str) {
            this.b = str;
        }

        protected final String b() {
            return this.b;
        }

        protected final String c() {
            return this.c;
        }

        protected final void d() {
            this.e = true;
        }

        protected final boolean e() {
            return this.e;
        }
    }

    protected ef(bn bnVar, ej ejVar, String str, String str2, dm dmVar, String str3, String str4, String str5, String str6, String str7, String str8, bi biVar, String str9, String str10, String str11, String str12, String str13, cv cvVar, dp dpVar, dk dkVar, byte b2) {
        this.e = null;
        this.f = null;
        this.h = (short) -1;
        if (b.b()) {
            b.b("PDXTransactionImpl()");
        }
        this.p = new Object();
        this.i = bnVar;
        this.f = dmVar;
        this.e = ejVar;
        this.k = 30000L;
        this.c = dpVar;
        this.d = dkVar;
        this.a = b2;
        this.m = str;
        this.l = false;
        if (((dl) dpVar.g()).i() != null) {
            this.o = new a(this.a, this);
            b(this.o, "INTERNAL_ERROR");
        }
        synchronized (this.p) {
            this.h = (short) 0;
        }
        Object[] objArr = new Object[18];
        objArr[0] = str3;
        objArr[1] = str4;
        objArr[2] = str5;
        objArr[3] = str6;
        objArr[4] = str7;
        objArr[5] = str8;
        objArr[6] = biVar;
        objArr[7] = str9;
        objArr[8] = new Short((short) 1);
        objArr[9] = new Short((short) 1);
        objArr[10] = str10;
        objArr[11] = str11;
        objArr[12] = str12;
        objArr[13] = str13;
        objArr[14] = str2;
        objArr[15] = str;
        objArr[16] = cvVar;
        bn.a aVar = new bn.a((byte) 1, objArr);
        Thread threadCurrentThread = Thread.currentThread();
        bnVar.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    static /* synthetic */ void a(ef efVar, a aVar) {
        Vector vector = (Vector) ((dl) efVar.c.g()).i();
        if (vector == null) {
            b.c("appendLogToResLogs: NMSPDefines.DEVICE_CMD_LOG_TO_SERVER_ENABLED is disabled.");
        } else {
            if (vector.contains(aVar)) {
                return;
            }
            vector.addElement(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.g != null) {
            this.g.a(str).a();
        }
    }

    private void a(String str, boolean z) {
        if (this.g != null) {
            cg cgVarA = this.g.a("NMASResponse");
            try {
                cgVarA = cgVarA.a("ResultType", str);
                if (str.compareTo("QUERY_RESULT") == 0) {
                    cgVarA = cgVarA.a("IsFinal", z);
                }
            } catch (cf e) {
            }
            cgVarA.a();
        }
    }

    private static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            if (hexString.length() > 1) {
                stringBuffer.append(hexString.substring(hexString.length() - 2));
            } else {
                stringBuffer.append(hexString);
            }
            if (i == 3 || i == 5 || i == 7 || i == 9) {
                stringBuffer.append('-');
            }
        }
        return stringBuffer.toString();
    }

    static /* synthetic */ short b(ef efVar) {
        efVar.h = (short) -1;
        return (short) -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(a aVar, String str) {
        if (aVar != null) {
            a.a(aVar, str);
        }
    }

    @Override // defpackage.eg
    public final void a() throws da, db {
        if (b.b()) {
            b.b("PDXTransactionImpl.end()");
        }
        synchronized (this.p) {
            if (this.h == -1) {
                b.e("PDXTransactionImpl.end() transaction already finished!");
                throw new da("transaction already finished!");
            }
            if (this.h == 0) {
                this.h = (short) 1;
                bn bnVar = this.i;
                bn.a aVar = new bn.a((byte) 4, null);
                Thread threadCurrentThread = Thread.currentThread();
                this.i.a();
                bnVar.a(aVar, this, threadCurrentThread);
            } else {
                if (this.h == 1) {
                    b.e("PDXTransactionImpl.end() transaction already finished!");
                    throw new da("transaction already finished!");
                }
                if (this.h == 2) {
                    b.e("PDXTransactionImpl.end() transaction already expired!");
                    throw new db("transaction already expired!");
                }
            }
        }
    }

    @Override // defpackage.eg
    public final void a(bj.j jVar) throws da, db {
        if (b.b()) {
            b.b("PDXTransactionImpl.sendParam()");
        }
        if (jVar == null) {
            throw new NullPointerException("Parameter cannot be null");
        }
        synchronized (this.p) {
            if (this.h == -1) {
                b.e("PDXTransactionImpl.sendParam() transaction already finished!");
                throw new da("transaction already finished!");
            }
            if (this.h == 0) {
                bn bnVar = this.i;
                bn.a aVar = new bn.a((byte) 2, jVar);
                Thread threadCurrentThread = Thread.currentThread();
                this.i.a();
                bnVar.a(aVar, this, threadCurrentThread);
            } else {
                if (this.h == 1) {
                    b.e("PDXTransactionImpl.sendParam() transaction already finished!");
                    throw new da("transaction already finished!");
                }
                if (this.h == 2) {
                    b.e("PDXTransactionImpl.sendParam() transaction already expired!");
                    throw new db("transaction already expired!");
                }
            }
        }
    }

    public final void a(dy dyVar, byte b2) {
        b.b("PDXTransactionImpl.onQueryError()");
        if (b2 != this.a) {
            return;
        }
        synchronized (this.p) {
            if (this.h == -1 || this.h == 2) {
                return;
            }
            this.h = (short) -1;
            a("QUERY_ERROR", true);
            if (this.j != null) {
                this.i.a(this.j);
            }
            if (this.e != null) {
                try {
                    b(this.o, "QUERY_ERROR");
                    this.e.a((ek) dyVar);
                } catch (Throwable th) {
                    b.e("got exp in PDXCommandListener.PDXQueryErrorReturned() [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    public final void a(ea eaVar, byte b2) {
        if (b.b()) {
            b.b("PDXTransactionImpl.onQueryResults()");
        }
        if (b2 != this.a) {
            return;
        }
        synchronized (this.p) {
            if (this.h == -1 || this.h == 2) {
                return;
            }
            this.l = true;
            a("QUERY_RESULT", eaVar.g());
            if (this.j != null && eaVar.g()) {
                this.i.a(this.j);
            }
            if (this.e != null) {
                try {
                    if (eaVar.g()) {
                        b(this.o, "FINAL_RESULT");
                    }
                    this.e.a(eaVar);
                } catch (Throwable th) {
                    b.e("got exp in PDXCommandListener.PDXQueryResultReturned() [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    public final void a(eb ebVar, byte b2) {
        b.b("PDXTransactionImpl.onQueryRetry()");
        if (b2 != this.a) {
            return;
        }
        synchronized (this.p) {
            if (this.h == -1 || this.h == 2) {
                return;
            }
            this.h = (short) -1;
            a("QUERY_RETRY", true);
            if (this.j != null) {
                this.i.a(this.j);
            }
            if (this.e != null) {
                try {
                    b(this.o, "QUERY_RETRY");
                    this.e.a((el) ebVar);
                } catch (Throwable th) {
                    b.e("got exp in PDXCommandListener.PDXQueryRetryReturned() [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        Object obj3 = aVar.b;
        switch (aVar.a) {
            case 1:
                Object[] objArr = (Object[]) obj3;
                String str = (String) objArr[0];
                String str2 = (String) objArr[1];
                String str3 = (String) objArr[2];
                String str4 = (String) objArr[3];
                String str5 = (String) objArr[4];
                String str6 = (String) objArr[5];
                bi biVarC = (bi) objArr[6];
                String str7 = (String) objArr[7];
                short sShortValue = ((Short) objArr[8]).shortValue();
                short sShortValue2 = ((Short) objArr[9]).shortValue();
                String str8 = (String) objArr[10];
                String str9 = (String) objArr[11];
                String str10 = (String) objArr[12];
                String str11 = (String) objArr[13];
                byte[] bArrF = this.f.f();
                String str12 = (String) objArr[14];
                String str13 = (String) objArr[15];
                cv cvVar = (cv) objArr[16];
                if (new b(this.c.b).a()) {
                    biVarC = bx.c(biVarC);
                }
                Cdo.i();
                ce ceVarJ = this.f.j();
                if (ceVarJ != null) {
                    try {
                        this.g = ceVarJ.a("NMASCommand").a("Name", this.m).a("TransID", new Integer(this.a).toString()).a();
                        break;
                    } catch (cf e) {
                    }
                }
                dw dwVar = new dw(str, str2, str3, str4, str5, str6, biVarC, str7, sShortValue, sShortValue2, str8, str9, str10, str11, bArrF, str12, str13, cvVar);
                Vector vector = (Vector) ((dl) this.c.g()).i();
                if (vector == null) {
                    b.c("appendLogToQueryBegin: NMSPDefines.DEVICE_CMD_LOG_TO_SERVER_ENABLED is disabled");
                } else if (vector.size() == 0) {
                    b.c("appendLogToQueryBegin: nmasResLogsToServer is empty, nothing to log to server");
                } else {
                    int size = vector.size();
                    cv cqVar = new cq();
                    cw ctVar = new ct();
                    for (int i = 0; i < size; i++) {
                        a aVar2 = (a) vector.elementAt(i);
                        if (aVar2.a() != this.a) {
                            cq cqVar2 = new cq();
                            cqVar2.a("id", aVar2.b() + InterstitialAd.SEPARATOR + aVar2.a(), (short) 193);
                            cqVar2.a("status", aVar2.c().toString(), (short) 193);
                            ctVar.a(cqVar2);
                            aVar2.d();
                        }
                    }
                    cqVar.a("device_log", ctVar);
                    dwVar.a("app_info", cqVar);
                }
                this.f.a((short) 2597, "SEND_BCP_BEGIN" + ((int) this.a), dwVar.f(), null, this.a, 0L, this.c, false);
                if (bArrF != null) {
                    try {
                        if (!this.n) {
                            this.n = true;
                            b.b("PDXCommandCreated() called from handleInit()" + b(bArrF) + InterstitialAd.SEPARATOR + ((int) this.a) + " (" + this + "," + this.d + ")");
                            this.d.a(b(bArrF) + InterstitialAd.SEPARATOR + ((int) this.a));
                        }
                    } catch (Throwable th) {
                        b.e("got exp in PDXCommandListener.PDXCommandCreated() [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                        return;
                    }
                }
                break;
            case 2:
                Object obj4 = (bj.j) obj3;
                if (((dv) obj4).c() == 127) {
                    this.f.a(((ed) obj4).a(), ((ed) obj4).e(), this.c);
                }
                Cdo.i();
                if (obj4 instanceof dq) {
                    String strB = ((dq) obj4).b();
                    int iA = ((dq) obj4).a();
                    if (this.g != null) {
                        try {
                            this.g.a("NMASAudioParameter").a("Name", strB).a("AudioID", iA).a();
                            break;
                        } catch (cf e2) {
                        }
                    }
                }
                this.f.a((short) 2585, "SEND_BCP_DATA", new dz((dv) obj4).d(), null, this.a, 0L, this.c, false);
                break;
            case 3:
                Cdo.i();
                this.f.a((short) 2585, "SEND_BCP_DATA", new ds((byte[]) obj3).d(), null, this.a, 0L, this.c, false);
                break;
            case 4:
                Cdo.i();
                this.f.a((short) 2585, "SEND_BCP_DATA", new dx().d(), null, this.a, 0L, this.c, false);
                this.j = new bq() { // from class: ef.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            synchronized (ef.this.p) {
                                ef.b(ef.this);
                            }
                            ef.this.a("CommandExpired");
                            if (ef.this.e != null) {
                                ef.b.b("PDXTransactionImpl.end() timed out waiting for results. ");
                                try {
                                    ef efVar = ef.this;
                                    ef.b(ef.this.o, "TIMEOUT_CMD");
                                    ef.this.e.a((short) 1);
                                } catch (Throwable th2) {
                                    ef.b.e("got exp in PDXCommandListener.PDXCommandEvent(TIMED_OUT_WAITING_FOR_RESULT) [" + th2.getClass().getName() + "] msg [" + th2.getMessage() + "]");
                                }
                            }
                        } catch (Exception e3) {
                            ef.b.e("PDXTransactionImpl.run() " + e3.getClass().getName() + " " + e3.getMessage());
                        }
                    }
                };
                this.i.a(this.j, this.k);
                break;
            case 5:
                this.e.a((short) 4);
                break;
            case 6:
                eg.a aVar3 = (eg.a) obj3;
                a aVar4 = this.o;
                if (aVar4 != null) {
                    aVar4.a(aVar3);
                }
                break;
        }
    }

    public final void a(short s) {
        if (b.b()) {
            b.b("PDXTransactionImpl.onSessionDisconnected() " + ((int) s));
        }
        synchronized (this.p) {
            if (this.h == 1 && this.j != null) {
                this.i.a(this.j);
            }
            if (this.h == 2) {
                return;
            }
            try {
                if (s == 0) {
                    if (this.h != -1) {
                        b(this.o, "REMOTE_DISC");
                        this.e.a((short) 3);
                    }
                } else if (s == 1) {
                    if (this.h != -1) {
                        this.e.a((short) 4);
                    }
                } else if (s == 3) {
                    if (this.h != -1) {
                        b(this.o, "TIMEOUT_IDLE");
                        this.e.a((short) 5);
                    }
                } else if (s == 4) {
                    if (this.h != -1) {
                        b(this.o, "CONN_FAILED");
                        this.d.a();
                    }
                } else if (s == 5) {
                    if (this.h != -1) {
                        b(this.o, "CONN_FAILED");
                        this.d.a();
                    }
                } else if (s == 6) {
                    if (this.h != -1) {
                        b(this.o, "REMOTE_DISC");
                        this.e.a((short) 3);
                    }
                } else {
                    if (s != 7) {
                        if (s == 8) {
                            if (this.h != -1) {
                                b(this.o, "REMOTE_DISC");
                                this.e.a((short) 3);
                            }
                        }
                        this.h = (short) 2;
                    }
                    if (this.h != -1) {
                        b(this.o, "CONN_FAILED");
                        this.d.a();
                    }
                }
            } catch (Throwable th) {
                b.e("got exp in PDXCommandListener.PDXCommandEvent() or PDXManagerListener.PDXManagerError() or PDXManagerListener.PDXManagerDisconnected() reasonCode [" + ((int) s) + "] [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
            }
            this.h = (short) 2;
        }
    }

    public final void a(byte[] bArr) {
        if (b.b()) {
            b.b("PDXTransactionImpl.onSessionConnected()");
        }
        synchronized (this.p) {
            if (this.h == 0 || this.h == 1) {
                try {
                    String strB = b(bArr);
                    a aVar = this.o;
                    if (aVar != null) {
                        aVar.a(strB);
                    }
                    if (!this.n) {
                        this.n = true;
                        b.b("PDXCommandCreated() called from onSessionConnected()" + strB + InterstitialAd.SEPARATOR + ((int) this.a) + " (" + this + "," + this.d + ")");
                        this.d.a(strB + InterstitialAd.SEPARATOR + ((int) this.a));
                    }
                } catch (Throwable th) {
                    b.e("got exp in PDXCommandListener.PDXCommandCreated() [" + th.getClass().getName() + "] msg [" + th.getMessage() + "]");
                }
            }
        }
    }

    public final void b() {
        f();
    }

    public final void c() {
        f();
    }

    public final void d() {
        f();
    }

    public final void e() {
        synchronized (this.p) {
            if (this.h == 1 && this.j != null) {
                this.i.a(this.j);
            }
            if (this.h == 2) {
                return;
            }
            if (this.h != -1) {
                if (!this.l) {
                    a("CommandAbort");
                }
                bn bnVar = this.i;
                bn.a aVar = new bn.a((byte) 5, null);
                Thread threadCurrentThread = Thread.currentThread();
                this.i.a();
                bnVar.a(aVar, this, threadCurrentThread);
            }
            this.h = (short) 2;
        }
    }

    protected final void f() {
        if (this.j != null) {
            this.i.a(this.j);
            this.i.a(this.j, this.k);
        }
    }
}
