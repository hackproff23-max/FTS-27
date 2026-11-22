package com.google.android.gms.tagmanager;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class az extends aj {
    private static final String ID = com.google.android.gms.internal.a.JOINER.toString();
    private static final String arp = com.google.android.gms.internal.b.ARG0.toString();
    private static final String arJ = com.google.android.gms.internal.b.ITEM_SEPARATOR.toString();
    private static final String arK = com.google.android.gms.internal.b.KEY_VALUE_SEPARATOR.toString();
    private static final String arL = com.google.android.gms.internal.b.ESCAPE.toString();

    private enum a {
        NONE,
        URL,
        BACKSLASH
    }

    public az() {
        super(ID, arp);
    }

    private String a(String str, a aVar, Set<Character> set) {
        switch (aVar) {
            case URL:
                try {
                    return dm.dg(str);
                } catch (UnsupportedEncodingException e) {
                    bh.b("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String strReplace = str.replace("\\", "\\\\");
                Iterator<Character> it = set.iterator();
                while (true) {
                    String str2 = strReplace;
                    if (!it.hasNext()) {
                        return str2;
                    }
                    String string = it.next().toString();
                    strReplace = str2.replace(string, "\\" + string);
                }
                break;
            default:
                return str;
        }
    }

    private void a(StringBuilder sb, String str, a aVar, Set<Character> set) {
        sb.append(a(str, aVar, set));
    }

    private void a(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        HashSet hashSet;
        a aVar;
        com.google.android.gms.internal.d.a aVar2 = map.get(arp);
        if (aVar2 == null) {
            return di.rb();
        }
        com.google.android.gms.internal.d.a aVar3 = map.get(arJ);
        String strJ = aVar3 != null ? di.j(aVar3) : "";
        com.google.android.gms.internal.d.a aVar4 = map.get(arK);
        String strJ2 = aVar4 != null ? di.j(aVar4) : "=";
        a aVar5 = a.NONE;
        com.google.android.gms.internal.d.a aVar6 = map.get(arL);
        if (aVar6 != null) {
            String strJ3 = di.j(aVar6);
            if ("url".equals(strJ3)) {
                aVar = a.URL;
                hashSet = null;
            } else {
                if (!"backslash".equals(strJ3)) {
                    bh.T("Joiner: unsupported escape type: " + strJ3);
                    return di.rb();
                }
                aVar = a.BACKSLASH;
                hashSet = new HashSet();
                a(hashSet, strJ);
                a(hashSet, strJ2);
                hashSet.remove('\\');
            }
        } else {
            hashSet = null;
            aVar = aVar5;
        }
        StringBuilder sb = new StringBuilder();
        switch (aVar2.type) {
            case 2:
                boolean z = true;
                com.google.android.gms.internal.d.a[] aVarArr = aVar2.gw;
                int length = aVarArr.length;
                int i = 0;
                while (i < length) {
                    com.google.android.gms.internal.d.a aVar7 = aVarArr[i];
                    if (!z) {
                        sb.append(strJ);
                    }
                    a(sb, di.j(aVar7), aVar, hashSet);
                    i++;
                    z = false;
                }
                break;
            case 3:
                for (int i2 = 0; i2 < aVar2.gx.length; i2++) {
                    if (i2 > 0) {
                        sb.append(strJ);
                    }
                    String strJ4 = di.j(aVar2.gx[i2]);
                    String strJ5 = di.j(aVar2.gy[i2]);
                    a(sb, strJ4, aVar, hashSet);
                    sb.append(strJ2);
                    a(sb, strJ5, aVar, hashSet);
                }
                break;
            default:
                a(sb, di.j(aVar2), aVar, hashSet);
                break;
        }
        return di.u(sb.toString());
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
