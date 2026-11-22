package com.google.android.gms.fitness;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.internal.jx;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public interface HistoryApi {

    public static class ViewIntentBuilder {
        private long LW;
        private final DataType TM;
        private DataSource TN;
        private long TO;
        private String TP;
        private final Context mContext;

        public ViewIntentBuilder(Context context, DataType dataType) {
            this.mContext = context;
            this.TM = dataType;
        }

        private Intent i(Intent intent) {
            Intent intent2;
            ResolveInfo resolveInfoResolveActivity;
            if (this.TP == null || (resolveInfoResolveActivity = this.mContext.getPackageManager().resolveActivity((intent2 = new Intent(intent).setPackage(this.TP)), 0)) == null) {
                return intent;
            }
            intent2.setComponent(new ComponentName(this.TP, resolveInfoResolveActivity.activityInfo.name));
            return intent2;
        }

        public Intent build() {
            jx.a(this.LW > 0, "Start time must be set");
            jx.a(this.TO > this.LW, "End time must be set and after start time");
            Intent intent = new Intent(Fitness.ACTION_VIEW);
            intent.setType(DataType.getMimeType(this.TN.getDataType()));
            intent.putExtra(Fitness.EXTRA_START_TIME, this.LW);
            intent.putExtra(Fitness.EXTRA_END_TIME, this.TO);
            c.a(this.TN, intent, DataSource.EXTRA_DATA_SOURCE);
            return i(intent);
        }

        public ViewIntentBuilder setDataSource(DataSource dataSource) {
            jx.b(dataSource.getDataType().equals(this.TM), "Data source %s is not for the data type %s", dataSource, this.TM);
            this.TN = dataSource;
            return this;
        }

        public ViewIntentBuilder setPreferredApplication(String packageName) {
            this.TP = packageName;
            return this;
        }

        public ViewIntentBuilder setTimeInterval(long start, long end, TimeUnit timeUnit) {
            this.LW = timeUnit.toMillis(start);
            this.TO = timeUnit.toMillis(end);
            return this;
        }
    }

    PendingResult<Status> deleteData(GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest);

    PendingResult<Status> insertData(GoogleApiClient googleApiClient, DataSet dataSet);

    PendingResult<DataReadResult> readData(GoogleApiClient googleApiClient, DataReadRequest dataReadRequest);
}
