package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;

/* JADX INFO: loaded from: classes.dex */
public final class AchievementEntity implements SafeParcelable, Achievement {
    public static final AchievementEntityCreator CREATOR = new AchievementEntityCreator();
    private final int CK;
    private final int Gt;
    private final String UO;
    private final Uri XA;
    private final String XB;
    private final int XC;
    private final String XD;
    private final PlayerEntity XE;
    private final int XF;
    private final String XG;
    private final long XH;
    private final long XI;
    private final String Xx;
    private final Uri Xy;
    private final String Xz;
    private final String mName;
    private final int mState;

    AchievementEntity(int versionCode, String achievementId, int type, String name, String description, Uri unlockedImageUri, String unlockedImageUrl, Uri revealedImageUri, String revealedImageUrl, int totalSteps, String formattedTotalSteps, PlayerEntity player, int state, int currentSteps, String formattedCurrentSteps, long lastUpdatedTimestamp, long xpValue) {
        this.CK = versionCode;
        this.Xx = achievementId;
        this.Gt = type;
        this.mName = name;
        this.UO = description;
        this.Xy = unlockedImageUri;
        this.Xz = unlockedImageUrl;
        this.XA = revealedImageUri;
        this.XB = revealedImageUrl;
        this.XC = totalSteps;
        this.XD = formattedTotalSteps;
        this.XE = player;
        this.mState = state;
        this.XF = currentSteps;
        this.XG = formattedCurrentSteps;
        this.XH = lastUpdatedTimestamp;
        this.XI = xpValue;
    }

    public AchievementEntity(Achievement achievement) {
        this.CK = 1;
        this.Xx = achievement.getAchievementId();
        this.Gt = achievement.getType();
        this.mName = achievement.getName();
        this.UO = achievement.getDescription();
        this.Xy = achievement.getUnlockedImageUri();
        this.Xz = achievement.getUnlockedImageUrl();
        this.XA = achievement.getRevealedImageUri();
        this.XB = achievement.getRevealedImageUrl();
        this.XE = (PlayerEntity) achievement.getPlayer().freeze();
        this.mState = achievement.getState();
        this.XH = achievement.getLastUpdatedTimestamp();
        this.XI = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.XC = achievement.getTotalSteps();
            this.XD = achievement.getFormattedTotalSteps();
            this.XF = achievement.getCurrentSteps();
            this.XG = achievement.getFormattedCurrentSteps();
        } else {
            this.XC = 0;
            this.XD = null;
            this.XF = 0;
            this.XG = null;
        }
        je.f(this.Xx);
        je.f(this.UO);
    }

    static int a(Achievement achievement) {
        int totalSteps;
        int currentSteps;
        if (achievement.getType() == 1) {
            currentSteps = achievement.getCurrentSteps();
            totalSteps = achievement.getTotalSteps();
        } else {
            totalSteps = 0;
            currentSteps = 0;
        }
        return jv.hashCode(achievement.getAchievementId(), achievement.getName(), Integer.valueOf(achievement.getType()), achievement.getDescription(), Long.valueOf(achievement.getXpValue()), Integer.valueOf(achievement.getState()), Long.valueOf(achievement.getLastUpdatedTimestamp()), achievement.getPlayer(), Integer.valueOf(currentSteps), Integer.valueOf(totalSteps));
    }

    static boolean a(Achievement achievement, Object obj) {
        boolean zEqual;
        boolean zEqual2;
        if (!(obj instanceof Achievement)) {
            return false;
        }
        if (achievement == obj) {
            return true;
        }
        Achievement achievement2 = (Achievement) obj;
        if (achievement.getType() == 1) {
            zEqual2 = jv.equal(Integer.valueOf(achievement2.getCurrentSteps()), Integer.valueOf(achievement.getCurrentSteps()));
            zEqual = jv.equal(Integer.valueOf(achievement2.getTotalSteps()), Integer.valueOf(achievement.getTotalSteps()));
        } else {
            zEqual = true;
            zEqual2 = true;
        }
        return jv.equal(achievement2.getAchievementId(), achievement.getAchievementId()) && jv.equal(achievement2.getName(), achievement.getName()) && jv.equal(Integer.valueOf(achievement2.getType()), Integer.valueOf(achievement.getType())) && jv.equal(achievement2.getDescription(), achievement.getDescription()) && jv.equal(Long.valueOf(achievement2.getXpValue()), Long.valueOf(achievement.getXpValue())) && jv.equal(Integer.valueOf(achievement2.getState()), Integer.valueOf(achievement.getState())) && jv.equal(Long.valueOf(achievement2.getLastUpdatedTimestamp()), Long.valueOf(achievement.getLastUpdatedTimestamp())) && jv.equal(achievement2.getPlayer(), achievement.getPlayer()) && zEqual2 && zEqual;
    }

    static String b(Achievement achievement) {
        jv.a aVarA = jv.h(achievement).a("Id", achievement.getAchievementId()).a("Type", Integer.valueOf(achievement.getType())).a("Name", achievement.getName()).a("Description", achievement.getDescription()).a("Player", achievement.getPlayer()).a("State", Integer.valueOf(achievement.getState()));
        if (achievement.getType() == 1) {
            aVarA.a("CurrentSteps", Integer.valueOf(achievement.getCurrentSteps()));
            aVarA.a("TotalSteps", Integer.valueOf(achievement.getTotalSteps()));
        }
        return aVarA.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public Achievement freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getAchievementId() {
        return this.Xx;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int getCurrentSteps() {
        return this.XF;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public void getDescription(CharArrayBuffer dataOut) {
        le.b(this.UO, dataOut);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getFormattedCurrentSteps() {
        return this.XG;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public void getFormattedCurrentSteps(CharArrayBuffer dataOut) {
        le.b(this.XG, dataOut);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getFormattedTotalSteps() {
        return this.XD;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public void getFormattedTotalSteps(CharArrayBuffer dataOut) {
        le.b(this.XD, dataOut);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public long getLastUpdatedTimestamp() {
        return this.XH;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getName() {
        return this.mName;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public void getName(CharArrayBuffer dataOut) {
        le.b(this.mName, dataOut);
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Player getPlayer() {
        return this.XE;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Uri getRevealedImageUri() {
        return this.XA;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getRevealedImageUrl() {
        return this.XB;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int getState() {
        return this.mState;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int getTotalSteps() {
        return this.XC;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public int getType() {
        return this.Gt;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public Uri getUnlockedImageUri() {
        return this.Xy;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public String getUnlockedImageUrl() {
        return this.Xz;
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.games.achievement.Achievement
    public long getXpValue() {
        return this.XI;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        AchievementEntityCreator.a(this, dest, flags);
    }
}
