package com.nuance.nmdp.speechkit;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class l extends b<GenericResult> implements GenericCommand {
    private GenericCommand.Listener b;
    private String c;
    private SpeechError d;

    l(x xVar, String str, GenericCommand.Listener listener, Object obj) {
        super(xVar, xVar.l(), obj);
        this.d = null;
        this.c = str;
        synchronized (this.a) {
            this.b = listener;
        }
        a();
    }

    @Override // com.nuance.nmdp.speechkit.b
    protected final a<GenericResult> a(defpackage.m mVar, String str, List<defpackage.q> list) {
        return new k(mVar, str, this.c, list) { // from class: com.nuance.nmdp.speechkit.l.1
            @Override // com.nuance.nmdp.speechkit.a
            protected final void a(SpeechError speechError) {
                l.this.d = speechError;
            }

            @Override // com.nuance.nmdp.speechkit.a
            protected final /* synthetic */ void a(GenericResult genericResult) {
                final GenericResult genericResult2 = genericResult;
                l.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.l.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (l.this.b != null) {
                            l.this.b.onComplete(l.this, genericResult2, l.this.d);
                        }
                    }
                });
            }
        };
    }
}
