package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class LineItem implements SafeParcelable {
    public static final Parcelable.Creator<LineItem> CREATOR = new i();
    private final int CK;
    String auF;
    String auG;
    String auZ;
    String ava;
    int avb;
    String description;

    public final class Builder {
        private Builder() {
        }

        public LineItem build() {
            return LineItem.this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            LineItem.this.auG = currencyCode;
            return this;
        }

        public Builder setDescription(String description) {
            LineItem.this.description = description;
            return this;
        }

        public Builder setQuantity(String quantity) {
            LineItem.this.auZ = quantity;
            return this;
        }

        public Builder setRole(int role) {
            LineItem.this.avb = role;
            return this;
        }

        public Builder setTotalPrice(String totalPrice) {
            LineItem.this.auF = totalPrice;
            return this;
        }

        public Builder setUnitPrice(String unitPrice) {
            LineItem.this.ava = unitPrice;
            return this;
        }
    }

    public interface Role {
        public static final int REGULAR = 0;
        public static final int SHIPPING = 2;
        public static final int TAX = 1;
    }

    LineItem() {
        this.CK = 1;
        this.avb = 0;
    }

    LineItem(int versionCode, String description, String quantity, String unitPrice, String totalPrice, int role, String currencyCode) {
        this.CK = versionCode;
        this.description = description;
        this.auZ = quantity;
        this.ava = unitPrice;
        this.auF = totalPrice;
        this.avb = role;
        this.auG = currencyCode;
    }

    public static Builder newBuilder() {
        LineItem lineItem = new LineItem();
        lineItem.getClass();
        return new Builder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCurrencyCode() {
        return this.auG;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuantity() {
        return this.auZ;
    }

    public int getRole() {
        return this.avb;
    }

    public String getTotalPrice() {
        return this.auF;
    }

    public String getUnitPrice() {
        return this.ava;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        i.a(this, dest, flags);
    }
}
