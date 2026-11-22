package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
class v implements DataLayer.c {
    private static final String ara = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, "value", "expires");
    private final Executor arb;
    private a arc;
    private int ard;
    private final Context mContext;
    private ld wb;

    class a extends SQLiteOpenHelper {
        a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        private void a(SQLiteDatabase sQLiteDatabase) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
            HashSet hashSet = new HashSet();
            try {
                for (String str : cursorRawQuery.getColumnNames()) {
                    hashSet.add(str);
                }
                cursorRawQuery.close();
                if (!hashSet.remove(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY) || !hashSet.remove("value") || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                }
                if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } catch (Throwable th) {
                cursorRawQuery.close();
                throw th;
            }
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0048  */
        private boolean a(String str, SQLiteDatabase sQLiteDatabase) throws Throwable {
            Cursor cursor;
            Cursor cursor2 = null;
            try {
                Cursor cursorQuery = sQLiteDatabase.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, null, null, null);
                try {
                    boolean zMoveToFirst = cursorQuery.moveToFirst();
                    if (cursorQuery == null) {
                        return zMoveToFirst;
                    }
                    cursorQuery.close();
                    return zMoveToFirst;
                } catch (SQLiteException e) {
                    cursor = cursorQuery;
                    try {
                        bh.W("Error querying for table " + str);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        cursor2 = cursor;
                        th = th;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = cursorQuery;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e2) {
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase writableDatabase = null;
            try {
                writableDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                v.this.mContext.getDatabasePath("google_tagmanager.db").delete();
            }
            return writableDatabase == null ? super.getWritableDatabase() : writableDatabase;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            ak.ag(db.getPath());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase db) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor cursorRawQuery = db.rawQuery("PRAGMA journal_mode=memory", null);
                try {
                    cursorRawQuery.moveToFirst();
                    cursorRawQuery.close();
                } catch (Throwable th) {
                    cursorRawQuery.close();
                    throw th;
                }
            }
            if (a("datalayer", db)) {
                a(db);
            } else {
                db.execSQL(v.ara);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private static class b {
        final String KP;
        final byte[] arj;

        b(String str, byte[] bArr) {
            this.KP = str;
            this.arj = bArr;
        }

        public String toString() {
            return "KeyAndSerialized: key = " + this.KP + " serialized hash = " + Arrays.hashCode(this.arj);
        }
    }

    public v(Context context) {
        this(context, lf.m6if(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    v(Context context, ld ldVar, String str, int i, Executor executor) {
        this.mContext = context;
        this.wb = ldVar;
        this.ard = i;
        this.arb = executor;
        this.arc = new a(this.mContext, str);
    }

    private SQLiteDatabase al(String str) {
        try {
            return this.arc.getWritableDatabase();
        } catch (SQLiteException e) {
            bh.W(str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void b(List<b> list, long j) {
        try {
            long jCurrentTimeMillis = this.wb.currentTimeMillis();
            x(jCurrentTimeMillis);
            fI(list.size());
            c(list, jCurrentTimeMillis + j);
            pC();
        } catch (Throwable th) {
            pC();
            throw th;
        }
    }

    private void c(List<b> list, long j) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for writeEntryToDatabase.");
        if (sQLiteDatabaseAl == null) {
            return;
        }
        for (b bVar : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("expires", Long.valueOf(j));
            contentValues.put(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, bVar.KP);
            contentValues.put("value", bVar.arj);
            sQLiteDatabaseAl.insert("datalayer", null, contentValues);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cA(String str) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for clearKeysWithPrefix.");
        try {
            if (sQLiteDatabaseAl == null) {
                return;
            }
            bh.V("Cleared " + sQLiteDatabaseAl.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, str + ".%"}) + " items");
        } catch (SQLiteException e) {
            bh.W("Error deleting entries with key prefix: " + str + " (" + e + ").");
        } finally {
            pC();
        }
    }

    private void fI(int i) throws Throwable {
        int iPB = (pB() - this.ard) + i;
        if (iPB > 0) {
            List<String> listFJ = fJ(iPB);
            bh.U("DataLayer store full, deleting " + listFJ.size() + " entries to make room.");
            h((String[]) listFJ.toArray(new String[0]));
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    private List<String> fJ(int i) throws Throwable {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            bh.W("Invalid maxEntries specified. Skipping.");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for peekEntryIds.");
        if (sQLiteDatabaseAl == null) {
            return arrayList;
        }
        try {
            cursorQuery = sQLiteDatabaseAl.query("datalayer", new String[]{"ID"}, null, null, null, null, String.format("%s ASC", "ID"), Integer.toString(i));
            try {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            arrayList.add(String.valueOf(cursorQuery.getLong(0)));
                        } while (cursorQuery.moveToNext());
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } catch (SQLiteException e) {
                    e = e;
                    bh.W("Error in peekEntries fetching entryIds: " + e.getMessage());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        } catch (SQLiteException e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        return arrayList;
    }

    private void h(String[] strArr) {
        SQLiteDatabase sQLiteDatabaseAl;
        if (strArr == null || strArr.length == 0 || (sQLiteDatabaseAl = al("Error opening database for deleteEntries.")) == null) {
            return;
        }
        try {
            sQLiteDatabaseAl.delete("datalayer", String.format("%s in (%s)", "ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
        } catch (SQLiteException e) {
            bh.W("Error deleting entries " + Arrays.toString(strArr));
        }
    }

    private List<DataLayer.a> i(List<b> list) {
        ArrayList arrayList = new ArrayList();
        for (b bVar : list) {
            arrayList.add(new DataLayer.a(bVar.KP, j(bVar.arj)));
        }
        return arrayList;
    }

    private Object j(byte[] bArr) throws Throwable {
        ObjectInputStream objectInputStream;
        Throwable th;
        Object object = null;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            try {
                object = objectInputStream.readObject();
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                    }
                }
                byteArrayInputStream.close();
            } catch (IOException e2) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayInputStream.close();
            } catch (ClassNotFoundException e4) {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e5) {
                    }
                }
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th = th2;
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e6) {
                        throw th;
                    }
                }
                byteArrayInputStream.close();
                throw th;
            }
        } catch (IOException e7) {
            objectInputStream = null;
        } catch (ClassNotFoundException e8) {
            objectInputStream = null;
        } catch (Throwable th3) {
            objectInputStream = null;
            th = th3;
        }
        return object;
    }

    private List<b> j(List<DataLayer.a> list) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.a aVar : list) {
            arrayList.add(new b(aVar.KP, m(aVar.wF)));
        }
        return arrayList;
    }

    private byte[] m(Object obj) throws Throwable {
        ObjectOutputStream objectOutputStream;
        Throwable th;
        byte[] byteArray = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(obj);
                byteArray = byteArrayOutputStream.toByteArray();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                    }
                }
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e3) {
                    }
                }
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            objectOutputStream = null;
        } catch (Throwable th3) {
            objectOutputStream = null;
            th = th3;
        }
        return byteArray;
    }

    private List<b> pA() {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (sQLiteDatabaseAl == null) {
            return arrayList;
        }
        Cursor cursorQuery = sQLiteDatabaseAl.query("datalayer", new String[]{UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, "value"}, null, null, null, null, "ID", null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(new b(cursorQuery.getString(0), cursorQuery.getBlob(1)));
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    private int pB() {
        Cursor cursorRawQuery = null;
        int i = 0;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for getNumStoredEntries.");
        try {
            if (sQLiteDatabaseAl != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseAl.rawQuery("SELECT COUNT(*) from datalayer", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                } catch (SQLiteException e) {
                    bh.W("Error getting numStoredEntries");
                }
            }
            return i;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    private void pC() {
        try {
            this.arc.close();
        } catch (SQLiteException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<DataLayer.a> pz() {
        try {
            x(this.wb.currentTimeMillis());
            return i(pA());
        } finally {
            pC();
        }
    }

    private void x(long j) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for deleteOlderThan.");
        if (sQLiteDatabaseAl == null) {
            return;
        }
        try {
            bh.V("Deleted " + sQLiteDatabaseAl.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)}) + " expired items");
        } catch (SQLiteException e) {
            bh.W("Error deleting old entries.");
        }
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.c
    public void a(final DataLayer.c.a aVar) {
        this.arb.execute(new Runnable() { // from class: com.google.android.gms.tagmanager.v.2
            @Override // java.lang.Runnable
            public void run() {
                aVar.h(v.this.pz());
            }
        });
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.c
    public void a(List<DataLayer.a> list, final long j) {
        final List<b> listJ = j(list);
        this.arb.execute(new Runnable() { // from class: com.google.android.gms.tagmanager.v.1
            @Override // java.lang.Runnable
            public void run() {
                v.this.b(listJ, j);
            }
        });
    }

    @Override // com.google.android.gms.tagmanager.DataLayer.c
    public void cz(final String str) {
        this.arb.execute(new Runnable() { // from class: com.google.android.gms.tagmanager.v.3
            @Override // java.lang.Runnable
            public void run() {
                v.this.cA(str);
            }
        });
    }
}
