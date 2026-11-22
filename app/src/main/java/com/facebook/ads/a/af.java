package com.facebook.ads.a;

import android.content.Context;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: classes.dex */
public class af extends RelativeLayout {
    private int a;
    private int b;

    public af(Context context) {
        super(context);
        this.a = 0;
        this.b = 0;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b > 0 && getMeasuredWidth() > this.b) {
            setMeasuredDimension(this.b, getMeasuredHeight());
        } else if (getMeasuredWidth() < this.a) {
            setMeasuredDimension(this.a, getMeasuredHeight());
        }
    }

    protected void setMaxWidth(int i) {
        this.b = i;
    }

    public void setMinWidth(int i) {
        this.a = i;
    }
}
