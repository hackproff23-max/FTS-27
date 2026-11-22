package com.nuance.nmdp.speechkit.util.pdx;

import com.applovin.sdk.AppLovinTargetingData;
import com.nuance.nmdp.speechkit.recognitionresult.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class PdxValue {
    public static final int TYPE_BYTES = 4;
    public static final int TYPE_DICT = 2;
    public static final int TYPE_INT = 1;
    public static final int TYPE_SEQ = 3;
    public static final int TYPE_SEQ_CHUNK = 6;
    public static final int TYPE_SEQ_END = 7;
    public static final int TYPE_SEQ_START = 5;
    public static final int TYPE_STRING = 0;
    private final int a;

    public static final class Bytes extends PdxValue {
        private final byte[] a;

        public Bytes(byte[] bArr) {
            super(4);
            this.a = bArr;
        }

        public final byte[] get() {
            return this.a;
        }

        @Override // com.nuance.nmdp.speechkit.util.pdx.PdxValue
        public final java.lang.String toString(java.lang.String str) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE};
            StringBuilder sb = new StringBuilder();
            sb.append("0x");
            for (byte b : this.a) {
                sb.append(cArr[(byte) ((b >> 4) & 15)]);
                sb.append(cArr[(byte) (b & 15)]);
            }
            return sb.toString();
        }
    }

    public static class Dictionary extends DictionaryBase {
        public Dictionary() {
            this(null);
        }

        public Dictionary(Map<java.lang.String, PdxValue> map) {
            super(map, 2);
        }
    }

    public static abstract class DictionaryBase extends PdxValue {
        private final Map<java.lang.String, PdxValue> a;

        DictionaryBase(Map<java.lang.String, PdxValue> map, int i) {
            super(i);
            this.a = new HashMap();
            if (map != null) {
                this.a.putAll(map);
            }
        }

        private boolean a(java.lang.String str, Object obj) {
            if (str != null && obj != null) {
                return true;
            }
            b.b(this, "ignore this put action since either the key or the value is null: key[" + str + "] value[" + obj + "]");
            return false;
        }

        public PdxValue get(java.lang.String str) {
            return this.a.get(str);
        }

        public Set<Map.Entry<java.lang.String, PdxValue>> getEntries() {
            return this.a.entrySet();
        }

        public void put(java.lang.String str, int i) {
            if (a(str, new Integer(i))) {
                this.a.put(str, new Integer(i));
            }
        }

        public void put(java.lang.String str, PdxValue pdxValue) {
            if (a(str, pdxValue)) {
                this.a.put(str, pdxValue);
            }
        }

        public void put(java.lang.String str, java.lang.String str2) {
            if (a(str, str2)) {
                this.a.put(str, new String(str2));
            }
        }

        public void put(java.lang.String str, byte[] bArr) {
            if (a(str, bArr)) {
                this.a.put(str, new Bytes(bArr));
            }
        }

        @Override // com.nuance.nmdp.speechkit.util.pdx.PdxValue
        public java.lang.String toString(java.lang.String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            java.lang.String str2 = str + "  ";
            for (Map.Entry<java.lang.String, PdxValue> entry : this.a.entrySet()) {
                sb.append(str2);
                sb.append(entry.getKey());
                sb.append(" : ");
                sb.append(entry.getValue().toString(str2 + "  "));
                sb.append(",\n");
            }
            sb.append(str);
            sb.append("}");
            return sb.toString();
        }
    }

    public static final class Integer extends PdxValue {
        private final int a;

        public Integer(int i) {
            super(1);
            this.a = i;
        }

        public final int get() {
            return this.a;
        }

        @Override // com.nuance.nmdp.speechkit.util.pdx.PdxValue
        public final java.lang.String toString(java.lang.String str) {
            return java.lang.Integer.toString(this.a);
        }
    }

    public static class SeqenceChunk extends DictionaryBase {
        public SeqenceChunk() {
            this(null);
        }

        public SeqenceChunk(Map<java.lang.String, PdxValue> map) {
            super(map, 6);
        }
    }

    public static class SeqenceEnd extends DictionaryBase {
        public SeqenceEnd() {
            this(null);
        }

        public SeqenceEnd(Map<java.lang.String, PdxValue> map) {
            super(map, 7);
        }
    }

    public static class SeqenceStart extends DictionaryBase {
        public SeqenceStart() {
            this(null);
        }

        public SeqenceStart(Map<java.lang.String, PdxValue> map) {
            super(map, 5);
        }
    }

    public static final class Sequence extends PdxValue {
        private final List<PdxValue> a;

        public Sequence() {
            this(null);
        }

        public Sequence(List<PdxValue> list) {
            super(3);
            this.a = new ArrayList();
            if (list != null) {
                this.a.addAll(list);
            }
        }

        private boolean a(Object obj) {
            if (obj != null) {
                return true;
            }
            b.b(this, "ignore this add action since the value is null: value[" + obj + "]");
            return false;
        }

        public final void add(int i) {
            this.a.add(new Integer(i));
        }

        public final void add(PdxValue pdxValue) {
            if (a(pdxValue)) {
                this.a.add(pdxValue);
            }
        }

        public final void add(java.lang.String str) {
            if (a(str)) {
                this.a.add(new String(str));
            }
        }

        public final void add(byte[] bArr) {
            if (a(bArr)) {
                this.a.add(new Bytes(bArr));
            }
        }

        public final PdxValue get(int i) {
            return this.a.get(i);
        }

        public final List<PdxValue> getValues() {
            return this.a;
        }

        public final int size() {
            return this.a.size();
        }

        @Override // com.nuance.nmdp.speechkit.util.pdx.PdxValue
        public final java.lang.String toString(java.lang.String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            java.lang.String str2 = str + "  ";
            for (PdxValue pdxValue : this.a) {
                sb.append(str2);
                sb.append(pdxValue.toString(str2));
                sb.append(",\n");
            }
            sb.append(str);
            sb.append("]");
            return sb.toString();
        }
    }

    public static final class String extends PdxValue {
        private final java.lang.String a;

        public String(java.lang.String str) {
            super(0);
            this.a = str;
        }

        public final java.lang.String get() {
            return this.a;
        }

        @Override // com.nuance.nmdp.speechkit.util.pdx.PdxValue
        public final java.lang.String toString(java.lang.String str) {
            return this.a.replace("\n", "\n" + str);
        }
    }

    PdxValue(int i) {
        this.a = i;
    }

    public final int getType() {
        return this.a;
    }

    public abstract java.lang.String toString(java.lang.String str);
}
