package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;
import com.google.android.gms.maps.model.CameraPosition;

/* JADX INFO: loaded from: classes.dex */
public final class GoogleMapOptions implements SafeParcelable {
    public static final a CREATOR = new a();
    private final int CK;
    private Boolean akF;
    private Boolean akG;
    private int akH;
    private CameraPosition akI;
    private Boolean akJ;
    private Boolean akK;
    private Boolean akL;
    private Boolean akM;
    private Boolean akN;
    private Boolean akO;
    private Boolean akP;
    private Boolean akQ;

    public GoogleMapOptions() {
        this.akH = -1;
        this.CK = 1;
    }

    GoogleMapOptions(int versionCode, byte zOrderOnTop, byte useViewLifecycleInFragment, int mapType, CameraPosition camera, byte zoomControlsEnabled, byte compassEnabled, byte scrollGesturesEnabled, byte zoomGesturesEnabled, byte tiltGesturesEnabled, byte rotateGesturesEnabled, byte liteMode, byte mapToolbarEnabled) {
        this.akH = -1;
        this.CK = versionCode;
        this.akF = com.google.android.gms.maps.internal.a.a(zOrderOnTop);
        this.akG = com.google.android.gms.maps.internal.a.a(useViewLifecycleInFragment);
        this.akH = mapType;
        this.akI = camera;
        this.akJ = com.google.android.gms.maps.internal.a.a(zoomControlsEnabled);
        this.akK = com.google.android.gms.maps.internal.a.a(compassEnabled);
        this.akL = com.google.android.gms.maps.internal.a.a(scrollGesturesEnabled);
        this.akM = com.google.android.gms.maps.internal.a.a(zoomGesturesEnabled);
        this.akN = com.google.android.gms.maps.internal.a.a(tiltGesturesEnabled);
        this.akO = com.google.android.gms.maps.internal.a.a(rotateGesturesEnabled);
        this.akP = com.google.android.gms.maps.internal.a.a(liteMode);
        this.akQ = com.google.android.gms.maps.internal.a.a(mapToolbarEnabled);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return null;
        }
        TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attrs, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(typedArrayObtainAttributes.getInt(R.styleable.MapAttrs_mapType, -1));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiCompass, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_liteMode, false));
        }
        if (typedArrayObtainAttributes.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(typedArrayObtainAttributes.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attrs));
        typedArrayObtainAttributes.recycle();
        return googleMapOptions;
    }

    public GoogleMapOptions camera(CameraPosition camera) {
        this.akI = camera;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean enabled) {
        this.akK = Boolean.valueOf(enabled);
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CameraPosition getCamera() {
        return this.akI;
    }

    public Boolean getCompassEnabled() {
        return this.akK;
    }

    public Boolean getLiteMode() {
        return this.akP;
    }

    public Boolean getMapToolbarEnabled() {
        return this.akQ;
    }

    public int getMapType() {
        return this.akH;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.akO;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.akL;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.akN;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.akG;
    }

    int getVersionCode() {
        return this.CK;
    }

    public Boolean getZOrderOnTop() {
        return this.akF;
    }

    public Boolean getZoomControlsEnabled() {
        return this.akJ;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.akM;
    }

    public GoogleMapOptions liteMode(boolean enabled) {
        this.akP = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean enabled) {
        this.akQ = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapType(int mapType) {
        this.akH = mapType;
        return this;
    }

    byte nD() {
        return com.google.android.gms.maps.internal.a.c(this.akF);
    }

    byte nE() {
        return com.google.android.gms.maps.internal.a.c(this.akG);
    }

    byte nF() {
        return com.google.android.gms.maps.internal.a.c(this.akJ);
    }

    byte nG() {
        return com.google.android.gms.maps.internal.a.c(this.akK);
    }

    byte nH() {
        return com.google.android.gms.maps.internal.a.c(this.akL);
    }

    byte nI() {
        return com.google.android.gms.maps.internal.a.c(this.akM);
    }

    byte nJ() {
        return com.google.android.gms.maps.internal.a.c(this.akN);
    }

    byte nK() {
        return com.google.android.gms.maps.internal.a.c(this.akO);
    }

    byte nL() {
        return com.google.android.gms.maps.internal.a.c(this.akP);
    }

    byte nM() {
        return com.google.android.gms.maps.internal.a.c(this.akQ);
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.akO = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.akL = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.akN = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.akG = Boolean.valueOf(useViewLifecycleInFragment);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        if (aa.ob()) {
            b.a(this, out, flags);
        } else {
            a.a(this, out, flags);
        }
    }

    public GoogleMapOptions zOrderOnTop(boolean zOrderOnTop) {
        this.akF = Boolean.valueOf(zOrderOnTop);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.akJ = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.akM = Boolean.valueOf(enabled);
        return this;
    }
}
