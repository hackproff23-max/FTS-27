package com.mopub.volley.toolbox;

import com.mopub.volley.AuthFailureError;

/* JADX INFO: loaded from: classes.dex */
public interface Authenticator {
    String getAuthToken() throws AuthFailureError;

    void invalidateAuthToken(String str);
}
