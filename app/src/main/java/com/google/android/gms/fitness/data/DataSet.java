package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class DataSet implements SafeParcelable {
    public static final Parcelable.Creator<DataSet> CREATOR = new f();
    private final int CK;
    private final DataType TM;
    private final DataSource TN;
    private boolean Uc;
    private final List<DataPoint> Uj;
    private final List<DataSource> Uk;

    DataSet(int versionCode, DataSource dataSource, DataType dataType, List<RawDataPoint> dataPoints, List<DataSource> uniqueDataSources, boolean serverHasMoreData) {
        this.Uc = false;
        this.CK = versionCode;
        this.TN = dataSource;
        this.TM = dataType;
        this.Uc = serverHasMoreData;
        this.Uj = new ArrayList(dataPoints.size());
        this.Uk = versionCode < 2 ? Collections.singletonList(dataSource) : uniqueDataSources;
        Iterator<RawDataPoint> it = dataPoints.iterator();
        while (it.hasNext()) {
            this.Uj.add(new DataPoint(this.Uk, it.next()));
        }
    }

    private DataSet(DataSource dataSource, DataType dataType) {
        this.Uc = false;
        this.CK = 3;
        this.TN = (DataSource) jx.i(dataSource);
        this.TM = (DataType) jx.i(dataType);
        this.Uj = new ArrayList();
        this.Uk = new ArrayList();
        this.Uk.add(this.TN);
    }

    public DataSet(RawDataSet dataSet, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this(3, (DataSource) d(uniqueDataSources, dataSet.UJ), (DataType) d(uniqueDataTypes, dataSet.UL), dataSet.UM, uniqueDataSources, dataSet.Uc);
    }

    private boolean a(DataSet dataSet) {
        return jv.equal(this.TM, dataSet.TM) && jv.equal(this.TN, dataSet.TN) && jv.equal(this.Uj, dataSet.Uj) && this.Uc == dataSet.Uc;
    }

    public static DataSet create(DataSource dataSource) {
        jx.b(dataSource, "DataSource should be specified");
        return new DataSet(dataSource, dataSource.getDataType());
    }

    private static <T> T d(List<T> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public void a(Iterable<DataPoint> iterable) {
        Iterator<DataPoint> it = iterable.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    public void add(DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        jx.b(dataSource.getStreamIdentifier().equals(this.TN.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.TN);
        jx.b(dataPoint.getDataType().getName().equals(this.TM.getName()), "Conflicting data types found %s vs %s", dataPoint.getDataType(), this.TM);
        jx.b(dataPoint.getTimestamp(TimeUnit.NANOSECONDS) > 0, "Data point does not have the timestamp set: %s", dataPoint);
        jx.b(dataPoint.getStartTime(TimeUnit.NANOSECONDS) <= dataPoint.getEndTime(TimeUnit.NANOSECONDS), "Data point with start time greater than end time found: %s", dataPoint);
        b(dataPoint);
    }

    public void addAll(Iterable<DataPoint> dataPoints) {
        Iterator<DataPoint> it = dataPoints.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public void b(DataPoint dataPoint) {
        this.Uj.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource == null || this.Uk.contains(originalDataSource)) {
            return;
        }
        this.Uk.add(originalDataSource);
    }

    public DataPoint createDataPoint() {
        return DataPoint.create(this.TN);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof DataSet) && a((DataSet) o));
    }

    List<RawDataPoint> f(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.Uj.size());
        Iterator<DataPoint> it = this.Uj.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataPoint(it.next(), list));
        }
        return arrayList;
    }

    public List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.Uj);
    }

    public DataSource getDataSource() {
        return this.TN;
    }

    public DataType getDataType() {
        return this.TM;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.TM, this.TN);
    }

    public boolean jn() {
        return this.Uc;
    }

    List<RawDataPoint> jv() {
        return f(this.Uk);
    }

    List<DataSource> jw() {
        return this.Uk;
    }

    public String toString() {
        List<RawDataPoint> listJv = jv();
        Object[] objArr = new Object[2];
        objArr[0] = this.TN.toDebugString();
        Object obj = listJv;
        if (this.Uj.size() >= 10) {
            obj = String.format("%d data points, first 5: %s", Integer.valueOf(this.Uj.size()), listJv.subList(0, 5));
        }
        objArr[1] = obj;
        return String.format("DataSet{%s %s}", objArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        f.a(this, parcel, flags);
    }
}
