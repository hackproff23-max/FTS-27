package com.facebook.ads.a.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.VideoAdActivity;
import com.google.android.gms.drive.DriveFile;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e extends c {
    private static final String a = e.class.getSimpleName();
    private final Context b;
    private final Uri c;

    public e(Context context, Uri uri) {
        super(context, uri);
        this.b = context;
        this.c = uri;
    }

    private static void a(Map map, Intent intent) {
        for (Map.Entry entry : map.entrySet()) {
            intent.putExtra((String) entry.getKey(), (String) entry.getValue());
        }
    }

    @Override // com.facebook.ads.a.a.c, com.facebook.ads.a.a.a
    public void a(Map map) {
        a(this.b, this.c);
        String queryParameter = this.c.getQueryParameter(BaseVideoPlayerActivity.VIDEO_URL);
        Intent intent = new Intent(this.b, (Class<?>) VideoAdActivity.class);
        intent.putExtra(VideoAdActivity.URI_INTENT_EXTRA, this.c.toString());
        intent.putExtra(VideoAdActivity.VIDEO_PATH_INTENT_EXTRA, queryParameter);
        intent.putExtra(VideoAdActivity.MARKET_URI_INTENT_EXTRA, b().toString());
        a(map, intent);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        try {
            this.b.startActivity(intent);
        } catch (Exception e) {
            Log.d(a, "Failed to start video", e);
        }
    }
}
