package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class jy extends com.google.android.gms.dynamic.g<ju> {
    private static final jy Nt = new jy();

    private jy() {
        super("com.google.android.gms.common.ui.SignInButtonCreatorImpl");
    }

    public static View b(Context context, int i, int i2) throws com.google.android.gms.dynamic.g.a {
        return Nt.c(context, i, i2);
    }

    private View c(Context context, int i, int i2) throws com.google.android.gms.dynamic.g.a {
        try {
            return (View) com.google.android.gms.dynamic.e.f(L(context).a(com.google.android.gms.dynamic.e.k(context), i, i2));
        } catch (Exception e) {
            throw new com.google.android.gms.dynamic.g.a("Could not get button with size " + i + " and color " + i2, e);
        }
    }

    @Override // com.google.android.gms.dynamic.g
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public ju d(IBinder iBinder) {
        return ju.a.R(iBinder);
    }
}
