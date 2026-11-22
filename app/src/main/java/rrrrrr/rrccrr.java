package rrrrrr;

import com.immersion.hapticmediasdk.MediaPlaybackSDK;
import com.immersion.hapticmediasdk.utils.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class rrccrr implements Runnable {
    private static final String a = "ValidateURL";

    /* JADX INFO: renamed from: b044A044Aъ044A044A044A, reason: contains not printable characters */
    public static int f143b044A044A044A044A044A = 2;

    /* JADX INFO: renamed from: b044Aъъ044A044A044A, reason: contains not printable characters */
    public static int f144b044A044A044A044A = 24;

    /* JADX INFO: renamed from: bъ044Aъ044A044A044A, reason: contains not printable characters */
    public static int f145b044A044A044A044A = 1;
    private URL b;

    /* JADX INFO: renamed from: b0425Х0425ХХ0425, reason: contains not printable characters */
    public final /* synthetic */ MediaPlaybackSDK f146b042504250425;

    public rrccrr(MediaPlaybackSDK mediaPlaybackSDK, String str) throws MalformedURLException {
        String str2 = null;
        this.f146b042504250425 = mediaPlaybackSDK;
        while (true) {
            try {
                int[] iArr = new int[-1];
            } catch (Exception e) {
                while (true) {
                    try {
                        str2.length();
                    } catch (Exception e2) {
                        while (true) {
                            try {
                                str2.length();
                            } catch (Exception e3) {
                                while (true) {
                                    switch (1) {
                                        case 0:
                                            break;
                                        case 1:
                                            break;
                                        default:
                                            while (true) {
                                                switch (1) {
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
                                this.b = new URL(str);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(int i) {
        synchronized (this) {
            MediaPlaybackSDK.m26b043B043B(this.f146b042504250425, i);
            notifyAll();
            while (true) {
                char c = 0;
                switch (c) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        while (true) {
                            switch (1) {
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
        }
    }

    /* JADX INFO: renamed from: bъъ044A044A044A044A, reason: contains not printable characters */
    public static int m135b044A044A044A044A() {
        return 6;
    }

    @Override // java.lang.Runnable
    public void run() throws Exception {
        try {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) this.b.openConnection();
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    httpURLConnection.setUseCaches(false);
                    int i = f144b044A044A044A044A;
                    switch ((i * (f145b044A044A044A044A + i)) % f143b044A044A044A044A044A) {
                        case 0:
                            break;
                        default:
                            f144b044A044A044A044A = 0;
                            f145b044A044A044A044A = m135b044A044A044A044A();
                            break;
                    }
                    httpURLConnection.setRequestMethod("HEAD");
                    try {
                        a(httpURLConnection.getResponseCode());
                    } catch (Exception e) {
                        throw e;
                    }
                } catch (IOException e2) {
                    Log.e(a, e2.getMessage());
                    a(500);
                }
            } catch (Throwable th) {
                a(500);
                throw th;
            }
        } catch (Exception e3) {
            throw e3;
        }
    }
}
