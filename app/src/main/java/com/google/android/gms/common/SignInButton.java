package com.google.android.gms.common;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.g;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.jz;

/* JADX INFO: loaded from: classes.dex */
public final class SignInButton extends FrameLayout implements View.OnClickListener {
    public static final int COLOR_DARK = 0;
    public static final int COLOR_LIGHT = 1;
    public static final int SIZE_ICON_ONLY = 2;
    public static final int SIZE_STANDARD = 0;
    public static final int SIZE_WIDE = 1;
    private View Jk;
    private View.OnClickListener Jl;
    private int mColor;
    private int mSize;

    public SignInButton(Context context) {
        this(context, null);
    }

    public SignInButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignInButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.Jl = null;
        setStyle(0, 0);
    }

    private void G(Context context) {
        if (this.Jk != null) {
            removeView(this.Jk);
        }
        try {
            this.Jk = jy.b(context, this.mSize, this.mColor);
        } catch (g.a e) {
            Log.w("SignInButton", "Sign in button not found, using placeholder instead");
            this.Jk = a(context, this.mSize, this.mColor);
        }
        addView(this.Jk);
        this.Jk.setEnabled(isEnabled());
        this.Jk.setOnClickListener(this);
    }

    private static Button a(Context context, int i, int i2) {
        jz jzVar = new jz(context);
        jzVar.a(context.getResources(), i, i2);
        return jzVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.Jl == null || view != this.Jk) {
            return;
        }
        this.Jl.onClick(this);
    }

    public void setColorScheme(int colorScheme) {
        setStyle(this.mSize, colorScheme);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.Jk.setEnabled(enabled);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener listener) {
        this.Jl = listener;
        if (this.Jk != null) {
            this.Jk.setOnClickListener(this);
        }
    }

    public void setSize(int buttonSize) {
        setStyle(buttonSize, this.mColor);
    }

    public void setStyle(int buttonSize, int colorScheme) {
        jx.a(buttonSize >= 0 && buttonSize < 3, "Unknown button size %d", Integer.valueOf(buttonSize));
        jx.a(colorScheme >= 0 && colorScheme < 2, "Unknown color scheme %s", Integer.valueOf(colorScheme));
        this.mSize = buttonSize;
        this.mColor = colorScheme;
        G(getContext());
    }
}
