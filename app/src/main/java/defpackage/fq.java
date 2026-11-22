package defpackage;

import com.google.android.vending.expansion.downloader.Constants;
import java.util.Hashtable;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes.dex */
public class fq extends DefaultHandler implements fk {
    private static final bm a = d.a(fq.class);
    private Stack b = new Stack();
    private StringBuffer c = null;
    private StringBuffer d = null;
    private String e = null;
    private String f = null;
    private double g = 0.0d;
    private Vector h = new Vector();
    private Hashtable i = new Hashtable();

    private void a(fg fgVar) {
        if (!this.h.isEmpty()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.h.size()) {
                    break;
                }
                if (((fg) this.h.elementAt(i2)).b() < fgVar.b()) {
                    this.h.insertElementAt(fgVar, i2);
                    return;
                }
                i = i2 + 1;
            }
        }
        this.h.addElement(fgVar);
    }

    private void b() throws SAXException {
        if (this.b.isEmpty() || this.b.peek() != "input") {
            throw new SAXException("End Element> The top of the stack does not contain the token interpretation");
        }
        if (this.e == null) {
            throw new SAXException("Did not get any timings from input");
        }
        if (this.c == null) {
            throw new SAXException("Did not get any character from input");
        }
        StringTokenizer stringTokenizer = new StringTokenizer(this.c.toString(), " ");
        StringTokenizer stringTokenizer2 = new StringTokenizer(this.e, ",");
        StringTokenizer stringTokenizer3 = this.f != null ? new StringTokenizer(this.f, ",") : null;
        this.c = null;
        this.e = null;
        this.f = null;
        if (stringTokenizer.countTokens() != stringTokenizer2.countTokens()) {
            throw new SAXException("timing(" + stringTokenizer2.countTokens() + ") and inputs(" + stringTokenizer.countTokens() + ") information does not have the same number of items");
        }
        fg fgVar = new fg();
        fgVar.a(this.g);
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            String strNextToken2 = stringTokenizer2.nextToken();
            double d = 0.0d;
            if (stringTokenizer3 != null) {
                d = Double.parseDouble(stringTokenizer3.nextToken());
            }
            fgVar.a(new fh(strNextToken, Long.parseLong(strNextToken2.substring(0, strNextToken2.indexOf(Constants.FILENAME_SEQUENCE_SEPARATOR))), Long.parseLong(strNextToken2.substring(strNextToken2.indexOf(Constants.FILENAME_SEQUENCE_SEPARATOR) + 1)), d, true, (byte) 0));
        }
        a(fgVar);
    }

    @Override // defpackage.fk
    public final fa a() {
        Vector vector = this.h;
        Hashtable hashtable = this.i;
        return new ff(vector);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (a.a()) {
            a.a("Received characters: " + new String(cArr, i, i2));
        }
        if (!this.b.isEmpty() && this.b.peek() == "input") {
            if (this.c == null) {
                this.c = new StringBuffer(i2);
            }
            this.c.append(cArr, i, i2);
        } else {
            if (this.b.isEmpty() || this.b.peek() != "NSS_Audio_Statistics") {
                return;
            }
            if (this.d == null) {
                this.d = new StringBuffer(i2);
            }
            this.d.append(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        if (a.a()) {
            a.a("Received endElement " + str2);
        }
        if (str2.equals("result")) {
            if (this.b.isEmpty() || this.b.peek() != "result") {
                throw new SAXException("End Element> The top of the stack does not contain the token result");
            }
            this.b.pop();
            return;
        }
        if (str2.equals("interpretation")) {
            if (this.b.isEmpty() || this.b.peek() != "interpretation") {
                throw new SAXException("End Element> The top of the stack does not contain the token interpretation");
            }
            this.b.pop();
            return;
        }
        if (str2.equals("input")) {
            b();
            this.b.pop();
            return;
        }
        if (str2.equals("NSS_Audio_Statistics")) {
            if (this.b.isEmpty() || this.b.peek() != "NSS_Audio_Statistics") {
                throw new SAXException("End Element> The top of the stack does not contain the token NSS_Audio_Statistics");
            }
            this.b.pop();
            return;
        }
        if (this.b.isEmpty() || this.b.peek() != "NSS_Audio_Statistics") {
            return;
        }
        String strTrim = this.d == null ? "" : this.d.toString().trim();
        if (str2.equals("InputAudioLength")) {
            this.i.put("IAL", strTrim);
        }
        this.i.put(str2, strTrim);
        this.d = null;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (a.a()) {
            a.a("Received startElement " + str2);
        }
        if (str2.equals("result")) {
            if (this.b.size() != 0) {
                throw new SAXException("StartElement> Found result but it is not the first token.");
            }
            this.b.push("result");
            return;
        }
        if (!str2.equals("interpretation")) {
            if (str2.equals("input")) {
                if (this.b.isEmpty() || this.b.peek() != "interpretation") {
                    throw new SAXException("StartElement> The input token was found without a interpretation being opened before.");
                }
                this.b.push("input");
                return;
            }
            if (str2.equals("NSS_Audio_Statistics")) {
                if (this.b.isEmpty() || this.b.peek() != "result") {
                    throw new SAXException("StartElement> The NSS_Audio_Statistics token was found without a result being opened before.");
                }
                this.b.push("NSS_Audio_Statistics");
                return;
            }
            return;
        }
        if (this.b.isEmpty() || this.b.peek() != "result") {
            throw new SAXException("StartElement> The interpretation token was found without a result being opened before.");
        }
        this.b.push("interpretation");
        String value = attributes.getValue("timing");
        String value2 = attributes.getValue("confidence");
        String value3 = attributes.getValue("word_confidence");
        if (value == null) {
            throw new SAXException("StartElement> There are no timings associated with this interpretation.");
        }
        if (value2 == null) {
            throw new SAXException("StartElement> There is no confidence associated with this interpretation.");
        }
        this.e = value;
        this.f = value3;
        try {
            this.g = Double.parseDouble(value2);
        } catch (NumberFormatException e) {
            throw new SAXException("StartElement> Could not parse the confidence: " + value2, e);
        }
    }
}
