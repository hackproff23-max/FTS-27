package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jx;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class Cast {
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    static final Api.c<im> DQ = new Api.c<>();
    private static final Api.b<im, CastOptions> DR = new Api.b<im, CastOptions>() { // from class: com.google.android.gms.cast.Cast.1
        @Override // com.google.android.gms.common.api.Api.b
        public im a(Context context, Looper looper, jg jgVar, CastOptions castOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            jx.b(castOptions, "Setting the API options is required.");
            return new im(context, looper, castOptions.Fz, castOptions.FB, castOptions.FA, connectionCallbacks, onConnectionFailedListener);
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    };
    public static final Api<CastOptions> API = new Api<>(DR, DQ, new Scope[0]);
    public static final CastApi CastApi = new CastApi.a();

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    public interface CastApi {

        public static final class a implements CastApi {
            @Override // com.google.android.gms.cast.Cast.CastApi
            public ApplicationMetadata getApplicationMetadata(GoogleApiClient client) throws IllegalStateException {
                return ((im) client.a(Cast.DQ)).getApplicationMetadata();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public String getApplicationStatus(GoogleApiClient client) throws IllegalStateException {
                return ((im) client.a(Cast.DQ)).getApplicationStatus();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public double getVolume(GoogleApiClient client) throws IllegalStateException {
                return ((im) client.a(Cast.DQ)).fZ();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public boolean isMute(GoogleApiClient client) throws IllegalStateException {
                return ((im) client.a(Cast.DQ)).isMute();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client) {
                return client.b(new c(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.6
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.b(null, null, this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client, final String applicationId) {
                return client.b(new c(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.5
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.b(applicationId, null, this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient client, final String applicationId, final String sessionId) {
                return client.b(new c(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.4
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.b(applicationId, sessionId, this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, final String applicationId) {
                return client.b(new c(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.a(applicationId, false, (BaseImplementation.b<ApplicationConnectionResult>) this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, final String applicationId, final LaunchOptions options) {
                return client.b(new c(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.3
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.a(applicationId, options, this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            @Deprecated
            public PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient client, String applicationId, boolean relaunchIfRunning) {
                return launchApplication(client, applicationId, new LaunchOptions.Builder().setRelaunchIfRunning(relaunchIfRunning).build());
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<Status> leaveApplication(GoogleApiClient client) {
                return client.b(new b(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.7
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.d(this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public void removeMessageReceivedCallbacks(GoogleApiClient client, String namespace) throws IOException, IllegalArgumentException {
                try {
                    ((im) client.a(Cast.DQ)).aE(namespace);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public void requestStatus(GoogleApiClient client) throws IllegalStateException, IOException {
                try {
                    ((im) client.a(Cast.DQ)).fY();
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<Status> sendMessage(GoogleApiClient client, final String namespace, final String message) {
                return client.b(new b(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.a(namespace, message, this);
                        } catch (IllegalArgumentException e) {
                            W(2001);
                        } catch (IllegalStateException e2) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public void setMessageReceivedCallbacks(GoogleApiClient client, String namespace, MessageReceivedCallback callbacks) throws IllegalStateException, IOException {
                try {
                    ((im) client.a(Cast.DQ)).a(namespace, callbacks);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public void setMute(GoogleApiClient client, boolean mute) throws IllegalStateException, IOException {
                try {
                    ((im) client.a(Cast.DQ)).I(mute);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public void setVolume(GoogleApiClient client, double volume) throws IllegalStateException, IOException, IllegalArgumentException {
                try {
                    ((im) client.a(Cast.DQ)).a(volume);
                } catch (RemoteException e) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<Status> stopApplication(GoogleApiClient client) {
                return client.b(new b(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.8
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        try {
                            imVar.a("", this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public PendingResult<Status> stopApplication(GoogleApiClient client, final String sessionId) {
                return client.b(new b(client) { // from class: com.google.android.gms.cast.Cast.CastApi.a.9
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.google.android.gms.common.api.BaseImplementation.a
                    public void a(im imVar) throws RemoteException {
                        if (TextUtils.isEmpty(sessionId)) {
                            e(2001, "IllegalArgument: sessionId cannot be null or empty");
                            return;
                        }
                        try {
                            imVar.a(sessionId, this);
                        } catch (IllegalStateException e) {
                            W(2001);
                        }
                    }
                });
            }
        }

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IllegalStateException, IOException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IllegalStateException, IOException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IllegalStateException, IOException, IllegalArgumentException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static final class CastOptions implements Api.ApiOptions.HasOptions {
        final Listener FA;
        private final int FB;
        final CastDevice Fz;

        public static final class Builder {
            CastDevice FC;
            Listener FD;
            private int FE;

            private Builder(CastDevice castDevice, Listener castListener) {
                jx.b(castDevice, "CastDevice parameter cannot be null");
                jx.b(castListener, "CastListener parameter cannot be null");
                this.FC = castDevice;
                this.FD = castListener;
                this.FE = 0;
            }

            public CastOptions build() {
                return new CastOptions(this);
            }

            public Builder setVerboseLoggingEnabled(boolean enabled) {
                if (enabled) {
                    this.FE |= 1;
                } else {
                    this.FE &= -2;
                }
                return this;
            }
        }

        private CastOptions(Builder builder) {
            this.Fz = builder.FC;
            this.FA = builder.FD;
            this.FB = builder.FE;
        }

        public static Builder builder(CastDevice castDevice, Listener castListener) {
            return new Builder(castDevice, castListener);
        }
    }

    public static class Listener {
        public void X(int i) {
        }

        public void Y(int i) {
        }

        public void onApplicationDisconnected(int statusCode) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    protected static abstract class a<R extends Result> extends BaseImplementation.a<R, im> {
        public a(GoogleApiClient googleApiClient) {
            super(Cast.DQ, googleApiClient);
        }

        public void W(int i) {
            b((Result) c(new Status(i)));
        }

        public void e(int i, String str) {
            b((Result) c(new Status(i, str, null)));
        }
    }

    private static abstract class b extends a<Status> {
        b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class c extends a<ApplicationConnectionResult> {
        public c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public ApplicationConnectionResult c(final Status status) {
            return new ApplicationConnectionResult() { // from class: com.google.android.gms.cast.Cast.c.1
                @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
                public ApplicationMetadata getApplicationMetadata() {
                    return null;
                }

                @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
                public String getApplicationStatus() {
                    return null;
                }

                @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
                public String getSessionId() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }

                @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
                public boolean getWasLaunched() {
                    return false;
                }
            };
        }
    }

    private Cast() {
    }
}
