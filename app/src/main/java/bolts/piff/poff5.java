package bolts.piff;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class poff5 implements DialogInterface.OnClickListener {
    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ((Dialog) dialogInterface).getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://youtube.com/@giiodroid?si=75PcCStha0hEfk_9")));
        Log.i("Dialog", "Redirected");
        Logger.getGlobal().log(Level.INFO, "Dialog Channel Link Clicked");
    }
}
