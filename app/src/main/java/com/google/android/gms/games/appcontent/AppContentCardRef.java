package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentCardRef extends MultiDataBufferRef implements AppContentCard {
    AppContentCardRef(ArrayList<DataHolder> dataHolderCollection, int dataRow) {
        super(dataHolderCollection, 0, dataRow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return AppContentCardEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentAction> getActions() {
        return AppContentUtils.a(this.JG, this.XX, "card_actions", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String getDescription() {
        return getString("card_description");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Uri getIconImageUri() {
        return aR("card_icon_image_uri");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String getTitle() {
        return getString("card_title");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String getType() {
        return getString("card_type");
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return AppContentCardEntity.a(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentCondition> kH() {
        return AppContentUtils.c(this.JG, this.XX, "card_conditions", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String kI() {
        return getString("card_content_description");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Uri kL() {
        return aR("card_image_uri");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentAnnotation> kN() {
        return AppContentUtils.b(this.JG, this.XX, "card_annotations", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public int kO() {
        return getInteger("card_current_steps");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Bundle kP() {
        return AppContentUtils.d(this.JG, this.XX, "card_data", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String kQ() {
        return getString("card_subtitle");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public int kR() {
        return getInteger("card_total_steps");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kS, reason: merged with bridge method [inline-methods] */
    public AppContentCard freeze() {
        return new AppContentCardEntity(this);
    }

    public String toString() {
        return AppContentCardEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentCardEntity) freeze()).writeToParcel(dest, flags);
    }
}
