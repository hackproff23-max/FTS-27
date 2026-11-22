package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes.dex */
public class cl<ObjectType> implements gx<ObjectType> {
    protected Class<ObjectType> a;
    private final ia b;

    public cl(Class<ObjectType> cls) {
        this(cls, false);
    }

    public cl(Class<ObjectType> cls, boolean z) {
        this.a = cls;
        ib ibVar = new ib();
        if (z) {
            ibVar.a();
        }
        this.b = ibVar.b();
    }

    @Override // com.flurry.sdk.gx
    public void a(OutputStream outputStream, ObjectType objecttype) throws IOException {
        if (outputStream != null && objecttype != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            this.b.a(objecttype, outputStreamWriter);
            outputStreamWriter.flush();
        }
    }

    @Override // com.flurry.sdk.gx
    public ObjectType b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        return (ObjectType) this.b.a(new InputStreamReader(inputStream), this.a);
    }
}
