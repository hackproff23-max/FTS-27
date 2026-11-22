package com.google.android.gms.deviceconnection.features;

import com.google.android.gms.common.data.DataBuffer;

/* JADX INFO: loaded from: classes.dex */
public class DeviceFeatureBuffer extends DataBuffer<DeviceFeature> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.DataBuffer
    public DeviceFeature get(int position) {
        return new a(this.JG, position);
    }
}
