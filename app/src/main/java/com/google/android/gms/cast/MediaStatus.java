package com.google.android.gms.cast;

import com.google.android.gms.internal.in;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class MediaStatus {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    private static final long[] Gs = new long[0];
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    private JSONObject Ga;
    private MediaInfo Gb;
    private long Gj;
    private double Gk;
    private int Gl;
    private int Gm;
    private long Gn;
    private long Go;
    private double Gp;
    private boolean Gq;
    private long[] Gr;

    public MediaStatus(JSONObject json) throws JSONException {
        a(json, 0);
    }

    public int a(JSONObject jSONObject, int i) throws JSONException {
        int i2;
        long[] jArr;
        int i3;
        int i4 = 2;
        boolean z = false;
        boolean z2 = true;
        long j = jSONObject.getLong("mediaSessionId");
        if (j != this.Gj) {
            this.Gj = j;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (jSONObject.has("playerState")) {
            String string = jSONObject.getString("playerState");
            if (string.equals("IDLE")) {
                i3 = 1;
            } else if (string.equals("PLAYING")) {
                i3 = 2;
            } else if (string.equals("PAUSED")) {
                i3 = 3;
            } else {
                i3 = string.equals("BUFFERING") ? 4 : 0;
            }
            if (i3 != this.Gl) {
                this.Gl = i3;
                i2 |= 2;
            }
            if (i3 == 1 && jSONObject.has("idleReason")) {
                String string2 = jSONObject.getString("idleReason");
                if (!string2.equals("CANCELLED")) {
                    if (string2.equals("INTERRUPTED")) {
                        i4 = 3;
                    } else if (string2.equals("FINISHED")) {
                        i4 = 1;
                    } else {
                        i4 = string2.equals("ERROR") ? 4 : 0;
                    }
                }
                if (i4 != this.Gm) {
                    this.Gm = i4;
                    i2 |= 2;
                }
            }
        }
        if (jSONObject.has("playbackRate")) {
            double d = jSONObject.getDouble("playbackRate");
            if (this.Gk != d) {
                this.Gk = d;
                i2 |= 2;
            }
        }
        if (jSONObject.has("currentTime") && (i & 2) == 0) {
            long jB = in.b(jSONObject.getDouble("currentTime"));
            if (jB != this.Gn) {
                this.Gn = jB;
                i2 |= 2;
            }
        }
        if (jSONObject.has("supportedMediaCommands")) {
            long j2 = jSONObject.getLong("supportedMediaCommands");
            if (j2 != this.Go) {
                this.Go = j2;
                i2 |= 2;
            }
        }
        if (jSONObject.has("volume") && (i & 1) == 0) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("volume");
            double d2 = jSONObject2.getDouble("level");
            if (d2 != this.Gp) {
                this.Gp = d2;
                i2 |= 2;
            }
            boolean z3 = jSONObject2.getBoolean("muted");
            if (z3 != this.Gq) {
                this.Gq = z3;
                i2 |= 2;
            }
        }
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            long[] jArr2 = new long[length];
            for (int i5 = 0; i5 < length; i5++) {
                jArr2[i5] = jSONArray.getLong(i5);
            }
            if (this.Gr != null && this.Gr.length == length) {
                int i6 = 0;
                while (true) {
                    if (i6 >= length) {
                        z2 = false;
                        break;
                    }
                    if (this.Gr[i6] != jArr2[i6]) {
                        break;
                    }
                    i6++;
                }
            }
            if (z2) {
                this.Gr = jArr2;
            }
            z = z2;
            jArr = jArr2;
        } else if (this.Gr != null) {
            z = true;
            jArr = null;
        } else {
            jArr = null;
        }
        if (z) {
            this.Gr = jArr;
            i2 |= 2;
        }
        if (jSONObject.has("customData")) {
            this.Ga = jSONObject.getJSONObject("customData");
            i2 |= 2;
        }
        if (!jSONObject.has("media")) {
            return i2;
        }
        JSONObject jSONObject3 = jSONObject.getJSONObject("media");
        this.Gb = new MediaInfo(jSONObject3);
        int i7 = i2 | 2;
        return jSONObject3.has("metadata") ? i7 | 4 : i7;
    }

    public long fR() {
        return this.Gj;
    }

    public long[] getActiveTrackIds() {
        return this.Gr != null ? this.Gr : Gs;
    }

    public JSONObject getCustomData() {
        return this.Ga;
    }

    public int getIdleReason() {
        return this.Gm;
    }

    public MediaInfo getMediaInfo() {
        return this.Gb;
    }

    public double getPlaybackRate() {
        return this.Gk;
    }

    public int getPlayerState() {
        return this.Gl;
    }

    public long getStreamPosition() {
        return this.Gn;
    }

    public double getStreamVolume() {
        return this.Gp;
    }

    public boolean isMediaCommandSupported(long mediaCommand) {
        return (this.Go & mediaCommand) != 0;
    }

    public boolean isMute() {
        return this.Gq;
    }
}
