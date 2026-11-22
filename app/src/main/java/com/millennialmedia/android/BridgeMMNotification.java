package com.millennialmedia.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import com.facebook.share.internal.ShareConstants;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
class BridgeMMNotification extends MMJSObject implements DialogInterface.OnClickListener {
    private String ALERT = "alert";
    private String VIBRATE = "vibrate";
    private int index;

    BridgeMMNotification() {
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (this.ALERT.equals(name)) {
            MMJSResponse response = alert(arguments);
            return response;
        }
        if (!this.VIBRATE.equals(name)) {
            return null;
        }
        MMJSResponse response2 = vibrate(arguments);
        return response2;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0010 A[Catch: all -> 0x001b, TryCatch #0 {, blocks: (B:6:0x0005, B:9:0x000a, B:12:0x0010, B:13:0x0013), top: B:19:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x000a A[Catch: all -> 0x001b, TryCatch #0 {, blocks: (B:6:0x0005, B:9:0x000a, B:12:0x0010, B:13:0x0013), top: B:19:0x0005 }] */
    @Override // android.content.DialogInterface.OnClickListener
    public synchronized void onClick(DialogInterface dialog, int which) {
        if (which == -2) {
            this.index = 0;
            if (which == -3) {
                this.index = 1;
            }
            if (which == -1) {
                this.index = 2;
            }
            dialog.cancel();
            notify();
        } else {
            if (which == -3) {
                this.index = 1;
            }
            if (which == -1) {
                this.index = 2;
            }
            dialog.cancel();
            notify();
        }
        throw th;
    }

    public synchronized MMJSResponse alert(final Map<String, String> arguments) {
        return runOnUiThreadFuture(new Callable<MMJSResponse>() { // from class: com.millennialmedia.android.BridgeMMNotification.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MMJSResponse call() {
                MMWebView mmWebView = BridgeMMNotification.this.mmWebViewRef.get();
                if (mmWebView != null) {
                    Activity activity = mmWebView.getActivity();
                    Map<String, String> finalArguments = arguments;
                    if (activity != null) {
                        if (!activity.isFinishing()) {
                            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                            if (finalArguments.containsKey("title")) {
                                alertDialog.setTitle(finalArguments.get("title"));
                            }
                            if (finalArguments.containsKey(ShareConstants.WEB_DIALOG_PARAM_MESSAGE)) {
                                alertDialog.setMessage(finalArguments.get(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
                            }
                            if (finalArguments.containsKey("cancelButton")) {
                                alertDialog.setButton(-2, finalArguments.get("cancelButton"), BridgeMMNotification.this);
                            }
                            if (finalArguments.containsKey("buttons")) {
                                String[] buttons = finalArguments.get("buttons").split(",");
                                if (buttons.length > 0) {
                                    alertDialog.setButton(-3, buttons[0], BridgeMMNotification.this);
                                }
                                if (buttons.length > 1) {
                                    alertDialog.setButton(-1, buttons[1], BridgeMMNotification.this);
                                }
                            }
                            alertDialog.show();
                        }
                        MMJSResponse response = new MMJSResponse();
                        response.result = 1;
                        response.response = Integer.valueOf(BridgeMMNotification.this.index);
                        return response;
                    }
                }
                return null;
            }
        });
    }

    public MMJSResponse vibrate(Map<String, String> arguments) {
        Context context = this.contextRef.get();
        long time = 0;
        if (arguments.containsKey("duration")) {
            time = (long) (((double) Float.parseFloat(arguments.get("duration"))) * 1000.0d);
        }
        if (context != null && time > 0) {
            if (context.getPackageManager().checkPermission("android.permission.VIBRATE", context.getPackageName()) == 0) {
                Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
                vibrator.vibrate(time);
                return MMJSResponse.responseWithSuccess("Vibrating for " + time);
            }
            return MMJSResponse.responseWithError("The required permissions to vibrate are not set.");
        }
        return null;
    }
}
