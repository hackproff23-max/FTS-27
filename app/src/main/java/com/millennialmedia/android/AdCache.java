package com.millennialmedia.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
final class AdCache {
    private static final String CACHED_AD_FILE = "ad.dat";
    private static final String CACHE_INCOMPLETE_PREFIX = "incompleteDownload_";
    private static final String CACHE_PREFIX = "nextCachedAd_";
    private static final String CACHE_PREFIX_APID = "nextCachedAd_apids";
    private static final String LOCK_FILE = "ad.lock";
    static final int PRIORITY_FETCH = 3;
    static final int PRIORITY_PRECACHE = 1;
    static final int PRIORITY_REFRESH = 2;
    private static final String PRIVATE_CACHE_DIR = ".mmsyscache";
    private static final String TAG = "AdCache";
    private static Set<String> apidListSet;
    private static String cachedVideoList;
    private static boolean cachedVideoListLoaded;
    private static Set<String> cachedVideoSet;
    private static Map<String, String> incompleteDownloadHashMap;
    private static boolean incompleteDownloadHashMapLoaded;
    static boolean isExternalEnabled = true;
    private static Map<String, String> nextCachedAdHashMap;
    private static boolean nextCachedAdHashMapLoaded;

    interface AdCacheTaskListener {
        void downloadCompleted(CachedAd cachedAd, boolean z);

        void downloadStart(CachedAd cachedAd);
    }

    private AdCache() {
    }

    static boolean startDownloadTask(Context context, String adName, CachedAd ad, AdCacheTaskListener listener) {
        return AdCacheThreadPool.sharedThreadPool().startDownloadTask(context, adName, ad, listener);
    }

    static synchronized void cachedVideoWasAdded(Context context, String acid) {
        if (acid != null) {
            if (!cachedVideoListLoaded) {
                getCachedVideoList(context);
            }
            if (cachedVideoSet == null) {
                cachedVideoSet = new HashSet();
            }
            cachedVideoSet.add(acid);
            cachedVideoList = null;
        }
    }

    static synchronized void cachedVideoWasRemoved(Context context, String acid) {
        if (acid != null) {
            if (!cachedVideoListLoaded) {
                getCachedVideoList(context);
            }
            if (cachedVideoSet != null) {
                cachedVideoSet.remove(acid);
                cachedVideoList = null;
            }
        }
    }

    static synchronized String getCachedVideoList(final Context context) {
        if (cachedVideoList == null) {
            if (!cachedVideoListLoaded) {
                final Set<String> hashSet = new HashSet<>();
                iterateCachedAds(context, 2, new Iterator() { // from class: com.millennialmedia.android.AdCache.1
                    @Override // com.millennialmedia.android.AdCache.Iterator
                    boolean callback(CachedAd cachedAd) {
                        if (cachedAd.acid != null && cachedAd.getType() == 1 && cachedAd.isOnDisk(context)) {
                            hashSet.add(cachedAd.acid);
                        }
                        return true;
                    }
                });
                cachedVideoSet = hashSet;
                cachedVideoListLoaded = true;
            }
            if (cachedVideoSet != null && cachedVideoSet.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String acid : cachedVideoSet) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append("," + acid);
                    } else {
                        stringBuilder.append(acid);
                    }
                }
                cachedVideoList = stringBuilder.toString();
            }
        }
        return cachedVideoList;
    }

    private static void loadNextCachedAdHashMap(Context context) {
        SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
        nextCachedAdHashMap = new ConcurrentHashMap();
        if (apidListSet == null) {
            loadApidListSet(settings);
        }
        for (String apid : apidListSet) {
            String[] arr$ = MMAdImpl.getAdTypes();
            for (String adType : arr$) {
                String result = settings.getString(CACHE_PREFIX + adType + '_' + apid, null);
                if (result != null) {
                    nextCachedAdHashMap.put(adType + '_' + apid, result);
                }
            }
        }
        nextCachedAdHashMapLoaded = true;
    }

    private static void saveNextCachedAdHashMapValue(Context context, String adName) {
        if (adName != null) {
            SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
            SharedPreferences.Editor editor = settings.edit();
            saveApidListSet(editor, adName);
            editor.putString(CACHE_PREFIX + adName, nextCachedAdHashMap.get(adName));
            editor.commit();
        }
    }

    static synchronized String getNextCachedAd(Context context, String adName) {
        if (!nextCachedAdHashMapLoaded) {
            loadNextCachedAdHashMap(context);
        }
        return adName == null ? null : nextCachedAdHashMap.get(adName);
    }

    static CachedAd loadNextCachedAd(Context context, String adName) {
        String nextAd = getNextCachedAd(context, adName);
        if (nextAd == null || nextAd.equals("")) {
            return null;
        }
        return load(context, nextAd);
    }

    static synchronized void setNextCachedAd(Context context, String adName, String id) {
        if (!nextCachedAdHashMapLoaded) {
            loadNextCachedAdHashMap(context);
        }
        if (adName != null) {
            Map<String, String> map = nextCachedAdHashMap;
            if (id == null) {
                id = "";
            }
            map.put(adName, id);
            saveNextCachedAdHashMapValue(context, adName);
        }
    }

    private static void loadIncompleteDownloadHashMap(Context context) {
        SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
        incompleteDownloadHashMap = new ConcurrentHashMap();
        if (apidListSet == null) {
            loadApidListSet(settings);
        }
        for (String apid : apidListSet) {
            String[] arr$ = MMAdImpl.getAdTypes();
            for (String adType : arr$) {
                String result = settings.getString(CACHE_INCOMPLETE_PREFIX + adType + '_' + apid, null);
                if (result != null) {
                    incompleteDownloadHashMap.put(adType + '_' + apid, result);
                }
            }
        }
        incompleteDownloadHashMapLoaded = true;
    }

    private static void saveIncompleteDownloadHashMap(Context context, String adName) {
        if (adName != null) {
            SharedPreferences settings = context.getSharedPreferences("MillennialMediaSettings", 0);
            SharedPreferences.Editor editor = settings.edit();
            saveApidListSet(editor, adName);
            editor.putString(CACHE_INCOMPLETE_PREFIX + adName, incompleteDownloadHashMap.get(adName));
            editor.commit();
        }
    }

    static synchronized String getIncompleteDownload(Context context, String adName) {
        if (!incompleteDownloadHashMapLoaded) {
            loadIncompleteDownloadHashMap(context);
        }
        return adName == null ? null : incompleteDownloadHashMap.get(adName);
    }

    static CachedAd loadIncompleteDownload(Context context, String adName) {
        String nextIncompleteAd = getIncompleteDownload(context, adName);
        if (nextIncompleteAd == null) {
            return null;
        }
        return load(context, nextIncompleteAd);
    }

    static synchronized void setIncompleteDownload(Context context, String adName, String id) {
        if (!incompleteDownloadHashMapLoaded) {
            loadIncompleteDownloadHashMap(context);
        }
        if (adName != null) {
            Map<String, String> map = incompleteDownloadHashMap;
            if (id == null) {
                id = "";
            }
            map.put(adName, id);
            saveIncompleteDownloadHashMap(context, adName);
        }
    }

    private static void loadApidListSet(SharedPreferences settings) {
        String[] apidArray;
        apidListSet = new HashSet();
        String apids = settings.getString(CACHE_PREFIX_APID, null);
        if (apids != null && (apidArray = apids.split(MMSDK.COMMA)) != null && apidArray.length > 0) {
            for (String apid : apidArray) {
                apidListSet.add(apid);
            }
        }
    }

    private static void saveApidListSet(SharedPreferences.Editor editor, String adName) {
        String apid;
        int start = adName.indexOf(95);
        if (start >= 0 && start < adName.length() && (apid = adName.substring(start + 1)) != null && !apidListSet.contains(apid)) {
            StringBuilder builder = null;
            if (!apidListSet.isEmpty()) {
                java.util.Iterator<String> apidIter = apidListSet.iterator();
                builder = new StringBuilder();
                while (apidIter.hasNext()) {
                    builder.append(apidIter.next() + MMSDK.COMMA);
                }
            }
            editor.putString(CACHE_PREFIX_APID, (builder == null ? "" : builder.toString()) + apid);
            apidListSet.add(apid);
        }
    }

    static class Iterator {
        static final int ITERATE_ID = 0;
        static final int ITERATE_INFO = 1;
        static final int ITERATE_OBJECT = 2;

        Iterator() {
        }

        boolean callback(String id) {
            return false;
        }

        boolean callback(String id, int type, Date expiration, String acid, long deferredViewStart, ObjectInputStream objectInputStream) {
            return false;
        }

        boolean callback(CachedAd ad) {
            return false;
        }

        void done() {
        }
    }

    /* JADX INFO: Removed unreachable split cross block B:92:0x0055 */
    /* JADX WARN: Code duplicated, block: B:111:0x0031 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:13:0x002b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x013b  */
    /* JADX WARN: Code duplicated, block: B:86:0x002d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x00e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static void iterateCachedAds(Context context, int mode, Iterator iterator) {
        File[] adFiles;
        File cachedDir = getInternalCacheDirectory(context);
        ObjectInputStream objectInputStream = null;
        if (cachedDir != null && (adFiles = cachedDir.listFiles(new FileFilter() { // from class: com.millennialmedia.android.AdCache.2
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return !file.isDirectory() && file.getName().endsWith(AdCache.CACHED_AD_FILE);
            }
        })) != null) {
            int len$ = adFiles.length;
            int i$ = 0;
            while (true) {
                ObjectInputStream objectInputStream2 = objectInputStream;
                if (i$ >= len$) {
                    break;
                }
                File adFile = adFiles[i$];
                if (adFile != null) {
                    try {
                        if (adFile.exists()) {
                            if (mode != 0) {
                                objectInputStream = new ObjectInputStream(new FileInputStream(adFile));
                                try {
                                    try {
                                        int type = objectInputStream.readInt();
                                        Date expiration = (Date) objectInputStream.readObject();
                                        String acid = (String) objectInputStream.readObject();
                                        long deferredViewStart = objectInputStream.readLong();
                                        if (mode == 1) {
                                            if (!iterator.callback(adFile.getName(), type, expiration, acid, deferredViewStart, objectInputStream)) {
                                                objectInputStream.close();
                                                ObjectInputStream objectInputStream3 = null;
                                                if (0 != 0) {
                                                    try {
                                                        objectInputStream3.close();
                                                    } catch (IOException e) {
                                                        MMLog.e(TAG, "Failed to close", e);
                                                    }
                                                }
                                            }
                                            if (objectInputStream != null) {
                                                try {
                                                    objectInputStream.close();
                                                    objectInputStream = null;
                                                } catch (IOException e2) {
                                                    MMLog.e(TAG, "Failed to close", e2);
                                                }
                                            }
                                        } else {
                                            CachedAd ad = (CachedAd) objectInputStream.readObject();
                                            if (!iterator.callback(ad)) {
                                                objectInputStream.close();
                                                ObjectInputStream objectInputStream4 = null;
                                                if (0 != 0) {
                                                    try {
                                                        objectInputStream4.close();
                                                    } catch (IOException e3) {
                                                        MMLog.e(TAG, "Failed to close", e3);
                                                    }
                                                }
                                            }
                                            if (objectInputStream != null) {
                                                objectInputStream.close();
                                                objectInputStream = null;
                                            }
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                        MMLog.e(TAG, String.format("There was a problem reading the cached ad %s.", adFile.getName()), e);
                                        if (objectInputStream != null) {
                                            try {
                                                objectInputStream.close();
                                                objectInputStream = null;
                                            } catch (IOException e5) {
                                                MMLog.e(TAG, "Failed to close", e5);
                                            }
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    if (objectInputStream != null) {
                                        try {
                                            objectInputStream.close();
                                        } catch (IOException e6) {
                                            MMLog.e(TAG, "Failed to close", e6);
                                        }
                                    }
                                    throw th;
                                }
                            } else if (iterator.callback(adFile.getName())) {
                                if (objectInputStream2 != null) {
                                    try {
                                        objectInputStream2.close();
                                        objectInputStream = null;
                                    } catch (IOException e7) {
                                        MMLog.e(TAG, "Failed to close", e7);
                                        objectInputStream = objectInputStream2;
                                    }
                                } else {
                                    objectInputStream = objectInputStream2;
                                }
                            } else if (objectInputStream2 != null) {
                                try {
                                    objectInputStream2.close();
                                } catch (IOException e8) {
                                    MMLog.e(TAG, "Failed to close", e8);
                                }
                            }
                        } else if (objectInputStream2 != null) {
                            try {
                                objectInputStream2.close();
                                objectInputStream = null;
                            } catch (IOException e9) {
                                MMLog.e(TAG, "Failed to close", e9);
                                objectInputStream = objectInputStream2;
                            }
                        } else {
                            objectInputStream = objectInputStream2;
                        }
                    } catch (Exception e10) {
                        e = e10;
                        objectInputStream = objectInputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        objectInputStream = objectInputStream2;
                    }
                } else if (objectInputStream2 != null) {
                    objectInputStream2.close();
                    objectInputStream = null;
                } else {
                    objectInputStream = objectInputStream2;
                }
                i$++;
            }
        }
        iterator.done();
    }

    static void cleanCache(final Context context) {
        Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.AdCache.3
            @Override // java.lang.Runnable
            public void run() {
                MMLog.d(AdCache.TAG, AdCache.TAG);
                AdCache.cleanUpExpiredAds(context);
                AdCache.cleanupCache(context);
            }
        });
    }

    static void cleanUpExpiredAds(Context context) {
        iterateCachedAds(context, 1, new Iterator() { // from class: com.millennialmedia.android.AdCache.4
            @Override // com.millennialmedia.android.AdCache.Iterator
            boolean callback(String id, int type, Date expiration, String acid, long deferredViewStart, ObjectInputStream objectInputStream) {
                if (expiration != null && expiration.getTime() <= System.currentTimeMillis()) {
                    try {
                        CachedAd ad = (CachedAd) objectInputStream.readObject();
                        MMLog.e(AdCache.TAG, String.format("Deleting expired ad %s.", ad.getId()));
                        return true;
                    } catch (Exception e) {
                        MMLog.e(AdCache.TAG, String.format("There was a problem reading the cached ad %s.", id), e);
                        return true;
                    }
                }
                return true;
            }
        });
    }

    static void cleanupCache(Context context) {
        cleanupInternalCache(context);
        if (isExternalStorageAvailable(context)) {
            cleanupExternalCache(context);
        }
    }

    private static void cleanupInternalCache(Context context) {
        File internalCacheDir = getInternalCacheDirectory(context);
        if (internalCacheDir != null && internalCacheDir.exists() && internalCacheDir.isDirectory()) {
            HandShake handShake = HandShake.sharedHandShake(context);
            cleanupDirectory(internalCacheDir, handShake.creativeCacheTimeout);
        }
    }

    private static void cleanupExternalCache(Context context) {
        File externalCacheDir = getExternalCacheDirectory(context);
        if (externalCacheDir != null && externalCacheDir.exists() && externalCacheDir.isDirectory()) {
            HandShake handShake = HandShake.sharedHandShake(context);
            cleanupDirectory(externalCacheDir, handShake.creativeCacheTimeout);
        }
    }

    static void cleanupDirectory(File file, long timeout) {
        File[] entries = file.listFiles();
        for (File entry : entries) {
            if (entry.isFile()) {
                if (System.currentTimeMillis() - entry.lastModified() > timeout) {
                    entry.delete();
                }
            } else if (entry.isDirectory()) {
                try {
                    cleanupDirectory(entry, timeout);
                    if (entry.list() != null && entry.list().length == 0) {
                        entry.delete();
                    }
                } catch (SecurityException e) {
                    MMLog.e(TAG, "Security Exception cleaning up directory", e);
                }
            }
        }
    }

    static void resetCache(final Context context) {
        iterateCachedAds(context, 2, new Iterator() { // from class: com.millennialmedia.android.AdCache.5
            @Override // com.millennialmedia.android.AdCache.Iterator
            boolean callback(CachedAd ad) {
                MMLog.d(AdCache.TAG, String.format("Deleting ad %s.", ad.getId()));
                ad.delete(context);
                return true;
            }
        });
        cachedVideoSet = null;
        cachedVideoList = null;
        cachedVideoListLoaded = false;
        if (nextCachedAdHashMap != null) {
            for (String key : nextCachedAdHashMap.keySet()) {
                setNextCachedAd(context, key, null);
            }
        }
        if (incompleteDownloadHashMap != null) {
            for (String key2 : incompleteDownloadHashMap.keySet()) {
                setIncompleteDownload(context, key2, null);
            }
        }
    }

    static File getDownloadFile(Context context, String name) {
        File dir = getExternalCacheDirectory(context);
        if (dir == null) {
            return null;
        }
        File file = new File(dir, name);
        return file;
    }

    static File getDownloadFile(Context context, String subDirectory, String name) {
        File dir = getExternalCacheDirectory(context);
        if (dir == null) {
            return null;
        }
        File file = new File(dir, subDirectory + File.separator + name);
        return file;
    }

    static boolean hasDownloadFile(Context context, String name) {
        File file = getDownloadFile(context, name);
        return file != null && file.exists();
    }

    static File getCacheDirectory(Context context) {
        return isExternalStorageAvailable(context) ? getExternalCacheDirectory(context) : getInternalCacheDirectory(context);
    }

    static File getExternalCacheDirectory(Context context) {
        File externalStorageDir = Environment.getExternalStorageDirectory();
        if (externalStorageDir == null) {
            return null;
        }
        File extCacheDir = new File(externalStorageDir, PRIVATE_CACHE_DIR);
        if (!extCacheDir.exists() && !extCacheDir.mkdirs()) {
            return null;
        }
        return extCacheDir;
    }

    static File getInternalCacheDirectory(Context context) {
        if (context == null) {
            return null;
        }
        File cacheDir = new File(context.getFilesDir(), PRIVATE_CACHE_DIR);
        if (cacheDir != null && !cacheDir.exists() && !cacheDir.mkdirs()) {
            return null;
        }
        return cacheDir;
    }

    private static File getCachedAdFile(Context context, String id) {
        File internalCacheDir;
        String fileName = id + CACHED_AD_FILE;
        boolean isExternalStateMounted = isExternalStorageAvailable(context);
        File cacheDir = getInternalCacheDirectory(context);
        File adFile = null;
        if (cacheDir != null) {
            try {
                if (cacheDir.isDirectory()) {
                    adFile = new File(cacheDir, fileName);
                }
            } catch (Exception e) {
                MMLog.e(TAG, "getCachedAdFile: ", e);
                return null;
            }
        }
        if ((adFile == null || !adFile.exists()) && !isExternalStateMounted && (internalCacheDir = context.getFilesDir()) != null) {
            File internalFile = new File(internalCacheDir, PRIVATE_CACHE_DIR + File.separator + fileName);
            if (internalFile.exists() && internalFile.isFile()) {
                return internalFile;
            }
        }
        return adFile;
    }

    static boolean downloadComponentInternalCache(String urlString, String name, Context context) {
        File internalDir = getInternalCacheDirectory(context);
        if (internalDir == null || !internalDir.isDirectory()) {
            return false;
        }
        return downloadComponent(urlString, name, internalDir, context);
    }

    static boolean downloadComponentExternalStorage(String urlString, String name, Context context) {
        File externalDir = getExternalCacheDirectory(context);
        if (externalDir == null || !externalDir.isDirectory()) {
            return false;
        }
        return downloadComponent(urlString, name, externalDir, context);
    }

    static boolean downloadComponentExternalStorage(String urlString, String subDirectory, String name, Context context) {
        File externalDir = getExternalCacheDirectory(context);
        if (externalDir == null || !externalDir.isDirectory()) {
            return false;
        }
        File externalSubDir = new File(externalDir, subDirectory);
        return downloadComponent(urlString, name, externalSubDir, context);
    }

    private static boolean downloadComponent(String urlString, String name, File path, Context context) throws Throwable {
        if (TextUtils.isEmpty(urlString)) {
            MMLog.d(TAG, "No Url ...");
            return false;
        }
        File file = new File(path, name);
        MMLog.v(TAG, String.format("Downloading Component: %s from %s", name, urlString));
        File parent = file.getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            MMLog.v(TAG, parent + " does not exist and cannot create directory.");
            return false;
        }
        if (file.exists() && file.length() > 0) {
            MMLog.v(TAG, name + " already exists, skipping...");
            return true;
        }
        InputStream inStream = null;
        FileOutputStream out = null;
        try {
            try {
                URL connectURL = new URL(urlString);
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
                conn.setConnectTimeout(30000);
                conn.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET);
                conn.connect();
                inStream = conn.getInputStream();
                String temp = conn.getHeaderField("Content-Length");
                long contentLength = temp != null ? Long.parseLong(temp) : -1L;
                if (inStream == null) {
                    MMLog.e(TAG, String.format("Connection stream is null downloading %s.", name));
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException ex) {
                            MMLog.e(TAG, "Content caching error downloading component: ", ex);
                            if (file != null) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    if (0 == 0) {
                        return false;
                    }
                    out.close();
                    return false;
                }
                FileOutputStream out2 = new FileOutputStream(file);
                try {
                    byte[] buf = new byte[4096];
                    while (true) {
                        int numread = inStream.read(buf);
                        if (numread <= 0) {
                            break;
                        }
                        out2.write(buf, 0, numread);
                    }
                    if (file != null) {
                        try {
                            if (file.length() == contentLength || contentLength == -1) {
                                if (inStream != null) {
                                    try {
                                        inStream.close();
                                    } catch (IOException ex2) {
                                        MMLog.e(TAG, "Content caching error downloading component: ", ex2);
                                        if (file != null) {
                                            file.delete();
                                        }
                                        return false;
                                    }
                                }
                                if (out2 == null) {
                                    return true;
                                }
                                out2.close();
                                return true;
                            }
                            MMLog.e(TAG, "Content-Length does not match actual length.");
                        } catch (SecurityException e) {
                            MMLog.e(TAG, String.format("Exception downloading component %s: ", name), e);
                        }
                    }
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException ex3) {
                            MMLog.e(TAG, "Content caching error downloading component: ", ex3);
                            if (file != null) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    if (out2 != null) {
                        out2.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    out = out2;
                    MMLog.e(TAG, String.format("Exception downloading component %s: %s", name, e));
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException ex4) {
                            MMLog.e(TAG, "Content caching error downloading component: ", ex4);
                            if (file != null) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    out = out2;
                    if (inStream != null) {
                        try {
                            inStream.close();
                        } catch (IOException ex5) {
                            MMLog.e(TAG, "Content caching error downloading component: ", ex5);
                            if (file != null) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    if (out != null) {
                        out.close();
                    }
                    throw th;
                }
                if (file != null) {
                    file.delete();
                }
                return false;
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    static CachedAd load(Context context, String id) throws Throwable {
        if (id == null || id.equals("")) {
            return null;
        }
        ObjectInputStream objectInputStream = null;
        CachedAd ad = null;
        File cachedAdFile = getCachedAdFile(context, id);
        try {
            if (cachedAdFile == null) {
                return null;
            }
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream(cachedAdFile));
                try {
                    objectInputStream2.readInt();
                    objectInputStream2.readObject();
                    objectInputStream2.readLong();
                    ad = (CachedAd) objectInputStream2.readObject();
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e) {
                            MMLog.e(TAG, "Failed to close", e);
                            objectInputStream = objectInputStream2;
                        }
                    }
                    objectInputStream = objectInputStream2;
                } catch (FileNotFoundException e2) {
                    fe = e2;
                    objectInputStream = objectInputStream2;
                    MMLog.e(TAG, "There was a problem loading up the cached ad " + id + ". Ad is not on disk.", fe);
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e3) {
                            MMLog.e(TAG, "Failed to close", e3);
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    objectInputStream = objectInputStream2;
                    MMLog.e(TAG, String.format("There was a problem loading up the cached ad %s.", id), e);
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e5) {
                            MMLog.e(TAG, "Failed to close", e5);
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    objectInputStream = objectInputStream2;
                }
            } catch (FileNotFoundException e6) {
                fe = e6;
            } catch (Exception e7) {
                e = e7;
            }
            return ad;
        } catch (Throwable th2) {
            th = th2;
        }
        if (objectInputStream != null) {
            try {
                objectInputStream.close();
            } catch (IOException e8) {
                MMLog.e(TAG, "Failed to close", e8);
            }
        }
        throw th;
    }

    /* JADX INFO: Removed unreachable split cross block B:64:0x0007 */
    static boolean save(Context context, CachedAd ad) {
        File cachedAdFile;
        boolean z = false;
        File lockFile = null;
        ObjectOutputStream objectOutputStream = null;
        if (ad != null && (cachedAdFile = getCachedAdFile(context, ad.getId())) != null) {
            MMLog.v(TAG, String.format("Saving CachedAd %s to %s", ad.getId(), cachedAdFile));
            try {
                File lockFile2 = new File(cachedAdFile.getParent(), ad.getId() + LOCK_FILE);
                try {
                    if (lockFile2.createNewFile()) {
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(cachedAdFile));
                        try {
                            objectOutputStream2.writeInt(ad.getType());
                            objectOutputStream2.writeObject(ad.expiration);
                            objectOutputStream2.writeObject(ad.acid);
                            objectOutputStream2.writeLong(ad.deferredViewStart);
                            objectOutputStream2.writeObject(ad);
                            try {
                                lockFile2.delete();
                                if (objectOutputStream2 != null) {
                                    objectOutputStream2.close();
                                }
                            } catch (IOException e) {
                                MMLog.e(TAG, "Failed to close", e);
                            }
                            if (ad.saveAssets(context)) {
                                objectOutputStream = objectOutputStream2;
                                lockFile = lockFile2;
                                z = true;
                            } else {
                                ad.delete(context);
                                objectOutputStream = objectOutputStream2;
                                lockFile = lockFile2;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            objectOutputStream = objectOutputStream2;
                            lockFile = lockFile2;
                            MMLog.e(TAG, String.format("There was a problem saving the cached ad %s.", ad.getId()), e);
                            try {
                                lockFile.delete();
                                if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                }
                            } catch (IOException e3) {
                                MMLog.e(TAG, "Failed to close", e3);
                            }
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            lockFile = lockFile2;
                            try {
                                lockFile.delete();
                                if (objectOutputStream != null) {
                                    objectOutputStream.close();
                                }
                            } catch (IOException e4) {
                                MMLog.e(TAG, "Failed to close", e4);
                            }
                            throw th;
                        }
                    } else {
                        MMLog.v(TAG, String.format("Could not save the cached ad %s. Ad was locked.", ad.getId()));
                        try {
                            lockFile2.delete();
                            if (0 != 0) {
                                objectOutputStream.close();
                            }
                        } catch (IOException e5) {
                            MMLog.e(TAG, "Failed to close", e5);
                        }
                        lockFile = lockFile2;
                    }
                } catch (Exception e6) {
                    e = e6;
                    lockFile = lockFile2;
                } catch (Throwable th2) {
                    th = th2;
                    lockFile = lockFile2;
                }
            } catch (Exception e7) {
                e = e7;
            }
        }
        return z;
    }

    static boolean deleteFile(Context context, String fileName) {
        File deleteFile = getCachedAdFile(context, fileName);
        if (deleteFile != null) {
            return deleteFile.delete();
        }
        return false;
    }

    static boolean isExternalStorageAvailable(Context context) {
        if (context == null) {
            return false;
        }
        String storageState = Environment.getExternalStorageState();
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0 && !TextUtils.isEmpty(storageState) && storageState.equals("mounted") && isExternalEnabled;
    }

    static boolean isExternalMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    static void setEnableExternalStorage(boolean on) {
        isExternalEnabled = on;
    }
}
