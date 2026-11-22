package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class MaskedWalletRequest implements SafeParcelable {
    public static final Parcelable.Creator<MaskedWalletRequest> CREATOR = new l();
    private final int CK;
    String auG;
    String auM;
    Cart auV;
    boolean avA;
    boolean avB;
    String avC;
    String avD;
    boolean avE;
    boolean avF;
    CountrySpecification[] avG;
    boolean avH;
    boolean avI;
    ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> avJ;
    boolean avz;

    public final class Builder {
        private Builder() {
        }

        public Builder addAllowedCountrySpecificationForShipping(com.google.android.gms.identity.intents.model.CountrySpecification countrySpecification) {
            if (MaskedWalletRequest.this.avJ == null) {
                MaskedWalletRequest.this.avJ = new ArrayList<>();
            }
            MaskedWalletRequest.this.avJ.add(countrySpecification);
            return this;
        }

        public Builder addAllowedCountrySpecificationsForShipping(Collection<com.google.android.gms.identity.intents.model.CountrySpecification> countrySpecifications) {
            if (countrySpecifications != null) {
                if (MaskedWalletRequest.this.avJ == null) {
                    MaskedWalletRequest.this.avJ = new ArrayList<>();
                }
                MaskedWalletRequest.this.avJ.addAll(countrySpecifications);
            }
            return this;
        }

        public MaskedWalletRequest build() {
            return MaskedWalletRequest.this;
        }

        public Builder setAllowDebitCard(boolean allowDebitCard) {
            MaskedWalletRequest.this.avI = allowDebitCard;
            return this;
        }

        public Builder setAllowPrepaidCard(boolean allowPrepaidCard) {
            MaskedWalletRequest.this.avH = allowPrepaidCard;
            return this;
        }

        public Builder setCart(Cart cart) {
            MaskedWalletRequest.this.auV = cart;
            return this;
        }

        public Builder setCurrencyCode(String currencyCode) {
            MaskedWalletRequest.this.auG = currencyCode;
            return this;
        }

        public Builder setEstimatedTotalPrice(String estimatedTotalPrice) {
            MaskedWalletRequest.this.avC = estimatedTotalPrice;
            return this;
        }

        public Builder setIsBillingAgreement(boolean isBillingAgreement) {
            MaskedWalletRequest.this.avF = isBillingAgreement;
            return this;
        }

        public Builder setMerchantName(String merchantName) {
            MaskedWalletRequest.this.avD = merchantName;
            return this;
        }

        public Builder setMerchantTransactionId(String merchantTransactionId) {
            MaskedWalletRequest.this.auM = merchantTransactionId;
            return this;
        }

        public Builder setPhoneNumberRequired(boolean phoneNumberRequired) {
            MaskedWalletRequest.this.avz = phoneNumberRequired;
            return this;
        }

        public Builder setShippingAddressRequired(boolean shippingAddressRequired) {
            MaskedWalletRequest.this.avA = shippingAddressRequired;
            return this;
        }

        public Builder setShouldRetrieveWalletObjects(boolean shouldRetrieveWalletObjects) {
            MaskedWalletRequest.this.avE = shouldRetrieveWalletObjects;
            return this;
        }

        public Builder setUseMinimalBillingAddress(boolean useMinimalBillingAddress) {
            MaskedWalletRequest.this.avB = useMinimalBillingAddress;
            return this;
        }
    }

    MaskedWalletRequest() {
        this.CK = 3;
        this.avH = true;
        this.avI = true;
    }

    MaskedWalletRequest(int versionCode, String merchantTransactionId, boolean phoneNumberRequired, boolean shippingAddressRequired, boolean useMinimalBillingAddress, String estimatedTotalPrice, String currencyCode, String merchantName, Cart cart, boolean shouldRetrieveWalletObjects, boolean isBillingAgreement, CountrySpecification[] allowedShippingCountrySpecifications, boolean allowPrepaidCard, boolean allowDebitCard, ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> allowedCountrySpecificationsForShipping) {
        this.CK = versionCode;
        this.auM = merchantTransactionId;
        this.avz = phoneNumberRequired;
        this.avA = shippingAddressRequired;
        this.avB = useMinimalBillingAddress;
        this.avC = estimatedTotalPrice;
        this.auG = currencyCode;
        this.avD = merchantName;
        this.auV = cart;
        this.avE = shouldRetrieveWalletObjects;
        this.avF = isBillingAgreement;
        this.avG = allowedShippingCountrySpecifications;
        this.avH = allowPrepaidCard;
        this.avI = allowDebitCard;
        this.avJ = allowedCountrySpecificationsForShipping;
    }

    public static Builder newBuilder() {
        MaskedWalletRequest maskedWalletRequest = new MaskedWalletRequest();
        maskedWalletRequest.getClass();
        return new Builder();
    }

    public boolean allowDebitCard() {
        return this.avI;
    }

    public boolean allowPrepaidCard() {
        return this.avH;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<com.google.android.gms.identity.intents.model.CountrySpecification> getAllowedCountrySpecificationsForShipping() {
        return this.avJ;
    }

    public CountrySpecification[] getAllowedShippingCountrySpecifications() {
        return this.avG;
    }

    public Cart getCart() {
        return this.auV;
    }

    public String getCurrencyCode() {
        return this.auG;
    }

    public String getEstimatedTotalPrice() {
        return this.avC;
    }

    public String getMerchantName() {
        return this.avD;
    }

    public String getMerchantTransactionId() {
        return this.auM;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public boolean isBillingAgreement() {
        return this.avF;
    }

    public boolean isPhoneNumberRequired() {
        return this.avz;
    }

    public boolean isShippingAddressRequired() {
        return this.avA;
    }

    public boolean shouldRetrieveWalletObjects() {
        return this.avE;
    }

    public boolean useMinimalBillingAddress() {
        return this.avB;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        l.a(this, dest, flags);
    }
}
