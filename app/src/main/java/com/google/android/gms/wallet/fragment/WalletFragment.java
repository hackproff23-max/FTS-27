package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
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
import com.google.android.gms.internal.qa;
import com.google.android.gms.internal.qb;
import com.google.android.gms.internal.qi;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

/* JADX INFO: loaded from: classes.dex */
public final class WalletFragment extends Fragment {
    private WalletFragmentOptions awe;
    private WalletFragmentInitParams awf;
    private MaskedWalletRequest awg;
    private MaskedWallet awh;
    private Boolean awi;
    private b awn;
    private boolean mCreated = false;
    private final com.google.android.gms.dynamic.b awo = com.google.android.gms.dynamic.b.a(this);
    private final c awp = new c();
    private a awq = new a(this);
    private final Fragment TG = this;

    public interface OnStateChangedListener {
        void onStateChanged(WalletFragment walletFragment, int i, int i2, Bundle bundle);
    }

    static class a extends qb.a {
        private OnStateChangedListener awr;
        private final WalletFragment aws;

        a(WalletFragment walletFragment) {
            this.aws = walletFragment;
        }

        @Override // com.google.android.gms.internal.qb
        public void a(int i, int i2, Bundle bundle) {
            if (this.awr != null) {
                this.awr.onStateChanged(this.aws, i, i2, bundle);
            }
        }

        public void a(OnStateChangedListener onStateChangedListener) {
            this.awr = onStateChangedListener;
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
            Button button = new Button(WalletFragment.this.TG.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int iA = -1;
            int iA2 = -2;
            if (WalletFragment.this.awe != null && (fragmentStyle = WalletFragment.this.awe.getFragmentStyle()) != null) {
                DisplayMetrics displayMetrics = WalletFragment.this.TG.getResources().getDisplayMetrics();
                iA = fragmentStyle.a("buyButtonWidth", displayMetrics, -1);
                iA2 = fragmentStyle.a("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(iA, iA2));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        @Override // com.google.android.gms.dynamic.a
        protected void a(f<b> fVar) {
            Activity activity = WalletFragment.this.TG.getActivity();
            if (WalletFragment.this.awn == null && WalletFragment.this.mCreated && activity != null) {
                try {
                    qa qaVarA = qi.a(activity, WalletFragment.this.awo, WalletFragment.this.awe, WalletFragment.this.awq);
                    WalletFragment.this.awn = new b(qaVarA);
                    WalletFragment.this.awe = null;
                    fVar.a(WalletFragment.this.awn);
                    if (WalletFragment.this.awf != null) {
                        WalletFragment.this.awn.initialize(WalletFragment.this.awf);
                        WalletFragment.this.awf = null;
                    }
                    if (WalletFragment.this.awg != null) {
                        WalletFragment.this.awn.updateMaskedWalletRequest(WalletFragment.this.awg);
                        WalletFragment.this.awg = null;
                    }
                    if (WalletFragment.this.awh != null) {
                        WalletFragment.this.awn.updateMaskedWallet(WalletFragment.this.awh);
                        WalletFragment.this.awh = null;
                    }
                    if (WalletFragment.this.awi != null) {
                        WalletFragment.this.awn.setEnabled(WalletFragment.this.awi.booleanValue());
                        WalletFragment.this.awi = null;
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                }
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Activity activity = WalletFragment.this.TG.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions options) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", options);
        walletFragment.TG.setArguments(bundle);
        return walletFragment;
    }

    public int getState() {
        if (this.awn != null) {
            return this.awn.getState();
        }
        return 0;
    }

    public void initialize(WalletFragmentInitParams initParams) {
        if (this.awn != null) {
            this.awn.initialize(initParams);
            this.awf = null;
        } else {
            if (this.awf != null) {
                Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
                return;
            }
            this.awf = initParams;
            if (this.awg != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.awh != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        }
    }

    @Override // android.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.awn != null) {
            this.awn.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        WalletFragmentOptions walletFragmentOptions;
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            savedInstanceState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) savedInstanceState.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.awf != null) {
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        } else if (this.TG.getArguments() != null && (walletFragmentOptions = (WalletFragmentOptions) this.TG.getArguments().getParcelable("extraWalletFragmentOptions")) != null) {
            walletFragmentOptions.ab(this.TG.getActivity());
            this.awe = walletFragmentOptions;
        }
        this.mCreated = true;
        this.awp.onCreate(savedInstanceState);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.awp.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }

    @Override // android.app.Fragment
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        if (this.awe == null) {
            this.awe = WalletFragmentOptions.a(activity, attrs);
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("attrKeyWalletFragmentOptions", this.awe);
        this.awp.onInflate(activity, bundle, savedInstanceState);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.awp.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.awp.onResume();
        FragmentManager fragmentManager = this.TG.getActivity().getFragmentManager();
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (fragmentFindFragmentByTag != null) {
            fragmentManager.beginTransaction().remove(fragmentFindFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.TG.getActivity()), this.TG.getActivity(), -1);
        }
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.awp.onSaveInstanceState(outState);
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

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.awp.onStart();
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.awp.onStop();
    }

    public void setEnabled(boolean enabled) {
        if (this.awn == null) {
            this.awi = Boolean.valueOf(enabled);
        } else {
            this.awn.setEnabled(enabled);
            this.awi = null;
        }
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        this.awq.a(listener);
    }

    public void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.awn == null) {
            this.awh = maskedWallet;
        } else {
            this.awn.updateMaskedWallet(maskedWallet);
            this.awh = null;
        }
    }

    public void updateMaskedWalletRequest(MaskedWalletRequest request) {
        if (this.awn == null) {
            this.awg = request;
        } else {
            this.awn.updateMaskedWalletRequest(request);
            this.awg = null;
        }
    }
}
