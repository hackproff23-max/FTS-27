package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class gr {
    String b;
    LinkedHashMap<String, List<String>> c;
    private static final String d = gr.class.getSimpleName();
    public static final Integer a = 50;

    public gr(String str) throws Throwable {
        a(str);
    }

    void a(String str) throws Throwable {
        this.b = str + "Main";
        e(this.b);
    }

    private void e(String str) throws Throwable {
        this.c = new LinkedHashMap<>();
        ArrayList<String> arrayList = new ArrayList();
        if (j(str)) {
            List<String> listK = k(str);
            if (listK != null && listK.size() > 0) {
                arrayList.addAll(listK);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    f((String) it.next());
                }
            }
            i(str);
        } else {
            List list = (List) new fw(fp.a().c().getFileStreamPath(g(this.b)), str, 1, new ha<List<gs>>() { // from class: com.flurry.sdk.gr.1
                @Override // com.flurry.sdk.ha
                public gx<List<gs>> a(int i) {
                    return new gw(new gs.a());
                }
            }).a();
            if (list == null) {
                gd.c(d, "New main file also not found. returning..");
                return;
            } else {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList.add(((gs) it2.next()).a());
                }
            }
        }
        for (String str2 : arrayList) {
            List<String> listH = h(str2);
            if (listH != null) {
                this.c.put(str2, listH);
            }
        }
    }

    private void f(String str) throws Throwable {
        List<String> listK = k(str);
        if (listK == null) {
            gd.c(d, "No old file to replace");
            return;
        }
        for (String str2 : listK) {
            byte[] bArrM = m(str2);
            if (bArrM == null) {
                gd.a(6, d, "File does not exist");
            } else {
                a(str2, bArrM);
                l(str2);
            }
        }
        if (listK != null) {
            a(str, listK, ".YFlurrySenderIndex.info.");
            i(str);
        }
    }

    private String g(String str) {
        return ".YFlurrySenderIndex.info." + str;
    }

    private synchronized void c() {
        LinkedList linkedList = new LinkedList(this.c.keySet());
        b();
        if (!linkedList.isEmpty()) {
            a(this.b, linkedList, this.b);
        }
    }

    public synchronized void a(gq gqVar, String str) {
        List<String> linkedList;
        boolean z = false;
        synchronized (this) {
            gd.a(4, d, "addBlockInfo" + str);
            String strA = gqVar.a();
            List<String> list = this.c.get(str);
            if (list == null) {
                gd.a(4, d, "New Data Key");
                linkedList = new LinkedList();
                z = true;
            } else {
                linkedList = list;
            }
            linkedList.add(strA);
            if (linkedList.size() > a.intValue()) {
                b(linkedList.get(0));
                linkedList.remove(0);
            }
            this.c.put(str, linkedList);
            a(str, linkedList, ".YFlurrySenderIndex.info.");
            if (z) {
                c();
            }
        }
    }

    boolean b(String str) {
        return new fw(fp.a().c().getFileStreamPath(gq.a(str)), ".yflurrydatasenderblock.", 1, new ha<gq>() { // from class: com.flurry.sdk.gr.2
            @Override // com.flurry.sdk.ha
            public gx<gq> a(int i) {
                return new gq.a();
            }
        }).b();
    }

    public boolean a(String str, String str2) {
        List<String> list = this.c.get(str2);
        boolean zRemove = false;
        if (list != null) {
            b(str);
            zRemove = list.remove(str);
        }
        if (list != null && !list.isEmpty()) {
            this.c.put(str2, list);
            a(str2, list, ".YFlurrySenderIndex.info.");
        } else {
            d(str2);
        }
        return zRemove;
    }

    public List<String> a() {
        return new ArrayList(this.c.keySet());
    }

    public List<String> c(String str) {
        return this.c.get(str);
    }

    public synchronized boolean d(String str) {
        boolean zB;
        hp.b();
        fw fwVar = new fw(fp.a().c().getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new ha<List<gs>>() { // from class: com.flurry.sdk.gr.3
            @Override // com.flurry.sdk.ha
            public gx<List<gs>> a(int i) {
                return new gw(new gs.a());
            }
        });
        List<String> listC = c(str);
        if (listC != null) {
            gd.a(4, d, "discardOutdatedBlocksForDataKey: notSentBlocks = " + listC.size());
            for (String str2 : listC) {
                b(str2);
                gd.a(4, d, "discardOutdatedBlocksForDataKey: removed block = " + str2);
            }
        }
        this.c.remove(str);
        zB = fwVar.b();
        c();
        return zB;
    }

    void b() {
        new fw(fp.a().c().getFileStreamPath(g(this.b)), ".YFlurrySenderIndex.info.", 1, new ha<List<gs>>() { // from class: com.flurry.sdk.gr.4
            @Override // com.flurry.sdk.ha
            public gx<List<gs>> a(int i) {
                return new gw(new gs.a());
            }
        }).b();
    }

    private synchronized List<String> h(String str) {
        ArrayList arrayList;
        hp.b();
        gd.a(5, d, "Reading Index File for " + str + " file name:" + fp.a().c().getFileStreamPath(g(str)));
        List list = (List) new fw(fp.a().c().getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new ha<List<gs>>() { // from class: com.flurry.sdk.gr.5
            @Override // com.flurry.sdk.ha
            public gx<List<gs>> a(int i) {
                return new gw(new gs.a());
            }
        }).a();
        arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((gs) it.next()).a());
        }
        return arrayList;
    }

    private synchronized void a(String str, byte[] bArr) {
        hp.b();
        gd.a(5, d, "Saving Block File for " + str + " file name:" + fp.a().c().getFileStreamPath(gq.a(str)));
        new fw(fp.a().c().getFileStreamPath(gq.a(str)), ".yflurrydatasenderblock.", 1, new ha<gq>() { // from class: com.flurry.sdk.gr.6
            @Override // com.flurry.sdk.ha
            public gx<gq> a(int i) {
                return new gq.a();
            }
        }).a(new gq(bArr));
    }

    private void i(String str) {
        hp.b();
        gd.a(5, d, "Deleting Index File for " + str + " file name:" + fp.a().c().getFileStreamPath(".FlurrySenderIndex.info." + str));
        File fileStreamPath = fp.a().c().getFileStreamPath(".FlurrySenderIndex.info." + str);
        if (fileStreamPath.exists()) {
            gd.a(5, d, "Found file for " + str + ". Deleted - " + fileStreamPath.delete());
        }
    }

    private synchronized void a(String str, List<String> list, String str2) {
        hp.b();
        gd.a(5, d, "Saving Index File for " + str + " file name:" + fp.a().c().getFileStreamPath(g(str)));
        fw fwVar = new fw(fp.a().c().getFileStreamPath(g(str)), str2, 1, new ha<List<gs>>() { // from class: com.flurry.sdk.gr.7
            @Override // com.flurry.sdk.ha
            public gx<List<gs>> a(int i) {
                return new gw(new gs.a());
            }
        });
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new gs(it.next()));
        }
        fwVar.a(arrayList);
    }

    private synchronized boolean j(String str) {
        File fileStreamPath;
        fileStreamPath = fp.a().c().getFileStreamPath(".FlurrySenderIndex.info." + str);
        gd.a(5, d, "isOldIndexFilePresent: for " + str + fileStreamPath.exists());
        return fileStreamPath.exists();
    }

    private synchronized List<String> k(String str) {
        ArrayList arrayList;
        DataInputStream dataInputStream;
        Throwable th;
        ArrayList arrayList2 = null;
        synchronized (this) {
            hp.b();
            gd.a(5, d, "Reading Index File for " + str + " file name:" + fp.a().c().getFileStreamPath(".FlurrySenderIndex.info." + str));
            File fileStreamPath = fp.a().c().getFileStreamPath(".FlurrySenderIndex.info." + str);
            if (fileStreamPath.exists()) {
                gd.a(5, d, "Reading Index File for " + str + " Found file.");
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                    try {
                        try {
                            int unsignedShort = dataInputStream.readUnsignedShort();
                            if (unsignedShort == 0) {
                                hp.a(dataInputStream);
                            } else {
                                arrayList = new ArrayList(unsignedShort);
                                for (int i = 0; i < unsignedShort; i++) {
                                    try {
                                        int unsignedShort2 = dataInputStream.readUnsignedShort();
                                        gd.a(4, d, "read iter " + i + " dataLength = " + unsignedShort2);
                                        byte[] bArr = new byte[unsignedShort2];
                                        dataInputStream.readFully(bArr);
                                        arrayList.add(new String(bArr));
                                    } catch (Throwable th2) {
                                        th = th2;
                                        gd.a(6, d, "Error when loading persistent file", th);
                                        hp.a(dataInputStream);
                                        arrayList2 = arrayList;
                                        return arrayList2;
                                    }
                                }
                                if (dataInputStream.readUnsignedShort() == 0) {
                                }
                                hp.a(dataInputStream);
                            }
                        } catch (Throwable th3) {
                            arrayList = null;
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        hp.a(dataInputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    dataInputStream = null;
                    hp.a(dataInputStream);
                    throw th;
                }
            } else {
                gd.a(5, d, "Agent cache file doesn't exist.");
                arrayList = null;
            }
            arrayList2 = arrayList;
        }
        return arrayList2;
    }

    private void l(String str) {
        hp.b();
        gd.a(5, d, "Deleting  block File for " + str + " file name:" + fp.a().c().getFileStreamPath(".flurrydatasenderblock." + str));
        File fileStreamPath = fp.a().c().getFileStreamPath(".flurrydatasenderblock." + str);
        if (fileStreamPath.exists()) {
            gd.a(5, d, "Found file for " + str + ". Deleted - " + fileStreamPath.delete());
        }
    }

    private byte[] m(String str) throws Throwable {
        DataInputStream dataInputStream;
        Throwable th;
        byte[] bArr = null;
        hp.b();
        gd.a(5, d, "Reading block File for " + str + " file name:" + fp.a().c().getFileStreamPath(".flurrydatasenderblock." + str));
        File fileStreamPath = fp.a().c().getFileStreamPath(".flurrydatasenderblock." + str);
        if (fileStreamPath.exists()) {
            gd.a(5, d, "Reading Index File for " + str + " Found file.");
            try {
                dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                try {
                    try {
                        int unsignedShort = dataInputStream.readUnsignedShort();
                        if (unsignedShort == 0) {
                            hp.a(dataInputStream);
                        } else {
                            bArr = new byte[unsignedShort];
                            dataInputStream.readFully(bArr);
                            if (dataInputStream.readUnsignedShort() == 0) {
                            }
                            hp.a(dataInputStream);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        gd.a(6, d, "Error when loading persistent file", th);
                        hp.a(dataInputStream);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    hp.a(dataInputStream);
                    throw th;
                }
            } catch (Throwable th4) {
                dataInputStream = null;
                th = th4;
                hp.a(dataInputStream);
                throw th;
            }
        } else {
            gd.a(4, d, "Agent cache file doesn't exist.");
        }
        return bArr;
    }
}
