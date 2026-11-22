package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class ct implements Serializable, Comparable<ct> {
    static final boolean a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
    final int b = 0;
    final Date c = null;

    public int a() {
        if (this.c == null) {
            return 0;
        }
        return (int) (this.c.getTime() / 1000);
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return "TS time:" + this.c + " inc:" + this.b;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ct ctVar) {
        return a() != ctVar.a() ? a() - ctVar.a() : b() - ctVar.b();
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ct)) {
            return false;
        }
        ct ctVar = (ct) obj;
        return a() == ctVar.a() && b() == ctVar.b();
    }
}
