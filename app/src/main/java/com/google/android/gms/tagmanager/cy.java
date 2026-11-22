package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes.dex */
class cy extends cx {
    private static cy atJ;
    private static final Object yT = new Object();
    private at atA;
    private volatile ar atB;
    private bo atH;
    private Context atz;
    private Handler handler;
    private int atC = 1800000;
    private boolean atD = true;
    private boolean atE = false;
    private boolean connected = true;
    private boolean atF = true;
    private au atG = new au() { // from class: com.google.android.gms.tagmanager.cy.1
        @Override // com.google.android.gms.tagmanager.au
        public void B(boolean z) {
            cy.this.a(z, cy.this.connected);
        }
    };
    private boolean atI = false;

    private cy() {
    }

    private void ev() {
        this.atH = new bo(this);
        this.atH.z(this.atz);
    }

    private void ew() {
        this.handler = new Handler(this.atz.getMainLooper(), new Handler.Callback() { // from class: com.google.android.gms.tagmanager.cy.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (1 == msg.what && cy.yT.equals(msg.obj)) {
                    cy.this.dispatch();
                    if (cy.this.atC > 0 && !cy.this.atI) {
                        cy.this.handler.sendMessageDelayed(cy.this.handler.obtainMessage(1, cy.yT), cy.this.atC);
                    }
                }
                return true;
            }
        });
        if (this.atC > 0) {
            this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yT), this.atC);
        }
    }

    public static cy qN() {
        if (atJ == null) {
            atJ = new cy();
        }
        return atJ;
    }

    @Override // com.google.android.gms.tagmanager.cx
    synchronized void C(boolean z) {
        a(this.atI, z);
    }

    synchronized void a(Context context, ar arVar) {
        if (this.atz == null) {
            this.atz = context.getApplicationContext();
            if (this.atB == null) {
                this.atB = arVar;
            }
        }
    }

    synchronized void a(boolean z, boolean z2) {
        if (this.atI != z || this.connected != z2) {
            if ((z || !z2) && this.atC > 0) {
                this.handler.removeMessages(1, yT);
            }
            if (!z && z2 && this.atC > 0) {
                this.handler.sendMessageDelayed(this.handler.obtainMessage(1, yT), this.atC);
            }
            bh.V("PowerSaveMode " + ((z || !z2) ? "initiated." : "terminated."));
            this.atI = z;
            this.connected = z2;
        }
    }

    @Override // com.google.android.gms.tagmanager.cx
    public synchronized void dispatch() {
        if (this.atE) {
            this.atB.b(new Runnable() { // from class: com.google.android.gms.tagmanager.cy.3
                @Override // java.lang.Runnable
                public void run() {
                    cy.this.atA.dispatch();
                }
            });
        } else {
            bh.V("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.atD = true;
        }
    }

    @Override // com.google.android.gms.tagmanager.cx
    synchronized void ey() {
        if (!this.atI && this.connected && this.atC > 0) {
            this.handler.removeMessages(1, yT);
            this.handler.sendMessage(this.handler.obtainMessage(1, yT));
        }
    }

    synchronized at qO() {
        if (this.atA == null) {
            if (this.atz == null) {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
            this.atA = new cb(this.atG, this.atz);
        }
        if (this.handler == null) {
            ew();
        }
        this.atE = true;
        if (this.atD) {
            dispatch();
            this.atD = false;
        }
        if (this.atH == null && this.atF) {
            ev();
        }
        return this.atA;
    }
}
