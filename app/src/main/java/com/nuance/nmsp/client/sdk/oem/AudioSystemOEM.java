package com.nuance.nmsp.client.sdk.oem;

import android.content.Context;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.games.GamesStatusCodes;
import defpackage.bi;
import defpackage.bj;
import defpackage.bm;
import defpackage.bn;
import defpackage.bq;
import defpackage.cz;
import defpackage.eo;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class AudioSystemOEM implements bj, bn.b {
    private static Object C = new Object();
    private static Object D = new Object();
    private static Object E = new Object();
    private static final Integer P;
    private static final Integer Q;
    private static final Integer R;
    public static boolean a;
    public static Object b;
    public static Object c;
    public static int d;
    public static int e;
    private c A;
    private d B;
    private EndPointerOEM F;
    private short[] G;
    private byte[] H;
    private int I;
    private int J;
    private int K;
    private b L;
    private boolean M;
    private bn N;
    private boolean O;
    private Context S;
    private bi U;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;
    private bm f = defpackage.d.a(getClass());
    private eo T = null;

    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    public static class b {
        public static b a = new b();
        public static b b = new b();

        private b() {
        }
    }

    class c extends Thread implements AudioTrack.OnPlaybackPositionUpdateListener {
        private bm b;
        private Handler c;
        private AudioTrack d;
        private byte[] e;
        private short[] f;
        private boolean g;
        private int h;
        private int i;
        private boolean j;
        private int k;
        private boolean l;
        private boolean m;
        private bj.a n;
        private bj.c o;
        private bj.m p;
        private int q;

        private c() {
            this.b = defpackage.d.a(getClass());
            this.j = true;
            this.l = false;
            this.m = false;
            this.n = null;
            this.o = null;
            this.p = null;
            this.q = 0;
        }

        /* synthetic */ c(AudioSystemOEM audioSystemOEM, byte b) {
            this();
        }

        private int a(short[] sArr, int i, bj.i iVar) throws a {
            int iDecodeSpeex;
            if (i == 0) {
                return 0;
            }
            if (AudioSystemOEM.this.L == b.a) {
                int i2 = AudioSystemOEM.e - AudioSystemOEM.d;
                if (i2 >= i) {
                    System.arraycopy(AudioSystemOEM.this.G, AudioSystemOEM.d, sArr, 0, i);
                    AudioSystemOEM.d += i;
                    return i;
                }
                if (i2 > 0) {
                    System.arraycopy(AudioSystemOEM.this.G, AudioSystemOEM.d, sArr, 0, i2);
                }
                AudioSystemOEM.d = 0;
                this.n.a(null, AudioSystemOEM.this.H, new bj.i(0), iVar, null);
                if (iVar.a > 0) {
                    synchronized (AudioSystemOEM.c) {
                        iDecodeSpeex = AudioSystemOEM.decodeSpeex(AudioSystemOEM.this.H, iVar.a, AudioSystemOEM.this.G, AudioSystemOEM.this.G.length);
                        AudioSystemOEM.e = iDecodeSpeex;
                        if (iDecodeSpeex > AudioSystemOEM.this.G.length) {
                            AudioSystemOEM.e = AudioSystemOEM.this.G.length;
                        }
                    }
                    if (iDecodeSpeex < 0) {
                        throw new a("Call to decodeSpeex() failed with code: " + AudioSystemOEM.speexCodeToString(iDecodeSpeex));
                    }
                    if (this.b.b()) {
                        this.b.b("Obtained " + iDecodeSpeex + " shorts of PCM data after decoding SPEEX.");
                    }
                }
                if (i2 > 0) {
                    return i2;
                }
            }
            return 0;
        }

        static /* synthetic */ Object a() {
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(a aVar) {
            synchronized (AudioSystemOEM.D) {
                if (this.b.e()) {
                    this.b.e(aVar.getMessage());
                }
                if (this.d != null) {
                    if (this.d.getPlayState() != 1) {
                        this.d.stop();
                    }
                    this.d.release();
                    Looper looperMyLooper = Looper.myLooper();
                    if (looperMyLooper != null) {
                        looperMyLooper.quit();
                    }
                    this.j = false;
                }
                if (AudioSystemOEM.this.T != null) {
                    AudioSystemOEM.this.T.e();
                    AudioSystemOEM.this.j();
                }
                this.o.a(bj.b.b, null);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() throws a {
            int i;
            int playbackHeadPosition;
            int i2;
            int i3;
            int playbackHeadPosition2 = 0;
            synchronized (AudioSystemOEM.D) {
                if (this.b.b()) {
                    this.b.b("++++++++++++========== onPlay()");
                }
                if (AudioSystemOEM.this.L == b.a) {
                    if (this.j) {
                        bj.i iVar = new bj.i(AudioSystemOEM.this.k);
                        short[] sArr = new short[AudioSystemOEM.this.k];
                        i2 = 0;
                        while (i2 < AudioSystemOEM.this.k) {
                            synchronized (AudioSystemOEM.E) {
                                if (this.l) {
                                    this.j = false;
                                    i2 = 0;
                                    break;
                                }
                                int iA = a(sArr, AudioSystemOEM.this.k - i2, iVar);
                                if (iA == 0) {
                                    try {
                                        sleep(300L);
                                    } catch (InterruptedException e) {
                                    }
                                }
                                if (iVar.a == -1) {
                                    if (this.b.b()) {
                                        this.b.b("No more data to playback.");
                                    }
                                    this.j = false;
                                    break;
                                } else if (iVar.a > 0 && iA > 0) {
                                    System.arraycopy(sArr, 0, this.f, i2, iA);
                                    i2 += iA;
                                }
                            }
                        }
                    } else {
                        i2 = 0;
                    }
                    if (i2 > 0 && i2 < AudioSystemOEM.this.k) {
                        if (this.b.b()) {
                            this.b.b("Filling buffer with silence.");
                        }
                        for (int i4 = i2; i4 < AudioSystemOEM.this.k; i4++) {
                            this.f[i4] = 0;
                        }
                    }
                    if (i2 > 0) {
                        while (i2 != 0) {
                            int iWrite = this.d.write(this.f, playbackHeadPosition2, AudioSystemOEM.this.k);
                            if (iWrite <= 0) {
                                break;
                            }
                            i2 -= iWrite;
                            playbackHeadPosition2 += iWrite;
                            this.i = iWrite + this.i;
                        }
                        int notificationMarkerPosition = this.d.setNotificationMarkerPosition(this.i);
                        if (notificationMarkerPosition != 0) {
                            throw new a("Call to AudioTrack.setNotificationMarkerPosition() SPEEX 0 failed with code: " + notificationMarkerPosition);
                        }
                        this.c.sendMessage(this.c.obtainMessage(1));
                    } else {
                        if (this.i < AudioSystemOEM.this.r) {
                            int i5 = AudioSystemOEM.this.r - this.i;
                            short[] sArr2 = new short[i5];
                            while (playbackHeadPosition2 < i5) {
                                sArr2[playbackHeadPosition2] = 0;
                                playbackHeadPosition2++;
                            }
                            int iWrite2 = this.d.write(sArr2, 0, i5);
                            if (iWrite2 > 0) {
                                this.i = iWrite2 + this.i;
                            }
                            int notificationMarkerPosition2 = this.d.setNotificationMarkerPosition(this.i);
                            if (notificationMarkerPosition2 != 0) {
                                throw new a("Call to AudioTrack.setNotificationMarkerPosition() SPEEX 1 failed with code: " + notificationMarkerPosition2);
                            }
                            this.c.sendMessage(this.c.obtainMessage(1));
                            return;
                        }
                        if (this.b.b()) {
                            this.b.b("onPlay() AudioTrack status is :" + this.d.getState());
                        }
                        if (this.d.getState() != 0) {
                            playbackHeadPosition2 = this.d.getPlaybackHeadPosition();
                            i3 = playbackHeadPosition2 == this.k ? this.i : playbackHeadPosition2;
                            this.k = i3;
                        } else {
                            i3 = 0;
                        }
                        if (i3 >= this.i || this.d.getState() == 0) {
                            long j = i3 > playbackHeadPosition2 ? ((i3 - playbackHeadPosition2) * 1000) / AudioSystemOEM.this.K : 0L;
                            if (this.b.b()) {
                                this.b.b("calculating the delay......");
                                this.b.b("parameter pos = " + i3);
                                this.b.b("parameter prev_pos = " + playbackHeadPosition2);
                                this.b.b("parameter delay = " + j);
                            }
                            AudioSystemOEM.this.N.a(new bq() { // from class: com.nuance.nmsp.client.sdk.oem.AudioSystemOEM.c.2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    if (c.this.b.b()) {
                                        c.this.b.b("++++++++++++========== stop mTrack: " + c.this.d);
                                    }
                                    synchronized (AudioSystemOEM.E) {
                                        if (!c.this.l && !c.this.m) {
                                            synchronized (AudioSystemOEM.D) {
                                                if (c.this.d.getState() != 0) {
                                                    c.this.d.stop();
                                                }
                                                c.this.d.flush();
                                                c.this.d.release();
                                                if (AudioSystemOEM.this.T != null) {
                                                    AudioSystemOEM.this.T.e();
                                                    AudioSystemOEM.this.j();
                                                }
                                                bj.c cVar = c.this.o;
                                                bj.b bVar = bj.b.a;
                                                c cVar2 = c.this;
                                                cVar.a(bVar, c.a());
                                                synchronized (AudioSystemOEM.c) {
                                                    if (AudioSystemOEM.a) {
                                                        AudioSystemOEM.decodeCleanupSpeex();
                                                        AudioSystemOEM.this.G = null;
                                                        AudioSystemOEM.this.H = null;
                                                        AudioSystemOEM.a = false;
                                                    } else if (c.this.b.b()) {
                                                        c.this.b.b("Speex was already cleaned up");
                                                    }
                                                }
                                                c.this.c.sendMessage(c.this.c.obtainMessage(3));
                                            }
                                        } else if (c.this.b.b()) {
                                            c.this.b.b("AudioSystemOEM was already stopping");
                                        }
                                    }
                                }
                            }, j);
                        }
                    }
                } else {
                    if (this.j) {
                        bj.i iVar2 = new bj.i(AudioSystemOEM.this.l);
                        this.n.a(this.e, null, iVar2, new bj.i(0), null);
                        i = iVar2.a;
                        if (this.b.b()) {
                            bm bmVar = this.b;
                            StringBuilder sb = new StringBuilder("PLAYBACK: Sample #");
                            int i6 = this.q;
                            this.q = i6 + 1;
                            bmVar.b(sb.append(i6).append(". Asked for ").append(AudioSystemOEM.this.l).append(" bytes of data, ").append(i).append(" bytes received.").toString());
                        }
                    } else {
                        i = 0;
                    }
                    if (i == 0 && this.h < AudioSystemOEM.this.l) {
                        if (this.b.b()) {
                            this.b.b("Filling buffer with silence.");
                        }
                        i = AudioSystemOEM.this.l - this.h;
                        for (int i7 = 0; i7 < i; i7++) {
                            this.e[i7] = 0;
                        }
                    }
                    if (i != -1) {
                        while (i != 0) {
                            int iWrite3 = this.d.write(this.e, playbackHeadPosition2, i);
                            if (iWrite3 <= 0) {
                                break;
                            }
                            i -= iWrite3;
                            playbackHeadPosition2 += iWrite3;
                            this.h = iWrite3 + this.h;
                        }
                        int notificationMarkerPosition3 = this.d.setNotificationMarkerPosition(this.h / 2);
                        if (notificationMarkerPosition3 != 0) {
                            throw new a("Call to AudioTrack.setNotificationMarkerPosition() PCM 0 failed with code: " + notificationMarkerPosition3);
                        }
                        this.c.sendMessage(this.c.obtainMessage(1));
                    } else {
                        if (this.h < AudioSystemOEM.this.s) {
                            int i8 = AudioSystemOEM.this.s - this.h;
                            byte[] bArr = new byte[i8];
                            while (playbackHeadPosition2 < i8) {
                                bArr[playbackHeadPosition2] = 0;
                                playbackHeadPosition2++;
                            }
                            int iWrite4 = this.d.write(bArr, 0, i8);
                            if (iWrite4 > 0) {
                                this.h = iWrite4 + this.h;
                            }
                            int notificationMarkerPosition4 = this.d.setNotificationMarkerPosition(this.h / 2);
                            if (notificationMarkerPosition4 != 0) {
                                throw new a("Call to AudioTrack.setNotificationMarkerPosition() PCM 1 failed with code: " + notificationMarkerPosition4);
                            }
                            this.c.sendMessage(this.c.obtainMessage(1));
                            return;
                        }
                        if (this.b.b()) {
                            this.b.b("onPlay() AudioTrack status is :" + this.d.getState());
                        }
                        if (this.d.getState() != 0) {
                            playbackHeadPosition = this.d.getPlaybackHeadPosition();
                            playbackHeadPosition2 = playbackHeadPosition == this.k ? this.h / 2 : playbackHeadPosition;
                            this.k = playbackHeadPosition2;
                        } else {
                            playbackHeadPosition = 0;
                        }
                        if (playbackHeadPosition2 >= this.h / 2) {
                            long j2 = playbackHeadPosition2 > playbackHeadPosition ? ((playbackHeadPosition2 - playbackHeadPosition) * 1000) / AudioSystemOEM.this.K : 0L;
                            if (j2 < AudioSystemOEM.this.q) {
                                j2 = AudioSystemOEM.this.q;
                            }
                            AudioSystemOEM.this.N.a(new bq() { // from class: com.nuance.nmsp.client.sdk.oem.AudioSystemOEM.c.3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    synchronized (AudioSystemOEM.D) {
                                        c.this.d.stop();
                                        c.this.d.flush();
                                        c.this.d.release();
                                        if (AudioSystemOEM.this.T != null) {
                                            AudioSystemOEM.this.T.e();
                                            AudioSystemOEM.this.j();
                                        }
                                        bj.c cVar = c.this.o;
                                        bj.b bVar = bj.b.a;
                                        c cVar2 = c.this;
                                        cVar.a(bVar, c.a());
                                        Looper looperMyLooper = Looper.myLooper();
                                        if (looperMyLooper != null) {
                                            looperMyLooper.quit();
                                        }
                                        c.j(c.this);
                                    }
                                }
                            }, j2);
                        }
                    }
                }
            }
        }

        static /* synthetic */ void b(c cVar) {
            if (cVar.b.b()) {
                cVar.b.b("Calling AudioSystemOEM.onStop()");
            }
            synchronized (AudioSystemOEM.D) {
                try {
                    cVar.d.stop();
                } catch (IllegalStateException e) {
                    if (cVar.b.b()) {
                        cVar.b.b("Could not stop audioTrack.");
                    }
                }
                cVar.j = false;
                cVar.d.flush();
                cVar.d.release();
                if (AudioSystemOEM.this.T != null) {
                    AudioSystemOEM.this.T.e();
                    AudioSystemOEM.this.j();
                }
                if (AudioSystemOEM.this.L == b.a) {
                    synchronized (AudioSystemOEM.c) {
                        if (AudioSystemOEM.a) {
                            AudioSystemOEM.decodeCleanupSpeex();
                            AudioSystemOEM.this.G = null;
                            AudioSystemOEM.this.H = null;
                            AudioSystemOEM.a = false;
                        } else if (cVar.b.b()) {
                            cVar.b.b("Attempting to cleanup speex decode while it is already stopped");
                        }
                    }
                    cVar.p.a(bj.b.a);
                    Looper looperMyLooper = Looper.myLooper();
                    if (looperMyLooper != null) {
                        looperMyLooper.quit();
                    }
                }
            }
        }

        static /* synthetic */ boolean c(c cVar) {
            cVar.m = true;
            return true;
        }

        static /* synthetic */ boolean j(c cVar) {
            cVar.j = false;
            return false;
        }

        public final void a(bj.a aVar, bj.c cVar) throws a {
            if (AudioSystemOEM.this.T != null) {
                AudioSystemOEM.this.T.a();
            }
            synchronized (this) {
                this.q = 0;
                this.n = aVar;
                this.o = cVar;
                if (AudioSystemOEM.this.L == b.a) {
                    synchronized (AudioSystemOEM.c) {
                        int iDecodeInitSpeex = AudioSystemOEM.decodeInitSpeex(AudioSystemOEM.this.I, 0, AudioSystemOEM.this.K);
                        if (iDecodeInitSpeex < 0) {
                            throw new a("decodeInitSpeex failed with code: " + AudioSystemOEM.speexCodeToString(iDecodeInitSpeex));
                        }
                        AudioSystemOEM.this.G = new short[AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END];
                        AudioSystemOEM.this.H = new byte[AccessibilityEventCompat.TYPE_GESTURE_DETECTION_END];
                        AudioSystemOEM.a = true;
                    }
                    AudioSystemOEM.d = 0;
                    AudioSystemOEM.e = 0;
                }
                start();
                while (isAlive() && this.c == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
                if (isAlive()) {
                    if (this.c != null) {
                        this.c.sendMessage(this.c.obtainMessage(1));
                    }
                }
            }
        }

        public final void a(bj.m mVar) {
            synchronized (AudioSystemOEM.E) {
                this.l = true;
            }
            synchronized (AudioSystemOEM.D) {
                this.j = false;
            }
            synchronized (this) {
                this.p = mVar;
                if (!isAlive() || this.g) {
                    return;
                }
                if (this.c != null) {
                    this.c.sendMessage(this.c.obtainMessage(2));
                }
                while (isAlive() && !this.g) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
        public final void onMarkerReached(AudioTrack audioTrack) {
            try {
                b();
            } catch (a e) {
                a(e);
            }
        }

        @Override // android.media.AudioTrack.OnPlaybackPositionUpdateListener
        public final void onPeriodicNotification(AudioTrack audioTrack) {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Looper.prepare();
            synchronized (this) {
                this.c = new Handler() { // from class: com.nuance.nmsp.client.sdk.oem.AudioSystemOEM.c.1
                    @Override // android.os.Handler
                    public final void handleMessage(Message message) {
                        switch (message.what) {
                            case 1:
                                try {
                                    c.this.b();
                                } catch (a e) {
                                    c.this.a(e);
                                    return;
                                }
                                break;
                            case 2:
                                c.b(c.this);
                                break;
                            case 3:
                                Looper looperMyLooper = Looper.myLooper();
                                if (looperMyLooper != null) {
                                    looperMyLooper.quit();
                                }
                                c.c(c.this);
                                break;
                        }
                    }
                };
                try {
                    synchronized (AudioSystemOEM.D) {
                        try {
                            if (this.b.b()) {
                                this.b.b("Creating Audio Track");
                            }
                            this.e = new byte[AudioSystemOEM.this.l];
                            this.f = new short[AudioSystemOEM.this.p];
                            try {
                                this.k = 0;
                                this.d = new AudioTrack(AudioSystemOEM.this.g, AudioSystemOEM.this.i, 2, 2, AudioSystemOEM.this.m, AudioSystemOEM.this.h);
                                if (this.d == null || this.d.getState() != 1) {
                                    throw new a("AudioTrack object has not been initialized correctly. One or several parameters used to create it must be wrong.");
                                }
                                this.d.setPlaybackPositionUpdateListener(this);
                                this.d.play();
                            } catch (IllegalArgumentException e) {
                                throw new a("Could not instanciate AudioTrack object.");
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    notifyAll();
                } catch (a e2) {
                    a(e2);
                    return;
                }
            }
            Looper.loop();
            synchronized (this) {
                this.g = true;
                notifyAll();
            }
        }
    }

    class d implements AudioRecord.OnRecordPositionUpdateListener, bn.b {
        private bm a;
        private AudioRecord b;
        private boolean c;
        private bj.a d;
        private bj.d e;
        private bj.l f;
        private bj.e g;
        private bj.f h;
        private bj.m i;
        private bj.g j;
        private boolean k;
        private boolean l;
        private int m;

        private d() {
            this.a = defpackage.d.a(getClass());
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = null;
            this.j = null;
            this.k = false;
            this.l = false;
            this.m = 0;
        }

        /* synthetic */ d(AudioSystemOEM audioSystemOEM, byte b) {
            this();
        }

        private static float a(short[] sArr) {
            double dLog10;
            long j = 0;
            for (long j2 : sArr) {
                j += (j2 * j2) >> 9;
            }
            double d = j / 1.073741824E9d;
            if (d < 1.0E-9d) {
                dLog10 = -90.0d;
            } else {
                dLog10 = Math.log10(d) * 10.0d;
                if (dLog10 > 0.0d) {
                    dLog10 = 0.0d;
                }
            }
            return (float) dLog10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(a aVar) {
            if (this.a.e()) {
                this.a.e(aVar.getMessage());
            }
            if (this.c) {
                this.b.stop();
                synchronized (AudioSystemOEM.C) {
                    this.c = false;
                    this.b.release();
                    this.b = null;
                }
                this.j.d();
                if (AudioSystemOEM.this.L == b.a) {
                    synchronized (AudioSystemOEM.b) {
                        AudioSystemOEM.encodeCleanupSpeex();
                    }
                }
                if (AudioSystemOEM.this.T != null) {
                    AudioSystemOEM.this.T.e();
                    AudioSystemOEM.this.j();
                }
                if (this.i != null) {
                    this.i.a(bj.b.b);
                }
            }
        }

        public final void a(bj.e eVar) {
            this.g = eVar;
            bn bnVar = AudioSystemOEM.this.N;
            Object[] objArr = {AudioSystemOEM.P};
            Object objB = AudioSystemOEM.this.N.b();
            AudioSystemOEM.this.N.a();
            bnVar.a(objArr, this, objB);
        }

        public final void a(bj.f fVar) {
            this.h = fVar;
            bn bnVar = AudioSystemOEM.this.N;
            Object[] objArr = {AudioSystemOEM.Q};
            Object objB = AudioSystemOEM.this.N.b();
            AudioSystemOEM.this.N.a();
            bnVar.a(objArr, this, objB);
        }

        public final void a(bj.m mVar, bj.d dVar) {
            boolean z = false;
            if (this.c) {
                try {
                    Thread.sleep(AudioSystemOEM.this.v);
                    if (this.c) {
                        this.b.stop();
                        z = true;
                    }
                    synchronized (AudioSystemOEM.C) {
                        this.c = false;
                        if (z) {
                            this.b.release();
                            this.b = null;
                        }
                    }
                    if (z && AudioSystemOEM.this.L == b.a) {
                        synchronized (AudioSystemOEM.b) {
                            AudioSystemOEM.encodeCleanupSpeex();
                        }
                    }
                } catch (IllegalStateException e) {
                    if (this.a.b()) {
                        this.a.b("Could not stop audioRecord.");
                    }
                } catch (Exception e2) {
                }
            }
            if (AudioSystemOEM.this.T != null) {
                AudioSystemOEM.this.T.e();
                AudioSystemOEM.this.j();
            }
            if (dVar != null) {
                dVar.a();
            }
            if (mVar != null) {
                mVar.a(bj.b.a);
            }
        }

        @Override // bn.b
        public final void a(Object obj, Object obj2) {
            Object[] objArr = (Object[]) obj;
            if (((Integer) objArr[0]).intValue() == AudioSystemOEM.P.intValue()) {
                if (this.k) {
                    return;
                }
                this.k = true;
                AudioSystemOEM.this.F.b();
                this.l = false;
                if (this.g != null) {
                    this.g.b();
                    return;
                }
                return;
            }
            if (((Integer) objArr[0]).intValue() == AudioSystemOEM.Q.intValue()) {
                if (this.k) {
                    this.k = false;
                    if (this.h != null) {
                        this.h.c();
                        return;
                    }
                    return;
                }
                return;
            }
            if (((Integer) objArr[0]).intValue() == AudioSystemOEM.R.intValue()) {
                int iIntValue = ((Integer) objArr[1]).intValue();
                Object obj3 = objArr[2];
                if (this.c) {
                    if (AudioSystemOEM.this.L != b.a) {
                        if (this.c) {
                            byte[] bArr = (byte[]) obj3;
                            if (iIntValue < 0) {
                                a(new a("Call to AudioRecord.read() failed with code: " + iIntValue));
                                return;
                            }
                            if (iIntValue == 0) {
                                if (this.a.b()) {
                                    this.a.b("AudioRecorder has no audio.");
                                    return;
                                }
                                return;
                            }
                            if (this.a.b()) {
                                bm bmVar = this.a;
                                StringBuilder sb = new StringBuilder("RECORDING: Sample #");
                                int i = this.m;
                                this.m = i + 1;
                                bmVar.b(sb.append(i).append(". Sending ").append(iIntValue).append(" bytes from recorder.").toString());
                            }
                            this.d.a(bArr, null, new bj.i(iIntValue), new bj.i(0), null);
                            return;
                        }
                        return;
                    }
                    byte[] bArr2 = new byte[AudioSystemOEM.this.z];
                    if (this.c) {
                        short[] sArr = (short[]) obj3;
                        if (iIntValue < 0) {
                            a(new a("Call to AudioRecord.read() failed with code: " + iIntValue));
                            return;
                        }
                        if (iIntValue == 0) {
                            if (this.a.b()) {
                                this.a.b("AudioRecorder has no audio.");
                                return;
                            }
                            return;
                        }
                        EndPointerOEM.a aVar = EndPointerOEM.a.a;
                        synchronized (AudioSystemOEM.b) {
                            if (this.c) {
                                int iEncodeSpeex = AudioSystemOEM.encodeSpeex(sArr, bArr2, AudioSystemOEM.this.z);
                                EndPointerOEM.a aVarA = (!this.k || this.l) ? aVar : AudioSystemOEM.this.F.a();
                                if (iEncodeSpeex < 0) {
                                    a(new a("Call to encodeSpeex() failed with code: " + AudioSystemOEM.speexCodeToString(iEncodeSpeex)));
                                    return;
                                }
                                if (this.a.b()) {
                                    bm bmVar2 = this.a;
                                    StringBuilder sb2 = new StringBuilder("RECORDING: Sample #");
                                    int i2 = this.m;
                                    this.m = i2 + 1;
                                    bmVar2.b(sb2.append(i2).append(". Got ").append(iIntValue).append(" shorts from recorder, sending ").append(iEncodeSpeex).append(" SPEEX vocoded bytes. ").toString());
                                }
                                if (this.a.b()) {
                                    if (aVarA == EndPointerOEM.a.b) {
                                        this.a.b("ENDPOINTING SPEECH_END");
                                    } else if (aVarA == EndPointerOEM.a.c) {
                                        this.a.b("ENDPOINTING SPEECH_START");
                                    }
                                }
                                float fA = AudioSystemOEM.this.M ? (long) (a(sArr) + 90.0f) : -1.0f;
                                this.d.a(null, bArr2, new bj.i(0), new bj.i(iEncodeSpeex), fA < 0.0f ? null : Float.valueOf(fA));
                                if (aVarA == EndPointerOEM.a.b) {
                                    this.l = true;
                                    if (!AudioSystemOEM.this.O) {
                                        a(this.i, this.e);
                                    } else if (this.e != null) {
                                        this.e.a();
                                    }
                                }
                                if (aVarA == EndPointerOEM.a.c) {
                                    this.f.e();
                                }
                            }
                        }
                    }
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:30:0x009d A[Catch: all -> 0x006f, IllegalArgumentException -> 0x00a5, TryCatch #2 {IllegalArgumentException -> 0x00a5, blocks: (B:26:0x0075, B:28:0x0094, B:30:0x009d, B:31:0x00a4), top: B:60:0x0075, outer: #3 }] */
        public final void a(boolean z, bj.a aVar, bj.m mVar, bj.g gVar, bj.l lVar, bj.d dVar, bj.e eVar, bj.f fVar) throws a {
            int iEncodeInitSpeex;
            if (AudioSystemOEM.this.T != null) {
                AudioSystemOEM.this.T.a();
            }
            synchronized (AudioSystemOEM.C) {
                this.m = 0;
                this.d = aVar;
                this.e = dVar;
                this.f = lVar;
                this.g = eVar;
                this.h = fVar;
                this.i = mVar;
                this.j = gVar;
                if (z) {
                    a(this.g);
                }
                if (AudioSystemOEM.this.L != b.a) {
                    this.b = new AudioRecord(AudioSystemOEM.this.t, AudioSystemOEM.this.u, 2, 2, AudioSystemOEM.this.y);
                    if (this.b != null) {
                        throw new a("AudioRecord object has not been initialized correctly. One or several parameters used to create it must be wrong.");
                    }
                    throw new a("AudioRecord object has not been initialized correctly. One or several parameters used to create it must be wrong.");
                }
                synchronized (AudioSystemOEM.b) {
                    iEncodeInitSpeex = AudioSystemOEM.encodeInitSpeex(AudioSystemOEM.this.I, AudioSystemOEM.this.K, 3, AudioSystemOEM.this.J, 1);
                }
                if (iEncodeInitSpeex < 0) {
                    throw new a("encodeInitSpeex failed with code:" + AudioSystemOEM.speexCodeToString(iEncodeInitSpeex));
                }
                try {
                    this.b = new AudioRecord(AudioSystemOEM.this.t, AudioSystemOEM.this.u, 2, 2, AudioSystemOEM.this.y);
                    if (this.b != null || this.b.getState() != 1) {
                        throw new a("AudioRecord object has not been initialized correctly. One or several parameters used to create it must be wrong.");
                    }
                    this.c = true;
                    this.b.setRecordPositionUpdateListener(this);
                    int positionNotificationPeriod = this.b.setPositionNotificationPeriod(AudioSystemOEM.this.w);
                    if (positionNotificationPeriod != 0) {
                        throw new a("Call to AudioRecord.setPositionNotificationPeriod() failed with code:" + positionNotificationPeriod);
                    }
                    try {
                        this.b.startRecording();
                        int i = 0;
                        short[] sArr = new short[AudioSystemOEM.this.w];
                        do {
                            int i2 = this.b.read(sArr, i, AudioSystemOEM.this.w - i);
                            if (i2 != -3 && i2 != -2) {
                                if (i2 <= 0) {
                                    break;
                                } else {
                                    i += i2;
                                }
                            } else {
                                throw new a("Call to AudioRecord.read() failed with code:" + i2);
                            }
                        } while (i < AudioSystemOEM.this.w);
                    } catch (IllegalStateException e) {
                        throw new a("Call to AudioRecord.startRecording() failed.");
                    }
                } catch (IllegalArgumentException e2) {
                    throw new a("Could not instanciate AudioRecord object.");
                }
                throw th;
            }
        }

        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public final void onMarkerReached(AudioRecord audioRecord) {
        }

        @Override // android.media.AudioRecord.OnRecordPositionUpdateListener
        public final void onPeriodicNotification(AudioRecord audioRecord) {
            int i = 0;
            if (AudioSystemOEM.this.L == b.a) {
                synchronized (AudioSystemOEM.C) {
                    if (this.c) {
                        short[] sArr = new short[AudioSystemOEM.this.w];
                        do {
                            int i2 = audioRecord.read(sArr, i, AudioSystemOEM.this.w - i);
                            if (i2 == -3 || i2 == -2 || i2 <= 0) {
                                break;
                            } else {
                                i += i2;
                            }
                        } while (i < AudioSystemOEM.this.w);
                        bn bnVar = AudioSystemOEM.this.N;
                        Object[] objArr = {AudioSystemOEM.R, new Integer(i), sArr};
                        Object objB = AudioSystemOEM.this.N.b();
                        AudioSystemOEM.this.N.a();
                        bnVar.a(objArr, this, objB);
                    }
                }
                return;
            }
            synchronized (AudioSystemOEM.C) {
                if (this.c) {
                    byte[] bArr = new byte[AudioSystemOEM.this.x];
                    do {
                        int i3 = audioRecord.read(bArr, i, AudioSystemOEM.this.x - i);
                        if (i3 == -3 || i3 == -2 || i3 <= 0) {
                            break;
                        } else {
                            i += i3;
                        }
                    } while (i < AudioSystemOEM.this.x);
                    bn bnVar2 = AudioSystemOEM.this.N;
                    Object[] objArr2 = {AudioSystemOEM.R, new Integer(i), bArr};
                    Object objB2 = AudioSystemOEM.this.N.b();
                    AudioSystemOEM.this.N.a();
                    bnVar2.a(objArr2, this, objB2);
                }
            }
        }
    }

    static {
        System.loadLibrary("nmsp_speex");
        a = false;
        b = new Object();
        c = new Object();
        d = 0;
        e = 0;
        P = new Integer(1);
        Q = new Integer(2);
        R = new Integer(3);
    }

    public AudioSystemOEM(bn bnVar, bi biVar, Vector vector) {
        int i = 0;
        this.F = null;
        this.M = false;
        this.O = false;
        this.S = null;
        this.U = biVar;
        a(biVar);
        this.N = bnVar;
        this.F = new EndPointerOEM();
        this.F.a(vector);
        if (vector == null) {
            return;
        }
        while (true) {
            int i2 = i;
            if (i2 >= vector.size()) {
                return;
            }
            cz czVar = (cz) vector.get(i2);
            String strA = czVar.a();
            if (czVar.d() == cz.a.a) {
                if (strA.equals("USE_ENERGY_LEVEL")) {
                    if (new String(czVar.b()).equalsIgnoreCase("TRUE")) {
                        if (this.f.b()) {
                            this.f.b("Use energy level is activated.");
                        }
                        this.M = true;
                    }
                } else if (strA.equals("NMSP_DEFINES_RECORDER_CONTINUES_ON_ENDPOINTER_AND_TIMER_STOPPING")) {
                    if (new String(czVar.b()).equalsIgnoreCase("TRUE")) {
                        if (this.f.b()) {
                            this.f.b("_continuesOnEndPointerAndTimerStopping is activated.");
                        }
                        this.O = true;
                    }
                } else if (strA.equals("NMSP_DEFINES_CAPTURING_CONTINUES_ON_ENDPOINTER")) {
                    if (new String(czVar.b()).equalsIgnoreCase("TRUE") && this.f.b()) {
                        this.f.b("_capturingContinuesOnEndPointer is activated.");
                    }
                } else if (strA.equals("Android_Context")) {
                    this.S = (Context) czVar.c();
                    if (this.f.b()) {
                        this.f.b("NMSP_DEFINES_ANDROID_CONTEXT is passed in as" + this.S);
                    }
                }
            }
            i = i2 + 1;
        }
    }

    private void a(bi biVar) {
        if (biVar == bi.d) {
            this.L = b.b;
            this.K = GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY;
            this.I = -1;
            this.J = -1;
        } else if (biVar == bi.e) {
            this.L = b.b;
            this.K = 11000;
            this.I = -1;
            this.J = -1;
        } else if (biVar == bi.f) {
            this.L = b.b;
            this.K = 16000;
            this.I = -1;
            this.J = -1;
        } else if (biVar == bi.g) {
            this.L = b.b;
            this.K = 32000;
            this.I = -1;
            this.J = -1;
        } else if (biVar == bi.a) {
            this.L = b.a;
            this.K = GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY;
            this.J = 6;
            this.I = 0;
        } else if (biVar == bi.b || biVar == bi.c) {
            this.L = b.a;
            this.K = 16000;
            this.J = 8;
            this.I = 1;
        } else {
            if (this.f.e()) {
                this.f.e("Codec " + biVar + " is not handled, using PCM_16_8K by default.");
            }
            this.L = b.b;
            this.K = GamesStatusCodes.STATUS_MILESTONE_CLAIMED_PREVIOUSLY;
            this.I = -1;
            this.J = -1;
        }
        this.h = 1;
        this.i = this.K;
        this.q = 750;
        this.r = (this.q * this.i) / 1000;
        this.s = this.r << 1;
        this.j = 300;
        this.k = (this.i * this.j) / 1000;
        this.l = this.k << 1;
        int i = this.l * 5;
        int minBufferSize = AudioTrack.getMinBufferSize(this.i, 2, 2);
        if (minBufferSize == -2 || minBufferSize == -1 || minBufferSize <= i) {
            this.n = i;
        } else {
            this.n = minBufferSize;
        }
        this.m = this.n / 2;
        this.o = 500;
        this.p = (this.i * this.o) / 1000;
        this.u = this.K;
        this.v = 100;
        this.w = (this.u * this.v) / 1000;
        this.x = this.w << 1;
        int i2 = this.x * 3;
        int minBufferSize2 = AudioRecord.getMinBufferSize(this.u, 2, 2);
        if (minBufferSize2 == -2 || minBufferSize2 == -1 || minBufferSize2 <= i2) {
            this.y = i2;
        } else {
            this.y = minBufferSize2;
        }
        this.z = this.x;
    }

    public static native void decodeCleanupSpeex();

    public static native int decodeInitSpeex(int i, int i2, int i3);

    public static native int decodeSpeex(byte[] bArr, int i, short[] sArr, int i2);

    public static native void encodeCleanupSpeex();

    public static native int encodeInitSpeex(int i, int i2, int i3, int i4, int i5);

    public static native int encodeSpeex(short[] sArr, byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.T != null) {
            this.T.c();
            this.T = null;
        }
    }

    private void k() {
        if (this.U == bi.b || this.U == bi.c) {
            this.U = bi.a;
            a(this.U);
        } else if (this.U == bi.e || this.U == bi.f || this.U == bi.h || this.U == bi.g) {
            this.U = bi.d;
            a(this.U);
        }
    }

    public static native String speexCodeToString(int i);

    @Override // defpackage.bj
    public final void a(bj.e eVar) {
        if (this.f.b()) {
            this.f.b("++++++++++++========== turnOnEndPointer()");
        }
        if (eVar == null || this.B == null) {
            return;
        }
        this.B.a(eVar);
    }

    @Override // defpackage.bj
    public final void a(bj.f fVar) {
        if (this.f.b()) {
            this.f.b("++++++++++++========== turnOnEndPointer()");
        }
        if (fVar == null || this.B == null) {
            return;
        }
        this.B.a(fVar);
    }

    @Override // defpackage.bj
    public final void a(bj.m mVar) {
        if (this.f.b()) {
            this.f.b("++++++++++++========== stopRecording()");
        }
        if (mVar == null) {
            return;
        }
        if (this.B == null) {
            mVar.a(bj.b.a);
        } else {
            this.B.a(mVar, (bj.d) null);
        }
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        if (this.f.b()) {
            this.f.b("---------------------- AudioSystemAndroid +++++ handleMessage() Thread:" + Thread.currentThread());
        }
    }

    @Override // defpackage.bj
    public final boolean a() {
        this.f.c("++++++++++++========== pausePlayback() is not implemented");
        return false;
    }

    @Override // defpackage.bj
    public final boolean a(bj.h hVar, boolean z, bj.a aVar, bj.m mVar, bj.g gVar, bj.l lVar, bj.d dVar, bj.e eVar, bj.f fVar) {
        if (this.f.b()) {
            this.f.b("++++++++++++========== startRecording()");
        }
        if (this.B != null) {
            if (this.f.b()) {
                this.f.b("previous recording still running!");
            }
            return false;
        }
        if (aVar == null) {
            if (this.f.e()) {
                this.f.e("audioCallback cannot be null.");
            }
            return false;
        }
        if (this.S == null) {
            if (this.f.e()) {
                this.f.e("inputDevice is BLUETOOTH_HEADSET, but ANDROID_CONTEXT parameter is not passed in!!!");
            }
            return false;
        }
        this.T = eo.a(this.S);
        if (this.T.b()) {
            k();
        }
        if (hVar == bj.h.a) {
            this.t = 6;
            j();
        } else {
            if (hVar != bj.h.c) {
                if (this.f.e()) {
                    this.f.e("Unexpected inputDevice.");
                }
                j();
                return false;
            }
            this.t = this.T.g();
            if (!this.T.b()) {
                j();
            }
        }
        this.B = new d(this, (byte) 0);
        try {
            this.B.a(z, aVar, mVar, gVar, lVar, dVar, eVar, fVar);
            return true;
        } catch (a e2) {
            this.B.a(e2);
            this.B = null;
            return false;
        }
    }

    @Override // defpackage.bj
    public final boolean a(bj.k kVar, bj.a aVar, bj.c cVar) {
        byte b2 = 0;
        if (this.f.b()) {
            this.f.b("++++++++++++========== startPlayback()");
        }
        if (aVar == null) {
            if (!this.f.e()) {
                return false;
            }
            this.f.e("audioCallback cannot be null.");
            return false;
        }
        if (this.S == null) {
            if (!this.f.e()) {
                return false;
            }
            this.f.e("inputDevice is BLUETOOTH_HEADSET, but ANDROID_CONTEXT parameter is not passed in!!!");
            return false;
        }
        this.T = eo.a(this.S);
        if (this.T.b()) {
            k();
        }
        if (kVar == bj.k.a) {
            this.g = 3;
            j();
        } else {
            if (kVar != bj.k.c) {
                if (this.f.e()) {
                    this.f.e("Unexpected outputDevice.");
                }
                j();
                return false;
            }
            this.g = this.T.f();
            if (!this.T.b()) {
                j();
            }
        }
        this.A = new c(this, b2);
        try {
            this.A.a(aVar, cVar);
            return true;
        } catch (a e2) {
            this.A.a(e2);
            this.A = null;
            return false;
        }
    }

    @Override // defpackage.bj
    public final void b(bj.m mVar) {
        if (this.f.b()) {
            this.f.b("++++++++++++========== stopPlayback()");
        }
        if (this.A != null) {
            this.A.a(mVar);
        }
        this.A = null;
    }

    @Override // defpackage.bj
    public final boolean b() {
        this.f.c("++++++++++++========== previousPlayback() is not implemented");
        return false;
    }

    @Override // defpackage.bj
    public final boolean c() {
        this.f.c("++++++++++++========== nextPlayback() is not implemented");
        return false;
    }
}
