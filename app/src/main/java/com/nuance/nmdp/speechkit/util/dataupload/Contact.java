package com.nuance.nmdp.speechkit.util.dataupload;

import com.nuance.nmdp.speechkit.util.pdx.PdxValue;

/* JADX INFO: loaded from: classes.dex */
public class Contact {
    private String a;
    private String c;
    private String g;
    private String b = null;
    private String d = null;
    private String h = null;
    private String e = null;
    private String f = null;
    private String i = null;

    public Contact(String str, String str2, String str3) {
        this.a = str;
        this.c = str3;
        this.g = str2;
    }

    final PdxValue.Dictionary a() {
        PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
        if (this.e != null) {
            dictionary.put("full_name", this.e);
        }
        if (this.a != null) {
            dictionary.put("first_name", this.a);
        }
        if (this.b != null) {
            dictionary.put("first_name_phonetic", this.b);
        }
        if (this.c != null) {
            dictionary.put("last_name", this.c);
        }
        if (this.d != null) {
            dictionary.put("last_name_phonetic", this.d);
        }
        if (this.f != null) {
            dictionary.put("nick_name", this.f);
        }
        if (this.g != null) {
            dictionary.put("middle_name", this.g);
        }
        if (this.h != null) {
            dictionary.put("middle_name_phonetic", this.h);
        }
        if (this.i != null) {
            dictionary.put("company", this.i);
        }
        return dictionary;
    }

    public void clearData() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.g = null;
        this.h = null;
        this.e = null;
        this.f = null;
        this.i = null;
    }

    public int getCheckSum() {
        int iHashCode = this.a != null ? this.a.hashCode() + 0 : 0;
        if (this.b != null) {
            iHashCode += this.b.hashCode();
        }
        if (this.c != null) {
            iHashCode += this.c.hashCode();
        }
        if (this.d != null) {
            iHashCode += this.d.hashCode();
        }
        if (this.e != null) {
            iHashCode += this.e.hashCode();
        }
        if (this.f != null) {
            iHashCode += this.f.hashCode();
        }
        if (this.g != null) {
            iHashCode += this.g.hashCode();
        }
        if (this.h != null) {
            iHashCode += this.h.hashCode();
        }
        return this.i != null ? iHashCode + this.i.hashCode() : iHashCode;
    }

    public String getCompany() {
        return this.i;
    }

    public String getFirstName() {
        return this.a;
    }

    public String getFirstNamePhonetic() {
        return this.b;
    }

    public String getFullName() {
        return this.e;
    }

    public String getLastName() {
        return this.c;
    }

    public String getLastNamePhonetic() {
        return this.d;
    }

    public String getMiddleName() {
        return this.g;
    }

    public String getMiddleNamePhonetic() {
        return this.h;
    }

    public String getNickName() {
        return this.f;
    }

    public void setCompany(String str) {
        this.i = str;
    }

    public void setFirstName(String str) {
        this.a = str;
    }

    public void setFirstNamePhonetic(String str) {
        this.b = str;
    }

    public void setFullName(String str) {
        this.e = str;
    }

    public void setLastName(String str) {
        this.c = str;
    }

    public void setLastNamePhonetic(String str) {
        this.d = str;
    }

    public void setMiddleName(String str) {
        this.g = str;
    }

    public void setMiddleNamePhonetic(String str) {
        this.h = str;
    }

    public void setNickName(String str) {
        this.f = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.a != null) {
            stringBuffer.append("firstname:" + this.a + "\n");
        }
        if (this.b != null) {
            stringBuffer.append("firstname_phonetic:" + this.b + "\n");
        }
        if (this.g != null) {
            stringBuffer.append("middlename:" + this.g + "\n");
        }
        if (this.h != null) {
            stringBuffer.append("middlename_phonetic:" + this.h + "\n");
        }
        if (this.c != null) {
            stringBuffer.append("lastname:" + this.c + "\n");
        }
        if (this.d != null) {
            stringBuffer.append("lastname_phonetic:" + this.d + "\n");
        }
        if (this.e != null) {
            stringBuffer.append("fullname:" + this.e + "\n");
        }
        if (this.f != null) {
            stringBuffer.append("nickname:" + this.f + "\n");
        }
        if (this.i != null) {
            stringBuffer.append("company:" + this.i + "\n");
        }
        return stringBuffer.toString();
    }
}
