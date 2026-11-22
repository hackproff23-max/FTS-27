package com.millennialmedia.android;

import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMInlineVideo extends MMJSObject {
    private static final String ADJUST_VIDEO = "adjustVideo";
    private static final String INSERT_VIDEO = "insertVideo";
    private static final String PAUSE_VIDEO = "pauseVideo";
    private static final String PLAY_VIDEO = "playVideo";
    private static final String REMOVE_VIDEO = "removeVideo";
    private static final String RESUME_VIDEO = "resumeVideo";
    private static final String SET_STREAM_VIDEO_SOURCE = "setStreamVideoSource";
    private static final String STOP_VIDEO = "stopVideo";

    BridgeMMInlineVideo() {
    }

    public MMJSResponse insertVideo(final Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMWebView mmWebView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (mmWebView == null) {
                    return MMJSResponse.responseWithError();
                }
                MMLayout mmLayout = mmWebView.getMMLayout();
                InlineVideoView.InlineParams params = new InlineVideoView.InlineParams(parameters, mmWebView.getContext());
                mmLayout.initInlineVideo(params);
                return MMJSResponse.responseWithSuccess("usingStreaming=" + mmLayout.isVideoPlayingStreaming());
            }
        });
    }

    public MMJSResponse removeVideo(Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMLayout mmLayout;
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView == null || (mmLayout = webView.getMMLayout()) == null) {
                    return MMJSResponse.responseWithError();
                }
                mmLayout.removeVideo();
                return MMJSResponse.responseWithSuccess();
            }
        });
    }

    public MMJSResponse playVideo(Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMLayout mmLayout;
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView == null || (mmLayout = webView.getMMLayout()) == null) {
                    return MMJSResponse.responseWithError();
                }
                mmLayout.playVideo();
                return MMJSResponse.responseWithSuccess();
            }
        });
    }

    public MMJSResponse adjustVideo(final Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView != null && webView != null) {
                    MMLayout mmLayout = webView.getMMLayout();
                    InlineVideoView.InlineParams params = new InlineVideoView.InlineParams(parameters, webView.getContext());
                    if (mmLayout.adjustVideo(params)) {
                        return MMJSResponse.responseWithSuccess();
                    }
                }
                return MMJSResponse.responseWithError();
            }
        });
    }

    public MMJSResponse stopVideo(Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMLayout mmLayout;
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView == null || (mmLayout = webView.getMMLayout()) == null) {
                    return MMJSResponse.responseWithError();
                }
                mmLayout.stopVideo();
                return MMJSResponse.responseWithSuccess();
            }
        });
    }

    public MMJSResponse pauseVideo(Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMLayout mmLayout;
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView == null || (mmLayout = webView.getMMLayout()) == null) {
                    return MMJSResponse.responseWithError();
                }
                mmLayout.pauseVideo();
                return MMJSResponse.responseWithSuccess();
            }
        });
    }

    public MMJSResponse resumeVideo(Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMLayout mmLayout;
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView == null || (mmLayout = webView.getMMLayout()) == null) {
                    return MMJSResponse.responseWithError();
                }
                mmLayout.resumeVideo();
                return MMJSResponse.responseWithSuccess();
            }
        });
    }

    public MMJSResponse setStreamVideoSource(final Map<String, String> parameters) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMInlineVideo.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMWebView webView = BridgeMMInlineVideo.this.mmWebViewRef.get();
                if (webView != null) {
                    MMLayout mmLayout = webView.getMMLayout();
                    String streamVideoURI = (String) parameters.get("streamVideoURI");
                    if (mmLayout != null && streamVideoURI != null) {
                        mmLayout.setVideoSource(streamVideoURI);
                        return MMJSResponse.responseWithSuccess();
                    }
                }
                return MMJSResponse.responseWithError();
            }
        });
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (ADJUST_VIDEO.equals(name)) {
            MMJSResponse response = adjustVideo(arguments);
            return response;
        }
        if (INSERT_VIDEO.equals(name)) {
            MMJSResponse response2 = insertVideo(arguments);
            return response2;
        }
        if (PAUSE_VIDEO.equals(name)) {
            MMJSResponse response3 = pauseVideo(arguments);
            return response3;
        }
        if ("playVideo".equals(name)) {
            MMJSResponse response4 = playVideo(arguments);
            return response4;
        }
        if (REMOVE_VIDEO.equals(name)) {
            MMJSResponse response5 = removeVideo(arguments);
            return response5;
        }
        if (RESUME_VIDEO.equals(name)) {
            MMJSResponse response6 = resumeVideo(arguments);
            return response6;
        }
        if (SET_STREAM_VIDEO_SOURCE.equals(name)) {
            MMJSResponse response7 = setStreamVideoSource(arguments);
            return response7;
        }
        if (!STOP_VIDEO.equals(name)) {
            return null;
        }
        MMJSResponse response8 = stopVideo(arguments);
        return response8;
    }
}
