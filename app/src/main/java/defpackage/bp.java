package defpackage;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public interface bp {

    public interface a {
        void a();
    }

    public static class b {
        public static final b a = new b();

        private b() {
        }
    }

    public static class c {
        public static final c a = new c();
        public static final c b = new c();
        public static final c c = new c();

        private c() {
        }
    }

    public interface d {
        void a(c cVar, Object obj);
    }

    public interface e {
        void a(c cVar, Object obj, byte[] bArr, int i, int i2, Object obj2);
    }

    public interface f {
        void a(c cVar, Object obj, int i, int i2, Object obj2);
    }

    c a(Object obj, b bVar, byte[] bArr, int i, e eVar, Object obj2);

    c a(Object obj, byte[] bArr, int i, f fVar, Object obj2);

    void a(Object obj);

    void a(String str, int i, d dVar, a aVar);

    void a(String str, int i, Vector vector, d dVar, a aVar);

    void b(Object obj);
}
