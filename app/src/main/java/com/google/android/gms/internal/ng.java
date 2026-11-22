package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ng implements GeofencingApi {

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

    @Override // com.google.android.gms.location.GeofencingApi
    public PendingResult<Status> addGeofences(GoogleApiClient client, final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.ng.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(geofencingRequest, pendingIntent, new com.google.android.gms.location.c.a() { // from class: com.google.android.gms.internal.ng.1.1
                    @Override // com.google.android.gms.location.c.a
                    public void a(int i, String[] strArr) {
                        b(LocationStatusCodes.eD(i));
                    }
                });
            }
        });
    }

    @Override // com.google.android.gms.location.GeofencingApi
    @Deprecated
    public PendingResult<Status> addGeofences(GoogleApiClient client, List<Geofence> geofences, PendingIntent pendingIntent) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.addGeofences(geofences);
        builder.setInitialTrigger(5);
        return addGeofences(client, builder.build(), pendingIntent);
    }

    @Override // com.google.android.gms.location.GeofencingApi
    public PendingResult<Status> removeGeofences(GoogleApiClient client, final PendingIntent pendingIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.ng.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(pendingIntent, new com.google.android.gms.location.c.b() { // from class: com.google.android.gms.internal.ng.2.1
                    @Override // com.google.android.gms.location.c.b
                    public void a(int i, PendingIntent pendingIntent2) {
                        b(LocationStatusCodes.eD(i));
                    }

                    @Override // com.google.android.gms.location.c.b
                    public void b(int i, String[] strArr) {
                        Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
                    }
                });
            }
        });
    }

    @Override // com.google.android.gms.location.GeofencingApi
    public PendingResult<Status> removeGeofences(GoogleApiClient client, final List<String> geofenceRequestIds) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.ng.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(geofenceRequestIds, new com.google.android.gms.location.c.b() { // from class: com.google.android.gms.internal.ng.3.1
                    @Override // com.google.android.gms.location.c.b
                    public void a(int i, PendingIntent pendingIntent) {
                        Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
                    }

                    @Override // com.google.android.gms.location.c.b
                    public void b(int i, String[] strArr) {
                        b(LocationStatusCodes.eD(i));
                    }
                });
            }
        });
    }
}
