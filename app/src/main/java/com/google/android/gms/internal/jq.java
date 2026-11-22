package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface jq extends IInterface {

    public static abstract class a extends Binder implements jq {
        public a() {
            attachInterface(this, "com.google.android.gms.common.internal.IAccountAccessor");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 2:
                    data.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
                    Account accountHk = hk();
                    reply.writeNoException();
                    if (accountHk == null) {
                        reply.writeInt(0);
                        return true;
                    }
                    reply.writeInt(1);
                    accountHk.writeToParcel(reply, 1);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.IAccountAccessor");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Account hk() throws RemoteException;
}
