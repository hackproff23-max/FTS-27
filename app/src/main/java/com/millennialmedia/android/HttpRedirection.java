package com.millennialmedia.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
final class HttpRedirection {
    private static final String HEADER_LOCATION = "Location";
    private static final String HTTPS = "https";
    private static final String LOG_URL_FORMAT = "Redirecting to: %s";
    private static final String METHOD_GET = "GET";
    private static final String TAG = "HttpRedirection";

    private interface Listener {
        boolean canOpenOverlay();

        OverlaySettings getOverlaySettings();

        boolean isActivityStartable(Uri uri);

        boolean isExpandingToUrl();

        boolean isHandlingMMVideo(Uri uri);

        void startingActivity(Uri uri);

        void startingVideo();

        void updateLastVideoViewedTime();
    }

    HttpRedirection() {
    }

    static final String navigateRedirects(String urlString) {
        if (urlString == null) {
            return null;
        }
        HttpURLConnection.setFollowRedirects(false);
        while (!urlString.startsWith("https")) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                conn.connect();
                int rc = conn.getResponseCode();
                String locationUrl = conn.getHeaderField(HEADER_LOCATION);
                if (rc >= 300 && rc < 400 && !TextUtils.isEmpty(locationUrl)) {
                    URI locationUri = new URI(locationUrl);
                    if (!locationUri.isAbsolute()) {
                        urlString = url.toURI().resolve(locationUri).toString();
                    } else if (locationUrl != null) {
                        urlString = locationUrl;
                    }
                    MMLog.v(TAG, String.format(LOG_URL_FORMAT, urlString));
                } else {
                    return urlString;
                }
            } catch (MalformedURLException e) {
                MMLog.e(TAG, "Bad url scheme", e);
                return urlString;
            } catch (SocketTimeoutException e2) {
                MMLog.e(TAG, "Connection timeout.", e2);
                return urlString;
            } catch (IOException e3) {
                MMLog.e(TAG, "IOException following redirects: ", e3);
                return urlString;
            } catch (URISyntaxException e4) {
                MMLog.e(TAG, "URI Syntax incorrect.", e4);
                return urlString;
            }
        }
        return urlString;
    }

    static class RedirectionListenerImpl implements Listener {
        long creatorAdImplInternalId;
        Uri destinationUri;
        String orientation;
        String url;
        WeakReference<Context> weakContext;

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public OverlaySettings getOverlaySettings() {
            return null;
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public boolean isHandlingMMVideo(Uri uri) {
            return false;
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public void updateLastVideoViewedTime() {
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public void startingActivity(Uri destinationUri) {
            MMLog.d(HttpRedirection.TAG, String.format("Starting activity for %s", destinationUri));
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public boolean isActivityStartable(Uri uri) {
            return true;
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public void startingVideo() {
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public boolean canOpenOverlay() {
            return false;
        }

        @Override // com.millennialmedia.android.HttpRedirection.Listener
        public boolean isExpandingToUrl() {
            return false;
        }
    }

    static void startActivityFromUri(RedirectionListenerImpl listener) {
        if (listener != null && listener.url != null && listener.weakContext != null) {
            final WeakReference<RedirectionListenerImpl> listenerReference = new WeakReference<>(listener);
            Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.HttpRedirection.1
                @Override // java.lang.Runnable
                public void run() {
                    String destination;
                    RedirectionListenerImpl listener2 = (RedirectionListenerImpl) listenerReference.get();
                    if (listener2 != null && (destination = HttpRedirection.navigateRedirects(listener2.url)) != null) {
                        listener2.destinationUri = Uri.parse(destination);
                        if (listener2.destinationUri != null) {
                            handleDestinationUri(listener2);
                        } else {
                            MMLog.e(HttpRedirection.TAG, String.format("Could not start activity for %s", destination));
                        }
                    }
                }

                private void handleDestinationUri(RedirectionListenerImpl listener2) {
                    Intent intent = null;
                    Context context = listener2.weakContext.get();
                    if (context != null) {
                        String scheme = listener2.destinationUri.getScheme();
                        if (scheme != null) {
                            if (!scheme.equalsIgnoreCase("mmvideo")) {
                                intent = Utils.IntentUtils.getIntentForUri(listener2);
                            } else if (!listener2.isHandlingMMVideo(listener2.destinationUri)) {
                                String id = listener2.destinationUri.getHost();
                                VideoAd.playAd(context, id, listener2);
                            }
                        }
                        if (intent != null) {
                            OverlaySettings settings = listener2.getOverlaySettings();
                            if (intent != null && settings != null) {
                                if (listener2.orientation != null) {
                                    settings.orientation = listener2.orientation;
                                }
                                intent.putExtra("settings", settings);
                            }
                            String clazz = intent.getStringExtra("class");
                            if (clazz == null || !clazz.equals(AdViewOverlayActivity.class.getCanonicalName())) {
                                try {
                                    if (listener2.isActivityStartable(listener2.destinationUri)) {
                                        Utils.IntentUtils.startActivity(context, intent);
                                        listener2.startingActivity(listener2.destinationUri);
                                    }
                                } catch (ActivityNotFoundException e) {
                                    MMLog.e(HttpRedirection.TAG, String.format("No activity found for %s", listener2.destinationUri), e);
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
