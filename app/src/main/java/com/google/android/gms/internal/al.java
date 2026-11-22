package com.google.android.gms.internal;

import android.support.v4.widget.ExploreByTouchHelper;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@ey
public class al {
    private final Object mH = new Object();
    private List<ak> nA = new LinkedList();
    private int nz;

    public boolean a(ak akVar) {
        boolean z;
        synchronized (this.mH) {
            z = this.nA.contains(akVar);
        }
        return z;
    }

    public ak aZ() {
        int i;
        ak akVar;
        ak akVar2 = null;
        synchronized (this.mH) {
            if (this.nA.size() == 0) {
                gr.S("Queue empty");
                return null;
            }
            if (this.nA.size() < 2) {
                ak akVar3 = this.nA.get(0);
                akVar3.aU();
                return akVar3;
            }
            int i2 = ExploreByTouchHelper.INVALID_ID;
            for (ak akVar4 : this.nA) {
                int score = akVar4.getScore();
                if (score > i2) {
                    akVar = akVar4;
                    i = score;
                } else {
                    i = i2;
                    akVar = akVar2;
                }
                i2 = i;
                akVar2 = akVar;
            }
            this.nA.remove(akVar2);
            return akVar2;
        }
    }

    public boolean b(ak akVar) {
        boolean z;
        synchronized (this.mH) {
            for (ak akVar2 : this.nA) {
                if (akVar != akVar2 && akVar2.aT().equals(akVar.aT())) {
                    this.nA.remove(akVar);
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public void c(ak akVar) {
        synchronized (this.mH) {
            if (this.nA.size() >= 10) {
                gr.S("Queue is full, current size = " + this.nA.size());
                this.nA.remove(0);
            }
            int i = this.nz;
            this.nz = i + 1;
            akVar.c(i);
            this.nA.add(akVar);
        }
    }
}
