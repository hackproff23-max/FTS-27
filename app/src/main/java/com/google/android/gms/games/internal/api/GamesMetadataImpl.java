package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.internal.GamesClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class GamesMetadataImpl implements GamesMetadata {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$10, reason: invalid class name */
    class AnonymousClass10 extends LoadExtendedGamesImpl {
        final /* synthetic */ String aaq;
        final /* synthetic */ int aar;
        final /* synthetic */ boolean aas;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult>) this, this.aaq, this.aar, false, true, false, this.aas);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$11, reason: invalid class name */
    class AnonymousClass11 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c(this, this.ZY, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$12, reason: invalid class name */
    class AnonymousClass12 extends LoadExtendedGamesImpl {
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.c(this, this.ZY, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$13, reason: invalid class name */
    class AnonymousClass13 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.ZY, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$14, reason: invalid class name */
    class AnonymousClass14 extends LoadExtendedGamesImpl {
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.d(this, this.ZY, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$15, reason: invalid class name */
    class AnonymousClass15 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String aaq;
        final /* synthetic */ int aar;
        final /* synthetic */ boolean aas;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult>) this, this.aaq, this.aar, true, false, this.ZW, this.aas);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$16, reason: invalid class name */
    class AnonymousClass16 extends LoadExtendedGamesImpl {
        final /* synthetic */ String aaq;
        final /* synthetic */ int aar;
        final /* synthetic */ boolean aas;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult>) this, this.aaq, this.aar, true, true, false, this.aas);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$17, reason: invalid class name */
    class AnonymousClass17 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;
        final /* synthetic */ String aat;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.aat, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$18, reason: invalid class name */
    class AnonymousClass18 extends LoadExtendedGamesImpl {
        final /* synthetic */ int aar;
        final /* synthetic */ String aat;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e(this, this.aat, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$19, reason: invalid class name */
    class AnonymousClass19 extends LoadGameSearchSuggestionsImpl {
        final /* synthetic */ String aat;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.l(this, this.aat);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$2, reason: invalid class name */
    class AnonymousClass2 extends LoadExtendedGamesImpl {
        final /* synthetic */ String ZZ;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.j(this, this.ZZ);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$3, reason: invalid class name */
    class AnonymousClass3 extends LoadGameInstancesImpl {
        final /* synthetic */ String ZZ;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.k(this, this.ZZ);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$4, reason: invalid class name */
    class AnonymousClass4 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, null, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$5, reason: invalid class name */
    class AnonymousClass5 extends LoadExtendedGamesImpl {
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, null, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$6, reason: invalid class name */
    class AnonymousClass6 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.ZY, this.aar, false, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$7, reason: invalid class name */
    class AnonymousClass7 extends LoadExtendedGamesImpl {
        final /* synthetic */ String ZY;
        final /* synthetic */ int aar;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.b(this, this.ZY, this.aar, true, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$8, reason: invalid class name */
    class AnonymousClass8 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aar;
        final /* synthetic */ int aau;
        final /* synthetic */ boolean aav;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.aar, this.aau, this.aav, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.GamesMetadataImpl$9, reason: invalid class name */
    class AnonymousClass9 extends LoadExtendedGamesImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ String aaq;
        final /* synthetic */ int aar;
        final /* synthetic */ boolean aas;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a((BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult>) this, this.aaq, this.aar, false, false, this.ZW, this.aas);
        }
    }

    private static abstract class LoadExtendedGamesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadExtendedGamesResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: P, reason: merged with bridge method [inline-methods] */
        public GamesMetadata.LoadExtendedGamesResult c(final Status status) {
            return new GamesMetadata.LoadExtendedGamesResult() { // from class: com.google.android.gms.games.internal.api.GamesMetadataImpl.LoadExtendedGamesImpl.1
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

    private static abstract class LoadGameInstancesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGameInstancesResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
        public GamesMetadata.LoadGameInstancesResult c(final Status status) {
            return new GamesMetadata.LoadGameInstancesResult() { // from class: com.google.android.gms.games.internal.api.GamesMetadataImpl.LoadGameInstancesImpl.1
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

    private static abstract class LoadGameSearchSuggestionsImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGameSearchSuggestionsResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
        public GamesMetadata.LoadGameSearchSuggestionsResult c(final Status status) {
            return new GamesMetadata.LoadGameSearchSuggestionsResult() { // from class: com.google.android.gms.games.internal.api.GamesMetadataImpl.LoadGameSearchSuggestionsImpl.1
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

    private static abstract class LoadGamesImpl extends Games.BaseGamesApiMethodImpl<GamesMetadata.LoadGamesResult> {
        private LoadGamesImpl(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
        public GamesMetadata.LoadGamesResult c(final Status status) {
            return new GamesMetadata.LoadGamesResult() { // from class: com.google.android.gms.games.internal.api.GamesMetadataImpl.LoadGamesImpl.1
                @Override // com.google.android.gms.games.GamesMetadata.LoadGamesResult
                public GameBuffer getGames() {
                    return new GameBuffer(DataHolder.av(14));
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

    @Override // com.google.android.gms.games.GamesMetadata
    public Game getCurrentGame(GoogleApiClient apiClient) {
        return Games.d(apiClient).lk();
    }

    @Override // com.google.android.gms.games.GamesMetadata
    public PendingResult<GamesMetadata.LoadGamesResult> loadGame(GoogleApiClient apiClient) {
        return apiClient.a(new LoadGamesImpl(apiClient) { // from class: com.google.android.gms.games.internal.api.GamesMetadataImpl.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(GamesClientImpl gamesClientImpl) {
                gamesClientImpl.f(this);
            }
        });
    }
}
