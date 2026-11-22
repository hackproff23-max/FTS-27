package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.internal.GamesLog;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.lh;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes.dex */
public final class SnapshotContentsEntity implements SafeParcelable, SnapshotContents {
    private final int CK;
    private Contents PW;
    private static final Object aeY = new Object();
    public static final SnapshotContentsEntityCreator CREATOR = new SnapshotContentsEntityCreator();

    SnapshotContentsEntity(int versionCode, Contents contents) {
        this.CK = versionCode;
        this.PW = contents;
    }

    public SnapshotContentsEntity(Contents contents) {
        this(1, contents);
    }

    private boolean a(int i, byte[] bArr, int i2, int i3, boolean z) {
        jx.a(!isClosed(), "Must provide a previously opened SnapshotContents");
        synchronized (aeY) {
            FileOutputStream fileOutputStream = new FileOutputStream(this.PW.getParcelFileDescriptor().getFileDescriptor());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            try {
                FileChannel channel = fileOutputStream.getChannel();
                channel.position(i);
                bufferedOutputStream.write(bArr, i2, i3);
                if (z) {
                    channel.truncate(bArr.length);
                }
                bufferedOutputStream.flush();
            } catch (IOException e) {
                GamesLog.a("SnapshotContentsEntity", "Failed to write snapshot data", e);
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public void close() {
        this.PW = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public ParcelFileDescriptor getParcelFileDescriptor() {
        jx.a(!isClosed(), "Cannot mutate closed contents!");
        return this.PW.getParcelFileDescriptor();
    }

    public int getVersionCode() {
        return this.CK;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public Contents ir() {
        return this.PW;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public boolean isClosed() {
        return this.PW == null;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public boolean modifyBytes(int dstOffset, byte[] content, int srcOffset, int count) {
        return a(dstOffset, content, srcOffset, content.length, false);
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public byte[] readFully() throws IOException {
        byte[] bArrA;
        jx.a(isClosed() ? false : true, "Must provide a previously opened Snapshot");
        synchronized (aeY) {
            FileInputStream fileInputStream = new FileInputStream(this.PW.getParcelFileDescriptor().getFileDescriptor());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            try {
                fileInputStream.getChannel().position(0L);
                bArrA = lh.a(bufferedInputStream, false);
                fileInputStream.getChannel().position(0L);
            } catch (IOException e) {
                GamesLog.b("SnapshotContentsEntity", "Failed to read snapshot data", e);
                throw e;
            }
        }
        return bArrA;
    }

    @Override // com.google.android.gms.games.snapshot.SnapshotContents
    public boolean writeBytes(byte[] content) {
        return a(0, content, 0, content.length, true);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        SnapshotContentsEntityCreator.a(this, out, flags);
    }
}
