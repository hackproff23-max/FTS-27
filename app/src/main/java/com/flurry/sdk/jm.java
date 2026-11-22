package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public final class jm implements is {
    private final ja a;

    public jm(ja jaVar) {
        this.a = jaVar;
    }

    @Override // com.flurry.sdk.is
    public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
        iu iuVar = (iu) jwVar.a().getAnnotation(iu.class);
        if (iuVar == null) {
            return null;
        }
        return (ir<T>) a(this.a, iaVar, jwVar, iuVar);
    }

    static ir<?> a(ja jaVar, ia iaVar, jw<?> jwVar, iu iuVar) {
        Class<?> clsA = iuVar.a();
        if (ir.class.isAssignableFrom(clsA)) {
            return (ir) jaVar.a(jw.b(clsA)).a();
        }
        if (is.class.isAssignableFrom(clsA)) {
            return ((is) jaVar.a(jw.b(clsA)).a()).a(iaVar, jwVar);
        }
        throw new IllegalArgumentException("@JsonAdapter value must be TypeAdapter or TypeAdapterFactory reference.");
    }
}
