package com.millennialmedia.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
final class MMConversionTracker {
    private static final String KEY_REFERRER = "installReferrer";
    private static final String TAG = "MMConversionTracker";

    MMConversionTracker() {
    }

    protected static synchronized void trackConversion(Context context, final String goalId, MMRequest request) {
        long installTime = 0;
        if (context != null && goalId != null) {
            if (goalId.length() != 0) {
                SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
                final boolean isFirstLaunch = settings.getBoolean("firstLaunch_" + goalId, true);
                String referrerString = settings.getString(KEY_REFERRER, null);
                final TreeMap<String, String> extraParams = new TreeMap<>();
                if (request != null) {
                    request.getUrlParams(extraParams);
                    MMRequest.insertLocation(extraParams);
                }
                if (referrerString != null) {
                    String[] components = referrerString.split("&");
                    for (String param : components) {
                        String[] subComponents = param.split("=");
                        if (subComponents.length >= 2) {
                            extraParams.put(subComponents[0], subComponents[1]);
                        }
                    }
                }
                if (isFirstLaunch) {
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("firstLaunch_" + goalId, false);
                    editor.commit();
                }
                try {
                    PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                    try {
                        Field field = info.getClass().getField("firstInstallTime");
                        installTime = field.getLong(info);
                    } catch (Exception e) {
                        MMLog.e(TAG, "Error with firstInstallTime", e);
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    MMLog.e(TAG, "Can't find packagename: ", e2);
                }
                if (installTime > 0) {
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.setTimeInMillis(installTime);
                    calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
                    installTime = calendar.getTimeInMillis();
                }
                final long installTimeUTC = installTime;
                if (MMSDK.isConnected(context)) {
                    extraParams.put("ua", "Android:" + Build.MODEL);
                    extraParams.put("apid", HandShake.apid);
                    MMSDK.insertUrlCommonValues(context, extraParams);
                    Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.MMConversionTracker.1
                        @Override // java.lang.Runnable
                        public void run() {
                            HttpGetRequest tc = new HttpGetRequest();
                            try {
                                tc.trackConversion(goalId, isFirstLaunch, installTimeUTC, extraParams);
                            } catch (Exception e3) {
                                MMLog.e(MMConversionTracker.TAG, "Problem doing conversion tracking.", e3);
                            }
                        }
                    });
                } else {
                    MMLog.w(TAG, "No network available for conversion tracking.");
                }
            }
        }
    }
}
