package com.chartboost.sdk.impl;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class by extends bx {
    private de<cc> a = new de<>();

    by() {
    }

    void a(Class cls, cc ccVar) {
        this.a.a(cls, ccVar);
    }

    @Override // com.chartboost.sdk.impl.cc
    public void a(Object obj, StringBuilder sb) {
        Object objA = bu.a(obj);
        if (objA == null) {
            sb.append(" null ");
            return;
        }
        cc ccVarA = null;
        Iterator<Class<?>> it = de.a((Class) objA.getClass()).iterator();
        while (it.hasNext()) {
            ccVarA = this.a.a(it.next());
            if (ccVarA != null) {
                break;
            }
        }
        if (ccVarA == null && objA.getClass().isArray()) {
            ccVarA = this.a.a((Object) Object[].class);
        }
        if (ccVarA == null) {
            throw new RuntimeException("json can't serialize type : " + objA.getClass());
        }
        ccVarA.a(objA, sb);
    }
}
