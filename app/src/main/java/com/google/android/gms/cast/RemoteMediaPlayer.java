package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.im;
import com.google.android.gms.internal.it;
import com.google.android.gms.internal.iu;
import com.google.android.gms.internal.iv;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    private OnMetadataUpdatedListener Gy;
    private OnStatusUpdatedListener Gz;
    private final Object mH = new Object();
    private final a Gx = new a();
    private final it Gw = new it() { // from class: com.google.android.gms.cast.RemoteMediaPlayer.1
        @Override // com.google.android.gms.internal.it
        protected void onMetadataUpdated() {
            RemoteMediaPlayer.this.onMetadataUpdated();
        }

        @Override // com.google.android.gms.internal.it
        protected void onStatusUpdated() {
            RemoteMediaPlayer.this.onStatusUpdated();
        }
    };

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    private class a implements iu {
        private GoogleApiClient GN;
        private long GO = 0;

        /* JADX INFO: renamed from: com.google.android.gms.cast.RemoteMediaPlayer$a$a, reason: collision with other inner class name */
        private final class C0036a implements ResultCallback<Status> {
            private final long GP;

            C0036a(long j) {
                this.GP = j;
            }

            @Override // com.google.android.gms.common.api.ResultCallback
            /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    return;
                }
                RemoteMediaPlayer.this.Gw.b(this.GP, status.getStatusCode());
            }
        }

        public a() {
        }

        @Override // com.google.android.gms.internal.iu
        public void a(String str, String str2, long j, String str3) throws IOException {
            if (this.GN == null) {
                throw new IOException("No GoogleApiClient available");
            }
            Cast.CastApi.sendMessage(this.GN, str, str2).setResultCallback(new C0036a(j));
        }

        public void b(GoogleApiClient googleApiClient) {
            this.GN = googleApiClient;
        }

        @Override // com.google.android.gms.internal.iu
        public long fS() {
            long j = this.GO + 1;
            this.GO = j;
            return j;
        }
    }

    private static abstract class b extends Cast.a<MediaChannelResult> {
        iv GR;

        b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
            this.GR = new iv() { // from class: com.google.android.gms.cast.RemoteMediaPlayer.b.1
                @Override // com.google.android.gms.internal.iv
                public void a(long j, int i, JSONObject jSONObject) {
                    b.this.b(new c(new Status(i), jSONObject));
                }

                @Override // com.google.android.gms.internal.iv
                public void n(long j) {
                    b.this.b(b.this.c(new Status(RemoteMediaPlayer.STATUS_REPLACED)));
                }
            };
        }

        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public MediaChannelResult c(final Status status) {
            return new MediaChannelResult() { // from class: com.google.android.gms.cast.RemoteMediaPlayer.b.2
                @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
                public JSONObject getCustomData() {
                    return null;
                }

                @Override // com.google.android.gms.common.api.Result
                public Status getStatus() {
                    return status;
                }
            };
        }
    }

    private static final class c implements MediaChannelResult {
        private final Status Eb;
        private final JSONObject Ga;

        c(Status status, JSONObject jSONObject) {
            this.Eb = status;
            this.Ga = jSONObject;
        }

        @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
        public JSONObject getCustomData() {
            return this.Ga;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    public RemoteMediaPlayer() {
        this.Gw.a(this.Gx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMetadataUpdated() {
        if (this.Gy != null) {
            this.Gy.onMetadataUpdated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStatusUpdated() {
        if (this.Gz != null) {
            this.Gz.onStatusUpdated();
        }
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.mH) {
            approximateStreamPosition = this.Gw.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.mH) {
            mediaInfo = this.Gw.getMediaInfo();
        }
        return mediaInfo;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.mH) {
            mediaStatus = this.Gw.getMediaStatus();
        }
        return mediaStatus;
    }

    public String getNamespace() {
        return this.Gw.getNamespace();
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.mH) {
            streamDuration = this.Gw.getStreamDuration();
        }
        return streamDuration;
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo) {
        return load(apiClient, mediaInfo, true, 0L, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay) {
        return load(apiClient, mediaInfo, autoplay, 0L, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay, long playPosition) {
        return load(apiClient, mediaInfo, autoplay, playPosition, null, null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient apiClient, MediaInfo mediaInfo, boolean autoplay, long playPosition, JSONObject customData) {
        return load(apiClient, mediaInfo, autoplay, playPosition, null, customData);
    }

    public PendingResult<MediaChannelResult> load(final GoogleApiClient apiClient, final MediaInfo mediaInfo, final boolean autoplay, final long playPosition, final long[] activeTrackIds, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.Gw.a(this.GR, mediaInfo, autoplay, playPosition, activeTrackIds, customData);
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                    } finally {
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public void onMessageReceived(CastDevice castDevice, String namespace, String message) {
        this.Gw.aD(message);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient apiClient) {
        return pause(apiClient, null);
    }

    public PendingResult<MediaChannelResult> pause(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.a(this.GR, customData);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (Throwable th) {
                            RemoteMediaPlayer.this.Gx.b(null);
                            throw th;
                        }
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient apiClient) {
        return play(apiClient, null);
    }

    public PendingResult<MediaChannelResult> play(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.c(this.GR, customData);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (Throwable th) {
                            RemoteMediaPlayer.this.Gx.b(null);
                            throw th;
                        }
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> requestStatus(final GoogleApiClient apiClient) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.a(this.GR);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (IOException e) {
                            b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                            RemoteMediaPlayer.this.Gx.b(null);
                        }
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.Gx.b(null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient apiClient, long position) {
        return seek(apiClient, position, 0, null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient apiClient, long position, int resumeState) {
        return seek(apiClient, position, resumeState, null);
    }

    public PendingResult<MediaChannelResult> seek(final GoogleApiClient apiClient, final long position, final int resumeState, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.a(this.GR, position, resumeState, customData);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (IOException e) {
                            b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                            RemoteMediaPlayer.this.Gx.b(null);
                        }
                    } catch (Throwable th) {
                        RemoteMediaPlayer.this.Gx.b(null);
                        throw th;
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(final GoogleApiClient apiClient, final long[] trackIds) {
        if (trackIds == null || trackIds.length == 0) {
            throw new IllegalArgumentException("trackIds cannot be null or empty");
        }
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.a(this.GR, trackIds);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (Throwable th) {
                            RemoteMediaPlayer.this.Gx.b(null);
                            throw th;
                        }
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener listener) {
        this.Gy = listener;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener listener) {
        this.Gz = listener;
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient apiClient, boolean muteState) {
        return setStreamMute(apiClient, muteState, null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(final GoogleApiClient apiClient, final boolean muteState, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        RemoteMediaPlayer.this.Gw.a(this.GR, muteState, customData);
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                    } catch (IllegalStateException e2) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                    } finally {
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient apiClient, double volume) throws IllegalArgumentException {
        return setStreamVolume(apiClient, volume, null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(final GoogleApiClient apiClient, final double volume, final JSONObject customData) throws IllegalArgumentException {
        if (Double.isInfinite(volume) || Double.isNaN(volume)) {
            throw new IllegalArgumentException("Volume cannot be " + volume);
        }
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            try {
                                RemoteMediaPlayer.this.Gw.a(this.GR, volume, customData);
                                RemoteMediaPlayer.this.Gx.b(null);
                            } catch (Throwable th) {
                                RemoteMediaPlayer.this.Gx.b(null);
                                throw th;
                            }
                        } catch (IllegalStateException e) {
                            b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                            RemoteMediaPlayer.this.Gx.b(null);
                        }
                    } catch (IOException e2) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    } catch (IllegalArgumentException e3) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(final GoogleApiClient apiClient, final TextTrackStyle trackStyle) {
        if (trackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.a(this.GR, trackStyle);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (Throwable th) {
                            RemoteMediaPlayer.this.Gx.b(null);
                            throw th;
                        }
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient apiClient) {
        return stop(apiClient, null);
    }

    public PendingResult<MediaChannelResult> stop(final GoogleApiClient apiClient, final JSONObject customData) {
        return apiClient.b(new b(apiClient) { // from class: com.google.android.gms.cast.RemoteMediaPlayer.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(im imVar) {
                synchronized (RemoteMediaPlayer.this.mH) {
                    RemoteMediaPlayer.this.Gx.b(apiClient);
                    try {
                        try {
                            RemoteMediaPlayer.this.Gw.b(this.GR, customData);
                            RemoteMediaPlayer.this.Gx.b(null);
                        } catch (Throwable th) {
                            RemoteMediaPlayer.this.Gx.b(null);
                            throw th;
                        }
                    } catch (IOException e) {
                        b(c(new Status(RemoteMediaPlayer.STATUS_FAILED)));
                        RemoteMediaPlayer.this.Gx.b(null);
                    }
                }
            }
        });
    }
}
