package com.flurry.sdk;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class jl extends ir<Date> {
    public static final is a = new is() { // from class: com.flurry.sdk.jl.1
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            if (jwVar.a() == Date.class) {
                return new jl();
            }
            return null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat d = a();

    private static DateFormat a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    @Override // com.flurry.sdk.ir
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Date b(jx jxVar) throws IOException {
        if (jxVar.f() != jy.NULL) {
            return a(jxVar.h());
        }
        jxVar.j();
        return null;
    }

    private synchronized Date a(String str) {
        Date date;
        try {
            date = this.c.parse(str);
        } catch (ParseException e) {
            try {
                date = this.b.parse(str);
            } catch (ParseException e2) {
                try {
                    date = this.d.parse(str);
                } catch (ParseException e3) {
                    throw new io(str, e3);
                }
            }
        }
        return date;
    }

    @Override // com.flurry.sdk.ir
    public synchronized void a(jz jzVar, Date date) throws IOException {
        try {
            if (date == null) {
                jzVar.f();
            } else {
                jzVar.b(this.b.format(date));
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
