package com.flurry.android.ads;

import com.flurry.sdk.e;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class FlurryAdTargeting extends e {
    @Override // com.flurry.sdk.e
    public void setLocation(float f, float f2) {
        super.setLocation(f, f2);
    }

    @Override // com.flurry.sdk.e
    public void clearLocation() {
        super.clearLocation();
    }

    @Override // com.flurry.sdk.e
    public void setUserCookies(Map<String, String> map) {
        super.setUserCookies(map);
    }

    @Override // com.flurry.sdk.e
    public void clearUserCookies() {
        super.clearUserCookies();
    }

    @Override // com.flurry.sdk.e
    public void setKeywords(Map<String, String> map) {
        super.setKeywords(map);
    }

    @Override // com.flurry.sdk.e
    public void clearKeywords() {
        super.clearKeywords();
    }

    @Override // com.flurry.sdk.e
    public void setAge(int i) {
        super.setAge(i);
    }

    @Override // com.flurry.sdk.e
    public void clearAge() {
        super.clearAge();
    }

    public void setGender(FlurryGender flurryGender) {
        super.setGender(flurryGender == null ? FlurryGender.UNKNOWN.ordinal() : flurryGender.ordinal());
    }

    @Override // com.flurry.sdk.e
    public void clearGender() {
        super.clearGender();
    }

    @Override // com.flurry.sdk.e
    public void setEnableTestAds(boolean z) {
        super.setEnableTestAds(z);
    }
}
