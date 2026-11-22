package com.google.android.gms.wearable.internal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jt;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class ba extends jl<af> {
    private final ExecutorService asJ;
    private final ag<DataApi.DataListener> ayg;
    private final ag<MessageApi.MessageListener> ayh;
    private final ag<NodeApi.NodeListener> ayi;

    public ba(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.asJ = Executors.newCachedThreadPool();
        this.ayg = new ag.b();
        this.ayh = new ag.c();
        this.ayi = new ag.d();
    }

    private FutureTask<Boolean> a(final ParcelFileDescriptor parcelFileDescriptor, final byte[] bArr) {
        return new FutureTask<>(new Callable<Boolean>() { // from class: com.google.android.gms.wearable.internal.ba.1
            @Override // java.util.concurrent.Callable
            /* JADX INFO: renamed from: rr, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                if (Log.isLoggable("WearableClient", 3)) {
                    Log.d("WearableClient", "processAssets: writing data to FD : " + parcelFileDescriptor);
                }
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
                try {
                    autoCloseOutputStream.write(bArr);
                    autoCloseOutputStream.flush();
                    if (Log.isLoggable("WearableClient", 3)) {
                        Log.d("WearableClient", "processAssets: wrote data: " + parcelFileDescriptor);
                    }
                    try {
                        return true;
                    } catch (IOException e) {
                        return true;
                    }
                } catch (IOException e2) {
                    Log.w("WearableClient", "processAssets: writing data failed: " + parcelFileDescriptor);
                    return false;
                } finally {
                    try {
                        if (Log.isLoggable("WearableClient", 3)) {
                            Log.d("WearableClient", "processAssets: closing: " + parcelFileDescriptor);
                        }
                        autoCloseOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(int i, IBinder iBinder, Bundle bundle) {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.d("WearableClient", "onPostInitHandler: statusCode " + i);
        }
        if (i == 0) {
            this.ayg.ca(iBinder);
            this.ayh.ca(iBinder);
            this.ayi.ca(iBinder);
        }
        super.a(i, iBinder, bundle);
    }

    public void a(BaseImplementation.b<DataApi.DataItemResult> bVar, Uri uri) throws RemoteException {
        hw().a(new az.d(bVar), uri);
    }

    public void a(BaseImplementation.b<DataApi.GetFdForAssetResult> bVar, Asset asset) throws RemoteException {
        hw().a(new az.f(bVar), asset);
    }

    public void a(BaseImplementation.b<Status> bVar, DataApi.DataListener dataListener) throws RemoteException {
        this.ayg.a(this, bVar, dataListener);
    }

    public void a(BaseImplementation.b<Status> bVar, DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) throws RemoteException {
        this.ayg.a(this, bVar, dataListener, intentFilterArr);
    }

    public void a(BaseImplementation.b<DataApi.GetFdForAssetResult> bVar, DataItemAsset dataItemAsset) throws RemoteException {
        a(bVar, Asset.createFromRef(dataItemAsset.getId()));
    }

    public void a(BaseImplementation.b<Status> bVar, MessageApi.MessageListener messageListener) throws RemoteException {
        this.ayh.a(this, bVar, messageListener);
    }

    public void a(BaseImplementation.b<Status> bVar, MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) throws RemoteException {
        this.ayh.a(this, bVar, messageListener, intentFilterArr);
    }

    public void a(BaseImplementation.b<Status> bVar, NodeApi.NodeListener nodeListener) throws RemoteException {
        this.ayi.a(this, bVar, nodeListener, null);
    }

    public void a(BaseImplementation.b<DataApi.DataItemResult> bVar, PutDataRequest putDataRequest) throws RemoteException {
        Iterator<Map.Entry<String, Asset>> it = putDataRequest.getAssets().entrySet().iterator();
        while (it.hasNext()) {
            Asset value = it.next().getValue();
            if (value.getData() == null && value.getDigest() == null && value.getFd() == null && value.getUri() == null) {
                throw new IllegalArgumentException("Put for " + putDataRequest.getUri() + " contains invalid asset: " + value);
            }
        }
        PutDataRequest putDataRequestK = PutDataRequest.k(putDataRequest.getUri());
        putDataRequestK.setData(putDataRequest.getData());
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Asset> entry : putDataRequest.getAssets().entrySet()) {
            Asset value2 = entry.getValue();
            if (value2.getData() == null) {
                putDataRequestK.putAsset(entry.getKey(), entry.getValue());
            } else {
                try {
                    ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
                    if (Log.isLoggable("WearableClient", 3)) {
                        Log.d("WearableClient", "processAssets: replacing data with FD in asset: " + value2 + " read:" + parcelFileDescriptorArrCreatePipe[0] + " write:" + parcelFileDescriptorArrCreatePipe[1]);
                    }
                    putDataRequestK.putAsset(entry.getKey(), Asset.createFromFd(parcelFileDescriptorArrCreatePipe[0]));
                    FutureTask<Boolean> futureTaskA = a(parcelFileDescriptorArrCreatePipe[1], value2.getData());
                    arrayList.add(futureTaskA);
                    this.asJ.submit(futureTaskA);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to create ParcelFileDescriptor for asset in request: " + putDataRequest, e);
                }
            }
        }
        try {
            hw().a(new az.i(bVar, arrayList), putDataRequestK);
        } catch (NullPointerException e2) {
            throw new IllegalStateException("Unable to putDataItem: " + putDataRequest, e2);
        }
    }

    public void a(BaseImplementation.b<MessageApi.SendMessageResult> bVar, String str, String str2, byte[] bArr) throws RemoteException {
        hw().a(new az.j(bVar), str, str2, bArr);
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.e(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName());
    }

    public void b(BaseImplementation.b<DataItemBuffer> bVar, Uri uri) throws RemoteException {
        hw().b(new az.e(bVar), uri);
    }

    public void b(BaseImplementation.b<Status> bVar, NodeApi.NodeListener nodeListener) throws RemoteException {
        this.ayi.a(this, bVar, nodeListener);
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.wearable.BIND";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.wearable.internal.IWearableService";
    }

    public void c(BaseImplementation.b<DataApi.DeleteDataItemsResult> bVar, Uri uri) throws RemoteException {
        hw().c(new az.b(bVar), uri);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: cb, reason: merged with bridge method [inline-methods] */
    public af l(IBinder iBinder) {
        return af.a.bZ(iBinder);
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void disconnect() {
        this.ayg.b(this);
        this.ayh.b(this);
        this.ayi.b(this);
        super.disconnect();
    }

    public void n(BaseImplementation.b<DataItemBuffer> bVar) throws RemoteException {
        hw().b(new az.e(bVar));
    }

    public void o(BaseImplementation.b<NodeApi.GetLocalNodeResult> bVar) throws RemoteException {
        hw().c(new az.g(bVar));
    }

    public void p(BaseImplementation.b<NodeApi.GetConnectedNodesResult> bVar) throws RemoteException {
        hw().d(new az.c(bVar));
    }
}
