package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class m extends j<UserMetadata> {
    public m(String str, int i) {
        super(str, bp(str), Collections.emptyList(), i);
    }

    private String bo(String str) {
        return q(getName(), str);
    }

    private static Collection<String> bp(String str) {
        return Arrays.asList(q(str, "permissionId"), q(str, "displayName"), q(str, "picture"), q(str, "isAuthenticatedUser"), q(str, "emailAddress"));
    }

    private static String q(String str, String str2) {
        return str + "." + str2;
    }

    @Override // com.google.android.gms.drive.metadata.a
    protected boolean b(DataHolder dataHolder, int i, int i2) {
        return !dataHolder.h(bo("permissionId"), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.drive.metadata.a
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public UserMetadata c(DataHolder dataHolder, int i, int i2) {
        String strC = dataHolder.c(bo("permissionId"), i, i2);
        if (strC == null) {
            return null;
        }
        String strC2 = dataHolder.c(bo("displayName"), i, i2);
        String strC3 = dataHolder.c(bo("picture"), i, i2);
        Boolean boolValueOf = Boolean.valueOf(dataHolder.d(bo("isAuthenticatedUser"), i, i2));
        return new UserMetadata(strC, strC2, strC3, boolValueOf.booleanValue(), dataHolder.c(bo("emailAddress"), i, i2));
    }
}
