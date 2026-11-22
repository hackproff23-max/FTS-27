package com.nuance.nmdp.speechkit;

import defpackage.aq;
import defpackage.ay;

/* JADX INFO: loaded from: classes.dex */
final class aa implements Vocalizer {
    private String a;
    private String b;
    private final Vocalizer.Listener c;
    private final defpackage.m d;
    private final aq e = new aq() { // from class: com.nuance.nmdp.speechkit.aa.1
        @Override // defpackage.h
        public final void a(defpackage.g gVar) {
            if (aa.this.h != null) {
                if (aa.this.f == gVar) {
                    aa.this.a(aa.this.g.b, aa.this.g.e, aa.this.h);
                    aa.d(aa.this);
                    aa.e(aa.this);
                    aa.this.h = null;
                    return;
                }
                return;
            }
            if (aa.this.f == gVar) {
                aa.this.c.onSpeakingDone(aa.this, aa.this.g.b, null, aa.this.g.e);
                if (aa.this.i.c() > 0) {
                    aa.this.a((a) aa.this.i.a());
                } else {
                    aa.d(aa.this);
                    aa.e(aa.this);
                }
            }
        }

        @Override // defpackage.h
        public final void a(defpackage.g gVar, int i, String str, String str2) {
            if (aa.this.f == gVar) {
                aa.this.h = new v(i, str, str2);
            }
        }

        @Override // defpackage.aq
        public final void b(defpackage.g gVar) {
            if (aa.this.f == gVar) {
                aa.this.c.onSpeakingBegin(aa.this, aa.this.g.b, aa.this.g.e);
            }
        }
    };
    private defpackage.g f = null;
    private a g = null;
    private SpeechError h = null;
    private final ay i = new ay();

    class a {
        private final boolean a;
        private final String b;
        private final String c;
        private final String d;
        private final Object e;

        public a(boolean z, String str, String str2, String str3, Object obj) {
            this.a = z;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = obj;
        }
    }

    aa(defpackage.m mVar, String str, String str2, Vocalizer.Listener listener) {
        this.a = str;
        this.b = str2;
        this.c = listener;
        this.d = mVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) {
        if (!this.d.b()) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create TTS transaction. Transaction runner is invalid.");
            a(aVar.b, aVar.e, new v(0, null, null));
            return;
        }
        this.h = null;
        this.f = this.d.a(aVar.b, aVar.c, aVar.d, aVar.a, this.e);
        if (this.f == null) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Unable to create TTS transaction");
            a(aVar.b, aVar.e, new v(0, null, null));
        } else {
            this.g = aVar;
            this.f.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Object obj, SpeechError speechError) {
        this.c.onSpeakingDone(this, str, speechError, obj);
        int iC = this.i.c();
        for (int i = 0; i < iC; i++) {
            a aVar = (a) this.i.a(i);
            this.c.onSpeakingDone(this, aVar.b, speechError, aVar.e);
        }
        this.i.b();
    }

    static /* synthetic */ defpackage.g d(aa aaVar) {
        aaVar.f = null;
        return null;
    }

    static /* synthetic */ a e(aa aaVar) {
        aaVar.g = null;
        return null;
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void cancel() {
        if (this.f != null) {
            this.f.b();
        }
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setLanguage(String str) {
        this.b = str;
        this.a = null;
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setListener(Vocalizer.Listener listener) {
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void setVoice(String str) {
        this.b = null;
        this.a = str;
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void speakMarkupString(String str, Object obj) {
        a aVar = new a(true, str, this.a, this.b, obj);
        if (this.f == null) {
            a(aVar);
        } else {
            this.i.a(aVar);
        }
    }

    @Override // com.nuance.nmdp.speechkit.Vocalizer
    public final void speakString(String str, Object obj) {
        a aVar = new a(false, str, this.a, this.b, obj);
        if (this.f == null) {
            a(aVar);
        } else {
            this.i.a(aVar);
        }
    }
}
