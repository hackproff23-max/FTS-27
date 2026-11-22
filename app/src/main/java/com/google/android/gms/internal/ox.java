package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ox extends jl<oq> {
    private final String CS;
    private final ou anm;
    private final os ann;
    private boolean ano;
    private final Object mH;

    public ox(Context context, ou ouVar) {
        super(context, ouVar, ouVar, new String[0]);
        this.CS = context.getPackageName();
        this.anm = (ou) jx.i(ouVar);
        this.anm.a(this);
        this.ann = new os();
        this.mH = new Object();
        this.ano = true;
    }

    private void c(ov ovVar, or orVar) {
        this.ann.a(ovVar, orVar);
    }

    private void d(ov ovVar, or orVar) {
        try {
            or();
            hw().a(this.CS, ovVar, orVar);
        } catch (RemoteException e) {
            Log.e("PlayLoggerImpl", "Couldn't send log event.  Will try caching.");
            c(ovVar, orVar);
        } catch (IllegalStateException e2) {
            Log.e("PlayLoggerImpl", "Service was disconnected.  Will try caching.");
            c(ovVar, orVar);
        }
    }

    private void or() {
        ov ovVar;
        je.K(!this.ano);
        if (this.ann.isEmpty()) {
            return;
        }
        ov ovVar2 = null;
        try {
            ArrayList arrayList = new ArrayList();
            for (os.a aVar : this.ann.op()) {
                if (aVar.and != null) {
                    hw().a(this.CS, aVar.anb, qw.f(aVar.and));
                } else {
                    if (aVar.anb.equals(ovVar2)) {
                        arrayList.add(aVar.anc);
                        ovVar = ovVar2;
                    } else {
                        if (!arrayList.isEmpty()) {
                            hw().a(this.CS, ovVar2, arrayList);
                            arrayList.clear();
                        }
                        ov ovVar3 = aVar.anb;
                        arrayList.add(aVar.anc);
                        ovVar = ovVar3;
                    }
                    ovVar2 = ovVar;
                }
            }
            if (!arrayList.isEmpty()) {
                hw().a(this.CS, ovVar2, arrayList);
            }
            this.ann.clear();
        } catch (RemoteException e) {
            Log.e("PlayLoggerImpl", "Couldn't send cached log events to AndroidLog service.  Retaining in memory cache.");
        }
    }

    void U(boolean z) {
        synchronized (this.mH) {
            boolean z2 = this.ano;
            this.ano = z;
            if (z2 && !this.ano) {
                or();
            }
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        jtVar.f(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), new Bundle());
    }

    public void b(ov ovVar, or orVar) {
        synchronized (this.mH) {
            if (this.ano) {
                c(ovVar, orVar);
            } else {
                d(ovVar, orVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: bJ, reason: merged with bridge method [inline-methods] */
    public oq l(IBinder iBinder) {
        return oq.a.bI(iBinder);
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.playlog.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.playlog.internal.IPlayLogService";
    }

    public void start() {
        synchronized (this.mH) {
            if (isConnecting() || isConnected()) {
                return;
            }
            this.anm.T(true);
            connect();
        }
    }

    public void stop() {
        synchronized (this.mH) {
            this.anm.T(false);
            disconnect();
        }
    }
}
