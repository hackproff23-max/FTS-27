package com.google.android.gms.games.internal.api;

import android.os.Bundle;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.internal.GamesClientImpl;

/* JADX INFO: loaded from: classes.dex */
public final class NotificationsImpl implements Notifications {

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$1, reason: invalid class name */
    class AnonymousClass1 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusChangeResult> {
        final /* synthetic */ String aaR;

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public Notifications.GameMuteStatusChangeResult c(final Status status) {
            return new Notifications.GameMuteStatusChangeResult() { // from class: com.google.android.gms.games.internal.api.NotificationsImpl.1.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e((BaseImplementation.b<Notifications.GameMuteStatusChangeResult>) this, this.aaR, true);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$2, reason: invalid class name */
    class AnonymousClass2 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusChangeResult> {
        final /* synthetic */ String aaR;

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: Y, reason: merged with bridge method [inline-methods] */
        public Notifications.GameMuteStatusChangeResult c(final Status status) {
            return new Notifications.GameMuteStatusChangeResult() { // from class: com.google.android.gms.games.internal.api.NotificationsImpl.2.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.e((BaseImplementation.b<Notifications.GameMuteStatusChangeResult>) this, this.aaR, false);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$3, reason: invalid class name */
    class AnonymousClass3 extends Games.BaseGamesApiMethodImpl<Notifications.GameMuteStatusLoadResult> {
        final /* synthetic */ String aaR;

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
        public Notifications.GameMuteStatusLoadResult c(final Status status) {
            return new Notifications.GameMuteStatusLoadResult() { // from class: com.google.android.gms.games.internal.api.NotificationsImpl.3.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.p(this, this.aaR);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$4, reason: invalid class name */
    class AnonymousClass4 extends ContactSettingLoadImpl {
        final /* synthetic */ boolean ZW;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.h(this, this.ZW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$5, reason: invalid class name */
    class AnonymousClass5 extends ContactSettingUpdateImpl {
        final /* synthetic */ boolean aaV;
        final /* synthetic */ Bundle aaW;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.a(this, this.aaV, this.aaW);
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.games.internal.api.NotificationsImpl$6, reason: invalid class name */
    class AnonymousClass6 extends InboxCountImpl {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public void a(GamesClientImpl gamesClientImpl) {
            gamesClientImpl.i(this);
        }
    }

    private static abstract class ContactSettingLoadImpl extends Games.BaseGamesApiMethodImpl<Notifications.ContactSettingLoadResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: aa, reason: merged with bridge method [inline-methods] */
        public Notifications.ContactSettingLoadResult c(final Status status) {
            return new Notifications.ContactSettingLoadResult() { // from class: com.google.android.gms.games.internal.api.NotificationsImpl.ContactSettingLoadImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static abstract class ContactSettingUpdateImpl extends Games.BaseGamesApiMethodImpl<Status> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class InboxCountImpl extends Games.BaseGamesApiMethodImpl<Notifications.InboxCountResult> {
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ab, reason: merged with bridge method [inline-methods] */
        public Notifications.InboxCountResult c(final Status status) {
            return new Notifications.InboxCountResult() { // from class: com.google.android.gms.games.internal.api.NotificationsImpl.InboxCountImpl.1
                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    @Override // com.google.android.gms.games.Notifications
    public void clear(GoogleApiClient apiClient, int notificationTypes) {
        Games.d(apiClient).dT(notificationTypes);
    }

    @Override // com.google.android.gms.games.Notifications
    public void clearAll(GoogleApiClient apiClient) {
        clear(apiClient, 31);
    }
}
