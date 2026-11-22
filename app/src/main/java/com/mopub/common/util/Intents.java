package com.mopub.common.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Intents {
    private static final String MARKET = "market";
    private static final String MARKET_ANDROID_COM = "market.android.com";
    private static final String PLAY_GOOGLE_COM = "play.google.com";

    private Intents() {
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            throw new IntentNotResolvableException(e);
        }
    }

    public static Intent getStartActivityIntent(@NonNull Context context, @NonNull Class clazz, @Nullable Bundle extras) {
        Intent intent = new Intent(context, (Class<?>) clazz);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        if (extras != null) {
            intent.putExtras(extras);
        }
        return intent;
    }

    public static boolean deviceCanHandleIntent(@NonNull Context context, @NonNull Intent intent) {
        try {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
            return !activities.isEmpty();
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean isHttpUrl(String url) {
        if (url == null) {
            return false;
        }
        String scheme = Uri.parse(url).getScheme();
        return Constants.HTTP.equals(scheme) || Constants.HTTPS.equals(scheme);
    }

    private static boolean isAppStoreUrl(String url) {
        if (url == null) {
            return false;
        }
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        String host = uri.getHost();
        if (PLAY_GOOGLE_COM.equals(host) || MARKET_ANDROID_COM.equals(host)) {
            return true;
        }
        return "market".equals(scheme);
    }

    public static boolean isDeepLink(String url) {
        return isAppStoreUrl(url) || !isHttpUrl(url);
    }

    public static boolean canHandleApplicationUrl(Context context, String url) {
        return canHandleApplicationUrl(context, url, true);
    }

    public static boolean canHandleApplicationUrl(Context context, String url, boolean logError) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        if (deviceCanHandleIntent(context, intent)) {
            return true;
        }
        if (logError) {
            MoPubLog.w("Could not handle application specific action: " + url + ". You may be running in the emulator or another device which does not have the required application.");
        }
        return false;
    }

    public static boolean isNativeBrowserScheme(@NonNull String url) {
        return url.startsWith("mopubnativebrowser://");
    }

    public static Intent intentForNativeBrowserScheme(@NonNull String url) throws UrlParseException {
        Preconditions.checkNotNull(url);
        if (!isNativeBrowserScheme(url)) {
            throw new UrlParseException("URL does not have mopubnativebrowser:// scheme.");
        }
        Uri uri = Uri.parse(url);
        if (!"navigate".equals(uri.getHost())) {
            throw new UrlParseException("URL missing 'navigate' host parameter.");
        }
        try {
            String urlToOpenInNativeBrowser = uri.getQueryParameter("url");
            if (urlToOpenInNativeBrowser == null) {
                throw new UrlParseException("URL missing 'url' query parameter.");
            }
            Uri intentUri = Uri.parse(urlToOpenInNativeBrowser);
            return new Intent("android.intent.action.VIEW", intentUri);
        } catch (UnsupportedOperationException e) {
            MoPubLog.w("Could not handle url: " + url);
            throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
        }
    }
}
