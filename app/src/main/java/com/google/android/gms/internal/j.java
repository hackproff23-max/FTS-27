package com.google.android.gms.internal;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class j extends i {
    private static AdvertisingIdClient kO;
    private static CountDownLatch kP = new CountDownLatch(1);
    private static boolean kQ;

    class a {
        private String kR;
        private boolean kS;

        public a(String str, boolean z) {
            this.kR = str;
            this.kS = z;
        }

        public String getId() {
            return this.kR;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.kS;
        }
    }

    protected j(Context context, m mVar, n nVar) {
        super(context, mVar, nVar);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [com.google.android.gms.internal.j$1] */
    public static j a(String str, Context context) {
        e eVar = new e();
        a(str, context, eVar);
        synchronized (j.class) {
            if (kO == null) {
                kO = new AdvertisingIdClient(context);
                new AsyncTask<Void, Void, Void>() { // from class: com.google.android.gms.internal.j.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public Void doInBackground(Void... voidArr) {
                        try {
                            j.kO.start();
                        } catch (GooglePlayServicesNotAvailableException e) {
                            boolean unused = j.kQ = true;
                            AdvertisingIdClient unused2 = j.kO = null;
                        } catch (GooglePlayServicesRepairableException e2) {
                            AdvertisingIdClient unused3 = j.kO = null;
                        } catch (IOException e3) {
                            AdvertisingIdClient unused4 = j.kO = null;
                        }
                        j.kP.countDown();
                        return null;
                    }
                }.execute(new Void[0]);
            }
        }
        return new j(context, eVar, new p(239));
    }

    @Override // com.google.android.gms.internal.i, com.google.android.gms.internal.h
    protected void b(Context context) {
        super.b(context);
        try {
            if (kQ) {
                a(24, d(context));
            } else {
                a aVarZ = z();
                a(28, aVarZ.isLimitAdTrackingEnabled() ? 1L : 0L);
                String id = aVarZ.getId();
                if (id != null) {
                    a(26, 5L);
                    a(24, id);
                }
            }
        } catch (i.a e) {
        } catch (IOException e2) {
        }
    }

    a z() throws IOException {
        a aVar;
        String strA;
        int i = 0;
        synchronized (j.class) {
            try {
                if (!kP.await(2L, TimeUnit.SECONDS)) {
                    aVar = new a(null, false);
                } else if (kO == null) {
                    aVar = new a(null, false);
                } else {
                    AdvertisingIdClient.Info info = kO.getInfo();
                    String id = info.getId();
                    if (id == null || !id.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")) {
                        strA = id;
                    } else {
                        byte[] bArr = new byte[16];
                        int i2 = 0;
                        while (i < id.length()) {
                            if (i == 8 || i == 13 || i == 18 || i == 23) {
                                i++;
                            }
                            bArr[i2] = (byte) ((Character.digit(id.charAt(i), 16) << 4) + Character.digit(id.charAt(i + 1), 16));
                            i2++;
                            i += 2;
                        }
                        strA = this.ky.a(bArr, true);
                    }
                    aVar = new a(strA, info.isLimitAdTrackingEnabled());
                }
            } catch (InterruptedException e) {
                aVar = new a(null, false);
            }
        }
        return aVar;
    }
}
