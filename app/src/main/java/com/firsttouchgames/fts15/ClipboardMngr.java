package com.firsttouchgames.fts15;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ClipboardMngr {
    private static ClipboardManager mClipboard;
    private static Context mContext;

    public static void onStart(Context context) {
        mContext = context;
        mClipboard = (ClipboardManager) mContext.getSystemService("clipboard");
    }

    public static String getPngData() {
        if (!mClipboard.hasPrimaryClip()) {
            return null;
        }
        ClipDescription clipdesc = mClipboard.getPrimaryClipDescription();
        mContext.getContentResolver();
        ClipData clip = mClipboard.getPrimaryClip();
        clip.getItemAt(0);
        String string = clipdesc.toString();
        return string;
    }

    public static String getText() {
        if (mClipboard.hasPrimaryClip()) {
            ClipDescription clipdesc = mClipboard.getPrimaryClipDescription();
            if (clipdesc.hasMimeType("text/plain")) {
                ClipData clipdata = mClipboard.getPrimaryClip();
                ClipData.Item item = clipdata.getItemAt(0);
                String text = item.coerceToText(mContext).toString();
                if (text != null) {
                    return text;
                }
            }
        }
        return null;
    }
}
