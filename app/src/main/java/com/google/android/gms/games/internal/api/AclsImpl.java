package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.game.Acls;

/* JADX INFO: loaded from: classes.dex */
public final class AclsImpl implements Acls {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.AclsImpl$2, reason: invalid class name */
    class AnonymousClass2 extends LoadNotifyAclImpl {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.h(this);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.AclsImpl$3, reason: invalid class name */
    class AnonymousClass3 extends UpdateNotifyAclImpl {
        final /* synthetic */ String aae;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.o(this, this.aae);
        }
    }

    private static abstract class LoadNotifyAclImpl extends Games.BaseGamesApiMethodImpl<Acls.LoadAclResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: M, reason: merged with bridge method [inline-methods] */
        public Acls.LoadAclResult c(Status status) {
            return AclsImpl.K(status);
        }
    }

    private static abstract class UpdateNotifyAclImpl extends Games.BaseGamesApiMethodImpl<Status> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Acls.LoadAclResult K(final Status status) {
        return new Acls.LoadAclResult() { // from class: com.google.android.gms.games.internal.api.AclsImpl.1
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
