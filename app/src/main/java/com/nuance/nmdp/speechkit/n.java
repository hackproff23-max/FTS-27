package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;

/* JADX INFO: loaded from: classes.dex */
final class n extends s<GenericRecognition> implements NluRecognizer {
    private NluRecognizer.Listener b;
    private final PdxValue.Dictionary c;

    public n(x xVar, String str, int i, String str2, String str3, PdxValue.Dictionary dictionary, NluRecognizer.Listener listener, Object obj) {
        super(xVar, str, i, str2, str3, obj);
        synchronized (this.a) {
            this.b = listener;
        }
        this.c = dictionary;
        a();
    }

    @Override // com.nuance.nmdp.speechkit.s
    protected final p<GenericRecognition> a(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, ac acVar) {
        return new m(mVar, str, z, z2, str2, str3, this.c, acVar) { // from class: com.nuance.nmdp.speechkit.n.1
            @Override // com.nuance.nmdp.speechkit.p
            protected final void a(final SpeechError speechError) {
                n.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.n.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (n.this.b != null) {
                            n.this.b.onError(n.this, speechError);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final /* synthetic */ void a(GenericRecognition genericRecognition) {
                final GenericRecognition genericRecognition2 = genericRecognition;
                n.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.n.1.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (n.this.b != null) {
                            n.this.b.onResults(n.this, genericRecognition2);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void b() {
                n.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.n.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (n.this.b != null) {
                            n.this.b.onRecordingBegin(n.this);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void c() {
                n.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.n.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (n.this.b != null) {
                            n.this.b.onRecordingDone(n.this);
                        }
                    }
                });
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.NluRecognizer
    public final void setListener(NluRecognizer.Listener listener) {
        x.a(listener, "listener");
        synchronized (this.a) {
            this.b = listener;
        }
    }
}
