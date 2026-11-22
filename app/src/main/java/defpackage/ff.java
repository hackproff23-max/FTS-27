package defpackage;

import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class ff implements fa {
    private static final bm a = d.a(ff.class);
    private Vector b;

    public ff(Vector vector) {
        if (a.b()) {
            a.b("Received " + vector.size() + " sentences constituting the dictation result.");
        }
        this.b = vector;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.b.size()) {
                return;
            }
            fg fgVar = (fg) vector.elementAt(i2);
            fgVar.a(this);
            fgVar.c();
            i = i2 + 1;
        }
    }

    @Override // defpackage.fa
    public final int a() {
        return this.b.size();
    }

    @Override // defpackage.fa
    public final fc a(int i) {
        if (i < 0 || i >= this.b.size()) {
            return null;
        }
        return (fc) this.b.elementAt(i);
    }

    final Vector a(fg fgVar, Vector vector) {
        if (vector.size() == 0) {
            return new Vector();
        }
        long jC = ((fh) vector.elementAt(0)).c();
        long jD = ((fh) vector.elementAt(vector.size() - 1)).d();
        if (a.b()) {
            a.b("Getting alternatives of " + fgVar + " at times [" + jC + ", " + jD + "]");
        }
        Vector vector2 = new Vector();
        Hashtable hashtable = new Hashtable();
        Object obj = new Object();
        hashtable.put(fgVar.a(jC, jD).toString(), obj);
        for (int i = 0; i < this.b.size(); i++) {
            fg fgVar2 = (fg) this.b.elementAt(i);
            if (fgVar2 != fgVar) {
                fe feVarA = fgVar2.a(jC, jD);
                fe.b();
                if (a.b()) {
                    a.b("Got alternative [" + feVarA + "] for sentence at index " + i);
                }
                if (feVarA.a() != 0) {
                    String string = feVarA.toString();
                    if (!hashtable.containsKey(string)) {
                        hashtable.put(string, obj);
                        vector2.addElement(feVarA);
                    } else if (a.b()) {
                        a.b("That alternative has already been given by another sentence.");
                    }
                } else if (a.b()) {
                    a.b("Got no alternative for sentence at index " + i);
                }
            } else if (a.b()) {
                a.b("Found the same sentence at index " + i);
            }
        }
        return vector2;
    }

    public String toString() {
        return this.b.size() > 0 ? this.b.elementAt(0).toString() : "";
    }
}
