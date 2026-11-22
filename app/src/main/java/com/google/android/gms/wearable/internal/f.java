package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.PutDataRequest;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class f implements DataApi {

    private static final class a extends com.google.android.gms.wearable.internal.d<Status> {
        private DataApi.DataListener axC;
        private IntentFilter[] axD;

        private a(GoogleApiClient googleApiClient, DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) {
            super(googleApiClient);
            this.axC = dataListener;
            this.axD = intentFilterArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(ba baVar) throws RemoteException {
            baVar.a(this, this.axC, this.axD);
            this.axC = null;
            this.axD = null;
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            this.axC = null;
            this.axD = null;
            return status;
        }
    }

    public static class b implements DataApi.DataItemResult {
        private final Status Eb;
        private final DataItem axE;

        public b(Status status, DataItem dataItem) {
            this.Eb = status;
            this.axE = dataItem;
        }

        @Override // com.google.android.gms.wearable.DataApi.DataItemResult
        public DataItem getDataItem() {
            return this.axE;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    public static class c implements DataApi.DeleteDataItemsResult {
        private final Status Eb;
        private final int axF;

        public c(Status status, int i) {
            this.Eb = status;
            this.axF = i;
        }

        @Override // com.google.android.gms.wearable.DataApi.DeleteDataItemsResult
        public int getNumDeleted() {
            return this.axF;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    public static class d implements DataApi.GetFdForAssetResult {
        private final Status Eb;
        private volatile InputStream ZO;
        private volatile ParcelFileDescriptor axG;
        private volatile boolean mClosed = false;

        public d(Status status, ParcelFileDescriptor parcelFileDescriptor) {
            this.Eb = status;
            this.axG = parcelFileDescriptor;
        }

        @Override // com.google.android.gms.wearable.DataApi.GetFdForAssetResult
        public ParcelFileDescriptor getFd() {
            if (this.mClosed) {
                throw new IllegalStateException("Cannot access the file descriptor after release().");
            }
            return this.axG;
        }

        @Override // com.google.android.gms.wearable.DataApi.GetFdForAssetResult
        public InputStream getInputStream() {
            if (this.mClosed) {
                throw new IllegalStateException("Cannot access the input stream after release().");
            }
            if (this.axG == null) {
                return null;
            }
            if (this.ZO == null) {
                this.ZO = new ParcelFileDescriptor.AutoCloseInputStream(this.axG);
            }
            return this.ZO;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            if (this.axG == null) {
                return;
            }
            if (this.mClosed) {
                throw new IllegalStateException("releasing an already released result.");
            }
            try {
                if (this.ZO != null) {
                    this.ZO.close();
                } else {
                    this.axG.close();
                }
                this.mClosed = true;
                this.axG = null;
                this.ZO = null;
            } catch (IOException e) {
            }
        }
    }

    private PendingResult<Status> a(GoogleApiClient googleApiClient, DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) {
        return googleApiClient.a(new a(googleApiClient, dataListener, intentFilterArr));
    }

    private void a(Asset asset) {
        if (asset == null) {
            throw new IllegalArgumentException("asset is null");
        }
        if (asset.getDigest() == null) {
            throw new IllegalArgumentException("invalid asset");
        }
        if (asset.getData() != null) {
            throw new IllegalArgumentException("invalid asset");
        }
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<Status> addListener(GoogleApiClient client, DataApi.DataListener listener) {
        return a(client, listener, null);
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataApi.DeleteDataItemsResult> deleteDataItems(GoogleApiClient client, final Uri uri) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataApi.DeleteDataItemsResult>(client) { // from class: com.google.android.gms.wearable.internal.f.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.c(this, uri);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aG, reason: merged with bridge method [inline-methods] */
            public DataApi.DeleteDataItemsResult c(Status status) {
                return new c(status, 0);
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataApi.DataItemResult> getDataItem(GoogleApiClient client, final Uri uri) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataApi.DataItemResult>(client) { // from class: com.google.android.gms.wearable.internal.f.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, uri);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aE, reason: merged with bridge method [inline-methods] */
            public DataApi.DataItemResult c(Status status) {
                return new b(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataItemBuffer> getDataItems(GoogleApiClient client) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataItemBuffer>(client) { // from class: com.google.android.gms.wearable.internal.f.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.n(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aF, reason: merged with bridge method [inline-methods] */
            public DataItemBuffer c(Status status) {
                return new DataItemBuffer(DataHolder.av(status.getStatusCode()));
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataItemBuffer> getDataItems(GoogleApiClient client, final Uri uri) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataItemBuffer>(client) { // from class: com.google.android.gms.wearable.internal.f.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.b(this, uri);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aF, reason: merged with bridge method [inline-methods] */
            public DataItemBuffer c(Status status) {
                return new DataItemBuffer(DataHolder.av(status.getStatusCode()));
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataApi.GetFdForAssetResult> getFdForAsset(GoogleApiClient client, final Asset asset) {
        a(asset);
        return client.a(new com.google.android.gms.wearable.internal.d<DataApi.GetFdForAssetResult>(client) { // from class: com.google.android.gms.wearable.internal.f.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, asset);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aH, reason: merged with bridge method [inline-methods] */
            public DataApi.GetFdForAssetResult c(Status status) {
                return new d(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataApi.GetFdForAssetResult> getFdForAsset(GoogleApiClient client, final DataItemAsset asset) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataApi.GetFdForAssetResult>(client) { // from class: com.google.android.gms.wearable.internal.f.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, asset);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aH, reason: merged with bridge method [inline-methods] */
            public DataApi.GetFdForAssetResult c(Status status) {
                return new d(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<DataApi.DataItemResult> putDataItem(GoogleApiClient client, final PutDataRequest request) {
        return client.a(new com.google.android.gms.wearable.internal.d<DataApi.DataItemResult>(client) { // from class: com.google.android.gms.wearable.internal.f.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, request);
            }

            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aE, reason: merged with bridge method [inline-methods] */
            public DataApi.DataItemResult c(Status status) {
                return new b(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.DataApi
    public PendingResult<Status> removeListener(GoogleApiClient client, final DataApi.DataListener listener) {
        return client.a(new com.google.android.gms.wearable.internal.d<Status>(client) { // from class: com.google.android.gms.wearable.internal.f.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.a(this, listener);
            }

            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Status c(Status status) {
                return status;
            }
        });
    }
}
