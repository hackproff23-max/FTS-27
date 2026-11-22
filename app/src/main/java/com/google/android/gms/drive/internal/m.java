package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Metadata {
    private final MetadataBundle PD;

    public m(MetadataBundle metadataBundle) {
        this.PD = metadataBundle;
    }

    @Override // com.google.android.gms.drive.Metadata
    protected <T> T a(MetadataField<T> metadataField) {
        return (T) this.PD.a(metadataField);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return this.PD != null;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: iy, reason: merged with bridge method [inline-methods] */
    public Metadata freeze() {
        return new m(MetadataBundle.a(this.PD));
    }

    public String toString() {
        return "Metadata [mImpl=" + this.PD + "]";
    }
}
