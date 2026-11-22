package com.firsttouchgames.fts15;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;

/* JADX INFO: loaded from: classes.dex */
public class ExpansionDownloaderService extends DownloaderService {
    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyXuQblpTeRVqeKX/QstKd8cvWOWYE59iA1UypIM2Lt/Ofvg2Nv7xcyXgS0IJnByT/LM8OOhrTxZn9bdaZhxLL/ManPF98JQCjfAxN1EFePvAOqFj6Ib42iBwBf7SPp3yGQLJsS3CjcWfBaRvrY8QxVZ3IZFEMg79fnyuFn9AN3ZWVfMymeL9KceH1BDb30FGMYq1wmenmdyPpTZ8KOElsUEQlpVWTc8jbVtwHQhLrvFKdaEjADH2G8xylAmqWRpxzlCaSmWpM6tM5bepT6alWUZAguwdZMUA3CkxvZbAQUSL2igoaYckHvg9xGBXTIxWuIT0Ng3/sMuwiZiz3jkCwIDAQAB";
    private static final byte[] SALT = {-46, 65, 30, -128, -103, -57, 74, -64, 51, 88, -95, -45, 77, -117, -36, -113, -11, 32, -64, 89};

    @Override // com.google.android.vending.expansion.downloader.impl.DownloaderService
    public String getPublicKey() {
        return BASE64_PUBLIC_KEY;
    }

    @Override // com.google.android.vending.expansion.downloader.impl.DownloaderService
    public byte[] getSALT() {
        return SALT;
    }

    @Override // com.google.android.vending.expansion.downloader.impl.DownloaderService
    public String getAlarmReceiverClassName() {
        return SimpleAlarmReceiver.class.getName();
    }
}
