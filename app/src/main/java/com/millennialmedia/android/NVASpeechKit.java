package com.millennialmedia.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import com.flurry.android.Constants;
import com.mopub.volley.BuildConfig;
import com.nuance.nmdp.speechkit.DataUploadCommand;
import com.nuance.nmdp.speechkit.DataUploadResult;
import com.nuance.nmdp.speechkit.GenericCommand;
import com.nuance.nmdp.speechkit.GenericResult;
import com.nuance.nmdp.speechkit.Recognition;
import com.nuance.nmdp.speechkit.Recognizer;
import com.nuance.nmdp.speechkit.RecognizerConstants;
import com.nuance.nmdp.speechkit.SpeechError;
import com.nuance.nmdp.speechkit.SpeechKit;
import com.nuance.nmdp.speechkit.Vocalizer;
import com.nuance.nmdp.speechkit.recognitionresult.DetailedResult;
import com.nuance.nmdp.speechkit.util.dataupload.Action;
import com.nuance.nmdp.speechkit.util.dataupload.Data;
import com.nuance.nmdp.speechkit.util.dataupload.DataBlock;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NVASpeechKit {
    static final float AUDIO_LEVEL_CHANGE_INTERVAL = 0.25f;
    static final float AUDIO_LEVEL_MAX = 90.0f;
    private static final int AUDIO_LEVEL_UPDATE_FREQUENCY = 50;
    private static final int AUDIO_SAMPLE_PERIOD = 2000;
    static final float SAMPLING_BG_INTERVAL = 0.1f;
    private static final String TAG = "NVASpeechKit";
    private static String nuanceIdCache = null;
    HandShake.NuanceCredentials _credentials;
    private String nuance_transaction_session_id;
    private String packageName;
    private DataUploadCommand pendingDataUploadCommand;
    private CustomWordsOp pendingDataUploadCommandType;
    private SpeechKit sk;
    private Recognizer skCurrentRecognizer;
    private Vocalizer skCurrentVocalizer;
    private Recognizer.Listener skRecogListener;
    private Vocalizer.Listener skVocalListener;
    private Handler speeckKitHandler;
    private State state;
    private WeakReference<MMWebView> webViewRef;
    public Result[] _results = null;
    private AudioLevelTracker audioLevelTracker = new AudioLevelTracker();
    private Runnable audioSampleCallback = new Runnable() { // from class: com.millennialmedia.android.NVASpeechKit.1
        @Override // java.lang.Runnable
        public void run() {
            NVASpeechKit.this.endRecording();
        }
    };
    private Runnable audioLevelCallback = new Runnable() { // from class: com.millennialmedia.android.NVASpeechKit.2
        @Override // java.lang.Runnable
        public void run() {
            if (NVASpeechKit.this.skCurrentRecognizer != null) {
                double normalizedLevel = AudioLevelTracker.normalize(NVASpeechKit.this.skCurrentRecognizer.getAudioLevel());
                MMLog.d(NVASpeechKit.TAG, "audiolevel changed: level=" + normalizedLevel);
                if (NVASpeechKit.this.audioLevelTracker.update(normalizedLevel) && NVASpeechKit.this.speechKitListener != null) {
                    NVASpeechKit.this.speechKitListener.onAudioLevelUpdate(normalizedLevel);
                }
                if (NVASpeechKit.this.state == State.RECORDING || NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample) {
                    NVASpeechKit.this.speeckKitHandler.postDelayed(NVASpeechKit.this.audioLevelCallback, 50L);
                }
            }
        }
    };
    private GenericCommand.Listener commandListener = new GenericCommand.Listener() { // from class: com.millennialmedia.android.NVASpeechKit.3
        @Override // com.nuance.nmdp.speechkit.GenericCommand.Listener
        public void onComplete(GenericCommand command, GenericResult result, SpeechError error) {
            if (error != null) {
                MMLog.e(NVASpeechKit.TAG, "GenericCommand listener. Error: " + error.getErrorDetail());
            } else {
                MMLog.d(NVASpeechKit.TAG, "GenericCommand listener. Success: " + result.getQueryResult());
            }
            NVASpeechKit.this.notifySpeechResults();
        }
    };
    private DataUploadCommand.Listener dataUploadListener = new DataUploadCommand.Listener() { // from class: com.millennialmedia.android.NVASpeechKit.4
        @Override // com.nuance.nmdp.speechkit.DataUploadCommand.Listener
        public void onResults(DataUploadCommand command, DataUploadResult results) {
            MMLog.d(NVASpeechKit.TAG, "DataUploadCommand listener successful command:" + command.toString() + " isVocRegenerated:" + results.isVocRegenerated() + " results:" + results.toString());
            notifyListener(command);
        }

        @Override // com.nuance.nmdp.speechkit.DataUploadCommand.Listener
        public void onError(DataUploadCommand command, SpeechError error) {
            MMLog.e(NVASpeechKit.TAG, "DataUploadCommand listener error. command:" + command.toString() + " Error:" + error.getErrorDetail());
            notifyListener(command);
        }

        private void notifyListener(DataUploadCommand command) {
            if (NVASpeechKit.this.speechKitListener != null && NVASpeechKit.this.pendingDataUploadCommand == command) {
                if (NVASpeechKit.this.pendingDataUploadCommandType == CustomWordsOp.Add) {
                    NVASpeechKit.this.speechKitListener.onCustomWordsAdded();
                } else {
                    NVASpeechKit.this.speechKitListener.onCustomWordsDeleted();
                }
            }
            NVASpeechKit.this.pendingDataUploadCommand = null;
        }
    };
    private Listener speechKitListener = new Listener() { // from class: com.millennialmedia.android.NVASpeechKit.5
        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onStateChange(State state) {
            switch (AnonymousClass8.$SwitchMap$com$millennialmedia$android$NVASpeechKit$State[state.ordinal()]) {
                case 1:
                    NVASpeechKit.this.voiceStateChangeError();
                    break;
                case 2:
                    NVASpeechKit.this.voiceStateChangeProcessing();
                    break;
                case 3:
                    NVASpeechKit.this.voiceStateChangeReady();
                    break;
                case 4:
                    NVASpeechKit.this.voiceStateChangeRecording();
                    break;
                case 5:
                    NVASpeechKit.this.voiceStateChangeVocalizing();
                    break;
            }
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onResults() {
            JSONArray jsonArray = NVASpeechKit.this.resultsToJSON(NVASpeechKit.this.getResults());
            NVASpeechKit.this.recognitionResult(jsonArray.toString());
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onError() {
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onCustomWordsDeleted() {
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onCustomWordsAdded() {
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onAudioSampleUpdate(double averageAudioLevel) {
            NVASpeechKit.this.backgroundAudioLevel(averageAudioLevel);
        }

        @Override // com.millennialmedia.android.NVASpeechKit.Listener
        public void onAudioLevelUpdate(double audioLevel) {
            NVASpeechKit.this.audioLevelChange(audioLevel);
        }
    };

    public enum CustomWordsOp {
        Add,
        Remove
    }

    public interface Listener {
        void onAudioLevelUpdate(double d);

        void onAudioSampleUpdate(double d);

        void onCustomWordsAdded();

        void onCustomWordsDeleted();

        void onError();

        void onResults();

        void onStateChange(State state);
    }

    public enum State {
        ERROR("error"),
        VOCALIZING("vocalizing"),
        RECORDING("recording"),
        READY("ready"),
        PROCESSING("processing");

        private String name;

        State(String name) {
            this.name = name;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public class Result {
        public final int resultScore;
        public final String resultString;

        public Result(String resultString, double resultScore) {
            this.resultString = resultString;
            this.resultScore = (int) resultScore;
        }

        public String getResultString() {
            return this.resultString;
        }

        public int getResultScore() {
            return this.resultScore;
        }
    }

    static class AudioLevelTracker {
        private static final double MAX = 80.0d;
        private static final double MIN = 40.0d;
        private static final double NORMALIZE_FACTOR = 4.004004004004004d;
        private static final double SCALE = 9.99d;
        double audioLevel;
        int audioLevelCount;
        double averageLevel;
        boolean isTrackingAudioSample;

        public AudioLevelTracker() {
            reset();
        }

        public void reset() {
            this.averageLevel = 0.0d;
            this.audioLevelCount = 0;
            this.isTrackingAudioSample = false;
        }

        public void startTrackingAudioSample() {
            reset();
            this.isTrackingAudioSample = true;
        }

        public boolean isTrackingAudioSample() {
            return this.isTrackingAudioSample;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static double normalize(double level) {
            double normalized = Math.floor(level - MIN) / NORMALIZE_FACTOR;
            return Math.min(SCALE, Math.max(normalized, 0.0d));
        }

        public boolean update(double level) {
            double oldAverage = this.averageLevel;
            double oldLevel = this.audioLevel;
            this.audioLevel = level;
            this.audioLevelCount++;
            this.averageLevel = ((((double) (this.audioLevelCount - 1)) * oldAverage) + level) / ((double) this.audioLevelCount);
            return (this.isTrackingAudioSample || this.audioLevel == oldLevel) ? false : true;
        }
    }

    public NVASpeechKit(MMWebView webView) {
        if (webView != null) {
            this.webViewRef = new WeakReference<>(webView);
            initInternalData(webView.getContext().getApplicationContext());
        }
        this.state = State.READY;
    }

    private MMWebView getMMWebView() {
        if (this.webViewRef != null) {
            return this.webViewRef.get();
        }
        return null;
    }

    private void releaseWebView() {
        if (getMMWebView() != null) {
            this.webViewRef.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray resultsToJSON(Result[] resultArray) {
        JSONArray list = new JSONArray();
        for (int i = 0; i < resultArray.length; i++) {
            JSONObject object = new JSONObject();
            try {
                object.put("score", "" + resultArray[i].getResultScore());
                object.put("result", resultArray[i].getResultString());
                list.put(object);
            } catch (JSONException e) {
                MMLog.e(TAG, "JSON creation error.", e);
                return null;
            }
        }
        return list;
    }

    /* JADX INFO: renamed from: com.millennialmedia.android.NVASpeechKit$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$millennialmedia$android$NVASpeechKit$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$com$millennialmedia$android$NVASpeechKit$State[State.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$millennialmedia$android$NVASpeechKit$State[State.PROCESSING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$millennialmedia$android$NVASpeechKit$State[State.READY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$millennialmedia$android$NVASpeechKit$State[State.RECORDING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$millennialmedia$android$NVASpeechKit$State[State.VOCALIZING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    void voiceStateChangeReady() {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceStateChange('ready')");
        }
    }

    void voiceStateChangeRecording() {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceStateChange('recording')");
        }
    }

    void voiceStateChangeProcessing() {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceStateChange('processing')");
        }
    }

    void voiceStateChangeVocalizing() {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceStateChange('vocalizing')");
        }
    }

    void voiceStateChangeError() {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceStateChange('error')");
        }
    }

    void audioLevelChange(double audioLevel) {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.audioLevelChange(" + audioLevel + ")");
        }
    }

    void recognitionResult(String jsonResults) {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.recognitionResult(" + jsonResults + ")");
        }
    }

    void voiceError(String error) {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.voiceError('" + error + "')");
        }
    }

    void backgroundAudioLevel(double audioLevel) {
        MMWebView webView = getMMWebView();
        if (webView != null) {
            webView.loadUrl("javascript:MMJS.sdk.backgroundAudioLevel(" + audioLevel + ")");
        }
    }

    public synchronized State getState() {
        return this.state;
    }

    public void setSpeechKitListener(Listener speechKitListener) {
        this.speechKitListener = speechKitListener;
    }

    public boolean startRecording(String languageCode) {
        MMLog.d(TAG, "RECORDING INVOKED.");
        if (this.state != State.READY || this.sk == null) {
            return false;
        }
        this.nuance_transaction_session_id = null;
        this.skCurrentRecognizer = this.sk.createRecognizer(RecognizerConstants.RecognizerType.Dictation, 1, languageCode, this.skRecogListener, this.speeckKitHandler);
        MMLog.d(TAG, "START RECORDING");
        this.skCurrentRecognizer.start();
        return true;
    }

    public boolean endRecording() {
        if (this.skCurrentRecognizer == null) {
            return false;
        }
        MMLog.d(TAG, "end RECORDING");
        this.skCurrentRecognizer.stopRecording();
        this.skCurrentRecognizer = null;
        return true;
    }

    public void cancelRecording() {
        if (this.skCurrentRecognizer != null) {
            MMLog.d(TAG, "cancel RECORDING");
            this.skCurrentRecognizer.cancel();
            this.skCurrentRecognizer = null;
            setState(State.READY);
        }
    }

    public void startSampleRecording() {
        this.audioLevelTracker.startTrackingAudioSample();
        startRecording("en_US");
    }

    public Result[] getResults() {
        return this._results;
    }

    public void stopActions() {
        if (this.sk != null) {
            try {
                this.sk.cancelCurrent();
            } catch (Exception e) {
                MMLog.e(TAG, "No speech kit to disconnect.", e);
            }
        }
    }

    public boolean textToSpeech(String input, String languageCode) {
        MMLog.d(TAG, "TTS INVOKED.");
        if (this.state != State.READY || this.sk == null) {
            return false;
        }
        this.skCurrentVocalizer = this.sk.createVocalizerWithLanguage(languageCode, this.skVocalListener, this.speeckKitHandler);
        this.skCurrentVocalizer.speakString(input, this);
        return true;
    }

    public void release() {
        MMLog.d(TAG, "release called.");
        stopActions();
        cancelAudioLevelCallbacks();
        if (this.sk != null) {
            this.sk.release();
            setState(State.READY);
            this.sk = null;
        }
        this.pendingDataUploadCommand = null;
        releaseWebView();
    }

    public void logEvent() {
        if (this.sk != null) {
            PdxValue.Dictionary content = new PdxValue.Dictionary();
            content.put("nva_ad_network_id", "MillenialMedia");
            content.put("nva_device_id", getNuanceId());
            content.put("nva_ad_publisher_id", this.packageName);
            String mmSessionId = "";
            if (this._credentials != null && !TextUtils.isEmpty(this._credentials.sessionID)) {
                mmSessionId = this._credentials.sessionID;
                content.put("nva_ad_session_id", this._credentials.sessionID);
            }
            String adId = getAdId();
            if (!TextUtils.isEmpty(adId)) {
                content.put("nva_ad_id", adId);
            }
            if (this.nuance_transaction_session_id != null) {
                content.put("nva_nvc_session_id", this.nuance_transaction_session_id);
                String str = this.nuance_transaction_session_id;
                this.nuance_transaction_session_id = null;
            } else {
                this.sk.getSessionId();
            }
            MMLog.d(TAG, "Sending log revision command to server. sessionId[" + this.sk.getSessionId() + "] deviceId[" + getNuanceId() + "] adId[" + adId + "] mmSessionId[" + mmSessionId + "]");
            GenericCommand command = this.sk.createLogRevisionCmd("NVA_LOG_EVENT", content, this.sk.getSessionId(), this.commandListener, this.speeckKitHandler);
            command.start();
        }
    }

    private String getAdId() {
        MMWebView webView;
        return (this.webViewRef == null || (webView = this.webViewRef.get()) == null) ? "DEFAULT_AD_ID" : webView.getAdId();
    }

    private String byte2Str(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        StringBuffer hexString = new StringBuffer();
        for (byte b : byteArray) {
            int v = b & Constants.UNKNOWN;
            String hex = Integer.toHexString(v);
            if (v < 16) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private byte[] string2Byte(String string) {
        if (string == null) {
            return null;
        }
        byte[] bytes = new byte[string.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(string.substring(i * 2, (i * 2) + 2), 16);
        }
        return bytes;
    }

    public boolean initialize(HandShake.NuanceCredentials credentials, Context context) {
        MMLog.d(TAG, "initialize called.");
        if (credentials == null || context == null) {
            return false;
        }
        this._credentials = credentials;
        if (this.sk != null) {
            try {
                this.sk.connect();
            } catch (IllegalStateException e) {
                this.sk = null;
            }
        }
        if (this.sk == null) {
            byte[] appKeyBytes = string2Byte(credentials.appKey);
            MMLog.d(TAG, credentials.toString());
            this.sk = SpeechKit.initialize(context, BuildConfig.VERSION_NAME, credentials.appID, credentials.server, credentials.port, false, appKeyBytes, SpeechKit.CmdSetType.NVC);
            this.skVocalListener = createVocalizerListener();
            this.skRecogListener = createRecognizerListener();
            this.speeckKitHandler = new Handler(Looper.getMainLooper());
            this.sk.connect();
            setState(State.READY);
            return true;
        }
        MMLog.d(TAG, "Already initialized. Skipping.");
        return false;
    }

    String getSessionId() {
        return this.sk != null ? this.sk.getSessionId() : "";
    }

    public void updateCustomWords(CustomWordsOp op, String[] words) {
        if (this.sk != null) {
            DataBlock datablock = new DataBlock();
            MMLog.d(TAG, "Creating dataupload command and " + (op == CustomWordsOp.Add ? "adding" : "deleting") + " words.");
            Data data = new Data("nva_custom_word_uploads", Data.DataType.CUSTOMWORDS);
            Action addAction = new Action(op == CustomWordsOp.Add ? Action.ActionType.ADD : Action.ActionType.REMOVE);
            for (String word : words) {
                addAction.addWord(word);
                MMLog.d(TAG, "\tword: '" + word + "'");
            }
            data.addAction(addAction);
            datablock.addData(data);
            int checksum = datablock.getChecksum();
            this.pendingDataUploadCommandType = op;
            this.pendingDataUploadCommand = this.sk.createDataUploadCmd(datablock, checksum, checksum, this.dataUploadListener, this.speeckKitHandler);
            this.pendingDataUploadCommand.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResults(List<DetailedResult> inputResults) {
        MMLog.d(TAG, "processResults called.");
        this._results = new Result[inputResults.size()];
        int i = 0;
        for (DetailedResult r : inputResults) {
            this._results[i] = new Result(r.toString(), r.getConfidenceScore());
            i++;
        }
    }

    private Vocalizer.Listener createVocalizerListener() {
        return new Vocalizer.Listener() { // from class: com.millennialmedia.android.NVASpeechKit.6
            @Override // com.nuance.nmdp.speechkit.Vocalizer.Listener
            public void onSpeakingBegin(Vocalizer vocalizer, String text, Object context) {
                MMLog.d(NVASpeechKit.TAG, "Vocalization begins. text=" + text);
                NVASpeechKit.this.setState(State.VOCALIZING);
            }

            @Override // com.nuance.nmdp.speechkit.Vocalizer.Listener
            public void onSpeakingDone(Vocalizer vocalizer, String text, SpeechError error, Object context) {
                MMLog.d(NVASpeechKit.TAG, "Vocalization has ended.");
                if (error == null) {
                    NVASpeechKit.this.setState(State.READY);
                } else {
                    MMLog.e(NVASpeechKit.TAG, "Vocalizer error: " + error.getErrorDetail());
                    NVASpeechKit.this.handleSpeechError(error);
                }
            }
        };
    }

    private String getSpeechError(SpeechError error) {
        return error == null ? "No Error given" : "Speech Kit Error code:" + error.getErrorCode() + " detail:" + error.getErrorDetail() + " suggestions:" + error.getSuggestion();
    }

    private Recognizer.Listener createRecognizerListener() {
        return new Recognizer.Listener() { // from class: com.millennialmedia.android.NVASpeechKit.7
            @Override // com.nuance.nmdp.speechkit.Recognizer.Listener
            public void onRecordingBegin(Recognizer recognizer) {
                MMLog.d(NVASpeechKit.TAG, "recording begins");
                NVASpeechKit.this._results = null;
                if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample()) {
                    NVASpeechKit.this.setState(State.RECORDING);
                }
                NVASpeechKit.this.startProgress(recognizer);
                if (NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample()) {
                    NVASpeechKit.this.speeckKitHandler.removeCallbacks(NVASpeechKit.this.audioSampleCallback);
                    NVASpeechKit.this.speeckKitHandler.postDelayed(NVASpeechKit.this.audioSampleCallback, 2000L);
                }
            }

            @Override // com.nuance.nmdp.speechkit.Recognizer.Listener
            public void onRecordingDone(Recognizer recognizer) {
                MMLog.d(NVASpeechKit.TAG, "recording has ended");
                NVASpeechKit.this.cancelAudioLevelCallbacks();
                if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample()) {
                    NVASpeechKit.this.setState(State.PROCESSING);
                }
                if (NVASpeechKit.this.sk != null) {
                    NVASpeechKit.this.nuance_transaction_session_id = NVASpeechKit.this.sk.getSessionId();
                }
            }

            @Override // com.nuance.nmdp.speechkit.Recognizer.Listener
            public void onError(Recognizer recognizer, SpeechError error) {
                MMLog.d(NVASpeechKit.TAG, "Speech Kit Error code:" + error.getErrorCode() + " detail:" + error.getErrorDetail() + " suggestions:" + error.getSuggestion());
                NVASpeechKit.this.cancelAudioLevelCallbacks();
                NVASpeechKit.this.handleSpeechError(error);
                NVASpeechKit.this.skCurrentRecognizer = null;
                if (NVASpeechKit.this.sk != null) {
                    MMLog.d(NVASpeechKit.TAG, "Recognizer.Listener.onError: session id [" + NVASpeechKit.this.sk.getSessionId() + "]");
                }
            }

            @Override // com.nuance.nmdp.speechkit.Recognizer.Listener
            public void onResults(Recognizer recognizer, Recognition results) {
                MMLog.d(NVASpeechKit.TAG, "recording results returned.");
                NVASpeechKit.this.cancelAudioLevelCallbacks();
                if (!NVASpeechKit.this.audioLevelTracker.isTrackingAudioSample) {
                    NVASpeechKit.this.processResults(results.getDetailedResults());
                    if (NVASpeechKit.this.nuance_transaction_session_id != null) {
                        MMLog.d(NVASpeechKit.TAG, "Recognizer.Listener.onResults: session id [" + NVASpeechKit.this.nuance_transaction_session_id + "]");
                    }
                    NVASpeechKit.this.logEvent();
                    return;
                }
                NVASpeechKit.this._results = new Result[0];
                NVASpeechKit.this.notifySpeechResults();
            }
        };
    }

    synchronized String getNuanceId() {
        String mmdid;
        MMWebView webView;
        String nuanceId = null;
        synchronized (this) {
            if (nuanceIdCache != null) {
                nuanceId = nuanceIdCache;
            } else {
                Context context = null;
                if (this.webViewRef != null && (webView = this.webViewRef.get()) != null) {
                    context = webView.getContext();
                }
                if (context != null && (mmdid = Settings.Secure.getString(context.getContentResolver(), "android_id")) != null) {
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA1");
                        byte[] hashBytes = md.digest(mmdid.getBytes());
                        nuanceId = MMSDK.byteArrayToString(hashBytes);
                        nuanceIdCache = nuanceId;
                    } catch (Exception e) {
                        MMLog.e(TAG, "Problem with nuanceid", e);
                    }
                }
            }
        }
        return nuanceId;
    }

    private void initInternalData(Context context) {
        if (this.packageName == null) {
            this.packageName = context.getApplicationContext().getPackageName();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSpeechError(SpeechError error) {
        switch (error.getErrorCode()) {
            case 2:
                if (!this.audioLevelTracker.isTrackingAudioSample) {
                    setState(State.PROCESSING);
                }
                this._results = new Result[0];
                notifySpeechResults();
                break;
            case 3:
            case 4:
            default:
                if (this.speechKitListener != null) {
                    this.speechKitListener.onError();
                    setState(State.ERROR);
                    voiceError(getSpeechError(error));
                }
                break;
            case 5:
                setState(State.READY);
                this.skCurrentRecognizer = null;
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifySpeechResults() {
        if (this.speechKitListener != null && this._results != null) {
            if (this.audioLevelTracker.isTrackingAudioSample) {
                this.speechKitListener.onAudioSampleUpdate(this.audioLevelTracker.averageLevel);
                this.audioLevelTracker.reset();
            } else {
                this.speechKitListener.onResults();
            }
        }
        setState(State.READY);
        this.skCurrentRecognizer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startProgress(Recognizer recognizer) {
        this.speeckKitHandler.removeCallbacks(this.audioLevelCallback);
        this.speeckKitHandler.postDelayed(this.audioLevelCallback, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setState(State state) {
        MMLog.d(TAG, "recording results returned. state=" + state);
        State old_state = this.state;
        this.state = state;
        if (this.speechKitListener != null && this.state != old_state) {
            this.speechKitListener.onStateChange(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAudioLevelCallbacks() {
        if (this.speeckKitHandler != null) {
            this.speeckKitHandler.removeCallbacks(this.audioSampleCallback);
            this.speeckKitHandler.removeCallbacks(this.audioLevelCallback);
        }
    }
}
