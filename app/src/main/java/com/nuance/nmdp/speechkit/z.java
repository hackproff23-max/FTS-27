package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ac;

/* JADX INFO: loaded from: classes.dex */
final class z extends s<GenericRecognition> implements TextRecognizer {
    private TextRecognizer.Listener b;
    private final PdxValue.Dictionary c;

    public z(x xVar, String str, String str2, PdxValue.Dictionary dictionary, TextRecognizer.Listener listener, Object obj) {
        super(xVar, RecognizerConstants.RecognizerType.Search, -1, str, str2, obj);
        synchronized (this.a) {
            this.b = listener;
        }
        this.c = dictionary;
        a();
    }

    @Override // com.nuance.nmdp.speechkit.s
    protected final p<GenericRecognition> a(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, ac acVar) {
        return new y(mVar, str2, str3, this.c, acVar) { // from class: com.nuance.nmdp.speechkit.z.1
            @Override // com.nuance.nmdp.speechkit.p
            protected final void a(final SpeechError speechError) {
                z.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.z.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (z.this.b != null) {
                            z.this.b.onError(z.this, speechError);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final /* synthetic */ void a(GenericRecognition genericRecognition) {
                final GenericRecognition genericRecognition2 = genericRecognition;
                z.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.z.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (z.this.b != null) {
                            z.this.b.onResults(z.this, genericRecognition2);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void b() {
                com.nuance.nmdp.speechkit.recognitionresult.b.a(z.this, "onRecordingRegin is called for TextRecognizer for nothing.");
            }

            @Override // com.nuance.nmdp.speechkit.p
            protected final void c() {
                com.nuance.nmdp.speechkit.recognitionresult.b.a(z.this, "onRecordingDone is called for TextRecognizer for nothing.");
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.TextRecognizer
    public final void setListener(TextRecognizer.Listener listener) {
        x.a(listener, "listener");
        synchronized (this.a) {
            this.b = listener;
        }
    }
}
