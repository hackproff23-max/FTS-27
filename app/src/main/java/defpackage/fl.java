package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class fl {
    private byte[] a;
    private fa b;
    private String c = "";

    public fl(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Buffer cannot be null");
        }
        this.a = bArr;
    }

    private static boolean a(byte[] bArr) {
        try {
            try {
                new InputStreamReader(new ByteArrayInputStream(bArr), "UTF8").close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e2) {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return false;
        } catch (Throwable th) {
            InputStreamReader inputStreamReader2 = null;
            try {
                inputStreamReader2.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public final void a() throws IOException {
        try {
            System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
            InputSource inputSource = new InputSource(new ByteArrayInputStream(this.a));
            if (!a(this.a)) {
                inputSource.setEncoding("cp1252");
            }
            XMLReader xMLReaderCreateXMLReader = XMLReaderFactory.createXMLReader();
            fk fkVarE = e();
            xMLReaderCreateXMLReader.setContentHandler(fkVarE);
            xMLReaderCreateXMLReader.parse(inputSource);
            this.b = fkVarE.a();
        } catch (SAXException e) {
            this.c = e.getMessage();
        }
    }

    public final boolean b() {
        return this.b != null;
    }

    public final fa c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    protected abstract fk e();
}
