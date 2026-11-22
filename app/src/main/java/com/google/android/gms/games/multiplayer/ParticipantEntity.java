package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;

/* JADX INFO: loaded from: classes.dex */
public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new ParticipantEntityCreatorCompat();
    private final int CK;
    private final int FO;
    private final int FP;
    private final String OS;
    private final Uri WD;
    private final Uri WE;
    private final String WO;
    private final String WP;
    private final PlayerEntity XE;
    private final String Yi;
    private final String Zk;
    private final boolean adZ;
    private final ParticipantResult aea;

    static final class ParticipantEntityCreatorCompat extends ParticipantEntityCreator {
        ParticipantEntityCreatorCompat() {
        }

        @Override // com.google.android.gms.games.multiplayer.ParticipantEntityCreator, android.os.Parcelable.Creator
        /* JADX INFO: renamed from: cA, reason: merged with bridge method [inline-methods] */
        public ParticipantEntity createFromParcel(Parcel parcel) {
            if (ParticipantEntity.c(ParticipantEntity.ht()) || ParticipantEntity.aW(ParticipantEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            Uri uri = string3 == null ? null : Uri.parse(string3);
            String string4 = parcel.readString();
            return new ParticipantEntity(3, string, string2, uri, string4 == null ? null : Uri.parse(string4), parcel.readInt(), parcel.readString(), parcel.readInt() > 0, parcel.readInt() > 0 ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, null, null, null);
        }
    }

    ParticipantEntity(int versionCode, String participantId, String displayName, Uri iconImageUri, Uri hiResImageUri, int status, String clientAddress, boolean connectedToRoom, PlayerEntity player, int capabilities, ParticipantResult result, String iconImageUrl, String hiResImageUrl) {
        this.CK = versionCode;
        this.Zk = participantId;
        this.OS = displayName;
        this.WD = iconImageUri;
        this.WE = hiResImageUri;
        this.FP = status;
        this.Yi = clientAddress;
        this.adZ = connectedToRoom;
        this.XE = player;
        this.FO = capabilities;
        this.aea = result;
        this.WO = iconImageUrl;
        this.WP = hiResImageUrl;
    }

    public ParticipantEntity(Participant participant) {
        this.CK = 3;
        this.Zk = participant.getParticipantId();
        this.OS = participant.getDisplayName();
        this.WD = participant.getIconImageUri();
        this.WE = participant.getHiResImageUri();
        this.FP = participant.getStatus();
        this.Yi = participant.le();
        this.adZ = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.XE = player == null ? null : new PlayerEntity(player);
        this.FO = participant.getCapabilities();
        this.aea = participant.getResult();
        this.WO = participant.getIconImageUrl();
        this.WP = participant.getHiResImageUrl();
    }

    static int a(Participant participant) {
        return jv.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.le(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId());
    }

    static boolean a(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return jv.equal(participant2.getPlayer(), participant.getPlayer()) && jv.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && jv.equal(participant2.le(), participant.le()) && jv.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && jv.equal(participant2.getDisplayName(), participant.getDisplayName()) && jv.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && jv.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && jv.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && jv.equal(participant2.getResult(), participant.getResult()) && jv.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    static String b(Participant participant) {
        return jv.h(participant).a("ParticipantId", participant.getParticipantId()).a("Player", participant.getPlayer()).a("Status", Integer.valueOf(participant.getStatus())).a("ClientAddress", participant.le()).a("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).a("DisplayName", participant.getDisplayName()).a("IconImage", participant.getIconImageUri()).a("IconImageUrl", participant.getIconImageUrl()).a("HiResImage", participant.getHiResImageUri()).a("HiResImageUrl", participant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(participant.getCapabilities())).a("Result", participant.getResult()).toString();
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
    public Participant freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public int getCapabilities() {
        return this.FO;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String getDisplayName() {
        return this.XE == null ? this.OS : this.XE.getDisplayName();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public void getDisplayName(CharArrayBuffer dataOut) {
        if (this.XE == null) {
            le.b(this.OS, dataOut);
        } else {
            this.XE.getDisplayName(dataOut);
        }
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Uri getHiResImageUri() {
        return this.XE == null ? this.WE : this.XE.getHiResImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String getHiResImageUrl() {
        return this.XE == null ? this.WP : this.XE.getHiResImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Uri getIconImageUri() {
        return this.XE == null ? this.WD : this.XE.getIconImageUri();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String getIconImageUrl() {
        return this.XE == null ? this.WO : this.XE.getIconImageUrl();
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String getParticipantId() {
        return this.Zk;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public Player getPlayer() {
        return this.XE;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public ParticipantResult getResult() {
        return this.aea;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public int getStatus() {
        return this.FP;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public boolean isConnectedToRoom() {
        return this.adZ;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.games.multiplayer.Participant
    public String le() {
        return this.Yi;
    }

    public String toString() {
        return b(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (!hu()) {
            ParticipantEntityCreator.a(this, dest, flags);
            return;
        }
        dest.writeString(this.Zk);
        dest.writeString(this.OS);
        dest.writeString(this.WD == null ? null : this.WD.toString());
        dest.writeString(this.WE != null ? this.WE.toString() : null);
        dest.writeInt(this.FP);
        dest.writeString(this.Yi);
        dest.writeInt(this.adZ ? 1 : 0);
        dest.writeInt(this.XE != null ? 1 : 0);
        if (this.XE != null) {
            this.XE.writeToParcel(dest, flags);
        }
    }
}
