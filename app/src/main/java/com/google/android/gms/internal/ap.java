package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes.dex */
public class ap {
    private final int nU;
    private Base64OutputStream nW;
    private ByteArrayOutputStream nX;
    private final ao nV = new ar();
    private final int nT = 6;

    public ap(int i) {
        this.nU = i;
    }

    private String m(String str) {
        String[] strArrSplit = str.split("\n");
        if (strArrSplit == null || strArrSplit.length == 0) {
            return "";
        }
        this.nX = new ByteArrayOutputStream();
        this.nW = new Base64OutputStream(this.nX, 10);
        Arrays.sort(strArrSplit, new Comparator<String>() { // from class: com.google.android.gms.internal.ap.1
            @Override // java.util.Comparator
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });
        for (int i = 0; i < strArrSplit.length && i < this.nU; i++) {
            if (strArrSplit[i].trim().length() != 0) {
                try {
                    this.nW.write(this.nV.l(strArrSplit[i]));
                } catch (IOException e) {
                    gr.b("Error while writing hash to byteStream", e);
                }
            }
        }
        try {
            this.nW.flush();
            this.nW.close();
            return this.nX.toString();
        } catch (IOException e2) {
            gr.b("HashManager: Unable to convert to base 64", e2);
            return "";
        }
    }

    public String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toLowerCase(Locale.US));
            stringBuffer.append('\n');
        }
        char c = 0;
        switch (c) {
            case 0:
                return n(stringBuffer.toString());
            case 1:
                return m(stringBuffer.toString());
            default:
                return "";
        }
    }

    String n(String str) {
        String[] strArrSplit = str.split("\n");
        if (strArrSplit == null || strArrSplit.length == 0) {
            return "";
        }
        this.nX = new ByteArrayOutputStream();
        this.nW = new Base64OutputStream(this.nX, 10);
        PriorityQueue priorityQueue = new PriorityQueue(this.nU, new Comparator<as.a>() { // from class: com.google.android.gms.internal.ap.2
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(as.a aVar, as.a aVar2) {
                return (int) (aVar.value - aVar2.value);
            }
        });
        for (String str2 : strArrSplit) {
            String[] strArrP = aq.p(str2);
            if (strArrP.length >= this.nT) {
                as.a(strArrP, this.nU, this.nT, (PriorityQueue<as.a>) priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                this.nW.write(this.nV.l(((as.a) it.next()).oa));
            } catch (IOException e) {
                gr.b("Error while writing hash to byteStream", e);
            }
        }
        try {
            this.nW.flush();
            this.nW.close();
            return this.nX.toString();
        } catch (IOException e2) {
            gr.b("HashManager: unable to convert to base 64", e2);
            return "";
        }
    }
}
