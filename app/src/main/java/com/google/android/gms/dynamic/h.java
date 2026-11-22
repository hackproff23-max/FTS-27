package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class h extends c.a {
    private Fragment Mx;

    private h(Fragment fragment) {
        this.Mx = fragment;
    }

    public static h a(Fragment fragment) {
        if (fragment != null) {
            return new h(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.c
    public void d(d dVar) {
        this.Mx.registerForContextMenu((View) e.f(dVar));
    }

    @Override // com.google.android.gms.dynamic.c
    public void e(d dVar) {
        this.Mx.unregisterForContextMenu((View) e.f(dVar));
    }

    @Override // com.google.android.gms.dynamic.c
    public Bundle getArguments() {
        return this.Mx.getArguments();
    }

    @Override // com.google.android.gms.dynamic.c
    public int getId() {
        return this.Mx.getId();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean getRetainInstance() {
        return this.Mx.getRetainInstance();
    }

    @Override // com.google.android.gms.dynamic.c
    public String getTag() {
        return this.Mx.getTag();
    }

    @Override // com.google.android.gms.dynamic.c
    public int getTargetRequestCode() {
        return this.Mx.getTargetRequestCode();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean getUserVisibleHint() {
        return this.Mx.getUserVisibleHint();
    }

    @Override // com.google.android.gms.dynamic.c
    public d getView() {
        return e.k(this.Mx.getView());
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isAdded() {
        return this.Mx.isAdded();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isDetached() {
        return this.Mx.isDetached();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isHidden() {
        return this.Mx.isHidden();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isInLayout() {
        return this.Mx.isInLayout();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isRemoving() {
        return this.Mx.isRemoving();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isResumed() {
        return this.Mx.isResumed();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isVisible() {
        return this.Mx.isVisible();
    }

    @Override // com.google.android.gms.dynamic.c
    public d jf() {
        return e.k(this.Mx.getActivity());
    }

    @Override // com.google.android.gms.dynamic.c
    public c jg() {
        return a(this.Mx.getParentFragment());
    }

    @Override // com.google.android.gms.dynamic.c
    public d jh() {
        return e.k(this.Mx.getResources());
    }

    @Override // com.google.android.gms.dynamic.c
    public c ji() {
        return a(this.Mx.getTargetFragment());
    }

    @Override // com.google.android.gms.dynamic.c
    public void setHasOptionsMenu(boolean hasMenu) {
        this.Mx.setHasOptionsMenu(hasMenu);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setMenuVisibility(boolean menuVisible) {
        this.Mx.setMenuVisibility(menuVisible);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setRetainInstance(boolean retain) {
        this.Mx.setRetainInstance(retain);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.Mx.setUserVisibleHint(isVisibleToUser);
    }

    @Override // com.google.android.gms.dynamic.c
    public void startActivity(Intent intent) {
        this.Mx.startActivity(intent);
    }

    @Override // com.google.android.gms.dynamic.c
    public void startActivityForResult(Intent intent, int requestCode) {
        this.Mx.startActivityForResult(intent, requestCode);
    }
}
