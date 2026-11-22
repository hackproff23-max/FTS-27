package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* JADX INFO: loaded from: classes.dex */
public class mh implements BleApi {

    private static class a extends mp.a {
        private final BaseImplementation.b<BleDevicesResult> Ea;

        private a(BaseImplementation.b<BleDevicesResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.mp
        public void a(BleDevicesResult bleDevicesResult) {
            this.Ea.b(bleDevicesResult);
        }
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> claimBleDevice(GoogleApiClient client, final BleDevice bleDevice) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mh.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.b(bleDevice), new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> claimBleDevice(GoogleApiClient client, final String deviceAddress) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mh.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.b(deviceAddress), new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient client) {
        return client.a(new lu.a<BleDevicesResult>(client) { // from class: com.google.android.gms.internal.mh.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new a(this), luVar.getContext().getPackageName());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
            public BleDevicesResult c(Status status) {
                return BleDevicesResult.C(status);
            }
        });
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> startBleScan(GoogleApiClient client, final StartBleScanRequest request) {
        return client.a(new lu.c(client) { // from class: com.google.android.gms.internal.mh.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(request, new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> stopBleScan(GoogleApiClient client, final BleScanCallback requestCallback) {
        return client.a(new lu.c(client) { // from class: com.google.android.gms.internal.mh.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                lu.b bVar = new lu.b(this);
                String packageName = luVar.getContext().getPackageName();
                luVar.jM().a(new com.google.android.gms.fitness.request.ad(requestCallback), bVar, packageName);
            }
        });
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> unclaimBleDevice(GoogleApiClient client, BleDevice bleDevice) {
        return unclaimBleDevice(client, bleDevice.getAddress());
    }

    @Override // com.google.android.gms.fitness.BleApi
    public PendingResult<Status> unclaimBleDevice(GoogleApiClient client, final String deviceAddress) {
        return client.b(new lu.c(client) { // from class: com.google.android.gms.internal.mh.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(lu luVar) throws RemoteException {
                luVar.jM().a(new com.google.android.gms.fitness.request.ah(deviceAddress), new lu.b(this), luVar.getContext().getPackageName());
            }
        });
    }
}
