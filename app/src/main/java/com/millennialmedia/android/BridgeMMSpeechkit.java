package com.millennialmedia.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.mopub.common.Constants;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BridgeMMSpeechkit extends MMJSObject implements MediaPlayer.OnCompletionListener, BridgeMMMedia.Audio.PeriodicListener {
    private static final String TAG = "BridgeMMSpeechkit";
    private String START_RECORDING = "startRecording";
    private String END_RECORDING = "endRecording";
    private String CACHE_AUDIO = "cacheAudio";
    private String GET_SESSION_ID = "getSessionId";
    private String PLAY_AUDIO = "playAudio";
    private String TEXT_TO_SPEECH = "textToSpeech";
    private String STOP_AUDIO = "stopAudio";
    private String SAMPLE_BACKGROUND_AUDIO_LEVEL = "sampleBackgroundAudioLevel";
    private String RELEASE_VOICE = "releaseVoice";
    private String ADD_CUSTOM_VOICE_WORDS = "addCustomVoiceWords";
    private String DELETE_CUSTOM_VOICE_WORDS = "deleteCustomVoiceWords";

    BridgeMMSpeechkit() {
    }

    @Override // com.millennialmedia.android.MMJSObject
    MMJSResponse executeCommand(String name, Map<String, String> arguments) {
        if (this.START_RECORDING.equals(name)) {
            MMJSResponse response = startRecording(arguments);
            return response;
        }
        if (this.END_RECORDING.equals(name)) {
            MMJSResponse response2 = endRecording(arguments);
            return response2;
        }
        if (this.CACHE_AUDIO.equals(name)) {
            MMJSResponse response3 = cacheAudio(arguments);
            return response3;
        }
        if (this.GET_SESSION_ID.equals(name)) {
            MMJSResponse response4 = getSessionId(arguments);
            return response4;
        }
        if (this.PLAY_AUDIO.equals(name)) {
            MMJSResponse response5 = playAudio(arguments);
            return response5;
        }
        if (this.TEXT_TO_SPEECH.equals(name)) {
            MMJSResponse response6 = textToSpeech(arguments);
            return response6;
        }
        if (this.STOP_AUDIO.equals(name)) {
            MMJSResponse response7 = stopAudio(arguments);
            return response7;
        }
        if (this.SAMPLE_BACKGROUND_AUDIO_LEVEL.equals(name)) {
            MMJSResponse response8 = sampleBackgroundAudioLevel(arguments);
            return response8;
        }
        if (this.RELEASE_VOICE.equals(name)) {
            MMJSResponse response9 = releaseVoice(arguments);
            return response9;
        }
        if (this.ADD_CUSTOM_VOICE_WORDS.equals(name)) {
            MMJSResponse response10 = addCustomVoiceWords(arguments);
            return response10;
        }
        if (!this.DELETE_CUSTOM_VOICE_WORDS.equals(name)) {
            return null;
        }
        MMJSResponse response11 = deleteCustomVoiceWords(arguments);
        return response11;
    }

    private NVASpeechKit getCreateSpeechKit() {
        NVASpeechKit speechKit = null;
        MMWebView webView = this.mmWebViewRef.get();
        if (webView != null && webView.allowSpeechCreationCommands()) {
            if (getSpeechKitInternal() != null) {
                return getSpeechKitInternal();
            }
            Context context = webView.getContext();
            if (context != null) {
                speechKit = new NVASpeechKit(webView);
                setSpeechKit(speechKit);
                HandShake.NuanceCredentials credentials = HandShake.sharedHandShake(context).nuanceCredentials;
                if (credentials != null) {
                    speechKit.initialize(credentials, context.getApplicationContext());
                }
            }
        }
        return speechKit;
    }

    private NVASpeechKit getSpeechKit() {
        MMWebView webView = this.mmWebViewRef.get();
        if (webView == null || !webView.allowRecordingCommands()) {
            return null;
        }
        return getSpeechKitInternal();
    }

    private NVASpeechKit getSpeechKitRelease() {
        return getSpeechKitInternal();
    }

    public MMJSResponse startRecording(Map<String, String> arguments) {
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        String language = arguments.get("language");
        if (TextUtils.isEmpty(language)) {
            language = "en_GB";
        }
        if (speechKit.startRecording(language)) {
            return MMJSResponse.responseWithSuccess();
        }
        return MMJSResponse.responseWithError("Failed in speechKit");
    }

    public MMJSResponse endRecording(Map<String, String> arguments) {
        MMJSResponse mMJSResponseResponseWithError;
        NVASpeechKit speechKit = getSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to get speech kit");
        }
        synchronized (speechKit) {
            if (speechKit.endRecording()) {
                mMJSResponseResponseWithError = MMJSResponse.responseWithSuccess();
            } else {
                mMJSResponseResponseWithError = MMJSResponse.responseWithError("Failed in speechKit");
            }
        }
        return mMJSResponseResponseWithError;
    }

    public MMJSResponse cacheAudio(Map<String, String> arguments) {
        Context context;
        String url = arguments.get("url");
        if (!URLUtil.isValidUrl(url)) {
            return MMJSResponse.responseWithError("Invalid url");
        }
        if (this.contextRef != null && (context = this.contextRef.get()) != null) {
            String fileName = url.substring(url.lastIndexOf("/") + 1);
            if (AdCache.downloadComponentExternalStorage(url, fileName, context)) {
                injectJavascript("javascript:MMJS.sdk.audioCached()");
                return MMJSResponse.responseWithSuccess("Successfully cached audio at " + url);
            }
        }
        return MMJSResponse.responseWithError("Failed to cache audio at" + url);
    }

    public MMJSResponse getSessionId(Map<String, String> arguments) {
        NVASpeechKit speechKit = getSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("No SpeechKit session open.");
        }
        String sessionId = speechKit.getSessionId();
        if (!TextUtils.isEmpty(sessionId)) {
            return MMJSResponse.responseWithSuccess(sessionId);
        }
        return MMJSResponse.responseWithError("No SpeechKit session open.");
    }

    public MMJSResponse playAudio(Map<String, String> arguments) {
        Context context;
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        String url = arguments.get("url");
        if (!URLUtil.isValidUrl(url)) {
            return MMJSResponse.responseWithError("Invalid url");
        }
        String path = arguments.get("url");
        if (!TextUtils.isEmpty(path) && (context = this.contextRef.get()) != null) {
            BridgeMMMedia.Audio audio = BridgeMMMedia.Audio.sharedAudio(context);
            if (audio != null) {
                audio.addCompletionListener(this);
                audio.addPeriodicListener(this);
            }
            arguments.put("path", path);
            MMJSResponse response = playAudioInternal(arguments);
            if (response != null && response.result == 1) {
                injectJavascript("javascript:MMJS.sdk.audioStarted()");
                return response;
            }
            return response;
        }
        return null;
    }

    private MMJSResponse playAudioInternal(Map<String, String> arguments) {
        BridgeMMMedia.Audio audio;
        Context context = this.contextRef.get();
        String path = arguments.get("path");
        if (context == null || path == null || (audio = BridgeMMMedia.Audio.sharedAudio(context)) == null) {
            return null;
        }
        if (audio.isPlaying()) {
            return MMJSResponse.responseWithError("Audio already playing.");
        }
        if (path.startsWith(Constants.HTTP)) {
            return audio.playAudio(Uri.parse(path), Boolean.parseBoolean(arguments.get("repeat")));
        }
        File file = AdCache.getDownloadFile(context, path);
        if (file.exists()) {
            return audio.playAudio(Uri.fromFile(file), Boolean.parseBoolean(arguments.get("repeat")));
        }
        return null;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mp) {
        BridgeMMMedia.Audio audio;
        injectJavascript("javascript:MMJS.sdk.audioCompleted()");
        Context context = this.contextRef.get();
        if (context != null && (audio = BridgeMMMedia.Audio.sharedAudio(context)) != null) {
            audio.removeCompletionListener(this);
            audio.removePeriodicListener(this);
        }
    }

    @Override // com.millennialmedia.android.BridgeMMMedia.Audio.PeriodicListener
    public void onUpdate(int currentPositionMillis) {
        injectJavascript("javascript:MMJS.sdk.audioPositionChange(" + currentPositionMillis + ")");
    }

    void injectJavascript(String javascript) {
        MMWebView webView = this.mmWebViewRef.get();
        if (webView != null) {
            webView.loadUrl(javascript);
        }
    }

    public MMJSResponse textToSpeech(Map<String, String> arguments) {
        MMLog.d(TAG, "@@-Calling textToSpeech");
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        String language = arguments.get("language");
        String text = arguments.get("text");
        if (TextUtils.isEmpty(language)) {
            language = "en_GB";
        }
        speechKit.stopActions();
        if (speechKit.textToSpeech(text, language)) {
            return MMJSResponse.responseWithSuccess();
        }
        return MMJSResponse.responseWithError("Failed in speechKit");
    }

    public MMJSResponse stopAudio(Map<String, String> arguments) {
        BridgeMMMedia.Audio audio;
        NVASpeechKit speechKit = getSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to get speech kit");
        }
        speechKit.stopActions();
        if (this.contextRef != null && (audio = BridgeMMMedia.Audio.sharedAudio(this.contextRef.get())) != null) {
            return audio.stop();
        }
        return MMJSResponse.responseWithSuccess();
    }

    public MMJSResponse sampleBackgroundAudioLevel(Map<String, String> arguments) {
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        speechKit.startSampleRecording();
        return null;
    }

    public MMJSResponse releaseVoice(Map<String, String> arguments) {
        return releaseSpeechKit() ? MMJSResponse.responseWithError("Unable to get speech kit") : MMJSResponse.responseWithSuccess("released speechkit");
    }

    static boolean releaseSpeechKit() {
        return getInstance().release();
    }

    public MMJSResponse addCustomVoiceWords(Map<String, String> arguments) {
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        String words = arguments.get("words");
        if (!TextUtils.isEmpty(words)) {
            String[] wordArray = words.split(",");
            speechKit.updateCustomWords(NVASpeechKit.CustomWordsOp.Add, wordArray);
            injectJavascript("javascript:MMJS.sdk.customVoiceWordsAdded()");
            return MMJSResponse.responseWithSuccess("Added " + words);
        }
        return null;
    }

    public MMJSResponse deleteCustomVoiceWords(Map<String, String> arguments) {
        NVASpeechKit speechKit = getCreateSpeechKit();
        if (speechKit == null) {
            return MMJSResponse.responseWithError("Unable to create speech kit");
        }
        String words = arguments.get("words");
        if (!TextUtils.isEmpty(words)) {
            String[] wordArray = words.split(",");
            speechKit.updateCustomWords(NVASpeechKit.CustomWordsOp.Remove, wordArray);
            injectJavascript("javascript:MMJS.sdk.customVoiceWordsDeleted()");
            return MMJSResponse.responseWithSuccess("Deleted " + words);
        }
        return null;
    }

    private static class SpeechKitHolder {
        private NVASpeechKit _speechKit;

        private SpeechKitHolder() {
        }

        public boolean release() {
            if (this._speechKit == null) {
                return false;
            }
            Utils.ThreadUtils.execute(new Runnable() { // from class: com.millennialmedia.android.BridgeMMSpeechkit.SpeechKitHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (SpeechKitHolder.this) {
                        if (SpeechKitHolder.this._speechKit != null) {
                            SpeechKitHolder.this._speechKit.cancelRecording();
                            SpeechKitHolder.this._speechKit.release();
                            SpeechKitHolder.this._speechKit = null;
                        }
                    }
                }
            });
            return true;
        }

        public NVASpeechKit getSpeechKit() {
            return this._speechKit;
        }

        public void setSpeechKit(NVASpeechKit speechKit) {
            this._speechKit = speechKit;
        }
    }

    private static class SingletonHolder {
        public static final SpeechKitHolder INSTANCE = new SpeechKitHolder();

        private SingletonHolder() {
        }
    }

    static SpeechKitHolder getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static NVASpeechKit getSpeechKitInternal() {
        return getInstance().getSpeechKit();
    }

    static void setSpeechKit(NVASpeechKit speechKit) {
        getInstance().release();
        getInstance().setSpeechKit(speechKit);
    }
}
