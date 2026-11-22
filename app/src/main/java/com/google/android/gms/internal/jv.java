package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class jv {

    public static final class a {
        private final List<String> Nr;
        private final Object Ns;

        private a(Object obj) {
            this.Ns = jx.i(obj);
            this.Nr = new ArrayList();
        }

        public a a(String str, Object obj) {
            this.Nr.add(((String) jx.i(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder sbAppend = new StringBuilder(100).append(this.Ns.getClass().getSimpleName()).append('{');
            int size = this.Nr.size();
            for (int i = 0; i < size; i++) {
                sbAppend.append(this.Nr.get(i));
                if (i < size - 1) {
                    sbAppend.append(", ");
                }
            }
            return sbAppend.append('}').toString();
        }
    }

    public static boolean equal(Object a2, Object b) {
        return a2 == b || (a2 != null && a2.equals(b));
    }

    public static a h(Object obj) {
        return new a(obj);
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
