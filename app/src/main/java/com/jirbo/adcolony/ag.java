package com.jirbo.adcolony;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
class ag implements Serializable {
    String a;
    int b;
    int c;
    int d;

    ag() {
        this.a = "";
    }

    ag(String str) {
        this.a = "";
        this.a = str;
    }

    boolean a(ADCData.g gVar) {
        if (gVar == null) {
            return false;
        }
        this.a = gVar.a("uuid", "error");
        this.b = gVar.g("skipped_plays");
        this.c = gVar.g("play_order_index");
        return true;
    }

    ADCData.g a() {
        ADCData.g gVar = new ADCData.g();
        gVar.b("uuid", this.a);
        gVar.b("skipped_plays", this.b);
        gVar.b("play_order_index", this.c);
        return gVar;
    }
}
