package com.google.android.gms.appstate;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ib;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jx;
import com.mopub.nativeads.MoPubNativeAdPositioning;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class AppStateManager {
    static final Api.c<ib> DQ = new Api.c<>();
    private static final Api.b<ib, Api.ApiOptions.NoOptions> DR = new Api.b<ib, Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.appstate.AppStateManager.1
        @Override // com.google.android.gms.common.api.Api.b
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ib a(Context context, Looper looper, jg jgVar, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new ib(context, looper, connectionCallbacks, onConnectionFailedListener, jgVar.hm(), (String[]) jgVar.ho().toArray(new String[0]));
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Scope SCOPE_APP_STATE = new Scope(Scopes.APP_STATE);
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(DR, DQ, SCOPE_APP_STATE);

    public interface StateConflictResult extends Releasable, Result {
        byte[] getLocalData();

        String getResolvedVersion();

        byte[] getServerData();

        int getStateKey();
    }

    public interface StateDeletedResult extends Result {
        int getStateKey();
    }

    public interface StateListResult extends Result {
        AppStateBuffer getStateBuffer();
    }

    public interface StateLoadedResult extends Releasable, Result {
        byte[] getLocalData();

        int getStateKey();
    }

    public interface StateResult extends Releasable, Result {
        StateConflictResult getConflictResult();

        StateLoadedResult getLoadedResult();
    }

    public static abstract class a<R extends Result> extends BaseImplementation.a<R, ib> {
        public a(GoogleApiClient googleApiClient) {
            super(AppStateManager.DQ, googleApiClient);
        }
    }

    private static abstract class b extends a<StateDeletedResult> {
        b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
    }

    private static abstract class c extends a<StateListResult> {
        public c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public StateListResult c(final Status status) {
            return new StateListResult() { // from class: com.google.android.gms.appstate.AppStateManager.c.1
                @Override // com.google.android.gms.appstate.AppStateManager.StateListResult
                public AppStateBuffer getStateBuffer() {
                    return new AppStateBuffer(null);
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class d extends a<Status> {
        public d(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class e extends a<StateResult> {
        public e(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public StateResult c(Status status) {
            return AppStateManager.d(status);
        }
    }

    private AppStateManager() {
    }

    public static ib a(GoogleApiClient googleApiClient) {
        jx.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        jx.a(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        ib ibVar = (ib) googleApiClient.a(DQ);
        jx.a(ibVar != null, "GoogleApiClient is not configured to use the AppState API. Pass AppStateManager.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return ibVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static StateResult d(final Status status) {
        return new StateResult() { // from class: com.google.android.gms.appstate.AppStateManager.2
            @Override // com.google.android.gms.appstate.AppStateManager.StateResult
            public StateConflictResult getConflictResult() {
                return null;
            }

            @Override // com.google.android.gms.appstate.AppStateManager.StateResult
            public StateLoadedResult getLoadedResult() {
                return null;
            }

            @Override // com.google.android.gms.common.api.Result
            public Status getStatus() {
                return status;
            }

            @Override // com.google.android.gms.common.api.Releasable
            public void release() {
            }
        };
    }

    public static PendingResult<StateDeletedResult> delete(GoogleApiClient googleApiClient, final int stateKey) {
        return googleApiClient.b(new b(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.a(this, stateKey);
            }

            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public StateDeletedResult c(final Status status) {
                return new StateDeletedResult() { // from class: com.google.android.gms.appstate.AppStateManager.5.1
                    @Override // com.google.android.gms.appstate.AppStateManager.StateDeletedResult
                    public int getStateKey() {
                        return stateKey;
                    }

                    @Override // com.google.android.gms.common.api.Result
                    public Status getStatus() {
                        return status;
                    }
                };
            }
        });
    }

    public static int getMaxNumKeys(GoogleApiClient googleApiClient) {
        return a(googleApiClient).fM();
    }

    public static int getMaxStateSize(GoogleApiClient googleApiClient) {
        return a(googleApiClient).fL();
    }

    public static PendingResult<StateListResult> list(GoogleApiClient googleApiClient) {
        return googleApiClient.a(new c(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.a(this);
            }
        });
    }

    public static PendingResult<StateResult> load(GoogleApiClient googleApiClient, final int stateKey) {
        return googleApiClient.a(new e(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.b(this, stateKey);
            }
        });
    }

    public static PendingResult<StateResult> resolve(GoogleApiClient googleApiClient, final int stateKey, final String resolvedVersion, final byte[] resolvedData) {
        return googleApiClient.b(new e(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.a(this, stateKey, resolvedVersion, resolvedData);
            }
        });
    }

    public static PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return googleApiClient.b(new d(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.b(this);
            }
        });
    }

    public static void update(GoogleApiClient googleApiClient, final int stateKey, final byte[] data) {
        googleApiClient.b(new e(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.a((BaseImplementation.b<StateResult>) null, stateKey, data);
            }
        });
    }

    public static PendingResult<StateResult> updateImmediate(GoogleApiClient googleApiClient, final int stateKey, final byte[] data) {
        return googleApiClient.b(new e(googleApiClient) { // from class: com.google.android.gms.appstate.AppStateManager.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ib ibVar) {
                ibVar.a(this, stateKey, data);
            }
        });
    }
}
