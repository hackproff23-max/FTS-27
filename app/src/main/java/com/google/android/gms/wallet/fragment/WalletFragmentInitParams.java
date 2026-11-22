package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jx;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

/* JADX INFO: loaded from: classes.dex */
public final class WalletFragmentInitParams implements SafeParcelable {
    public static final Parcelable.Creator<WalletFragmentInitParams> CREATOR = new a();
    final int CK;
    private String DZ;
    private MaskedWalletRequest awg;
    private MaskedWallet awh;
    private int awu;

    public final class Builder {
        private Builder() {
        }

        public WalletFragmentInitParams build() {
            jx.a((WalletFragmentInitParams.this.awh != null && WalletFragmentInitParams.this.awg == null) || (WalletFragmentInitParams.this.awh == null && WalletFragmentInitParams.this.awg != null), "Exactly one of MaskedWallet or MaskedWalletRequest is required");
            jx.a(WalletFragmentInitParams.this.awu >= 0, "masked wallet request code is required and must be non-negative");
            return WalletFragmentInitParams.this;
        }

        public Builder setAccountName(String accountName) {
            WalletFragmentInitParams.this.DZ = accountName;
            return this;
        }

        public Builder setMaskedWallet(MaskedWallet maskedWallet) {
            WalletFragmentInitParams.this.awh = maskedWallet;
            return this;
        }

        public Builder setMaskedWalletRequest(MaskedWalletRequest request) {
            WalletFragmentInitParams.this.awg = request;
            return this;
        }

        public Builder setMaskedWalletRequestCode(int requestCode) {
            WalletFragmentInitParams.this.awu = requestCode;
            return this;
        }
    }

    private WalletFragmentInitParams() {
        this.CK = 1;
        this.awu = -1;
    }

    WalletFragmentInitParams(int versionCode, String accountName, MaskedWalletRequest maskedWalletRequest, int maskedWalletRequestCode, MaskedWallet maskedWallet) {
        this.CK = versionCode;
        this.DZ = accountName;
        this.awg = maskedWalletRequest;
        this.awu = maskedWalletRequestCode;
        this.awh = maskedWallet;
    }

    public static Builder newBuilder() {
        WalletFragmentInitParams walletFragmentInitParams = new WalletFragmentInitParams();
        walletFragmentInitParams.getClass();
        return new Builder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAccountName() {
        return this.DZ;
    }

    public MaskedWallet getMaskedWallet() {
        return this.awh;
    }

    public MaskedWalletRequest getMaskedWalletRequest() {
        return this.awg;
    }

    public int getMaskedWalletRequestCode() {
        return this.awu;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        a.a(this, dest, flags);
    }
}
