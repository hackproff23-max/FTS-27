package defpackage;

import android.media.MediaRecorder;

/* JADX INFO: loaded from: classes.dex */
public final class en {
    public static final int a;

    static {
        new er();
        Integer num = (Integer) er.a((Class<?>) MediaRecorder.AudioSource.class, "VOICE_RECOGNITION");
        if (num == null) {
            num = 0;
        }
        a = num.intValue();
    }
}
