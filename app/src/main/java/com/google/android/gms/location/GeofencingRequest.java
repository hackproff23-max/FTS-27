package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.nn;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GeofencingRequest implements SafeParcelable {
    public static final Parcelable.Creator<GeofencingRequest> CREATOR = new a();
    public static final int INITIAL_TRIGGER_DWELL = 4;
    public static final int INITIAL_TRIGGER_ENTER = 1;
    public static final int INITIAL_TRIGGER_EXIT = 2;
    private final int CK;
    private final List<nn> ago;
    private final int agp;

    public static final class Builder {
        private final List<nn> ago = new ArrayList();
        private int agp = 5;

        public static int ew(int i) {
            return i & 7;
        }

        public Builder addGeofence(Geofence geofence) {
            jx.b(geofence, "geofence can't be null.");
            jx.b(geofence instanceof nn, "Geofence must be created using Geofence.Builder.");
            this.ago.add((nn) geofence);
            return this;
        }

        public Builder addGeofences(List<Geofence> geofences) {
            if (geofences != null && !geofences.isEmpty()) {
                for (Geofence geofence : geofences) {
                    if (geofence != null) {
                        addGeofence(geofence);
                    }
                }
            }
            return this;
        }

        public GeofencingRequest build() {
            jx.b(!this.ago.isEmpty(), "No geofence has been added to this request.");
            return new GeofencingRequest(this.ago, this.agp);
        }

        public Builder setInitialTrigger(int initialTrigger) {
            this.agp = ew(initialTrigger);
            return this;
        }
    }

    GeofencingRequest(int version, List<nn> geofences, int initialTrigger) {
        this.CK = version;
        this.ago = geofences;
        this.agp = initialTrigger;
    }

    private GeofencingRequest(List<nn> geofences, int initialTrigger) {
        this(1, geofences, initialTrigger);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<Geofence> getGeofences() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.ago);
        return arrayList;
    }

    public int getInitialTrigger() {
        return this.agp;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public List<nn> ng() {
        return this.ago;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
