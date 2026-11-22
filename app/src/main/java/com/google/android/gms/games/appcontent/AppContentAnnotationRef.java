package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentAnnotationRef extends MultiDataBufferRef implements AppContentAnnotation {
    AppContentAnnotationRef(ArrayList<DataHolder> dataHolders, int dataRow) {
        super(dataHolders, 2, dataRow);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.common.data.d
    public boolean equals(Object obj) {
        return AppContentAnnotationEntity.a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAnnotation
    public String getDescription() {
        return getString("annotation_description");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAnnotation
    public String getTitle() {
        return getString("annotation_title");
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAnnotation
    public String getType() {
        return getString("annotation_type");
    }

    @Override // com.google.android.gms.common.data.d
    public int hashCode() {
        return AppContentAnnotationEntity.a(this);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAnnotation
    public Uri kL() {
        return aR("annotation_image_uri");
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kM, reason: merged with bridge method [inline-methods] */
    public AppContentAnnotation freeze() {
        return new AppContentAnnotationEntity(this);
    }

    public String toString() {
        return AppContentAnnotationEntity.b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        ((AppContentAnnotationEntity) freeze()).writeToParcel(dest, flags);
    }
}
