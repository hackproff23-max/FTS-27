package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentSectionEntity implements SafeParcelable, AppContentSection {
    public static final AppContentSectionEntityCreator CREATOR = new AppContentSectionEntityCreator();
    private final int CK;
    private final String OH;
    private final String XK;
    private final Bundle XQ;
    private final String XR;
    private final Uri XY;
    private final ArrayList<AppContentCardEntity> XZ;
    private final ArrayList<AppContentActionEntity> mActions;
    private final String vc;

    AppContentSectionEntity(int versionCode, ArrayList<AppContentActionEntity> actions, Uri backgroundImageUri, ArrayList<AppContentCardEntity> cards, String contentDescription, Bundle extraData, String subtitle, String title, String type) {
        this.CK = versionCode;
        this.mActions = actions;
        this.XY = backgroundImageUri;
        this.XZ = cards;
        this.XK = contentDescription;
        this.XQ = extraData;
        this.XR = subtitle;
        this.OH = title;
        this.vc = type;
    }

    public AppContentSectionEntity(AppContentSection cardSection) {
        this.CK = 1;
        this.XY = cardSection.kY();
        this.XK = cardSection.kI();
        this.XQ = cardSection.kP();
        this.XR = cardSection.kQ();
        this.OH = cardSection.getTitle();
        this.vc = cardSection.getType();
        List<AppContentAction> actions = cardSection.getActions();
        int size = actions.size();
        this.mActions = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.mActions.add((AppContentActionEntity) actions.get(i).freeze());
        }
        List<AppContentCard> listKZ = cardSection.kZ();
        int size2 = listKZ.size();
        this.XZ = new ArrayList<>(size2);
        for (int i2 = 0; i2 < size2; i2++) {
            this.XZ.add((AppContentCardEntity) listKZ.get(i2).freeze());
        }
    }

    static int a(AppContentSection appContentSection) {
        return jv.hashCode(appContentSection.getActions(), appContentSection.kY(), appContentSection.kZ(), appContentSection.kI(), appContentSection.kP(), appContentSection.kQ(), appContentSection.getTitle(), appContentSection.getType());
    }

    static boolean a(AppContentSection appContentSection, Object obj) {
        if (!(obj instanceof AppContentSection)) {
            return false;
        }
        if (appContentSection == obj) {
            return true;
        }
        AppContentSection appContentSection2 = (AppContentSection) obj;
        return jv.equal(appContentSection2.getActions(), appContentSection.getActions()) && jv.equal(appContentSection2.kY(), appContentSection.kY()) && jv.equal(appContentSection2.kZ(), appContentSection.kZ()) && jv.equal(appContentSection2.kI(), appContentSection.kI()) && jv.equal(appContentSection2.kP(), appContentSection.kP()) && jv.equal(appContentSection2.kQ(), appContentSection.kQ()) && jv.equal(appContentSection2.getTitle(), appContentSection.getTitle()) && jv.equal(appContentSection2.getType(), appContentSection.getType());
    }

    static String b(AppContentSection appContentSection) {
        return jv.h(appContentSection).a("Actions", appContentSection.getActions()).a("BackgroundImageUri", appContentSection.kY()).a("Cards", appContentSection.kZ()).a("ContentDescription", appContentSection.kI()).a("ExtraData", appContentSection.kP()).a("Subtitle", appContentSection.kQ()).a("Title", appContentSection.getTitle()).a("Type", appContentSection.getType()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public List<AppContentAction> getActions() {
        return new ArrayList(this.mActions);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String getTitle() {
        return this.OH;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
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

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String kI() {
        return this.XK;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public Bundle kP() {
        return this.XQ;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public String kQ() {
        return this.XR;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public Uri kY() {
        return this.XY;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentSection
    public List<AppContentCard> kZ() {
        return new ArrayList(this.XZ);
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: la, reason: merged with bridge method [inline-methods] */
    public AppContentSection freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        AppContentSectionEntityCreator.a(this, out, flags);
    }
}
