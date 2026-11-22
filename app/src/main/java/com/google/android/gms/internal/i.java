package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends h {
    private static Method kA;
    private static Method kB;
    private static Method kC;
    private static Method kD;
    private static Method kE;
    private static Method kF;
    private static Method kG;
    private static Method kH;
    private static Method kI;
    private static String kJ;
    private static String kK;
    private static String kL;
    private static o kM;
    private static long startTime = 0;
    static boolean kN = false;

    static class a extends Exception {
        public a() {
        }

        public a(Throwable th) {
            super(th);
        }
    }

    protected i(Context context, m mVar, n nVar) {
        super(context, mVar, nVar);
    }

    static String a(Context context, m mVar) throws a {
        if (kK != null) {
            return kK;
        }
        if (kD == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) kD.invoke(null, context);
            if (byteBuffer == null) {
                throw new a();
            }
            kK = mVar.a(byteBuffer.array(), true);
            return kK;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static ArrayList<Long> a(MotionEvent motionEvent, DisplayMetrics displayMetrics) throws a {
        if (kE == null || motionEvent == null) {
            throw new a();
        }
        try {
            return (ArrayList) kE.invoke(null, motionEvent, displayMetrics);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    protected static synchronized void a(String str, Context context, m mVar) {
        if (!kN) {
            try {
                kM = new o(mVar, null);
                kJ = str;
                g(context);
                startTime = w().longValue();
                kN = true;
            } catch (a e) {
            } catch (UnsupportedOperationException e2) {
            }
        }
    }

    static String b(Context context, m mVar) throws a {
        if (kL != null) {
            return kL;
        }
        if (kG == null) {
            throw new a();
        }
        try {
            ByteBuffer byteBuffer = (ByteBuffer) kG.invoke(null, context);
            if (byteBuffer == null) {
                throw new a();
            }
            kL = mVar.a(byteBuffer.array(), true);
            return kL;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    private static String b(byte[] bArr, String str) throws a {
        try {
            return new String(kM.c(bArr, str), "UTF-8");
        } catch (o.a e) {
            throw new a(e);
        } catch (UnsupportedEncodingException e2) {
            throw new a(e2);
        }
    }

    static String d(Context context) throws a {
        if (kF == null) {
            throw new a();
        }
        try {
            String str = (String) kF.invoke(null, context);
            if (str == null) {
                throw new a();
            }
            return str;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static ArrayList<Long> e(Context context) throws a {
        if (kH == null) {
            throw new a();
        }
        try {
            ArrayList<Long> arrayList = (ArrayList) kH.invoke(null, context);
            if (arrayList == null || arrayList.size() != 2) {
                throw new a();
            }
            return arrayList;
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static int[] f(Context context) throws a {
        if (kI == null) {
            throw new a();
        }
        try {
            return (int[]) kI.invoke(null, context);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    private static void g(Context context) throws a {
        try {
            byte[] bArrB = kM.b(q.getKey());
            byte[] bArrC = kM.c(bArrB, q.E());
            File cacheDir = context.getCacheDir();
            if (cacheDir == null && (cacheDir = context.getDir("dex", 0)) == null) {
                throw new a();
            }
            File file = cacheDir;
            File fileCreateTempFile = File.createTempFile("ads", ".jar", file);
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            fileOutputStream.write(bArrC, 0, bArrC.length);
            fileOutputStream.close();
            try {
                DexClassLoader dexClassLoader = new DexClassLoader(fileCreateTempFile.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
                Class clsLoadClass = dexClassLoader.loadClass(b(bArrB, q.H()));
                Class clsLoadClass2 = dexClassLoader.loadClass(b(bArrB, q.T()));
                Class clsLoadClass3 = dexClassLoader.loadClass(b(bArrB, q.N()));
                Class clsLoadClass4 = dexClassLoader.loadClass(b(bArrB, q.L()));
                Class clsLoadClass5 = dexClassLoader.loadClass(b(bArrB, q.V()));
                Class clsLoadClass6 = dexClassLoader.loadClass(b(bArrB, q.J()));
                Class clsLoadClass7 = dexClassLoader.loadClass(b(bArrB, q.R()));
                Class clsLoadClass8 = dexClassLoader.loadClass(b(bArrB, q.P()));
                Class clsLoadClass9 = dexClassLoader.loadClass(b(bArrB, q.F()));
                kA = clsLoadClass.getMethod(b(bArrB, q.I()), new Class[0]);
                kB = clsLoadClass2.getMethod(b(bArrB, q.U()), new Class[0]);
                kC = clsLoadClass3.getMethod(b(bArrB, q.O()), new Class[0]);
                kD = clsLoadClass4.getMethod(b(bArrB, q.M()), Context.class);
                kE = clsLoadClass5.getMethod(b(bArrB, q.W()), MotionEvent.class, DisplayMetrics.class);
                kF = clsLoadClass6.getMethod(b(bArrB, q.K()), Context.class);
                kG = clsLoadClass7.getMethod(b(bArrB, q.S()), Context.class);
                kH = clsLoadClass8.getMethod(b(bArrB, q.Q()), Context.class);
                kI = clsLoadClass9.getMethod(b(bArrB, q.G()), Context.class);
            } finally {
                String name = fileCreateTempFile.getName();
                fileCreateTempFile.delete();
                new File(file, name.replace(".jar", ".dex")).delete();
            }
        } catch (o.a e) {
            throw new a(e);
        } catch (FileNotFoundException e2) {
            throw new a(e2);
        } catch (IOException e3) {
            throw new a(e3);
        } catch (ClassNotFoundException e4) {
            throw new a(e4);
        } catch (NoSuchMethodException e5) {
            throw new a(e5);
        } catch (NullPointerException e6) {
            throw new a(e6);
        }
    }

    static String v() throws a {
        if (kJ == null) {
            throw new a();
        }
        return kJ;
    }

    static Long w() throws a {
        if (kA == null) {
            throw new a();
        }
        try {
            return (Long) kA.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static String x() throws a {
        if (kC == null) {
            throw new a();
        }
        try {
            return (String) kC.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    static Long y() throws a {
        if (kB == null) {
            throw new a();
        }
        try {
            return (Long) kB.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new a(e);
        } catch (InvocationTargetException e2) {
            throw new a(e2);
        }
    }

    @Override // com.google.android.gms.internal.h
    protected void b(Context context) {
        try {
            try {
                a(1, x());
            } catch (a e) {
            }
            try {
                a(2, v());
            } catch (a e2) {
            }
            try {
                long jLongValue = w().longValue();
                a(25, jLongValue);
                if (startTime != 0) {
                    a(17, jLongValue - startTime);
                    a(23, startTime);
                }
            } catch (a e3) {
            }
            try {
                ArrayList<Long> arrayListE = e(context);
                a(31, arrayListE.get(0).longValue());
                a(32, arrayListE.get(1).longValue());
            } catch (a e4) {
            }
            try {
                a(33, y().longValue());
            } catch (a e5) {
            }
            try {
                a(27, a(context, this.ky));
            } catch (a e6) {
            }
            try {
                a(29, b(context, this.ky));
            } catch (a e7) {
            }
            try {
                int[] iArrF = f(context);
                a(5, iArrF[0]);
                a(6, iArrF[1]);
            } catch (a e8) {
            }
        } catch (IOException e9) {
        }
    }

    @Override // com.google.android.gms.internal.h
    protected void c(Context context) {
        try {
            try {
                a(2, v());
            } catch (a e) {
            }
            try {
                a(1, x());
            } catch (a e2) {
            }
            try {
                a(25, w().longValue());
            } catch (a e3) {
            }
            try {
                ArrayList<Long> arrayListA = a(this.kw, this.kx);
                a(14, arrayListA.get(0).longValue());
                a(15, arrayListA.get(1).longValue());
                if (arrayListA.size() >= 3) {
                    a(16, arrayListA.get(2).longValue());
                }
            } catch (a e4) {
            }
        } catch (IOException e5) {
        }
    }
}
