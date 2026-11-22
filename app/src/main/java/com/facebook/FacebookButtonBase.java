package com.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.facebook.appevents.AppEventsLogger;

/* JADX INFO: loaded from: classes.dex */
public abstract class FacebookButtonBase extends Button {
    private String analyticsButtonCreatedEventName;
    private View.OnClickListener externalOnClickListener;
    private View.OnClickListener internalOnClickListener;
    private Fragment parentFragment;
    private int requestCode;

    protected FacebookButtonBase(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, String analyticsButtonCreatedEventName, int requestCode) {
        super(context, attrs, 0);
        defStyleRes = defStyleRes == 0 ? getDefaultStyleResource() : defStyleRes;
        configureButton(context, attrs, defStyleAttr, defStyleRes == 0 ? R.style.com_facebook_button : defStyleRes);
        this.analyticsButtonCreatedEventName = analyticsButtonCreatedEventName;
        this.requestCode = requestCode;
    }

    public void setFragment(Fragment fragment) {
        this.parentFragment = fragment;
    }

    public Fragment getFragment() {
        return this.parentFragment;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.externalOnClickListener = l;
    }

    protected void setRequestCode(int requestCode) {
        if (FacebookSdk.isFacebookRequestCode(requestCode)) {
            throw new IllegalArgumentException("Request code " + requestCode + " cannot be within the range reserved by the Facebook SDK.");
        }
        this.requestCode = requestCode;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        logButtonCreated(getContext());
    }

    protected Activity getActivity() {
        Context context = getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof Activity) {
                return (Activity) baseContext;
            }
        }
        throw new FacebookException("Unable to get Activity.");
    }

    protected int getDefaultStyleResource() {
        return 0;
    }

    protected void configureButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        parseBackgroundAttributes(context, attrs, defStyleAttr, defStyleRes);
        parseCompoundDrawableAttributes(context, attrs, defStyleAttr, defStyleRes);
        parseContentAttributes(context, attrs, defStyleAttr, defStyleRes);
        parseTextAttributes(context, attrs, defStyleAttr, defStyleRes);
        setupOnClickListener();
    }

    protected void callExternalOnClickListener(View v) {
        if (this.externalOnClickListener != null) {
            this.externalOnClickListener.onClick(v);
        }
    }

    protected void setInternalOnClickListener(View.OnClickListener l) {
        this.internalOnClickListener = l;
    }

    private void logButtonCreated(Context context) {
        AppEventsLogger logger = AppEventsLogger.newLogger(context);
        logger.logSdkEvent(this.analyticsButtonCreatedEventName, null, null);
    }

    private void parseBackgroundAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] attrsResources = {android.R.attr.background};
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, attrsResources, defStyleAttr, defStyleRes);
        try {
            if (a.hasValue(0)) {
                int backgroundResource = a.getResourceId(0, 0);
                if (backgroundResource != 0) {
                    setBackgroundResource(backgroundResource);
                } else {
                    setBackgroundColor(a.getColor(0, 0));
                }
            } else {
                setBackgroundColor(a.getColor(0, R.color.com_facebook_blue));
            }
        } finally {
            a.recycle();
        }
    }

    private void parseCompoundDrawableAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] attrsResources = {android.R.attr.drawableLeft, android.R.attr.drawableTop, android.R.attr.drawableRight, android.R.attr.drawableBottom, android.R.attr.drawablePadding};
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, attrsResources, defStyleAttr, defStyleRes);
        try {
            setCompoundDrawablesWithIntrinsicBounds(a.getResourceId(0, 0), a.getResourceId(1, 0), a.getResourceId(2, 0), a.getResourceId(3, 0));
            setCompoundDrawablePadding(a.getDimensionPixelSize(4, 0));
        } finally {
            a.recycle();
        }
    }

    private void parseContentAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] attrsResources = {android.R.attr.paddingLeft, android.R.attr.paddingTop, android.R.attr.paddingRight, android.R.attr.paddingBottom};
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, attrsResources, defStyleAttr, defStyleRes);
        try {
            setPadding(a.getDimensionPixelSize(0, 0), a.getDimensionPixelSize(1, 0), a.getDimensionPixelSize(2, 0), a.getDimensionPixelSize(3, 0));
        } finally {
            a.recycle();
        }
    }

    private void parseTextAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int[] colorResources = {android.R.attr.textColor};
        TypedArray colorAttrs = context.getTheme().obtainStyledAttributes(attrs, colorResources, defStyleAttr, defStyleRes);
        try {
            setTextColor(colorAttrs.getColor(0, -1));
            colorAttrs.recycle();
            int[] gravityResources = {android.R.attr.gravity};
            TypedArray gravityAttrs = context.getTheme().obtainStyledAttributes(attrs, gravityResources, defStyleAttr, defStyleRes);
            try {
                setGravity(gravityAttrs.getInt(0, 17));
                gravityAttrs.recycle();
                int[] attrsResources = {android.R.attr.textSize, android.R.attr.textStyle, android.R.attr.text};
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, attrsResources, defStyleAttr, defStyleRes);
                try {
                    setTextSize(0, a.getDimensionPixelSize(0, 0));
                    setTypeface(Typeface.defaultFromStyle(a.getInt(1, 1)));
                    setText(a.getString(2));
                } finally {
                    a.recycle();
                }
            } catch (Throwable th) {
                gravityAttrs.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            colorAttrs.recycle();
            throw th2;
        }
    }

    private void setupOnClickListener() {
        super.setOnClickListener(new View.OnClickListener() { // from class: com.facebook.FacebookButtonBase.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (FacebookButtonBase.this.internalOnClickListener != null) {
                    FacebookButtonBase.this.internalOnClickListener.onClick(v);
                } else if (FacebookButtonBase.this.externalOnClickListener != null) {
                    FacebookButtonBase.this.externalOnClickListener.onClick(v);
                }
            }
        });
    }
}
