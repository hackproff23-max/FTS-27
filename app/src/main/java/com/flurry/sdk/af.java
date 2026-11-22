package com.flurry.sdk;

/* JADX INFO: loaded from: classes.dex */
public enum af {
    NOT_STARTED(0),
    IN_PROGRESS(1),
    INCOMPLETE(2),
    COMPLETE(3),
    EVICTED(4),
    ERROR(5);

    private final int g;

    af(int i) {
        this.g = i;
    }
}
