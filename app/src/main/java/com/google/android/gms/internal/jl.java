package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class jl<T extends IInterface> implements Api.a, jm.b {
    public static final String[] MP = {"service_esmobile", "service_googleme"};
    private final Looper JF;
    private final jm JS;
    private T MJ;
    private final ArrayList<jl<T>.b<?>> MK;
    private jl<T>.f ML;
    private int MM;
    private final String[] MN;
    boolean MO;
    private final Context mContext;
    private final Object mH;
    final Handler mHandler;

    final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1 && !jl.this.isConnecting()) {
                b bVar = (b) msg.obj;
                bVar.hx();
                bVar.unregister();
                return;
            }
            if (msg.what == 3) {
                jl.this.JS.b(new ConnectionResult(((Integer) msg.obj).intValue(), null));
                return;
            }
            if (msg.what == 4) {
                jl.this.a(4, (IInterface) null);
                jl.this.JS.aE(((Integer) msg.obj).intValue());
                jl.this.a(4, 1, (IInterface) null);
            } else if (msg.what == 2 && !jl.this.isConnected()) {
                b bVar2 = (b) msg.obj;
                bVar2.hx();
                bVar2.unregister();
            } else if (msg.what == 2 || msg.what == 1) {
                ((b) msg.obj).hy();
            } else {
                Log.wtf("GmsClient", "Don't know how to handle this message.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract class b<TListener> {
        private boolean MR = false;
        private TListener mListener;

        public b(TListener tlistener) {
            this.mListener = tlistener;
        }

        protected abstract void g(TListener tlistener);

        protected abstract void hx();

        public void hy() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.mListener;
                if (this.MR) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if (tlistener != null) {
                try {
                    g(tlistener);
                } catch (RuntimeException e) {
                    hx();
                    throw e;
                }
            } else {
                hx();
            }
            synchronized (this) {
                this.MR = true;
            }
            unregister();
        }

        public void hz() {
            synchronized (this) {
                this.mListener = null;
            }
        }

        public void unregister() {
            hz();
            synchronized (jl.this.MK) {
                jl.this.MK.remove(this);
            }
        }
    }

    public static final class c implements GoogleApiClient.ConnectionCallbacks {
        private final GooglePlayServicesClient.ConnectionCallbacks MS;

        public c(GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks) {
            this.MS = connectionCallbacks;
        }

        public boolean equals(Object other) {
            return other instanceof c ? this.MS.equals(((c) other).MS) : this.MS.equals(other);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle connectionHint) {
            this.MS.onConnected(connectionHint);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int cause) {
            this.MS.onDisconnected();
        }
    }

    public abstract class d<TListener> extends jl<T>.b<TListener> {
        private final DataHolder JG;

        public d(TListener tlistener, DataHolder dataHolder) {
            super(tlistener);
            this.JG = dataHolder;
        }

        protected abstract void b(TListener tlistener, DataHolder dataHolder);

        @Override // com.google.android.gms.internal.jl.b
        protected final void g(TListener tlistener) {
            b(tlistener, this.JG);
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
            if (this.JG != null) {
                this.JG.close();
            }
        }

        @Override // com.google.android.gms.internal.jl.b
        public /* bridge */ /* synthetic */ void hy() {
            super.hy();
        }

        @Override // com.google.android.gms.internal.jl.b
        public /* bridge */ /* synthetic */ void hz() {
            super.hz();
        }

        @Override // com.google.android.gms.internal.jl.b
        public /* bridge */ /* synthetic */ void unregister() {
            super.unregister();
        }
    }

    public static final class e extends js.a {
        private jl MT;

        public e(jl jlVar) {
            this.MT = jlVar;
        }

        @Override // com.google.android.gms.internal.js
        public void b(int i, IBinder iBinder, Bundle bundle) {
            jx.b("onPostInitComplete can be called only once per call to getServiceFromBroker", (Object) this.MT);
            this.MT.a(i, iBinder, bundle);
            this.MT = null;
        }
    }

    public final class f implements ServiceConnection {
        public f() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName component, IBinder binder) {
            jl.this.N(binder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName component) {
            jl.this.mHandler.sendMessage(jl.this.mHandler.obtainMessage(4, 1));
        }
    }

    public static final class g implements GoogleApiClient.OnConnectionFailedListener {
        private final GooglePlayServicesClient.OnConnectionFailedListener MU;

        public g(GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.MU = onConnectionFailedListener;
        }

        public boolean equals(Object other) {
            return other instanceof g ? this.MU.equals(((g) other).MU) : this.MU.equals(other);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult result) {
            this.MU.onConnectionFailed(result);
        }
    }

    protected final class h extends jl<T>.b<Boolean> {
        public final Bundle MV;
        public final IBinder MW;
        public final int statusCode;

        public h(int i, IBinder iBinder, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.MW = iBinder;
            this.MV = bundle;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void g(Boolean bool) {
            IInterface iInterfaceL;
            if (bool == null) {
                jl.this.a(1, (IInterface) null);
                return;
            }
            switch (this.statusCode) {
                case 0:
                    try {
                        if (jl.this.bL().equals(this.MW.getInterfaceDescriptor()) && (iInterfaceL = jl.this.l(this.MW)) != null) {
                            jl.this.a(3, iInterfaceL);
                            jl.this.JS.dU();
                            return;
                        }
                    } catch (RemoteException e) {
                    }
                    jn.J(jl.this.mContext).b(jl.this.bK(), jl.this.ML);
                    jl.this.ML = null;
                    jl.this.a(1, (IInterface) null);
                    jl.this.JS.b(new ConnectionResult(8, null));
                    return;
                case 10:
                    jl.this.a(1, (IInterface) null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                default:
                    PendingIntent pendingIntent = this.MV != null ? (PendingIntent) this.MV.getParcelable("pendingIntent") : null;
                    if (jl.this.ML != null) {
                        jn.J(jl.this.mContext).b(jl.this.bK(), jl.this.ML);
                        jl.this.ML = null;
                    }
                    jl.this.a(1, (IInterface) null);
                    jl.this.JS.b(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
            }
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
        }
    }

    protected jl(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this.mH = new Object();
        this.MK = new ArrayList<>();
        this.MM = 1;
        this.MO = false;
        this.mContext = (Context) jx.i(context);
        this.JF = (Looper) jx.b(looper, "Looper must not be null");
        this.JS = new jm(context, looper, this);
        this.mHandler = new a(looper);
        c(strArr);
        this.MN = strArr;
        registerConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) jx.i(connectionCallbacks));
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) jx.i(onConnectionFailedListener));
    }

    @Deprecated
    protected jl(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, String... strArr) {
        this(context, context.getMainLooper(), new c(connectionCallbacks), new g(onConnectionFailedListener), strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, T t) {
        jx.L((i == 3) == (t != null));
        synchronized (this.mH) {
            this.MM = i;
            this.MJ = t;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, int i2, T t) {
        boolean z;
        synchronized (this.mH) {
            if (this.MM != i) {
                z = false;
            } else {
                a(i2, t);
                z = true;
            }
        }
        return z;
    }

    protected final void N(IBinder iBinder) {
        try {
            a(jt.a.Q(iBinder), new e(this));
        } catch (DeadObjectException e2) {
            Log.w("GmsClient", "service died");
            aD(1);
        } catch (RemoteException e3) {
            Log.w("GmsClient", "Remote exception occurred", e3);
        }
    }

    protected void a(int i, IBinder iBinder, Bundle bundle) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, new h(i, iBinder, bundle)));
    }

    @Deprecated
    public final void a(jl<T>.b<?> bVar) {
        synchronized (this.MK) {
            this.MK.add(bVar);
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2, bVar));
    }

    protected abstract void a(jt jtVar, e eVar) throws RemoteException;

    public void aD(int i) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, Integer.valueOf(i)));
    }

    protected abstract String bK();

    protected abstract String bL();

    protected void c(String... strArr) {
    }

    @Override // com.google.android.gms.common.api.Api.a
    public void connect() {
        this.MO = true;
        a(2, (IInterface) null);
        int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mContext);
        if (iIsGooglePlayServicesAvailable != 0) {
            a(1, (IInterface) null);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, Integer.valueOf(iIsGooglePlayServicesAvailable)));
            return;
        }
        if (this.ML != null) {
            Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + bK());
            jn.J(this.mContext).b(bK(), this.ML);
        }
        this.ML = new f();
        if (jn.J(this.mContext).a(bK(), this.ML)) {
            return;
        }
        Log.e("GmsClient", "unable to connect to service: " + bK());
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, 9));
    }

    protected final void dS() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @Override // com.google.android.gms.common.api.Api.a
    public void disconnect() {
        this.MO = false;
        synchronized (this.MK) {
            int size = this.MK.size();
            for (int i = 0; i < size; i++) {
                this.MK.get(i).hz();
            }
            this.MK.clear();
        }
        a(1, (IInterface) null);
        if (this.ML != null) {
            jn.J(this.mContext).b(bK(), this.ML);
            this.ML = null;
        }
    }

    public Bundle fX() {
        return null;
    }

    @Override // com.google.android.gms.internal.jm.b
    public boolean gN() {
        return this.MO;
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.JF;
    }

    public final String[] hv() {
        return this.MN;
    }

    public final T hw() throws DeadObjectException {
        T t;
        synchronized (this.mH) {
            if (this.MM == 4) {
                throw new DeadObjectException();
            }
            dS();
            jx.a(this.MJ != null, "Client is connected but service is null");
            t = this.MJ;
        }
        return t;
    }

    @Override // com.google.android.gms.common.api.Api.a, com.google.android.gms.internal.jm.b
    public boolean isConnected() {
        boolean z;
        synchronized (this.mH) {
            z = this.MM == 3;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mH) {
            z = this.MM == 2;
        }
        return z;
    }

    @Deprecated
    public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks listener) {
        return this.JS.isConnectionCallbacksRegistered(new c(listener));
    }

    @Deprecated
    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        return this.JS.isConnectionFailedListenerRegistered(listener);
    }

    protected abstract T l(IBinder iBinder);

    @Deprecated
    public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.JS.registerConnectionCallbacks(new c(listener));
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks listener) {
        this.JS.registerConnectionCallbacks(listener);
    }

    @Deprecated
    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.JS.registerConnectionFailedListener(listener);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener listener) {
        this.JS.registerConnectionFailedListener(listener);
    }

    @Deprecated
    public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.JS.unregisterConnectionCallbacks(new c(listener));
    }

    @Deprecated
    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.JS.unregisterConnectionFailedListener(listener);
    }
}
