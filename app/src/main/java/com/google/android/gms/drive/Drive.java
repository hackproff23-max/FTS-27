package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.p;
import com.google.android.gms.drive.internal.r;
import com.google.android.gms.drive.internal.u;
import com.google.android.gms.drive.internal.x;
import com.google.android.gms.drive.internal.z;
import com.google.android.gms.internal.jg;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Drive {
    public static final Api.c<r> DQ = new Api.c<>();
    public static final Scope SCOPE_FILE = new Scope(Scopes.DRIVE_FILE);
    public static final Scope SCOPE_APPFOLDER = new Scope(Scopes.DRIVE_APPFOLDER);
    public static final Scope Oo = new Scope("https://www.googleapis.com/auth/drive");
    public static final Scope Op = new Scope("https://www.googleapis.com/auth/drive.apps");
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>(new a<Api.ApiOptions.NoOptions>() { // from class: com.google.android.gms.drive.Drive.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.drive.Drive.a
        public Bundle a(Api.ApiOptions.NoOptions noOptions) {
            return new Bundle();
        }
    }, DQ, new Scope[0]);
    public static final Api<b> Oq = new Api<>(new a<b>() { // from class: com.google.android.gms.drive.Drive.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.drive.Drive.a
        public Bundle a(b bVar) {
            return bVar == null ? new Bundle() : bVar.iq();
        }
    }, DQ, new Scope[0]);
    public static final DriveApi DriveApi = new p();
    public static final com.google.android.gms.drive.b Or = new u();
    public static final e Os = new z();
    public static final DrivePreferencesApi DrivePreferencesApi = new x();

    public static abstract class a<O extends Api.ApiOptions> implements Api.b<r, O> {
        protected abstract Bundle a(O o);

        @Override // com.google.android.gms.common.api.Api.b
        public r a(Context context, Looper looper, jg jgVar, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            List<String> listHo = jgVar.ho();
            return new r(context, looper, jgVar, connectionCallbacks, onConnectionFailedListener, (String[]) listHo.toArray(new String[listHo.size()]), a(o));
        }

        @Override // com.google.android.gms.common.api.Api.b
        public int getPriority() {
            return MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT;
        }
    }

    public static class b implements Api.ApiOptions.Optional {
        private final Bundle DJ;

        private b() {
            this(new Bundle());
        }

        private b(Bundle bundle) {
            this.DJ = bundle;
        }

        public Bundle iq() {
            return this.DJ;
        }
    }

    private Drive() {
    }
}
