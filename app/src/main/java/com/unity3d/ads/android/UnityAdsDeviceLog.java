package com.unity3d.ads.android;

import android.util.Log;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsDeviceLog {
    private static HashMap f;
    private static boolean a = true;
    private static boolean b = true;
    private static boolean c = true;
    private static boolean d = false;
    private static boolean e = true;
    public static int LOGLEVEL_NONE = 0;
    public static int LOGLEVEL_ERROR = 1;
    public static int LOGLEVEL_WARNING = 2;
    public static int LOGLEVEL_INFO = 4;
    public static int LOGLEVEL_DEBUG = 8;

    public enum UnityAdsLogLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    static {
        f = null;
        HashMap map = new HashMap();
        f = map;
        map.put(UnityAdsLogLevel.INFO, new UnityAdsDeviceLogLevel(UnityAdsLogLevel.INFO, UnityAdsConstants.LOG_NAME, "i"));
        f.put(UnityAdsLogLevel.DEBUG, new UnityAdsDeviceLogLevel(UnityAdsLogLevel.DEBUG, UnityAdsConstants.LOG_NAME, "d"));
        f.put(UnityAdsLogLevel.WARNING, new UnityAdsDeviceLogLevel(UnityAdsLogLevel.WARNING, UnityAdsConstants.LOG_NAME, "w"));
        f.put(UnityAdsLogLevel.ERROR, new UnityAdsDeviceLogLevel(UnityAdsLogLevel.ERROR, UnityAdsConstants.LOG_NAME, "e"));
    }

    public static void setLogLevel(int i) {
        if (i >= LOGLEVEL_DEBUG) {
            b = true;
            c = true;
            e = true;
            d = true;
            return;
        }
        if (i >= LOGLEVEL_INFO) {
            b = true;
            c = true;
            e = true;
            d = false;
            return;
        }
        if (i >= LOGLEVEL_WARNING) {
            b = true;
            c = true;
            e = false;
            d = false;
            return;
        }
        if (i >= LOGLEVEL_ERROR) {
            b = true;
            c = false;
            e = false;
            d = false;
            return;
        }
        b = false;
        c = false;
        e = false;
        d = false;
    }

    public static void entered() {
        debug("ENTERED METHOD");
    }

    public static void info(String str) {
        a(UnityAdsLogLevel.INFO, a(str));
    }

    public static void info(String str, Object... objArr) {
        info(String.format(str, objArr));
    }

    public static void debug(String str) {
        a(UnityAdsLogLevel.DEBUG, a(str));
    }

    public static void debug(String str, Object... objArr) {
        debug(String.format(str, objArr));
    }

    public static void warning(String str) {
        a(UnityAdsLogLevel.WARNING, a(str));
    }

    public static void warning(String str, Object... objArr) {
        warning(String.format(str, objArr));
    }

    public static void error(String str) {
        a(UnityAdsLogLevel.ERROR, a(str));
    }

    public static void error(String str, Object... objArr) {
        error(String.format(str, objArr));
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0061  */
    /* JADX WARN: Code duplicated, block: B:27:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x00af  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b1  */
    private static void a(UnityAdsLogLevel unityAdsLogLevel, String str) {
        UnityAdsDeviceLogEntry unityAdsDeviceLogEntry;
        Method method;
        StackTraceElement stackTraceElement;
        boolean z = false;
        boolean z2 = a;
        if (z2) {
            switch (l.a[unityAdsLogLevel.ordinal()]) {
                case 1:
                    z2 = e;
                    break;
                case 2:
                    z2 = d;
                    break;
                case 3:
                    z2 = c;
                    break;
                case 4:
                    z2 = b;
                    break;
            }
        }
        if (z2) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            UnityAdsDeviceLogLevel unityAdsDeviceLogLevel = (UnityAdsDeviceLogLevel) f.get(unityAdsLogLevel);
            if (unityAdsDeviceLogLevel != null) {
                for (int i = 0; i < stackTrace.length; i++) {
                    StackTraceElement stackTraceElement2 = stackTrace[i];
                    if (stackTraceElement2.getClassName().equals(UnityAdsDeviceLog.class.getName())) {
                        z = true;
                    }
                    if (!stackTraceElement2.getClassName().equals(UnityAdsDeviceLog.class.getName()) && z) {
                        if (i < stackTrace.length) {
                            stackTraceElement = stackTrace[i];
                        } else {
                            stackTraceElement = null;
                        }
                        if (stackTraceElement != null) {
                            unityAdsDeviceLogEntry = new UnityAdsDeviceLogEntry(unityAdsDeviceLogLevel, str, stackTraceElement);
                        } else {
                            unityAdsDeviceLogEntry = null;
                        }
                    }
                }
                if (i < stackTrace.length) {
                    stackTraceElement = stackTrace[i];
                } else {
                    stackTraceElement = null;
                }
                if (stackTraceElement != null) {
                    unityAdsDeviceLogEntry = new UnityAdsDeviceLogEntry(unityAdsDeviceLogLevel, str, stackTraceElement);
                } else {
                    unityAdsDeviceLogEntry = null;
                }
            } else {
                unityAdsDeviceLogEntry = null;
            }
            if (unityAdsDeviceLogEntry == null || unityAdsDeviceLogEntry.getLogLevel() == null) {
                return;
            }
            try {
                method = Log.class.getMethod(unityAdsDeviceLogEntry.getLogLevel().getReceivingMethodName(), String.class, String.class);
            } catch (Exception e2) {
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(null, unityAdsDeviceLogEntry.getLogLevel().getLogTag(), unityAdsDeviceLogEntry.getParsedMessage());
                } catch (Exception e3) {
                }
            }
        }
    }

    private static String a(String str) {
        if (str == null || str.length() == 0) {
            return "DO NOT USE EMPTY MESSAGES, use UnityAdsDeviceLog.entered() instead";
        }
        return str;
    }
}
