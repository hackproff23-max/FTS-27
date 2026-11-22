package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.recognitionresult.DetailedResult;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Recognition {

    public interface Result {
        int getScore();

        String getText();
    }

    List<DetailedResult> getDetailedResults();

    Result getResult(int i);

    int getResultCount();

    String getSuggestion();
}
