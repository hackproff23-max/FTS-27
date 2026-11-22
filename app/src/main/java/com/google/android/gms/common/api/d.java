package com.google.android.gms.common.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public final class d<L> {
    private final d<L>.a Kt;
    private volatile L mListener;

    private final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            jx.L(msg.what == 1);
            d.this.b((b) msg.obj);
        }
    }

    public interface b<L> {
        void c(L l);

        void gG();
    }

    d(Looper looper, L l) {
        this.Kt = new a(looper);
        this.mListener = (L) jx.b(l, "Listener must not be null");
    }

    public void a(b<? super L> bVar) {
        jx.b(bVar, "Notifier must not be null");
        this.Kt.sendMessage(this.Kt.obtainMessage(1, bVar));
    }

    void b(b<? super L> bVar) {
        L l = this.mListener;
        if (l == null) {
            bVar.gG();
            return;
        }
        try {
            bVar.c(l);
        } catch (Exception e) {
            Log.w("ListenerHolder", "Notifying listener failed", e);
            bVar.gG();
        }
    }

    public void clear() {
        this.mListener = null;
    }
}
