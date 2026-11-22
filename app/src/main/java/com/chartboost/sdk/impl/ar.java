package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chartboost.sdk.Libraries.CBUtility;

/* JADX INFO: loaded from: classes.dex */
public class ar extends aq {
    private ImageView a;

    public ar(ax axVar, Context context) {
        super(context);
        this.a = new ImageView(context);
        addView(this.a, new LinearLayout.LayoutParams(-1, -1));
    }

    @Override // com.chartboost.sdk.impl.aq
    public void a(com.chartboost.sdk.Libraries.e.a aVar, int i) {
        com.chartboost.sdk.Libraries.e.a aVarA = aVar.a("assets").a(CBUtility.c().b() ? "portrait" : "landscape");
        if (aVarA.c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String strE = "";
            if (aVarA.e("checksum") != null && !aVarA.e("checksum").isEmpty()) {
                strE = aVarA.e("checksum");
            }
            bd.a().a(aVarA.e("url"), strE, null, this.a, bundle);
        }
    }

    @Override // com.chartboost.sdk.impl.aq
    public int a() {
        return CBUtility.a(110, getContext());
    }
}
