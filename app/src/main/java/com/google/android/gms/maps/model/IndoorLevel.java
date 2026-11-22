package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public final class IndoorLevel {
    private final com.google.android.gms.maps.model.internal.k amj;

    public IndoorLevel(com.google.android.gms.maps.model.internal.k delegate) {
        this.amj = (com.google.android.gms.maps.model.internal.k) jx.i(delegate);
    }

    public void activate() {
        try {
            this.amj.activate();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object other) {
        if (!(other instanceof IndoorLevel)) {
            return false;
        }
        try {
            return this.amj.a(((IndoorLevel) other).amj);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getName() {
        try {
            return this.amj.getName();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getShortName() {
        try {
            return this.amj.getShortName();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.amj.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
