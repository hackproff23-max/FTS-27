package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.facebook.share.internal.ShareConstants;
import com.mopub.common.AdType;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.net.URI;
import java.net.URLDecoder;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes.dex */
public final class bs extends com.chartboost.sdk.f {
    private String k;

    public class a extends com.chartboost.sdk.f.a {
        public WebView b;

        public a(Context context, String str) {
            super(context);
            setFocusable(false);
            this.b = bs.this.new b(context);
            this.b.setWebViewClient(new c());
            addView(this.b);
            this.b.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        }

        @Override // com.chartboost.sdk.f.a
        protected void a(int i, int i2) {
        }
    }

    public bs(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.k = null;
    }

    @Override // com.chartboost.sdk.f
    protected com.chartboost.sdk.f.a b(Context context) {
        return new a(context, this.k);
    }

    @Override // com.chartboost.sdk.f
    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        String strE = aVar.e(AdType.HTML);
        if (strE == null) {
            a(CBError.CBImpressionError.INTERNAL);
            return false;
        }
        this.k = strE;
        b();
        return true;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private class b extends WebView {
        public b(Context context) {
            super(context);
            setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            setBackgroundColor(0);
            getSettings().setJavaScriptEnabled(true);
        }

        @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == 4 || keyCode == 3) {
                bs.this.h();
            }
            return super.onKeyDown(keyCode, event);
        }
    }

    private class c extends WebViewClient {
        private c() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bs.this.i();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            bs.this.a(CBError.CBImpressionError.INTERNAL);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            Exception exc;
            String str;
            JSONObject jSONObject = null;
            CBLogging.e("CBWebViewProtocol", "loading url: " + url);
            try {
                if (new URI(url).getScheme().equals("chartboost")) {
                    String[] strArrSplit = url.split("/");
                    Integer numValueOf = Integer.valueOf(strArrSplit.length);
                    if (numValueOf.intValue() < 3) {
                        bs.this.h();
                        return false;
                    }
                    String str2 = strArrSplit[2];
                    if (str2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE)) {
                        bs.this.h();
                    } else if (str2.equals(ShareConstants.WEB_DIALOG_PARAM_LINK)) {
                        if (numValueOf.intValue() < 4) {
                            bs.this.h();
                            return false;
                        }
                        try {
                            String strDecode = URLDecoder.decode(strArrSplit[3], "UTF-8");
                            try {
                                jSONObject = numValueOf.intValue() > 4 ? new JSONObject(new JSONTokener(URLDecoder.decode(strArrSplit[4], "UTF-8"))) : null;
                                str = strDecode;
                            } catch (Exception e) {
                                str = strDecode;
                                exc = e;
                                CBLogging.b("CBWebViewProtocol", "Error decoding URL or JSON", exc);
                            }
                        } catch (Exception e2) {
                            exc = e2;
                            str = null;
                        }
                        bs.this.a(str, com.chartboost.sdk.Libraries.e.a.a(jSONObject));
                    }
                }
                return true;
            } catch (Exception e3) {
                bs.this.h();
                return false;
            }
        }
    }
}
