package com.google.android.gms.wallet.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: loaded from: classes.dex */
public final class WalletFragmentStyle implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentStyle> CREATOR = new c();
    final int CK;
    Bundle awy;
    int awz;

    public WalletFragmentStyle() {
        this.CK = 1;
        this.awy = new Bundle();
    }

    WalletFragmentStyle(int versionCode, Bundle attributes, int styleResourceId) {
        this.CK = versionCode;
        this.awy = attributes;
        this.awz = styleResourceId;
    }

    private void a(TypedArray typedArray, int i, String str) {
        TypedValue typedValuePeekValue;
        if (this.awy.containsKey(str) || (typedValuePeekValue = typedArray.peekValue(i)) == null) {
            return;
        }
        this.awy.putLong(str, Dimension.a(typedValuePeekValue));
    }

    private void a(TypedArray typedArray, int i, String str, String str2) {
        TypedValue typedValuePeekValue;
        if (this.awy.containsKey(str) || this.awy.containsKey(str2) || (typedValuePeekValue = typedArray.peekValue(i)) == null) {
            return;
        }
        if (typedValuePeekValue.type < 28 || typedValuePeekValue.type > 31) {
            this.awy.putInt(str2, typedValuePeekValue.resourceId);
        } else {
            this.awy.putInt(str, typedValuePeekValue.data);
        }
    }

    private void b(TypedArray typedArray, int i, String str) {
        TypedValue typedValuePeekValue;
        if (this.awy.containsKey(str) || (typedValuePeekValue = typedArray.peekValue(i)) == null) {
            return;
        }
        this.awy.putInt(str, typedValuePeekValue.data);
    }

    public int a(String str, DisplayMetrics displayMetrics, int i) {
        return this.awy.containsKey(str) ? Dimension.a(this.awy.getLong(str), displayMetrics) : i;
    }

    public void ab(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(this.awz <= 0 ? R.style.WalletFragmentDefaultStyle : this.awz, R.styleable.WalletFragmentStyle);
        a(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonWidth, "buyButtonWidth");
        a(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonHeight, "buyButtonHeight");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonText, "buyButtonText");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_buyButtonAppearance, "buyButtonAppearance");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance, "maskedWalletDetailsTextAppearance");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance, "maskedWalletDetailsHeaderTextAppearance");
        a(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground, "maskedWalletDetailsBackgroundColor", "maskedWalletDetailsBackgroundResource");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance, "maskedWalletDetailsButtonTextAppearance");
        a(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground, "maskedWalletDetailsButtonBackgroundColor", "maskedWalletDetailsButtonBackgroundResource");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor, "maskedWalletDetailsLogoTextColor");
        b(typedArrayObtainStyledAttributes, R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType, "maskedWalletDetailsLogoImageType");
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WalletFragmentStyle setBuyButtonAppearance(int buyButtonAppearance) {
        this.awy.putInt("buyButtonAppearance", buyButtonAppearance);
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int height) {
        this.awy.putLong("buyButtonHeight", Dimension.gg(height));
        return this;
    }

    public WalletFragmentStyle setBuyButtonHeight(int unit, float height) {
        this.awy.putLong("buyButtonHeight", Dimension.a(unit, height));
        return this;
    }

    public WalletFragmentStyle setBuyButtonText(int buyButtonText) {
        this.awy.putInt("buyButtonText", buyButtonText);
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int width) {
        this.awy.putLong("buyButtonWidth", Dimension.gg(width));
        return this;
    }

    public WalletFragmentStyle setBuyButtonWidth(int unit, float width) {
        this.awy.putLong("buyButtonWidth", Dimension.a(unit, width));
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundColor(int color) {
        this.awy.remove("maskedWalletDetailsBackgroundResource");
        this.awy.putInt("maskedWalletDetailsBackgroundColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsBackgroundResource(int resourceId) {
        this.awy.remove("maskedWalletDetailsBackgroundColor");
        this.awy.putInt("maskedWalletDetailsBackgroundResource", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundColor(int color) {
        this.awy.remove("maskedWalletDetailsButtonBackgroundResource");
        this.awy.putInt("maskedWalletDetailsButtonBackgroundColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonBackgroundResource(int resourceId) {
        this.awy.remove("maskedWalletDetailsButtonBackgroundColor");
        this.awy.putInt("maskedWalletDetailsButtonBackgroundResource", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsButtonTextAppearance(int resourceId) {
        this.awy.putInt("maskedWalletDetailsButtonTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsHeaderTextAppearance(int resourceId) {
        this.awy.putInt("maskedWalletDetailsHeaderTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoImageType(int imageType) {
        this.awy.putInt("maskedWalletDetailsLogoImageType", imageType);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsLogoTextColor(int color) {
        this.awy.putInt("maskedWalletDetailsLogoTextColor", color);
        return this;
    }

    public WalletFragmentStyle setMaskedWalletDetailsTextAppearance(int resourceId) {
        this.awy.putInt("maskedWalletDetailsTextAppearance", resourceId);
        return this;
    }

    public WalletFragmentStyle setStyleResourceId(int id) {
        this.awz = id;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        c.a(this, dest, flags);
    }
}
