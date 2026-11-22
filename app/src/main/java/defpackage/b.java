package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Thread {
    private Handler a = null;
    private final ArrayList<a> b = new ArrayList<>();
    private final Object c;

    static class a {
        public final Runnable a;
        public final int b;

        public a(Runnable runnable, int i) {
            this.a = runnable;
            this.b = i;
        }
    }

    public b(Object obj) {
        this.c = obj;
        start();
    }

    public static void a() {
        Looper.myLooper().quit();
    }

    public final void a(Runnable runnable, int i) {
        if (this.a == null) {
            this.b.add(new a(runnable, i));
        } else if (i > 0) {
            this.a.postDelayed(runnable, i);
        } else {
            this.a.post(runnable);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Looper.prepare();
        synchronized (this.c) {
            this.a = new Handler();
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                a aVar = this.b.get(i);
                if (aVar.b > 0) {
                    this.a.postDelayed(aVar.a, aVar.b);
                } else {
                    this.a.post(aVar.a);
                }
            }
            this.b.clear();
        }
        Looper.loop();
    }
}
