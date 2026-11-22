package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@ey
public class ak {
    private final int np;
    private final int nq;
    private final int nr;
    private final ap ns;
    private int nx;
    private final Object mH = new Object();
    private ArrayList<String> nt = new ArrayList<>();
    private int nu = 0;
    private int nv = 0;
    private int nw = 0;
    private String ny = "";

    public ak(int i, int i2, int i3, int i4) {
        this.np = i;
        this.nq = i2;
        this.nr = i3;
        this.ns = new ap(i4);
    }

    private String a(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            stringBuffer.append(' ');
            if (stringBuffer.length() > i) {
                break;
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String string = stringBuffer.toString();
        return string.length() >= i ? string.substring(0, i) : string;
    }

    private void j(String str) {
        if (str == null || str.length() < this.nr) {
            return;
        }
        synchronized (this.mH) {
            this.nt.add(str);
            this.nu += str.length();
        }
    }

    int a(int i, int i2) {
        return (this.np * i) + (this.nq * i2);
    }

    public boolean aS() {
        boolean z;
        synchronized (this.mH) {
            z = this.nw == 0;
        }
        return z;
    }

    public String aT() {
        return this.ny;
    }

    public void aU() {
        synchronized (this.mH) {
            this.nx -= 100;
        }
    }

    public void aV() {
        synchronized (this.mH) {
            this.nw--;
        }
    }

    public void aW() {
        synchronized (this.mH) {
            this.nw++;
        }
    }

    public void aX() {
        synchronized (this.mH) {
            int iA = a(this.nu, this.nv);
            if (iA > this.nx) {
                this.nx = iA;
                this.ny = this.ns.a(this.nt);
            }
        }
    }

    int aY() {
        return this.nu;
    }

    public void c(int i) {
        this.nv = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ak)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ak akVar = (ak) obj;
        return akVar.aT() != null && akVar.aT().equals(aT());
    }

    public int getScore() {
        return this.nx;
    }

    public void h(String str) {
        j(str);
        synchronized (this.mH) {
            if (this.nw < 0) {
                gr.S("ActivityContent: negative number of WebViews.");
            }
            aX();
        }
    }

    public int hashCode() {
        return aT().hashCode();
    }

    public void i(String str) {
        j(str);
    }

    public String toString() {
        return "ActivityContent fetchId: " + this.nv + " score:" + this.nx + " total_length:" + this.nu + "\n text: " + a(this.nt, 200) + "\n signture: " + this.ny;
    }
}
