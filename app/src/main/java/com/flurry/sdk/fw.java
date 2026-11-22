package com.flurry.sdk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class fw<T> {
    private static final String a = fw.class.getSimpleName();
    private final File b;
    private final gx<T> c;

    public fw(File file, String str, int i, ha<T> haVar) {
        this.b = file;
        this.c = new gv(new gz(str, i, haVar));
    }

    public T a() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        T tB = null;
        if (this.b != null) {
            if (!this.b.exists()) {
                gd.a(5, a, "No data to read for file:" + this.b.getName());
            } else {
                boolean z = false;
                try {
                    try {
                        fileInputStream = new FileInputStream(this.b);
                        try {
                            tB = this.c.b(fileInputStream);
                            hp.a(fileInputStream);
                        } catch (Exception e) {
                            e = e;
                            gd.a(3, a, "Error reading data file:" + this.b.getName(), e);
                            z = true;
                            hp.a(fileInputStream);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        hp.a(fileInputStream);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th3) {
                    fileInputStream = null;
                    th = th3;
                    hp.a(fileInputStream);
                    throw th;
                }
                if (z) {
                    gd.a(3, a, "Deleting data file:" + this.b.getName());
                    this.b.delete();
                }
            }
        }
        return tB;
    }

    public void a(T t) throws Throwable {
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        try {
            if (t == null) {
                gd.a(3, a, "No data to write for file:" + this.b.getName());
                z = true;
            } else {
                try {
                    if (!ho.a(this.b)) {
                        throw new IOException("Cannot create parent directory!");
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(this.b);
                    try {
                        this.c.a(fileOutputStream2, t);
                        hp.a(fileOutputStream2);
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        gd.a(3, a, "Error writing data file:" + this.b.getName(), e);
                        hp.a(fileOutputStream);
                        z = true;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        hp.a(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (z) {
                gd.a(3, a, "Deleting data file:" + this.b.getName());
                this.b.delete();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public boolean b() {
        if (this.b == null) {
            return false;
        }
        return this.b.delete();
    }
}
