package com.nuance.nmsp.client.sdk.oem;

import defpackage.bm;
import defpackage.cz;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class EndPointerOEM {
    private bm a = defpackage.d.a(getClass());
    private boolean b = false;
    private b c = new b(0);

    public static class a {
        public static final a a = new a(1);
        public static final a b = new a(2);
        public static final a c = new a(3);
        private int d;

        private a(int i) {
            this.d = 1;
            this.d = i;
        }

        public static a a(int i) {
            if (b.d == i) {
                return b;
            }
            return c.d == i ? c : a;
        }
    }

    class b {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }
    }

    private static native int getCurrentEndPointerState();

    private static native void initializeEndPointer(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9);

    private static native int resetVad();

    public final a a() {
        return this.b ? a.a(getCurrentEndPointerState()) : a.a;
    }

    public final void a(Vector vector) {
        this.c.a = 0;
        this.c.b = 0;
        this.c.c = 50;
        this.c.d = 15;
        this.c.e = 7;
        this.c.g = 50;
        this.c.h = 5;
        this.c.i = 35;
        this.c.f = 0;
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                cz czVar = (cz) vector.get(i);
                String strA = czVar.a();
                if (strA.equals("ep.enable")) {
                    if (new String(czVar.b()).equalsIgnoreCase("TRUE")) {
                        if (this.a.b()) {
                            this.a.b("Stop on end of speech is activated.");
                        }
                        this.c.a = 1;
                    }
                } else if (strA.equals("ep.VadLongUtterance")) {
                    if (new String(czVar.b()).equalsIgnoreCase("TRUE")) {
                        this.c.b = 1;
                    }
                } else if (strA.equals("ep.VadHistoryLength")) {
                    this.c.c = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadBeginLength")) {
                    this.c.d = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadBeginThreshold")) {
                    this.c.e = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadEndLength")) {
                    this.c.g = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadEndThreshold")) {
                    this.c.h = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadInterSpeechLength")) {
                    this.c.i = Integer.parseInt(new String(czVar.b()));
                } else if (strA.equals("ep.VadBeginDelay")) {
                    this.c.f = Integer.parseInt(new String(czVar.b()));
                }
            }
        }
        this.b = this.c.a == 1;
        if (this.b) {
            initializeEndPointer(this.c.a, this.c.b, this.c.c, this.c.d, this.c.e, this.c.f, this.c.g, this.c.h, this.c.i);
            b();
        }
    }

    public final void b() {
        if (this.b) {
            resetVad();
        }
    }
}
