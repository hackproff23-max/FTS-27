package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class ay extends c {

    public final class a extends c.a {
        public a(ay ayVar) {
            super(ayVar);
        }
    }

    public ay() {
        super(0);
    }

    private ay(int i) {
        super(i);
    }

    public final a d() {
        return new a(this);
    }

    public final ay e() {
        ay ayVar = new ay(c());
        a aVarD = d();
        while (aVarD.a()) {
            ayVar.a(aVarD.b());
        }
        return ayVar;
    }

    public final Vector f() {
        Vector vector = new Vector(c());
        a aVarD = d();
        while (aVarD.a()) {
            vector.addElement(aVarD.b());
        }
        return vector;
    }
}
