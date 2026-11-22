package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.internal.jx;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a extends l.a {
    private final BleScanCallback VC;

    /* JADX INFO: renamed from: com.google.android.gms.fitness.request.a$a, reason: collision with other inner class name */
    public static class C0061a {
        private static final C0061a VD = new C0061a();
        private final Map<BleScanCallback, a> VE = new HashMap();

        private C0061a() {
        }

        public static C0061a jP() {
            return VD;
        }

        public a a(BleScanCallback bleScanCallback) {
            a aVar;
            synchronized (this.VE) {
                aVar = this.VE.get(bleScanCallback);
                if (aVar == null) {
                    aVar = new a(bleScanCallback);
                    this.VE.put(bleScanCallback, aVar);
                }
            }
            return aVar;
        }

        public a b(BleScanCallback bleScanCallback) {
            a aVar;
            synchronized (this.VE) {
                aVar = this.VE.get(bleScanCallback);
                if (aVar == null) {
                    aVar = new a(bleScanCallback);
                }
            }
            return aVar;
        }
    }

    private a(BleScanCallback bleScanCallback) {
        this.VC = (BleScanCallback) jx.i(bleScanCallback);
    }

    @Override // com.google.android.gms.fitness.request.l
    public void onDeviceFound(BleDevice device) throws RemoteException {
        this.VC.onDeviceFound(device);
    }

    @Override // com.google.android.gms.fitness.request.l
    public void onScanStopped() throws RemoteException {
        this.VC.onScanStopped();
    }
}
