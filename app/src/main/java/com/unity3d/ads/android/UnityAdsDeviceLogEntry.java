package com.unity3d.ads.android;

/* JADX INFO: loaded from: classes.dex */
public class UnityAdsDeviceLogEntry {
    private UnityAdsDeviceLogLevel a;
    private String b;
    private StackTraceElement c;

    public UnityAdsDeviceLogEntry(UnityAdsDeviceLogLevel unityAdsDeviceLogLevel, String str, StackTraceElement stackTraceElement) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = unityAdsDeviceLogLevel;
        this.b = str;
        this.c = stackTraceElement;
    }

    public UnityAdsDeviceLogLevel getLogLevel() {
        return this.a;
    }

    public String getOriginalMessage() {
        return this.b;
    }

    public String getParsedMessage() {
        String str = this.b;
        String className = "UnknownClass";
        String methodName = "unknownMethod";
        int lineNumber = -1;
        if (this.c != null) {
            className = this.c.getClassName();
            methodName = this.c.getMethodName();
            lineNumber = this.c.getLineNumber();
        }
        if (str != null && str.length() > 0) {
            str = " :: " + str;
        }
        if (str == null) {
            str = "";
        }
        return className + "." + methodName + "()" + (" (line:" + lineNumber + ")") + str;
    }
}
