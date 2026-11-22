package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;

/* JADX INFO: loaded from: classes.dex */
class NativeViewHolder {

    @VisibleForTesting
    static final NativeViewHolder EMPTY_VIEW_HOLDER = new NativeViewHolder();

    @Nullable
    TextView callToActionView;

    @Nullable
    ImageView iconImageView;

    @Nullable
    ImageView mainImageView;

    @Nullable
    TextView textView;

    @Nullable
    TextView titleView;

    private NativeViewHolder() {
    }

    @NonNull
    static NativeViewHolder fromViewBinder(@NonNull View view, @NonNull ViewBinder viewBinder) {
        NativeViewHolder nativeViewHolder = new NativeViewHolder();
        try {
            nativeViewHolder.titleView = (TextView) view.findViewById(viewBinder.titleId);
            nativeViewHolder.textView = (TextView) view.findViewById(viewBinder.textId);
            nativeViewHolder.callToActionView = (TextView) view.findViewById(viewBinder.callToActionId);
            nativeViewHolder.mainImageView = (ImageView) view.findViewById(viewBinder.mainImageId);
            nativeViewHolder.iconImageView = (ImageView) view.findViewById(viewBinder.iconImageId);
            return nativeViewHolder;
        } catch (ClassCastException exception) {
            MoPubLog.w("Could not cast from id in ViewBinder to expected View type", exception);
            return EMPTY_VIEW_HOLDER;
        }
    }

    void update(@NonNull NativeResponse nativeResponse) {
        addTextView(this.titleView, nativeResponse.getTitle());
        addTextView(this.textView, nativeResponse.getText());
        addTextView(this.callToActionView, nativeResponse.getCallToAction());
        nativeResponse.loadMainImage(this.mainImageView);
        nativeResponse.loadIconImage(this.iconImageView);
    }

    void updateExtras(@NonNull View outerView, @NonNull NativeResponse nativeResponse, @NonNull ViewBinder viewBinder) {
        for (String key : viewBinder.extras.keySet()) {
            int resourceId = viewBinder.extras.get(key).intValue();
            View view = outerView.findViewById(resourceId);
            Object content = nativeResponse.getExtra(key);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(null);
                nativeResponse.loadExtrasImage(key, (ImageView) view);
            } else if (view instanceof TextView) {
                ((TextView) view).setText((CharSequence) null);
                if (content instanceof String) {
                    addTextView((TextView) view, (String) content);
                }
            } else {
                MoPubLog.d("View bound to " + key + " should be an instance of TextView or ImageView.");
            }
        }
    }

    private void addTextView(@Nullable TextView textView, @Nullable String contents) {
        if (textView == null) {
            MoPubLog.d("Attempted to add text (" + contents + ") to null TextView.");
            return;
        }
        textView.setText((CharSequence) null);
        if (contents == null) {
            MoPubLog.d("Attempted to set TextView contents to null.");
        } else {
            textView.setText(contents);
        }
    }
}
