package com.google.android.gms.tagmanager;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.facebook.internal.AnalyticsEvents;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class ct {
    private static final bz<com.google.android.gms.internal.d.a> atb = new bz<>(di.rb(), true);
    private final DataLayer aqn;
    private final cr.c atc;
    private final ag atd;
    private final Map<String, aj> ate;
    private final Map<String, aj> atf;
    private final Map<String, aj> atg;
    private final k<cr.a, bz<com.google.android.gms.internal.d.a>> ath;
    private final k<String, b> ati;
    private final Set<cr.e> atj;
    private final Map<String, c> atk;
    private volatile String atl;
    private int atm;

    interface a {
        void a(cr.e eVar, Set<cr.a> set, Set<cr.a> set2, cn cnVar);
    }

    private static class b {
        private com.google.android.gms.internal.d.a asN;
        private bz<com.google.android.gms.internal.d.a> ats;

        public b(bz<com.google.android.gms.internal.d.a> bzVar, com.google.android.gms.internal.d.a aVar) {
            this.ats = bzVar;
            this.asN = aVar;
        }

        public int getSize() {
            return (this.asN == null ? 0 : this.asN.rY()) + this.ats.getObject().rY();
        }

        public bz<com.google.android.gms.internal.d.a> qG() {
            return this.ats;
        }

        public com.google.android.gms.internal.d.a qm() {
            return this.asN;
        }
    }

    private static class c {
        private cr.a atx;
        private final Set<cr.e> atj = new HashSet();
        private final Map<cr.e, List<cr.a>> att = new HashMap();
        private final Map<cr.e, List<String>> atv = new HashMap();
        private final Map<cr.e, List<cr.a>> atu = new HashMap();
        private final Map<cr.e, List<String>> atw = new HashMap();

        public void a(cr.e eVar, cr.a aVar) {
            List<cr.a> arrayList = this.att.get(eVar);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.att.put(eVar, arrayList);
            }
            arrayList.add(aVar);
        }

        public void a(cr.e eVar, String str) {
            List<String> arrayList = this.atv.get(eVar);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.atv.put(eVar, arrayList);
            }
            arrayList.add(str);
        }

        public void b(cr.e eVar) {
            this.atj.add(eVar);
        }

        public void b(cr.e eVar, cr.a aVar) {
            List<cr.a> arrayList = this.atu.get(eVar);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.atu.put(eVar, arrayList);
            }
            arrayList.add(aVar);
        }

        public void b(cr.e eVar, String str) {
            List<String> arrayList = this.atw.get(eVar);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.atw.put(eVar, arrayList);
            }
            arrayList.add(str);
        }

        public void i(cr.a aVar) {
            this.atx = aVar;
        }

        public Set<cr.e> qH() {
            return this.atj;
        }

        public Map<cr.e, List<cr.a>> qI() {
            return this.att;
        }

        public Map<cr.e, List<String>> qJ() {
            return this.atv;
        }

        public Map<cr.e, List<String>> qK() {
            return this.atw;
        }

        public Map<cr.e, List<cr.a>> qL() {
            return this.atu;
        }

        public cr.a qM() {
            return this.atx;
        }
    }

    public ct(Context context, cr.c cVar, DataLayer dataLayer, s.a aVar, s.a aVar2, ag agVar) {
        if (cVar == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.atc = cVar;
        this.atj = new HashSet(cVar.qp());
        this.aqn = dataLayer;
        this.atd = agVar;
        this.ath = new l().a(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new l.a<cr.a, bz<com.google.android.gms.internal.d.a>>() { // from class: com.google.android.gms.tagmanager.ct.1
            @Override // com.google.android.gms.tagmanager.l.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int sizeOf(cr.a aVar3, bz<com.google.android.gms.internal.d.a> bzVar) {
                return bzVar.getObject().rY();
            }
        });
        this.ati = new l().a(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new l.a<String, b>() { // from class: com.google.android.gms.tagmanager.ct.2
            @Override // com.google.android.gms.tagmanager.l.a
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int sizeOf(String str, b bVar) {
                return str.length() + bVar.getSize();
            }
        });
        this.ate = new HashMap();
        b(new i(context));
        b(new s(aVar2));
        b(new w(dataLayer));
        b(new dj(context, dataLayer));
        this.atf = new HashMap();
        c(new q());
        c(new ad());
        c(new ae());
        c(new al());
        c(new am());
        c(new bd());
        c(new be());
        c(new ci());
        c(new dc());
        this.atg = new HashMap();
        a(new com.google.android.gms.tagmanager.b(context));
        a(new com.google.android.gms.tagmanager.c(context));
        a(new e(context));
        a(new f(context));
        a(new g(context));
        a(new h(context));
        a(new m());
        a(new p(this.atc.getVersion()));
        a(new s(aVar));
        a(new u(dataLayer));
        a(new z(context));
        a(new aa());
        a(new ac());
        a(new ah(this));
        a(new an());
        a(new ao());
        a(new ax(context));
        a(new az());
        a(new bc());
        a(new bj());
        a(new bl(context));
        a(new ca());
        a(new cc());
        a(new cf());
        a(new ch());
        a(new cj(context));
        a(new cu());
        a(new cv());
        a(new de());
        a(new dk());
        this.atk = new HashMap();
        for (cr.e eVar : this.atj) {
            if (agVar.pH()) {
                a(eVar.qx(), eVar.qy(), "add macro");
                a(eVar.qC(), eVar.qz(), "remove macro");
                a(eVar.qv(), eVar.qA(), "add tag");
                a(eVar.qw(), eVar.qB(), "remove tag");
            }
            for (int i = 0; i < eVar.qx().size(); i++) {
                cr.a aVar3 = eVar.qx().get(i);
                String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (agVar.pH() && i < eVar.qy().size()) {
                    str = eVar.qy().get(i);
                }
                c cVarE = e(this.atk, h(aVar3));
                cVarE.b(eVar);
                cVarE.a(eVar, aVar3);
                cVarE.a(eVar, str);
            }
            for (int i2 = 0; i2 < eVar.qC().size(); i2++) {
                cr.a aVar4 = eVar.qC().get(i2);
                String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (agVar.pH() && i2 < eVar.qz().size()) {
                    str2 = eVar.qz().get(i2);
                }
                c cVarE2 = e(this.atk, h(aVar4));
                cVarE2.b(eVar);
                cVarE2.b(eVar, aVar4);
                cVarE2.b(eVar, str2);
            }
        }
        for (Map.Entry<String, List<cr.a>> entry : this.atc.qq().entrySet()) {
            for (cr.a aVar5 : entry.getValue()) {
                if (!di.n(aVar5.ql().get(com.google.android.gms.internal.b.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    e(this.atk, entry.getKey()).i(aVar5);
                }
            }
        }
    }

    private bz<com.google.android.gms.internal.d.a> a(com.google.android.gms.internal.d.a aVar, Set<String> set, dl dlVar) {
        if (!aVar.gF) {
            return new bz<>(aVar, true);
        }
        switch (aVar.type) {
            case 2:
                com.google.android.gms.internal.d.a aVarG = cr.g(aVar);
                aVarG.gw = new com.google.android.gms.internal.d.a[aVar.gw.length];
                for (int i = 0; i < aVar.gw.length; i++) {
                    bz<com.google.android.gms.internal.d.a> bzVarA = a(aVar.gw[i], set, dlVar.fK(i));
                    if (bzVarA == atb) {
                        return atb;
                    }
                    aVarG.gw[i] = bzVarA.getObject();
                }
                return new bz<>(aVarG, false);
            case 3:
                com.google.android.gms.internal.d.a aVarG2 = cr.g(aVar);
                if (aVar.gx.length != aVar.gy.length) {
                    bh.T("Invalid serving value: " + aVar.toString());
                    return atb;
                }
                aVarG2.gx = new com.google.android.gms.internal.d.a[aVar.gx.length];
                aVarG2.gy = new com.google.android.gms.internal.d.a[aVar.gx.length];
                for (int i2 = 0; i2 < aVar.gx.length; i2++) {
                    bz<com.google.android.gms.internal.d.a> bzVarA2 = a(aVar.gx[i2], set, dlVar.fL(i2));
                    bz<com.google.android.gms.internal.d.a> bzVarA3 = a(aVar.gy[i2], set, dlVar.fM(i2));
                    if (bzVarA2 == atb || bzVarA3 == atb) {
                        return atb;
                    }
                    aVarG2.gx[i2] = bzVarA2.getObject();
                    aVarG2.gy[i2] = bzVarA3.getObject();
                }
                return new bz<>(aVarG2, false);
            case 4:
                if (set.contains(aVar.gz)) {
                    bh.T("Macro cycle detected.  Current macro reference: " + aVar.gz + ".  Previous macro references: " + set.toString() + ".");
                    return atb;
                }
                set.add(aVar.gz);
                bz<com.google.android.gms.internal.d.a> bzVarA4 = dm.a(a(aVar.gz, set, dlVar.pW()), aVar.gE);
                set.remove(aVar.gz);
                return bzVarA4;
            case 5:
            case 6:
            default:
                bh.T("Unknown type: " + aVar.type);
                return atb;
            case 7:
                com.google.android.gms.internal.d.a aVarG3 = cr.g(aVar);
                aVarG3.gD = new com.google.android.gms.internal.d.a[aVar.gD.length];
                for (int i3 = 0; i3 < aVar.gD.length; i3++) {
                    bz<com.google.android.gms.internal.d.a> bzVarA5 = a(aVar.gD[i3], set, dlVar.fN(i3));
                    if (bzVarA5 == atb) {
                        return atb;
                    }
                    aVarG3.gD[i3] = bzVarA5.getObject();
                }
                return new bz<>(aVarG3, false);
        }
    }

    private bz<com.google.android.gms.internal.d.a> a(String str, Set<String> set, bk bkVar) {
        cr.a next;
        this.atm++;
        b bVar = this.ati.get(str);
        if (bVar != null && !this.atd.pH()) {
            a(bVar.qm(), set);
            this.atm--;
            return bVar.qG();
        }
        c cVar = this.atk.get(str);
        if (cVar == null) {
            bh.T(qF() + "Invalid macro: " + str);
            this.atm--;
            return atb;
        }
        bz<Set<cr.a>> bzVarA = a(str, cVar.qH(), cVar.qI(), cVar.qJ(), cVar.qL(), cVar.qK(), set, bkVar.py());
        if (bzVarA.getObject().isEmpty()) {
            next = cVar.qM();
        } else {
            if (bzVarA.getObject().size() > 1) {
                bh.W(qF() + "Multiple macros active for macroName " + str);
            }
            next = bzVarA.getObject().iterator().next();
        }
        if (next == null) {
            this.atm--;
            return atb;
        }
        bz<com.google.android.gms.internal.d.a> bzVarA2 = a(this.atg, next, set, bkVar.pN());
        bz<com.google.android.gms.internal.d.a> bzVar = bzVarA2 == atb ? atb : new bz<>(bzVarA2.getObject(), bzVarA.pX() && bzVarA2.pX());
        com.google.android.gms.internal.d.a aVarQm = next.qm();
        if (bzVar.pX()) {
            this.ati.e(str, new b(bzVar, aVarQm));
        }
        a(aVarQm, set);
        this.atm--;
        return bzVar;
    }

    private bz<com.google.android.gms.internal.d.a> a(Map<String, aj> map, cr.a aVar, Set<String> set, ck ckVar) {
        boolean z;
        com.google.android.gms.internal.d.a aVar2 = aVar.ql().get(com.google.android.gms.internal.b.FUNCTION.toString());
        if (aVar2 == null) {
            bh.T("No function id in properties");
            return atb;
        }
        String str = aVar2.gA;
        aj ajVar = map.get(str);
        if (ajVar == null) {
            bh.T(str + " has no backing implementation.");
            return atb;
        }
        bz<com.google.android.gms.internal.d.a> bzVar = this.ath.get(aVar);
        if (bzVar != null && !this.atd.pH()) {
            return bzVar;
        }
        HashMap map2 = new HashMap();
        boolean z2 = true;
        for (Map.Entry<String, com.google.android.gms.internal.d.a> entry : aVar.ql().entrySet()) {
            bz<com.google.android.gms.internal.d.a> bzVarA = a(entry.getValue(), set, ckVar.cJ(entry.getKey()).e(entry.getValue()));
            if (bzVarA == atb) {
                return atb;
            }
            if (bzVarA.pX()) {
                aVar.a(entry.getKey(), bzVarA.getObject());
                z = z2;
            } else {
                z = false;
            }
            map2.put(entry.getKey(), bzVarA.getObject());
            z2 = z;
        }
        if (!ajVar.a(map2.keySet())) {
            bh.T("Incorrect keys for function " + str + " required " + ajVar.pJ() + " had " + map2.keySet());
            return atb;
        }
        boolean z3 = z2 && ajVar.pe();
        bz<com.google.android.gms.internal.d.a> bzVar2 = new bz<>(ajVar.B(map2), z3);
        if (z3) {
            this.ath.e(aVar, bzVar2);
        }
        ckVar.d(bzVar2.getObject());
        return bzVar2;
    }

    private bz<Set<cr.a>> a(Set<cr.e> set, Set<String> set2, a aVar, cs csVar) {
        Set<cr.a> hashSet = new HashSet<>();
        Set<cr.a> hashSet2 = new HashSet<>();
        boolean z = true;
        for (cr.e eVar : set) {
            cn cnVarPV = csVar.pV();
            bz<Boolean> bzVarA = a(eVar, set2, cnVarPV);
            if (bzVarA.getObject().booleanValue()) {
                aVar.a(eVar, hashSet, hashSet2, cnVarPV);
            }
            z = z && bzVarA.pX();
        }
        hashSet.removeAll(hashSet2);
        csVar.b(hashSet);
        return new bz<>(hashSet, z);
    }

    private void a(com.google.android.gms.internal.d.a aVar, Set<String> set) {
        bz<com.google.android.gms.internal.d.a> bzVarA;
        if (aVar == null || (bzVarA = a(aVar, set, new bx())) == atb) {
            return;
        }
        Object objO = di.o(bzVarA.getObject());
        if (objO instanceof Map) {
            this.aqn.push((Map) objO);
            return;
        }
        if (!(objO instanceof List)) {
            bh.W("pushAfterEvaluate: value not a Map or List");
            return;
        }
        for (Object obj : (List) objO) {
            if (obj instanceof Map) {
                this.aqn.push((Map) obj);
            } else {
                bh.W("pushAfterEvaluate: value not a Map");
            }
        }
    }

    private static void a(List<cr.a> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            bh.U("Invalid resource: imbalance of rule names of functions for " + str + " operation. Using default rule name instead");
        }
    }

    private static void a(Map<String, aj> map, aj ajVar) {
        if (map.containsKey(ajVar.pI())) {
            throw new IllegalArgumentException("Duplicate function type name: " + ajVar.pI());
        }
        map.put(ajVar.pI(), ajVar);
    }

    private static c e(Map<String, c> map, String str) {
        c cVar = map.get(str);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c();
        map.put(str, cVar2);
        return cVar2;
    }

    private static String h(cr.a aVar) {
        return di.j(aVar.ql().get(com.google.android.gms.internal.b.INSTANCE_NAME.toString()));
    }

    private String qF() {
        if (this.atm <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.atm));
        for (int i = 2; i < this.atm; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    bz<Boolean> a(cr.a aVar, Set<String> set, ck ckVar) {
        bz<com.google.android.gms.internal.d.a> bzVarA = a(this.atf, aVar, set, ckVar);
        Boolean boolN = di.n(bzVarA.getObject());
        ckVar.d(di.u(boolN));
        return new bz<>(boolN, bzVarA.pX());
    }

    bz<Boolean> a(cr.e eVar, Set<String> set, cn cnVar) {
        Iterator<cr.a> it = eVar.qu().iterator();
        boolean z = true;
        while (it.hasNext()) {
            bz<Boolean> bzVarA = a(it.next(), set, cnVar.pP());
            if (bzVarA.getObject().booleanValue()) {
                cnVar.f(di.u(false));
                return new bz<>(false, bzVarA.pX());
            }
            z = z && bzVarA.pX();
        }
        Iterator<cr.a> it2 = eVar.qt().iterator();
        while (it2.hasNext()) {
            bz<Boolean> bzVarA2 = a(it2.next(), set, cnVar.pQ());
            if (!bzVarA2.getObject().booleanValue()) {
                cnVar.f(di.u(false));
                return new bz<>(false, bzVarA2.pX());
            }
            z = z && bzVarA2.pX();
        }
        cnVar.f(di.u(true));
        return new bz<>(true, z);
    }

    bz<Set<cr.a>> a(String str, Set<cr.e> set, final Map<cr.e, List<cr.a>> map, final Map<cr.e, List<String>> map2, final Map<cr.e, List<cr.a>> map3, final Map<cr.e, List<String>> map4, Set<String> set2, cs csVar) {
        return a(set, set2, new a() { // from class: com.google.android.gms.tagmanager.ct.3
            @Override // com.google.android.gms.tagmanager.ct.a
            public void a(cr.e eVar, Set<cr.a> set3, Set<cr.a> set4, cn cnVar) {
                List<cr.a> list = (List) map.get(eVar);
                List<String> list2 = (List) map2.get(eVar);
                if (list != null) {
                    set3.addAll(list);
                    cnVar.pR().c(list, list2);
                }
                List<cr.a> list3 = (List) map3.get(eVar);
                List<String> list4 = (List) map4.get(eVar);
                if (list3 != null) {
                    set4.addAll(list3);
                    cnVar.pS().c(list3, list4);
                }
            }
        }, csVar);
    }

    bz<Set<cr.a>> a(Set<cr.e> set, cs csVar) {
        return a(set, new HashSet(), new a() { // from class: com.google.android.gms.tagmanager.ct.4
            @Override // com.google.android.gms.tagmanager.ct.a
            public void a(cr.e eVar, Set<cr.a> set2, Set<cr.a> set3, cn cnVar) {
                set2.addAll(eVar.qv());
                set3.addAll(eVar.qw());
                cnVar.pT().c(eVar.qv(), eVar.qA());
                cnVar.pU().c(eVar.qw(), eVar.qB());
            }
        }, csVar);
    }

    void a(aj ajVar) {
        a(this.atg, ajVar);
    }

    void b(aj ajVar) {
        a(this.ate, ajVar);
    }

    void c(aj ajVar) {
        a(this.atf, ajVar);
    }

    public bz<com.google.android.gms.internal.d.a> cT(String str) {
        this.atm = 0;
        af afVarCC = this.atd.cC(str);
        bz<com.google.android.gms.internal.d.a> bzVarA = a(str, new HashSet(), afVarCC.pE());
        afVarCC.pG();
        return bzVarA;
    }

    synchronized void cU(String str) {
        this.atl = str;
    }

    public synchronized void cr(String str) {
        cU(str);
        af afVarCD = this.atd.cD(str);
        t tVarPF = afVarCD.pF();
        Iterator<cr.a> it = a(this.atj, tVarPF.py()).getObject().iterator();
        while (it.hasNext()) {
            a(this.ate, it.next(), new HashSet(), tVarPF.px());
        }
        afVarCD.pG();
        cU(null);
    }

    public synchronized void l(List<com.google.android.gms.internal.c.i> list) {
        for (com.google.android.gms.internal.c.i iVar : list) {
            if (iVar.name == null || !iVar.name.startsWith("gaExperiment:")) {
                bh.V("Ignored supplemental: " + iVar);
            } else {
                ai.a(this.aqn, iVar);
            }
        }
    }

    synchronized String qE() {
        return this.atl;
    }
}
