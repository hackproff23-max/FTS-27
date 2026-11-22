package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.pu;
import com.google.android.gms.internal.qv;
import com.google.android.gms.internal.qw;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
class cq implements o.f {
    private final String aqm;
    private bg<pu.a> asC;
    private final ExecutorService asJ = Executors.newSingleThreadExecutor();
    private final Context mContext;

    cq(Context context, String str) {
        this.mContext = context;
        this.aqm = str;
    }

    private cr.c a(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return ba.cI(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            bh.S("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException e2) {
            bh.W("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private void d(pu.a aVar) throws IllegalArgumentException {
        if (aVar.gs == null && aVar.auC == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    private cr.c k(byte[] bArr) {
        try {
            cr.c cVarB = cr.b(com.google.android.gms.internal.c.f.a(bArr));
            if (cVarB == null) {
                return cVarB;
            }
            bh.V("The container was successfully loaded from the resource (using binary file)");
            return cVarB;
        } catch (qv e) {
            bh.T("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (cr.g e2) {
            bh.W("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.o.f
    public void a(bg<pu.a> bgVar) {
        this.asC = bgVar;
    }

    @Override // com.google.android.gms.tagmanager.o.f
    public void b(final pu.a aVar) {
        this.asJ.execute(new Runnable() { // from class: com.google.android.gms.tagmanager.cq.2
            @Override // java.lang.Runnable
            public void run() {
                cq.this.c(aVar);
            }
        });
    }

    boolean c(pu.a aVar) {
        boolean z = false;
        File fileQj = qj();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileQj);
            try {
                fileOutputStream.write(qw.f(aVar));
                z = true;
            } catch (IOException e) {
                bh.W("Error writing resource to disk. Removing resource from disk.");
                fileQj.delete();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    bh.W("error closing stream for writing resource to disk");
                }
            }
        } catch (FileNotFoundException e3) {
            bh.T("Error opening resource file for writing");
        }
        return z;
    }

    @Override // com.google.android.gms.tagmanager.o.f
    public cr.c fH(int i) {
        try {
            InputStream inputStreamOpenRawResource = this.mContext.getResources().openRawResource(i);
            bh.V("Attempting to load a container from the resource ID " + i + " (" + this.mContext.getResources().getResourceName(i) + ")");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                cr.b(inputStreamOpenRawResource, byteArrayOutputStream);
                cr.c cVarA = a(byteArrayOutputStream);
                if (cVarA != null) {
                    bh.V("The container was successfully loaded from the resource (using JSON file format)");
                } else {
                    cVarA = k(byteArrayOutputStream.toByteArray());
                }
                return cVarA;
            } catch (IOException e) {
                bh.W("Error reading the default container with resource ID " + i + " (" + this.mContext.getResources().getResourceName(i) + ")");
                return null;
            }
        } catch (Resources.NotFoundException e2) {
            bh.W("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }

    @Override // com.google.android.gms.tagmanager.o.f
    public void pt() {
        this.asJ.execute(new Runnable() { // from class: com.google.android.gms.tagmanager.cq.1
            @Override // java.lang.Runnable
            public void run() {
                cq.this.qi();
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:10:0x002e  */
    /* JADX WARN: Code duplicated, block: B:12:0x003e  */
    /* JADX WARN: Multi-variable type inference failed */
    void qi() {
        boolean zEquals;
        FileInputStream fileInputStream;
        if (this.asC == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        this.asC.ps();
        bh.V("Attempting to load resource from disk");
        if (ce.qa().qb() != ce.a.CONTAINER) {
            ce.a aVarQb = ce.qa().qb();
            ce.a aVar = ce.a.CONTAINER_DEBUG;
            fileInputStream = aVar;
            if (aVarQb == aVar) {
                String str = this.aqm;
                String containerId = ce.qa().getContainerId();
                zEquals = str.equals(containerId);
                fileInputStream = containerId;
                if (zEquals) {
                    this.asC.a(bg.a.NOT_AVAILABLE);
                    return;
                }
            }
        } else {
            String str2 = this.aqm;
            String containerId2 = ce.qa().getContainerId();
            zEquals = str2.equals(containerId2);
            fileInputStream = containerId2;
            if (zEquals) {
                this.asC.a(bg.a.NOT_AVAILABLE);
                return;
            }
        }
        try {
            try {
                fileInputStream = new FileInputStream(qj());
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    cr.b(fileInputStream, byteArrayOutputStream);
                    pu.a aVarL = pu.a.l(byteArrayOutputStream.toByteArray());
                    d(aVarL);
                    this.asC.l(aVarL);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        bh.W("Error closing stream for reading resource from disk");
                    }
                } catch (IOException e2) {
                    this.asC.a(bg.a.IO_ERROR);
                    bh.W("Failed to read the resource from disk");
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        bh.W("Error closing stream for reading resource from disk");
                    }
                } catch (IllegalArgumentException e4) {
                    this.asC.a(bg.a.IO_ERROR);
                    bh.W("Failed to read the resource from disk. The resource is inconsistent");
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        bh.W("Error closing stream for reading resource from disk");
                    }
                }
                bh.V("The Disk resource was successfully read.");
            } catch (FileNotFoundException e6) {
                bh.S("Failed to find the resource in the disk");
                this.asC.a(bg.a.NOT_AVAILABLE);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (IOException e7) {
                bh.W("Error closing stream for reading resource from disk");
            }
            throw th;
        }
    }

    File qj() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.aqm);
    }

    @Override // com.google.android.gms.common.api.Releasable
    public synchronized void release() {
        this.asJ.shutdown();
    }
}
