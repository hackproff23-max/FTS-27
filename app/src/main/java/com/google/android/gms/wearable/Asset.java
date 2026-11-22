package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;

/* JADX INFO: loaded from: classes.dex */
public class Asset implements SafeParcelable {
    public static final Parcelable.Creator<Asset> CREATOR = new a();
    final int CK;
    private byte[] aeA;
    private String axa;
    public ParcelFileDescriptor axb;
    public Uri uri;

    Asset(int versionCode, byte[] data, String digest, ParcelFileDescriptor fd, Uri uri) {
        this.CK = versionCode;
        this.aeA = data;
        this.axa = digest;
        this.axb = fd;
        this.uri = uri;
    }

    public static Asset createFromBytes(byte[] assetData) {
        if (assetData == null) {
            throw new IllegalArgumentException("Asset data cannot be null");
        }
        return new Asset(1, assetData, null, null, null);
    }

    public static Asset createFromFd(ParcelFileDescriptor fd) {
        if (fd == null) {
            throw new IllegalArgumentException("Asset fd cannot be null");
        }
        return new Asset(1, null, null, fd, null);
    }

    public static Asset createFromRef(String digest) {
        if (digest == null) {
            throw new IllegalArgumentException("Asset digest cannot be null");
        }
        return new Asset(1, null, digest, null, null);
    }

    public static Asset createFromUri(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Asset uri cannot be null");
        }
        return new Asset(1, null, null, null, uri);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Asset)) {
            return false;
        }
        Asset asset = (Asset) o;
        return jv.equal(this.aeA, asset.aeA) && jv.equal(this.axa, asset.axa) && jv.equal(this.axb, asset.axb) && jv.equal(this.uri, asset.uri);
    }

    public byte[] getData() {
        return this.aeA;
    }

    public String getDigest() {
        return this.axa;
    }

    public ParcelFileDescriptor getFd() {
        return this.axb;
    }

    public Uri getUri() {
        return this.uri;
    }

    public int hashCode() {
        return jv.hashCode(this.aeA, this.axa, this.axb, this.uri);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asset[@");
        sb.append(Integer.toHexString(hashCode()));
        if (this.axa == null) {
            sb.append(", nodigest");
        } else {
            sb.append(", ");
            sb.append(this.axa);
        }
        if (this.aeA != null) {
            sb.append(", size=");
            sb.append(this.aeA.length);
        }
        if (this.axb != null) {
            sb.append(", fd=");
            sb.append(this.axb);
        }
        if (this.uri != null) {
            sb.append(", uri=");
            sb.append(this.uri);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags | 1);
    }
}
