package com.nuance.nmdp.speechkit.util.dataupload;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Action {
    private ActionType a;
    private Vector<Contact> b;
    private Vector<String> c;

    public enum ActionType {
        ADD,
        REMOVE,
        CLEARALL
    }

    public Action(ActionType actionType) {
        if (actionType == ActionType.ADD || actionType == ActionType.REMOVE || actionType == ActionType.CLEARALL) {
            this.a = actionType;
        } else {
            this.a = ActionType.ADD;
        }
        this.b = new Vector<>();
        this.c = new Vector<>();
    }

    /* JADX WARN: Code duplicated, block: B:4:0x000f  */
    final PdxValue.Dictionary a() {
        PdxValue.Dictionary dictionaryA;
        int i = 0;
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
        PdxValue.Dictionary dictionary2 = new PdxValue.Dictionary();
        if (this.a != null) {
            switch (this.a) {
                case ADD:
                    dictionary.put("action", ProductAction.ACTION_ADD);
                    break;
                case REMOVE:
                    dictionary.put("action", ProductAction.ACTION_REMOVE);
                    break;
                case CLEARALL:
                    dictionary.put("action", "clear_all");
                    break;
                default:
                    dictionary.put("action", ProductAction.ACTION_ADD);
                    break;
            }
        } else {
            dictionary.put("action", ProductAction.ACTION_ADD);
        }
        if (!this.b.isEmpty()) {
            PdxValue.Sequence sequence = new PdxValue.Sequence();
            while (true) {
                int i2 = i;
                if (i2 < this.b.size()) {
                    Contact contactElementAt = this.b.elementAt(i2);
                    if (contactElementAt != null && (dictionaryA = contactElementAt.a()) != null) {
                        sequence.add(dictionaryA);
                    }
                    i = i2 + 1;
                } else {
                    dictionary2.put("list", sequence);
                }
            }
        } else if (!this.c.isEmpty()) {
            PdxValue.Sequence sequence2 = new PdxValue.Sequence();
            while (true) {
                int i3 = i;
                if (i3 < this.c.size()) {
                    String strElementAt = this.c.elementAt(i3);
                    if (strElementAt != null) {
                        sequence2.add(strElementAt);
                    }
                    i = i3 + 1;
                } else {
                    dictionary2.put("list", sequence2);
                }
            }
        }
        dictionary.put("content", dictionary2);
        return dictionary;
    }

    public void addContact(Contact contact) {
        this.b.add(contact);
    }

    public void addWord(String str) {
        this.c.add(str);
    }

    public void cleanData() {
        this.a = null;
        this.b.removeAllElements();
        this.c.removeAllElements();
    }

    public void clearContacts() {
        this.b.removeAllElements();
    }

    public void clearWords() {
        this.c.removeAllElements();
    }

    public int getChecksum() {
        int i = 0;
        int i2 = 0;
        int iHashCode = 0;
        while (i2 < this.b.size()) {
            Contact contactElementAt = this.b.elementAt(i2);
            i2++;
            iHashCode = contactElementAt != null ? contactElementAt.getCheckSum() + iHashCode : iHashCode;
        }
        while (i < this.c.size()) {
            String strElementAt = this.c.elementAt(i);
            i++;
            iHashCode = strElementAt != null ? strElementAt.hashCode() + iHashCode : iHashCode;
        }
        return iHashCode;
    }

    public void removeContact(Contact contact) {
        this.b.remove(contact);
    }

    public void removeWord(String str) {
        this.c.remove(str);
    }

    public void setType(ActionType actionType) {
        if (actionType == ActionType.ADD || actionType == ActionType.REMOVE || actionType == ActionType.CLEARALL) {
            this.a = actionType;
        } else {
            this.a = ActionType.ADD;
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("action:" + this.a + "\n");
        if (!this.b.isEmpty()) {
            int size = this.b.size();
            stringBuffer.append("contact list: " + size + "\n");
            for (int i = 0; i < size; i++) {
                Contact contactElementAt = this.b.elementAt(i);
                if (contactElementAt != null) {
                    stringBuffer.append("contact: " + i + "\n");
                    stringBuffer.append(contactElementAt.toString());
                }
            }
        }
        if (!this.c.isEmpty()) {
            int size2 = this.c.size();
            stringBuffer.append("word list: " + size2 + "\n");
            for (int i2 = 0; i2 < size2; i2++) {
                String strElementAt = this.c.elementAt(i2);
                if (strElementAt != null) {
                    stringBuffer.append("word: " + i2 + " " + strElementAt + "\n");
                }
            }
        }
        return stringBuffer.toString();
    }
}
