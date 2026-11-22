package com.google.android.gms.internal;

import android.os.Parcel;
import com.flurry.android.AdCreative;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMRequest;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class pi extends ks implements Person {
    public static final pj CREATOR = new pj();
    private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
    String CE;
    final int CK;
    String FR;
    String OS;
    final Set<Integer> aon;
    int apA;
    String apB;
    List<h> apC;
    boolean apD;
    String apl;
    a apm;
    String apn;
    String apo;
    int app;
    b apq;
    String apr;
    c aps;
    boolean apt;
    d apu;
    String apv;
    int apw;
    List<f> apx;
    List<g> apy;
    int apz;
    int ow;
    String vf;

    public static final class a extends ks implements Person.AgeRange {
        public static final pk CREATOR = new pk();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        final Set<Integer> aon;
        int apE;
        int apF;

        static {
            aom.put("max", kr.a.i("max", 2));
            aom.put("min", kr.a.i("min", 3));
        }

        public a() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        a(Set<Integer> set, int i, int i2, int i3) {
            this.aon = set;
            this.CK = i;
            this.apE = i2;
            this.apF = i3;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return Integer.valueOf(this.apE);
                case 3:
                    return Integer.valueOf(this.apF);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            pk pkVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            a aVar = (a) obj;
            for (kr.a<?, ?> aVar2 : aom.values()) {
                if (a(aVar2)) {
                    if (aVar.a(aVar2) && b(aVar2).equals(aVar.b(aVar2))) {
                    }
                    return false;
                }
                if (aVar.a(aVar2)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public int getMax() {
            return this.apE;
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public int getMin() {
            return this.apF;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public boolean hasMax() {
            return this.aon.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.AgeRange
        public boolean hasMin() {
            return this.aon.contains(3);
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
        /* JADX INFO: renamed from: oM, reason: merged with bridge method [inline-methods] */
        public a freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            pk pkVar = CREATOR;
            pk.a(this, out, flags);
        }
    }

    public static final class b extends ks implements Person.Cover {
        public static final pl CREATOR = new pl();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        final Set<Integer> aon;
        a apG;
        C0119b apH;
        int apI;

        public static final class a extends ks implements Person.Cover.CoverInfo {
            public static final pm CREATOR = new pm();
            private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
            final int CK;
            final Set<Integer> aon;
            int apJ;
            int apK;

            static {
                aom.put("leftImageOffset", kr.a.i("leftImageOffset", 2));
                aom.put("topImageOffset", kr.a.i("topImageOffset", 3));
            }

            public a() {
                this.CK = 1;
                this.aon = new HashSet();
            }

            a(Set<Integer> set, int i, int i2, int i3) {
                this.aon = set;
                this.CK = i;
                this.apJ = i2;
                this.apK = i3;
            }

            @Override // com.google.android.gms.internal.kr
            protected boolean a(kr.a aVar) {
                return this.aon.contains(Integer.valueOf(aVar.hR()));
            }

            @Override // com.google.android.gms.internal.kr
            protected Object b(kr.a aVar) {
                switch (aVar.hR()) {
                    case 2:
                        return Integer.valueOf(this.apJ);
                    case 3:
                        return Integer.valueOf(this.apK);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                pm pmVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof a)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                a aVar = (a) obj;
                for (kr.a<?, ?> aVar2 : aom.values()) {
                    if (a(aVar2)) {
                        if (aVar.a(aVar2) && b(aVar2).equals(aVar.b(aVar2))) {
                        }
                        return false;
                    }
                    if (aVar.a(aVar2)) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public int getLeftImageOffset() {
                return this.apJ;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public int getTopImageOffset() {
                return this.apK;
            }

            @Override // com.google.android.gms.internal.kr
            public HashMap<String, kr.a<?, ?>> hK() {
                return aom;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public boolean hasLeftImageOffset() {
                return this.aon.contains(2);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverInfo
            public boolean hasTopImageOffset() {
                return this.aon.contains(3);
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
            /* JADX INFO: renamed from: oO, reason: merged with bridge method [inline-methods] */
            public a freeze() {
                return this;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel out, int flags) {
                pm pmVar = CREATOR;
                pm.a(this, out, flags);
            }
        }

        /* JADX INFO: renamed from: com.google.android.gms.internal.pi$b$b, reason: collision with other inner class name */
        public static final class C0119b extends ks implements Person.Cover.CoverPhoto {
            public static final pn CREATOR = new pn();
            private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
            final int CK;
            final Set<Integer> aon;
            int li;
            int lj;
            String vf;

            static {
                aom.put("height", kr.a.i("height", 2));
                aom.put("url", kr.a.l("url", 3));
                aom.put("width", kr.a.i("width", 4));
            }

            public C0119b() {
                this.CK = 1;
                this.aon = new HashSet();
            }

            C0119b(Set<Integer> set, int i, int i2, String str, int i3) {
                this.aon = set;
                this.CK = i;
                this.lj = i2;
                this.vf = str;
                this.li = i3;
            }

            @Override // com.google.android.gms.internal.kr
            protected boolean a(kr.a aVar) {
                return this.aon.contains(Integer.valueOf(aVar.hR()));
            }

            @Override // com.google.android.gms.internal.kr
            protected Object b(kr.a aVar) {
                switch (aVar.hR()) {
                    case 2:
                        return Integer.valueOf(this.lj);
                    case 3:
                        return this.vf;
                    case 4:
                        return Integer.valueOf(this.li);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
                }
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                pn pnVar = CREATOR;
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof C0119b)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0119b c0119b = (C0119b) obj;
                for (kr.a<?, ?> aVar : aom.values()) {
                    if (a(aVar)) {
                        if (c0119b.a(aVar) && b(aVar).equals(c0119b.b(aVar))) {
                        }
                        return false;
                    }
                    if (c0119b.a(aVar)) {
                        return false;
                    }
                }
                return true;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public int getHeight() {
                return this.lj;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public String getUrl() {
                return this.vf;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public int getWidth() {
                return this.li;
            }

            @Override // com.google.android.gms.internal.kr
            public HashMap<String, kr.a<?, ?>> hK() {
                return aom;
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public boolean hasHeight() {
                return this.aon.contains(2);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public boolean hasUrl() {
                return this.aon.contains(3);
            }

            @Override // com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto
            public boolean hasWidth() {
                return this.aon.contains(4);
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
            /* JADX INFO: renamed from: oP, reason: merged with bridge method [inline-methods] */
            public C0119b freeze() {
                return this;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel out, int flags) {
                pn pnVar = CREATOR;
                pn.a(this, out, flags);
            }
        }

        static {
            aom.put("coverInfo", kr.a.a("coverInfo", 2, a.class));
            aom.put("coverPhoto", kr.a.a("coverPhoto", 3, C0119b.class));
            aom.put("layout", kr.a.a("layout", 4, new ko().h(AdCreative.kFormatBanner, 0), false));
        }

        public b() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        b(Set<Integer> set, int i, a aVar, C0119b c0119b, int i2) {
            this.aon = set;
            this.CK = i;
            this.apG = aVar;
            this.apH = c0119b;
            this.apI = i2;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return this.apG;
                case 3:
                    return this.apH;
                case 4:
                    return Integer.valueOf(this.apI);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            pl plVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            b bVar = (b) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (bVar.a(aVar) && b(aVar).equals(bVar.b(aVar))) {
                    }
                    return false;
                }
                if (bVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public Person.Cover.CoverInfo getCoverInfo() {
            return this.apG;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public Person.Cover.CoverPhoto getCoverPhoto() {
            return this.apH;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public int getLayout() {
            return this.apI;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public boolean hasCoverInfo() {
            return this.aon.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public boolean hasCoverPhoto() {
            return this.aon.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Cover
        public boolean hasLayout() {
            return this.aon.contains(4);
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
        /* JADX INFO: renamed from: oN, reason: merged with bridge method [inline-methods] */
        public b freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            pl plVar = CREATOR;
            pl.a(this, out, flags);
        }
    }

    public static final class c extends ks implements Person.Image {
        public static final po CREATOR = new po();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        final Set<Integer> aon;
        String vf;

        static {
            aom.put("url", kr.a.l("url", 2));
        }

        public c() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        public c(String str) {
            this.aon = new HashSet();
            this.CK = 1;
            this.vf = str;
            this.aon.add(2);
        }

        c(Set<Integer> set, int i, String str) {
            this.aon = set;
            this.CK = i;
            this.vf = str;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return this.vf;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            po poVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            c cVar = (c) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (cVar.a(aVar) && b(aVar).equals(cVar.b(aVar))) {
                    }
                    return false;
                }
                if (cVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Image
        public String getUrl() {
            return this.vf;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Image
        public boolean hasUrl() {
            return this.aon.contains(2);
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
        /* JADX INFO: renamed from: oQ, reason: merged with bridge method [inline-methods] */
        public c freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            po poVar = CREATOR;
            po.a(this, out, flags);
        }
    }

    public static final class d extends ks implements Person.Name {
        public static final pp CREATOR = new pp();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        String aoL;
        String aoO;
        final Set<Integer> aon;
        String apL;
        String apM;
        String apN;
        String apO;

        static {
            aom.put("familyName", kr.a.l("familyName", 2));
            aom.put("formatted", kr.a.l("formatted", 3));
            aom.put("givenName", kr.a.l("givenName", 4));
            aom.put("honorificPrefix", kr.a.l("honorificPrefix", 5));
            aom.put("honorificSuffix", kr.a.l("honorificSuffix", 6));
            aom.put("middleName", kr.a.l("middleName", 7));
        }

        public d() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        d(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.aon = set;
            this.CK = i;
            this.aoL = str;
            this.apL = str2;
            this.aoO = str3;
            this.apM = str4;
            this.apN = str5;
            this.apO = str6;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return this.aoL;
                case 3:
                    return this.apL;
                case 4:
                    return this.aoO;
                case 5:
                    return this.apM;
                case 6:
                    return this.apN;
                case 7:
                    return this.apO;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            pp ppVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof d)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            d dVar = (d) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (dVar.a(aVar) && b(aVar).equals(dVar.b(aVar))) {
                    }
                    return false;
                }
                if (dVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getFamilyName() {
            return this.aoL;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getFormatted() {
            return this.apL;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getGivenName() {
            return this.aoO;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getHonorificPrefix() {
            return this.apM;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getHonorificSuffix() {
            return this.apN;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public String getMiddleName() {
            return this.apO;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasFamilyName() {
            return this.aon.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasFormatted() {
            return this.aon.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasGivenName() {
            return this.aon.contains(4);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasHonorificPrefix() {
            return this.aon.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasHonorificSuffix() {
            return this.aon.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Name
        public boolean hasMiddleName() {
            return this.aon.contains(7);
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
        /* JADX INFO: renamed from: oR, reason: merged with bridge method [inline-methods] */
        public d freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            pp ppVar = CREATOR;
            pp.a(this, out, flags);
        }
    }

    public static class e {
        public static int ck(String str) {
            if (str.equals("person")) {
                return 0;
            }
            if (str.equals("page")) {
                return 1;
            }
            throw new IllegalArgumentException("Unknown objectType string: " + str);
        }
    }

    public static final class f extends ks implements Person.Organizations {
        public static final pq CREATOR = new pq();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        int Gt;
        String OH;
        String UO;
        String aoK;
        final Set<Integer> aon;
        String apP;
        String apQ;
        boolean apR;
        String apa;
        String mName;

        static {
            aom.put("department", kr.a.l("department", 2));
            aom.put("description", kr.a.l("description", 3));
            aom.put("endDate", kr.a.l("endDate", 4));
            aom.put("location", kr.a.l("location", 5));
            aom.put("name", kr.a.l("name", 6));
            aom.put("primary", kr.a.k("primary", 7));
            aom.put("startDate", kr.a.l("startDate", 8));
            aom.put("title", kr.a.l("title", 9));
            aom.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, kr.a.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, 10, new ko().h("work", 0).h("school", 1), false));
        }

        public f() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        f(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.aon = set;
            this.CK = i;
            this.apP = str;
            this.UO = str2;
            this.aoK = str3;
            this.apQ = str4;
            this.mName = str5;
            this.apR = z;
            this.apa = str6;
            this.OH = str7;
            this.Gt = i2;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return this.apP;
                case 3:
                    return this.UO;
                case 4:
                    return this.aoK;
                case 5:
                    return this.apQ;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.apR);
                case 8:
                    return this.apa;
                case 9:
                    return this.OH;
                case 10:
                    return Integer.valueOf(this.Gt);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            pq pqVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            f fVar = (f) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (fVar.a(aVar) && b(aVar).equals(fVar.b(aVar))) {
                    }
                    return false;
                }
                if (fVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getDepartment() {
            return this.apP;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getDescription() {
            return this.UO;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getEndDate() {
            return this.aoK;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getLocation() {
            return this.apQ;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getName() {
            return this.mName;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getStartDate() {
            return this.apa;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public String getTitle() {
            return this.OH;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public int getType() {
            return this.Gt;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasDepartment() {
            return this.aon.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasDescription() {
            return this.aon.contains(3);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasEndDate() {
            return this.aon.contains(4);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasLocation() {
            return this.aon.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasName() {
            return this.aon.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasPrimary() {
            return this.aon.contains(7);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasStartDate() {
            return this.aon.contains(8);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasTitle() {
            return this.aon.contains(9);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean hasType() {
            return this.aon.contains(10);
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

        @Override // com.google.android.gms.plus.model.people.Person.Organizations
        public boolean isPrimary() {
            return this.apR;
        }

        @Override // com.google.android.gms.common.data.Freezable
        /* JADX INFO: renamed from: oS, reason: merged with bridge method [inline-methods] */
        public f freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            pq pqVar = CREATOR;
            pq.a(this, out, flags);
        }
    }

    public static final class g extends ks implements Person.PlacesLived {
        public static final pr CREATOR = new pr();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        final Set<Integer> aon;
        boolean apR;
        String mValue;

        static {
            aom.put("primary", kr.a.k("primary", 2));
            aom.put("value", kr.a.l("value", 3));
        }

        public g() {
            this.CK = 1;
            this.aon = new HashSet();
        }

        g(Set<Integer> set, int i, boolean z, String str) {
            this.aon = set;
            this.CK = i;
            this.apR = z;
            this.mValue = str;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 2:
                    return Boolean.valueOf(this.apR);
                case 3:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            pr prVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof g)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            g gVar = (g) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (gVar.a(aVar) && b(aVar).equals(gVar.b(aVar))) {
                    }
                    return false;
                }
                if (gVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public String getValue() {
            return this.mValue;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public boolean hasPrimary() {
            return this.aon.contains(2);
        }

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public boolean hasValue() {
            return this.aon.contains(3);
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

        @Override // com.google.android.gms.plus.model.people.Person.PlacesLived
        public boolean isPrimary() {
            return this.apR;
        }

        @Override // com.google.android.gms.common.data.Freezable
        /* JADX INFO: renamed from: oT, reason: merged with bridge method [inline-methods] */
        public g freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            pr prVar = CREATOR;
            pr.a(this, out, flags);
        }
    }

    public static final class h extends ks implements Person.Urls {
        public static final ps CREATOR = new ps();
        private static final HashMap<String, kr.a<?, ?>> aom = new HashMap<>();
        final int CK;
        int Gt;
        String XL;
        final Set<Integer> aon;
        private final int apS;
        String mValue;

        static {
            aom.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, kr.a.l(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
            aom.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, kr.a.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, 6, new ko().h("home", 0).h("work", 1).h("blog", 2).h(Scopes.PROFILE, 3).h("other", 4).h("otherProfile", 5).h("contributor", 6).h("website", 7), false));
            aom.put("value", kr.a.l("value", 4));
        }

        public h() {
            this.apS = 4;
            this.CK = 1;
            this.aon = new HashSet();
        }

        h(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
            this.apS = 4;
            this.aon = set;
            this.CK = i;
            this.XL = str;
            this.Gt = i2;
            this.mValue = str2;
        }

        @Override // com.google.android.gms.internal.kr
        protected boolean a(kr.a aVar) {
            return this.aon.contains(Integer.valueOf(aVar.hR()));
        }

        @Override // com.google.android.gms.internal.kr
        protected Object b(kr.a aVar) {
            switch (aVar.hR()) {
                case 4:
                    return this.mValue;
                case 5:
                    return this.XL;
                case 6:
                    return Integer.valueOf(this.Gt);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            ps psVar = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof h)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            h hVar = (h) obj;
            for (kr.a<?, ?> aVar : aom.values()) {
                if (a(aVar)) {
                    if (hVar.a(aVar) && b(aVar).equals(hVar.b(aVar))) {
                    }
                    return false;
                }
                if (hVar.a(aVar)) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public String getLabel() {
            return this.XL;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public int getType() {
            return this.Gt;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public String getValue() {
            return this.mValue;
        }

        @Override // com.google.android.gms.internal.kr
        public HashMap<String, kr.a<?, ?>> hK() {
            return aom;
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public boolean hasLabel() {
            return this.aon.contains(5);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public boolean hasType() {
            return this.aon.contains(6);
        }

        @Override // com.google.android.gms.plus.model.people.Person.Urls
        public boolean hasValue() {
            return this.aon.contains(4);
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

        @Deprecated
        public int oU() {
            return 4;
        }

        @Override // com.google.android.gms.common.data.Freezable
        /* JADX INFO: renamed from: oV, reason: merged with bridge method [inline-methods] */
        public h freeze() {
            return this;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            ps psVar = CREATOR;
            ps.a(this, out, flags);
        }
    }

    static {
        aom.put("aboutMe", kr.a.l("aboutMe", 2));
        aom.put("ageRange", kr.a.a("ageRange", 3, a.class));
        aom.put("birthday", kr.a.l("birthday", 4));
        aom.put("braggingRights", kr.a.l("braggingRights", 5));
        aom.put("circledByCount", kr.a.i("circledByCount", 6));
        aom.put("cover", kr.a.a("cover", 7, b.class));
        aom.put("currentLocation", kr.a.l("currentLocation", 8));
        aom.put("displayName", kr.a.l("displayName", 9));
        aom.put(MMRequest.KEY_GENDER, kr.a.a(MMRequest.KEY_GENDER, 12, new ko().h(MMRequest.GENDER_MALE, 0).h(MMRequest.GENDER_FEMALE, 1).h("other", 2), false));
        aom.put("id", kr.a.l("id", 14));
        aom.put("image", kr.a.a("image", 15, c.class));
        aom.put("isPlusUser", kr.a.k("isPlusUser", 16));
        aom.put("language", kr.a.l("language", 18));
        aom.put("name", kr.a.a("name", 19, d.class));
        aom.put("nickname", kr.a.l("nickname", 20));
        aom.put("objectType", kr.a.a("objectType", 21, new ko().h("person", 0).h("page", 1), false));
        aom.put("organizations", kr.a.b("organizations", 22, f.class));
        aom.put("placesLived", kr.a.b("placesLived", 23, g.class));
        aom.put("plusOneCount", kr.a.i("plusOneCount", 24));
        aom.put("relationshipStatus", kr.a.a("relationshipStatus", 25, new ko().h(MMRequest.MARITAL_SINGLE, 0).h("in_a_relationship", 1).h(MMRequest.MARITAL_ENGAGED, 2).h(MMRequest.MARITAL_MARRIED, 3).h("its_complicated", 4).h("open_relationship", 5).h("widowed", 6).h("in_domestic_partnership", 7).h("in_civil_union", 8), false));
        aom.put("tagline", kr.a.l("tagline", 26));
        aom.put("url", kr.a.l("url", 27));
        aom.put("urls", kr.a.b("urls", 28, h.class));
        aom.put("verified", kr.a.k("verified", 29));
    }

    public pi() {
        this.CK = 1;
        this.aon = new HashSet();
    }

    public pi(String str, String str2, c cVar, int i, String str3) {
        this.CK = 1;
        this.aon = new HashSet();
        this.OS = str;
        this.aon.add(9);
        this.CE = str2;
        this.aon.add(14);
        this.aps = cVar;
        this.aon.add(15);
        this.apw = i;
        this.aon.add(21);
        this.vf = str3;
        this.aon.add(27);
    }

    pi(Set<Integer> set, int i, String str, a aVar, String str2, String str3, int i2, b bVar, String str4, String str5, int i3, String str6, c cVar, boolean z, String str7, d dVar, String str8, int i4, List<f> list, List<g> list2, int i5, int i6, String str9, String str10, List<h> list3, boolean z2) {
        this.aon = set;
        this.CK = i;
        this.apl = str;
        this.apm = aVar;
        this.apn = str2;
        this.apo = str3;
        this.app = i2;
        this.apq = bVar;
        this.apr = str4;
        this.OS = str5;
        this.ow = i3;
        this.CE = str6;
        this.aps = cVar;
        this.apt = z;
        this.FR = str7;
        this.apu = dVar;
        this.apv = str8;
        this.apw = i4;
        this.apx = list;
        this.apy = list2;
        this.apz = i5;
        this.apA = i6;
        this.apB = str9;
        this.vf = str10;
        this.apC = list3;
        this.apD = z2;
    }

    public static pi i(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        pi piVarCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return piVarCreateFromParcel;
    }

    @Override // com.google.android.gms.internal.kr
    protected boolean a(kr.a aVar) {
        return this.aon.contains(Integer.valueOf(aVar.hR()));
    }

    @Override // com.google.android.gms.internal.kr
    protected Object b(kr.a aVar) {
        switch (aVar.hR()) {
            case 2:
                return this.apl;
            case 3:
                return this.apm;
            case 4:
                return this.apn;
            case 5:
                return this.apo;
            case 6:
                return Integer.valueOf(this.app);
            case 7:
                return this.apq;
            case 8:
                return this.apr;
            case 9:
                return this.OS;
            case 10:
            case 11:
            case 13:
            case 17:
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + aVar.hR());
            case 12:
                return Integer.valueOf(this.ow);
            case 14:
                return this.CE;
            case 15:
                return this.aps;
            case 16:
                return Boolean.valueOf(this.apt);
            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
                return this.FR;
            case 19:
                return this.apu;
            case 20:
                return this.apv;
            case MMException.DISPLAY_AD_EXPIRED /* 21 */:
                return Integer.valueOf(this.apw);
            case MMException.DISPLAY_AD_NOT_FOUND /* 22 */:
                return this.apx;
            case MMException.DISPLAY_AD_ALREADY_DISPLAYED /* 23 */:
                return this.apy;
            case MMException.DISPLAY_AD_NOT_PERMITTED /* 24 */:
                return Integer.valueOf(this.apz);
            case MMException.AD_BROKEN_REFERENCE /* 25 */:
                return Integer.valueOf(this.apA);
            case MMException.AD_NO_ACTIVITY /* 26 */:
                return this.apB;
            case 27:
                return this.vf;
            case 28:
                return this.apC;
            case 29:
                return Boolean.valueOf(this.apD);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        pj pjVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof pi)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        pi piVar = (pi) obj;
        for (kr.a<?, ?> aVar : aom.values()) {
            if (a(aVar)) {
                if (piVar.a(aVar) && b(aVar).equals(piVar.b(aVar))) {
                }
                return false;
            }
            if (piVar.a(aVar)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getAboutMe() {
        return this.apl;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public Person.AgeRange getAgeRange() {
        return this.apm;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getBirthday() {
        return this.apn;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getBraggingRights() {
        return this.apo;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public int getCircledByCount() {
        return this.app;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public Person.Cover getCover() {
        return this.apq;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getCurrentLocation() {
        return this.apr;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getDisplayName() {
        return this.OS;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public int getGender() {
        return this.ow;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getId() {
        return this.CE;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public Person.Image getImage() {
        return this.aps;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getLanguage() {
        return this.FR;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public Person.Name getName() {
        return this.apu;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getNickname() {
        return this.apv;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public int getObjectType() {
        return this.apw;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public List<Person.Organizations> getOrganizations() {
        return (ArrayList) this.apx;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public List<Person.PlacesLived> getPlacesLived() {
        return (ArrayList) this.apy;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public int getPlusOneCount() {
        return this.apz;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public int getRelationshipStatus() {
        return this.apA;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getTagline() {
        return this.apB;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public String getUrl() {
        return this.vf;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public List<Person.Urls> getUrls() {
        return (ArrayList) this.apC;
    }

    @Override // com.google.android.gms.internal.kr
    public HashMap<String, kr.a<?, ?>> hK() {
        return aom;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasAboutMe() {
        return this.aon.contains(2);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasAgeRange() {
        return this.aon.contains(3);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasBirthday() {
        return this.aon.contains(4);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasBraggingRights() {
        return this.aon.contains(5);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasCircledByCount() {
        return this.aon.contains(6);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasCover() {
        return this.aon.contains(7);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasCurrentLocation() {
        return this.aon.contains(8);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasDisplayName() {
        return this.aon.contains(9);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasGender() {
        return this.aon.contains(12);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasId() {
        return this.aon.contains(14);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasImage() {
        return this.aon.contains(15);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasIsPlusUser() {
        return this.aon.contains(16);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasLanguage() {
        return this.aon.contains(18);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasName() {
        return this.aon.contains(19);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasNickname() {
        return this.aon.contains(20);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasObjectType() {
        return this.aon.contains(21);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasOrganizations() {
        return this.aon.contains(22);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasPlacesLived() {
        return this.aon.contains(23);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasPlusOneCount() {
        return this.aon.contains(24);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasRelationshipStatus() {
        return this.aon.contains(25);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasTagline() {
        return this.aon.contains(26);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasUrl() {
        return this.aon.contains(27);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasUrls() {
        return this.aon.contains(28);
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean hasVerified() {
        return this.aon.contains(29);
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

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean isPlusUser() {
        return this.apt;
    }

    @Override // com.google.android.gms.plus.model.people.Person
    public boolean isVerified() {
        return this.apD;
    }

    @Override // com.google.android.gms.common.data.Freezable
    /* JADX INFO: renamed from: oL, reason: merged with bridge method [inline-methods] */
    public pi freeze() {
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        pj pjVar = CREATOR;
        pj.a(this, out, flags);
    }
}
