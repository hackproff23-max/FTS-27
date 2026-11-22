package com.nuance.nmdp.speechkit.util.grammar;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.List;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Grammar {
    private GrammarType a;
    private String b;
    private int c;
    private boolean d;
    private boolean e;
    private int f;
    private boolean g;
    private String h;
    private String i;
    private String j;
    private String k;
    private Vector<String> l;

    public enum GrammarType {
        CONTACTS,
        CUSTOMWORDS,
        HISTORY,
        URI,
        PREDEFINED_STATIC_GRAMMARS,
        INSTANT_ITEM_LIST
    }

    public Grammar(GrammarType grammarType, String str) {
        this.e = false;
        this.g = false;
        this.b = str;
        if (grammarType == GrammarType.CONTACTS || grammarType == GrammarType.CUSTOMWORDS || grammarType == GrammarType.HISTORY || grammarType == GrammarType.URI || grammarType == GrammarType.PREDEFINED_STATIC_GRAMMARS || grammarType == GrammarType.INSTANT_ITEM_LIST) {
            this.a = grammarType;
        } else {
            this.a = GrammarType.CONTACTS;
        }
        if (this.a == GrammarType.INSTANT_ITEM_LIST) {
            this.l = new Vector<>();
        }
    }

    public Grammar(GrammarType grammarType, String str, int i) {
        this.e = false;
        this.g = false;
        this.b = str;
        if (grammarType == GrammarType.CONTACTS || grammarType == GrammarType.CUSTOMWORDS || grammarType == GrammarType.HISTORY) {
            this.a = grammarType;
        } else {
            this.a = GrammarType.CONTACTS;
        }
        this.c = i;
    }

    public Grammar(String str, String str2) {
        this.e = false;
        this.g = false;
        this.a = GrammarType.URI;
        this.b = str;
        this.k = str2;
        this.c = 0;
    }

    public Grammar(String str, List<String> list) {
        this.e = false;
        this.g = false;
        this.a = GrammarType.INSTANT_ITEM_LIST;
        this.b = str;
        this.l = new Vector<>();
        for (String str2 : list) {
            if (str2 != null) {
                this.l.add(str2);
            }
        }
    }

    public PdxValue.Dictionary getGrammarDictionary() {
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
        dictionary.put("id", this.b);
        dictionary.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, getTypeStr());
        if (this.a == GrammarType.PREDEFINED_STATIC_GRAMMARS) {
            return dictionary;
        }
        if (this.a == GrammarType.CONTACTS || this.a == GrammarType.CUSTOMWORDS || this.a == GrammarType.HISTORY) {
            dictionary.put("checksum", Integer.toString(this.c));
            if (this.j != null) {
                dictionary.put("common_user_id", this.j);
            }
        } else if (this.a == GrammarType.URI) {
            dictionary.put("uri", this.k);
        } else if (this.a == GrammarType.INSTANT_ITEM_LIST) {
            PdxValue.Sequence sequence = new PdxValue.Sequence();
            if (!this.l.isEmpty()) {
                for (int i = 0; i < this.l.size(); i++) {
                    String strElementAt = this.l.elementAt(i);
                    if (strElementAt != null) {
                        sequence.add(strElementAt);
                    }
                }
            }
            dictionary.put("item_list", sequence);
        }
        if (this.e) {
            dictionary.put("load_as_lmh", this.d ? 1 : 0);
        }
        if (this.g) {
            dictionary.put("topic_weight", this.f);
        }
        if (this.h != null) {
            dictionary.put("slot_tag", this.h);
        }
        if (this.i != null) {
            dictionary.put("interpretation_uri", this.i);
        }
        return dictionary;
    }

    public String getTypeStr() {
        if (this.a == null) {
            return "contacts";
        }
        switch (this.a) {
            case CONTACTS:
                return "contacts";
            case CUSTOMWORDS:
                return "custom_words";
            case HISTORY:
                return "history";
            case URI:
                return "uri";
            case PREDEFINED_STATIC_GRAMMARS:
                return "predefined_static_grammars";
            case INSTANT_ITEM_LIST:
                return "instant_item_list";
            default:
                return "contacts";
        }
    }

    public void setInterUri(String str) {
        this.i = str;
    }

    public void setLoadAsLmh(boolean z) {
        this.e = true;
        this.d = z;
    }

    public void setSlotTag(String str) {
        this.h = str;
    }

    public void setTopicWeight(int i) {
        this.g = true;
        this.f = i;
    }

    public void setUri(String str) {
        this.k = str;
    }

    public void setUserId(String str) {
        this.j = str;
    }
}
