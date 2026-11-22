package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class pb implements Moments {

    private static abstract class a extends Plus.a<Moments.LoadMomentsResult> {
        private a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: aB, reason: merged with bridge method [inline-methods] */
        public Moments.LoadMomentsResult c(final Status status) {
            return new Moments.LoadMomentsResult() { // from class: com.google.android.gms.internal.pb.a.1
                @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
                public MomentBuffer getMomentBuffer() {
                    return null;
                }

                @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
                public String getNextPageToken() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }

                @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
                public String getUpdated() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Releasable
                public void release() {
                }
            };
        }
    }

    private static abstract class b extends Plus.a<Status> {
        private b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static abstract class c extends Plus.a<Status> {
        private c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    @Override // com.google.android.gms.plus.Moments
    public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient googleApiClient) {
        return googleApiClient.a(new a(googleApiClient) { // from class: com.google.android.gms.internal.pb.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(com.google.android.gms.plus.internal.e eVar) {
                eVar.j(this);
            }
        });
    }

    @Override // com.google.android.gms.plus.Moments
    public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient googleApiClient, final int maxResults, final String pageToken, final Uri targetUrl, final String type, final String userId) {
        return googleApiClient.a(new a(googleApiClient) { // from class: com.google.android.gms.internal.pb.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(com.google.android.gms.plus.internal.e eVar) {
                eVar.a(this, maxResults, pageToken, targetUrl, type, userId);
            }
        });
    }

    @Override // com.google.android.gms.plus.Moments
    public PendingResult<Status> remove(GoogleApiClient googleApiClient, final String momentId) {
        return googleApiClient.b(new b(googleApiClient) { // from class: com.google.android.gms.internal.pb.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(com.google.android.gms.plus.internal.e eVar) {
                eVar.removeMoment(momentId);
                b(Status.Kw);
            }
        });
    }

    @Override // com.google.android.gms.plus.Moments
    public PendingResult<Status> write(GoogleApiClient googleApiClient, final Moment moment) {
        return googleApiClient.b(new c(googleApiClient) { // from class: com.google.android.gms.internal.pb.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(com.google.android.gms.plus.internal.e eVar) {
                eVar.a(this, moment);
            }
        });
    }
}
