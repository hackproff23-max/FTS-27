package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.panorama.Panorama;
import com.google.android.gms.panorama.PanoramaApi;

/* JADX INFO: loaded from: classes.dex */
public class ol implements PanoramaApi {

    private static abstract class a extends c<PanoramaApi.PanoramaResult> {
        public a(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: ay, reason: merged with bridge method [inline-methods] */
        public PanoramaApi.PanoramaResult c(Status status) {
            return new on(status, null);
        }
    }

    private static final class b extends oj.a {
        private final BaseImplementation.b<PanoramaApi.PanoramaResult> Ea;

        public b(BaseImplementation.b<PanoramaApi.PanoramaResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.internal.oj
        public void a(int i, Bundle bundle, int i2, Intent intent) {
            this.Ea.b(new on(new Status(i, null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null), intent));
        }
    }

    private static abstract class c<R extends Result> extends BaseImplementation.a<R, om> {
        protected c(GoogleApiClient googleApiClient) {
            super(Panorama.DQ, googleApiClient);
        }

        protected abstract void a(Context context, ok okVar) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public final void a(om omVar) throws RemoteException {
            a(omVar.getContext(), omVar.hw());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Context context, Uri uri) {
        context.revokeUriPermission(uri, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final Context context, ok okVar, final oj ojVar, final Uri uri, Bundle bundle) throws RemoteException {
        context.grantUriPermission(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE, uri, 1);
        try {
            okVar.a(new oj.a() { // from class: com.google.android.gms.internal.ol.3
                @Override // com.google.android.gms.internal.oj
                public void a(int i, Bundle bundle2, int i2, Intent intent) throws RemoteException {
                    ol.a(context, uri);
                    ojVar.a(i, bundle2, i2, intent);
                }
            }, uri, bundle, true);
        } catch (RemoteException e) {
            a(context, uri);
            throw e;
        } catch (RuntimeException e2) {
            a(context, uri);
            throw e2;
        }
    }

    @Override // com.google.android.gms.panorama.PanoramaApi
    public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfo(GoogleApiClient client, final Uri uri) {
        return client.a(new a(client) { // from class: com.google.android.gms.internal.ol.1
            @Override // com.google.android.gms.internal.ol.c
            protected void a(Context context, ok okVar) throws RemoteException {
                okVar.a(new b(this), uri, null, false);
            }
        });
    }

    @Override // com.google.android.gms.panorama.PanoramaApi
    public PendingResult<PanoramaApi.PanoramaResult> loadPanoramaInfoAndGrantAccess(GoogleApiClient client, final Uri uri) {
        return client.a(new a(client) { // from class: com.google.android.gms.internal.ol.2
            @Override // com.google.android.gms.internal.ol.c
            protected void a(Context context, ok okVar) throws RemoteException {
                ol.a(context, okVar, new b(this), uri, null);
            }
        });
    }
}
