package com.google.android.gms.tagmanager;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
class ch extends aj {
    private static final String ID = com.google.android.gms.internal.a.REGEX_GROUP.toString();
    private static final String asw = com.google.android.gms.internal.b.ARG0.toString();
    private static final String asx = com.google.android.gms.internal.b.ARG1.toString();
    private static final String asy = com.google.android.gms.internal.b.IGNORE_CASE.toString();
    private static final String asz = com.google.android.gms.internal.b.GROUP.toString();

    public ch() {
        super(ID, asw, asx);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        int iIntValue;
        com.google.android.gms.internal.d.a aVar = map.get(asw);
        com.google.android.gms.internal.d.a aVar2 = map.get(asx);
        if (aVar == null || aVar == di.rb() || aVar2 == null || aVar2 == di.rb()) {
            return di.rb();
        }
        int i = di.n(map.get(asy)).booleanValue() ? 66 : 64;
        com.google.android.gms.internal.d.a aVar3 = map.get(asz);
        if (aVar3 != null) {
            Long l = di.l(aVar3);
            if (l == di.qW()) {
                return di.rb();
            }
            iIntValue = l.intValue();
            if (iIntValue < 0) {
                return di.rb();
            }
        } else {
            iIntValue = 1;
        }
        try {
            String strJ = di.j(aVar);
            String strGroup = null;
            Matcher matcher = Pattern.compile(di.j(aVar2), i).matcher(strJ);
            if (matcher.find() && matcher.groupCount() >= iIntValue) {
                strGroup = matcher.group(iIntValue);
            }
            return strGroup == null ? di.rb() : di.u(strGroup);
        } catch (PatternSyntaxException e) {
            return di.rb();
        }
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return true;
    }
}
