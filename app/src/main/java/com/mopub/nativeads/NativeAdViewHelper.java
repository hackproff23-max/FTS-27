package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class NativeAdViewHelper {

    @VisibleForTesting
    static final WeakHashMap<Context, ImpressionTracker> sImpressionTrackerMap = new WeakHashMap<>();
    private static final WeakHashMap<View, NativeResponse> sNativeResponseMap = new WeakHashMap<>();

    private NativeAdViewHelper() {
    }

    @NonNull
    @Deprecated
    static View getAdView(@Nullable View convertView, @Nullable ViewGroup parent, @NonNull Context context, @NonNull NativeResponse nativeResponse, @NonNull ViewBinder viewBinder) {
        MoPubNativeAdRenderer moPubNativeAdRenderer = new MoPubNativeAdRenderer(viewBinder);
        if (convertView == null) {
            convertView = moPubNativeAdRenderer.createAdView(context, parent);
        }
        clearNativeResponse(context, convertView);
        if (nativeResponse.isDestroyed()) {
            MoPubLog.d("NativeResponse is destroyed, returning hidden view.");
            convertView.setVisibility(8);
        } else {
            prepareNativeResponse(context, convertView, nativeResponse);
            moPubNativeAdRenderer.renderAdView(convertView, nativeResponse);
        }
        return convertView;
    }

    private static void clearNativeResponse(@NonNull Context context, @NonNull View view) {
        getImpressionTracker(context).removeView(view);
        NativeResponse nativeResponse = sNativeResponseMap.get(view);
        if (nativeResponse != null) {
            nativeResponse.clear(view);
        }
    }

    private static void prepareNativeResponse(@NonNull Context context, @NonNull View view, @NonNull NativeResponse nativeResponse) {
        sNativeResponseMap.put(view, nativeResponse);
        if (!nativeResponse.isOverridingImpressionTracker()) {
            getImpressionTracker(context).addView(view, nativeResponse);
        }
        nativeResponse.prepare(view);
    }

    private static ImpressionTracker getImpressionTracker(@NonNull Context context) {
        ImpressionTracker impressionTracker = sImpressionTrackerMap.get(context);
        if (impressionTracker == null) {
            ImpressionTracker impressionTracker2 = new ImpressionTracker(context);
            sImpressionTrackerMap.put(context, impressionTracker2);
            return impressionTracker2;
        }
        return impressionTracker;
    }
}
