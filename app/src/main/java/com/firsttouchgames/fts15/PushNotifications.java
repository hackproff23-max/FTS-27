package com.firsttouchgames.fts15;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.firsttouchgames.util.ExternalReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class PushNotifications {
    private static MainActivity mActivity;
    private static GoogleCloudMessaging msGCM;
    private static ArrayList<NotificationData> ms_Notifications;

    public static void Initialise(MainActivity mainActivity) {
        mActivity = mainActivity;
        ms_Notifications = new ArrayList<>();
        CancelAllTimedNotifications(10);
    }

    public static void Setup() {
        msGCM = GoogleCloudMessaging.getInstance(mActivity.getApplicationContext());
        Register();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.firsttouchgames.fts15.PushNotifications$1] */
    private static void Register() {
        new AsyncTask() { // from class: com.firsttouchgames.fts15.PushNotifications.1
            @Override // android.os.AsyncTask
            protected Object doInBackground(Object... params) {
                try {
                    Context context = PushNotifications.mActivity.getApplicationContext();
                    String token = PushNotifications.msGCM.register(context.getString(com.FTS27.giiodroicerargaming.R.string.project_number));
                    Log.i("registrationId", token);
                    SharedPreferences sharedPref = PushNotifications.mActivity.getPreferences(0);
                    if (sharedPref.getBoolean(context.getString(com.FTS27.giiodroicerargaming.R.string.first_launch), true)) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean(context.getString(com.FTS27.giiodroicerargaming.R.string.first_launch), false);
                        editor.commit();
                    }
                } catch (IOException e) {
                    Log.i("Registration Error", e.getMessage());
                }
                return true;
            }
        }.execute(null, null, null);
    }

    public static void SendNotification(Context context, String sMessage) {
        if (sMessage != null) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(com.FTS27.giiodroicerargaming.R.drawable.fts_icon).setContentTitle(context.getString(com.FTS27.giiodroicerargaming.R.string.app_name)).setContentText(sMessage);
            Intent resultIntent = new Intent(context, (Class<?>) MainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 134217728);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager notifyMngr = (NotificationManager) context.getSystemService("notification");
            notifyMngr.notify(1, mBuilder.build());
        }
    }

    public static void Reset() {
        ms_Notifications.clear();
    }

    public static int AddTimedNotification(int iSeconds, String sMessage, int iReward) {
        NotificationData data = new NotificationData();
        data.iSeconds = iSeconds;
        data.sMessage = sMessage;
        data.iReward = iReward;
        ms_Notifications.add(data);
        return ms_Notifications.size() - 1;
    }

    public static void SendAllTimedNotifications() {
        for (int iNotification = 0; iNotification < ms_Notifications.size(); iNotification++) {
            NotificationData data = ms_Notifications.get(iNotification);
            SendTimedNotification(data.sMessage, data.iSeconds, iNotification);
        }
    }

    public static void SendTimedNotification(String sMsg, int iSeconds, int iNotification) {
        AlarmManager alarm = (AlarmManager) mActivity.getSystemService("alarm");
        Intent resultIntent = new Intent(mActivity, (Class<?>) ExternalReceiver.class);
        resultIntent.putExtra(UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_KEY, sMsg);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, iNotification, resultIntent, 134217728);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + ((long) (iSeconds * 1000)));
        alarm.set(0, calendar.getTimeInMillis(), pendingIntent);
    }

    public static void CancelTimedNotification(int iNotificationID) {
        AlarmManager alarm = (AlarmManager) mActivity.getSystemService("alarm");
        Intent resultIntent = new Intent(mActivity, (Class<?>) ExternalReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, iNotificationID, resultIntent, 134217728);
        alarm.cancel(pendingIntent);
    }

    public static void CancelAllTimedNotifications(int iNumOverride) {
        AlarmManager alarm = (AlarmManager) mActivity.getSystemService("alarm");
        Intent resultIntent = new Intent(mActivity, (Class<?>) ExternalReceiver.class);
        int iNumNotifications = ms_Notifications.size();
        if (iNumNotifications <= 0) {
            iNumNotifications = iNumOverride;
        }
        for (int iNotification = 0; iNotification < iNumNotifications; iNotification++) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mActivity, iNotification, resultIntent, 134217728);
            alarm.cancel(pendingIntent);
        }
    }
}
