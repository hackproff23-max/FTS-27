package com.flurry.sdk;

import com.google.android.vending.expansion.downloader.Constants;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public enum hy implements hz {
    IDENTITY { // from class: com.flurry.sdk.hy.1
        @Override // com.flurry.sdk.hz
        public String a(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: com.flurry.sdk.hy.2
        @Override // com.flurry.sdk.hz
        public String a(Field field) {
            return hy.b(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: com.flurry.sdk.hy.3
        @Override // com.flurry.sdk.hz
        public String a(Field field) {
            return hy.b(hy.b(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: com.flurry.sdk.hy.4
        @Override // com.flurry.sdk.hz
        public String a(Field field) {
            return hy.b(field.getName(), "_").toLowerCase();
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: com.flurry.sdk.hy.5
        @Override // com.flurry.sdk.hz
        public String a(Field field) {
            return hy.b(field.getName(), Constants.FILENAME_SEQUENCE_SEPARATOR).toLowerCase();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char cCharAt = str.charAt(0);
        while (i < str.length() - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i++;
            cCharAt = str.charAt(i);
        }
        if (i == str.length()) {
            return sb.toString();
        }
        if (!Character.isUpperCase(cCharAt)) {
            return sb.append(a(Character.toUpperCase(cCharAt), str, i + 1)).toString();
        }
        return str;
    }

    private static String a(char c, String str, int i) {
        return i < str.length() ? c + str.substring(i) : String.valueOf(c);
    }
}
