package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.recognitionresult.DetailedResult;
import defpackage.bf;
import defpackage.cv;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class o implements Recognition {
    private String[] a = null;
    private int[] b = null;
    private String c = null;
    private List<DetailedResult> d;

    class a implements Recognition.Result {
        private final String a;
        private final int b;

        a(String str, int i) {
            this.a = str;
            this.b = i;
        }

        @Override // com.nuance.nmdp.speechkit.Recognition.Result
        public final int getScore() {
            return this.b;
        }

        @Override // com.nuance.nmdp.speechkit.Recognition.Result
        public final String getText() {
            return this.a;
        }
    }

    final bf<Recognition> a() {
        return new bf<Recognition>() { // from class: com.nuance.nmdp.speechkit.o.1
            /* JADX INFO: Access modifiers changed from: private */
            @Override // defpackage.bf
            public boolean a(cv cvVar) {
                String strF;
                try {
                    o.this.d = com.nuance.nmdp.speechkit.recognitionresult.b.a(cvVar.e("transcription"));
                    String[] strArr = new String[o.this.d.size()];
                    int[] iArr = new int[o.this.d.size()];
                    try {
                        strF = cvVar.f("prompt");
                    } catch (Throwable th) {
                        strF = null;
                    }
                    for (int i = 0; i < o.this.d.size(); i++) {
                        strArr[i] = ((DetailedResult) o.this.d.get(i)).toString();
                        iArr[i] = (int) ((DetailedResult) o.this.d.get(i)).getConfidenceScore();
                    }
                    o.this.a = strArr;
                    o.this.b = iArr;
                    o.this.c = strF;
                    return true;
                } catch (Throwable th2) {
                    com.nuance.nmdp.speechkit.recognitionresult.b.a(this, "Unable to extract transcriptions from result", th2);
                    o.this.a = new String[0];
                    o.this.b = new int[0];
                    o.this.c = null;
                    return false;
                }
            }

            @Override // defpackage.bf
            public final boolean a() {
                return false;
            }

            @Override // defpackage.bf
            public final /* bridge */ /* synthetic */ Recognition b() {
                return o.this;
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.Recognition
    public final List<DetailedResult> getDetailedResults() {
        return this.d;
    }

    @Override // com.nuance.nmdp.speechkit.Recognition
    public final Recognition.Result getResult(int i) {
        if (i < 0 || i >= this.b.length) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < getResultCount().");
        }
        return new a(this.a[i], this.b[i]);
    }

    @Override // com.nuance.nmdp.speechkit.Recognition
    public final int getResultCount() {
        return this.b.length;
    }

    @Override // com.nuance.nmdp.speechkit.Recognition
    public final String getSuggestion() {
        return this.c;
    }
}
