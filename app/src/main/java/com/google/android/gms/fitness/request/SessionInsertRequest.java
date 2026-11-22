package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.lt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class SessionInsertRequest implements SafeParcelable {
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new s();
    private final int CK;
    private final Session TQ;
    private final List<DataSet> Ua;
    private final List<DataPoint> Wh;

    public static class Builder {
        private Session TQ;
        private List<DataSet> Ua = new ArrayList();
        private List<DataPoint> Wh = new ArrayList();
        private List<DataSource> Wi = new ArrayList();

        private void d(DataPoint dataPoint) {
            f(dataPoint);
            e(dataPoint);
        }

        private void e(DataPoint dataPoint) {
            long startTime = this.TQ.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.TQ.getEndTime(TimeUnit.NANOSECONDS);
            long startTime2 = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = dataPoint.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime2 == 0 || endTime2 == 0) {
                return;
            }
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            if (endTime2 > endTime) {
                endTime2 = lt.a(endTime2, TimeUnit.NANOSECONDS, timeUnit);
            }
            jx.a(startTime2 >= startTime && endTime2 <= endTime, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
            if (endTime2 != dataPoint.getEndTime(TimeUnit.NANOSECONDS)) {
                Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(endTime2), timeUnit));
                dataPoint.setTimeInterval(startTime2, endTime2, TimeUnit.NANOSECONDS);
            }
        }

        private void f(DataPoint dataPoint) {
            long startTime = this.TQ.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.TQ.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = lt.a(timestamp, TimeUnit.NANOSECONDS, timeUnit);
                }
                jx.a(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), timeUnit));
                    dataPoint.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
        }

        private void ki() {
            Iterator<DataSet> it = this.Ua.iterator();
            while (it.hasNext()) {
                Iterator<DataPoint> it2 = it.next().getDataPoints().iterator();
                while (it2.hasNext()) {
                    d(it2.next());
                }
            }
            Iterator<DataPoint> it3 = this.Wh.iterator();
            while (it3.hasNext()) {
                d(it3.next());
            }
        }

        public Builder addAggregateDataPoint(DataPoint aggregateDataPoint) {
            jx.b(aggregateDataPoint != null, "Must specify a valid aggregate data point.");
            long startTime = aggregateDataPoint.getStartTime(TimeUnit.NANOSECONDS);
            jx.b(startTime > 0 && aggregateDataPoint.getEndTime(TimeUnit.NANOSECONDS) > startTime, "Aggregate data point should have valid start and end times: %s", aggregateDataPoint);
            DataSource dataSource = aggregateDataPoint.getDataSource();
            jx.a(!this.Wi.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            this.Wi.add(dataSource);
            this.Wh.add(aggregateDataPoint);
            return this;
        }

        public Builder addDataSet(DataSet dataSet) {
            jx.b(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            jx.a(!this.Wi.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            jx.b(dataSet.getDataPoints().isEmpty() ? false : true, "No data points specified in the input data set.");
            this.Wi.add(dataSource);
            this.Ua.add(dataSet);
            return this;
        }

        public SessionInsertRequest build() {
            jx.a(this.TQ != null, "Must specify a valid session.");
            jx.a(this.TQ.getEndTime(TimeUnit.MILLISECONDS) != 0, "Must specify a valid end time, cannot insert a continuing session.");
            ki();
            return new SessionInsertRequest(this);
        }

        public Builder setSession(Session session) {
            this.TQ = session;
            return this;
        }
    }

    SessionInsertRequest(int versionCode, Session session, List<DataSet> dataSets, List<DataPoint> aggregateDataPoints) {
        this.CK = versionCode;
        this.TQ = session;
        this.Ua = Collections.unmodifiableList(dataSets);
        this.Wh = Collections.unmodifiableList(aggregateDataPoints);
    }

    private SessionInsertRequest(Builder builder) {
        this.CK = 1;
        this.TQ = builder.TQ;
        this.Ua = Collections.unmodifiableList(builder.Ua);
        this.Wh = Collections.unmodifiableList(builder.Wh);
    }

    private boolean a(SessionInsertRequest sessionInsertRequest) {
        return jv.equal(this.TQ, sessionInsertRequest.TQ) && jv.equal(this.Ua, sessionInsertRequest.Ua) && jv.equal(this.Wh, sessionInsertRequest.Wh);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        return o == this || ((o instanceof SessionInsertRequest) && a((SessionInsertRequest) o));
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.Wh;
    }

    public List<DataSet> getDataSets() {
        return this.Ua;
    }

    public Session getSession() {
        return this.TQ;
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.TQ, this.Ua, this.Wh);
    }

    public String toString() {
        return jv.h(this).a("session", this.TQ).a("dataSets", this.Ua).a("aggregateDataPoints", this.Wh).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        s.a(this, dest, flags);
    }
}
