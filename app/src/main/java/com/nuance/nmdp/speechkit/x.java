package com.nuance.nmdp.speechkit;

import com.nuance.nmdp.speechkit.util.dataupload.Data;
import com.nuance.nmdp.speechkit.util.dataupload.DataBlock;
import com.nuance.nmdp.speechkit.util.grammar.Grammar;
import com.nuance.nmdp.speechkit.util.pdx.PdxValue;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import defpackage.ax;
import defpackage.ay;
import java.util.Iterator;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
final class x {
    private static x a = null;
    private static Object b = new Object();
    private static Object o = new Object();
    private final String d;
    private final String e;
    private final int f;
    private final defpackage.k n;
    private DataBlock q;
    private b p = null;
    private boolean r = false;
    private int s = 0;
    private int t = 0;
    private boolean g = true;
    private defpackage.m c = null;
    private final ay i = new ay();
    private Object h = null;
    private Prompt j = null;
    private Prompt k = null;
    private Prompt l = null;
    private Prompt m = null;

    private x(Object obj, String str, String str2, String str3, int i, String str4, boolean z, byte[] bArr, SpeechKit.CmdSetType cmdSetType) {
        this.d = str2;
        this.e = str3;
        this.f = i;
        this.n = new defpackage.k(obj, str, this.e, this.f, str4, z, this.d, bArr, cmdSetType);
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.1
            @Override // java.lang.Runnable
            public final void run() {
                x.this.c = new defpackage.m(x.this.n);
            }
        });
    }

    public static x a(Object obj, String str, String str2, String str3, int i, String str4, boolean z, byte[] bArr, SpeechKit.CmdSetType cmdSetType) {
        a(obj, "appContext");
        a((Object) str2, "id");
        a(str3, "host");
        a((Object) str, "appVersion");
        a(bArr, "applicationKey");
        if (i < 0 || i > 65535) {
            a((RuntimeException) new IllegalArgumentException("port must be between 0 and 65535"));
        }
        a(obj, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        synchronized (b) {
            com.nuance.nmdp.speechkit.recognitionresult.b.a((Object) null, "Initializing SpeechKit");
            if (a == null) {
                ax.a();
            }
            if (a != null) {
                x xVar = a;
                if (!(xVar.d.equals(str2) && xVar.e.equals(str3) && xVar.f == i)) {
                    com.nuance.nmdp.speechkit.recognitionresult.b.a((Object) null, "Releasing old SpeechKit before creating new instance");
                    a.p();
                    a = null;
                }
            }
            if (a == null) {
                com.nuance.nmdp.speechkit.recognitionresult.b.a((Object) null, "Creating fresh SpeechKit instance");
                a = new x(obj, str, str2, str3, i, str4, z, bArr, cmdSetType);
            }
        }
        return a;
    }

    private void a(j jVar) {
        if (this.j != null) {
            jVar.setPrompt(0, this.j);
        }
        if (this.k != null) {
            jVar.setPrompt(1, this.k);
        }
        if (this.l != null) {
            jVar.setPrompt(2, this.l);
        }
        if (this.m != null) {
            jVar.setPrompt(3, this.m);
        }
    }

    static final void a(Object obj, String str) {
        if (obj == null) {
            a((RuntimeException) new IllegalArgumentException(str + " must not be null"));
        }
    }

    private static void a(RuntimeException runtimeException) {
        com.nuance.nmdp.speechkit.recognitionresult.b.c(null, runtimeException.getMessage());
        throw runtimeException;
    }

    static final void a(String str, String str2) {
        if (str == null || str.length() == 0) {
            a((RuntimeException) new IllegalArgumentException(str2 + " must not be null or empty"));
        }
    }

    static Object b() {
        return b;
    }

    static final void f() {
        a((RuntimeException) new IllegalStateException("SpeechKit instance is released"));
    }

    static /* synthetic */ boolean h(x xVar) {
        xVar.r = true;
        return true;
    }

    private void p() {
        this.g = false;
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.2
            @Override // java.lang.Runnable
            public final void run() {
                x.this.c.a();
                int iC = x.this.i.c();
                for (int i = 0; i < iC; i++) {
                    ((Prompt) x.this.i.a()).a().c();
                }
            }
        });
    }

    public final DataUploadCommand a(DataBlock dataBlock, int i, int i2, DataUploadCommand.Listener listener, Object obj) {
        f fVar;
        a(dataBlock, "datablock");
        a(listener, "listener");
        synchronized (b) {
            try {
                if (!this.g) {
                    f();
                }
                fVar = new f(this, listener, obj);
                this.p = fVar;
                this.q = dataBlock;
                this.s = i;
                this.t = i2;
                ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.5
                    @Override // java.lang.Runnable
                    public final void run() {
                        synchronized (x.o) {
                            x.this.p.addParam("DATA_BLOCK", x.this.q.getDataBlockDictionary());
                            PdxValue.Dictionary dictionary = new PdxValue.Dictionary();
                            PdxValue.Sequence sequence = new PdxValue.Sequence();
                            dictionary.put("num_data_blocks", 1);
                            Vector<Data> dataList = x.this.q.getDataList();
                            for (int i3 = 0; i3 < dataList.size(); i3++) {
                                PdxValue.Dictionary dictionary2 = new PdxValue.Dictionary();
                                dictionary2.put("id", dataList.elementAt(i3).getId());
                                dictionary2.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, dataList.elementAt(i3).getTypeStr());
                                dictionary2.put("current_checksum", Integer.toString(x.this.s));
                                dictionary2.put("new_checksum", Integer.toString(x.this.t));
                                dictionary2.put("algorithm_id", "MD5");
                                sequence.add(dictionary2);
                            }
                            dictionary.put("checksums", sequence);
                            x.this.p.addParam("UPLOAD_DONE", dictionary);
                            x.h(x.this);
                            x.o.notify();
                        }
                    }
                });
                synchronized (o) {
                    while (!this.r) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    fVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return fVar;
    }

    public final GenericCommand a(GenericCommand.Listener listener, Object obj) {
        u uVar;
        a((Object) listener, "listener");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            uVar = new u(this, listener, obj);
        }
        return uVar;
    }

    public final GenericCommand a(String str, PdxValue.Dictionary dictionary, String str2, GenericCommand.Listener listener, Object obj) {
        l lVar;
        a((Object) str, "event");
        a(dictionary, "content");
        a((Object) listener, "listener");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            lVar = new l(this, str2, listener, obj);
            PdxValue.Dictionary dictionary2 = new PdxValue.Dictionary();
            dictionary2.put("event", str);
            dictionary2.put("content", dictionary);
            lVar.addParam("LOG_CONTENT", dictionary2);
        }
        return lVar;
    }

    public final NluRecognizer a(String str, int i, String str2, String str3, PdxValue.Dictionary dictionary, NluRecognizer.Listener listener, Object obj) {
        n nVar;
        a((Object) str, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        a((Object) str2, "language");
        a(listener, "listener");
        a(dictionary, "requestParams");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            nVar = new n(this, str, i, str2, str3, dictionary, listener, obj);
            a((j) nVar);
        }
        return nVar;
    }

    public final Recognizer a(String str, int i, String str2, Recognizer.Listener listener, Object obj) {
        r rVar;
        a((Object) str, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        a((Object) str2, "language");
        a(listener, "listener");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            rVar = new r(this, str, i, str2, listener, obj);
            a((j) rVar);
        }
        return rVar;
    }

    public final Recognizer a(String str, int i, String str2, Vector<Grammar> vector, Recognizer.Listener listener, Object obj) {
        d dVar;
        a((Object) str, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        a((Object) str2, "language");
        a(listener, "listener");
        a(vector, "grammarList");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            PdxValue.Sequence sequence = null;
            if (vector != null) {
                sequence = new PdxValue.Sequence();
                Iterator<Grammar> it = vector.iterator();
                while (it.hasNext()) {
                    sequence.add(it.next().getGrammarDictionary());
                }
            }
            dVar = new d(this, str, i, str2, sequence, listener, obj);
            a((j) dVar);
        }
        return dVar;
    }

    public final TextRecognizer a(String str, String str2, PdxValue.Dictionary dictionary, TextRecognizer.Listener listener, Object obj) {
        z zVar;
        a((Object) str, "language");
        a(listener, "listener");
        a(dictionary, "requestParams");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            zVar = new z(this, str, str2, dictionary, listener, obj);
        }
        return zVar;
    }

    public final Vocalizer a(String str, Vocalizer.Listener listener, Object obj) {
        ab abVar;
        a((Object) str, "language");
        a(listener, "listener");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            abVar = new ab(this, null, str, listener, obj);
        }
        return abVar;
    }

    final void a(Prompt prompt) {
        if (this.i.c(prompt)) {
            this.i.b(prompt);
        }
        prompt.a().c();
        if (prompt == this.j) {
            this.j = null;
        }
        if (prompt == this.k) {
            this.k = null;
        }
        if (prompt == this.l) {
            this.l = null;
        }
        if (prompt == this.m) {
            this.m = null;
        }
    }

    public final void a(final Prompt prompt, final Prompt prompt2, final Prompt prompt3, final Prompt prompt4) {
        synchronized (b) {
            if (!this.g) {
                f();
            }
        }
        ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.6
            @Override // java.lang.Runnable
            public final void run() {
                x.this.j = null;
                x.this.k = null;
                x.this.l = null;
                x.this.m = null;
                if (prompt != null) {
                    if (prompt.a().b()) {
                        com.nuance.nmdp.speechkit.recognitionresult.b.b(this, "Recording start prompt is invalid");
                    } else {
                        x.this.j = prompt;
                    }
                }
                if (prompt2 != null) {
                    if (prompt2.a().b()) {
                        com.nuance.nmdp.speechkit.recognitionresult.b.b(this, "Recording stop prompt is invalid");
                    } else {
                        x.this.k = prompt2;
                    }
                }
                if (prompt3 != null) {
                    if (prompt3.a().b()) {
                        com.nuance.nmdp.speechkit.recognitionresult.b.b(this, "Result prompt is invalid");
                    } else {
                        x.this.l = prompt3;
                    }
                }
                if (prompt4 != null) {
                    if (prompt4.a().b()) {
                        com.nuance.nmdp.speechkit.recognitionresult.b.b(this, "Error prompt is invalid");
                    } else {
                        x.this.m = prompt4;
                    }
                }
            }
        });
    }

    public final void a(SpeechKit.CmdSetType cmdSetType) {
        this.n.a(cmdSetType);
    }

    final void a(Object obj) {
        this.h = obj;
    }

    final boolean a() {
        return this.g;
    }

    public final Vocalizer b(String str, Vocalizer.Listener listener, Object obj) {
        ab abVar;
        a((Object) str, "voice");
        a(listener, "listener");
        synchronized (b) {
            if (!this.g) {
                f();
            }
            abVar = new ab(this, str, null, listener, obj);
        }
        return abVar;
    }

    public final void b(Prompt prompt) {
        synchronized (b) {
            this.i.a(prompt);
        }
    }

    public final void b(final String str, final String str2) {
        synchronized (b) {
            if (!this.g) {
                f();
            }
            ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.8
                @Override // java.lang.Runnable
                public final void run() {
                    x.this.c.a(str, str2);
                }
            });
        }
    }

    final defpackage.m c() {
        return this.c;
    }

    final Object d() {
        return this.h;
    }

    final void e() {
        synchronized (b) {
            if (!this.g) {
                f();
            }
        }
    }

    public final void g() {
        synchronized (b) {
            if (this == a) {
                com.nuance.nmdp.speechkit.recognitionresult.b.a(this, "Releasing SpeechKit instance");
                p();
                a = null;
                ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.3
                    @Override // java.lang.Runnable
                    public final void run() {
                        ax.b();
                    }
                });
            } else {
                com.nuance.nmdp.speechkit.recognitionresult.b.b(this, "SpeechKit instance already released");
            }
        }
    }

    public final void h() {
        synchronized (b) {
            if (!this.g) {
                f();
            }
            ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.4
                @Override // java.lang.Runnable
                public final void run() {
                    defpackage.g gVarA = x.this.c.a(new defpackage.h() { // from class: com.nuance.nmdp.speechkit.x.4.1
                        @Override // defpackage.h
                        public final void a(defpackage.g gVar) {
                        }

                        @Override // defpackage.h
                        public final void a(defpackage.g gVar, int i, String str, String str2) {
                        }
                    });
                    if (gVarA != null) {
                        gVarA.a();
                    }
                }
            });
        }
    }

    public final String i() {
        String strC;
        synchronized (b) {
            strC = this.c != null ? this.c.c() : null;
        }
        return strC;
    }

    public final void j() {
        synchronized (b) {
            if (!this.g) {
                f();
            }
            ax.a(new Runnable() { // from class: com.nuance.nmdp.speechkit.x.7
                @Override // java.lang.Runnable
                public final void run() {
                    x.this.c.d();
                }
            });
        }
    }

    public final String k() {
        return this.n.v();
    }

    public final String l() {
        return this.n.w();
    }

    public final String m() {
        return this.n.x();
    }

    public final SpeechKit.CmdSetType n() {
        return this.n.A();
    }
}
