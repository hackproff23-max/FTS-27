package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jx;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public interface GoogleApiClient {

    public static final class Builder {
        private String DZ;
        private Looper JF;
        private final Set<String> JH;
        private int JI;
        private View JJ;
        private String JK;
        private final Map<Api<?>, Api.ApiOptions> JL;
        private FragmentActivity JM;
        private int JN;
        private OnConnectionFailedListener JO;
        private final Set<ConnectionCallbacks> JP;
        private final Set<OnConnectionFailedListener> JQ;
        private final Context mContext;

        public Builder(Context context) {
            this.JH = new HashSet();
            this.JL = new HashMap();
            this.JN = -1;
            this.JP = new HashSet();
            this.JQ = new HashSet();
            this.mContext = context;
            this.JF = context.getMainLooper();
            this.JK = context.getPackageName();
        }

        public Builder(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
            this(context);
            jx.b(connectedListener, "Must provide a connected listener");
            this.JP.add(connectedListener);
            jx.b(connectionFailedListener, "Must provide a connection failed listener");
            this.JQ.add(connectionFailedListener);
        }

        private GoogleApiClient gI() {
            g gVarA = g.a(this.JM);
            GoogleApiClient googleApiClientAn = gVarA.an(this.JN);
            if (googleApiClientAn == null) {
                googleApiClientAn = new c(this.mContext.getApplicationContext(), this.JF, gH(), this.JL, this.JP, this.JQ, this.JN);
            }
            gVarA.a(this.JN, googleApiClientAn, this.JO);
            return googleApiClientAn;
        }

        public Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            this.JL.put(api, null);
            List<Scope> listGy = api.gy();
            int size = listGy.size();
            for (int i = 0; i < size; i++) {
                this.JH.add(listGy.get(i).gO());
            }
            return this;
        }

        public <O extends Api.ApiOptions.HasOptions> Builder addApi(Api<O> api, O options) {
            jx.b(options, "Null options are not permitted for this Api");
            this.JL.put(api, options);
            List<Scope> listGy = api.gy();
            int size = listGy.size();
            for (int i = 0; i < size; i++) {
                this.JH.add(listGy.get(i).gO());
            }
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks listener) {
            this.JP.add(listener);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener listener) {
            this.JQ.add(listener);
            return this;
        }

        public Builder addScope(Scope scope) {
            this.JH.add(scope.gO());
            return this;
        }

        public GoogleApiClient build() {
            jx.b(!this.JL.isEmpty(), "must call addApi() to add at least one API");
            return this.JN >= 0 ? gI() : new c(this.mContext, this.JF, gH(), this.JL, this.JP, this.JQ, -1);
        }

        public Builder enableAutoManage(FragmentActivity fragmentActivity, int clientId, OnConnectionFailedListener unresolvedConnectionFailedListener) {
            jx.b(clientId >= 0, "clientId must be non-negative");
            this.JN = clientId;
            this.JM = (FragmentActivity) jx.b(fragmentActivity, "Null activity is not permitted.");
            this.JO = unresolvedConnectionFailedListener;
            return this;
        }

        public jg gH() {
            return new jg(this.DZ, this.JH, this.JI, this.JJ, this.JK);
        }

        public Builder setAccountName(String accountName) {
            this.DZ = accountName;
            return this;
        }

        public Builder setGravityForPopups(int gravityForPopups) {
            this.JI = gravityForPopups;
            return this;
        }

        public Builder setHandler(Handler handler) {
            jx.b(handler, "Handler must not be null");
            this.JF = handler.getLooper();
            return this;
        }

        public Builder setViewForPopups(View viewForPopups) {
            this.JJ = viewForPopups;
            return this;
        }

        public Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }
    }

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener extends GooglePlayServicesClient.OnConnectionFailedListener {
        @Override // com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    <C extends Api.a> C a(Api.c<C> cVar);

    <A extends Api.a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T t);

    boolean a(Scope scope);

    <A extends Api.a, T extends BaseImplementation.a<? extends Result, A>> T b(T t);

    ConnectionResult blockingConnect();

    ConnectionResult blockingConnect(long j, TimeUnit timeUnit);

    PendingResult<Status> clearDefaultAccountAndReconnect();

    void connect();

    <L> d<L> d(L l);

    void disconnect();

    Looper getLooper();

    boolean isConnected();

    boolean isConnecting();

    boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks);

    boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener);

    void reconnect();

    void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    void stopAutoManage(FragmentActivity fragmentActivity);

    void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);
}
