package com.google.android.gms.wearable;

import android.net.Uri;
import android.util.Log;
import com.google.android.gms.internal.ql;
import com.google.android.gms.internal.qw;

/* JADX INFO: loaded from: classes.dex */
public class PutDataMapRequest {
    private final DataMap axh = new DataMap();
    private final PutDataRequest axi;

    private PutDataMapRequest(PutDataRequest putDataRequest, DataMap dataMap) {
        this.axi = putDataRequest;
        if (dataMap != null) {
            this.axh.putAll(dataMap);
        }
    }

    public static PutDataMapRequest create(String path) {
        return new PutDataMapRequest(PutDataRequest.create(path), null);
    }

    public static PutDataMapRequest createFromDataMapItem(DataMapItem source) {
        return new PutDataMapRequest(PutDataRequest.k(source.getUri()), source.getDataMap());
    }

    public static PutDataMapRequest createWithAutoAppendedId(String pathPrefix) {
        return new PutDataMapRequest(PutDataRequest.createWithAutoAppendedId(pathPrefix), null);
    }

    public PutDataRequest asPutDataRequest() {
        ql.a aVarA = ql.a(this.axh);
        this.axi.setData(qw.f(aVarA.ayo));
        int size = aVarA.ayp.size();
        for (int i = 0; i < size; i++) {
            String string = Integer.toString(i);
            Asset asset = aVarA.ayp.get(i);
            if (string == null) {
                throw new IllegalStateException("asset key cannot be null: " + asset);
            }
            if (asset == null) {
                throw new IllegalStateException("asset cannot be null: key=" + string);
            }
            if (Log.isLoggable(DataMap.TAG, 3)) {
                Log.d(DataMap.TAG, "asPutDataRequest: adding asset: " + string + " " + asset);
            }
            this.axi.putAsset(string, asset);
        }
        return this.axi;
    }

    public DataMap getDataMap() {
        return this.axh;
    }

    public Uri getUri() {
        return this.axi.getUri();
    }
}
