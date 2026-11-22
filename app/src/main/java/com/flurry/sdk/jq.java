package com.flurry.sdk;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jq extends ir<Object> {
    public static final is a = new is() { // from class: com.flurry.sdk.jq.1
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            if (jwVar.a() == Object.class) {
                return new jq(iaVar);
            }
            return null;
        }
    };
    private final ia b;

    private jq(ia iaVar) {
        this.b = iaVar;
    }

    @Override // com.flurry.sdk.ir
    public Object b(jx jxVar) throws IOException {
        switch (jxVar.f()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                jxVar.a();
                while (jxVar.e()) {
                    arrayList.add(b(jxVar));
                }
                jxVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                je jeVar = new je();
                jxVar.c();
                while (jxVar.e()) {
                    jeVar.put(jxVar.g(), b(jxVar));
                }
                jxVar.d();
                return jeVar;
            case STRING:
                return jxVar.h();
            case NUMBER:
                return Double.valueOf(jxVar.k());
            case BOOLEAN:
                return Boolean.valueOf(jxVar.i());
            case NULL:
                jxVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.flurry.sdk.ir
    public void a(jz jzVar, Object obj) throws IOException {
        if (obj == null) {
            jzVar.f();
            return;
        }
        ir irVarA = this.b.a(obj.getClass());
        if (irVarA instanceof jq) {
            jzVar.d();
            jzVar.e();
        } else {
            irVarA.a(jzVar, obj);
        }
    }
}
