package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.chartboost.sdk.CBLocation;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataReadResult implements Result, SafeParcelable {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new b();
    private final int CK;
    private final Status Eb;
    private final List<DataSet> Ua;
    private final List<DataSource> Uk;
    private final List<Bucket> Wr;
    private int Ws;
    private final List<DataType> Wt;

    DataReadResult(int versionCode, List<RawDataSet> dataSets, Status status, List<RawBucket> buckets, int batchCount, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this.CK = versionCode;
        this.Eb = status;
        this.Ws = batchCount;
        this.Uk = uniqueDataSources;
        this.Wt = uniqueDataTypes;
        this.Ua = new ArrayList(dataSets.size());
        Iterator<RawDataSet> it = dataSets.iterator();
        while (it.hasNext()) {
            this.Ua.add(new DataSet(it.next(), uniqueDataSources, uniqueDataTypes));
        }
        this.Wr = new ArrayList(buckets.size());
        Iterator<RawBucket> it2 = buckets.iterator();
        while (it2.hasNext()) {
            this.Wr.add(new Bucket(it2.next(), uniqueDataSources, uniqueDataTypes));
        }
    }

    public DataReadResult(List<DataSet> dataSets, List<Bucket> buckets, Status status) {
        this.CK = 5;
        this.Ua = dataSets;
        this.Eb = status;
        this.Wr = buckets;
        this.Ws = 1;
        this.Uk = new ArrayList();
        this.Wt = new ArrayList();
    }

    public static DataReadResult a(Status status, DataReadRequest dataReadRequest) {
        ArrayList arrayList = new ArrayList();
        Iterator<DataSource> it = dataReadRequest.getDataSources().iterator();
        while (it.hasNext()) {
            arrayList.add(DataSet.create(it.next()));
        }
        Iterator<DataType> it2 = dataReadRequest.getDataTypes().iterator();
        while (it2.hasNext()) {
            arrayList.add(DataSet.create(new DataSource.Builder().setDataType(it2.next()).setType(1).setName(CBLocation.LOCATION_DEFAULT).build()));
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    private void a(Bucket bucket, List<Bucket> list) {
        for (Bucket bucket2 : list) {
            if (bucket2.b(bucket)) {
                Iterator<DataSet> it = bucket.getDataSets().iterator();
                while (it.hasNext()) {
                    a(it.next(), bucket2.getDataSets());
                }
                return;
            }
        }
        this.Wr.add(bucket);
    }

    private void a(DataSet dataSet, List<DataSet> list) {
        for (DataSet dataSet2 : list) {
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                dataSet2.a(dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    private boolean c(DataReadResult dataReadResult) {
        return this.Eb.equals(dataReadResult.Eb) && jv.equal(this.Ua, dataReadResult.Ua) && jv.equal(this.Wr, dataReadResult.Wr);
    }

    public void b(DataReadResult dataReadResult) {
        Iterator<DataSet> it = dataReadResult.getDataSets().iterator();
        while (it.hasNext()) {
            a(it.next(), this.Ua);
        }
        Iterator<Bucket> it2 = dataReadResult.getBuckets().iterator();
        while (it2.hasNext()) {
            a(it2.next(), this.Wr);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof DataReadResult) && c((DataReadResult) that));
    }

    public List<Bucket> getBuckets() {
        return this.Wr;
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet dataSet : this.Ua) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", dataSource.getStreamIdentifier()));
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.Ua) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", dataType.getName()));
    }

    public List<DataSet> getDataSets() {
        return this.Ua;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.Eb, this.Ua, this.Wr);
    }

    List<DataSource> jw() {
        return this.Uk;
    }

    public int kr() {
        return this.Ws;
    }

    List<RawBucket> ks() {
        ArrayList arrayList = new ArrayList(this.Wr.size());
        Iterator<Bucket> it = this.Wr.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawBucket(it.next(), this.Uk, this.Wt));
        }
        return arrayList;
    }

    List<RawDataSet> kt() {
        ArrayList arrayList = new ArrayList(this.Ua.size());
        Iterator<DataSet> it = this.Ua.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataSet(it.next(), this.Uk, this.Wt));
        }
        return arrayList;
    }

    List<DataType> ku() {
        return this.Wt;
    }

    public String toString() {
        return jv.h(this).a("status", this.Eb).a("dataSets", this.Ua.size() > 5 ? this.Ua.size() + " data sets" : this.Ua).a("buckets", this.Wr.size() > 5 ? this.Wr.size() + " buckets" : this.Wr).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        b.a(this, dest, flags);
    }
}
