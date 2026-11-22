package rrrrrr;

import com.immersion.hapticmediasdk.HapticContentSDK;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class crccrr {

    /* JADX INFO: renamed from: b041704170417ЗЗЗ, reason: contains not printable characters */
    public static int f116b041704170417 = 1;

    /* JADX INFO: renamed from: b042504250425ХХ0425, reason: contains not printable characters */
    public static final /* synthetic */ int[] f117b0425042504250425 = new int[HapticContentSDK.SDKStatus.values().length];

    /* JADX INFO: renamed from: bЗ04170417ЗЗЗ, reason: contains not printable characters */
    public static int f118b04170417 = 88;

    /* JADX INFO: renamed from: bЗЗЗ0417ЗЗ, reason: contains not printable characters */
    public static int f119b0417 = 2;

    /* JADX WARN: Code duplicated, block: B:13:0x004a  */
    static {
        int i;
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    f117b0425042504250425[HapticContentSDK.SDKStatus.NOT_INITIALIZED.ordinal()] = 1;
                                    loop0: while (true) {
                                        switch (1) {
                                            case 0:
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
                                            case 1:
                                                f117b0425042504250425[HapticContentSDK.SDKStatus.PLAYING.ordinal()] = 3;
                                                f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED.ordinal()] = 4;
                                                i = f118b04170417;
                                                switch ((i * (f116b041704170417 + i)) % f119b0417) {
                                                    case 0:
                                                        break;
                                                    default:
                                                        f118b04170417 = 57;
                                                        f116b041704170417 = 26;
                                                        break;
                                                }
                                                f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED_DUE_TO_TIMEOUT.ordinal()] = 5;
                                                f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED_DUE_TO_BUFFERING.ordinal()] = 6;
                                                f117b0425042504250425[HapticContentSDK.SDKStatus.STOPPED.ordinal()] = 7;
                                                return;
                                        }
                                    }
                                } catch (NoSuchFieldError e) {
                                }
                                f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED_DUE_TO_BUFFERING.ordinal()] = 6;
                            } catch (NoSuchFieldError e2) {
                            }
                            f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED_DUE_TO_TIMEOUT.ordinal()] = 5;
                        } catch (NoSuchFieldError e3) {
                        }
                        f117b0425042504250425[HapticContentSDK.SDKStatus.PAUSED.ordinal()] = 4;
                        i = f118b04170417;
                        switch ((i * (f116b041704170417 + i)) % f119b0417) {
                            case 0:
                                break;
                            default:
                                f118b04170417 = 57;
                                f116b041704170417 = 26;
                                break;
                        }
                    } catch (NoSuchFieldError e4) {
                    }
                    f117b0425042504250425[HapticContentSDK.SDKStatus.STOPPED.ordinal()] = 7;
                    return;
                } catch (NoSuchFieldError e5) {
                    return;
                }
                f117b0425042504250425[HapticContentSDK.SDKStatus.INITIALIZED.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
                break;
            }
            f117b0425042504250425[HapticContentSDK.SDKStatus.PLAYING.ordinal()] = 3;
        } catch (NoSuchFieldError e7) {
        }
    }
}
