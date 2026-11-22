package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.la;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.p;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class LoyaltyWalletObject implements SafeParcelable {
    public static final Parcelable.Creator<LoyaltyWalletObject> CREATOR = new j();
    private final int CK;
    String aki;
    String avd;
    String ave;
    String avf;
    String avg;
    String avh;
    String avi;
    String avj;
    String avk;
    ArrayList<p> avl;
    com.google.android.gms.wallet.wobs.l avm;
    ArrayList<LatLng> avn;
    String avo;
    String avp;
    ArrayList<com.google.android.gms.wallet.wobs.d> avq;
    boolean avr;
    ArrayList<com.google.android.gms.wallet.wobs.n> avs;
    ArrayList<com.google.android.gms.wallet.wobs.j> avt;
    ArrayList<com.google.android.gms.wallet.wobs.n> avu;
    com.google.android.gms.wallet.wobs.f avv;
    String fl;
    int state;

    LoyaltyWalletObject() {
        this.CK = 4;
        this.avl = la.ie();
        this.avn = la.ie();
        this.avq = la.ie();
        this.avs = la.ie();
        this.avt = la.ie();
        this.avu = la.ie();
    }

    LoyaltyWalletObject(int versionCode, String id, String accountId, String issuerName, String programName, String accountName, String barcodeAlternateText, String barcodeType, String barcodeValue, String barcodeLabel, String classId, int state, ArrayList<p> messages, com.google.android.gms.wallet.wobs.l validTimeInterval, ArrayList<LatLng> locations, String infoModuleDataHexFontColor, String infoModuleDataHexBackgroundColor, ArrayList<com.google.android.gms.wallet.wobs.d> infoModuleDataLabelValueRows, boolean infoModuleDataShowLastUpdateTime, ArrayList<com.google.android.gms.wallet.wobs.n> imageModuleDataMainImageUris, ArrayList<com.google.android.gms.wallet.wobs.j> textModulesData, ArrayList<com.google.android.gms.wallet.wobs.n> linksModuleDataUris, com.google.android.gms.wallet.wobs.f loyaltyPoints) {
        this.CK = versionCode;
        this.fl = id;
        this.avd = accountId;
        this.ave = issuerName;
        this.avf = programName;
        this.aki = accountName;
        this.avg = barcodeAlternateText;
        this.avh = barcodeType;
        this.avi = barcodeValue;
        this.avj = barcodeLabel;
        this.avk = classId;
        this.state = state;
        this.avl = messages;
        this.avm = validTimeInterval;
        this.avn = locations;
        this.avo = infoModuleDataHexFontColor;
        this.avp = infoModuleDataHexBackgroundColor;
        this.avq = infoModuleDataLabelValueRows;
        this.avr = infoModuleDataShowLastUpdateTime;
        this.avs = imageModuleDataMainImageUris;
        this.avt = textModulesData;
        this.avu = linksModuleDataUris;
        this.avv = loyaltyPoints;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAccountId() {
        return this.avd;
    }

    public String getAccountName() {
        return this.aki;
    }

    public String getBarcodeAlternateText() {
        return this.avg;
    }

    public String getBarcodeType() {
        return this.avh;
    }

    public String getBarcodeValue() {
        return this.avi;
    }

    public String getId() {
        return this.fl;
    }

    public String getIssuerName() {
        return this.ave;
    }

    public String getProgramName() {
        return this.avf;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        j.a(this, dest, flags);
    }
}
