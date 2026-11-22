package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class z extends aj {
    private static final String ID = com.google.android.gms.internal.a.DEVICE_ID.toString();
    private final Context mContext;

    public z(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String strY = Y(this.mContext);
        return strY == null ? di.rb() : di.u(strY);
    }

    protected String Y(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
