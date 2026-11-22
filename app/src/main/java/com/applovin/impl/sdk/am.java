package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

/* JADX INFO: loaded from: classes.dex */
class am implements Runnable {
    final /* synthetic */ al a;

    am(al alVar) {
        this.a = alVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str = (String) this.a.a.a(bb.ab);
        String strB = this.a.b();
        String str2 = (String) this.a.a.a(bb.ag);
        if (!m.a(AppLovinConfirmationActivity.class, this.a.c)) {
            this.a.a(strB, null);
            return;
        }
        try {
            Intent intent = new Intent(this.a.c, (Class<?>) AppLovinConfirmationActivity.class);
            intent.putExtra("dialog_title", str);
            intent.putExtra("dialog_body", strB);
            intent.putExtra("dialog_button_text", str2);
            this.a.c.startActivity(intent);
        } catch (Throwable th) {
            this.a.a(strB, th);
        }
    }
}
