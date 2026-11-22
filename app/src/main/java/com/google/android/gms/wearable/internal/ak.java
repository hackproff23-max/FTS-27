package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ak implements NodeApi {

    private static final class a extends d<Status> {
        private NodeApi.NodeListener axZ;

        private a(GoogleApiClient googleApiClient, NodeApi.NodeListener nodeListener) {
            super(googleApiClient);
            this.axZ = nodeListener;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(ba baVar) throws RemoteException {
            baVar.a(this, this.axZ);
            this.axZ = null;
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            this.axZ = null;
            return status;
        }
    }

    public static class b implements NodeApi.GetConnectedNodesResult {
        private final Status Eb;
        private final List<Node> aya;

        public b(Status status, List<Node> list) {
            this.Eb = status;
            this.aya = list;
        }

        @Override // com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult
        public List<Node> getNodes() {
            return this.aya;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    public static class c implements NodeApi.GetLocalNodeResult {
        private final Status Eb;
        private final Node ayb;

        public c(Status status, Node node) {
            this.Eb = status;
            this.ayb = node;
        }

        @Override // com.google.android.gms.wearable.NodeApi.GetLocalNodeResult
        public Node getNode() {
            return this.ayb;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    @Override // com.google.android.gms.wearable.NodeApi
    public PendingResult<Status> addListener(GoogleApiClient client, NodeApi.NodeListener listener) {
        return client.a(new a(client, listener));
    }

    @Override // com.google.android.gms.wearable.NodeApi
    public PendingResult<NodeApi.GetConnectedNodesResult> getConnectedNodes(GoogleApiClient client) {
        return client.a(new d<NodeApi.GetConnectedNodesResult>(client) { // from class: com.google.android.gms.wearable.internal.ak.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.p(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aK, reason: merged with bridge method [inline-methods] */
            public NodeApi.GetConnectedNodesResult c(Status status) {
                return new b(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.NodeApi
    public PendingResult<NodeApi.GetLocalNodeResult> getLocalNode(GoogleApiClient client) {
        return client.a(new d<NodeApi.GetLocalNodeResult>(client) { // from class: com.google.android.gms.wearable.internal.ak.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.o(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: aJ, reason: merged with bridge method [inline-methods] */
            public NodeApi.GetLocalNodeResult c(Status status) {
                return new c(status, null);
            }
        });
    }

    @Override // com.google.android.gms.wearable.NodeApi
    public PendingResult<Status> removeListener(GoogleApiClient client, final NodeApi.NodeListener listener) {
        return client.a(new d<Status>(client) { // from class: com.google.android.gms.wearable.internal.ak.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(ba baVar) throws RemoteException {
                baVar.b(this, listener);
            }

            @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public Status c(Status status) {
                return status;
            }
        });
    }
}
