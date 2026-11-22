package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CacheService {
    private static final int APP_VERSION = 1;
    private static final int DISK_CACHE_INDEX = 0;
    static final String UNIQUE_CACHE_NAME = "mopub-cache";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCache sDiskLruCache;

    public interface DiskLruCacheGetListener {
        void onComplete(String str, byte[] bArr);
    }

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache != null) {
            return true;
        }
        File cacheDirectory = getDiskCacheDirectory(context);
        long diskCacheSizeBytes = DeviceUtils.diskCacheSizeBytes(cacheDirectory);
        try {
            sDiskLruCache = DiskLruCache.open(cacheDirectory, 1, 1, diskCacheSizeBytes);
            return true;
        } catch (IOException e) {
            MoPubLog.d("Unable to create DiskLruCache", e);
            return true;
        }
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String key) {
        return Utils.sha1(key);
    }

    public static File getDiskCacheDirectory(Context context) {
        String cachePath = context.getCacheDir().getPath();
        return new File(cachePath + File.separator + UNIQUE_CACHE_NAME);
    }

    public static boolean containsKeyDiskCache(String key) {
        if (sDiskLruCache == null) {
            return false;
        }
        try {
            DiskLruCache.Snapshot snapshot = sDiskLruCache.get(createValidDiskCacheKey(key));
            return snapshot != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String key) {
        if (sDiskLruCache == null) {
            return null;
        }
        return sDiskLruCache.getDirectory() + File.separator + createValidDiskCacheKey(key) + ".0";
    }

    public static byte[] getFromDiskCache(String key) {
        if (sDiskLruCache == null) {
            return null;
        }
        byte[] bytes = null;
        DiskLruCache.Snapshot snapshot = null;
        try {
            try {
                DiskLruCache.Snapshot snapshot2 = sDiskLruCache.get(createValidDiskCacheKey(key));
                if (snapshot2 == null) {
                    if (snapshot2 != null) {
                        snapshot2.close();
                    }
                    return null;
                }
                InputStream in = snapshot2.getInputStream(0);
                if (in != null) {
                    bytes = new byte[(int) snapshot2.getLength(0)];
                    BufferedInputStream buffIn = new BufferedInputStream(in);
                    try {
                        Streams.readStream(buffIn, bytes);
                        Streams.closeStream(buffIn);
                    } catch (Throwable th) {
                        Streams.closeStream(buffIn);
                        throw th;
                    }
                }
                if (snapshot2 == null) {
                    return bytes;
                }
                snapshot2.close();
                return bytes;
            } catch (Exception e) {
                MoPubLog.d("Unable to get from DiskLruCache", e);
                if (0 == 0) {
                    return null;
                }
                snapshot.close();
                return null;
            }
        } catch (Throwable th2) {
            if (0 != 0) {
                snapshot.close();
            }
            throw th2;
        }
    }

    public static void getFromDiskCacheAsync(String key, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(key, diskLruCacheGetListener).execute(new Void[0]);
    }

    public static boolean putToDiskCache(String key, byte[] content) {
        return putToDiskCache(key, new ByteArrayInputStream(content));
    }

    public static boolean putToDiskCache(String key, InputStream content) {
        if (sDiskLruCache == null) {
            return false;
        }
        DiskLruCache.Editor editor = null;
        try {
            editor = sDiskLruCache.edit(createValidDiskCacheKey(key));
            if (editor == null) {
                return false;
            }
            OutputStream outputStream = new BufferedOutputStream(editor.newOutputStream(0));
            Streams.copyContent(content, outputStream);
            outputStream.flush();
            outputStream.close();
            sDiskLruCache.flush();
            editor.commit();
            return true;
        } catch (Exception e) {
            MoPubLog.d("Unable to put to DiskLruCache", e);
            if (editor == null) {
                return false;
            }
            try {
                editor.abort();
                return false;
            } catch (IOException e2) {
                return false;
            }
        }
    }

    public static void putToDiskCacheAsync(String key, byte[] content) {
        new DiskLruCachePutTask(key, content).execute(new Void[0]);
    }

    private static class DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
        private final DiskLruCacheGetListener mDiskLruCacheGetListener;
        private final String mKey;

        DiskLruCacheGetTask(String key, DiskLruCacheGetListener diskLruCacheGetListener) {
            this.mDiskLruCacheGetListener = diskLruCacheGetListener;
            this.mKey = key;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public byte[] doInBackground(Void... voids) {
            return CacheService.getFromDiskCache(this.mKey);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(byte[] bytes) {
            if (isCancelled()) {
                onCancelled();
            } else if (this.mDiskLruCacheGetListener != null) {
                this.mDiskLruCacheGetListener.onComplete(this.mKey, bytes);
            }
        }

        @Override // android.os.AsyncTask
        protected void onCancelled() {
            if (this.mDiskLruCacheGetListener != null) {
                this.mDiskLruCacheGetListener.onComplete(this.mKey, null);
            }
        }
    }

    private static class DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
        private final byte[] mContent;
        private final String mKey;

        DiskLruCachePutTask(String key, byte[] content) {
            this.mKey = key;
            this.mContent = content;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voids) {
            CacheService.putToDiskCache(this.mKey, this.mContent);
            return null;
        }
    }

    @VisibleForTesting
    @Deprecated
    public static void clearAndNullCaches() {
        if (sDiskLruCache != null) {
            try {
                sDiskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException e) {
                sDiskLruCache = null;
            }
        }
    }

    @VisibleForTesting
    @Deprecated
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
