package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentActionRef extends MultiDataBufferRef implements AppContentAction {
    AppContentActionRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 1, dataRow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return AppContentActionEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public Bundle getExtras() {
        return AppContentUtils.d(this.JG, this.XX, "action_data", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String getLabel() {
        return getString("action_label");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String getType() {
        return getString(ShareConstants.WEB_DIALOG_PARAM_ACTION_TYPE);
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return AppContentActionEntity.a(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public List<AppContentCondition> kH() {
        return AppContentUtils.c(this.JG, this.XX, "action_conditions", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String kI() {
        return getString("action_content_description");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String kJ() {
        return getString("action_label_style");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kK, reason: merged with bridge method [inline-methods] */
    public AppContentAction freeze() {
        return new AppContentActionEntity(this);
    }

    public String toString() {
        return AppContentActionEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentActionEntity) freeze()).writeToParcel(dest, flags);
    }
}
