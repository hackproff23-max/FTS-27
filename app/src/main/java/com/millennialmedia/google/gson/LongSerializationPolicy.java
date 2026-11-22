package com.millennialmedia.google.gson;

/* JADX INFO: loaded from: classes.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.millennialmedia.google.gson.LongSerializationPolicy.1
        @Override // com.millennialmedia.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long value) {
            return new JsonPrimitive((Number) value);
        }
    },
    STRING { // from class: com.millennialmedia.google.gson.LongSerializationPolicy.2
        @Override // com.millennialmedia.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long value) {
            return new JsonPrimitive(String.valueOf(value));
        }
    };

    public abstract JsonElement serialize(Long l);
}
