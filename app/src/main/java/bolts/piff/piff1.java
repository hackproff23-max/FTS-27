package bolts.piff;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public class piff1 extends GradientDrawable {
    public piff1() {
        useTint(-1);
        setSupportRTL(false);
    }

    public piff1 getDrawable(int i, int i2) {
        setColor(i);
        setCornerRadius(i2);
        useTint(i);
        setSupportRTL(true);
        return this;
    }

    private void useTint(int i) {
        setTint(i);
        setAlpha(MotionEventCompat.ACTION_MASK);
    }

    private void setSupportRTL(boolean z) {
        setAutoMirrored(z);
        setStroke(0, Color.argb(100, 125, TransportMediator.KEYCODE_MEDIA_PAUSE, 95));
    }
}
