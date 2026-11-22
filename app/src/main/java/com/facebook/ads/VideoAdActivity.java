package com.facebook.ads;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.facebook.ads.a.a.b;
import com.facebook.ads.a.a.c;
import com.facebook.ads.a.y;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VideoAdActivity extends Activity {
    private static final long CONTROLS_FADE_DELAY = 3000;
    public static final String MARKET_URI_INTENT_EXTRA = "adMarketUri";
    public static final String URI_INTENT_EXTRA = "adUri";
    public static final String VIDEO_PATH_INTENT_EXTRA = "adVideoPath";
    private List mAllControls;
    private List mBufferingView;
    private ImageButton mCloseButton;
    private List mEndControls;
    private List mFullPlayingControls;
    private ImageButton mInstallButton;
    private MediaPlayer mMediaPlayer;
    private List mMinPlayingControls;
    private ImageButton mMuteButton;
    private List mPausedControls;
    private View mRootView;
    private ImageButton mSkipButton;
    private a mState;
    private long mTimeOfLastTouch;
    private VideoView mVideoView;
    private Uri marketUri;
    private String path;
    private RelativeLayout relativeLayout;
    private String uniqueId;
    private Uri uri;
    private boolean mIsMuted = false;
    private int mCurrentPosition = -1;
    private Handler mHandler = new Handler();
    private boolean mGoToAppStore = false;

    private enum a {
        UNINITIALIZED,
        INITIALIZED,
        PLAYING,
        PAUSED,
        COMPLETED
    }

    private void activateControlSet(List list) {
        for (ImageButton imageButton : this.mAllControls) {
            if (list.contains(imageButton)) {
                imageButton.setVisibility(0);
            } else {
                imageButton.setVisibility(8);
            }
        }
    }

    private void bindModel() {
        setState(a.UNINITIALIZED);
        this.mInstallButton.setBackground(getResources().getDrawable(R.drawable.stat_sys_download));
        this.mSkipButton.setBackground(getResources().getDrawable(R.drawable.ic_menu_info_details));
        this.mMuteButton.setBackground(getResources().getDrawable(R.drawable.ic_lock_silent_mode_off));
        this.mCloseButton.setBackground(getResources().getDrawable(R.drawable.ic_menu_close_clear_cancel));
        this.mVideoView.setVideoPath(this.path);
    }

    private void configureLayout() {
        this.relativeLayout.addView(this.mRootView);
        this.relativeLayout.addView(this.mInstallButton);
        this.relativeLayout.addView(this.mSkipButton);
        this.relativeLayout.addView(this.mMuteButton);
        this.relativeLayout.addView(this.mCloseButton);
    }

    private void configureViews() {
        this.mAllControls = Arrays.asList(this.mInstallButton, this.mMuteButton, this.mCloseButton, this.mSkipButton);
        this.mFullPlayingControls = Arrays.asList(this.mInstallButton, this.mMuteButton, this.mCloseButton, this.mSkipButton);
        this.mMinPlayingControls = Arrays.asList(this.mCloseButton, this.mInstallButton, this.mSkipButton);
        this.mPausedControls = Arrays.asList(this.mInstallButton, this.mMuteButton, this.mCloseButton, this.mSkipButton);
        this.mEndControls = Arrays.asList(this.mInstallButton, this.mMuteButton, this.mCloseButton, this.mSkipButton);
        this.mBufferingView = Arrays.asList(this.mInstallButton, this.mCloseButton, this.mSkipButton);
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: com.facebook.ads.VideoAdActivity.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    view.setAlpha(0.5f);
                    return false;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                view.setAlpha(1.0f);
                return false;
            }
        };
        this.mInstallButton.setOnTouchListener(onTouchListener);
        this.mSkipButton.setOnTouchListener(onTouchListener);
        this.mMuteButton.setOnTouchListener(onTouchListener);
        this.mCloseButton.setOnTouchListener(onTouchListener);
        this.mInstallButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.VideoAdActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoAdActivity.this.mGoToAppStore = true;
                VideoAdActivity.this.finish();
            }
        });
        this.mSkipButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.VideoAdActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoAdActivity.this.displayInterstitial();
            }
        });
        this.mMuteButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.VideoAdActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoAdActivity.this.videoMute();
            }
        });
        this.mCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.ads.VideoAdActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoAdActivity.this.finish();
            }
        });
        this.mRootView.setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.VideoAdActivity.10
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                VideoAdActivity.this.pulseControlsFromTouch();
                return false;
            }
        });
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.facebook.ads.VideoAdActivity.11
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoAdActivity.this.mMediaPlayer = mediaPlayer;
                if (VideoAdActivity.this.mIsMuted) {
                    VideoAdActivity.this.mMediaPlayer.setVolume(0.0f, 0.0f);
                } else {
                    VideoAdActivity.this.mMediaPlayer.setVolume(1.0f, 1.0f);
                }
                VideoAdActivity.this.updateVideoPlayerSize();
                if (VideoAdActivity.this.mState == a.PAUSED || VideoAdActivity.this.mState == a.COMPLETED) {
                    VideoAdActivity.this.mMediaPlayer.seekTo(VideoAdActivity.this.mCurrentPosition);
                    return;
                }
                if (VideoAdActivity.this.mState == a.PLAYING) {
                    VideoAdActivity.this.mMediaPlayer.seekTo(VideoAdActivity.this.mCurrentPosition);
                    VideoAdActivity.this.videoPlay();
                } else if (VideoAdActivity.this.mState == a.UNINITIALIZED) {
                    VideoAdActivity.this.setState(a.INITIALIZED);
                    VideoAdActivity.this.videoPlay();
                }
            }
        });
        this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.facebook.ads.VideoAdActivity.12
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoAdActivity.this.setState(a.COMPLETED);
                VideoAdActivity.this.displayInterstitial();
            }
        });
        this.mVideoView.setOnTouchListener(new View.OnTouchListener() { // from class: com.facebook.ads.VideoAdActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                VideoAdActivity.this.pulseControlsFromTouch();
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayInterstitial() {
        Intent intent = new Intent(this, (Class<?>) InterstitialAdActivity.class);
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        intent.putExtra(InterstitialAd.DISPLAY_ROTATION_INTENT_EXTRA, defaultDisplay.getRotation());
        intent.putExtra(InterstitialAd.DISPLAY_WIDTH_INTENT_EXTRA, displayMetrics.widthPixels);
        intent.putExtra(InterstitialAd.DISPLAY_HEIGHT_INTENT_EXTRA, displayMetrics.heightPixels);
        intent.putExtra(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA, this.uniqueId);
        y.b(getIntent()).a(intent);
        startActivity(intent);
        finish();
    }

    private void getIntentExtras() {
        Bundle extras = getIntent().getExtras();
        this.uri = Uri.parse(extras.getString(URI_INTENT_EXTRA));
        this.marketUri = Uri.parse(extras.getString(MARKET_URI_INTENT_EXTRA));
        this.path = extras.getString(VIDEO_PATH_INTENT_EXTRA);
        this.uniqueId = extras.getString(InterstitialAd.INTERSTITIAL_UNIQUE_ID_EXTRA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pulseControlsFromTouch() {
        this.mTimeOfLastTouch = System.currentTimeMillis();
        updateControlState();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.postDelayed(new Runnable() { // from class: com.facebook.ads.VideoAdActivity.3
            @Override // java.lang.Runnable
            public void run() {
                VideoAdActivity.this.updateControlState();
            }
        }, CONTROLS_FADE_DELAY);
    }

    private void setButtonPosition() {
        this.mInstallButton.setX(270.0f);
        this.mInstallButton.setY(1400.0f);
        this.mSkipButton.setX(700.0f);
        this.mSkipButton.setY(1400.0f);
        this.mMuteButton.setX(10.0f);
        this.mMuteButton.setY(1160.0f);
        this.mCloseButton.setX(970.0f);
        this.mInstallButton.setScaleX(1.5f);
        this.mInstallButton.setScaleY(1.5f);
        this.mSkipButton.setScaleX(1.5f);
        this.mSkipButton.setScaleY(1.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(a aVar) {
        if (aVar != this.mState) {
            this.mState = aVar;
            pulseControlsFromTouch();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateControlState() {
        this.mInstallButton.setAlpha(1.0f);
        this.mSkipButton.setAlpha(1.0f);
        this.mCloseButton.setAlpha(1.0f);
        this.mVideoView.setAlpha(1.0f);
        switch (this.mState) {
            case PLAYING:
                if (System.currentTimeMillis() - this.mTimeOfLastTouch < CONTROLS_FADE_DELAY) {
                    activateControlSet(this.mFullPlayingControls);
                } else {
                    activateControlSet(this.mMinPlayingControls);
                    this.mInstallButton.setAlpha(0.5f);
                    this.mSkipButton.setAlpha(0.5f);
                    this.mCloseButton.setAlpha(0.5f);
                }
                break;
            case PAUSED:
                activateControlSet(this.mPausedControls);
                break;
            case UNINITIALIZED:
                activateControlSet(this.mBufferingView);
                break;
            case INITIALIZED:
                activateControlSet(this.mBufferingView);
                break;
            case COMPLETED:
                activateControlSet(this.mEndControls);
                this.mMediaPlayer.seekTo((int) (this.mMediaPlayer.getDuration() * 0.75f));
                this.mVideoView.setAlpha(1.0f);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVideoPlayerSize() {
        if (this.mMediaPlayer != null) {
            float fMin = Math.min(this.mRootView.getWidth() / this.mMediaPlayer.getVideoWidth(), this.mRootView.getHeight() / this.mMediaPlayer.getVideoHeight());
            this.mVideoView.getHolder().setFixedSize((int) (this.mMediaPlayer.getVideoWidth() * fMin), (int) (fMin * this.mMediaPlayer.getVideoHeight()));
            this.mVideoView.requestLayout();
            this.mVideoView.invalidate();
        }
    }

    private void videoBackground() {
        if (this.mVideoView.isPlaying()) {
            this.mCurrentPosition = this.mVideoView.getCurrentPosition();
        }
        this.mVideoView.pause();
        this.mHandler.removeCallbacksAndMessages(null);
        this.mMediaPlayer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void videoMute() {
        if (this.mState != a.UNINITIALIZED) {
            if (this.mIsMuted) {
                this.mMediaPlayer.setVolume(1.0f, 1.0f);
                this.mMuteButton.setBackground(getResources().getDrawable(R.drawable.ic_lock_silent_mode_off));
            } else {
                this.mMediaPlayer.setVolume(0.0f, 0.0f);
                this.mMuteButton.setBackground(getResources().getDrawable(R.drawable.ic_lock_silent_mode));
            }
            this.mIsMuted = !this.mIsMuted;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void videoPlay() {
        if (this.mState == a.UNINITIALIZED || this.mState == a.PLAYING) {
            return;
        }
        if (this.mState == a.COMPLETED) {
            this.mVideoView.seekTo(0);
        }
        this.mVideoView.start();
        this.mCurrentPosition = this.mVideoView.getCurrentPosition();
        setState(a.PLAYING);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.facebook.ads.VideoAdActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                VideoAdActivity.this.mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                VideoAdActivity.this.updateVideoPlayerSize();
            }
        });
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(15);
        setContentView(this.relativeLayout, layoutParams);
        this.mVideoView = new VideoView(this);
        this.mVideoView.setLayoutParams(layoutParams);
        this.relativeLayout.addView(this.mVideoView);
        this.mRootView = new View(this);
        this.mInstallButton = new ImageButton(this);
        this.mSkipButton = new ImageButton(this);
        this.mMuteButton = new ImageButton(this);
        this.mCloseButton = new ImageButton(this);
        getIntentExtras();
        setVolumeControlStream(3);
        configureViews();
        bindModel();
        setButtonPosition();
        configureLayout();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mGoToAppStore) {
            ((c) b.a(this, this.uri)).d();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        videoBackground();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mState == a.PLAYING && !this.mVideoView.isPlaying()) {
            this.mVideoView.seekTo(this.mCurrentPosition);
            this.mVideoView.start();
        }
        pulseControlsFromTouch();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        videoBackground();
    }
}
