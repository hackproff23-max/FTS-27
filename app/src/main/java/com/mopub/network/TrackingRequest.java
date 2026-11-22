package com.mopub.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.event.BaseEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.Response;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TrackingRequest extends Request<Void> {

    @Nullable
    private final Listener mListener;

    public interface Listener extends Response.ErrorListener {
        void onResponse(@NonNull String str);
    }

    private TrackingRequest(@NonNull String url, @Nullable Listener listener) {
        super(0, url, listener);
        this.mListener = listener;
        setShouldCache(false);
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, 1.0f));
    }

    @Override // com.mopub.volley.Request
    protected Response<Void> parseNetworkResponse(NetworkResponse networkResponse) {
        return networkResponse.statusCode != 200 ? Response.error(new MoPubNetworkError("Failed to log tracking request. Response code: " + networkResponse.statusCode + " for url: " + getUrl(), MoPubNetworkError.Reason.TRACKING_FAILURE)) : Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
    }

    @Override // com.mopub.volley.Request
    public void deliverResponse(Void aVoid) {
        if (this.mListener != null) {
            this.mListener.onResponse(getUrl());
        }
    }

    public static void makeTrackingHttpRequest(Iterable<String> urls, Context context) {
        makeTrackingHttpRequest(urls, context, (Listener) null, (BaseEvent.Name) null);
    }

    public static void makeTrackingHttpRequest(Iterable<String> urls, Context context, BaseEvent.Name name) {
        makeTrackingHttpRequest(urls, context, (Listener) null, name);
    }

    public static void makeTrackingHttpRequest(Iterable<String> urls, Context context, @Nullable final Listener listener, BaseEvent.Name name) throws Throwable {
        if (urls != null && context != null) {
            RequestQueue requestQueue = Networking.getRequestQueue(context);
            for (final String url : urls) {
                if (!TextUtils.isEmpty(url)) {
                    Listener internalListener = new Listener() { // from class: com.mopub.network.TrackingRequest.1
                        @Override // com.mopub.network.TrackingRequest.Listener
                        public void onResponse(@NonNull String url2) {
                            MoPubLog.d("Successfully hit tracking endpoint: " + url2);
                            if (listener != null) {
                                listener.onResponse(url2);
                            }
                        }

                        @Override // com.mopub.volley.Response.ErrorListener
                        public void onErrorResponse(VolleyError volleyError) {
                            MoPubLog.d("Failed to hit tracking endpoint: " + url);
                            if (listener != null) {
                                listener.onErrorResponse(volleyError);
                            }
                        }
                    };
                    TrackingRequest trackingRequest = new TrackingRequest(url, internalListener);
                    requestQueue.add(trackingRequest);
                }
            }
        }
    }

    public static void makeTrackingHttpRequest(String url, Context context) {
        makeTrackingHttpRequest(url, context, (Listener) null, (BaseEvent.Name) null);
    }

    public static void makeTrackingHttpRequest(String url, Context context, @Nullable Listener listener) throws Throwable {
        makeTrackingHttpRequest(url, context, listener, (BaseEvent.Name) null);
    }

    public static void makeTrackingHttpRequest(String url, Context context, BaseEvent.Name name) {
        makeTrackingHttpRequest(url, context, (Listener) null, name);
    }

    public static void makeTrackingHttpRequest(String url, Context context, @Nullable Listener listener, BaseEvent.Name name) throws Throwable {
        if (url != null) {
            makeTrackingHttpRequest(Arrays.asList(url), context, listener, name);
        }
    }
}
