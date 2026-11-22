package com.nuance.nmdp.speechkit;

import defpackage.ax;
import defpackage.ba;

/* JADX INFO: loaded from: classes.dex */
public final class Prompt {
    private final ba a;
    private final x b;

    Prompt(ba baVar, x xVar) {
        this.a = baVar;
        this.b = xVar;
    }

    public static Prompt vibration(int i) {
        if (i <= 0) {
            return null;
        }
        return new Prompt(new defpackage.e(i), null);
    }

    final ba a() {
        return this.a;
    }

    public final void release() {
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.Prompt.1
            @Override // java.lang.Runnable
            public final void run() {
                if (Prompt.this.b != null) {
                    Prompt.this.b.a(Prompt.this);
                }
            }
        });
    }
}
