package com.google.android.gms.tagmanager;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ai {
    private static void a(DataLayer dataLayer, com.google.android.gms.internal.c.d dVar) {
        for (com.google.android.gms.internal.d.a aVar : dVar.fB) {
            dataLayer.cx(di.j(aVar));
        }
    }

    public static void a(DataLayer dataLayer, com.google.android.gms.internal.c.i iVar) {
        if (iVar.gq == null) {
            bh.W("supplemental missing experimentSupplemental");
            return;
        }
        a(dataLayer, iVar.gq);
        b(dataLayer, iVar.gq);
        c(dataLayer, iVar.gq);
    }

    private static void b(DataLayer dataLayer, com.google.android.gms.internal.c.d dVar) {
        for (com.google.android.gms.internal.d.a aVar : dVar.fA) {
            Map<String, Object> mapC = c(aVar);
            if (mapC != null) {
                dataLayer.push(mapC);
            }
        }
    }

    private static Map<String, Object> c(com.google.android.gms.internal.d.a aVar) {
        Object objO = di.o(aVar);
        if (objO instanceof Map) {
            return (Map) objO;
        }
        bh.W("value: " + objO + " is not a map value, ignored.");
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0063  */
    /* JADX WARN: Code duplicated, block: B:25:0x006b  */
    /* JADX WARN: Code duplicated, block: B:29:0x009b  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:32:0x00b3  */
    private static void c(DataLayer dataLayer, com.google.android.gms.internal.c.d dVar) {
        Map<String, Object> mapC;
        Object obj;
        for (com.google.android.gms.internal.c.C0070c c0070c : dVar.fC) {
            if (c0070c.fv == null) {
                bh.W("GaExperimentRandom: No key");
            } else {
                Object objValueOf = dataLayer.get(c0070c.fv);
                Long lValueOf = !(objValueOf instanceof Number) ? null : Long.valueOf(((Number) objValueOf).longValue());
                long j = c0070c.fw;
                long j2 = c0070c.fx;
                if (c0070c.fy && lValueOf != null && lValueOf.longValue() >= j && lValueOf.longValue() <= j2) {
                    dataLayer.cx(c0070c.fv);
                    mapC = dataLayer.c(c0070c.fv, objValueOf);
                    if (c0070c.fz > 0) {
                        if (mapC.containsKey("gtm")) {
                            obj = mapC.get("gtm");
                            if (obj instanceof Map) {
                                ((Map) obj).put("lifetime", Long.valueOf(c0070c.fz));
                            } else {
                                bh.W("GaExperimentRandom: gtm not a map");
                            }
                        } else {
                            mapC.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(c0070c.fz)));
                        }
                    }
                    dataLayer.push(mapC);
                } else if (j <= j2) {
                    objValueOf = Long.valueOf(Math.round((Math.random() * (j2 - j)) + j));
                    dataLayer.cx(c0070c.fv);
                    mapC = dataLayer.c(c0070c.fv, objValueOf);
                    if (c0070c.fz > 0) {
                        if (mapC.containsKey("gtm")) {
                            mapC.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(c0070c.fz)));
                        } else {
                            obj = mapC.get("gtm");
                            if (obj instanceof Map) {
                                ((Map) obj).put("lifetime", Long.valueOf(c0070c.fz));
                            } else {
                                bh.W("GaExperimentRandom: gtm not a map");
                            }
                        }
                    }
                    dataLayer.push(mapC);
                } else {
                    bh.W("GaExperimentRandom: random range invalid");
                }
            }
        }
    }
}
