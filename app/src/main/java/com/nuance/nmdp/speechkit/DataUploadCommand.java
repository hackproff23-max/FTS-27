package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;

/* JADX INFO: loaded from: classes.dex */
public interface DataUploadCommand {

    public interface Listener {
        void onError(DataUploadCommand dataUploadCommand, SpeechError speechError);

        void onResults(DataUploadCommand dataUploadCommand, DataUploadResult dataUploadResult);
    }

    void addParam(String str, PdxValue.Dictionary dictionary);

    void addParam(String str, PdxValue.String string);

    void cancel();

    void setListener(Listener listener);

    void start();
}
