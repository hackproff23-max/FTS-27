package defpackage;

import android.content.Context;
import android.media.AudioManager;

/* JADX INFO: loaded from: classes.dex */
final class eu extends eo {
    private AudioManager c;

    public eu(Context context) {
        super(context);
        this.c = (AudioManager) this.a.getSystemService("audio");
    }

    @Override // defpackage.eo
    public final void d() {
        if (em.c) {
            return;
        }
        this.c.startBluetoothSco();
    }

    @Override // defpackage.eo
    public final void e() {
        if (em.c) {
            return;
        }
        this.c.stopBluetoothSco();
    }

    @Override // defpackage.eo
    public final int f() {
        return b() ? 0 : 3;
    }

    @Override // defpackage.eo
    public final int g() {
        if (b()) {
            return en.a;
        }
        return 6;
    }
}
