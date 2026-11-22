package com.facebook.internal;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookException;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static final Uri ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");

    private static AttributionIdentifiers getAndroidId(Context context) {
        Method getAdvertisingIdInfo;
        Object advertisingInfo;
        AttributionIdentifiers identifiers = new AttributionIdentifiers();
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method isGooglePlayServicesAvailable = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
            if (isGooglePlayServicesAvailable != null) {
                Object connectionResult = Utility.invokeMethodQuietly(null, isGooglePlayServicesAvailable, context);
                if ((connectionResult instanceof Integer) && ((Integer) connectionResult).intValue() == 0 && (getAdvertisingIdInfo = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class})) != null && (advertisingInfo = Utility.invokeMethodQuietly(null, getAdvertisingIdInfo, context)) != null) {
                    Method getId = Utility.getMethodQuietly(advertisingInfo.getClass(), "getId", (Class<?>[]) new Class[0]);
                    Method isLimitAdTrackingEnabled = Utility.getMethodQuietly(advertisingInfo.getClass(), GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, (Class<?>[]) new Class[0]);
                    if (getId != null && isLimitAdTrackingEnabled != null) {
                        identifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(advertisingInfo, getId, new Object[0]);
                        identifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(advertisingInfo, isLimitAdTrackingEnabled, new Object[0])).booleanValue();
                    }
                }
            }
            return identifiers;
        } catch (Exception e) {
            Utility.logd("android_id", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00b2  */
    public static AttributionIdentifiers getAttributionIdentifiers(Context context) {
        if (recentlyFetchedIdentifiers != null && System.currentTimeMillis() - recentlyFetchedIdentifiers.fetchTime < IDENTIFIER_REFRESH_INTERVAL_MILLIS) {
            return recentlyFetchedIdentifiers;
        }
        AttributionIdentifiers identifiers = getAndroidId(context);
        Cursor c = null;
        try {
            try {
                String[] projection = {ATTRIBUTION_ID_COLUMN_NAME, ANDROID_ID_COLUMN_NAME, LIMIT_TRACKING_COLUMN_NAME};
                c = context.getContentResolver().query(ATTRIBUTION_ID_CONTENT_URI, projection, null, null, null);
                if (c == null || !c.moveToFirst()) {
                    if (c == null) {
                        return identifiers;
                    }
                    c.close();
                    return identifiers;
                }
                int attributionColumnIndex = c.getColumnIndex(ATTRIBUTION_ID_COLUMN_NAME);
                int androidIdColumnIndex = c.getColumnIndex(ANDROID_ID_COLUMN_NAME);
                int limitTrackingColumnIndex = c.getColumnIndex(LIMIT_TRACKING_COLUMN_NAME);
                identifiers.attributionId = c.getString(attributionColumnIndex);
                if (androidIdColumnIndex > 0 && limitTrackingColumnIndex > 0 && identifiers.getAndroidAdvertiserId() == null) {
                    identifiers.androidAdvertiserId = c.getString(androidIdColumnIndex);
                    identifiers.limitTracking = Boolean.parseBoolean(c.getString(limitTrackingColumnIndex));
                }
                if (c != null) {
                    c.close();
                }
                identifiers.fetchTime = System.currentTimeMillis();
                recentlyFetchedIdentifiers = identifiers;
                return identifiers;
            } catch (Exception e) {
                Log.d(TAG, "Caught unexpected exception in getAttributionId(): " + e.toString());
                if (c != null) {
                    c.close();
                }
                return null;
            }
        } catch (Throwable th) {
            if (c != null) {
                c.close();
            }
            throw th;
        }
        if (c != null) {
            c.close();
        }
        throw th;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
