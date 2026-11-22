package com.google.android.gms.internal;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class dt extends FrameLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private final gu mo;
    private final MediaController so;
    private final a sp;
    private final VideoView sq;
    private long sr;
    private String ss;

    private static final class a {
        private final Runnable mv;
        private volatile boolean st = false;

        public a(final dt dtVar) {
            this.mv = new Runnable() { // from class: com.google.android.gms.internal.dt.a.1
                private final WeakReference<dt> su;

                {
                    this.su = new WeakReference<>(dtVar);
                }

                @Override // java.lang.Runnable
                public void run() {
                    dt dtVar2 = this.su.get();
                    if (a.this.st || dtVar2 == null) {
                        return;
                    }
                    dtVar2.cr();
                    a.this.cs();
                }
            };
        }

        public void cancel() {
            this.st = true;
            gq.wR.removeCallbacks(this.mv);
        }

        public void cs() {
            gq.wR.postDelayed(this.mv, 250L);
        }
    }

    public dt(Context context, gu guVar) {
        super(context);
        this.mo = guVar;
        this.sq = new VideoView(context);
        addView(this.sq, new FrameLayout.LayoutParams(-1, -1, 17));
        this.so = new MediaController(context);
        this.sp = new a(this);
        this.sp.cs();
        this.sq.setOnCompletionListener(this);
        this.sq.setOnPreparedListener(this);
        this.sq.setOnErrorListener(this);
    }

    private static void a(gu guVar, String str) {
        a(guVar, str, new HashMap(1));
    }

    public static void a(gu guVar, String str, String str2) {
        boolean z = str2 == null;
        HashMap map = new HashMap(z ? 2 : 3);
        map.put("what", str);
        if (!z) {
            map.put("extra", str2);
        }
        a(guVar, "error", map);
    }

    private static void a(gu guVar, String str, String str2, String str3) {
        HashMap map = new HashMap(2);
        map.put(str2, str3);
        a(guVar, str, map);
    }

    private static void a(gu guVar, String str, Map<String, String> map) {
        map.put("event", str);
        guVar.a("onVideoEvent", map);
    }

    public void C(String str) {
        this.ss = str;
    }

    public void b(MotionEvent motionEvent) {
        this.sq.dispatchTouchEvent(motionEvent);
    }

    public void cq() {
        if (TextUtils.isEmpty(this.ss)) {
            a(this.mo, "no_src", (String) null);
        } else {
            this.sq.setVideoPath(this.ss);
        }
    }

    public void cr() {
        long currentPosition = this.sq.getCurrentPosition();
        if (this.sr != currentPosition) {
            a(this.mo, "timeupdate", "time", String.valueOf(currentPosition / 1000.0f));
            this.sr = currentPosition;
        }
    }

    public void destroy() {
        this.sp.cancel();
        this.sq.stopPlayback();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        a(this.mo, "ended");
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        a(this.mo, String.valueOf(what), String.valueOf(extra));
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        a(this.mo, "canplaythrough", "duration", String.valueOf(this.sq.getDuration() / 1000.0f));
    }

    public void pause() {
        this.sq.pause();
    }

    public void play() {
        this.sq.start();
    }

    public void s(boolean z) {
        if (z) {
            this.sq.setMediaController(this.so);
        } else {
            this.so.hide();
            this.sq.setMediaController(null);
        }
    }

    public void seekTo(int timeInMilliseconds) {
        this.sq.seekTo(timeInMilliseconds);
    }
}
