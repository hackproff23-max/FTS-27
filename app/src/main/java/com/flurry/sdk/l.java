package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    private static final String a = l.class.getSimpleName();

    public static List<az> a(File file) throws Throwable {
        DataInputStream dataInputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file == null || !file.exists()) {
            return Collections.emptyList();
        }
        az.b bVar = new az.b();
        ArrayList arrayList = new ArrayList();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                dataInputStream = new DataInputStream(fileInputStream);
                while (1 == dataInputStream.readShort()) {
                    try {
                        arrayList.add(bVar.b(dataInputStream));
                    } catch (Exception e) {
                        e = e;
                        fileInputStream2 = fileInputStream;
                        try {
                            gd.a(3, a, "Error loading legacy FreqCap data.", e);
                            hp.a(dataInputStream);
                            hp.a(fileInputStream2);
                            return arrayList;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            hp.a(dataInputStream);
                            hp.a(fileInputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        hp.a(dataInputStream);
                        hp.a(fileInputStream);
                        throw th;
                    }
                }
                hp.a(dataInputStream);
                hp.a(fileInputStream);
                return arrayList;
            } catch (Exception e2) {
                e = e2;
                dataInputStream = null;
                fileInputStream2 = fileInputStream;
            } catch (Throwable th3) {
                th = th3;
                dataInputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            dataInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = null;
            fileInputStream = null;
        }
    }

    public static List<at> b(File file) throws Throwable {
        DataInputStream dataInputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file == null || !file.exists()) {
            return Collections.emptyList();
        }
        at.a aVar = new at.a(new as.a());
        ArrayList arrayList = new ArrayList();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                dataInputStream = new DataInputStream(fileInputStream);
                try {
                    if (46586 != dataInputStream.readUnsignedShort()) {
                        throw new IOException("Unexpected data format");
                    }
                    while (1 == dataInputStream.readShort()) {
                        arrayList.add(aVar.b(dataInputStream));
                    }
                    hp.a(dataInputStream);
                    hp.a(fileInputStream);
                    return arrayList;
                } catch (Exception e) {
                    e = e;
                    fileInputStream2 = fileInputStream;
                    try {
                        gd.a(3, a, "Error loading legacy AdLog data.", e);
                        hp.a(dataInputStream);
                        hp.a(fileInputStream2);
                        return arrayList;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        hp.a(dataInputStream);
                        hp.a(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    hp.a(dataInputStream);
                    hp.a(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                dataInputStream = null;
                fileInputStream2 = fileInputStream;
            } catch (Throwable th3) {
                th = th3;
                dataInputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            dataInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = null;
            fileInputStream = null;
        }
    }
}
