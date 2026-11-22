package com.mopub.common;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ResponseHeader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* JADX INFO: loaded from: classes.dex */
public class HttpClient {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final String DEFAULT_USER_AGENT = System.getProperty("http.agent");
    private static final int SOCKET_TIMEOUT = 10000;
    private static String sWebViewUserAgent;

    public static AndroidHttpClient getHttpClient() {
        String userAgent = getWebViewUserAgent(DEFAULT_USER_AGENT);
        AndroidHttpClient httpClient = AndroidHttpClient.newInstance(userAgent);
        HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 10000);
        HttpClientParams.setRedirecting(params, true);
        return httpClient;
    }

    public static HttpGet initializeHttpGet(@NonNull String url) {
        return initializeHttpGet(url, null);
    }

    public static HttpGet initializeHttpGet(@NonNull String url, @Nullable Context context) {
        String getUrl;
        Preconditions.checkNotNull(url);
        try {
            getUrl = urlEncode(url);
        } catch (Exception e) {
            getUrl = url;
        }
        HttpGet httpGet = new HttpGet(getUrl);
        if (getWebViewUserAgent() == null && context != null) {
            setWebViewUserAgent(new WebView(context).getSettings().getUserAgentString());
        }
        String webViewUserAgent = getWebViewUserAgent();
        if (webViewUserAgent != null) {
            httpGet.addHeader(ResponseHeader.USER_AGENT.getKey(), webViewUserAgent);
        }
        return httpGet;
    }

    public static String urlEncode(@NonNull String url) throws Exception {
        URI uri;
        Preconditions.checkNotNull(url);
        if (isUrlImproperlyEncoded(url)) {
            throw new UnsupportedEncodingException("URL is improperly encoded: " + url);
        }
        if (isUrlUnencoded(url)) {
            uri = encodeUrl(url);
        } else {
            uri = new URI(url);
        }
        return uri.toURL().toString();
    }

    static boolean isUrlImproperlyEncoded(@NonNull String url) {
        try {
            URLDecoder.decode(url, "UTF-8");
            return false;
        } catch (UnsupportedEncodingException e) {
            MoPubLog.w("Url is improperly encoded: " + url);
            return true;
        }
    }

    static boolean isUrlUnencoded(@NonNull String url) {
        try {
            new URI(url);
            return false;
        } catch (URISyntaxException e) {
            return true;
        }
    }

    static URI encodeUrl(@NonNull String urlString) throws Exception {
        try {
            URL url = new URL(urlString);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri;
        } catch (Exception e) {
            MoPubLog.w("Failed to encode url: " + urlString);
            throw e;
        }
    }

    public static synchronized String getWebViewUserAgent(String defaultUserAgent) {
        if (!TextUtils.isEmpty(sWebViewUserAgent)) {
            defaultUserAgent = sWebViewUserAgent;
        }
        return defaultUserAgent;
    }

    public static synchronized String getWebViewUserAgent() {
        return getWebViewUserAgent(null);
    }

    public static synchronized void setWebViewUserAgent(String userAgent) {
        sWebViewUserAgent = userAgent;
    }
}
