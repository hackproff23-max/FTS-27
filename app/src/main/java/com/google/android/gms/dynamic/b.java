package com.google.android.gms.dynamic;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class b extends c.a {
    private Fragment TG;

    private b(Fragment fragment) {
        this.TG = fragment;
    }

    public static b a(Fragment fragment) {
        if (fragment != null) {
            return new b(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.c
    public void d(d dVar) {
        this.TG.registerForContextMenu((View) e.f(dVar));
    }

    @Override // com.google.android.gms.dynamic.c
    public void e(d dVar) {
        this.TG.unregisterForContextMenu((View) e.f(dVar));
    }

    @Override // com.google.android.gms.dynamic.c
    public Bundle getArguments() {
        return this.TG.getArguments();
    }

    @Override // com.google.android.gms.dynamic.c
    public int getId() {
        return this.TG.getId();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean getRetainInstance() {
        return this.TG.getRetainInstance();
    }

    @Override // com.google.android.gms.dynamic.c
    public String getTag() {
        return this.TG.getTag();
    }

    @Override // com.google.android.gms.dynamic.c
    public int getTargetRequestCode() {
        return this.TG.getTargetRequestCode();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean getUserVisibleHint() {
        return this.TG.getUserVisibleHint();
    }

    @Override // com.google.android.gms.dynamic.c
    public d getView() {
        return e.k(this.TG.getView());
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isAdded() {
        return this.TG.isAdded();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isDetached() {
        return this.TG.isDetached();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isHidden() {
        return this.TG.isHidden();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isInLayout() {
        return this.TG.isInLayout();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isRemoving() {
        return this.TG.isRemoving();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isResumed() {
        return this.TG.isResumed();
    }

    @Override // com.google.android.gms.dynamic.c
    public boolean isVisible() {
        return this.TG.isVisible();
    }

    @Override // com.google.android.gms.dynamic.c
    public d jf() {
        return e.k(this.TG.getActivity());
    }

    @Override // com.google.android.gms.dynamic.c
    public c jg() {
        return a(this.TG.getParentFragment());
    }

    @Override // com.google.android.gms.dynamic.c
    public d jh() {
        return e.k(this.TG.getResources());
    }

    @Override // com.google.android.gms.dynamic.c
    public c ji() {
        return a(this.TG.getTargetFragment());
    }

    @Override // com.google.android.gms.dynamic.c
    public void setHasOptionsMenu(boolean hasMenu) {
        this.TG.setHasOptionsMenu(hasMenu);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setMenuVisibility(boolean menuVisible) {
        this.TG.setMenuVisibility(menuVisible);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setRetainInstance(boolean retain) {
        this.TG.setRetainInstance(retain);
    }

    @Override // com.google.android.gms.dynamic.c
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.TG.setUserVisibleHint(isVisibleToUser);
    }

    @Override // com.google.android.gms.dynamic.c
    public void startActivity(Intent intent) {
        this.TG.startActivity(intent);
    }

    @Override // com.google.android.gms.dynamic.c
    public void startActivityForResult(Intent intent, int requestCode) {
        this.TG.startActivityForResult(intent, requestCode);
    }
}
