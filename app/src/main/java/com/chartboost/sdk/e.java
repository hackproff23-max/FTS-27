package com.chartboost.sdk;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.bi;
import com.chartboost.sdk.impl.bq;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    private static e c;
    private bq a = null;
    private com.chartboost.sdk.Model.a b;

    private e() {
    }

    public static e a() {
        if (c == null) {
            c = new e();
        }
        return c;
    }

    protected void a(com.chartboost.sdk.Model.a aVar) {
        switch (aVar.b) {
            case LOADING:
                if (aVar.i && b.s()) {
                    f(aVar);
                    break;
                }
                break;
            default:
                e(aVar);
                break;
        }
    }

    private void e(com.chartboost.sdk.Model.a aVar) {
        if (this.a != null && this.a.h() != aVar) {
            CBLogging.b("CBViewController", "Impression already visible");
            aVar.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            return;
        }
        boolean z = aVar.b != com.chartboost.sdk.Model.a.b.DISPLAYED;
        aVar.b = com.chartboost.sdk.Model.a.b.DISPLAYED;
        Activity activityE = Chartboost.e();
        CBError.CBImpressionError cBImpressionErrorK = activityE == null ? CBError.CBImpressionError.NO_HOST_ACTIVITY : null;
        if (cBImpressionErrorK == null) {
            cBImpressionErrorK = aVar.k();
        }
        if (cBImpressionErrorK != null) {
            CBLogging.b("CBViewController", "Cannot able to create the view while trying th display the impression");
            aVar.a(cBImpressionErrorK);
            return;
        }
        if (this.a == null) {
            this.a = new bq(activityE, aVar);
            activityE.addContentView(this.a, new FrameLayout.LayoutParams(-1, -1));
        }
        this.a.a();
        CBLogging.b("CBViewController", "Displaying the impression");
        aVar.h = this.a;
        if (z) {
            this.a.e().a();
            bi.b bVar = bi.b.PERSPECTIVE_ROTATE;
            if (aVar.c == com.chartboost.sdk.Model.a.c.MORE_APPS) {
                bVar = bi.b.PERSPECTIVE_ZOOM;
            }
            bi.b bVarA = bi.b.a(aVar.w().f("animation"));
            if (bVarA != null) {
                bVar = bVarA;
            }
            if (b.f()) {
                bVar = bi.b.NONE;
            }
            aVar.n();
            bi.a(bVar, aVar, new bi.a() { // from class: com.chartboost.sdk.e.1
                @Override // com.chartboost.sdk.impl.bi.a
                public void a(com.chartboost.sdk.Model.a aVar2) {
                    aVar2.o();
                }
            });
            if (b.d() != null && (aVar.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_VIDEO || aVar.e == com.chartboost.sdk.Model.a.d.INTERSTITIAL_REWARD_VIDEO)) {
                b.d().willDisplayVideo(aVar.d);
            }
            if (aVar.q().b() != null) {
                aVar.q().b().e(aVar);
            }
        }
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.e$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ com.chartboost.sdk.Model.a a;

        AnonymousClass2(com.chartboost.sdk.Model.a aVar) {
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.b = com.chartboost.sdk.Model.a.b.DISMISSING;
            bi.b bVar = bi.b.PERSPECTIVE_ROTATE;
            if (this.a.c == com.chartboost.sdk.Model.a.c.MORE_APPS) {
                bVar = bi.b.PERSPECTIVE_ZOOM;
            }
            bi.b bVarA = bi.b.a(this.a.w().f("animation"));
            if (bVarA != null) {
                bVar = bVarA;
            }
            if (b.f()) {
                bVar = bi.b.NONE;
            }
            bi.b(bVar, this.a, new bi.a() { // from class: com.chartboost.sdk.e.2.1
                @Override // com.chartboost.sdk.impl.bi.a
                public void a(final com.chartboost.sdk.Model.a aVar) {
                    CBUtility.e().post(new Runnable() { // from class: com.chartboost.sdk.e.2.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            e.this.d(aVar);
                        }
                    });
                    aVar.m();
                }
            });
        }
    }

    public void b(com.chartboost.sdk.Model.a aVar) {
        CBLogging.b("CBViewController", "Dismissing impression");
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(aVar);
        if (aVar.k) {
            aVar.a(anonymousClass2);
        } else {
            anonymousClass2.run();
        }
    }

    private void f(com.chartboost.sdk.Model.a aVar) {
        Activity activityE = Chartboost.e();
        if (activityE == null) {
            CBLogging.d(this, "No host activity to display loading view");
            return;
        }
        if (this.a == null) {
            this.a = new bq(activityE, aVar);
            activityE.addContentView(this.a, new FrameLayout.LayoutParams(-1, -1));
        }
        this.a.b();
        this.b = aVar;
    }

    public void a(com.chartboost.sdk.Model.a aVar, boolean z) {
        if (aVar != null) {
            if (aVar == this.b || aVar == c.a().c()) {
                this.b = null;
                CBLogging.b("CBViewController", "Dismissing loading view");
                if (b()) {
                    this.a.c();
                    if (z && this.a != null && this.a.h() != null) {
                        d(this.a.h());
                    }
                }
            }
        }
    }

    public void c(com.chartboost.sdk.Model.a aVar) {
        CBLogging.b("CBViewController", "Removing impression silently");
        if (b()) {
            a(aVar, false);
        }
        aVar.j();
        try {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        } catch (Exception e) {
            CBLogging.b("CBViewController", "Exception removing impression silently", e);
        }
        this.a = null;
    }

    public void d(com.chartboost.sdk.Model.a aVar) {
        CBLogging.b("CBViewController", "Removing impression");
        aVar.b = com.chartboost.sdk.Model.a.b.NONE;
        aVar.q().b().c(aVar);
        if (aVar.y()) {
            aVar.q().b().b(aVar);
        }
        if (this.a == null) {
            if (b.e()) {
                e();
                return;
            }
            return;
        }
        try {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        } catch (Exception e) {
            CBLogging.b("CBViewController", "Exception removing impression ", e);
        }
        aVar.i();
        this.a = null;
        if (b.e()) {
            e();
        }
    }

    private void e() {
        CBLogging.b("CBViewController", " Closing impression activity");
        Activity activityE = Chartboost.e();
        if (activityE != null && (activityE instanceof CBImpressionActivity)) {
            CBLogging.b("CBViewController", " Closing impression activity #######");
            Chartboost.f();
            activityE.finish();
        }
    }

    public boolean b() {
        return this.a != null && this.a.g();
    }

    public boolean c() {
        return this.a != null;
    }

    public bq d() {
        return this.a;
    }
}
