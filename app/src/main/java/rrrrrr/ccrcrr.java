package rrrrrr;

import android.os.SystemClock;
import com.immersion.content.EndpointWarp;
import com.immersion.hapticmediasdk.controllers.HapticPlaybackThread;

/* JADX INFO: loaded from: classes.dex */
public class ccrcrr implements Runnable {

    /* JADX INFO: renamed from: b044A044Aъъ044A044A, reason: contains not printable characters */
    public static int f108b044A044A044A044A = 2;

    /* JADX INFO: renamed from: b044Aъъъ044A044A, reason: contains not printable characters */
    public static int f109b044A044A044A = 45;

    /* JADX INFO: renamed from: bъ044Aъъ044A044A, reason: contains not printable characters */
    public static int f110b044A044A044A = 1;
    private final byte[] a;
    private final long b;

    /* JADX INFO: renamed from: bХ0425ХХХ0425, reason: contains not printable characters */
    public final /* synthetic */ HapticPlaybackThread f111b04250425;
    private final long c;
    private final int d;
    private final long e;

    public ccrcrr(HapticPlaybackThread hapticPlaybackThread, long j, long j2, byte[] bArr, int i, long j3) throws Exception {
        try {
            this.f111b04250425 = hapticPlaybackThread;
            this.a = bArr;
            this.b = j;
            int i2 = f109b044A044A044A;
            switch ((i2 * (f110b044A044A044A + i2)) % f108b044A044A044A044A) {
                case 0:
                    break;
                default:
                    f109b044A044A044A = 15;
                    f110b044A044A044A = m123b044A044A044A();
                    break;
            }
            try {
                this.c = j2;
                this.d = i;
                this.e = j3;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    /* JADX INFO: renamed from: bъъ044Aъ044A044A, reason: contains not printable characters */
    public static int m123b044A044A044A() {
        return 32;
    }

    @Override // java.lang.Runnable
    public void run() throws Exception {
        if (HapticPlaybackThread.m42b0411041104110411(this.f111b04250425)) {
            synchronized (this.f111b04250425.o) {
                HapticPlaybackThread.m46b0411041104110411(this.f111b04250425).remove(this);
            }
            if (this.b >= this.c) {
                if (HapticPlaybackThread.m56b0411041104110411(this.f111b04250425).areHapticsEnabled()) {
                    EndpointWarp endpointWarpM39b04110411041104110411 = HapticPlaybackThread.m39b04110411041104110411(this.f111b04250425);
                    while (true) {
                        switch (1) {
                            case 0:
                                break;
                            case 1:
                                break;
                            default:
                                while (true) {
                                    char c = 0;
                                    switch (c) {
                                        case 0:
                                            break;
                                        case 1:
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    endpointWarpM39b04110411041104110411.update(this.a, this.a.length, this.e, this.d);
                }
                synchronized (HapticPlaybackThread.m67b04110411(this.f111b04250425)) {
                    HapticPlaybackThread.m51b041104110411(this.f111b04250425, this.f111b04250425.e);
                    HapticPlaybackThread.m60b041104110411(this.f111b04250425, HapticPlaybackThread.m43b0411041104110411(this.f111b04250425));
                    HapticPlaybackThread.m64b041104110411(this.f111b04250425, SystemClock.uptimeMillis());
                }
            }
            HapticPlaybackThread.m58b041104110411(this.f111b04250425).post(HapticPlaybackThread.m47b0411041104110411(this.f111b04250425));
        }
    }
}
