package com.flurry.sdk;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class il extends ig {
    private static final Class<?>[] a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public il(Boolean bool) {
        a(bool);
    }

    public il(Number number) {
        a(number);
    }

    public il(String str) {
        a(str);
    }

    void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
        } else {
            iy.a((obj instanceof Number) || b(obj));
            this.b = obj;
        }
    }

    public boolean o() {
        return this.b instanceof Boolean;
    }

    @Override // com.flurry.sdk.ig
    Boolean n() {
        return (Boolean) this.b;
    }

    @Override // com.flurry.sdk.ig
    public boolean f() {
        return o() ? n().booleanValue() : Boolean.parseBoolean(b());
    }

    public boolean p() {
        return this.b instanceof Number;
    }

    @Override // com.flurry.sdk.ig
    public Number a() {
        return this.b instanceof String ? new jd((String) this.b) : (Number) this.b;
    }

    public boolean q() {
        return this.b instanceof String;
    }

    @Override // com.flurry.sdk.ig
    public String b() {
        if (p()) {
            return a().toString();
        }
        if (o()) {
            return n().toString();
        }
        return (String) this.b;
    }

    @Override // com.flurry.sdk.ig
    public double c() {
        return p() ? a().doubleValue() : Double.parseDouble(b());
    }

    @Override // com.flurry.sdk.ig
    public long d() {
        return p() ? a().longValue() : Long.parseLong(b());
    }

    @Override // com.flurry.sdk.ig
    public int e() {
        return p() ? a().intValue() : Integer.parseInt(b());
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : a) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            long jLongValue = a().longValue();
            return (int) (jLongValue ^ (jLongValue >>> 32));
        }
        if (this.b instanceof Number) {
            long jDoubleToLongBits = Double.doubleToLongBits(a().doubleValue());
            return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }
        return this.b.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        il ilVar = (il) obj;
        if (this.b == null) {
            return ilVar.b == null;
        }
        if (a(this) && a(ilVar)) {
            return a().longValue() == ilVar.a().longValue();
        }
        if ((this.b instanceof Number) && (ilVar.b instanceof Number)) {
            double dDoubleValue = a().doubleValue();
            double dDoubleValue2 = ilVar.a().doubleValue();
            if (dDoubleValue == dDoubleValue2 || (Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2))) {
                z = true;
            }
            return z;
        }
        return this.b.equals(ilVar.b);
    }

    private static boolean a(il ilVar) {
        if (!(ilVar.b instanceof Number)) {
            return false;
        }
        Number number = (Number) ilVar.b;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
