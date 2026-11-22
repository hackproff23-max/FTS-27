package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.internal.GamesClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class AchievementsImpl implements Achievements {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.AchievementsImpl$10, reason: invalid class name */
    class AnonymousClass10 extends LoadImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZY;
        final /* synthetic */ String ZZ;

        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.ZY, this.ZZ, this.ZW);
        }
    }

    private static abstract class LoadImpl extends Games.BaseGamesApiMethodImpl<Achievements.LoadAchievementsResult> {
        private LoadImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public Achievements.LoadAchievementsResult c(final Status status) {
            return new Achievements.LoadAchievementsResult() { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.LoadImpl.1
                @Override // com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
                public AchievementBuffer getAchievements() {
                    return new AchievementBuffer(DataHolder.av(14));
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }

                @Override // com.google.android.gms.common.api.Releasable
                public void release() {
                }
            };
        }
    }

    private static abstract class UpdateImpl extends Games.BaseGamesApiMethodImpl<Achievements.UpdateAchievementResult> {
        private final String CE;

        public UpdateImpl(String id, GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.CE = id;
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
        public Achievements.UpdateAchievementResult c(final Status status) {
            return new Achievements.UpdateAchievementResult() { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.UpdateImpl.1
                @Override // com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
                public String getAchievementId() {
                    return UpdateImpl.this.CE;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public Intent getAchievementsIntent(GoogleApiClient apiClient) {
        return Games.d(apiClient).lm();
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public void increment(GoogleApiClient apiClient, final String id, final int numSteps) {
        apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.6
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Achievements.UpdateAchievementResult>) null, id, numSteps);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public PendingResult<Achievements.UpdateAchievementResult> incrementImmediate(GoogleApiClient apiClient, final String id, final int numSteps) {
        return apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.7
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(this, id, numSteps);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public PendingResult<Achievements.LoadAchievementsResult> load(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.1
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.c(this, forceReload);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public void reveal(GoogleApiClient apiClient, final String id) {
        apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.2
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Achievements.UpdateAchievementResult>) null, id);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public PendingResult<Achievements.UpdateAchievementResult> revealImmediate(GoogleApiClient apiClient, final String id) {
        return apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.3
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(this, id);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public void setSteps(GoogleApiClient apiClient, final String id, final int numSteps) {
        apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.8
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<Achievements.UpdateAchievementResult>) null, id, numSteps);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public PendingResult<Achievements.UpdateAchievementResult> setStepsImmediate(GoogleApiClient apiClient, final String id, final int numSteps) {
        return apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.9
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(this, id, numSteps);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public void unlock(GoogleApiClient apiClient, final String id) {
        apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.4
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((BaseImplementation.b<Achievements.UpdateAchievementResult>) null, id);
            }
        });
    }

    @Override // com.google.android.gms.games.achievement.Achievements
    public PendingResult<Achievements.UpdateAchievementResult> unlockImmediate(GoogleApiClient apiClient, final String id) {
        return apiClient.b(new UpdateImpl(id, apiClient) { // from class: com.google.android.gms.games.internal.api.AchievementsImpl.5
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(this, id);
            }
        });
    }
}
