package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/* JADX INFO: loaded from: classes.dex */
class InterstitialAd extends CachedAd implements Parcelable, Externalizable {
    static final String EXTRA_AD_URL = "EXTRA_AD_URL";
    static final String EXTRA_CONTENT = "EXTRA_CONTENT";
    static final long serialVersionUID = 5158660334173309853L;
    String adUrl;
    String content;
    HttpMMHeaders mmHeaders;
    private static final String TAG = InterstitialAd.class.getName();
    public static final Parcelable.Creator<InterstitialAd> CREATOR = new Parcelable.Creator<InterstitialAd>() { // from class: com.millennialmedia.android.InterstitialAd.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InterstitialAd createFromParcel(Parcel in) {
            return new InterstitialAd(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InterstitialAd[] newArray(int size) {
            return new InterstitialAd[size];
        }
    };

    public InterstitialAd() {
    }

    InterstitialAd(Parcel in) {
        super(in);
        try {
            this.content = in.readString();
            this.adUrl = in.readString();
            this.mmHeaders = (HttpMMHeaders) in.readParcelable(HttpMMHeaders.class.getClassLoader());
        } catch (Exception e) {
            MMLog.e(TAG, "Interstitial problem reading parcel: ", e);
        }
    }

    @Override // com.millennialmedia.android.CachedAd
    int getType() {
        return 2;
    }

    @Override // com.millennialmedia.android.CachedAd
    String getTypeString() {
        return "Interstitial";
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean saveAssets(Context context) {
        return true;
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean download(Context context) {
        return true;
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean isOnDisk(Context context) {
        return true;
    }

    @Override // com.millennialmedia.android.CachedAd
    boolean canShow(Context context, MMAdImpl adImpl, boolean checkDeferredViewStart) {
        if (checkDeferredViewStart) {
            return this.content != null && this.content.length() > 0 && this.adUrl != null && this.adUrl.length() > 0 && HandShake.sharedHandShake(context).canDisplayCachedAd(adImpl.adType, this.deferredViewStart);
        }
        return this.content != null && this.content.length() > 0 && this.adUrl != null && this.adUrl.length() > 0;
    }

    @Override // com.millennialmedia.android.CachedAd
    void show(Context context, long adImplInternalId) {
        Utils.IntentUtils.startAdViewOverlayActivity(context, getExpandExtrasIntent(context, adImplInternalId));
    }

    private Intent getExpandExtrasIntent(Context context, long adImplInternalId) {
        Intent intent = new Intent();
        OverlaySettings settings = new OverlaySettings();
        settings.creatorAdImplId = adImplInternalId;
        settings.content = this.content;
        settings.adUrl = this.adUrl;
        settings.setWebMMHeaders(this.mmHeaders);
        settings.isFromInterstitial = true;
        intent.putExtra("settings", settings);
        intent.putExtra("internalId", adImplInternalId);
        return intent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.millennialmedia.android.CachedAd, android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.content);
        dest.writeString(this.adUrl);
        dest.writeParcelable(this.mmHeaders, flags);
    }

    @Override // com.millennialmedia.android.CachedAd, java.io.Externalizable
    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        super.readExternal(input);
        this.content = (String) input.readObject();
        this.adUrl = (String) input.readObject();
        this.mmHeaders = (HttpMMHeaders) input.readObject();
    }

    @Override // com.millennialmedia.android.CachedAd, java.io.Externalizable
    public void writeExternal(ObjectOutput output) throws IOException {
        super.writeExternal(output);
        output.writeObject(this.content);
        output.writeObject(this.adUrl);
        output.writeObject(this.mmHeaders);
    }
}
