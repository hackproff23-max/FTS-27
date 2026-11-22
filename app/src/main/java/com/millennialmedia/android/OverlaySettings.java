package com.millennialmedia.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.millennialmedia.google.gson.Gson;
import com.millennialmedia.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: classes.dex */
class OverlaySettings implements Parcelable {
    public static final Parcelable.Creator<OverlaySettings> CREATOR = new Parcelable.Creator<OverlaySettings>() { // from class: com.millennialmedia.android.OverlaySettings.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverlaySettings createFromParcel(Parcel in) {
            return new OverlaySettings(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OverlaySettings[] newArray(int size) {
            return new OverlaySettings[size];
        }
    };
    static final String PROPERTIES_ACID = "acid";
    static final String PROPERTIES_ALLOW_ORIENTATION_CHANGE = "allowOrientationChange";
    static final String PROPERTIES_CUSTOM_CLOSE = "useCustomClose";
    static final String PROPERTIES_ENABLE_HARDWARE_ACCEL = "enableHardwareAccel";
    static final String PROPERTIES_FORCE_ORIENTATION = "forceOrientation";
    static final String PROPERTIES_HEIGHT = "height";
    static final String PROPERTIES_MODAL = "modal";
    static final String PROPERTIES_ORIENTATION = "orientation";
    static final String PROPERTIES_TRANSITION = "transition";
    static final String PROPERTIES_TRANSITION_DURATION = "transitionDuration";
    static final String PROPERTIES_TRANSPARENT = "transparent";
    static final String PROPERTIES_WIDTH = "width";
    private static final String TAG = "OverlaySettings";
    String adUrl;
    boolean allowOrientationChange;
    String content;
    long creatorAdImplId;
    boolean hasLoadedExpandUrl;
    int height;
    boolean isFromInterstitial;

    @SerializedName(PROPERTIES_TRANSPARENT)
    private boolean isTransparent;
    HttpMMHeaders mmHeaders;
    boolean modal;
    String orientation;
    boolean shouldLaunchToOverlay;
    int shouldResizeOverlay;
    private String transition;

    @SerializedName(PROPERTIES_TRANSITION_DURATION)
    private long transitionTimeInMillis;
    String urlToLoad;
    private boolean useCustomClose;
    int width;

    static final OverlaySettings createFromJson(String json) {
        Gson gson = new Gson();
        OverlaySettings settings = (OverlaySettings) gson.fromJson(json, OverlaySettings.class);
        return settings;
    }

    OverlaySettings(Parcel in) {
        this.transition = "";
        this.orientation = "";
        this.urlToLoad = "";
        this.allowOrientationChange = true;
        this.content = "";
        this.adUrl = "";
        this.hasLoadedExpandUrl = false;
        try {
            boolean[] booleanValues = new boolean[6];
            in.readBooleanArray(booleanValues);
            this.shouldLaunchToOverlay = booleanValues[0];
            this.isTransparent = booleanValues[1];
            this.useCustomClose = booleanValues[2];
            this.modal = booleanValues[3];
            this.isFromInterstitial = booleanValues[4];
            this.allowOrientationChange = booleanValues[5];
            this.shouldResizeOverlay = in.readInt();
            this.transition = in.readString();
            this.transitionTimeInMillis = in.readLong();
            this.transitionTimeInMillis = this.transitionTimeInMillis >= 0 ? this.transitionTimeInMillis : 0L;
            this.orientation = in.readString();
            this.creatorAdImplId = in.readLong();
            this.urlToLoad = in.readString();
            this.height = in.readInt();
            this.width = in.readInt();
            this.content = in.readString();
            this.adUrl = in.readString();
            this.mmHeaders = (HttpMMHeaders) in.readParcelable(HttpMMHeaders.class.getClassLoader());
        } catch (Exception e) {
            MMLog.e(TAG, "Exception Overlaysettings creationg from parcel: ", e);
        }
    }

    OverlaySettings() {
        this.transition = "";
        this.orientation = "";
        this.urlToLoad = "";
        this.allowOrientationChange = true;
        this.content = "";
        this.adUrl = "";
        this.hasLoadedExpandUrl = false;
    }

    void log() {
        MMLog.v(TAG, toString());
    }

    public String toString() {
        return String.format("height %d width %d modal %b urlToLoad %s creatorAdImplId %s shouldResizeOverlay: %d transitionTime: %d overlayTransition: %s shouldMakeOverlayTransparent: %b shouldShowCustomClose: %b Orientation: %s", Integer.valueOf(this.height), Integer.valueOf(this.width), Boolean.valueOf(this.modal), this.urlToLoad, Long.valueOf(this.creatorAdImplId), Integer.valueOf(this.shouldResizeOverlay), Long.valueOf(this.transitionTimeInMillis), this.transition, Boolean.valueOf(this.isTransparent), Boolean.valueOf(this.useCustomClose), this.orientation);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        boolean[] booleanValues = {this.shouldLaunchToOverlay, this.isTransparent, this.useCustomClose, this.modal, this.isFromInterstitial, this.allowOrientationChange};
        dest.writeBooleanArray(booleanValues);
        dest.writeInt(this.shouldResizeOverlay);
        dest.writeString(this.transition);
        dest.writeLong(this.transitionTimeInMillis);
        dest.writeString(this.orientation);
        dest.writeLong(this.creatorAdImplId);
        dest.writeString(this.urlToLoad);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeString(this.content);
        dest.writeString(this.adUrl);
        dest.writeParcelable(this.mmHeaders, flags);
    }

    boolean isExpanded() {
        return (this.isFromInterstitial || this.creatorAdImplId == 0) ? false : true;
    }

    boolean isFromInterstitial() {
        return this.isFromInterstitial && this.creatorAdImplId != 0;
    }

    boolean hasExpandUrl() {
        return (this.urlToLoad == null || this.urlToLoad.equals("")) ? false : true;
    }

    boolean hasLoadedExpandUrl() {
        if (this.hasLoadedExpandUrl) {
            return true;
        }
        this.hasLoadedExpandUrl = true;
        return false;
    }

    void setWebMMHeaders(HttpMMHeaders headers) {
        this.mmHeaders = headers;
    }

    long getTransitionDurationInMillis() {
        if (this.transitionTimeInMillis > 0) {
            return this.transitionTimeInMillis;
        }
        if (this.mmHeaders != null) {
            return this.mmHeaders.transitionTimeInMillis;
        }
        return 0L;
    }

    void setTransitionDurationInMillis(long transTimeMillis) {
        this.transitionTimeInMillis = transTimeMillis;
    }

    String getTransition() {
        if (!TextUtils.isEmpty(this.transition)) {
            return this.transition;
        }
        if (this.mmHeaders != null && !TextUtils.isEmpty(this.mmHeaders.transition)) {
            return this.mmHeaders.transition;
        }
        return "none";
    }

    String getAcid() {
        return (this.mmHeaders == null || TextUtils.isEmpty(this.mmHeaders.acid)) ? "" : this.mmHeaders.acid;
    }

    void setTransition(String trans) {
        this.transition = trans;
    }

    boolean getUseCustomClose() {
        return this.useCustomClose || (this.mmHeaders != null && this.mmHeaders.useCustomClose);
    }

    void setUseCustomClose(boolean isUseCustomClose) {
        this.useCustomClose = isUseCustomClose;
    }

    boolean getIsTransparent() {
        return this.isTransparent || (this.mmHeaders != null && this.mmHeaders.isTransparent);
    }

    void setIsTransparent(boolean transparent) {
        this.isTransparent = transparent;
    }

    boolean enableHardwareAccel() {
        return this.mmHeaders != null && this.mmHeaders.enableHardwareAccel;
    }
}
