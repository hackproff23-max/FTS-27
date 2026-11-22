package com.applovin.impl.sdk;

import android.content.Context;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.immersion.hapticmediasdk.utils.FileManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class v {
    private final AppLovinLogger a;
    private final AppLovinSdkImpl b;
    private final String c = FileManager.TAG;
    private final Object d = new Object();

    v(AppLovinSdk appLovinSdk) {
        this.b = (AppLovinSdkImpl) appLovinSdk;
        this.a = appLovinSdk.getLogger();
    }

    long a(long j) {
        return j / 1048576;
    }

    public File a(String str, Context context, boolean z) {
        File file;
        File file2 = null;
        this.a.d(FileManager.TAG, "Looking up cached resource: " + str);
        if (a(context) || z) {
            if (str.contains("icon")) {
                str = str.replace("/", "_").replace(".", "_");
            }
            synchronized (this.d) {
                try {
                    File fileB = b(context);
                    file = new File(fileB, str);
                    try {
                        fileB.mkdirs();
                    } catch (Exception e) {
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            file2 = file;
        }
        return file2;
    }

    void a(long j, Context context) {
        long jC = c();
        if (jC == -1) {
            this.a.d(FileManager.TAG, "Cache has no maximum size set; skipping drop...");
        } else {
            if (a(j) <= jC) {
                this.a.d(FileManager.TAG, "Cache is present but under size limit; not dropping...");
                return;
            }
            this.a.d(FileManager.TAG, "Cache has exceeded maximum size; dropping...");
            f(context);
            this.b.b().a("cache_drop_count");
        }
    }

    boolean a() {
        return ((Boolean) this.b.a(bb.aA)).booleanValue();
    }

    protected boolean a(Context context) {
        return m.a("android.permission.WRITE_EXTERNAL_STORAGE", context);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    boolean a(ByteArrayOutputStream byteArrayOutputStream, File file) {
        FileOutputStream fileOutputStream;
        boolean z;
        this.a.d(FileManager.TAG, "Writing resource to filesystem: " + file.getName());
        synchronized (this.d) {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        try {
                            byteArrayOutputStream.writeTo(fileOutputStream);
                            z = true;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e) {
                                }
                            }
                        } catch (IOException e2) {
                            e = e2;
                            this.a.e(FileManager.TAG, "Unable to write data to file", e);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (Exception e3) {
                                    z = false;
                                }
                            }
                            z = false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            } catch (IOException e5) {
                e = e5;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    boolean a(File file) {
        boolean zDelete;
        this.a.d(FileManager.TAG, "Removing file " + file.getName() + " from filesystem...");
        synchronized (this.d) {
            try {
                zDelete = file.delete();
            } catch (Exception e) {
                this.a.e(FileManager.TAG, "Failed to remove file " + file.getName() + " from filesystem!", e);
                zDelete = false;
            }
        }
        return zDelete;
    }

    public boolean a(String str, Context context) {
        boolean zB;
        synchronized (this.d) {
            zB = b(str, context, false);
        }
        return zB;
    }

    long b() {
        long jLongValue = ((Long) this.b.a(bb.aB)).longValue();
        if (jLongValue < 0 || !a()) {
            return -1L;
        }
        return jLongValue;
    }

    File b(Context context) {
        return a(context) ? new File(context.getExternalFilesDir(null), "al") : new File(context.getCacheDir(), "al");
    }

    public boolean b(String str, Context context, boolean z) {
        boolean z2;
        synchronized (this.d) {
            File fileA = a(str, context, z);
            z2 = (fileA == null || !fileA.exists() || fileA.isDirectory()) ? false : true;
        }
        return z2;
    }

    int c() {
        int iIntValue = ((Integer) this.b.a(bb.aC)).intValue();
        if (iIntValue < 0 || !a()) {
            return -1;
        }
        return iIntValue;
    }

    public List c(Context context) {
        List listAsList;
        File fileB = b(context);
        if (!fileB.isDirectory()) {
            return new ArrayList(0);
        }
        synchronized (this.d) {
            listAsList = Arrays.asList(fileB.listFiles());
        }
        return listAsList;
    }

    void d(Context context) {
        try {
            if (a()) {
                if (this.b.isEnabled()) {
                    this.a.e(FileManager.TAG, "Cannot empty file cache after SDK has completed initialization and ad loads are in progress!");
                    return;
                }
                this.a.d(FileManager.TAG, "Compacting cache...");
                synchronized (this.d) {
                    a(e(context), context);
                }
            }
        } catch (Exception e) {
            this.a.e(FileManager.TAG, "Caught exception while compacting cache!", e);
            this.b.getSettingsManager().a(bb.aA, false);
            this.b.getSettingsManager().b();
        }
    }

    long e(Context context) {
        long length = 0;
        long jB = b();
        boolean z = jB != -1;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        synchronized (this.d) {
            for (File file : c(context)) {
                boolean z2 = false;
                if (z && seconds - TimeUnit.MILLISECONDS.toSeconds(file.lastModified()) > jB) {
                    this.a.d(FileManager.TAG, "File " + file.getName() + " has expired, removing...");
                    a(file);
                    z2 = true;
                }
                if (z2) {
                    this.b.b().a("cached_files_expired");
                } else {
                    length += file.length();
                }
            }
        }
        return length;
    }

    void f(Context context) {
        synchronized (this.d) {
            Iterator it = c(context).iterator();
            while (it.hasNext()) {
                a((File) it.next());
            }
        }
    }
}
