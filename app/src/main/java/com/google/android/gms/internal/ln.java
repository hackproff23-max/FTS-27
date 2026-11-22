package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class ln {
    public static final MetadataField<DriveId> Rj = lq.RV;
    public static final MetadataField<String> Rk = new com.google.android.gms.drive.metadata.internal.l("alternateLink", 4300000);
    public static final a Rl = new a(5000000);
    public static final MetadataField<String> Rm = new com.google.android.gms.drive.metadata.internal.l("description", 4300000);
    public static final MetadataField<String> Rn = new com.google.android.gms.drive.metadata.internal.l("embedLink", 4300000);
    public static final MetadataField<String> Ro = new com.google.android.gms.drive.metadata.internal.l("fileExtension", 4300000);
    public static final MetadataField<Long> Rp = new com.google.android.gms.drive.metadata.internal.g("fileSize", 4300000);
    public static final MetadataField<Boolean> Rq = new com.google.android.gms.drive.metadata.internal.b("hasThumbnail", 4300000);
    public static final MetadataField<String> Rr = new com.google.android.gms.drive.metadata.internal.l("indexableText", 4300000);
    public static final MetadataField<Boolean> Rs = new com.google.android.gms.drive.metadata.internal.b("isAppData", 4300000);
    public static final MetadataField<Boolean> Rt = new com.google.android.gms.drive.metadata.internal.b("isCopyable", 4300000);
    public static final MetadataField<Boolean> Ru = new com.google.android.gms.drive.metadata.internal.b("isEditable", 4100000);
    public static final b Rv = new b("isPinned", 4100000);
    public static final MetadataField<Boolean> Rw = new com.google.android.gms.drive.metadata.internal.b("isRestricted", 4300000);
    public static final MetadataField<Boolean> Rx = new com.google.android.gms.drive.metadata.internal.b("isShared", 4300000);
    public static final MetadataField<Boolean> Ry = new com.google.android.gms.drive.metadata.internal.b("isTrashable", 4400000);
    public static final MetadataField<Boolean> Rz = new com.google.android.gms.drive.metadata.internal.b("isViewed", 4300000);
    public static final c RA = new c("mimeType", 4100000);
    public static final MetadataField<String> RB = new com.google.android.gms.drive.metadata.internal.l("originalFilename", 4300000);
    public static final com.google.android.gms.drive.metadata.b<String> RC = new com.google.android.gms.drive.metadata.internal.k("ownerNames", 4300000);
    public static final com.google.android.gms.drive.metadata.internal.m RD = new com.google.android.gms.drive.metadata.internal.m("lastModifyingUser", 6000000);
    public static final com.google.android.gms.drive.metadata.internal.m RE = new com.google.android.gms.drive.metadata.internal.m("sharingUser", 6000000);
    public static final d RF = new d("parents", 4100000);
    public static final e RG = new e("quotaBytesUsed", 4300000);
    public static final f RH = new f("starred", 4100000);
    public static final MetadataField<com.google.android.gms.common.data.a> RI = new com.google.android.gms.drive.metadata.internal.j<com.google.android.gms.common.data.a>("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000) { // from class: com.google.android.gms.internal.ln.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.drive.metadata.a
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public com.google.android.gms.common.data.a c(DataHolder dataHolder, int i, int i2) {
            throw new IllegalStateException("Thumbnail field is write only");
        }
    };
    public static final g RJ = new g("title", 4100000);
    public static final h RK = new h("trashed", 4100000);
    public static final MetadataField<String> RL = new com.google.android.gms.drive.metadata.internal.l("webContentLink", 4300000);
    public static final MetadataField<String> RM = new com.google.android.gms.drive.metadata.internal.l("webViewLink", 4300000);
    public static final MetadataField<String> RN = new com.google.android.gms.drive.metadata.internal.l("uniqueIdentifier", 5000000);
    public static final com.google.android.gms.drive.metadata.internal.b RO = new com.google.android.gms.drive.metadata.internal.b("writersCanShare", 6000000);
    public static final MetadataField<String> RP = new com.google.android.gms.drive.metadata.internal.l("role", 6000000);

    public static class a extends lo implements SearchableMetadataField<AppVisibleCustomProperties> {
        public a(int i) {
            super(i);
        }
    }

    public static class b extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public b(String str, int i) {
            super(str, i);
        }
    }

    public static class c extends com.google.android.gms.drive.metadata.internal.l implements SearchableMetadataField<String> {
        public c(String str, int i) {
            super(str, i);
        }
    }

    public static class d extends com.google.android.gms.drive.metadata.internal.i<DriveId> implements SearchableCollectionMetadataField<DriveId> {
        public d(String str, int i) {
            super(str, i);
        }
    }

    public static class e extends com.google.android.gms.drive.metadata.internal.g implements SortableMetadataField<Long> {
        public e(String str, int i) {
            super(str, i);
        }
    }

    public static class f extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public f(String str, int i) {
            super(str, i);
        }
    }

    public static class g extends com.google.android.gms.drive.metadata.internal.l implements SearchableMetadataField<String>, SortableMetadataField<String> {
        public g(String str, int i) {
            super(str, i);
        }
    }

    public static class h extends com.google.android.gms.drive.metadata.internal.b implements SearchableMetadataField<Boolean> {
        public h(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.drive.metadata.internal.b, com.google.android.gms.drive.metadata.a
        /* JADX INFO: renamed from: e */
        public Boolean c(DataHolder dataHolder, int i, int i2) {
            return Boolean.valueOf(dataHolder.b(getName(), i, i2) != 0);
        }
    }
}
