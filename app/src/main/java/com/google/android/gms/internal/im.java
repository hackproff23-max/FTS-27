package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class im extends jl<iq> {
    private final Cast.Listener FD;
    private double Gp;
    private boolean Gq;
    private BaseImplementation.b<Status> HA;
    private ApplicationMetadata Hi;
    private final CastDevice Hj;
    private final Map<String, Cast.MessageReceivedCallback> Hk;
    private final long Hl;
    private c Hm;
    private String Hn;
    private boolean Ho;
    private boolean Hp;
    private boolean Hq;
    private int Hr;
    private int Hs;
    private final AtomicLong Ht;
    private String Hu;
    private String Hv;
    private Bundle Hw;
    private final Map<Long, BaseImplementation.b<Status>> Hx;
    private final b Hy;
    private BaseImplementation.b<Cast.ApplicationConnectionResult> Hz;
    private final Handler mHandler;
    private static final is Hh = new is("CastClientImpl");
    private static final Object HB = new Object();
    private static final Object HC = new Object();

    private static final class a implements Cast.ApplicationConnectionResult {
        private final Status Eb;
        private final ApplicationMetadata HD;
        private final String HE;
        private final boolean HF;
        private final String vZ;

        public a(Status status) {
            this(status, null, null, null, false);
        }

        public a(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
            this.Eb = status;
            this.HD = applicationMetadata;
            this.HE = str;
            this.vZ = str2;
            this.HF = z;
        }

        @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
        public ApplicationMetadata getApplicationMetadata() {
            return this.HD;
        }

        @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
        public String getApplicationStatus() {
            return this.HE;
        }

        @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
        public String getSessionId() {
            return this.vZ;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
        public boolean getWasLaunched() {
            return this.HF;
        }
    }

    private class b implements GoogleApiClient.OnConnectionFailedListener {
        private b() {
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult result) {
            im.this.ga();
        }
    }

    private class c extends ir.a {
        private final AtomicBoolean HH;

        private c() {
            this.HH = new AtomicBoolean(false);
        }

        private boolean ah(int i) {
            synchronized (im.HC) {
                if (im.this.HA == null) {
                    return false;
                }
                im.this.HA.b(new Status(i));
                im.this.HA = null;
                return true;
            }
        }

        private void c(long j, int i) {
            BaseImplementation.b bVar;
            synchronized (im.this.Hx) {
                bVar = (BaseImplementation.b) im.this.Hx.remove(Long.valueOf(j));
            }
            if (bVar != null) {
                bVar.b(new Status(i));
            }
        }

        @Override // com.google.android.gms.internal.ir
        public void a(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
            if (this.HH.get()) {
                return;
            }
            im.this.Hi = applicationMetadata;
            im.this.Hu = applicationMetadata.getApplicationId();
            im.this.Hv = str2;
            synchronized (im.HB) {
                if (im.this.Hz != null) {
                    im.this.Hz.b(new a(new Status(0), applicationMetadata, str, str2, z));
                    im.this.Hz = null;
                }
            }
        }

        @Override // com.google.android.gms.internal.ir
        public void a(String str, double d, boolean z) {
            im.Hh.b("Deprecated callback: \"onStatusreceived\"", new Object[0]);
        }

        @Override // com.google.android.gms.internal.ir
        public void a(String str, long j) {
            if (this.HH.get()) {
                return;
            }
            c(j, 0);
        }

        @Override // com.google.android.gms.internal.ir
        public void a(String str, long j, int i) {
            if (this.HH.get()) {
                return;
            }
            c(j, i);
        }

        @Override // com.google.android.gms.internal.ir
        public void ad(int i) {
            if (gg()) {
                im.Hh.b("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
                if (i != 0) {
                    im.this.aD(2);
                }
            }
        }

        @Override // com.google.android.gms.internal.ir
        public void ae(int i) {
            if (this.HH.get()) {
                return;
            }
            synchronized (im.HB) {
                if (im.this.Hz != null) {
                    im.this.Hz.b(new a(new Status(i)));
                    im.this.Hz = null;
                }
            }
        }

        @Override // com.google.android.gms.internal.ir
        public void af(int i) {
            if (this.HH.get()) {
                return;
            }
            ah(i);
        }

        @Override // com.google.android.gms.internal.ir
        public void ag(int i) {
            if (this.HH.get()) {
                return;
            }
            ah(i);
        }

        @Override // com.google.android.gms.internal.ir
        public void b(final ij ijVar) {
            if (this.HH.get()) {
                return;
            }
            im.Hh.b("onApplicationStatusChanged", new Object[0]);
            im.this.mHandler.post(new Runnable() { // from class: com.google.android.gms.internal.im.c.3
                @Override // java.lang.Runnable
                public void run() {
                    im.this.a(ijVar);
                }
            });
        }

        @Override // com.google.android.gms.internal.ir
        public void b(final io ioVar) {
            if (this.HH.get()) {
                return;
            }
            im.Hh.b("onDeviceStatusChanged", new Object[0]);
            im.this.mHandler.post(new Runnable() { // from class: com.google.android.gms.internal.im.c.2
                @Override // java.lang.Runnable
                public void run() {
                    im.this.a(ioVar);
                }
            });
        }

        @Override // com.google.android.gms.internal.ir
        public void b(String str, byte[] bArr) {
            if (this.HH.get()) {
                return;
            }
            im.Hh.b("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
        }

        public boolean gg() {
            if (this.HH.getAndSet(true)) {
                return false;
            }
            im.this.fW();
            return true;
        }

        public boolean gh() {
            return this.HH.get();
        }

        @Override // com.google.android.gms.internal.ir
        public void j(final String str, final String str2) {
            if (this.HH.get()) {
                return;
            }
            im.Hh.b("Receive (type=text, ns=%s) %s", str, str2);
            im.this.mHandler.post(new Runnable() { // from class: com.google.android.gms.internal.im.c.4
                @Override // java.lang.Runnable
                public void run() {
                    Cast.MessageReceivedCallback messageReceivedCallback;
                    synchronized (im.this.Hk) {
                        messageReceivedCallback = (Cast.MessageReceivedCallback) im.this.Hk.get(str);
                    }
                    if (messageReceivedCallback != null) {
                        messageReceivedCallback.onMessageReceived(im.this.Hj, str, str2);
                    } else {
                        im.Hh.b("Discarded message for unknown namespace '%s'", str);
                    }
                }
            });
        }

        @Override // com.google.android.gms.internal.ir
        public void onApplicationDisconnected(final int statusCode) {
            if (this.HH.get()) {
                return;
            }
            im.this.Hu = null;
            im.this.Hv = null;
            ah(statusCode);
            if (im.this.FD != null) {
                im.this.mHandler.post(new Runnable() { // from class: com.google.android.gms.internal.im.c.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (im.this.FD != null) {
                            im.this.FD.onApplicationDisconnected(statusCode);
                        }
                    }
                });
            }
        }
    }

    public im(Context context, Looper looper, CastDevice castDevice, long j, Cast.Listener listener, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, (String[]) null);
        this.Hj = castDevice;
        this.FD = listener;
        this.Hl = j;
        this.mHandler = new Handler(looper);
        this.Hk = new HashMap();
        this.Ht = new AtomicLong(0L);
        this.Hx = new HashMap();
        fW();
        this.Hy = new b();
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this.Hy);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ij ijVar) {
        boolean z;
        String strFT = ijVar.fT();
        if (in.a(strFT, this.Hn)) {
            z = false;
        } else {
            this.Hn = strFT;
            z = true;
        }
        Hh.b("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.Ho));
        if (this.FD != null && (z || this.Ho)) {
            this.FD.onApplicationStatusChanged();
        }
        this.Ho = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(io ioVar) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadata = ioVar.getApplicationMetadata();
        if (!in.a(applicationMetadata, this.Hi)) {
            this.Hi = applicationMetadata;
            this.FD.onApplicationMetadataChanged(this.Hi);
        }
        double dFZ = ioVar.fZ();
        if (dFZ == Double.NaN || dFZ == this.Gp) {
            z = false;
        } else {
            this.Gp = dFZ;
            z = true;
        }
        boolean zGi = ioVar.gi();
        if (zGi != this.Gq) {
            this.Gq = zGi;
            z = true;
        }
        Hh.b("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.Hp));
        if (this.FD != null && (z || this.Hp)) {
            this.FD.onVolumeChanged();
        }
        int iGj = ioVar.gj();
        if (iGj != this.Hr) {
            this.Hr = iGj;
            z2 = true;
        } else {
            z2 = false;
        }
        Hh.b("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.Hp));
        if (this.FD != null && (z2 || this.Hp)) {
            this.FD.X(this.Hr);
        }
        int iGk = ioVar.gk();
        if (iGk != this.Hs) {
            this.Hs = iGk;
            z3 = true;
        } else {
            z3 = false;
        }
        Hh.b("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(this.Hp));
        if (this.FD != null && (z3 || this.Hp)) {
            this.FD.Y(this.Hs);
        }
        this.Hp = false;
    }

    private void c(BaseImplementation.b<Cast.ApplicationConnectionResult> bVar) {
        synchronized (HB) {
            if (this.Hz != null) {
                this.Hz.b(new a(new Status(2002)));
            }
            this.Hz = bVar;
        }
    }

    private void e(BaseImplementation.b<Status> bVar) {
        synchronized (HC) {
            if (this.HA != null) {
                bVar.b(new Status(2001));
            } else {
                this.HA = bVar;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fW() {
        this.Hq = false;
        this.Hr = -1;
        this.Hs = -1;
        this.Hi = null;
        this.Hn = null;
        this.Gp = 0.0d;
        this.Gq = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ga() {
        Hh.b("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.Hk) {
            this.Hk.clear();
        }
    }

    private void gb() throws IllegalStateException {
        if (!this.Hq || this.Hm == null || this.Hm.gh()) {
            throw new IllegalStateException("Not connected to a device");
        }
    }

    public void I(boolean z) throws IllegalStateException, RemoteException {
        hw().a(z, this.Gp, this.Gq);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: L, reason: merged with bridge method [inline-methods] */
    public iq l(IBinder iBinder) {
        return iq.a.M(iBinder);
    }

    public void a(double d) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        hw().a(d, this.Gp, this.Gq);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(int i, IBinder iBinder, Bundle bundle) {
        Hh.b("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.Hq = true;
            this.Ho = true;
            this.Hp = true;
        } else {
            this.Hq = false;
        }
        if (i == 1001) {
            this.Hw = new Bundle();
            this.Hw.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.a(i, iBinder, bundle);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        Hh.b("getServiceFromBroker(): mLastApplicationId=%s, mLastSessionId=%s", this.Hu, this.Hv);
        this.Hj.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.Hl);
        if (this.Hu != null) {
            bundle.putString("last_application_id", this.Hu);
            if (this.Hv != null) {
                bundle.putString("last_session_id", this.Hv);
            }
        }
        this.Hm = new c();
        jtVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.Hm.asBinder(), bundle);
    }

    public void a(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, RemoteException, IllegalArgumentException {
        in.aF(str);
        aE(str);
        if (messageReceivedCallback != null) {
            synchronized (this.Hk) {
                this.Hk.put(str, messageReceivedCallback);
            }
            hw().aI(str);
        }
    }

    public void a(String str, LaunchOptions launchOptions, BaseImplementation.b<Cast.ApplicationConnectionResult> bVar) throws IllegalStateException, RemoteException {
        c(bVar);
        hw().a(str, launchOptions);
    }

    public void a(String str, BaseImplementation.b<Status> bVar) throws IllegalStateException, RemoteException {
        e(bVar);
        hw().aH(str);
    }

    public void a(String str, String str2, BaseImplementation.b<Status> bVar) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() > 65536) {
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        in.aF(str);
        gb();
        long jIncrementAndGet = this.Ht.incrementAndGet();
        try {
            this.Hx.put(Long.valueOf(jIncrementAndGet), bVar);
            hw().a(str, str2, jIncrementAndGet);
        } catch (Throwable th) {
            this.Hx.remove(Long.valueOf(jIncrementAndGet));
            throw th;
        }
    }

    public void a(String str, boolean z, BaseImplementation.b<Cast.ApplicationConnectionResult> bVar) throws IllegalStateException, RemoteException {
        c(bVar);
        hw().g(str, z);
    }

    public void aE(String str) throws RemoteException, IllegalArgumentException {
        Cast.MessageReceivedCallback messageReceivedCallbackRemove;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.Hk) {
            messageReceivedCallbackRemove = this.Hk.remove(str);
        }
        if (messageReceivedCallbackRemove != null) {
            try {
                hw().aJ(str);
            } catch (IllegalStateException e) {
                Hh.a(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
            }
        }
    }

    public void b(String str, String str2, BaseImplementation.b<Cast.ApplicationConnectionResult> bVar) throws IllegalStateException, RemoteException {
        c(bVar);
        hw().k(str, str2);
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    public void d(BaseImplementation.b<Status> bVar) throws IllegalStateException, RemoteException {
        e(bVar);
        hw().gl();
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void disconnect() {
        Hh.b("disconnect(); ServiceListener=%s, isConnected=%b", this.Hm, Boolean.valueOf(isConnected()));
        c cVar = this.Hm;
        this.Hm = null;
        if (cVar == null || !cVar.gg()) {
            Hh.b("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        ga();
        try {
            if (isConnected() || isConnecting()) {
                hw().disconnect();
            }
        } catch (RemoteException e) {
            Hh.a(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.internal.jm.b
    public Bundle fX() {
        if (this.Hw == null) {
            return super.fX();
        }
        Bundle bundle = this.Hw;
        this.Hw = null;
        return bundle;
    }

    public void fY() throws IllegalStateException, RemoteException {
        hw().fY();
    }

    public double fZ() throws IllegalStateException {
        gb();
        return this.Gp;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        gb();
        return this.Hi;
    }

    public String getApplicationStatus() throws IllegalStateException {
        gb();
        return this.Hn;
    }

    public boolean isMute() throws IllegalStateException {
        gb();
        return this.Gq;
    }
}
