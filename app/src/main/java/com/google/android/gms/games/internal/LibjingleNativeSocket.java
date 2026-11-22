package com.google.android.gms.games.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class LibjingleNativeSocket implements RealTimeSocket {
    private static final String TAG = LibjingleNativeSocket.class.getSimpleName();
    private final ParcelFileDescriptor LG;
    private final InputStream ZO;
    private final OutputStream ZP;

    LibjingleNativeSocket(ParcelFileDescriptor parcelFileDescriptor) {
        this.LG = parcelFileDescriptor;
        this.ZO = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor);
        this.ZP = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public void close() throws IOException {
        this.LG.close();
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public InputStream getInputStream() throws IOException {
        return this.ZO;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public OutputStream getOutputStream() throws IOException {
        return this.ZP;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public ParcelFileDescriptor getParcelFileDescriptor() throws IOException {
        return this.LG;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RealTimeSocket
    public boolean isClosed() {
        try {
            this.ZO.available();
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
