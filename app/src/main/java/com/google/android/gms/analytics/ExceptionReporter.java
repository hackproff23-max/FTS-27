package com.google.android.gms.analytics;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler yN;
    private final Tracker yO;
    private ExceptionParser yP;
    private GoogleAnalytics yQ;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler originalHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        }
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.yN = originalHandler;
        this.yO = tracker;
        this.yP = new StandardExceptionParser(context, new ArrayList());
        this.mContext = context.getApplicationContext();
        ae.V("ExceptionReporter created, original handler is " + (originalHandler == null ? "null" : originalHandler.getClass().getName()));
    }

    GoogleAnalytics es() {
        if (this.yQ == null) {
            this.yQ = GoogleAnalytics.getInstance(this.mContext);
        }
        return this.yQ;
    }

    Thread.UncaughtExceptionHandler et() {
        return this.yN;
    }

    public ExceptionParser getExceptionParser() {
        return this.yP;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.yP = exceptionParser;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread t, Throwable e) {
        String description = "UncaughtException";
        if (this.yP != null) {
            description = this.yP.getDescription(t != null ? t.getName() : null, e);
        }
        ae.V("Tracking Exception: " + description);
        this.yO.send(new HitBuilders.ExceptionBuilder().setDescription(description).setFatal(true).build());
        GoogleAnalytics googleAnalyticsEs = es();
        googleAnalyticsEs.dispatchLocalHits();
        googleAnalyticsEs.dY();
        if (this.yN != null) {
            ae.V("Passing exception to original handler.");
            this.yN.uncaughtException(t, e);
        }
    }
}
