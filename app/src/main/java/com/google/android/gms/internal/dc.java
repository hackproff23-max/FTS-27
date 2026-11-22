package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dc extends cz.a {
    private final MediationAdapter qV;

    public dc(MediationAdapter mediationAdapter) {
        this.qV = mediationAdapter;
    }

    private Bundle a(String str, int i, String str2) throws RemoteException {
        gr.W("Server parameters: " + str);
        try {
            Bundle bundle = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                Bundle bundle2 = new Bundle();
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    bundle2.putString(next, jSONObject.getString(next));
                }
                bundle = bundle2;
            }
            if (this.qV instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                bundle.putInt("tagForChildDirectedTreatment", i);
            }
            return bundle;
        } catch (Throwable th) {
            gr.d("Could not get Server Parameters Bundle.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, av avVar, String str, da daVar) throws RemoteException {
        a(dVar, avVar, str, (String) null, daVar);
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, av avVar, String str, String str2, da daVar) throws RemoteException {
        if (!(this.qV instanceof MediationInterstitialAdapter)) {
            gr.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qV.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.qV;
            mediationInterstitialAdapter.requestInterstitialAd((Context) com.google.android.gms.dynamic.e.f(dVar), new dd(daVar), a(str, avVar.oh, str2), new db(new Date(avVar.od), avVar.oe, avVar.of != null ? new HashSet(avVar.of) : null, avVar.ol, avVar.og, avVar.oh), avVar.on != null ? avVar.on.getBundle(mediationInterstitialAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            gr.d("Could not request interstitial ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, ay ayVar, av avVar, String str, da daVar) throws RemoteException {
        a(dVar, ayVar, avVar, str, null, daVar);
    }

    @Override // com.google.android.gms.internal.cz
    public void a(com.google.android.gms.dynamic.d dVar, ay ayVar, av avVar, String str, String str2, da daVar) throws RemoteException {
        if (!(this.qV instanceof MediationBannerAdapter)) {
            gr.W("MediationAdapter is not a MediationBannerAdapter: " + this.qV.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.qV;
            mediationBannerAdapter.requestBannerAd((Context) com.google.android.gms.dynamic.e.f(dVar), new dd(daVar), a(str, avVar.oh, str2), com.google.android.gms.ads.a.a(ayVar.width, ayVar.height, ayVar.op), new db(new Date(avVar.od), avVar.oe, avVar.of != null ? new HashSet(avVar.of) : null, avVar.ol, avVar.og, avVar.oh), avVar.on != null ? avVar.on.getBundle(mediationBannerAdapter.getClass().getName()) : null);
        } catch (Throwable th) {
            gr.d("Could not request banner ad from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void destroy() throws RemoteException {
        try {
            this.qV.onDestroy();
        } catch (Throwable th) {
            gr.d("Could not destroy adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public com.google.android.gms.dynamic.d getView() throws RemoteException {
        if (!(this.qV instanceof MediationBannerAdapter)) {
            gr.W("MediationAdapter is not a MediationBannerAdapter: " + this.qV.getClass().getCanonicalName());
            throw new RemoteException();
        }
        try {
            return com.google.android.gms.dynamic.e.k(((MediationBannerAdapter) this.qV).getBannerView());
        } catch (Throwable th) {
            gr.d("Could not get banner view from adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void pause() throws RemoteException {
        try {
            this.qV.onPause();
        } catch (Throwable th) {
            gr.d("Could not pause adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void resume() throws RemoteException {
        try {
            this.qV.onResume();
        } catch (Throwable th) {
            gr.d("Could not resume adapter.", th);
            throw new RemoteException();
        }
    }

    @Override // com.google.android.gms.internal.cz
    public void showInterstitial() throws RemoteException {
        if (!(this.qV instanceof MediationInterstitialAdapter)) {
            gr.W("MediationAdapter is not a MediationInterstitialAdapter: " + this.qV.getClass().getCanonicalName());
            throw new RemoteException();
        }
        gr.S("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.qV).showInterstitial();
        } catch (Throwable th) {
            gr.d("Could not show interstitial from adapter.", th);
            throw new RemoteException();
        }
    }
}
