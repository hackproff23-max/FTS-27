package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface be extends IInterface {

    public static abstract class a extends Binder implements be {

        /* JADX INFO: renamed from: com.google.android.gms.internal.be$a$a, reason: collision with other inner class name */
        private static class C0066a implements be {
            private IBinder le;

            C0066a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // com.google.android.gms.internal.be
            public IBinder a(com.google.android.gms.dynamic.d dVar, ay ayVar, String str, cy cyVar, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (ayVar != null) {
                        parcelObtain.writeInt(1);
                        ayVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(cyVar != null ? cyVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    this.le.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readStrongBinder();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.le;
            }
        }

        public static be g(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof be)) ? new C0066a(iBinder) : (be) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    IBinder iBinderA = a(com.google.android.gms.dynamic.d.a.ap(data.readStrongBinder()), data.readInt() != 0 ? ay.CREATOR.createFromParcel(data) : null, data.readString(), cy.a.n(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(iBinderA);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    IBinder a(com.google.android.gms.dynamic.d dVar, ay ayVar, String str, cy cyVar, int i) throws RemoteException;
}
