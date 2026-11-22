package com.flurry.sdk;

import com.flurry.android.impl.ads.protocol.v13.FrequencyCapResponseInfo;
import com.flurry.android.impl.ads.protocol.v13.FrequencyCapType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class az {
    private FrequencyCapType a;
    private String b;
    private long c;
    private long d;
    private long e;
    private int f;
    private int g;
    private int h;
    private int i;
    private long j;

    public static class b implements gx<az> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, az azVar) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public az b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.az.b.1
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            az azVar = new az();
            azVar.a = FrequencyCapType.ADSPACE;
            azVar.e = 0L;
            azVar.j = 0L;
            azVar.b = dataInputStream.readUTF();
            azVar.c = dataInputStream.readLong();
            azVar.d = dataInputStream.readLong();
            azVar.i = dataInputStream.readInt();
            azVar.f = dataInputStream.readInt();
            azVar.g = dataInputStream.readInt();
            azVar.h = dataInputStream.readInt();
            return azVar;
        }
    }

    public static class a implements gx<az> {
        @Override // com.flurry.sdk.gx
        public void a(OutputStream outputStream, az azVar) throws IOException {
            if (outputStream != null && azVar != null) {
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream) { // from class: com.flurry.sdk.az.a.1
                    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                    }
                };
                dataOutputStream.writeUTF(azVar.a.name());
                dataOutputStream.writeUTF(azVar.b);
                dataOutputStream.writeLong(azVar.c);
                dataOutputStream.writeLong(azVar.d);
                dataOutputStream.writeLong(azVar.e);
                dataOutputStream.writeInt(azVar.f);
                dataOutputStream.writeInt(azVar.g);
                dataOutputStream.writeInt(azVar.h);
                dataOutputStream.writeInt(azVar.i);
                dataOutputStream.writeLong(azVar.j);
                dataOutputStream.flush();
            }
        }

        @Override // com.flurry.sdk.gx
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public az b(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(inputStream) { // from class: com.flurry.sdk.az.a.2
                @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }
            };
            az azVar = new az();
            azVar.a = (FrequencyCapType) Enum.valueOf(FrequencyCapType.class, dataInputStream.readUTF());
            azVar.b = dataInputStream.readUTF();
            azVar.c = dataInputStream.readLong();
            azVar.d = dataInputStream.readLong();
            azVar.e = dataInputStream.readLong();
            azVar.f = dataInputStream.readInt();
            azVar.g = dataInputStream.readInt();
            azVar.h = dataInputStream.readInt();
            azVar.i = dataInputStream.readInt();
            azVar.j = dataInputStream.readLong();
            return azVar;
        }
    }

    private az() {
    }

    public az(FrequencyCapResponseInfo frequencyCapResponseInfo) {
        this.a = frequencyCapResponseInfo.capType;
        this.b = frequencyCapResponseInfo.id;
        this.c = frequencyCapResponseInfo.serveTime;
        this.d = frequencyCapResponseInfo.expirationTime;
        this.e = frequencyCapResponseInfo.streamCapDurationMillis;
        this.f = frequencyCapResponseInfo.capRemaining;
        this.g = frequencyCapResponseInfo.totalCap;
        this.h = frequencyCapResponseInfo.capDurationType;
        this.i = 0;
        this.j = 0L;
    }

    public void a() {
        this.i++;
        this.j = System.currentTimeMillis();
    }

    public FrequencyCapType b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return this.d;
    }

    public long f() {
        return this.e;
    }

    public int g() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public int i() {
        return this.h;
    }

    public int j() {
        return this.i;
    }

    public long k() {
        return this.j;
    }
}
