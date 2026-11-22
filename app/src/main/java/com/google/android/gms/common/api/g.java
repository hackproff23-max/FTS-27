package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public class g extends Fragment implements DialogInterface.OnCancelListener, LoaderManager.LoaderCallbacks<ConnectionResult> {
    private boolean KC;
    private ConnectionResult KE;
    private int KD = -1;
    private final Handler KF = new Handler(Looper.getMainLooper());
    private final SparseArray<b> KG = new SparseArray<>();

    static class a extends Loader<ConnectionResult> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        public final GoogleApiClient KH;
        private boolean KI;
        private ConnectionResult KJ;

        public a(Context context, GoogleApiClient googleApiClient) {
            super(context);
            this.KH = googleApiClient;
        }

        private void a(ConnectionResult connectionResult) {
            this.KJ = connectionResult;
            if (!isStarted() || isAbandoned()) {
                return;
            }
            deliverResult(connectionResult);
        }

        public void gS() {
            if (this.KI) {
                this.KI = false;
                if (!isStarted() || isAbandoned()) {
                    return;
                }
                this.KH.connect();
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle connectionHint) {
            this.KI = false;
            a(ConnectionResult.Iu);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult result) {
            this.KI = true;
            a(result);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int cause) {
        }

        @Override // android.support.v4.content.Loader
        protected void onReset() {
            this.KJ = null;
            this.KI = false;
            this.KH.unregisterConnectionCallbacks(this);
            this.KH.unregisterConnectionFailedListener(this);
            this.KH.disconnect();
        }

        @Override // android.support.v4.content.Loader
        protected void onStartLoading() {
            super.onStartLoading();
            this.KH.registerConnectionCallbacks(this);
            this.KH.registerConnectionFailedListener(this);
            if (this.KJ != null) {
                deliverResult(this.KJ);
            }
            if (this.KH.isConnected() || this.KH.isConnecting() || this.KI) {
                return;
            }
            this.KH.connect();
        }

        @Override // android.support.v4.content.Loader
        protected void onStopLoading() {
            this.KH.disconnect();
        }
    }

    private static class b {
        public final GoogleApiClient KH;
        public final GoogleApiClient.OnConnectionFailedListener KK;

        private b(GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.KH = googleApiClient;
            this.KK = onConnectionFailedListener;
        }
    }

    private class c implements Runnable {
        private final int KL;
        private final ConnectionResult KM;

        public c(int i, ConnectionResult connectionResult) {
            this.KL = i;
            this.KM = connectionResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.KM.hasResolution()) {
                try {
                    this.KM.startResolutionForResult(g.this.getActivity(), ((g.this.getActivity().getSupportFragmentManager().getFragments().indexOf(g.this) + 1) << 16) + 1);
                    return;
                } catch (IntentSender.SendIntentException e) {
                    g.this.gR();
                    return;
                }
            }
            if (GooglePlayServicesUtil.isUserRecoverableError(this.KM.getErrorCode())) {
                GooglePlayServicesUtil.showErrorDialogFragment(this.KM.getErrorCode(), g.this.getActivity(), g.this, 2, g.this);
            } else {
                g.this.b(this.KL, this.KM);
            }
        }
    }

    public static g a(FragmentActivity fragmentActivity) {
        jx.aU("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            g gVar = (g) supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (gVar != null && !gVar.isRemoving()) {
                return gVar;
            }
            g gVar2 = new g();
            supportFragmentManager.beginTransaction().add(gVar2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return gVar2;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    private void a(int i, ConnectionResult connectionResult) {
        if (this.KC) {
            return;
        }
        this.KC = true;
        this.KD = i;
        this.KE = connectionResult;
        this.KF.post(new c(i, connectionResult));
    }

    private void aq(int i) {
        if (i == this.KD) {
            gR();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        b bVar = this.KG.get(i);
        if (bVar != null) {
            ao(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = bVar.KK;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
        gR();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gR() {
        this.KC = false;
        this.KD = -1;
        this.KE = null;
        LoaderManager loaderManager = getLoaderManager();
        for (int i = 0; i < this.KG.size(); i++) {
            int iKeyAt = this.KG.keyAt(i);
            a aVarAp = ap(iKeyAt);
            if (aVarAp != null) {
                aVarAp.gS();
            }
            loaderManager.initLoader(iKeyAt, null, this);
        }
    }

    public void a(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        jx.b(googleApiClient, "GoogleApiClient instance cannot be null");
        jx.a(this.KG.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        this.KG.put(i, new b(googleApiClient, onConnectionFailedListener));
        if (getActivity() != null) {
            getLoaderManager().initLoader(i, null, this);
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<ConnectionResult> loader, ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            aq(loader.getId());
        } else {
            a(loader.getId(), connectionResult);
        }
    }

    public GoogleApiClient an(int i) {
        a aVarAp;
        if (getActivity() == null || (aVarAp = ap(i)) == null) {
            return null;
        }
        return aVarAp.KH;
    }

    public void ao(int i) {
        getLoaderManager().destroyLoader(i);
        this.KG.remove(i);
    }

    a ap(int i) {
        try {
            return (a) getLoaderManager().getLoader(i);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", e);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean z = true;
        switch (requestCode) {
            case 1:
                if (resultCode != -1) {
                    z = false;
                }
                break;
            case 2:
                if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) != 0) {
                    z = false;
                }
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            gR();
        } else {
            b(this.KD, this.KE);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.KG.size()) {
                return;
            }
            int iKeyAt = this.KG.keyAt(i2);
            a aVarAp = ap(iKeyAt);
            if (aVarAp == null || this.KG.valueAt(i2).KH == aVarAp.KH) {
                getLoaderManager().initLoader(iKeyAt, null, this);
            } else {
                getLoaderManager().restartLoader(iKeyAt, null, this);
            }
            i = i2 + 1;
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        b(this.KD, this.KE);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.KC = savedInstanceState.getBoolean("resolving_error", false);
            this.KD = savedInstanceState.getInt("failed_client_id", -1);
            if (this.KD >= 0) {
                this.KE = new ConnectionResult(savedInstanceState.getInt("failed_status"), (PendingIntent) savedInstanceState.getParcelable("failed_resolution"));
            }
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public Loader<ConnectionResult> onCreateLoader(int id, Bundle args) {
        return new a(getActivity(), this.KG.get(id).KH);
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<ConnectionResult> loader) {
        if (loader.getId() == this.KD) {
            gR();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("resolving_error", this.KC);
        if (this.KD >= 0) {
            outState.putInt("failed_client_id", this.KD);
            outState.putInt("failed_status", this.KE.getErrorCode());
            outState.putParcelable("failed_resolution", this.KE.getResolution());
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.KC) {
            return;
        }
        for (int i = 0; i < this.KG.size(); i++) {
            getLoaderManager().initLoader(this.KG.keyAt(i), null, this);
        }
    }
}
