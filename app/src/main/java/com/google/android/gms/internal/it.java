package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class it extends il {
    private long HV;
    private MediaStatus HW;
    private final iw HX;
    private final iw HY;
    private final iw HZ;
    private final iw Ia;
    private final iw Ib;
    private final iw Ic;
    private final iw Id;
    private final iw Ie;
    private final iw If;
    private final iw Ig;
    private final List<iw> Ih;
    private final Runnable Ii;
    private boolean Ij;
    private final Handler mHandler;
    private static final String NAMESPACE = in.aG("com.google.cast.media");
    private static final long HR = TimeUnit.HOURS.toMillis(24);
    private static final long HS = TimeUnit.HOURS.toMillis(24);
    private static final long HT = TimeUnit.HOURS.toMillis(24);
    private static final long HU = TimeUnit.SECONDS.toMillis(1);

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = false;
            it.this.Ij = false;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it = it.this.Ih.iterator();
            while (it.hasNext()) {
                ((iw) it.next()).e(jElapsedRealtime, RemoteMediaPlayer.STATUS_TIMED_OUT);
            }
            synchronized (iw.Ip) {
                Iterator it2 = it.this.Ih.iterator();
                while (it2.hasNext()) {
                    z = ((iw) it2.next()).gr() ? true : z;
                }
            }
            it.this.J(z);
        }
    }

    public it() {
        this(null);
    }

    public it(String str) {
        super(NAMESPACE, "MediaControlChannel", str);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.Ii = new a();
        this.Ih = new ArrayList();
        this.HX = new iw(HS);
        this.Ih.add(this.HX);
        this.HY = new iw(HR);
        this.Ih.add(this.HY);
        this.HZ = new iw(HR);
        this.Ih.add(this.HZ);
        this.Ia = new iw(HR);
        this.Ih.add(this.Ia);
        this.Ib = new iw(HT);
        this.Ih.add(this.Ib);
        this.Ic = new iw(HR);
        this.Ih.add(this.Ic);
        this.Id = new iw(HR);
        this.Ih.add(this.Id);
        this.Ie = new iw(HR);
        this.Ih.add(this.Ie);
        this.If = new iw(HR);
        this.Ih.add(this.If);
        this.Ig = new iw(HR);
        this.Ih.add(this.Ig);
        gp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(boolean z) {
        if (this.Ij != z) {
            this.Ij = z;
            if (z) {
                this.mHandler.postDelayed(this.Ii, HU);
            } else {
                this.mHandler.removeCallbacks(this.Ii);
            }
        }
    }

    private void a(long j, JSONObject jSONObject) throws JSONException {
        int iA;
        boolean z = true;
        boolean zP = this.HX.p(j);
        boolean z2 = this.Ib.gr() && !this.Ib.p(j);
        if ((!this.Ic.gr() || this.Ic.p(j)) && (!this.Id.gr() || this.Id.p(j))) {
            z = false;
        }
        int i = z2 ? 2 : 0;
        if (z) {
            i |= 1;
        }
        if (zP || this.HW == null) {
            this.HW = new MediaStatus(jSONObject);
            this.HV = SystemClock.elapsedRealtime();
            iA = 7;
        } else {
            iA = this.HW.a(jSONObject, i);
        }
        if ((iA & 1) != 0) {
            this.HV = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((iA & 2) != 0) {
            this.HV = SystemClock.elapsedRealtime();
            onStatusUpdated();
        }
        if ((iA & 4) != 0) {
            onMetadataUpdated();
        }
        Iterator<iw> it = this.Ih.iterator();
        while (it.hasNext()) {
            it.next().d(j, 0);
        }
    }

    private void gp() {
        J(false);
        this.HV = 0L;
        this.HW = null;
        this.HX.clear();
        this.Ib.clear();
        this.Ic.clear();
    }

    public long a(iv ivVar) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long jFU = fU();
        this.Ie.a(jFU, ivVar);
        J(true);
        try {
            jSONObject.put("requestId", jFU);
            jSONObject.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "GET_STATUS");
            if (this.HW != null) {
                jSONObject.put("mediaSessionId", this.HW.fR());
            }
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, double d, JSONObject jSONObject) throws IllegalStateException, IOException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Volume cannot be " + d);
        }
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.Ic.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", fR());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, long j, int i, JSONObject jSONObject) throws IllegalStateException, IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.Ib.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "SEEK");
            jSONObject2.put("mediaSessionId", fR());
            jSONObject2.put("currentTime", in.o(j));
            if (i == 1) {
                jSONObject2.put("resumeState", "PLAYBACK_START");
            } else if (i == 2) {
                jSONObject2.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.HX.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "LOAD");
            jSONObject2.put("media", mediaInfo.toJson());
            jSONObject2.put("autoplay", z);
            jSONObject2.put("currentTime", in.o(j));
            if (jArr != null && jArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < jArr.length; i++) {
                    jSONArray.put(i, jArr[i]);
                }
                jSONObject2.put("activeTrackIds", jSONArray);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, TextTrackStyle textTrackStyle) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long jFU = fU();
        this.Ig.a(jFU, ivVar);
        J(true);
        try {
            jSONObject.put("requestId", jFU);
            jSONObject.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            jSONObject.put("mediaSessionId", fR());
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.HY.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "PAUSE");
            jSONObject2.put("mediaSessionId", fR());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, boolean z, JSONObject jSONObject) throws IllegalStateException, IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.Id.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", fR());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long a(iv ivVar, long[] jArr) throws IOException {
        JSONObject jSONObject = new JSONObject();
        long jFU = fU();
        this.If.a(jFU, ivVar);
        J(true);
        try {
            jSONObject.put("requestId", jFU);
            jSONObject.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", fR());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException e) {
        }
        a(jSONObject.toString(), jFU, (String) null);
        return jFU;
    }

    @Override // com.google.android.gms.internal.il
    public final void aD(String str) {
        this.He.b("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
            long jOptLong = jSONObject.optLong("requestId", -1L);
            if (string.equals("MEDIA_STATUS")) {
                JSONArray jSONArray = jSONObject.getJSONArray("status");
                if (jSONArray.length() > 0) {
                    a(jOptLong, jSONArray.getJSONObject(0));
                    return;
                }
                this.HW = null;
                onStatusUpdated();
                onMetadataUpdated();
                this.Ie.d(jOptLong, 0);
                return;
            }
            if (string.equals("INVALID_PLAYER_STATE")) {
                this.He.d("received unexpected error: Invalid Player State.", new Object[0]);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("customData");
                Iterator<iw> it = this.Ih.iterator();
                while (it.hasNext()) {
                    it.next().b(jOptLong, RemoteMediaPlayer.STATUS_FAILED, jSONObjectOptJSONObject);
                }
                return;
            }
            if (string.equals("LOAD_FAILED")) {
                this.HX.b(jOptLong, RemoteMediaPlayer.STATUS_FAILED, jSONObject.optJSONObject("customData"));
                return;
            }
            if (string.equals("LOAD_CANCELLED")) {
                this.HX.b(jOptLong, RemoteMediaPlayer.STATUS_CANCELED, jSONObject.optJSONObject("customData"));
                return;
            }
            if (string.equals("INVALID_REQUEST")) {
                this.He.d("received unexpected error: Invalid Request.", new Object[0]);
                JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("customData");
                Iterator<iw> it2 = this.Ih.iterator();
                while (it2.hasNext()) {
                    it2.next().b(jOptLong, RemoteMediaPlayer.STATUS_FAILED, jSONObjectOptJSONObject2);
                }
            }
        } catch (JSONException e) {
            this.He.d("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    public long b(iv ivVar, JSONObject jSONObject) throws IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.Ia.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "STOP");
            jSONObject2.put("mediaSessionId", fR());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    @Override // com.google.android.gms.internal.il
    public void b(long j, int i) {
        Iterator<iw> it = this.Ih.iterator();
        while (it.hasNext()) {
            it.next().d(j, i);
        }
    }

    public long c(iv ivVar, JSONObject jSONObject) throws IllegalStateException, IOException {
        JSONObject jSONObject2 = new JSONObject();
        long jFU = fU();
        this.HZ.a(jFU, ivVar);
        J(true);
        try {
            jSONObject2.put("requestId", jFU);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "PLAY");
            jSONObject2.put("mediaSessionId", fR());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException e) {
        }
        a(jSONObject2.toString(), jFU, (String) null);
        return jFU;
    }

    public long fR() throws IllegalStateException {
        if (this.HW == null) {
            throw new IllegalStateException("No current media session");
        }
        return this.HW.fR();
    }

    @Override // com.google.android.gms.internal.il
    public void fV() {
        gp();
    }

    public long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null || this.HV == 0) {
            return 0L;
        }
        double playbackRate = this.HW.getPlaybackRate();
        long streamPosition = this.HW.getStreamPosition();
        int playerState = this.HW.getPlayerState();
        if (playbackRate == 0.0d || playerState != 2) {
            return streamPosition;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.HV;
        long j = jElapsedRealtime < 0 ? 0L : jElapsedRealtime;
        if (j == 0) {
            return streamPosition;
        }
        long streamDuration = mediaInfo.getStreamDuration();
        long j2 = streamPosition + ((long) (j * playbackRate));
        if (streamDuration <= 0 || j2 <= streamDuration) {
            streamDuration = j2 < 0 ? 0L : j2;
        }
        return streamDuration;
    }

    public MediaInfo getMediaInfo() {
        if (this.HW == null) {
            return null;
        }
        return this.HW.getMediaInfo();
    }

    public MediaStatus getMediaStatus() {
        return this.HW;
    }

    public long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0L;
    }

    protected void onMetadataUpdated() {
    }

    protected void onStatusUpdated() {
    }
}
