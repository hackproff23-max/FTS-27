package com.firsttouchgames.fts15;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FacebookManager {
    private static final String LOG_TAG = "FacebookManager";
    private static CallbackManager m_callbackManager;
    private static MainActivity mActivity = null;
    private static List<String> mFriendIDs = null;
    private static List<String> mFriendNames = null;
    private static List<String> mFriendPictureURLs = null;
    private static boolean mbInviteSending = false;
    private static boolean mbInviteSuccess = false;
    private static boolean mbInviteCancelled = false;
    private static boolean mbLoggingIn = false;

    public static void Init(MainActivity activity) {
        mActivity = activity;
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        m_callbackManager = CallbackManager.Factory.create();
        GetKeyHash();
    }

    public static boolean LoggedOrLoggingIn() {
        return mbLoggingIn || LoggedIn();
    }

    public static void Login(boolean bInSafeMode) {
        if (!mbLoggingIn) {
            mbLoggingIn = true;
            LoginManager.getInstance().registerCallback(m_callbackManager, new FacebookCallback<LoginResult>() { // from class: com.firsttouchgames.fts15.FacebookManager.1
                @Override // com.facebook.FacebookCallback
                public void onSuccess(LoginResult loginResult) {
                    boolean unused = FacebookManager.mbLoggingIn = false;
                    JNI.areConnectedToFacebook();
                    Toast.makeText(FacebookManager.mActivity.getApplicationContext(), "Facebook Sign-in Complete", 1).show();
                    if (FacebookManager.LoggedIn()) {
                        LoginManager.getInstance().logOut();
                    }
                }

                @Override // com.facebook.FacebookCallback
                public void onCancel() {
                    boolean unused = FacebookManager.mbLoggingIn = false;
                }

                @Override // com.facebook.FacebookCallback
                public void onError(FacebookException exception) {
                    boolean unused = FacebookManager.mbLoggingIn = false;
                    Toast.makeText(FacebookManager.mActivity.getApplicationContext(), "Failed to log into Facebook: " + exception.toString(), 1).show();
                }
            });
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.FacebookManager.2
                @Override // java.lang.Runnable
                public void run() {
                    LoginManager.getInstance().logInWithReadPermissions(FacebookManager.mActivity, Arrays.asList("public_profile", "user_friends"));
                }
            }));
        }
    }

    public static void Logout() {
        if (LoggedIn()) {
            LoginManager.getInstance().logOut();
        }
    }

    public static boolean LoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    public static String GetKeyHash() {
        try {
            PackageInfo info = mActivity.getPackageManager().getPackageInfo("com.firsttouchgames.fts15", 64);
            Signature[] arr$ = info.signatures;
            int len$ = arr$.length;
            if (0 < len$) {
                Signature signature = arr$[0];
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), 0);
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e2) {
        }
        return null;
    }

    public static void OnActivityResult(int requestCode, int resultCode, Intent data) {
        if (LoggedOrLoggingIn()) {
            m_callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private static boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
        for (String string : subset) {
            if (!superset.contains(string)) {
                return false;
            }
        }
        return true;
    }

    public static void onSaveInstanceState(Bundle bundle) {
    }

    public static void Post(final String sMessage, String sImageURL, final String sURL) {
        if (LoggedIn()) {
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.FacebookManager.3
                @Override // java.lang.Runnable
                public void run() {
                    if (ShareDialog.canShow((Class<? extends ShareContent>) ShareLinkContent.class)) {
                        ShareDialog shareDialog = new ShareDialog(FacebookManager.mActivity);
                        shareDialog.registerCallback(FacebookManager.m_callbackManager, new FacebookCallback<Sharer.Result>() { // from class: com.firsttouchgames.fts15.FacebookManager.3.1
                            @Override // com.facebook.FacebookCallback
                            public void onSuccess(Sharer.Result result) {
                            }

                            @Override // com.facebook.FacebookCallback
                            public void onCancel() {
                            }

                            @Override // com.facebook.FacebookCallback
                            public void onError(FacebookException exception) {
                                Toast.makeText(FacebookManager.mActivity.getApplicationContext(), "Failed to share to Facebook, error: " + exception.toString(), 1).show();
                            }
                        });
                        ShareLinkContent linkContent = new ShareLinkContent.Builder().setContentDescription(sMessage).setContentUrl(Uri.parse(sURL)).build();
                        shareDialog.show(linkContent);
                    }
                }
            }));
        }
    }

    public static String GetFacebookID() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            return profile.getId();
        }
        return null;
    }

    public static String GetFacebookName() {
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            return profile.getName();
        }
        return null;
    }

    public static void AddLikeButton(String sObjectID, boolean bPage, int iXPos, int iYPos) {
        mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.FacebookManager.4
            @Override // java.lang.Runnable
            public void run() {
            }
        }));
    }

    public static void RemoveLikeButton() {
        mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.FacebookManager.5
            @Override // java.lang.Runnable
            public void run() {
            }
        }));
    }

    public static void Shutdown() {
    }
}
