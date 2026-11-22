package com.nuance.nmdp.speechkit;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class f extends b<DataUploadResult> implements DataUploadCommand {
    private DataUploadCommand.Listener b;
    private boolean c;

    public f(x xVar, DataUploadCommand.Listener listener, Object obj) {
        super(xVar, xVar.k(), obj);
        synchronized (this.a) {
            this.b = listener;
        }
        this.c = false;
        a();
    }

    @Override // com.nuance.nmdp.speechkit.b
    protected final a<DataUploadResult> a(defpackage.m mVar, String str, List<defpackage.q> list) {
        return new e(mVar, str, list) { // from class: com.nuance.nmdp.speechkit.f.1
            @Override // com.nuance.nmdp.speechkit.a
            protected final void a(final SpeechError speechError) {
                f.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.f.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (f.this.b != null) {
                            f.this.b.onError(f.this, speechError);
                        }
                    }
                });
            }

            @Override // com.nuance.nmdp.speechkit.a
            protected final /* synthetic */ void a(DataUploadResult dataUploadResult) {
                final DataUploadResult dataUploadResult2 = dataUploadResult;
                f.this.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.f.1.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (f.this.b != null) {
                            f.this.b.onResults(f.this, dataUploadResult2);
                        }
                    }
                });
            }
        };
    }

    final void b() {
        this.c = true;
    }

    @Override // com.nuance.nmdp.speechkit.DataUploadCommand
    public final void setListener(DataUploadCommand.Listener listener) {
        x.a(listener, "listener");
        synchronized (this.a) {
            this.b = listener;
        }
    }

    @Override // com.nuance.nmdp.speechkit.b, com.nuance.nmdp.speechkit.DataUploadCommand
    public final void start() {
        if (this.c) {
            super.start();
        }
    }
}
