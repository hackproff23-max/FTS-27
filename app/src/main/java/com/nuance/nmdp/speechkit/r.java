package com.nuance.nmdp.speechkit;

import defpackage.ac;

/* JADX INFO: loaded from: classes.dex */
final class r extends s<Recognition> implements Recognizer {
    private Recognizer.Listener b;

    public r(x xVar, String str, int i, String str2, Recognizer.Listener listener, Object obj) {
        super(xVar, str, i, str2, null, obj);
        synchronized (this.a) {
            this.b = listener;
        }
        a();
    }

    @Override // com.nuance.nmdp.speechkit.s
    protected final p<Recognition> a(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, ac acVar) {
        return new q(mVar, str, z, z2, str2, acVar) { // from class: com.nuance.nmdp.speechkit.r.1
            @Override // com.nuance.nmdp.speechkit.p
            protected final void a(final SpeechError speechError) {
                r.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.r.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (r.this.b != null) {
                            r.this.b.onError(r.this, speechError);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final /* synthetic */ void a(Recognition recognition) {
                final Recognition recognition2 = recognition;
                r.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.r.1.4
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (r.this.b != null) {
                            r.this.b.onResults(r.this, recognition2);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void b() {
                r.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.r.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (r.this.b != null) {
                            r.this.b.onRecordingBegin(r.this);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void c() {
                r.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.r.1.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (r.this.b != null) {
                            r.this.b.onRecordingDone(r.this);
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
