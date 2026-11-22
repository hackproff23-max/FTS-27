package com.flurry.sdk;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes.dex */
public final class js extends ir<Date> {
    public static final is a = new is() { // from class: com.flurry.sdk.js.1
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            if (jwVar.a() == Date.class) {
                return new js();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.flurry.sdk.ir
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Date b(jx jxVar) throws IOException {
        Date date;
        if (jxVar.f() == jy.NULL) {
            jxVar.j();
            date = null;
        } else {
            try {
                date = new Date(this.b.parse(jxVar.h()).getTime());
            } catch (ParseException e) {
                throw new io(e);
            }
        }
        return date;
    }

    @Override // com.flurry.sdk.ir
    public synchronized void a(jz jzVar, Date date) throws IOException {
        jzVar.b(date == null ? null : this.b.format((java.util.Date) date));
    }
}
