package com.nuance.nmdp.speechkit;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Handler;
import com.nuance.nmdp.speechkit.util.dataupload.DataBlock;
import com.nuance.nmdp.speechkit.util.grammar.Grammar;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import defpackage.ax;
import java.io.IOException;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class SpeechKit extends w {
    private final x a;
    private final Context b;

    public enum CmdSetType {
        NVC(0),
        DRAGON_NLU(1);

        private int a;

        CmdSetType(int i) {
            this.a = i;
        }

        public final int getIndex() {
            return this.a;
        }
    }

    private SpeechKit(x xVar, Context context) {
        this.a = xVar;
        this.b = context;
    }

    private Prompt a(final AssetFileDescriptor assetFileDescriptor) {
        if (assetFileDescriptor == null) {
            return null;
        }
        final defpackage.d dVar = new defpackage.d();
        Prompt prompt = new Prompt(dVar, this.a);
        synchronized (x.b()) {
            if (!this.a.a()) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e) {
                }
                x.f();
            }
            this.a.b(prompt);
        }
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.SpeechKit.1
            @Override // java.lang.Runnable
            public final void run() {
                dVar.a(assetFileDescriptor);
            }
        });
        return prompt;
    }

    public static SpeechKit initialize(Context context, String str, String str2, int i, boolean z, byte[] bArr) {
        return initialize(context, "", str, str2, i, null, z, bArr, CmdSetType.NVC);
    }

    public static SpeechKit initialize(Context context, String str, String str2, String str3, int i, String str4, boolean z, byte[] bArr, CmdSetType cmdSetType) {
        SpeechKit speechKit;
        x xVarA = x.a(context, str, str2, str3, i, str4, z, bArr, cmdSetType);
        if (xVarA == null) {
            return null;
        }
        synchronized (x.b()) {
            speechKit = (SpeechKit) xVarA.d();
            if (speechKit == null) {
                speechKit = new SpeechKit(xVarA, context);
                xVarA.a(speechKit);
            }
        }
        return speechKit;
    }

    public static SpeechKit initialize(Context context, String str, String str2, String str3, int i, boolean z, byte[] bArr, CmdSetType cmdSetType) {
        return initialize(context, str, str2, str3, i, null, z, bArr, cmdSetType);
    }

    public final void cancelCurrent() {
        this.a.j();
    }

    public final void connect() {
        this.a.h();
    }

    public final Recognizer createConstraintRecognizer(String str, int i, String str2, Vector<Grammar> vector, Recognizer.Listener listener, Object obj) {
        return this.a.a(str, i, str2, vector, listener, obj);
    }

    public final DataUploadCommand createDataUploadCmd(DataBlock dataBlock, int i, int i2, DataUploadCommand.Listener listener, Handler handler) {
        return this.a.a(dataBlock, i, i2, listener, handler);
    }

    public final GenericCommand createLogRevisionCmd(String str, PdxValue.Dictionary dictionary, String str2, GenericCommand.Listener listener, Handler handler) {
        return this.a.a(str, dictionary, str2, listener, handler);
    }

    public final NluRecognizer createNluRecognizer(String str, int i, String str2, String str3, PdxValue.Dictionary dictionary, NluRecognizer.Listener listener, Handler handler) {
        return this.a.a(str, i, str2, str3, dictionary, listener, handler);
    }

    public final Recognizer createRecognizer(String str, int i, String str2, Recognizer.Listener listener, Handler handler) {
        return this.a.a(str, i, str2, listener, handler);
    }

    public final GenericCommand createResetUserProfileCmd(GenericCommand.Listener listener, Handler handler) {
        return this.a.a(listener, handler);
    }

    public final TextRecognizer createTextRecognizer(String str, String str2, PdxValue.Dictionary dictionary, TextRecognizer.Listener listener, Handler handler) {
        return this.a.a(str, str2, dictionary, listener, handler);
    }

    public final Vocalizer createVocalizerWithLanguage(String str, Vocalizer.Listener listener, Handler handler) {
        return this.a.a(str, listener, handler);
    }

    public final Vocalizer createVocalizerWithVoice(String str, Vocalizer.Listener listener, Handler handler) {
        return this.a.b(str, listener, handler);
    }

    public final Prompt defineAudioPrompt(int i) {
        AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = this.b.getResources().openRawResourceFd(i);
        if (assetFileDescriptorOpenRawResourceFd == null) {
            throw new IllegalArgumentException("resourceId must refer to an uncompressed resource");
        }
        return a(assetFileDescriptorOpenRawResourceFd);
    }

    public final CmdSetType getCmdSetType() {
        return this.a.n();
    }

    public final String getSessionId() {
        return this.a.i();
    }

    public final void logToServer(String str, String str2) {
        this.a.b(str, str2);
    }

    public final void release() {
        this.a.g();
    }

    public final void setCmdSetType(CmdSetType cmdSetType) {
        this.a.a(cmdSetType);
    }

    public final void setDefaultRecognizerPrompts(Prompt prompt, Prompt prompt2, Prompt prompt3, Prompt prompt4) {
        this.a.a(prompt, prompt2, prompt3, prompt4);
    }
}
