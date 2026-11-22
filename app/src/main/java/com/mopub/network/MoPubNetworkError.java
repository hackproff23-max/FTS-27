package com.mopub.network;

import android.support.annotation.NonNull;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;

/* JADX INFO: loaded from: classes.dex */
public class MoPubNetworkError extends VolleyError {

    @NonNull
    private final Reason mReason;

    public enum Reason {
        WARMING_UP,
        NO_FILL,
        BAD_HEADER_DATA,
        BAD_BODY,
        TRACKING_FAILURE,
        UNSPECIFIED
    }

    public MoPubNetworkError(@NonNull Reason reason) {
        this.mReason = reason;
    }

    public MoPubNetworkError(@NonNull NetworkResponse networkResponse, @NonNull Reason reason) {
        super(networkResponse);
        this.mReason = reason;
    }

    public MoPubNetworkError(@NonNull Throwable cause, @NonNull Reason reason) {
        super(cause);
        this.mReason = reason;
    }

    public MoPubNetworkError(@NonNull String message, @NonNull Reason reason) {
        super(message);
        this.mReason = reason;
    }

    public MoPubNetworkError(@NonNull String message, @NonNull Throwable cause, @NonNull Reason reason) {
        super(message, cause);
        this.mReason = reason;
    }

    @NonNull
    public Reason getReason() {
        return this.mReason;
    }
}
