package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HitBuilders {

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            y.eK().a(y.a.CONSTRUCT_APP_VIEW);
            set("&t", "screenview");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            y.eK().a(y.a.CONSTRUCT_EVENT);
            set("&t", "event");
        }

        public EventBuilder(String category, String action) {
            this();
            setCategory(category);
            setAction(action);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public EventBuilder setAction(String action) {
            set("&ea", action);
            return this;
        }

        public EventBuilder setCategory(String category) {
            set("&ec", category);
            return this;
        }

        public EventBuilder setLabel(String label) {
            set("&el", label);
            return this;
        }

        public EventBuilder setValue(long value) {
            set("&ev", Long.toString(value));
            return this;
        }
    }

    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            y.eK().a(y.a.CONSTRUCT_EXCEPTION);
            set("&t", "exception");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public ExceptionBuilder setDescription(String description) {
            set("&exd", description);
            return this;
        }

        public ExceptionBuilder setFatal(boolean fatal) {
            set("&exf", an.E(fatal));
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static class HitBuilder<T extends HitBuilder> {
        ProductAction BB;
        private Map<String, String> BA = new HashMap();
        Map<String, List<Product>> BC = new HashMap();
        List<Promotion> BD = new ArrayList();
        List<Product> BE = new ArrayList();

        protected HitBuilder() {
        }

        public T addImpression(Product product, String impressionList) {
            if (product == null) {
                ae.W("product should be non-null");
            } else {
                if (impressionList == null) {
                    impressionList = "";
                }
                if (!this.BC.containsKey(impressionList)) {
                    this.BC.put(impressionList, new ArrayList());
                }
                this.BC.get(impressionList).add(product);
            }
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                ae.W("product should be non-null");
            } else {
                this.BE.add(product);
            }
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                ae.W("promotion should be non-null");
            } else {
                this.BD.add(promotion);
            }
            return this;
        }

        public Map<String, String> build() {
            HashMap map = new HashMap(this.BA);
            if (this.BB != null) {
                map.putAll(this.BB.build());
            }
            Iterator<Promotion> it = this.BD.iterator();
            int i = 1;
            while (it.hasNext()) {
                map.putAll(it.next().aq(s.B(i)));
                i++;
            }
            Iterator<Product> it2 = this.BE.iterator();
            int i2 = 1;
            while (it2.hasNext()) {
                map.putAll(it2.next().aq(s.A(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry<String, List<Product>> entry : this.BC.entrySet()) {
                List<Product> value = entry.getValue();
                String strD = s.D(i3);
                Iterator<Product> it3 = value.iterator();
                int i4 = 1;
                while (it3.hasNext()) {
                    map.putAll(it3.next().aq(strD + s.C(i4)));
                    i4++;
                }
                if (!TextUtils.isEmpty(entry.getKey())) {
                    map.put(strD + "nm", entry.getKey());
                }
                i3++;
            }
            return map;
        }

        protected String get(String paramName) {
            return this.BA.get(paramName);
        }

        public final T set(String paramName, String paramValue) {
            y.eK().a(y.a.MAP_BUILDER_SET);
            if (paramName != null) {
                this.BA.put(paramName, paramValue);
            } else {
                ae.W(" HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> params) {
            y.eK().a(y.a.MAP_BUILDER_SET_ALL);
            if (params != null) {
                this.BA.putAll(new HashMap(params));
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String utmParams) {
            y.eK().a(y.a.MAP_BUILDER_SET_CAMPAIGN_PARAMS);
            String strAo = an.ao(utmParams);
            if (!TextUtils.isEmpty(strAo)) {
                Map<String, String> mapAn = an.an(strAo);
                set("&cc", mapAn.get("utm_content"));
                set("&cm", mapAn.get("utm_medium"));
                set("&cn", mapAn.get("utm_campaign"));
                set("&cs", mapAn.get("utm_source"));
                set("&ck", mapAn.get("utm_term"));
                set("&ci", mapAn.get("utm_id"));
                set("&gclid", mapAn.get("gclid"));
                set("&dclid", mapAn.get("dclid"));
                set("&gmob_t", mapAn.get("gmob_t"));
            }
            return this;
        }

        public T setCustomDimension(int index, String dimension) {
            set(s.y(index), dimension);
            return this;
        }

        public T setCustomMetric(int index, float metric) {
            set(s.z(index), Float.toString(metric));
            return this;
        }

        protected T setHitType(String hitType) {
            set("&t", hitType);
            return this;
        }

        public T setNewSession() {
            set("&sc", "start");
            return this;
        }

        public T setNonInteraction(boolean nonInteraction) {
            set("&ni", an.E(nonInteraction));
            return this;
        }

        public T setProductAction(ProductAction action) {
            this.BB = action;
            return this;
        }

        public T setPromotionAction(String action) {
            this.BA.put("&promoa", action);
            return this;
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            y.eK().a(y.a.CONSTRUCT_ITEM);
            set("&t", UnityAdsConstants.UNITY_ADS_REWARD_ITEM_KEY);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public ItemBuilder setCategory(String category) {
            set("&iv", category);
            return this;
        }

        public ItemBuilder setCurrencyCode(String currencyCode) {
            set("&cu", currencyCode);
            return this;
        }

        public ItemBuilder setName(String name) {
            set("&in", name);
            return this;
        }

        public ItemBuilder setPrice(double price) {
            set("&ip", Double.toString(price));
            return this;
        }

        public ItemBuilder setQuantity(long quantity) {
            set("&iq", Long.toString(quantity));
            return this;
        }

        public ItemBuilder setSku(String sku) {
            set("&ic", sku);
            return this;
        }

        public ItemBuilder setTransactionId(String transactionid) {
            set("&ti", transactionid);
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            y.eK().a(y.a.CONSTRUCT_APP_VIEW);
            set("&t", "screenview");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            y.eK().a(y.a.CONSTRUCT_SOCIAL);
            set("&t", "social");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public SocialBuilder setAction(String action) {
            set("&sa", action);
            return this;
        }

        public SocialBuilder setNetwork(String network) {
            set("&sn", network);
            return this;
        }

        public SocialBuilder setTarget(String target) {
            set("&st", target);
            return this;
        }
    }

    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            y.eK().a(y.a.CONSTRUCT_TIMING);
            set("&t", "timing");
        }

        public TimingBuilder(String category, String variable, long value) {
            this();
            setVariable(variable);
            setValue(value);
            setCategory(category);
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public TimingBuilder setCategory(String category) {
            set("&utc", category);
            return this;
        }

        public TimingBuilder setLabel(String label) {
            set("&utl", label);
            return this;
        }

        public TimingBuilder setValue(long value) {
            set("&utt", Long.toString(value));
            return this;
        }

        public TimingBuilder setVariable(String variable) {
            set("&utv", variable);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            y.eK().a(y.a.CONSTRUCT_TRANSACTION);
            set("&t", "transaction");
        }

        @Override // com.google.android.gms.analytics.HitBuilders.HitBuilder
        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public TransactionBuilder setAffiliation(String affiliation) {
            set("&ta", affiliation);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String currencyCode) {
            set("&cu", currencyCode);
            return this;
        }

        public TransactionBuilder setRevenue(double revenue) {
            set("&tr", Double.toString(revenue));
            return this;
        }

        public TransactionBuilder setShipping(double shipping) {
            set("&ts", Double.toString(shipping));
            return this;
        }

        public TransactionBuilder setTax(double tax) {
            set("&tt", Double.toString(tax));
            return this;
        }

        public TransactionBuilder setTransactionId(String transactionid) {
            set("&ti", transactionid);
            return this;
        }
    }
}
