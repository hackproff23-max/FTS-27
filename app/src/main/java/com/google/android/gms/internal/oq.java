package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface oq extends IInterface {

    public static abstract class a extends Binder implements oq {

        /* JADX INFO: renamed from: com.google.android.gms.internal.oq$a$a, reason: collision with other inner class name */
        private static class C0118a implements oq {
            private IBinder le;

            C0118a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // com.google.android.gms.internal.oq
            public void a(String str, ov ovVar, or orVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    parcelObtain.writeString(str);
                    if (ovVar != null) {
                        parcelObtain.writeInt(1);
                        ovVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (orVar != null) {
                        parcelObtain.writeInt(1);
                        orVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.le.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.oq
            public void a(String str, ov ovVar, List<or> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    parcelObtain.writeString(str);
                    if (ovVar != null) {
                        parcelObtain.writeInt(1);
                        ovVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    this.le.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.oq
            public void a(String str, ov ovVar, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
                    parcelObtain.writeString(str);
                    if (ovVar != null) {
                        parcelObtain.writeInt(1);
                        ovVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeByteArray(bArr);
                    this.le.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.le;
            }
        }

        public static oq bI(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof oq)) ? new C0118a(iBinder) : (oq) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 2:
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    a(parcel.readString(), parcel.readInt() != 0 ? ov.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? or.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    a(parcel.readString(), parcel.readInt() != 0 ? ov.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(or.CREATOR));
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
                    a(parcel.readString(), parcel.readInt() != 0 ? ov.CREATOR.createFromParcel(parcel) : null, parcel.createByteArray());
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a(String str, ov ovVar, or orVar) throws RemoteException;

    void a(String str, ov ovVar, List<or> list) throws RemoteException;

    void a(String str, ov ovVar, byte[] bArr) throws RemoteException;
}
