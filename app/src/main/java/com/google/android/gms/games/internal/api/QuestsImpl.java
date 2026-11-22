package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;

/* JADX INFO: loaded from: classes.dex */
public final class QuestsImpl implements Quests {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.QuestsImpl$5, reason: invalid class name */
    class AnonymousClass5 extends LoadsImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZZ;
        final /* synthetic */ int aaA;
        final /* synthetic */ int[] abn;
        final /* synthetic */ String abp;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.ZZ, this.abp, this.abn, this.aaA, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.QuestsImpl$6, reason: invalid class name */
    class AnonymousClass6 extends LoadsImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZZ;
        final /* synthetic */ String[] abo;
        final /* synthetic */ String abp;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.ZZ, this.abp, this.ZW, this.abo);
        }
    }

    private static abstract class AcceptImpl extends Games.BaseGamesApiMethodImpl<Quests.AcceptQuestResult> {
        private AcceptImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ah, reason: merged with bridge method [inline-methods] */
        public Quests.AcceptQuestResult c(final Status status) {
            return new Quests.AcceptQuestResult() { // from class: com.google.android.gms.games.internal.api.QuestsImpl.AcceptImpl.1
                @Override // com.google.android.gms.games.quest.Quests.AcceptQuestResult
                public Quest getQuest() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class ClaimImpl extends Games.BaseGamesApiMethodImpl<Quests.ClaimMilestoneResult> {
        private ClaimImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ai, reason: merged with bridge method [inline-methods] */
        public Quests.ClaimMilestoneResult c(final Status status) {
            return new Quests.ClaimMilestoneResult() { // from class: com.google.android.gms.games.internal.api.QuestsImpl.ClaimImpl.1
                @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
                public Milestone getMilestone() {
                    return null;
                }

                @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
                public Quest getQuest() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadsImpl extends Games.BaseGamesApiMethodImpl<Quests.LoadQuestsResult> {
        private LoadsImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: aj, reason: merged with bridge method [inline-methods] */
        public Quests.LoadQuestsResult c(final Status status) {
            return new Quests.LoadQuestsResult() { // from class: com.google.android.gms.games.internal.api.QuestsImpl.LoadsImpl.1
                @Override // com.google.android.gms.games.quest.Quests.LoadQuestsResult
                public QuestBuffer getQuests() {
                    return new QuestBuffer(DataHolder.av(status.getStatusCode()));
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

    @Override // com.google.android.gms.games.quest.Quests
    public PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient apiClient, final String questId) {
        return apiClient.b(new AcceptImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.QuestsImpl.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.h(this, questId);
            }
        });
    }

    @Override // com.google.android.gms.games.quest.Quests
    public PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient apiClient, final String questId, final String milestoneId) {
        return apiClient.b(new ClaimImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.QuestsImpl.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(this, questId, milestoneId);
            }
        });
    }

    @Override // com.google.android.gms.games.quest.Quests
    public Intent getQuestIntent(GoogleApiClient apiClient, String questId) {
        return Games.d(apiClient).bE(questId);
    }

    @Override // com.google.android.gms.games.quest.Quests
    public Intent getQuestsIntent(GoogleApiClient apiClient, int[] questSelectors) {
        return Games.d(apiClient).b(questSelectors);
    }

    @Override // com.google.android.gms.games.quest.Quests
    public PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient apiClient, final int[] questSelectors, final int sortOrder, final boolean forceReload) {
        return apiClient.a(new LoadsImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.QuestsImpl.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(this, questSelectors, sortOrder, forceReload);
            }
        });
    }

    @Override // com.google.android.gms.games.quest.Quests
    public PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient apiClient, final boolean forceReload, final String... questIds) {
        return apiClient.a(new LoadsImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.QuestsImpl.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b(this, forceReload, questIds);
            }
        });
    }

    @Override // com.google.android.gms.games.quest.Quests
    public void registerQuestUpdateListener(GoogleApiClient apiClient, QuestUpdateListener listener) {
        Games.d(apiClient).c(apiClient.d(listener));
    }

    @Override // com.google.android.gms.games.quest.Quests
    public void showStateChangedPopup(GoogleApiClient apiClient, String questId) {
        Games.d(apiClient).bF(questId);
    }

    @Override // com.google.android.gms.games.quest.Quests
    public void unregisterQuestUpdateListener(GoogleApiClient apiClient) {
        Games.d(apiClient).lr();
    }
}
