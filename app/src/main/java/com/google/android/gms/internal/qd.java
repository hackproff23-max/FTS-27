package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;

/* JADX INFO: loaded from: classes.dex */
public interface qd extends IInterface {

    public static abstract class a extends Binder implements qd {

        /* JADX INFO: renamed from: com.google.android.gms.internal.qd$a$a, reason: collision with other inner class name */
        private static class C0123a implements qd {
            private IBinder le;

            C0123a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // com.google.android.gms.internal.qd
            public qa a(com.google.android.gms.dynamic.d dVar, com.google.android.gms.dynamic.c cVar, WalletFragmentOptions walletFragmentOptions, qb qbVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    parcelObtain.writeStrongBinder(cVar != null ? cVar.asBinder() : null);
                    if (walletFragmentOptions != null) {
                        parcelObtain.writeInt(1);
                        walletFragmentOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(qbVar != null ? qbVar.asBinder() : null);
                    this.le.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return qa.a.bP(parcelObtain2.readStrongBinder());
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

        public static qd bS(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof qd)) ? new C0123a(iBinder) : (qd) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    qa qaVarA = a(com.google.android.gms.dynamic.d.a.ap(data.readStrongBinder()), com.google.android.gms.dynamic.c.a.ao(data.readStrongBinder()), data.readInt() != 0 ? WalletFragmentOptions.CREATOR.createFromParcel(data) : null, qb.a.bQ(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeStrongBinder(qaVarA != null ? qaVarA.asBinder() : null);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.wallet.internal.IWalletDynamiteCreator");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    qa a(com.google.android.gms.dynamic.d dVar, com.google.android.gms.dynamic.c cVar, WalletFragmentOptions walletFragmentOptions, qb qbVar) throws RemoteException;
}
