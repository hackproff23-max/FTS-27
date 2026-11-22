package com.google.android.gms.games.internal.request;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jv;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class GameRequestCluster implements SafeParcelable, GameRequest {
    public static final GameRequestClusterCreator CREATOR = new GameRequestClusterCreator();
    private final int CK;
    private final ArrayList<GameRequestEntity> adk;

    GameRequestCluster(int versionCode, ArrayList<GameRequestEntity> requestList) {
        this.CK = versionCode;
        this.adk = requestList;
        mp();
    }

    private void mp() {
        je.K(!this.adk.isEmpty());
        GameRequestEntity gameRequestEntity = this.adk.get(0);
        int size = this.adk.size();
        for (int i = 1; i < size; i++) {
            GameRequestEntity gameRequestEntity2 = this.adk.get(i);
            je.a(gameRequestEntity.getType() == gameRequestEntity2.getType(), "All the requests must be of the same type");
            je.a(gameRequestEntity.getSender().equals(gameRequestEntity2.getSender()), "All the requests must be from the same sender");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GameRequestCluster)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GameRequestCluster gameRequestCluster = (GameRequestCluster) obj;
        if (gameRequestCluster.adk.size() != this.adk.size()) {
            return false;
        }
        int size = this.adk.size();
        for (int i = 0; i < size; i++) {
            if (!this.adk.get(i).equals(gameRequestCluster.adk.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.common.data.Freezable
    public GameRequest freeze() {
        return this;
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long getCreationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public byte[] getData() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public long getExpirationTimestamp() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Game getGame() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getRecipientStatus(String playerId) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public String getRequestId() {
        return this.adk.get(0).getRequestId();
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public Player getSender() {
        return this.adk.get(0).getSender();
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getStatus() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public int getType() {
        return this.adk.get(0).getType();
    }

    public int getVersionCode() {
        return this.CK;
    }

    public int hashCode() {
        return jv.hashCode(this.adk.toArray());
    }

    @Override // com.google.android.gms.games.request.GameRequest
    public boolean isConsumed(String playerId) {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    public ArrayList<GameRequest> mD() {
        return new ArrayList<>(this.adk);
    }

    @Override // com.google.android.gms.games.request.GameRequest
    /* JADX INFO: renamed from: mE, reason: merged with bridge method [inline-methods] */
    public ArrayList<Player> getRecipients() {
        throw new UnsupportedOperationException("Method not supported on a cluster");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        GameRequestClusterCreator.a(this, dest, flags);
    }
}
