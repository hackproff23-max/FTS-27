package defpackage;

/* JADX INFO: loaded from: classes.dex */
public abstract class bv {
    private int a;

    public bv(int i) {
        this.a = i;
    }

    public boolean equals(Object obj) {
        return obj.getClass().getName().equals(getClass().getName()) && ((bv) obj).a == this.a;
    }
}
