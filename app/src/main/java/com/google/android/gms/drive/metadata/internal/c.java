package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;

/* JADX INFO: loaded from: classes.dex */
public class c implements Parcelable.Creator<CustomProperty> {
    static void a(CustomProperty customProperty, Parcel parcel, int i) {
        int iH = com.google.android.gms.common.internal.safeparcel.b.H(parcel);
        com.google.android.gms.common.internal.safeparcel.b.c(parcel, 1, customProperty.CK);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 2, (Parcelable) customProperty.Rg, i, false);
        com.google.android.gms.common.internal.safeparcel.b.a(parcel, 3, customProperty.mValue, false);
        com.google.android.gms.common.internal.safeparcel.b.H(parcel, iH);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: aO, reason: merged with bridge method [inline-methods] */
    public CustomProperty createFromParcel(Parcel parcel) {
        String strO;
        CustomPropertyKey customPropertyKey;
        int iG;
        String str = null;
        int iG2 = com.google.android.gms.common.internal.safeparcel.a.G(parcel);
        int i = 0;
        CustomPropertyKey customPropertyKey2 = null;
        while (parcel.dataPosition() < iG2) {
            int iF = com.google.android.gms.common.internal.safeparcel.a.F(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.aH(iF)) {
                case 1:
                    String str2 = str;
                    customPropertyKey = customPropertyKey2;
                    iG = com.google.android.gms.common.internal.safeparcel.a.g(parcel, iF);
                    strO = str2;
                    break;
                case 2:
                    CustomPropertyKey customPropertyKey3 = (CustomPropertyKey) com.google.android.gms.common.internal.safeparcel.a.a(parcel, iF, CustomPropertyKey.CREATOR);
                    iG = i;
                    strO = str;
                    customPropertyKey = customPropertyKey3;
                    break;
                case 3:
                    strO = com.google.android.gms.common.internal.safeparcel.a.o(parcel, iF);
                    customPropertyKey = customPropertyKey2;
                    iG = i;
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, iF);
                    strO = str;
                    customPropertyKey = customPropertyKey2;
                    iG = i;
                    break;
            }
            i = iG;
            customPropertyKey2 = customPropertyKey;
            str = strO;
        }
        if (parcel.dataPosition() != iG2) {
            throw new com.google.android.gms.common.internal.safeparcel.a.C0039a("Overread allowed size end=" + iG2, parcel);
        }
        return new CustomProperty(i, customPropertyKey2, str);
    }

    @Override // android.os.Parcelable.Creator
    /* JADX INFO: renamed from: cd, reason: merged with bridge method [inline-methods] */
    public CustomProperty[] newArray(int i) {
        return new CustomProperty[i];
    }
}
