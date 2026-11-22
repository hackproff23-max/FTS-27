package com.google.android.gms.analytics;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.google.android.gms.analytics.m;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
abstract class n<T extends m> {
    Context mContext;
    a<T> yL;

    public interface a<U extends m> {
        void c(String str, int i);

        void e(String str, String str2);

        void e(String str, boolean z);

        U er();

        void f(String str, String str2);
    }

    public n(Context context, a<T> aVar) {
        this.mContext = context;
        this.yL = aVar;
    }

    private T a(XmlResourceParser xmlResourceParser) {
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (xmlResourceParser.getEventType() == 2) {
                    String lowerCase = xmlResourceParser.getName().toLowerCase();
                    if (lowerCase.equals("screenname")) {
                        String attributeValue = xmlResourceParser.getAttributeValue(null, "name");
                        String strTrim = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue) && !TextUtils.isEmpty(strTrim)) {
                            this.yL.e(attributeValue, strTrim);
                        }
                    } else if (lowerCase.equals("string")) {
                        String attributeValue2 = xmlResourceParser.getAttributeValue(null, "name");
                        String strTrim2 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue2) && strTrim2 != null) {
                            this.yL.f(attributeValue2, strTrim2);
                        }
                    } else if (lowerCase.equals("bool")) {
                        String attributeValue3 = xmlResourceParser.getAttributeValue(null, "name");
                        String strTrim3 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue3) && !TextUtils.isEmpty(strTrim3)) {
                            try {
                                this.yL.e(attributeValue3, Boolean.parseBoolean(strTrim3));
                            } catch (NumberFormatException e) {
                                ae.T("Error parsing bool configuration value: " + strTrim3);
                            }
                        }
                    } else if (lowerCase.equals("integer")) {
                        String attributeValue4 = xmlResourceParser.getAttributeValue(null, "name");
                        String strTrim4 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue4) && !TextUtils.isEmpty(strTrim4)) {
                            try {
                                this.yL.c(attributeValue4, Integer.parseInt(strTrim4));
                            } catch (NumberFormatException e2) {
                                ae.T("Error parsing int configuration value: " + strTrim4);
                            }
                        }
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e3) {
            ae.T("Error parsing tracker configuration file: " + e3);
        } catch (XmlPullParserException e4) {
            ae.T("Error parsing tracker configuration file: " + e4);
        }
        return (T) this.yL.er();
    }

    public T x(int i) {
        try {
            return (T) a(this.mContext.getResources().getXml(i));
        } catch (Resources.NotFoundException e) {
            ae.W("inflate() called with unknown resourceId: " + e);
            return null;
        }
    }
}
