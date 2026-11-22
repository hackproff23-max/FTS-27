package com.google.android.gms.internal;

import android.content.Context;
import android.view.MotionEvent;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
@ey
class x implements g, Runnable {
    private u.b lB;
    private final List<Object[]> mp = new Vector();
    private final AtomicReference<g> mq = new AtomicReference<>();
    CountDownLatch mr = new CountDownLatch(1);

    public x(u.b bVar) {
        this.lB = bVar;
        if (gq.dB()) {
            gh.a(this);
        } else {
            run();
        }
    }

    private void aB() {
        if (this.mp.isEmpty()) {
            return;
        }
        for (Object[] objArr : this.mp) {
            if (objArr.length == 1) {
                this.mq.get().a((MotionEvent) objArr[0]);
            } else if (objArr.length == 3) {
                this.mq.get().a(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
    }

    private Context i(Context context) {
        Context applicationContext;
        return (aC() && (applicationContext = context.getApplicationContext()) != null) ? applicationContext : context;
    }

    @Override // com.google.android.gms.internal.g
    public String a(Context context) {
        g gVar;
        if (!aA() || (gVar = this.mq.get()) == null) {
            return "";
        }
        aB();
        return gVar.a(i(context));
    }

    @Override // com.google.android.gms.internal.g
    public String a(Context context, String str) {
        g gVar;
        if (!aA() || (gVar = this.mq.get()) == null) {
            return "";
        }
        aB();
        return gVar.a(i(context), str);
    }

    @Override // com.google.android.gms.internal.g
    public void a(int i, int i2, int i3) {
        g gVar = this.mq.get();
        if (gVar == null) {
            this.mp.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        } else {
            aB();
            gVar.a(i, i2, i3);
        }
    }

    @Override // com.google.android.gms.internal.g
    public void a(MotionEvent motionEvent) {
        g gVar = this.mq.get();
        if (gVar == null) {
            this.mp.add(new Object[]{motionEvent});
        } else {
            aB();
            gVar.a(motionEvent);
        }
    }

    protected void a(g gVar) {
        this.mq.set(gVar);
    }

    protected boolean aA() {
        try {
            this.mr.await();
            return true;
        } catch (InterruptedException e) {
            gr.d("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    protected boolean aC() {
        return ga.bN().getBoolean("gads:spam_app_context:enabled", false);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            a(j.a(this.lB.lO.wS, i(this.lB.lM)));
        } finally {
            this.mr.countDown();
            this.lB = null;
        }
    }
}
