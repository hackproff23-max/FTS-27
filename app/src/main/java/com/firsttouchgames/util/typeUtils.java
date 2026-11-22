package com.firsttouchgames.util;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class typeUtils {
    public static String LOG_TAG = "Util";

    public static Map<String, String> GetMap(String s0, String s1) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(s0, s1);
        return parameters;
    }
}
