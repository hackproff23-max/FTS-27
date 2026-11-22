package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.f;
import com.google.android.gms.dynamic.h;
import com.google.android.gms.internal.qa;
import com.google.android.gms.internal.qb;
import com.google.android.gms.internal.qi;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

/* JADX INFO: loaded from: classes.dex */
public final class SupportWalletFragment extends Fragment {
    private b awa;
    private WalletFragmentOptions awe;
    private WalletFragmentInitParams awf;
    private MaskedWalletRequest awg;
    private MaskedWallet awh;
    private Boolean awi;
    private boolean mCreated = false;
    private final h awb = h.a(this);
    private final c awc = new c();
    private a awd = new a(this);
    private final Fragment Mx = this;

    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class a extends qb.a {
        private OnStateChangedListener awj;
        private final SupportWalletFragment awk;

        a(SupportWalletFragment supportWalletFragment) {
            this.awk = supportWalletFragment;
        }

        @Override // com.google.android.gms.internal.qb
        public void a(int i, int i2, Bundle bundle) {
            if (this.awj != null) {
                this.awj.onStateChanged(this.awk, i, i2, bundle);
            }
        }

        public void a(OnStateChangedListener onStateChangedListener) {
            this.awj = onStateChangedListener;
        }
    }

    private static class b implements LifecycleDelegate {
        private final qa awl;

        private b(qa qaVar) {
            this.awl = qaVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getState() {
            try {
                return this.awl.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initialize(WalletFragmentInitParams startParams) {
            try {
                this.awl.initialize(startParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            try {
                this.awl.onActivityResult(requestCode, resultCode, data);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnabled(boolean enabled) {
            try {
                this.awl.setEnabled(enabled);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.awl.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateMaskedWalletRequest(MaskedWalletRequest request) {
            try {
                this.awl.updateMaskedWalletRequest(request);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onCreate(Bundle savedInstanceState) {
            try {
                this.awl.onCreate(savedInstanceState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            try {
                return (View) e.f(this.awl.onCreateView(e.k(inflater), e.k(container), savedInstanceState));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onDestroy() {
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onDestroyView() {
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onInflate(Activity activity, Bundle attrs, Bundle savedInstanceState) {
            try {
                this.awl.a(e.k(activity), (WalletFragmentOptions) attrs.getParcelable("extraWalletFragmentOptions"), savedInstanceState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onLowMemory() {
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onPause() {
            try {
                this.awl.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onResume() {
            try {
                this.awl.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onSaveInstanceState(Bundle outState) {
            try {
                this.awl.onSaveInstanceState(outState);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onStart() {
            try {
                this.awl.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.android.gms.dynamic.LifecycleDelegate
        public void onStop() {
            try {
                this.awl.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class c extends com.google.android.gms.dynamic.a<b> implements View.OnClickListener {
        private c() {
        }

        @Override // com.google.android.gms.dynamic.a
        protected void a(FrameLayout frameLayout) {
            WalletFragmentStyle fragmentStyle;
            Button button = new Button(SupportWalletFragment.this.Mx.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int iA = -1;
            int iA2 = -2;
            if (SupportWalletFragment.this.awe != null && (fragmentStyle = SupportWalletFragment.this.awe.getFragmentStyle()) != null) {
                DisplayMetrics displayMetrics = SupportWalletFragment.this.Mx.getResources().getDisplayMetrics();
                iA = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                iA2 = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(iA, iA2));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        @Override // com.google.android.gms.dynamic.a
        protected void a(f<b> fVar) {
            FragmentActivity activity = SupportWalletFragment.this.Mx.getActivity();
            if (SupportWalletFragment.this.awa == null && SupportWalletFragment.this.mCreated && activity != null) {
                try {
                    qa qaVarA = qi.a(activity, SupportWalletFragment.this.awb, SupportWalletFragment.this.awe, SupportWalletFragment.this.awd);
                    SupportWalletFragment.this.awa = new b(qaVarA);
                    SupportWalletFragment.this.awe = null;
                    fVar.a(SupportWalletFragment.this.awa);
                    if (SupportWalletFragment.this.awf != null) {
                        SupportWalletFragment.this.awa.initialize(SupportWalletFragment.this.awf);
                        SupportWalletFragment.this.awf = null;
                    }
                    if (SupportWalletFragment.this.awg != null) {
                        SupportWalletFragment.this.awa.updateMaskedWalletRequest(SupportWalletFragment.this.awg);
                        SupportWalletFragment.this.awg = null;
                    }
                    if (SupportWalletFragment.this.awh != null) {
                        SupportWalletFragment.this.awa.updateMaskedWallet(SupportWalletFragment.this.awh);
                        SupportWalletFragment.this.awh = null;
                    }
                    if (SupportWalletFragment.this.awi != null) {
                        SupportWalletFragment.this.awa.setEnabled(SupportWalletFragment.this.awi.booleanValue());
                        SupportWalletFragment.this.awi = null;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FragmentActivity activity = SupportWalletFragment.this.Mx.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions options) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", options);
        supportWalletFragment.Mx.setArguments(bundle);
        return supportWalletFragment;
    }

    public int getState() {
        if (this.awa != null) {
            return this.awa.getState();
        }
        return 0;
    }

    public void initialize(WalletFragmentInitParams initParams) {
        if (this.awa != null) {
            this.awa.initialize(initParams);
            this.awf = null;
        } else {
            if (this.awf != null) {
                Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.awf = initParams;
            if (this.awg != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.awh != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.awa != null) {
            this.awa.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        WalletFragmentOptions walletFragmentOptions;
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) savedInstanceState.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.awf != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.awf = walletFragmentInitParams;
            }
            if (this.awg == null) {
                this.awg = (MaskedWalletRequest) savedInstanceState.getParcelable("maskedWalletRequest");
            }
            if (this.awh == null) {
                this.awh = (MaskedWallet) savedInstanceState.getParcelable("maskedWallet");
            }
            if (savedInstanceState.containsKey("walletFragmentOptions")) {
                this.awe = (WalletFragmentOptions) savedInstanceState.getParcelable("walletFragmentOptions");
            }
            if (savedInstanceState.containsKey("enabled")) {
                this.awi = Boolean.valueOf(savedInstanceState.getBoolean("enabled"));
            }
        } else if (this.Mx.getArguments() != null && (walletFragmentOptions = (WalletFragmentOptions) this.Mx.getArguments().getParcelable("extraWalletFragmentOptions")) != null) {
            walletFragmentOptions.ab(this.Mx.getActivity());
            this.awe = walletFragmentOptions;
        }
        this.mCreated = true;
        this.awc.onCreate(savedInstanceState);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.awc.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }

    @Override // android.support.v4.app.Fragment
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        if (this.awe == null) {
            this.awe = WalletFragmentOptions.a(activity, attrs);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("attrKeyWalletFragmentOptions", this.awe);
        this.awc.onInflate(activity, bundle, savedInstanceState);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.awc.onPause();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.awc.onResume();
        FragmentManager supportFragmentManager = this.Mx.getActivity().getSupportFragmentManager();
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (fragmentFindFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(fragmentFindFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.Mx.getActivity()), this.Mx.getActivity(), -1);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.awc.onSaveInstanceState(outState);
        if (this.awf != null) {
            outState.putParcelable("walletFragmentInitParams", this.awf);
            this.awf = null;
        }
        if (this.awg != null) {
            outState.putParcelable("maskedWalletRequest", this.awg);
            this.awg = null;
        }
        if (this.awh != null) {
            outState.putParcelable("maskedWallet", this.awh);
            this.awh = null;
        }
        if (this.awe != null) {
            outState.putParcelable("walletFragmentOptions", this.awe);
            this.awe = null;
        }
        if (this.awi != null) {
            outState.putBoolean("enabled", this.awi.booleanValue());
            this.awi = null;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        this.awc.onStart();
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        this.awc.onStop();
    }

    public void setEnabled(boolean enabled) {
        if (this.awa == null) {
            this.awi = Boolean.valueOf(enabled);
        } else {
            this.awa.setEnabled(enabled);
            this.awi = null;
        }
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.awd.a(listener);
    }

    public void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.awa == null) {
            this.awh = maskedWallet;
        } else {
            this.awa.updateMaskedWallet(maskedWallet);
            this.awh = null;
        }
    }

    public void updateMaskedWalletRequest(MaskedWalletRequest request) {
        if (this.awa == null) {
            this.awg = request;
        } else {
            this.awa.updateMaskedWalletRequest(request);
            this.awg = null;
        }
    }
}
