package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.GamesClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class PlayersImpl implements Players {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$10, reason: invalid class name */
    class AnonymousClass10 extends LoadPlayersImpl {
        final /* synthetic */ String ZZ;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "nearby", this.ZZ, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$11, reason: invalid class name */
    class AnonymousClass11 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZZ;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "played_with", this.ZZ, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$12, reason: invalid class name */
    class AnonymousClass12 extends LoadPlayersImpl {
        final /* synthetic */ String ZZ;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "played_with", this.ZZ, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$13, reason: invalid class name */
    class AnonymousClass13 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$14, reason: invalid class name */
    class AnonymousClass14 extends LoadPlayersImpl {
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$15, reason: invalid class name */
    class AnonymousClass15 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$16, reason: invalid class name */
    class AnonymousClass16 extends LoadPlayersImpl {
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$17, reason: invalid class name */
    class AnonymousClass17 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$18, reason: invalid class name */
    class AnonymousClass18 extends LoadPlayersImpl {
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$19, reason: invalid class name */
    class AnonymousClass19 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;
        final /* synthetic */ String aat;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.aat, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$20, reason: invalid class name */
    class AnonymousClass20 extends LoadPlayersImpl {
        final /* synthetic */ int aar;
        final /* synthetic */ String aat;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.aat, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$21, reason: invalid class name */
    class AnonymousClass21 extends LoadPlayersImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String aaR;
        final /* synthetic */ int aar;
        final /* synthetic */ String aba;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aba, this.aaR, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$22, reason: invalid class name */
    class AnonymousClass22 extends LoadPlayersImpl {
        final /* synthetic */ String aaR;
        final /* synthetic */ int aar;
        final /* synthetic */ String aba;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b((BaseImplementation.b<Players.LoadPlayersResult>) this, this.aba, this.aaR, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$23, reason: invalid class name */
    class AnonymousClass23 extends LoadOwnerCoverPhotoUrisImpl {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.g(this);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$24, reason: invalid class name */
    class AnonymousClass24 extends LoadXpForGameCategoriesResultImpl {
        final /* synthetic */ String abb;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.m(this, this.abb);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$25, reason: invalid class name */
    class AnonymousClass25 extends LoadXpStreamResultImpl {
        final /* synthetic */ String abb;
        final /* synthetic */ int abc;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c(this, this.abb, this.abc);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$26, reason: invalid class name */
    class AnonymousClass26 extends LoadXpStreamResultImpl {
        final /* synthetic */ String abb;
        final /* synthetic */ int abc;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.abb, this.abc);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$27, reason: invalid class name */
    class AnonymousClass27 extends LoadProfileSettingsResultImpl {
        final /* synthetic */ boolean ZW;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.f(this, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$28, reason: invalid class name */
    class AnonymousClass28 extends UpdateProfileSettingsResultImpl {
        final /* synthetic */ boolean abd;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.g(this, this.abd);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$3, reason: invalid class name */
    class AnonymousClass3 extends LoadPlayersImpl {
        final /* synthetic */ String[] abe;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.abe);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.PlayersImpl$9, reason: invalid class name */
    class AnonymousClass9 extends LoadPlayersImpl {
        final /* synthetic */ String ZZ;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "nearby", this.ZZ, this.aar, false, false);
        }
    }

    private static abstract class LoadOwnerCoverPhotoUrisImpl extends Games.BaseGamesApiMethodImpl<Players.LoadOwnerCoverPhotoUrisResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ac, reason: merged with bridge method [inline-methods] */
        public Players.LoadOwnerCoverPhotoUrisResult c(final Status status) {
            return new Players.LoadOwnerCoverPhotoUrisResult() { // from class: com.google.android.gms.games.internal.api.PlayersImpl.LoadOwnerCoverPhotoUrisImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadPlayersImpl extends Games.BaseGamesApiMethodImpl<Players.LoadPlayersResult> {
        private LoadPlayersImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ad, reason: merged with bridge method [inline-methods] */
        public Players.LoadPlayersResult c(final Status status) {
            return new Players.LoadPlayersResult() { // from class: com.google.android.gms.games.internal.api.PlayersImpl.LoadPlayersImpl.1
                @Override // com.google.android.gms.games.Players.LoadPlayersResult
                public PlayerBuffer getPlayers() {
                    return new PlayerBuffer(DataHolder.av(14));
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

    private static abstract class LoadProfileSettingsResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadProfileSettingsResult> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ae, reason: merged with bridge method [inline-methods] */
        public Players.LoadProfileSettingsResult c(final Status status) {
            return new Players.LoadProfileSettingsResult() { // from class: com.google.android.gms.games.internal.api.PlayersImpl.LoadProfileSettingsResultImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }

                @Override // com.google.android.gms.games.Players.LoadProfileSettingsResult
                public boolean isProfileVisible() {
                    return true;
                }

                @Override // com.google.android.gms.games.Players.LoadProfileSettingsResult
                public boolean isVisibilityExplicitlySet() {
                    return false;
                }
            };
        }
    }

    private static abstract class LoadXpForGameCategoriesResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadXpForGameCategoriesResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: af, reason: merged with bridge method [inline-methods] */
        public Players.LoadXpForGameCategoriesResult c(final Status status) {
            return new Players.LoadXpForGameCategoriesResult() { // from class: com.google.android.gms.games.internal.api.PlayersImpl.LoadXpForGameCategoriesResultImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class LoadXpStreamResultImpl extends Games.BaseGamesApiMethodImpl<Players.LoadXpStreamResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ag, reason: merged with bridge method [inline-methods] */
        public Players.LoadXpStreamResult c(final Status status) {
            return new Players.LoadXpStreamResult() { // from class: com.google.android.gms.games.internal.api.PlayersImpl.LoadXpStreamResultImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class UpdateProfileSettingsResultImpl extends Games.BaseGamesApiMethodImpl<Status> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    @Override // com.google.android.gms.games.Players
    public Player getCurrentPlayer(GoogleApiClient apiClient) {
        return Games.d(apiClient).lj();
    }

    @Override // com.google.android.gms.games.Players
    public String getCurrentPlayerId(GoogleApiClient apiClient) {
        return Games.d(apiClient).li();
    }

    @Override // com.google.android.gms.games.Players
    public Intent getPlayerSearchIntent(GoogleApiClient apiClient) {
        return Games.d(apiClient).lt();
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadConnectedPlayers(GoogleApiClient apiClient, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(this, forceReload);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadInvitablePlayers(GoogleApiClient apiClient, final int pageSize, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, pageSize, false, forceReload);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient apiClient, final int pageSize) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, pageSize, true, false);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient apiClient, final int pageSize) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "played_with", pageSize, true, false);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient apiClient, final String playerId) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, playerId, false);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient apiClient, final String playerId, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a(this, playerId, forceReload);
            }
        });
    }

    @Override // com.google.android.gms.games.Players
    public PendingResult<Players.LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient apiClient, final int pageSize, final boolean forceReload) {
        return apiClient.a(new LoadPlayersImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.PlayersImpl.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<Players.LoadPlayersResult>) this, "played_with", pageSize, false, forceReload);
            }
        });
    }
}
