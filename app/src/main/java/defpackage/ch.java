package defpackage;

import android.support.v4.view.MotionEventCompat;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.nuance.nmdp.speechkit.recognitionresult.b;
import com.nuance.nmsp.client.sdk.oem.d;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ch implements bn.b, cb.a, cd {
    private static final bm a = d.a(ch.class);
    private static byte[] l = new byte[16];
    private final String b;
    private final long c;
    private ce h;
    private bn i;
    private cb j;
    private final List d = new LinkedList();
    private int e = 0;
    private long f = 0;
    private boolean g = false;
    private int k = 1;

    /* JADX INFO: renamed from: ch$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public int a;
        public byte[] b;

        private AnonymousClass1() {
        }

        /* synthetic */ AnonymousClass1(byte b) {
            this();
        }
    }

    public ch(String str, long j, int i, String str2, String str3, String str4, bn bnVar, cb cbVar) {
        this.j = null;
        this.b = str;
        this.c = j;
        this.j = cbVar;
        this.i = bnVar;
        try {
            this.h = cl.a((str2 == null || str2.length() == 0) ? bo.b() : str2, "CLIENT", "2.0", i, this, "CLIENT").a("UniqueID", str3).a("ApplicationID", str4).a("client_hardware_model", b.h()).a("client_hardware_submodel", b.i()).a("client_os_type", b.j()).a("client_os_version", b.k()).a("client_sdk_release", "NMSP 5.0 client SDK - build 009").a();
        } catch (cf e) {
        }
        bn bnVar2 = this.i;
        bn.a aVar = new bn.a((byte) 17, null);
        Thread threadCurrentThread = Thread.currentThread();
        this.i.a();
        bnVar2.a(aVar, this, threadCurrentThread);
    }

    private void b(int i) {
        while (this.d.size() > 0) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) this.d.get(0);
            if (i < anonymousClass1.a) {
                return;
            }
            this.f -= (long) anonymousClass1.b.length;
            this.d.remove(0);
            this.e--;
            d();
        }
    }

    private void d() {
        if (this.g) {
            if (a.b()) {
                a.b("trySendingLogs() _sessionConnected:" + this.g + ", _outstandingEvents:" + this.e + ", _events.size()" + this.d.size());
            }
            while (this.e < 5 && this.e < this.d.size()) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) this.d.get(this.e);
                if (a.b()) {
                    a.b("bcpLog, packet len [" + anonymousClass1.b.length + "]");
                }
                bn bnVar = this.i;
                bn.a aVar = new bn.a((byte) 18, new Object[]{anonymousClass1});
                Thread threadCurrentThread = Thread.currentThread();
                this.i.a();
                bnVar.a(aVar, this, threadCurrentThread);
                this.e++;
            }
        }
    }

    private void e() {
        boolean z;
        int i;
        d dVar = new d();
        if (this.b == null || !dVar.a(this.b, bl.a.a)) {
            if (a.c()) {
                a.c("handleLoadFile() cannot open + file:" + this.b);
                return;
            }
            return;
        }
        if (dVar.b() > 2147483647L) {
            if (a.e()) {
                a.e("calllog file too big (" + dVar.b() + " > 2147483647)!!!");
            }
            dVar.a();
            return;
        }
        long jB = dVar.b();
        long length = 0;
        int size = this.d.size();
        while (true) {
            if (length >= jB) {
                z = false;
                break;
            }
            byte[] bArr = new byte[1];
            if (dVar.a(bArr, bArr.length) != bArr.length) {
                z = true;
                break;
            }
            long length2 = length + ((long) bArr.length);
            byte[] bArr2 = new byte[bArr[0]];
            if (dVar.a(bArr2, bArr2.length) != bArr2.length) {
                z = true;
                break;
            }
            long length3 = length2 + ((long) bArr2.length);
            if (bArr2.length == 1) {
                i = bArr2[0] & MotionEventCompat.ACTION_MASK;
            } else if (bArr2.length == 2) {
                i = (bArr2[0] & MotionEventCompat.ACTION_MASK) + ((bArr2[1] & MotionEventCompat.ACTION_MASK) << 8);
            } else if (bArr2.length == 3) {
                i = (bArr2[0] & MotionEventCompat.ACTION_MASK) + ((bArr2[2] & MotionEventCompat.ACTION_MASK) << 16) + ((bArr2[1] & MotionEventCompat.ACTION_MASK) << 8);
            } else {
                i = (bArr2[0] & MotionEventCompat.ACTION_MASK) + ((bArr2[3] & MotionEventCompat.ACTION_MASK) << 24) + ((bArr2[2] & MotionEventCompat.ACTION_MASK) << 16) + ((bArr2[1] & MotionEventCompat.ACTION_MASK) << 8);
            }
            byte[] bArr3 = new byte[i];
            if (dVar.a(bArr3, bArr3.length) != bArr3.length) {
                z = true;
                break;
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1((byte) 0);
            anonymousClass1.b = bArr3;
            this.f += (long) bArr3.length;
            this.d.add(anonymousClass1);
            length = length3 + ((long) bArr3.length);
        }
        dVar.a();
        if (z) {
            dVar.c();
            if (a.e()) {
                a.e("handleLoadFile() failed!!!");
            }
        } else if (a.c()) {
            a.c("handleLoadFile() " + (this.d.size() - size) + " events are loaded");
        }
        f();
    }

    private void f() {
        if (this.f <= this.c) {
            return;
        }
        int size = this.d.size();
        while (this.f > this.c) {
            this.f -= (long) ((byte[]) this.d.remove(0)).length;
        }
        if (a.c()) {
            a.c("trimEventsList() " + (size - this.d.size()) + " events are removed due to size limit (" + this.c + ")");
        }
    }

    @Override // cb.a
    public final void a() {
        if (a.b()) {
            a.b("socketOpened()");
        }
    }

    public final void a(int i) {
        if (a.b()) {
            a.b("bcpLogResponse requestId = " + i);
        }
        bn bnVar = this.i;
        bn.a aVar = new bn.a((byte) 19, new Object[]{new Integer(i)});
        Thread threadCurrentThread = Thread.currentThread();
        this.i.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    @Override // cb.a
    public final void a(bs bsVar, byte[] bArr) {
    }

    public final void a(cm cmVar, cl.a aVar) {
        bn bnVar = this.i;
        bn.a aVar2 = new bn.a((byte) 20, new Object[]{cmVar, aVar});
        Thread threadCurrentThread = Thread.currentThread();
        this.i.a();
        bnVar.a(aVar2, this, threadCurrentThread);
    }

    final void a(cm cmVar, cl.b bVar) {
        bn bnVar = this.i;
        bn.a aVar = new bn.a((byte) 16, new Object[]{cmVar, bVar});
        Thread threadCurrentThread = Thread.currentThread();
        this.i.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        byte b = 0;
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 16:
                cm cmVar = (cm) ((Object[]) aVar.b)[0];
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(b);
                anonymousClass1.b = cmVar.g();
                if (this.f < this.c) {
                    new StringBuilder().append(cmVar.b.b()).append(cmVar.d);
                    this.d.add(anonymousClass1);
                    this.f = ((long) anonymousClass1.b.length) + this.f;
                    d();
                }
                break;
            case 17:
                e();
                break;
            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                AnonymousClass1 anonymousClass2 = (AnonymousClass1) ((Object[]) aVar.b)[0];
                int i = this.k;
                this.k = i + 1;
                anonymousClass2.a = i;
                byte[] bArr = new byte[anonymousClass2.b.length + 25];
                bArr[0] = 0;
                bu.a(anonymousClass2.a, bArr, 1);
                byte[] bArrA = br.a(bArr, l);
                bu.a(anonymousClass2.b.length, bArrA, 21);
                System.arraycopy(anonymousClass2.b, 0, bArrA, 25, anonymousClass2.b.length);
                this.j.a(br.a((byte) 2, (byte) 34, (short) 2640, bArrA), 1, "SEND_BCP_LOG");
                break;
            case 19:
                b(((Integer) ((Object[]) aVar.b)[0]).intValue());
                break;
            case 20:
                Object[] objArr = (Object[]) aVar.b;
                cm cmVar2 = (cm) objArr[0];
                cl.a aVar2 = (cl.a) objArr[1];
                cmVar2.c();
                if (aVar2 != null) {
                    if (!(cmVar2 instanceof cj)) {
                        aVar2.a(null);
                    } else {
                        aVar2.a(((cj) cmVar2).b());
                    }
                }
                break;
        }
    }

    @Override // cb.a
    public final void a(String str) {
        if (str.startsWith("SEND_BCP_LOG") && a.b()) {
            a.b("onMsgSent(" + str + ")");
        }
    }

    @Override // cb.a
    public final void a(short s, short s2) {
        if (a.b()) {
            a.b("socketClosed() reason [" + ((int) s) + "] subReason [" + ((int) s2) + "]");
        }
        this.g = false;
    }

    public final ce b() {
        return this.h;
    }

    @Override // cb.a
    public final void b(String str) {
        if (str.startsWith("SEND_BCP_LOG")) {
            if (a.b()) {
                a.b("onMsgNotSent(" + str + ")");
            }
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) this.d.remove(0);
            this.f -= (long) anonymousClass1.b.length;
            this.e--;
            this.d.add(this.e, anonymousClass1);
            d();
        }
    }

    @Override // defpackage.cd
    public final cg c(String str) throws cc {
        if (a.b()) {
            a.b("openSession(" + str + ")");
        }
        if (((ck) this.h).a != null) {
            throw new cc("Application session already opened.");
        }
        cj cjVar = (cj) cl.a((cm) this.h, "APP_REMOTEEVENT", null).a(new cl.b());
        cl clVarA = cl.a(cjVar, str);
        ((ck) this.h).a = cjVar;
        return clVarA;
    }

    public final void c() {
        this.g = true;
        d();
    }
}
