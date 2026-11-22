package com.millennialmedia.google.gson;

import com.millennialmedia.google.gson.reflect.TypeToken;

/* JADX INFO: loaded from: classes.dex */
public interface TypeAdapterFactory {
    <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken);
}
