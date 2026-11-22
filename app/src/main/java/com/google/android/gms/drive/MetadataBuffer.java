package com.google.android.gms.drive;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.internal.m;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.ln;

/* JADX INFO: loaded from: classes.dex */
public final class MetadataBuffer extends DataBuffer<Metadata> {
    private final String OB;
    private a OC;

    private static class a extends Metadata {
        private final DataHolder JG;
        private final int La;
        private final int OD;

        public a(DataHolder dataHolder, int i) {
            this.JG = dataHolder;
            this.OD = i;
            this.La = dataHolder.au(i);
        }

        @Override // com.google.android.gms.drive.Metadata
        protected <T> T a(MetadataField<T> metadataField) {
            return metadataField.a(this.JG, this.OD, this.La);
        }

        @Override // com.google.android.gms.common.data.Freezable
        public boolean isDataValid() {
            return !this.JG.isClosed();
        }

        @Override // com.google.android.gms.common.data.Freezable
        /* JADX INFO: renamed from: iy, reason: merged with bridge method [inline-methods] */
        public Metadata freeze() {
            MetadataBundle metadataBundleIZ = MetadataBundle.iZ();
            for (MetadataField<?> metadataField : com.google.android.gms.drive.metadata.internal.e.iY()) {
                if (!(metadataField instanceof com.google.android.gms.drive.metadata.b) && metadataField != ln.RI) {
                    metadataField.a(this.JG, metadataBundleIZ, this.OD, this.La);
                }
            }
            return new m(metadataBundleIZ);
        }
    }

    public MetadataBuffer(DataHolder dataHolder, String nextPageToken) {
        super(dataHolder);
        this.OB = nextPageToken;
        dataHolder.gV().setClassLoader(MetadataBuffer.class.getClassLoader());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.DataBuffer
    public Metadata get(int row) {
        a aVar = this.OC;
        if (aVar != null && aVar.OD == row) {
            return aVar;
        }
        a aVar2 = new a(this.JG, row);
        this.OC = aVar2;
        return aVar2;
    }

    public String getNextPageToken() {
        return this.OB;
    }
}
