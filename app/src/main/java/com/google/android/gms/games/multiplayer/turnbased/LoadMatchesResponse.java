package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class LoadMatchesResponse {
    private final InvitationBuffer aes;
    private final TurnBasedMatchBuffer aet;
    private final TurnBasedMatchBuffer aeu;
    private final TurnBasedMatchBuffer aev;

    public LoadMatchesResponse(Bundle matchData) {
        DataHolder dataHolderA = a(matchData, 0);
        if (dataHolderA != null) {
            this.aes = new InvitationBuffer(dataHolderA);
        } else {
            this.aes = null;
        }
        DataHolder dataHolderA2 = a(matchData, 1);
        if (dataHolderA2 != null) {
            this.aet = new TurnBasedMatchBuffer(dataHolderA2);
        } else {
            this.aet = null;
        }
        DataHolder dataHolderA3 = a(matchData, 2);
        if (dataHolderA3 != null) {
            this.aeu = new TurnBasedMatchBuffer(dataHolderA3);
        } else {
            this.aeu = null;
        }
        DataHolder dataHolderA4 = a(matchData, 3);
        if (dataHolderA4 != null) {
            this.aev = new TurnBasedMatchBuffer(dataHolderA4);
        } else {
            this.aev = null;
        }
    }

    private static DataHolder a(Bundle bundle, int i) {
        String strDZ = TurnBasedMatchTurnStatus.dZ(i);
        if (bundle.containsKey(strDZ)) {
            return (DataHolder) bundle.getParcelable(strDZ);
        }
        return null;
    }

    @Deprecated
    public void close() {
        release();
    }

    public TurnBasedMatchBuffer getCompletedMatches() {
        return this.aev;
    }

    public InvitationBuffer getInvitations() {
        return this.aes;
    }

    public TurnBasedMatchBuffer getMyTurnMatches() {
        return this.aet;
    }

    public TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.aeu;
    }

    public boolean hasData() {
        if (this.aes != null && this.aes.getCount() > 0) {
            return true;
        }
        if (this.aet != null && this.aet.getCount() > 0) {
            return true;
        }
        if (this.aeu == null || this.aeu.getCount() <= 0) {
            return this.aev != null && this.aev.getCount() > 0;
        }
        return true;
    }

    public void release() {
        if (this.aes != null) {
            this.aes.release();
        }
        if (this.aet != null) {
            this.aet.release();
        }
        if (this.aeu != null) {
            this.aeu.release();
        }
        if (this.aev != null) {
            this.aev.release();
        }
    }
}
