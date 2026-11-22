package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.internal.jv;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class Bucket implements SafeParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new d();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final int CK;
    private final long LW;
    private final long TO;
    private final Session TQ;
    private final int TZ;
    private final List<DataSet> Ua;
    private final int Ub;
    private boolean Uc;

    Bucket(int versionCode, long startTimeMillis, long endTimeMillis, Session session, int activityType, List<DataSet> dataSets, int bucketType, boolean serverHasMoreData) {
        this.Uc = false;
        this.CK = versionCode;
        this.LW = startTimeMillis;
        this.TO = endTimeMillis;
        this.TQ = session;
        this.TZ = activityType;
        this.Ua = dataSets;
        this.Ub = bucketType;
        this.Uc = serverHasMoreData;
    }

    public Bucket(RawBucket bucket, List<DataSource> uniqueDataSources, List<DataType> uniqueDataTypes) {
        this(2, bucket.LW, bucket.TO, bucket.TQ, bucket.UI, a(bucket.Ua, uniqueDataSources, uniqueDataTypes), bucket.Ub, bucket.Uc);
    }

    private static List<DataSet> a(List<RawDataSet> list, List<DataSource> list2, List<DataType> list3) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<RawDataSet> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new DataSet(it.next(), list2, list3));
        }
        return arrayList;
    }

    private boolean a(Bucket bucket) {
        return this.LW == bucket.LW && this.TO == bucket.TO && this.TZ == bucket.TZ && jv.equal(this.Ua, bucket.Ua) && this.Ub == bucket.Ub && this.Uc == bucket.Uc;
    }

    public static String cJ(int i) {
        switch (i) {
            case 0:
                return "unknown";
            case 1:
                return "time";
            case 2:
                return "session";
            case 3:
                return UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY;
            case 4:
                return "segment";
            default:
                return "bug";
        }
    }

    public boolean b(Bucket bucket) {
        return this.LW == bucket.LW && this.TO == bucket.TO && this.TZ == bucket.TZ && this.Ub == bucket.Ub;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof Bucket) && a((Bucket) o));
    }

    public String getActivity() {
        return FitnessActivities.getName(this.TZ);
    }

    public int getBucketType() {
        return this.Ub;
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.Ua) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    public List<DataSet> getDataSets() {
        return this.Ua;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.TO, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.TQ;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.LW, TimeUnit.MILLISECONDS);
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(Long.valueOf(this.LW), Long.valueOf(this.TO), Integer.valueOf(this.TZ), Integer.valueOf(this.Ub));
    }

    public int jm() {
        return this.TZ;
    }

    public boolean jn() {
        if (this.Uc) {
            return true;
        }
        Iterator<DataSet> it = this.Ua.iterator();
        while (it.hasNext()) {
            if (it.next().jn()) {
                return true;
            }
        }
        return false;
    }

    public long jo() {
        return this.LW;
    }

    public long jp() {
        return this.TO;
    }

    public String toString() {
        return jv.h(this).a("startTime", Long.valueOf(this.LW)).a("endTime", Long.valueOf(this.TO)).a("activity", Integer.valueOf(this.TZ)).a("dataSets", this.Ua).a("bucketType", cJ(this.Ub)).a("serverHasMoreData", Boolean.valueOf(this.Uc)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        d.a(this, dest, flags);
    }
}
