package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

/* JADX INFO: loaded from: classes.dex */
public final class ah implements MessageApi {

    private static final class a extends d<Status> {
        private IntentFilter[] axD;
        private MessageApi.MessageListener axV;

        private a(GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) {
            super(googleApiClient);
            this.axV = messageListener;
            this.axD = intentFilterArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(ba baVar) throws RemoteException {
            baVar.a(this, this.axV, this.axD);
            this.axV = null;
            this.axD = null;
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            this.axV = null;
            this.axD = null;
            return status;
        }
    }

    public static class b implements MessageApi.SendMessageResult {
        private final Status Eb;
        private final int ve;

        public b(Status status, int i) {
            this.Eb = status;
            this.ve = i;
        }

        @Override // com.google.android.gms.wearable.MessageApi.SendMessageResult
        public int getRequestId() {
            return this.ve;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) {
        return googleApiClient.a(new a(googleApiClient, messageListener, intentFilterArr));
    }

    @Override // com.google.android.gms.wearable.MessageApi
    public PendingResult<Status> addListener(GoogleApiClient client, MessageApi.MessageListener listener) {
        return a(client, listener, null);
    }

    @Override // com.google.android.gms.wearable.MessageApi
    public PendingResult<Status> removeListener(GoogleApiClient client, final MessageApi.MessageListener listener) {
        return client.a(new d<Status>(client) { // from class: com.google.android.gms.wearable.internal.ah.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, listener);
            }

            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Status c(Status status) {
                return status;
            }
        });
    }

    @Override // com.google.android.gms.wearable.MessageApi
    public PendingResult<MessageApi.SendMessageResult> sendMessage(GoogleApiClient client, final String nodeId, final String action, final byte[] data) {
        return client.a(new d<MessageApi.SendMessageResult>(client) { // from class: com.google.android.gms.wearable.internal.ah.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, nodeId, action, data);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aI, reason: merged with bridge method [inline-methods] */
            public MessageApi.SendMessageResult c(Status status) {
                return new b(status, -1);
            }
        });
    }
}
