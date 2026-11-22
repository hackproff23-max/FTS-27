package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gu<ObjectType> implements gx<ObjectType> {
    protected final gx<ObjectType> a;

    public gu(gx<ObjectType> gxVar) {
        this.a = gxVar;
    }

    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, ObjectType objecttype) throws IOException {
        if (this.a != null && outputStream != null && objecttype != null) {
            this.a.a(outputStream, objecttype);
        }
    }

    @Override // com.flurry.sdk.gx
    public ObjectType b(InputStream inputStream) throws IOException {
        if (this.a == null || inputStream == null) {
            return null;
        }
        return this.a.b(inputStream);
    }
}
