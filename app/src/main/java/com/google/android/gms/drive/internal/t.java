package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes.dex */
public class t extends y implements DriveFile {

    private static class a implements DriveFile.DownloadProgressListener {
        private final com.google.android.gms.common.api.d<DriveFile.DownloadProgressListener> Qe;

        public a(com.google.android.gms.common.api.d<DriveFile.DownloadProgressListener> dVar) {
            this.Qe = dVar;
        }

        @Override // com.google.android.gms.drive.DriveFile.DownloadProgressListener
        public void onProgress(final long bytesDownloaded, final long bytesExpected) {
            this.Qe.a(new com.google.android.gms.common.api.d.b<DriveFile.DownloadProgressListener>() { // from class: com.google.android.gms.drive.internal.t.a.1
                @Override // com.google.android.gms.common.api.d.b
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void c(DriveFile.DownloadProgressListener downloadProgressListener) {
                    downloadProgressListener.onProgress(bytesDownloaded, bytesExpected);
                }

                @Override // com.google.android.gms.common.api.d.b
                public void gG() {
                }
            });
        }
    }

    public t(DriveId driveId) {
        super(driveId);
    }

    private static DriveFile.DownloadProgressListener a(GoogleApiClient googleApiClient, DriveFile.DownloadProgressListener downloadProgressListener) {
        if (downloadProgressListener == null) {
            return null;
        }
        return new a(googleApiClient.d(downloadProgressListener));
    }

    @Override // com.google.android.gms.drive.DriveFile
    public PendingResult<DriveApi.DriveContentsResult> open(GoogleApiClient apiClient, final int mode, DriveFile.DownloadProgressListener listener) {
        if (mode != 268435456 && mode != 536870912 && mode != 805306368) {
            throw new IllegalArgumentException("Invalid mode provided.");
        }
        final DriveFile.DownloadProgressListener downloadProgressListenerA = a(apiClient, listener);
        return apiClient.a(new p.b(apiClient) { // from class: com.google.android.gms.drive.internal.t.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new OpenContentsRequest(t.this.getDriveId(), mode, 0), new az(this, downloadProgressListenerA));
            }
        });
    }
}
