package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/* JADX INFO: loaded from: classes.dex */
public class nf implements FusedLocationProviderApi {

    private static abstract class a extends LocationServices.a<Status> {
        public a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public Location getLastLocation(GoogleApiClient client) {
        try {
            return LocationServices.f(client).nl();
        } catch (Exception e) {
            return null;
        }
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, final PendingIntent callbackIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.c(callbackIntent);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> removeLocationUpdates(GoogleApiClient client, final LocationListener listener) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(listener);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final PendingIntent callbackIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.b(request, callbackIntent);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final LocationListener listener) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(request, listener, (Looper) null);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> requestLocationUpdates(GoogleApiClient client, final LocationRequest request, final LocationListener listener, final Looper looper) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(request, listener, looper);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> setMockLocation(GoogleApiClient client, final Location mockLocation) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.b(mockLocation);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.FusedLocationProviderApi
    public PendingResult<Status> setMockMode(GoogleApiClient client, final boolean isMockMode) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nf.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.S(isMockMode);
                b(Status.Kw);
            }
        });
    }
}
