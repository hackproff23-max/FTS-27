package com.flurry.sdk;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class jt extends ir<Time> {
    public static final is a = new is() { // from class: com.flurry.sdk.jt.1
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            if (jwVar.a() == Time.class) {
                return new jt();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    @Override // com.flurry.sdk.ir
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Time b(jx jxVar) throws IOException {
        Time time;
        if (jxVar.f() == jy.NULL) {
            jxVar.j();
            time = null;
        } else {
            try {
                time = new Time(this.b.parse(jxVar.h()).getTime());
            } catch (ParseException e) {
                throw new io(e);
            }
        }
        return time;
    }

    @Override // com.flurry.sdk.ir
    public synchronized void a(jz jzVar, Time time) throws IOException {
        jzVar.b(time == null ? null : this.b.format((Date) time));
    }
}
