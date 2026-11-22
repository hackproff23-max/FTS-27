package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
class n implements ContainerHolder {
    private Status Eb;
    private final Looper JF;
    private boolean Pf;
    private Container aqu;
    private Container aqv;
    private b aqw;
    private a aqx;
    private TagManager aqy;

    public interface a {
        void ct(String str);

        String pl();

        void pn();
    }

    private class b extends Handler {
        private final ContainerHolder.ContainerAvailableListener aqz;

        public b(ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
            super(looper);
            this.aqz = containerAvailableListener;
        }

        public void cu(String str) {
            sendMessage(obtainMessage(1, str));
        }

        protected void cv(String str) {
            this.aqz.onContainerAvailable(n.this, str);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    cv((String) msg.obj);
                    break;
                default:
                    bh.T("Don't know how to handle this message.");
                    break;
            }
        }
    }

    public n(Status status) {
        this.Eb = status;
        this.JF = null;
    }

    public n(TagManager tagManager, Looper looper, Container container, a aVar) {
        this.aqy = tagManager;
        this.JF = looper == null ? Looper.getMainLooper() : looper;
        this.aqu = container;
        this.aqx = aVar;
        this.Eb = Status.Kw;
        tagManager.a(this);
    }

    private void pm() {
        if (this.aqw != null) {
            this.aqw.cu(this.aqv.pj());
        }
    }

    public synchronized void a(Container container) {
        if (!this.Pf) {
            if (container == null) {
                bh.T("Unexpected null container.");
            } else {
                this.aqv = container;
                pm();
            }
        }
    }

    public synchronized void cr(String str) {
        if (!this.Pf) {
            this.aqu.cr(str);
        }
    }

    void ct(String str) {
        if (this.Pf) {
            bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.aqx.ct(str);
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.Pf) {
                bh.T("ContainerHolder is released.");
            } else {
                if (this.aqv != null) {
                    this.aqu = this.aqv;
                    this.aqv = null;
                }
                container = this.aqu;
            }
        }
        return container;
    }

    String getContainerId() {
        if (!this.Pf) {
            return this.aqu.getContainerId();
        }
        bh.T("getContainerId called on a released ContainerHolder.");
        return "";
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.Eb;
    }

    String pl() {
        if (!this.Pf) {
            return this.aqx.pl();
        }
        bh.T("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public synchronized void refresh() {
        if (this.Pf) {
            bh.T("Refreshing a released ContainerHolder.");
        } else {
            this.aqx.pn();
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public synchronized void release() {
        if (this.Pf) {
            bh.T("Releasing a released ContainerHolder.");
        } else {
            this.Pf = true;
            this.aqy.b(this);
            this.aqu.release();
            this.aqu = null;
            this.aqv = null;
            this.aqx = null;
            this.aqw = null;
        }
    }

    @Override // com.google.android.gms.tagmanager.ContainerHolder
    public synchronized void setContainerAvailableListener(ContainerHolder.ContainerAvailableListener listener) {
        if (this.Pf) {
            bh.T("ContainerHolder is released.");
        } else if (listener == null) {
            this.aqw = null;
        } else {
            this.aqw = new b(listener, this.JF);
            if (this.aqv != null) {
                pm();
            }
        }
    }
}
