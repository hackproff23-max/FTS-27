package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.util.DateAndTime;

/* JADX INFO: loaded from: classes.dex */
public class WebViewAdUrlGenerator extends AdUrlGenerator {
    private final boolean mIsStorePictureSupported;

    public WebViewAdUrlGenerator(Context context, boolean isStorePictureSupported) {
        super(context);
        this.mIsStorePictureSupported = isStorePictureSupported;
    }

    @Override // com.mopub.common.BaseUrlGenerator
    public String generateUrlString(String serverHostname) throws Throwable {
        initUrlString(serverHostname, Constants.AD_HANDLER);
        ClientMetadata clientMetadata = ClientMetadata.getInstance(this.mContext);
        setApiVersion("6");
        setAdUnitId(this.mAdUnitId);
        setSdkVersion(clientMetadata.getSdkVersion());
        setDeviceInfo(clientMetadata.getDeviceManufacturer(), clientMetadata.getDeviceModel(), clientMetadata.getDeviceProduct());
        setKeywords(this.mKeywords);
        setLocation(this.mLocation);
        setTimezone(DateAndTime.getTimeZoneOffsetString());
        setOrientation(clientMetadata.getOrientationString());
        setDensity(clientMetadata.getDensity());
        setMraidFlag(true);
        String networkOperator = clientMetadata.getNetworkOperatorForUrl();
        setMccCode(networkOperator);
        setMncCode(networkOperator);
        setIsoCountryCode(clientMetadata.getIsoCountryCode());
        setCarrierName(clientMetadata.getNetworkOperatorName());
        setNetworkType(clientMetadata.getActiveNetworkType());
        setAppVersion(clientMetadata.getAppVersion());
        setExternalStoragePermission(this.mIsStorePictureSupported);
        appendAdvertisingInfoTemplates();
        return getFinalUrlString();
    }
}
