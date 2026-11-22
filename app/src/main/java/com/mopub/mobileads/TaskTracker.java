package com.mopub.mobileads;

/* JADX INFO: loaded from: classes.dex */
public class TaskTracker {
    private long mCurrentTaskId = -1;
    private long mLastCompletedTaskId;

    public long getCurrentTaskId() {
        return this.mCurrentTaskId;
    }

    public void newTaskStarted() {
        this.mCurrentTaskId++;
    }

    public void markTaskCompleted(long taskId) {
        if (taskId > this.mLastCompletedTaskId) {
            this.mLastCompletedTaskId = taskId;
        }
    }

    public boolean isMostCurrentTask(long taskId) {
        return taskId >= this.mLastCompletedTaskId;
    }
}
