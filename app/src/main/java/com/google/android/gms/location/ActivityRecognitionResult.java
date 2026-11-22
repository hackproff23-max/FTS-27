package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ActivityRecognitionResult implements SafeParcelable {
    public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
    public static final String EXTRA_ACTIVITY_RESULT = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT";
    private final int CK;
    List<DetectedActivity> afX;
    long afY;
    long afZ;

    public ActivityRecognitionResult(int versionCode, List<DetectedActivity> probableActivities, long timeMillis, long elapsedRealtimeMillis) {
        this.CK = 1;
        this.afX = probableActivities;
        this.afY = timeMillis;
        this.afZ = elapsedRealtimeMillis;
    }

    public ActivityRecognitionResult(DetectedActivity mostProbableActivity, long time, long elapsedRealtimeMillis) {
        this((List<DetectedActivity>) Collections.singletonList(mostProbableActivity), time, elapsedRealtimeMillis);
    }

    public ActivityRecognitionResult(List<DetectedActivity> probableActivities, long time, long elapsedRealtimeMillis) {
        boolean z = false;
        jx.b(probableActivities != null && probableActivities.size() > 0, "Must have at least 1 detected activity");
        if (time > 0 && elapsedRealtimeMillis > 0) {
            z = true;
        }
        jx.b(z, "Must set times");
        this.CK = 1;
        this.afX = probableActivities;
        this.afY = time;
        this.afZ = elapsedRealtimeMillis;
    }

    public static ActivityRecognitionResult extractResult(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        Object obj = intent.getExtras().get(EXTRA_ACTIVITY_RESULT);
        if (!(obj instanceof byte[])) {
            if (obj instanceof ActivityRecognitionResult) {
                return (ActivityRecognitionResult) obj;
            }
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall((byte[]) obj, 0, ((byte[]) obj).length);
        parcelObtain.setDataPosition(0);
        return CREATOR.createFromParcel(parcelObtain);
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra(EXTRA_ACTIVITY_RESULT);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getActivityConfidence(int activityType) {
        for (DetectedActivity detectedActivity : this.afX) {
            if (detectedActivity.getType() == activityType) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.afZ;
    }

    public DetectedActivity getMostProbableActivity() {
        return this.afX.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.afX;
    }

    public long getTime() {
        return this.afY;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.afX + ", timeMillis=" + this.afY + ", elapsedRealtimeMillis=" + this.afZ + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        ActivityRecognitionResultCreator.a(this, out, flags);
    }
}
