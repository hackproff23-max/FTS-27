package defpackage;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.nuance.nmdp.speechkit.recognitionresult.b;
import com.nuance.nmsp.client.sdk.oem.e;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class d implements ba {
    private az b = null;
    private Object c = null;
    private MediaPlayer a = null;
    private boolean d = false;
    private AssetFileDescriptor f = null;
    private boolean e = false;

    public static bm a(Class cls) {
        return new e(cls);
    }

    static /* synthetic */ void a(d dVar, final boolean z) {
        ax.a(new Runnable() { // from class: d.1
            @Override // java.lang.Runnable
            public final void run() {
                if (d.this.a == null || d.this.e) {
                    return;
                }
                if (z) {
                    d.this.a.release();
                    d.this.a = null;
                    d.this.a = d.this.e();
                    if (d.this.a == null) {
                        if (d.this.b != null) {
                            d.this.b.a(d.this.c);
                            d.f(d.this);
                            d.g(d.this);
                        }
                        d.this.c();
                        return;
                    }
                }
                if (d.this.d) {
                    d.this.d();
                    return;
                }
                if (d.this.b != null) {
                    if (z) {
                        d.this.b.a(d.this.c);
                    } else {
                        d.this.b.c(d.this.c);
                    }
                }
                d.f(d.this);
                d.g(d.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.d = false;
        this.a.start();
        this.b.b(this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MediaPlayer e() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            long startOffset = this.f.getStartOffset();
            long length = this.f.getLength();
            if (length == -1) {
                b.a(this, "Attempting to initialize MediaPlayer with asset file of unknown length");
                mediaPlayer.setDataSource(this.f.getFileDescriptor());
            } else {
                mediaPlayer.setDataSource(this.f.getFileDescriptor(), startOffset, length);
            }
            mediaPlayer.prepare();
            mediaPlayer.getDuration();
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: d.2
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                    b.c(d.this, "Error during audio prompt: " + i);
                    d.a(d.this, true);
                    return true;
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: d.3
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    b.a(d.this, "Audio prompt completed");
                    d.a(d.this, false);
                }
            });
            return mediaPlayer;
        } catch (Exception e) {
            b.a(this, "Unable to create MediaPlayer for audio prompt", e);
            try {
                mediaPlayer.release();
            } catch (Exception e2) {
            }
            return null;
        }
    }

    static /* synthetic */ az f(d dVar) {
        dVar.b = null;
        return null;
    }

    static /* synthetic */ Object g(d dVar) {
        dVar.c = null;
        return null;
    }

    @Override // defpackage.ba
    public final void a() {
        if (this.a == null) {
            return;
        }
        this.d = false;
        if (this.a.isPlaying()) {
            try {
                this.a.stop();
            } catch (Throwable th) {
                b.a(this, "Error stopping player", th);
            }
            this.a.release();
            this.a = null;
            this.a = e();
            if (this.a == null) {
                c();
            }
        }
    }

    public final void a(AssetFileDescriptor assetFileDescriptor) {
        this.f = assetFileDescriptor;
        this.a = e();
        if (this.a == null) {
            c();
        }
    }

    @Override // defpackage.ba
    public final void a(Object obj, az azVar, Object obj2) {
        if (this.a == null || this.e) {
            b.c(this, "Can't start disposed audio prompt");
            azVar.a(obj2);
            return;
        }
        b.a(this, "Starting audio prompt");
        this.b = azVar;
        this.c = obj2;
        if (!this.a.isPlaying()) {
            d();
            return;
        }
        b.b(this, "Audio prompt is already playing. Stopping to restart.");
        this.a.stop();
        this.d = true;
    }

    @Override // defpackage.ba
    public final boolean b() {
        return this.e;
    }

    @Override // defpackage.ba
    public final void c() {
        this.e = true;
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
        if (this.f != null) {
            try {
                this.f.close();
            } catch (IOException e) {
                b.a(this, "Error closing audio prompt file", e);
            }
            this.f = null;
        }
        if (this.b != null) {
            this.b.a(this.c);
            this.b = null;
        }
        this.c = null;
    }
}
