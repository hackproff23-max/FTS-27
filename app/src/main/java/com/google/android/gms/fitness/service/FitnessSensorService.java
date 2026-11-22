package com.google.android.gms.fitness.service;

import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.ll;
import com.google.android.gms.internal.lx;
import com.google.android.gms.internal.md;
import com.google.android.gms.internal.mq;
import com.google.android.gms.internal.ms;
import com.google.android.gms.internal.mu;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class FitnessSensorService extends Service {
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    private a Ww;

    private static class a extends mu.a {
        private final FitnessSensorService Wx;

        private a(FitnessSensorService fitnessSensorService) {
            this.Wx = fitnessSensorService;
        }

        private void kw() throws SecurityException {
            int callingUid = Binder.getCallingUid();
            if (ll.im()) {
                ((AppOpsManager) this.Wx.getSystemService("appops")).checkPackage(callingUid, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE);
                return;
            }
            String[] packagesForUid = this.Wx.getPackageManager().getPackagesForUid(callingUid);
            if (packagesForUid != null) {
                for (String str : packagesForUid) {
                    if (str.equals(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE)) {
                        return;
                    }
                }
            }
            throw new SecurityException("Unauthorized caller");
        }

        @Override // com.google.android.gms.internal.mu
        public void a(FitnessSensorServiceRequest fitnessSensorServiceRequest, md mdVar) throws RemoteException {
            kw();
            if (this.Wx.onRegister(fitnessSensorServiceRequest)) {
                mdVar.j(Status.Kw);
            } else {
                mdVar.j(new Status(13));
            }
        }

        @Override // com.google.android.gms.internal.mu
        public void a(mq mqVar, lx lxVar) throws RemoteException {
            kw();
            lxVar.a(new DataSourcesResult(this.Wx.onFindDataSources(mqVar.getDataTypes()), Status.Kw));
        }

        @Override // com.google.android.gms.internal.mu
        public void a(ms msVar, md mdVar) throws RemoteException {
            kw();
            if (this.Wx.onUnregister(msVar.getDataSource())) {
                mdVar.j(Status.Kw);
            } else {
                mdVar.j(new Status(13));
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (Log.isLoggable("FitnessSensorService", 3)) {
            Log.d("FitnessSensorService", "Intent " + intent + " received by " + getClass().getName());
        }
        return this.Ww.asBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.Ww = new a();
    }

    public abstract List<DataSource> onFindDataSources(List<DataType> list);

    public abstract boolean onRegister(FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(DataSource dataSource);
}
