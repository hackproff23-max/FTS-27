package com.nuance.nmdp.speechkit;

import defpackage.ac;
import defpackage.ax;

/* JADX INFO: loaded from: classes.dex */
abstract class s<ResultType> extends defpackage.a implements j {
    private p<ResultType> b;
    private final x c;
    private float d;
    private Runnable e;
    private String f;
    private final Object g;

    public s(x xVar, final String str, final int i, final String str2, String str3, Object obj) {
        super(obj);
        this.c = xVar;
        this.d = 0.0f;
        this.g = new Object();
        this.f = str3;
        this.e = new Runnable() { // from class: com.nuance.nmdp.speechkit.s.1
            @Override // java.lang.Runnable
            public final void run() {
                s sVar = s.this;
                s sVar2 = s.this;
                defpackage.m mVarC = s.this.c.c();
                String str4 = str;
                boolean z = i == 1;
                boolean z2 = i == 2;
                String str5 = str2;
                String str6 = s.this.f;
                final s sVar3 = s.this;
                sVar.b = sVar2.a(mVarC, str4, z, z2, str5, str6, new ac() { // from class: com.nuance.nmdp.speechkit.s.2
                    @Override // defpackage.ac
                    public final void a(float f) {
                        synchronized (s.this.g) {
                            s.this.d = f;
                        }
                    }
                });
            }
        };
    }

    protected abstract p<ResultType> a(defpackage.m mVar, String str, boolean z, boolean z2, String str2, String str3, ac acVar);

    protected final void a() {
        if (this.e != null) {
            ax.a(this.e);
            this.e = null;
        }
    }

    public void cancel() {
        this.c.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.s.6
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b.f();
            }
        });
    }

    public float getAudioLevel() {
        float f;
        synchronized (this.g) {
            f = this.d;
        }
        return f;
    }

    @Override // com.nuance.nmdp.speechkit.j
    public void setPrompt(final int i, final Prompt prompt) {
        this.c.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.s.3
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b.a(i, prompt);
            }
        });
    }

    public void start() {
        this.c.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.s.4
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b.d();
            }
        });
    }

    public void stopRecording() {
        this.c.e();
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.s.5
            @Override // java.lang.Runnable
            public final void run() {
                s.this.b.e();
            }
        });
    }
}
