package com.applovin.impl.adview;

/* JADX INFO: loaded from: classes.dex */
class j implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private j(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    /* synthetic */ j(AdViewControllerImpl adViewControllerImpl, a aVar) {
        this(adViewControllerImpl);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.a.h.loadDataWithBaseURL("/", "<html></html>", "text/html", null, "");
        } catch (Exception e) {
        }
    }
}
