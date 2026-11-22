package com.chartboost.sdk.impl;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ca {
    public static cc a() {
        by byVarB = b();
        byVarB.a(Date.class, new i(byVarB));
        byVarB.a(ct.class, new g(byVarB));
        byVarB.a(cu.class, new h());
        byVarB.a(byte[].class, new h());
        return byVarB;
    }

    static by b() {
        by byVar = new by();
        byVar.a(Object[].class, new m(byVar));
        byVar.a(Boolean.class, new q());
        byVar.a(cv.class, new a(byVar));
        byVar.a(cw.class, new b(byVar));
        byVar.a(bv.class, new d(byVar));
        byVar.a(bw.class, new e(byVar));
        byVar.a(Iterable.class, new f(byVar));
        byVar.a(Map.class, new j(byVar));
        byVar.a(cx.class, new k(byVar));
        byVar.a(cy.class, new l(byVar));
        byVar.a(Number.class, new q());
        byVar.a(cz.class, new n(byVar));
        byVar.a(Pattern.class, new o(byVar));
        byVar.a(String.class, new p());
        byVar.a(UUID.class, new r(byVar));
        return byVar;
    }

    private static abstract class c extends bx {
        protected final cc a;

        c(cc ccVar) {
            this.a = ccVar;
        }
    }

    private static class h extends bx {
        private h() {
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            sb.append("<Binary Data>");
        }
    }

    private static class m extends c {
        m(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            sb.append("[ ");
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    sb.append(" , ");
                }
                this.a.a(Array.get(obj, i), sb);
            }
            sb.append("]");
        }
    }

    private static class q extends bx {
        private q() {
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            sb.append(obj.toString());
        }
    }

    private static class g extends c {
        g(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            ct ctVar = (ct) obj;
            bt btVar = new bt();
            btVar.put("$ts", Integer.valueOf(ctVar.a()));
            btVar.put("$inc", Integer.valueOf(ctVar.b()));
            this.a.a(btVar, sb);
        }
    }

    private static class a extends c {
        a(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            bt btVar = new bt();
            btVar.put("$code", ((cv) obj).a());
            this.a.a(btVar, sb);
        }
    }

    private static class b extends c {
        b(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            cw cwVar = (cw) obj;
            bt btVar = new bt();
            btVar.put("$code", cwVar.a());
            btVar.put("$scope", cwVar.b());
            this.a.a(btVar, sb);
        }
    }

    private static class i extends c {
        i(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setCalendar(new GregorianCalendar(new SimpleTimeZone(0, "GMT")));
            this.a.a(new bt("$date", simpleDateFormat.format((Date) obj)), sb);
        }
    }

    private static class d extends c {
        d(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            bv bvVar = (bv) obj;
            boolean z = true;
            for (String str : bvVar.keySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                bz.a(sb, str);
                sb.append(" : ");
                this.a.a(bvVar.a(str), sb);
            }
            sb.append("}");
        }
    }

    private static class e extends c {
        e(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            bw bwVar = (bw) obj;
            bt btVar = new bt();
            btVar.put("$ref", bwVar.b());
            btVar.put("$id", bwVar.a());
            this.a.a(btVar, sb);
        }
    }

    private static class f extends c {
        f(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            boolean z = true;
            sb.append("[ ");
            for (Object obj2 : (Iterable) obj) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                this.a.a(obj2, sb);
            }
            sb.append("]");
        }
    }

    private static class j extends c {
        j(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            sb.append("{ ");
            boolean z = true;
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append(" , ");
                }
                bz.a(sb, entry.getKey().toString());
                sb.append(" : ");
                this.a.a(entry.getValue(), sb);
            }
            sb.append("}");
        }
    }

    private static class k extends c {
        k(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new bt("$maxKey", 1), sb);
        }
    }

    private static class l extends c {
        l(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new bt("$minKey", 1), sb);
        }
    }

    private static class n extends c {
        n(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            this.a.a(new bt("$oid", obj.toString()), sb);
        }
    }

    private static class o extends c {
        o(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            bt btVar = new bt();
            btVar.put("$regex", obj.toString());
            if (((Pattern) obj).flags() != 0) {
                btVar.put("$options", bu.a(((Pattern) obj).flags()));
            }
            this.a.a(btVar, sb);
        }
    }

    private static class p extends bx {
        private p() {
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            bz.a(sb, (String) obj);
        }
    }

    private static class r extends c {
        r(cc ccVar) {
            super(ccVar);
        }

        @Override // com.chartboost.sdk.impl.cc
        public void a(Object obj, StringBuilder sb) {
            bt btVar = new bt();
            btVar.put("$uuid", ((UUID) obj).toString());
            this.a.a(btVar, sb);
        }
    }
}
