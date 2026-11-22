package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.util.vast.VastVideoConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class BaseVideoPlayerActivity extends Activity {
    static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(Context context, String videoUrl) {
        Intent intentVideoPlayerActivity = createIntentMraid(context, videoUrl);
        try {
            context.startActivity(intentVideoPlayerActivity);
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(Context context, String videoUrl) {
        Intent intentVideoPlayerActivity = new Intent(context, (Class<?>) MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(DriveFile.MODE_READ_ONLY);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, AdType.MRAID);
        intentVideoPlayerActivity.putExtra(VIDEO_URL, videoUrl);
        return intentVideoPlayerActivity;
    }

    static void startVast(Context context, VastVideoConfiguration vastVideoConfiguration, long broadcastIdentifier) {
        Intent intentVideoPlayerActivity = createIntentVast(context, vastVideoConfiguration, broadcastIdentifier);
        try {
            context.startActivity(intentVideoPlayerActivity);
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(Context context, VastVideoConfiguration vastVideoConfiguration, long broadcastIdentifier) {
        Intent intentVideoPlayerActivity = new Intent(context, (Class<?>) MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(DriveFile.MODE_READ_ONLY);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, "vast");
        intentVideoPlayerActivity.putExtra("vast_video_configuration", vastVideoConfiguration);
        intentVideoPlayerActivity.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        return intentVideoPlayerActivity;
    }
}
