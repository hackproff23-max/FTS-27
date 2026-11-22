package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface bx extends IInterface {

    public static abstract class a extends Binder implements bx {
        public a() {
            attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    j(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    aw();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    String strBB = bB();
                    reply.writeNoException();
                    reply.writeString(strBB);
                    return true;
                case 4:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    com.google.android.gms.dynamic.d dVarBC = bC();
                    reply.writeNoException();
                    reply.writeStrongBinder(dVarBC != null ? dVarBC.asBinder() : null);
                    return true;
                case 5:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    String body = getBody();
                    reply.writeNoException();
                    reply.writeString(body);
                    return true;
                case 6:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    com.google.android.gms.dynamic.d dVarBI = bI();
                    reply.writeNoException();
                    reply.writeStrongBinder(dVarBI != null ? dVarBI.asBinder() : null);
                    return true;
                case 7:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    String strBE = bE();
                    reply.writeNoException();
                    reply.writeString(strBE);
                    return true;
                case 8:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    String strBJ = bJ();
                    reply.writeNoException();
                    reply.writeString(strBJ);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void aw() throws RemoteException;

    String bB() throws RemoteException;

    com.google.android.gms.dynamic.d bC() throws RemoteException;

    String bE() throws RemoteException;

    com.google.android.gms.dynamic.d bI() throws RemoteException;

    String bJ() throws RemoteException;

    String getBody() throws RemoteException;

    void j(int i) throws RemoteException;
}
