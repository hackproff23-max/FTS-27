package com.google.android.gms.analytics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.ha;
import com.google.android.gms.internal.ld;
import com.google.android.gms.internal.lf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: loaded from: classes.dex */
class ag implements d {
    private static final String BS = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id");
    private final a BT;
    private volatile r BU;
    private final String BV;
    private af BW;
    private long BX;
    private final int BY;
    private final Context mContext;
    private ld wb;
    private o ys;
    private volatile boolean yt;
    private final e zc;

    class a extends SQLiteOpenHelper {
        private boolean BZ;
        private long Ca;

        a(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
            this.Ca = 0L;
        }

        private void a(SQLiteDatabase sQLiteDatabase) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
            HashSet hashSet = new HashSet();
            try {
                for (String str : cursorRawQuery.getColumnNames()) {
                    hashSet.add(str);
                }
                cursorRawQuery.close();
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_string") || !hashSet.remove("hit_time")) {
                    throw new SQLiteException("Database column missing");
                }
                boolean z = hashSet.remove("hit_app_id") ? false : true;
                if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
                if (z) {
                    sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
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
                        ae.W("Error querying for table " + str);
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
            if (this.BZ && this.Ca + 3600000 > ag.this.wb.currentTimeMillis()) {
                throw new SQLiteException("Database creation failed");
            }
            SQLiteDatabase writableDatabase = null;
            this.BZ = true;
            this.Ca = ag.this.wb.currentTimeMillis();
            try {
                writableDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                ag.this.mContext.getDatabasePath(ag.this.BV).delete();
            }
            if (writableDatabase == null) {
                writableDatabase = super.getWritableDatabase();
            }
            this.BZ = false;
            return writableDatabase;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            t.ag(db.getPath());
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
            if (a("hits2", db)) {
                a(db);
            } else {
                db.execSQL(ag.BS);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    ag(e eVar, Context context, o oVar) {
        this(eVar, context, "google_analytics_v4.db", 2000, oVar);
    }

    ag(e eVar, Context context, String str, int i, o oVar) {
        this.yt = true;
        this.mContext = context.getApplicationContext();
        this.ys = oVar;
        this.BV = str;
        this.zc = eVar;
        this.wb = lf.m6if();
        this.BT = new a(this.mContext, this.BV);
        this.BU = new h(new DefaultHttpClient(), this.mContext, this.ys);
        this.BX = 0L;
        this.BY = i;
    }

    static String A(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(ac.encode(entry.getKey()) + "=" + ac.encode(entry.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    private void a(Map<String, String> map, long j, String str) {
        long j2;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for putHit");
        if (sQLiteDatabaseAl == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", A(map));
        contentValues.put("hit_time", Long.valueOf(j));
        if (map.containsKey("AppUID")) {
            try {
                j2 = Long.parseLong(map.get("AppUID"));
            } catch (NumberFormatException e) {
                j2 = 0;
            }
        } else {
            j2 = 0;
        }
        contentValues.put("hit_app_id", Long.valueOf(j2));
        if (str == null) {
            str = "http://www.google-analytics.com/collect";
        }
        if (str.length() == 0) {
            ae.W("Empty path: not sending hit");
            return;
        }
        contentValues.put("hit_url", str);
        try {
            sQLiteDatabaseAl.insert("hits2", null, contentValues);
            this.zc.B(false);
        } catch (SQLiteException e2) {
            ae.W("Error storing hit");
        }
    }

    private void a(Map<String, String> map, Collection<ha> collection) {
        String strSubstring = "&_v".substring(1);
        if (collection != null) {
            for (ha haVar : collection) {
                if ("appendVersion".equals(haVar.getId())) {
                    map.put(strSubstring, haVar.getValue());
                    return;
                }
            }
        }
    }

    private SQLiteDatabase al(String str) {
        try {
            return this.BT.getWritableDatabase();
        } catch (SQLiteException e) {
            ae.W(str);
            return null;
        }
    }

    private void fh() throws Throwable {
        int iFj = (fj() - this.BY) + 1;
        if (iFj > 0) {
            List<String> listG = G(iFj);
            ae.V("Store full, deleting " + listG.size() + " hits to make room.");
            b((String[]) listG.toArray(new String[0]));
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    List<String> G(int i) throws Throwable {
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            ae.W("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for peekHitIds.");
        if (sQLiteDatabaseAl == null) {
            return arrayList;
        }
        try {
            cursorQuery = sQLiteDatabaseAl.query("hits2", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
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
                    ae.W("Error in peekHits fetching hitIds: " + e.getMessage());
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

    /* JADX WARN: Code duplicated, block: B:35:0x00fe  */
    public List<ab> H(int i) throws Throwable {
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
            Cursor cursorQuery = sQLiteDatabaseAl.query("hits2", new String[]{"hit_id", "hit_time"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
            try {
                try {
                    ArrayList<ab> arrayList3 = new ArrayList();
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                arrayList3.add(new ab(null, cursorQuery.getLong(0), cursorQuery.getLong(1), ""));
                            } while (cursorQuery.moveToNext());
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        try {
                            try {
                                Cursor cursorQuery2 = sQLiteDatabaseAl.query("hits2", new String[]{"hit_id", "hit_string", "hit_url"}, null, null, null, null, String.format("%s ASC", "hit_id"), Integer.toString(i));
                                try {
                                    if (cursorQuery2.moveToFirst()) {
                                        int i2 = 0;
                                        while (true) {
                                            if (((SQLiteCursor) cursorQuery2).getWindow().getNumRows() > 0) {
                                                ((ab) arrayList3.get(i2)).aj(cursorQuery2.getString(1));
                                                ((ab) arrayList3.get(i2)).ak(cursorQuery2.getString(2));
                                            } else {
                                                ae.W(String.format("HitString for hitId %d too large.  Hit will be deleted.", Long.valueOf(((ab) arrayList3.get(i2)).fb())));
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
                                    ae.W("Error in peekHits fetching hitString: " + e.getMessage());
                                    ArrayList arrayList4 = new ArrayList();
                                    boolean z = false;
                                    for (ab abVar : arrayList3) {
                                        if (TextUtils.isEmpty(abVar.fa())) {
                                            if (z) {
                                                break;
                                            }
                                            z = true;
                                        }
                                        arrayList4.add(abVar);
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
                            ae.W("Error in peekHits fetching hitIds: " + sQLiteException.getMessage());
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
                } catch (Throwable th4) {
                    th = th4;
                    cursor2 = cursorQuery;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (SQLiteException e4) {
                sQLiteException = e4;
                cursor = cursorQuery;
                arrayList = arrayList2;
            }
        } catch (SQLiteException e5) {
            sQLiteException = e5;
            cursor = null;
            arrayList = arrayList2;
        } catch (Throwable th5) {
            th = th5;
        }
    }

    @Override // com.google.android.gms.analytics.d
    public void a(Map<String, String> map, long j, String str, Collection<ha> collection) throws Throwable {
        fi();
        fh();
        a(map, collection);
        a(map, j, str);
    }

    @Deprecated
    void b(Collection<ab> collection) {
        if (collection == null || collection.isEmpty()) {
            ae.W("Empty/Null collection passed to deleteHits.");
            return;
        }
        String[] strArr = new String[collection.size()];
        Iterator<ab> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = String.valueOf(it.next().fb());
            i++;
        }
        b(strArr);
    }

    void b(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            ae.W("Empty hitIds passed to deleteHits.");
            return;
        }
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for deleteHits.");
        if (sQLiteDatabaseAl != null) {
            try {
                sQLiteDatabaseAl.delete("hits2", String.format("HIT_ID in (%s)", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))), strArr);
                this.zc.B(fj() == 0);
            } catch (SQLiteException e) {
                ae.W("Error deleting hits " + TextUtils.join(",", strArr));
            }
        }
    }

    @Override // com.google.android.gms.analytics.d
    public r dV() {
        return this.BU;
    }

    @Override // com.google.android.gms.analytics.d
    public void dispatch() throws Throwable {
        ae.V("Dispatch running...");
        if (this.BU.ea()) {
            List<ab> listH = H(20);
            if (listH.isEmpty()) {
                ae.V("...nothing to dispatch");
                this.zc.B(true);
                return;
            }
            if (this.BW == null) {
                this.BW = new af("_t=dispatch&_v=ma4.0.4", false);
            }
            int iA = this.BU.a(listH, this.BW, fj() <= listH.size());
            ae.V("sent " + iA + " of " + listH.size() + " hits");
            b(listH.subList(0, Math.min(iA, listH.size())));
            if (iA != listH.size() || fj() <= 0) {
                this.BW = null;
            } else {
                GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
            }
        }
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
        int iDelete = sQLiteDatabaseAl.delete("hits2", "HIT_TIME < ?", new String[]{Long.toString(this.wb.currentTimeMillis() - 2592000000L)});
        this.zc.B(fj() == 0);
        return iDelete;
    }

    int fj() {
        Cursor cursorRawQuery = null;
        int i = 0;
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for getNumStoredHits.");
        try {
            if (sQLiteDatabaseAl != null) {
                try {
                    cursorRawQuery = sQLiteDatabaseAl.rawQuery("SELECT COUNT(*) from hits2", null);
                    i = cursorRawQuery.moveToFirst() ? (int) cursorRawQuery.getLong(0) : 0;
                } catch (SQLiteException e) {
                    ae.W("Error getting numStoredHits");
                }
            }
            return i;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    @Override // com.google.android.gms.analytics.d
    public void l(long j) {
        SQLiteDatabase sQLiteDatabaseAl = al("Error opening database for clearHits");
        if (sQLiteDatabaseAl != null) {
            if (j == 0) {
                sQLiteDatabaseAl.delete("hits2", null, null);
            } else {
                sQLiteDatabaseAl.delete("hits2", "hit_app_id = ?", new String[]{Long.valueOf(j).toString()});
            }
            this.zc.B(fj() == 0);
        }
    }

    @Override // com.google.android.gms.analytics.d
    public void setDryRun(boolean dryRun) {
        this.yt = dryRun;
        if (this.BU != null) {
            this.BU.setDryRun(dryRun);
        }
    }
}
