package com.flurry.sdk;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.millennialmedia.android.MMSDK;
import com.mopub.common.Constants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;
import org.apache.http.client.utils.URIUtils;

/* JADX INFO: loaded from: classes.dex */
public class cv {
    private static final Pattern a = Pattern.compile("/");

    public static String a(String str) {
        URI uriH;
        int iIndexOf;
        if (!TextUtils.isEmpty(str) && (uriH = h(str)) != null) {
            String scheme = uriH.getScheme();
            if (TextUtils.isEmpty(scheme)) {
                return "http://" + str;
            }
            String lowerCase = scheme.toLowerCase();
            return (scheme == null || scheme.equals(lowerCase) || (iIndexOf = str.indexOf(scheme)) < 0) ? str : lowerCase + str.substring(scheme.length() + iIndexOf);
        }
        return str;
    }

    public static String b(String str) {
        URI uriH;
        URI uriA;
        if (!TextUtils.isEmpty(str) && (uriH = h(str)) != null) {
            URI uriNormalize = uriH.normalize();
            if (!uriNormalize.isOpaque() && (uriA = a(uriNormalize.getScheme(), uriNormalize.getAuthority(), "/", null, null)) != null) {
                return uriA.toString();
            }
            return str;
        }
        return str;
    }

    public static String c(String str) {
        URI uriH;
        URI uriResolve;
        if (!TextUtils.isEmpty(str) && (uriH = h(str)) != null) {
            URI uriNormalize = uriH.normalize();
            if (!uriNormalize.isOpaque() && (uriResolve = URIUtils.resolve(uriNormalize, "./")) != null) {
                return uriResolve.toString();
            }
            return str;
        }
        return str;
    }

    public static String a(String str, String str2) {
        URI uriH;
        String str3;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && (uriH = h(str)) != null) {
            URI uriNormalize = uriH.normalize();
            URI uriH2 = h(str2);
            if (uriH2 != null) {
                URI uriNormalize2 = uriH2.normalize();
                if (!uriNormalize.isOpaque() && !uriNormalize2.isOpaque()) {
                    String scheme = uriNormalize.getScheme();
                    String scheme2 = uriNormalize2.getScheme();
                    if (scheme2 != null || scheme == null) {
                        if (scheme2 == null || scheme2.equals(scheme)) {
                            String authority = uriNormalize.getAuthority();
                            String authority2 = uriNormalize2.getAuthority();
                            if (authority2 != null || authority == null) {
                                if (authority2 == null || authority2.equals(authority)) {
                                    String path = uriNormalize.getPath();
                                    String path2 = uriNormalize2.getPath();
                                    String[] strArrSplit = a.split(path, -1);
                                    String[] strArrSplit2 = a.split(path2, -1);
                                    int length = strArrSplit.length;
                                    int length2 = strArrSplit2.length;
                                    int i = 0;
                                    while (i < length2 && i < length && strArrSplit[i].equals(strArrSplit2[i])) {
                                        i++;
                                    }
                                    String query = uriNormalize.getQuery();
                                    String fragment = uriNormalize.getFragment();
                                    StringBuilder sb = new StringBuilder();
                                    if (i == length2 && i == length) {
                                        String query2 = uriNormalize2.getQuery();
                                        String fragment2 = uriNormalize2.getFragment();
                                        boolean zEquals = TextUtils.equals(query, query2);
                                        boolean zEquals2 = TextUtils.equals(fragment, fragment2);
                                        if (zEquals && zEquals2) {
                                            str3 = null;
                                            query = null;
                                        } else {
                                            String str4 = (!zEquals || TextUtils.isEmpty(fragment)) ? query : null;
                                            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(fragment)) {
                                                sb.append(strArrSplit[length - 1]);
                                                str3 = fragment;
                                            } else {
                                                query = str4;
                                                str3 = fragment;
                                            }
                                        }
                                        fragment = str3;
                                    } else {
                                        int i2 = length - 1;
                                        int i3 = length2 - 1;
                                        for (int i4 = i; i4 < i3; i4++) {
                                            sb.append("..");
                                            sb.append("/");
                                        }
                                        while (i < i2) {
                                            sb.append(strArrSplit[i]);
                                            sb.append("/");
                                            i++;
                                        }
                                        if (i < length) {
                                            sb.append(strArrSplit[i]);
                                        }
                                        if (sb.length() == 0) {
                                            sb.append(".");
                                            sb.append("/");
                                        }
                                    }
                                    URI uriA = a(null, null, sb.toString(), query, fragment);
                                    if (uriA != null) {
                                        return uriA.toString();
                                    }
                                    return str;
                                }
                                return str;
                            }
                            return str;
                        }
                        return str;
                    }
                    return str;
                }
                return str;
            }
            return str;
        }
        return str;
    }

    private static URI h(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private static URI a(String str, String str2, String str3, String str4, String str5) {
        try {
            return new URI(str, str2, str3, str4, str5);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri uri = Uri.parse(str);
        return uri.getScheme() != null && uri.getScheme().equals(MMSDK.Event.INTENT_MARKET);
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri uri = Uri.parse(str);
        if (uri.getScheme() != null) {
            return uri.getScheme().equals("http://") || uri.getScheme().equals("https://");
        }
        return false;
    }

    public static boolean f(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri uri = Uri.parse(str);
        return uri.getHost() != null && uri.getHost().equals("play.google.com") && uri.getScheme() != null && uri.getScheme().startsWith(Constants.HTTP);
    }

    public static boolean g(String str) {
        String mimeTypeFromExtension;
        return (TextUtils.isEmpty(str) || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str))) == null || !mimeTypeFromExtension.startsWith("video/")) ? false : true;
    }

    public static String b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (!new URI(str).isAbsolute() && !TextUtils.isEmpty(str2)) {
                    URI uri = new URI(str2);
                    return uri.getScheme() + "://" + uri.getHost() + str;
                }
                return str;
            } catch (Exception e) {
                return str;
            }
        }
        return str;
    }
}
