package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class nk extends nc {
    private final nj agX;
    private final mz agY;
    private final ie agZ;

    private final class a extends jl<ni>.b<com.google.android.gms.location.c.a> {
        private final int Iv;
        private final String[] aha;

        public a(com.google.android.gms.location.c.a aVar, int i, String[] strArr) {
            super(aVar);
            this.Iv = LocationStatusCodes.eC(i);
            this.aha = strArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void g(com.google.android.gms.location.c.a aVar) {
            if (aVar != null) {
                aVar.a(this.Iv, this.aha);
            }
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
        }
    }

    private static final class b extends nh.a {
        private com.google.android.gms.location.c.a ahc;
        private com.google.android.gms.location.c.b ahd;
        private nk ahe;

        public b(com.google.android.gms.location.c.a aVar, nk nkVar) {
            this.ahc = aVar;
            this.ahd = null;
            this.ahe = nkVar;
        }

        public b(com.google.android.gms.location.c.b bVar, nk nkVar) {
            this.ahd = bVar;
            this.ahc = null;
            this.ahe = nkVar;
        }

        @Override // com.google.android.gms.internal.nh
        public void a(int i, PendingIntent pendingIntent) {
            if (this.ahe == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByPendingIntentResult called multiple times");
                return;
            }
            nk nkVar = this.ahe;
            nk nkVar2 = this.ahe;
            nkVar2.getClass();
            nkVar.a(nkVar2.new c(1, this.ahd, i, pendingIntent));
            this.ahe = null;
            this.ahc = null;
            this.ahd = null;
        }

        @Override // com.google.android.gms.internal.nh
        public void a(int i, String[] strArr) throws RemoteException {
            if (this.ahe == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            nk nkVar = this.ahe;
            nk nkVar2 = this.ahe;
            nkVar2.getClass();
            nkVar.a(nkVar2.new a(this.ahc, i, strArr));
            this.ahe = null;
            this.ahc = null;
            this.ahd = null;
        }

        @Override // com.google.android.gms.internal.nh
        public void b(int i, String[] strArr) {
            if (this.ahe == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesByRequestIdsResult called multiple times");
                return;
            }
            nk nkVar = this.ahe;
            nk nkVar2 = this.ahe;
            nkVar2.getClass();
            nkVar.a(nkVar2.new c(2, this.ahd, i, strArr));
            this.ahe = null;
            this.ahc = null;
            this.ahd = null;
        }
    }

    private final class c extends jl<ni>.b<com.google.android.gms.location.c.b> {
        private final int Iv;
        private final String[] aha;
        private final int ahf;
        private final PendingIntent mPendingIntent;

        public c(int i, com.google.android.gms.location.c.b bVar, int i2, PendingIntent pendingIntent) {
            super(bVar);
            je.K(i == 1);
            this.ahf = i;
            this.Iv = LocationStatusCodes.eC(i2);
            this.mPendingIntent = pendingIntent;
            this.aha = null;
        }

        public c(int i, com.google.android.gms.location.c.b bVar, int i2, String[] strArr) {
            super(bVar);
            je.K(i == 2);
            this.ahf = i;
            this.Iv = LocationStatusCodes.eC(i2);
            this.aha = strArr;
            this.mPendingIntent = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void g(com.google.android.gms.location.c.b bVar) {
            if (bVar != null) {
                switch (this.ahf) {
                    case 1:
                        bVar.a(this.Iv, this.mPendingIntent);
                        break;
                    case 2:
                        bVar.b(this.Iv, this.aha);
                        break;
                    default:
                        Log.wtf("LocationClientImpl", "Unsupported action: " + this.ahf);
                        break;
                }
            }
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
        }
    }

    public nk(Context context, Looper looper, String str, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str2) {
        this(context, looper, str, connectionCallbacks, onConnectionFailedListener, str2, null);
    }

    public nk(Context context, Looper looper, String str, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str2, String str3) {
        this(context, looper, str, connectionCallbacks, onConnectionFailedListener, str2, str3, null);
    }

    public nk(Context context, Looper looper, String str, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str2, String str3, String str4) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str2);
        this.agX = new nj(context, this.Ee);
        this.agY = mz.a(context, str3, str4, this.Ee);
        this.agZ = ie.a(context, this.Ee);
    }

    public void S(boolean z) throws RemoteException {
        this.agX.S(z);
    }

    public void a(long j, PendingIntent pendingIntent) throws RemoteException {
        dS();
        jx.i(pendingIntent);
        jx.b(j >= 0, "detectionIntervalMillis must be >= 0");
        hw().a(j, true, pendingIntent);
    }

    public void a(PendingIntent pendingIntent) throws RemoteException {
        dS();
        jx.i(pendingIntent);
        hw().a(pendingIntent);
    }

    public void a(PendingIntent pendingIntent, com.google.android.gms.location.c.b bVar) throws RemoteException {
        dS();
        jx.b(pendingIntent, "PendingIntent must be specified.");
        jx.b(bVar, "OnRemoveGeofencesResultListener not provided.");
        hw().a(pendingIntent, bVar == null ? null : new b(bVar, this), getContext().getPackageName());
    }

    public void a(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, com.google.android.gms.location.c.a aVar) throws RemoteException {
        dS();
        jx.b(geofencingRequest, "geofencingRequest can't be null.");
        jx.b(pendingIntent, "PendingIntent must be specified.");
        jx.b(aVar, "OnAddGeofencesResultListener not provided.");
        hw().a(geofencingRequest, pendingIntent, aVar == null ? null : new b(aVar, this));
    }

    public void a(LocationListener locationListener) throws RemoteException {
        this.agX.a(locationListener);
    }

    public void a(LocationRequest locationRequest, LocationListener locationListener, Looper looper) throws RemoteException {
        synchronized (this.agX) {
            this.agX.a(locationRequest, locationListener, looper);
        }
    }

    public void a(List<String> list, com.google.android.gms.location.c.b bVar) throws RemoteException {
        dS();
        jx.b(list != null && list.size() > 0, "geofenceRequestIds can't be null nor empty.");
        jx.b(bVar, "OnRemoveGeofencesResultListener not provided.");
        hw().a((String[]) list.toArray(new String[0]), bVar == null ? null : new b(bVar, this), getContext().getPackageName());
    }

    public void b(Location location) throws RemoteException {
        this.agX.b(location);
    }

    public void b(LocationRequest locationRequest, PendingIntent pendingIntent) throws RemoteException {
        this.agX.b(locationRequest, pendingIntent);
    }

    public void c(PendingIntent pendingIntent) throws RemoteException {
        this.agX.c(pendingIntent);
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void disconnect() {
        synchronized (this.agX) {
            if (isConnected()) {
                try {
                    this.agX.removeAllListeners();
                    this.agX.nm();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
                super.disconnect();
            } else {
                super.disconnect();
            }
            throw th;
        }
    }

    public Location nl() {
        return this.agX.nl();
    }
}
