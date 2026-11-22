package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import com.mopub.common.DownloadResponse;
import com.mopub.common.DownloadTask;
import com.mopub.common.HttpClient;
import com.mopub.common.HttpResponses;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Drawables;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Streams;
import com.mopub.common.util.VersionCode;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.mopub.mobileads.util.vast.VastCompanionAd;
import com.mopub.mobileads.util.vast.VastVideoConfiguration;
import com.mopub.nativeads.MoPubNativeAdPositioning;
import com.mopub.network.TrackingRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.methods.HttpGet;

/* JADX INFO: loaded from: classes.dex */
public class VastVideoViewController extends BaseVideoViewController implements DownloadTask.DownloadTaskListener {
    static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    static final int MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON = 16000;
    private static final int MAX_VIDEO_RETRIES = 1;
    private static final int MOPUB_BROWSER_REQUEST_CODE = 1;
    static final String VAST_VIDEO_CONFIGURATION = "vast_video_configuration";
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    private static final int VIDEO_VIEW_FILE_PERMISSION_ERROR = Integer.MIN_VALUE;
    private final View.OnTouchListener mClickThroughListener;
    private final ImageView mCompanionAdImageView;
    private boolean mCompletionTrackerFired;
    private final Handler mHandler;
    private boolean mIsVideoFinishedPlaying;
    private boolean mIsVideoProgressShouldBeChecked;
    private int mSeekerPositionOnPause;
    private int mShowCloseButtonDelay;
    private boolean mShowCloseButtonEventFired;
    private final VastCompanionAd mVastCompanionAd;
    private final VastVideoConfiguration mVastVideoConfiguration;
    private final VastVideoToolbar mVastVideoToolbar;
    private boolean mVideoError;
    private final Runnable mVideoProgressCheckerRunnable;
    private int mVideoRetries;
    private final VideoView mVideoView;

    VastVideoViewController(Context context, Bundle bundle, long broadcastIdentifier, BaseVideoViewController.BaseVideoViewControllerListener baseVideoViewControllerListener) throws IllegalStateException {
        super(context, Long.valueOf(broadcastIdentifier), baseVideoViewControllerListener);
        this.mShowCloseButtonDelay = 5000;
        this.mHandler = new Handler();
        this.mIsVideoProgressShouldBeChecked = false;
        this.mSeekerPositionOnPause = -1;
        this.mVideoRetries = 0;
        Serializable serializable = bundle.getSerializable(VAST_VIDEO_CONFIGURATION);
        if (serializable != null && (serializable instanceof VastVideoConfiguration)) {
            this.mVastVideoConfiguration = (VastVideoConfiguration) serializable;
            if (this.mVastVideoConfiguration.getDiskMediaFileUrl() == null) {
                throw new IllegalStateException("VastVideoConfiguration does not have a video disk path");
            }
            this.mVastCompanionAd = this.mVastVideoConfiguration.getVastCompanionAd();
            this.mClickThroughListener = new View.OnTouchListener() { // from class: com.mopub.mobileads.VastVideoViewController.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1 && VastVideoViewController.this.shouldAllowClickThrough()) {
                        VastVideoViewController.this.handleClick(VastVideoViewController.this.mVastVideoConfiguration.getClickTrackers(), VastVideoViewController.this.mVastVideoConfiguration.getClickThroughUrl());
                    }
                    return true;
                }
            };
            createVideoBackground(context);
            this.mVideoView = createVideoView(context);
            this.mVideoView.requestFocus();
            this.mVastVideoToolbar = createVastVideoToolBar(context);
            getLayout().addView(this.mVastVideoToolbar);
            this.mCompanionAdImageView = createCompanionAdImageView(context);
            this.mVideoProgressCheckerRunnable = createVideoProgressCheckerRunnable();
            return;
        }
        throw new IllegalStateException("VastVideoConfiguration is invalid");
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    protected void onCreate() {
        super.onCreate();
        getBaseVideoViewControllerListener().onSetRequestedOrientation(0);
        downloadCompanionAd();
        TrackingRequest.makeTrackingHttpRequest(this.mVastVideoConfiguration.getImpressionTrackers(), getContext(), BaseEvent.Name.IMPRESSION_REQUEST);
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    protected void onResume() {
        this.mVideoRetries = 0;
        startProgressChecker();
        this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    protected void onPause() {
        stopProgressChecker();
        this.mSeekerPositionOnPause = this.mVideoView.getCurrentPosition();
        this.mVideoView.pause();
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    protected void onDestroy() {
        stopProgressChecker();
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    @Override // com.mopub.mobileads.BaseVideoViewController
    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == -1) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    @Override // com.mopub.common.DownloadTask.DownloadTaskListener
    public void onComplete(String url, DownloadResponse downloadResponse) {
        Bitmap companionAdBitmap;
        if (downloadResponse != null && downloadResponse.getStatusCode() == 200 && (companionAdBitmap = HttpResponses.asBitmap(downloadResponse)) != null) {
            int width = Dips.dipsToIntPixels(companionAdBitmap.getWidth(), getContext());
            int height = Dips.dipsToIntPixels(companionAdBitmap.getHeight(), getContext());
            int imageViewWidth = this.mCompanionAdImageView.getMeasuredWidth();
            int imageViewHeight = this.mCompanionAdImageView.getMeasuredHeight();
            if (width < imageViewWidth && height < imageViewHeight) {
                this.mCompanionAdImageView.getLayoutParams().width = width;
                this.mCompanionAdImageView.getLayoutParams().height = height;
            }
            this.mCompanionAdImageView.setImageBitmap(companionAdBitmap);
            this.mCompanionAdImageView.setOnClickListener(new View.OnClickListener() { // from class: com.mopub.mobileads.VastVideoViewController.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (VastVideoViewController.this.mVastCompanionAd != null) {
                        VastVideoViewController.this.handleClick(VastVideoViewController.this.mVastCompanionAd.getClickTrackers(), VastVideoViewController.this.mVastCompanionAd.getClickThroughUrl());
                    }
                }
            });
        }
    }

    private void downloadCompanionAd() {
        if (this.mVastCompanionAd != null) {
            try {
                HttpGet httpGet = HttpClient.initializeHttpGet(this.mVastCompanionAd.getImageUrl(), getContext());
                DownloadTask downloadTask = new DownloadTask(this);
                AsyncTasks.safeExecuteOnExecutor(downloadTask, httpGet);
            } catch (Exception e) {
                MoPubLog.d("Failed to download companion ad", e);
            }
        }
    }

    @NonNull
    private Runnable createVideoProgressCheckerRunnable() {
        return new Runnable() { // from class: com.mopub.mobileads.VastVideoViewController.3
            @Override // java.lang.Runnable
            public void run() {
                int videoLength = VastVideoViewController.this.mVideoView.getDuration();
                int currentPosition = VastVideoViewController.this.mVideoView.getCurrentPosition();
                if (videoLength > 0) {
                    List<VastTracker> trackersToTrack = VastVideoViewController.this.getUntriggeredTrackersBefore(currentPosition, videoLength);
                    if (!trackersToTrack.isEmpty()) {
                        List<String> trackUrls = new ArrayList<>();
                        for (VastTracker tracker : trackersToTrack) {
                            trackUrls.add(tracker.getTrackingUrl());
                            tracker.setTracked();
                        }
                        TrackingRequest.makeTrackingHttpRequest(trackUrls, VastVideoViewController.this.getContext());
                    }
                    if (VastVideoViewController.this.isLongVideo(VastVideoViewController.this.mVideoView.getDuration())) {
                        VastVideoViewController.this.mVastVideoToolbar.updateCountdownWidget(VastVideoViewController.this.mShowCloseButtonDelay - VastVideoViewController.this.mVideoView.getCurrentPosition());
                    }
                    if (VastVideoViewController.this.shouldBeInteractable()) {
                        VastVideoViewController.this.makeVideoInteractable();
                    }
                }
                VastVideoViewController.this.mVastVideoToolbar.updateDurationWidget(VastVideoViewController.this.mVideoView.getDuration() - VastVideoViewController.this.mVideoView.getCurrentPosition());
                if (VastVideoViewController.this.mIsVideoProgressShouldBeChecked) {
                    VastVideoViewController.this.mHandler.postDelayed(VastVideoViewController.this.mVideoProgressCheckerRunnable, VastVideoViewController.VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public List<VastTracker> getUntriggeredTrackersBefore(int currentPositionMillis, int videoLengthMillis) {
        if (Preconditions.NoThrow.checkArgument(videoLengthMillis > 0)) {
            float progressFraction = currentPositionMillis / videoLengthMillis;
            List<VastTracker> untriggeredTrackers = new ArrayList<>();
            ArrayList<VastAbsoluteProgressTracker> absoluteTrackers = this.mVastVideoConfiguration.getAbsoluteTrackers();
            VastAbsoluteProgressTracker absoluteTest = new VastAbsoluteProgressTracker("", currentPositionMillis);
            int absoluteTrackerCount = absoluteTrackers.size();
            for (int i = 0; i < absoluteTrackerCount; i++) {
                VastAbsoluteProgressTracker tracker = absoluteTrackers.get(i);
                if (tracker.compareTo(absoluteTest) > 0) {
                    break;
                }
                if (!tracker.isTracked()) {
                    untriggeredTrackers.add(tracker);
                }
            }
            ArrayList<VastFractionalProgressTracker> fractionalTrackers = this.mVastVideoConfiguration.getFractionalTrackers();
            VastFractionalProgressTracker fractionalTest = new VastFractionalProgressTracker("", progressFraction);
            int fractionalTrackerCount = fractionalTrackers.size();
            for (int i2 = 0; i2 < fractionalTrackerCount; i2++) {
                VastFractionalProgressTracker tracker2 = fractionalTrackers.get(i2);
                if (tracker2.compareTo(fractionalTest) <= 0) {
                    if (!tracker2.isTracked()) {
                        untriggeredTrackers.add(tracker2);
                    }
                } else {
                    return untriggeredTrackers;
                }
            }
            return untriggeredTrackers;
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int remainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT, MoPubNativeAdPositioning.MoPubClientPositioning.NO_REPEAT).size();
    }

    private void createVideoBackground(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.argb(0, 0, 0, 0), Color.argb(MotionEventCompat.ACTION_MASK, 0, 0, 0)});
        Drawable[] layers = {Drawables.THATCHED_BACKGROUND.createDrawable(context), gradientDrawable};
        LayerDrawable layerList = new LayerDrawable(layers);
        getLayout().setBackgroundDrawable(layerList);
    }

    private VastVideoToolbar createVastVideoToolBar(final Context context) {
        VastVideoToolbar vastVideoToolbar = new VastVideoToolbar(context);
        vastVideoToolbar.setCloseButtonOnTouchListener(new View.OnTouchListener() { // from class: com.mopub.mobileads.VastVideoViewController.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    TrackingRequest.makeTrackingHttpRequest(VastVideoViewController.this.mVastVideoConfiguration.getCloseTrackers(), context);
                    VastVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
                }
                return true;
            }
        });
        vastVideoToolbar.setLearnMoreButtonOnTouchListener(this.mClickThroughListener);
        return vastVideoToolbar;
    }

    private VideoView createVideoView(final Context context) {
        final VideoView videoView = new VideoView(context);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.mopub.mobileads.VastVideoViewController.5
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mp) {
                if (VastVideoViewController.this.mVideoView.getDuration() < VastVideoViewController.MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON) {
                    VastVideoViewController.this.mShowCloseButtonDelay = VastVideoViewController.this.mVideoView.getDuration();
                }
            }
        });
        videoView.setOnTouchListener(this.mClickThroughListener);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.mopub.mobileads.VastVideoViewController.6
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mp) {
                VastVideoViewController.this.stopProgressChecker();
                VastVideoViewController.this.makeVideoInteractable();
                VastVideoViewController.this.videoCompleted(false);
                VastVideoViewController.this.mIsVideoFinishedPlaying = true;
                if (!VastVideoViewController.this.mVideoError && VastVideoViewController.this.remainingProgressTrackerCount() == 0 && !VastVideoViewController.this.mCompletionTrackerFired) {
                    TrackingRequest.makeTrackingHttpRequest(VastVideoViewController.this.mVastVideoConfiguration.getCompleteTrackers(), context);
                    VastVideoViewController.this.mCompletionTrackerFired = true;
                }
                videoView.setVisibility(8);
                if (VastVideoViewController.this.mCompanionAdImageView.getDrawable() != null) {
                    VastVideoViewController.this.mCompanionAdImageView.setVisibility(0);
                }
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.mopub.mobileads.VastVideoViewController.7
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                if (VastVideoViewController.this.retryMediaPlayer(mediaPlayer, what, extra)) {
                    return true;
                }
                VastVideoViewController.this.stopProgressChecker();
                VastVideoViewController.this.makeVideoInteractable();
                VastVideoViewController.this.videoError(false);
                VastVideoViewController.this.mVideoError = true;
                return false;
            }
        });
        videoView.setVideoPath(this.mVastVideoConfiguration.getDiskMediaFileUrl());
        return videoView;
    }

    boolean retryMediaPlayer(MediaPlayer mediaPlayer, int what, int extra) throws Throwable {
        if (!VersionCode.currentApiLevel().isBelow(VersionCode.JELLY_BEAN) || what != 1 || extra != Integer.MIN_VALUE || this.mVideoRetries >= 1) {
            return false;
        }
        FileInputStream inputStream = null;
        try {
            mediaPlayer.reset();
            File file = new File(this.mVastVideoConfiguration.getDiskMediaFileUrl());
            FileInputStream inputStream2 = new FileInputStream(file);
            try {
                mediaPlayer.setDataSource(inputStream2.getFD());
                mediaPlayer.prepareAsync();
                this.mVideoView.start();
                Streams.closeStream(inputStream2);
                this.mVideoRetries++;
                return true;
            } catch (Exception e) {
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                this.mVideoRetries++;
                return false;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                this.mVideoRetries++;
                throw th;
            }
        } catch (Exception e2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @VisibleForTesting
    void handleClick(List<String> clickThroughTrackers, String clickThroughUrl) {
        TrackingRequest.makeTrackingHttpRequest(clickThroughTrackers, getContext(), BaseEvent.Name.CLICK_REQUEST);
        if (clickThroughUrl != null) {
            broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
            if (Intents.isNativeBrowserScheme(clickThroughUrl)) {
                try {
                    Intent intent = Intents.intentForNativeBrowserScheme(clickThroughUrl);
                    Intents.startActivity(getContext(), intent);
                    return;
                } catch (IntentNotResolvableException e) {
                    MoPubLog.d("Could not handle intent for URI: " + clickThroughUrl + ". " + e.getMessage());
                    return;
                } catch (UrlParseException e2) {
                    MoPubLog.d(e2.getMessage());
                    return;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, clickThroughUrl);
            getBaseVideoViewControllerListener().onStartActivityForResult(MoPubBrowser.class, 1, bundle);
        }
    }

    private ImageView createCompanionAdImageView(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, this.mVastVideoToolbar.getId());
        getLayout().addView(relativeLayout, layoutParams);
        ImageView imageView = new ImageView(context);
        imageView.setVisibility(4);
        RelativeLayout.LayoutParams companionAdLayout = new RelativeLayout.LayoutParams(-1, -1);
        relativeLayout.addView(imageView, companionAdLayout);
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLongVideo(int duration) {
        return duration >= MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mVastVideoToolbar.makeInteractable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && this.mVideoView.getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startProgressChecker() {
        if (!this.mIsVideoProgressShouldBeChecked) {
            this.mIsVideoProgressShouldBeChecked = true;
            this.mHandler.post(this.mVideoProgressCheckerRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopProgressChecker() {
        if (this.mIsVideoProgressShouldBeChecked) {
            this.mIsVideoProgressShouldBeChecked = false;
            this.mHandler.removeCallbacks(this.mVideoProgressCheckerRunnable);
        }
    }

    @VisibleForTesting
    @Deprecated
    boolean getIsVideoProgressShouldBeChecked() {
        return this.mIsVideoProgressShouldBeChecked;
    }

    @VisibleForTesting
    @Deprecated
    int getVideoRetries() {
        return this.mVideoRetries;
    }

    @VisibleForTesting
    @Deprecated
    int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @VisibleForTesting
    @Deprecated
    boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @VisibleForTesting
    @Deprecated
    void setCloseButtonVisible(boolean visible) {
        this.mShowCloseButtonEventFired = visible;
    }

    @VisibleForTesting
    @Deprecated
    boolean isVideoFinishedPlaying() {
        return this.mIsVideoFinishedPlaying;
    }

    @VisibleForTesting
    @Deprecated
    ImageView getCompanionAdImageView() {
        return this.mCompanionAdImageView;
    }

    @VisibleForTesting
    @Deprecated
    void setVideoError() {
        this.mVideoError = true;
    }

    @VisibleForTesting
    @Deprecated
    boolean getVideoError() {
        return this.mVideoError;
    }
}
