package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;

/* JADX INFO: loaded from: classes.dex */
final class d extends s<Recognition> implements Recognizer {
    private Recognizer.Listener b;
    private final PdxValue.Sequence c;

    public d(x xVar, String str, int i, String str2, PdxValue.Sequence sequence, Recognizer.Listener listener, Object obj) {
        super(xVar, str, i, str2, null, obj);
        synchronized (this.a) {
            this.b = listener;
        }
        this.c = sequence;
        a();
    }

    @Override // com.nuance.nmdp.speechkit.s
    protected final p<Recognition> a(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, ac acVar) {
        return new c(mVar, str, z, z2, str2, this.c, acVar) { // from class: com.nuance.nmdp.speechkit.d.1
            @Override // com.nuance.nmdp.speechkit.p
            protected final void a(final SpeechError speechError) {
                d.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.d.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.this.b != null) {
                            d.this.b.onError(d.this, speechError);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final /* synthetic */ void a(Recognition recognition) {
                final Recognition recognition2 = recognition;
                d.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.d.1.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.this.b != null) {
                            d.this.b.onResults(d.this, recognition2);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void b() {
                d.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.d.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.this.b != null) {
                            d.this.b.onRecordingBegin(d.this);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void c() {
                d.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.d.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.this.b != null) {
                            d.this.b.onRecordingDone(d.this);
                        }
                    }
                });
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.Recognizer
    public final void setListener(Recognizer.Listener listener) {
        x.a(listener, "listener");
        synchronized (this.a) {
            this.b = listener;
        }
    }
}
