package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public class ComparisonFilter<T> extends AbstractFilter {
    public static final a CREATOR = new a();
    final int CK;
    final Operator Sh;
    final MetadataBundle Si;
    final MetadataField<T> Sj;

    ComparisonFilter(int i, Operator operator, MetadataBundle metadataBundle) {
        this.CK = i;
        this.Sh = operator;
        this.Si = metadataBundle;
        this.Sj = (MetadataField<T>) e.b(metadataBundle);
    }

    public ComparisonFilter(Operator operator, SearchableMetadataField<T> field, T value) {
        this(1, operator, MetadataBundle.a(field, value));
    }

    @Override // com.google.android.gms.drive.query.Filter
    public <F> F a(f<F> fVar) {
        return fVar.b(this.Sh, this.Sj, getValue());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public T getValue() {
        return (T) this.Si.a(this.Sj);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        a.a(this, out, flags);
    }
}
