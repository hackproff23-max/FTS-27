package com.flurry.sdk;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class k {
    private static final String a = k.class.getSimpleName();
    private final fy<fi> b = new fy<fi>() { // from class: com.flurry.sdk.k.1
        @Override // com.flurry.sdk.fy
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void notify(fi fiVar) {
            if (fiVar.a) {
                k.this.e();
            }
        }
    };
    private final File c;
    private final File d;
    private String e;
    private int f;
    private ai g;

    static /* synthetic */ int d(k kVar) {
        int i = kVar.f;
        kVar.f = i + 1;
        return i;
    }

    public k() {
        fz.a().a("com.flurry.android.sdk.NetworkStateEvent", this.b);
        this.c = fp.a().c().getFileStreamPath(".flurryads.mediaassets");
        this.d = fp.a().c().getFileStreamPath(".flurryads.mediaassets.tmp");
    }

    public boolean a() {
        return this.c.exists();
    }

    public File b() {
        if (a()) {
            return this.c;
        }
        return null;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e = str;
        }
    }

    public void c() {
        this.f = 0;
        fp.a().b(new hq() { // from class: com.flurry.sdk.k.2
            @Override // com.flurry.sdk.hq
            public void safeRun() {
                k.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void e() {
        final String str;
        if (!TextUtils.isEmpty(this.e)) {
            final SharedPreferences sharedPreferences = fp.a().c().getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
            if (this.e.equals(sharedPreferences.getString("flurry_last_media_asset_url", null)) && a()) {
                gd.a(3, a, "Media player assets: download not necessary");
            } else {
                if (this.f < 3) {
                    str = this.e + "android.zip";
                } else {
                    str = "http://flurry.cachefly.net/vast/videocontrols/v1/android.zip";
                }
                if (this.g != null) {
                    this.g.e();
                }
                this.d.delete();
                gd.a(3, a, "Media player assets: attempting download from url: " + str);
                this.g = new aj(this.d);
                this.g.a(str);
                this.g.a(30000);
                this.g.a(new ai.a() { // from class: com.flurry.sdk.k.3
                    @Override // com.flurry.sdk.ai.a
                    public void a(ai aiVar) {
                        if (!aiVar.a() || !k.this.d.exists()) {
                            gd.a(3, k.a, "Media player assets: download failed");
                            if (fj.a().c()) {
                                k.d(k.this);
                            }
                            fp.a().a(new hq() { // from class: com.flurry.sdk.k.3.1
                                @Override // com.flurry.sdk.hq
                                public void safeRun() {
                                    k.this.e();
                                }
                            }, 10000L);
                        } else {
                            k.this.c.delete();
                            if (k.this.d.renameTo(k.this.c)) {
                                gd.a(3, k.a, "Media player assets: download successful");
                                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                editorEdit.putString("flurry_last_media_asset_url", str);
                                editorEdit.commit();
                            } else {
                                gd.a(3, k.a, "Media player assets: couldn't rename tmp file (giving up)");
                            }
                        }
                        k.this.g = null;
                    }
                });
                this.g.d();
            }
        }
    }
}
