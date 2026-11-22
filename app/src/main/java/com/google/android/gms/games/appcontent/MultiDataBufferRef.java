package com.google.android.gms.games.appcontent;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class MultiDataBufferRef extends d {
    protected final ArrayList<DataHolder> XX;

    protected MultiDataBufferRef(ArrayList<DataHolder> dataHolderCollection, int indexForRef, int dataRow) {
        super(dataHolderCollection.get(indexForRef), dataRow);
        this.XX = dataHolderCollection;
    }
}
