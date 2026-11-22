package com.unity3d.ads.android.cache;

import android.os.AsyncTask;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: compiled from: UnityAdsDownloader.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends AsyncTask {
    private URL a = null;
    private InputStream b = null;
    private OutputStream c = null;
    private int d = 0;
    private URLConnection e = null;
    private boolean f = false;
    private UnityAdsCampaign g;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Object obj) {
        String str = (String) obj;
        if (this.f) {
            return;
        }
        UnityAdsDownloader.b(this.g);
        UnityAdsDownloader.a(this);
        UnityAdsDownloader.b();
        UnityAdsDownloader.a(c.DownloadCompleted, this.a != null ? this.a.toString() : "ERROR");
        super.onPostExecute(str);
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onProgressUpdate(Object[] objArr) {
        Integer[] numArr = (Integer[]) objArr;
        super.onProgressUpdate(numArr);
        numArr[0].intValue();
    }

    public b(UnityAdsCampaign unityAdsCampaign) {
        this.g = null;
        this.g = unityAdsCampaign;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public String doInBackground(String... strArr) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            this.a = new URL(strArr[0]);
            try {
                this.e = this.a.openConnection();
                this.e.setConnectTimeout(10000);
                this.e.setReadTimeout(10000);
                this.e.connect();
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Problems opening connection: " + e.getMessage());
            }
            if (this.e != null) {
                this.d = this.e.getContentLength();
                try {
                    this.b = new BufferedInputStream(this.a.openStream());
                } catch (Exception e2) {
                    UnityAdsDeviceLog.error("Problems opening stream: " + e2.getMessage());
                }
                this.c = UnityAdsDownloader.c(this.g.getVideoFilename());
                if (this.c == null) {
                    onCancelled();
                }
                byte[] bArr = new byte[1024];
                long j = 0;
                do {
                    try {
                        int i = this.b.read(bArr);
                        if (i != -1) {
                            j += (long) i;
                            publishProgress(Integer.valueOf((int) ((100 * j) / ((long) this.d))));
                            this.c.write(bArr, 0, i);
                        } else {
                            a();
                            UnityAdsDeviceLog.debug("File: " + this.g.getVideoFilename() + " of size: " + j + " downloaded in: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
                            break;
                        }
                    } catch (Exception e3) {
                        UnityAdsDeviceLog.error("Problems downloading file: " + e3.getMessage());
                        b();
                        UnityAdsDownloader.b();
                    }
                } while (!this.f);
            }
        } catch (Exception e4) {
            UnityAdsDeviceLog.error("Problems with url: " + e4.getMessage());
            onCancelled();
        }
        return null;
    }

    @Override // android.os.AsyncTask
    protected final void onCancelled() {
        UnityAdsDeviceLog.entered();
        this.f = true;
        b();
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
    }

    private void a() {
        try {
            this.c.flush();
            this.c.close();
            this.b.close();
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problems closing connection: " + e.getMessage());
        }
    }

    private void b() {
        String string = "ERROR";
        if (this.a != null) {
            string = this.a.toString();
        }
        UnityAdsDeviceLog.debug("Download cancelled for: " + string);
        a();
        UnityAdsUtils.removeFile(this.g.getVideoFilename());
        UnityAdsDownloader.b(this.g);
        UnityAdsDownloader.a(this);
        UnityAdsDownloader.a(c.DownloadCancelled, string);
    }
}
