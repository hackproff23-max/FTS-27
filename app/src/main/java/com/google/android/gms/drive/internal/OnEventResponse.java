package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;

/* JADX INFO: loaded from: classes.dex */
public class OnEventResponse implements SafeParcelable {
    public static final Parcelable.Creator<OnEventResponse> CREATOR = new aq();
    final int CK;
    final int Pm;
    final ChangeEvent QO;
    final CompletionEvent QP;

    OnEventResponse(int versionCode, int eventType, ChangeEvent changeEvent, CompletionEvent completionEvent) {
        this.CK = versionCode;
        this.Pm = eventType;
        this.QO = changeEvent;
        this.QP = completionEvent;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DriveEvent iQ() {
        switch (this.Pm) {
            case 1:
                return this.QO;
            case 2:
                return this.QP;
            default:
                throw new IllegalStateException("Unexpected event type " + this.Pm);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        aq.a(this, dest, flags);
    }
}
