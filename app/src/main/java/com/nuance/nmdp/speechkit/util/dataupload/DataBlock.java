package com.nuance.nmdp.speechkit.util.dataupload;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class DataBlock {
    private int a = 0;
    private String b = null;
    private Vector<Data> c = new Vector<>();

    public void addData(Data data) {
        if (data != null) {
            this.c.add(data);
        }
    }

    public void clearDataBlock() {
        this.c.removeAllElements();
    }

    public int getChecksum() {
        int checksum = 0;
        int i = 0;
        while (true) {
            int i2 = checksum;
            if (i >= this.c.size()) {
                return i2;
            }
            Data dataElementAt = this.c.elementAt(i);
            checksum = dataElementAt != null ? dataElementAt.getChecksum() + i2 : i2;
            i++;
        }
    }

    public PdxValue.Dictionary getDataBlockDictionary() {
        PdxValue.Dictionary dictionaryA;
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
        dictionary.put("delete_all", this.a);
        if (this.b != null) {
            dictionary.put("common_user_id", this.b);
        }
        if (!this.c.isEmpty()) {
            PdxValue.Sequence sequence = new PdxValue.Sequence();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.c.size()) {
                    break;
                }
                Data dataElementAt = this.c.elementAt(i2);
                if (dataElementAt != null && (dictionaryA = dataElementAt.a()) != null) {
                    sequence.add(dictionaryA);
                }
                i = i2 + 1;
            }
            dictionary.put("data_list", sequence);
        }
        return dictionary;
    }

    public Vector<Data> getDataList() {
        return this.c;
    }

    public void removeData(Data data) {
        if (data != null) {
            this.c.remove(data);
        }
    }

    public void setDeleteAll(boolean z) {
        this.a = z ? 1 : 0;
    }

    public void setUserId(String str) {
        this.b = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("delete_all:" + this.a + "\n");
        stringBuffer.append("checksum:" + getChecksum() + "\n");
        if (!this.c.isEmpty()) {
            int size = this.c.size();
            stringBuffer.append("data_list: elements " + size + "\n");
            for (int i = 0; i < size; i++) {
                Data dataElementAt = this.c.elementAt(i);
                if (dataElementAt != null) {
                    stringBuffer.append("data: " + i + "\n");
                    stringBuffer.append(dataElementAt.toString());
                }
            }
        }
        return stringBuffer.toString();
    }
}
