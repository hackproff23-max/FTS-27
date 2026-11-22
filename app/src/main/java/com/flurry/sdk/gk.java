package com.flurry.sdk;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class gk<RequestObjectType, ResponseObjectType> extends gl {
    private a<RequestObjectType, ResponseObjectType> a;
    private RequestObjectType b;
    private ResponseObjectType c;
    private gx<RequestObjectType> d;
    private gx<ResponseObjectType> e;

    public interface a<RequestObjectType, ResponseObjectType> {
        void a(gk<RequestObjectType, ResponseObjectType> gkVar, ResponseObjectType responseobjecttype);
    }

    public void a(RequestObjectType requestobjecttype) {
        this.b = requestobjecttype;
    }

    public void a(gx<RequestObjectType> gxVar) {
        this.d = gxVar;
    }

    public void b(gx<ResponseObjectType> gxVar) {
        this.e = gxVar;
    }

    public void a(a<RequestObjectType, ResponseObjectType> aVar) {
        this.a = aVar;
    }

    @Override // com.flurry.sdk.gl, com.flurry.sdk.hq
    public void safeRun() {
        m();
        super.safeRun();
    }

    private void m() {
        a(new gl.c() { // from class: com.flurry.sdk.gk.1
            @Override // com.flurry.sdk.gl.c
            public void a(gl glVar, OutputStream outputStream) throws Exception {
                if (gk.this.b != null && gk.this.d != null) {
                    gk.this.d.a(outputStream, gk.this.b);
                }
            }

            @Override // com.flurry.sdk.gl.c
            public void a(gl glVar, InputStream inputStream) throws Exception {
                if (glVar.d() && gk.this.e != null) {
                    gk.this.c = gk.this.e.b(inputStream);
                }
            }

            @Override // com.flurry.sdk.gl.c
            public void a(gl glVar) {
                gk.this.n();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.a != null && !b()) {
            this.a.a(this, this.c);
        }
    }
}
