package com.nuance.nmdp.speechkit;

/* JADX INFO: loaded from: classes.dex */
public interface NluRecognizer extends j {

    public static final class EndOfSpeechDetection extends RecognizerConstants.EndOfSpeechDetection {
    }

    public interface Listener {
        void onError(NluRecognizer nluRecognizer, SpeechError speechError);

        void onRecordingBegin(NluRecognizer nluRecognizer);

        void onRecordingDone(NluRecognizer nluRecognizer);

        void onResults(NluRecognizer nluRecognizer, GenericRecognition genericRecognition);
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
