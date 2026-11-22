package defpackage;

import com.facebook.internal.ServerProtocol;
import com.google.android.gms.drive.FileUploadPreferences;
import com.nuance.nmsp.client.sdk.oem.h;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class cb implements bn.b, bp.a, bp.d, bp.e, bp.f {
    private static bm c = d.a(cb.class);
    private a A;
    private ce B;
    protected byte a;
    public byte[] b;
    private bq d;
    private int e;
    private bq f;
    private int g;
    private bq h;
    private String i;
    private short j;
    private Vector k;
    private boolean l;
    private Vector m;
    private bn n;
    private Object o;
    private String s;
    private String t;
    private byte[] u;
    private int x;
    private short y;
    private short z;
    private bs p = null;
    private short q = 0;
    private short r = 9;
    private bp v = null;
    private String w = "";
    private cl C = null;
    private Queue D = new LinkedList();
    private Queue E = new LinkedList();

    public interface a {
        void a();

        void a(bs bsVar, byte[] bArr);

        void a(String str);

        void a(short s, short s2);

        void b(String str);
    }

    class b {
        byte[] a;
        Object b;

        public b(byte[] bArr, Object obj) {
            this.a = bArr;
            this.b = obj;
        }
    }

    public cb(String str, short s, String str2, byte[] bArr, String str3, a aVar, Vector vector, bn bnVar) {
        this.e = 30;
        this.g = 50;
        this.i = null;
        this.j = (short) 0;
        this.l = false;
        this.m = null;
        this.n = null;
        this.s = "Not specified";
        this.t = "Not specified";
        this.u = null;
        this.i = str;
        this.j = s;
        this.s = str2;
        this.u = bArr;
        this.t = str3;
        this.A = aVar;
        if (vector != null) {
            this.k = vector;
        } else {
            this.k = new Vector();
        }
        this.n = bnVar;
        if (c.b()) {
            c.b("XMode() server: " + str + " port: " + ((int) s));
        }
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            cz czVar = (cz) enumerationElements.nextElement();
            if (c.b()) {
                c.b("XMode() " + czVar.d() + " : " + czVar.a() + " = " + new String(czVar.b()));
            }
            if (czVar.d() == cz.a.a) {
                if (czVar.a().equals("IdleSessionTimeout")) {
                    int i = Integer.parseInt(new String(czVar.b()));
                    if (i > 0) {
                        this.g = i;
                    }
                } else if (czVar.a().equals("ConnectionTimeout")) {
                    this.e = Integer.parseInt(new String(czVar.b()));
                } else if (czVar.a().equals("SSL_Socket_Enable") || czVar.a().equals("SSL_Cert_Summary") || czVar.a().equals("SSL_Cert_Data") || czVar.a().equals("SSL_SelfSigned_Cert")) {
                    if (czVar.a().equals("SSL_Socket_Enable") && (new String(czVar.b()).equals("TRUE") || new String(czVar.b()).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE))) {
                        this.l = true;
                    }
                    if (this.m == null) {
                        this.m = new Vector();
                    }
                    this.m.addElement(czVar);
                }
            }
        }
        this.a = (byte) 3;
    }

    static /* synthetic */ short a(cb cbVar) {
        cbVar.q = (short) 3;
        return (short) 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte b2, Object obj) {
        try {
            bn bnVar = this.n;
            bn.a aVar = new bn.a(b2, obj);
            Thread threadCurrentThread = Thread.currentThread();
            this.n.a();
            bnVar.a(aVar, this, threadCurrentThread);
        } catch (Exception e) {
            if (c.e()) {
                c.e("XMode.sendCmdMsg() " + e.getMessage());
            }
        }
    }

    private void a(bs bsVar, byte[] bArr) {
        ch chVar;
        if (c.b()) {
            c.b("XMode.parseXModeMsg() protocol: " + ((int) bsVar.a) + " cmd: " + ((int) bsVar.b));
        }
        switch (bsVar.a) {
            case 1:
                if (this.h != null && this.n.a(this.h)) {
                    this.h = new bq() { // from class: cb.8
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                cb.a(cb.this);
                                if (cb.c.e()) {
                                    cb.c.e("Session Idle for too long, longer than [" + cb.this.g + "] (initiated from XMode.parseVap)");
                                }
                                cb.this.a = (byte) 2;
                                cb.this.a((byte) 4, (Object) null);
                            } catch (Exception e) {
                                if (cb.c.e()) {
                                    cb.c.e("XMode.parseXModeMsg() " + e.getClass().getName() + " " + e.getMessage());
                                }
                            }
                        }
                    };
                    this.n.a(this.h, this.g * 1000);
                }
                this.A.a(bsVar, bArr);
                return;
            case 2:
                if (this.h != null && this.n.a(this.h)) {
                    this.h = new bq() { // from class: cb.7
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                cb.a(cb.this);
                                if (cb.c.e()) {
                                    cb.c.e("Session Idle for too long, longer than [" + cb.this.g + "] (initiated from XMode.parseBcp)");
                                }
                                cb.this.a = (byte) 2;
                                cb.this.a((byte) 4, (Object) null);
                            } catch (Exception e) {
                                if (cb.c.e()) {
                                    cb.c.e("XMode.parseXModeMsg() " + e.getClass().getName() + " " + e.getMessage());
                                }
                            }
                        }
                    };
                    this.n.a(this.h, this.g * 1000);
                }
                if (bsVar.b != 2641) {
                    this.A.a(bsVar, bArr);
                    return;
                }
                int iB = bu.b(bArr, 17);
                short sA = bu.a(bArr, 21);
                ch chVar2 = (ch) ((cm) this.B).e();
                if (sA == 0 || sA == 1 || sA == 3) {
                    chVar2.a(iB);
                    return;
                } else {
                    if (c.e()) {
                        c.e("XMode.parseXModeMsgBcpLogResponse() XMODE_BCP_LOG_WRITE_ERROR. ");
                        return;
                    }
                    return;
                }
            case 3:
                switch (bsVar.b) {
                    case FileUploadPreferences.BATTERY_USAGE_CHARGING_ONLY /* 257 */:
                        this.n.a(this.f);
                        if (this.C != null) {
                            this.C.a();
                            this.C = null;
                        }
                        this.b = new byte[16];
                        System.arraycopy(bArr, 0, this.b, 0, 16);
                        bn bnVar = this.n;
                        byte[] bArr2 = this.b;
                        bp bpVar = this.v;
                        Object obj = this.o;
                        byte[] bArr3 = this.b;
                        if (this.B != null && (chVar = (ch) ((cm) this.B).e()) != null) {
                            chVar.c();
                        }
                        this.w = bm.b(this.b);
                        bm bmVar = c;
                        String str = this.w;
                        bmVar.f();
                        try {
                            if (c.b()) {
                                c.b("Received COP_Connected " + this.w);
                            }
                            a((byte) 10, (Object) null);
                            c.g();
                            a("SocketOpened");
                            this.h = new bq() { // from class: cb.6
                                @Override // java.lang.Runnable
                                public final void run() {
                                    try {
                                        cb.a(cb.this);
                                        if (cb.c.e()) {
                                            cb.c.e("Session Idle for too long, longer than [" + cb.this.g + "] (initiated from XMode.parseCopConnected)");
                                        }
                                        cb.this.a = (byte) 2;
                                        cb.this.a((byte) 4, (Object) null);
                                    } catch (Exception e) {
                                        if (cb.c.e()) {
                                            cb.c.e("XMode.parseXModeMsg() " + e.getClass().getName() + " " + e.getMessage());
                                        }
                                    }
                                }
                            };
                            this.n.a(this.h, this.g * 1000);
                        } catch (Throwable th) {
                            c.g();
                            throw th;
                        }
                        break;
                    case 258:
                        this.x = bu.b(bArr, 0);
                        a((byte) 8, (Object) null);
                        break;
                    case 512:
                        if (this.h != null) {
                            this.n.a(this.h);
                        }
                        this.r = bu.a(bArr, 0);
                        this.a = (byte) 2;
                        this.q = (short) 6;
                        if (c.d()) {
                            c.d("XMode.parseXModeMsgCopDisconnect() Received COP DISCONNECT. ");
                        }
                        a((byte) 4, (Object) null);
                        break;
                    case 768:
                        short sA2 = bu.a(bArr, 0);
                        bu.b(bArr, 2);
                        this.q = (short) 7;
                        this.r = sA2;
                        this.a = (byte) 2;
                        if (c.e()) {
                            c.e("XMode.parseXModeMsgCopConnectFailed() COP CONNECT failure. ");
                        }
                        a((byte) 4, (Object) null);
                        break;
                }
                this.A.a(bsVar, bArr);
                return;
            case 15:
                return;
            default:
                if (c.e()) {
                    c.e("XMode.parseXModeMsg() unknown protocol: " + Integer.toHexString(bsVar.a));
                    return;
                }
                return;
        }
    }

    private void a(String str) {
        if (this.B != null) {
            this.B.a(str).a();
        }
    }

    static /* synthetic */ cl d(cb cbVar) {
        cbVar.C = null;
        return null;
    }

    @Override // bp.a
    public final void a() {
        ch chVar;
        if (c.b()) {
            c.b("XMode.closeSocketCallback() " + this.w);
        }
        this.a = (byte) 3;
        if (this.d != null) {
            this.n.a(this.d);
            this.d = null;
        }
        if (this.h != null) {
            this.n.a(this.h);
            this.h = null;
        }
        if (this.o != null) {
            this.A.a(this.q, this.r);
        }
        if (this.B != null && (chVar = (ch) ((cm) this.B).e()) != null) {
            chVar.a(this.q, this.r);
        }
        this.o = null;
        this.v = null;
        this.b = null;
        this.w = "";
    }

    public final void a(int i) {
        if (c.b()) {
            c.b("XMode.startStreaming() audio id: " + i);
        }
        if (this.a != 1) {
            return;
        }
        byte[] bArr = new byte[6];
        bu.a(i, bArr, 0);
        bu.a(this.y, bArr, 4);
        a(br.a((byte) 1, (byte) 18, (short) 257, bArr), 3, "SEND_VAP_RECORD_BEGIN");
    }

    @Override // bp.d
    public final void a(bp.c cVar, Object obj) {
        if (c.b()) {
            c.b("XMode.openSocketCallback() ");
        }
        if (cVar == bp.c.a) {
            this.o = obj;
            if (this.a != 0) {
                this.a = (byte) 2;
                this.v.a(this.o);
                return;
            } else if (this.B == null) {
                a((byte) 7, (Object) null);
                return;
            } else {
                a((byte) 13, (Object) null);
                return;
            }
        }
        if (cVar != bp.c.b) {
            if (cVar == bp.c.c) {
                this.q = (short) 4;
                if (c.e()) {
                    c.e("XMode.openSocketCallback() NETWORK_MEMORY_ERROR");
                    return;
                }
                return;
            }
            return;
        }
        if (c.e()) {
            c.e("XMode.openSocketCallback() NETWORK_ERROR");
        }
        this.a = (byte) 3;
        this.q = (short) 4;
        this.A.a(this.q, this.r);
        this.o = null;
        this.v = null;
        this.b = null;
        this.w = "";
    }

    @Override // bp.f
    public final void a(bp.c cVar, Object obj, int i, int i2, Object obj2) {
        int i3 = 0;
        String str = (String) ((b) obj2).b;
        if (cVar == bp.c.a && i == i2) {
            if (str.equals("SEND_COP_CONNECT")) {
                a((byte) 5, (Object) null);
            } else if (str.equals("SEND_COP_DISCONNECT")) {
                this.v.a(obj);
            }
            a((byte) 11, str);
            this.D.remove(obj2);
            if (this.D.size() != 0 || this.E.size() <= 0) {
                return;
            }
            a((byte) 14, (Object) null);
            return;
        }
        if (cVar == bp.c.b) {
            if (this.q != 1 && this.q != 3) {
                this.q = (short) 8;
                if (c.e()) {
                    c.e("XMode.writeSocketCallback() NETWORK_ERROR");
                }
            }
            a((byte) 12, str);
            this.D.remove(obj2);
            if (this.D.size() == 0) {
                int size = this.E.size();
                while (i3 < size) {
                    a((byte) 12, ((b) this.E.remove()).b);
                    i3++;
                }
                return;
            }
            return;
        }
        if (cVar == bp.c.c) {
            if (this.q != 1 && this.q != 3) {
                this.q = (short) 8;
                if (c.e()) {
                    c.e("XMode.writeSocketCallback() NETWORK_MEMORY_ERROR");
                }
            }
            a((byte) 12, str);
            this.D.remove(obj2);
            if (this.D.size() == 0) {
                int size2 = this.E.size();
                while (i3 < size2) {
                    a((byte) 12, ((b) this.E.remove()).b);
                    i3++;
                }
            }
        }
    }

    @Override // bp.e
    public final void a(bp.c cVar, Object obj, byte[] bArr, int i, int i2, Object obj2) {
        String str = (String) obj2;
        if (c.b()) {
            c.b("Read callback");
        }
        if (c.a()) {
            c.a(bArr);
        }
        if (cVar != bp.c.a) {
            if (cVar == bp.c.b) {
                if (this.q != 1 && this.q != 3 && this.q != 6) {
                    this.q = (short) 8;
                    if (c.e()) {
                        c.e("XMode.readSocketCallback() NETWORK_ERROR");
                    }
                }
                a((byte) 4, (Object) null);
                return;
            }
            if (cVar != bp.c.c || this.q == 1 || this.q == 3) {
                return;
            }
            this.q = (short) 8;
            if (c.e()) {
                c.e("XMode.readSocketCallback() NETWORK_MEMORY_ERROR");
                return;
            }
            return;
        }
        if (!str.equals("READ_XMODE_HEADER")) {
            if (str.equals("READ_XMODE_PAYLOAD")) {
                if (i2 == 0) {
                    if (c.b()) {
                        c.b(Integer.toHexString(this.p.b) + " payload not read bytesRead is 0");
                    }
                    this.d = new bq() { // from class: cb.3
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                cb.this.a((byte) 6, (Object) null);
                            } catch (Exception e) {
                                if (cb.c.e()) {
                                    cb.c.e("XMode.readSocketCallback() " + e.getClass().getName() + " " + e.getMessage());
                                }
                            }
                        }
                    };
                    this.n.a(this.d, 20L);
                    return;
                }
                if (i2 == i) {
                    if (this.p.c <= i) {
                        a(this.p, bArr);
                    }
                    a((byte) 5, (Object) null);
                    return;
                } else {
                    if (c.e()) {
                        c.e("----***---- readSocketCallback fatal error in readSocketCallback NET_CONTEXT_READ_XMODE_PAYLOAD bytesRead:[" + i2 + "] bufferLen:[" + i + "]");
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (i2 == 0) {
            this.d = new bq() { // from class: cb.2
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        cb.this.a((byte) 5, (Object) null);
                    } catch (Exception e) {
                        if (cb.c.e()) {
                            cb.c.e("XMode.readSocketCallback() " + e.getClass().getName() + " " + e.getMessage());
                        }
                    }
                }
            };
            this.n.a(this.d, 20L);
            return;
        }
        if (i2 != i) {
            if (c.e()) {
                c.e("----***---- readSocketCallback fatal error in readSocketCallback NET_CONTEXT_READ_XMODE_HEADER bytesRead:[" + i2 + "] bufferLen:[" + i + "]");
                return;
            }
            return;
        }
        this.p = new bs(bArr);
        if (this.p.c == 0) {
            a(this.p, (byte[]) null);
            a((byte) 5, (Object) null);
        } else if (this.p.c <= 512000 && this.p.c >= 0) {
            a((byte) 6, (Object) null);
        } else {
            this.v.b(obj);
            a((byte) 5, (Object) null);
        }
    }

    public final void a(ce ceVar) {
        if (ceVar != null) {
            this.B = ceVar.a("NMSPSocket").a();
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x035e A[PHI: r1
      0x035e: PHI (r1v46 java.lang.String) = (r1v38 java.lang.String), (r1v56 java.lang.String) binds: [B:34:0x00f8, B:36:0x013c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        boolean z;
        String str;
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 1:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_CONNECT");
                }
                if (this.a != 0) {
                    this.A.a(this.q, this.r);
                } else {
                    a((byte) 3, (Object) null);
                }
                break;
            case 2:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_DISCONNECT");
                }
                byte[] bArr = new byte[2];
                bu.a((short) 0, bArr, 0);
                byte[] bArrA = br.a((byte) 3, (byte) 23, (short) 512, bArr);
                this.v.b(this.o);
                a(bArrA, 3, "SEND_COP_DISCONNECT");
                break;
            case 3:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_OPEN_SOCKET");
                }
                if (this.a != 0) {
                    this.A.a(this.q, this.r);
                } else {
                    this.v = new h(this.n);
                    if (!this.l) {
                        this.v.a(this.i, this.j, this, this);
                    } else {
                        this.v.a(this.i, this.j, this.m, this, this);
                    }
                }
                break;
            case 4:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_CLOSE_SOCKET");
                }
                if (this.v != null && this.o != null) {
                    this.v.a(this.o);
                    break;
                }
                break;
            case 5:
                if (this.v != null && this.o != null) {
                    this.v.a(this.o, bp.b.a, new byte[8], 8, this, "READ_XMODE_HEADER");
                    break;
                }
                break;
            case 6:
                if (this.p.c > 0 && this.p.c <= 512000) {
                    byte[] bArr2 = new byte[this.p.c];
                    if (this.v != null && this.o != null) {
                        this.v.a(this.o, bp.b.a, bArr2, bArr2.length, this, "READ_XMODE_PAYLOAD");
                        break;
                    }
                }
                break;
            case 7:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_COP_CONNECT");
                }
                if (this.a == 0) {
                    String str2 = (String) aVar.b;
                    String str3 = "<?xml version=\"1.0\"?><cc><s></s><t>7</t><b>20091023</b><tsc>" + ((int) this.y) + "</tsc><fsc>" + ((int) this.z) + "</fsc><nmaid>" + this.s + "</nmaid><uid>" + this.t + "</uid>" + (str2 != null ? "<rootlog>" + str2 + "</rootlog>" : "");
                    Enumeration enumerationElements = this.k.elements();
                    String str4 = str3;
                    boolean z2 = false;
                    while (enumerationElements.hasMoreElements()) {
                        cz czVar = (cz) enumerationElements.nextElement();
                        if (czVar.d() == cz.a.b) {
                            str4 = str4 + "<nmsp p=\"" + czVar.a() + "\" v=\"" + bx.a(new String(czVar.b())) + "\"/>";
                            if (czVar.a().equals("Ping_IntervalSecs")) {
                                z = true;
                                str = str4;
                            } else {
                                String str5 = str4;
                                z = z2;
                                str = str5;
                            }
                        } else {
                            String str6 = str4;
                            z = z2;
                            str = str6;
                        }
                        String str7 = czVar.d() == cz.a.c ? str + "<app p=\"" + czVar.a() + "\" v=\"" + bx.a(new String(czVar.b())) + "\"/>" : str;
                        z2 = z;
                        str4 = str7;
                    }
                    if (!z2) {
                        str4 = str4 + "<nmsp p=\"Ping_IntervalSecs\" v=\"0\"/>";
                    }
                    byte[] bytes = (str4 + "</cc>").getBytes();
                    int length = bytes.length;
                    int i = length + 4 + 1;
                    byte[] bArr3 = new byte[i];
                    bu.a(i - 4, bArr3, 0);
                    bArr3[4] = 0;
                    System.arraycopy(bytes, 0, bArr3, 5, length);
                    a(br.a((byte) 3, (byte) 23, (short) 256, bArr3), 3, "SEND_COP_CONNECT");
                    this.f = new bq() { // from class: cb.5
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                if (cb.c.e()) {
                                    cb.c.e("XMode.handleSendCopConnect() COP CONNECT timed out. ");
                                }
                                if (cb.this.C != null) {
                                    cb.this.C.b();
                                    cb.d(cb.this);
                                }
                                cb.this.a((byte) 9, (Object) null);
                            } catch (Exception e) {
                                if (cb.c.e()) {
                                    cb.c.e("XMode.handleSendCopConnect() " + e.getClass().getName() + " " + e.getMessage());
                                }
                            }
                        }
                    };
                    this.n.a(this.f, this.e * 1000);
                    this.a = (byte) 1;
                    this.A.a();
                }
                break;
            case 8:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_COP_PING_RESPONSE");
                }
                byte[] bArr4 = new byte[8];
                bu.a(this.x, bArr4, 0);
                bu.a(0, bArr4, 4);
                a(br.a((byte) 3, (byte) 23, (short) 259, bArr4), 3, "SEND_COP_PING_RESPONSE");
                break;
            case 9:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_COP_CONNECT_TIMED_OUT");
                }
                if (this.a == 1) {
                    this.a = (byte) 2;
                    this.q = (short) 5;
                    if (c.e()) {
                        c.e("XMode.handleCopConnectTimeout() COP CONNECT timed out. ");
                    }
                    a((byte) 4, (Object) null);
                }
                break;
            case 10:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_COP_CONFIRM");
                }
                byte[] bArrB = bo.b(this.u, this.b);
                byte[] bArr5 = new byte[bArrB.length + 4];
                bu.a(bArrB.length, bArr5, 0);
                System.arraycopy(bArrB, 0, bArr5, 4, bArrB.length);
                a(br.a((byte) 3, (byte) 23, (short) 262, bArr5), 3, "SEND_COP_CONFIRM");
                break;
            case 11:
                this.A.a((String) aVar.b);
                break;
            case 12:
                this.A.b((String) aVar.b);
                break;
            case 13:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_GENERATE_COP_REF_ID");
                }
                this.C = cl.a((cm) ((cm) this.B).f(), "NMSP_GATEWAY", new cl.a() { // from class: cb.4
                    @Override // cl.a
                    public final void a(String str8) {
                        cb.this.a((byte) 7, str8);
                    }
                });
                break;
            case 14:
                if (c.b()) {
                    c.b("XMode.handleMessage() CMD_SEND_LOW_PRIORITY_MSG");
                }
                a((byte[]) null, 2, (Object) null);
                break;
        }
    }

    public final void a(short s, short s2) {
        if (c.b()) {
            c.b("XMode.connect() codec: " + ((int) s2));
        }
        if (this.a == 1 || this.a == 0) {
            return;
        }
        if (this.a != 2) {
            if (this.a == 3) {
                a("OpenSocket");
                this.y = s;
                this.z = s2;
                this.a = (byte) 0;
                a((byte) 1, (Object) null);
                return;
            }
            return;
        }
        if (this.d != null) {
            this.n.a(this.d);
        }
        if (this.h != null) {
            this.n.a(this.h);
        }
        a("OpenSocket");
        this.o = null;
        this.v = null;
        this.b = null;
        this.w = "";
        this.y = s;
        this.z = s2;
        this.a = (byte) 0;
        a((byte) 1, (Object) null);
    }

    public final void a(byte[] bArr, int i) {
        if (c.b()) {
            c.b("XMode.sendVapRecordMsg() audio id: " + i);
        }
        if (this.a != 1) {
            return;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length + 8];
        bu.a(i, bArr2, 0);
        bu.a(length, bArr2, 4);
        System.arraycopy(bArr, 0, bArr2, 8, length);
        a(br.a((byte) 1, (byte) 18, (short) 513, bArr2), 3, "SEND_VAP_RECORD");
    }

    public final void a(byte[] bArr, int i, Object obj) {
        b bVar;
        byte[] bArr2;
        if (c.b()) {
            c.b("XMode.sendXModeMsg() " + obj + ", buffer.length:" + bArr.length);
        }
        if (this.h != null && this.n.a(this.h)) {
            this.h = new bq() { // from class: cb.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        cb.a(cb.this);
                        if (cb.c.e()) {
                            cb.c.e("Session Idle for too long, longer than [" + cb.this.g + "] ()");
                        }
                        cb.this.a = (byte) 2;
                        cb.this.a((byte) 4, (Object) null);
                    } catch (Exception e) {
                        if (cb.c.e()) {
                            cb.c.e("XMode.sendXModeMsg() " + e.getClass().getName() + " " + e.getMessage());
                        }
                    }
                }
            };
            this.n.a(this.h, this.g * 1000);
        }
        if (this.v == null || this.o == null) {
            if (i == 1) {
                this.E.add(new b(bArr, obj));
            }
        } else {
            if (i != 3 && i != 2 && (this.D.size() != 0 || this.E.size() != 0)) {
                this.E.add(new b(bArr, obj));
                return;
            }
            if (i == 2) {
                b bVar2 = (b) this.E.remove();
                bArr2 = bVar2.a;
                bVar = bVar2;
            } else {
                bVar = new b(null, obj);
                bArr2 = bArr;
            }
            this.D.add(bVar);
            this.v.a(this.o, bArr2, bArr2.length, this, bVar);
        }
    }

    public final void b() {
        if (c.b()) {
            c.b("XMode.disconnect() state:" + ((int) this.a) + ", socket:" + this.o);
        }
        if (this.a == 3) {
            this.q = (short) 1;
            this.A.a(this.q, this.r);
            return;
        }
        if (this.a != 2) {
            if (this.a != 0) {
                if (this.a == 1) {
                    this.a = (byte) 2;
                    this.q = (short) 1;
                    a((byte) 2, (Object) null);
                    return;
                }
                return;
            }
            a("CloseSocket");
            this.q = (short) 1;
            this.a = (byte) 2;
            if (this.o != null) {
                this.v.a(this.o);
            }
        }
    }

    public final void b(int i) {
        if (c.b()) {
            c.b("XMode.sendVapRecordEnd() audio id: " + i);
        }
        if (this.a != 1) {
            return;
        }
        byte[] bArr = new byte[4];
        bu.a(i, bArr, 0);
        a(br.a((byte) 1, (byte) 18, (short) 256, bArr), 3, "SEND_VAP_RECORD_END");
    }
}
