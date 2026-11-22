package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class hz implements AppIndexApi, ht {

    private static final class a implements AppIndexApi.ActionResult {
        private hz DE;
        private PendingResult<Status> DF;
        private Action DG;
        private String DH;

        a(hz hzVar, PendingResult<Status> pendingResult, Action action, String str) {
            this.DE = hzVar;
            this.DF = pendingResult;
            this.DG = action;
            this.DH = str;
        }

        @Override // com.google.android.gms.appindexing.AppIndexApi.ActionResult
        public PendingResult<Status> end(GoogleApiClient apiClient) {
            return this.DE.a(apiClient, hy.a(this.DG, this.DH, System.currentTimeMillis(), ((hx) apiClient.a(hc.CG)).getContext().getPackageName(), 3));
        }

        @Override // com.google.android.gms.appindexing.AppIndexApi.ActionResult
        public PendingResult<Status> getPendingResult() {
            return this.DF;
        }
    }

    private static abstract class b<T extends Result> extends BaseImplementation.a<T, hx> {
        public b(GoogleApiClient googleApiClient) {
            super(hc.CG, googleApiClient);
        }

        protected abstract void a(hu huVar) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.a
        public final void a(hx hxVar) throws RemoteException {
            a(hxVar.fH());
        }
    }

    private static abstract class c<T extends Result> extends b<Status> {
        c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Status c(Status status) {
            return status;
        }
    }

    private static final class d extends hw<Status> {
        public d(BaseImplementation.b<Status> bVar) {
            super(bVar);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // com.google.android.gms.internal.hw, com.google.android.gms.internal.hv
        public void a(Status status) {
            this.DA.b((T) status);
        }
    }

    public static Intent a(String str, Uri uri) {
        b(str, uri);
        List<String> pathSegments = uri.getPathSegments();
        String str2 = pathSegments.get(0);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(str2);
        if (pathSegments.size() > 1) {
            builder.authority(pathSegments.get(1));
            int i = 2;
            while (true) {
                int i2 = i;
                if (i2 >= pathSegments.size()) {
                    break;
                }
                builder.appendPath(pathSegments.get(i2));
                i = i2 + 1;
            }
        }
        builder.encodedQuery(uri.getEncodedQuery());
        builder.encodedFragment(uri.getEncodedFragment());
        return new Intent("android.intent.action.VIEW", builder.build());
    }

    private static void b(String str, Uri uri) {
        if (!"android-app".equals(uri.getScheme())) {
            throw new IllegalArgumentException("AppIndex: The URI scheme must be 'android-app' and follow the format (android-app://<package_name>/<scheme>/[host_path]). Provided URI: " + uri);
        }
        String host = uri.getHost();
        if (str != null && !str.equals(host)) {
            throw new IllegalArgumentException("AppIndex: The URI host must match the package name and follow the format (android-app://<package_name>/<scheme>/[host_path]). Provided URI: " + uri);
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.isEmpty() || pathSegments.get(0).isEmpty()) {
            throw new IllegalArgumentException("AppIndex: The app URI scheme must exist and follow the format android-app://<package_name>/<scheme>/[host_path]). Provided URI: " + uri);
        }
    }

    public static void c(List<AppIndexApi.AppIndexingLink> list) {
        if (list == null) {
            return;
        }
        Iterator<AppIndexApi.AppIndexingLink> it = list.iterator();
        while (it.hasNext()) {
            b(null, it.next().appIndexingUrl);
        }
    }

    public PendingResult<Status> a(GoogleApiClient googleApiClient, final hr... hrVarArr) {
        final String packageName = ((hx) googleApiClient.a(hc.CG)).getContext().getPackageName();
        return googleApiClient.a(new c<Status>(googleApiClient) { // from class: com.google.android.gms.internal.hz.1
            @Override // com.google.android.gms.internal.hz.b
            protected void a(hu huVar) throws RemoteException {
                huVar.a(new d(this), packageName, hrVarArr);
            }
        });
    }

    @Override // com.google.android.gms.appindexing.AppIndexApi
    public AppIndexApi.ActionResult action(GoogleApiClient apiClient, Action action) {
        String packageName = ((hx) apiClient.a(hc.CG)).getContext().getPackageName();
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strValueOf = String.valueOf(jCurrentTimeMillis);
        return new a(this, a(apiClient, hy.a(action, strValueOf, jCurrentTimeMillis, packageName, 0)), action, strValueOf);
    }

    @Override // com.google.android.gms.appindexing.AppIndexApi
    public PendingResult<Status> view(GoogleApiClient apiClient, Activity activity, Intent viewIntent, String title, Uri webUrl, List<AppIndexApi.AppIndexingLink> outLinks) {
        String packageName = ((hx) apiClient.a(hc.CG)).getContext().getPackageName();
        c(outLinks);
        return a(apiClient, new hr(packageName, viewIntent, title, webUrl, (String) null, outLinks));
    }

    @Override // com.google.android.gms.appindexing.AppIndexApi
    public PendingResult<Status> view(GoogleApiClient apiClient, Activity activity, Uri appIndexingUrl, String title, Uri webUrl, List<AppIndexApi.AppIndexingLink> outLinks) {
        String packageName = ((hx) apiClient.a(hc.CG)).getContext().getPackageName();
        b(packageName, appIndexingUrl);
        return view(apiClient, activity, a(packageName, appIndexingUrl), title, webUrl, outLinks);
    }

    @Override // com.google.android.gms.appindexing.AppIndexApi
    public PendingResult<Status> viewEnd(GoogleApiClient apiClient, Activity activity, Intent viewIntent) {
        return a(apiClient, new hr(hr.a(((hx) apiClient.a(hc.CG)).getContext().getPackageName(), viewIntent), System.currentTimeMillis(), 3));
    }

    @Override // com.google.android.gms.appindexing.AppIndexApi
    public PendingResult<Status> viewEnd(GoogleApiClient apiClient, Activity activity, Uri appIndexingUrl) {
        return viewEnd(apiClient, activity, a(((hx) apiClient.a(hc.CG)).getContext().getPackageName(), appIndexingUrl));
    }
}
