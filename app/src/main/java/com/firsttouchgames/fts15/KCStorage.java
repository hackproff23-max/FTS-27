package com.firsttouchgames.fts15;

import android.app.Activity;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public class KCStorage {
    private static String PREFS_NAME = "MySettings";
    static Activity m_Activity;

    public static void SetActivity(Activity act) {
        m_Activity = act;
    }

    public static void SetSettingName(String name) {
        PREFS_NAME = name;
    }

    public static void SaveSetting(String settingName, String settingValue) {
        SharedPreferences settings = m_Activity.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(settingName, settingValue);
        editor.commit();
    }

    public static String LoadSetting(String settingName) {
        SharedPreferences settings = m_Activity.getSharedPreferences(PREFS_NAME, 0);
        String settingValue = settings.getString(settingName, "");
        return settingValue;
    }

    public static boolean SettingExisits(String settingName) {
        SharedPreferences settings = m_Activity.getSharedPreferences(PREFS_NAME, 0);
        return settings.contains(settingName);
    }
}
