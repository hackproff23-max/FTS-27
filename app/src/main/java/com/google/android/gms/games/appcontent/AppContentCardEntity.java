package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentCardEntity implements SafeParcelable, AppContentCard {
    public static final AppContentCardEntityCreator CREATOR = new AppContentCardEntityCreator();
    private final int CK;
    private final String OH;
    private final String UO;
    private final Uri WD;
    private final ArrayList<AppContentConditionEntity> XJ;
    private final String XK;
    private final Uri XN;
    private final ArrayList<AppContentAnnotationEntity> XO;
    private final int XP;
    private final Bundle XQ;
    private final String XR;
    private final int XS;
    private final ArrayList<AppContentActionEntity> mActions;
    private final String vc;

    AppContentCardEntity(int versionCode, ArrayList<AppContentActionEntity> actions, ArrayList<AppContentAnnotationEntity> annotations, ArrayList<AppContentConditionEntity> conditions, String contentDescription, int currentProgress, String description, Bundle extraData, Uri iconImageUri, Uri imageUri, String subtitle, String title, int totalProgress, String type) {
        this.CK = versionCode;
        this.mActions = actions;
        this.XO = annotations;
        this.XJ = conditions;
        this.XK = contentDescription;
        this.XP = currentProgress;
        this.UO = description;
        this.XQ = extraData;
        this.WD = iconImageUri;
        this.XN = imageUri;
        this.XR = subtitle;
        this.OH = title;
        this.XS = totalProgress;
        this.vc = type;
    }

    public AppContentCardEntity(AppContentCard card) {
        this.CK = 1;
        this.XK = card.kI();
        this.XP = card.kO();
        this.UO = card.getDescription();
        this.XQ = card.kP();
        this.WD = card.getIconImageUri();
        this.XN = card.kL();
        this.OH = card.getTitle();
        this.XR = card.kQ();
        this.XS = card.kR();
        this.vc = card.getType();
        List<AppContentAction> actions = card.getActions();
        int size = actions.size();
        this.mActions = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.mActions.add((AppContentActionEntity) actions.get(i).freeze());
        }
        List<AppContentAnnotation> listKN = card.kN();
        int size2 = listKN.size();
        this.XO = new ArrayList<>(size2);
        for (int i2 = 0; i2 < size2; i2++) {
            this.XO.add((AppContentAnnotationEntity) listKN.get(i2).freeze());
        }
        List<AppContentCondition> listKH = card.kH();
        int size3 = listKH.size();
        this.XJ = new ArrayList<>(size3);
        for (int i3 = 0; i3 < size3; i3++) {
            this.XJ.add((AppContentConditionEntity) listKH.get(i3).freeze());
        }
    }

    static int a(AppContentCard appContentCard) {
        return jv.hashCode(appContentCard.getActions(), appContentCard.kN(), appContentCard.kH(), appContentCard.kI(), Integer.valueOf(appContentCard.kO()), appContentCard.getDescription(), appContentCard.kP(), appContentCard.getIconImageUri(), appContentCard.kL(), appContentCard.kQ(), appContentCard.getTitle(), Integer.valueOf(appContentCard.kR()), appContentCard.getType());
    }

    static boolean a(AppContentCard appContentCard, Object obj) {
        if (!(obj instanceof AppContentCard)) {
            return false;
        }
        if (appContentCard == obj) {
            return true;
        }
        AppContentCard appContentCard2 = (AppContentCard) obj;
        return jv.equal(appContentCard2.getActions(), appContentCard.getActions()) && jv.equal(appContentCard2.kN(), appContentCard.kN()) && jv.equal(appContentCard2.kH(), appContentCard.kH()) && jv.equal(appContentCard2.kI(), appContentCard.kI()) && jv.equal(Integer.valueOf(appContentCard2.kO()), Integer.valueOf(appContentCard.kO())) && jv.equal(appContentCard2.getDescription(), appContentCard.getDescription()) && jv.equal(appContentCard2.kP(), appContentCard.kP()) && jv.equal(appContentCard2.getIconImageUri(), appContentCard.getIconImageUri()) && jv.equal(appContentCard2.kL(), appContentCard.kL()) && jv.equal(appContentCard2.kQ(), appContentCard.kQ()) && jv.equal(appContentCard2.getTitle(), appContentCard.getTitle()) && jv.equal(Integer.valueOf(appContentCard2.kR()), Integer.valueOf(appContentCard.kR())) && jv.equal(appContentCard2.getType(), appContentCard.getType());
    }

    static String b(AppContentCard appContentCard) {
        return jv.h(appContentCard).a("Actions", appContentCard.getActions()).a("Annotations", appContentCard.kN()).a("Conditions", appContentCard.kH()).a("ContentDescription", appContentCard.kI()).a("CurrentSteps", Integer.valueOf(appContentCard.kO())).a("Description", appContentCard.getDescription()).a("ExtraData", appContentCard.kP()).a("IconImageUri", appContentCard.getIconImageUri()).a("ImageUri", appContentCard.kL()).a("Subtitle", appContentCard.kQ()).a("Title", appContentCard.getTitle()).a("TotalSteps", Integer.valueOf(appContentCard.kR())).a("Type", appContentCard.getType()).toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentAction> getActions() {
        return new ArrayList(this.mActions);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Uri getIconImageUri() {
        return this.WD;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String getTitle() {
        return this.OH;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
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

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentCondition> kH() {
        return new ArrayList(this.XJ);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String kI() {
        return this.XK;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Uri kL() {
        return this.XN;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public List<AppContentAnnotation> kN() {
        return new ArrayList(this.XO);
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public int kO() {
        return this.XP;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public Bundle kP() {
        return this.XQ;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public String kQ() {
        return this.XR;
    }

    @Override // com.google.android.gms.games.appcontent.AppContentCard
    public int kR() {
        return this.XS;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: kS, reason: merged with bridge method [inline-methods] */
    public AppContentCard freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        AppContentCardEntityCreator.a(this, out, flags);
    }
}
