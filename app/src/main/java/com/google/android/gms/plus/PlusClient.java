package com.google.android.gms.plus;

import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class PlusClient implements GooglePlayServicesClient {
    final e ant;

    @Deprecated
    public interface OnAccessRevokedListener {
        void onAccessRevoked(ConnectionResult connectionResult);
    }

    @Deprecated
    public interface OnMomentsLoadedListener {
        @Deprecated
        void onMomentsLoaded(ConnectionResult connectionResult, MomentBuffer momentBuffer, String str, String str2);
    }

    @Deprecated
    public interface OnPeopleLoadedListener {
        void onPeopleLoaded(ConnectionResult connectionResult, PersonBuffer personBuffer, String str);
    }

    @Deprecated
    public void clearDefaultAccount() {
        this.ant.clearDefaultAccount();
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void connect() {
        this.ant.connect();
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void disconnect() {
        this.ant.disconnect();
    }

    @Deprecated
    public String getAccountName() {
        return this.ant.getAccountName();
    }

    @Deprecated
    public Person getCurrentPerson() {
        return this.ant.getCurrentPerson();
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public boolean isConnected() {
        return this.ant.isConnected();
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public boolean isConnecting() {
        return this.ant.isConnecting();
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public boolean isConnectionCallbacksRegistered(GooglePlayServicesClient.ConnectionCallbacks listener) {
        return this.ant.isConnectionCallbacksRegistered(listener);
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public boolean isConnectionFailedListenerRegistered(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        return this.ant.isConnectionFailedListenerRegistered(listener);
    }

    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener listener) {
        this.ant.j(new BaseImplementation.b<Moments.LoadMomentsResult>() { // from class: com.google.android.gms.plus.PlusClient.1
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(Moments.LoadMomentsResult loadMomentsResult) {
                listener.onMomentsLoaded(loadMomentsResult.getStatus().gQ(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        });
    }

    @Deprecated
    public void loadMoments(final OnMomentsLoadedListener listener, int maxResults, String pageToken, Uri targetUrl, String type, String userId) {
        this.ant.a(new BaseImplementation.b<Moments.LoadMomentsResult>() { // from class: com.google.android.gms.plus.PlusClient.2
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(Moments.LoadMomentsResult loadMomentsResult) {
                listener.onMomentsLoaded(loadMomentsResult.getStatus().gQ(), loadMomentsResult.getMomentBuffer(), loadMomentsResult.getNextPageToken(), loadMomentsResult.getUpdated());
            }
        }, maxResults, pageToken, targetUrl, type, userId);
    }

    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener listener, Collection<String> personIds) {
        this.ant.a(new BaseImplementation.b<People.LoadPeopleResult>() { // from class: com.google.android.gms.plus.PlusClient.5
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().gQ(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, personIds);
    }

    @Deprecated
    public void loadPeople(final OnPeopleLoadedListener listener, String... personIds) {
        this.ant.d(new BaseImplementation.b<People.LoadPeopleResult>() { // from class: com.google.android.gms.plus.PlusClient.6
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().gQ(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, personIds);
    }

    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener listener, int orderBy, String pageToken) {
        this.ant.a(new BaseImplementation.b<People.LoadPeopleResult>() { // from class: com.google.android.gms.plus.PlusClient.3
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().gQ(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, orderBy, pageToken);
    }

    @Deprecated
    public void loadVisiblePeople(final OnPeopleLoadedListener listener, String pageToken) {
        this.ant.q(new BaseImplementation.b<People.LoadPeopleResult>() { // from class: com.google.android.gms.plus.PlusClient.4
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void b(People.LoadPeopleResult loadPeopleResult) {
                listener.onPeopleLoaded(loadPeopleResult.getStatus().gQ(), loadPeopleResult.getPersonBuffer(), loadPeopleResult.getNextPageToken());
            }
        }, pageToken);
    }

    e os() {
        return this.ant;
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void registerConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.ant.registerConnectionCallbacks(listener);
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void registerConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.ant.registerConnectionFailedListener(listener);
    }

    @Deprecated
    public void removeMoment(String momentId) {
        this.ant.removeMoment(momentId);
    }

    @Deprecated
    public void revokeAccessAndDisconnect(final OnAccessRevokedListener listener) {
        this.ant.l(new BaseImplementation.b<Status>() { // from class: com.google.android.gms.plus.PlusClient.7
            @Override // com.google.android.gms.common.api.BaseImplementation.b
            /* JADX INFO: renamed from: az, reason: merged with bridge method [inline-methods] */
            public void b(Status status) {
                listener.onAccessRevoked(status.getStatus().gQ());
            }
        });
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void unregisterConnectionCallbacks(GooglePlayServicesClient.ConnectionCallbacks listener) {
        this.ant.unregisterConnectionCallbacks(listener);
    }

    @Override // com.google.android.gms.common.GooglePlayServicesClient
    @Deprecated
    public void unregisterConnectionFailedListener(GooglePlayServicesClient.OnConnectionFailedListener listener) {
        this.ant.unregisterConnectionFailedListener(listener);
    }

    @Deprecated
    public void writeMoment(Moment moment) {
        this.ant.a((BaseImplementation.b<Status>) null, moment);
    }
}
