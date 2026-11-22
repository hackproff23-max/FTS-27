package defpackage;

import com.flurry.android.Constants;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.millennialmedia.android.MMException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class cq extends cp implements cv {
    private static final bm a = d.a(cq.class);
    private Hashtable b;

    public cq() {
        super((short) 224);
        this.b = new Hashtable();
    }

    public cq(byte[] bArr) {
        super((short) 224);
        this.b = new Hashtable();
        if (bArr.length > 0) {
            if ((bArr[0] & Constants.UNKNOWN) != 224) {
                a.e("PDXDictionary() Expected a dictionary. ");
                return;
            }
            int iA = a(bArr, 1);
            byte[] bArr2 = new byte[iA];
            System.arraycopy(bArr, bArr.length - iA, bArr2, 0, bArr2.length);
            b(bArr2);
        }
    }

    public cq(byte[] bArr, byte b) {
        super((short) 224);
        this.b = new Hashtable();
        b(bArr);
    }

    private void b(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            int i3 = bArr[i] & Constants.UNKNOWN;
            if (i3 == 22) {
                int iA = a(bArr, i2);
                int iA2 = i2 + a(iA);
                byte[] bArr2 = new byte[iA];
                System.arraycopy(bArr, iA2, bArr2, 0, bArr2.length);
                int i4 = iA + iA2;
                String str = new String(bArr2);
                int i5 = i4 + 1;
                int i6 = bArr[i4] & Constants.UNKNOWN;
                int iA3 = a(bArr, i5);
                int iA4 = i5 + a(iA3);
                byte[] bArr3 = new byte[iA3];
                System.arraycopy(bArr, iA4, bArr3, 0, bArr3.length);
                i = iA3 + iA4;
                switch (i6) {
                    case 4:
                        this.b.put(str, new co(bArr3));
                        break;
                    case 5:
                        this.b.put(str, new cs());
                        break;
                    case 16:
                        this.b.put(str, new ct(bArr3));
                        break;
                    case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                        this.b.put(str, new cn(bArr3));
                        break;
                    case DownloaderService.STATUS_RUNNING /* 192 */:
                        this.b.put(str, new cr(bArr3));
                        break;
                    case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                        this.b.put(str, new cu(bArr3));
                        break;
                    case 224:
                        this.b.put(str, new cq(bArr3, (byte) 0));
                        break;
                    default:
                        a.e("PDXDictionary.setContent() Unknown PDXClass type: " + i6 + ". ");
                        break;
                }
            } else {
                a.e("PDXDictionary.setContent() Expected an ASCII string but got " + i3 + ". ");
                return;
            }
        }
    }

    @Override // defpackage.cv
    public final Enumeration a() {
        return this.b.keys();
    }

    protected final void a(String str, int i) {
        this.b.put(str, new cr(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.cv
    public final void a(String str, cv cvVar) {
        if (str == null || cvVar == 0) {
            throw new IllegalArgumentException("key is : " + str + " value is : " + cvVar);
        }
        if (((cp) cvVar).c() != 224) {
            a.e("PDXDictionary.addDictionary() value is not a valid dictionary.");
            throw new IllegalArgumentException("value is not a valid dictionary. ");
        }
        this.b.put(str, cvVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.cv
    public final void a(String str, cw cwVar) {
        if (str == null || cwVar == 0) {
            throw new IllegalArgumentException("key is : " + str + " value is : " + cwVar);
        }
        if (((cp) cwVar).c() != 16) {
            a.e("PDXDictionary.addSequence() value is not a valid sequence.");
            throw new IllegalArgumentException("value is not a valid sequence. ");
        }
        this.b.put(str, cwVar);
    }

    @Override // defpackage.cv
    public final void a(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("key is : " + str + " value is : " + str2);
        }
        this.b.put(str, new cn(str2));
    }

    public final void a(String str, String str2, short s) {
        switch (s) {
            case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                this.b.put(str, new cn(str2));
                break;
            case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                this.b.put(str, new cu(str2));
                break;
        }
    }

    @Override // defpackage.cv
    public final void a(String str, byte[] bArr) {
        if (str == null || bArr == null) {
            throw new IllegalArgumentException("key is : " + str + " value is : " + bArr);
        }
        this.b.put(str, new co(bArr));
    }

    protected final void a(String str, byte[] bArr, short s) {
        switch (s) {
            case 4:
                this.b.put(str, new co(bArr));
                break;
            case 5:
                this.b.put(str, new cs());
                break;
            case 16:
                this.b.put(str, new ct(bArr));
                break;
            case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                this.b.put(str, new cn(bArr));
                break;
            case DownloaderService.STATUS_RUNNING /* 192 */:
                this.b.put(str, new cr(bArr));
                break;
            case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                this.b.put(str, new cu(bArr));
                break;
            case 224:
                this.b.put(str, new cq(bArr, (byte) 0));
                break;
            default:
                a.e("PDXDictionary.put() Unknown PDXClass type: " + ((int) s) + ". ");
                break;
        }
    }

    @Override // defpackage.cv
    public final boolean a(String str) {
        if (str == null) {
            throw new NullPointerException("PDXDictionary.containsKey key is null");
        }
        return this.b.containsKey(str);
    }

    public final cp b(String str) {
        return (cp) this.b.get(str);
    }

    protected final String b(int i) {
        String str;
        String str2;
        String str3 = "";
        int i2 = 0;
        while (true) {
            str = str3;
            if (i2 >= i - 1) {
                break;
            }
            str3 = str + "    ";
            i2++;
        }
        String str4 = i > 0 ? str + "    " : "";
        Enumeration enumerationKeys = this.b.keys();
        String str5 = i != 0 ? "{ \n" : "";
        while (enumerationKeys.hasMoreElements()) {
            String str6 = (String) enumerationKeys.nextElement();
            cp cpVar = (cp) this.b.get(str6);
            switch (cpVar.c()) {
                case 4:
                    str5 = str5 + str4 + str6 + ": <BYTES> \"" + ((co) cpVar).a() + "\"\n";
                    continue;
                case 5:
                    str5 = str5 + str4 + str6 + ": <NULL> \n";
                    continue;
                case 16:
                    str2 = str5 + str4 + str6 + ": " + ((ct) cpVar).j(i + 1) + "\n";
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    str5 = str5 + str4 + str6 + ": <ASCII> \"" + ((cn) cpVar).a() + "\"\n";
                    continue;
                case DownloaderService.STATUS_RUNNING /* 192 */:
                    str5 = str5 + str4 + str6 + ": <INT> " + ((cr) cpVar).a() + "\n";
                    continue;
                case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                    str5 = str5 + str4 + str6 + ": <UTF8> \"" + ((cu) cpVar).a() + "\"\n";
                    continue;
                case 224:
                    str5 = str5 + str4 + str6 + ": " + ((cq) cpVar).b(i + 1) + "\n";
                    continue;
                default:
                    str2 = str5;
                    break;
            }
            str5 = str2;
        }
        return i != 0 ? str5 + str + "} " : str5;
    }

    @Override // defpackage.cv
    public final void b(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        this.b.put(str, new cr(i));
    }

    @Override // defpackage.cv
    public final void b(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("key is : " + str + " value is : " + str2);
        }
        this.b.put(str, new cu(str2));
    }

    public final byte[] b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Enumeration enumerationKeys = this.b.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            try {
                byteArrayOutputStream.write(new cn(str).b());
                cp cpVar = (cp) this.b.get(str);
                switch (cpVar.c()) {
                    case 4:
                        byteArrayOutputStream.write(((co) cpVar).b());
                        break;
                    case 5:
                        byteArrayOutputStream.write(((cs) cpVar).a());
                        break;
                    case 16:
                        byteArrayOutputStream.write(((ct) cpVar).b());
                        break;
                    case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                        byteArrayOutputStream.write(((cn) cpVar).b());
                        break;
                    case DownloaderService.STATUS_RUNNING /* 192 */:
                        byteArrayOutputStream.write(((cr) cpVar).b());
                        break;
                    case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                        byteArrayOutputStream.write(((cu) cpVar).b());
                        break;
                    case 224:
                        byteArrayOutputStream.write(((cq) cpVar).d());
                        break;
                }
            } catch (IOException e) {
                a.e("PDXDictionary.getContent() " + e.toString() + ". ");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // defpackage.cv
    public final short c(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        return ((cp) this.b.get(str)).c();
    }

    @Override // defpackage.cv
    public final int d(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            if (a.e()) {
                a.e("PDXDictionary.getInteger() " + str + " does not exist. ");
            }
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 192) {
            return ((cr) cpVar).a();
        }
        if (a.e()) {
            a.e("PDXDictionary.getInteger() " + str + " is not a PDXInteger. ");
        }
        throw new RuntimeException("key is of wrong type. ");
    }

    public byte[] d() {
        return super.a(b());
    }

    @Override // defpackage.cv
    public final byte[] e(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            a.e("PDXDictionary.getByteString() " + str + " does not exist. ");
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 4) {
            return ((co) cpVar).a();
        }
        a.e("PDXDictionary.getByteString() " + str + " is not a PDXByteString. ");
        throw new RuntimeException("key is of wrong type. ");
    }

    @Override // defpackage.cv
    public final String f(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            a.e("PDXDictionary.getUTF8String() " + str + " does not exist. ");
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 193) {
            return ((cu) cpVar).a();
        }
        a.e("PDXDictionary.getUTF8String() " + str + " is not a PDXUTF8String. ");
        throw new RuntimeException("key is of wrong type. ");
    }

    @Override // defpackage.cv
    public final String g(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            a.e("PDXDictionary.getAsciiString() " + str + " does not exist. ");
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 22) {
            return ((cn) cpVar).a();
        }
        a.e("PDXDictionary.getAsciiString() " + str + " is not a PDXAsciiString. ");
        throw new RuntimeException("key is of wrong type. ");
    }

    @Override // defpackage.cv
    public final cv h(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            a.e("PDXDictionary.getDictionary() " + str + " does not exist. ");
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 224) {
            return (cq) cpVar;
        }
        a.e("PDXDictionary.getDictionary() " + str + " is not a PDXDictionary. ");
        throw new RuntimeException("key is of wrong type. ");
    }

    @Override // defpackage.cv
    public final cw i(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null.");
        }
        cp cpVar = (cp) this.b.get(str);
        if (cpVar == null) {
            a.e("PDXDictionary.getSequence() " + str + " does not exist. ");
            throw new RuntimeException("key does not exist. ");
        }
        if (cpVar.c() == 16) {
            return (ct) cpVar;
        }
        a.e("PDXDictionary.getSequence() " + str + " is not a PDXSequence. ");
        throw new RuntimeException("key is of wrong type. ");
    }

    public String toString() {
        return b(0);
    }
}
