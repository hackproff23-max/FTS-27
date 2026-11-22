package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListSubscriptionsResult implements Result, SafeParcelable {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new e();
    private final int CK;
    private final Status Eb;
    private final List<Subscription> Wu;

    ListSubscriptionsResult(int versionCode, List<Subscription> subscriptions, Status status) {
        this.CK = versionCode;
        this.Wu = subscriptions;
        this.Eb = status;
    }

    public ListSubscriptionsResult(List<Subscription> subscriptions, Status status) {
        this.CK = 3;
        this.Wu = Collections.unmodifiableList(subscriptions);
        this.Eb = (Status) jx.b(status, "status");
    }

    public static ListSubscriptionsResult F(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    private boolean b(ListSubscriptionsResult listSubscriptionsResult) {
        return this.Eb.equals(listSubscriptionsResult.Eb) && jv.equal(this.Wu, listSubscriptionsResult.Wu);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        return this == that || ((that instanceof ListSubscriptionsResult) && b((ListSubscriptionsResult) that));
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    public List<Subscription> getSubscriptions() {
        return this.Wu;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.Wu) {
            if (dataType.equals(subscription.jJ())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.Eb, this.Wu);
    }

    public String toString() {
        return jv.h(this).a("status", this.Eb).a("subscriptions", this.Wu).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        e.a(this, dest, flags);
    }
}
