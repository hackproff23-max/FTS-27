package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class DataHolder implements SafeParcelable {
    public static final f CREATOR = new f();
    private static final a Ll = new a(new String[0], null) { // from class: com.google.android.gms.common.data.DataHolder.1
    };
    private final int CK;
    private final int Iv;
    private final String[] Ld;
    Bundle Le;
    private final CursorWindow[] Lf;
    private final Bundle Lg;
    int[] Lh;
    int Li;
    private Object Lj;
    private boolean Lk;
    boolean mClosed;

    public static class a {
        private final String[] Ld;
        private final ArrayList<HashMap<String, Object>> Lm;
        private final String Ln;
        private final HashMap<Object, Integer> Lo;
        private boolean Lp;
        private String Lq;

        private a(String[] strArr, String str) {
            this.Ld = (String[]) jx.i(strArr);
            this.Lm = new ArrayList<>();
            this.Ln = str;
            this.Lo = new HashMap<>();
            this.Lp = false;
            this.Lq = null;
        }
    }

    public static class b extends RuntimeException {
        public b(String str) {
            super(str);
        }
    }

    DataHolder(int versionCode, String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.Lk = true;
        this.CK = versionCode;
        this.Ld = columns;
        this.Lf = windows;
        this.Iv = statusCode;
        this.Lg = metadata;
    }

    private DataHolder(a builder, int statusCode, Bundle metadata) {
        this(builder.Ld, a(builder, -1), statusCode, metadata);
    }

    public DataHolder(String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.Lk = true;
        this.CK = 1;
        this.Ld = (String[]) jx.i(columns);
        this.Lf = (CursorWindow[]) jx.i(windows);
        this.Iv = statusCode;
        this.Lg = metadata;
        gX();
    }

    public static DataHolder a(int i, Bundle bundle) {
        return new DataHolder(Ll, i, bundle);
    }

    private static CursorWindow[] a(a aVar, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (aVar.Ld.length == 0) {
            return new CursorWindow[0];
        }
        List listSubList = (i < 0 || i >= aVar.Lm.size()) ? aVar.Lm : aVar.Lm.subList(0, i);
        int size = listSubList.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(aVar.Ld.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(aVar.Ld.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) listSubList.get(i3);
                boolean zPutDouble = true;
                for (int i4 = 0; i4 < aVar.Ld.length && zPutDouble; i4++) {
                    String str = aVar.Ld[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        zPutDouble = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        zPutDouble = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        zPutDouble = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        zPutDouble = cursorWindow2.putLong(((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        zPutDouble = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i3, i4);
                    } else if (obj instanceof byte[]) {
                        zPutDouble = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        zPutDouble = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else {
                        if (!(obj instanceof Float)) {
                            throw new IllegalArgumentException("Unsupported object for column " + str + ": " + obj);
                        }
                        zPutDouble = cursorWindow2.putDouble(((Float) obj).floatValue(), i3, i4);
                    }
                }
                if (zPutDouble) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else {
                    if (z2) {
                        throw new b("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(aVar.Ld.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static DataHolder av(int i) {
        return a(i, (Bundle) null);
    }

    private void g(String str, int i) {
        if (this.Le == null || !this.Le.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.Li) {
            throw new CursorIndexOutOfBoundsException(i, this.Li);
        }
    }

    public long a(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].getLong(i, this.Le.getInt(str));
    }

    public void a(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        g(str, i);
        this.Lf[i2].copyStringToBuffer(i, this.Le.getInt(str), charArrayBuffer);
    }

    public boolean aQ(String str) {
        return this.Le.containsKey(str);
    }

    public int au(int i) {
        int i2 = 0;
        jx.K(i >= 0 && i < this.Li);
        while (i2 < this.Lh.length) {
            if (i < this.Lh[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.Lh.length ? i2 - 1 : i2;
    }

    public int b(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].getInt(i, this.Le.getInt(str));
    }

    public String c(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].getString(i, this.Le.getInt(str));
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.Lf.length; i++) {
                    this.Lf[i].close();
                }
            }
        }
    }

    public boolean d(String str, int i, int i2) {
        g(str, i);
        return Long.valueOf(this.Lf[i2].getLong(i, this.Le.getInt(str))).longValue() == 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float e(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].getFloat(i, this.Le.getInt(str));
    }

    public void e(Object obj) {
        this.Lj = obj;
    }

    public byte[] f(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].getBlob(i, this.Le.getInt(str));
    }

    protected void finalize() throws Throwable {
        try {
            if (this.Lk && this.Lf.length > 0 && !isClosed()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + (this.Lj == null ? "internal object: " + toString() : this.Lj.toString()) + ")");
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public Uri g(String str, int i, int i2) {
        String strC = c(str, i, i2);
        if (strC == null) {
            return null;
        }
        return Uri.parse(strC);
    }

    public Bundle gV() {
        return this.Lg;
    }

    public void gX() {
        this.Le = new Bundle();
        for (int i = 0; i < this.Ld.length; i++) {
            this.Le.putInt(this.Ld[i], i);
        }
        this.Lh = new int[this.Lf.length];
        int numRows = 0;
        for (int i2 = 0; i2 < this.Lf.length; i2++) {
            this.Lh[i2] = numRows;
            numRows += this.Lf[i2].getNumRows() - (numRows - this.Lf[i2].getStartPosition());
        }
        this.Li = numRows;
    }

    String[] gY() {
        return this.Ld;
    }

    CursorWindow[] gZ() {
        return this.Lf;
    }

    public int getCount() {
        return this.Li;
    }

    public int getStatusCode() {
        return this.Iv;
    }

    int getVersionCode() {
        return this.CK;
    }

    public boolean h(String str, int i, int i2) {
        g(str, i);
        return this.Lf[i2].isNull(i, this.Le.getInt(str));
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        f.a(this, dest, flags);
    }
}
