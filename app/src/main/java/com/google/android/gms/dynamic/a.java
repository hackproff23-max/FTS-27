package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends LifecycleDelegate> {
    private T Tu;
    private Bundle Tv;
    private LinkedList<InterfaceC0057a> Tw;
    private final f<T> Tx = (f<T>) new f<T>() { // from class: com.google.android.gms.dynamic.a.1
        @Override // com.google.android.gms.dynamic.f
        public void a(T t) {
            a.this.Tu = t;
            Iterator it = a.this.Tw.iterator();
            while (it.hasNext()) {
                ((InterfaceC0057a) it.next()).b(a.this.Tu);
            }
            a.this.Tw.clear();
            a.this.Tv = null;
        }
    };

    /* JADX INFO: renamed from: com.google.android.gms.dynamic.a$a, reason: collision with other inner class name */
    private interface InterfaceC0057a {
        void b(LifecycleDelegate lifecycleDelegate);

        int getState();
    }

    private void a(Bundle bundle, InterfaceC0057a interfaceC0057a) {
        if (this.Tu != null) {
            interfaceC0057a.b(this.Tu);
            return;
        }
        if (this.Tw == null) {
            this.Tw = new LinkedList<>();
        }
        this.Tw.add(interfaceC0057a);
        if (bundle != null) {
            if (this.Tv == null) {
                this.Tv = (Bundle) bundle.clone();
            } else {
                this.Tv.putAll(bundle);
            }
        }
        a(this.Tx);
    }

    public static void b(FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        String strB = GooglePlayServicesUtil.b(context, iIsGooglePlayServicesAvailable);
        String strC = GooglePlayServicesUtil.c(context, iIsGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setText(strB);
        linearLayout.addView(textView);
        if (strC != null) {
            Button button = new Button(context);
            button.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button.setText(strC);
            linearLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.gms.dynamic.a.5
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    context.startActivity(GooglePlayServicesUtil.aj(iIsGooglePlayServicesAvailable));
                }
            });
        }
    }

    private void cG(int i) {
        while (!this.Tw.isEmpty() && this.Tw.getLast().getState() >= i) {
            this.Tw.removeLast();
        }
    }

    protected void a(FrameLayout frameLayout) {
        b(frameLayout);
    }

    protected abstract void a(f<T> fVar);

    public T je() {
        return this.Tu;
    }

    public void onCreate(final Bundle savedInstanceState) {
        a(savedInstanceState, new InterfaceC0057a() { // from class: com.google.android.gms.dynamic.a.3
            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.Tu.onCreate(savedInstanceState);
            }

            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public int getState() {
                return 1;
            }
        });
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final FrameLayout frameLayout = new FrameLayout(inflater.getContext());
        a(savedInstanceState, new InterfaceC0057a() { // from class: com.google.android.gms.dynamic.a.4
            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public void b(LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(a.this.Tu.onCreateView(inflater, container, savedInstanceState));
            }

            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public int getState() {
                return 2;
            }
        });
        if (this.Tu == null) {
            a(frameLayout);
        }
        return frameLayout;
    }

    public void onDestroy() {
        if (this.Tu != null) {
            this.Tu.onDestroy();
        } else {
            cG(1);
        }
    }

    public void onDestroyView() {
        if (this.Tu != null) {
            this.Tu.onDestroyView();
        } else {
            cG(2);
        }
    }

    public void onInflate(final Activity activity, final Bundle attrs, final Bundle savedInstanceState) {
        a(savedInstanceState, new InterfaceC0057a() { // from class: com.google.android.gms.dynamic.a.2
            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.Tu.onInflate(activity, attrs, savedInstanceState);
            }

            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public int getState() {
                return 0;
            }
        });
    }

    public void onLowMemory() {
        if (this.Tu != null) {
            this.Tu.onLowMemory();
        }
    }

    public void onPause() {
        if (this.Tu != null) {
            this.Tu.onPause();
        } else {
            cG(5);
        }
    }

    public void onResume() {
        a((Bundle) null, new InterfaceC0057a() { // from class: com.google.android.gms.dynamic.a.7
            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.Tu.onResume();
            }

            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public int getState() {
                return 5;
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.Tu != null) {
            this.Tu.onSaveInstanceState(outState);
        } else if (this.Tv != null) {
            outState.putAll(this.Tv);
        }
    }

    public void onStart() {
        a((Bundle) null, new InterfaceC0057a() { // from class: com.google.android.gms.dynamic.a.6
            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public void b(LifecycleDelegate lifecycleDelegate) {
                a.this.Tu.onStart();
            }

            @Override // com.google.android.gms.dynamic.a.InterfaceC0057a
            public int getState() {
                return 4;
            }
        });
    }

    public void onStop() {
        if (this.Tu != null) {
            this.Tu.onStop();
        } else {
            cG(4);
        }
    }
}
