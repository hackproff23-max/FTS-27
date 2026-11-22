package defpackage;

import com.flurry.android.Constants;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.millennialmedia.android.MMException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class ct extends cp implements cw {
    private static final bm a = d.a(ct.class);
    private Vector b;

    public ct() {
        super((short) 16);
        this.b = new Vector();
    }

    public ct(byte[] bArr) {
        super((short) 16);
        this.b = new Vector();
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            int i3 = bArr[i] & Constants.UNKNOWN;
            int iA = a(bArr, i2);
            int iA2 = i2 + a(iA);
            byte[] bArr2 = new byte[iA];
            System.arraycopy(bArr, iA2, bArr2, 0, bArr2.length);
            i = iA + iA2;
            switch (i3) {
                case 4:
                    this.b.addElement(new co(bArr2));
                    break;
                case 5:
                    this.b.addElement(new cs());
                    break;
                case 16:
                    this.b.addElement(new ct(bArr2));
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    this.b.addElement(new cn(bArr2));
                    break;
                case DownloaderService.STATUS_RUNNING /* 192 */:
                    this.b.addElement(new cr(bArr2));
                    break;
                case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                    this.b.addElement(new cu(bArr2));
                    break;
                case 224:
                    this.b.addElement(new cq(bArr2, (byte) 0));
                    break;
                default:
                    a.e("PDXSequence() Unknown PDXClass type: " + i3 + ". ");
                    break;
            }
        }
    }

    @Override // defpackage.cw
    public final int a() {
        return this.b.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.cw
    public final void a(cv cvVar) {
        if (cvVar == 0) {
            throw new IllegalArgumentException("value is null.");
        }
        if (((cp) cvVar).c() != 224) {
            a.e("PDXSequence.addDictionary() value is not a valid dictionary.");
            throw new IllegalArgumentException("value is not a valid dictionary. ");
        }
        this.b.addElement(cvVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.cw
    public final void a(cw cwVar) {
        if (cwVar == 0) {
            throw new IllegalArgumentException("value is null.");
        }
        if (((cp) cwVar).c() != 16) {
            a.e("PDXSequence.addSequence() value is not a valid sequence.");
            throw new IllegalArgumentException("value is not a valid sequence. ");
        }
        this.b.addElement(cwVar);
    }

    @Override // defpackage.cw
    public final void a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("value is null.");
        }
        this.b.addElement(new cu(str));
    }

    @Override // defpackage.cw
    public final short b(int i) {
        if (i < this.b.size()) {
            return ((cp) this.b.elementAt(i)).c();
        }
        a.e("PDXSequence.getType() index " + i + " is out of range. ");
        throw new IndexOutOfBoundsException();
    }

    @Override // defpackage.cw
    public final void b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("value is null.");
        }
        this.b.addElement(new co(bArr));
    }

    public final byte[] b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Enumeration enumerationElements = this.b.elements();
        while (enumerationElements.hasMoreElements()) {
            cp cpVar = (cp) enumerationElements.nextElement();
            try {
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
                a.e("PDXSequence.toByteArray() " + e.toString() + ". ");
            }
        }
        return super.a(byteArrayOutputStream.toByteArray());
    }

    @Override // defpackage.cw
    public final int c(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getInteger() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 192) {
            return ((cr) cpVar).a();
        }
        a.e("PDXSequence.getInteger() index " + i + " is not a PDXInteger. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final byte[] d(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getByteString() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 4) {
            return ((co) cpVar).a();
        }
        a.e("PDXSequence.getByteString() index " + i + " is not a PDXByteString. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final String e(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getUTF8String() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 193) {
            return ((cu) cpVar).a();
        }
        a.e("PDXSequence.getUTF8String() index " + i + " is not a PDXUTF8String. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final String f(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getAsciiString() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 22) {
            return ((cn) cpVar).a();
        }
        a.e("PDXSequence.getAsciiString() index " + i + " is not a PDXAsciiString. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final cv g(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getDictionary() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 224) {
            return (cq) cpVar;
        }
        a.e("PDXSequence.getDictionary() index " + i + " is not a PDXDictionary. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final cw h(int i) {
        if (i >= this.b.size()) {
            a.e("PDXSequence.getSequence() index " + i + " is out of range. ");
            throw new IndexOutOfBoundsException();
        }
        cp cpVar = (cp) this.b.elementAt(i);
        if (cpVar.c() == 16) {
            return (ct) cpVar;
        }
        a.e("PDXSequence.getSequence() index " + i + " is not a PDXSequence. ");
        throw new RuntimeException("index is of wrong type.");
    }

    @Override // defpackage.cw
    public final void i(int i) {
        this.b.addElement(new cr(i));
    }

    protected final String j(int i) {
        String str;
        String str2 = "";
        int i2 = 0;
        while (true) {
            str = str2;
            if (i2 >= i - 1) {
                break;
            }
            str2 = str + "    ";
            i2++;
        }
        String str3 = i > 0 ? str + "    " : "";
        Enumeration enumerationElements = this.b.elements();
        String str4 = "[ \n";
        while (true) {
            String str5 = str4;
            if (!enumerationElements.hasMoreElements()) {
                return str5 + str + "] ";
            }
            cp cpVar = (cp) enumerationElements.nextElement();
            switch (cpVar.c()) {
                case 4:
                    str5 = str5 + str3 + "<BYTES> \"" + ((co) cpVar).a() + "\" ";
                    break;
                case 5:
                    str5 = str5 + str3 + "<NULL> ";
                    break;
                case 16:
                    str5 = str5 + str3 + ((ct) cpVar).j(i + 1);
                    break;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    str5 = str5 + str3 + "<ASCII> \"" + ((cn) cpVar).a() + "\" ";
                    break;
                case DownloaderService.STATUS_RUNNING /* 192 */:
                    str5 = str5 + str3 + "<INT> " + ((cr) cpVar).a();
                    break;
                case DownloaderService.STATUS_PAUSED_BY_APP /* 193 */:
                    str5 = str5 + str3 + "<UTF8> \"" + ((cu) cpVar).a() + "\" ";
                    break;
                case 224:
                    str5 = str5 + str3 + ((cq) cpVar).b(i + 1);
                    break;
            }
            if (enumerationElements.hasMoreElements()) {
                str5 = str5 + ",";
            }
            str4 = str5 + "\n";
        }
    }

    public String toString() {
        return j(0);
    }
}
