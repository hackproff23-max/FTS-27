package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.appcontent.AppContents;
import com.google.android.gms.games.internal.GamesClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class AppContentsImpl implements AppContents {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.AppContentsImpl$1, reason: invalid class name */
    class AnonymousClass1 extends LoadsImpl {
        final /* synthetic */ boolean ZW;
        final /* synthetic */ int aaf;
        final /* synthetic */ String aag;
        final /* synthetic */ String[] aah;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.aaf, this.aag, this.aah, this.ZW);
        }
    }

    private static abstract class LoadsImpl extends Games.BaseGamesApiMethodImpl<AppContents.LoadAppContentResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: N, reason: merged with bridge method [inline-methods] */
        public AppContents.LoadAppContentResult c(final Status status) {
            return new AppContents.LoadAppContentResult() { // from class: com.google.android.gms.games.internal.api.AppContentsImpl.LoadsImpl.1
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
}
