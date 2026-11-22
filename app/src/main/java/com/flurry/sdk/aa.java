package com.flurry.sdk;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.flurry.android.impl.ads.protocol.v13.AdFrame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class aa {
    private static final String b = aa.class.getSimpleName();
    z a;
    private long c;
    private long d;
    private a e = a.NONE;

    enum a {
        NONE,
        INIT,
        ACTIVE,
        PAUSED
    }

    public synchronized void a(long j, long j2) {
        if (!a()) {
            gd.a(3, b, "Precaching: Initializing AssetCacheManager.");
            this.c = j;
            this.d = j2;
            j();
            this.e = a.INIT;
            a(ab.a.INIT);
        }
    }

    public boolean a() {
        return !a.NONE.equals(this.e);
    }

    public boolean b() {
        return a.ACTIVE.equals(this.e) || a.PAUSED.equals(this.e);
    }

    public boolean c() {
        return a.PAUSED.equals(this.e);
    }

    public List<ae> d() {
        if (a()) {
            return this.a.d();
        }
        return null;
    }

    public void a(ae aeVar) {
        if (a() && aeVar != null && !ah.QUEUED.equals(aeVar.b()) && !ah.IN_PROGRESS.equals(aeVar.b())) {
            this.a.a(aeVar);
        }
    }

    public synchronized void e() {
        if (a() && !b()) {
            gd.a(3, b, "Precaching: Starting AssetCacheManager.");
            this.a = new z("fileStreamCacheDownloader", this.c, this.d, false);
            this.a.e();
            this.e = a.ACTIVE;
            a(ab.a.START);
        }
    }

    public synchronized void f() {
        if (b()) {
            gd.a(3, b, "Precaching: Stopping AssetCacheManager.");
            this.a.f();
            this.e = a.INIT;
            a(ab.a.STOP);
        }
    }

    public synchronized void g() {
        if (b() && c()) {
            gd.a(3, b, "Precaching: Resuming AssetCacheManager.");
            this.a.g();
            this.e = a.ACTIVE;
            a(ab.a.RESUME);
        }
    }

    public void a(List<ap> list) {
        if (b() && list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                d(list.get(size));
            }
            Iterator<ap> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2 = (b(it.next()) > 0 ? 1 : 0) + i;
                if (i2 >= 2) {
                    return;
                } else {
                    i = i2;
                }
            }
        }
    }

    public af a(ap apVar) {
        if (!b()) {
            return af.ERROR;
        }
        if (apVar == null) {
            return af.ERROR;
        }
        if (apVar.a() == null) {
            return af.COMPLETE;
        }
        af afVar = af.NOT_STARTED;
        List<AdFrame> list = apVar.a().adFrames;
        boolean z = false;
        int i = 0;
        af afVar2 = afVar;
        while (i < list.size()) {
            Iterator<String> it = apVar.f(i).iterator();
            boolean z2 = z;
            af afVarA = afVar2;
            while (it.hasNext()) {
                afVarA = a(afVarA, this.a.b(it.next()));
                z2 = true;
            }
            i++;
            afVar2 = afVarA;
            z = z2;
        }
        return !z ? af.COMPLETE : afVar2;
    }

    private af a(af afVar, ah ahVar) {
        if (afVar == null) {
            return af.NOT_STARTED;
        }
        if (ahVar != null) {
            if (ah.ERROR.equals(ahVar)) {
                return af.ERROR;
            }
            if (ah.EVICTED.equals(ahVar)) {
                if (!afVar.equals(af.ERROR)) {
                    return af.EVICTED;
                }
                return afVar;
            }
            if (ah.NONE.equals(ahVar) || ah.CANCELLED.equals(ahVar)) {
                if (!afVar.equals(af.ERROR) && !afVar.equals(af.EVICTED)) {
                    return af.INCOMPLETE;
                }
                return afVar;
            }
            if (ah.QUEUED.equals(ahVar) || ah.IN_PROGRESS.equals(ahVar)) {
                if (af.NOT_STARTED.equals(afVar) || af.COMPLETE.equals(afVar)) {
                    return af.IN_PROGRESS;
                }
                return afVar;
            }
            if (ah.COMPLETE.equals(ahVar) && af.NOT_STARTED.equals(afVar)) {
                return af.COMPLETE;
            }
            return afVar;
        }
        return afVar;
    }

    public boolean a(r rVar, ap apVar) {
        if (!b() || rVar == null || apVar == null) {
            return false;
        }
        gd.a(3, b, "Precaching: Saving local assets for adObject:" + rVar.d());
        List<AdFrame> list = apVar.a().adFrames;
        for (int i = 0; i < list.size(); i++) {
            Iterator<String> it = apVar.f(i).iterator();
            while (it.hasNext()) {
                if (!b(rVar, it.next())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void a(r rVar) {
        if (rVar != null) {
            try {
                File fileA = ct.a("fileStreamCacheDownloaderTmp", rVar.d());
                gd.a(3, b, "Precaching: Removing local assets for adObject:" + rVar.d());
                ho.b(fileA);
            } catch (Exception e) {
                gd.a(6, b, "Precaching: Error removing local assets for adObject:" + rVar.d() + " " + e.getMessage(), e);
            }
        }
    }

    private boolean b(r rVar, String str) {
        if (!b() || TextUtils.isEmpty(str)) {
            return false;
        }
        gd.a(3, b, "Precaching: Saving local asset for adObject:" + rVar.d());
        if (ah.COMPLETE.equals(this.a.b(str))) {
            return c(rVar, str);
        }
        return false;
    }

    public boolean a(ap apVar, String str) {
        if (apVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        List<AdFrame> list = apVar.a().adFrames;
        for (int i = 0; i < list.size(); i++) {
            Iterator<String> it = apVar.f(i).iterator();
            while (it.hasNext()) {
                if (it.next().equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public File a(r rVar, String str) {
        if (rVar == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return a(rVar.d(), str);
    }

    public File a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(ct.a("fileStreamCacheDownloaderTmp", i), c(str));
        if (file.exists()) {
            return file;
        }
        return null;
    }

    private void j() {
        try {
            File fileB = ct.b("fileStreamCacheDownloaderTmp");
            gd.a(3, b, "Precaching: Cleaning temp asset directory: " + fileB);
            ho.b(fileB);
        } catch (Exception e) {
            gd.a(6, b, "Precaching: Error cleaning temp asset directory: " + e.getMessage(), e);
        }
    }

    private void d(ap apVar) {
        if (b() && apVar != null) {
            List<AdFrame> list = apVar.a().adFrames;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    Iterator<String> it = apVar.f(i2).iterator();
                    while (it.hasNext()) {
                        b(it.next());
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public int b(ap apVar) {
        if (!b() || apVar == null) {
            return 0;
        }
        List<AdFrame> list = apVar.a().adFrames;
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            AdFrame adFrame = list.get(i);
            Iterator<String> it = apVar.f(i).iterator();
            int i3 = i2;
            while (it.hasNext()) {
                if (a(it.next(), adFrame.assetExpirationTimestampUTCMillis)) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    public void c(ap apVar) {
        if (b() && apVar != null) {
            List<AdFrame> list = apVar.a().adFrames;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    AdFrame adFrame = list.get(i2);
                    for (String str : apVar.f(i2)) {
                        if (a(str, adFrame.assetExpirationTimestampUTCMillis)) {
                            a(str);
                        }
                    }
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public void a(String str) {
        if (b()) {
            this.a.a(str);
        }
    }

    public void h() {
        if (b()) {
            this.a.h();
        }
    }

    public List<ae> i() {
        return !b() ? Collections.emptyList() : this.a.k();
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.a.b(str);
        }
    }

    private boolean a(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        an anVar = an.UNKNOWN;
        String path = Uri.parse(str).getPath();
        if (!TextUtils.isEmpty(path)) {
            String strGuessContentTypeFromName = URLConnection.guessContentTypeFromName(path);
            gd.a(3, b, "Precaching: assetLink: " + str + " urlPath: " + path + " mimeType: " + strGuessContentTypeFromName);
            if (strGuessContentTypeFromName != null) {
                if (strGuessContentTypeFromName.startsWith(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
                    gd.a(3, b, "Precaching: asset is a video: " + str);
                    anVar = an.VIDEO;
                } else if (strGuessContentTypeFromName.startsWith("image")) {
                    gd.a(3, b, "Precaching: asset is an image: " + str);
                    anVar = an.IMAGE;
                } else if (strGuessContentTypeFromName.startsWith("text")) {
                    gd.a(3, b, "Precaching: asset is text: " + str);
                    anVar = an.TEXT;
                } else {
                    gd.a(5, b, "Precaching: could not identify media type for asset: " + str);
                }
            }
        } else {
            gd.a(5, b, "Precaching: could not identify urlPath for asset: " + str);
        }
        return this.a.a(str, anVar, j);
    }

    private boolean c(r rVar, String str) throws Throwable {
        FileOutputStream fileOutputStream;
        Throwable th;
        al.b bVar;
        IOException iOException;
        boolean z;
        FileOutputStream fileOutputStream2;
        al.b bVarC = null;
        if (rVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(ct.a("fileStreamCacheDownloaderTmp", rVar.d()), c(str));
        try {
            if (file.exists()) {
                fileOutputStream2 = null;
            } else {
                if (!ho.a(file)) {
                    throw new IOException("Precaching: Error creating directory to save tmp file:" + file.getAbsolutePath());
                }
                fileOutputStream2 = new FileOutputStream(file);
                try {
                    bVarC = this.a.c(str);
                    try {
                        if (bVarC != null) {
                            hp.a(bVarC.a(), fileOutputStream2);
                            gd.a(3, b, "Precaching: Temp asset " + str + " saved to :" + file.getAbsolutePath());
                        } else {
                            gd.a(3, b, "Precaching: Temp asset not saved.  Could not open cache reader: " + str);
                        }
                    } catch (IOException e) {
                        fileOutputStream = fileOutputStream2;
                        bVar = bVarC;
                        iOException = e;
                        try {
                            gd.a(6, b, "Precaching: Error saving temp file for url:" + str + " " + iOException.getMessage(), iOException);
                            hp.a(bVar);
                            hp.a(fileOutputStream);
                            z = false;
                        } catch (Throwable th2) {
                            th = th2;
                            hp.a(bVar);
                            hp.a(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        fileOutputStream = fileOutputStream2;
                        bVar = bVarC;
                        th = th3;
                        hp.a(bVar);
                        hp.a(fileOutputStream);
                        throw th;
                    }
                } catch (IOException e2) {
                    fileOutputStream = fileOutputStream2;
                    bVar = null;
                    iOException = e2;
                } catch (Throwable th4) {
                    fileOutputStream = fileOutputStream2;
                    bVar = null;
                    th = th4;
                }
            }
            hp.a(bVarC);
            hp.a(fileOutputStream2);
            z = true;
        } catch (IOException e3) {
            fileOutputStream = null;
            iOException = e3;
            bVar = null;
        } catch (Throwable th5) {
            fileOutputStream = null;
            th = th5;
            bVar = null;
        }
        if (z) {
            return z;
        }
        file.delete();
        return z;
    }

    private String c(String str) {
        return ct.c(str);
    }

    private synchronized void a(ab.a aVar) {
        ab abVar = new ab();
        abVar.a = aVar;
        fz.a().a(abVar);
    }
}
