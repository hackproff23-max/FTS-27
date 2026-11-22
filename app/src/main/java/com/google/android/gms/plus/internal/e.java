package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.jd;
import com.google.android.gms.internal.jj;
import com.google.android.gms.internal.jl;
import com.google.android.gms.internal.jr;
import com.google.android.gms.internal.jt;
import com.google.android.gms.internal.ky;
import com.google.android.gms.internal.lm;
import com.google.android.gms.internal.pf;
import com.google.android.gms.internal.pi;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class e extends jl<com.google.android.gms.plus.internal.d> {
    private Person anG;
    private final com.google.android.gms.plus.internal.h anH;

    final class a extends com.google.android.gms.plus.internal.a {
        private final BaseImplementation.b<Status> anI;

        public a(BaseImplementation.b<Status> bVar) {
            this.anI = bVar;
        }

        @Override // com.google.android.gms.plus.internal.a, com.google.android.gms.plus.internal.b
        public void aA(Status status) {
            e.this.a(e.this.new d(this.anI, status));
        }
    }

    final class b extends com.google.android.gms.plus.internal.a {
        private final BaseImplementation.b<Moments.LoadMomentsResult> anI;

        public b(BaseImplementation.b<Moments.LoadMomentsResult> bVar) {
            this.anI = bVar;
        }

        @Override // com.google.android.gms.plus.internal.a, com.google.android.gms.plus.internal.b
        public void a(DataHolder dataHolder, String str, String str2) {
            DataHolder dataHolder2;
            Status status = new Status(dataHolder.getStatusCode(), null, dataHolder.gV() != null ? (PendingIntent) dataHolder.gV().getParcelable("pendingIntent") : null);
            if (status.isSuccess() || dataHolder == null) {
                dataHolder2 = dataHolder;
            } else {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder2 = null;
            }
            e.this.a(e.this.new c(this.anI, status, dataHolder2, str, str2));
        }
    }

    final class c extends jl<com.google.android.gms.plus.internal.d>.d<BaseImplementation.b<Moments.LoadMomentsResult>> implements Moments.LoadMomentsResult {
        private final Status Eb;
        private final String OB;
        private final String anK;
        private MomentBuffer anL;

        public c(BaseImplementation.b<Moments.LoadMomentsResult> bVar, Status status, DataHolder dataHolder, String str, String str2) {
            super(bVar, dataHolder);
            this.Eb = status;
            this.OB = str;
            this.anK = str2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(BaseImplementation.b<Moments.LoadMomentsResult> bVar, DataHolder dataHolder) {
            this.anL = dataHolder != null ? new MomentBuffer(dataHolder) : null;
            bVar.b(this);
        }

        @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
        public MomentBuffer getMomentBuffer() {
            return this.anL;
        }

        @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
        public String getNextPageToken() {
            return this.OB;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.plus.Moments.LoadMomentsResult
        public String getUpdated() {
            return this.anK;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            if (this.anL != null) {
                this.anL.close();
            }
        }
    }

    final class d extends jl<com.google.android.gms.plus.internal.d>.b<BaseImplementation.b<Status>> {
        private final Status Eb;

        public d(BaseImplementation.b<Status> bVar, Status status) {
            super(bVar);
            this.Eb = status;
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.b
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public void g(BaseImplementation.b<Status> bVar) {
            if (bVar != null) {
                bVar.b(this.Eb);
            }
        }
    }

    /* JADX INFO: renamed from: com.google.android.gms.plus.internal.e$e, reason: collision with other inner class name */
    final class BinderC0175e extends com.google.android.gms.plus.internal.a {
        private final BaseImplementation.b<People.LoadPeopleResult> anI;

        public BinderC0175e(BaseImplementation.b<People.LoadPeopleResult> bVar) {
            this.anI = bVar;
        }

        @Override // com.google.android.gms.plus.internal.a, com.google.android.gms.plus.internal.b
        public void a(DataHolder dataHolder, String str) {
            DataHolder dataHolder2;
            Status status = new Status(dataHolder.getStatusCode(), null, dataHolder.gV() != null ? (PendingIntent) dataHolder.gV().getParcelable("pendingIntent") : null);
            if (status.isSuccess() || dataHolder == null) {
                dataHolder2 = dataHolder;
            } else {
                if (!dataHolder.isClosed()) {
                    dataHolder.close();
                }
                dataHolder2 = null;
            }
            e.this.a(e.this.new f(this.anI, status, dataHolder2, str));
        }
    }

    final class f extends jl<com.google.android.gms.plus.internal.d>.d<BaseImplementation.b<People.LoadPeopleResult>> implements People.LoadPeopleResult {
        private final Status Eb;
        private final String OB;
        private PersonBuffer anM;

        public f(BaseImplementation.b<People.LoadPeopleResult> bVar, Status status, DataHolder dataHolder, String str) {
            super(bVar, dataHolder);
            this.Eb = status;
            this.OB = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(BaseImplementation.b<People.LoadPeopleResult> bVar, DataHolder dataHolder) {
            this.anM = dataHolder != null ? new PersonBuffer(dataHolder) : null;
            bVar.b(this);
        }

        @Override // com.google.android.gms.plus.People.LoadPeopleResult
        public String getNextPageToken() {
            return this.OB;
        }

        @Override // com.google.android.gms.plus.People.LoadPeopleResult
        public PersonBuffer getPersonBuffer() {
            return this.anM;
        }

        @Override // com.google.android.gms.common.api.Result
        public Status getStatus() {
            return this.Eb;
        }

        @Override // com.google.android.gms.common.api.Releasable
        public void release() {
            if (this.anM != null) {
                this.anM.close();
            }
        }
    }

    final class g extends com.google.android.gms.plus.internal.a {
        private final BaseImplementation.b<Status> anI;

        public g(BaseImplementation.b<Status> bVar) {
            this.anI = bVar;
        }

        @Override // com.google.android.gms.plus.internal.a, com.google.android.gms.plus.internal.b
        public void h(int i, Bundle bundle) {
            e.this.a(e.this.new h(this.anI, new Status(i, null, bundle != null ? (PendingIntent) bundle.getParcelable("pendingIntent") : null)));
        }
    }

    final class h extends jl<com.google.android.gms.plus.internal.d>.b<BaseImplementation.b<Status>> {
        private final Status Eb;

        public h(BaseImplementation.b<Status> bVar, Status status) {
            super(bVar);
            this.Eb = status;
        }

        @Override // com.google.android.gms.internal.jl.b
        protected void hx() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.jl.b
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public void g(BaseImplementation.b<Status> bVar) {
            e.this.disconnect();
            if (bVar != null) {
                bVar.b(this.Eb);
            }
        }
    }

    public e(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, com.google.android.gms.plus.internal.h hVar) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, hVar.oz());
        this.anH = hVar;
    }

    public jr a(BaseImplementation.b<People.LoadPeopleResult> bVar, int i, String str) {
        dS();
        BinderC0175e binderC0175e = new BinderC0175e(bVar);
        try {
            return hw().a(binderC0175e, 1, i, -1, str);
        } catch (RemoteException e) {
            binderC0175e.a(DataHolder.av(8), (String) null);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(int i, IBinder iBinder, Bundle bundle) {
        if (i == 0 && bundle != null && bundle.containsKey("loaded_person")) {
            this.anG = pi.i(bundle.getByteArray("loaded_person"));
        }
        super.a(i, iBinder, bundle);
    }

    public void a(BaseImplementation.b<Moments.LoadMomentsResult> bVar, int i, String str, Uri uri, String str2, String str3) {
        dS();
        b bVar2 = bVar != null ? new b(bVar) : null;
        try {
            hw().a(bVar2, i, str, uri, str2, str3);
        } catch (RemoteException e) {
            bVar2.a(DataHolder.av(8), (String) null, (String) null);
        }
    }

    public void a(BaseImplementation.b<Status> bVar, Moment moment) {
        dS();
        a aVar = bVar != null ? new a(bVar) : null;
        try {
            hw().a(aVar, ky.a((pf) moment));
        } catch (RemoteException e) {
            if (aVar == null) {
                throw new IllegalStateException(e);
            }
            aVar.aA(new Status(8, null, null));
        }
    }

    public void a(BaseImplementation.b<People.LoadPeopleResult> bVar, Collection<String> collection) {
        dS();
        BinderC0175e binderC0175e = new BinderC0175e(bVar);
        try {
            hw().a(binderC0175e, new ArrayList(collection));
        } catch (RemoteException e) {
            binderC0175e.a(DataHolder.av(8), (String) null);
        }
    }

    @Override // com.google.android.gms.internal.jl
    protected void a(jt jtVar, jl.e eVar) throws RemoteException {
        Bundle bundleOH = this.anH.oH();
        bundleOH.putStringArray("request_visible_actions", this.anH.oA());
        bundleOH.putString("auth_package", this.anH.oC());
        jtVar.a(eVar, new jj(2).aX(this.anH.oD()).a(jd.aT(this.anH.getAccountName())).a(lm.d(hv())).e(bundleOH));
    }

    @Override // com.google.android.gms.internal.jl
    protected String bK() {
        return "com.google.android.gms.plus.service.START";
    }

    @Override // com.google.android.gms.internal.jl
    protected String bL() {
        return "com.google.android.gms.plus.internal.IPlusService";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.jl
    /* JADX INFO: renamed from: bN, reason: merged with bridge method [inline-methods] */
    public com.google.android.gms.plus.internal.d l(IBinder iBinder) {
        return com.google.android.gms.plus.internal.d.a.bM(iBinder);
    }

    public boolean cj(String str) {
        return Arrays.asList(hv()).contains(str);
    }

    public void clearDefaultAccount() {
        dS();
        try {
            this.anG = null;
            hw().clearDefaultAccount();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public void d(BaseImplementation.b<People.LoadPeopleResult> bVar, String[] strArr) {
        a(bVar, Arrays.asList(strArr));
    }

    public String getAccountName() {
        dS();
        try {
            return hw().getAccountName();
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }

    public Person getCurrentPerson() {
        dS();
        return this.anG;
    }

    public void j(BaseImplementation.b<Moments.LoadMomentsResult> bVar) {
        a(bVar, 20, null, null, null, "me");
    }

    public void k(BaseImplementation.b<People.LoadPeopleResult> bVar) {
        dS();
        BinderC0175e binderC0175e = new BinderC0175e(bVar);
        try {
            hw().a(binderC0175e, 2, 1, -1, null);
        } catch (RemoteException e) {
            binderC0175e.a(DataHolder.av(8), (String) null);
        }
    }

    public void l(BaseImplementation.b<Status> bVar) {
        dS();
        clearDefaultAccount();
        g gVar = new g(bVar);
        try {
            hw().b(gVar);
        } catch (RemoteException e) {
            gVar.h(8, null);
        }
    }

    public jr q(BaseImplementation.b<People.LoadPeopleResult> bVar, String str) {
        return a(bVar, 0, str);
    }

    public void removeMoment(String momentId) {
        dS();
        try {
            hw().removeMoment(momentId);
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}
