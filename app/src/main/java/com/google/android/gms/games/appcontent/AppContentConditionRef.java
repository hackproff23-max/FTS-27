package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentConditionRef extends MultiDataBufferRef implements AppContentCondition {
    AppContentConditionRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 4, dataRow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return AppContentConditionEntity.a(this, obj);
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return AppContentConditionEntity.a(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCondition
    public String kT() {
        return getString("condition_default_value");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCondition
    public String kU() {
        return getString("condition_expected_value");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCondition
    public String kV() {
        return getString("condition_predicate");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCondition
    public Bundle kW() {
        return AppContentUtils.d(this.JG, this.XX, "condition_predicate_parameters", this.KZ);
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kX, reason: merged with bridge method [inline-methods] */
    public AppContentCondition freeze() {
        return new AppContentConditionEntity(this);
    }

    public String toString() {
        return AppContentConditionEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentConditionEntity) freeze()).writeToParcel(dest, flags);
    }
}
