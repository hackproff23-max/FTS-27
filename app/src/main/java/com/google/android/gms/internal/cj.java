package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@ey
public final class cj implements cd {
    private static int a(DisplayMetrics displayMetrics, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            return gq.a(displayMetrics, Integer.parseInt(str2));
        } catch (NumberFormatException e) {
            gr.W("Could not parse " + str + " in a video GMSG: " + str2);
            return i;
        }
    }

    @Override // com.google.android.gms.internal.cd
    public void a(gu guVar, Map<String, String> map) {
        String str = map.get("action");
        if (str == null) {
            gr.W("Action missing from video GMSG.");
            return;
        }
        dp dpVarDC = guVar.dC();
        if (dpVarDC == null) {
            gr.W("Could not get ad overlay for a video GMSG.");
            return;
        }
        boolean zEqualsIgnoreCase = "new".equalsIgnoreCase(str);
        boolean zEqualsIgnoreCase2 = "position".equalsIgnoreCase(str);
        if (zEqualsIgnoreCase || zEqualsIgnoreCase2) {
            DisplayMetrics displayMetrics = guVar.getContext().getResources().getDisplayMetrics();
            int iA = a(displayMetrics, map, "x", 0);
            int iA2 = a(displayMetrics, map, "y", 0);
            int iA3 = a(displayMetrics, map, "w", -1);
            int iA4 = a(displayMetrics, map, "h", -1);
            if (zEqualsIgnoreCase && dpVarDC.ce() == null) {
                dpVarDC.c(iA, iA2, iA3, iA4);
                return;
            } else {
                dpVarDC.b(iA, iA2, iA3, iA4);
                return;
            }
        }
        dt dtVarCe = dpVarDC.ce();
        if (dtVarCe == null) {
            dt.a(guVar, "no_video_view", (String) null);
            return;
        }
        if ("click".equalsIgnoreCase(str)) {
            DisplayMetrics displayMetrics2 = guVar.getContext().getResources().getDisplayMetrics();
            int iA5 = a(displayMetrics2, map, "x", 0);
            int iA6 = a(displayMetrics2, map, "y", 0);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, iA5, iA6, 0);
            dtVarCe.b(motionEventObtain);
            motionEventObtain.recycle();
            return;
        }
        if ("controls".equalsIgnoreCase(str)) {
            String str2 = map.get("enabled");
            if (str2 == null) {
                gr.W("Enabled parameter missing from controls video GMSG.");
                return;
            } else {
                dtVarCe.s(Boolean.parseBoolean(str2));
                return;
            }
        }
        if ("currentTime".equalsIgnoreCase(str)) {
            String str3 = map.get("time");
            if (str3 == null) {
                gr.W("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                dtVarCe.seekTo((int) (Float.parseFloat(str3) * 1000.0f));
                return;
            } catch (NumberFormatException e) {
                gr.W("Could not parse time parameter from currentTime video GMSG: " + str3);
                return;
            }
        }
        if ("hide".equalsIgnoreCase(str)) {
            dtVarCe.setVisibility(4);
            return;
        }
        if ("load".equalsIgnoreCase(str)) {
            dtVarCe.cq();
            return;
        }
        if ("pause".equalsIgnoreCase(str)) {
            dtVarCe.pause();
            return;
        }
        if ("play".equalsIgnoreCase(str)) {
            dtVarCe.play();
            return;
        }
        if ("show".equalsIgnoreCase(str)) {
            dtVarCe.setVisibility(0);
        } else if ("src".equalsIgnoreCase(str)) {
            dtVarCe.C(map.get("src"));
        } else {
            gr.W("Unknown video action: " + str);
        }
    }
}
