package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cx extends cy.a {
    private Map<Class<? extends NetworkExtras>, NetworkExtras> qT;

    private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> cz z(String str) throws RemoteException {
        try {
            Class<?> cls = Class.forName(str, false, cx.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(cls)) {
                MediationAdapter mediationAdapter = (MediationAdapter) cls.newInstance();
                return new de(mediationAdapter, (com.google.ads.mediation.NetworkExtras) this.qT.get(mediationAdapter.getAdditionalParametersType()));
            }
            if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(cls)) {
                return new dc((com.google.android.gms.ads.mediation.MediationAdapter) cls.newInstance());
            }
            gr.W("Could not instantiate mediation adapter: " + str + " (not a valid adapter).");
            throw new RemoteException();
        } catch (Throwable th) {
            gr.W("Could not instantiate mediation adapter: " + str + ". " + th.getMessage());
            throw new RemoteException();
        }
    }

    public void d(Map<Class<? extends NetworkExtras>, NetworkExtras> map) {
        this.qT = map;
    }

    @Override // com.google.android.gms.internal.cy
    public cz x(String str) throws RemoteException {
        return z(str);
    }

    @Override // com.google.android.gms.internal.cy
    public boolean y(String str) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(str, false, cx.class.getClassLoader()));
        } catch (Throwable th) {
            gr.W("Could not load custom event implementation class: " + str + ", assuming old implementation.");
            return false;
        }
    }
}
