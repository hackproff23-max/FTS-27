package com.flurry.sdk;

import com.facebook.appevents.AppEventsConstants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class kb implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream p = new OutputStream() { // from class: com.flurry.sdk.kb.2
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    };
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private Writer k;
    private int m;
    private long j = 0;
    private final LinkedHashMap<String, b> l = new LinkedHashMap<>(0, 0.75f, true);
    private long n = 0;
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> o = new Callable<Void>() { // from class: com.flurry.sdk.kb.1
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (kb.this) {
                if (kb.this.k != null) {
                    kb.this.h();
                    if (kb.this.f()) {
                        kb.this.e();
                        kb.this.m = 0;
                    }
                }
            }
            return null;
        }
    };

    private kb(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static kb a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        kb kbVar = new kb(file, i, i2, j);
        if (kbVar.d.exists()) {
            try {
                kbVar.c();
                kbVar.d();
                kbVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(kbVar.d, true), kd.a));
                return kbVar;
            } catch (IOException e) {
                System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                kbVar.a();
            }
        }
        file.mkdirs();
        kb kbVar2 = new kb(file, i, i2, j);
        kbVar2.e();
        return kbVar2;
    }

    private void c() throws IOException {
        kc kcVar = new kc(new FileInputStream(this.d), kd.a);
        try {
            String strA = kcVar.a();
            String strA2 = kcVar.a();
            String strA3 = kcVar.a();
            String strA4 = kcVar.a();
            String strA5 = kcVar.a();
            if (!"libcore.io.DiskLruCache".equals(strA) || !AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(strA2) || !Integer.toString(this.g).equals(strA3) || !Integer.toString(this.i).equals(strA4) || !"".equals(strA5)) {
                throw new IOException("unexpected journal header: [" + strA + ", " + strA2 + ", " + strA4 + ", " + strA5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(kcVar.a());
                    i++;
                } catch (EOFException e) {
                    this.m = i - this.l.size();
                    kd.a(kcVar);
                    return;
                }
            }
        } catch (Throwable th) {
            kd.a(kcVar);
            throw th;
        }
    }

    private void d(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            String strSubstring2 = str.substring(i);
            if (iIndexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.l.remove(strSubstring2);
                return;
            }
            strSubstring = strSubstring2;
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        b bVar = this.l.get(strSubstring);
        if (bVar == null) {
            bVar = new b(strSubstring);
            this.l.put(strSubstring, bVar);
        }
        if (iIndexOf2 != -1 && iIndexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            bVar.d = true;
            bVar.e = null;
            bVar.a(strArrSplit);
            return;
        }
        if (iIndexOf2 != -1 || iIndexOf != "DIRTY".length() || !str.startsWith("DIRTY")) {
            if (iIndexOf2 != -1 || iIndexOf != "READ".length() || !str.startsWith("READ")) {
                throw new IOException("unexpected journal line: " + str);
            }
            return;
        }
        bVar.e = new a(bVar);
    }

    private void d() throws IOException {
        a(this.e);
        Iterator<b> it = this.l.values().iterator();
        while (it.hasNext()) {
            b next = it.next();
            if (next.e == null) {
                for (int i = 0; i < this.i; i++) {
                    this.j += next.c[i];
                }
            } else {
                next.e = null;
                for (int i2 = 0; i2 < this.i; i2++) {
                    a(next.a(i2));
                    a(next.b(i2));
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() throws IOException {
        try {
            if (this.k != null) {
                this.k.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), kd.a));
            try {
                bufferedWriter.write("libcore.io.DiskLruCache");
                bufferedWriter.write("\n");
                bufferedWriter.write(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.g));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(this.i));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (b bVar : this.l.values()) {
                    if (bVar.e != null) {
                        bufferedWriter.write("DIRTY " + bVar.b + '\n');
                    } else {
                        bufferedWriter.write("CLEAN " + bVar.b + bVar.a() + '\n');
                    }
                }
                bufferedWriter.close();
                if (this.d.exists()) {
                    a(this.d, this.f, true);
                }
                a(this.e, this.d, false);
                this.f.delete();
                this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), kd.a));
            } catch (Throwable th) {
                bufferedWriter.close();
                throw th;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized c a(String str) throws IOException {
        c cVar = null;
        synchronized (this) {
            try {
                g();
                e(str);
                b bVar = this.l.get(str);
                if (bVar != null && bVar.d) {
                    InputStream[] inputStreamArr = new InputStream[this.i];
                    for (int i = 0; i < this.i; i++) {
                        try {
                            inputStreamArr[i] = new FileInputStream(bVar.a(i));
                        } catch (FileNotFoundException e) {
                            for (int i2 = 0; i2 < this.i && inputStreamArr[i2] != null; i2++) {
                                kd.a(inputStreamArr[i2]);
                            }
                        }
                    }
                    this.m++;
                    this.k.append((CharSequence) ("READ " + str + '\n'));
                    if (f()) {
                        this.b.submit(this.o);
                    }
                    cVar = new c(str, bVar.f, inputStreamArr, bVar.c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    public a b(String str) throws IOException {
        return a(str, -1L);
    }

    private synchronized a a(String str, long j) throws IOException {
        b bVar;
        a aVar;
        g();
        e(str);
        b bVar2 = this.l.get(str);
        if (j == -1 || (bVar2 != null && bVar2.f == j)) {
            if (bVar2 == null) {
                b bVar3 = new b(str);
                this.l.put(str, bVar3);
                bVar = bVar3;
            } else if (bVar2.e != null) {
                aVar = null;
            } else {
                bVar = bVar2;
            }
            aVar = new a(bVar);
            bVar.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:29:0x005c A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0062 A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x0068 A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x0089 A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x009e A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ce A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00eb A[Catch: all -> 0x0012, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00f4 A[Catch: all -> 0x0012, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x000c, B:7:0x0011, B:12:0x0017, B:15:0x001e, B:17:0x0022, B:19:0x002a, B:20:0x0045, B:21:0x0046, B:23:0x0050, B:27:0x0058, B:29:0x005c, B:31:0x0062, B:33:0x0068, B:34:0x0086, B:35:0x0089, B:36:0x008d, B:38:0x009e, B:40:0x00ce, B:41:0x00d8, B:43:0x00e5, B:45:0x00eb, B:46:0x00f4), top: B:48:0x0002 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0086 A[SYNTHETIC] */
    public synchronized void a(a aVar, boolean z) throws IOException {
        File fileB;
        synchronized (this) {
            b bVar = aVar.b;
            if (bVar.e != aVar) {
                throw new IllegalStateException();
            }
            if (z && !bVar.d) {
                for (int i = 0; i < this.i; i++) {
                    if (!aVar.c[i]) {
                        aVar.b();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                    if (!bVar.b(i).exists()) {
                        aVar.b();
                    }
                }
                for (int i2 = 0; i2 < this.i; i2++) {
                    fileB = bVar.b(i2);
                    if (z) {
                        if (fileB.exists()) {
                            File fileA = bVar.a(i2);
                            fileB.renameTo(fileA);
                            long j = bVar.c[i2];
                            long length = fileA.length();
                            bVar.c[i2] = length;
                            this.j = (this.j - j) + length;
                        }
                    } else {
                        a(fileB);
                    }
                }
                this.m++;
                bVar.e = null;
                if (bVar.d | z) {
                    bVar.d = true;
                    this.k.write("CLEAN " + bVar.b + bVar.a() + '\n');
                    if (z) {
                        long j2 = this.n;
                        this.n = 1 + j2;
                        bVar.f = j2;
                    }
                } else {
                    this.l.remove(bVar.b);
                    this.k.write("REMOVE " + bVar.b + '\n');
                }
                this.k.flush();
                if (this.j <= this.h) {
                    this.b.submit(this.o);
                } else {
                    this.b.submit(this.o);
                }
            } else {
                while (i2 < this.i) {
                    fileB = bVar.b(i2);
                    if (z) {
                        if (fileB.exists()) {
                            File fileA2 = bVar.a(i2);
                            fileB.renameTo(fileA2);
                            long j3 = bVar.c[i2];
                            long length2 = fileA2.length();
                            bVar.c[i2] = length2;
                            this.j = (this.j - j3) + length2;
                        }
                    } else {
                        a(fileB);
                    }
                }
                this.m++;
                bVar.e = null;
                if (bVar.d | z) {
                    bVar.d = true;
                    this.k.write("CLEAN " + bVar.b + bVar.a() + '\n');
                    if (z) {
                        long j4 = this.n;
                        this.n = 1 + j4;
                        bVar.f = j4;
                    }
                } else {
                    this.l.remove(bVar.b);
                    this.k.write("REMOVE " + bVar.b + '\n');
                }
                this.k.flush();
                if (this.j <= this.h || f()) {
                    this.b.submit(this.o);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        return this.m >= 2000 && this.m >= this.l.size();
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        synchronized (this) {
            g();
            e(str);
            b bVar = this.l.get(str);
            if (bVar == null || bVar.e != null) {
                z = false;
            } else {
                for (int i = 0; i < this.i; i++) {
                    File fileA = bVar.a(i);
                    if (fileA.exists() && !fileA.delete()) {
                        throw new IOException("failed to delete " + fileA);
                    }
                    this.j -= bVar.c[i];
                    bVar.c[i] = 0;
                }
                this.m++;
                this.k.append((CharSequence) ("REMOVE " + str + '\n'));
                this.l.remove(str);
                if (f()) {
                    this.b.submit(this.o);
                }
                z = true;
            }
        }
        return z;
    }

    private void g() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.k != null) {
            for (b bVar : new ArrayList(this.l.values())) {
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            h();
            this.k.close();
            this.k = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() throws IOException {
        while (this.j > this.h) {
            c(this.l.entrySet().iterator().next().getKey());
        }
    }

    public void a() throws IOException {
        close();
        kd.a(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }

    public final class c implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private c(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.d) {
                kd.a(inputStream);
            }
        }
    }

    public final class a {
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        private a(b bVar) {
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[kb.this.i];
        }

        public OutputStream a(int i) throws IOException {
            OutputStream c0033a;
            FileOutputStream fileOutputStream;
            synchronized (kb.this) {
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                File fileB = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(fileB);
                } catch (FileNotFoundException e) {
                    kb.this.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(fileB);
                    } catch (FileNotFoundException e2) {
                        c0033a = kb.p;
                    }
                }
                c0033a = new C0033a(fileOutputStream);
            }
            return c0033a;
        }

        public void a() throws IOException {
            if (this.d) {
                kb.this.a(this, false);
                kb.this.c(this.b.b);
            } else {
                kb.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            kb.this.a(this, false);
        }

        /* JADX INFO: renamed from: com.flurry.sdk.kb$a$a, reason: collision with other inner class name */
        class C0033a extends FilterOutputStream {
            private C0033a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }
        }
    }

    final class b {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private b(String str) {
            this.b = str;
            this.c = new long[kb.this.i];
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.c) {
                sb.append(' ').append(j);
            }
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String[] strArr) throws IOException {
            if (strArr.length != kb.this.i) {
                throw b(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(kb.this.c, this.b + "." + i);
        }

        public File b(int i) {
            return new File(kb.this.c, this.b + "." + i + ".tmp");
        }
    }
}
