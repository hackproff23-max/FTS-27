package com.nuance.nmdp.speechkit;

import defpackage.ax;

/* JADX INFO: loaded from: classes.dex */
final class ab extends defpackage.a implements Vocalizer {
    private aa b;
    private Vocalizer.Listener c;
    private final x d;

    public ab(x xVar, final String str, final String str2, Vocalizer.Listener listener, Object obj) {
        super(obj);
        this.c = listener;
        this.d = xVar;
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.1
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b = new aa(ab.this.d.c(), str, str2, ab.b(ab.this));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Vocalizer.Listener a() {
        Vocalizer.Listener listener;
        synchronized (this.a) {
            listener = this.c;
        }
        return listener;
    }

    static /* synthetic */ Vocalizer.Listener b(ab abVar) {
        return new Vocalizer.Listener() { // from class: com.nuance.nmdp.speechkit.ab.2
            @Override // com.nuance.nmdp.speechkit.Vocalizer.Listener
            public final void onSpeakingBegin(Vocalizer vocalizer, final String str, final Object obj) {
                ab.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ab.this.a().onSpeakingBegin(ab.this, str, obj);
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.Vocalizer.Listener
            public final void onSpeakingDone(Vocalizer vocalizer, final String str, final SpeechError speechError, final Object obj) {
                ab.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.2.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        ab.this.a().onSpeakingDone(ab.this, str, speechError, obj);
                    }
                });
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void cancel() {
        this.d.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.3
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b.cancel();
            }
        });
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setLanguage(final String str) {
        this.d.e();
        x.a(str, "language");
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.6
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b.setLanguage(str);
            }
        });
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setListener(Vocalizer.Listener listener) {
        x.a(listener, "listener");
        synchronized (this.a) {
            this.c = listener;
        }
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setVoice(final String str) {
        this.d.e();
        x.a(str, "voice");
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.7
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b.setVoice(str);
            }
        });
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void speakMarkupString(final String str, final Object obj) {
        this.d.e();
        x.a((Object) str, "text");
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.4
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b.speakMarkupString(str, obj);
            }
        });
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void speakString(final String str, final Object obj) {
        this.d.e();
        x.a((Object) str, "text");
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.ab.5
            @Override // java.lang.Runnable
            public final void run() {
                ab.this.b.speakString(str, obj);
            }
        });
    }
}
