package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface bw extends IInterface {

    public static abstract class a extends Binder implements bw {
        public a() {
            attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    j(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    aw();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    String strBB = bB();
                    reply.writeNoException();
                    reply.writeString(strBB);
                    return true;
                case 4:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    com.google.android.gms.dynamic.d dVarBC = bC();
                    reply.writeNoException();
                    reply.writeStrongBinder(dVarBC != null ? dVarBC.asBinder() : null);
                    return true;
                case 5:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    String body = getBody();
                    reply.writeNoException();
                    reply.writeString(body);
                    return true;
                case 6:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    com.google.android.gms.dynamic.d dVarBD = bD();
                    reply.writeNoException();
                    reply.writeStrongBinder(dVarBD != null ? dVarBD.asBinder() : null);
                    return true;
                case 7:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    String strBE = bE();
                    reply.writeNoException();
                    reply.writeString(strBE);
                    return true;
                case 8:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    double dBF = bF();
                    reply.writeNoException();
                    reply.writeDouble(dBF);
                    return true;
                case 9:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    String strBG = bG();
                    reply.writeNoException();
                    reply.writeString(strBG);
                    return true;
                case 10:
                    data.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    String strBH = bH();
                    reply.writeNoException();
                    reply.writeString(strBH);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.internal.formats.client.INativeAppInstallAd");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void aw() throws RemoteException;

    String bB() throws RemoteException;

    com.google.android.gms.dynamic.d bC() throws RemoteException;

    com.google.android.gms.dynamic.d bD() throws RemoteException;

    String bE() throws RemoteException;

    double bF() throws RemoteException;

    String bG() throws RemoteException;

    String bH() throws RemoteException;

    String getBody() throws RemoteException;

    void j(int i) throws RemoteException;
}
