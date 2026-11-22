package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.bf;
import defpackage.cv;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
final class h implements bf<GenericRecognition> {
    private LinkedList<GenericRecognition> a = new LinkedList<>();
    private boolean b = false;

    static class a implements GenericRecognition {
        private final boolean a;
        private final PdxValue.Dictionary b;
        private final PdxValue.Dictionary c;

        a(PdxValue.Dictionary dictionary) {
            boolean z;
            this.b = dictionary;
            PdxValue pdxValue = dictionary.get("appserver_results");
            if (pdxValue != null && pdxValue.getType() == 2) {
                this.c = (PdxValue.Dictionary) pdxValue;
                PdxValue pdxValue2 = dictionary == null ? null : this.c.get("final_response");
                if (pdxValue2 != null && pdxValue2.getType() == 1 && ((PdxValue.Integer) pdxValue2).get() == 0) {
                    z = false;
                }
                this.a = z;
            }
            this.c = null;
            z = true;
            this.a = z;
        }

        @Override // com.nuance.nmdp.speechkit.GenericRecognition
        public final PdxValue.Dictionary getAppserverResult() {
            return this.c;
        }

        @Override // com.nuance.nmdp.speechkit.GenericRecognition
        public final PdxValue.Dictionary getFullResult() {
            return this.b;
        }

        @Override // com.nuance.nmdp.speechkit.GenericRecognition
        public final boolean isFinalResult() {
            return this.a;
        }
    }

    h() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // defpackage.bf
    public boolean a(cv cvVar) {
        try {
            a aVar = new a(com.nuance.nmdp.speechkit.recognitionresult.b.a(cvVar));
            this.b = !aVar.isFinalResult();
            this.a.add(aVar);
            return true;
        } catch (Throwable th) {
            com.nuance.nmdp.speechkit.recognitionresult.b.c(this, "Error parsing result");
            return false;
        }
    }

    @Override // defpackage.bf
    public final boolean a() {
        return this.b;
    }

    @Override // defpackage.bf
    public final /* synthetic */ GenericRecognition b() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.remove();
    }
}
