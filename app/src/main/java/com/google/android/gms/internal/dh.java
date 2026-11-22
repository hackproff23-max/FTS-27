package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.drive.DriveFile;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
@ey
public class dh {
    private final Context mContext;
    private final gu mo;
    private final Map<String, String> rd;
    private String re;
    private long rf;
    private long rg;
    private String rh;
    private String ri;

    public dh(gu guVar, Map<String, String> map) {
        this.mo = guVar;
        this.rd = map;
        this.mContext = guVar.dI();
        bQ();
    }

    private String A(String str) {
        return TextUtils.isEmpty(this.rd.get(str)) ? "" : this.rd.get(str);
    }

    private void bQ() {
        this.re = A("description");
        this.rh = A("summary");
        this.rf = gi.O(this.rd.get("start"));
        this.rg = gi.O(this.rd.get("end"));
        this.ri = A("location");
    }

    Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.rh);
        data.putExtra("eventLocation", this.ri);
        data.putExtra("description", this.re);
        data.putExtra("beginTime", this.rf);
        data.putExtra("endTime", this.rg);
        data.setFlags(DriveFile.MODE_READ_ONLY);
        return data;
    }

    public void execute() {
        if (!new bl(this.mContext).bt()) {
            gr.W("This feature is not available on this version of the device.");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setTitle(ga.c(R.string.create_calendar_title, "Create calendar event"));
        builder.setMessage(ga.c(R.string.create_calendar_message, "Allow Ad to create a calendar event?"));
        builder.setPositiveButton(ga.c(R.string.accept, "Accept"), new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.dh.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dh.this.mContext.startActivity(dh.this.createIntent());
            }
        });
        builder.setNegativeButton(ga.c(R.string.decline, "Decline"), new DialogInterface.OnClickListener() { // from class: com.google.android.gms.internal.dh.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                dh.this.mo.b("onCalendarEventCanceled", new JSONObject());
            }
        });
        builder.create().show();
    }
}
