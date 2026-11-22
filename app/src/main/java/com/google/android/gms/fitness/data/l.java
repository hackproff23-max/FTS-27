package com.google.android.gms.fitness.data;

import android.os.RemoteException;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.internal.jx;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l extends k.a {
    private final OnDataPointListener UF;

    public static class a {
        private static final a UG = new a();
        private final Map<OnDataPointListener, l> UH = new HashMap();

        private a() {
        }

        public static a jG() {
            return UG;
        }

        public l a(OnDataPointListener onDataPointListener) {
            l lVar;
            synchronized (this.UH) {
                lVar = this.UH.get(onDataPointListener);
                if (lVar == null) {
                    lVar = new l(onDataPointListener);
                    this.UH.put(onDataPointListener, lVar);
                }
            }
            return lVar;
        }

        public l b(OnDataPointListener onDataPointListener) {
            l lVar;
            synchronized (this.UH) {
                lVar = this.UH.get(onDataPointListener);
            }
            return lVar;
        }

        public l c(OnDataPointListener onDataPointListener) {
            l lVarRemove;
            synchronized (this.UH) {
                lVarRemove = this.UH.remove(onDataPointListener);
                if (lVarRemove == null) {
                    lVarRemove = new l(onDataPointListener);
                }
            }
            return lVarRemove;
        }
    }

    private l(OnDataPointListener onDataPointListener) {
        this.UF = (OnDataPointListener) jx.i(onDataPointListener);
    }

    @Override // com.google.android.gms.fitness.data.k
    public void c(DataPoint dataPoint) throws RemoteException {
        this.UF.onDataPoint(dataPoint);
    }
}
