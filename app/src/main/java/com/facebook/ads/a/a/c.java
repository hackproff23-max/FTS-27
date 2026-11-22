package com.facebook.ads.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.a.ag;
import com.facebook.ads.a.h;
import com.facebook.ads.a.u;
import com.google.android.gms.drive.DriveFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    private static final String a = c.class.getSimpleName();
    private final Context b;
    private final Uri c;

    public c(Context context, Uri uri) {
        this.b = context;
        this.c = uri;
    }

    private Intent a(u uVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        if (!ag.a(uVar.a()) && !ag.a(uVar.b())) {
            intent.setComponent(new ComponentName(uVar.a(), uVar.b()));
        }
        if (!ag.a(uVar.c())) {
            intent.setData(Uri.parse(uVar.c()));
        }
        return intent;
    }

    private Intent b(u uVar) {
        if (!ag.a(uVar.a()) && h.a(this.b, uVar.a())) {
            String strC = uVar.c();
            if (!ag.a(strC) && (strC.startsWith("tel:") || strC.startsWith("telprompt:"))) {
                return new Intent("android.intent.action.CALL", Uri.parse(strC));
            }
            PackageManager packageManager = this.b.getPackageManager();
            if (ag.a(uVar.b()) && ag.a(strC)) {
                return packageManager.getLaunchIntentForPackage(uVar.a());
            }
            Intent intentA = a(uVar);
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intentA, 65536);
            if (intentA.getComponent() == null) {
                for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                    if (resolveInfo.activityInfo.packageName.equals(uVar.a())) {
                        intentA.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                        break;
                    }
                }
            }
            if (listQueryIntentActivities.isEmpty() || intentA.getComponent() == null) {
                return null;
            }
            return intentA;
        }
        return null;
    }

    private List e() {
        String queryParameter = this.c.getQueryParameter("appsite_data");
        if (ag.a(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (jSONArrayOptJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                u uVarA = u.a(jSONArrayOptJSONArray.optJSONObject(i));
                if (uVarA != null) {
                    arrayList.add(uVarA);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            Log.w(a, "Error parsing appsite_data", e);
            return arrayList;
        }
    }

    @Override // com.facebook.ads.a.a.a
    public com.facebook.ads.a.b.a a() {
        return com.facebook.ads.a.b.a.OPEN_STORE;
    }

    @Override // com.facebook.ads.a.a.a
    public void a(Map map) {
        a(this.b, this.c);
        List listC = c();
        if (listC != null) {
            Iterator it = listC.iterator();
            while (it.hasNext()) {
                try {
                    this.b.startActivity((Intent) it.next());
                    return;
                } catch (Exception e) {
                    Log.d(a, "Failed to open app intent, falling back", e);
                }
            }
        }
        d();
    }

    protected Uri b() {
        String queryParameter = this.c.getQueryParameter("store_url");
        return !ag.a(queryParameter) ? Uri.parse(queryParameter) : Uri.parse(String.format("market://details?id=%s", this.c.getQueryParameter("store_id")));
    }

    protected List c() {
        List listE = e();
        ArrayList arrayList = new ArrayList();
        if (listE != null) {
            Iterator it = listE.iterator();
            while (it.hasNext()) {
                Intent intentB = b((u) it.next());
                if (intentB != null) {
                    arrayList.add(intentB);
                }
            }
        }
        return arrayList;
    }

    public void d() {
        Intent intent = new Intent("android.intent.action.VIEW", b());
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        try {
            this.b.startActivity(intent);
        } catch (Exception e) {
            Log.d(a, "Failed to open market url: " + this.c.toString(), e);
            String queryParameter = this.c.getQueryParameter("store_url_web_fallback");
            if (queryParameter == null || queryParameter.length() <= 0) {
                return;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(queryParameter));
            intent2.addFlags(DriveFile.MODE_READ_ONLY);
            try {
                this.b.startActivity(intent2);
            } catch (Exception e2) {
                Log.d(a, "Failed to open fallback url: " + queryParameter, e2);
            }
        }
    }
}
