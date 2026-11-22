package com.unity3d.ads.android;

import android.content.DialogInterface;

/* JADX INFO: compiled from: UnityAds.java */
/* JADX INFO: loaded from: classes.dex */
final class a implements DialogInterface.OnClickListener {
    final /* synthetic */ UnityAds a;

    a(UnityAds unityAds) {
        this.a = unityAds;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        UnityAds.j.dismiss();
    }
}
