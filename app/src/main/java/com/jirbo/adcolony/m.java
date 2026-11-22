package com.jirbo.adcolony;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import com.facebook.ads.InterstitialAd;
import com.facebook.share.internal.ShareConstants;
import com.millennialmedia.android.MMSDK;
import com.mopub.common.Constants;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
class m {
    WebView a;
    Activity b;
    ADCVideo c;
    Handler d = new Handler();
    Runnable e = new Runnable() { // from class: com.jirbo.adcolony.m.1
        @Override // java.lang.Runnable
        public void run() {
            a.A = false;
        }
    };
    AdColonyAd f;
    String g;

    public m(ADCVideo aDCVideo, WebView webView, Activity activity) {
        this.a = webView;
        this.b = activity;
        this.c = aDCVideo;
    }

    void a(String str) {
        String str2;
        String[] strArr;
        String strReplace = str.replace("mraid://", "");
        if (strReplace.contains("?")) {
            String[] strArrSplit = strReplace.split("\\?");
            str2 = strArrSplit[0];
            strArr = strArrSplit;
        } else {
            str2 = strReplace;
            strArr = null;
        }
        String[] strArrSplit2 = strArr != null ? strArr[1].split("&") : new String[0];
        HashMap map = new HashMap();
        for (String str3 : strArrSplit2) {
            map.put(str3.split("=")[0], str3.split("=")[1]);
        }
        this.f = a.J;
        this.g = "{\"ad_slot\":" + this.f.h.k.d + "}";
        if (str2.equals("send_adc_event")) {
            b((String) map.get(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY));
        } else if (str2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE)) {
            b();
        } else if (str2.equals("open_store") && !a.A) {
            c((String) map.get(UnityAdsConstants.UNITY_ADS_REWARD_ITEM_KEY));
        } else if (str2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN) && !a.A) {
            d((String) map.get("url"));
        } else if (str2.equals("expand")) {
            e((String) map.get("url"));
        } else if (str2.equals("create_calendar_event") && !a.A) {
            c(map);
        } else if (str2.equals("mail") && !a.A) {
            d(map);
        } else if (str2.equals(MMSDK.Event.INTENT_TXT_MESSAGE) && !a.A) {
            e(map);
        } else if (str2.equals(MMSDK.Event.INTENT_PHONE_CALL) && !a.A) {
            f(map);
        } else if (str2.equals("custom_event")) {
            g(map);
        } else if (str2.equals("launch_app") && !a.A) {
            h(map);
        } else if (str2.equals("check_app_presence")) {
            i(map);
        } else if (str2.equals("auto_play")) {
            j(map);
        } else if (str2.equals("save_screenshot")) {
            a();
        } else if (str2.equals("social_post") && !a.A) {
            b(map);
        } else if (str2.equals("make_in_app_purchase") && !a.A) {
            a(map);
        }
        f("adc_bridge.nativeCallComplete()");
    }

    void a(HashMap map) {
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        a.a("html5_interaction", this.g, this.c.G);
        String strG = g((String) map.get("product"));
        Integer.parseInt(g((String) map.get("quantity")));
        this.b.finish();
        this.c.G.m = strG;
        this.c.G.u = AdColonyIAPEngagement.END_CARD;
        a.M.a(this.c.G);
    }

    void b(HashMap map) {
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g((String) map.get("text"));
        String strG2 = g((String) map.get("url"));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", strG + " " + strG2);
        this.b.startActivity(Intent.createChooser(intent, "Share this post using..."));
    }

    void a() {
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        a.a("html5_interaction", this.g, this.c.G);
        String str = Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots/AdColony_Screenshot_" + System.currentTimeMillis() + ".jpg";
        View rootView = this.a.getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures");
        File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots");
        try {
            file.mkdir();
            file2.mkdir();
        } catch (Exception e) {
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            MediaScannerConnection.scanFile(this.b, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.jirbo.adcolony.m.2
                @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                public void onScanCompleted(String path, Uri uri) {
                    Toast.makeText(m.this.b, "Screenshot saved to Gallery!", 0).show();
                }
            });
        } catch (FileNotFoundException e2) {
            Toast.makeText(this.b, "Error saving screenshot.", 0).show();
            l.a.a("ADC [info] FileNotFoundException in MRAIDCommandTakeScreenshot");
        } catch (IOException e3) {
            Toast.makeText(this.b, "Error saving screenshot.", 0).show();
            l.a.a("ADC [info] IOException in MRAIDCommandTakeScreenshot");
        }
    }

    void b(String str) {
        l.a.a("ADC [info] MRAIDCommandSendADCEvent called with type: ").b((Object) str);
        a.a(str, this.c.G);
    }

    void b() {
        l.a.b((Object) "ADC [info] MRAIDCommandClose called");
        this.b.finish();
        a.M.a(this.c.G);
    }

    void c(String str) {
        l.a.a("ADC [info] MRAIDCommandOpenStore called with item: ").b((Object) str);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        a.a("html5_interaction", this.g, this.c.G);
        try {
            this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(g(str))));
        } catch (Exception e) {
            Toast.makeText(this.b, "Unable to open store.", 0).show();
        }
    }

    void d(String str) {
        l.a.a("ADC [info] MRAIDCommandOpen called with url: ").b((Object) str);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g(str);
        if (strG.startsWith("adcvideo")) {
            this.c.a(strG.replace("adcvideo", Constants.HTTP));
            return;
        }
        if (str.contains("youtube")) {
            try {
                this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("vnd.youtube:" + strG.substring(strG.indexOf(118) + 2))));
                return;
            } catch (Exception e) {
                String strG2 = g(str);
                if (strG2.contains("safari")) {
                    strG2 = strG2.replace("safari", Constants.HTTP);
                }
                this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(strG2)));
                return;
            }
        }
        if (strG.startsWith(MMSDK.Event.INTENT_EXTERNAL_BROWSER)) {
            a.a("html5_interaction", this.c.G);
            this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(strG.replace(MMSDK.Event.INTENT_EXTERNAL_BROWSER, Constants.HTTP))));
        } else {
            a.a("html5_interaction", this.g, this.c.G);
            AdColonyBrowser.url = strG;
            this.b.startActivity(new Intent(this.b, (Class<?>) AdColonyBrowser.class));
        }
    }

    void e(String str) {
        l.a.a("ADC [info] MRAIDCommandExpand called with url: ").b((Object) str);
        f("adc_bridge.fireChangeEvent({state:'expanded'});");
    }

    void c(HashMap map) {
        Date date;
        Date date2;
        Date date3;
        Date date4;
        long j;
        Intent intentPutExtra;
        l.a.a("ADC [info] MRAIDCommandCreateCalendarEvent called with parameters: ").b(map);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        a.a("html5_interaction", this.g, this.c.G);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        String strG = g((String) map.get("description"));
        g((String) map.get("location"));
        String strG2 = g((String) map.get("start"));
        String strG3 = g((String) map.get("end"));
        String strG4 = g((String) map.get("summary"));
        String strG5 = g((String) map.get("recurrence"));
        String strG6 = "";
        String upperCase = "";
        HashMap map2 = new HashMap();
        String strReplace = strG5.replace("\"", "").replace("{", "").replace("}", "");
        if (!strReplace.equals("")) {
            for (String str : strReplace.split(",")) {
                map2.put(str.substring(0, str.indexOf(InterstitialAd.SEPARATOR)), str.substring(str.indexOf(InterstitialAd.SEPARATOR) + 1, str.length()));
            }
            strG6 = g((String) map2.get("expires"));
            upperCase = g((String) map2.get("frequency")).toUpperCase();
            l.a.a("Calendar Recurrence - ").b((Object) strReplace);
            l.a.a("Calendar Recurrence - frequency = ").b((Object) upperCase);
            l.a.a("Calendar Recurrence - expires =  ").b((Object) strG6);
        }
        String str2 = strG6;
        String str3 = upperCase;
        if (strG4.equals("")) {
            strG4 = g((String) map.get("description"));
        }
        try {
            date = simpleDateFormat.parse(strG2);
            try {
                date2 = simpleDateFormat.parse(strG3);
            } catch (Exception e) {
                date2 = null;
            }
        } catch (Exception e2) {
            date = null;
        }
        if (date == null) {
            try {
                date = simpleDateFormat2.parse(strG2);
                date2 = simpleDateFormat2.parse(strG3);
                date3 = date;
            } catch (Exception e3) {
                date3 = date;
            }
        } else {
            date3 = date;
        }
        try {
            date4 = simpleDateFormat.parse(str2);
        } catch (Exception e4) {
            date4 = null;
        }
        if (date4 == null) {
            try {
                date4 = simpleDateFormat3.parse(str2);
            } catch (Exception e5) {
            }
        }
        if (date3 == null) {
            Toast.makeText(this.b, "Unable to create Calendar Event.", 0).show();
            return;
        }
        long time = date3.getTime();
        long time2 = date2.getTime();
        long time3 = 0;
        if (date4 != null) {
            time3 = (date4.getTime() - date3.getTime()) / 1000;
        }
        if (str3.equals("DAILY")) {
            j = (time3 / 86400) + 1;
        } else if (str3.equals("WEEKLY")) {
            j = (time3 / 604800) + 1;
        } else if (str3.equals("MONTHLY")) {
            j = (time3 / 2629800) + 1;
        } else if (!str3.equals("YEARLY")) {
            j = 0;
        } else {
            j = (time3 / 31557600) + 1;
        }
        if (!strReplace.equals("")) {
            intentPutExtra = new Intent("android.intent.action.EDIT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE).putExtra("title", strG4).putExtra("description", strG).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", "FREQ=" + str3 + ";COUNT=" + j);
            l.a.a("Calendar Recurrence - count = ").b(j);
        } else {
            intentPutExtra = new Intent("android.intent.action.EDIT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE).putExtra("title", strG4).putExtra("description", strG).putExtra("beginTime", time).putExtra("endTime", time2);
        }
        try {
            this.b.startActivity(intentPutExtra);
        } catch (Exception e6) {
            Toast.makeText(this.b, "Unable to create Calendar Event.", 0).show();
        }
    }

    void d(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandMail called with parameters: ").b(map);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g((String) map.get("subject"));
        String strG2 = g((String) map.get(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY));
        String strG3 = g((String) map.get(ShareConstants.WEB_DIALOG_PARAM_TO));
        a.a("html5_interaction", this.g, this.c.G);
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("plain/text");
            intent.putExtra("android.intent.extra.SUBJECT", strG).putExtra("android.intent.extra.TEXT", strG2).putExtra("android.intent.extra.EMAIL", new String[]{strG3});
            this.b.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.b, "Unable to launch email client.", 0).show();
        }
    }

    void e(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandSMS called with parameters: ").b(map);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g((String) map.get(ShareConstants.WEB_DIALOG_PARAM_TO));
        String strG2 = g((String) map.get(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY));
        a.a("html5_interaction", this.g, this.c.G);
        try {
            this.b.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("sms:" + strG)).putExtra("sms_body", strG2));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this.b, "Failed to create sms.", 0).show();
        }
    }

    void f(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandTel called with parameters: ").b(map);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g((String) map.get("number"));
        a.a("html5_interaction", this.g, this.c.G);
        try {
            this.b.startActivity(new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + strG)));
        } catch (Exception e) {
            Toast.makeText(this.b, "Failed to dial number.", 0).show();
        }
    }

    void g(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandSendCustomADCEvent called with parameters: ").b(map);
        a.a("custom_event", "{\"event_type\":\"" + g((String) map.get("event_type")) + "\",\"ad_slot\":" + this.f.h.k.d + "}", this.c.G);
    }

    void h(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandLaunchApp called with parameters: ").b(map);
        a.A = true;
        this.d.postDelayed(this.e, 1000L);
        String strG = g((String) map.get("handle"));
        a.a("html5_interaction", this.g, this.c.G);
        try {
            this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage(strG));
        } catch (Exception e) {
            Toast.makeText(this.b, "Failed to launch external application.", 0).show();
        }
    }

    void i(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandCheckAppPresence called with parameters: ").b(map);
        String strG = g((String) map.get("handle"));
        f("adc_bridge.fireAppPresenceEvent('" + strG + "'," + ab.a(strG) + ")");
    }

    void j(HashMap map) {
        l.a.a("ADC [info] MRAIDCommandCheckAutoPlay called with parameters: ").b(map);
    }

    void f(String str) {
        this.a.loadUrl("javascript:" + str);
    }

    String g(String str) {
        return str == null ? "" : URLDecoder.decode(str);
    }
}
