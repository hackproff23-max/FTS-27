package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class DetectedActivity implements SafeParcelable {
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    private final int CK;
    int agb;
    int agc;
    public static final Comparator<DetectedActivity> aga = new Comparator<DetectedActivity>() { // from class: com.google.android.gms.location.DetectedActivity.1
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(DetectedActivity detectedActivity, DetectedActivity detectedActivity2) {
            int iCompareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
            return iCompareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : iCompareTo;
        }
    };
    public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();

    public DetectedActivity(int activityType, int confidence) {
        this.CK = 1;
        this.agb = activityType;
        this.agc = confidence;
    }

    public DetectedActivity(int versionCode, int activityType, int confidence) {
        this.CK = versionCode;
        this.agb = activityType;
        this.agc = confidence;
    }

    private int ev(int i) {
        if (i > 9) {
            return 4;
        }
        return i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getConfidence() {
        return this.agc;
    }

    public int getType() {
        return ev(this.agb);
    }

    public int getVersionCode() {
        return this.CK;
    }

    public String toString() {
        return "DetectedActivity [type=" + getType() + ", confidence=" + this.agc + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        DetectedActivityCreator.a(this, out, flags);
    }
}
