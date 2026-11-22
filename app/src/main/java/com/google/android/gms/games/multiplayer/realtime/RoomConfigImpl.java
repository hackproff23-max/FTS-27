package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.internal.jx;

/* JADX INFO: loaded from: classes.dex */
public final class RoomConfigImpl extends RoomConfig {
    private final String YG;
    private final int adW;
    private final RoomUpdateListener aeh;
    private final RoomStatusUpdateListener aei;
    private final RealTimeMessageReceivedListener aej;
    private final Bundle aem;
    private final boolean aen;
    private final String[] aeo;

    RoomConfigImpl(RoomConfig.Builder builder) {
        this.aeh = builder.aeh;
        this.aei = builder.aei;
        this.aej = builder.aej;
        this.YG = builder.aek;
        this.adW = builder.adW;
        this.aem = builder.aem;
        this.aen = builder.aen;
        this.aeo = (String[]) builder.ael.toArray(new String[builder.ael.size()]);
        if (this.aej == null) {
            jx.a(this.aen, "Must either enable sockets OR specify a message listener");
        }
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public Bundle getAutoMatchCriteria() {
        return this.aem;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public String getInvitationId() {
        return this.YG;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public String[] getInvitedPlayerIds() {
        return this.aeo;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public RealTimeMessageReceivedListener getMessageReceivedListener() {
        return this.aej;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public RoomStatusUpdateListener getRoomStatusUpdateListener() {
        return this.aei;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public RoomUpdateListener getRoomUpdateListener() {
        return this.aeh;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public int getVariant() {
        return this.adW;
    }

    @Override // com.google.android.gms.games.multiplayer.realtime.RoomConfig
    public boolean isSocketEnabled() {
        return this.aen;
    }
}
