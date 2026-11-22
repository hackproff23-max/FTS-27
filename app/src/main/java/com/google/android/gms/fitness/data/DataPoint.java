package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.lt;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class DataPoint implements SafeParcelable {
    public static final Parcelable.Creator<DataPoint> CREATOR = new e();
    private final int CK;
    private final DataSource TN;
    private long Ud;
    private long Ue;
    private final Value[] Uf;
    private DataSource Ug;
    private long Uh;
    private long Ui;

    DataPoint(int versionCode, DataSource dataSource, long timestampNanos, long startTimeNanos, Value[] values, DataSource originalDataSource, long rawTimestamp, long insertionTimeMillis) {
        this.CK = versionCode;
        this.TN = dataSource;
        this.Ug = originalDataSource;
        this.Ud = timestampNanos;
        this.Ue = startTimeNanos;
        this.Uf = values;
        this.Uh = rawTimestamp;
        this.Ui = insertionTimeMillis;
    }

    private DataPoint(DataSource dataSource) {
        this.CK = 4;
        this.TN = (DataSource) jx.b(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.Uf = new Value[fields.size()];
        int i = 0;
        Iterator<Field> it = fields.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return;
            }
            this.Uf[i2] = new Value(it.next().getFormat());
            i = i2 + 1;
        }
    }

    DataPoint(List<DataSource> dataSources, RawDataPoint rawDataPoint) {
        this(4, c(dataSources, rawDataPoint.UJ), rawDataPoint.Ud, rawDataPoint.Ue, rawDataPoint.Uf, c(dataSources, rawDataPoint.UK), rawDataPoint.Uh, rawDataPoint.Ui);
    }

    private boolean a(DataPoint dataPoint) {
        return jv.equal(this.TN, dataPoint.TN) && this.Ud == dataPoint.Ud && this.Ue == dataPoint.Ue && Arrays.equals(this.Uf, dataPoint.Uf) && jv.equal(this.Ug, dataPoint.Ug);
    }

    private static DataSource c(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    private void cL(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        jx.b(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public static DataPoint create(DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    public static DataPoint extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) com.google.android.gms.common.internal.safeparcel.c.a(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    private boolean jq() {
        return getDataType() == DataType.TYPE_LOCATION_SAMPLE;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || ((o instanceof DataPoint) && a((DataPoint) o));
    }

    public DataSource getDataSource() {
        return this.TN;
    }

    public DataType getDataType() {
        return this.TN.getDataType();
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.Ud, TimeUnit.NANOSECONDS);
    }

    public DataSource getOriginalDataSource() {
        return this.Ug;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.Ue, TimeUnit.NANOSECONDS);
    }

    public long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.Ud, TimeUnit.NANOSECONDS);
    }

    public long getTimestampNanos() {
        return this.Ud;
    }

    public Value getValue(Field field) {
        return this.Uf[getDataType().indexOf(field)];
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.TN, Long.valueOf(this.Ud), Long.valueOf(this.Ue));
    }

    public Value[] jr() {
        return this.Uf;
    }

    public long js() {
        return this.Uh;
    }

    public long jt() {
        return this.Ui;
    }

    public long ju() {
        return this.Ue;
    }

    public DataPoint setFloatValues(float... values) {
        cL(values.length);
        for (int i = 0; i < values.length; i++) {
            this.Uf[i].setFloat(values[i]);
        }
        return this;
    }

    public DataPoint setIntValues(int... values) {
        cL(values.length);
        for (int i = 0; i < values.length; i++) {
            this.Uf[i].setInt(values[i]);
        }
        return this;
    }

    public DataPoint setTimeInterval(long startTime, long endTime, TimeUnit timeUnit) {
        this.Ue = timeUnit.toNanos(startTime);
        this.Ud = timeUnit.toNanos(endTime);
        return this;
    }

    public DataPoint setTimestamp(long timestamp, TimeUnit timeUnit) {
        this.Ud = timeUnit.toNanos(timestamp);
        if (jq() && lt.a(timeUnit)) {
            Log.w("Fitness", "Storing location at more than millisecond granularity is not supported. Extra precision is lost as the value is converted to milliseconds.");
            this.Ud = lt.a(this.Ud, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS);
        }
        return this;
    }

    public String toString() {
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", Arrays.toString(this.Uf), Long.valueOf(this.Ue), Long.valueOf(this.Ud), Long.valueOf(this.Uh), Long.valueOf(this.Ui), this.TN, this.Ug);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        e.a(this, parcel, flags);
    }
}
