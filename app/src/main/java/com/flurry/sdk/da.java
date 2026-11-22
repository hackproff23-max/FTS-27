package com.flurry.sdk;

import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import android.util.Xml;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class da {
    private static final String a = null;
    private static a b = a.PARSING_UNKNOWN;

    enum a {
        PARSING_UNKNOWN,
        PARSING_STARTED,
        PARSING_COMPLETE,
        PARSING_ERROR
    }

    public static cy a(String str) throws Throwable {
        StringReader stringReader;
        cy cyVarB;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a(a.PARSING_UNKNOWN);
        try {
            stringReader = new StringReader(str);
            try {
                try {
                    XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                    xmlPullParserNewPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
                    xmlPullParserNewPullParser.setInput(stringReader);
                    xmlPullParserNewPullParser.nextTag();
                    cyVarB = a(xmlPullParserNewPullParser, new cy.a(), new ArrayList());
                    if (cyVarB != null && cyVarB.c() && cyVarB.f() == null) {
                        cyVarB = new cy.a().a().b();
                    }
                    hp.a(stringReader);
                } catch (Exception e) {
                    e = e;
                    if (a().equals(a.PARSING_ERROR)) {
                        gd.a(3, "VASTXmlParser", "Error parsing VAST XML: " + str, e);
                        cyVarB = new cy.a().a().b();
                    } else {
                        gd.a(3, "VASTXmlParser", "Not a VAST Ad");
                        cyVarB = null;
                    }
                    hp.a(stringReader);
                }
            } catch (Throwable th) {
                th = th;
                hp.a(stringReader);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            stringReader = null;
        } catch (Throwable th2) {
            th = th2;
            stringReader = null;
        }
        return cyVarB;
    }

    private static cy a(XmlPullParser xmlPullParser, cy.a aVar, List<dg> list) throws XmlPullParserException, IOException, IllegalArgumentException {
        xmlPullParser.require(2, a, "VAST");
        a(a.PARSING_STARTED);
        int iC = c(xmlPullParser);
        if (a(iC)) {
            aVar.a(iC);
            while (xmlPullParser.next() != 3) {
                if (xmlPullParser.getEventType() == 2) {
                    if (xmlPullParser.getName().equals("Ad")) {
                        list.add(a(xmlPullParser, new dg.a()));
                    } else {
                        b(xmlPullParser);
                    }
                }
            }
            aVar.a(list);
            aVar.a(a(list));
            if (!a(list)) {
                a(a.PARSING_UNKNOWN);
            } else {
                a(a.PARSING_COMPLETE);
            }
            return aVar.b();
        }
        a(a.PARSING_ERROR);
        throw new IllegalArgumentException();
    }

    private static dg a(XmlPullParser xmlPullParser, dg.a aVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "Ad");
        aVar.a(xmlPullParser.getAttributeValue(a, "id"));
        try {
            String attributeValue = xmlPullParser.getAttributeValue(a, "sequence");
            if (!TextUtils.isEmpty(attributeValue)) {
                aVar.a(Integer.parseInt(attributeValue));
            }
        } catch (NumberFormatException e) {
            gd.a(3, "VASTXmlParser", "Could not identify Ad Sequence");
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                switch (xmlPullParser.getName()) {
                    case "InLine":
                        aVar.a(a(xmlPullParser, new di.a(), new dh.a(), new ArrayList(), new ArrayList()));
                        break;
                    case "Wrapper":
                        aVar.a(a(xmlPullParser, new di.a(), new dh.a(), new ArrayList(), new ArrayList(), new ArrayList()));
                        break;
                    default:
                        b(xmlPullParser);
                        break;
                }
            }
        }
        return aVar.a();
    }

    private static di a(XmlPullParser xmlPullParser, di.a aVar, dh.a aVar2, List<String> list, List<String> list2) throws XmlPullParserException, IOException {
        aVar.a(db.InLine);
        xmlPullParser.require(2, a, "InLine");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                switch (xmlPullParser.getName()) {
                    case "Creatives":
                        aVar.d(a(xmlPullParser, new ArrayList()));
                        break;
                    case "AdSystem":
                        aVar2.a(xmlPullParser.getAttributeValue(a, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION));
                        aVar2.b(a(xmlPullParser));
                        aVar.a(aVar2.a());
                        break;
                    case "AdTitle":
                        aVar.a(a(xmlPullParser));
                        break;
                    case "Impression":
                        a(list, a(xmlPullParser));
                        break;
                    case "Error":
                        a(list2, a(xmlPullParser));
                        break;
                    default:
                        b(xmlPullParser);
                        break;
                }
            }
        }
        aVar.b(list);
        aVar.c(list2);
        return aVar.a();
    }

    private static di a(XmlPullParser xmlPullParser, di.a aVar, dh.a aVar2, List<String> list, List<String> list2, List<String> list3) throws XmlPullParserException, IOException {
        aVar.a(db.Wrapper);
        xmlPullParser.require(2, a, "Wrapper");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                switch (xmlPullParser.getName()) {
                    case "Creatives":
                        aVar.d(a(xmlPullParser, new ArrayList()));
                        break;
                    case "AdSystem":
                        aVar2.a(xmlPullParser.getAttributeValue(a, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION));
                        aVar2.b(a(xmlPullParser));
                        aVar.a(aVar2.a());
                        break;
                    case "VASTAdTagURI":
                        a(list, a(xmlPullParser));
                        break;
                    case "Impression":
                        a(list2, a(xmlPullParser));
                        break;
                    case "Error":
                        a(list3, a(xmlPullParser));
                        break;
                    default:
                        b(xmlPullParser);
                        break;
                }
            }
        }
        aVar.a(list);
        aVar.b(list2);
        aVar.c(list3);
        return aVar.a();
    }

    private static List<dj> a(XmlPullParser xmlPullParser, List<dj> list) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "Creatives");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Creative")) {
                    list.add(a(xmlPullParser, new dj.a()));
                } else {
                    b(xmlPullParser);
                }
            }
        }
        return list;
    }

    private static dj a(XmlPullParser xmlPullParser, dj.a aVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "Creative");
        aVar.a(xmlPullParser.getAttributeValue(a, "id"));
        String attributeValue = xmlPullParser.getAttributeValue(a, "sequence");
        if (attributeValue != null) {
            try {
                aVar.a(Math.round(Float.parseFloat(attributeValue)));
            } catch (NumberFormatException e) {
                gd.a(3, "VASTXmlParser", "Could not identify Creative sequence");
            }
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Linear")) {
                    aVar.a(dc.Linear);
                    aVar.a(a(xmlPullParser, new dk.a()));
                } else {
                    b(xmlPullParser);
                }
            }
        }
        return aVar.a();
    }

    private static dk a(XmlPullParser xmlPullParser, dk.a aVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "Linear");
        String attributeValue = xmlPullParser.getAttributeValue(a, "skipoffset");
        if (attributeValue != null) {
            aVar.b(cz.a(attributeValue));
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                switch (xmlPullParser.getName()) {
                    case "Duration":
                        aVar.a(cz.a(a(xmlPullParser)));
                        break;
                    case "TrackingEvents":
                        aVar.a(b(xmlPullParser, (fu<de, String>) new fu()));
                        break;
                    case "VideoClicks":
                        aVar.b(a(xmlPullParser, (fu<df, String>) new fu()));
                        break;
                    case "MediaFiles":
                        dl dlVarA = cz.a(b(xmlPullParser, new ArrayList()));
                        if (dlVarA == null) {
                            break;
                        } else {
                            aVar.a(dlVarA);
                            break;
                        }
                        break;
                    default:
                        b(xmlPullParser);
                        break;
                }
            }
        }
        return aVar.a();
    }

    private static fu<df, String> a(XmlPullParser xmlPullParser, fu<df, String> fuVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "VideoClicks");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                switch (xmlPullParser.getName()) {
                    case "ClickThrough":
                        fuVar.a(df.ClickThrough, a(xmlPullParser));
                        break;
                    case "ClickTracking":
                        fuVar.a(df.ClickTracking, a(xmlPullParser));
                        break;
                    case "CustomClick":
                        fuVar.a(df.CustomClick, a(xmlPullParser));
                        break;
                    default:
                        b(xmlPullParser);
                        break;
                }
            }
        }
        return fuVar;
    }

    private static fu<de, String> b(XmlPullParser xmlPullParser, fu<de, String> fuVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "TrackingEvents");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("Tracking")) {
                    dm dmVarA = a(xmlPullParser, new dm.a());
                    if (!TextUtils.isEmpty(dmVarA.b())) {
                        fuVar.a(dmVarA.a(), dmVarA.b());
                    }
                } else {
                    b(xmlPullParser);
                }
            }
        }
        return fuVar;
    }

    private static dm a(XmlPullParser xmlPullParser, dm.a aVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "Tracking");
        aVar.a(de.a(xmlPullParser.getAttributeValue(a, "event")));
        aVar.a(a(xmlPullParser));
        return aVar.a();
    }

    private static List<dl> b(XmlPullParser xmlPullParser, List<dl> list) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "MediaFiles");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equals("MediaFile")) {
                    list.add(a(xmlPullParser, new dl.a()));
                } else {
                    b(xmlPullParser);
                }
            }
        }
        return list;
    }

    private static dl a(XmlPullParser xmlPullParser, dl.a aVar) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, a, "MediaFile");
        aVar.a(xmlPullParser.getAttributeValue(a, "id"));
        aVar.d(xmlPullParser.getAttributeValue(a, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY));
        aVar.b(xmlPullParser.getAttributeValue(a, "apiFramework"));
        aVar.a(dd.a(xmlPullParser.getAttributeValue(a, "delivery")));
        try {
            aVar.b(Integer.parseInt(xmlPullParser.getAttributeValue(a, "height")));
        } catch (NumberFormatException e) {
            gd.a(3, "VASTXmlParser", "Could not identify MediaFile height");
        }
        try {
            aVar.c(Integer.parseInt(xmlPullParser.getAttributeValue(a, "width")));
        } catch (NumberFormatException e2) {
            gd.a(3, "VASTXmlParser", "Could not identify MediaFile width");
        }
        try {
            aVar.a(Integer.parseInt(xmlPullParser.getAttributeValue(a, "bitrate")));
        } catch (NumberFormatException e3) {
            gd.a(3, "VASTXmlParser", "Could not identify MediaFile bitRate");
        }
        aVar.b(Boolean.parseBoolean(xmlPullParser.getAttributeValue(a, "scalable")));
        aVar.a(Boolean.parseBoolean(xmlPullParser.getAttributeValue(a, "maintainAspectRatio")));
        aVar.c(a(xmlPullParser));
        return aVar.a();
    }

    private static String a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.next() != 4) {
            return null;
        }
        String strTrim = xmlPullParser.getText().trim();
        xmlPullParser.nextTag();
        return strTrim;
    }

    private static void a(List<String> list, String str) {
        if (list != null && !TextUtils.isEmpty(str)) {
            list.add(str);
        }
    }

    private static void b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            switch (xmlPullParser.next()) {
                case 2:
                    i++;
                    break;
                case 3:
                    i--;
                    break;
            }
        }
    }

    public static boolean a(List<dg> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Iterator<dg> it = list.iterator();
        while (it.hasNext()) {
            di diVarC = it.next().c();
            if (diVarC == null || !db.InLine.equals(diVarC.a())) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(int i) {
        return i >= 1 && i <= 3;
    }

    private static int c(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(a, ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION);
        gd.a(3, "VASTXmlParser", "Version" + attributeValue);
        if (TextUtils.isEmpty(attributeValue)) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        String[] strArrSplit = attributeValue.split("\\.");
        if (strArrSplit.length <= 0 || TextUtils.isEmpty(strArrSplit[0])) {
            return ExploreByTouchHelper.INVALID_ID;
        }
        try {
            return Integer.parseInt(strArrSplit[0]);
        } catch (NumberFormatException e) {
            gd.a(3, "VASTXmlParser", "Could not detect VAST version " + strArrSplit[0]);
            return ExploreByTouchHelper.INVALID_ID;
        }
    }

    private static void a(a aVar) {
        gd.a(3, "VASTXmlParser", "Setting VAST parse state as: " + aVar.name());
        b = aVar;
    }

    private static a a() {
        return b;
    }
}
