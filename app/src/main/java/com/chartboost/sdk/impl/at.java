package com.chartboost.sdk.impl;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;

/* JADX INFO: loaded from: classes.dex */
public class at extends as {
    private ax a;
    private Button b;
    private bh c;
    private com.chartboost.sdk.Libraries.e.a d;

    public at(ax axVar, Context context) {
        super(axVar, context);
        this.a = axVar;
        this.b = new Button(context);
        this.b.setTextColor(-14571545);
        this.b.setText("Preview");
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.at.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                at.this.c();
            }
        });
        addView(this.b, 2);
    }

    @Override // com.chartboost.sdk.impl.as, com.chartboost.sdk.impl.aq
    public void a(com.chartboost.sdk.Libraries.e.a aVar, int i) {
        super.a(aVar, i);
        this.d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        CBLogging.c(this, "play the video");
        if (this.c == null) {
            this.c = new bh(getContext());
            this.a.e().addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
        this.c.b().a(new MediaPlayer.OnCompletionListener() { // from class: com.chartboost.sdk.impl.at.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer arg0) {
                bi.a(false, at.this.c);
            }
        });
        bi.a(true, this.c);
        this.c.b().a();
    }
}
