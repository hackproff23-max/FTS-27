package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.util.DateAndTime;

/* JADX INFO: loaded from: classes.dex */
class NativeUrlGenerator extends AdUrlGenerator {

    @Nullable
    private String mDesiredAssets;

    @Nullable
    private String mSequenceNumber;

    NativeUrlGenerator(Context context) {
        super(context);
    }

    @Override // com.mopub.common.AdUrlGenerator
    @NonNull
    public NativeUrlGenerator withAdUnitId(String adUnitId) {
        this.mAdUnitId = adUnitId;
        return this;
    }

    @NonNull
    NativeUrlGenerator withRequest(@Nullable RequestParameters requestParameters) {
        if (requestParameters != null) {
            this.mKeywords = requestParameters.getKeywords();
            this.mLocation = requestParameters.getLocation();
            this.mDesiredAssets = requestParameters.getDesiredAssets();
        }
        return this;
    }

    @NonNull
    NativeUrlGenerator withSequenceNumber(int sequenceNumber) {
        this.mSequenceNumber = String.valueOf(sequenceNumber);
        return this;
    }

    @Override // com.mopub.common.BaseUrlGenerator
    public String generateUrlString(String serverHostname) throws Throwable {
        initUrlString(serverHostname, Constants.AD_HANDLER);
        setAdUnitId(this.mAdUnitId);
        setKeywords(this.mKeywords);
        setLocation(this.mLocation);
        ClientMetadata clientMetadata = ClientMetadata.getInstance(this.mContext);
        setSdkVersion(clientMetadata.getSdkVersion());
        setDeviceInfo(clientMetadata.getDeviceManufacturer(), clientMetadata.getDeviceModel(), clientMetadata.getDeviceProduct());
        setTimezone(DateAndTime.getTimeZoneOffsetString());
        setOrientation(clientMetadata.getOrientationString());
        setDensity(clientMetadata.getDensity());
        String networkOperator = clientMetadata.getNetworkOperatorForUrl();
        setMccCode(networkOperator);
        setMncCode(networkOperator);
        setIsoCountryCode(clientMetadata.getIsoCountryCode());
        setCarrierName(clientMetadata.getNetworkOperatorName());
        setNetworkType(clientMetadata.getActiveNetworkType());
        setAppVersion(clientMetadata.getAppVersion());
        setDesiredAssets();
        setSequenceNumber();
        appendAdvertisingInfoTemplates();
        return getFinalUrlString();
    }

    private void setSequenceNumber() {
        if (!TextUtils.isEmpty(this.mSequenceNumber)) {
            addParam("MAGIC_NO", this.mSequenceNumber);
        }
    }

    private void setDesiredAssets() {
        if (!TextUtils.isEmpty(this.mDesiredAssets)) {
            addParam("assets", this.mDesiredAssets);
        }
    }

    @Override // com.mopub.common.AdUrlGenerator
    protected void setSdkVersion(String sdkVersion) {
        addParam("nsv", sdkVersion);
    }
}
