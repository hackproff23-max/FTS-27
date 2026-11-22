package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TimeSpan;
import com.google.android.gms.internal.jv;
import com.google.android.gms.internal.jx;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ScoreSubmissionData {
    private static final String[] adl = {"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
    private int Iv;
    private String Xh;
    private HashMap<Integer, Result> adR = new HashMap<>();
    private String adn;

    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long rawScore, String formattedScore, String scoreTag, boolean newBest) {
            this.rawScore = rawScore;
            this.formattedScore = formattedScore;
            this.scoreTag = scoreTag;
            this.newBest = newBest;
        }

        public String toString() {
            return jv.h(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.Iv = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        jx.L(count == 3);
        for (int i = 0; i < count; i++) {
            int iAu = dataHolder.au(i);
            if (i == 0) {
                this.adn = dataHolder.c("leaderboardId", i, iAu);
                this.Xh = dataHolder.c("playerId", i, iAu);
            }
            if (dataHolder.d("hasResult", i, iAu)) {
                a(new Result(dataHolder.a("rawScore", i, iAu), dataHolder.c("formattedScore", i, iAu), dataHolder.c("scoreTag", i, iAu), dataHolder.d("newBest", i, iAu)), dataHolder.b("timeSpan", i, iAu));
            }
        }
    }

    private void a(Result result, int i) {
        this.adR.put(Integer.valueOf(i), result);
    }

    public String getLeaderboardId() {
        return this.adn;
    }

    public String getPlayerId() {
        return this.Xh;
    }

    public Result getScoreResult(int timeSpan) {
        return this.adR.get(Integer.valueOf(timeSpan));
    }

    public String toString() {
        jv.a aVarA = jv.h(this).a("PlayerId", this.Xh).a("StatusCode", Integer.valueOf(this.Iv));
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= 3) {
                return aVarA.toString();
            }
            Result result = this.adR.get(Integer.valueOf(i2));
            aVarA.a("TimesSpan", TimeSpan.dZ(i2));
            aVarA.a("Result", result == null ? "null" : result.toString());
            i = i2 + 1;
        }
    }
}
