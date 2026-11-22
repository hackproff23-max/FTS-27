package com.google.android.gms.common.data;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class DataBufferObserverSet implements DataBufferObserver.Observable, DataBufferObserver {
    private HashSet<DataBufferObserver> KY = new HashSet<>();

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public void addObserver(DataBufferObserver observer) {
        this.KY.add(observer);
    }

    public void clear() {
        this.KY.clear();
    }

    public boolean hasObservers() {
        return !this.KY.isEmpty();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public void onDataChanged() {
        Iterator<DataBufferObserver> it = this.KY.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged();
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeChanged(int position, int count) {
        Iterator<DataBufferObserver> it = this.KY.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeChanged(position, count);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeInserted(int position, int count) {
        Iterator<DataBufferObserver> it = this.KY.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeInserted(position, count);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeMoved(int fromPosition, int toPosition, int count) {
        Iterator<DataBufferObserver> it = this.KY.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeMoved(fromPosition, toPosition, count);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver
    public void onDataRangeRemoved(int position, int count) {
        Iterator<DataBufferObserver> it = this.KY.iterator();
        while (it.hasNext()) {
            it.next().onDataRangeRemoved(position, count);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public void removeObserver(DataBufferObserver observer) {
        this.KY.remove(observer);
    }
}
