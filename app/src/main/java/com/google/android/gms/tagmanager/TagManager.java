package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.PendingResult;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class TagManager {
    private static TagManager atW;
    private final DataLayer aqn;
    private final r asD;
    private final a atT;
    private final cx atU;
    private final ConcurrentMap<n, Boolean> atV;
    private final Context mContext;

    interface a {
        o a(Context context, TagManager tagManager, Looper looper, String str, int i, r rVar);
    }

    TagManager(Context context, a containerHolderLoaderProvider, DataLayer dataLayer, cx serviceManager) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.atU = serviceManager;
        this.atT = containerHolderLoaderProvider;
        this.atV = new ConcurrentHashMap();
        this.aqn = dataLayer;
        this.aqn.a(new DataLayer.b() { // from class: com.google.android.gms.tagmanager.TagManager.1
            @Override // com.google.android.gms.tagmanager.DataLayer.b
            public void C(Map<String, Object> map) {
                Object obj = map.get("event");
                if (obj != null) {
                    TagManager.this.cV(obj.toString());
                }
            }
        });
        this.aqn.a(new d(this.mContext));
        this.asD = new r();
        qP();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cV(String str) {
        Iterator<n> it = this.atV.keySet().iterator();
        while (it.hasNext()) {
            it.next().cr(str);
        }
    }

    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (atW == null) {
                if (context == null) {
                    bh.T("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                atW = new TagManager(context, new a() { // from class: com.google.android.gms.tagmanager.TagManager.2
                    @Override // com.google.android.gms.tagmanager.TagManager.a
                    public o a(Context context2, TagManager tagManager2, Looper looper, String str, int i, r rVar) {
                        return new o(context2, tagManager2, looper, str, i, rVar);
                    }
                }, new DataLayer(new v(context)), cy.qN());
            }
            tagManager = atW;
        }
        return tagManager;
    }

    private void qP() {
        if (Build.VERSION.SDK_INT >= 14) {
            this.mContext.registerComponentCallbacks(new ComponentCallbacks2() { // from class: com.google.android.gms.tagmanager.TagManager.3
                @Override // android.content.ComponentCallbacks
                public void onConfigurationChanged(Configuration configuration) {
                }

                @Override // android.content.ComponentCallbacks
                public void onLowMemory() {
                }

                @Override // android.content.ComponentCallbacks2
                public void onTrimMemory(int i) {
                    if (i == 20) {
                        TagManager.this.dispatch();
                    }
                }
            });
        }
    }

    void a(n nVar) {
        this.atV.put(nVar, true);
    }

    boolean b(n nVar) {
        return this.atV.remove(nVar) != null;
    }

    public void dispatch() {
        this.atU.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.aqn;
    }

    synchronized boolean i(Uri uri) {
        boolean z;
        ce ceVarQa = ce.qa();
        if (ceVarQa.i(uri)) {
            String containerId = ceVarQa.getContainerId();
            switch (ceVarQa.qb()) {
                case NONE:
                    for (n nVar : this.atV.keySet()) {
                        if (nVar.getContainerId().equals(containerId)) {
                            nVar.ct(null);
                            nVar.refresh();
                        }
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (n nVar2 : this.atV.keySet()) {
                        if (nVar2.getContainerId().equals(containerId)) {
                            nVar2.ct(ceVarQa.qc());
                            nVar2.refresh();
                        } else if (nVar2.pl() != null) {
                            nVar2.ct(null);
                            nVar2.refresh();
                        }
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId) {
        o oVarA = this.atT.a(this.mContext, this, null, containerId, defaultContainerResourceId, this.asD);
        oVarA.po();
        return oVarA;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String containerId, int defaultContainerResourceId, Handler handler) {
        o oVarA = this.atT.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.asD);
        oVarA.po();
        return oVarA;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId) {
        o oVarA = this.atT.a(this.mContext, this, null, containerId, defaultContainerResourceId, this.asD);
        oVarA.pq();
        return oVarA;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String containerId, int defaultContainerResourceId, Handler handler) {
        o oVarA = this.atT.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.asD);
        oVarA.pq();
        return oVarA;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId) {
        o oVarA = this.atT.a(this.mContext, this, null, containerId, defaultContainerResourceId, this.asD);
        oVarA.pp();
        return oVarA;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String containerId, int defaultContainerResourceId, Handler handler) {
        o oVarA = this.atT.a(this.mContext, this, handler.getLooper(), containerId, defaultContainerResourceId, this.asD);
        oVarA.pp();
        return oVarA;
    }

    public void setVerboseLoggingEnabled(boolean enableVerboseLogging) {
        bh.setLogLevel(enableVerboseLogging ? 2 : 5);
    }
}
