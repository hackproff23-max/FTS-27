package com.facebook;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
final class AccessTokenManager {
    static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private TokenRefreshRequest currentTokenRefreshRequest;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;

    AccessTokenManager(LocalBroadcastManager localBroadcastManager, AccessTokenCache accessTokenCache) {
        Validate.notNull(localBroadcastManager, "localBroadcastManager");
        Validate.notNull(accessTokenCache, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager;
        this.accessTokenCache = accessTokenCache;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    Context applicationContext = FacebookSdk.getApplicationContext();
                    LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext);
                    AccessTokenCache accessTokenCache = new AccessTokenCache();
                    instance = new AccessTokenManager(localBroadcastManager, accessTokenCache);
                }
            }
        }
        return instance;
    }

    AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    boolean loadCurrentAccessToken() {
        AccessToken accessToken = this.accessTokenCache.load();
        if (accessToken == null) {
            return false;
        }
        setCurrentAccessToken(accessToken, false);
        return true;
    }

    void setCurrentAccessToken(AccessToken currentAccessToken) {
        setCurrentAccessToken(currentAccessToken, true);
    }

    private void setCurrentAccessToken(AccessToken currentAccessToken, boolean saveToCache) {
        AccessToken oldAccessToken = this.currentAccessToken;
        this.currentAccessToken = currentAccessToken;
        this.currentTokenRefreshRequest = null;
        this.lastAttemptedTokenExtendDate = new Date(0L);
        if (saveToCache) {
            if (currentAccessToken != null) {
                this.accessTokenCache.save(currentAccessToken);
            } else {
                this.accessTokenCache.clear();
            }
        }
        if (!Utility.areObjectsEqual(oldAccessToken, currentAccessToken)) {
            sendCurrentAccessTokenChangedBroadcast(oldAccessToken, currentAccessToken);
        }
    }

    private void sendCurrentAccessTokenChangedBroadcast(AccessToken oldAccessToken, AccessToken currentAccessToken) {
        Intent intent = new Intent(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, oldAccessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, currentAccessToken);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            this.currentTokenRefreshRequest = new TokenRefreshRequest(this.currentAccessToken);
            this.currentTokenRefreshRequest.bind();
        }
    }

    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken == null || this.currentTokenRefreshRequest != null) {
            return false;
        }
        Long now = Long.valueOf(new Date().getTime());
        return this.currentAccessToken.getSource().canExtendToken() && now.longValue() - this.lastAttemptedTokenExtendDate.getTime() > 3600000 && now.longValue() - this.currentAccessToken.getLastRefresh().getTime() > 86400000;
    }

    class TokenRefreshRequest implements ServiceConnection {
        final Messenger messageReceiver;
        Messenger messageSender = null;

        TokenRefreshRequest(AccessToken accessToken) {
            this.messageReceiver = new Messenger(new TokenRefreshRequestHandler(accessToken, this));
        }

        public void bind() {
            Context context = FacebookSdk.getApplicationContext();
            Intent intent = NativeProtocol.createTokenRefreshIntent(context);
            if (intent != null && context.bindService(intent, this, 1)) {
                AccessTokenManager.this.lastAttemptedTokenExtendDate = new Date();
            } else {
                cleanup();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            this.messageSender = new Messenger(service);
            refreshToken();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName arg) {
            cleanup();
            try {
                FacebookSdk.getApplicationContext().unbindService(this);
            } catch (IllegalArgumentException e) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cleanup() {
            if (AccessTokenManager.this.currentTokenRefreshRequest == this) {
                AccessTokenManager.this.currentTokenRefreshRequest = null;
            }
        }

        private void refreshToken() {
            Bundle requestData = new Bundle();
            requestData.putString("access_token", AccessTokenManager.this.getCurrentAccessToken().getToken());
            Message request = Message.obtain();
            request.setData(requestData);
            request.replyTo = this.messageReceiver;
            try {
                this.messageSender.send(request);
            } catch (RemoteException e) {
                cleanup();
            }
        }
    }

    static class TokenRefreshRequestHandler extends Handler {
        private AccessToken accessToken;
        private TokenRefreshRequest tokenRefreshRequest;

        TokenRefreshRequestHandler(AccessToken accessToken, TokenRefreshRequest tokenRefreshRequest) {
            super(Looper.getMainLooper());
            this.accessToken = accessToken;
            this.tokenRefreshRequest = tokenRefreshRequest;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            if (currentAccessToken != null && currentAccessToken.equals(this.accessToken) && msg.getData().getString("access_token") != null) {
                AccessToken newToken = AccessToken.createFromRefresh(this.accessToken, msg.getData());
                AccessToken.setCurrentAccessToken(newToken);
            }
            FacebookSdk.getApplicationContext().unbindService(this.tokenRefreshRequest);
            this.tokenRefreshRequest.cleanup();
        }
    }
}
