package com.firsttouchgames.fts15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.firsttouchgames.util.IabHelper;
import com.firsttouchgames.util.IabResult;
import com.firsttouchgames.util.Inventory;
import com.firsttouchgames.util.Purchase;
import com.firsttouchgames.util.SkuDetails;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IAP {
    public static final int GPIAP_STATE_INVENTORYFAIL = 12;
    public static final int IAP_STATE_CONSUMECOMPLETE = 8;
    public static final int IAP_STATE_CONSUMEINPROGRESS = 9;
    public static final int IAP_STATE_CONSUMFAILED = 10;
    public static final int IAP_STATE_INITIALISED = 1;
    public static final int IAP_STATE_PURCHASECOMPLETE = 6;
    public static final int IAP_STATE_PURCHASEFAILED = 7;
    public static final int IAP_STATE_PURCHASEINPROGRESS = 4;
    public static final int IAP_STATE_PURCHASEREFRESHINVENTORY = 5;
    public static final int IAP_STATE_PURCHASEREFRESHINVENTORYPROGRESS = 11;
    public static final int IAP_STATE_READY = 2;
    public static final int IAP_STATE_READY2 = 3;
    public static final int IAP_STATE_UNINITIALISED = 0;
    static final int RC_REQUEST = 10001;
    static final String TAG = "IAP";
    static String base64EncodedPublicKey;
    static Activity mActivity;
    static Context mContext;
    static IabHelper mHelper;
    static Inventory mInventory;
    static List<String> mSkuInfo;
    static boolean mbSetupFinished;
    static String sConsuming;
    static int iStatus = 0;
    static IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() { // from class: com.firsttouchgames.fts15.IAP.1
        @Override // com.firsttouchgames.util.IabHelper.OnIabPurchaseFinishedListener
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                IAP.iStatus = 7;
            } else {
                IAP.mInventory.addPurchase(purchase);
                IAP.iStatus = 6;
            }
        }
    };
    static IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() { // from class: com.firsttouchgames.fts15.IAP.3
        @Override // com.firsttouchgames.util.IabHelper.QueryInventoryFinishedListener
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (result.isFailure()) {
                IAP.complain("Failed to query inventory: " + result);
                IAP.iStatus = 12;
                return;
            }
            IAP.mInventory = inventory;
            if (IAP.iStatus == 11) {
                IAP.iStatus = 6;
            } else {
                IAP.iStatus = 2;
            }
            IAP.mbSetupFinished = true;
        }
    };
    static IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() { // from class: com.firsttouchgames.fts15.IAP.4
        @Override // com.firsttouchgames.util.IabHelper.OnConsumeFinishedListener
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                IAP.mInventory.erasePurchase(purchase.getSku());
                IAP.iStatus = 8;
            } else {
                IAP.complain("Error while consuming: " + result);
                IAP.iStatus = 10;
            }
        }
    };

    public static void temp(Context c, String s) {
        Log.d(TAG, s);
    }

    public static void SetActivity(Activity act) {
        mActivity = act;
        mContext = act.getApplicationContext();
        iStatus = 0;
    }

    public static void SetPublicKeyAndSkuCount(String base64PublicKey, int iMaxSkus) {
        base64EncodedPublicKey = base64PublicKey;
        mSkuInfo = new ArrayList(iMaxSkus);
    }

    public static void AddSku(String sku) {
        mSkuInfo.add(sku);
    }

    public static void QueryInventoryAsync() {
        mHelper.queryInventoryAsync(true, mSkuInfo, mGotInventoryListener);
    }

    public static boolean HasBeenSetup() {
        return mbSetupFinished;
    }

    public static int GetStatus() {
        return iStatus;
    }

    public static void SetStatus(int iNewStatus) {
        iStatus = iNewStatus;
    }

    public static void AcknowledgeResult() {
        iStatus = 2;
    }

    public static boolean HasPurchased(String sSku) {
        if (mInventory != null) {
            return mInventory.hasPurchase(sSku);
        }
        return false;
    }

    public static void Consume(String sSku) {
        sConsuming = sSku;
        iStatus = 9;
        mHelper.consumeAsync(mInventory.getPurchase(sSku), mConsumeFinishedListener);
    }

    public static String GetConsumed() {
        return sConsuming;
    }

    public static void Buy(String skuID) {
        Log.d(TAG, "Buy");
        iStatus = 4;
        mHelper.launchPurchaseFlow(mActivity, skuID, 10001, mPurchaseFinishedListener);
    }

    public static String GetPrice(String skuID) {
        SkuDetails details = mInventory.getSkuDetails(skuID);
        return details.getPrice();
    }

    public static void Init() {
        mbSetupFinished = false;
        mHelper = new IabHelper(mContext, base64EncodedPublicKey);
        mHelper.enableDebugLogging(false);
        iStatus = 1;
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() { // from class: com.firsttouchgames.fts15.IAP.2
            @Override // com.firsttouchgames.util.IabHelper.OnIabSetupFinishedListener
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    IAP.complain("Problem setting up in-app billing: " + result);
                    return;
                }
                IAP.mHelper.handler = new Handler();
                IAP.mHelper.queryInventoryAsync(true, IAP.mSkuInfo, IAP.mGotInventoryListener);
            }
        });
    }

    public static boolean HandleActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper != null) {
            return mHelper.handleActivityResult(requestCode, resultCode, data);
        }
        return false;
    }

    static void complain(String message) {
    }

    public static void Exit() {
        mHelper.dispose();
    }
}
