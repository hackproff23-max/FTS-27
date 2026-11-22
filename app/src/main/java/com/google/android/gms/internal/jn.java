package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class jn implements Handler.Callback {
    private static final Object Nd = new Object();
    private static jn Ne;
    private final HashMap<String, a> Nf = new HashMap<>();
    private final Handler mHandler;
    private final Context mO;

    final class a {
        private final String Ng;
        private boolean Nj;
        private IBinder Nk;
        private ComponentName Nl;
        private final ServiceConnectionC0093a Nh = new ServiceConnectionC0093a();
        private final HashSet<jl<?>.f> Ni = new HashSet<>();
        private int mState = 2;

        /* JADX INFO: renamed from: com.google.android.gms.internal.jn$a$a, reason: collision with other inner class name */
        public class ServiceConnectionC0093a implements ServiceConnection {
            public ServiceConnectionC0093a() {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName component, IBinder binder) {
                synchronized (jn.this.Nf) {
                    a.this.Nk = binder;
                    a.this.Nl = component;
                    Iterator it = a.this.Ni.iterator();
                    while (it.hasNext()) {
                        ((jl.f) it.next()).onServiceConnected(component, binder);
                    }
                    a.this.mState = 1;
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName component) {
                synchronized (jn.this.Nf) {
                    a.this.Nk = null;
                    a.this.Nl = component;
                    Iterator it = a.this.Ni.iterator();
                    while (it.hasNext()) {
                        ((jl.f) it.next()).onServiceDisconnected(component);
                    }
                    a.this.mState = 2;
                }
            }
        }

        public a(String str) {
            this.Ng = str;
        }

        public void a(jl<?>.f fVar) {
            this.Ni.add(fVar);
        }

        public void b(jl<?>.f fVar) {
            this.Ni.remove(fVar);
        }

        public boolean c(jl<?>.f fVar) {
            return this.Ni.contains(fVar);
        }

        public IBinder getBinder() {
            return this.Nk;
        }

        public ComponentName getComponentName() {
            return this.Nl;
        }

        public int getState() {
            return this.mState;
        }

        public void hA() {
            this.Nj = jn.this.mO.bindService(new Intent(this.Ng).setPackage(GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_PACKAGE), this.Nh, 129);
            if (this.Nj) {
                this.mState = 3;
            } else {
                jn.this.mO.unbindService(this.Nh);
            }
        }

        public void hB() {
            jn.this.mO.unbindService(this.Nh);
            this.Nj = false;
            this.mState = 2;
        }

        public String hC() {
            return this.Ng;
        }

        public boolean hD() {
            return this.Ni.isEmpty();
        }

        public boolean isBound() {
            return this.Nj;
        }
    }

    private jn(Context context) {
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.mO = context.getApplicationContext();
    }

    public static jn J(Context context) {
        synchronized (Nd) {
            if (Ne == null) {
                Ne = new jn(context.getApplicationContext());
            }
        }
        return Ne;
    }

    public boolean a(String str, jl<?>.f fVar) {
        boolean zIsBound;
        synchronized (this.Nf) {
            a aVar = this.Nf.get(str);
            if (aVar != null) {
                this.mHandler.removeMessages(0, aVar);
                if (!aVar.c(fVar)) {
                    aVar.a(fVar);
                    switch (aVar.getState()) {
                        case 1:
                            fVar.onServiceConnected(aVar.getComponentName(), aVar.getBinder());
                            break;
                        case 2:
                            aVar.hA();
                            break;
                    }
                } else {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  startServiceAction=" + str);
                }
            } else {
                aVar = new a(str);
                aVar.a(fVar);
                aVar.hA();
                this.Nf.put(str, aVar);
            }
            zIsBound = aVar.isBound();
        }
        return zIsBound;
    }

    public void b(String str, jl<?>.f fVar) {
        synchronized (this.Nf) {
            a aVar = this.Nf.get(str);
            if (aVar == null) {
                throw new IllegalStateException("Nonexistent connection status for service action: " + str);
            }
            if (!aVar.c(fVar)) {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  startServiceAction=" + str);
            }
            aVar.b(fVar);
            if (aVar.hD()) {
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, aVar), Constants.ACTIVE_THREAD_WATCHDOG);
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                a aVar = (a) msg.obj;
                synchronized (this.Nf) {
                    if (aVar.hD()) {
                        aVar.hB();
                        this.Nf.remove(aVar.hC());
                    }
                    break;
                }
                return true;
            default:
                return false;
        }
    }
}
