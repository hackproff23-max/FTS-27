package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
final class az {

    static abstract class a<T> extends com.google.android.gms.wearable.internal.a {
        private BaseImplementation.b<T> DA;

        public a(BaseImplementation.b<T> bVar) {
            this.DA = bVar;
        }

        public void A(T t) {
            BaseImplementation.b<T> bVar = this.DA;
            if (bVar != null) {
                bVar.b(t);
                this.DA = null;
            }
        }
    }

    static final class b extends a<DataApi.DeleteDataItemsResult> {
        public b(BaseImplementation.b<DataApi.DeleteDataItemsResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(p pVar) {
            A(new com.google.android.gms.wearable.internal.f.c(av.gN(pVar.statusCode), pVar.axI));
        }
    }

    static final class c extends a<NodeApi.GetConnectedNodesResult> {
        public c(BaseImplementation.b<NodeApi.GetConnectedNodesResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(v vVar) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(vVar.axL);
            A(new ak.b(av.gN(vVar.statusCode), arrayList));
        }
    }

    static final class d extends a<DataApi.DataItemResult> {
        public d(BaseImplementation.b<DataApi.DataItemResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(x xVar) {
            A(new com.google.android.gms.wearable.internal.f.b(av.gN(xVar.statusCode), xVar.axM));
        }
    }

    static final class e extends a<DataItemBuffer> {
        public e(BaseImplementation.b<DataItemBuffer> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void ab(DataHolder dataHolder) {
            A(new DataItemBuffer(dataHolder));
        }
    }

    static final class f extends a<DataApi.GetFdForAssetResult> {
        public f(BaseImplementation.b<DataApi.GetFdForAssetResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(z zVar) {
            A(new com.google.android.gms.wearable.internal.f.d(av.gN(zVar.statusCode), zVar.axN));
        }
    }

    static final class g extends a<NodeApi.GetLocalNodeResult> {
        public g(BaseImplementation.b<NodeApi.GetLocalNodeResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(ab abVar) {
            A(new ak.c(av.gN(abVar.statusCode), abVar.axO));
        }
    }

    static final class h extends com.google.android.gms.wearable.internal.a {
        h() {
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(Status status) {
        }
    }

    static final class i extends a<DataApi.DataItemResult> {
        private final List<FutureTask<Boolean>> ayf;

        i(BaseImplementation.b<DataApi.DataItemResult> bVar, List<FutureTask<Boolean>> list) {
            super(bVar);
            this.ayf = list;
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(ap apVar) {
            A(new com.google.android.gms.wearable.internal.f.b(av.gN(apVar.statusCode), apVar.axM));
            if (apVar.statusCode != 0) {
                Iterator<FutureTask<Boolean>> it = this.ayf.iterator();
                while (it.hasNext()) {
                    it.next().cancel(true);
                }
            }
        }
    }

    static final class j extends a<MessageApi.SendMessageResult> {
        public j(BaseImplementation.b<MessageApi.SendMessageResult> bVar) {
            super(bVar);
        }

        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(at atVar) {
            A(new ah.b(av.gN(atVar.statusCode), atVar.ayd));
        }
    }
}
