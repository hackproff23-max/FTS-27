package com.google.android.gms.analytics;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

/* JADX INFO: loaded from: classes.dex */
public class h implements r {
    private final Context mContext;
    private final HttpClient yf;
    private URL yg;
    private int yh;
    private int yi;
    private int yj;
    private String yk;
    private String yl;
    private i ym;
    private l yn;
    private long yq;
    private long yr;
    private o ys;
    private Set<Integer> yo = new HashSet();
    private boolean yp = false;
    private volatile boolean yt = false;
    private final String wl = a("GoogleAnalytics", "4.0", Build.VERSION.RELEASE, an.a(Locale.getDefault()), Build.MODEL, Build.ID);

    h(HttpClient httpClient, Context context, o oVar) {
        this.mContext = context.getApplicationContext();
        this.ys = oVar;
        this.yf = httpClient;
    }

    private String a(ab abVar, List<String> list, i iVar) {
        if (iVar == i.NONE) {
            return TextUtils.isEmpty((abVar.fa() == null || abVar.fa().length() == 0) ? "" : abVar.fa()) ? "" : ac.a(abVar, System.currentTimeMillis());
        }
        String str = "";
        for (String str2 : list) {
            if (str2.length() != 0) {
                if (str.length() != 0) {
                    str = str + "\n";
                }
                str = str + str2;
            }
        }
        return str;
    }

    private URL a(ab abVar) {
        if (this.yg != null) {
            return this.yg;
        }
        try {
            return new URL("http:".equals(abVar.fd()) ? "http://www.google-analytics.com/collect" : "https://ssl.google-analytics.com/collect");
        } catch (MalformedURLException e) {
            ae.T("Error trying to parse the hardcoded host url. This really shouldn't happen.");
            return null;
        }
    }

    private void a(af afVar, HttpHost httpHost, i iVar, l lVar) {
        HttpHost httpHost2;
        afVar.g("_bs", iVar.toString());
        afVar.g("_cs", lVar.toString());
        String strFg = afVar.fg();
        if (TextUtils.isEmpty(strFg)) {
            return;
        }
        if (httpHost == null) {
            try {
                URL url = new URL("https://ssl.google-analytics.com");
                httpHost2 = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
            } catch (MalformedURLException e) {
                return;
            }
        } else {
            httpHost2 = httpHost;
        }
        a(strFg, httpHost2, 1, afVar, l.NONE);
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
                ae.W("Error Writing hit to log...");
            }
        }
        ae.U(stringBuffer.toString());
    }

    private boolean a(String str, HttpHost httpHost, int i, af afVar, l lVar) {
        int length;
        int length2;
        boolean z = i > 1;
        if (str.getBytes().length > this.yj || str.getBytes().length > this.yi) {
            ae.W("Request too long (> " + Math.min(this.yi, this.yj) + " bytes)--not sent");
            return true;
        }
        if (this.yt) {
            ae.U("Dry run enabled. Hit not actually sent.");
            return true;
        }
        HttpEntityEnclosingRequest httpEntityEnclosingRequestD = d(str, z);
        if (httpEntityEnclosingRequestD == null) {
            return true;
        }
        if (httpEntityEnclosingRequestD.getRequestLine().getMethod().equals(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET)) {
            length2 = str.getBytes().length;
            length = str.getBytes().length;
        } else {
            try {
                switch (lVar) {
                    case GZIP:
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                        gZIPOutputStream.write(str.getBytes());
                        gZIPOutputStream.close();
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        length = byteArray.length + 0;
                        httpEntityEnclosingRequestD.setEntity(new ByteArrayEntity(byteArray));
                        httpEntityEnclosingRequestD.addHeader("Content-Encoding", "gzip");
                        break;
                    default:
                        length = str.getBytes().length;
                        httpEntityEnclosingRequestD.setEntity(new StringEntity(str));
                        break;
                }
                length2 = str.getBytes().length;
            } catch (UnsupportedEncodingException e) {
                ae.T("Encoding error, hit will be discarded");
                return true;
            } catch (IOException e2) {
                ae.T("Unexpected IOException: " + e2.getMessage());
                ae.T("Request will be discarded");
                return true;
            }
        }
        httpEntityEnclosingRequestD.addHeader("Host", httpHost.toHostString());
        if (ae.ff()) {
            a(httpEntityEnclosingRequestD);
        }
        try {
            HttpResponse httpResponseExecute = this.yf.execute(httpHost, httpEntityEnclosingRequestD);
            afVar.e("_td", length2);
            afVar.e("_cd", length);
            int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
            HttpEntity entity = httpResponseExecute.getEntity();
            if (entity != null) {
                entity.consumeContent();
            }
            if (statusCode == 200) {
                return true;
            }
            if (!z || !this.yo.contains(Integer.valueOf(statusCode))) {
                ae.W("Bad response: " + httpResponseExecute.getStatusLine().getStatusCode());
                return true;
            }
            ae.U("Falling back to single hit per request mode.");
            this.yp = true;
            this.yq = System.currentTimeMillis();
            return false;
        } catch (ClientProtocolException e3) {
            ae.W("ClientProtocolException sending hit; discarding hit...");
            return true;
        } catch (IOException e4) {
            ae.W("Exception sending hit: " + e4.getClass().getSimpleName());
            ae.W(e4.getMessage());
            return false;
        }
    }

    private HttpEntityEnclosingRequest d(String str, boolean z) {
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        if (TextUtils.isEmpty(str)) {
            System.out.println("Empty hit, discarding.");
            return null;
        }
        String str2 = this.yk + "?" + str;
        if (str2.length() >= this.yh || z) {
            basicHttpEntityEnclosingRequest = z ? new BasicHttpEntityEnclosingRequest(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, this.yl) : new BasicHttpEntityEnclosingRequest(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, this.yk);
        } else {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, str2);
        }
        basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.wl);
        return basicHttpEntityEnclosingRequest;
    }

    int a(List<ab> list, int i) {
        long j;
        int i2;
        if (list.isEmpty()) {
            return 0;
        }
        if (i > list.size()) {
            i = list.size();
        }
        int i3 = i - 1;
        long j2 = 0;
        int i4 = i;
        while (i3 > 0) {
            ab abVar = list.get(i3 - 1);
            ab abVar2 = list.get(i3);
            long jFc = abVar.fc();
            long jFc2 = abVar2.fc();
            if (jFc == 0 || jFc2 == 0 || jFc2 - jFc <= j2) {
                j = j2;
                i2 = i4;
            } else {
                j = jFc2 - jFc;
                i2 = i3;
            }
            i3--;
            i4 = i2;
            j2 = j;
        }
        return i4;
    }

    /* JADX WARN: Code duplicated, block: B:92:0x02bc  */
    @Override // com.google.android.gms.analytics.r
    public int a(List<ab> list, af afVar, boolean z) {
        l lVar;
        i iVar;
        int i;
        int i2;
        List<String> listSubList;
        int i3;
        int iB;
        this.yh = this.ys.eb();
        this.yi = this.ys.ec();
        this.yj = this.ys.ed();
        int iEe = this.ys.ee();
        this.yk = this.ys.eg();
        this.yl = this.ys.eh();
        this.ym = this.ys.ei();
        this.yn = this.ys.ej();
        this.yo.clear();
        this.yo.addAll(this.ys.ek());
        this.yr = this.ys.ef();
        if (!this.yp && this.yo.isEmpty()) {
            this.yp = true;
            this.yq = System.currentTimeMillis();
        }
        if (this.yp && System.currentTimeMillis() - this.yq > 1000 * this.yr) {
            this.yp = false;
        }
        if (this.yp) {
            i iVar2 = i.NONE;
            lVar = l.NONE;
            iVar = iVar2;
        } else {
            i iVar3 = this.ym;
            lVar = this.yn;
            iVar = iVar3;
        }
        int i4 = 0;
        int iMin = Math.min(list.size(), iEe);
        afVar.e("_hr", list.size());
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        long length = 0;
        int i5 = 0;
        if (iVar != i.NONE) {
            Iterator<ab> it = list.iterator();
            while (true) {
                i = i5;
                if (!it.hasNext()) {
                    i2 = 1;
                    break;
                }
                ab next = it.next();
                String strA = TextUtils.isEmpty(next.fa()) ? "" : ac.a(next, jCurrentTimeMillis);
                if (strA.getBytes().length > this.yi) {
                    strA = "";
                }
                arrayList.add(strA);
                if (!TextUtils.isEmpty(strA)) {
                    length += (long) ((i == 0 ? 0 : 1) + strA.getBytes().length);
                }
                arrayList2.add(Long.valueOf(length));
                i5 = length <= ((long) this.yj) ? i + 1 : i;
                if (i5 == iMin) {
                    i = i5;
                    i2 = 1;
                    break;
                }
            }
        } else {
            i = 0;
            i2 = iMin;
        }
        while (i > 1 && ((Long) arrayList2.get(i - 1)).longValue() > this.yj) {
            i--;
        }
        if (length > this.yj) {
            switch (iVar) {
                case BATCH_BY_COUNT:
                    iB = arrayList.size() / 2;
                    if (i <= iB) {
                        iB = i;
                    }
                    break;
                case BATCH_BY_TIME:
                    iB = a(list, i);
                    break;
                case BATCH_BY_SESSION:
                    iB = b(list, i);
                    break;
                case BATCH_BY_SIZE:
                    if (length >= this.yj * 2) {
                        iB = i;
                    } else {
                        int size = arrayList2.size() - 1;
                        while (size > 0 && ((Long) arrayList2.get(size)).longValue() > length / 2) {
                            size--;
                        }
                        iB = size;
                    }
                    break;
                case BATCH_BY_BRUTE_FORCE:
                    iB = i;
                    break;
                default:
                    ae.W("Unexpected batching strategy encountered; sending a single hit.");
                    String str = arrayList.get(0);
                    arrayList.clear();
                    arrayList.add(str);
                    iB = 1;
                    break;
            }
            if (iB < arrayList.size()) {
                listSubList = arrayList.subList(0, iB);
            } else {
                listSubList = arrayList;
            }
        } else {
            listSubList = arrayList;
        }
        int i6 = 0;
        HttpHost httpHost = null;
        int i7 = 0;
        while (i7 < i2) {
            ab abVar = list.get(i7);
            URL urlA = a(abVar);
            int iMax = Math.max(1, listSubList.size());
            if (urlA == null) {
                ae.W("No destination: discarding hit.");
                i3 = i6 + iMax;
            } else {
                httpHost = new HttpHost(urlA.getHost(), urlA.getPort(), urlA.getProtocol());
                if (!a(a(abVar, listSubList, iVar), httpHost, iMax, afVar, lVar)) {
                    afVar.e("_de", 1);
                    afVar.e("_hd", i6);
                    afVar.e("_hs", i4);
                    a(afVar, httpHost, iVar, lVar);
                    return i4;
                }
                Iterator<String> it2 = listSubList.iterator();
                i3 = i6;
                while (it2.hasNext()) {
                    i3 = TextUtils.isEmpty(it2.next()) ? i3 + 1 : i3;
                }
                afVar.e("_rs", 1);
            }
            i7++;
            i6 = i3;
            i4 += iMax;
        }
        afVar.e("_hd", i6);
        afVar.e("_hs", i4);
        if (z) {
            a(afVar, httpHost, iVar, lVar);
        }
        return i4;
    }

    String a(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", str, str2, str3, str4, str5, str6);
    }

    @Override // com.google.android.gms.analytics.r
    public void ad(String str) {
        try {
            this.yg = new URL(str);
        } catch (MalformedURLException e) {
            this.yg = null;
        }
    }

    int b(List<ab> list, int i) {
        if (list.isEmpty()) {
            return 0;
        }
        for (int i2 = i - 1; i2 > 0; i2--) {
            String strFa = list.get(i2).fa();
            if (!TextUtils.isEmpty(strFa)) {
                if (strFa.contains("sc=start")) {
                    return i2;
                }
                if (strFa.contains("sc=end")) {
                    return i2 + 1;
                }
            }
        }
        return i;
    }

    @Override // com.google.android.gms.analytics.r
    public boolean ea() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        ae.V("...no network connectivity");
        return false;
    }

    @Override // com.google.android.gms.analytics.r
    public void setDryRun(boolean dryRun) {
        this.yt = dryRun;
    }
}
