package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class fn implements Callable<fy> {
    private final Context mContext;
    private final u pM;
    private final fy.a tB;
    private final gn ul;
    private final ai um;
    private final Object mH = new Object();
    private boolean un = false;
    private int tq = -2;
    private List<String> uo = null;

    public interface a<T extends bv.a> {
        T a(fn fnVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException;
    }

    public fn(Context context, u uVar, ai aiVar, gn gnVar, fy.a aVar) {
        this.mContext = context;
        this.pM = uVar;
        this.ul = gnVar;
        this.um = aiVar;
        this.tB = aVar;
    }

    private bv.a a(ah ahVar, a aVar, JSONObject jSONObject) throws ExecutionException, JSONException, InterruptedException {
        if (cO()) {
            return null;
        }
        String[] strArrB = b(jSONObject.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
        this.uo = strArrB == null ? null : Arrays.asList(strArrB);
        bv.a aVarA = aVar.a(this, jSONObject);
        if (aVarA == null) {
            gr.T("Failed to retrieve ad assets.");
            return null;
        }
        aVarA.a(new bv(this.pM, ahVar, jSONObject));
        return aVarA;
    }

    private fy a(bv.a aVar) {
        int i;
        synchronized (this.mH) {
            i = this.tq;
            if (aVar == null && this.tq == -2) {
                i = 0;
            }
        }
        return new fy(this.tB.vJ.tL, null, this.tB.vK.qw, i, this.tB.vK.qx, this.uo, this.tB.vK.orientation, this.tB.vK.qA, this.tB.vJ.tO, false, null, null, null, null, null, 0L, this.tB.lS, this.tB.vK.tV, this.tB.vG, this.tB.vH, this.tB.vK.ub, this.tB.vD, i != -2 ? null : aVar);
    }

    private String[] b(JSONObject jSONObject, String str) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        String[] strArr = new String[jSONArrayOptJSONArray.length()];
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            strArr[i] = jSONArrayOptJSONArray.getString(i);
        }
        return strArr;
    }

    private JSONObject c(final ah ahVar) throws JSONException, TimeoutException {
        if (cO()) {
            return null;
        }
        final gj gjVar = new gj();
        ahVar.a("/nativeAdPreProcess", new cd() { // from class: com.google.android.gms.internal.fn.1
            @Override // com.google.android.gms.internal.cd
            public void a(gu guVar, Map<String, String> map) {
                ahVar.g("/nativeAdPreProcess");
                try {
                    String str = map.get(GraphResponse.SUCCESS_KEY);
                    if (!TextUtils.isEmpty(str)) {
                        gjVar.a(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
                        return;
                    }
                } catch (JSONException e) {
                    gr.b("Malformed native JSON response.", e);
                }
                fn.this.t(0);
                jx.a(fn.this.cO(), "Unable to set the ad state error!");
                gjVar.a(null);
            }
        });
        ahVar.a("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.tB.vK.tU));
        return (JSONObject) gjVar.get();
    }

    private ah cN() throws ExecutionException, InterruptedException, CancellationException, TimeoutException {
        if (cO()) {
            return null;
        }
        ah ahVar = this.um.a(this.mContext, this.tB.vJ.lO, "https://googleads.g.doubleclick.net/mads/static/mad/sdk/native/native_ads.html").get();
        ahVar.a(this.pM, this.pM, this.pM, this.pM, false, this.pM);
        return ahVar;
    }

    public Future<Drawable> a(JSONObject jSONObject, String str, final boolean z) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        String string = z ? jSONObject2.getString("url") : jSONObject2.optString("url");
        if (!TextUtils.isEmpty(string)) {
            return this.ul.a(string, new gn.a<Drawable>() { // from class: com.google.android.gms.internal.fn.2
                @Override // com.google.android.gms.internal.gn.a
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public Drawable b(InputStream inputStream) {
                    byte[] bArrD;
                    try {
                        bArrD = lh.d(inputStream);
                    } catch (IOException e) {
                        bArrD = null;
                    }
                    if (bArrD == null) {
                        fn.this.a(2, z);
                        return null;
                    }
                    Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrD, 0, bArrD.length);
                    if (bitmapDecodeByteArray != null) {
                        return new BitmapDrawable(Resources.getSystem(), bitmapDecodeByteArray);
                    }
                    fn.this.a(2, z);
                    return null;
                }

                @Override // com.google.android.gms.internal.gn.a
                /* JADX INFO: renamed from: cP, reason: merged with bridge method [inline-methods] */
                public Drawable cQ() {
                    fn.this.a(2, z);
                    return null;
                }
            });
        }
        a(0, z);
        return new gk(null);
    }

    public void a(int i, boolean z) {
        if (z) {
            t(i);
        }
    }

    protected a b(JSONObject jSONObject) throws JSONException {
        if (cO()) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        if ("2".equals(string)) {
            return new fo();
        }
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(string)) {
            return new fp();
        }
        t(0);
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x001f  */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: cM, reason: merged with bridge method [inline-methods] */
    public fy call() {
        try {
            ah ahVarCN = cN();
            JSONObject jSONObjectC = c(ahVarCN);
            return a(a(ahVarCN, b(jSONObjectC), jSONObjectC));
        } catch (InterruptedException e) {
            if (!this.un) {
                t(0);
            }
            return a(null);
        } catch (CancellationException e2) {
            if (!this.un) {
                t(0);
            }
            return a(null);
        } catch (ExecutionException e3) {
            if (!this.un) {
                t(0);
            }
            return a(null);
        } catch (TimeoutException e4) {
            gr.d("Timeout when loading native ad.", e4);
            if (!this.un) {
                t(0);
            }
            return a(null);
        } catch (JSONException e5) {
            gr.d("Malformed native JSON response.", e5);
            if (!this.un) {
                t(0);
            }
            return a(null);
        }
    }

    public boolean cO() {
        boolean z;
        synchronized (this.mH) {
            z = this.un;
        }
        return z;
    }

    public void t(int i) {
        synchronized (this.mH) {
            this.un = true;
            this.tq = i;
        }
    }
}
