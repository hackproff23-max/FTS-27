package com.nuance.nmdp.speechkit;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class u extends b<GenericResult> implements GenericCommand {
    private GenericCommand.Listener b;
    private SpeechError c;

    public u(x xVar, GenericCommand.Listener listener, Object obj) {
        super(xVar, xVar.m(), obj);
        this.c = null;
        synchronized (this.a) {
            this.b = listener;
        }
        a();
    }

    @Override // com.nuance.nmdp.speechkit.b
    protected final a<GenericResult> a(defpackage.m mVar, String str, List<defpackage.q> list) {
        return new t(mVar, str) { // from class: com.nuance.nmdp.speechkit.u.1
            @Override // com.nuance.nmdp.speechkit.a
            protected final void a(SpeechError speechError) {
                u.this.c = speechError;
            }

            @Override // com.nuance.nmdp.speechkit.a
            protected final /* synthetic */ void a(GenericResult genericResult) {
                final GenericResult genericResult2 = genericResult;
                u.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.u.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (u.this.b != null) {
                            u.this.b.onComplete(u.this, genericResult2, u.this.c);
                        }
                    }
                });
            }
        };
    }
}
