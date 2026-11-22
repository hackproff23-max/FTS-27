package com.flurry.sdk;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes.dex */
public final class et {
    private static final String a = et.class.getSimpleName();

    public static fc a(File file) throws Throwable {
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;
        fc fcVar;
        if (file == null || !file.exists()) {
            return null;
        }
        fc.a aVar = new fc.a();
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    dataInputStream = new DataInputStream(fileInputStream);
                    try {
                        if (dataInputStream.readUnsignedShort() != 46586) {
                            gd.a(3, a, "Unexpected file type");
                            hp.a(dataInputStream);
                            hp.a(fileInputStream);
                            return null;
                        }
                        int unsignedShort = dataInputStream.readUnsignedShort();
                        if (unsignedShort != 2) {
                            gd.a(6, a, "Unknown agent file version: " + unsignedShort);
                            hp.a(dataInputStream);
                            hp.a(fileInputStream);
                            return null;
                        }
                        fcVar = (fc) aVar.b(dataInputStream);
                        hp.a(dataInputStream);
                        hp.a(fileInputStream);
                        return fcVar;
                    } catch (Exception e) {
                        e = e;
                        gd.a(3, a, "Error loading legacy agent data.", e);
                        hp.a(dataInputStream);
                        hp.a(fileInputStream);
                        fcVar = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    dataInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    hp.a((Closeable) 0);
                    hp.a(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
            dataInputStream = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }
}
