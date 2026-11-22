package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.NodeApi;

/* JADX INFO: loaded from: classes.dex */
public class bb extends ae.a {
    private final IntentFilter[] axD;
    private DataApi.DataListener ayl;
    private MessageApi.MessageListener aym;
    private NodeApi.NodeListener ayn;

    private bb(DataApi.DataListener dataListener, MessageApi.MessageListener messageListener, NodeApi.NodeListener nodeListener, IntentFilter[] intentFilterArr) {
        this.ayl = dataListener;
        this.aym = messageListener;
        this.ayn = nodeListener;
        this.axD = intentFilterArr;
    }

    public static bb a(NodeApi.NodeListener nodeListener) {
        return new bb(null, null, nodeListener, null);
    }

    public static bb b(DataApi.DataListener dataListener, IntentFilter[] intentFilterArr) {
        return new bb(dataListener, null, null, intentFilterArr);
    }

    public static bb b(MessageApi.MessageListener messageListener, IntentFilter[] intentFilterArr) {
        return new bb(null, messageListener, null, intentFilterArr);
    }

    @Override // com.google.android.gms.wearable.internal.ae
    public void a(ai aiVar) {
        if (this.aym != null) {
            this.aym.onMessageReceived(aiVar);
        }
    }

    @Override // com.google.android.gms.wearable.internal.ae
    public void a(al alVar) {
        if (this.ayn != null) {
            this.ayn.onPeerConnected(alVar);
        }
    }

    @Override // com.google.android.gms.wearable.internal.ae
    public void aa(DataHolder dataHolder) {
        if (this.ayl == null) {
            dataHolder.close();
            return;
        }
        try {
            this.ayl.onDataChanged(new DataEventBuffer(dataHolder));
        } finally {
            dataHolder.close();
        }
    }

    @Override // com.google.android.gms.wearable.internal.ae
    public void b(al alVar) {
        if (this.ayn != null) {
            this.ayn.onPeerDisconnected(alVar);
        }
    }

    public void clear() {
        this.ayl = null;
        this.aym = null;
        this.ayn = null;
    }

    public IntentFilter[] rs() {
        return this.axD;
    }
}
