package com.unity3d.ads.android.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.data.UnityAdsGraphicsBundle;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsMuteVideoButton extends ImageButton {
    private UnityAdsMuteVideoButtonState a;
    private UnityAdsMuteVideoButtonSize b;

    public enum UnityAdsMuteVideoButtonSize {
        Small,
        Medium,
        Large
    }

    public enum UnityAdsMuteVideoButtonState {
        UnMuted,
        Muted
    }

    public UnityAdsMuteVideoButton(Context context) {
        super(context);
        this.a = UnityAdsMuteVideoButtonState.UnMuted;
        this.b = UnityAdsMuteVideoButtonSize.Medium;
        b();
    }

    public UnityAdsMuteVideoButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = UnityAdsMuteVideoButtonState.UnMuted;
        this.b = UnityAdsMuteVideoButtonSize.Medium;
        b();
    }

    public UnityAdsMuteVideoButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = UnityAdsMuteVideoButtonState.UnMuted;
        this.b = UnityAdsMuteVideoButtonSize.Medium;
        b();
    }

    public void setState(UnityAdsMuteVideoButtonState unityAdsMuteVideoButtonState) {
        if (unityAdsMuteVideoButtonState != null && !unityAdsMuteVideoButtonState.equals(this.a)) {
            this.a = unityAdsMuteVideoButtonState;
            setImageBitmap(a());
        }
    }

    private Bitmap a() {
        if (this.b != null && this.b.equals(UnityAdsMuteVideoButtonSize.Medium)) {
            switch (this.a) {
                case UnMuted:
                    String str = UnityAdsGraphicsBundle.ICON_AUDIO_UNMUTED_50x50;
                    if (UnityAdsDevice.getScreenDensity() == 120) {
                        str = UnityAdsGraphicsBundle.ICON_AUDIO_UNMUTED_32x32;
                    }
                    return UnityAdsGraphicsBundle.getBitmapFromString(str);
                case Muted:
                    String str2 = UnityAdsGraphicsBundle.ICON_AUDIO_MUTED_50x50;
                    if (UnityAdsDevice.getScreenDensity() == 120) {
                        str2 = UnityAdsGraphicsBundle.ICON_AUDIO_MUTED_32x32;
                    }
                    return UnityAdsGraphicsBundle.getBitmapFromString(str2);
            }
        }
        return null;
    }

    private void b() {
        setAdjustViewBounds(true);
        setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        setImageBitmap(a());
        setBackgroundResource(0);
        setPadding(0, 0, 0, 0);
    }
}
