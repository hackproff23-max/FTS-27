package com.google.android.gms.games.internal;

import android.net.LocalSocket;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
final class RealTimeSocketImpl implements RealTimeSocket {
    private ParcelFileDescriptor LG;
    private final LocalSocket ZV;
    private final String Zk;

    RealTimeSocketImpl(LocalSocket localSocket, String participantId) {
        this.ZV = localSocket;
        this.Zk = participantId;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public void close() throws IOException {
        this.ZV.close();
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public InputStream getInputStream() throws IOException {
        return this.ZV.getInputStream();
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public OutputStream getOutputStream() throws IOException {
        return this.ZV.getOutputStream();
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        if (this.LG == null && !isClosed()) {
            Parcel parcelObtain = Parcel.obtain();
            parcelObtain.writeFileDescriptor(this.ZV.getFileDescriptor());
            parcelObtain.setDataPosition(0);
            this.LG = parcelObtain.readFileDescriptor();
        }
        return this.LG;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public boolean isClosed() {
        return (this.ZV.isConnected() || this.ZV.isBound()) ? false : true;
    }
}
