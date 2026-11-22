package com.flurry.sdk;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.flurry.android.AdCreative;
import com.flurry.android.ICustomAdNetworkHandler;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import com.flurry.android.impl.ads.protocol.v13.AdSpaceLayout;
import com.flurry.android.impl.ads.protocol.v13.AdUnit;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class eg {
    private static final String a = eg.class.getSimpleName();
    private static final Map<Class<? extends ViewGroup>, c> b = b();

    public static void a(Context context, s sVar) {
        ec ecVarB;
        if (context != null && sVar != null) {
            ap apVarK = sVar.k();
            AdUnit adUnitA = apVarK.a();
            AdFrame adFrame = adUnitA.adFrames.get(0);
            int i = adFrame.binding;
            String str = adFrame.content;
            AdCreative adCreativeA = cr.a(adFrame.adSpaceLayout);
            ICustomAdNetworkHandler iCustomAdNetworkHandlerC = j.a().c();
            ef efVarP = i.a().p();
            if (i == 4 && iCustomAdNetworkHandlerC != null) {
                ecVarB = iCustomAdNetworkHandlerC.getAdFromNetwork(context, adCreativeA, str);
            } else {
                ecVarB = efVarP.b(context, sVar);
            }
            if (ecVarB == null) {
                gd.e(a, "Failed to create view for ad network: " + str + ", network is unsupported on Android, or no ICustomAdNetworkHandler was registered or it failed to return a view.");
                HashMap map = new HashMap();
                map.put("errorCode", Integer.toString(av.kPrepareFailed.a()));
                if (i == 4) {
                    map.put("binding_3rd_party", Integer.toString(4));
                }
                co.a(aw.EV_RENDER_FAILED, map, context, sVar, apVarK, 1);
                return;
            }
            a(sVar, ecVarB, adUnitA);
        }
    }

    private static void a(s sVar, ec ecVar, AdUnit adUnit) {
        RelativeLayout relativeLayout;
        if (sVar != null && ecVar != null && adUnit != null) {
            ViewGroup viewGroupF = sVar.f();
            RelativeLayout relativeLayoutS = sVar.s();
            if (relativeLayoutS == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(sVar.e());
                sVar.a(relativeLayout2);
                relativeLayout = relativeLayout2;
            } else {
                relativeLayout = relativeLayoutS;
            }
            ViewGroup viewGroup = (ViewGroup) relativeLayout.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(relativeLayout);
            }
            relativeLayout.removeAllViews();
            ViewGroup viewGroup2 = (ViewGroup) ecVar.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(ecVar);
            }
            relativeLayout.addView(ecVar, new RelativeLayout.LayoutParams(-1, -1));
            ecVar.initLayout();
            ViewGroup.LayoutParams layoutParamsB = b(viewGroupF, adUnit);
            if (layoutParamsB != null) {
                relativeLayout.setLayoutParams(layoutParamsB);
                gd.a(3, a, "banner ad holder layout params = " + layoutParamsB.getClass().getName() + " {width = " + layoutParamsB.width + ", height = " + layoutParamsB.height + "} for banner ad with adSpaceName = " + sVar.g());
            }
            viewGroupF.setBackgroundColor(369098752);
            viewGroupF.addView(relativeLayout, a(viewGroupF, adUnit));
        }
    }

    private static int a(ViewGroup viewGroup, AdUnit adUnit) {
        int childCount = viewGroup.getChildCount();
        if (adUnit == null || adUnit.adFrames.size() < 1) {
            return childCount;
        }
        AdSpaceLayout adSpaceLayout = adUnit.adFrames.get(0).adSpaceLayout;
        if (adSpaceLayout != null) {
            String[] strArrSplit = adSpaceLayout.alignment.split(Constants.FILENAME_SEQUENCE_SEPARATOR);
            if (strArrSplit.length == 2 && "t".equals(strArrSplit[0])) {
                return 0;
            }
        }
        return childCount;
    }

    private static ViewGroup.LayoutParams b(ViewGroup viewGroup, AdUnit adUnit) {
        if (adUnit == null || adUnit.adFrames.size() < 1) {
            return null;
        }
        AdSpaceLayout adSpaceLayout = adUnit.adFrames.get(0).adSpaceLayout;
        if (adSpaceLayout == null) {
            return null;
        }
        c cVarA = a(viewGroup);
        if (cVarA == null) {
            gd.a(5, a, "Ad space layout and alignment from the server is being ignored for ViewGroup subclass " + viewGroup.getClass().getSimpleName());
            return null;
        }
        ViewGroup.LayoutParams layoutParamsA = cVarA.a(adSpaceLayout);
        if (layoutParamsA == null) {
            gd.a(6, a, "Ad space layout and alignment from the server is being ignored for ViewGroup subclass " + viewGroup.getClass().getSimpleName());
            return layoutParamsA;
        }
        return layoutParamsA;
    }

    private static c a(ViewGroup viewGroup) {
        return b.get(viewGroup.getClass());
    }

    static abstract class c {
        public abstract ViewGroup.LayoutParams a(AdSpaceLayout adSpaceLayout);

        private c() {
        }

        public int b(AdSpaceLayout adSpaceLayout) {
            return h(adSpaceLayout) ? d(adSpaceLayout) : f(adSpaceLayout);
        }

        public int c(AdSpaceLayout adSpaceLayout) {
            return i(adSpaceLayout) ? e(adSpaceLayout) : g(adSpaceLayout);
        }

        public int d(AdSpaceLayout adSpaceLayout) {
            return hn.b(adSpaceLayout.adWidth);
        }

        public int e(AdSpaceLayout adSpaceLayout) {
            return hn.b(adSpaceLayout.adHeight);
        }

        public int f(AdSpaceLayout adSpaceLayout) {
            return -1;
        }

        public int g(AdSpaceLayout adSpaceLayout) {
            return -2;
        }

        private static boolean h(AdSpaceLayout adSpaceLayout) {
            return adSpaceLayout.adWidth != 0;
        }

        private static boolean i(AdSpaceLayout adSpaceLayout) {
            return adSpaceLayout.adHeight != 0;
        }
    }

    static final class d extends c {
        d() {
            super();
        }

        @Override // com.flurry.sdk.eg.c
        public ViewGroup.LayoutParams a(AdSpaceLayout adSpaceLayout) {
            return new LinearLayout.LayoutParams(b(adSpaceLayout), c(adSpaceLayout));
        }
    }

    static final class a extends c {
        a() {
            super();
        }

        @Override // com.flurry.sdk.eg.c
        public ViewGroup.LayoutParams a(AdSpaceLayout adSpaceLayout) {
            gd.a(5, eg.a, "AbsoluteLayout is deprecated, please consider to use FrameLayout or RelativeLayout for banner ad container view");
            return new AbsoluteLayout.LayoutParams(b(adSpaceLayout), c(adSpaceLayout), 0, 0);
        }
    }

    static final class b extends c {
        b() {
            super();
        }

        @Override // com.flurry.sdk.eg.c
        public ViewGroup.LayoutParams a(AdSpaceLayout adSpaceLayout) {
            return new FrameLayout.LayoutParams(b(adSpaceLayout), c(adSpaceLayout), 17);
        }
    }

    static final class e extends c {
        private static final Map<String, Integer> a = a();

        e() {
            super();
        }

        @Override // com.flurry.sdk.eg.c
        public int g(AdSpaceLayout adSpaceLayout) {
            return -1;
        }

        @Override // com.flurry.sdk.eg.c
        public ViewGroup.LayoutParams a(AdSpaceLayout adSpaceLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(b(adSpaceLayout), c(adSpaceLayout));
            String[] strArrSplit = adSpaceLayout.alignment.split(Constants.FILENAME_SEQUENCE_SEPARATOR);
            if (strArrSplit.length == 2) {
                Integer numA = a(strArrSplit[0]);
                if (numA != null) {
                    layoutParams.addRule(numA.intValue());
                }
                Integer numA2 = a(strArrSplit[1]);
                if (numA2 != null) {
                    layoutParams.addRule(numA2.intValue());
                }
            }
            return layoutParams;
        }

        private static Map<String, Integer> a() {
            HashMap map = new HashMap();
            map.put("b", 12);
            map.put("t", 10);
            map.put("m", 15);
            map.put("c", 14);
            map.put("l", 9);
            map.put("r", 11);
            return Collections.unmodifiableMap(map);
        }

        private static Integer a(String str) {
            return a.get(str);
        }
    }

    private static Map<Class<? extends ViewGroup>, c> b() {
        HashMap map = new HashMap();
        map.put(LinearLayout.class, new d());
        map.put(AbsoluteLayout.class, new a());
        map.put(FrameLayout.class, new b());
        map.put(RelativeLayout.class, new e());
        return Collections.unmodifiableMap(map);
    }
}
