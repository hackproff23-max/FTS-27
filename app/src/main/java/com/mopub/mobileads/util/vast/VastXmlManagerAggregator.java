package com.mopub.mobileads.util.vast;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import com.mopub.common.HttpClient;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Strings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: loaded from: classes.dex */
public class VastXmlManagerAggregator extends AsyncTask<String, Void, List<VastXmlManager>> {
    static final int MAX_TIMES_TO_FOLLOW_VAST_REDIRECT = 20;
    private int mTimesFollowedVastRedirect;
    private final WeakReference<VastXmlManagerAggregatorListener> mVastXmlManagerAggregatorListener;

    interface VastXmlManagerAggregatorListener {
        void onAggregationComplete(List<VastXmlManager> list);
    }

    VastXmlManagerAggregator(VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener) {
        this.mVastXmlManagerAggregatorListener = new WeakReference<>(vastXmlManagerAggregatorListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public List<VastXmlManager> doInBackground(String... strings) throws Throwable {
        List<VastXmlManager> vastXmlManagers = null;
        AndroidHttpClient httpClient = null;
        try {
            try {
                httpClient = HttpClient.getHttpClient();
                if (strings != null && strings.length > 0) {
                    String vastXml = strings[0];
                    List<VastXmlManager> vastXmlManagers2 = new ArrayList<>();
                    while (vastXml != null) {
                        try {
                            if (vastXml.length() <= 0 || isCancelled()) {
                                break;
                            }
                            VastXmlManager xmlManager = new VastXmlManager();
                            xmlManager.parseVastXml(vastXml);
                            vastXmlManagers2.add(xmlManager);
                            vastXml = followVastRedirect(httpClient, xmlManager.getVastAdTagURI());
                        } catch (Exception e) {
                            e = e;
                            vastXmlManagers = vastXmlManagers2;
                            MoPubLog.d("Failed to parse VAST XML", e);
                            if (httpClient != null) {
                                httpClient.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (httpClient != null) {
                                httpClient.close();
                            }
                            throw th;
                        }
                    }
                    vastXmlManagers = vastXmlManagers2;
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e2) {
                e = e2;
            }
            return vastXmlManagers;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<VastXmlManager> vastXmlManagers) {
        VastXmlManagerAggregatorListener listener = this.mVastXmlManagerAggregatorListener.get();
        if (listener != null) {
            listener.onAggregationComplete(vastXmlManagers);
        }
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        VastXmlManagerAggregatorListener listener = this.mVastXmlManagerAggregatorListener.get();
        if (listener != null) {
            listener.onAggregationComplete(null);
        }
    }

    String followVastRedirect(AndroidHttpClient httpClient, String redirectUrl) throws Exception {
        if (redirectUrl == null || this.mTimesFollowedVastRedirect >= 20) {
            return null;
        }
        this.mTimesFollowedVastRedirect++;
        HttpGet httpget = HttpClient.initializeHttpGet(redirectUrl);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            return Strings.fromStream(entity.getContent());
        }
        return null;
    }

    @Deprecated
    void setTimesFollowedVastRedirect(int timesFollowedVastRedirect) {
        this.mTimesFollowedVastRedirect = timesFollowedVastRedirect;
    }
}
