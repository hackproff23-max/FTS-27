package com.nuance.nmdp.speechkit.util.dataupload;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Data {
    private String a;
    private int b = 0;
    private DataType c;
    private Vector<Action> d;

    public enum DataType {
        CONTACTS,
        CUSTOMWORDS,
        HISTORY
    }

    public Data(String str, DataType dataType) {
        this.a = str;
        if (dataType == DataType.CONTACTS || dataType == DataType.CUSTOMWORDS || dataType == DataType.HISTORY) {
            this.c = dataType;
        } else {
            this.c = DataType.CONTACTS;
        }
        this.d = new Vector<>();
    }

    /* JADX WARN: Code duplicated, block: B:4:0x0010  */
    final PdxValue.Dictionary a() {
        PdxValue.Dictionary dictionaryA;
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
        dictionary.put("id", this.a);
        if (this.c != null) {
            switch (this.c) {
                case CONTACTS:
                    dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "contacts");
                    break;
                case CUSTOMWORDS:
                    dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "custom_words");
                    break;
                case HISTORY:
                    dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "history");
                    break;
                default:
                    dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "contacts");
                    break;
            }
        } else {
            dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, "contacts");
        }
        if (!this.d.isEmpty()) {
            PdxValue.Sequence sequence = new PdxValue.Sequence();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.d.size()) {
                    Action actionElementAt = this.d.elementAt(i2);
                    if (actionElementAt != null && (dictionaryA = actionElementAt.a()) != null) {
                        sequence.add(dictionaryA);
                    }
                    i = i2 + 1;
                } else {
                    dictionary.put("actions", sequence);
                }
            }
        }
        return dictionary;
    }

    public void addAction(Action action) {
        if (action != null) {
            this.d.add(action);
        }
    }

    public void clearActions() {
        this.d.removeAllElements();
    }

    public void clearData() {
        this.a = null;
        this.b = 0;
        this.c = null;
        this.d.removeAllElements();
    }

    public int getChecksum() {
        int checksum = 0;
        int i = 0;
        while (true) {
            int i2 = checksum;
            if (i >= this.d.size()) {
                this.b = i2;
                return i2;
            }
            Action actionElementAt = this.d.elementAt(i);
            checksum = actionElementAt != null ? actionElementAt.getChecksum() + i2 : i2;
            i++;
        }
    }

    public String getId() {
        return this.a;
    }

    public String getTypeStr() {
        if (this.c == null) {
            return "contacts";
        }
        switch (this.c) {
            case CONTACTS:
                return "contacts";
            case CUSTOMWORDS:
                return "custom_words";
            case HISTORY:
                return "history";
            default:
                return "contacts";
        }
    }

    public void removeAction(Action action) {
        if (action != null) {
            this.d.remove(action);
        }
    }

    public void setType(DataType dataType) {
        if (dataType == DataType.CONTACTS || dataType == DataType.CUSTOMWORDS || dataType == DataType.HISTORY) {
            this.c = dataType;
        } else {
            this.c = DataType.CONTACTS;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("id:" + this.a + "\n");
        stringBuffer.append("checksum:" + this.b + "\n");
        stringBuffer.append("type:" + this.c + "\n");
        if (!this.d.isEmpty()) {
            int size = this.d.size();
            stringBuffer.append("action list: " + size + "\n");
            for (int i = 0; i < size; i++) {
                Action actionElementAt = this.d.elementAt(i);
                if (actionElementAt != null) {
                    stringBuffer.append("action: " + i + "\n");
                    stringBuffer.append(actionElementAt.toString());
                }
            }
        }
        return stringBuffer.toString();
    }
}
