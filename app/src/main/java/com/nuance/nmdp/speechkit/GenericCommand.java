package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;

/* JADX INFO: loaded from: classes.dex */
public interface GenericCommand {

    public interface Listener {
        void onComplete(GenericCommand genericCommand, GenericResult genericResult, SpeechError speechError);
    }

    void addParam(String str, PdxValue.Dictionary dictionary);

    void addParam(String str, PdxValue.String string);

    void cancel();

    void start();
}
