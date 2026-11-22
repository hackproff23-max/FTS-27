package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

/* JADX INFO: loaded from: classes.dex */
public interface mu extends IInterface {

    public static abstract class a extends Binder implements mu {
        public a() {
            attachInterface(this, "com.google.android.gms.fitness.internal.service.IFitnessSensorService");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                    a(parcel.readInt() != 0 ? mq.CREATOR.createFromParcel(parcel) : null, lx.a.at(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                    a(parcel.readInt() != 0 ? FitnessSensorServiceRequest.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                    a(parcel.readInt() != 0 ? ms.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()));
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a(FitnessSensorServiceRequest fitnessSensorServiceRequest, md mdVar) throws RemoteException;

    void a(mq mqVar, lx lxVar) throws RemoteException;

    void a(ms msVar, md mdVar) throws RemoteException;
}
