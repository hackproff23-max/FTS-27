package com.nuance.nmsp.client.sdk.oem;

import com.facebook.internal.ServerProtocol;
import defpackage.bm;
import defpackage.bn;
import defpackage.bp;
import defpackage.cz;
import defpackage.ev;
import defpackage.ex;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Vector;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public class h implements bn.b, bp {
    private static final bm a = defpackage.d.a(h.class);
    private static final Integer c = new Integer(0);
    private static final Integer d = new Integer(1);
    private static final Integer e = new Integer(2);
    private static final Integer f = new Integer(3);
    private bn b;
    private Object g = new Object();

    static class a {
        Socket a;
        bp.a b;
        InputStream c;
        OutputStream d;
        Object e;
        c f;

        public a(Socket socket, bp.a aVar, InputStream inputStream, OutputStream outputStream, c cVar) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.a = socket;
            this.b = aVar;
            this.e = null;
            this.c = inputStream;
            this.d = outputStream;
            this.f = cVar;
        }
    }

    class b {
        byte[] a;
        int b;
        bp.e c;
        Object d;

        public b(byte[] bArr, int i, bp.e eVar, Object obj) {
            this.a = bArr;
            this.b = i;
            this.c = eVar;
            this.d = obj;
        }
    }

    class c extends Thread {
        private String a;
        private int b;
        private bp.d c;
        private bp.a d;
        private bp.c e;
        private boolean f;
        private Vector g;
        private ex h;
        private boolean i;

        public c(String str, int i, bp.d dVar, bp.a aVar) {
            this.a = null;
            this.c = null;
            this.d = null;
            this.e = bp.c.a;
            this.f = false;
            this.g = null;
            this.h = null;
            this.i = false;
            this.a = str;
            this.b = i;
            this.c = dVar;
            this.d = aVar;
            this.g = new Vector();
        }

        public c(String str, int i, ex exVar, bp.d dVar, bp.a aVar) {
            this.a = null;
            this.c = null;
            this.d = null;
            this.e = bp.c.a;
            this.f = false;
            this.g = null;
            this.h = null;
            this.i = false;
            this.a = str;
            this.b = i;
            this.c = dVar;
            this.d = aVar;
            this.g = new Vector();
            this.i = true;
            this.h = exVar;
        }

        public final bp.c a(b bVar) {
            bp.c cVar;
            Object obj;
            if (this.f) {
                if (h.a.e()) {
                    h.a.e("SocketReadThread is already stopping!");
                }
                return bp.c.b;
            }
            bp.c cVar2 = bp.c.a;
            synchronized (h.this.g) {
                if (this.g == null) {
                    cVar = bp.c.b;
                    obj = "SocketReadThread: queue is null!!";
                } else {
                    this.g.addElement(bVar);
                    cVar = cVar2;
                    obj = null;
                }
                if (h.a.b()) {
                    h.a.b("addNewJob() notifying");
                }
                h.this.g.notify();
            }
            if (!h.a.e() || obj == null) {
                return cVar;
            }
            h.a.e(obj);
            return cVar;
        }

        public final void a() {
            synchronized (h.this.g) {
                this.f = true;
                this.g.removeAllElements();
                this.g = null;
                h.this.g.notify();
            }
        }

        public final void b() {
            String str = null;
            synchronized (h.this.g) {
                if (this.g == null) {
                    str = "SocketReadThread.cleanPendingJobs(): queue is null!!";
                } else {
                    this.g.removeAllElements();
                }
            }
            if (!h.a.e() || str == null) {
                return;
            }
            h.a.e(str);
        }

        /* JADX WARN: Code duplicated, block: B:156:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:67:0x0291  */
        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            b bVar;
            if (h.a.b()) {
                h.a.b("Open Socket connection on host:- [" + this.a + "] and port[" + this.b + "]");
            }
            try {
                Socket socketA = this.i ? ev.a(this.a, this.b, this.h) : new Socket(this.a, this.b);
                if (!socketA.isConnected()) {
                    if (h.a.e()) {
                        h.a.e("SocketReadThread NETWORK_ERROR. !sock.isConnected()");
                    }
                    h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
                    return;
                }
                try {
                    if (this.i) {
                        ((SSLSocket) socketA).setUseClientMode(true);
                    }
                    socketA.setTcpNoDelay(true);
                    socketA.setKeepAlive(true);
                    socketA.setSoLinger(true, 40);
                } catch (Exception e) {
                    if (h.a.e()) {
                        h.a.e("Set Socket Option Exception - [" + e.getClass().getName() + "] Message - [" + e.getMessage() + "]");
                    }
                }
                try {
                    InputStream inputStream = socketA.getInputStream();
                    a aVar = new a(socketA, this.d, inputStream, socketA.getOutputStream(), this);
                    h.this.a(new Object[]{h.c, this.c, bp.c.a, aVar, null});
                    while (!this.f) {
                        try {
                            synchronized (h.this.g) {
                                while (!this.f && this.g != null && this.g.isEmpty()) {
                                    try {
                                        if (h.a.b()) {
                                            h.a.b("socket read thread. waiting syncObj in.");
                                        }
                                        try {
                                            h.this.g.wait();
                                        } catch (Exception e2) {
                                            if (h.a.e()) {
                                                h.a.e("InterruptedException ie:" + e2);
                                            }
                                        }
                                        if (h.a.b()) {
                                            h.a.b("socket read thread. waiting syncObj out.");
                                        }
                                    } catch (Throwable th) {
                                        throw th;
                                    }
                                }
                                if (this.f || this.g == null) {
                                    return;
                                }
                                bVar = (b) this.g.elementAt(0);
                                this.g.removeElementAt(0);
                                if (h.a.e()) {
                                    h.a.e("NetworkSystemJ2me.run() " + e.getClass().getName() + " " + e.getMessage());
                                    return;
                                }
                                return;
                            }
                            try {
                                if (h.a.b()) {
                                    h.a.b("socket read thread. reading socket in.");
                                }
                                int i = 0;
                                while (i < bVar.b) {
                                    int i2 = inputStream.read(bVar.a, i + 0, bVar.b - i);
                                    if (h.a.b()) {
                                        h.a.b("socket read thread. bytesRead:" + i2);
                                    }
                                    if (i2 < 0) {
                                        if (!this.f) {
                                            this.e = bp.c.b;
                                            break;
                                        }
                                        break;
                                    }
                                    i += i2;
                                }
                                if (h.a.b()) {
                                    h.a.b("socket read thread. reading socket out.");
                                }
                            } catch (IOException e3) {
                                if (h.a.e()) {
                                    h.a.e("Socket Read/Available Expception - [" + e3.getClass().getName() + "] Message - [" + e3.getMessage() + "]");
                                }
                                this.e = bp.c.b;
                            }
                            if (this.e == bp.c.a) {
                                h.this.a(new Object[]{h.e, bVar.c, bp.c.a, aVar, bVar.a, new Integer(0), new Integer(bVar.b), new Integer(bVar.b), bVar.d});
                            } else {
                                h.this.a(new Object[]{h.e, bVar.c, bp.c.b, aVar, bVar.a, new Integer(0), new Integer(bVar.b), new Integer(0), bVar.d});
                                h.this.a(aVar);
                            }
                        } catch (Exception e4) {
                            if (h.a.e()) {
                                h.a.e("NetworkSystemJ2me.run() " + e4.getClass().getName() + " " + e4.getMessage());
                                return;
                            }
                            return;
                        }
                    }
                } catch (IOException e5) {
                    try {
                        socketA.close();
                    } catch (IOException e6) {
                    }
                    if (h.a.e()) {
                        h.a.e("Set Socket Option Exception - [" + e5.getClass().getName() + "] Message - [" + e5.getMessage() + "]");
                    }
                    h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
                }
            } catch (UnknownHostException e7) {
                if (h.a.e()) {
                    h.a.e("Open Streams Exception - [" + e7.getClass().getName() + "] Message - [" + e7.getMessage() + "]");
                }
                h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
            } catch (IOException e8) {
                if (h.a.e()) {
                    h.a.e("Open Streams Exception - [" + e8.getClass().getName() + "] Message - [" + e8.getMessage() + "]");
                }
                h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
            } catch (SecurityException e9) {
                if (h.a.e()) {
                    h.a.e("Open Streams Exception - [" + e9.getClass().getName() + "] Message - [" + e9.getMessage() + "]");
                }
                h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
            } catch (Throwable th2) {
                if (h.a.e()) {
                    h.a.e("Open Streams Exception - [" + th2.getClass().getName() + "] Message - [" + th2.getMessage() + "]");
                }
                h.this.a(new Object[]{h.c, this.c, bp.c.b, null, null});
            }
        }
    }

    public h(bn bnVar) {
        this.b = null;
        this.b = bnVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object[] objArr) {
        bn bnVar = this.b;
        Object objB = this.b.b();
        this.b.a();
        bnVar.a(objArr, this, objB);
    }

    @Override // defpackage.bp
    public final bp.c a(Object obj, bp.b bVar, byte[] bArr, int i, bp.e eVar, Object obj2) {
        if (bVar != bp.b.a) {
            if (a.e()) {
                a.e("Blackberry NetworkSystem only supports NETWORK_READ_FULL");
            }
            return bp.c.b;
        }
        a aVar = (a) obj;
        if (aVar.f == null) {
            if (a.b()) {
                a.b("SOCKET READ ERROR: socket read thread is null");
            }
            return bp.c.b;
        }
        if (!aVar.f.f) {
            return aVar.f.a(new b(bArr, i, eVar, obj2));
        }
        if (a.e()) {
            a.e("socket read thread is stopping");
        }
        return bp.c.b;
    }

    @Override // defpackage.bp
    public final bp.c a(Object obj, byte[] bArr, int i, bp.f fVar, Object obj2) {
        if (a.b()) {
            a.b("socketWrite(bufferLen:" + i + ") start");
        }
        a aVar = (a) obj;
        if (aVar.a == null || aVar.d == null) {
            return bp.c.b;
        }
        OutputStream outputStream = aVar.d;
        try {
            outputStream.write(bArr, 0, i);
            outputStream.flush();
            a(new Object[]{f, fVar, bp.c.a, obj, bArr, new Integer(0), new Integer(i), new Integer(i), obj2});
            if (a.b()) {
                a.b("socketWrite(bufferLen:" + i + ") end");
            }
            return bp.c.a;
        } catch (Exception e2) {
            if (a.e()) {
                a.e("Socket Write Exception - [" + e2.getClass().getName() + "] Message - [" + e2.getMessage() + "]");
            }
            a(new Object[]{f, fVar, bp.c.b, obj, bArr, new Integer(0), new Integer(i), new Integer(0), obj2});
            a(obj);
            return bp.c.b;
        }
    }

    @Override // defpackage.bp
    public final void a(Object obj) {
        boolean z;
        a aVar = (a) obj;
        if (aVar == null) {
            return;
        }
        synchronized (this.g) {
            if (aVar.f == null) {
                return;
            }
            if (aVar.f.f) {
                z = false;
            } else {
                aVar.f.a();
                aVar.f = null;
                z = true;
            }
            if (aVar.a == null || !z) {
                return;
            }
            try {
                aVar.c.close();
                aVar.c = null;
                aVar.a.close();
                aVar.a = null;
                a(new Object[]{d, aVar.b, bp.c.a, obj, null});
            } catch (Throwable th) {
                if (a.e()) {
                    a.e("Socket Close Expception - [" + th.getClass().getName() + "] Message - [" + th.getMessage() + "]");
                }
                a(new Object[]{d, aVar.b, bp.c.b, obj, null});
            }
        }
    }

    @Override // bn.b
    public final void a(Object obj, Object obj2) {
        Object[] objArr = (Object[]) obj;
        if (objArr[0] == c) {
            ((bp.d) objArr[1]).a((bp.c) objArr[2], objArr[3]);
            return;
        }
        if (objArr[0] == d) {
            ((bp.a) objArr[1]).a();
        } else if (objArr[0] == e) {
            ((bp.e) objArr[1]).a((bp.c) objArr[2], objArr[3], (byte[]) objArr[4], ((Integer) objArr[6]).intValue(), ((Integer) objArr[7]).intValue(), objArr[8]);
        } else if (objArr[0] == f) {
            ((bp.f) objArr[1]).a((bp.c) objArr[2], objArr[3], ((Integer) objArr[6]).intValue(), ((Integer) objArr[7]).intValue(), objArr[8]);
        }
    }

    @Override // defpackage.bp
    public final void a(String str, int i, bp.d dVar, bp.a aVar) {
        try {
            new c(str, i, dVar, aVar).start();
        } catch (Exception e2) {
            if (a.e()) {
                a.e("Open Socket Exception - [" + e2.getClass().getName() + "] Message - [" + e2.getMessage() + "]");
            }
            a(new Object[]{c, dVar, bp.c.b, null, null});
        }
    }

    @Override // defpackage.bp
    public final void a(String str, int i, Vector vector, bp.d dVar, bp.a aVar) {
        ex exVar = new ex();
        Enumeration enumerationElements = vector.elements();
        while (enumerationElements.hasMoreElements()) {
            cz czVar = (cz) enumerationElements.nextElement();
            if (czVar.a().equals("SSL_SelfSigned_Cert") && (new String(czVar.b()).equals("TRUE") || new String(czVar.b()).equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE))) {
                exVar.a = true;
            }
            if (czVar.a().equals("SSL_Cert_Summary")) {
                exVar.b = new String(czVar.b());
            }
            if (czVar.a().equals("SSL_Cert_Data")) {
                exVar.c = new String(czVar.b());
            }
        }
        try {
            new c(str, i, exVar, dVar, aVar).start();
        } catch (Exception e2) {
            if (a.e()) {
                a.e("Open Socket Exception - [" + e2.getClass().getName() + "] Message - [" + e2.getMessage() + "]");
            }
            a(new Object[]{c, dVar, bp.c.b, null, null});
        }
    }

    @Override // defpackage.bp
    public final void b(Object obj) {
        a aVar = (a) obj;
        if (aVar.f != null) {
            aVar.f.b();
            return;
        }
        if (a.b()) {
            a.b("SOCKET WRITE ERROR: socket read thread is null");
        }
        a(obj);
    }
}
