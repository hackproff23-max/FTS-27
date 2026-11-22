package com.google.android.gms.common.data;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class g<T> extends DataBuffer<T> {
    private boolean Lr;
    private ArrayList<Integer> Ls;

    protected g(DataHolder dataHolder) {
        super(dataHolder);
        this.Lr = false;
    }

    private void hb() {
        synchronized (this) {
            if (!this.Lr) {
                int count = this.JG.getCount();
                this.Ls = new ArrayList<>();
                if (count > 0) {
                    this.Ls.add(0);
                    String strHa = ha();
                    String strC = this.JG.c(strHa, 0, this.JG.au(0));
                    int i = 1;
                    while (i < count) {
                        int iAu = this.JG.au(i);
                        String strC2 = this.JG.c(strHa, i, iAu);
                        if (strC2 == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + strHa + ", at row: " + i + ", for window: " + iAu);
                        }
                        if (strC2.equals(strC)) {
                            strC2 = strC;
                        } else {
                            this.Ls.add(Integer.valueOf(i));
                        }
                        i++;
                        strC = strC2;
                    }
                }
                this.Lr = true;
            }
        }
    }

    int ax(int i) {
        if (i < 0 || i >= this.Ls.size()) {
            throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
        }
        return this.Ls.get(i).intValue();
    }

    protected int ay(int i) {
        if (i < 0 || i == this.Ls.size()) {
            return 0;
        }
        int count = i == this.Ls.size() + (-1) ? this.JG.getCount() - this.Ls.get(i).intValue() : this.Ls.get(i + 1).intValue() - this.Ls.get(i).intValue();
        if (count != 1) {
            return count;
        }
        int iAx = ax(i);
        int iAu = this.JG.au(iAx);
        String strHc = hc();
        if (strHc == null || this.JG.c(strHc, iAx, iAu) != null) {
            return count;
        }
        return 0;
    }

    protected abstract T f(int i, int i2);

    @Override // com.google.android.gms.common.data.DataBuffer
    public final T get(int position) {
        hb();
        return f(ax(position), ay(position));
    }

    @Override // com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        hb();
        return this.Ls.size();
    }

    protected abstract String ha();

    protected String hc() {
        return null;
    }
}
