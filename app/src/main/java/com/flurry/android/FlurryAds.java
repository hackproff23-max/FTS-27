package com.flurry.android;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.view.ViewGroup;
import com.flurry.sdk.fp;
import com.flurry.sdk.gd;
import com.flurry.sdk.hg;
import com.flurry.sdk.i;
import com.flurry.sdk.j;
import com.flurry.sdk.u;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class FlurryAds {
    private static final String a = FlurryAds.class.getSimpleName();

    private FlurryAds() {
    }

    @Deprecated
    public static boolean getAd(Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize, long j) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
            return false;
        }
        if (fp.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
        if (context == null) {
            gd.b(a, "Context passed to getAd was null.");
            return false;
        }
        if (str == null) {
            gd.b(a, "Ad space name passed to getAd was null.");
            return false;
        }
        if (str.length() == 0) {
            gd.b(a, "Ad space name passed to getAd was empty.");
            return false;
        }
        if (viewGroup == null) {
            gd.b(a, "ViewGroup passed to getAd was null.");
            return false;
        }
        if (flurryAdSize == null) {
            gd.b(a, "FlurryAdSize passed to getAd was null.");
            return false;
        }
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            gd.b(a, "getAd must be called from UI thread.");
            return false;
        }
        try {
            i iVarA = i.a();
            if (iVarA == null) {
                gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
                return false;
            }
            u uVarA = iVarA.e().a(context, str);
            if (uVarA == null) {
                uVarA = new u(context, viewGroup, str);
            } else if (context != uVarA.e() || viewGroup != uVarA.f()) {
                uVarA.a();
                uVarA = new u(context, viewGroup, str);
            }
            return uVarA.r();
        } catch (Throwable th) {
            gd.a(a, "Exception while getting Ad : ", th);
            return false;
        }
    }

    @Deprecated
    public static void initializeAds(Context context) {
    }

    @Deprecated
    public static boolean isAdAvailable(Context context, String str, FlurryAdSize flurryAdSize, long j) {
        boolean zO = false;
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            if (fp.a() == null) {
                throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
            }
            if (context == null) {
                gd.b(a, "Context passed to isAdAvailable was null.");
            } else if (str == null) {
                gd.b(a, "Ad space name passed to isAdAvailable was null.");
            } else if (str.length() == 0) {
                gd.b(a, "Ad space name passed to isAdAvailable was empty.");
            } else if (flurryAdSize == null) {
                gd.b(a, "FlurryAdSize passed to isAdAvailable was null.");
            } else {
                try {
                    i iVarA = i.a();
                    if (iVarA == null) {
                        gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
                    } else {
                        u uVarA = iVarA.e().a(context, str);
                        if (uVarA != null) {
                            zO = uVarA.o();
                        }
                    }
                } catch (Throwable th) {
                    gd.a(a, "Exception while checking Ads if available: ", th);
                }
            }
        }
        return zO;
    }

    @Deprecated
    public static boolean isAdReady(String str) {
        boolean zO = false;
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            if (fp.a() == null) {
                throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
            }
            if (str == null) {
                gd.b(a, "Ad space name passed to isAdReady was null.");
            } else if (str.length() == 0) {
                gd.b(a, "Ad space name passed to isAdReady was empty.");
            } else {
                try {
                    i iVarA = i.a();
                    if (iVarA == null) {
                        gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
                    } else {
                        u uVarA = iVarA.e().a(str);
                        if (uVarA != null) {
                            zO = uVarA.o();
                        }
                    }
                } catch (Throwable th) {
                    gd.a(a, "Exception while checking Ads if ready: ", th);
                }
            }
        }
        return zO;
    }

    @Deprecated
    public static void fetchAd(Context context, String str, ViewGroup viewGroup, FlurryAdSize flurryAdSize) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
            return;
        }
        if (fp.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
        if (context == null) {
            gd.b(a, "Context passed to fetchAd was null.");
            return;
        }
        if (str == null) {
            gd.b(a, "Ad space name passed to fetchAd was null.");
            return;
        }
        if (str.length() == 0) {
            gd.b(a, "Ad space name passed to fetchAd was empty.");
            return;
        }
        if (viewGroup == null) {
            gd.b(a, "ViewGroup passed to fetchAd was null.");
            return;
        }
        if (flurryAdSize == null) {
            gd.b(a, "FlurryAdSize passed to fetchAd was null.");
            return;
        }
        try {
            i iVarA = i.a();
            if (iVarA == null) {
                gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
                return;
            }
            u uVarA = iVarA.e().a(context, str);
            if (uVarA == null) {
                uVarA = new u(context, viewGroup, str);
            } else if (viewGroup != uVarA.f()) {
                uVarA.a();
                uVarA = new u(context, viewGroup, str);
            }
            uVarA.p();
        } catch (Throwable th) {
            gd.a(a, "Exception while fetching Ad: ", th);
        }
    }

    @Deprecated
    public static void displayAd(Context context, String str, ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
            return;
        }
        if (fp.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
        if (context == null) {
            gd.b(a, "Context passed to displayAd was null.");
            return;
        }
        if (str == null) {
            gd.b(a, "Ad space name passed to displayAd was null.");
            return;
        }
        if (str.length() == 0) {
            gd.b(a, "Ad space name passed to displayAd was empty.");
            return;
        }
        if (viewGroup == null) {
            gd.b(a, "ViewGroup passed to displayAd was null.");
            return;
        }
        try {
            i iVarA = i.a();
            if (iVarA == null) {
                gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
                return;
            }
            u uVarA = iVarA.e().a(context, str);
            if (uVarA == null) {
                uVarA = new u(context, viewGroup, str);
            } else if (viewGroup != uVarA.f()) {
                gd.b(a, "An ad must be displayed with the same context and viewGroup as the fetch.");
                return;
            }
            uVarA.q();
        } catch (Throwable th) {
            gd.a(a, "Exception while displaying Ad: ", th);
        }
    }

    @Deprecated
    public static void removeAd(Context context, String str, ViewGroup viewGroup) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
            return;
        }
        if (fp.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        }
        if (context == null) {
            gd.b(a, "Context passed to removeAd was null.");
            return;
        }
        if (str == null) {
            gd.b(a, "Ad space name passed to removeAd was null.");
            return;
        }
        if (str.length() == 0) {
            gd.b(a, "Ad space name passed to removeAd was empty.");
            return;
        }
        if (viewGroup == null) {
            gd.b(a, "ViewGroup passed to removeAd was null.");
            return;
        }
        try {
            i iVarA = i.a();
            if (iVarA == null) {
                gd.b(a, "Could not find FlurryAds module. Please make sure the library is included.");
            } else {
                iVarA.e().b(context, str);
            }
        } catch (Throwable th) {
            gd.a(a, "Exception while removing Ad: ", th);
        }
    }

    @Deprecated
    public static void setAdListener(FlurryAdListener flurryAdListener) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            j.a().a(flurryAdListener);
        }
    }

    @Deprecated
    public static void setLocation(float f, float f2) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
            return;
        }
        Location location = new Location("Explicit");
        location.setLatitude(f);
        location.setLongitude(f2);
        hg.a().a("ExplicitLocation", (Object) location);
    }

    @Deprecated
    public static void clearLocation() {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            hg.a().a("ExplicitLocation", (Object) null);
        }
    }

    public static void setCustomAdNetworkHandler(ICustomAdNetworkHandler iCustomAdNetworkHandler) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else if (iCustomAdNetworkHandler == null) {
            gd.b(a, "ICustomAdNetworkHandler passed to setCustomAdNetworkHandler was null.");
        } else {
            j.a().a(iCustomAdNetworkHandler);
        }
    }

    @Deprecated
    public static void setUserCookies(Map<String, String> map) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else if (map == null) {
            gd.b(a, "userCookies Map passed to setUserCookies was null.");
        } else {
            j.a().a(map);
        }
    }

    @Deprecated
    public static void clearUserCookies() {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            j.a().e();
        }
    }

    @Deprecated
    public static void setTargetingKeywords(Map<String, String> map) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else if (map == null) {
            gd.b(a, "targetingKeywords Map passed to setTargetingKeywords was null.");
        } else if (map != null) {
            j.a().b(map);
        }
    }

    @Deprecated
    public static void clearTargetingKeywords() {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            j.a().f();
        }
    }

    @Deprecated
    public static void enableTestAds(boolean z) {
        if (Build.VERSION.SDK_INT < 10) {
            gd.b(a, "Device SDK Version older than 10");
        } else {
            j.a().a(z);
        }
    }
}
