package com.firsttouchgames.fts15;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GooglePlusManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<People.LoadPeopleResult> {
    private static final int RC_SIGN_IN = 0;
    private static final int REQUEST_ACHIEVEMENTS = 1001;
    private static final int REQUEST_ALL_LEADERBOARDS = 1003;
    private static final int REQUEST_LEADERBOARD = 1002;
    private static final String TAG = "GooglePlusManager";
    private static MainActivity mActivity;
    private static ConnectionResult mConnectionResult;
    private static GoogleApiClient mGoogleApiClient;
    private static LeaderboardScoreBuffer mScores;
    private static boolean mbLeaderboardPublic;
    private static int miLeaderboardForScores;
    private static boolean m_bIntentInProgress = false;
    private static boolean m_bSignInClicked = false;
    private static List<String> mFriendIDs = null;
    private static List<String> mFriendNames = null;
    private static List<String> mFriendPictureURLs = null;
    private static boolean mbInSafeMode = false;
    private static String[] m_strLeaderboardIDs = {"CgkIlvr757MREAIQAQ", "CgkIlvr757MREAIQAg", "CgkIlvr757MREAIQAw"};
    private static boolean mbDisplayAchievements = false;
    private static int miDisplayLeaderboards = m_strLeaderboardIDs.length;

    public void Init(MainActivity activity) {
        mActivity = activity;
        mGoogleApiClient = new GoogleApiClient.Builder(activity).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN).addApi(Games.API).addScope(Games.SCOPE_GAMES).build();
    }

    public static void onStart() {
        mGoogleApiClient.connect();
    }

    public static void onStop() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle arg0) {
        Log.d(TAG, "Connected to Google Play Services");
        if (m_bSignInClicked) {
            Toast.makeText(mActivity, "Google Sign-in Complete", 1).show();
            m_bSignInClicked = false;
        }
        Plus.PeopleApi.loadVisible(mGoogleApiClient, 0, null).setResultCallback(this);
        JNI.areConnectedToGoogle();
        if (mbDisplayAchievements) {
            ShowAchievements();
            mbDisplayAchievements = false;
        } else if (miDisplayLeaderboards < m_strLeaderboardIDs.length) {
            ShowLeaderboards(miDisplayLeaderboards);
            miDisplayLeaderboards = m_strLeaderboardIDs.length;
        }
    }

    private static void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.GooglePlusManager.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        boolean unused = GooglePlusManager.m_bIntentInProgress = true;
                        GooglePlusManager.mActivity.startIntentSenderForResult(GooglePlusManager.mConnectionResult.getResolution().getIntentSender(), 0, null, 0, 0, 0);
                    } catch (IntentSender.SendIntentException e) {
                        boolean unused2 = GooglePlusManager.m_bIntentInProgress = false;
                        GooglePlusManager.mGoogleApiClient.connect();
                    }
                }
            }));
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult result) {
        Log.d(TAG, "Failed to connect to Google Plus: " + result);
        boolean bResolve = false;
        if (!m_bIntentInProgress) {
            mConnectionResult = result;
            if (m_bSignInClicked) {
                resolveSignInError();
                bResolve = true;
            }
        }
        if (!bResolve) {
            mbDisplayAchievements = false;
            miDisplayLeaderboards = m_strLeaderboardIDs.length;
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public void onResult(People.LoadPeopleResult peopleData) {
        if (peopleData.getStatus().getStatusCode() == 0) {
            if (mFriendIDs == null) {
                mFriendIDs = new ArrayList();
            }
            if (mFriendNames == null) {
                mFriendNames = new ArrayList();
            }
            if (mFriendPictureURLs == null) {
                mFriendPictureURLs = new ArrayList();
            }
            PersonBuffer personBuffer = peopleData.getPersonBuffer();
            try {
                int iNextPosition = mFriendIDs.size();
                int iCount = personBuffer.getCount();
                for (int iPerson = 0; iPerson < iCount; iPerson++) {
                    Person person = personBuffer.get(iPerson);
                    if (person.getObjectType() == 0) {
                        if (person.hasId()) {
                            Log.d(TAG, "ID: " + person.getId());
                            mFriendIDs.add(iNextPosition, person.getId());
                        }
                        if (person.hasDisplayName()) {
                            Log.d(TAG, "Name: " + person.getDisplayName());
                            mFriendNames.add(iNextPosition, person.getDisplayName());
                        }
                        if (person.getImage().hasUrl()) {
                            Log.d(TAG, "URL: " + person.getImage().getUrl());
                            mFriendPictureURLs.add(iNextPosition, person.getImage().getUrl());
                        }
                        iNextPosition++;
                    }
                }
                personBuffer.close();
                String sNextPageToken = peopleData.getNextPageToken();
                if (sNextPageToken != null) {
                    Plus.PeopleApi.loadVisible(mGoogleApiClient, 0, sNextPageToken).setResultCallback(this);
                    return;
                }
                return;
            } catch (Throwable th) {
                personBuffer.close();
                throw th;
            }
        }
        Log.e(TAG, "Error requesting visible circles: " + peopleData.getStatus());
    }

    public static void Login() {
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            m_bSignInClicked = true;
            resolveSignInError();
        }
    }

    public static void Logout() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() { // from class: com.firsttouchgames.fts15.GooglePlusManager.2
                @Override // com.google.android.gms.common.api.ResultCallback
                public void onResult(Status status) {
                }
            });
            mGoogleApiClient.disconnect();
            mFriendIDs = null;
            mFriendNames = null;
            mFriendPictureURLs = null;
        }
    }

    public static boolean LoggedIn() {
        return mGoogleApiClient.isConnected();
    }

    public void OnActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == 0) {
            if (responseCode != -1) {
                m_bSignInClicked = false;
            }
            m_bIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    public static void Post(final String sText) {
        mActivity.runOnUiThread(new Thread(new Runnable() { // from class: com.firsttouchgames.fts15.GooglePlusManager.3
            @Override // java.lang.Runnable
            public void run() {
                Intent shareIntent = new PlusShare.Builder((Activity) GooglePlusManager.mActivity).setType("text/plain").setText(sText).getIntent();
                GooglePlusManager.mActivity.startActivityForResult(shareIntent, 0);
            }
        }));
    }

    public static int GetNumberOfFriends() {
        if (mFriendIDs == null) {
            return 0;
        }
        return mFriendIDs.size();
    }

    public static void UnlockAchievement(String sAchievementID) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Achievements.unlock(mGoogleApiClient, sAchievementID);
        }
    }

    public static boolean ShowAchievements() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mActivity.startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient), 1001);
            return true;
        }
        mbDisplayAchievements = true;
        Login();
        return false;
    }

    public static void SubmitScore(int iTable, int iScore) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, m_strLeaderboardIDs[iTable], iScore);
        }
    }

    public static boolean ShowLeaderboards(int iTable) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            if (iTable >= 0) {
                mActivity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, m_strLeaderboardIDs[iTable]), 1002);
            } else {
                mActivity.startActivityForResult(Games.Leaderboards.getAllLeaderboardsIntent(mGoogleApiClient), REQUEST_ALL_LEADERBOARDS);
            }
            return true;
        }
        miDisplayLeaderboards = iTable;
        Login();
        return false;
    }
}
