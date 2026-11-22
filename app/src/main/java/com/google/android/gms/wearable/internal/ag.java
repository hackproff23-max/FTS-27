package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class ag<T> {
    private final HashMap<T, bb> agU = new HashMap<>();

    private static class a<T> extends az.a<Status> {
        private WeakReference<Map<T, bb>> axP;
        private WeakReference<T> axQ;

        a(Map<T, bb> map, T t, BaseImplementation.b<Status> bVar) {
            super(bVar);
            this.axP = new WeakReference<>(map);
            this.axQ = new WeakReference<>(t);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(Status status) {
            Map<T, bb> map = this.axP.get();
            T t = this.axQ.get();
            if (!status.getStatus().isSuccess() && map != null && t != null) {
                synchronized (map) {
                    bb bbVarRemove = map.remove(t);
                    if (bbVarRemove != null) {
                        bbVarRemove.clear();
                    }
                }
            }
            A(status);
        }
    }

    static final class b extends ag<DataApi.DataListener> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.gms.wearable.internal.ag
        public bb a(DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) {
            return bb.b(dataListener, intentFilterArr);
        }
    }

    static final class c extends ag<MessageApi.MessageListener> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.gms.wearable.internal.ag
        public bb a(MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) {
            return bb.b(messageListener, intentFilterArr);
        }
    }

    static final class d extends ag<NodeApi.NodeListener> {
        d() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.gms.wearable.internal.ag
        public bb a(NodeApi.NodeListener nodeListener, IntentFilter[] intentFilterArr) {
            return bb.a(nodeListener);
        }
    }

    private static class e<T> extends az.a<Status> {
        private WeakReference<Map<T, bb>> axP;
        private WeakReference<T> axQ;

        e(Map<T, bb> map, T t, BaseImplementation.b<Status> bVar) {
            super(bVar);
            this.axP = new WeakReference<>(map);
            this.axQ = new WeakReference<>(t);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.wearable.internal.a, com.google.android.gms.wearable.internal.ad
        public void a(Status status) {
            Map<T, bb> map = this.axP.get();
            T t = this.axQ.get();
            if (status.getStatus().getStatusCode() == 4002 && map != null && t != null) {
                synchronized (map) {
                    bb bbVarRemove = map.remove(t);
                    if (bbVarRemove != null) {
                        bbVarRemove.clear();
                    }
                }
            }
            A(status);
        }
    }

    ag() {
    }

    abstract bb a(T t, IntentFilter[] intentFilterArr);

    public void a(ba baVar, BaseImplementation.b<Status> bVar, T t) throws RemoteException {
        synchronized (this.agU) {
            bb bbVarRemove = this.agU.remove(t);
            if (bbVarRemove == null) {
                bVar.b(new Status(4002));
            } else {
                bbVarRemove.clear();
                baVar.hw().a(new e(this.agU, t, bVar), new ar(bbVarRemove));
            }
        }
    }

    public void a(ba baVar, BaseImplementation.b<Status> bVar, T t, IntentFilter[] intentFilterArr) throws RemoteException {
        bb bbVarA = a(t, intentFilterArr);
        synchronized (this.agU) {
            if (this.agU.get(t) != null) {
                bVar.b(new Status(4001));
                return;
            }
            this.agU.put(t, bbVarA);
            try {
                baVar.hw().a(new a(this.agU, t, bVar), new com.google.android.gms.wearable.internal.b(bbVarA));
            } catch (RemoteException e2) {
                this.agU.remove(t);
                throw e2;
            }
        }
    }

    public void b(ba baVar) {
        synchronized (this.agU) {
            az.h hVar = new az.h();
            for (Map.Entry<T, bb> entry : this.agU.entrySet()) {
                bb value = entry.getValue();
                if (value != null) {
                    value.clear();
                    if (baVar.isConnected()) {
                        try {
                            baVar.hw().a(hVar, new ar(value));
                            if (Log.isLoggable("WearableClient", 2)) {
                                Log.d("WearableClient", "disconnect: removed: " + entry.getKey() + "/" + value);
                            }
                        } catch (RemoteException e2) {
                            Log.w("WearableClient", "disconnect: Didn't remove: " + entry.getKey() + "/" + value);
                        }
                    }
                }
            }
            this.agU.clear();
        }
    }

    public void ca(IBinder iBinder) {
        synchronized (this.agU) {
            af afVarBZ = af.a.bZ(iBinder);
            az.h hVar = new az.h();
            for (Map.Entry<T, bb> entry : this.agU.entrySet()) {
                bb value = entry.getValue();
                try {
                    afVarBZ.a(hVar, new com.google.android.gms.wearable.internal.b(value));
                    if (Log.isLoggable("WearableClient", 2)) {
                        Log.d("WearableClient", "onPostInitHandler: added: " + entry.getKey() + "/" + value);
                    }
                } catch (RemoteException e2) {
                    Log.d("WearableClient", "onPostInitHandler: Didn't add: " + entry.getKey() + "/" + value);
                }
            }
        }
    }
}
