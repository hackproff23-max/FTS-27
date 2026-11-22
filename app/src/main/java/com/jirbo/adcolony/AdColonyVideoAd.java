package com.jirbo.adcolony;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyVideoAd extends AdColonyInterstitialAd {
    public AdColonyVideoAd() {
    }

    public AdColonyVideoAd(String zone_id) {
        super(zone_id);
    }

    @Override // com.jirbo.adcolony.AdColonyInterstitialAd
    public AdColonyVideoAd withListener(AdColonyAdListener listener) {
        this.v = listener;
        return this;
    }
}
