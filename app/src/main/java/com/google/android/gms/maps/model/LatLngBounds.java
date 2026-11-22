package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import com.google.android.gms.maps.internal.aa;

/* JADX INFO: loaded from: classes.dex */
public final class LatLngBounds implements SafeParcelable {
    public static final g CREATOR = new g();
    private final int CK;
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double amk = Double.POSITIVE_INFINITY;
        private double aml = Double.NEGATIVE_INFINITY;
        private double amm = Double.NaN;
        private double amn = Double.NaN;

        private boolean d(double d) {
            if (this.amm <= this.amn) {
                return this.amm <= d && d <= this.amn;
            }
            return this.amm <= d || d <= this.amn;
        }

        public LatLngBounds build() {
            jx.a(!Double.isNaN(this.amm), "no included points");
            return new LatLngBounds(new LatLng(this.amk, this.amm), new LatLng(this.aml, this.amn));
        }

        public Builder include(LatLng point) {
            this.amk = Math.min(this.amk, point.latitude);
            this.aml = Math.max(this.aml, point.latitude);
            double d = point.longitude;
            if (Double.isNaN(this.amm)) {
                this.amm = d;
                this.amn = d;
            } else if (!d(d)) {
                if (LatLngBounds.b(this.amm, d) < LatLngBounds.c(this.amn, d)) {
                    this.amm = d;
                } else {
                    this.amn = d;
                }
            }
            return this;
        }
    }

    LatLngBounds(int versionCode, LatLng southwest, LatLng northeast) {
        jx.b(southwest, "null southwest");
        jx.b(northeast, "null northeast");
        jx.b(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(southwest.latitude), Double.valueOf(northeast.latitude));
        this.CK = versionCode;
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public LatLngBounds(LatLng southwest, LatLng northeast) {
        this(1, southwest, northeast);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double b(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double c(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private boolean c(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean d(double d) {
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        }
        return this.southwest.longitude <= d || d <= this.northeast.longitude;
    }

    public boolean contains(LatLng point) {
        return c(point.latitude) && d(point.longitude);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) o;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public LatLng getCenter() {
        double d = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        return new LatLng(d, d3 <= d2 ? (d2 + d3) / 2.0d : ((d2 + 360.0d) + d3) / 2.0d);
    }

    int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.southwest, this.northeast);
    }

    public LatLngBounds including(LatLng point) {
        double d;
        double dMin = Math.min(this.southwest.latitude, point.latitude);
        double dMax = Math.max(this.northeast.latitude, point.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = point.longitude;
        if (d(d4)) {
            d4 = d3;
            d = d2;
        } else if (b(d3, d4) < c(d2, d4)) {
            d = d2;
        } else {
            d = d4;
            d4 = d3;
        }
        return new LatLngBounds(new LatLng(dMin, d4), new LatLng(dMax, d));
    }

    public String toString() {
        return jv.h(this).a("southwest", this.southwest).a("northeast", this.northeast).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        if (aa.ob()) {
            h.a(this, out, flags);
        } else {
            g.a(this, out, flags);
        }
    }
}
