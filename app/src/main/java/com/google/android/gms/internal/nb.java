package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionApi;

/* JADX INFO: loaded from: classes.dex */
public class nb implements ActivityRecognitionApi {

    private static abstract class a extends ActivityRecognition.a<Status> {
        public a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    @Override // com.google.android.gms.location.ActivityRecognitionApi
    public PendingResult<Status> removeActivityUpdates(GoogleApiClient client, final PendingIntent callbackIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nb.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(callbackIntent);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.location.ActivityRecognitionApi
    public PendingResult<Status> requestActivityUpdates(GoogleApiClient client, final long detectionIntervalMillis, final PendingIntent callbackIntent) {
        return client.b(new a(client) { // from class: com.google.android.gms.internal.nb.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(nk nkVar) throws RemoteException {
                nkVar.a(detectionIntervalMillis, callbackIntent);
                b(Status.Kw);
            }
        });
    }
}
