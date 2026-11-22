package com.flurry.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class dw extends SurfaceView implements MediaController.MediaPlayerControl {
    private static final String d = dw.class.getSimpleName();
    MediaPlayer.OnPreparedListener a;
    MediaPlayer.OnVideoSizeChangedListener b;
    SurfaceHolder.Callback c;
    private dz e;
    private MediaPlayer f;
    private Uri g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private a p;
    private boolean q;
    private float r;
    private final fy<hj> s;
    private MediaPlayer.OnCompletionListener t;
    private MediaPlayer.OnErrorListener u;
    private MediaPlayer.OnBufferingUpdateListener v;

    enum a {
        STATE_UNKNOWN,
        STATE_INIT,
        STATE_PREPARING,
        STATE_PREPARED,
        STATE_PLAYING,
        STATE_PAUSED,
        STATE_PLAYBACK_COMPLETED,
        STATE_SUSPEND,
        STATE_ERROR
    }

    public dw(Context context, dz dzVar) {
        super(context);
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.p = a.STATE_UNKNOWN;
        this.q = false;
        this.r = 0.0f;
        this.s = new fy<hj>() { // from class: com.flurry.sdk.dw.1
            @Override // com.flurry.sdk.fy
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void notify(hj hjVar) {
                if (dw.this.f != null) {
                    int duration = dw.this.getDuration();
                    int currentPosition = dw.this.getCurrentPosition();
                    if (duration < 0 || dw.this.e == null) {
                        return;
                    }
                    if (currentPosition - dw.this.r > 1000.0f || dw.this.r <= 300.0f) {
                        dw.this.r = currentPosition;
                        dw.this.e.a(dw.this.g.toString(), duration, currentPosition);
                    }
                }
            }
        };
        this.a = new MediaPlayer.OnPreparedListener() { // from class: com.flurry.sdk.dw.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                dw.this.p = a.STATE_PREPARED;
                gd.a(5, dw.d, "OnPreparedListener: " + dw.this.g);
                if (dw.this.o > 3) {
                    dw.this.seekTo(dw.this.o);
                } else {
                    dw.this.seekTo(3);
                }
                if (dw.this.e != null && dw.this.g != null) {
                    dw.this.e.a(dw.this.g.toString());
                }
            }
        };
        this.b = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.flurry.sdk.dw.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                dw.this.h = mediaPlayer.getVideoWidth();
                dw.this.i = mediaPlayer.getVideoHeight();
                if (dw.this.h != 0 && dw.this.i != 0 && dw.this.getHolder() != null) {
                    dw.this.getHolder().setFixedSize(dw.this.h, dw.this.i);
                }
            }
        };
        this.t = new MediaPlayer.OnCompletionListener() { // from class: com.flurry.sdk.dw.4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                dw.this.p = a.STATE_PLAYBACK_COMPLETED;
                if (dw.this.e != null) {
                    dw.this.e.b(dw.this.g.toString());
                }
            }
        };
        this.u = new MediaPlayer.OnErrorListener() { // from class: com.flurry.sdk.dw.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                gd.a(5, dw.d, "Error: " + dw.this.g + " framework_err " + i + " impl_err " + i2);
                dw.this.p = a.STATE_ERROR;
                if (dw.this.e != null) {
                    dw.this.e.a(dw.this.g.toString(), av.kVideoPlaybackError.a(), i, i2);
                    return true;
                }
                return true;
            }
        };
        this.v = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.flurry.sdk.dw.6
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                dw.this.n = i;
            }
        };
        this.c = new SurfaceHolder.Callback() { // from class: com.flurry.sdk.dw.7
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                dw.this.q = true;
                dw.this.h();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                dw.this.q = false;
                dw.this.c();
            }
        };
        a(dzVar);
    }

    public void a(Uri uri, int i) {
        if (uri != null) {
            this.l = i;
            this.g = uri;
        }
    }

    public void a(int i) {
        int i2;
        if (this.p == a.STATE_PAUSED || this.p == a.STATE_PREPARED) {
            if (i < 0) {
                i2 = this.l > 3 ? this.l : 0;
            } else {
                i2 = i;
            }
            seekTo(i2);
            start();
        }
    }

    public boolean a() {
        return this.p == a.STATE_PAUSED || this.p == a.STATE_PREPARED;
    }

    public void b() {
        this.l = getCurrentPosition();
        pause();
    }

    public void c() {
        this.l = getCurrentPosition();
        this.p = a.STATE_SUSPEND;
        pause();
        g();
    }

    public int d() {
        return 3;
    }

    private void g() {
        if (this.f != null) {
            setFocusable(false);
            setFocusableInTouchMode(false);
            j();
            this.f.reset();
            this.f.release();
            hk.a().b(this.s);
            this.f = null;
        }
    }

    public void e() {
        g();
    }

    private void a(dz dzVar) {
        this.e = dzVar;
        this.g = null;
        this.p = a.STATE_INIT;
        this.l = 3;
        if (getHolder() != null) {
            getHolder().addCallback(this.c);
            getHolder().setType(3);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        requestLayout();
        hk.a().a(this.s);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.h, i);
        int defaultSize2 = getDefaultSize(this.i, i2);
        if (this.h > 0 && this.i > 0) {
            if (this.h * defaultSize2 > this.i * defaultSize) {
                defaultSize2 = (this.i * defaultSize) / this.h;
            } else if (this.h * defaultSize2 < this.i * defaultSize) {
                defaultSize = (this.h * defaultSize2) / this.i;
            }
        }
        if ((this.j != defaultSize || this.k != defaultSize2) && this.q) {
            gd.a(4, d, "setting size: " + defaultSize + 'x' + defaultSize2 + " " + System.currentTimeMillis());
            this.j = defaultSize;
            this.k = defaultSize2;
            a(defaultSize, defaultSize2);
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (l()) {
            try {
                g();
                i();
                return;
            } catch (IOException | IllegalArgumentException e) {
                gd.a(5, d, "Unable to open content: " + this.g, e);
                this.u.onError(this.f, 1, 0);
                return;
            }
        }
        gd.a(5, d, "Cannot open video: " + this.g);
        this.u.onError(this.f, 1, 0);
    }

    private void i() throws IOException, IllegalArgumentException {
        if (this.f == null && this.g != null) {
            this.f = new MediaPlayer();
            this.f.setOnPreparedListener(this.a);
            this.f.setOnVideoSizeChangedListener(this.b);
            this.m = -1;
            this.f.setOnCompletionListener(this.t);
            this.f.setOnErrorListener(this.u);
            this.f.setOnBufferingUpdateListener(this.v);
            this.f.setDisplay(getHolder());
            if (k()) {
                this.f.setDataSource(getContext(), this.g);
            } else {
                FileInputStream fileInputStream = new FileInputStream(this.g.getPath());
                this.f.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            }
            this.f.setScreenOnWhilePlaying(true);
            this.f.prepareAsync();
            this.p = a.STATE_PREPARING;
        }
    }

    private void j() {
        if (getContext() != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra("command", "pause");
            getContext().sendBroadcast(intent);
        }
    }

    private boolean k() {
        return (this.g == null || this.g.getScheme() == null || this.g.getScheme().equalsIgnoreCase("file")) ? false : true;
    }

    private boolean l() {
        Context context = getContext();
        boolean zIsFinishing = context != null ? ((Activity) context).isFinishing() : false;
        gd.a(4, d, "IsFinishing " + zIsFinishing);
        return (!this.q || isPlaying() || zIsFinishing || this.p.equals(a.STATE_SUSPEND)) ? false : true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (this.f != null && m() && !this.f.isPlaying()) {
            this.p = a.STATE_PLAYING;
            this.f.start();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (this.f != null && m() && this.f.isPlaying()) {
            gd.a(4, d, "Video pause at " + this.f.getCurrentPosition());
            this.p = a.STATE_PAUSED;
            this.f.pause();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        this.m = m() ? this.f.getDuration() : -1;
        return this.m;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (this.f == null || !(this.p.equals(a.STATE_PREPARED) || this.p.equals(a.STATE_PLAYING))) {
            return -1;
        }
        return this.f.getCurrentPosition();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (m()) {
            this.o = i;
            this.f.seekTo(i);
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return this.f != null && this.f.isPlaying() && this.p.equals(a.STATE_PLAYING);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (m()) {
            return this.n;
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    private void a(int i, int i2) {
        if (this.e != null) {
            gd.a(4, d, "Firing updateView ");
            this.e.a(i, i2);
        }
    }

    private boolean m() {
        return this.f != null && (this.p.equals(a.STATE_PREPARED) || this.p.equals(a.STATE_PLAYING) || this.p.equals(a.STATE_PAUSED));
    }
}
