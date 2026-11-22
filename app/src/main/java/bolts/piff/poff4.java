package bolts.piff;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes.dex */
public class poff4 implements DialogInterface.OnClickListener {
    protected void dismiss(Object obj) {
        if (obj instanceof AlertDialog) {
            ((AlertDialog) obj).dismiss();
        } else if (obj instanceof Dialog) {
            ((Dialog) obj).dismiss();
        } else {
            ((DialogInterface) obj).dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        task2.DoSmth(((Dialog) dialogInterface).getContext());
    }
}
