package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.vending.expansion.downloader.Constants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

/* JADX INFO: loaded from: classes.dex */
class db implements ab {
    private final String atQ = a("GoogleTagManager", "4.00", Build.VERSION.RELEASE, c(Locale.getDefault()), Build.MODEL, Build.ID);
    private final HttpClient atR;
    private a atS;
    private final Context atz;

    public interface a {
        void a(ap apVar);

        void b(ap apVar);

        void c(ap apVar);
    }

    db(HttpClient httpClient, Context context, a aVar) {
        this.atz = context.getApplicationContext();
        this.atR = httpClient;
        this.atS = aVar;
    }

    private HttpEntityEnclosingRequest a(URL url) {
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        URISyntaxException e;
        try {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, url.toURI().toString());
            try {
                basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.atQ);
            } catch (URISyntaxException e2) {
                e = e2;
                bh.W("Exception sending hit: " + e.getClass().getSimpleName());
                bh.W(e.getMessage());
            }
        } catch (URISyntaxException e3) {
            basicHttpEntityEnclosingRequest = null;
            e = e3;
        }
        return basicHttpEntityEnclosingRequest;
    }

    private void a(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        int iAvailable;
        StringBuffer stringBuffer = new StringBuffer();
        for (Header header : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(header.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null && (iAvailable = content.available()) > 0) {
                    byte[] bArr = new byte[iAvailable];
                    content.read(bArr);
                    stringBuffer.append("POST:\n");
                    stringBuffer.append(new String(bArr)).append("\n");
                }
            } catch (IOException e) {
                bh.V("Error Writing hit to log...");
            }
        }
        bh.V(stringBuffer.toString());
    }

    static String c(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage().toLowerCase());
        if (locale.getCountry() != null && locale.getCountry().length() != 0) {
            sb.append(Constants.FILENAME_SEQUENCE_SEPARATOR).append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    String a(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", str, str2, str3, str4, str5, str6);
    }

    URL d(ap apVar) {
        try {
            return new URL(apVar.pL());
        } catch (MalformedURLException e) {
            bh.T("Error trying to parse the GTM url.");
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.ab
    public boolean ea() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.atz.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        bh.V("...no network connectivity");
        return false;
    }

    @Override // com.google.android.gms.tagmanager.ab
    public void k(List<ap> list) {
        boolean z;
        int iMin = Math.min(list.size(), 40);
        boolean z2 = true;
        int i = 0;
        while (i < iMin) {
            ap apVar = list.get(i);
            URL urlD = d(apVar);
            if (urlD == null) {
                bh.W("No destination: discarding hit.");
                this.atS.b(apVar);
                z = z2;
            } else {
                HttpEntityEnclosingRequest httpEntityEnclosingRequestA = a(urlD);
                if (httpEntityEnclosingRequestA == null) {
                    this.atS.b(apVar);
                    z = z2;
                } else {
                    HttpHost httpHost = new HttpHost(urlD.getHost(), urlD.getPort(), urlD.getProtocol());
                    httpEntityEnclosingRequestA.addHeader("Host", httpHost.toHostString());
                    a(httpEntityEnclosingRequestA);
                    if (z2) {
                        try {
                            bo.aa(this.atz);
                            z2 = false;
                        } catch (ClientProtocolException e) {
                            bh.W("ClientProtocolException sending hit; discarding hit...");
                            this.atS.b(apVar);
                            z = z2;
                        } catch (IOException e2) {
                            bh.W("Exception sending hit: " + e2.getClass().getSimpleName());
                            bh.W(e2.getMessage());
                            this.atS.c(apVar);
                            z = z2;
                        }
                    }
                    HttpResponse httpResponseExecute = this.atR.execute(httpHost, httpEntityEnclosingRequestA);
                    int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
                    HttpEntity entity = httpResponseExecute.getEntity();
                    if (entity != null) {
                        entity.consumeContent();
                    }
                    if (statusCode != 200) {
                        bh.W("Bad response: " + httpResponseExecute.getStatusLine().getStatusCode());
                        this.atS.c(apVar);
                    } else {
                        this.atS.a(apVar);
                    }
                    z = z2;
                }
            }
            i++;
            z2 = z;
        }
    }
}
