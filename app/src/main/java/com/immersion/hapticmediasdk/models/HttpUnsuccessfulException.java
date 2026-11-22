package com.immersion.hapticmediasdk.models;

/* JADX INFO: loaded from: classes.dex */
public class HttpUnsuccessfulException extends Exception {

    /* JADX INFO: renamed from: b04270427Ч0427ЧЧ, reason: contains not printable characters */
    public static int f84b042704270427 = 2;

    /* JADX INFO: renamed from: b0427ЧЧ0427ЧЧ, reason: contains not printable characters */
    public static int f85b04270427 = 0;

    /* JADX INFO: renamed from: bЧ0427Ч0427ЧЧ, reason: contains not printable characters */
    public static int f86b04270427 = 1;

    /* JADX INFO: renamed from: bЧЧЧ0427ЧЧ, reason: contains not printable characters */
    public static int f87b0427 = 24;
    private static final long serialVersionUID = -251711421440827767L;
    private int a;

    public HttpUnsuccessfulException(int i, String str) {
        super(str);
        while (true) {
            switch (1) {
                case 0:
                    continue;
                case 1:
                    break;
                default:
                    while (true) {
                        char c = 0;
                        switch (c) {
                            case 0:
                                break;
                            case 1:
                                continue;
                            default:
                                break;
                        }
                    }
                    break;
            }
        }
        if (((f87b0427 + f86b04270427) * f87b0427) % f84b042704270427 != f85b04270427) {
            f87b0427 = 74;
            f85b04270427 = 98;
        }
        this.a = i;
    }

    /* JADX INFO: renamed from: b0427Ч04270427ЧЧ, reason: contains not printable characters */
    public static int m112b042704270427() {
        return 75;
    }

    /* JADX INFO: renamed from: bЧЧ04270427ЧЧ, reason: contains not printable characters */
    public static int m113b04270427() {
        return 1;
    }

    public int getHttpStatusCode() throws Exception {
        if (((f87b0427 + m113b04270427()) * f87b0427) % f84b042704270427 != f85b04270427) {
            f87b0427 = m112b042704270427();
            f85b04270427 = m112b042704270427();
        }
        try {
            return this.a;
        } catch (Exception e) {
            throw e;
        }
    }
}
