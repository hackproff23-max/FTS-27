package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CustomPropertyKey implements SafeParcelable {
    public static final int PRIVATE = 1;
    public static final int PUBLIC = 0;
    final int CK;
    final String KP;
    final int mVisibility;
    public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new c();
    private static final Pattern Rc = Pattern.compile("[\\w.!@$%^&*()/-]+");

    CustomPropertyKey(int versionCode, String key, int visibility) {
        boolean z = true;
        jx.b(key, (Object) UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
        jx.b(Rc.matcher(key).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
        if (visibility != 0 && visibility != 1) {
            z = false;
        }
        jx.b(z, "visibility must be either PUBLIC or PRIVATE");
        this.CK = versionCode;
        this.KP = key;
        this.mVisibility = visibility;
    }

    public CustomPropertyKey(String key, int visibility) {
        this(1, key, visibility);
    }

    public static CustomPropertyKey fromJson(JSONObject jsonObject) throws JSONException {
        return new CustomPropertyKey(jsonObject.getString(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY), jsonObject.getInt("visibility"));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomPropertyKey)) {
            return false;
        }
        CustomPropertyKey customPropertyKey = (CustomPropertyKey) obj;
        return customPropertyKey.getKey().equals(this.KP) && customPropertyKey.getVisibility() == this.mVisibility;
    }

    public String getKey() {
        return this.KP;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public int hashCode() {
        return (this.KP + this.mVisibility).hashCode();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, getKey());
        jSONObject.put("visibility", getVisibility());
        return jSONObject;
    }

    public String toString() {
        return "CustomPropertyKey(" + this.KP + "," + this.mVisibility + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
