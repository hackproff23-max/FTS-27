package com.nuance.nmdp.speechkit;

/* JADX INFO: loaded from: classes.dex */
final class v implements SpeechError {
    private final int a;
    private final String b;
    private final String c;

    public v(int i, String str, String str2) {
        String str3;
        this.a = i;
        this.c = str2;
        if (str == null) {
            switch (i) {
                case 1:
                    str3 = "Failed to connect to speech server.";
                    break;
                case 2:
                    str3 = "Please retry your query.";
                    break;
                case 3:
                case 4:
                default:
                    str3 = "An error occurred.";
                    break;
                case 5:
                    str3 = "Query cancelled.";
                    break;
            }
        } else {
            str3 = str;
        }
        this.b = str3;
    }

    @Override // com.nuance.nmdp.speechkit.SpeechError
    public final int getErrorCode() {
        return this.a;
    }

    @Override // com.nuance.nmdp.speechkit.SpeechError
    public final String getErrorDetail() {
        return this.b;
    }

    @Override // com.nuance.nmdp.speechkit.SpeechError
    public final String getSuggestion() {
        return this.c;
    }
}
