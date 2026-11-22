package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.facebook.internal.ServerProtocol;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class i extends dg {
    private static final String ID = com.google.android.gms.internal.a.ARBITRARY_PIXEL.toString();
    private static final String URL = com.google.android.gms.internal.b.URL.toString();
    private static final String aqf = com.google.android.gms.internal.b.ADDITIONAL_PARAMS.toString();
    private static final String aqg = com.google.android.gms.internal.b.UNREPEATABLE.toString();
    static final String aqh = "gtm_" + ID + "_unrepeatable";
    private static final Set<String> aqi = new HashSet();
    private final a aqj;
    private final Context mContext;

    public interface a {
        aq pf();
    }

    public i(final Context context) {
        this(context, new a() { // from class: com.google.android.gms.tagmanager.i.1
            @Override // com.google.android.gms.tagmanager.i.a
            public aq pf() {
                return y.X(context);
            }
        });
    }

    i(Context context, a aVar) {
        super(ID, URL);
        this.aqj = aVar;
        this.mContext = context;
    }

    private synchronized boolean cl(String str) {
        boolean z = true;
        synchronized (this) {
            if (!cn(str)) {
                if (cm(str)) {
                    aqi.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.google.android.gms.tagmanager.dg
    public void D(Map<String, com.google.android.gms.internal.d.a> map) {
        String strJ = map.get(aqg) != null ? di.j(map.get(aqg)) : null;
        if (strJ == null || !cl(strJ)) {
            Uri.Builder builderBuildUpon = Uri.parse(di.j(map.get(URL))).buildUpon();
            com.google.android.gms.internal.d.a aVar = map.get(aqf);
            if (aVar != null) {
                Object objO = di.o(aVar);
                if (!(objO instanceof List)) {
                    bh.T("ArbitraryPixel: additional params not a list: not sending partial hit: " + builderBuildUpon.build().toString());
                    return;
                }
                for (Object obj : (List) objO) {
                    if (!(obj instanceof Map)) {
                        bh.T("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + builderBuildUpon.build().toString());
                        return;
                    }
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        builderBuildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String string = builderBuildUpon.build().toString();
            this.aqj.pf().cB(string);
            bh.V("ArbitraryPixel: url = " + string);
            if (strJ != null) {
                synchronized (i.class) {
                    aqi.add(strJ);
                    cz.a(this.mContext, aqh, strJ, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
            }
        }
    }

    boolean cm(String str) {
        return this.mContext.getSharedPreferences(aqh, 0).contains(str);
    }

    boolean cn(String str) {
        return aqi.contains(str);
    }
}
