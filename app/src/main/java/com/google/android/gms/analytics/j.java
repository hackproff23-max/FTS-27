package com.google.android.gms.analytics;

import com.google.android.gms.wallet.WalletConstants;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class j implements o {
    private final Set<Integer> yo = new HashSet();

    j() {
        this.yo.add(302);
        this.yo.add(Integer.valueOf(WalletConstants.ERROR_CODE_INVALID_PARAMETERS));
        this.yo.add(502);
    }

    @Override // com.google.android.gms.analytics.o
    public int eb() {
        return 2036;
    }

    @Override // com.google.android.gms.analytics.o
    public int ec() {
        return 8192;
    }

    @Override // com.google.android.gms.analytics.o
    public int ed() {
        return 8192;
    }

    @Override // com.google.android.gms.analytics.o
    public int ee() {
        return 20;
    }

    @Override // com.google.android.gms.analytics.o
    public long ef() {
        return 3600L;
    }

    @Override // com.google.android.gms.analytics.o
    public String eg() {
        return "/collect";
    }

    @Override // com.google.android.gms.analytics.o
    public String eh() {
        return "/batch";
    }

    @Override // com.google.android.gms.analytics.o
    public i ei() {
        return i.BATCH_BY_SESSION;
    }

    @Override // com.google.android.gms.analytics.o
    public l ej() {
        return l.GZIP;
    }

    @Override // com.google.android.gms.analytics.o
    public Set<Integer> ek() {
        return this.yo;
    }
}
