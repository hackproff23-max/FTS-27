package com.mopub.mobileads.factories;

import com.mopub.mobileads.VastVideoDownloadTask;

/* JADX INFO: loaded from: classes.dex */
public class VastVideoDownloadTaskFactory {
    private static VastVideoDownloadTaskFactory instance = new VastVideoDownloadTaskFactory();

    @Deprecated
    public static void setInstance(VastVideoDownloadTaskFactory factory) {
        instance = factory;
    }

    public static VastVideoDownloadTask create(VastVideoDownloadTask.VastVideoDownloadTaskListener vastVideoDownloadTaskListener) {
        return instance.internalCreate(vastVideoDownloadTaskListener);
    }

    protected VastVideoDownloadTask internalCreate(VastVideoDownloadTask.VastVideoDownloadTaskListener vastVideoDownloadTaskListener) {
        return new VastVideoDownloadTask(vastVideoDownloadTaskListener);
    }
}
