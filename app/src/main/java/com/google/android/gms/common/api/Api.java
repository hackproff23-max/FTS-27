package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.internal.jg;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Api<O extends ApiOptions> {
    private final b<?, O> Jm;
    private final c<?> Jn;
    private final ArrayList<Scope> Jo;

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public interface Optional extends HasOptions, NotRequiredOptions {
        }
    }

    public interface a {
        void connect();

        void disconnect();

        boolean isConnected();
    }

    public interface b<T extends a, O> {
        T a(Context context, Looper looper, jg jgVar, O o, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener);

        int getPriority();
    }

    public static final class c<C extends a> {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <C extends a> Api(b<C, O> bVar, c<C> cVar, Scope... impliedScopes) {
        this.Jm = bVar;
        this.Jn = cVar;
        this.Jo = new ArrayList<>(Arrays.asList(impliedScopes));
    }

    public b<?, O> gx() {
        return this.Jm;
    }

    public List<Scope> gy() {
        return this.Jo;
    }

    public c<?> gz() {
        return this.Jn;
    }
}
