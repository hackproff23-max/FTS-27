package com.nuance.nmsp.client.sdk.oem;

import defpackage.bl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class d implements bl {
    private File a = null;
    private FileInputStream b = null;

    static {
        defpackage.d.a(h.class);
    }

    @Override // defpackage.bl
    public final int a(byte[] bArr, int i) {
        if (this.a == null) {
            return -1;
        }
        if (this.b == null) {
            try {
                this.b = new FileInputStream(this.a);
            } catch (FileNotFoundException e) {
                this.a = null;
                return -1;
            }
        }
        try {
            return this.b.read(bArr, 0, i);
        } catch (Exception e2) {
            try {
                this.b.close();
            } catch (IOException e3) {
            }
            this.b = null;
            this.a = null;
            return -1;
        }
    }

    @Override // defpackage.bl
    public final void a() {
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e) {
            }
        }
        this.b = null;
        this.a = null;
    }

    @Override // defpackage.bl
    public final boolean a(String str, bl.a aVar) {
        this.a = new File(str);
        boolean z = aVar == bl.a.a && !this.a.exists();
        if (z) {
            this.a = null;
        }
        return !z;
    }

    @Override // defpackage.bl
    public final long b() {
        if (this.a == null) {
            return -1L;
        }
        return this.a.length();
    }

    @Override // defpackage.bl
    public final boolean c() {
        if (this.a == null) {
            return false;
        }
        if (this.b != null) {
            try {
                this.b.close();
            } catch (IOException e) {
            }
        }
        this.b = null;
        File file = this.a;
        this.a = null;
        return file.delete();
    }
}
