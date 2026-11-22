package com.immersion.hapticmediasdk;

import android.content.Context;
import com.immersion.content.EndpointWarp;
import com.immersion.hapticmediasdk.utils.Log;

/* JADX INFO: loaded from: classes.dex */
public class HapticContentSDKFactory {
    private static final String a = "HapticContentSDKFactory";

    /* JADX INFO: renamed from: b04460446044604460446ц, reason: contains not printable characters */
    public static int f27b04460446044604460446 = 19;

    /* JADX INFO: renamed from: b0446цццц0446, reason: contains not printable characters */
    public static int f28b04460446 = 2;

    /* JADX INFO: renamed from: bццццц0446, reason: contains not printable characters */
    public static int f29b0446 = 1;

    public HapticContentSDKFactory() {
        int i = f27b04460446044604460446;
        switch ((i * (f29b0446 + i)) % f28b04460446) {
            case 0:
                break;
            default:
                f27b04460446044604460446 = m22b04460446();
                f29b0446 = m22b04460446();
                break;
        }
    }

    public static HapticContentSDK GetNewSDKInstance(int i, Context context) throws Exception {
        if (!EndpointWarp.loadSharedLibrary()) {
            return null;
        }
        if (context == null) {
            Log.e(a, "Failed to create a Haptic Content SDK instance. invalid context: null");
            return null;
        }
        switch (i) {
            case 0:
                MediaPlaybackSDK mediaPlaybackSDK = new MediaPlaybackSDK(context);
                int iM18b0411041104110411 = mediaPlaybackSDK.m18b0411041104110411();
                if (iM18b0411041104110411 == 0) {
                    Log.i(a, "Haptic Content SDK instance was created successfully");
                    return mediaPlaybackSDK;
                }
                StringBuilder sb = new StringBuilder();
                int i2 = f27b04460446044604460446;
                switch ((i2 * (f29b0446 + i2)) % f28b04460446) {
                    case 0:
                        break;
                    default:
                        f27b04460446044604460446 = 42;
                        f29b0446 = 92;
                        break;
                }
                Log.e(a, sb.append("Failed to create Haptic Content SDK instance. error=").append(iM18b0411041104110411).toString());
                return null;
            default:
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
                Log.e(a, "Failed to create a Haptic Content SDK instance. Invalid mode");
                return null;
        }
    }

    /* JADX INFO: renamed from: bц0446ццц0446, reason: contains not printable characters */
    public static int m22b04460446() {
        return 96;
    }
}
