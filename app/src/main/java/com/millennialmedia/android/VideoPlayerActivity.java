package com.millennialmedia.android;

import android.R;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.vending.expansion.downloader.Constants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class VideoPlayerActivity extends MMBaseActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final int CONTROLS_ID = 83756563;
    private static final String END_VIDEO = "endVideo";
    protected static final int MESSAGE_CHECK_PLAYING_VIDEO = 4;
    protected static final int MESSAGE_DELAYED_BUTTON = 3;
    protected static final int MESSAGE_INACTIVITY_ANIMATION = 1;
    protected static final int MESSAGE_ONE_SECOND_CHECK = 2;
    protected static final int MESSAGE_SET_TRANSPARENCY = 5;
    private static final String RESTART_VIDEO = "restartVideo";
    private static final String TAG = "VideoPlayerActivity";
    View blackView;
    private boolean hasFocus;
    boolean isPaused;
    protected boolean isVideoCompleted;
    private boolean isVideoCompletedOnce;
    String lastOverlayOrientation;
    protected VideoView mVideoView;
    Button pausePlay;
    ProgressBar progBar;
    HttpRedirection.RedirectionListenerImpl redirectListenerImpl;
    RelativeLayout videoLayout;
    private boolean shouldSetUri = true;
    protected boolean hasBottomBar = true;
    protected int currentVideoPosition = 0;
    TransparentHandler transparentHandler = new TransparentHandler(this);
    boolean isUserPausing = false;

    VideoPlayerActivity() {
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme);
        super.onCreate(savedInstanceState);
        MMLog.d(TAG, "Setting up the video player");
        initWindow();
        initSavedInstance(savedInstanceState);
        initRedirectListener();
        RelativeLayout parent = initLayout();
        setContentView(parent);
    }

    private void initWindow() {
        requestWindowFeature(1);
        getWindow().clearFlags(2048);
        getWindow().addFlags(1024);
    }

    protected void initSavedInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.isVideoCompleted = savedInstanceState.getBoolean(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED);
            this.isVideoCompletedOnce = savedInstanceState.getBoolean("videoCompletedOnce");
            this.currentVideoPosition = savedInstanceState.getInt("videoPosition");
            this.hasBottomBar = savedInstanceState.getBoolean("hasBottomBar");
            this.shouldSetUri = savedInstanceState.getBoolean("shouldSetUri");
        }
    }

    static class VideoRedirectionListener extends HttpRedirection.RedirectionListenerImpl {
        WeakReference<VideoPlayerActivity> activityRef;

        public VideoRedirectionListener(VideoPlayerActivity activity) {
            if (activity != null) {
                this.activityRef = new WeakReference<>(activity);
                if (activity.activity != null) {
                    this.creatorAdImplInternalId = activity.activity.creatorAdImplInternalId;
                }
            }
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public boolean isHandlingMMVideo(Uri uri) {
            final VideoPlayerActivity activity = this.activityRef.get();
            if (activity != null) {
                activity.runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.VideoPlayerActivity.VideoRedirectionListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        activity.enableButtons();
                    }
                });
                if (uri != null && activity.isActionable(uri)) {
                    activity.processVideoPlayerUri(uri.getHost());
                    return true;
                }
            }
            return false;
        }

        @Override // com.millennialmedia.android.HttpRedirection.RedirectionListenerImpl, com.millennialmedia.android.HttpRedirection.Listener
        public OverlaySettings getOverlaySettings() {
            VideoPlayerActivity activity = this.activityRef.get();
            if (activity == null || activity.lastOverlayOrientation == null) {
                return null;
            }
            OverlaySettings settings = new OverlaySettings();
            settings.orientation = activity.lastOverlayOrientation;
            return settings;
        }
    }

    private void initRedirectListener() {
        this.redirectListenerImpl = new VideoRedirectionListener(this);
    }

    protected void enableButtons() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isActionable(Uri actionUri) {
        if (actionUri.getScheme().equalsIgnoreCase("mmsdk")) {
            if (isActionSupported(actionUri.getHost())) {
                return true;
            }
            MMLog.v(TAG, String.format("Unrecognized mmsdk:// URI %s.", actionUri));
        }
        return false;
    }

    private boolean isActionSupported(String action) {
        return action != null && (action.equalsIgnoreCase(RESTART_VIDEO) || action.equalsIgnoreCase(END_VIDEO));
    }

    void processVideoPlayerUri(final String action) {
        runOnUiThread(new Runnable() { // from class: com.millennialmedia.android.VideoPlayerActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (action.equalsIgnoreCase(VideoPlayerActivity.RESTART_VIDEO)) {
                    VideoPlayerActivity.this.restartVideo();
                } else if (action.equalsIgnoreCase(VideoPlayerActivity.END_VIDEO)) {
                    VideoPlayerActivity.this.endVideo();
                }
            }
        });
    }

    protected void restartVideo() {
        MMLog.d(TAG, "Restart Video.");
        if (this.mVideoView != null) {
            playVideo(0);
        }
    }

    protected void endVideo() {
        MMLog.d(TAG, "End Video.");
        if (this.mVideoView != null) {
            this.shouldSetUri = true;
            dismiss();
        }
    }

    private void initBottomBar(RelativeLayout relativeLayout) {
        RelativeLayout controlsLayout = new RelativeLayout(this.activity);
        controlsLayout.setId(CONTROLS_ID);
        controlsLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        RelativeLayout.LayoutParams controlsLp = new RelativeLayout.LayoutParams(-1, -2);
        controlsLayout.setLayoutParams(controlsLp);
        controlsLp.addRule(12);
        Button mRewind = new Button(this.activity);
        this.pausePlay = new Button(this.activity);
        Button mStop = new Button(this.activity);
        mRewind.setBackgroundResource(R.drawable.ic_media_previous);
        if (this.mVideoView.isPlaying()) {
            this.pausePlay.setBackgroundResource(R.drawable.ic_media_pause);
        } else {
            this.pausePlay.setBackgroundResource(R.drawable.ic_media_play);
        }
        mStop.setBackgroundResource(R.drawable.ic_menu_close_clear_cancel);
        RelativeLayout.LayoutParams pauseLp = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout.LayoutParams stopLp = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout.LayoutParams rewindLp = new RelativeLayout.LayoutParams(-2, -2);
        pauseLp.addRule(14);
        controlsLayout.addView(this.pausePlay, pauseLp);
        rewindLp.addRule(0, this.pausePlay.getId());
        controlsLayout.addView(mRewind);
        stopLp.addRule(11);
        controlsLayout.addView(mStop, stopLp);
        mRewind.setOnClickListener(new View.OnClickListener() { // from class: com.millennialmedia.android.VideoPlayerActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoPlayerActivity.this.mVideoView != null) {
                    VideoPlayerActivity.this.mVideoView.seekTo(0);
                }
            }
        });
        this.pausePlay.setOnClickListener(new View.OnClickListener() { // from class: com.millennialmedia.android.VideoPlayerActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoPlayerActivity.this.mVideoView != null) {
                    if (VideoPlayerActivity.this.mVideoView.isPlaying()) {
                        VideoPlayerActivity.this.pauseVideoByUser();
                        VideoPlayerActivity.this.pausePlay.setBackgroundResource(R.drawable.ic_media_play);
                        return;
                    }
                    if (VideoPlayerActivity.this.isVideoCompleted) {
                        VideoPlayerActivity.this.playVideo(0);
                    } else if (VideoPlayerActivity.this.isUserPausing && !VideoPlayerActivity.this.isVideoCompleted) {
                        VideoPlayerActivity.this.resumeVideo();
                    } else {
                        VideoPlayerActivity.this.playVideo(VideoPlayerActivity.this.currentVideoPosition);
                    }
                    VideoPlayerActivity.this.pausePlay.setBackgroundResource(R.drawable.ic_media_pause);
                }
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() { // from class: com.millennialmedia.android.VideoPlayerActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VideoPlayerActivity.this.mVideoView != null) {
                    VideoPlayerActivity.this.shouldSetUri = true;
                    VideoPlayerActivity.this.dismiss();
                }
            }
        });
        relativeLayout.addView(controlsLayout, controlsLp);
    }

    protected RelativeLayout initLayout() {
        RelativeLayout parent = new RelativeLayout(this.activity);
        parent.setId(Constants.STATUS_BAD_REQUEST);
        parent.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        parent.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.videoLayout = new RelativeLayout(this.activity);
        this.videoLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.videoLayout.setId(WalletConstants.ERROR_CODE_INVALID_TRANSACTION);
        RelativeLayout.LayoutParams videoContainerLp = new RelativeLayout.LayoutParams(-1, -2);
        RelativeLayout.LayoutParams videoLp = new RelativeLayout.LayoutParams(-1, -1);
        videoLp.addRule(13);
        videoContainerLp.addRule(13);
        this.mVideoView = new VideoView(this.activity);
        this.mVideoView.setId(411);
        this.mVideoView.getHolder().setFormat(-2);
        this.mVideoView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        initVideoListeners();
        this.videoLayout.addView(this.mVideoView, videoLp);
        this.blackView = new View(this.activity);
        this.blackView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        RelativeLayout.LayoutParams blackViewParams = new RelativeLayout.LayoutParams(-1, -1);
        parent.addView(this.videoLayout, videoContainerLp);
        if (this.hasBottomBar) {
            blackViewParams.addRule(2, CONTROLS_ID);
            initBottomBar(parent);
        }
        this.blackView.setLayoutParams(blackViewParams);
        parent.addView(this.blackView);
        this.progBar = new ProgressBar(this.activity);
        this.progBar.setIndeterminate(true);
        RelativeLayout.LayoutParams progParams = new RelativeLayout.LayoutParams(-2, -2);
        progParams.addRule(13);
        this.progBar.setLayoutParams(progParams);
        parent.addView(this.progBar);
        this.progBar.setVisibility(4);
        return parent;
    }

    protected boolean canFadeButtons() {
        return !this.isVideoCompleted;
    }

    protected void setButtonAlpha(ImageButton button, float alpha) {
        AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
        animation.setDuration(0L);
        animation.setFillEnabled(true);
        animation.setFillBefore(true);
        animation.setFillAfter(true);
        button.startAnimation(animation);
    }

    protected void logButtonEvent(VideoImage button) {
        MMLog.d(TAG, "Cached video button event logged");
        for (int i = 0; i < button.eventLoggingUrls.length; i++) {
            MMSDK.Event.logEvent(button.eventLoggingUrls[i]);
        }
    }

    protected void playVideo(int position) {
        try {
            this.isUserPausing = false;
            Uri uri = getIntent().getData();
            String fullPath = uri.toString();
            MMLog.d(TAG, String.format("playVideo path: %s", fullPath));
            if (fullPath == null || fullPath.length() == 0 || this.mVideoView == null) {
                errorPlayVideo("no name or null videoview");
                return;
            }
            this.isVideoCompleted = false;
            if (this.shouldSetUri && this.mVideoView != null) {
                this.mVideoView.setVideoURI(Uri.parse(fullPath));
            }
            startVideo(position);
        } catch (Exception e) {
            MMLog.e(TAG, "playVideo error: ", e);
            errorPlayVideo("error: " + e);
        }
    }

    protected void errorPlayVideo(String error) {
        Toast.makeText(this.activity, "Sorry. There was a problem playing the video", 1).show();
        if (this.mVideoView != null) {
            this.mVideoView.stopPlayback();
        }
    }

    private void startVideo(int position) {
        this.mVideoView.requestFocus();
        this.mVideoView.seekTo(position);
        PowerManager pm = (PowerManager) getSystemService("power");
        if (pm.isScreenOn()) {
            if (this.progBar != null) {
                this.progBar.bringToFront();
                this.progBar.setVisibility(0);
            }
            if (this.pausePlay != null) {
                this.pausePlay.setBackgroundResource(R.drawable.ic_media_pause);
            }
            this.mVideoView.start();
            makeTransparent();
        }
    }

    private static class TransparentHandler extends Handler {
        private WeakReference<VideoPlayerActivity> activityRef;

        public TransparentHandler(VideoPlayerActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            VideoPlayerActivity activity = this.activityRef.get();
            if (activity != null) {
                activity.handleTransparentMessage(msg);
            }
        }
    }

    void handleTransparentMessage(Message msg) {
        switch (msg.what) {
            case 4:
                if (this.mVideoView != null && this.mVideoView.isPlaying() && this.mVideoView.getCurrentPosition() > 0) {
                    this.mVideoView.setBackgroundColor(0);
                    this.transparentHandler.sendEmptyMessageDelayed(5, 100L);
                } else {
                    this.transparentHandler.sendEmptyMessageDelayed(4, 50L);
                }
                break;
            case 5:
                if (this.mVideoView != null && this.mVideoView.isPlaying() && this.mVideoView.getCurrentPosition() > 0) {
                    this.blackView.setVisibility(4);
                    this.progBar.setVisibility(4);
                    break;
                }
                break;
        }
    }

    private void makeTransparent() {
        if (!this.transparentHandler.hasMessages(4)) {
            this.transparentHandler.sendEmptyMessage(4);
        }
    }

    private void initVideoListeners() {
        this.mVideoView.setOnCompletionListener(this);
        this.mVideoView.setOnPreparedListener(this);
        this.mVideoView.setOnErrorListener(this);
    }

    public void onCompletion(MediaPlayer mp) {
        this.isVideoCompletedOnce = true;
        this.isVideoCompleted = true;
        if (this.pausePlay != null && !this.mVideoView.isPlaying()) {
            this.pausePlay.setBackgroundResource(R.drawable.ic_media_play);
        }
        MMLog.v(TAG, "Video player on complete");
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mp) {
        MMLog.d(TAG, "Video Prepared");
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    protected void onResume() {
        super.onResume();
        this.blackView.bringToFront();
        this.blackView.setVisibility(0);
        this.isPaused = false;
        MMLog.v(TAG, "VideoPlayer - onResume");
        if (this.hasFocus && !this.isUserPausing) {
            resumeVideo();
        }
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    protected void onPause() {
        super.onPause();
        this.isPaused = true;
        MMLog.v(TAG, "VideoPlayer - onPause");
        pauseVideo();
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        this.hasFocus = hasWindowFocus;
        if (!this.isPaused && hasWindowFocus && !this.isUserPausing) {
            resumeVideo();
        }
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && event.getRepeatCount() == 0 && !this.isVideoCompletedOnce) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void dismiss() {
        MMLog.d(TAG, "Video ad player closed");
        if (this.mVideoView != null) {
            if (this.mVideoView.isPlaying()) {
                this.mVideoView.stopPlayback();
            }
            this.mVideoView = null;
        }
        finish();
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentVideoPosition", this.currentVideoPosition);
        outState.putBoolean("isVideoCompleted", this.isVideoCompleted);
        outState.putBoolean("isVideoCompletedOnce", this.isVideoCompletedOnce);
        outState.putBoolean("hasBottomBar", this.hasBottomBar);
        outState.putBoolean("shouldSetUri", this.shouldSetUri);
        outState.putBoolean("isUserPausing", this.isUserPausing);
        outState.putBoolean("isPaused", this.isPaused);
        super.onSaveInstanceState(outState);
    }

    @Override // com.millennialmedia.android.MMBaseActivity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        this.currentVideoPosition = savedInstanceState.getInt("currentVideoPosition");
        this.isVideoCompleted = savedInstanceState.getBoolean("isVideoCompleted");
        this.isVideoCompletedOnce = savedInstanceState.getBoolean("isVideoCompletedOnce");
        this.hasBottomBar = savedInstanceState.getBoolean("hasBottomBar", this.hasBottomBar);
        this.shouldSetUri = savedInstanceState.getBoolean("shouldSetUri", this.shouldSetUri);
        this.isUserPausing = savedInstanceState.getBoolean("isUserPausing", this.isUserPausing);
        this.isPaused = savedInstanceState.getBoolean("isPaused", this.isPaused);
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void pauseVideoByUser() {
        this.isUserPausing = true;
        pauseVideo();
    }

    protected void pauseVideo() {
        if (this.mVideoView != null && this.mVideoView.isPlaying()) {
            this.currentVideoPosition = this.mVideoView.getCurrentPosition();
            this.mVideoView.pause();
            MMLog.v(TAG, "Video paused");
        }
    }

    protected boolean isPlayable() {
        return (this.mVideoView == null || this.mVideoView.isPlaying() || this.isVideoCompleted) ? false : true;
    }

    protected void resumeVideo() {
        if (isPlayable()) {
            playVideo(this.currentVideoPosition);
        }
    }

    void dispatchButtonClick(String urlString) {
        if (urlString != null) {
            MMLog.d(TAG, String.format("Button Click with URL: %s", urlString));
            this.redirectListenerImpl.url = urlString;
            this.redirectListenerImpl.weakContext = new WeakReference<>(this.activity);
            if (!this.redirectListenerImpl.isHandlingMMVideo(Uri.parse(urlString))) {
                HttpRedirection.startActivityFromUri(this.redirectListenerImpl);
            }
        }
    }
}
