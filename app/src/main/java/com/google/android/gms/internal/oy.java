package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Account;
import com.google.android.gms.plus.Plus;

/* JADX INFO: loaded from: classes.dex */
public final class oy implements Account {

    private static abstract class a extends Plus.a<Status> {
        private a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static com.google.android.gms.plus.internal.e a(GoogleApiClient googleApiClient, Api.c<com.google.android.gms.plus.internal.e> cVar) {
        jx.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        jx.a(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        com.google.android.gms.plus.internal.e eVar = (com.google.android.gms.plus.internal.e) googleApiClient.a(cVar);
        jx.a(eVar != null, "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return eVar;
    }

    @Override // com.google.android.gms.plus.Account
    public void clearDefaultAccount(GoogleApiClient googleApiClient) {
        a(googleApiClient, Plus.DQ).clearDefaultAccount();
    }

    @Override // com.google.android.gms.plus.Account
    public String getAccountName(GoogleApiClient googleApiClient) {
        return a(googleApiClient, Plus.DQ).getAccountName();
    }

    @Override // com.google.android.gms.plus.Account
    public PendingResult<Status> revokeAccessAndDisconnect(GoogleApiClient googleApiClient) {
        return googleApiClient.b(new a(googleApiClient) { // from class: com.google.android.gms.internal.oy.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(com.google.android.gms.plus.internal.e eVar) {
                eVar.l(this);
            }
        });
    }
}
