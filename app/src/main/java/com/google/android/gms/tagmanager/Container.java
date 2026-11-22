package com.google.android.gms.tagmanager;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Container {
    private final String aqm;
    private final DataLayer aqn;
    private ct aqo;
    private volatile long aqr;
    private final Context mContext;
    private Map<String, FunctionCallMacroCallback> aqp = new HashMap();
    private Map<String, FunctionCallTagCallback> aqq = new HashMap();
    private volatile String aqs = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class a implements s.a {
        private a() {
        }

        @Override // com.google.android.gms.tagmanager.s.a
        public Object b(String str, Map<String, Object> map) {
            FunctionCallMacroCallback functionCallMacroCallbackCp = Container.this.cp(str);
            if (functionCallMacroCallbackCp == null) {
                return null;
            }
            return functionCallMacroCallbackCp.getValue(str, map);
        }
    }

    private class b implements s.a {
        private b() {
        }

        @Override // com.google.android.gms.tagmanager.s.a
        public Object b(String str, Map<String, Object> map) {
            FunctionCallTagCallback functionCallTagCallbackCq = Container.this.cq(str);
            if (functionCallTagCallbackCq != null) {
                functionCallTagCallbackCq.execute(str, map);
            }
            return di.ra();
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, com.google.android.gms.internal.c.j resource) {
        this.mContext = context;
        this.aqn = dataLayer;
        this.aqm = containerId;
        this.aqr = lastRefreshTime;
        a(resource.gs);
        if (resource.gr != null) {
            a(resource.gr);
        }
    }

    Container(Context context, DataLayer dataLayer, String containerId, long lastRefreshTime, cr.c resource) {
        this.mContext = context;
        this.aqn = dataLayer;
        this.aqm = containerId;
        this.aqr = lastRefreshTime;
        a(resource);
    }

    private void a(com.google.android.gms.internal.c.f fVar) {
        if (fVar == null) {
            throw new NullPointerException();
        }
        try {
            a(cr.b(fVar));
        } catch (cr.g e) {
            bh.T("Not loading resource: " + fVar + " because it is invalid: " + e.toString());
        }
    }

    private void a(cr.c cVar) {
        this.aqs = cVar.getVersion();
        a(new ct(this.mContext, cVar, this.aqn, new a(), new b(), cs(this.aqs)));
    }

    private synchronized void a(ct ctVar) {
        this.aqo = ctVar;
    }

    private void a(com.google.android.gms.internal.c.i[] iVarArr) {
        ArrayList arrayList = new ArrayList();
        for (com.google.android.gms.internal.c.i iVar : iVarArr) {
            arrayList.add(iVar);
        }
        pk().l(arrayList);
    }

    private synchronized ct pk() {
        return this.aqo;
    }

    FunctionCallMacroCallback cp(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.aqp) {
            functionCallMacroCallback = this.aqp.get(str);
        }
        return functionCallMacroCallback;
    }

    FunctionCallTagCallback cq(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.aqq) {
            functionCallTagCallback = this.aqq.get(str);
        }
        return functionCallTagCallback;
    }

    void cr(String str) {
        pk().cr(str);
    }

    ag cs(String str) {
        if (ce.qa().qb().equals(ce.a.CONTAINER_DEBUG)) {
        }
        return new br();
    }

    public boolean getBoolean(String key) {
        ct ctVarPk = pk();
        if (ctVarPk == null) {
            bh.T("getBoolean called for closed container.");
            return di.qY().booleanValue();
        }
        try {
            return di.n(ctVarPk.cT(key).getObject()).booleanValue();
        } catch (Exception e) {
            bh.T("Calling getBoolean() threw an exception: " + e.getMessage() + " Returning default value.");
            return di.qY().booleanValue();
        }
    }

    public String getContainerId() {
        return this.aqm;
    }

    public double getDouble(String key) {
        ct ctVarPk = pk();
        if (ctVarPk == null) {
            bh.T("getDouble called for closed container.");
            return di.qX().doubleValue();
        }
        try {
            return di.m(ctVarPk.cT(key).getObject()).doubleValue();
        } catch (Exception e) {
            bh.T("Calling getDouble() threw an exception: " + e.getMessage() + " Returning default value.");
            return di.qX().doubleValue();
        }
    }

    public long getLastRefreshTime() {
        return this.aqr;
    }

    public long getLong(String key) {
        ct ctVarPk = pk();
        if (ctVarPk == null) {
            bh.T("getLong called for closed container.");
            return di.qW().longValue();
        }
        try {
            return di.l(ctVarPk.cT(key).getObject()).longValue();
        } catch (Exception e) {
            bh.T("Calling getLong() threw an exception: " + e.getMessage() + " Returning default value.");
            return di.qW().longValue();
        }
    }

    public String getString(String key) {
        ct ctVarPk = pk();
        if (ctVarPk == null) {
            bh.T("getString called for closed container.");
            return di.ra();
        }
        try {
            return di.j(ctVarPk.cT(key).getObject());
        } catch (Exception e) {
            bh.T("Calling getString() threw an exception: " + e.getMessage() + " Returning default value.");
            return di.ra();
        }
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    String pj() {
        return this.aqs;
    }

    public void registerFunctionCallMacroCallback(String customMacroName, FunctionCallMacroCallback customMacroCallback) {
        if (customMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.aqp) {
            this.aqp.put(customMacroName, customMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String customTagName, FunctionCallTagCallback customTagCallback) {
        if (customTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.aqq) {
            this.aqq.put(customTagName, customTagCallback);
        }
    }

    void release() {
        this.aqo = null;
    }

    public void unregisterFunctionCallMacroCallback(String customMacroName) {
        synchronized (this.aqp) {
            this.aqp.remove(customMacroName);
        }
    }

    public void unregisterFunctionCallTagCallback(String customTagName) {
        synchronized (this.aqq) {
            this.aqq.remove(customTagName);
        }
    }
}
