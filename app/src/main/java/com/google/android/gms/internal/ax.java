package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ax {
    public static final ax oo = new ax();

    private ax() {
    }

    public static ax bg() {
        return oo;
    }

    public av a(Context context, bg bgVar) {
        Date birthday = bgVar.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1L;
        String contentUrl = bgVar.getContentUrl();
        int gender = bgVar.getGender();
        Set<String> keywords = bgVar.getKeywords();
        List listUnmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean zIsTestDevice = bgVar.isTestDevice(context);
        int iBl = bgVar.bl();
        Location location = bgVar.getLocation();
        Bundle networkExtrasBundle = bgVar.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = bgVar.getManualImpressionsEnabled();
        String publisherProvidedId = bgVar.getPublisherProvidedId();
        SearchAdRequest searchAdRequestBi = bgVar.bi();
        return new av(4, time, networkExtrasBundle, gender, listUnmodifiableList, zIsTestDevice, iBl, manualImpressionsEnabled, publisherProvidedId, searchAdRequestBi != null ? new bj(searchAdRequestBi) : null, location, contentUrl, bgVar.bk());
    }
}
