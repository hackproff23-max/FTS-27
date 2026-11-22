package com.google.android.gms.games.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.b;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.OnNearbyPlayerDetectedListener;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.appcontent.AppContents;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.internal.events.EventIncrementCache;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.game.GameSearchSuggestionBuffer;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotContentsEntity;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChangeEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.ll;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class GamesClientImpl extends jl<IGamesService> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final String DZ;
    EventIncrementManager Yk;
    private final String Yl;
    private final Map<String, RealTimeSocket> Ym;
    private PlayerEntity Yn;
    private GameEntity Yo;
    private final PopupManager Yp;
    private boolean Yq;
    private final Binder Yr;
    private final long Ys;
    private final Games.GamesOptions Yt;

    private static abstract class AbstractPeerStatusNotifier extends AbstractRoomStatusNotifier {
        private final ArrayList<String> Yv;

        AbstractPeerStatusNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder);
            this.Yv = new ArrayList<>();
            for (String str : participantIds) {
                this.Yv.add(str);
            }
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            a(roomStatusUpdateListener, room, this.Yv);
        }

        protected abstract void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList);
    }

    private static abstract class AbstractRoomNotifier extends a<RoomUpdateListener> {
        AbstractRoomNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.a
        public void a(RoomUpdateListener roomUpdateListener, DataHolder dataHolder) {
            a(roomUpdateListener, GamesClientImpl.R(dataHolder), dataHolder.getStatusCode());
        }

        protected abstract void a(RoomUpdateListener roomUpdateListener, Room room, int i);
    }

    private static abstract class AbstractRoomStatusNotifier extends a<RoomStatusUpdateListener> {
        AbstractRoomStatusNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.a
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, DataHolder dataHolder) {
            a(roomStatusUpdateListener, GamesClientImpl.R(dataHolder));
        }

        protected abstract void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room);
    }

    private static final class AcceptQuestResultImpl extends b implements Quests.AcceptQuestResult {
        private final Quest Yw;

        AcceptQuestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.Yw = new QuestEntity(questBuffer.get(0));
                } else {
                    this.Yw = null;
                }
            } finally {
                questBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.quest.Quests.AcceptQuestResult
        public Quest getQuest() {
            return this.Yw;
        }
    }

    private static final class AchievementUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Achievements.UpdateAchievementResult> Ea;

        AchievementUpdatedBinderCallback(BaseImplementation.b<Achievements.UpdateAchievementResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void g(int i, String str) {
            this.Ea.b(new UpdateAchievementResultImpl(i, str));
        }
    }

    private static final class AchievementsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Achievements.LoadAchievementsResult> Ea;

        AchievementsLoadedBinderCallback(BaseImplementation.b<Achievements.LoadAchievementsResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void c(DataHolder dataHolder) {
            this.Ea.b(new LoadAchievementsResultImpl(dataHolder));
        }
    }

    private static final class AppContentLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<AppContents.LoadAppContentResult> Yx;

        public AppContentLoadedBinderCallbacks(BaseImplementation.b<AppContents.LoadAppContentResult> resultHolder) {
            this.Yx = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(DataHolder[] dataHolderArr) {
            this.Yx.b(new LoadAppContentsResultImpl(dataHolderArr));
        }
    }

    private static final class CancelMatchResultImpl implements TurnBasedMultiplayer.CancelMatchResult {
        private final Status Eb;
        private final String Yy;

        CancelMatchResultImpl(Status status, String externalMatchId) {
            this.Eb = status;
            this.Yy = externalMatchId;
        }

        @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
        public String getMatchId() {
            return this.Yy;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class ClaimMilestoneResultImpl extends b implements Quests.ClaimMilestoneResult {
        private final Quest Yw;
        private final Milestone Yz;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        ClaimMilestoneResultImpl(DataHolder dataHolder, String milestoneId) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.Yw = new QuestEntity(questBuffer.get(0));
                    List<Milestone> listMQ = this.Yw.mQ();
                    int size = listMQ.size();
                    for (int i = 0; i < size; i++) {
                        if (listMQ.get(i).getMilestoneId().equals(milestoneId)) {
                            this.Yz = listMQ.get(i);
                            questBuffer.release();
                            return;
                        }
                    }
                    this.Yz = null;
                } else {
                    this.Yz = null;
                    this.Yw = null;
                }
                questBuffer.release();
            } catch (Throwable th) {
                questBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
        public Milestone getMilestone() {
            return this.Yz;
        }

        @Override // com.google.android.gms.games.quest.Quests.ClaimMilestoneResult
        public Quest getQuest() {
            return this.Yw;
        }
    }

    private static final class CommitSnapshotResultImpl extends b implements Snapshots.CommitSnapshotResult {
        private final SnapshotMetadata YA;

        CommitSnapshotResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() > 0) {
                    this.YA = new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0));
                } else {
                    this.YA = null;
                }
            } finally {
                snapshotMetadataBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult
        public SnapshotMetadata getSnapshotMetadata() {
            return this.YA;
        }
    }

    private static final class ConnectedToRoomNotifier extends AbstractRoomStatusNotifier {
        ConnectedToRoomNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomStatusNotifier
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }

    private static final class ContactSettingLoadResultImpl extends b implements Notifications.ContactSettingLoadResult {
        ContactSettingLoadResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class ContactSettingsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Notifications.ContactSettingLoadResult> Ea;

        ContactSettingsLoadedBinderCallback(BaseImplementation.b<Notifications.ContactSettingLoadResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void D(DataHolder dataHolder) {
            this.Ea.b(new ContactSettingLoadResultImpl(dataHolder));
        }
    }

    private static final class ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Status> Ea;

        ContactSettingsUpdatedBinderCallback(BaseImplementation.b<Status> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void dP(int i) {
            this.Ea.b(new Status(i));
        }
    }

    private static final class DeleteSnapshotResultImpl implements Snapshots.DeleteSnapshotResult {
        private final Status Eb;
        private final String YB;

        DeleteSnapshotResultImpl(int statusCode, String snapshotId) {
            this.Eb = new Status(statusCode);
            this.YB = snapshotId;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult
        public String getSnapshotId() {
            return this.YB;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class DisconnectedFromRoomNotifier extends AbstractRoomStatusNotifier {
        DisconnectedFromRoomNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomStatusNotifier
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }

    private static final class EventsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Events.LoadEventsResult> Ea;

        EventsLoadedBinderCallback(BaseImplementation.b<Events.LoadEventsResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void d(DataHolder dataHolder) {
            this.Ea.b(new LoadEventResultImpl(dataHolder));
        }
    }

    private static final class ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> Ea;

        ExtendedGamesLoadedBinderCallback(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void j(DataHolder dataHolder) {
            this.Ea.b(new LoadExtendedGamesResultImpl(dataHolder));
        }
    }

    private class GameClientEventIncrementCache extends EventIncrementCache {
        public GameClientEventIncrementCache() {
            super(GamesClientImpl.this.getContext().getMainLooper(), 1000);
        }

        @Override // com.google.android.gms.games.internal.events.EventIncrementCache
        protected void r(String str, int i) {
            try {
                if (GamesClientImpl.this.isConnected()) {
                    GamesClientImpl.this.hw().o(str, i);
                } else {
                    GamesLog.p("GamesClientImpl", "Unable to increment event " + str + " by " + i + " because the games client is no longer connected");
                }
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
            }
        }
    }

    private static final class GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> Ea;

        GameInstancesLoadedBinderCallback(BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void k(DataHolder dataHolder) {
            this.Ea.b(new LoadGameInstancesResultImpl(dataHolder));
        }
    }

    private static final class GameMuteStatusChangeResultImpl implements Notifications.GameMuteStatusChangeResult {
        private final Status Eb;
        private final String YC;
        private final boolean YD;

        public GameMuteStatusChangeResultImpl(int statusCode, String externalGameId, boolean isMuted) {
            this.Eb = new Status(statusCode);
            this.YC = externalGameId;
            this.YD = isMuted;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Notifications.GameMuteStatusChangeResult> Ea;

        GameMuteStatusChangedBinderCallback(BaseImplementation.b<Notifications.GameMuteStatusChangeResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(int i, String str, boolean z) {
            this.Ea.b(new GameMuteStatusChangeResultImpl(i, str, z));
        }
    }

    private static final class GameMuteStatusLoadResultImpl implements Notifications.GameMuteStatusLoadResult {
        private final Status Eb;
        private final String YC;
        private final boolean YD;

        public GameMuteStatusLoadResultImpl(DataHolder dataHolder) {
            try {
                this.Eb = new Status(dataHolder.getStatusCode());
                if (dataHolder.getCount() > 0) {
                    this.YC = dataHolder.c("external_game_id", 0, 0);
                    this.YD = dataHolder.d("muted", 0, 0);
                } else {
                    this.YC = null;
                    this.YD = false;
                }
            } finally {
                dataHolder.close();
            }
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Notifications.GameMuteStatusLoadResult> Ea;

        GameMuteStatusLoadedBinderCallback(BaseImplementation.b<Notifications.GameMuteStatusLoadResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void B(DataHolder dataHolder) {
            this.Ea.b(new GameMuteStatusLoadResultImpl(dataHolder));
        }
    }

    private static final class GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> Ea;

        GameSearchSuggestionsLoadedBinderCallback(BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void l(DataHolder dataHolder) {
            this.Ea.b(new LoadGameSearchSuggestionsResultImpl(dataHolder));
        }
    }

    private static final class GamesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<GamesMetadata.LoadGamesResult> Ea;

        GamesLoadedBinderCallback(BaseImplementation.b<GamesMetadata.LoadGamesResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void i(DataHolder dataHolder) {
            this.Ea.b(new LoadGamesResultImpl(dataHolder));
        }
    }

    private static final class InboxCountResultImpl implements Notifications.InboxCountResult {
        private final Status Eb;
        private final Bundle YE;

        InboxCountResultImpl(Status status, Bundle inboxCounts) {
            this.Eb = status;
            this.YE = inboxCounts;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class InboxCountsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Notifications.InboxCountResult> Ea;

        InboxCountsLoadedBinderCallback(BaseImplementation.b<Notifications.InboxCountResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void f(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.Ea.b(new InboxCountResultImpl(new Status(i), bundle));
        }
    }

    private static final class InitiateMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.InitiateMatchResult {
        InitiateMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class InvitationReceivedBinderCallback extends AbstractGamesCallbacks {
        private final d<OnInvitationReceivedListener> Qe;

        InvitationReceivedBinderCallback(d<OnInvitationReceivedListener> listener) {
            this.Qe = listener;
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void n(DataHolder dataHolder) {
            InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            try {
                Invitation invitationFreeze = invitationBuffer.getCount() > 0 ? invitationBuffer.get(0).freeze() : null;
                invitationBuffer.release();
                if (invitationFreeze != null) {
                    this.Qe.a(new InvitationReceivedNotifier(invitationFreeze));
                }
            } catch (Throwable th) {
                invitationBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onInvitationRemoved(String invitationId) {
            this.Qe.a(new InvitationRemovedNotifier(invitationId));
        }
    }

    private static final class InvitationReceivedNotifier implements d.b<OnInvitationReceivedListener> {
        private final Invitation YF;

        InvitationReceivedNotifier(Invitation invitation) {
            this.YF = invitation;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.YF);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class InvitationRemovedNotifier implements d.b<OnInvitationReceivedListener> {
        private final String YG;

        InvitationRemovedNotifier(String invitationId) {
            this.YG = invitationId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.YG);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class InvitationsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Invitations.LoadInvitationsResult> Ea;

        InvitationsLoadedBinderCallback(BaseImplementation.b<Invitations.LoadInvitationsResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void m(DataHolder dataHolder) {
            this.Ea.b(new LoadInvitationsResultImpl(dataHolder));
        }
    }

    private static final class JoinedRoomNotifier extends AbstractRoomNotifier {
        public JoinedRoomNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomNotifier
        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onJoinedRoom(i, room);
        }
    }

    private static final class LeaderboardMetadataResultImpl extends b implements Leaderboards.LeaderboardMetadataResult {
        private final LeaderboardBuffer YH;

        LeaderboardMetadataResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YH = new LeaderboardBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
        public LeaderboardBuffer getLeaderboards() {
            return this.YH;
        }
    }

    private static final class LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Leaderboards.LoadScoresResult> Ea;

        LeaderboardScoresLoadedBinderCallback(BaseImplementation.b<Leaderboards.LoadScoresResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(DataHolder dataHolder, DataHolder dataHolder2) {
            this.Ea.b(new LoadScoresResultImpl(dataHolder, dataHolder2));
        }
    }

    private static final class LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> Ea;

        LeaderboardsLoadedBinderCallback(BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void e(DataHolder dataHolder) {
            this.Ea.b(new LeaderboardMetadataResultImpl(dataHolder));
        }
    }

    private static final class LeaveMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.LeaveMatchResult {
        LeaveMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LeftRoomNotifier implements d.b<RoomUpdateListener> {
        private final int Iv;
        private final String YI;

        LeftRoomNotifier(int statusCode, String roomId) {
            this.Iv = statusCode;
            this.YI = roomId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.Iv, this.YI);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class LoadAchievementsResultImpl extends b implements Achievements.LoadAchievementsResult {
        private final AchievementBuffer YJ;

        LoadAchievementsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YJ = new AchievementBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
        public AchievementBuffer getAchievements() {
            return this.YJ;
        }
    }

    private static final class LoadAclResultImpl extends b implements Acls.LoadAclResult {
        LoadAclResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadAppContentsResultImpl extends b implements AppContents.LoadAppContentResult {
        private final ArrayList<DataHolder> YK;

        LoadAppContentsResultImpl(DataHolder[] appContentData) {
            super(appContentData[0]);
            this.YK = new ArrayList<>(Arrays.asList(appContentData));
        }
    }

    private static final class LoadEventResultImpl extends b implements Events.LoadEventsResult {
        private final EventBuffer YL;

        LoadEventResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YL = new EventBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.event.Events.LoadEventsResult
        public EventBuffer getEvents() {
            return this.YL;
        }
    }

    private static final class LoadExtendedGamesResultImpl extends b implements GamesMetadata.LoadExtendedGamesResult {
        private final ExtendedGameBuffer YM;

        LoadExtendedGamesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YM = new ExtendedGameBuffer(dataHolder);
        }
    }

    private static final class LoadGameInstancesResultImpl extends b implements GamesMetadata.LoadGameInstancesResult {
        private final GameInstanceBuffer YN;

        LoadGameInstancesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YN = new GameInstanceBuffer(dataHolder);
        }
    }

    private static final class LoadGameSearchSuggestionsResultImpl extends b implements GamesMetadata.LoadGameSearchSuggestionsResult {
        private final GameSearchSuggestionBuffer YO;

        LoadGameSearchSuggestionsResultImpl(DataHolder data) {
            super(data);
            this.YO = new GameSearchSuggestionBuffer(data);
        }
    }

    private static final class LoadGamesResultImpl extends b implements GamesMetadata.LoadGamesResult {
        private final GameBuffer YP;

        LoadGamesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YP = new GameBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.GamesMetadata.LoadGamesResult
        public GameBuffer getGames() {
            return this.YP;
        }
    }

    private static final class LoadInvitationsResultImpl extends b implements Invitations.LoadInvitationsResult {
        private final InvitationBuffer YQ;

        LoadInvitationsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YQ = new InvitationBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult
        public InvitationBuffer getInvitations() {
            return this.YQ;
        }
    }

    private static final class LoadMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.LoadMatchResult {
        LoadMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadMatchesResultImpl implements TurnBasedMultiplayer.LoadMatchesResult {
        private final Status Eb;
        private final LoadMatchesResponse YR;

        LoadMatchesResultImpl(Status status, Bundle matchData) {
            this.Eb = status;
            this.YR = new LoadMatchesResponse(matchData);
        }

        @Override // com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
        public LoadMatchesResponse getMatches() {
            return this.YR;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            this.YR.release();
        }
    }

    private static final class LoadOwnerCoverPhotoUrisResultImpl implements Players.LoadOwnerCoverPhotoUrisResult {
        private final Bundle DJ;
        private final Status Eb;

        LoadOwnerCoverPhotoUrisResultImpl(int statusCode, Bundle bundle) {
            this.Eb = new Status(statusCode);
            this.DJ = bundle;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class LoadPlayerScoreResultImpl extends b implements Leaderboards.LoadPlayerScoreResult {
        private final LeaderboardScoreEntity YS;

        LoadPlayerScoreResultImpl(DataHolder scoreHolder) {
            super(scoreHolder);
            LeaderboardScoreBuffer leaderboardScoreBuffer = new LeaderboardScoreBuffer(scoreHolder);
            try {
                if (leaderboardScoreBuffer.getCount() > 0) {
                    this.YS = (LeaderboardScoreEntity) leaderboardScoreBuffer.get(0).freeze();
                } else {
                    this.YS = null;
                }
            } finally {
                leaderboardScoreBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult
        public LeaderboardScore getScore() {
            return this.YS;
        }
    }

    private static final class LoadPlayersResultImpl extends b implements Players.LoadPlayersResult {
        private final PlayerBuffer YT;

        LoadPlayersResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.YT = new PlayerBuffer(dataHolder);
        }

        @Override // com.google.android.gms.games.Players.LoadPlayersResult
        public PlayerBuffer getPlayers() {
            return this.YT;
        }
    }

    private static final class LoadProfileSettingsResultImpl extends b implements Players.LoadProfileSettingsResult {
        private final boolean YU;
        private final boolean Yh;

        LoadProfileSettingsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            try {
                if (dataHolder.getCount() > 0) {
                    int iAu = dataHolder.au(0);
                    this.Yh = dataHolder.d("profile_visible", 0, iAu);
                    this.YU = dataHolder.d("profile_visibility_explicitly_set", 0, iAu);
                } else {
                    this.Yh = true;
                    this.YU = false;
                }
            } finally {
                dataHolder.close();
            }
        }

        @Override // com.google.android.gms.common.api.b, com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.games.Players.LoadProfileSettingsResult
        public boolean isProfileVisible() {
            return this.Yh;
        }

        @Override // com.google.android.gms.games.Players.LoadProfileSettingsResult
        public boolean isVisibilityExplicitlySet() {
            return this.YU;
        }
    }

    private static final class LoadQuestsResultImpl extends b implements Quests.LoadQuestsResult {
        private final DataHolder JG;

        LoadQuestsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.JG = dataHolder;
        }

        @Override // com.google.android.gms.games.quest.Quests.LoadQuestsResult
        public QuestBuffer getQuests() {
            return new QuestBuffer(this.JG);
        }
    }

    private static final class LoadRequestSummariesResultImpl extends b implements Requests.LoadRequestSummariesResult {
        LoadRequestSummariesResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class LoadRequestsResultImpl implements Requests.LoadRequestsResult {
        private final Status Eb;
        private final Bundle YV;

        LoadRequestsResultImpl(Status status, Bundle requestData) {
            this.Eb = status;
            this.YV = requestData;
        }

        @Override // com.google.android.gms.games.request.Requests.LoadRequestsResult
        public GameRequestBuffer getRequests(int requestType) {
            String strDZ = RequestType.dZ(requestType);
            if (this.YV.containsKey(strDZ)) {
                return new GameRequestBuffer((DataHolder) this.YV.get(strDZ));
            }
            return null;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            Iterator<String> it = this.YV.keySet().iterator();
            while (it.hasNext()) {
                DataHolder dataHolder = (DataHolder) this.YV.getParcelable(it.next());
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }

    private static final class LoadScoresResultImpl extends b implements Leaderboards.LoadScoresResult {
        private final LeaderboardEntity YW;
        private final LeaderboardScoreBuffer YX;

        LoadScoresResultImpl(DataHolder leaderboard, DataHolder scores) {
            super(scores);
            LeaderboardBuffer leaderboardBuffer = new LeaderboardBuffer(leaderboard);
            try {
                if (leaderboardBuffer.getCount() > 0) {
                    this.YW = (LeaderboardEntity) leaderboardBuffer.get(0).freeze();
                } else {
                    this.YW = null;
                }
                leaderboardBuffer.release();
                this.YX = new LeaderboardScoreBuffer(scores);
            } catch (Throwable th) {
                leaderboardBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
        public Leaderboard getLeaderboard() {
            return this.YW;
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
        public LeaderboardScoreBuffer getScores() {
            return this.YX;
        }
    }

    private static final class LoadSnapshotsResultImpl extends b implements Snapshots.LoadSnapshotsResult {
        LoadSnapshotsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult
        public SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.JG);
        }
    }

    private static final class LoadXpForGameCategoriesResultImpl implements Players.LoadXpForGameCategoriesResult {
        private final Status Eb;
        private final List<String> YY;
        private final Bundle YZ;

        LoadXpForGameCategoriesResultImpl(Status status, Bundle xpData) {
            this.Eb = status;
            this.YY = xpData.getStringArrayList("game_category_list");
            this.YZ = xpData;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class LoadXpStreamResultImpl extends b implements Players.LoadXpStreamResult {
        private final ExperienceEventBuffer Za;

        LoadXpStreamResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.Za = new ExperienceEventBuffer(dataHolder);
        }
    }

    private static final class MatchRemovedNotifier implements d.b<OnTurnBasedMatchUpdateReceivedListener> {
        private final String Zb;

        MatchRemovedNotifier(String matchId) {
            this.Zb = matchId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.Zb);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks {
        private final d<OnTurnBasedMatchUpdateReceivedListener> Qe;

        MatchUpdateReceivedBinderCallback(d<OnTurnBasedMatchUpdateReceivedListener> listener) {
            this.Qe = listener;
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onTurnBasedMatchRemoved(String matchId) {
            this.Qe.a(new MatchRemovedNotifier(matchId));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void t(DataHolder dataHolder) {
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                TurnBasedMatch turnBasedMatchFreeze = turnBasedMatchBuffer.getCount() > 0 ? turnBasedMatchBuffer.get(0).freeze() : null;
                turnBasedMatchBuffer.release();
                if (turnBasedMatchFreeze != null) {
                    this.Qe.a(new MatchUpdateReceivedNotifier(turnBasedMatchFreeze));
                }
            } catch (Throwable th) {
                turnBasedMatchBuffer.release();
                throw th;
            }
        }
    }

    private static final class MatchUpdateReceivedNotifier implements d.b<OnTurnBasedMatchUpdateReceivedListener> {
        private final TurnBasedMatch Zc;

        MatchUpdateReceivedNotifier(TurnBasedMatch match) {
            this.Zc = match;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.Zc);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class MessageReceivedNotifier implements d.b<RealTimeMessageReceivedListener> {
        private final RealTimeMessage Zd;

        MessageReceivedNotifier(RealTimeMessage message) {
            this.Zd = message;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            realTimeMessageReceivedListener.onRealTimeMessageReceived(this.Zd);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class NearbyPlayerDetectedNotifier implements d.b<OnNearbyPlayerDetectedListener> {
        private final Player Ze;

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnNearbyPlayerDetectedListener onNearbyPlayerDetectedListener) {
            onNearbyPlayerDetectedListener.a(this.Ze);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Acls.LoadAclResult> Ea;

        NotifyAclLoadedBinderCallback(BaseImplementation.b<Acls.LoadAclResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void C(DataHolder dataHolder) {
            this.Ea.b(new LoadAclResultImpl(dataHolder));
        }
    }

    private static final class NotifyAclUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Status> Ea;

        NotifyAclUpdatedBinderCallback(BaseImplementation.b<Status> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void dO(int i) {
            this.Ea.b(new Status(i));
        }
    }

    private static final class OpenSnapshotResultImpl extends b implements Snapshots.OpenSnapshotResult {
        private final Snapshot Zf;
        private final String Zg;
        private final Snapshot Zh;
        private final Contents Zi;
        private final SnapshotContents Zj;

        OpenSnapshotResultImpl(DataHolder dataHolder, Contents currentContents) {
            this(dataHolder, null, currentContents, null, null);
        }

        OpenSnapshotResultImpl(DataHolder metadataHolder, String conflictId, Contents currentContents, Contents conflictContents, Contents resolutionContents) {
            super(metadataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(metadataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() == 0) {
                    this.Zf = null;
                    this.Zh = null;
                } else if (snapshotMetadataBuffer.getCount() == 1) {
                    je.K(metadataHolder.getStatusCode() != 4004);
                    this.Zf = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContentsEntity(currentContents));
                    this.Zh = null;
                } else {
                    this.Zf = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContentsEntity(currentContents));
                    this.Zh = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(1)), new SnapshotContentsEntity(conflictContents));
                }
                snapshotMetadataBuffer.release();
                this.Zg = conflictId;
                this.Zi = resolutionContents;
                this.Zj = new SnapshotContentsEntity(resolutionContents);
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public String getConflictId() {
            return this.Zg;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public Snapshot getConflictingSnapshot() {
            return this.Zh;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public SnapshotContents getResolutionSnapshotContents() {
            return this.Zj;
        }

        @Override // com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult
        public Snapshot getSnapshot() {
            return this.Zf;
        }
    }

    private static final class OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> Ea;

        OwnerCoverPhotoUrisLoadedBinderCallback(BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void d(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.Ea.b(new LoadOwnerCoverPhotoUrisResultImpl(i, bundle));
        }
    }

    private static final class P2PConnectedNotifier implements d.b<RoomStatusUpdateListener> {
        private final String Zk;

        P2PConnectedNotifier(String participantId) {
            this.Zk = participantId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PConnected(this.Zk);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class P2PDisconnectedNotifier implements d.b<RoomStatusUpdateListener> {
        private final String Zk;

        P2PDisconnectedNotifier(String participantId) {
            this.Zk = participantId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(RoomStatusUpdateListener roomStatusUpdateListener) {
            roomStatusUpdateListener.onP2PDisconnected(this.Zk);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class PeerConnectedNotifier extends AbstractPeerStatusNotifier {
        PeerConnectedNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersConnected(room, arrayList);
        }
    }

    private static final class PeerDeclinedNotifier extends AbstractPeerStatusNotifier {
        PeerDeclinedNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerDeclined(room, arrayList);
        }
    }

    private static final class PeerDisconnectedNotifier extends AbstractPeerStatusNotifier {
        PeerDisconnectedNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersDisconnected(room, arrayList);
        }
    }

    private static final class PeerInvitedToRoomNotifier extends AbstractPeerStatusNotifier {
        PeerInvitedToRoomNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, arrayList);
        }
    }

    private static final class PeerJoinedRoomNotifier extends AbstractPeerStatusNotifier {
        PeerJoinedRoomNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerJoined(room, arrayList);
        }
    }

    private static final class PeerLeftRoomNotifier extends AbstractPeerStatusNotifier {
        PeerLeftRoomNotifier(DataHolder dataHolder, String[] participantIds) {
            super(dataHolder, participantIds);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractPeerStatusNotifier
        protected void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerLeft(room, arrayList);
        }
    }

    private static final class PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> Ea;

        PlayerLeaderboardScoreLoadedBinderCallback(BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void E(DataHolder dataHolder) {
            this.Ea.b(new LoadPlayerScoreResultImpl(dataHolder));
        }
    }

    private static final class PlayerXpForGameCategoriesLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Players.LoadXpForGameCategoriesResult> Ea;

        PlayerXpForGameCategoriesLoadedBinderCallback(BaseImplementation.b<Players.LoadXpForGameCategoriesResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void e(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.Ea.b(new LoadXpForGameCategoriesResultImpl(new Status(i), bundle));
        }
    }

    static final class PlayerXpStreamLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Players.LoadXpStreamResult> Ea;

        PlayerXpStreamLoadedBinderCallback(BaseImplementation.b<Players.LoadXpStreamResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void P(DataHolder dataHolder) {
            this.Ea.b(new LoadXpStreamResultImpl(dataHolder));
        }
    }

    private static final class PlayersLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Players.LoadPlayersResult> Ea;

        PlayersLoadedBinderCallback(BaseImplementation.b<Players.LoadPlayersResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void g(DataHolder dataHolder) {
            this.Ea.b(new LoadPlayersResultImpl(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void h(DataHolder dataHolder) {
            this.Ea.b(new LoadPlayersResultImpl(dataHolder));
        }
    }

    static final class ProfileSettingsLoadedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Players.LoadProfileSettingsResult> Ea;

        ProfileSettingsLoadedBinderCallback(BaseImplementation.b<Players.LoadProfileSettingsResult> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void Q(DataHolder dataHolder) {
            this.Ea.b(new LoadProfileSettingsResultImpl(dataHolder));
        }
    }

    private static final class ProfileSettingsUpdatedBinderCallback extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Status> Ea;

        ProfileSettingsUpdatedBinderCallback(BaseImplementation.b<Status> holder) {
            this.Ea = (BaseImplementation.b) jx.b(holder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void dQ(int i) {
            this.Ea.b(new Status(i));
        }
    }

    private static final class QuestAcceptedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Quests.AcceptQuestResult> Zl;

        public QuestAcceptedBinderCallbacks(BaseImplementation.b<Quests.AcceptQuestResult> resultHolder) {
            this.Zl = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void L(DataHolder dataHolder) {
            this.Zl.b(new AcceptQuestResultImpl(dataHolder));
        }
    }

    private static final class QuestCompletedNotifier implements d.b<QuestUpdateListener> {
        private final Quest Yw;

        QuestCompletedNotifier(Quest quest) {
            this.Yw = quest;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(QuestUpdateListener questUpdateListener) {
            questUpdateListener.onQuestCompleted(this.Yw);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class QuestMilestoneClaimBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Quests.ClaimMilestoneResult> Zm;
        private final String Zn;

        public QuestMilestoneClaimBinderCallbacks(BaseImplementation.b<Quests.ClaimMilestoneResult> resultHolder, String milestoneId) {
            this.Zm = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
            this.Zn = (String) jx.b(milestoneId, (Object) "MilestoneId must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void K(DataHolder dataHolder) {
            this.Zm.b(new ClaimMilestoneResultImpl(dataHolder, this.Zn));
        }
    }

    private static final class QuestUpdateBinderCallback extends AbstractGamesCallbacks {
        private final d<QuestUpdateListener> Qe;

        QuestUpdateBinderCallback(d<QuestUpdateListener> listener) {
            this.Qe = listener;
        }

        private Quest T(DataHolder dataHolder) {
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                return questBuffer.getCount() > 0 ? questBuffer.get(0).freeze() : null;
            } finally {
                questBuffer.release();
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void M(DataHolder dataHolder) {
            Quest questT = T(dataHolder);
            if (questT != null) {
                this.Qe.a(new QuestCompletedNotifier(questT));
            }
        }
    }

    private static final class QuestsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Quests.LoadQuestsResult> Zo;

        public QuestsLoadedBinderCallbacks(BaseImplementation.b<Quests.LoadQuestsResult> resultHolder) {
            this.Zo = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void O(DataHolder dataHolder) {
            this.Zo.b(new LoadQuestsResultImpl(dataHolder));
        }
    }

    private static final class RealTimeMessageSentNotifier implements d.b<RealTimeMultiplayer.ReliableMessageSentCallback> {
        private final int Iv;
        private final String Zp;
        private final int Zq;

        RealTimeMessageSentNotifier(int statusCode, int token, String recipientParticipantId) {
            this.Iv = statusCode;
            this.Zq = token;
            this.Zp = recipientParticipantId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.Iv, this.Zq, this.Zp);
            }
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks {
        final d<RealTimeMultiplayer.ReliableMessageSentCallback> Zr;

        public RealTimeReliableMessageBinderCallbacks(d<RealTimeMultiplayer.ReliableMessageSentCallback> messageSentCallbacks) {
            this.Zr = messageSentCallbacks;
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void b(int i, int i2, String str) {
            if (this.Zr != null) {
                this.Zr.a(new RealTimeMessageSentNotifier(i, i2, str));
            }
        }
    }

    private static final class RequestReceivedBinderCallback extends AbstractGamesCallbacks {
        private final d<OnRequestReceivedListener> Qe;

        RequestReceivedBinderCallback(d<OnRequestReceivedListener> listener) {
            this.Qe = listener;
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void o(DataHolder dataHolder) {
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            try {
                GameRequest gameRequestFreeze = gameRequestBuffer.getCount() > 0 ? gameRequestBuffer.get(0).freeze() : null;
                gameRequestBuffer.release();
                if (gameRequestFreeze != null) {
                    this.Qe.a(new RequestReceivedNotifier(gameRequestFreeze));
                }
            } catch (Throwable th) {
                gameRequestBuffer.release();
                throw th;
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onRequestRemoved(String requestId) {
            this.Qe.a(new RequestRemovedNotifier(requestId));
        }
    }

    private static final class RequestReceivedNotifier implements d.b<OnRequestReceivedListener> {
        private final GameRequest Zs;

        RequestReceivedNotifier(GameRequest request) {
            this.Zs = request;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.Zs);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class RequestRemovedNotifier implements d.b<OnRequestReceivedListener> {
        private final String Zt;

        RequestRemovedNotifier(String requestId) {
            this.Zt = requestId;
        }

        @Override // com.google.android.gms.common.api.d.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void c(OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.Zt);
        }

        @Override // com.google.android.gms.common.api.d.b
        public void gG() {
        }
    }

    private static final class RequestSentBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Requests.SendRequestResult> Zu;

        public RequestSentBinderCallbacks(BaseImplementation.b<Requests.SendRequestResult> resultHolder) {
            this.Zu = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void G(DataHolder dataHolder) {
            this.Zu.b(new SendRequestResultImpl(dataHolder));
        }
    }

    private static final class RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Requests.LoadRequestSummariesResult> Zv;

        public RequestSummariesLoadedBinderCallbacks(BaseImplementation.b<Requests.LoadRequestSummariesResult> resultHolder) {
            this.Zv = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void H(DataHolder dataHolder) {
            this.Zv.b(new LoadRequestSummariesResultImpl(dataHolder));
        }
    }

    private static final class RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Requests.LoadRequestsResult> Zw;

        public RequestsLoadedBinderCallbacks(BaseImplementation.b<Requests.LoadRequestsResult> resultHolder) {
            this.Zw = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void c(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.Zw.b(new LoadRequestsResultImpl(new Status(i), bundle));
        }
    }

    private static final class RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Requests.UpdateRequestsResult> Zx;

        public RequestsUpdatedBinderCallbacks(BaseImplementation.b<Requests.UpdateRequestsResult> resultHolder) {
            this.Zx = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void F(DataHolder dataHolder) {
            this.Zx.b(new UpdateRequestsResultImpl(dataHolder));
        }
    }

    private static final class RoomAutoMatchingNotifier extends AbstractRoomStatusNotifier {
        RoomAutoMatchingNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomStatusNotifier
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }

    private static final class RoomBinderCallbacks extends AbstractGamesCallbacks {
        private final d<RealTimeMessageReceivedListener> ZA;
        private final d<? extends RoomUpdateListener> Zy;
        private final d<? extends RoomStatusUpdateListener> Zz;

        public RoomBinderCallbacks(d<RoomUpdateListener> roomCallbacks) {
            this.Zy = (d) jx.b(roomCallbacks, "Callbacks must not be null");
            this.Zz = null;
            this.ZA = null;
        }

        public RoomBinderCallbacks(d<? extends RoomUpdateListener> roomCallbacks, d<? extends RoomStatusUpdateListener> roomStatusCallbacks, d<RealTimeMessageReceivedListener> realTimeMessageReceivedCallbacks) {
            this.Zy = (d) jx.b(roomCallbacks, "Callbacks must not be null");
            this.Zz = roomStatusCallbacks;
            this.ZA = realTimeMessageReceivedCallbacks;
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void A(DataHolder dataHolder) {
            if (this.Zz != null) {
                this.Zz.a(new DisconnectedFromRoomNotifier(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerInvitedToRoomNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void b(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerJoinedRoomNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void c(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerLeftRoomNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void d(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerDeclinedNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void e(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerConnectedNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void f(DataHolder dataHolder, String[] strArr) {
            if (this.Zz != null) {
                this.Zz.a(new PeerDisconnectedNotifier(dataHolder, strArr));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onLeftRoom(int statusCode, String externalRoomId) {
            this.Zy.a(new LeftRoomNotifier(statusCode, externalRoomId));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onP2PConnected(String participantId) {
            if (this.Zz != null) {
                this.Zz.a(new P2PConnectedNotifier(participantId));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onP2PDisconnected(String participantId) {
            if (this.Zz != null) {
                this.Zz.a(new P2PDisconnectedNotifier(participantId));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void onRealTimeMessageReceived(RealTimeMessage message) {
            if (this.ZA != null) {
                this.ZA.a(new MessageReceivedNotifier(message));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void u(DataHolder dataHolder) {
            this.Zy.a(new RoomCreatedNotifier(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void v(DataHolder dataHolder) {
            this.Zy.a(new JoinedRoomNotifier(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void w(DataHolder dataHolder) {
            if (this.Zz != null) {
                this.Zz.a(new RoomConnectingNotifier(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void x(DataHolder dataHolder) {
            if (this.Zz != null) {
                this.Zz.a(new RoomAutoMatchingNotifier(dataHolder));
            }
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void y(DataHolder dataHolder) {
            this.Zy.a(new RoomConnectedNotifier(dataHolder));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void z(DataHolder dataHolder) {
            if (this.Zz != null) {
                this.Zz.a(new ConnectedToRoomNotifier(dataHolder));
            }
        }
    }

    private static final class RoomConnectedNotifier extends AbstractRoomNotifier {
        RoomConnectedNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomNotifier
        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomConnected(i, room);
        }
    }

    private static final class RoomConnectingNotifier extends AbstractRoomStatusNotifier {
        RoomConnectingNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomStatusNotifier
        public void a(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }

    private static final class RoomCreatedNotifier extends AbstractRoomNotifier {
        public RoomCreatedNotifier(DataHolder dataHolder) {
            super(dataHolder);
        }

        @Override // com.google.android.gms.games.internal.GamesClientImpl.AbstractRoomNotifier
        public void a(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomCreated(i, room);
        }
    }

    private static final class SendRequestResultImpl extends b implements Requests.SendRequestResult {
        private final GameRequest Zs;

        SendRequestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    this.Zs = gameRequestBuffer.get(0).freeze();
                } else {
                    this.Zs = null;
                }
            } finally {
                gameRequestBuffer.release();
            }
        }
    }

    private static final class SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Status> Ea;

        public SignOutCompleteBinderCallbacks(BaseImplementation.b<Status> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void fK() {
            this.Ea.b(new Status(0));
        }
    }

    private static final class SnapshotCommittedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Snapshots.CommitSnapshotResult> ZB;

        public SnapshotCommittedBinderCallbacks(BaseImplementation.b<Snapshots.CommitSnapshotResult> resultHolder) {
            this.ZB = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void J(DataHolder dataHolder) {
            this.ZB.b(new CommitSnapshotResultImpl(dataHolder));
        }
    }

    static final class SnapshotDeletedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Snapshots.DeleteSnapshotResult> Ea;

        public SnapshotDeletedBinderCallbacks(BaseImplementation.b<Snapshots.DeleteSnapshotResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void i(int i, String str) {
            this.Ea.b(new DeleteSnapshotResultImpl(i, str));
        }
    }

    private static final class SnapshotOpenedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Snapshots.OpenSnapshotResult> ZC;

        public SnapshotOpenedBinderCallbacks(BaseImplementation.b<Snapshots.OpenSnapshotResult> resultHolder) {
            this.ZC = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(DataHolder dataHolder, Contents contents) {
            this.ZC.b(new OpenSnapshotResultImpl(dataHolder, contents));
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void a(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            this.ZC.b(new OpenSnapshotResultImpl(dataHolder, str, contents, contents2, contents3));
        }
    }

    private static final class SnapshotsLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Snapshots.LoadSnapshotsResult> ZD;

        public SnapshotsLoadedBinderCallbacks(BaseImplementation.b<Snapshots.LoadSnapshotsResult> resultHolder) {
            this.ZD = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void I(DataHolder dataHolder) {
            this.ZD.b(new LoadSnapshotsResultImpl(dataHolder));
        }
    }

    private static final class SubmitScoreBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<Leaderboards.SubmitScoreResult> Ea;

        public SubmitScoreBinderCallbacks(BaseImplementation.b<Leaderboards.SubmitScoreResult> resultHolder) {
            this.Ea = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void f(DataHolder dataHolder) {
            this.Ea.b(new SubmitScoreResultImpl(dataHolder));
        }
    }

    private static final class SubmitScoreResultImpl extends b implements Leaderboards.SubmitScoreResult {
        private final ScoreSubmissionData ZE;

        public SubmitScoreResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.ZE = new ScoreSubmissionData(dataHolder);
            } finally {
                dataHolder.close();
            }
        }

        @Override // com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
        public ScoreSubmissionData getScoreData() {
            return this.ZE;
        }
    }

    private static final class TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> ZF;

        public TurnBasedMatchCanceledBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> resultHolder) {
            this.ZF = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void h(int i, String str) {
            this.ZF.b(new CancelMatchResultImpl(new Status(i), str));
        }
    }

    private static final class TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> ZG;

        public TurnBasedMatchInitiatedBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> resultHolder) {
            this.ZG = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void q(DataHolder dataHolder) {
            this.ZG.b(new InitiateMatchResultImpl(dataHolder));
        }
    }

    private static final class TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> ZH;

        public TurnBasedMatchLeftBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> resultHolder) {
            this.ZH = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void s(DataHolder dataHolder) {
            this.ZH.b(new LeaveMatchResultImpl(dataHolder));
        }
    }

    private static final class TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> ZI;

        public TurnBasedMatchLoadedBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> resultHolder) {
            this.ZI = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void p(DataHolder dataHolder) {
            this.ZI.b(new LoadMatchResultImpl(dataHolder));
        }
    }

    private static abstract class TurnBasedMatchResult extends b {
        final TurnBasedMatch Zc;

        TurnBasedMatchResult(DataHolder dataHolder) {
            super(dataHolder);
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    this.Zc = turnBasedMatchBuffer.get(0).freeze();
                } else {
                    this.Zc = null;
                }
            } finally {
                turnBasedMatchBuffer.release();
            }
        }

        public TurnBasedMatch getMatch() {
            return this.Zc;
        }
    }

    private static final class TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> ZJ;

        public TurnBasedMatchUpdatedBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> resultHolder) {
            this.ZJ = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void r(DataHolder dataHolder) {
            this.ZJ.b(new UpdateMatchResultImpl(dataHolder));
        }
    }

    private static final class TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks {
        private final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> ZK;

        public TurnBasedMatchesLoadedBinderCallbacks(BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> resultHolder) {
            this.ZK = (BaseImplementation.b) jx.b(resultHolder, "Holder must not be null");
        }

        @Override // com.google.android.gms.games.internal.AbstractGamesCallbacks, com.google.android.gms.games.internal.IGamesCallbacks
        public void b(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.ZK.b(new LoadMatchesResultImpl(new Status(i), bundle));
        }
    }

    private static final class UpdateAchievementResultImpl implements Achievements.UpdateAchievementResult {
        private final Status Eb;
        private final String Xx;

        UpdateAchievementResultImpl(int statusCode, String achievementId) {
            this.Eb = new Status(statusCode);
            this.Xx = achievementId;
        }

        @Override // com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
        public String getAchievementId() {
            return this.Xx;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private static final class UpdateMatchResultImpl extends TurnBasedMatchResult implements TurnBasedMultiplayer.UpdateMatchResult {
        UpdateMatchResultImpl(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class UpdateRequestsResultImpl extends b implements Requests.UpdateRequestsResult {
        private final RequestUpdateOutcomes ZL;

        UpdateRequestsResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            this.ZL = RequestUpdateOutcomes.W(dataHolder);
        }

        @Override // com.google.android.gms.games.request.Requests.UpdateRequestsResult
        public Set<String> getRequestIds() {
            return this.ZL.getRequestIds();
        }

        @Override // com.google.android.gms.games.request.Requests.UpdateRequestsResult
        public int getRequestOutcome(String requestId) {
            return this.ZL.getRequestOutcome(requestId);
        }
    }

    public GamesClientImpl(Context context, Looper looper, String gamePackageName, String accountName, GoogleApiClient.ConnectionCallbacks connectedListener, GoogleApiClient.OnConnectionFailedListener connectionFailedListener, String[] scopes, int gravity, View gamesContentView, Games.GamesOptions options) {
        super(context, looper, connectedListener, connectionFailedListener, scopes);
        this.Yk = new EventIncrementManager() { // from class: com.google.android.gms.games.internal.GamesClientImpl.1
            @Override // com.google.android.gms.games.internal.events.EventIncrementManager
            public EventIncrementCache lF() {
                return GamesClientImpl.this.new GameClientEventIncrementCache();
            }
        };
        this.Yq = false;
        this.Yl = gamePackageName;
        this.DZ = (String) jx.i(accountName);
        this.Yr = new Binder();
        this.Ym = new HashMap();
        this.Yp = PopupManager.a(this, gravity);
        k(gamesContentView);
        this.Ys = hashCode();
        this.Yt = options;
        registerConnectionCallbacks(this);
        registerConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Room R(DataHolder dataHolder) {
        RoomBuffer roomBuffer = new RoomBuffer(dataHolder);
        try {
            return roomBuffer.getCount() > 0 ? roomBuffer.get(0).freeze() : null;
        } finally {
            roomBuffer.release();
        }
    }

    private RealTimeSocket bB(String str) {
        RealTimeSocket realTimeSocketBD = ll.ii() ? bD(str) : bC(str);
        if (realTimeSocketBD != null) {
            this.Ym.put(str, realTimeSocketBD);
        }
        return realTimeSocketBD;
    }

    private RealTimeSocket bC(String str) {
        try {
            String strBH = hw().bH(str);
            if (strBH == null) {
                return null;
            }
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress(strBH));
            return new RealTimeSocketImpl(localSocket, str);
        } catch (RemoteException e) {
            GamesLog.p("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        } catch (IOException e2) {
            GamesLog.p("GamesClientImpl", "connect() call failed on socket: " + e2.getMessage());
            return null;
        }
    }

    private RealTimeSocket bD(String str) {
        LibjingleNativeSocket libjingleNativeSocket;
        try {
            ParcelFileDescriptor parcelFileDescriptorBN = hw().bN(str);
            if (parcelFileDescriptorBN != null) {
                GamesLog.n("GamesClientImpl", "Created native libjingle socket.");
                libjingleNativeSocket = new LibjingleNativeSocket(parcelFileDescriptorBN);
            } else {
                GamesLog.p("GamesClientImpl", "Unable to create socket for " + str);
                libjingleNativeSocket = null;
            }
            return libjingleNativeSocket;
        } catch (RemoteException e) {
            GamesLog.p("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
    }

    private void lD() {
        Iterator<RealTimeSocket> it = this.Ym.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException e) {
                GamesLog.c("GamesClientImpl", "IOException:", e);
            }
        }
        this.Ym.clear();
    }

    private void lg() {
        this.Yn = null;
    }

    public int a(d<RealTimeMultiplayer.ReliableMessageSentCallback> dVar, byte[] bArr, String str, String str2) {
        try {
            return hw().a(new RealTimeReliableMessageBinderCallbacks(dVar), bArr, str, str2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int a(byte[] bArr, String str, String[] strArr) {
        jx.b(strArr, "Participant IDs must not be null");
        try {
            return hw().b(bArr, str, strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public Intent a(int i, int i2, boolean z) {
        try {
            return hw().a(i, i2, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(int i, byte[] bArr, int i2, Bitmap bitmap, String str) {
        try {
            Intent intentA = hw().a(i, bArr, i2, str);
            jx.b(bitmap, "Must provide a non null icon");
            intentA.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
            return intentA;
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(Room room, int i) {
        try {
            return hw().a((RoomEntity) room.freeze(), i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent a(String str, boolean z, boolean z2, int i) {
        try {
            return hw().a(str, z, z2, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(int i, IBinder iBinder, Bundle bundle) {
        if (i == 0 && bundle != null) {
            this.Yq = bundle.getBoolean("show_welcome_popup");
        }
        super.a(i, iBinder, bundle);
    }

    public void a(IBinder iBinder, Bundle bundle) {
        if (isConnected()) {
            try {
                hw().a(iBinder, bundle);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
            }
        }
    }

    public void a(BaseImplementation.b<Requests.LoadRequestsResult> bVar, int i, int i2, int i3) {
        try {
            hw().a(new RequestsLoadedBinderCallbacks(bVar), i, i2, i3);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, int i, int i2, boolean z, boolean z2) {
        try {
            hw().a(new ExtendedGamesLoadedBinderCallback(bVar), i, i2, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<AppContents.LoadAppContentResult> bVar, int i, String str, String[] strArr, boolean z) {
        try {
            hw().a(new AppContentLoadedBinderCallbacks(bVar), i, str, strArr, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, int i, boolean z, boolean z2) {
        try {
            hw().a(new PlayersLoadedBinderCallback(bVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> bVar, int i, int[] iArr) {
        try {
            hw().a(new TurnBasedMatchesLoadedBinderCallbacks(bVar), i, iArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Leaderboards.LoadScoresResult> bVar, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        try {
            hw().a(new LeaderboardScoresLoadedBinderCallback(bVar), leaderboardScoreBuffer.mH().mI(), i, i2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> bVar, TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            hw().a(new TurnBasedMatchInitiatedBinderCallbacks(bVar), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.mO(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Snapshots.CommitSnapshotResult> bVar, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        jx.a(!snapshotContents.isClosed(), "Snapshot already closed");
        com.google.android.gms.common.data.a aVarMT = snapshotMetadataChange.mT();
        if (aVarMT != null) {
            aVarMT.a(getContext().getCacheDir());
        }
        Contents contentsIr = snapshotContents.ir();
        snapshotContents.close();
        try {
            hw().a(new SnapshotCommittedBinderCallbacks(bVar), snapshot.getMetadata().getSnapshotId(), (SnapshotMetadataChangeEntity) snapshotMetadataChange, contentsIr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Achievements.UpdateAchievementResult> bVar, String str) {
        AchievementUpdatedBinderCallback achievementUpdatedBinderCallback;
        if (bVar == null) {
            achievementUpdatedBinderCallback = null;
        } else {
            try {
                achievementUpdatedBinderCallback = new AchievementUpdatedBinderCallback(bVar);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
                return;
            }
        }
        hw().a(achievementUpdatedBinderCallback, str, this.Yp.lV(), this.Yp.lU());
    }

    public void a(BaseImplementation.b<Achievements.UpdateAchievementResult> bVar, String str, int i) {
        AchievementUpdatedBinderCallback achievementUpdatedBinderCallback;
        if (bVar == null) {
            achievementUpdatedBinderCallback = null;
        } else {
            try {
                achievementUpdatedBinderCallback = new AchievementUpdatedBinderCallback(bVar);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
                return;
            }
        }
        hw().a(achievementUpdatedBinderCallback, str, i, this.Yp.lV(), this.Yp.lU());
    }

    public void a(BaseImplementation.b<Leaderboards.LoadScoresResult> bVar, String str, int i, int i2, int i3, boolean z) {
        try {
            hw().a(new LeaderboardScoresLoadedBinderCallback(bVar), str, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, String str, int i, boolean z, boolean z2) {
        switch (str) {
            case "played_with":
                try {
                    hw().d(new PlayersLoadedBinderCallback(bVar), str, i, z, z2);
                    return;
                } catch (RemoteException e) {
                    GamesLog.o("GamesClientImpl", "service died");
                    return;
                }
            default:
                throw new IllegalArgumentException("Invalid player collection: " + str);
        }
    }

    public void a(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        try {
            hw().a(new ExtendedGamesLoadedBinderCallback(bVar), str, i, z, z2, z3, z4);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> bVar, String str, int i, int[] iArr) {
        try {
            hw().a(new TurnBasedMatchesLoadedBinderCallbacks(bVar), str, i, iArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Leaderboards.SubmitScoreResult> bVar, String str, long j, String str2) {
        SubmitScoreBinderCallbacks submitScoreBinderCallbacks;
        if (bVar == null) {
            submitScoreBinderCallbacks = null;
        } else {
            try {
                submitScoreBinderCallbacks = new SubmitScoreBinderCallbacks(bVar);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
                return;
            }
        }
        hw().a(submitScoreBinderCallbacks, str, j, str2);
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> bVar, String str, String str2) {
        try {
            hw().c(new TurnBasedMatchLeftBinderCallbacks(bVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> bVar, String str, String str2, int i, int i2) {
        try {
            hw().a(new PlayerLeaderboardScoreLoadedBinderCallback(bVar), str, str2, i, i2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Requests.LoadRequestsResult> bVar, String str, String str2, int i, int i2, int i3) {
        try {
            hw().a(new RequestsLoadedBinderCallbacks(bVar), str, str2, i, i2, i3);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Leaderboards.LoadScoresResult> bVar, String str, String str2, int i, int i2, int i3, boolean z) {
        try {
            hw().a(new LeaderboardScoresLoadedBinderCallback(bVar), str, str2, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, String str, String str2, int i, boolean z, boolean z2) {
        switch (str) {
            case "circled":
            case "played_with":
            case "nearby":
                try {
                    hw().a(new PlayersLoadedBinderCallback(bVar), str, str2, i, z, z2);
                    return;
                } catch (RemoteException e) {
                    GamesLog.o("GamesClientImpl", "service died");
                    return;
                }
            default:
                throw new IllegalArgumentException("Invalid player collection: " + str);
        }
    }

    public void a(BaseImplementation.b<Snapshots.OpenSnapshotResult> bVar, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) {
        jx.a(!snapshotContents.isClosed(), "SnapshotContents already closed");
        com.google.android.gms.common.data.a aVarMT = snapshotMetadataChange.mT();
        if (aVarMT != null) {
            aVarMT.a(getContext().getCacheDir());
        }
        Contents contentsIr = snapshotContents.ir();
        snapshotContents.close();
        try {
            hw().a(new SnapshotOpenedBinderCallbacks(bVar), str, str2, (SnapshotMetadataChangeEntity) snapshotMetadataChange, contentsIr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> bVar, String str, String str2, boolean z) {
        try {
            hw().b(new LeaderboardsLoadedBinderCallback(bVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Quests.LoadQuestsResult> bVar, String str, String str2, boolean z, String[] strArr) {
        try {
            this.Yk.flush();
            hw().a(new QuestsLoadedBinderCallbacks(bVar), str, str2, strArr, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Quests.LoadQuestsResult> bVar, String str, String str2, int[] iArr, int i, boolean z) {
        try {
            this.Yk.flush();
            hw().a(new QuestsLoadedBinderCallbacks(bVar), str, str2, iArr, i, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Requests.UpdateRequestsResult> bVar, String str, String str2, String[] strArr) {
        try {
            hw().a(new RequestsUpdatedBinderCallbacks(bVar), str, str2, strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, String str, boolean z) {
        try {
            hw().f(new PlayersLoadedBinderCallback(bVar), str, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> bVar, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) {
        try {
            hw().a(new TurnBasedMatchUpdatedBinderCallbacks(bVar), str, bArr, str2, participantResultArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> bVar, String str, byte[] bArr, ParticipantResult[] participantResultArr) {
        try {
            hw().a(new TurnBasedMatchUpdatedBinderCallbacks(bVar), str, bArr, participantResultArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Requests.SendRequestResult> bVar, String str, String[] strArr, int i, byte[] bArr, int i2) {
        try {
            hw().a(new RequestSentBinderCallbacks(bVar), str, strArr, i, bArr, i2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, boolean z) {
        try {
            hw().c(new PlayersLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Status> bVar, boolean z, Bundle bundle) {
        try {
            hw().a(new ContactSettingsUpdatedBinderCallback(bVar), z, bundle);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Events.LoadEventsResult> bVar, boolean z, String... strArr) {
        try {
            this.Yk.flush();
            hw().a(new EventsLoadedBinderCallback(bVar), z, strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Quests.LoadQuestsResult> bVar, int[] iArr, int i, boolean z) {
        try {
            this.Yk.flush();
            hw().a(new QuestsLoadedBinderCallbacks(bVar), iArr, i, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(BaseImplementation.b<Players.LoadPlayersResult> bVar, String[] strArr) {
        try {
            hw().c(new PlayersLoadedBinderCallback(bVar), strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(d<OnInvitationReceivedListener> dVar) {
        try {
            hw().a(new InvitationReceivedBinderCallback(dVar), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(d<RoomUpdateListener> dVar, d<RoomStatusUpdateListener> dVar2, d<RealTimeMessageReceivedListener> dVar3, RoomConfig roomConfig) {
        lD();
        try {
            hw().a(new RoomBinderCallbacks(dVar, dVar2, dVar3), this.Yr, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(d<RoomUpdateListener> dVar, String str) {
        try {
            hw().c(new RoomBinderCallbacks(dVar), str);
            lD();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void a(Snapshot snapshot) {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        jx.a(!snapshotContents.isClosed(), "Snapshot already closed");
        Contents contentsIr = snapshotContents.ir();
        snapshotContents.close();
        try {
            hw().a(contentsIr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        String string = getContext().getResources().getConfiguration().locale.toString();
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.Yt.Xa);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.Yt.Xb);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.Yt.Xc);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.Yt.Xd);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.Yt.Xe);
        bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.Yt.Xf);
        bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", this.Yt.Xg);
        jtVar.a(eVar, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE, getContext().getPackageName(), this.DZ, hv(), this.Yl, this.Yp.lV(), string, bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: aC, reason: merged with bridge method [inline-methods] */
    public IGamesService l(IBinder iBinder) {
        return IGamesService.Stub.aE(iBinder);
    }

    public Intent b(int i, int i2, boolean z) {
        try {
            return hw().b(i, i2, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent b(int[] iArr) {
        try {
            return hw().b(iArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public void b(BaseImplementation.b<Status> bVar) {
        try {
            this.Yk.flush();
            hw().a(new SignOutCompleteBinderCallbacks(bVar));
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Players.LoadPlayersResult> bVar, int i, boolean z, boolean z2) {
        try {
            hw().b(new PlayersLoadedBinderCallback(bVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Achievements.UpdateAchievementResult> bVar, String str) {
        AchievementUpdatedBinderCallback achievementUpdatedBinderCallback;
        if (bVar == null) {
            achievementUpdatedBinderCallback = null;
        } else {
            try {
                achievementUpdatedBinderCallback = new AchievementUpdatedBinderCallback(bVar);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
                return;
            }
        }
        hw().b(achievementUpdatedBinderCallback, str, this.Yp.lV(), this.Yp.lU());
    }

    public void b(BaseImplementation.b<Achievements.UpdateAchievementResult> bVar, String str, int i) {
        AchievementUpdatedBinderCallback achievementUpdatedBinderCallback;
        if (bVar == null) {
            achievementUpdatedBinderCallback = null;
        } else {
            try {
                achievementUpdatedBinderCallback = new AchievementUpdatedBinderCallback(bVar);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
                return;
            }
        }
        hw().b(achievementUpdatedBinderCallback, str, i, this.Yp.lV(), this.Yp.lU());
    }

    public void b(BaseImplementation.b<Leaderboards.LoadScoresResult> bVar, String str, int i, int i2, int i3, boolean z) {
        try {
            hw().b(new LeaderboardScoresLoadedBinderCallback(bVar), str, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str, int i, boolean z, boolean z2) {
        try {
            hw().a(new ExtendedGamesLoadedBinderCallback(bVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Quests.ClaimMilestoneResult> bVar, String str, String str2) {
        try {
            this.Yk.flush();
            hw().f(new QuestMilestoneClaimBinderCallbacks(bVar, str2), str, str2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Leaderboards.LoadScoresResult> bVar, String str, String str2, int i, int i2, int i3, boolean z) {
        try {
            hw().b(new LeaderboardScoresLoadedBinderCallback(bVar), str, str2, i, i2, i3, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Players.LoadPlayersResult> bVar, String str, String str2, int i, boolean z, boolean z2) {
        try {
            hw().b(new PlayersLoadedBinderCallback(bVar), str, str2, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Achievements.LoadAchievementsResult> bVar, String str, String str2, boolean z) {
        try {
            hw().a(new AchievementsLoadedBinderCallback(bVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> bVar, String str, boolean z) {
        try {
            hw().c(new LeaderboardsLoadedBinderCallback(bVar), str, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> bVar, boolean z) {
        try {
            hw().b(new LeaderboardsLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Quests.LoadQuestsResult> bVar, boolean z, String[] strArr) {
        try {
            this.Yk.flush();
            hw().a(new QuestsLoadedBinderCallbacks(bVar), strArr, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(BaseImplementation.b<Requests.UpdateRequestsResult> bVar, String[] strArr) {
        try {
            hw().a(new RequestsUpdatedBinderCallbacks(bVar), strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(d<OnTurnBasedMatchUpdateReceivedListener> dVar) {
        try {
            hw().b(new MatchUpdateReceivedBinderCallback(dVar), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void b(d<RoomUpdateListener> dVar, d<RoomStatusUpdateListener> dVar2, d<RealTimeMessageReceivedListener> dVar3, RoomConfig roomConfig) {
        lD();
        try {
            hw().a(new RoomBinderCallbacks(dVar, dVar2, dVar3), this.Yr, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void bA(String str) {
        try {
            hw().bL(str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public Intent bE(String str) {
        try {
            return hw().bE(str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public void bF(String str) {
        try {
            hw().a(str, this.Yp.lV(), this.Yp.lU());
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.games.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    public void c(BaseImplementation.b<Invitations.LoadInvitationsResult> bVar, int i) {
        try {
            hw().a((IGamesCallbacks) new InvitationsLoadedBinderCallback(bVar), i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Players.LoadPlayersResult> bVar, int i, boolean z, boolean z2) {
        try {
            hw().c(new PlayersLoadedBinderCallback(bVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> bVar, String str) {
        try {
            hw().l(new TurnBasedMatchInitiatedBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Players.LoadXpStreamResult> bVar, String str, int i) {
        try {
            hw().b(new PlayerXpStreamLoadedBinderCallback(bVar), str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str, int i, boolean z, boolean z2) {
        try {
            hw().e(new ExtendedGamesLoadedBinderCallback(bVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> bVar, String str, String str2) {
        try {
            hw().d(new TurnBasedMatchInitiatedBinderCallbacks(bVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Snapshots.LoadSnapshotsResult> bVar, String str, String str2, boolean z) {
        try {
            hw().c(new SnapshotsLoadedBinderCallbacks(bVar), str, str2, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Snapshots.OpenSnapshotResult> bVar, String str, boolean z) {
        try {
            hw().e(new SnapshotOpenedBinderCallbacks(bVar), str, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Achievements.LoadAchievementsResult> bVar, boolean z) {
        try {
            hw().a(new AchievementsLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(BaseImplementation.b<Requests.UpdateRequestsResult> bVar, String[] strArr) {
        try {
            hw().b(new RequestsUpdatedBinderCallbacks(bVar), strArr);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void c(d<QuestUpdateListener> dVar) {
        try {
            hw().d(new QuestUpdateBinderCallback(dVar), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void c(String... strArr) {
        boolean z = false;
        boolean z2 = false;
        for (String str : strArr) {
            if (str.equals(Scopes.GAMES)) {
                z2 = true;
            } else if (str.equals("https://www.googleapis.com/auth/games.firstparty")) {
                z = true;
            }
        }
        if (z) {
            jx.a(!z2, "Cannot have both %s and %s!", Scopes.GAMES, "https://www.googleapis.com/auth/games.firstparty");
        } else {
            jx.a(z2, "Games APIs requires %s to function.", Scopes.GAMES);
        }
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void connect() {
        lg();
        super.connect();
    }

    public int d(byte[] bArr, String str) {
        try {
            return hw().b(bArr, str, (String[]) null);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public void d(BaseImplementation.b<Players.LoadPlayersResult> bVar, int i, boolean z, boolean z2) {
        try {
            hw().e(new PlayersLoadedBinderCallback(bVar), i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> bVar, String str) {
        try {
            hw().m(new TurnBasedMatchInitiatedBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<Players.LoadXpStreamResult> bVar, String str, int i) {
        try {
            hw().c(new PlayerXpStreamLoadedBinderCallback(bVar), str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str, int i, boolean z, boolean z2) {
        try {
            hw().f(new ExtendedGamesLoadedBinderCallback(bVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> bVar, String str, String str2) {
        try {
            hw().e(new TurnBasedMatchInitiatedBinderCallbacks(bVar), str, str2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> bVar, String str, boolean z) {
        try {
            hw().d(new LeaderboardsLoadedBinderCallback(bVar), str, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(BaseImplementation.b<Events.LoadEventsResult> bVar, boolean z) {
        try {
            this.Yk.flush();
            hw().f(new EventsLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void d(d<OnRequestReceivedListener> dVar) {
        try {
            hw().c(new RequestReceivedBinderCallback(dVar), this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void dS(int i) {
        this.Yp.setGravity(i);
    }

    public void dT(int i) {
        try {
            hw().dT(i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.common.api.Api.a
    public void disconnect() {
        this.Yq = false;
        if (isConnected()) {
            try {
                IGamesService iGamesServiceHw = hw();
                iGamesServiceHw.lE();
                this.Yk.flush();
                iGamesServiceHw.q(this.Ys);
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        lD();
        super.disconnect();
    }

    public void e(BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> bVar, String str) {
        try {
            hw().o(new TurnBasedMatchLeftBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void e(BaseImplementation.b<Invitations.LoadInvitationsResult> bVar, String str, int i) {
        try {
            hw().b((IGamesCallbacks) new InvitationsLoadedBinderCallback(bVar), str, i, false);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void e(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str, int i, boolean z, boolean z2) {
        try {
            hw().c(new ExtendedGamesLoadedBinderCallback(bVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void e(BaseImplementation.b<Notifications.GameMuteStatusChangeResult> bVar, String str, boolean z) {
        try {
            hw().a(new GameMuteStatusChangedBinderCallback(bVar), str, z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void e(BaseImplementation.b<Snapshots.LoadSnapshotsResult> bVar, boolean z) {
        try {
            hw().d(new SnapshotsLoadedBinderCallbacks(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void f(BaseImplementation.b<GamesMetadata.LoadGamesResult> bVar) {
        try {
            hw().d(new GamesLoadedBinderCallback(bVar));
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void f(BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> bVar, String str) {
        try {
            hw().n(new TurnBasedMatchCanceledBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void f(BaseImplementation.b<Requests.LoadRequestSummariesResult> bVar, String str, int i) {
        try {
            hw().a((IGamesCallbacks) new RequestSummariesLoadedBinderCallbacks(bVar), str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void f(BaseImplementation.b<Players.LoadPlayersResult> bVar, String str, int i, boolean z, boolean z2) {
        try {
            hw().b(new PlayersLoadedBinderCallback(bVar), str, i, z, z2);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void f(BaseImplementation.b<Players.LoadProfileSettingsResult> bVar, boolean z) {
        try {
            hw().g(new ProfileSettingsLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    @Override // com.google.android.gms.internal.jl, com.google.android.gms.internal.jm.b
    public Bundle fX() {
        try {
            Bundle bundleFX = hw().fX();
            if (bundleFX == null) {
                return bundleFX;
            }
            bundleFX.setClassLoader(GamesClientImpl.class.getClassLoader());
            return bundleFX;
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public void g(BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> bVar) {
        try {
            hw().j(new OwnerCoverPhotoUrisLoadedBinderCallback(bVar));
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void g(BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> bVar, String str) {
        try {
            hw().p(new TurnBasedMatchLoadedBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void g(BaseImplementation.b<Status> bVar, boolean z) {
        try {
            hw().h(new ProfileSettingsUpdatedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void h(BaseImplementation.b<Acls.LoadAclResult> bVar) {
        try {
            hw().h(new NotifyAclLoadedBinderCallback(bVar));
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void h(BaseImplementation.b<Quests.AcceptQuestResult> bVar, String str) {
        try {
            this.Yk.flush();
            hw().u(new QuestAcceptedBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void h(BaseImplementation.b<Notifications.ContactSettingLoadResult> bVar, boolean z) {
        try {
            hw().e(new ContactSettingsLoadedBinderCallback(bVar), z);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void i(BaseImplementation.b<Notifications.InboxCountResult> bVar) {
        try {
            hw().t(new InboxCountsLoadedBinderCallback(bVar), (String) null);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void i(BaseImplementation.b<Snapshots.DeleteSnapshotResult> bVar, String str) {
        try {
            hw().r(new SnapshotDeletedBinderCallbacks(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void j(BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> bVar, String str) {
        try {
            hw().e(new ExtendedGamesLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void k(View view) {
        this.Yp.l(view);
    }

    public void k(BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> bVar, String str) {
        try {
            hw().f(new GameInstancesLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void l(BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> bVar, String str) {
        try {
            hw().q(new GameSearchSuggestionsLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public int lA() {
        try {
            return hw().lA();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int lB() {
        try {
            return hw().lB();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public int lC() {
        try {
            return hw().lC();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public void lE() {
        if (isConnected()) {
            try {
                hw().lE();
            } catch (RemoteException e) {
                GamesLog.o("GamesClientImpl", "service died");
            }
        }
    }

    public String lh() {
        try {
            return hw().lh();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public String li() {
        try {
            return hw().li();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Player lj() {
        dS();
        synchronized (this) {
            if (this.Yn == null) {
                try {
                    PlayerBuffer playerBuffer = new PlayerBuffer(hw().lG());
                    try {
                        if (playerBuffer.getCount() > 0) {
                            this.Yn = (PlayerEntity) playerBuffer.get(0).freeze();
                        }
                        playerBuffer.release();
                    } catch (Throwable th) {
                        playerBuffer.release();
                        throw th;
                    }
                } catch (RemoteException e) {
                    GamesLog.o("GamesClientImpl", "service died");
                }
            }
        }
        return this.Yn;
    }

    public Game lk() {
        dS();
        synchronized (this) {
            if (this.Yo == null) {
                try {
                    GameBuffer gameBuffer = new GameBuffer(hw().lI());
                    try {
                        if (gameBuffer.getCount() > 0) {
                            this.Yo = (GameEntity) gameBuffer.get(0).freeze();
                        }
                        gameBuffer.release();
                    } catch (Throwable th) {
                        gameBuffer.release();
                        throw th;
                    }
                } catch (RemoteException e) {
                    GamesLog.o("GamesClientImpl", "service died");
                }
            }
        }
        return this.Yo;
    }

    public Intent ll() {
        try {
            return hw().ll();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent lm() {
        try {
            return hw().lm();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent ln() {
        try {
            return hw().ln();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent lo() {
        try {
            return hw().lo();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public void lp() {
        try {
            hw().r(this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void lq() {
        try {
            hw().s(this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void lr() {
        try {
            hw().u(this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void ls() {
        try {
            hw().t(this.Ys);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public Intent lt() {
        try {
            return hw().lt();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public Intent lu() {
        try {
            return hw().lu();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public int lv() {
        try {
            return hw().lv();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return 4368;
        }
    }

    public String lw() {
        try {
            return hw().lw();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public int lx() {
        try {
            return hw().lx();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public Intent ly() {
        try {
            return hw().ly();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public int lz() {
        try {
            return hw().lz();
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return -1;
        }
    }

    public void m(BaseImplementation.b<Players.LoadXpForGameCategoriesResult> bVar, String str) {
        try {
            hw().s(new PlayerXpForGameCategoriesLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public Intent n(String str, int i) {
        try {
            return hw().u(str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
            return null;
        }
    }

    public void n(BaseImplementation.b<Invitations.LoadInvitationsResult> bVar, String str) {
        try {
            hw().k(new InvitationsLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void o(BaseImplementation.b<Status> bVar, String str) {
        try {
            hw().j(new NotifyAclUpdatedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void o(String str, int i) {
        this.Yk.o(str, i);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle connectionHint) {
        if (this.Yq) {
            this.Yp.lT();
            this.Yq = false;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult result) {
        this.Yq = false;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int cause) {
    }

    public void p(BaseImplementation.b<Notifications.GameMuteStatusLoadResult> bVar, String str) {
        try {
            hw().i(new GameMuteStatusLoadedBinderCallback(bVar), str);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void p(String str, int i) {
        try {
            hw().p(str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public void q(String str, int i) {
        try {
            hw().q(str, i);
        } catch (RemoteException e) {
            GamesLog.o("GamesClientImpl", "service died");
        }
    }

    public RealTimeSocket s(String str, String str2) {
        if (str2 == null || !ParticipantUtils.bY(str2)) {
            throw new IllegalArgumentException("Bad participant ID");
        }
        RealTimeSocket realTimeSocket = this.Ym.get(str2);
        return (realTimeSocket == null || realTimeSocket.isClosed()) ? bB(str2) : realTimeSocket;
    }
}
