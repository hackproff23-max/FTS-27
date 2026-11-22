package com.flurry.sdk;

import android.text.TextUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class cz {
    private static final String a = cz.class.getSimpleName();
    private static int b = 500;

    static int a() {
        return b;
    }

    public static void a(int i) {
        b = i;
    }

    static dl a(List<dl> list) {
        dl dlVar = null;
        if (list != null) {
            for (dl dlVar2 : list) {
                if (dlVar2.b() > a() || dlVar2.a() == null || (((dlVar2.c() == null || !dlVar2.c().equalsIgnoreCase("video/mp4")) && !dlVar2.a().endsWith("mp4")) || (dlVar != null && dlVar.b() >= dlVar2.b()))) {
                    dlVar2 = dlVar;
                }
                dlVar = dlVar2;
            }
        }
        return dlVar;
    }

    static int a(String str) {
        if (str == null) {
            return 0;
        }
        Matcher matcher = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})").matcher(str);
        if (!matcher.find() || matcher.groupCount() != 3) {
            return 0;
        }
        try {
            int i = Integer.parseInt(matcher.group(1));
            int i2 = Integer.parseInt(matcher.group(2));
            return Integer.parseInt(matcher.group(3)) + (i * 60 * 60) + (i2 * 60);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void a(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.Close)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "Close Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void b(ap apVar, String str, String str2) {
        List<String> listG;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listG = cyVarG.g()) != null) {
            for (String str3 : listG) {
                if (str3 != null) {
                    gd.a(3, a, "Error Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void c(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(df.ClickTracking)) != null) {
            for (String str3 : listA) {
                if (!TextUtils.isEmpty(str3)) {
                    gd.a(3, a, "ClickTracking Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void d(ap apVar, String str, String str2) {
        List<String> listH;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listH = cyVarG.h()) != null) {
            for (String str3 : listH) {
                if (str3 != null) {
                    gd.a(3, a, "Impression Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void e(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.Start)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "Start Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void f(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.FirstQuartile)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "First Quartile Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void g(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.Midpoint)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "Midpoint Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void h(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.ThirdQuartile)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "Third Quartile Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    public static void i(ap apVar, String str, String str2) {
        List<String> listA;
        cy cyVarG = apVar.g();
        if (cyVarG != null && (listA = cyVarG.a(de.Complete)) != null) {
            for (String str3 : listA) {
                if (str3 != null) {
                    gd.a(3, a, "Complete Tracking URL: " + str3);
                    a(str, str2, str3);
                }
            }
        }
    }

    private static void a(String str, String str2, String str3) {
        i.a().i().b(new cd(str, str2, str3, System.currentTimeMillis() + 900000, false));
    }
}
