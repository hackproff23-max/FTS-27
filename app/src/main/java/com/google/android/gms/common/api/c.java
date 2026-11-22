package com.google.android.gms.common.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jm;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.kc;
import com.google.android.vending.expansion.downloader.Constants;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class c implements GoogleApiClient {
    private final Looper JF;
    private final jm JS;
    private final int JT;
    private ConnectionResult JV;
    private int JW;
    private volatile boolean JY;
    private int Ka;
    final Handler Kd;
    BroadcastReceiver Ke;
    private final List<String> Kh;
    private boolean Ki;
    private final Context mContext;
    private final Lock zO = new ReentrantLock();
    private final Condition JR = this.zO.newCondition();
    final Queue<d<?>> JU = new LinkedList();
    private volatile int JX = 4;
    private boolean JZ = false;
    private long Kb = 120000;
    private long Kc = Constants.ACTIVE_THREAD_WATCHDOG;
    private final Bundle Kf = new Bundle();
    private final Map<Api.c<?>, Api.a> Kg = new HashMap();
    private final Set<com.google.android.gms.common.api.d<?>> Kj = Collections.newSetFromMap(new WeakHashMap());
    final Set<d<?>> Kk = Collections.newSetFromMap(new ConcurrentHashMap());
    private final b Jy = new b() { // from class: com.google.android.gms.common.api.c.1
        @Override // com.google.android.gms.common.api.c.b
        public void b(d<?> dVar) {
            c.this.Kk.remove(dVar);
        }
    };
    private final GoogleApiClient.ConnectionCallbacks Kl = new GoogleApiClient.ConnectionCallbacks() { // from class: com.google.android.gms.common.api.c.2
        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle connectionHint) {
            c.this.zO.lock();
            try {
                if (c.this.JX == 1) {
                    if (connectionHint != null) {
                        c.this.Kf.putAll(connectionHint);
                    }
                    c.this.gJ();
                }
            } finally {
                c.this.zO.unlock();
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int cause) {
            c.this.zO.lock();
            try {
                switch (cause) {
                    case 1:
                        if (c.this.gL()) {
                            c.this.zO.unlock();
                            return;
                        }
                        c.this.JY = true;
                        c.this.Ke = new a(c.this);
                        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
                        intentFilter.addDataScheme("package");
                        c.this.mContext.registerReceiver(c.this.Ke, intentFilter);
                        c.this.Kd.sendMessageDelayed(c.this.Kd.obtainMessage(1), c.this.Kb);
                        c.this.Kd.sendMessageDelayed(c.this.Kd.obtainMessage(2), c.this.Kc);
                        c.this.al(cause);
                        break;
                    case 2:
                        c.this.al(cause);
                        c.this.connect();
                        break;
                    default:
                        c.this.zO.unlock();
                }
                c.this.zO.unlock();
            } catch (Throwable th) {
                c.this.zO.unlock();
                throw th;
            }
        }
    };
    private final jm.b Km = new jm.b() { // from class: com.google.android.gms.common.api.c.3
        @Override // com.google.android.gms.internal.jm.b
        public Bundle fX() {
            return null;
        }

        @Override // com.google.android.gms.internal.jm.b
        public boolean gN() {
            return c.this.Ki;
        }

        @Override // com.google.android.gms.internal.jm.b
        public boolean isConnected() {
            return c.this.isConnected();
        }
    };

    private static class a extends BroadcastReceiver {
        private WeakReference<c> Ks;

        a(c cVar) {
            this.Ks = new WeakReference<>(cVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            c cVar;
            Uri data = intent.getData();
            String schemeSpecificPart = data != null ? data.getSchemeSpecificPart() : null;
            if (schemeSpecificPart == null || !schemeSpecificPart.equals(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE) || (cVar = this.Ks.get()) == null || cVar.isConnected() || cVar.isConnecting() || !cVar.gL()) {
                return;
            }
            cVar.connect();
        }
    }

    interface b {
        void b(d<?> dVar);
    }

    /* JADX INFO: renamed from: com.google.android.gms.common.api.c$c, reason: collision with other inner class name */
    private class HandlerC0037c extends Handler {
        HandlerC0037c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    c.this.gM();
                    break;
                case 2:
                    c.this.resume();
                    break;
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + msg.what);
                    break;
            }
        }
    }

    interface d<A extends Api.a> {
        void a(b bVar);

        void b(A a) throws DeadObjectException;

        void cancel();

        int gF();

        Api.c<A> gz();

        void l(Status status);
    }

    public c(Context context, Looper looper, jg jgVar, Map<Api<?>, Api.ApiOptions> map, Set<GoogleApiClient.ConnectionCallbacks> set, Set<GoogleApiClient.OnConnectionFailedListener> set2, int i) {
        this.mContext = context;
        this.JS = new jm(context, looper, this.Km);
        this.JF = looper;
        this.Kd = new HandlerC0037c(looper);
        this.JT = i;
        Iterator<GoogleApiClient.ConnectionCallbacks> it = set.iterator();
        while (it.hasNext()) {
            this.JS.registerConnectionCallbacks(it.next());
        }
        Iterator<GoogleApiClient.OnConnectionFailedListener> it2 = set2.iterator();
        while (it2.hasNext()) {
            this.JS.registerConnectionFailedListener(it2.next());
        }
        for (Api<?> api : map.keySet()) {
            final Api.b<?, O> bVarGx = api.gx();
            this.Kg.put(api.gz(), a(bVarGx, map.get(api), context, looper, jgVar, this.Kl, new GoogleApiClient.OnConnectionFailedListener() { // from class: com.google.android.gms.common.api.c.4
                @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
                public void onConnectionFailed(ConnectionResult result) {
                    c.this.zO.lock();
                    try {
                        if (c.this.JV == null || bVarGx.getPriority() < c.this.JW) {
                            c.this.JV = result;
                            c.this.JW = bVarGx.getPriority();
                        }
                        c.this.gJ();
                    } finally {
                        c.this.zO.unlock();
                    }
                }
            }));
        }
        this.Kh = Collections.unmodifiableList(jgVar.ho());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <C extends Api.a, O> C a(Api.b<C, O> bVar, Object obj, Context context, Looper looper, jg jgVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return (C) bVar.a(context, looper, jgVar, obj, connectionCallbacks, onConnectionFailedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final GoogleApiClient googleApiClient, final f fVar, final boolean z) {
        kc.Nu.c(googleApiClient).setResultCallback(new ResultCallback<Status>() { // from class: com.google.android.gms.common.api.c.7
            @Override // com.google.android.gms.common.api.ResultCallback
            /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
            public void onResult(Status status) {
                if (status.isSuccess() && c.this.isConnected()) {
                    c.this.reconnect();
                }
                fVar.b(status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private <A extends Api.a> void a(d<A> dVar) throws DeadObjectException {
        this.zO.lock();
        try {
            jx.b(dVar.gz() != null, "This task can not be executed or enqueued (it's probably a Batch or malformed)");
            this.Kk.add(dVar);
            dVar.a(this.Jy);
            if (gL()) {
                dVar.l(new Status(8));
            } else {
                dVar.b(a(dVar.gz()));
            }
        } finally {
            this.zO.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void al(int i) {
        this.zO.lock();
        try {
            if (this.JX != 3) {
                if (i == -1) {
                    if (isConnecting()) {
                        Iterator<d<?>> it = this.JU.iterator();
                        while (it.hasNext()) {
                            d<?> next = it.next();
                            if (next.gF() != 1) {
                                next.cancel();
                                it.remove();
                            }
                        }
                    } else {
                        this.JU.clear();
                    }
                    Iterator<d<?>> it2 = this.Kk.iterator();
                    while (it2.hasNext()) {
                        it2.next().cancel();
                    }
                    this.Kk.clear();
                    Iterator<com.google.android.gms.common.api.d<?>> it3 = this.Kj.iterator();
                    while (it3.hasNext()) {
                        it3.next().clear();
                    }
                    this.Kj.clear();
                    if (this.JV == null && !this.JU.isEmpty()) {
                        this.JZ = true;
                        this.zO.unlock();
                        return;
                    }
                }
                boolean zIsConnecting = isConnecting();
                boolean zIsConnected = isConnected();
                this.JX = 3;
                if (zIsConnecting) {
                    if (i == -1) {
                        this.JV = null;
                    }
                    this.JR.signalAll();
                }
                this.Ki = false;
                for (Api.a aVar : this.Kg.values()) {
                    if (aVar.isConnected()) {
                        aVar.disconnect();
                    }
                }
                this.Ki = true;
                this.JX = 4;
                if (zIsConnected) {
                    if (i != -1) {
                        this.JS.aE(i);
                    }
                    this.Ki = false;
                }
            }
            this.zO.unlock();
        } catch (Throwable th) {
            this.zO.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gJ() {
        this.Ka--;
        if (this.Ka == 0) {
            if (this.JV != null) {
                this.JZ = false;
                al(3);
                if (!gL() || !GooglePlayServicesUtil.e(this.mContext, this.JV.getErrorCode())) {
                    gM();
                    this.JS.b(this.JV);
                }
                this.Ki = false;
                return;
            }
            this.JX = 2;
            gM();
            this.JR.signalAll();
            gK();
            if (!this.JZ) {
                this.JS.f(this.Kf.isEmpty() ? null : this.Kf);
            } else {
                this.JZ = false;
                al(-1);
            }
        }
    }

    private void gK() {
        this.zO.lock();
        try {
            jx.a(isConnected() || gL(), "GoogleApiClient is not connected yet.");
            while (!this.JU.isEmpty()) {
                try {
                    a(this.JU.remove());
                } catch (DeadObjectException e) {
                    Log.w("GoogleApiClientImpl", "Service died while flushing queue", e);
                }
            }
            this.zO.unlock();
        } catch (Throwable th) {
            this.zO.unlock();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gM() {
        this.zO.lock();
        try {
            if (this.JY) {
                this.JY = false;
                this.Kd.removeMessages(2);
                this.Kd.removeMessages(1);
                this.mContext.unregisterReceiver(this.Ke);
            }
        } finally {
            this.zO.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resume() {
        this.zO.lock();
        try {
            if (gL()) {
                connect();
            }
        } finally {
            this.zO.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public <C extends Api.a> C a(Api.c<C> cVar) {
        C c = (C) this.Kg.get(cVar);
        jx.b(c, "Appropriate Api was not requested.");
        return c;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T t) {
        this.zO.lock();
        try {
            if (isConnected()) {
                b(t);
            } else {
                this.JU.add(t);
            }
            return t;
        } finally {
            this.zO.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public boolean a(Scope scope) {
        return this.Kh.contains(scope.gO());
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(T t) {
        jx.a(isConnected() || gL(), "GoogleApiClient is not connected yet.");
        gK();
        try {
            a((d) t);
        } catch (DeadObjectException e) {
            al(1);
        }
        return t;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        jx.a(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zO.lock();
        try {
            connect();
            while (isConnecting()) {
                try {
                    this.JR.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    connectionResult = new ConnectionResult(15, null);
                    this.zO.unlock();
                }
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.Iu;
                this.zO.unlock();
            } else if (this.JV != null) {
                connectionResult = this.JV;
                this.zO.unlock();
            } else {
                connectionResult = new ConnectionResult(13, null);
                this.zO.unlock();
            }
            return connectionResult;
        } catch (Throwable th) {
            this.zO.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public ConnectionResult blockingConnect(long timeout, TimeUnit unit) {
        ConnectionResult connectionResult;
        jx.a(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zO.lock();
        try {
            connect();
            long nanos = unit.toNanos(timeout);
            while (isConnecting()) {
                try {
                    nanos = this.JR.awaitNanos(nanos);
                    if (nanos <= 0) {
                        connectionResult = new ConnectionResult(14, null);
                        this.zO.unlock();
                        return connectionResult;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    connectionResult = new ConnectionResult(15, null);
                    this.zO.unlock();
                }
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.Iu;
                this.zO.unlock();
            } else if (this.JV != null) {
                connectionResult = this.JV;
                this.zO.unlock();
            } else {
                connectionResult = new ConnectionResult(13, null);
                this.zO.unlock();
            }
            return connectionResult;
        } catch (Throwable th) {
            this.zO.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        jx.a(isConnected(), "GoogleApiClient is not connected yet.");
        final f fVar = new f(this.JF);
        if (this.Kg.containsKey(kc.DQ)) {
            a(this, fVar, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() { // from class: com.google.android.gms.common.api.c.5
                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                public void onConnected(Bundle connectionHint) {
                    c.this.a((GoogleApiClient) atomicReference.get(), fVar, true);
                }

                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                public void onConnectionSuspended(int cause) {
                }
            };
            GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(this.mContext).addApi(kc.API).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() { // from class: com.google.android.gms.common.api.c.6
                @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
                public void onConnectionFailed(ConnectionResult result) {
                    fVar.b(new Status(8));
                }
            }).setHandler(this.Kd).build();
            atomicReference.set(googleApiClientBuild);
            googleApiClientBuild.connect();
        }
        return fVar;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void connect() {
        this.zO.lock();
        try {
            this.JZ = false;
            if (isConnected() || isConnecting()) {
                this.zO.unlock();
                return;
            }
            this.Ki = true;
            this.JV = null;
            this.JX = 1;
            this.Kf.clear();
            this.Ka = this.Kg.size();
            Iterator<Api.a> it = this.Kg.values().iterator();
            while (it.hasNext()) {
                it.next().connect();
            }
            this.zO.unlock();
        } catch (Throwable th) {
            this.zO.unlock();
            throw th;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public <L> com.google.android.gms.common.api.d<L> d(L l) {
        jx.b(l, "Listener must not be null");
        this.zO.lock();
        try {
            com.google.android.gms.common.api.d<L> dVar = new com.google.android.gms.common.api.d<>(this.JF, l);
            this.Kj.add(dVar);
            return dVar;
        } finally {
            this.zO.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void disconnect() {
        gM();
        al(-1);
    }

    boolean gL() {
        return this.JY;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public Looper getLooper() {
        return this.JF;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public boolean isConnected() {
        return this.JX == 2;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public boolean isConnecting() {
        return this.JX == 1;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks listener) {
        return this.JS.isConnectionCallbacksRegistered(listener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener listener) {
        return this.JS.isConnectionFailedListenerRegistered(listener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void reconnect() {
        disconnect();
        connect();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.JS.registerConnectionCallbacks(listener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.JS.registerConnectionFailedListener(listener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void stopAutoManage(FragmentActivity lifecycleActivity) {
        jx.a(this.JT >= 0, "Called stopAutoManage but automatic lifecycle management is not enabled.");
        g.a(lifecycleActivity).ao(this.JT);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.JS.unregisterConnectionCallbacks(listener);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient
    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.JS.unregisterConnectionFailedListener(listener);
    }
}
