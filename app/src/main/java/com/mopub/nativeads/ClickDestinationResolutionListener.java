package com.mopub.nativeads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class ClickDestinationResolutionListener implements UrlResolutionTask.UrlResolutionListener {
    private final Context mContext;
    private final WeakReference<SpinningProgressView> mSpinningProgressView;
    private final Iterator<String> mUrlIterator;

    public ClickDestinationResolutionListener(@NonNull Context context, @NonNull Iterator<String> urlIterator, @NonNull SpinningProgressView spinningProgressView) {
        this.mContext = context.getApplicationContext();
        this.mUrlIterator = urlIterator;
        this.mSpinningProgressView = new WeakReference<>(spinningProgressView);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0023  */
    /* JADX WARN: Code duplicated, block: B:13:0x0047  */
    @Override // com.mopub.nativeads.UrlResolutionTask.UrlResolutionListener
    public void onSuccess(@NonNull String resolvedUrl) {
        if (Intents.isNativeBrowserScheme(resolvedUrl)) {
            try {
                Intents.startActivity(this.mContext, Intents.intentForNativeBrowserScheme(resolvedUrl));
                removeSpinningProgressView();
                return;
            } catch (IntentNotResolvableException e) {
                MoPubLog.d("Could not handle intent for URI: " + resolvedUrl);
                if (this.mUrlIterator.hasNext()) {
                    UrlResolutionTask.getResolvedUrl(this.mUrlIterator.next(), this);
                    return;
                } else {
                    removeSpinningProgressView();
                    return;
                }
            } catch (UrlParseException e2) {
                MoPubLog.d(e2.getMessage());
                if (this.mUrlIterator.hasNext()) {
                    UrlResolutionTask.getResolvedUrl(this.mUrlIterator.next(), this);
                    return;
                } else {
                    removeSpinningProgressView();
                    return;
                }
            }
        }
        if (Intents.isDeepLink(resolvedUrl)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(resolvedUrl));
            try {
                if (Intents.deviceCanHandleIntent(this.mContext, intent)) {
                    try {
                        Intents.startActivity(this.mContext, intent);
                        removeSpinningProgressView();
                        return;
                    } catch (IntentNotResolvableException e3) {
                        MoPubLog.d("Could not handle intent with URI: " + resolvedUrl);
                        removeSpinningProgressView();
                    }
                }
                if (this.mUrlIterator.hasNext()) {
                    UrlResolutionTask.getResolvedUrl(this.mUrlIterator.next(), this);
                    return;
                } else {
                    removeSpinningProgressView();
                    return;
                }
            } catch (Throwable th) {
                removeSpinningProgressView();
                throw th;
            }
        }
        removeSpinningProgressView();
        MoPubBrowser.open(this.mContext, resolvedUrl);
    }

    @Override // com.mopub.nativeads.UrlResolutionTask.UrlResolutionListener
    public void onFailure() {
        MoPubLog.d("Failed to resolve URL for click.");
        removeSpinningProgressView();
    }

    private void removeSpinningProgressView() {
        SpinningProgressView spinningProgressView = this.mSpinningProgressView.get();
        if (spinningProgressView != null) {
            spinningProgressView.removeFromRoot();
        }
    }
}
