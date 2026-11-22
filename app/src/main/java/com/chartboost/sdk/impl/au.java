package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.mopub.common.AdType;

/* JADX INFO: loaded from: classes.dex */
public class au extends aq {
    private WebView a;
    private View.OnClickListener b;

    public au(ax axVar, Context context) {
        super(context);
        this.b = null;
        this.a = new WebView(context);
        addView(this.a, new LinearLayout.LayoutParams(-1, -1));
        this.a.setBackgroundColor(0);
        this.a.setWebViewClient(new WebViewClient() { // from class: com.chartboost.sdk.impl.au.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return false;
                }
                if (url.contains("chartboost") && url.contains("click") && au.this.b != null) {
                    au.this.b.onClick(au.this);
                }
                return true;
            }
        });
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.b = clickListener;
    }

    @Override // com.chartboost.sdk.impl.aq
    public void a(com.chartboost.sdk.Libraries.e.a aVar, int i) {
        String strE = aVar.e(AdType.HTML);
        if (strE != null) {
            try {
                this.a.loadDataWithBaseURL("file:///android_res/", strE, "text/html", "UTF-8", null);
            } catch (Exception e) {
                CBLogging.b("AppCellWebView", "Exception raised loading data into webview", e);
            }
        }
    }

    @Override // com.chartboost.sdk.impl.aq
    public int a() {
        return CBUtility.a(100, getContext());
    }
}
