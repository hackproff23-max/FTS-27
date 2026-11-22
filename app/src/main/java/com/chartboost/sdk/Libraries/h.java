package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.chartboost.sdk.impl.cd;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class h {
    private static h f;
    public Context a = com.chartboost.sdk.b.l();
    private File b;
    private File c;
    private File d;
    private boolean e;

    public h(String str, boolean z) {
        if (this.a == null) {
            throw new RuntimeException("Cannot find context object");
        }
        this.e = this.a.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (str != null) {
            this.b = a(str, z);
        } else {
            this.b = a("CBCommonCacheFolder", z);
        }
    }

    private File a(String str, boolean z) {
        File file;
        if (this.b != null) {
            return this.b;
        }
        if (z && d()) {
            file = new File(new File(this.a.getExternalCacheDir(), "__chartboost"), str);
            this.d = file;
        } else {
            file = new File(new File(this.a.getCacheDir(), "__chartboost"), str);
            this.c = file;
        }
        if (!file.exists()) {
            file.mkdirs();
            return file;
        }
        return file;
    }

    public synchronized void a(String str, e.a aVar) {
        if ((this.d == null || d()) && this.b != null) {
            File file = null;
            if (!TextUtils.isEmpty(str)) {
                file = new File(this.b.getPath(), str);
            }
            a(file, aVar);
        }
    }

    public synchronized File a(File file, e.a aVar) {
        File file2 = null;
        synchronized (this) {
            if ((this.d == null || d()) && this.b != null) {
                file2 = file == null ? new File(this.b.getPath(), Long.toString(System.nanoTime())) : file;
                try {
                    cd.a(file2, aVar.toString().getBytes());
                } catch (IOException e) {
                    CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
                }
            }
        }
        return file2;
    }

    public synchronized void a(String str, byte[] bArr) {
        if ((this.d == null || d()) && this.b != null) {
            File file = null;
            if (!TextUtils.isEmpty(str)) {
                file = new File(this.b.getPath(), str);
            }
            a(file, bArr);
        }
    }

    public synchronized void a(File file, byte[] bArr) {
        if ((this.d == null || d()) && this.b != null && bArr != null) {
            if (file == null) {
                file = new File(this.b.getPath(), Long.toString(System.nanoTime()));
            }
            try {
                cd.a(file, bArr);
            } catch (IOException e) {
                CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
            }
        }
    }

    public synchronized e.a a(String str) {
        e.a aVarA;
        if ((this.d != null && !d()) || this.b == null || str == null) {
            aVarA = e.a.a;
        } else {
            File file = new File(this.b, str);
            if (!file.exists()) {
                aVarA = e.a.a;
            } else {
                aVarA = a(file);
            }
        }
        return aVarA;
    }

    public synchronized e.a a(File file) {
        String str;
        e.a aVarJ;
        if ((this.d != null && !d()) || this.b == null) {
            aVarJ = e.a.a;
        } else {
            try {
                str = new String(cd.b(file));
            } catch (Exception e) {
                CBLogging.b("CBFileCache", "Error loading cache from disk", e);
                str = null;
            }
            aVarJ = e.a.j(str);
        }
        return aVarJ;
    }

    public synchronized byte[] b(File file) {
        byte[] bArrB = null;
        synchronized (this) {
            try {
                if ((this.d == null || d()) && this.b != null) {
                    try {
                        bArrB = cd.b(file);
                    } catch (Exception e) {
                        CBLogging.b("CBFileCache", "Error loading cache from disk", e);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArrB;
    }

    public synchronized String[] a() {
        String[] list = null;
        synchronized (this) {
            if ((this.d == null || d()) && this.b != null) {
                list = this.b.list();
            }
        }
        return list;
    }

    public synchronized void c(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(d(str));
        }
    }

    public synchronized void b() {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        synchronized (this) {
            if ((this.d == null || d()) && this.b != null) {
                try {
                    if (this.d != null && (fileArrListFiles2 = this.d.listFiles()) != null) {
                        for (File file : fileArrListFiles2) {
                            file.delete();
                        }
                    }
                    if (this.c != null && (fileArrListFiles = this.c.listFiles()) != null) {
                        for (File file2 : fileArrListFiles) {
                            file2.delete();
                        }
                    }
                } catch (Exception e) {
                    CBLogging.b("CBFileCache", "Error while clearing the file cache");
                }
            }
        }
    }

    private boolean d() {
        return this.e && Environment.getExternalStorageState().equals("mounted");
    }

    public boolean c(String str) {
        if ((this.d != null && !d()) || this.b == null || str == null) {
            return false;
        }
        return new File(this.b.getPath(), str).exists();
    }

    public File d(String str) {
        if ((this.d == null || d()) && this.b != null) {
            return new File(this.b.getPath(), str);
        }
        return null;
    }

    public static synchronized h c() {
        if (f == null) {
            f = new h("CBVideoCompletion", false);
        }
        return f;
    }
}
