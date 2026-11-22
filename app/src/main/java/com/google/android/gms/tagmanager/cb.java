package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: loaded from: classes.dex */
class cb implements at {
    private static final String BS = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", "gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time");
    private final String BV;
    private long BX;
    private final int BY;
    private final b asf;
    private volatile ab asg;
    private final au ash;
    private final Context mContext;
    private ld wb;

    class a implements db.a {
        a() {
        }

        @Override // com.google.android.gms.tagmanager.db.a
        public void a(ap apVar) {
            cb.this.y(apVar.fb());
        }

        @Override // com.google.android.gms.tagmanager.db.a
        public void b(ap apVar) {
            cb.this.y(apVar.fb());
            bh.V("Permanent failure dispatching hitId: " + apVar.fb());
        }

        @Override // com.google.android.gms.tagmanager.db.a
        public void c(ap apVar) {
            long jPK = apVar.pK();
            if (jPK == 0) {
                cb.this.c(apVar.fb(), cb.this.wb.currentTimeMillis());
            } else if (jPK + 14400000 < cb.this.wb.currentTimeMillis()) {
                cb.this.y(apVar.fb());
                bh.V("Giving up on failed hitId: " + apVar.fb());
            }
        }
    }

    class b extends SQLiteOpenHelper {
        private boolean BZ;
        private long Ca;

        b(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
            this.Ca = 0L;
        }

        private void a(SQLiteDatabase sQLiteDatabase) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", null);
            HashSet hashSet = new HashSet();
            try {
                for (String str : cursorRawQuery.getColumnNames()) {
                    hashSet.add(str);
                }
                cursorRawQuery.close();
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
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
            if (this.BZ && this.Ca + 3600000 > cb.this.wb.currentTimeMillis()) {
                throw new SQLiteException("Database creation failed");
            }
            SQLiteDatabase writableDatabase = null;
            this.BZ = true;
            this.Ca = cb.this.wb.currentTimeMillis();
            try {
                writableDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                cb.this.mContext.getDatabasePath(cb.this.BV).delete();
            }
            if (writableDatabase == null) {
                writableDatabase = super.getWritableDatabase();
            }
            this.BZ = false;
            return writableDatabase;
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
            if (a("gtm_hits", db)) {
                a(db);
            } else {
                db.execSQL(cb.BS);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    cb(au auVar, Context context) {
        this(auVar, context, "gtm_urls.db", 2000);
    }

    cb(au auVar, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.BV = str;
        this.ash = auVar;
        this.wb = lf.m6if();
        this.asf = new b(this.mContext, this.BV);
        this.asg = new db(new DefaultHttpClient(), this.mContext, new a());
        this.BX = 0L;
        this.BY = i;
    }

    private SQLiteDatabase al(String str) {
        try {
            return this.asf.getWritableDatabase();
        } catch (SQLiteException e) {
            bh.W(str);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(long j, long j2) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for getNumStoredHits.");
        if (sQLiteDatabaseAl == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_first_send_time", Long.valueOf(j2));
        try {
            sQLiteDatabaseAl.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
        } catch (SQLiteException e) {
            bh.W("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
            y(j);
        }
    }

    private void fh() throws Throwable {
        int iFj = (fj() - this.BY) + 1;
        if (iFj > 0) {
            List<String> listG = G(iFj);
            bh.V("Store full, deleting " + listG.size() + " hits to make room.");
            b((String[]) listG.toArray(new String[0]));
        }
    }

    private void g(long j, String str) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for putHit");
        if (sQLiteDatabaseAl == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_time", Long.valueOf(j));
        contentValues.put("hit_url", str);
        contentValues.put("hit_first_send_time", (Integer) 0);
        try {
            sQLiteDatabaseAl.insert("gtm_hits", null, contentValues);
            this.ash.B(false);
        } catch (SQLiteException e) {
            bh.W("Error storing hit");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(long j) {
        b(new String[]{String.valueOf(j)});
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    List<String> G(int i) throws Throwable {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            bh.W("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for peekHitIds.");
        if (sQLiteDatabaseAl == null) {
            return arrayList;
        }
        try {
            cursorQuery = sQLiteDatabaseAl.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
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
                    bh.W("Error in peekHits fetching hitIds: " + e.getMessage());
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

    /* JADX WARN: Code duplicated, block: B:35:0x00f2  */
    public List<ap> H(int i) throws Throwable {
        SQLiteException sQLiteException;
        Cursor cursor;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for peekHits");
        if (sQLiteDatabaseAl == null) {
            return arrayList2;
        }
        Cursor cursor2 = null;
        try {
            Cursor cursorQuery = sQLiteDatabaseAl.query("gtm_hits", new String[]{"hit_id", "hit_time", "hit_first_send_time"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
            try {
                try {
                    ArrayList<ap> arrayList3 = new ArrayList();
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                arrayList3.add(new ap(cursorQuery.getLong(0), cursorQuery.getLong(1), cursorQuery.getLong(2)));
                            } while (cursorQuery.moveToNext());
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        try {
                            try {
                                Cursor cursorQuery2 = sQLiteDatabaseAl.query("gtm_hits", new String[]{"hit_id", "hit_url"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
                                try {
                                    if (cursorQuery2.moveToFirst()) {
                                        int i2 = 0;
                                        while (true) {
                                            if (((SQLiteCursor) cursorQuery2).getWindow().getNumRows() > 0) {
                                                ((ap) arrayList3.get(i2)).ak(cursorQuery2.getString(1));
                                            } else {
                                                bh.W(String.format("HitString for hitId %d too large.  Hit will be deleted.", Long.valueOf(((ap) arrayList3.get(i2)).fb())));
                                            }
                                            int i3 = i2 + 1;
                                            if (!cursorQuery2.moveToNext()) {
                                                break;
                                            }
                                            i2 = i3;
                                        }
                                    }
                                    if (cursorQuery2 != null) {
                                        cursorQuery2.close();
                                    }
                                    return arrayList3;
                                } catch (SQLiteException e) {
                                    e = e;
                                    cursorQuery = cursorQuery2;
                                    bh.W("Error in peekHits fetching hit url: " + e.getMessage());
                                    ArrayList arrayList4 = new ArrayList();
                                    boolean z = false;
                                    for (ap apVar : arrayList3) {
                                        if (TextUtils.isEmpty(apVar.pL())) {
                                            if (z) {
                                                break;
                                            }
                                            z = true;
                                        }
                                        arrayList4.add(apVar);
                                    }
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    return arrayList4;
                                } catch (Throwable th) {
                                    th = th;
                                    cursorQuery = cursorQuery2;
                                    if (cursorQuery != null) {
                                        cursorQuery.close();
                                    }
                                    throw th;
                                }
                            } catch (SQLiteException e2) {
                                e = e2;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (SQLiteException e3) {
                        sQLiteException = e3;
                        cursor = cursorQuery;
                        arrayList = arrayList3;
                        try {
                            bh.W("Error in peekHits fetching hitIds: " + sQLiteException.getMessage());
                            if (cursor == null) {
                                return arrayList;
                            }
                            cursor.close();
                            return arrayList;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    }
                } catch (SQLiteException e4) {
                    sQLiteException = e4;
                    cursor = cursorQuery;
                    arrayList = arrayList2;
                }
            } catch (Throwable th4) {
                th = th4;
                cursor2 = cursorQuery;
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (SQLiteException e5) {
            sQLiteException = e5;
            cursor = null;
            arrayList = arrayList2;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    void b(String[] strArr) {
        SQLiteDatabase sQLiteDatabaseAl;
        if (strArr == null || strArr.length == 0 || (sQLiteDatabaseAl = al("Error opening database for deleteHits.")) == null) {
            return;
        }
        try {
            sQLiteDatabaseAl.delete("gtm_hits", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
            this.ash.B(fj() == 0);
        } catch (SQLiteException e) {
            bh.W("Error deleting hits");
        }
    }

    @Override // com.google.android.gms.tagmanager.at
    public void dispatch() throws Throwable {
        bh.V("GTM Dispatch running...");
        if (this.asg.ea()) {
            List<ap> listH = H(40);
            if (listH.isEmpty()) {
                bh.V("...nothing to dispatch");
                this.ash.B(true);
            } else {
                this.asg.k(listH);
                if (pY() > 0) {
                    cy.qN().dispatch();
                }
            }
        }
    }

    @Override // com.google.android.gms.tagmanager.at
    public void f(long j, String str) throws Throwable {
        fi();
        fh();
        g(j, str);
    }

    int fi() {
        long jCurrentTimeMillis = this.wb.currentTimeMillis();
        if (jCurrentTimeMillis <= this.BX + 86400000) {
            return 0;
        }
        this.BX = jCurrentTimeMillis;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for deleteStaleHits.");
        if (sQLiteDatabaseAl == null) {
            return 0;
        }
        int iDelete = sQLiteDatabaseAl.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.wb.currentTimeMillis() - 2592000000L)});
        this.ash.B(fj() == 0);
        return iDelete;
    }

    int fj() {
        Cursor cursorRawQuery = null;
        int i = 0;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for getNumStoredHits.");
        try {
            if (sQLiteDatabaseAl != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseAl.rawQuery("SELECT COUNT(*) from gtm_hits", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                } catch (SQLiteException e) {
                    bh.W("Error getting numStoredHits");
                }
            }
            return i;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0040  */
    int pY() throws Throwable {
        Cursor cursor;
        int count;
        Cursor cursor2 = null;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for getNumStoredHits.");
        if (sQLiteDatabaseAl == null) {
            return 0;
        }
        try {
            Cursor cursorQuery = sQLiteDatabaseAl.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
            try {
                count = cursorQuery.getCount();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (SQLiteException e) {
                cursor = cursorQuery;
                try {
                    bh.W("Error getting num untried hits");
                    if (cursor != null) {
                        cursor.close();
                        count = 0;
                    } else {
                        count = 0;
                    }
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
        return count;
    }
}
