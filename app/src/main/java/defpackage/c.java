package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    private final ArrayList<Object> a;

    public abstract class a {
        private final Iterator<Object> a;

        public a(c cVar) {
            this.a = cVar.a.iterator();
        }

        public final boolean a() {
            return this.a.hasNext();
        }

        public final Object b() {
            return this.a.next();
        }
    }

    public c(int i) {
        this.a = new ArrayList<>(i);
    }

    public final Object a() {
        return this.a.remove(0);
    }

    public final Object a(int i) {
        return this.a.get(i);
    }

    public final void a(Object obj) {
        this.a.add(obj);
    }

    public final void b() {
        this.a.clear();
    }

    public final void b(Object obj) {
        this.a.remove(obj);
    }

    public final int c() {
        return this.a.size();
    }

    public final boolean c(Object obj) {
        return this.a.contains(obj);
    }
}
