package com.google.android.gms.drive.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.CompletionListener;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.internal.jx;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class aa extends ag.a {
    private final int Pm;
    private final com.google.android.gms.drive.events.c Qw;
    private final a Qx;
    private final List<Integer> Qy = new ArrayList();

    private static class a extends Handler {
        private final Context mContext;

        private a(Looper looper, Context context) {
            super(looper);
            this.mContext = context;
        }

        public void a(com.google.android.gms.drive.events.c cVar, DriveEvent driveEvent) {
            sendMessage(obtainMessage(1, new Pair(cVar, driveEvent)));
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Pair pair = (Pair) msg.obj;
                    com.google.android.gms.drive.events.c cVar = (com.google.android.gms.drive.events.c) pair.first;
                    DriveEvent driveEvent = (DriveEvent) pair.second;
                    switch (driveEvent.getType()) {
                        case 1:
                            ((ChangeListener) cVar).onChange((ChangeEvent) driveEvent);
                            break;
                        case 2:
                            ((CompletionListener) cVar).onCompletion((CompletionEvent) driveEvent);
                            break;
                        default:
                            w.o("EventCallback", "Unexpected event: " + driveEvent);
                            break;
                    }
                    break;
                default:
                    w.e(this.mContext, "EventCallback", "Don't know how to handle this event");
                    break;
            }
        }
    }

    public aa(Looper looper, Context context, int i, com.google.android.gms.drive.events.c cVar) {
        this.Pm = i;
        this.Qw = cVar;
        this.Qx = new a(looper, context);
    }

    public void bw(int i) {
        this.Qy.add(Integer.valueOf(i));
    }

    public boolean bx(int i) {
        return this.Qy.contains(Integer.valueOf(i));
    }

    @Override // com.google.android.gms.drive.internal.ag
    public void c(OnEventResponse onEventResponse) throws RemoteException {
        DriveEvent driveEventIQ = onEventResponse.iQ();
        jx.K(this.Pm == driveEventIQ.getType());
        jx.K(this.Qy.contains(Integer.valueOf(driveEventIQ.getType())));
        this.Qx.a(this.Qw, driveEventIQ);
    }
}
