package com.google.android.gms.common.api;

import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.jr;
import com.google.android.gms.internal.jx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class BaseImplementation {

    public static abstract class AbstractPendingResult<R extends Result> implements b<R>, PendingResult<R> {
        private ResultCallback<R> Jr;
        private volatile R Js;
        private volatile boolean Jt;
        private boolean Ju;
        private boolean Jv;
        private jr Jw;
        protected final CallbackHandler<R> mHandler;
        private final Object Jp = new Object();
        private final CountDownLatch mr = new CountDownLatch(1);
        private final ArrayList<PendingResult.a> Jq = new ArrayList<>();

        protected AbstractPendingResult(Looper looper) {
            this.mHandler = new CallbackHandler<>(looper);
        }

        protected AbstractPendingResult(CallbackHandler<R> callbackHandler) {
            this.mHandler = callbackHandler;
        }

        private void c(R r) {
            this.Js = r;
            this.Jw = null;
            this.mr.countDown();
            Status status = this.Js.getStatus();
            if (this.Jr != null) {
                this.mHandler.removeTimeoutMessages();
                if (!this.Ju) {
                    this.mHandler.sendResultCallback(this.Jr, gA());
                }
            }
            Iterator<PendingResult.a> it = this.Jq.iterator();
            while (it.hasNext()) {
                it.next().m(status);
            }
            this.Jq.clear();
        }

        private R gA() {
            R r;
            synchronized (this.Jp) {
                jx.a(!this.Jt, "Result has already been consumed.");
                jx.a(isReady(), "Result is not ready.");
                r = this.Js;
                gB();
            }
            return r;
        }

        private void gC() {
            synchronized (this.Jp) {
                if (!isReady()) {
                    b(c(Status.Kx));
                    this.Jv = true;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void gD() {
            synchronized (this.Jp) {
                if (!isReady()) {
                    b(c(Status.Kz));
                    this.Jv = true;
                }
            }
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public final void a(PendingResult.a aVar) {
            jx.a(!this.Jt, "Result has already been consumed.");
            synchronized (this.Jp) {
                if (isReady()) {
                    aVar.m(this.Js.getStatus());
                } else {
                    this.Jq.add(aVar);
                }
            }
        }

        protected final void a(jr jrVar) {
            synchronized (this.Jp) {
                this.Jw = jrVar;
            }
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public final R await() {
            jx.a(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
            jx.a(this.Jt ? false : true, "Result has already been consumed");
            try {
                this.mr.await();
            } catch (InterruptedException e) {
                gC();
            }
            jx.a(isReady(), "Result is not ready.");
            return (R) gA();
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public final R await(long j, TimeUnit timeUnit) {
            jx.a(j <= 0 || Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread when time is greater than zero.");
            jx.a(this.Jt ? false : true, "Result has already been consumed.");
            try {
                if (!this.mr.await(j, timeUnit)) {
                    gD();
                }
            } catch (InterruptedException e) {
                gC();
            }
            jx.a(isReady(), "Result is not ready.");
            return (R) gA();
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.b
        public final void b(R r) {
            synchronized (this.Jp) {
                if (this.Jv || this.Ju) {
                    BaseImplementation.a(r);
                    return;
                }
                jx.a(!isReady(), "Results have already been set");
                jx.a(this.Jt ? false : true, "Result has already been consumed");
                c(r);
            }
        }

        protected abstract R c(Status status);

        @Override // com.google.android.gms.common.api.PendingResult
        public void cancel() {
            synchronized (this.Jp) {
                if (this.Ju || this.Jt) {
                    return;
                }
                if (this.Jw != null) {
                    try {
                        this.Jw.cancel();
                    } catch (RemoteException e) {
                    }
                }
                BaseImplementation.a(this.Js);
                this.Jr = null;
                this.Ju = true;
                c(c(Status.KA));
            }
        }

        protected void gB() {
            this.Jt = true;
            this.Js = null;
            this.Jr = null;
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public boolean isCanceled() {
            boolean z;
            synchronized (this.Jp) {
                z = this.Ju;
            }
            return z;
        }

        public final boolean isReady() {
            return this.mr.getCount() == 0;
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public final void setResultCallback(ResultCallback<R> callback) {
            jx.a(!this.Jt, "Result has already been consumed.");
            synchronized (this.Jp) {
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.mHandler.sendResultCallback(callback, gA());
                } else {
                    this.Jr = callback;
                }
            }
        }

        @Override // com.google.android.gms.common.api.PendingResult
        public final void setResultCallback(ResultCallback<R> callback, long time, TimeUnit units) {
            jx.a(!this.Jt, "Result has already been consumed.");
            jx.a(this.mHandler != null, "CallbackHandler has not been set before calling setResultCallback.");
            synchronized (this.Jp) {
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.mHandler.sendResultCallback(callback, gA());
                } else {
                    this.Jr = callback;
                    this.mHandler.sendTimeoutResultCallback(this, units.toMillis(time));
                }
            }
        }
    }

    public static class CallbackHandler<R extends Result> extends Handler {
        public static final int CALLBACK_ON_COMPLETE = 1;
        public static final int CALLBACK_ON_TIMEOUT = 2;

        public CallbackHandler() {
            this(Looper.getMainLooper());
        }

        public CallbackHandler(Looper looper) {
            super(looper);
        }

        protected void deliverResultCallback(ResultCallback<R> callback, R result) {
            try {
                callback.onResult(result);
            } catch (RuntimeException e) {
                BaseImplementation.a(result);
                throw e;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Pair pair = (Pair) msg.obj;
                    deliverResultCallback((ResultCallback) pair.first, (Result) pair.second);
                    break;
                case 2:
                    ((AbstractPendingResult) msg.obj).gD();
                    break;
                default:
                    Log.wtf("GoogleApi", "Don't know how to handle this message.");
                    break;
            }
        }

        public void removeTimeoutMessages() {
            removeMessages(2);
        }

        public void sendResultCallback(ResultCallback<R> callback, R result) {
            sendMessage(obtainMessage(1, new Pair(callback, result)));
        }

        public void sendTimeoutResultCallback(AbstractPendingResult<R> pendingResult, long millis) {
            sendMessageDelayed(obtainMessage(2, pendingResult), millis);
        }
    }

    public static abstract class a<R extends Result, A extends Api.a> extends AbstractPendingResult<R> implements c.d<A> {
        private final Api.c<A> Jn;
        private final GoogleApiClient Jx;
        private c.b Jy;

        protected a(Api.c<A> cVar, GoogleApiClient googleApiClient) {
            super(googleApiClient.getLooper());
            this.Jn = (Api.c) jx.i(cVar);
            this.Jx = googleApiClient;
        }

        private void a(RemoteException remoteException) {
            l(new Status(8, remoteException.getLocalizedMessage(), null));
        }

        protected abstract void a(A a) throws RemoteException;

        @Override // com.google.android.gms.common.api.c.d
        public void a(c.b bVar) {
            this.Jy = bVar;
        }

        @Override // com.google.android.gms.common.api.c.d
        public final void b(A a) throws DeadObjectException {
            try {
                a(a);
            } catch (DeadObjectException e) {
                a(e);
                throw e;
            } catch (RemoteException e2) {
                a(e2);
            }
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        protected void gB() {
            super.gB();
            if (this.Jy != null) {
                this.Jy.b(this);
                this.Jy = null;
            }
        }

        public final a gE() {
            jx.b(this.Jx, "GoogleApiClient was not set.");
            this.Jx.b(this);
            return this;
        }

        @Override // com.google.android.gms.common.api.c.d
        public int gF() {
            return 0;
        }

        @Override // com.google.android.gms.common.api.c.d
        public final Api.c<A> gz() {
            return this.Jn;
        }

        @Override // com.google.android.gms.common.api.c.d
        public final void l(Status status) {
            jx.b(!status.isSuccess(), "Failed result must not be success");
            b((Result) c(status));
        }
    }

    public interface b<R> {
        void b(R r);
    }

    static void a(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                Log.w("GoogleApi", "Unable to release " + result, e);
            }
        }
    }
}
