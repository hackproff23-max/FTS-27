package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wallet.wobs.CommonWalletObject;

/* JADX INFO: loaded from: classes.dex */
public final class OfferWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<OfferWalletObject> CREATOR = new n();
    private final int CK;
    String avN;
    CommonWalletObject avO;
    String fl;

    OfferWalletObject() {
        this.CK = 3;
    }

    OfferWalletObject(int versionCode, String id, String redemptionCode, CommonWalletObject commonWalletObject) {
        this.CK = versionCode;
        this.avN = redemptionCode;
        if (versionCode < 3) {
            this.avO = CommonWalletObject.rh().dh(id).ri();
        } else {
            this.avO = commonWalletObject;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.avO.getId();
    }

    public String getRedemptionCode() {
        return this.avN;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        n.a(this, dest, flags);
    }
}
