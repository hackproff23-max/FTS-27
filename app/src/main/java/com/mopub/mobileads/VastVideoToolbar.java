package com.mopub.mobileads;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.CloseButtonDrawable;
import com.mopub.mobileads.resource.CountdownDrawable;
import com.mopub.mobileads.resource.LearnMoreDrawable;

/* JADX INFO: loaded from: classes.dex */
class VastVideoToolbar extends LinearLayout {
    private static final int THRESHOLD_FOR_HIDING_VIDEO_DURATION = 200;
    private static final int TOOLBAR_HEIGHT_DIPS = 44;
    private final ToolbarWidget mCloseButtonWidget;
    private final ToolbarWidget mCountdownWidget;
    private final ToolbarWidget mDurationWidget;
    private final ToolbarWidget mLearnMoreWidget;

    public VastVideoToolbar(Context context) {
        super(context);
        setId((int) Utils.generateUniqueId());
        setOnTouchListener(new View.OnTouchListener() { // from class: com.mopub.mobileads.VastVideoToolbar.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        int videoToolbarHeight = Dips.dipsToIntPixels(44.0f, getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, videoToolbarHeight);
        setLayoutParams(layoutParams);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getBackground().setAlpha(180);
        this.mDurationWidget = createDurationWidget();
        this.mLearnMoreWidget = createLearnMoreWidget();
        this.mCountdownWidget = createCountdownWidget();
        this.mCloseButtonWidget = createCloseButtonWidget();
        addView(this.mDurationWidget);
        addView(this.mLearnMoreWidget);
        addView(this.mCountdownWidget);
        addView(this.mCloseButtonWidget);
    }

    @VisibleForTesting
    long convertMillisecondsToSecondsRoundedUp(long millis) {
        return Math.round(Math.ceil(millis / 1000.0f));
    }

    @VisibleForTesting
    void updateDurationWidget(int remainingTime) {
        if (remainingTime >= 200) {
            this.mDurationWidget.updateText("Ends in " + formatTime(remainingTime));
        } else if (remainingTime >= 0) {
            this.mDurationWidget.updateText("Thanks for watching");
        }
    }

    @VisibleForTesting
    String formatTime(long milliseconds) {
        long seconds = convertMillisecondsToSecondsRoundedUp(milliseconds);
        return seconds == 1 ? "1 second" : String.valueOf(seconds) + " seconds";
    }

    void updateCountdownWidget(int remainingTime) {
        if (remainingTime >= 0 && this.mCountdownWidget.getVisibility() == 4) {
            this.mCloseButtonWidget.setVisibility(8);
            this.mCountdownWidget.setVisibility(0);
        }
        this.mCountdownWidget.updateImageText(String.valueOf(convertMillisecondsToSecondsRoundedUp(remainingTime)));
    }

    void makeInteractable() {
        this.mCountdownWidget.setVisibility(8);
        this.mLearnMoreWidget.setVisibility(0);
        this.mCloseButtonWidget.setVisibility(0);
    }

    void setCloseButtonOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mCloseButtonWidget.setOnTouchListener(onTouchListener);
    }

    void setLearnMoreButtonOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mLearnMoreWidget.setOnTouchListener(onTouchListener);
    }

    private ToolbarWidget createDurationWidget() {
        return new ToolbarWidget.Builder(getContext()).weight(2.0f).widgetGravity(19).hasText().textAlign(9).build();
    }

    private ToolbarWidget createLearnMoreWidget() {
        return new ToolbarWidget.Builder(getContext()).weight(1.0f).widgetGravity(21).defaultText("Learn More").drawable(new LearnMoreDrawable()).visibility(4).build();
    }

    private ToolbarWidget createCountdownWidget() {
        CountdownDrawable countdownDrawable = new CountdownDrawable(getContext());
        return new ToolbarWidget.Builder(getContext()).weight(1.0f).widgetGravity(21).defaultText("Skip in").drawable(countdownDrawable).visibility(4).build();
    }

    private ToolbarWidget createCloseButtonWidget() {
        return new ToolbarWidget.Builder(getContext()).weight(1.0f).widgetGravity(21).defaultText("Close").drawable(new CloseButtonDrawable()).visibility(8).build();
    }

    @Deprecated
    ToolbarWidget getDurationWidget() {
        return this.mDurationWidget;
    }

    @Deprecated
    ToolbarWidget getLearnMoreWidget() {
        return this.mLearnMoreWidget;
    }

    @Deprecated
    ToolbarWidget getCountdownWidget() {
        return this.mCountdownWidget;
    }

    @Deprecated
    ToolbarWidget getCloseButtonWidget() {
        return this.mCloseButtonWidget;
    }
}
