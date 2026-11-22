package com.nuance.nmsp.client.sdk.oem;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Handler {
    private Handler a;
    private final ArrayList<C0192a> b = new ArrayList<>();

    /* JADX INFO: renamed from: com.nuance.nmsp.client.sdk.oem.a$a, reason: collision with other inner class name */
    class C0192a {
        final Message a;
        final long b;

        C0192a(Message message, long j) {
            this.a = message;
            this.b = j;
        }
    }

    public final synchronized void a() {
        this.a = new Handler();
        if (this.b.size() > 0) {
            for (C0192a c0192a : this.b) {
                this.a.sendMessageAtTime(c0192a.a, c0192a.b);
            }
            this.b.clear();
        }
    }

    @Override // android.os.Handler
    public final synchronized boolean sendMessageAtTime(Message message, long j) {
        boolean zSendMessageAtTime = true;
        synchronized (this) {
            if (this.a == null) {
                this.b.add(new C0192a(message, j));
            } else if (this.a.getLooper().getThread().isAlive()) {
                zSendMessageAtTime = this.a.sendMessageAtTime(message, j);
            }
        }
        return zSendMessageAtTime;
    }
}
