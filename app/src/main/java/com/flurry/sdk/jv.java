package com.flurry.sdk;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class jv {
    public static final ir<Class> a = new ir<Class>() { // from class: com.flurry.sdk.jv.1
        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Class cls) throws IOException {
            if (cls == null) {
                jzVar.f();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Class b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final is b = a(Class.class, a);
    public static final ir<BitSet> c = new ir<BitSet>() { // from class: com.flurry.sdk.jv.12
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BitSet b(jx jxVar) throws IOException {
            boolean zI;
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            BitSet bitSet = new BitSet();
            jxVar.a();
            jy jyVarF = jxVar.f();
            int i2 = 0;
            while (jyVarF != jy.END_ARRAY) {
                switch (AnonymousClass25.a[jyVarF.ordinal()]) {
                    case 1:
                        zI = jxVar.m() != 0;
                        break;
                    case 2:
                        zI = jxVar.i();
                        break;
                    case 3:
                        String strH = jxVar.h();
                        try {
                            zI = Integer.parseInt(strH) != 0;
                        } catch (NumberFormatException e2) {
                            throw new io("Error: Expecting: bitset number value (1, 0), Found: " + strH);
                        }
                        break;
                    default:
                        throw new io("Invalid bitset value type: " + jyVarF);
                }
                if (zI) {
                    bitSet.set(i2);
                }
                i2++;
                jyVarF = jxVar.f();
            }
            jxVar.b();
            return bitSet;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                jzVar.f();
                return;
            }
            jzVar.b();
            for (int i2 = 0; i2 < bitSet.length(); i2++) {
                jzVar.a(bitSet.get(i2) ? 1 : 0);
            }
            jzVar.c();
        }
    };
    public static final is d = a(BitSet.class, c);
    public static final ir<Boolean> e = new ir<Boolean>() { // from class: com.flurry.sdk.jv.22
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            if (jxVar.f() == jy.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(jxVar.h()));
            }
            return Boolean.valueOf(jxVar.i());
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Boolean bool) throws IOException {
            if (bool == null) {
                jzVar.f();
            } else {
                jzVar.a(bool.booleanValue());
            }
        }
    };
    public static final ir<Boolean> f = new ir<Boolean>() { // from class: com.flurry.sdk.jv.26
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return Boolean.valueOf(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Boolean bool) throws IOException {
            jzVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final is g = a(Boolean.TYPE, Boolean.class, e);
    public static final ir<Number> h = new ir<Number>() { // from class: com.flurry.sdk.jv.27
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) jxVar.m());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final is i = a(Byte.TYPE, Byte.class, h);
    public static final ir<Number> j = new ir<Number>() { // from class: com.flurry.sdk.jv.28
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) jxVar.m());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final is k = a(Short.TYPE, Short.class, j);
    public static final ir<Number> l = new ir<Number>() { // from class: com.flurry.sdk.jv.29
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return Integer.valueOf(jxVar.m());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final is m = a(Integer.TYPE, Integer.class, l);
    public static final ir<Number> n = new ir<Number>() { // from class: com.flurry.sdk.jv.30
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return Long.valueOf(jxVar.l());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final ir<Number> o = new ir<Number>() { // from class: com.flurry.sdk.jv.31
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return Float.valueOf((float) jxVar.k());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final ir<Number> p = new ir<Number>() { // from class: com.flurry.sdk.jv.2
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return Double.valueOf(jxVar.k());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final ir<Number> q = new ir<Number>() { // from class: com.flurry.sdk.jv.3
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number b(jx jxVar) throws IOException {
            jy jyVarF = jxVar.f();
            switch (jyVarF) {
                case NUMBER:
                    return new jd(jxVar.h());
                case BOOLEAN:
                case STRING:
                default:
                    throw new io("Expecting number, got: " + jyVarF);
                case NULL:
                    jxVar.j();
                    return null;
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Number number) throws IOException {
            jzVar.a(number);
        }
    };
    public static final is r = a(Number.class, q);
    public static final ir<Character> s = new ir<Character>() { // from class: com.flurry.sdk.jv.4
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Character b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            String strH = jxVar.h();
            if (strH.length() != 1) {
                throw new io("Expecting character, got: " + strH);
            }
            return Character.valueOf(strH.charAt(0));
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Character ch) throws IOException {
            jzVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final is t = a(Character.TYPE, Character.class, s);
    public static final ir<String> u = new ir<String>() { // from class: com.flurry.sdk.jv.5
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String b(jx jxVar) throws IOException {
            jy jyVarF = jxVar.f();
            if (jyVarF == jy.NULL) {
                jxVar.j();
                return null;
            }
            if (jyVarF == jy.BOOLEAN) {
                return Boolean.toString(jxVar.i());
            }
            return jxVar.h();
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, String str) throws IOException {
            jzVar.b(str);
        }
    };
    public static final ir<BigDecimal> v = new ir<BigDecimal>() { // from class: com.flurry.sdk.jv.6
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return new BigDecimal(jxVar.h());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, BigDecimal bigDecimal) throws IOException {
            jzVar.a(bigDecimal);
        }
    };
    public static final ir<BigInteger> w = new ir<BigInteger>() { // from class: com.flurry.sdk.jv.7
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BigInteger b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                return new BigInteger(jxVar.h());
            } catch (NumberFormatException e2) {
                throw new io(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, BigInteger bigInteger) throws IOException {
            jzVar.a(bigInteger);
        }
    };
    public static final is x = a(String.class, u);
    public static final ir<StringBuilder> y = new ir<StringBuilder>() { // from class: com.flurry.sdk.jv.8
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return new StringBuilder(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, StringBuilder sb) throws IOException {
            jzVar.b(sb == null ? null : sb.toString());
        }
    };
    public static final is z = a(StringBuilder.class, y);
    public static final ir<StringBuffer> A = new ir<StringBuffer>() { // from class: com.flurry.sdk.jv.9
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return new StringBuffer(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, StringBuffer stringBuffer) throws IOException {
            jzVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final is B = a(StringBuffer.class, A);
    public static final ir<URL> C = new ir<URL>() { // from class: com.flurry.sdk.jv.10
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public URL b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            String strH = jxVar.h();
            if ("null".equals(strH)) {
                return null;
            }
            return new URL(strH);
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, URL url) throws IOException {
            jzVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final is D = a(URL.class, C);
    public static final ir<URI> E = new ir<URI>() { // from class: com.flurry.sdk.jv.11
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public URI b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            try {
                String strH = jxVar.h();
                if ("null".equals(strH)) {
                    return null;
                }
                return new URI(strH);
            } catch (URISyntaxException e2) {
                throw new ih(e2);
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, URI uri) throws IOException {
            jzVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final is F = a(URI.class, E);
    public static final ir<InetAddress> G = new ir<InetAddress>() { // from class: com.flurry.sdk.jv.13
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public InetAddress b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return InetAddress.getByName(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, InetAddress inetAddress) throws IOException {
            jzVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final is H = b(InetAddress.class, G);
    public static final ir<UUID> I = new ir<UUID>() { // from class: com.flurry.sdk.jv.14
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public UUID b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return UUID.fromString(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, UUID uuid) throws IOException {
            jzVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final is J = a(UUID.class, I);
    public static final is K = new is() { // from class: com.flurry.sdk.jv.15
        @Override // com.flurry.sdk.is
        public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
            if (jwVar.a() != Timestamp.class) {
                return null;
            }
            final ir<T> irVarA = iaVar.a(Date.class);
            return (ir<T>) new ir<Timestamp>() { // from class: com.flurry.sdk.jv.15.1
                @Override // com.flurry.sdk.ir
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public Timestamp b(jx jxVar) throws IOException {
                    Date date = (Date) irVarA.b(jxVar);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                @Override // com.flurry.sdk.ir
                public void a(jz jzVar, Timestamp timestamp) throws IOException {
                    irVarA.a(jzVar, timestamp);
                }
            };
        }
    };
    public static final ir<Calendar> L = new ir<Calendar>() { // from class: com.flurry.sdk.jv.16
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Calendar b(jx jxVar) throws IOException {
            int i2 = 0;
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            jxVar.c();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (jxVar.f() != jy.END_OBJECT) {
                String strG = jxVar.g();
                int iM = jxVar.m();
                if ("year".equals(strG)) {
                    i7 = iM;
                } else if ("month".equals(strG)) {
                    i6 = iM;
                } else if ("dayOfMonth".equals(strG)) {
                    i5 = iM;
                } else if ("hourOfDay".equals(strG)) {
                    i4 = iM;
                } else if ("minute".equals(strG)) {
                    i3 = iM;
                } else if ("second".equals(strG)) {
                    i2 = iM;
                }
            }
            jxVar.d();
            return new GregorianCalendar(i7, i6, i5, i4, i3, i2);
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                jzVar.f();
                return;
            }
            jzVar.d();
            jzVar.a("year");
            jzVar.a(calendar.get(1));
            jzVar.a("month");
            jzVar.a(calendar.get(2));
            jzVar.a("dayOfMonth");
            jzVar.a(calendar.get(5));
            jzVar.a("hourOfDay");
            jzVar.a(calendar.get(11));
            jzVar.a("minute");
            jzVar.a(calendar.get(12));
            jzVar.a("second");
            jzVar.a(calendar.get(13));
            jzVar.e();
        }
    };
    public static final is M = b(Calendar.class, GregorianCalendar.class, L);
    public static final ir<Locale> N = new ir<Locale>() { // from class: com.flurry.sdk.jv.17
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Locale b(jx jxVar) throws IOException {
            if (jxVar.f() == jy.NULL) {
                jxVar.j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jxVar.h(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (strNextToken2 == null && strNextToken3 == null) {
                return new Locale(strNextToken);
            }
            if (strNextToken3 == null) {
                return new Locale(strNextToken, strNextToken2);
            }
            return new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, Locale locale) throws IOException {
            jzVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final is O = a(Locale.class, N);
    public static final ir<ig> P = new ir<ig>() { // from class: com.flurry.sdk.jv.18
        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ig b(jx jxVar) throws IOException {
            switch (AnonymousClass25.a[jxVar.f().ordinal()]) {
                case 1:
                    return new il(new jd(jxVar.h()));
                case 2:
                    return new il(Boolean.valueOf(jxVar.i()));
                case 3:
                    return new il(jxVar.h());
                case 4:
                    jxVar.j();
                    return ii.a;
                case 5:
                    id idVar = new id();
                    jxVar.a();
                    while (jxVar.e()) {
                        idVar.a(b(jxVar));
                    }
                    jxVar.b();
                    return idVar;
                case 6:
                    ij ijVar = new ij();
                    jxVar.c();
                    while (jxVar.e()) {
                        ijVar.a(jxVar.g(), b(jxVar));
                    }
                    jxVar.d();
                    return ijVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, ig igVar) throws IOException {
            if (igVar == null || igVar.j()) {
                jzVar.f();
                return;
            }
            if (igVar.i()) {
                il ilVarM = igVar.m();
                if (ilVarM.p()) {
                    jzVar.a(ilVarM.a());
                    return;
                } else if (ilVarM.o()) {
                    jzVar.a(ilVarM.f());
                    return;
                } else {
                    jzVar.b(ilVarM.b());
                    return;
                }
            }
            if (igVar.g()) {
                jzVar.b();
                Iterator<ig> it = igVar.l().iterator();
                while (it.hasNext()) {
                    a(jzVar, it.next());
                }
                jzVar.c();
                return;
            }
            if (igVar.h()) {
                jzVar.d();
                for (Map.Entry<String, ig> entry : igVar.k().o()) {
                    jzVar.a(entry.getKey());
                    a(jzVar, entry.getValue());
                }
                jzVar.e();
                return;
            }
            throw new IllegalArgumentException("Couldn't write " + igVar.getClass());
        }
    };
    public static final is Q = b(ig.class, P);
    public static final is R = a();

    static final class a<T extends Enum<T>> extends ir<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    iv ivVar = (iv) cls.getField(strName).getAnnotation(iv.class);
                    String strA = ivVar != null ? ivVar.a() : strName;
                    this.a.put(strA, t);
                    this.b.put(t, strA);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        @Override // com.flurry.sdk.ir
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public T b(jx jxVar) throws IOException {
            if (jxVar.f() != jy.NULL) {
                return this.a.get(jxVar.h());
            }
            jxVar.j();
            return null;
        }

        @Override // com.flurry.sdk.ir
        public void a(jz jzVar, T t) throws IOException {
            jzVar.b(t == null ? null : this.b.get(t));
        }
    }

    public static is a() {
        return new is() { // from class: com.flurry.sdk.jv.19
            @Override // com.flurry.sdk.is
            public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
                Class<? super T> clsA = jwVar.a();
                if (!Enum.class.isAssignableFrom(clsA) || clsA == Enum.class) {
                    return null;
                }
                if (!clsA.isEnum()) {
                    clsA = clsA.getSuperclass();
                }
                return new a(clsA);
            }
        };
    }

    public static <TT> is a(final Class<TT> cls, final ir<TT> irVar) {
        return new is() { // from class: com.flurry.sdk.jv.20
            @Override // com.flurry.sdk.is
            public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
                if (jwVar.a() == cls) {
                    return irVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + irVar + "]";
            }
        };
    }

    public static <TT> is a(final Class<TT> cls, final Class<TT> cls2, final ir<? super TT> irVar) {
        return new is() { // from class: com.flurry.sdk.jv.21
            @Override // com.flurry.sdk.is
            public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
                Class<? super T> clsA = jwVar.a();
                if (clsA == cls || clsA == cls2) {
                    return irVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + irVar + "]";
            }
        };
    }

    public static <TT> is b(final Class<TT> cls, final Class<? extends TT> cls2, final ir<? super TT> irVar) {
        return new is() { // from class: com.flurry.sdk.jv.23
            @Override // com.flurry.sdk.is
            public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
                Class<? super T> clsA = jwVar.a();
                if (clsA == cls || clsA == cls2) {
                    return irVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + irVar + "]";
            }
        };
    }

    public static <TT> is b(final Class<TT> cls, final ir<TT> irVar) {
        return new is() { // from class: com.flurry.sdk.jv.24
            @Override // com.flurry.sdk.is
            public <T> ir<T> a(ia iaVar, jw<T> jwVar) {
                if (cls.isAssignableFrom(jwVar.a())) {
                    return irVar;
                }
                return null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + irVar + "]";
            }
        };
    }
}
