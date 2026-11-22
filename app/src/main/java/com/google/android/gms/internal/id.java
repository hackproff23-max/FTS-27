package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.FitnessStatusCodes;

/* JADX INFO: loaded from: classes.dex */
public interface id extends IInterface {

    public static abstract class a extends Binder implements id {

        /* JADX INFO: renamed from: com.google.android.gms.internal.id$a$a, reason: collision with other inner class name */
        private static class C0089a implements id {
            private IBinder le;

            C0089a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // com.google.android.gms.internal.id
            public void a(ic icVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    this.le.transact(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public void a(ic icVar, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    this.le.transact(FitnessStatusCodes.APP_MISMATCH, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public void a(ic icVar, int i, String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    this.le.transact(FitnessStatusCodes.MISSING_BLE_PERMISSION, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public void a(ic icVar, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    this.le.transact(FitnessStatusCodes.DATA_TYPE_NOT_FOUND, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.le;
            }

            @Override // com.google.android.gms.internal.id
            public void b(ic icVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    this.le.transact(FitnessStatusCodes.TRANSIENT_ERROR, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public void b(ic icVar, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    parcelObtain.writeInt(i);
                    this.le.transact(FitnessStatusCodes.UNSUPPORTED_PLATFORM, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public void c(ic icVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    parcelObtain.writeStrongBinder(icVar != null ? icVar.asBinder() : null);
                    this.le.transact(FitnessStatusCodes.EQUIVALENT_SESSION_ENDED, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public int fL() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    this.le.transact(FitnessStatusCodes.CONFLICTING_DATA_TYPE, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.id
            public int fM() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.appstate.internal.IAppStateService");
                    this.le.transact(FitnessStatusCodes.INCONSISTENT_DATA_TYPE, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static id J(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.appstate.internal.IAppStateService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof id)) ? new C0089a(iBinder) : (id) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case FitnessStatusCodes.CONFLICTING_DATA_TYPE /* 5001 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    int iFL = fL();
                    reply.writeNoException();
                    reply.writeInt(iFL);
                    return true;
                case FitnessStatusCodes.INCONSISTENT_DATA_TYPE /* 5002 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    int iFM = fM();
                    reply.writeNoException();
                    reply.writeInt(iFM);
                    return true;
                case FitnessStatusCodes.DATA_TYPE_NOT_FOUND /* 5003 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    a(ic.a.I(data.readStrongBinder()), data.readInt(), data.createByteArray());
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.APP_MISMATCH /* 5004 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    a(ic.a.I(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.UNKNOWN_AUTH_ERROR /* 5005 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    a(ic.a.I(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.MISSING_BLE_PERMISSION /* 5006 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    a(ic.a.I(data.readStrongBinder()), data.readInt(), data.readString(), data.createByteArray());
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.UNSUPPORTED_PLATFORM /* 5007 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    b(ic.a.I(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.TRANSIENT_ERROR /* 5008 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    b(ic.a.I(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case FitnessStatusCodes.EQUIVALENT_SESSION_ENDED /* 5009 */:
                    data.enforceInterface("com.google.android.gms.appstate.internal.IAppStateService");
                    c(ic.a.I(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.appstate.internal.IAppStateService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ic icVar) throws RemoteException;

    void a(ic icVar, int i) throws RemoteException;

    void a(ic icVar, int i, String str, byte[] bArr) throws RemoteException;

    void a(ic icVar, int i, byte[] bArr) throws RemoteException;

    void b(ic icVar) throws RemoteException;

    void b(ic icVar, int i) throws RemoteException;

    void c(ic icVar) throws RemoteException;

    int fL() throws RemoteException;

    int fM() throws RemoteException;
}
