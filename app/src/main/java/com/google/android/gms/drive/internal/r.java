package com.google.android.gms.drive.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.DriveEventService;
import com.google.android.gms.internal.jg;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.jx;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class r extends jl<ae> {
    private final String DZ;
    private final String JK;
    private final Bundle PK;
    private final boolean PL;
    private DriveId PM;
    private DriveId PN;
    final GoogleApiClient.ConnectionCallbacks PO;
    final Map<DriveId, Map<com.google.android.gms.drive.events.c, aa>> PP;

    public r(Context context, Looper looper, jg jgVar, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String[] strArr, Bundle bundle) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, strArr);
        this.PP = new HashMap();
        this.DZ = (String) jx.b(jgVar.hm(), (Object) "Must call Api.ClientBuilder.setAccountName()");
        this.JK = jgVar.hq();
        this.PO = connectionCallbacks;
        this.PK = bundle;
        Intent intent = new Intent(DriveEventService.ACTION_HANDLE_EVENT);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        switch (listQueryIntentServices.size()) {
            case 0:
                this.PL = false;
                return;
            case 1:
                ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
                if (!serviceInfo.exported) {
                    throw new IllegalStateException("Drive event service " + serviceInfo.name + " must be exported in AndroidManifest.xml");
                }
                this.PL = true;
                return;
            default:
                throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + intent.getAction() + " action");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: W, reason: merged with bridge method [inline-methods] */
    public ae l(IBinder iBinder) {
        return ae.a.X(iBinder);
    }

    PendingResult<Status> a(GoogleApiClient googleApiClient, final DriveId driveId, final int i) {
        jx.b(com.google.android.gms.drive.events.d.a(i, driveId), "id");
        jx.a(isConnected(), "Client must be connected");
        if (this.PL) {
            return googleApiClient.b(new q.a(googleApiClient) { // from class: com.google.android.gms.drive.internal.r.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.android.gms.common.api.BaseImplementation.a
                public void a(r rVar) throws RemoteException {
                    rVar.iG().a(new AddEventListenerRequest(driveId, i), (ag) null, (String) null, new bg(this));
                }
            });
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }

    PendingResult<Status> a(GoogleApiClient googleApiClient, final DriveId driveId, final int i, com.google.android.gms.drive.events.c cVar) {
        Map<com.google.android.gms.drive.events.c, aa> map;
        final aa aaVar;
        PendingResult<Status> jVar;
        jx.b(com.google.android.gms.drive.events.d.a(i, driveId), "id");
        jx.b(cVar, "listener");
        jx.a(isConnected(), "Client must be connected");
        synchronized (this.PP) {
            Map<com.google.android.gms.drive.events.c, aa> map2 = this.PP.get(driveId);
            if (map2 == null) {
                HashMap map3 = new HashMap();
                this.PP.put(driveId, map3);
                map = map3;
            } else {
                map = map2;
            }
            aa aaVar2 = map.get(cVar);
            if (aaVar2 == null) {
                aaVar = new aa(getLooper(), getContext(), i, cVar);
                map.put(cVar, aaVar);
            } else if (aaVar2.bx(i)) {
                jVar = new p.j(googleApiClient, Status.Kw);
            } else {
                aaVar = aaVar2;
            }
            aaVar.bw(i);
            jVar = googleApiClient.b(new q.a(googleApiClient) { // from class: com.google.android.gms.drive.internal.r.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.google.android.gms.common.api.BaseImplementation.a
                public void a(r rVar) throws RemoteException {
                    rVar.iG().a(new AddEventListenerRequest(driveId, i), aaVar, (String) null, new bg(this));
                }
            });
        }
        return jVar;
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(int i, IBinder iBinder, Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.PM = (DriveId) bundle.getParcelable("com.google.android.gms.drive.root_id");
            this.PN = (DriveId) bundle.getParcelable("com.google.android.gms.drive.appdata_id");
        }
        super.a(i, iBinder, bundle);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        String packageName = getContext().getPackageName();
        jx.i(eVar);
        jx.i(packageName);
        jx.i(hv());
        Bundle bundle = new Bundle();
        if (!packageName.equals(this.JK)) {
            bundle.putString("proxy_package_name", this.JK);
        }
        bundle.putAll(this.PK);
        jtVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, packageName, hv(), this.DZ, bundle);
    }

    PendingResult<Status> b(GoogleApiClient googleApiClient, final DriveId driveId, final int i) {
        jx.b(com.google.android.gms.drive.events.d.a(i, driveId), "id");
        jx.a(isConnected(), "Client must be connected");
        return googleApiClient.b(new q.a(googleApiClient) { // from class: com.google.android.gms.drive.internal.r.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new RemoveEventListenerRequest(driveId, i), (ag) null, (String) null, new bg(this));
            }
        });
    }

    PendingResult<Status> b(GoogleApiClient googleApiClient, final DriveId driveId, final int i, com.google.android.gms.drive.events.c cVar) {
        PendingResult<Status> pendingResultB;
        jx.b(com.google.android.gms.drive.events.d.a(i, driveId), "id");
        jx.a(isConnected(), "Client must be connected");
        jx.b(cVar, "listener");
        synchronized (this.PP) {
            Map<com.google.android.gms.drive.events.c, aa> map = this.PP.get(driveId);
            if (map == null) {
                pendingResultB = new p.j(googleApiClient, Status.Kw);
            } else {
                final aa aaVarRemove = map.remove(cVar);
                if (aaVarRemove == null) {
                    pendingResultB = new p.j(googleApiClient, Status.Kw);
                } else {
                    if (map.isEmpty()) {
                        this.PP.remove(driveId);
                    }
                    pendingResultB = googleApiClient.b(new q.a(googleApiClient) { // from class: com.google.android.gms.drive.internal.r.2
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.google.android.gms.common.api.BaseImplementation.a
                        public void a(r rVar) throws RemoteException {
                            rVar.iG().a(new RemoveEventListenerRequest(driveId, i), aaVarRemove, (String) null, new bg(this));
                        }
                    });
                }
            }
        }
        return pendingResultB;
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.drive.ApiService.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.drive.internal.IDriveService";
    }

    PendingResult<Status> cancelPendingActions(GoogleApiClient apiClient, final List<String> pendingTags) {
        jx.L(pendingTags != null);
        jx.L(pendingTags.isEmpty() ? false : true);
        jx.a(isConnected(), "Client must be connected");
        return apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.r.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new CancelPendingActionsRequest(pendingTags), new bg(this));
            }
        });
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void disconnect() {
        if (isConnected()) {
            try {
                hw().a(new DisconnectRequest());
            } catch (RemoteException e) {
            }
        }
        super.disconnect();
        this.PP.clear();
    }

    public ae iG() throws DeadObjectException {
        return hw();
    }

    public DriveId iH() {
        return this.PM;
    }

    public DriveId iI() {
        return this.PN;
    }

    public boolean iJ() {
        return this.PL;
    }
}
