package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
class dj extends dg {
    private static final String ID = com.google.android.gms.internal.a.UNIVERSAL_ANALYTICS.toString();
    private static final String aul = com.google.android.gms.internal.b.ACCOUNT.toString();
    private static final String aum = com.google.android.gms.internal.b.ANALYTICS_PASS_THROUGH.toString();
    private static final String aun = com.google.android.gms.internal.b.ENABLE_ECOMMERCE.toString();
    private static final String auo = com.google.android.gms.internal.b.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String aup = com.google.android.gms.internal.b.ECOMMERCE_MACRO_DATA.toString();
    private static final String auq = com.google.android.gms.internal.b.ANALYTICS_FIELDS.toString();
    private static final String aur = com.google.android.gms.internal.b.TRACK_TRANSACTION.toString();
    private static final String aus = com.google.android.gms.internal.b.TRANSACTION_DATALAYER_MAP.toString();
    private static final String aut = com.google.android.gms.internal.b.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List<String> auu = Arrays.asList(ProductAction.ACTION_DETAIL, ProductAction.ACTION_CHECKOUT, ProductAction.ACTION_CHECKOUT_OPTION, "click", ProductAction.ACTION_ADD, ProductAction.ACTION_REMOVE, ProductAction.ACTION_PURCHASE, ProductAction.ACTION_REFUND);
    private static final Pattern auv = Pattern.compile("dimension(\\d+)");
    private static final Pattern auw = Pattern.compile("metric(\\d+)");
    private static Map<String, String> aux;
    private static Map<String, String> auy;
    private final DataLayer aqn;
    private final df auA;
    private final Set<String> auz;

    public dj(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new df(context));
    }

    dj(Context context, DataLayer dataLayer, df dfVar) {
        super(ID, new String[0]);
        this.aqn = dataLayer;
        this.auA = dfVar;
        this.auz = new HashSet();
        this.auz.add("");
        this.auz.add(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.auz.add("false");
    }

    private Promotion L(Map<String, String> map) {
        Promotion promotion = new Promotion();
        String str = map.get("id");
        if (str != null) {
            promotion.setId(String.valueOf(str));
        }
        String str2 = map.get("name");
        if (str2 != null) {
            promotion.setName(String.valueOf(str2));
        }
        String str3 = map.get("creative");
        if (str3 != null) {
            promotion.setCreative(String.valueOf(str3));
        }
        String str4 = map.get("position");
        if (str4 != null) {
            promotion.setPosition(String.valueOf(str4));
        }
        return promotion;
    }

    private Product M(Map<String, Object> map) {
        Product product = new Product();
        Object obj = map.get("id");
        if (obj != null) {
            product.setId(String.valueOf(obj));
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(String.valueOf(obj2));
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(String.valueOf(obj3));
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(String.valueOf(obj4));
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(String.valueOf(obj5));
        }
        Object obj6 = map.get("coupon");
        if (obj6 != null) {
            product.setCouponCode(String.valueOf(obj6));
        }
        Object obj7 = map.get("position");
        if (obj7 != null) {
            product.setPosition(z(obj7).intValue());
        }
        Object obj8 = map.get("price");
        if (obj8 != null) {
            product.setPrice(y(obj8).doubleValue());
        }
        Object obj9 = map.get("quantity");
        if (obj9 != null) {
            product.setQuantity(z(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = auv.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException e) {
                    bh.W("illegal number in custom dimension value: " + str);
                }
            } else {
                Matcher matcher2 = auw.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), z(map.get(str)).intValue());
                    } catch (NumberFormatException e2) {
                        bh.W("illegal number in custom metric value: " + str);
                    }
                }
            }
        }
        return product;
    }

    private Map<String, String> N(Map<String, com.google.android.gms.internal.d.a> map) {
        com.google.android.gms.internal.d.a aVar = map.get(aus);
        if (aVar != null) {
            return c(aVar);
        }
        if (aux == null) {
            HashMap map2 = new HashMap();
            map2.put("transactionId", "&ti");
            map2.put("transactionAffiliation", "&ta");
            map2.put("transactionTax", "&tt");
            map2.put("transactionShipping", "&ts");
            map2.put("transactionTotal", "&tr");
            map2.put("transactionCurrency", "&cu");
            aux = map2;
        }
        return aux;
    }

    private Map<String, String> O(Map<String, com.google.android.gms.internal.d.a> map) {
        com.google.android.gms.internal.d.a aVar = map.get(aut);
        if (aVar != null) {
            return c(aVar);
        }
        if (auy == null) {
            HashMap map2 = new HashMap();
            map2.put("name", "&in");
            map2.put("sku", "&ic");
            map2.put("category", "&iv");
            map2.put("price", "&ip");
            map2.put("quantity", "&iq");
            map2.put("currency", "&cu");
            auy = map2;
        }
        return auy;
    }

    private void a(Tracker tracker, Map<String, com.google.android.gms.internal.d.a> map) {
        String strDe = de("transactionId");
        if (strDe == null) {
            bh.T("Cannot find transactionId in data layer.");
            return;
        }
        LinkedList linkedList = new LinkedList();
        try {
            Map<String, String> mapP = p(map.get(auq));
            mapP.put("&t", "transaction");
            for (Map.Entry<String, String> entry : N(map).entrySet()) {
                b(mapP, entry.getValue(), de(entry.getKey()));
            }
            linkedList.add(mapP);
            List<Map<String, String>> listDf = df("transactionProducts");
            if (listDf != null) {
                for (Map<String, String> map2 : listDf) {
                    if (map2.get("name") == null) {
                        bh.T("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map<String, String> mapP2 = p(map.get(auq));
                    mapP2.put("&t", UnityAdsConstants.UNITY_ADS_REWARD_ITEM_KEY);
                    mapP2.put("&ti", strDe);
                    for (Map.Entry<String, String> entry2 : O(map).entrySet()) {
                        b(mapP2, entry2.getValue(), map2.get(entry2.getKey()));
                    }
                    linkedList.add(mapP2);
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                tracker.send((Map) it.next());
            }
        } catch (IllegalArgumentException e) {
            bh.b("Unable to send transaction", e);
        }
    }

    private void b(Tracker tracker, Map<String, com.google.android.gms.internal.d.a> map) {
        Map map2;
        boolean z;
        HitBuilders.HitBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
        Map<String, String> mapP = p(map.get(auq));
        screenViewBuilder.setAll(mapP);
        if (f(map, auo)) {
            Object obj = this.aqn.get("ecommerce");
            map2 = obj instanceof Map ? (Map) obj : null;
        } else {
            Object objO = di.o(map.get(aup));
            map2 = objO instanceof Map ? (Map) objO : null;
        }
        if (map2 != null) {
            String str = mapP.get("&cu");
            if (str == null) {
                str = (String) map2.get("currencyCode");
            }
            if (str != null) {
                screenViewBuilder.set("&cu", str);
            }
            Object obj2 = map2.get("impressions");
            if (obj2 instanceof List) {
                for (Map<String, Object> map3 : (List) obj2) {
                    try {
                        screenViewBuilder.addImpression(M(map3), (String) map3.get("list"));
                    } catch (RuntimeException e) {
                        bh.T("Failed to extract a product from DataLayer. " + e.getMessage());
                    }
                }
            }
            List list = map2.containsKey("promoClick") ? (List) ((Map) map2.get("promoClick")).get("promotions") : map2.containsKey("promoView") ? (List) ((Map) map2.get("promoView")).get("promotions") : null;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    try {
                        screenViewBuilder.addPromotion(L((Map) it.next()));
                    } catch (RuntimeException e2) {
                        bh.T("Failed to extract a promotion from DataLayer. " + e2.getMessage());
                    }
                }
                if (map2.containsKey("promoClick")) {
                    screenViewBuilder.set("&promoa", "click");
                    z = false;
                } else {
                    screenViewBuilder.set("&promoa", Promotion.ACTION_VIEW);
                    z = true;
                }
            } else {
                z = true;
            }
            if (z) {
                for (String str2 : auu) {
                    if (map2.containsKey(str2)) {
                        Map map4 = (Map) map2.get(str2);
                        List list2 = (List) map4.get("products");
                        if (list2 != null) {
                            Iterator it2 = list2.iterator();
                            while (it2.hasNext()) {
                                try {
                                    screenViewBuilder.addProduct(M((Map) it2.next()));
                                } catch (RuntimeException e3) {
                                    bh.T("Failed to extract a product from DataLayer. " + e3.getMessage());
                                }
                            }
                        }
                        try {
                            screenViewBuilder.setProductAction(map4.containsKey("actionField") ? c(str2, (Map) map4.get("actionField")) : new ProductAction(str2));
                            break;
                        } catch (RuntimeException e4) {
                            bh.T("Failed to extract a product action from DataLayer. " + e4.getMessage());
                            break;
                        }
                    }
                }
            }
        }
        tracker.send(screenViewBuilder.build());
    }

    private void b(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private ProductAction c(String str, Map<String, Object> map) {
        ProductAction productAction = new ProductAction(str);
        Object obj = map.get("id");
        if (obj != null) {
            productAction.setTransactionId(String.valueOf(obj));
        }
        Object obj2 = map.get("affiliation");
        if (obj2 != null) {
            productAction.setTransactionAffiliation(String.valueOf(obj2));
        }
        Object obj3 = map.get("coupon");
        if (obj3 != null) {
            productAction.setTransactionCouponCode(String.valueOf(obj3));
        }
        Object obj4 = map.get("list");
        if (obj4 != null) {
            productAction.setProductActionList(String.valueOf(obj4));
        }
        Object obj5 = map.get("option");
        if (obj5 != null) {
            productAction.setCheckoutOptions(String.valueOf(obj5));
        }
        Object obj6 = map.get("revenue");
        if (obj6 != null) {
            productAction.setTransactionRevenue(y(obj6).doubleValue());
        }
        Object obj7 = map.get("tax");
        if (obj7 != null) {
            productAction.setTransactionTax(y(obj7).doubleValue());
        }
        Object obj8 = map.get("shipping");
        if (obj8 != null) {
            productAction.setTransactionShipping(y(obj8).doubleValue());
        }
        Object obj9 = map.get("step");
        if (obj9 != null) {
            productAction.setCheckoutStep(z(obj9).intValue());
        }
        return productAction;
    }

    private Map<String, String> c(com.google.android.gms.internal.d.a aVar) {
        Object objO = di.o(aVar);
        if (!(objO instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) objO).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private String de(String str) {
        Object obj = this.aqn.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private List<Map<String, String>> df(String str) {
        Object obj = this.aqn.get(str);
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("transactionProducts should be of type List.");
        }
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            if (!(it.next() instanceof Map)) {
                throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
            }
        }
        return (List) obj;
    }

    private boolean f(Map<String, com.google.android.gms.internal.d.a> map, String str) {
        com.google.android.gms.internal.d.a aVar = map.get(str);
        if (aVar == null) {
            return false;
        }
        return di.n(aVar).booleanValue();
    }

    private Map<String, String> p(com.google.android.gms.internal.d.a aVar) {
        Map<String, String> mapC;
        if (aVar != null && (mapC = c(aVar)) != null) {
            String str = mapC.get("&aip");
            if (str != null && this.auz.contains(str.toLowerCase())) {
                mapC.remove("&aip");
            }
            return mapC;
        }
        return new HashMap();
    }

    private Double y(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Cannot convert the object to Double: " + e.getMessage());
            }
        }
        if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        throw new RuntimeException("Cannot convert the object to Double: " + obj.toString());
    }

    private Integer z(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Cannot convert the object to Integer: " + e.getMessage());
            }
        }
        if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        throw new RuntimeException("Cannot convert the object to Integer: " + obj.toString());
    }

    @Override // com.google.android.gms.tagmanager.dg
    public void D(Map<String, com.google.android.gms.internal.d.a> map) {
        Tracker trackerCW = this.auA.cW("_GTM_DEFAULT_TRACKER_");
        trackerCW.enableAdvertisingIdCollection(f(map, "collect_adid"));
        if (f(map, aun)) {
            b(trackerCW, map);
            return;
        }
        if (f(map, aum)) {
            trackerCW.send(p(map.get(auq)));
        } else if (f(map, aur)) {
            a(trackerCW, map);
        } else {
            bh.W("Ignoring unknown tag.");
        }
    }
}
