package com.flurry.sdk;

import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class am extends ai {
    private static final String d = am.class.getSimpleName();
    protected final al a;
    protected final String b;
    protected al.c c;

    public am(al alVar, String str) {
        this.a = alVar;
        this.b = str;
    }

    @Override // com.flurry.sdk.ai
    protected OutputStream f() throws IOException {
        if (this.c != null) {
            return this.c.a();
        }
        if (this.a == null) {
            throw new IOException("No cache specified");
        }
        if (TextUtils.isEmpty(this.b)) {
            throw new IOException("No cache key specified");
        }
        this.c = this.a.b(this.b);
        if (this.c == null) {
            throw new IOException("Could not open writer for key: " + this.b);
        }
        return this.c.a();
    }

    @Override // com.flurry.sdk.ai
    protected void g() {
        hp.a(this.c);
        this.c = null;
    }

    @Override // com.flurry.sdk.ai
    protected void h() {
        if (this.a != null && !TextUtils.isEmpty(this.b)) {
            try {
                this.a.c(this.b);
            } catch (Exception e) {
                gd.a(3, d, "Error removing result for key: " + this.b + " -- " + e);
            }
        }
    }
}
