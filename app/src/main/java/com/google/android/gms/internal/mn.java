package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.SessionsApi;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.result.SessionStopResult;

/* JADX INFO: loaded from: classes.dex */
public class mn implements SessionsApi {

    private static class a extends mb.a {
        private final BaseImplementation.b<SessionReadResult> Ea;

        private a(BaseImplementation.b<SessionReadResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.mb
        public void a(SessionReadResult sessionReadResult) throws RemoteException {
            this.Ea.b(sessionReadResult);
        }
    }

    private static class b extends mc.a {
        private final BaseImplementation.b<SessionStopResult> Ea;

        private b(BaseImplementation.b<SessionStopResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.mc
        public void a(SessionStopResult sessionStopResult) {
            this.Ea.b(sessionStopResult);
        }
    }

    private PendingResult<SessionStopResult> a(GoogleApiClient googleApiClient, final String str, final String str2) {
        return googleApiClient.b(new lu.a<SessionStopResult>(googleApiClient) { // from class: com.google.android.gms.internal.mn.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
            public SessionStopResult c(Status status) {
                return SessionStopResult.H(status);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.y.a().bx(str).by(str2).kl(), new b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<Status> insertSession(GoogleApiClient client, final SessionInsertRequest request) {
        return client.a(new lu.c(client) { // from class: com.google.android.gms.internal.mn.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<SessionReadResult> readSession(GoogleApiClient client, final SessionReadRequest request) {
        return client.a(new lu.a<SessionReadResult>(client) { // from class: com.google.android.gms.internal.mn.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
            public SessionReadResult c(Status status) {
                return SessionReadResult.G(status);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new a(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<Status> registerForSessions(GoogleApiClient client, final PendingIntent intent) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mn.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                lu.b bVar = new lu.b(this);
                luVar.jM().a(new com.google.android.gms.fitness.request.u(intent), bVar, luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<Status> startSession(GoogleApiClient client, final Session session) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mn.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.w.a().b(session).kk(), new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<SessionStopResult> stopSession(GoogleApiClient client, String identifier) {
        return a(client, null, identifier);
    }

    @Override // com.google.android.gms.fitness.SessionsApi
    public PendingResult<Status> unregisterForSessions(GoogleApiClient client, final PendingIntent intent) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mn.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                lu.b bVar = new lu.b(this);
                luVar.jM().a(new com.google.android.gms.fitness.request.aa(intent), bVar, luVar.getContext().getPackageName());
            }
        });
    }
}
