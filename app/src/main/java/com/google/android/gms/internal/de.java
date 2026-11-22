package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class de<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> extends cz.a {
    private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> qX;
    private final NETWORK_EXTRAS qY;

    public de(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mediationAdapter, NETWORK_EXTRAS network_extras) {
        this.qX = mediationAdapter;
        this.qY = network_extras;
    }

    private SERVER_PARAMETERS b(String str, int i, String str2) throws RemoteException {
        HashMap map;
        try {
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                map = new HashMap(jSONObject.length());
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.getString(next));
                }
            } else {
                map = new HashMap(0);
            }
            Class<SERVER_PARAMETERS> serverParametersType = this.qX.getServerParametersType();
            if (serverParametersType == null) {
                return null;
            }
            SERVER_PARAMETERS server_parametersNewInstance = serverParametersType.newInstance();
            server_parametersNewInstance.load(map);
            return server_parametersNewInstance;
        } catch (Throwable th) {
            gr.d("Could not get MediationServerParameters.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, av avVar, String str, da daVar) throws RemoteException {
        a(dVar, avVar, str, (String) null, daVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, av avVar, String str, String str2, da daVar) throws RemoteException {
        if (!(this.qX instanceof MediationInterstitialAdapter)) {
            gr.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qX.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.qX).requestInterstitialAd(new df(daVar), (Activity) com.google.android.gms.dynamic.e.f(dVar), b(str, avVar.oh, str2), dg.d(avVar), this.qY);
        } catch (Throwable th) {
            gr.d("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, ay ayVar, av avVar, String str, da daVar) throws RemoteException {
        a(dVar, ayVar, avVar, str, null, daVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, ay ayVar, av avVar, String str, String str2, da daVar) throws RemoteException {
        if (!(this.qX instanceof MediationBannerAdapter)) {
            gr.W("MediationAdapter is not a MediationBannerAdapter: " + this.qX.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Requesting banner ad from adapter.");
        try {
            ((MediationBannerAdapter) this.qX).requestBannerAd(new df(daVar), (Activity) com.google.android.gms.dynamic.e.f(dVar), b(str, avVar.oh, str2), dg.b(ayVar), dg.d(avVar), this.qY);
        } catch (Throwable th) {
            gr.d("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void destroy() throws RemoteException {
        try {
            this.qX.destroy();
        } catch (Throwable th) {
            gr.d("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public com.google.android.gms.dynamic.d getView() throws RemoteException {
        if (!(this.qX instanceof MediationBannerAdapter)) {
            gr.W("MediationAdapter is not a MediationBannerAdapter: " + this.qX.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return com.google.android.gms.dynamic.e.k(((MediationBannerAdapter) this.qX).getBannerView());
        } catch (Throwable th) {
            gr.d("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void pause() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.cz
    public void resume() throws RemoteException {
        throw new RemoteException();
    }

    @Override // com.google.android.gms.internal.cz
    public void showInterstitial() throws RemoteException {
        if (!(this.qX instanceof MediationInterstitialAdapter)) {
            gr.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qX.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.qX).showInterstitial();
        } catch (Throwable th) {
            gr.d("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }
}
