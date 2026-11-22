package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.millennialmedia.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class InlineVideoView extends VideoView implements Serializable {
    private static final String ANCHOR_IN_PNG = "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABOpJREFUeNrUmmtIY0cUgCfJ3VBXUXxR0dp2Ya0aBSFi6yOtFPGF1d1WavrAXy1YwVcLLaQpttsH+lcbirYq0a3Ptd0WpKi1P1SMaAitJNptalcQIwV/iI+o2ST39kx6I9cY3STcuSYHhjuTzD33fJk5M+fciWh9fR0tLCwgrhwfH6OcnBwkl8ur9vb2DIODg5aIiAjkq1itViQWi1FYWBgiKYmJiQhptdpzX6SlpSGz2fwRwzDHR0dH83V1dYn+KK6oqLjR19enQISluLgYof7+/jMfZmRkIIvFcoc5K783NTU95YvSsrKyFLvd/s/a2pqRNEBJSclZAJlMhra2tjyNP4Vobm5OukxhaWlpysnJyQPceXl5WS8SicgDDAwMuBrp6enY+M+Zy2UFIJ72pqy8vDwFfOcvd0ebzWYoLCwkD9DR0YFSU1Ox8W2Mb7IKEM+4lWBnHRoaSnM6nX97jhhAkQfQ6/XXNjY27jD+iamhoeFZiqLQyMhIOrQfeuljhCklJg4AD7rFBCA0Tf+h0+luQ9V80UgBAEUaQAzrvB7qk/7eDA6alZeXdx+qKRd0wb++hPRKJIZNaru+vv4NqP/Gs25sPEUcIDw8HHV1de2BY1bzDCFmC1kA91rd2dmJIV6D6nRIjQC3ARD7LMQvPOmWCArAQlhbWlqUPEBIrgQAC2xuh42NjUoI5O5BkwmpEXCLRqM5XFlZ+Qaq9qB2Yq9jL5HgHRaW+bzvoSkNZic+9wA2tnmxpqbmB2jGB7sPUB67KzZeAcb/CM24kHBi9z6Ar8PDwy8plcr7PBgvnBND9uSa82D8y2D8TzwZLxgAhRN4yF+zwfhfeX7gdSjXiANgp11cXDyCjOxr1ick7MojYQ2g2Cu3fvpZcnKyOCEhQcL5xd1XK+QLu8QB2GDuTyjvB6IgKSkJZWZmejouLrTBYLAJvoz6KxaLxVVYcbLljOTm5oqioqLwlHJw+jB4+s7NzQX8bIfDQX6jwdLb2/upTCZ7kzWcZouTHSUnZHd2tv2IvdrZ4vC4uuv09va2eXx8XCMIgNVqxXPsOW/fZWdnB6TTZrNp8egJAgByyKey/f19LeTD7+IFSCwQgINH47+rrKx8Z2lpyRkZGSkYwCM+lBwcHHwLxtfB1ME+hN+ohM4IsMa/B8YzPuUDwQSwu7urAePrZ2dnGd73AdJTCGK1n2traxvBeP8ysmAZAQhHXlCpVK/Ex8dfKUCgaSkO8xMKCgpGJycnb3uDCBUnvi6Xy4cA4lZcXFxIAmAJA4iRqamp12NjY0+TsJDaB0CeAIjB6enpmujo6P8PE0NtJ8Y5CEDcnZiYUOJQngpBACzS/Pz8u1lZWfSVr0I7OzufrK6uqgMZCUjG3qauEgDCgy+qqqq+woftsFGJIGf40g+dD+fn51XnzolJCCQtb3meP21ubn7GPcXE89lkMql9POFab21tTYVMDwkCMDMzo+Q+fXR0VI1DYW/5tdFoVD3G+AdtbW2uY62ioiJhAKqrq1/lGo9PNy8S/P8HgPjwEuNvuvueO6knDTA2NvbxZcY/BmKtvb39JrefC6Cnp4c4AMQyT3Z3d5f6cw9+3aPT6T7AlkNEalKr1Tc8+ygUCiSC7B7BMkYUgKZpnIQjqVTqeo3pYxjtGglY65+H+ib40b949Lj/v4iJiUH/CTAAFI2ZNCJ5irUAAAAASUVORK5CYII=";
    private static final String ANCHOR_OUT_PNG = "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABEZJREFUeNrUml9IU1Ecx8+2uwnCrBlaGD2YEKiVla2ypAj8X+l8KYpkLxUFoT0VBT1YUYYP6ktQCGUQgQr+w0o3sgdDEv+AaBH9odicEq4XY25u7vY9dhe3m9N5d+8Z/uDHvRzPn+/nnN/5d6eG5/mNhJCjcAPcD18QngHhPSDxBVE+cfo/aXa7/afL5eKJipaSkkI4QfwzuBZOGwwKQoKS90jTqOvm5uZqrFbrYzUB8vPzFwEMgnhqGtq44LLN4/F8bmxsfEdUNo1GswjgF3peo0SlED9hsVhKbTbbV8LAtELMBhUSP1ZeXn6clXgxwEK0FSHmqfgTvb293whD45QAEMQfh3gHYWxa0eohy2ZmZt4i5o/19PQwFx8CmI9mBLq6umrQ804SI4t6BCoqKh5UVVWZYwVAsBMfhrv56GwKEPtYay8oKFgEyIH/4KO36crKyoOxADDTxnll7AcgDrEG2A2f5JUzCpHLEmAn3MEraxTiCCuAdPj3ZcR4ZEK4MbH3qw2w7FFidna2t6mpiU7M1zLqT8QSu43FMroV/vm/bvd47Dhvm2ge9OQGJL1ZTfc7nc77WVlZOhYhtAX+USLeVlhYaBJnXg0ExN/LyMhgNolT4B9CjeNg9hLiE5YqAAgTsthWEp+ens50Gd0If08b93q9r8KJF0GsR1Z7GPG3WfS8FCCBzgGfz/e6qKgoIZKCWCLXS0dicnKyOjMzk/1Roq+vj+AsfyA7OzthNYUBYYTuF/BALMT/BcDlW3YFubm58Qi5vampqTE5iFIATq/Xy66gv7/fg8eQ3PJ5eXmbdTrduqGhofdut1vOHZwQbFQx6b2GhoZMhN8n+K/h4eHypKSkiMvGxcXRsN9XXV29KSYA9fX1GRD+RbQGeAFxMhIIKr6zs/M0yvjh55gDQPx2ifiQ+QBxKjk5eSXxZwTx1C4xBYB4evL9tsw+6B0ZGVkSwmAwkI6ODip+XpT/MjOAurq6HSucev+OBCAqxBCCeKtEPLUrqgPQxtHzeyIUH7L50dHRs4mJiYt1tLe3W0VhI7arqgMUFxfHSw+LEZpvcHDwZHd39+kw4qnd4NQOnfHxcf/ExMQAdurV3g0MZrP5ufDROdyHZ71WbQCHw+HHjnkRIE9lfrda7qs5p2UxgV0ulxeXo3OAeKJw1TotqyV0enqajsR5hSEMzACoTU1NBXD4oxCNClXJMQUQwolCXMDEfqRAdXrmAAIETyf22NjYQ/Ln5621MwJiCIvFcjEQCAysuREI7dC1tbVVuA/simYEuFiJb2lpuVZaWlqz5iYxvQE2NzcrIZ59CHEYcPT89bKyshql+oMZAGKdtLa23oT4u0r2CRMAk8mkb2truwPxt5SeTpzf71cdIC0tzVhSUpKP1++hARFcu8RzqbSwBzoN1mOCXVFVgGAwSHJycuKMRmM8+fcfSqQg0jQuzN90Qj3O3wIMAN8Np0JgnxtnAAAAAElFTkSuQmCC";
    private static final String TAG = "InlineVideoView";
    static final int TIME_TO_UPDATE_SEEK_JS = 500;
    private int duration;
    InlineParams inlineParams;
    MediaController mediaController;
    WeakReference<MMLayout> mmLayoutRef;
    TransparentFix transFix;
    TransparentHandler transparentHandler;
    Handler videoHandler;

    interface TransparentFix {
        void removeBlackView();
    }

    public InlineVideoView(MMLayout mmLayout) {
        super(mmLayout.getContext());
        this.transparentHandler = new TransparentHandler(this);
        setId(8832429);
        setFocusable(true);
        MMAdImplController.destroyOtherInlineVideo(mmLayout.getContext());
        this.mmLayoutRef = new WeakReference<>(mmLayout);
    }

    static class InlineParams {
        boolean autoPlay;
        int bodyHeight;
        int bodyWidth;
        String cachedVideoID;
        String cachedVideoURI;
        int currentPosition;
        boolean goingFullScreen;
        int height;
        boolean isCompleted;
        boolean isInitialPlayBack = true;
        boolean isPlayingStreaming;
        boolean isStopped;
        int originalOrientation;
        float scaleFactor;
        boolean showControls;
        String streamVideoURI;
        String touchCallBack;
        int width;
        int x;
        int y;

        InlineParams(Map<String, String> parameters, Context context) {
            if (parameters.get("x") != null) {
                this.x = (int) Float.parseFloat(parameters.get("x"));
            }
            if (parameters.get("y") != null) {
                this.y = (int) Float.parseFloat(parameters.get("y"));
            }
            if (parameters.get("width") != null) {
                this.width = (int) Float.parseFloat(parameters.get("width"));
            }
            if (parameters.get("height") != null) {
                this.height = (int) Float.parseFloat(parameters.get("height"));
            }
            this.streamVideoURI = parameters.get("streamVideoURI");
            this.cachedVideoURI = parameters.get("cachedVideoURI");
            this.cachedVideoID = parameters.get("cachedVideoID");
            if (parameters.get("autoPlay") != null) {
                this.autoPlay = Boolean.parseBoolean(parameters.get("autoPlay"));
            }
            if (parameters.get("showControls") != null) {
                this.showControls = Boolean.parseBoolean(parameters.get("showControls"));
            }
            if (parameters.get("bodyWidth") != null) {
                this.bodyWidth = (int) Float.parseFloat(parameters.get("bodyWidth"));
            }
            if (parameters.get("bodyHeight") != null) {
                this.bodyHeight = (int) Float.parseFloat(parameters.get("bodyHeight"));
            }
            this.touchCallBack = parameters.get("touchCallback");
            this.scaleFactor = context.getResources().getDisplayMetrics().density;
        }

        static InlineParams getInlineParams(String gson) {
            return (InlineParams) new Gson().fromJson(gson, InlineParams.class);
        }

        public String toString() {
            return String.format("%s id, %d x, %d y, %d bWidth, %d bHeight, %d pos, %b autoPlay", this.cachedVideoID, Integer.valueOf(this.x), Integer.valueOf(this.y), Integer.valueOf(this.bodyWidth), Integer.valueOf(this.bodyHeight), Integer.valueOf(this.currentPosition), Boolean.valueOf(this.autoPlay));
        }

        void inflateFromGson(String gson) {
            InlineParams params = (InlineParams) new Gson().fromJson(gson, InlineParams.class);
            this.x = params.x;
            this.y = params.y;
            this.bodyWidth = params.bodyWidth;
            this.bodyHeight = params.bodyHeight;
            this.width = params.width;
            this.height = params.height;
            this.currentPosition = params.currentPosition;
            this.streamVideoURI = params.streamVideoURI;
            this.cachedVideoURI = params.cachedVideoURI;
            this.cachedVideoID = params.cachedVideoID;
            this.touchCallBack = params.touchCallBack;
            this.autoPlay = params.autoPlay;
            this.showControls = params.showControls;
            this.isInitialPlayBack = params.isInitialPlayBack;
            this.scaleFactor = params.scaleFactor;
            this.goingFullScreen = params.goingFullScreen;
            this.originalOrientation = params.originalOrientation;
            this.isCompleted = params.isCompleted;
            MMLog.d(InlineVideoView.TAG, "gson*****" + gson);
            MMLog.d(InlineVideoView.TAG, "PARAMS*****" + params);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        AdViewOverlayView.SavedState ss = new AdViewOverlayView.SavedState(superState);
        if (isPlaying()) {
            this.inlineParams.currentPosition = getCurrentPosition();
        }
        ss.gson = getGsonState();
        return ss;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof AdViewOverlayView.SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        AdViewOverlayView.SavedState ss = (AdViewOverlayView.SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.inlineParams.inflateFromGson(ss.gson);
    }

    String getGsonState() {
        return new Gson().toJson(this.inlineParams);
    }

    boolean isValid() {
        return !TextUtils.isEmpty(this.inlineParams.streamVideoURI) || hasCachedVideo();
    }

    private boolean hasCachedVideo() {
        return !TextUtils.isEmpty(this.inlineParams.cachedVideoID) && VideoAd.hasVideoFile(getContext(), this.inlineParams.cachedVideoID);
    }

    @Override // android.view.View
    public String toString() {
        return this.inlineParams.toString();
    }

    public RelativeLayout.LayoutParams getCustomLayoutParams() {
        if (this.inlineParams.goingFullScreen) {
            return new RelativeLayout.LayoutParams(-1, -1);
        }
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams((int) (this.inlineParams.scaleFactor * this.inlineParams.width), (int) (this.inlineParams.scaleFactor * this.inlineParams.height));
        lp.topMargin = (int) (this.inlineParams.scaleFactor * this.inlineParams.y);
        lp.leftMargin = (int) (this.inlineParams.scaleFactor * this.inlineParams.x);
        MMLog.d(TAG, "lp height = " + lp.height);
        return lp;
    }

    private void setInlineVideoParams(InlineParams parameters) {
        this.inlineParams = parameters;
    }

    void setAdjustVideoParams(InlineParams parameters) {
        this.inlineParams.x = parameters.x;
        this.inlineParams.y = parameters.y;
        this.inlineParams.width = parameters.width;
        this.inlineParams.height = parameters.height;
    }

    synchronized void initInlineVideo(InlineParams parameters) {
        setInlineVideoParams(parameters);
        if (!TextUtils.isEmpty(this.inlineParams.cachedVideoURI)) {
            downloadCacheVideo();
        }
        if (isValid()) {
            initInternalInlineVideo();
        } else {
            MMLog.e(TAG, "The videoURI attribute was not specified on the video marker div.");
        }
    }

    private void downloadCacheVideo() {
        Utils.ThreadUtils.execute(new DownloadRunnable(this));
    }

    private static class DownloadRunnable implements Runnable {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public DownloadRunnable(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // java.lang.Runnable
        public void run() {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                inlineView.downloadVideo();
            }
        }
    }

    void downloadVideo() {
        VideoAd.downloadVideoFile(getContext(), this.inlineParams.cachedVideoURI, this.inlineParams.cachedVideoID);
    }

    private void initInternalInlineVideo() {
        this.videoHandler = createVideoHandler();
        setVideoURI(getVideoUri());
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setClickable(true);
        setOnErrorListener(createOnErrorListener());
        setOnCompletionListener(createOnCompletionListener());
        setOnPreparedListener(createOnPreparedListener());
        setOnTouchListener(createOnTouchListener());
        if (this.inlineParams.autoPlay) {
            seekTo(this.inlineParams.currentPosition);
            startInternal();
            if (this.videoHandler != null && !this.videoHandler.hasMessages(2)) {
                this.videoHandler.sendMessageDelayed(Message.obtain(this.videoHandler, 2), 500L);
            }
        }
        if (this.inlineParams.showControls) {
            this.mediaController = new MediaController(this);
            setMediaController(this.mediaController);
            this.mediaController.show();
        }
        MMLog.e(TAG, "Finished inserting inlineVideo player");
    }

    private Uri getVideoUri() {
        if (hasCachedVideo() && !this.inlineParams.isPlayingStreaming) {
            this.inlineParams.isPlayingStreaming = false;
            return VideoAd.getVideoUri(getContext(), this.inlineParams.cachedVideoID);
        }
        if (!TextUtils.isEmpty(this.inlineParams.streamVideoURI)) {
            this.inlineParams.isPlayingStreaming = true;
            return Uri.parse(this.inlineParams.streamVideoURI);
        }
        return null;
    }

    synchronized boolean adjustVideo(InlineParams params) {
        setAdjustVideoParams(params);
        MMLog.e(TAG, "Called initInlineVideo inside reposition section code");
        boolean wasPlaying = isPlaying();
        stopPlayback();
        MMLayout mmLayout = this.mmLayoutRef.get();
        if (mmLayout != null) {
            mmLayout.addInlineVideo();
        }
        resumeInternal(wasPlaying);
        return true;
    }

    @Override // android.widget.VideoView
    public void stopPlayback() {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        if (isPlaying()) {
            this.inlineParams.currentPosition = 0;
        }
        super.stopPlayback();
    }

    private void resumeInternal(boolean wasPlaying) {
        if (!this.inlineParams.isCompleted) {
            seekTo(this.inlineParams.currentPosition);
            if (wasPlaying || this.inlineParams.autoPlay) {
                startInternal();
                if (this.videoHandler != null && !this.videoHandler.hasMessages(2)) {
                    this.videoHandler.sendMessageDelayed(Message.obtain(this.videoHandler, 2), 500L);
                }
            }
        }
    }

    private View.OnTouchListener createOnTouchListener() {
        return new VideoTouchListener(this);
    }

    private static class VideoTouchListener implements View.OnTouchListener {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public VideoTouchListener(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View v, MotionEvent event) {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                return inlineView.onTouch(v, event);
            }
            return true;
        }
    }

    boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == 1) {
            MMLayout mmLayout = this.mmLayoutRef.get();
            if (mmLayout == null) {
                MMLog.w(TAG, "MMLayout weak reference broken");
                return false;
            }
            if (!TextUtils.isEmpty(this.inlineParams.touchCallBack)) {
                mmLayout.loadUrl(String.format("javascript:" + this.inlineParams.touchCallBack + "(%f,%f)", Float.valueOf(event.getX()), Float.valueOf(event.getY())));
            }
            if (this.inlineParams.showControls && this.mediaController != null && !this.mediaController.isShowing()) {
                this.mediaController.show();
                return true;
            }
        }
        return true;
    }

    private Handler createVideoHandler() {
        return new VideoHandler(this);
    }

    private static class VideoHandler extends Handler {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public VideoHandler(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    InlineVideoView inlineView = this.inlineVideoRef.get();
                    if (inlineView != null) {
                        if (inlineView.isPlaying()) {
                            inlineView.updateVideoSeekTime();
                        }
                        inlineView.videoHandler.sendMessageDelayed(Message.obtain(inlineView.videoHandler, 2), 500L);
                    }
                    break;
            }
        }
    }

    void updateVideoSeekTime() {
        int time = getCurrentPosition();
        if (time >= 0) {
            MMLog.d(TAG, "Time is " + time);
            double timeInSeconds = Math.floor(time / 1000.0f);
            updateVideoSeekTime(timeInSeconds);
        }
    }

    void updateVideoSeekTimeFinal() {
        if (this.duration > 0) {
            MMLog.d(TAG, "Time is " + this.duration);
            double timeInSeconds = Math.ceil(this.duration / 1000.0f);
            updateVideoSeekTime(timeInSeconds);
        }
    }

    void updateVideoSeekTime(double timeInSeconds) {
        MMLayout mmLayout = this.mmLayoutRef.get();
        if (mmLayout == null) {
            MMLog.w(TAG, "MMLayout weak reference broken");
        }
        mmLayout.loadUrl("javascript:MMJS.inlineVideo.updateVideoSeekTime(" + timeInSeconds + ");");
    }

    void removeVideo() {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        if (isPlaying()) {
            stopPlayback();
        }
        setOnCompletionListener(null);
        setOnErrorListener(null);
        setOnPreparedListener(null);
        setOnTouchListener(null);
        removeFromParent();
    }

    private void removeFromParent() {
        ViewGroup currentParent = (ViewGroup) getParent();
        if (currentParent != null) {
            currentParent.removeView(this);
        }
    }

    void playVideo() {
        if (!isPlaying()) {
            if (this.inlineParams.isStopped && getVideoUri() != null) {
                this.inlineParams.isStopped = false;
                setVideoURI(getVideoUri());
                seekTo(0);
            } else if (this.inlineParams.isCompleted) {
                seekTo(0);
            }
            this.inlineParams.isCompleted = false;
            startInternal();
        }
        if (this.videoHandler != null && !this.videoHandler.hasMessages(2)) {
            this.videoHandler.sendMessageDelayed(Message.obtain(this.videoHandler, 2), 500L);
        }
    }

    public void startInternal() {
        PowerManager pm = (PowerManager) getContext().getSystemService("power");
        if (pm.isScreenOn()) {
            start();
        }
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public void start() {
        makeTransparent();
        removeKeyboardFocusViewJira1642();
        super.start();
    }

    private static class TransparentHandler extends Handler {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public TransparentHandler(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                inlineView.handleTransparentMessage(msg);
            }
        }
    }

    void handleTransparentMessage(Message msg) {
        switch (msg.what) {
            case 4:
                if (isPlaying() && getCurrentPosition() > 0) {
                    this.transparentHandler.sendEmptyMessageDelayed(5, 100L);
                } else {
                    this.transparentHandler.sendEmptyMessageDelayed(4, 50L);
                }
                break;
            case 5:
                if (isPlaying() && getCurrentPosition() > 0) {
                    setBackgroundColor(0);
                    if (this.mmLayoutRef != null && this.mmLayoutRef.get() != null) {
                        this.mmLayoutRef.get().removeBlackView();
                        break;
                    }
                }
                break;
        }
    }

    private void makeTransparent() {
        if (!this.transparentHandler.hasMessages(4)) {
            this.transparentHandler.sendEmptyMessage(4);
        }
    }

    void stopVideo() {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        if (isPlaying()) {
            this.inlineParams.isStopped = true;
            this.inlineParams.currentPosition = 0;
            if (this.mmLayoutRef != null && this.mmLayoutRef.get() != null) {
                this.mmLayoutRef.get().addBlackView();
            }
            stopPlayback();
        }
    }

    void pauseVideo() {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        if (isPlaying()) {
            this.inlineParams.currentPosition = getCurrentPosition();
            pause();
        }
    }

    void resumeVideo() {
        if (!isPlaying() && !this.inlineParams.isCompleted) {
            startInternal();
            if (this.videoHandler != null && !this.videoHandler.hasMessages(2)) {
                this.videoHandler.sendMessageDelayed(Message.obtain(this.videoHandler, 2), 500L);
            }
        }
    }

    private MediaPlayer.OnErrorListener createOnErrorListener() {
        return new VideoErrorListener(this);
    }

    private static class VideoErrorListener implements MediaPlayer.OnErrorListener {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public VideoErrorListener(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mp, int what, int extra) {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                inlineView.onError(mp, what, extra);
                return true;
            }
            return true;
        }
    }

    boolean onError(MediaPlayer mp, int what, int extra) {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        MMLayout mmLayout = this.mmLayoutRef.get();
        if (mmLayout == null) {
            MMLog.w(TAG, "MMLayout weak reference broken");
            return false;
        }
        mmLayout.loadUrl("javascript:MMJS.setError(" + String.format("Error while playing, %d - %d", Integer.valueOf(what), Integer.valueOf(extra)) + ");");
        return true;
    }

    private MediaPlayer.OnCompletionListener createOnCompletionListener() {
        return new VideoCompletionListener(this);
    }

    private static class VideoCompletionListener implements MediaPlayer.OnCompletionListener {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public VideoCompletionListener(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mp) {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                inlineView.onCompletion(mp);
            }
        }
    }

    void onCompletion(MediaPlayer mp) {
        if (this.videoHandler != null && this.videoHandler.hasMessages(2)) {
            this.videoHandler.removeMessages(2);
        }
        this.inlineParams.isCompleted = true;
        this.inlineParams.currentPosition = this.duration;
        if (this.inlineParams.currentPosition == -1) {
            this.inlineParams.currentPosition = 0;
        }
        updateVideoSeekTimeFinal();
    }

    private MediaPlayer.OnPreparedListener createOnPreparedListener() {
        return new VideoPreparedListener(this);
    }

    private static class VideoPreparedListener implements MediaPlayer.OnPreparedListener {
        private WeakReference<InlineVideoView> inlineVideoRef;

        public VideoPreparedListener(InlineVideoView videoView) {
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mp) {
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                inlineView.onPrepared(mp);
            }
        }
    }

    void onPrepared(MediaPlayer mp) {
        if (this.inlineParams.autoPlay) {
            makeTransparent();
        }
        seekTo(this.inlineParams.currentPosition);
        if (this.inlineParams.autoPlay || !this.inlineParams.isInitialPlayBack) {
            getHeight();
        } else {
            this.inlineParams.isInitialPlayBack = false;
        }
        this.duration = getDuration();
    }

    void setVideoSource(String streamVideoURI) {
        if (isPlaying()) {
            stopPlayback();
        }
        this.inlineParams.currentPosition = 0;
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setVideoURI(Uri.parse(streamVideoURI));
        startInternal();
    }

    private void removeKeyboardFocusViewJira1642() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
        requestFocus();
    }

    private static class MediaController extends android.widget.MediaController {
        BitmapDrawable inDrawable;
        private WeakReference<InlineVideoView> inlineVideoRef;
        BitmapDrawable outDrawable;

        public MediaController(InlineVideoView videoView) {
            super(videoView.getContext());
            this.inlineVideoRef = new WeakReference<>(videoView);
        }

        @Override // android.widget.MediaController
        public void setAnchorView(View view) {
            super.setAnchorView(view);
            Button fullScreenButton = new Button(getContext());
            if (this.inDrawable == null) {
                try {
                    byte[] decodedIn = Base64.decode(InlineVideoView.ANCHOR_IN_PNG);
                    ByteArrayInputStream bias = new ByteArrayInputStream(decodedIn);
                    this.inDrawable = new BitmapDrawable(bias);
                } catch (Exception e) {
                    MMLog.e(InlineVideoView.TAG, "Exception setting image anchorView inDrawable:", e);
                }
            }
            if (this.outDrawable == null) {
                try {
                    byte[] decodedOut = Base64.decode(InlineVideoView.ANCHOR_OUT_PNG);
                    ByteArrayInputStream bias2 = new ByteArrayInputStream(decodedOut);
                    this.outDrawable = new BitmapDrawable(bias2);
                } catch (Exception e2) {
                    MMLog.e(InlineVideoView.TAG, "Exception setting image anchorView outDrawable:", e2);
                }
            }
            InlineVideoView inlineView = this.inlineVideoRef.get();
            if (inlineView != null) {
                if (inlineView.inlineParams.goingFullScreen) {
                    fullScreenButton.setBackgroundDrawable(this.inDrawable);
                } else {
                    fullScreenButton.setBackgroundDrawable(this.outDrawable);
                }
                fullScreenButton.setOnClickListener(new MediaClickListener(inlineView));
            }
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2, 5);
            params.setMargins(0, 20, 10, 0);
            addView(fullScreenButton, params);
        }

        static final class MediaClickListener implements View.OnClickListener {
            private WeakReference<InlineVideoView> inlineVideoRef;

            public MediaClickListener(InlineVideoView inlineView) {
                this.inlineVideoRef = new WeakReference<>(inlineView);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View arg0) {
                InlineVideoView inlineView = this.inlineVideoRef.get();
                if (inlineView != null) {
                    inlineView.onMediaControllerClick(arg0);
                }
            }
        }
    }

    boolean isPlayingStreaming() {
        return this.inlineParams != null && this.inlineParams.isPlayingStreaming;
    }

    void onMediaControllerClick(View arg0) {
        MMLayout mmLayout = this.mmLayoutRef.get();
        if (mmLayout != null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            if (isPlaying()) {
                this.inlineParams.currentPosition = getCurrentPosition();
            }
            if (this.inlineParams.goingFullScreen) {
                this.inlineParams.goingFullScreen = false;
                if (this.inlineParams.originalOrientation == 1) {
                    Activity activity = (Activity) getContext();
                    if (activity != null) {
                        activity.setRequestedOrientation(1);
                        return;
                    }
                    return;
                }
                boolean wasPlaying = isPlaying();
                stopPlayback();
                mmLayout.repositionVideoLayout();
                resumeInternal(wasPlaying);
                return;
            }
            this.inlineParams.originalOrientation = getContext().getResources().getConfiguration().orientation;
            this.inlineParams.goingFullScreen = true;
            if (this.inlineParams.originalOrientation != 2) {
                Activity activity2 = (Activity) getContext();
                if (activity2 != null) {
                    activity2.setRequestedOrientation(0);
                    return;
                }
                return;
            }
            boolean wasPlaying2 = isPlaying();
            stopPlayback();
            mmLayout.fullScreenVideoLayout();
            resumeInternal(wasPlaying2);
        }
    }
}
