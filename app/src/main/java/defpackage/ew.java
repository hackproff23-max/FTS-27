package defpackage;

import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: loaded from: classes.dex */
public final class ew implements X509TrustManager {
    private static X509TrustManager b;
    private ex a;

    public ew(ex exVar) throws GeneralSecurityException {
        this.a = exVar;
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        b = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        boolean z;
        boolean z2 = false;
        try {
            b.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException e) {
            if (!this.a.a) {
                throw e;
            }
        }
        if (this.a.b != null) {
            int i = 0;
            while (true) {
                if (i >= x509CertificateArr.length) {
                    z = false;
                    break;
                }
                String name = x509CertificateArr[i].getSubjectDN().getName();
                int iIndexOf = name.indexOf("CN=");
                if (iIndexOf != -1) {
                    int i2 = iIndexOf + 3;
                    int iIndexOf2 = name.indexOf(44, i2);
                    if (this.a.b.equals(iIndexOf2 == -1 ? name.substring(i2) : name.substring(i2, iIndexOf2))) {
                        z = true;
                        break;
                    }
                }
                i++;
            }
            if (!z) {
                throw new CertificateException("certificate summary is not identical");
            }
        }
        if (this.a.c != null) {
            for (X509Certificate x509Certificate : x509CertificateArr) {
                if (this.a.c.equals(bt.a(x509Certificate.getEncoded()))) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                throw new CertificateException("certificate data is not identical");
            }
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return b.getAcceptedIssuers();
    }
}
