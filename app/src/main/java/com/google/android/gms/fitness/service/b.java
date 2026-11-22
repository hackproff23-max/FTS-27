package com.google.android.gms.fitness.service;

import android.os.RemoteException;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.k;
import com.google.android.gms.internal.jx;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class b implements SensorEventDispatcher {
    private final k VW;

    b(k kVar) {
        this.VW = (k) jx.i(kVar);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public void publish(DataPoint dataPoint) throws RemoteException {
        this.VW.c(dataPoint);
    }

    @Override // com.google.android.gms.fitness.service.SensorEventDispatcher
    public void publish(List<DataPoint> dataPoints) throws RemoteException {
        Iterator<DataPoint> it = dataPoints.iterator();
        while (it.hasNext()) {
            publish(it.next());
        }
    }
}
