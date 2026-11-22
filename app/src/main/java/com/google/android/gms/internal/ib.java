package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.appstate.AppStateBuffer;
import com.google.android.gms.appstate.AppStateManager;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
public final class ib extends jl<id> {
    private final String DZ;

    private static final class a extends ia {
        private final BaseImplementation.b<AppStateManager.StateDeletedResult> Ea;

        public a(BaseImplementation.b<AppStateManager.StateDeletedResult> bVar) {
            this.Ea = (BaseImplementation.b) jx.b(bVar, "Result holder must not be null");
        }

        @Override // com.google.android.gms.internal.ia, com.google.android.gms.internal.ic
        public void e(int i, int i2) {
            this.Ea.b(new b(new Status(i), i2));
        }
    }

    private static final class b implements AppStateManager.StateDeletedResult {
        private final Status Eb;
        private final int Ec;

        public b(Status status, int i) {
            this.Eb = status;
            this.Ec = i;
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateDeletedResult
        public int getStateKey() {
            return this.Ec;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class c extends ia {
        private final BaseImplementation.b<AppStateManager.StateListResult> Ea;

        public c(BaseImplementation.b<AppStateManager.StateListResult> bVar) {
            this.Ea = (BaseImplementation.b) jx.b(bVar, "Result holder must not be null");
        }

        @Override // com.google.android.gms.internal.ia, com.google.android.gms.internal.ic
        public void a(DataHolder dataHolder) {
            this.Ea.b(new d(dataHolder));
        }
    }

    private static final class d extends com.google.android.gms.common.api.b implements AppStateManager.StateListResult {
        private final AppStateBuffer Ed;

        public d(DataHolder dataHolder) {
            super(dataHolder);
            this.Ed = new AppStateBuffer(dataHolder);
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateListResult
        public AppStateBuffer getStateBuffer() {
            return this.Ed;
        }
    }

    private static final class e extends ia {
        private final BaseImplementation.b<AppStateManager.StateResult> Ea;

        public e(BaseImplementation.b<AppStateManager.StateResult> bVar) {
            this.Ea = (BaseImplementation.b) jx.b(bVar, "Result holder must not be null");
        }

        @Override // com.google.android.gms.internal.ia, com.google.android.gms.internal.ic
        public void a(int i, DataHolder dataHolder) {
            this.Ea.b(new f(i, dataHolder));
        }
    }

    private static final class f extends com.google.android.gms.common.api.b implements AppStateManager.StateConflictResult, AppStateManager.StateLoadedResult, AppStateManager.StateResult {
        private final int Ec;
        private final AppStateBuffer Ed;

        public f(int i, DataHolder dataHolder) {
            super(dataHolder);
            this.Ec = i;
            this.Ed = new AppStateBuffer(dataHolder);
        }

        private boolean fN() {
            return this.Eb.getStatusCode() == 2000;
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateResult
        public AppStateManager.StateConflictResult getConflictResult() {
            if (fN()) {
                return this;
            }
            return null;
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateResult
        public AppStateManager.StateLoadedResult getLoadedResult() {
            if (fN()) {
                return null;
            }
            return this;
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateConflictResult, com.google.android.gms.appstate.AppStateManager.StateLoadedResult
        public byte[] getLocalData() {
            if (this.Ed.getCount() == 0) {
                return null;
            }
            return this.Ed.get(0).getLocalData();
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateConflictResult
        public String getResolvedVersion() {
            if (this.Ed.getCount() == 0) {
                return null;
            }
            return this.Ed.get(0).getConflictVersion();
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateConflictResult
        public byte[] getServerData() {
            if (this.Ed.getCount() == 0) {
                return null;
            }
            return this.Ed.get(0).getConflictData();
        }

        @Override // com.google.android.gms.appstate.AppStateManager.StateConflictResult, com.google.android.gms.appstate.AppStateManager.StateLoadedResult
        public int getStateKey() {
            return this.Ec;
        }

        @Override // com.google.android.gms.common.api.b, com.google.android.gms.common.api.Releasable
        public void release() {
            this.Ed.release();
        }
    }

    private static final class g extends ia {
        private final BaseImplementation.b<Status> Ea;

        public g(BaseImplementation.b<Status> bVar) {
            this.Ea = (BaseImplementation.b) jx.b(bVar, "Holder must not be null");
        }

        @Override // com.google.android.gms.internal.ia, com.google.android.gms.internal.ic
        public void fK() {
            this.Ea.b(new Status(0));
        }
    }

    public ib(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, String[] strArr) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
        this.DZ = (String) jx.i(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public id l(IBinder iBinder) {
        return id.a.J(iBinder);
    }

    public void a(BaseImplementation.b<AppStateManager.StateListResult> bVar) {
        try {
            hw().a(new c(bVar));
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(BaseImplementation.b<AppStateManager.StateDeletedResult> bVar, int i) {
        try {
            hw().b(new a(bVar), i);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(BaseImplementation.b<AppStateManager.StateResult> bVar, int i, String str, byte[] bArr) {
        try {
            hw().a(new e(bVar), i, str, bArr);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void a(BaseImplementation.b<AppStateManager.StateResult> bVar, int i, byte[] bArr) {
        e eVar;
        if (bVar == null) {
            eVar = null;
        } else {
            try {
                eVar = new e(bVar);
            } catch (RemoteException e2) {
                Log.w("AppStateClient", "service died");
                return;
            }
        }
        hw().a(eVar, i, bArr);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.DZ, hv());
    }

    public void b(BaseImplementation.b<Status> bVar) {
        try {
            hw().b(new g(bVar));
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    public void b(BaseImplementation.b<AppStateManager.StateResult> bVar, int i) {
        try {
            hw().a(new e(bVar), i);
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.appstate.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.appstate.internal.IAppStateService";
    }

    @Override // com.google.android.gms.internal.jl
    protected void c(String... strArr) {
        boolean z = false;
        for (String str : strArr) {
            if (str.equals(Scopes.APP_STATE)) {
                z = true;
            }
        }
        jx.a(z, String.format("App State APIs requires %s to function.", Scopes.APP_STATE));
    }

    public int fL() {
        try {
            return hw().fL();
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }

    public int fM() {
        try {
            return hw().fM();
        } catch (RemoteException e2) {
            Log.w("AppStateClient", "service died");
            return 2;
        }
    }
}
