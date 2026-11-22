package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public class e<T extends SafeParcelable> extends DataBuffer<T> {
    private static final String[] Lb = {"data"};
    private final Parcelable.Creator<T> Lc;

    public e(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.Lc = creator;
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    /* JADX INFO: renamed from: at, reason: merged with bridge method [inline-methods] */
    public T get(int i) {
        byte[] bArrF = this.JG.f("data", i, this.JG.au(i));
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArrF, 0, bArrF.length);
        parcelObtain.setDataPosition(0);
        T tCreateFromParcel = this.Lc.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tCreateFromParcel;
    }
}
