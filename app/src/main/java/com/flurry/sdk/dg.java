package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public class dg {
    private String a;
    private int b;
    private di c;

    private dg() {
    }

    public String a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public di c() {
        return this.c;
    }

    public static class a {
        private dg a = new dg();

        public a a(String str) {
            this.a.a = str;
            return this;
        }

        public a a(int i) {
            this.a.b = i;
            return this;
        }

        public a a(di diVar) {
            this.a.c = diVar;
            return this;
        }

        public dg a() {
            return this.a;
        }
    }
}
