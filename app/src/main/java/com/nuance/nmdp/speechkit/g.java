package com.nuance.nmdp.speechkit;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import defpackage.bf;
import defpackage.cv;
import defpackage.cw;

/* JADX INFO: loaded from: classes.dex */
final class g implements DataUploadResult {
    private boolean b = false;
    private a[] a = null;

    static final class a implements DataUploadResult.DataResult {
        private String a;
        private String b;
        private String c;
        private String d;
        private int e;
        private int f;

        a() {
        }

        final boolean a(cv cvVar) {
            if (cvVar.a("id")) {
                this.a = cvVar.f("id");
            }
            if (cvVar.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY)) {
                this.b = cvVar.f(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
            }
            if (cvVar.a("status")) {
                this.c = cvVar.f("status");
            }
            if (cvVar.a("checksum")) {
                this.d = cvVar.f("checksum");
            }
            if (cvVar.a("force_upload")) {
                this.f = cvVar.d("force_upload");
            }
            if (!cvVar.a("final_count")) {
                return true;
            }
            this.e = cvVar.d("final_count");
            return true;
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final int getChecksum() {
            try {
                return Integer.parseInt(this.d);
            } catch (Exception e) {
                return 0;
            }
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final int getDataCount() {
            return this.e;
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final int getForceUpload() {
            return this.f;
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final String getId() {
            return this.a;
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final String getStatus() {
            return this.c;
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadResult.DataResult
        public final String getType() {
            return this.b;
        }
    }

    g() {
    }

    final bf<DataUploadResult> a() {
        return new bf<DataUploadResult>() { // from class: com.nuance.nmdp.speechkit.g.1
            /* JADX INFO: Access modifiers changed from: private */
            @Override // defpackage.bf
            public boolean a(cv cvVar) {
                try {
                    if (cvVar.d("voc_pregeneration_status") == 1) {
                        g.this.b = true;
                    } else {
                        g.this.b = false;
                    }
                    cw cwVarI = cvVar.i("result_list");
                    if (cwVarI != null) {
                        int iA = cwVarI.a();
                        g.this.a = new a[iA];
                        for (int i = 0; i < iA; i++) {
                            g.this.a[i] = new a();
                            g.this.a[i].a(cwVarI.g(i));
                        }
                        return true;
                    }
                } catch (Throwable th) {
                    com.nuance.nmdp.speechkit.recognitionresult.b.a(this, "Error parsing result", th);
                }
                return false;
            }

            @Override // defpackage.bf
            public final boolean a() {
                return false;
            }

            @Override // defpackage.bf
            public final /* bridge */ /* synthetic */ DataUploadResult b() {
                return g.this;
            }
        };
    }

    @Override // com.nuance.nmdp.speechkit.DataUploadResult
    public final DataUploadResult.DataResult getDataResult(int i) {
        if (i < 0 || i >= this.a.length) {
            return null;
        }
        return this.a[i];
    }

    @Override // com.nuance.nmdp.speechkit.DataUploadResult
    public final int getDataResultCount() {
        return this.a.length;
    }

    @Override // com.nuance.nmdp.speechkit.DataUploadResult
    public final boolean isVocRegenerated() {
        return this.b;
    }
}
