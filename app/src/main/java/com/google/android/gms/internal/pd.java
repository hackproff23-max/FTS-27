package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class pd extends ks implements ItemScope {
    public static final pe CREATOR = new pe();
    private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
    String CE;
    final int CK;
    String UO;
    double agg;
    double agh;
    String aoA;
    pd aoB;
    String aoC;
    String aoD;
    List<pd> aoE;
    String aoF;
    String aoG;
    String aoH;
    String aoI;
    String aoJ;
    String aoK;
    String aoL;
    String aoM;
    pd aoN;
    String aoO;
    String aoP;
    String aoQ;
    pd aoR;
    pd aoS;
    pd aoT;
    List<pd> aoU;
    String aoV;
    String aoW;
    String aoX;
    String aoY;
    pd aoZ;
    final Set<Integer> aon;
    pd aoo;
    List<String> aop;
    pd aoq;
    String aor;
    String aos;
    String aot;
    List<pd> aou;
    int aov;
    List<pd> aow;
    pd aox;
    List<pd> aoy;
    String aoz;
    String apa;
    String apb;
    String apc;
    pd apd;
    String ape;
    String apf;
    String apg;
    String aph;
    String mName;
    String ov;
    String vc;
    String vf;

    static {
        aom.put("about", kr.a.a("about", 2, pd.class));
        aom.put("additionalName", kr.a.m("additionalName", 3));
        aom.put("address", kr.a.a("address", 4, pd.class));
        aom.put("addressCountry", kr.a.l("addressCountry", 5));
        aom.put("addressLocality", kr.a.l("addressLocality", 6));
        aom.put("addressRegion", kr.a.l("addressRegion", 7));
        aom.put("associated_media", kr.a.b("associated_media", 8, pd.class));
        aom.put("attendeeCount", kr.a.i("attendeeCount", 9));
        aom.put("attendees", kr.a.b("attendees", 10, pd.class));
        aom.put("audio", kr.a.a("audio", 11, pd.class));
        aom.put("author", kr.a.b("author", 12, pd.class));
        aom.put("bestRating", kr.a.l("bestRating", 13));
        aom.put("birthDate", kr.a.l("birthDate", 14));
        aom.put("byArtist", kr.a.a("byArtist", 15, pd.class));
        aom.put("caption", kr.a.l("caption", 16));
        aom.put("contentSize", kr.a.l("contentSize", 17));
        aom.put("contentUrl", kr.a.l("contentUrl", 18));
        aom.put("contributor", kr.a.b("contributor", 19, pd.class));
        aom.put("dateCreated", kr.a.l("dateCreated", 20));
        aom.put("dateModified", kr.a.l("dateModified", 21));
        aom.put("datePublished", kr.a.l("datePublished", 22));
        aom.put("description", kr.a.l("description", 23));
        aom.put("duration", kr.a.l("duration", 24));
        aom.put("embedUrl", kr.a.l("embedUrl", 25));
        aom.put("endDate", kr.a.l("endDate", 26));
        aom.put("familyName", kr.a.l("familyName", 27));
        aom.put(MMRequest.KEY_GENDER, kr.a.l(MMRequest.KEY_GENDER, 28));
        aom.put(MMSDK.Event.INTENT_MAPS, kr.a.a(MMSDK.Event.INTENT_MAPS, 29, pd.class));
        aom.put("givenName", kr.a.l("givenName", 30));
        aom.put("height", kr.a.l("height", 31));
        aom.put("id", kr.a.l("id", 32));
        aom.put("image", kr.a.l("image", 33));
        aom.put("inAlbum", kr.a.a("inAlbum", 34, pd.class));
        aom.put("latitude", kr.a.j("latitude", 36));
        aom.put("location", kr.a.a("location", 37, pd.class));
        aom.put("longitude", kr.a.j("longitude", 38));
        aom.put("name", kr.a.l("name", 39));
        aom.put("partOfTVSeries", kr.a.a("partOfTVSeries", 40, pd.class));
        aom.put("performers", kr.a.b("performers", 41, pd.class));
        aom.put("playerType", kr.a.l("playerType", 42));
        aom.put("postOfficeBoxNumber", kr.a.l("postOfficeBoxNumber", 43));
        aom.put("postalCode", kr.a.l("postalCode", 44));
        aom.put("ratingValue", kr.a.l("ratingValue", 45));
        aom.put("reviewRating", kr.a.a("reviewRating", 46, pd.class));
        aom.put("startDate", kr.a.l("startDate", 47));
        aom.put("streetAddress", kr.a.l("streetAddress", 48));
        aom.put("text", kr.a.l("text", 49));
        aom.put("thumbnail", kr.a.a("thumbnail", 50, pd.class));
        aom.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, kr.a.l(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL, 51));
        aom.put("tickerSymbol", kr.a.l("tickerSymbol", 52));
        aom.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, kr.a.l(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, 53));
        aom.put("url", kr.a.l("url", 54));
        aom.put("width", kr.a.l("width", 55));
        aom.put("worstRating", kr.a.l("worstRating", 56));
    }

    public pd() {
        this.CK = 1;
        this.aon = new HashSet();
    }

    pd(Set<Integer> set, int i, pd pdVar, List<String> list, pd pdVar2, String str, String str2, String str3, List<pd> list2, int i2, List<pd> list3, pd pdVar3, List<pd> list4, String str4, String str5, pd pdVar4, String str6, String str7, String str8, List<pd> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, pd pdVar5, String str18, String str19, String str20, String str21, pd pdVar6, double d, pd pdVar7, double d2, String str22, pd pdVar8, List<pd> list6, String str23, String str24, String str25, String str26, pd pdVar9, String str27, String str28, String str29, pd pdVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.aon = set;
        this.CK = i;
        this.aoo = pdVar;
        this.aop = list;
        this.aoq = pdVar2;
        this.aor = str;
        this.aos = str2;
        this.aot = str3;
        this.aou = list2;
        this.aov = i2;
        this.aow = list3;
        this.aox = pdVar3;
        this.aoy = list4;
        this.aoz = str4;
        this.aoA = str5;
        this.aoB = pdVar4;
        this.aoC = str6;
        this.aoD = str7;
        this.ov = str8;
        this.aoE = list5;
        this.aoF = str9;
        this.aoG = str10;
        this.aoH = str11;
        this.UO = str12;
        this.aoI = str13;
        this.aoJ = str14;
        this.aoK = str15;
        this.aoL = str16;
        this.aoM = str17;
        this.aoN = pdVar5;
        this.aoO = str18;
        this.aoP = str19;
        this.CE = str20;
        this.aoQ = str21;
        this.aoR = pdVar6;
        this.agg = d;
        this.aoS = pdVar7;
        this.agh = d2;
        this.mName = str22;
        this.aoT = pdVar8;
        this.aoU = list6;
        this.aoV = str23;
        this.aoW = str24;
        this.aoX = str25;
        this.aoY = str26;
        this.aoZ = pdVar9;
        this.apa = str27;
        this.apb = str28;
        this.apc = str29;
        this.apd = pdVar10;
        this.ape = str30;
        this.apf = str31;
        this.vc = str32;
        this.vf = str33;
        this.apg = str34;
        this.aph = str35;
    }

    public pd(Set<Integer> set, pd pdVar, List<String> list, pd pdVar2, String str, String str2, String str3, List<pd> list2, int i, List<pd> list3, pd pdVar3, List<pd> list4, String str4, String str5, pd pdVar4, String str6, String str7, String str8, List<pd> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, pd pdVar5, String str18, String str19, String str20, String str21, pd pdVar6, double d, pd pdVar7, double d2, String str22, pd pdVar8, List<pd> list6, String str23, String str24, String str25, String str26, pd pdVar9, String str27, String str28, String str29, pd pdVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.aon = set;
        this.CK = 1;
        this.aoo = pdVar;
        this.aop = list;
        this.aoq = pdVar2;
        this.aor = str;
        this.aos = str2;
        this.aot = str3;
        this.aou = list2;
        this.aov = i;
        this.aow = list3;
        this.aox = pdVar3;
        this.aoy = list4;
        this.aoz = str4;
        this.aoA = str5;
        this.aoB = pdVar4;
        this.aoC = str6;
        this.aoD = str7;
        this.ov = str8;
        this.aoE = list5;
        this.aoF = str9;
        this.aoG = str10;
        this.aoH = str11;
        this.UO = str12;
        this.aoI = str13;
        this.aoJ = str14;
        this.aoK = str15;
        this.aoL = str16;
        this.aoM = str17;
        this.aoN = pdVar5;
        this.aoO = str18;
        this.aoP = str19;
        this.CE = str20;
        this.aoQ = str21;
        this.aoR = pdVar6;
        this.agg = d;
        this.aoS = pdVar7;
        this.agh = d2;
        this.mName = str22;
        this.aoT = pdVar8;
        this.aoU = list6;
        this.aoV = str23;
        this.aoW = str24;
        this.aoX = str25;
        this.aoY = str26;
        this.aoZ = pdVar9;
        this.apa = str27;
        this.apb = str28;
        this.apc = str29;
        this.apd = pdVar10;
        this.ape = str30;
        this.apf = str31;
        this.vc = str32;
        this.vf = str33;
        this.apg = str34;
        this.aph = str35;
    }

    @Override // com.google.android.gms.internal.kr
    protected boolean a(kr.a aVar) {
        return this.aon.contains(Integer.valueOf(aVar.hR()));
    }

    @Override // com.google.android.gms.internal.kr
    protected Object b(kr.a aVar) {
        switch (aVar.hR()) {
            case 2:
                return this.aoo;
            case 3:
                return this.aop;
            case 4:
                return this.aoq;
            case 5:
                return this.aor;
            case 6:
                return this.aos;
            case 7:
                return this.aot;
            case 8:
                return this.aou;
            case 9:
                return Integer.valueOf(this.aov);
            case 10:
                return this.aow;
            case 11:
                return this.aox;
            case 12:
                return this.aoy;
            case 13:
                return this.aoz;
            case 14:
                return this.aoA;
            case 15:
                return this.aoB;
            case 16:
                return this.aoC;
            case 17:
                return this.aoD;
            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                return this.ov;
            case 19:
                return this.aoE;
            case 20:
                return this.aoF;
            case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                return this.aoG;
            case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                return this.aoH;
            case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                return this.UO;
            case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                return this.aoI;
            case MMException.AD_BROKEN_REFERENCE /* 25 */:
                return this.aoJ;
            case MMException.AD_NO_ACTIVITY /* 26 */:
                return this.aoK;
            case 27:
                return this.aoL;
            case 28:
                return this.aoM;
            case 29:
                return this.aoN;
            case 30:
                return this.aoO;
            case Notifications.NOTIFICATION_TYPES_ALL /* 31 */:
                return this.aoP;
            case 32:
                return this.CE;
            case 33:
                return this.aoQ;
            case 34:
                return this.aoR;
            case 35:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            case 36:
                return Double.valueOf(this.agg);
            case 37:
                return this.aoS;
            case 38:
                return Double.valueOf(this.agh);
            case 39:
                return this.mName;
            case 40:
                return this.aoT;
            case 41:
                return this.aoU;
            case 42:
                return this.aoV;
            case 43:
                return this.aoW;
            case 44:
                return this.aoX;
            case 45:
                return this.aoY;
            case 46:
                return this.aoZ;
            case 47:
                return this.apa;
            case 48:
                return this.apb;
            case 49:
                return this.apc;
            case 50:
                return this.apd;
            case 51:
                return this.ape;
            case 52:
                return this.apf;
            case 53:
                return this.vc;
            case 54:
                return this.vf;
            case 55:
                return this.apg;
            case 56:
                return this.aph;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        pe peVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof pd)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        pd pdVar = (pd) obj;
        for (kr.a<?, ?> aVar : aom.values()) {
            if (a(aVar)) {
                if (pdVar.a(aVar) && b(aVar).equals(pdVar.b(aVar))) {
                }
                return false;
            }
            if (pdVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getAbout() {
        return this.aoo;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<String> getAdditionalName() {
        return this.aop;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getAddress() {
        return this.aoq;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getAddressCountry() {
        return this.aor;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getAddressLocality() {
        return this.aos;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getAddressRegion() {
        return this.aot;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<ItemScope> getAssociated_media() {
        return (ArrayList) this.aou;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public int getAttendeeCount() {
        return this.aov;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<ItemScope> getAttendees() {
        return (ArrayList) this.aow;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getAudio() {
        return this.aox;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<ItemScope> getAuthor() {
        return (ArrayList) this.aoy;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getBestRating() {
        return this.aoz;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getBirthDate() {
        return this.aoA;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getByArtist() {
        return this.aoB;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getCaption() {
        return this.aoC;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getContentSize() {
        return this.aoD;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getContentUrl() {
        return this.ov;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<ItemScope> getContributor() {
        return (ArrayList) this.aoE;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getDateCreated() {
        return this.aoF;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getDateModified() {
        return this.aoG;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getDatePublished() {
        return this.aoH;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getDescription() {
        return this.UO;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getDuration() {
        return this.aoI;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getEmbedUrl() {
        return this.aoJ;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getEndDate() {
        return this.aoK;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getFamilyName() {
        return this.aoL;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getGender() {
        return this.aoM;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getGeo() {
        return this.aoN;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getGivenName() {
        return this.aoO;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getHeight() {
        return this.aoP;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getId() {
        return this.CE;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getImage() {
        return this.aoQ;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getInAlbum() {
        return this.aoR;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public double getLatitude() {
        return this.agg;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getLocation() {
        return this.aoS;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public double getLongitude() {
        return this.agh;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getName() {
        return this.mName;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getPartOfTVSeries() {
        return this.aoT;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public List<ItemScope> getPerformers() {
        return (ArrayList) this.aoU;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getPlayerType() {
        return this.aoV;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getPostOfficeBoxNumber() {
        return this.aoW;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getPostalCode() {
        return this.aoX;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getRatingValue() {
        return this.aoY;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getReviewRating() {
        return this.aoZ;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getStartDate() {
        return this.apa;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getStreetAddress() {
        return this.apb;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getText() {
        return this.apc;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public ItemScope getThumbnail() {
        return this.apd;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getThumbnailUrl() {
        return this.ape;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getTickerSymbol() {
        return this.apf;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getType() {
        return this.vc;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getUrl() {
        return this.vf;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getWidth() {
        return this.apg;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public String getWorstRating() {
        return this.aph;
    }

    @Override // com.google.android.gms.internal.kr
    public HashMap<String, kr.a<?, ?>> hK() {
        return aom;
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAbout() {
        return this.aon.contains(2);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAdditionalName() {
        return this.aon.contains(3);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAddress() {
        return this.aon.contains(4);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAddressCountry() {
        return this.aon.contains(5);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAddressLocality() {
        return this.aon.contains(6);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAddressRegion() {
        return this.aon.contains(7);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAssociated_media() {
        return this.aon.contains(8);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAttendeeCount() {
        return this.aon.contains(9);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAttendees() {
        return this.aon.contains(10);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAudio() {
        return this.aon.contains(11);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasAuthor() {
        return this.aon.contains(12);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasBestRating() {
        return this.aon.contains(13);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasBirthDate() {
        return this.aon.contains(14);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasByArtist() {
        return this.aon.contains(15);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasCaption() {
        return this.aon.contains(16);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasContentSize() {
        return this.aon.contains(17);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasContentUrl() {
        return this.aon.contains(18);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasContributor() {
        return this.aon.contains(19);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasDateCreated() {
        return this.aon.contains(20);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasDateModified() {
        return this.aon.contains(21);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasDatePublished() {
        return this.aon.contains(22);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasDescription() {
        return this.aon.contains(23);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasDuration() {
        return this.aon.contains(24);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasEmbedUrl() {
        return this.aon.contains(25);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasEndDate() {
        return this.aon.contains(26);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasFamilyName() {
        return this.aon.contains(27);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasGender() {
        return this.aon.contains(28);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasGeo() {
        return this.aon.contains(29);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasGivenName() {
        return this.aon.contains(30);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasHeight() {
        return this.aon.contains(31);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasId() {
        return this.aon.contains(32);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasImage() {
        return this.aon.contains(33);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasInAlbum() {
        return this.aon.contains(34);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasLatitude() {
        return this.aon.contains(36);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasLocation() {
        return this.aon.contains(37);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasLongitude() {
        return this.aon.contains(38);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasName() {
        return this.aon.contains(39);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasPartOfTVSeries() {
        return this.aon.contains(40);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasPerformers() {
        return this.aon.contains(41);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasPlayerType() {
        return this.aon.contains(42);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasPostOfficeBoxNumber() {
        return this.aon.contains(43);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasPostalCode() {
        return this.aon.contains(44);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasRatingValue() {
        return this.aon.contains(45);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasReviewRating() {
        return this.aon.contains(46);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasStartDate() {
        return this.aon.contains(47);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasStreetAddress() {
        return this.aon.contains(48);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasText() {
        return this.aon.contains(49);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasThumbnail() {
        return this.aon.contains(50);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasThumbnailUrl() {
        return this.aon.contains(51);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasTickerSymbol() {
        return this.aon.contains(52);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasType() {
        return this.aon.contains(53);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasUrl() {
        return this.aon.contains(54);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasWidth() {
        return this.aon.contains(55);
    }

    @Override // com.google.android.gms.plus.model.moments.ItemScope
    public boolean hasWorstRating() {
        return this.aon.contains(56);
    }

    public int hashCode() {
        int iHashCode = 0;
        Iterator<kr.a<?, ?>> it = aom.values().iterator();
        while (true) {
            int i = iHashCode;
            if (!it.hasNext()) {
                return i;
            }
            kr.a<?, ?> next = it.next();
            if (a(next)) {
                iHashCode = b(next).hashCode() + i + next.hR();
            } else {
                iHashCode = i;
            }
        }
    }

    @Override // com.google.android.gms.common.data.Freezable
    public boolean isDataValid() {
        return true;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: oI, reason: merged with bridge method [inline-methods] */
    public pd freeze() {
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        pe peVar = CREATOR;
        pe.a(this, out, flags);
    }
}
