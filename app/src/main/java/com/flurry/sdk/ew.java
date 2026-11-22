package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ew {
    private final Map<String, String> a = new HashMap();
    private int b;
    private String c;
    private long d;
    private boolean e;
    private boolean f;
    private long g;

    public ew(int i, String str, Map<String, String> map, long j, boolean z) {
        this.b = i;
        this.c = str;
        if (map != null) {
            this.a.putAll(map);
        }
        this.d = j;
        this.e = z;
        if (this.e) {
            this.f = false;
        } else {
            this.f = true;
        }
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return this.f;
    }

    public boolean a(String str) {
        return this.e && this.g == 0 && this.c.equals(str);
    }

    public void a(long j) {
        this.f = true;
        this.g = j - this.d;
        gd.a(3, "FlurryAgent", "Ended event '" + this.c + "' (" + this.d + ") after " + this.g + "ms");
    }

    public synchronized void a(Map<String, String> map) {
        if (map != null) {
            this.a.putAll(map);
        }
    }

    public synchronized Map<String, String> c() {
        return new HashMap(this.a);
    }

    public synchronized void b(Map<String, String> map) {
        this.a.clear();
        if (map != null) {
            this.a.putAll(map);
        }
    }

    public int d() {
        return e().length;
    }

    public synchronized byte[] e() {
        DataOutputStream dataOutputStream;
        Throwable th;
        DataOutputStream dataOutputStream2;
        byte[] byteArray;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                try {
                    dataOutputStream.writeShort(this.b);
                    dataOutputStream.writeUTF(this.c);
                    dataOutputStream.writeShort(this.a.size());
                    for (Map.Entry<String, String> entry : this.a.entrySet()) {
                        dataOutputStream.writeUTF(hp.b(entry.getKey()));
                        dataOutputStream.writeUTF(hp.b(entry.getValue()));
                    }
                    dataOutputStream.writeLong(this.d);
                    dataOutputStream.writeLong(this.g);
                    dataOutputStream.flush();
                    byteArray = byteArrayOutputStream.toByteArray();
                    hp.a(dataOutputStream);
                } catch (IOException e) {
                    dataOutputStream2 = dataOutputStream;
                    try {
                        byteArray = new byte[0];
                        hp.a(dataOutputStream2);
                    } catch (Throwable th2) {
                        th = th2;
                        dataOutputStream = dataOutputStream2;
                        hp.a(dataOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    hp.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                throw th4;
            }
        } catch (IOException e2) {
            dataOutputStream2 = null;
        } catch (Throwable th5) {
            dataOutputStream = null;
            th = th5;
        }
        return byteArray;
    }
}
