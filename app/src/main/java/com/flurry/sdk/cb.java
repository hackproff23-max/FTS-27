package com.flurry.sdk;

import android.graphics.Point;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class cb {
    private static final List<Integer> a = a();
    private static final SparseArray<Point> b = b();

    public static int a(Point point) {
        int iIntValue;
        if (point == null) {
            return -1;
        }
        for (Integer num : a) {
            Point pointA = a(num.intValue());
            if (pointA != null && point.x >= pointA.x && point.y >= pointA.y) {
                iIntValue = num.intValue();
                return iIntValue;
            }
        }
        iIntValue = -1;
        return iIntValue;
    }

    public static Point a(int i) {
        return b.get(i);
    }

    private static List<Integer> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);
        return Collections.unmodifiableList(arrayList);
    }

    private static SparseArray<Point> b() {
        SparseArray<Point> sparseArray = new SparseArray<>();
        sparseArray.put(4, new Point(728, 90));
        sparseArray.put(3, new Point(480, 60));
        sparseArray.put(1, new Point(320, 50));
        sparseArray.put(2, new Point(300, 250));
        return sparseArray;
    }
}
