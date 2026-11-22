package com.flurry.sdk;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import com.flurry.android.AdCreative;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class bj implements ef, eo {
    protected abstract ec a(Context context, r rVar, AdCreative adCreative, Bundle bundle);

    protected abstract en a(Context context, r rVar, Bundle bundle);

    protected abstract String f();

    protected abstract List<bf> g();

    protected abstract List<ActivityInfo> j();

    protected abstract List<bf> k();

    protected abstract List<String> n();

    protected abstract List<String> o();

    @Override // com.flurry.sdk.eo
    public en a(Context context, r rVar) {
        Bundle bundleA;
        if (context == null || rVar == null || !a(context, d()) || (bundleA = a(context)) == null) {
            return null;
        }
        return a(context, rVar, bundleA);
    }

    @Override // com.flurry.sdk.ef
    public ec b(Context context, r rVar) {
        Bundle bundleB;
        AdCreative adCreativeA;
        if (context == null || rVar == null || !b(context, e()) || (bundleB = b(context)) == null || (adCreativeA = cr.a(rVar.k().a())) == null) {
            return null;
        }
        return a(context, rVar, adCreativeA, bundleB);
    }

    protected boolean a(Context context, bm bmVar) {
        bi biVarA;
        if (context == null || bmVar == null || (biVarA = a()) == null) {
            return false;
        }
        return biVarA.a(context, bmVar);
    }

    protected boolean b(Context context, bm bmVar) {
        bi biVarB;
        if (context == null || bmVar == null || (biVarB = b()) == null) {
            return false;
        }
        return biVarB.a(context, bmVar);
    }

    protected bi a() {
        return c();
    }

    protected bi b() {
        return c();
    }

    protected bi c() {
        return new bh();
    }

    protected bm d() {
        return new bm(f(), g(), h(), i(), j());
    }

    protected bm e() {
        return new bm(f(), k(), l(), m(), Collections.emptyList());
    }

    protected List<String> h() {
        return n();
    }

    protected List<String> i() {
        return o();
    }

    protected List<String> l() {
        return n();
    }

    protected List<String> m() {
        return o();
    }

    protected Bundle a(Context context) {
        return c(context);
    }

    protected Bundle b(Context context) {
        return c(context);
    }

    protected Bundle c(Context context) {
        return hm.d(context);
    }
}
