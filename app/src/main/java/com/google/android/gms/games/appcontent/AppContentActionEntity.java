package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentActionEntity implements SafeParcelable, AppContentAction {
    public static final AppContentActionEntityCreator CREATOR = new AppContentActionEntityCreator();
    private final int CK;
    private final ArrayList<AppContentConditionEntity> XJ;
    private final String XK;
    private final String XL;
    private final String XM;
    private final Bundle mExtras;
    private final String vc;

    AppContentActionEntity(int versionCode, ArrayList<AppContentConditionEntity> conditions, String contentDescription, Bundle extras, String label, String labelStyle, String type) {
        this.CK = versionCode;
        this.XJ = conditions;
        this.XK = contentDescription;
        this.mExtras = extras;
        this.XL = label;
        this.XM = labelStyle;
        this.vc = type;
    }

    public AppContentActionEntity(AppContentAction action) {
        this.CK = 1;
        this.XK = action.kI();
        this.mExtras = action.getExtras();
        this.XL = action.getLabel();
        this.XM = action.kJ();
        this.vc = action.getType();
        List<AppContentCondition> listKH = action.kH();
        int size = listKH.size();
        this.XJ = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.XJ.add((AppContentConditionEntity) listKH.get(i).freeze());
        }
    }

    static int a(AppContentAction appContentAction) {
        return jv.hashCode(appContentAction.kH(), appContentAction.kI(), appContentAction.getExtras(), appContentAction.getLabel(), appContentAction.kJ(), appContentAction.getType());
    }

    static boolean a(AppContentAction appContentAction, Object obj) {
        if (!(obj instanceof AppContentAction)) {
            return false;
        }
        if (appContentAction == obj) {
            return true;
        }
        AppContentAction appContentAction2 = (AppContentAction) obj;
        return jv.equal(appContentAction2.kH(), appContentAction.kH()) && jv.equal(appContentAction2.kI(), appContentAction.kI()) && jv.equal(appContentAction2.getExtras(), appContentAction.getExtras()) && jv.equal(appContentAction2.getLabel(), appContentAction.getLabel()) && jv.equal(appContentAction2.kJ(), appContentAction.kJ()) && jv.equal(appContentAction2.getType(), appContentAction.getType());
    }

    static String b(AppContentAction appContentAction) {
        return jv.h(appContentAction).a("Conditions", appContentAction.kH()).a("ContentDescription", appContentAction.kI()).a("Extras", appContentAction.getExtras()).a("Label", appContentAction.getLabel()).a("LabelStyle", appContentAction.kJ()).a("Type", appContentAction.getType()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public Bundle getExtras() {
        return this.mExtras;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String getLabel() {
        return this.XL;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String getType() {
        return this.vc;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public List<AppContentCondition> kH() {
        return new ArrayList(this.XJ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String kI() {
        return this.XK;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentAction
    public String kJ() {
        return this.XM;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kK, reason: merged with bridge method [inline-methods] */
    public AppContentAction freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        AppContentActionEntityCreator.a(this, out, flags);
    }
}
