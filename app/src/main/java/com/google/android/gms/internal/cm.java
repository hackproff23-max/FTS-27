package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface cm extends IInterface {

    public static abstract class a extends Binder implements cm {

        /* JADX INFO: renamed from: com.google.android.gms.internal.cm$a$a, reason: collision with other inner class name */
        private static class C0071a implements cm {
            private IBinder le;

            C0071a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.le;
            }

            @Override // com.google.android.gms.internal.cm
            public Bundle bN() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
                    this.le.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static cm m(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof cm)) ? new C0071a(iBinder) : (cm) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
                    Bundle bundleBN = bN();
                    reply.writeNoException();
                    if (bundleBN == null) {
                        reply.writeInt(0);
                        return true;
                    }
                    reply.writeInt(1);
                    bundleBN.writeToParcel(reply, 1);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.internal.gservice.IGservicesValueService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Bundle bN() throws RemoteException;
}
