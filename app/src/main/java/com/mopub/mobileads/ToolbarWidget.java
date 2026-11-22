package com.mopub.mobileads;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.resource.TextDrawable;

/* JADX INFO: loaded from: classes.dex */
class ToolbarWidget extends RelativeLayout {
    private static final int IMAGE_PADDING_DIPS = 5;
    private static final int IMAGE_SIDE_LENGTH_DIPS = 37;
    private static final int TEXT_PADDING_DIPS = 5;
    private final int mImagePadding;
    private final int mImageSideLength;
    private ImageView mImageView;
    private final int mTextPadding;
    private TextView mTextView;

    static class Builder {
        private final Context context;
        private String defaultText;
        private Drawable drawable;
        private boolean hasDrawable;
        private boolean hasText;
        private View.OnTouchListener onTouchListener;
        private float weight = 1.0f;
        private int widgetGravity = 17;
        private int visibility = 0;
        private int textAlign = 9;
        private int drawableAlign = 11;

        Builder(Context context) {
            this.context = context;
        }

        Builder weight(float weight) {
            this.weight = weight;
            return this;
        }

        Builder widgetGravity(int widgetGravity) {
            this.widgetGravity = widgetGravity;
            return this;
        }

        Builder hasText() {
            this.hasText = true;
            return this;
        }

        Builder defaultText(String defaultText) {
            this.hasText = true;
            this.defaultText = defaultText;
            return this;
        }

        Builder hasDrawable() {
            this.hasDrawable = true;
            return this;
        }

        Builder drawable(Drawable drawable) {
            this.hasDrawable = true;
            this.drawable = drawable;
            return this;
        }

        Builder textAlign(int rule) {
            this.textAlign = rule;
            return this;
        }

        Builder drawableAlign(int rule) {
            this.drawableAlign = rule;
            return this;
        }

        Builder visibility(int visibility) {
            this.visibility = visibility;
            return this;
        }

        Builder onTouchListener(View.OnTouchListener onTouchListener) {
            this.onTouchListener = onTouchListener;
            return this;
        }

        ToolbarWidget build() {
            return new ToolbarWidget(this);
        }
    }

    private ToolbarWidget(Builder builder) {
        super(builder.context);
        LinearLayout.LayoutParams toolbarLayoutParams = new LinearLayout.LayoutParams(0, -2, builder.weight);
        toolbarLayoutParams.gravity = builder.widgetGravity;
        setLayoutParams(toolbarLayoutParams);
        this.mTextPadding = Dips.dipsToIntPixels(5.0f, getContext());
        this.mImagePadding = Dips.dipsToIntPixels(5.0f, getContext());
        this.mImageSideLength = Dips.dipsToIntPixels(37.0f, getContext());
        setVisibility(builder.visibility);
        if (builder.hasDrawable && builder.drawable != null) {
            this.mImageView = new ImageView(getContext());
            this.mImageView.setId((int) Utils.generateUniqueId());
            RelativeLayout.LayoutParams iconLayoutParams = new RelativeLayout.LayoutParams(this.mImageSideLength, this.mImageSideLength);
            iconLayoutParams.addRule(15);
            iconLayoutParams.addRule(builder.drawableAlign);
            this.mImageView.setPadding(this.mImagePadding, this.mImagePadding, this.mImagePadding, this.mImagePadding);
            this.mImageView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            this.mImageView.getBackground().setAlpha(0);
            this.mImageView.setImageDrawable(builder.drawable);
            addView(this.mImageView, iconLayoutParams);
        }
        if (builder.hasText) {
            this.mTextView = new TextView(getContext());
            this.mTextView.setSingleLine();
            this.mTextView.setEllipsize(TextUtils.TruncateAt.END);
            this.mTextView.setText(builder.defaultText);
            RelativeLayout.LayoutParams textLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
            textLayoutParams.addRule(15);
            if (this.mImageView == null) {
                textLayoutParams.addRule(builder.textAlign);
            } else {
                textLayoutParams.addRule(0, this.mImageView.getId());
            }
            this.mTextView.setPadding(this.mTextPadding, this.mTextPadding, this.mTextPadding, this.mTextPadding);
            addView(this.mTextView, textLayoutParams);
        }
        if (builder.onTouchListener != null) {
            setOnTouchListener(builder.onTouchListener);
        }
    }

    void updateText(String text) {
        if (this.mTextView != null) {
            this.mTextView.setText(text);
        }
    }

    void updateImageText(String text) {
        try {
            TextDrawable textDrawable = (TextDrawable) this.mImageView.getDrawable();
            textDrawable.updateText(text);
        } catch (Exception e) {
            MoPubLog.d("Unable to update ToolbarWidget text.");
        }
    }

    @Deprecated
    TextDrawable getImageViewDrawable() {
        return (TextDrawable) this.mImageView.getDrawable();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    void setImageViewDrawable(TextDrawable textDrawable) {
        this.mImageView.setImageDrawable((Drawable) textDrawable);
    }
}
