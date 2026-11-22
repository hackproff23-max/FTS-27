package com.firsttouchgames.fts15;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Messenger;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import bolts.piff.piff2;
import com.firsttouchgames.util.APKExpansionSupport;
import com.firsttouchgames.util.ZipResourceFile;
import com.firsttouchgames.util.typeUtils;
import com.flurry.android.FlurryAgent;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;
import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.ServerManagedPolicy;
import com.mopub.mobileads.MoPubView;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity implements IDownloaderClient {
    private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlyXuQblpTeRVqeKX/QstKd8cvWOWYE59iA1UypIM2Lt/Ofvg2Nv7xcyXgS0IJnByT/LM8OOhrTxZn9bdaZhxLL/ManPF98JQCjfAxN1EFePvAOqFj6Ib42iBwBf7SPp3yGQLJsS3CjcWfBaRvrY8QxVZ3IZFEMg79fnyuFn9AN3ZWVfMymeL9KceH1BDb30FGMYq1wmenmdyPpTZ8KOElsUEQlpVWTc8jbVtwHQhLrvFKdaEjADH2G8xylAmqWRpxzlCaSmWpM6tM5bepT6alWUZAguwdZMUA3CkxvZbAQUSL2igoaYckHvg9xGBXTIxWuIT0Ng3/sMuwiZiz3jkCwIDAQAB";
    private static final String LOG_TAG = "FTGMainActivity";
    static AssetManager assetManager;
    static GooglePlusManager mGooglePlus;
    static MainActivity mThis;
    AdSupport mAdSupport;
    private LicenseChecker mChecker;
    private IStub mDownloaderClientStub;
    private LicenseCheckerCallback mLicenseCheckerCallback;
    FrameLayout mParent;
    private IDownloaderService mRemoteService;
    GLSurfaceView mView;
    private static int iVersionCode = 59;
    private static int iExpansionSize = 115617792;
    private static int iLastTouchCount = 0;
    private static final byte[] SALT = {-46, 65, 30, -128, -103, -57, 74, -64, 51, 88, -95, -45, 77, -117, -36, -113, -11, 32, -64, 89};
    static boolean started = false;
    static boolean mbEnterSafeMode = false;

    static {
        try {
            System.loadLibrary("droicergadget");
            System.loadLibrary("FTS15");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("NmgNativeActivity encountered IllegalArgumentException exception. \n" + e);
        }
    }

    @Override // com.google.android.vending.expansion.downloader.IDownloaderClient
    public void onDownloadProgress(DownloadProgressInfo progress) {
        JNI.setExpansionProgress((int) ((progress.mOverallProgress * 100) / progress.mOverallTotal));
    }

    public void downloaderStart() {
        try {
            Intent launchIntent = getIntent();
            Intent intentToLaunchThisActivityFromNotification = new Intent(this, getClass());
            intentToLaunchThisActivityFromNotification.setFlags(335544320);
            intentToLaunchThisActivityFromNotification.setAction(launchIntent.getAction());
            if (launchIntent.getCategories() != null) {
                for (String category : launchIntent.getCategories()) {
                    intentToLaunchThisActivityFromNotification.addCategory(category);
                }
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentToLaunchThisActivityFromNotification, 134217728);
            int startResult = DownloaderClientMarshaller.startDownloadServiceIfRequired(this, pendingIntent, (Class<?>) ExpansionDownloaderService.class);
            if (startResult == 0 || startResult == 1) {
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Cannot find own package! MAYDAY!");
            e.printStackTrace();
        }
    }

    public static void BeginDownload() {
        mThis.downloaderStart();
    }

    public static void ResumeDownload() {
        mThis.mRemoteService.requestContinueDownload();
    }

    public static void AllowCellularDownload() {
        mThis.mRemoteService.setDownloadFlags(1);
        mThis.mRemoteService.requestContinueDownload();
    }

    public static void TurnOnWifi() {
        WifiManager wifiManager = (WifiManager) mThis.getApplicationContext().getSystemService("wifi");
        wifiManager.setWifiEnabled(true);
        mThis.mRemoteService.requestContinueDownload();
    }

    public static boolean CheckSpaceForDownload() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long lFree = (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        Log.d("MEM", "Free Memory: " + lFree);
        return lFree > ((long) (iExpansionSize / 1024));
    }

    @Override // com.google.android.vending.expansion.downloader.IDownloaderClient
    public void onDownloadStateChanged(int newState) {
        switch (newState) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 12:
            case IDownloaderClient.STATE_FAILED_CANCELED /* 18 */:
            case 19:
                return;
            case 5:
                try {
                    ZipResourceFile expansionFile = APKExpansionSupport.getAPKExpansionZipFile(getApplicationContext(), iVersionCode, 1);
                    if (expansionFile != null) {
                        ZipResourceFile.ZipEntryRO[] zip = expansionFile.getAllEntries();
                        JNI.setExpansionPath(zip[0].mZipFileName);
                        return;
                    }
                    return;
                } catch (IOException e) {
                    return;
                }
            case 6:
                break;
            case 8:
            case 9:
                JNI.setExpansionProgress(-4);
                return;
            case 10:
            case 11:
            case 13:
            case 17:
            default:
                JNI.setExpansionProgress(-1);
                return;
            case 14:
                JNI.setExpansionProgress(-3);
                return;
            case 15:
                JNI.setExpansionProgress(-2);
                return;
            case 16:
                JNI.setExpansionProgress(-1);
                break;
        }
        JNI.setExpansionProgress(-1);
    }

    @Override // com.google.android.vending.expansion.downloader.IDownloaderClient
    public void onServiceConnected(Messenger m) {
        this.mRemoteService = DownloaderServiceMarshaller.CreateProxy(m);
        this.mRemoteService.onClientUpdated(this.mDownloaderClientStub.getMessenger());
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || !IAP.HandleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
            FacebookManager.OnActivityResult(requestCode, resultCode, data);
            mGooglePlus.OnActivityResult(requestCode, resultCode, data);
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FacebookManager.onSaveInstanceState(outState);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent event) {
        float touched_x = event.getX();
        float touched_y = event.getY();
        int action = event.getAction();
        switch (action & MotionEventCompat.ACTION_MASK) {
            case 0:
                iLastTouchCount++;
                break;
            case 1:
                iLastTouchCount--;
                break;
            case 2:
                int iMax = event.getPointerCount();
                for (int i = 0; i < iMax; i++) {
                    int ptrIdx = i;
                    if (ptrIdx >= 0) {
                        float touched_x2 = event.getX(ptrIdx);
                        float touched_y2 = event.getY(ptrIdx);
                        JNI.AddTouch(touched_x2, touched_y2, 2);
                    }
                }
                return true;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                int ptrId = action >> 8;
                event.findPointerIndex(ptrId);
                float touched_x3 = event.getX(ptrId);
                float touched_y3 = event.getY(ptrId);
                JNI.AddTouch(touched_x3, touched_y3, 0);
                iLastTouchCount++;
                return true;
            case 6:
                int ptrId2 = action >> 8;
                event.findPointerIndex(ptrId2);
                float touched_x4 = event.getX(ptrId2);
                float touched_y4 = event.getY(ptrId2);
                JNI.AddTouch(touched_x4, touched_y4, 1);
                iLastTouchCount--;
                return true;
            default:
                return false;
        }
        JNI.AddTouch(touched_x, touched_y, action);
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!ChartboostManager.onBackPressed()) {
            JNI.backButtonPressed();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == 82) {
            return true;
        }
        return super.onKeyDown(keycode, event);
    }

    boolean expansionFilesDelivered() {
        for (int i = 0; i < iVersionCode; i++) {
            APKExpansionSupport.removeOldExpansionFile(getApplicationContext(), i, i);
        }
        String fileName = Helpers.getExpansionAPKFileName(this, true, iVersionCode);
        return Helpers.doesFileExist(this, fileName, (long) iExpansionSize, false);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        piff2.show(this);
        DeviceManager.GetCPUCores();
        super.onCreate(savedInstanceState);
        applyFullscreenCutout();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float scaleX = dm.widthPixels / dm.xdpi;
        float scaleY = dm.heightPixels / dm.ydpi;
        float scale = (scaleX * scaleX) + (scaleY * scaleY);
        JNI.SetScreenSize((float) Math.sqrt(scale));
        BootManager.SetContext(getApplicationContext());
        if (BootManager.ShouldEnterSafeMode()) {
            mbEnterSafeMode = true;
        }
        typeUtils.LOG_TAG = "Util";
        this.mDownloaderClientStub = DownloaderClientMarshaller.CreateStub(this, ExpansionDownloaderService.class);
        if (!expansionFilesDelivered()) {
        }
        mThis = this;
        this.mAdSupport = new AdSupport();
        this.mAdSupport.OnCreate(this);
        KCStorage.SetActivity(this);
        AppLovinManager.Initialise(this);
        FacebookManager.Init(this);
        mGooglePlus = new GooglePlusManager();
        mGooglePlus.Init(this);
        ForceChartboostOnCreate();
    }

    public static void ForceChartboostOnCreate() {
        ChartboostManager.OnCreate(mThis, "53ec9e7589b0bb33dd25a106", "b542d3d9f60023a27f10e856ee80e622b833c3fe");
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (!mbEnterSafeMode) {
            this.mView.onPause();
            JNI.appPaused();
            AdSupport.OnPause();
            ChartboostManager.onPause();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || this.mView == null || this.mView.getVisibility() == 8) {
        }
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            applyFullscreenCutout();
        }
    }

    // FTS27: fullscreen imersivo e desenha tambem sobre o recorte (notch/camera)
    private void applyFullscreenCutout() {
        try {
            getWindow().addFlags(1024); // FLAG_FULLSCREEN
            if (Build.VERSION.SDK_INT >= 28) {
                android.view.WindowManager.LayoutParams lp = getWindow().getAttributes();
                // 1 = SHORT_EDGES (API 28+), 3 = ALWAYS (API 30+)
                lp.layoutInDisplayCutoutMode = Build.VERSION.SDK_INT >= 30 ? 3 : 1;
                getWindow().setAttributes(lp);
            }
            // LAYOUT_STABLE | LAYOUT_HIDE_NAVIGATION | LAYOUT_FULLSCREEN | HIDE_NAVIGATION | FULLSCREEN | IMMERSIVE_STICKY
            getWindow().getDecorView().setSystemUiVisibility(5894);
        } catch (Throwable t) {
            Log.e(LOG_TAG, "applyFullscreenCutout: " + t);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // android.app.Activity
    public void onResume() {
        if (this.mDownloaderClientStub != null) {
            this.mDownloaderClientStub.connect(this);
        }
        super.onResume();
        if (!mbEnterSafeMode) {
            this.mView.onResume();
            AdSupport.OnResume(this);
            mThis = this;
            ChartboostManager.onResume();
            MoPubManager.UnloadAd();
            JNI.appResumed();
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        if (this.mDownloaderClientStub != null) {
            this.mDownloaderClientStub.disconnect(this);
        }
        super.onStop();
        if (!mbEnterSafeMode) {
            PushNotifications.SendAllTimedNotifications();
            AdSupport.OnStop();
            GooglePlusManager.onStop();
            ChartboostManager.onStop();
            FlurryAgent.onEndSession(this);
            return;
        }
        JNI.appPaused();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        JNI.appDestroyed();
        ChartboostManager.onDestroy();
        MoPubManager.Destroy();
        started = false;
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        assetManager = getAssets();
        JNI.getAssetManager(assetManager);
        Application app = getApplication();
        File f = app.getExternalFilesDir(null);
        if (f == null) {
            f = app.getFilesDir();
        }
        JNI.setExternalFilesDir(f.getAbsolutePath());
        DeviceManager.SetActivity(this);
        if (mbEnterSafeMode) {
            Intent intent = new Intent(this, (Class<?>) SafeModeActivity.class);
            startActivity(intent);
        }
        FlurryAgent.init(this, "JPV4SW97QXHQY6GW3GWZ");
        FlurryAgent.onStartSession(this);
        AdSupport.OnStart();
        if (!started) {
            ChartboostManager.onStart();
            GooglePlusManager.onStart();
            setContentView(com.FTS27.giiodroicerargaming.R.layout.main);
            started = true;
            this.mView = (GLSurfaceView) findViewById(com.FTS27.giiodroicerargaming.R.id.glsurfaceview);
            this.mView.setEGLContextClientVersion(2);
            this.mView.setEGLConfigChooser(true);
            this.mView.setRenderer(new GLRender());
            JNI.getJNIEnv(getApplicationContext());
            JNI.setDeviceModel(Build.MODEL);
            getApplicationContext().getPackageName();
            new Vector();
            if (Environment.getExternalStorageState().equals("mounted")) {
                Environment.getExternalStorageDirectory();
            }
            try {
                ZipResourceFile expansionFile = APKExpansionSupport.getAPKExpansionZipFile(getApplicationContext(), iVersionCode, 1);
                if (expansionFile != null) {
                    ZipResourceFile.ZipEntryRO[] zip = expansionFile.getAllEntries();
                    JNI.setExpansionPath(zip[0].mZipFileName);
                }
            } catch (IOException e) {
            }
            IAP.SetActivity(this);
            URLManager.SetActivity(this);
            EmailManager.SetActivity(this);
            AdSupport.InitialiseAppLovin();
            AdSupport.InitialiseChartboost();
            ChartboostManager.CacheInterstitial();
            MoPubManager.Setup((MoPubView) findViewById(com.FTS27.giiodroicerargaming.R.id.adView), this);
            String deviceId = Settings.Secure.getString(getContentResolver(), "android_id");
            this.mLicenseCheckerCallback = new MyLicenseCheckerCallback();
            this.mChecker = new LicenseChecker(this, new ServerManagedPolicy(this, new AESObfuscator(SALT, getPackageName(), deviceId)), BASE64_PUBLIC_KEY);
            this.mChecker.checkAccess(this.mLicenseCheckerCallback);
            FileManager.Initialise(this);
            ClipboardMngr.onStart(getApplicationContext());
            PushNotifications.Initialise(this);
            SharedPreferences sharedPref = getPreferences(0);
            if (sharedPref.getBoolean(getString(com.FTS27.giiodroicerargaming.R.string.first_launch), true)) {
                PushNotifications.Setup();
            }
        }
    }

    public static void TerminateApp() {
        if (mThis.mDownloaderClientStub != null) {
            mThis.mDownloaderClientStub.disconnect(mThis);
        }
        int pid = Process.myPid();
        Process.killProcess(pid);
    }

    public void EmailAttachment(String sSubject, String sBody, String sFileName) {
        Intent emailIntent = new Intent("android.intent.action.SEND");
        emailIntent.setType("image/png");
        emailIntent.putExtra("android.intent.extra.SUBJECT", sSubject);
        emailIntent.putExtra("android.intent.extra.TEXT", sBody);
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        String externalDirectory = externalDir.toString();
        File file = new File(externalDirectory + "/" + sFileName);
        if (file.exists()) {
            emailIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
            try {
                startActivity(emailIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void OpenWebpage(String url, boolean bChooser) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        if (activities.size() > 0) {
            if (bChooser) {
                Intent chooser = Intent.createChooser(intent, null);
                startActivity(chooser);
            } else {
                startActivity(intent);
            }
        }
    }

    public static String GetPackageName() {
        return mThis.getPackageName();
    }

    private class MyLicenseCheckerCallback implements LicenseCheckerCallback {
        private MyLicenseCheckerCallback() {
        }

        @Override // com.google.android.vending.licensing.LicenseCheckerCallback
        public void allow(int policyReason) {
            if (MainActivity.this.isFinishing()) {
            }
        }

        @Override // com.google.android.vending.licensing.LicenseCheckerCallback
        public void dontAllow(int policyReason) {
            if (MainActivity.this.isFinishing()) {
            }
        }

        @Override // com.google.android.vending.licensing.LicenseCheckerCallback
        public void applicationError(int errorCode) {
            if (MainActivity.this.isFinishing()) {
            }
        }
    }

    public static void logEvent(String sEvent, boolean bTimed) {
        FlurryAgent.logEvent(sEvent, bTimed);
    }

    public static void logEvent(String sEvent, Map<String, String> params, boolean bTimed) {
        FlurryAgent.logEvent(sEvent, params, bTimed);
    }

    public static void endTimedEvent(String sEvent) {
        FlurryAgent.endTimedEvent(sEvent);
    }

    public static void onEndSession() {
        FlurryAgent.onEndSession(mThis);
    }
}
