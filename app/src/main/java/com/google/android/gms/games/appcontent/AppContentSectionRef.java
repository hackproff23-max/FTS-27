package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentSectionRef extends MultiDataBufferRef implements AppContentSection {
    private final int Ya;

    AppContentSectionRef(ArrayList<DataHolder> dataHolderCollection, int dataRow, int numChildren) {
        super(dataHolderCollection, 0, dataRow);
        this.Ya = numChildren;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return AppContentSectionEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String getTitle() {
        return getString("section_title");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String getType() {
        return getString("section_type");
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return AppContentSectionEntity.a(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String kI() {
        return getString("section_content_description");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public Bundle kP() {
        return AppContentUtils.d(this.JG, this.XX, "section_data", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String kQ() {
        return getString("section_subtitle");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public Uri kY() {
        return aR("section_background_image_uri");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: la, reason: merged with bridge method [inline-methods] */
    public AppContentSection freeze() {
        return new AppContentSectionEntity(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    /* JADX INFO: renamed from: lb, reason: merged with bridge method [inline-methods] */
    public ArrayList<AppContentAction> getActions() {
        return AppContentUtils.a(this.JG, this.XX, "section_actions", this.KZ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    /* JADX INFO: renamed from: lc, reason: merged with bridge method [inline-methods] */
    public ArrayList<AppContentCard> kZ() {
        ArrayList<AppContentCard> arrayList = new ArrayList<>(this.Ya);
        for (int i = 0; i < this.Ya; i++) {
            arrayList.add(new AppContentCardRef(this.XX, this.KZ + i));
        }
        return arrayList;
    }

    public String toString() {
        return AppContentSectionEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentSectionEntity) freeze()).writeToParcel(dest, flags);
    }
}
