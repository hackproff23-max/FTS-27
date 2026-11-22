package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ez {
    private static final String b = ez.class.getSimpleName();
    byte[] a;

    public static class a implements gx<ez> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, ez ezVar) throws IOException {
            if (outputStream != null && ezVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.ez.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                int length = 0;
                if (ezVar.a != null) {
                    length = ezVar.a.length;
                }
                dataOutputStream.writeShort(length);
                if (length > 0) {
                    dataOutputStream.write(ezVar.a);
                }
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ez b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.ez.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            ez ezVar = new ez();
            int unsignedShort = dataInputStream.readUnsignedShort();
            if (unsignedShort > 0) {
                byte[] bArr = new byte[unsignedShort];
                dataInputStream.readFully(bArr);
                ezVar.a = bArr;
            }
            return ezVar;
        }
    }

    private ez() {
    }

    public ez(byte[] bArr) {
        this.a = bArr;
    }

    public ez(fa faVar) throws Throwable {
        DataOutputStream dataOutputStream;
        int i;
        DataOutputStream dataOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(5);
                dataOutputStream.writeUTF(faVar.a());
                dataOutputStream.writeLong(faVar.b());
                dataOutputStream.writeLong(faVar.c());
                dataOutputStream.writeLong(faVar.d());
                Map<String, String> mapE = faVar.e();
                if (mapE == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(mapE.size());
                    for (Map.Entry<String, String> entry : mapE.entrySet()) {
                        dataOutputStream.writeUTF(entry.getKey());
                        dataOutputStream.writeUTF(entry.getValue());
                        dataOutputStream.writeByte(0);
                    }
                }
                dataOutputStream.writeUTF(faVar.f());
                dataOutputStream.writeUTF(faVar.g());
                dataOutputStream.writeByte(faVar.h());
                dataOutputStream.writeByte(faVar.i());
                dataOutputStream.writeUTF(faVar.j());
                if (faVar.k() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeDouble(hp.a(faVar.k().getLatitude(), 3));
                    dataOutputStream.writeDouble(hp.a(faVar.k().getLongitude(), 3));
                    dataOutputStream.writeFloat(faVar.k().getAccuracy());
                }
                dataOutputStream.writeInt(faVar.l());
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(faVar.m());
                if (faVar.n() == null) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeLong(faVar.n().longValue());
                }
                Map<String, ev> mapO = faVar.o();
                if (mapO == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(mapO.size());
                    for (Map.Entry<String, ev> entry2 : mapO.entrySet()) {
                        dataOutputStream.writeUTF(entry2.getKey());
                        dataOutputStream.writeInt(entry2.getValue().a);
                    }
                }
                List<ew> listP = faVar.p();
                if (listP == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(listP.size());
                    Iterator<ew> it = listP.iterator();
                    while (it.hasNext()) {
                        dataOutputStream.write(it.next().e());
                    }
                }
                dataOutputStream.writeBoolean(faVar.q());
                List<eu> listS = faVar.s();
                if (listS != null) {
                    int i2 = 0;
                    int iA = 0;
                    int i3 = 0;
                    while (true) {
                        if (i2 >= listS.size()) {
                            i = i3;
                            break;
                        }
                        iA += listS.get(i2).a();
                        if (iA > 160000) {
                            gd.a(5, b, "Error Log size exceeded. No more event details logged.");
                            i = i3;
                            break;
                        } else {
                            i3++;
                            i2++;
                        }
                    }
                } else {
                    i = 0;
                }
                dataOutputStream.writeInt(faVar.r());
                dataOutputStream.writeShort(i);
                for (int i4 = 0; i4 < i; i4++) {
                    dataOutputStream.write(listS.get(i4).b());
                }
                dataOutputStream.writeInt(-1);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                this.a = byteArrayOutputStream.toByteArray();
                hp.a(dataOutputStream);
            } catch (IOException e) {
                e = e;
                dataOutputStream2 = dataOutputStream;
                try {
                    gd.a(6, b, "", e);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    hp.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                hp.a(dataOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
    }

    public byte[] a() {
        return this.a;
    }
}
