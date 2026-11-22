package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.vending.expansion.downloader.Constants;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ab {
    private final a mu;
    private final Runnable mv;
    private av mw;
    private boolean mx;
    private boolean my;
    private long mz;

    public static class a {
        private final Handler mHandler;

        public a(Handler handler) {
            this.mHandler = handler;
        }

        public boolean postDelayed(Runnable runnable, long timeFromNowInMillis) {
            return this.mHandler.postDelayed(runnable, timeFromNowInMillis);
        }

        public void removeCallbacks(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public ab(u uVar) {
        this(uVar, new a(gq.wR));
    }

    ab(final u uVar, a aVar) {
        this.mx = false;
        this.my = false;
        this.mz = 0L;
        this.mu = aVar;
        this.mv = new Runnable() { // from class: com.google.android.gms.internal.ab.1
            private final WeakReference<u> mA;

            {
                this.mA = new WeakReference<>(uVar);
            }

            @Override // java.lang.Runnable
            public void run() {
                ab.this.mx = false;
                u uVar2 = this.mA.get();
                if (uVar2 != null) {
                    uVar2.b(ab.this.mw);
                }
            }
        };
    }

    public void a(av avVar, long j) {
        if (this.mx) {
            gr.W("An ad refresh is already scheduled.");
            return;
        }
        this.mw = avVar;
        this.mx = true;
        this.mz = j;
        if (this.my) {
            return;
        }
        gr.U("Scheduling ad refresh " + j + " milliseconds from now.");
        this.mu.postDelayed(this.mv, j);
    }

    public boolean aD() {
        return this.mx;
    }

    public void c(av avVar) {
        a(avVar, Constants.WATCHDOG_WAKE_TIMER);
    }

    public void cancel() {
        this.mx = false;
        this.mu.removeCallbacks(this.mv);
    }

    public void pause() {
        this.my = true;
        if (this.mx) {
            this.mu.removeCallbacks(this.mv);
        }
    }

    public void resume() {
        this.my = false;
        if (this.mx) {
            this.mx = false;
            a(this.mw, this.mz);
        }
    }
}
