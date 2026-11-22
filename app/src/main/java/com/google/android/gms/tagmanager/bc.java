package com.google.android.gms.tagmanager;

import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class bc extends aj {
    private static final String ID = com.google.android.gms.internal.a.LANGUAGE.toString();

    public bc() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.aj
    public com.google.android.gms.internal.d.a B(Map<String, com.google.android.gms.internal.d.a> map) {
        String language;
        Locale locale = Locale.getDefault();
        if (locale != null && (language = locale.getLanguage()) != null) {
            return di.u(language.toLowerCase());
        }
        return di.rb();
    }

    @Override // com.google.android.gms.tagmanager.aj
    public boolean pe() {
        return false;
    }
}
