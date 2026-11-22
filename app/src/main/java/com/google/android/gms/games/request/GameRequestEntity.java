package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class GameRequestEntity implements SafeParcelable, GameRequest {
    public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
    private final int CK;
    private final int FP;
    private final int Gt;
    private final String Zt;
    private final GameEntity acs;
    private final long adS;
    private final byte[] aeA;
    private final PlayerEntity aeU;
    private final ArrayList<PlayerEntity> aeV;
    private final long aeW;
    private final Bundle aeX;

    GameRequestEntity(int versionCode, GameEntity game, PlayerEntity sender, byte[] data, String requestId, ArrayList<PlayerEntity> recipients, int type, long creationTimestamp, long expirationTimestamp, Bundle recipientStatuses, int status) {
        this.CK = versionCode;
        this.acs = game;
        this.aeU = sender;
        this.aeA = data;
        this.Zt = requestId;
        this.aeV = recipients;
        this.Gt = type;
        this.adS = creationTimestamp;
        this.aeW = expirationTimestamp;
        this.aeX = recipientStatuses;
        this.FP = status;
    }

    public GameRequestEntity(GameRequest request) {
        this.CK = 2;
        this.acs = new GameEntity(request.getGame());
        this.aeU = new PlayerEntity(request.getSender());
        this.Zt = request.getRequestId();
        this.Gt = request.getType();
        this.adS = request.getCreationTimestamp();
        this.aeW = request.getExpirationTimestamp();
        this.FP = request.getStatus();
        byte[] data = request.getData();
        if (data == null) {
            this.aeA = null;
        } else {
            this.aeA = new byte[data.length];
            System.arraycopy(data, 0, this.aeA, 0, data.length);
        }
        List<Player> recipients = request.getRecipients();
        int size = recipients.size();
        this.aeV = new ArrayList<>(size);
        this.aeX = new Bundle();
        for (int i = 0; i < size; i++) {
            Player playerFreeze = recipients.get(i).freeze();
            String playerId = playerFreeze.getPlayerId();
            this.aeV.add((PlayerEntity) playerFreeze);
            this.aeX.putInt(playerId, request.getRecipientStatus(playerId));
        }
    }

    static int a(GameRequest gameRequest) {
        return jv.hashCode(gameRequest.getGame(), gameRequest.getRecipients(), gameRequest.getRequestId(), gameRequest.getSender(), b(gameRequest), Integer.valueOf(gameRequest.getType()), Long.valueOf(gameRequest.getCreationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    static boolean a(GameRequest gameRequest, Object obj) {
        if (!(obj instanceof GameRequest)) {
            return false;
        }
        if (gameRequest == obj) {
            return true;
        }
        GameRequest gameRequest2 = (GameRequest) obj;
        return jv.equal(gameRequest2.getGame(), gameRequest.getGame()) && jv.equal(gameRequest2.getRecipients(), gameRequest.getRecipients()) && jv.equal(gameRequest2.getRequestId(), gameRequest.getRequestId()) && jv.equal(gameRequest2.getSender(), gameRequest.getSender()) && Arrays.equals(b(gameRequest2), b(gameRequest)) && jv.equal(Integer.valueOf(gameRequest2.getType()), Integer.valueOf(gameRequest.getType())) && jv.equal(Long.valueOf(gameRequest2.getCreationTimestamp()), Long.valueOf(gameRequest.getCreationTimestamp())) && jv.equal(Long.valueOf(gameRequest2.getExpirationTimestamp()), Long.valueOf(gameRequest.getExpirationTimestamp()));
    }

    private static int[] b(GameRequest gameRequest) {
        List<Player> recipients = gameRequest.getRecipients();
        int size = recipients.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = gameRequest.getRecipientStatus(recipients.get(i).getPlayerId());
        }
        return iArr;
    }

    static String c(GameRequest gameRequest) {
        return jv.h(gameRequest).a("Game", gameRequest.getGame()).a("Sender", gameRequest.getSender()).a("Recipients", gameRequest.getRecipients()).a("Data", gameRequest.getData()).a("RequestId", gameRequest.getRequestId()).a("Type", Integer.valueOf(gameRequest.getType())).a("CreationTimestamp", Long.valueOf(gameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(gameRequest.getExpirationTimestamp())).toString();
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
    public GameRequest freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long getCreationTimestamp() {
        return this.adS;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public byte[] getData() {
        return this.aeA;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long getExpirationTimestamp() {
        return this.aeW;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Game getGame() {
        return this.acs;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getRecipientStatus(String playerId) {
        return this.aeX.getInt(playerId, 0);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public List<Player> getRecipients() {
        return new ArrayList(this.aeV);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public String getRequestId() {
        return this.Zt;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Player getSender() {
        return this.aeU;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getStatus() {
        return this.FP;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getType() {
        return this.Gt;
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public boolean isConsumed(String playerId) {
        return getRecipientStatus(playerId) == 1;
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    public Bundle mS() {
        return this.aeX;
    }

    public String toString() {
        return c(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        GameRequestEntityCreator.a(this, dest, flags);
    }
}
