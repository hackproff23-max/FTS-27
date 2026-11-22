package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class fg implements fc {
    private Vector a = new Vector();
    private ff b = null;
    private double c = 0.0d;

    private Vector b(int i, int i2) {
        Vector vector = new Vector();
        Vector vectorC = c(i, i2);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= vectorC.size()) {
                return vector;
            }
            fh fhVar = (fh) vectorC.elementAt(i4);
            if (fhVar.e()) {
                vector.addElement(fhVar);
            }
            i3 = i4 + 1;
        }
    }

    private Vector c(int i) {
        Vector vector = new Vector();
        int length = toString().length();
        int i2 = i > 0 ? i - 1 : i;
        if (i < length) {
            i++;
        }
        Vector vectorC = c(i2, i);
        int i3 = 0;
        while (true) {
            int i4 = i3;
            if (i4 >= vectorC.size()) {
                return vector;
            }
            fh fhVar = (fh) vectorC.elementAt(i4);
            if (fhVar.e()) {
                vector.addElement(fhVar);
            }
            i3 = i4 + 1;
        }
    }

    private Vector c(int i, int i2) {
        Vector vector = new Vector();
        int length = toString().length();
        int size = -1;
        int i3 = -1;
        int length2 = 0;
        for (int i4 = 0; i4 < this.a.size(); i4++) {
            if (i == length2 && i2 == length2) {
                return vector;
            }
            length2 += d(i4).b().length();
            if (i3 == -1 && i < length2) {
                i3 = i4;
            }
            if (i3 != -1 && size == -1 && i2 <= length2) {
                size = i4;
            }
        }
        if (size == -1 && i2 == length) {
            size = this.a.size() - 1;
        }
        if (i3 == -1 || size == -1) {
            return vector;
        }
        while (i3 <= size) {
            vector.addElement(d(i3));
            i3++;
        }
        return vector;
    }

    private fh d(int i) {
        return (fh) this.a.elementAt(i);
    }

    @Override // defpackage.fc
    public final int a() {
        int i = 0;
        int size = this.a.size();
        if (size != 0) {
            for (int i2 = 0; i2 < size; i2++) {
                if (!d(i2).f()) {
                    i++;
                }
            }
        }
        return i;
    }

    @Override // defpackage.fc
    public final ez a(int i, int i2) {
        int length = toString().length();
        if (i < 0 || i2 > length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 < i) {
            throw new IllegalArgumentException("Cursor position end is less than begin");
        }
        Vector vectorC = i == i2 ? c(i) : b(i, i2);
        if (vectorC.isEmpty()) {
            return new ez(new Vector());
        }
        Vector vectorA = this.b.a(this, vectorC);
        for (int i3 = 0; i3 < this.a.size(); i3++) {
            d(i3);
            vectorC.firstElement();
            vectorC.lastElement();
        }
        return new ez(vectorA);
    }

    @Override // defpackage.fc
    public final fd a(int i) {
        int i2 = -1;
        int i3 = -1;
        while (i2 != i) {
            i3++;
            if (i3 > this.a.size()) {
                return null;
            }
            if (!d(i3).f()) {
                i2++;
            }
        }
        fh fhVarD = d(i3);
        if (i3 + 1 < this.a.size() && d(i3 + 1).f()) {
            d(i3 + 1);
        }
        if (i3 == 0 || !d(i3 - 1).f()) {
            return fhVarD;
        }
        d(i3 - 1);
        return fhVarD;
    }

    final fe a(long j, long j2) {
        Vector vector = new Vector();
        for (int i = 0; i < this.a.size(); i++) {
            fh fhVarD = d(i);
            long jC = fhVarD.c();
            long jD = fhVarD.d();
            if (jC >= j2 || jD <= j) {
                if (jC > j2) {
                    break;
                }
            } else {
                vector.addElement(fhVarD);
            }
        }
        if (vector.size() > 0 && ((fh) vector.firstElement()).f()) {
            vector.removeElementAt(0);
        }
        if (vector.size() > 0 && ((fh) vector.lastElement()).f()) {
            vector.removeElementAt(vector.size() - 1);
        }
        return new fe(vector);
    }

    public final void a(double d) {
        this.c = d;
    }

    final void a(ff ffVar) {
        this.b = ffVar;
    }

    public final void a(fh fhVar) {
        long jD;
        if (this.a.size() != 0 && !((fh) this.a.lastElement()).h() && !fhVar.g()) {
            int size = this.a.size();
            if (this.a.size() == 0) {
                jD = 0;
            } else {
                jD = size == this.a.size() ? d(size - 1).d() : d(size).c();
            }
            this.a.insertElementAt(new fh(" ", jD, jD, 0.0d, false, (byte) 0), size);
        }
        this.a.addElement(fhVar);
    }

    @Override // defpackage.fc
    public final double b() {
        return this.c;
    }

    @Override // defpackage.fc
    public final fd b(int i) {
        int i2 = 0;
        fd fdVar = null;
        if (this.a.size() > 0 && i <= toString().length()) {
            int length = 0;
            while (length <= i) {
                fdVar = (fd) this.a.get(i2);
                i2++;
                if (fdVar != null) {
                    length += fdVar.toString().length();
                }
            }
        }
        return fdVar;
    }

    final void c() {
        int iIndexOf;
        int iIndexOf2;
        String property = System.getProperty("line.separator");
        if (property == null) {
            property = "\n";
        }
        for (int i = 0; i < this.a.size(); i++) {
            fh fhVarD = d(i);
            StringBuffer stringBuffer = new StringBuffer(fhVarD.b());
            int i2 = 0;
            while (i2 < stringBuffer.length() && (iIndexOf2 = stringBuffer.toString().indexOf("\r\n", i2)) != -1) {
                stringBuffer.deleteCharAt(iIndexOf2);
                stringBuffer.deleteCharAt(iIndexOf2);
                stringBuffer.insert(iIndexOf2, '\n');
                i2 = iIndexOf2 + 1;
            }
            int length = 0;
            while (length < stringBuffer.length() && (iIndexOf = stringBuffer.toString().indexOf("\n", length)) != -1) {
                stringBuffer.deleteCharAt(iIndexOf);
                stringBuffer.insert(iIndexOf, property);
                length = iIndexOf + property.length();
            }
            fhVarD.a(stringBuffer.toString());
        }
    }

    public final String toString() {
        if (this.a.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.a.size(); i++) {
            stringBuffer.append(d(i).toString());
        }
        return stringBuffer.toString();
    }
}
