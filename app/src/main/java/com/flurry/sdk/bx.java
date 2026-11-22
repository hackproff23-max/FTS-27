package com.flurry.sdk;

import android.graphics.Point;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class bx {
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
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(15);
        arrayList.add(10);
        arrayList.add(13);
        return Collections.unmodifiableList(arrayList);
    }

    private static SparseArray<Point> b() {
        SparseArray<Point> sparseArray = new SparseArray<>();
        sparseArray.put(11, new Point(728, 90));
        sparseArray.put(12, new Point(468, 60));
        sparseArray.put(15, new Point(320, 50));
        sparseArray.put(10, new Point(300, 250));
        sparseArray.put(13, new Point(120, 600));
        return sparseArray;
    }
}
