package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.internal.jx;
import com.google.android.gms.internal.ka;
import com.google.android.gms.plus.internal.g;

/* JADX INFO: loaded from: classes.dex */
public final class PlusOneButton extends FrameLayout {
    public static final int ANNOTATION_BUBBLE = 1;
    public static final int ANNOTATION_INLINE = 2;
    public static final int ANNOTATION_NONE = 0;
    public static final int DEFAULT_ACTIVITY_REQUEST_CODE = -1;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_STANDARD = 3;
    public static final int SIZE_TALL = 2;
    private int anA;
    private OnPlusOneClickListener anB;
    private View any;
    private int anz;
    private int mSize;
    private String vf;

    protected class DefaultOnPlusOneClickListener implements View.OnClickListener, OnPlusOneClickListener {
        private final OnPlusOneClickListener anC;

        public DefaultOnPlusOneClickListener(OnPlusOneClickListener proxy) {
            this.anC = proxy;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = (Intent) PlusOneButton.this.any.getTag();
            if (this.anC != null) {
                this.anC.onPlusOneClick(intent);
            } else {
                onPlusOneClick(intent);
            }
        }

        @Override // com.google.android.gms.plus.PlusOneButton.OnPlusOneClickListener
        public void onPlusOneClick(Intent intent) {
            Context context = PlusOneButton.this.getContext();
            if (!(context instanceof Activity) || intent == null) {
                return;
            }
            ((Activity) context).startActivityForResult(intent, PlusOneButton.this.anA);
        }
    }

    public interface OnPlusOneClickListener {
        void onPlusOneClick(Intent intent);
    }

    public PlusOneButton(Context context) {
        this(context, null);
    }

    public PlusOneButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSize = getSize(context, attrs);
        this.anz = getAnnotation(context, attrs);
        this.anA = -1;
        G(getContext());
        if (isInEditMode()) {
        }
    }

    private void G(Context context) {
        if (this.any != null) {
            removeView(this.any);
        }
        this.any = g.a(context, this.mSize, this.anz, this.vf, this.anA);
        setOnPlusOneClickListener(this.anB);
        addView(this.any);
    }

    protected static int getAnnotation(Context context, AttributeSet attrs) {
        String strA = ka.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "annotation", context, attrs, true, false, "PlusOneButton");
        if ("INLINE".equalsIgnoreCase(strA)) {
            return 2;
        }
        return !"NONE".equalsIgnoreCase(strA) ? 1 : 0;
    }

    protected static int getSize(Context context, AttributeSet attrs) {
        String strA = ka.a("http://schemas.android.com/apk/lib/com.google.android.gms.plus", "size", context, attrs, true, false, "PlusOneButton");
        if ("SMALL".equalsIgnoreCase(strA)) {
            return 0;
        }
        if ("MEDIUM".equalsIgnoreCase(strA)) {
            return 1;
        }
        return "TALL".equalsIgnoreCase(strA) ? 2 : 3;
    }

    public void initialize(String url, int activityRequestCode) {
        jx.a(getContext() instanceof Activity, "To use this method, the PlusOneButton must be placed in an Activity. Use initialize(String, OnPlusOneClickListener).");
        this.vf = url;
        this.anA = activityRequestCode;
        G(getContext());
    }

    public void initialize(String url, OnPlusOneClickListener plusOneClickListener) {
        this.vf = url;
        this.anA = 0;
        G(getContext());
        setOnPlusOneClickListener(plusOneClickListener);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        this.any.layout(0, 0, right - left, bottom - top);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View view = this.any;
        measureChild(view, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setAnnotation(int annotation) {
        this.anz = annotation;
        G(getContext());
    }

    public void setOnPlusOneClickListener(OnPlusOneClickListener listener) {
        this.anB = listener;
        this.any.setOnClickListener(new DefaultOnPlusOneClickListener(listener));
    }

    public void setSize(int size) {
        this.mSize = size;
        G(getContext());
    }
}
