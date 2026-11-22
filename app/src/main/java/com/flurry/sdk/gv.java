package com.flurry.sdk;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gv<ObjectType> extends gu<ObjectType> {
    public gv(gx<ObjectType> gxVar) {
        super(gxVar);
    }

    @Override // com.flurry.sdk.gu, com.flurry.sdk.gx
    public void a(OutputStream outputStream, ObjectType objecttype) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        if (outputStream != null) {
            try {
                gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    super.a(gZIPOutputStream, objecttype);
                    hp.a(gZIPOutputStream);
                } catch (Throwable th) {
                    th = th;
                    hp.a(gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
            }
        }
    }

    @Override // com.flurry.sdk.gu, com.flurry.sdk.gx
    public ObjectType b(InputStream inputStream) throws Throwable {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        ObjectType objecttype = null;
        if (inputStream != null) {
            try {
                gZIPInputStream = new GZIPInputStream(inputStream);
                try {
                    objecttype = (ObjectType) super.b(gZIPInputStream);
                    hp.a(gZIPInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    hp.a(gZIPInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                gZIPInputStream = null;
                th = th3;
            }
        }
        return objecttype;
    }
}
