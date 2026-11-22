package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.la;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentUtils {

    private interface AppContentRunner {
        void b(ArrayList<DataHolder> arrayList, int i);
    }

    public static ArrayList<AppContentAction> a(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentAction> arrayList2 = new ArrayList<>();
        a(dataHolder, 1, str, "action_id", i, new AppContentRunner() { // from class: com.google.android.gms.games.appcontent.AppContentUtils.1
            @Override // com.google.android.gms.games.appcontent.AppContentUtils.AppContentRunner
            public void b(ArrayList<DataHolder> arrayList3, int i2) {
                arrayList2.add(new AppContentActionRef(arrayList3, i2));
            }
        }, arrayList);
        return arrayList2;
    }

    private static void a(DataHolder dataHolder, int i, String str, String str2, int i2, AppContentRunner appContentRunner, ArrayList<DataHolder> arrayList) {
        DataHolder dataHolder2 = arrayList.get(i);
        String strC = dataHolder.c(str, i2, dataHolder.au(i2));
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        int count = dataHolder2.getCount();
        String[] strArrSplit = strC.split(",");
        for (int i3 = 0; i3 < count; i3++) {
            String strC2 = dataHolder2.c(str2, i3, dataHolder2.au(i3));
            if (!TextUtils.isEmpty(strC2) && la.b(strArrSplit, strC2)) {
                appContentRunner.b(arrayList, i3);
            }
        }
    }

    public static ArrayList<AppContentAnnotation> b(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentAnnotation> arrayList2 = new ArrayList<>();
        a(dataHolder, 2, str, "annotation_id", i, new AppContentRunner() { // from class: com.google.android.gms.games.appcontent.AppContentUtils.2
            @Override // com.google.android.gms.games.appcontent.AppContentUtils.AppContentRunner
            public void b(ArrayList<DataHolder> arrayList3, int i2) {
                arrayList2.add(new AppContentAnnotationRef(arrayList3, i2));
            }
        }, arrayList);
        return arrayList2;
    }

    public static ArrayList<AppContentCondition> c(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final ArrayList<AppContentCondition> arrayList2 = new ArrayList<>();
        a(dataHolder, 4, str, "condition_id", i, new AppContentRunner() { // from class: com.google.android.gms.games.appcontent.AppContentUtils.3
            @Override // com.google.android.gms.games.appcontent.AppContentUtils.AppContentRunner
            public void b(ArrayList<DataHolder> arrayList3, int i2) {
                arrayList2.add(new AppContentConditionRef(arrayList3, i2));
            }
        }, arrayList);
        return arrayList2;
    }

    public static Bundle d(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        final Bundle bundle = new Bundle();
        final DataHolder dataHolder2 = arrayList.get(3);
        a(dataHolder, 3, str, "tuple_id", i, new AppContentRunner() { // from class: com.google.android.gms.games.appcontent.AppContentUtils.4
            @Override // com.google.android.gms.games.appcontent.AppContentUtils.AppContentRunner
            public void b(ArrayList<DataHolder> arrayList2, int i2) {
                AppContentTupleRef appContentTupleRef = new AppContentTupleRef(dataHolder2, i2);
                bundle.putString(appContentTupleRef.getName(), appContentTupleRef.getValue());
            }
        }, arrayList);
        return bundle;
    }
}
