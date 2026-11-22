package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

/* JADX INFO: loaded from: classes.dex */
public class x implements DrivePreferencesApi {

    private class a extends com.google.android.gms.drive.internal.c {
        private final BaseImplementation.b<DrivePreferencesApi.FileUploadPreferencesResult> Ea;

        private a(BaseImplementation.b<DrivePreferencesApi.FileUploadPreferencesResult> bVar) {
            this.Ea = bVar;
        }

        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void a(OnDeviceUsagePreferenceResponse onDeviceUsagePreferenceResponse) throws RemoteException {
            this.Ea.b(new b(Status.Kw, onDeviceUsagePreferenceResponse.iN()));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.drive.internal.c, com.google.android.gms.drive.internal.af
        public void n(Status status) throws RemoteException {
            this.Ea.b(new b(status, null));
        }
    }

    private class b implements DrivePreferencesApi.FileUploadPreferencesResult {
        private final Status Eb;
        private final FileUploadPreferences Qs;

        private b(Status status, FileUploadPreferences fileUploadPreferences) {
            this.Eb = status;
            this.Qs = fileUploadPreferences;
        }

        @Override // com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult
        public FileUploadPreferences getFileUploadPreferences() {
            return this.Qs;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }
    }

    private abstract class c extends q<DrivePreferencesApi.FileUploadPreferencesResult> {
        public c(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.common.api.BaseImplementation.AbstractPendingResult
        /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
        public DrivePreferencesApi.FileUploadPreferencesResult c(Status status) {
            return new b(status, null);
        }
    }

    @Override // com.google.android.gms.drive.DrivePreferencesApi
    public PendingResult<DrivePreferencesApi.FileUploadPreferencesResult> getFileUploadPreferences(GoogleApiClient apiClient) {
        return apiClient.a(new c(apiClient) { // from class: com.google.android.gms.drive.internal.x.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().h(new a(this));
            }
        });
    }

    @Override // com.google.android.gms.drive.DrivePreferencesApi
    public PendingResult<Status> setFileUploadPreferences(GoogleApiClient apiClient, FileUploadPreferences fileUploadPreferences) {
        if (!(fileUploadPreferences instanceof FileUploadPreferencesImpl)) {
            throw new IllegalArgumentException("Invalid preference value");
        }
        final FileUploadPreferencesImpl fileUploadPreferencesImpl = (FileUploadPreferencesImpl) fileUploadPreferences;
        return apiClient.b(new q.a(apiClient) { // from class: com.google.android.gms.drive.internal.x.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.BaseImplementation.a
            public void a(r rVar) throws RemoteException {
                rVar.iG().a(new SetFileUploadPreferencesRequest(fileUploadPreferencesImpl), new bg(this));
            }
        });
    }
}
