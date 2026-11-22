package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class s extends aj {
    private static final String ID = com.google.android.gms.internal.a.FUNCTION_CALL.toString();
    private static final String aqO = com.google.android.gms.internal.b.FUNCTION_CALL_NAME.toString();
    private static final String aqf = com.google.android.gms.internal.b.ADDITIONAL_PARAMS.toString();
    private final a aqP;

    public interface a {
        Object b(String str, Map<String, Object> map);
    }

    public s(a aVar) {
        super(ID, aqO);
        this.aqP = aVar;
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String strJ = di.j(map.get(aqO));
        HashMap map2 = new HashMap();
        com.google.android.gms.internal.d.a aVar = map.get(aqf);
        if (aVar != null) {
            Object objO = di.o(aVar);
            if (!(objO instanceof Map)) {
                bh.W("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return di.rb();
            }
            for (Map.Entry entry : ((Map) objO).entrySet()) {
                map2.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return di.u(this.aqP.b(strJ, map2));
        } catch (Exception e) {
            bh.W("Custom macro/tag " + strJ + " threw exception " + e.getMessage());
            return di.rb();
        }
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
