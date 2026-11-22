package com.flurry.sdk;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
final class hv implements Cif<Date>, in<Date> {
    private final DateFormat a;
    private final DateFormat b;
    private final DateFormat c;

    hv() {
        this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    hv(String str) {
        this(new SimpleDateFormat(str, Locale.US), new SimpleDateFormat(str));
    }

    public hv(int i, int i2) {
        this(DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    hv(DateFormat dateFormat, DateFormat dateFormat2) {
        this.a = dateFormat;
        this.b = dateFormat2;
        this.c = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        this.c.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // com.flurry.sdk.in
    public ig a(Date date, Type type, im imVar) {
        il ilVar;
        synchronized (this.b) {
            ilVar = new il(this.a.format(date));
        }
        return ilVar;
    }

    @Override // com.flurry.sdk.Cif
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Date b(ig igVar, Type type, ie ieVar) throws ik {
        if (!(igVar instanceof il)) {
            throw new ik("The date should be a string value");
        }
        Date dateA = a(igVar);
        if (type != Date.class) {
            if (type == Timestamp.class) {
                return new Timestamp(dateA.getTime());
            }
            if (type == java.sql.Date.class) {
                return new java.sql.Date(dateA.getTime());
            }
            throw new IllegalArgumentException(getClass() + " cannot deserialize to " + type);
        }
        return dateA;
    }

    private Date a(ig igVar) {
        Date date;
        synchronized (this.b) {
            try {
                date = this.b.parse(igVar.b());
            } catch (ParseException e) {
                try {
                    date = this.a.parse(igVar.b());
                } catch (ParseException e2) {
                    try {
                        date = this.c.parse(igVar.b());
                    } catch (ParseException e3) {
                        throw new io(igVar.b(), e3);
                    }
                }
            }
        }
        return date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(hv.class.getSimpleName());
        sb.append('(').append(this.b.getClass().getSimpleName()).append(')');
        return sb.toString();
    }
}
