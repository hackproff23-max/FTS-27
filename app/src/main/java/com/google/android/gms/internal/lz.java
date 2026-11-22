package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;

/* JADX INFO: loaded from: classes.dex */
public interface lz extends IInterface {

    public static abstract class a extends Binder implements lz {

        /* JADX INFO: renamed from: com.google.android.gms.internal.lz$a$a, reason: collision with other inner class name */
        private static class C0103a implements lz {
            private IBinder le;

            C0103a(IBinder iBinder) {
                this.le = iBinder;
            }

            @Override // com.google.android.gms.internal.lz
            public void a(DataDeleteRequest dataDeleteRequest, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataDeleteRequest != null) {
                        parcelObtain.writeInt(1);
                        dataDeleteRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(DataReadRequest dataReadRequest, lw lwVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataReadRequest != null) {
                        parcelObtain.writeInt(1);
                        dataReadRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(lwVar != null ? lwVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(8, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(DataSourcesRequest dataSourcesRequest, lx lxVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataSourcesRequest != null) {
                        parcelObtain.writeInt(1);
                        dataSourcesRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(lxVar != null ? lxVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(DataTypeCreateRequest dataTypeCreateRequest, ly lyVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (dataTypeCreateRequest != null) {
                        parcelObtain.writeInt(1);
                        dataTypeCreateRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(lyVar != null ? lyVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(13, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(SessionInsertRequest sessionInsertRequest, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (sessionInsertRequest != null) {
                        parcelObtain.writeInt(1);
                        sessionInsertRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(9, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(SessionReadRequest sessionReadRequest, mb mbVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (sessionReadRequest != null) {
                        parcelObtain.writeInt(1);
                        sessionReadRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mbVar != null ? mbVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(10, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(StartBleScanRequest startBleScanRequest, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (startBleScanRequest != null) {
                        parcelObtain.writeInt(1);
                        startBleScanRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(15, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.aa aaVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (aaVar != null) {
                        parcelObtain.writeInt(1);
                        aaVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(21, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.ad adVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (adVar != null) {
                        parcelObtain.writeInt(1);
                        adVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(16, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.af afVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (afVar != null) {
                        parcelObtain.writeInt(1);
                        afVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.ah ahVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ahVar != null) {
                        parcelObtain.writeInt(1);
                        ahVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(18, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.aj ajVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (ajVar != null) {
                        parcelObtain.writeInt(1);
                        ajVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(5, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.b bVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (bVar != null) {
                        parcelObtain.writeInt(1);
                        bVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(17, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.e eVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (eVar != null) {
                        parcelObtain.writeInt(1);
                        eVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(7, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.j jVar, ly lyVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (jVar != null) {
                        parcelObtain.writeInt(1);
                        jVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(lyVar != null ? lyVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(14, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.m mVar, ma maVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (mVar != null) {
                        parcelObtain.writeInt(1);
                        mVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(maVar != null ? maVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(6, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.o oVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (oVar != null) {
                        parcelObtain.writeInt(1);
                        oVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(2, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.q qVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (qVar != null) {
                        parcelObtain.writeInt(1);
                        qVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.u uVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (uVar != null) {
                        parcelObtain.writeInt(1);
                        uVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(20, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.w wVar, md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (wVar != null) {
                        parcelObtain.writeInt(1);
                        wVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(11, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(com.google.android.gms.fitness.request.y yVar, mc mcVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    if (yVar != null) {
                        parcelObtain.writeInt(1);
                        yVar.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(mcVar != null ? mcVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(12, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(22, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.google.android.gms.internal.lz
            public void a(mp mpVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    parcelObtain.writeStrongBinder(mpVar != null ? mpVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(24, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.le;
            }

            @Override // com.google.android.gms.internal.lz
            public void b(md mdVar, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    parcelObtain.writeStrongBinder(mdVar != null ? mdVar.asBinder() : null);
                    parcelObtain.writeString(str);
                    this.le.transact(23, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static lz av(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof lz)) ? new C0103a(iBinder) : (lz) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? DataSourcesRequest.CREATOR.createFromParcel(parcel) : null, lx.a.at(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.o.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.q.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.af.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.aj.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.m.CREATOR.createFromParcel(parcel) : null, ma.a.aw(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.e.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? DataReadRequest.CREATOR.createFromParcel(parcel) : null, lw.a.as(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? SessionInsertRequest.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? SessionReadRequest.CREATOR.createFromParcel(parcel) : null, mb.a.ax(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 11:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.w.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 12:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.y.CREATOR.createFromParcel(parcel) : null, mc.a.ay(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 13:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? DataTypeCreateRequest.CREATOR.createFromParcel(parcel) : null, ly.a.au(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 14:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.j.CREATOR.createFromParcel(parcel) : null, ly.a.au(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 15:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? StartBleScanRequest.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 16:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.ad.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 17:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.b.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.ah.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 19:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? DataDeleteRequest.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.u.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(parcel.readInt() != 0 ? com.google.android.gms.fitness.request.aa.CREATOR.createFromParcel(parcel) : null, md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    b(md.a.az(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                    parcel.enforceInterface("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    a(mp.a.aA(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.fitness.internal.IGoogleFitnessService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a(DataDeleteRequest dataDeleteRequest, md mdVar, String str) throws RemoteException;

    void a(DataReadRequest dataReadRequest, lw lwVar, String str) throws RemoteException;

    void a(DataSourcesRequest dataSourcesRequest, lx lxVar, String str) throws RemoteException;

    void a(DataTypeCreateRequest dataTypeCreateRequest, ly lyVar, String str) throws RemoteException;

    void a(SessionInsertRequest sessionInsertRequest, md mdVar, String str) throws RemoteException;

    void a(SessionReadRequest sessionReadRequest, mb mbVar, String str) throws RemoteException;

    void a(StartBleScanRequest startBleScanRequest, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.aa aaVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.ad adVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.af afVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.ah ahVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.aj ajVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.b bVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.e eVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.j jVar, ly lyVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.m mVar, ma maVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.o oVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.q qVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.u uVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.w wVar, md mdVar, String str) throws RemoteException;

    void a(com.google.android.gms.fitness.request.y yVar, mc mcVar, String str) throws RemoteException;

    void a(md mdVar, String str) throws RemoteException;

    void a(mp mpVar, String str) throws RemoteException;

    void b(md mdVar, String str) throws RemoteException;
}
