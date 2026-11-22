package com.nuance.nmdp.speechkit;

/* JADX INFO: loaded from: classes.dex */
public interface Recognizer extends j {

    public static final class EndOfSpeechDetection extends RecognizerConstants.EndOfSpeechDetection {
    }

    public interface Listener {
        void onError(Recognizer recognizer, SpeechError speechError);

        void onRecordingBegin(Recognizer recognizer);

        void onRecordingDone(Recognizer recognizer);

        void onResults(Recognizer recognizer, Recognition recognition);
    }

    public static final class PromptType extends RecognizerConstants.PromptType {
    }

    public static final class RecognizerType extends RecognizerConstants.RecognizerType {
    }

    void cancel();

    float getAudioLevel();

    void setListener(Listener listener);

    @Override // com.nuance.nmdp.speechkit.j
    void setPrompt(int i, Prompt prompt);

    void start();

    void stopRecording();
}
