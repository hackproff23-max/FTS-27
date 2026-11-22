package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.le;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class LeaderboardEntity implements Leaderboard {
    private final String OS;
    private final Uri WD;
    private final String WO;
    private final String adn;
    private final int ado;
    private final ArrayList<LeaderboardVariantEntity> adp;
    private final Game adq;

    public LeaderboardEntity(Leaderboard leaderboard) {
        this.adn = leaderboard.getLeaderboardId();
        this.OS = leaderboard.getDisplayName();
        this.WD = leaderboard.getIconImageUri();
        this.WO = leaderboard.getIconImageUrl();
        this.ado = leaderboard.getScoreOrder();
        Game game = leaderboard.getGame();
        this.adq = game == null ? null : new GameEntity(game);
        ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        int size = variants.size();
        this.adp = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.adp.add((LeaderboardVariantEntity) variants.get(i).freeze());
        }
    }

    static int a(Leaderboard leaderboard) {
        return jv.hashCode(leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), Integer.valueOf(leaderboard.getScoreOrder()), leaderboard.getVariants());
    }

    static boolean a(Leaderboard leaderboard, Object obj) {
        if (!(obj instanceof Leaderboard)) {
            return false;
        }
        if (leaderboard == obj) {
            return true;
        }
        Leaderboard leaderboard2 = (Leaderboard) obj;
        return jv.equal(leaderboard2.getLeaderboardId(), leaderboard.getLeaderboardId()) && jv.equal(leaderboard2.getDisplayName(), leaderboard.getDisplayName()) && jv.equal(leaderboard2.getIconImageUri(), leaderboard.getIconImageUri()) && jv.equal(Integer.valueOf(leaderboard2.getScoreOrder()), Integer.valueOf(leaderboard.getScoreOrder())) && jv.equal(leaderboard2.getVariants(), leaderboard.getVariants());
    }

    static String b(Leaderboard leaderboard) {
        return jv.h(leaderboard).a("LeaderboardId", leaderboard.getLeaderboardId()).a("DisplayName", leaderboard.getDisplayName()).a("IconImageUri", leaderboard.getIconImageUri()).a("IconImageUrl", leaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(leaderboard.getScoreOrder())).a("Variants", leaderboard.getVariants()).toString();
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public String getDisplayName() {
        return this.OS;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public void getDisplayName(CharArrayBuffer dataOut) {
        le.b(this.OS, dataOut);
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public Game getGame() {
        return this.adq;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public Uri getIconImageUri() {
        return this.WD;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public String getIconImageUrl() {
        return this.WO;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public String getLeaderboardId() {
        return this.adn;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public int getScoreOrder() {
        return this.ado;
    }

    @Override // com.google.android.gms.games.leaderboard.Leaderboard
    public ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<>(this.adp);
    }

    public int hashCode() {
        return a(this);
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: mG, reason: merged with bridge method [inline-methods] */
    public Leaderboard freeze() {
        return this;
    }

    public String toString() {
        return b(this);
    }
}
