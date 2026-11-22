package defpackage;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class dm implements bn.b, cb.a {
    private static final bm a = d.a(dm.class);
    private static byte[] m = new byte[16];
    private static int p = 1;
    private static String[] v = {AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
    private cb e;
    private String f;
    private short g;
    private bn i;
    private dh j;
    private bi n;
    private bi o;
    private ce u;
    private byte[] l = null;
    private boolean q = false;
    private long s = 1;
    private byte t = 1;
    private di r = null;
    private Vector k = new Vector();
    private Hashtable b = new Hashtable();
    private Hashtable c = new Hashtable();
    private Hashtable d = new Hashtable();
    private Vector h = new Vector();

    public dm(String str, short s, String str2, byte[] bArr, String str3, Vector vector, bn bnVar, dh dhVar) {
        this.f = str;
        this.g = s;
        this.i = bnVar;
        this.j = dhVar;
        this.e = new cb(this.f, this.g, str2, bArr, str3, this, vector, bnVar);
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            byte b = bArr[i];
            stringBuffer.append(v[(byte) (((byte) (((byte) (b & 240)) >>> 4)) & 15)] + v[(byte) (b & 15)]);
            if (i == 3 || i == 5 || i == 7 || i == 9) {
                stringBuffer.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
            }
        }
        return stringBuffer.toString().toLowerCase();
    }

    private void a(byte b, Object obj) {
        bn bnVar = this.i;
        bn.a aVar = new bn.a(b, obj);
        Thread threadCurrentThread = Thread.currentThread();
        this.i.a();
        bnVar.a(aVar, this, threadCurrentThread);
    }

    private void b(byte[] bArr) {
        byte b = bArr[16];
        short sA = bu.a(bArr, 17);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.h.size()) {
                return;
            }
            ((dn) this.h.elementAt(i2)).a(b, sA);
            i = i2 + 1;
        }
    }

    private void c(String str) {
        if (this.u != null) {
            cg cgVarA = this.u.a(str);
            if (str.compareTo("ConnectionEstablished") == 0) {
                try {
                    cgVarA.a("SessionID", a(this.l));
                } catch (cf e) {
                }
            }
            cgVarA.a();
        }
    }

    private void c(byte[] bArr) {
        int iB = bu.b(bArr, 0);
        if (this.c.size() == 0) {
            return;
        }
        cx cxVar = (cx) this.c.get(new Integer(iB));
        if (cxVar == null) {
            a.e("Could not find the audio sink associated with AID: " + iB);
            return;
        }
        int iB2 = bu.b(bArr, 4);
        int i = 8;
        if (bx.b(this.o)) {
            while ((bArr[i] & 128) > 0) {
                i++;
                iB2--;
            }
            i++;
            iB2--;
        }
        if (iB2 > 0 && iB2 <= bArr.length - i) {
            try {
                cxVar.a(bArr, i, iB2, false);
            } catch (dc e) {
                a.e(e.getMessage());
            }
        }
        if (this.d.size() != 0) {
            dn dnVar = (dn) this.d.get(new Integer(iB));
            if (dnVar == null) {
                a.e("parseXModeMsgVapPlay:: Could not find the session listener associated with AID: " + iB);
            } else {
                dnVar.e();
            }
        }
    }

    public static void d() {
    }

    private void k() {
        while (!this.k.isEmpty()) {
            bn.a aVar = (bn.a) this.k.firstElement();
            this.k.removeElementAt(0);
            switch (aVar.a) {
                case 1:
                    a((byte) 1, aVar.b);
                    break;
                case 2:
                    a((byte) 2, aVar.b);
                    break;
                case 4:
                    a((byte) 4, aVar.b);
                    break;
                case 5:
                    a((byte) 5, aVar.b);
                    break;
                case 6:
                    a((byte) 6, aVar.b);
                    break;
                case 7:
                    a((byte) 7, aVar.b);
                    break;
                case 8:
                    a((byte) 8, aVar.b);
                    break;
            }
        }
    }

    @Override // cb.a
    public final void a() {
    }

    public final void a(byte b, int i, dn dnVar) {
        a.b("freeResource, TID: " + ((int) b) + ", disconnect timeout: " + i);
        this.h.removeElement(dnVar);
        Object[] objArr = {new Byte(b), new Integer(i)};
        if (this.l == null || !this.k.isEmpty()) {
            this.k.addElement(new bn.a((byte) 4, objArr));
        } else {
            a((byte) 4, objArr);
        }
    }

    public final void a(int i) {
        if (a.b()) {
            a.b("vapRecordBegin, AID: " + i);
        }
        Integer num = new Integer(i);
        if (this.l == null || !this.k.isEmpty()) {
            this.k.addElement(new bn.a((byte) 5, num));
        } else {
            a((byte) 5, num);
        }
    }

    public final void a(int i, cx cxVar, dn dnVar) {
        this.c.put(new Integer(i), cxVar);
        this.d.put(new Integer(i), dnVar);
    }

    public final void a(bi biVar, bi biVar2) {
        if (a.b()) {
            a.b("connect()");
        }
        c("Connect");
        this.n = biVar;
        this.o = biVar2;
        a((byte) 1, (Object) null);
    }

    @Override // cb.a
    public final void a(bs bsVar, byte[] bArr) {
        int i = 0;
        if (a.b()) {
            a.b("xmodeMsgCallback, protocol: " + ((int) bsVar.a) + ", command: " + ((int) bsVar.b));
        }
        switch (bsVar.a) {
            case 1:
                switch (bsVar.b) {
                    case 512:
                        c(bArr);
                        break;
                    case 528:
                        int iB = bu.b(bArr, 0);
                        if (this.d.size() != 0) {
                            dn dnVar = (dn) this.d.get(new Integer(iB));
                            if (dnVar != null) {
                                dnVar.d();
                            } else {
                                a.e("parseVapPlayBegin:: Could not find the session listener associated with AID: " + iB);
                            }
                        }
                        break;
                    case 1024:
                        int iB2 = bu.b(bArr, 0);
                        if (this.c.size() != 0) {
                            cx cxVar = (cx) this.c.remove(new Integer(iB2));
                            if (cxVar != null) {
                                try {
                                    cxVar.a(null, 0, 0, true);
                                } catch (dc e) {
                                    a.e(e.getMessage());
                                }
                                dn dnVar2 = (dn) this.d.remove(new Integer(iB2));
                                if (dnVar2 != null) {
                                    dnVar2.f();
                                } else {
                                    a.e("parseXModeMsgVapPlayEnd:: Could not find the session listener associated with AID: " + iB2);
                                }
                            } else {
                                a.e("Could not find the audio sink associated with AID: " + iB2);
                            }
                        }
                        break;
                }
                break;
            case 2:
                switch (bsVar.b) {
                    case 2576:
                        byte b = bArr[16];
                        int iB3 = bu.b(bArr, 17);
                        short sA = bu.a(bArr, 21);
                        short sA2 = bu.a(bArr, 23);
                        short sA3 = bu.a(bArr, 25);
                        dn dnVar3 = (dn) this.b.get(new Long(iB3));
                        if (dnVar3 != null) {
                            dnVar3.a(b, iB3, sA, sA2, sA3);
                            if (sA != 200) {
                                this.b.remove(new Long(iB3));
                            }
                        }
                        break;
                    case 2577:
                        long jB = bu.b(bArr, 17);
                        bu.b(bArr, 21);
                        bu.a(bArr, 25);
                        this.b.remove(new Long(jB));
                        break;
                    case 2578:
                        break;
                    case 2579:
                        long jB2 = bu.b(bArr, 17);
                        bu.a(bArr, 21);
                        int iB4 = bu.b(bArr, 23);
                        if (iB4 > 0 && iB4 <= bArr.length - 27) {
                            System.arraycopy(bArr, 27, new byte[iB4], 0, iB4);
                        }
                        this.b.remove(new Long(jB2));
                        break;
                    case 2580:
                        long jB3 = bu.b(bArr, 17);
                        bu.a(bArr, 21);
                        int iB5 = bu.b(bArr, 23);
                        if (iB5 > 0 && iB5 <= bArr.length - 27) {
                            System.arraycopy(bArr, 27, new byte[iB5], 0, iB5);
                        }
                        this.b.get(new Long(jB3));
                        break;
                    case 2582:
                        byte[] bArr2 = null;
                        byte b2 = bArr[16];
                        int iB6 = bu.b(bArr, 17);
                        short sA4 = bu.a(bArr, 21);
                        int iB7 = bu.b(bArr, 23);
                        if (iB7 > 0 && iB7 <= bArr.length - 27) {
                            bArr2 = new byte[iB7];
                            System.arraycopy(bArr, 27, bArr2, 0, iB7);
                        }
                        dn dnVar4 = (dn) this.b.remove(new Long(iB6));
                        if (dnVar4 != null) {
                            dnVar4.a(b2, iB6, sA4, bArr2);
                        }
                        break;
                    case 2584:
                        byte[] bArr3 = null;
                        byte b3 = bArr[16];
                        int iB8 = bu.b(bArr, 17);
                        short sA5 = bu.a(bArr, 21);
                        int iB9 = bu.b(bArr, 23);
                        if (iB9 > 0 && iB9 <= bArr.length - 27) {
                            bArr3 = new byte[iB9];
                            System.arraycopy(bArr, 27, bArr3, 0, iB9);
                        }
                        dn dnVar5 = (dn) this.b.remove(new Long(iB8));
                        if (dnVar5 != null) {
                            dnVar5.b(b3, iB8, sA5, bArr3);
                        }
                        break;
                    case 2585:
                        byte b4 = bArr[16];
                        int iB10 = bu.b(bArr, 21);
                        if (iB10 > 0 && iB10 <= bArr.length - 25) {
                            byte[] bArr4 = new byte[iB10];
                            System.arraycopy(bArr, 25, bArr4, 0, iB10);
                            dn dnVar6 = (dn) this.b.get(new Long(0L));
                            if (dnVar6 != null) {
                                dnVar6.a(b4, bArr4);
                            }
                            break;
                        }
                        break;
                    case 2600:
                        b(bArr);
                        break;
                    default:
                        a.e("Unknown BCP command");
                        break;
                }
                break;
            case 3:
                switch (bsVar.b) {
                    case FileUploadPreferences.BATTERY_USAGE_CHARGING_ONLY /* 257 */:
                        this.l = this.e.b;
                        if (a.b()) {
                            a.b("connected(" + a(this.l) + ") called on " + this.j);
                        }
                        c("ConnectionEstablished");
                        dh dhVar = this.j;
                        String strA = a(this.l);
                        di diVar = this.r;
                        dhVar.a(strA);
                        while (true) {
                            int i2 = i;
                            if (i2 >= this.h.size()) {
                                k();
                            } else {
                                ((dn) this.h.elementAt(i2)).a(this.l);
                                i = i2 + 1;
                            }
                            break;
                        }
                        break;
                }
                break;
            default:
                a.e("Unknown Xmode protocol");
                break;
        }
    }

    public final void a(ce ceVar) {
        this.u = ceVar.a("NMSPSession").a();
        this.e.a(this.u);
    }

    public final void a(di diVar) {
        this.r = diVar;
    }

    public final void a(dn dnVar) {
        if (this.h.contains(dnVar)) {
            return;
        }
        this.h.addElement(dnVar);
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        bn.a aVar = (bn.a) obj;
        switch (aVar.a) {
            case 1:
                this.e.a(this.n.a(), this.o.a());
                break;
            case 2:
                this.e.b();
                break;
            case 3:
                this.q = true;
                this.e.b();
                break;
            case 4:
                if (this.l != null) {
                    Object[] objArr = (Object[]) aVar.b;
                    byte bByteValue = ((Byte) objArr[0]).byteValue();
                    int iIntValue = ((Integer) objArr[1]).intValue();
                    byte[] bArr = new byte[5];
                    bArr[0] = bByteValue;
                    bu.a(iIntValue, bArr, 1);
                    this.e.a(br.a((byte) 2, (byte) 34, (short) 2601, br.a(bArr, this.l)), 3, "SEND_BCP_FREE_RESOURCE");
                }
                break;
            case 5:
                if (this.l != null) {
                    this.e.a(((Integer) aVar.b).intValue());
                }
                break;
            case 6:
                if (this.l != null) {
                    Object[] objArr2 = (Object[]) aVar.b;
                    byte[] bArr2 = (byte[]) objArr2[0];
                    int iIntValue2 = ((Integer) objArr2[1]).intValue();
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= this.h.size()) {
                            this.e.a(bArr2, iIntValue2);
                        } else {
                            ((dn) this.h.elementAt(i2)).c();
                            i = i2 + 1;
                        }
                    }
                }
                break;
            case 7:
                if (this.l != null) {
                    this.e.b(((Integer) aVar.b).intValue());
                }
                break;
            case 8:
                if (this.l != null) {
                    Object[] objArr3 = (Object[]) aVar.b;
                    short sShortValue = ((Short) objArr3[0]).shortValue();
                    String str = (String) objArr3[1];
                    byte[] bArr3 = (byte[]) objArr3[2];
                    byte[] bArr4 = (byte[]) objArr3[3];
                    byte bByteValue2 = ((Byte) objArr3[4]).byteValue();
                    long jLongValue = ((Long) objArr3[5]).longValue();
                    dn dnVar = (dn) objArr3[6];
                    boolean zBooleanValue = ((Boolean) objArr3[7]).booleanValue();
                    this.b.put(new Long(jLongValue), dnVar);
                    int length = bArr3.length + 5;
                    if (sShortValue == 2585) {
                        length += 4;
                    }
                    byte[] bArr5 = new byte[length];
                    bArr5[0] = bByteValue2;
                    int i3 = 1;
                    if (sShortValue == 2585) {
                        bu.a((int) jLongValue, bArr5, 1);
                        i3 = 5;
                    }
                    bu.a(bArr3.length, bArr5, i3);
                    System.arraycopy(bArr3, 0, bArr5, i3 + 4, bArr3.length);
                    byte[] bArrA = br.a(bArr5, this.l != null ? this.l : m);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.write(bArrA, 0, bArrA.length);
                    if (bArr4 != null) {
                        byteArrayOutputStream.write(bArr4, 0, bArr4.length);
                    }
                    this.e.a(br.a((byte) 2, (byte) 34, sShortValue, byteArrayOutputStream.toByteArray()), 3, str);
                    if (zBooleanValue) {
                        if (sShortValue == 2581) {
                            if (dnVar != null) {
                                dnVar.a(bByteValue2, jLongValue, (short) 200, null);
                            }
                            break;
                        } else if (sShortValue == 2608 && dnVar != null) {
                            this.h.removeElement(dnVar);
                            dnVar.b();
                            break;
                        }
                    }
                }
                break;
            default:
                a.e("Unknown command");
                break;
        }
    }

    @Override // cb.a
    public final void a(String str) {
    }

    public final void a(short s, String str, byte[] bArr, byte[] bArr2, byte b, long j, dn dnVar, boolean z) {
        if (a.b()) {
            a.b("postBcpMessage, BCP: " + ((int) s) + ", TID: " + ((int) b) + ", RID: " + j);
        }
        Object[] objArr = {new Short(s), str, bArr, bArr2, new Byte(b), new Long(j), dnVar, new Boolean(z)};
        if (this.l == null || !this.k.isEmpty()) {
            this.k.addElement(new bn.a((byte) 8, objArr));
        } else {
            a((byte) 8, objArr);
        }
    }

    @Override // cb.a
    public final void a(short s, short s2) {
        if (a.b()) {
            a.b("socketClosed, reason: " + ((int) s));
        }
        if (a.b()) {
            a.b("socketClosed() sessionListeners.size():" + this.h.size());
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.h.size()) {
                break;
            }
            ((dn) this.h.elementAt(i2)).a(s);
            i = i2 + 1;
        }
        if (!this.k.isEmpty()) {
            this.k.removeAllElements();
        }
        this.h.removeAllElements();
        if (s == 4 || s == 5) {
            dh dhVar = this.j;
            di diVar = this.r;
            dhVar.a((short) 9);
        } else if (s == 7) {
            dh dhVar2 = this.j;
            di diVar2 = this.r;
            dhVar2.a(s2);
        } else if (s == 8 && this.l == null) {
            dh dhVar3 = this.j;
            di diVar3 = this.r;
            dhVar3.a((short) 9);
        } else if (this.l != null) {
            dh dhVar4 = this.j;
            di diVar4 = this.r;
            dhVar4.b(s2);
        }
        if (s == 1 && this.q) {
            this.i.c();
            this.j.a();
        }
        this.l = null;
    }

    public final void a(byte[] bArr, int i) {
        if (a.b()) {
            a.b("vapRecord, AID: " + i);
        }
        Object[] objArr = {bArr, new Integer(i)};
        if (this.l == null || !this.k.isEmpty()) {
            this.k.addElement(new bn.a((byte) 6, objArr));
        } else {
            a((byte) 6, objArr);
        }
    }

    public final cb b() {
        return this.e;
    }

    public final void b(int i) {
        if (a.b()) {
            a.b("vapRecordEnd, AID: " + i);
        }
        Integer num = new Integer(i);
        if (this.l == null || !this.k.isEmpty()) {
            this.k.addElement(new bn.a((byte) 7, num));
        } else {
            a((byte) 7, num);
        }
    }

    public final void b(dn dnVar) {
        this.h.removeElement(dnVar);
    }

    @Override // cb.a
    public final void b(String str) {
    }

    public final synchronized int c() {
        int i;
        i = p;
        p = i + 1;
        if (p == Integer.MIN_VALUE) {
            p = 1;
        }
        return i;
    }

    public final void e() {
        if (a.b()) {
            a.b("disconnectAndShutdown");
        }
        a((byte) 3, (Object) null);
    }

    public final byte[] f() {
        return this.l;
    }

    public final long g() {
        long j = this.s;
        this.s = j + 1;
        if (this.s == Long.MIN_VALUE) {
            this.s = 1L;
        }
        return j;
    }

    public final byte h() {
        byte b = this.t;
        this.t = (byte) (b + 1);
        if (this.t == -128) {
            this.t = (byte) 1;
        }
        return b;
    }

    public final bi i() {
        return this.n;
    }

    public final ce j() {
        return this.u;
    }
}
