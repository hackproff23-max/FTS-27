package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class bl extends RelativeLayout {
    private static Rect d = new Rect();
    private a a;
    private boolean b;
    protected Button c;
    private boolean e;

    protected abstract void a(MotionEvent motionEvent);

    public bl(Context context) {
        super(context);
        this.b = false;
        this.c = null;
        this.e = true;
        b();
    }

    private void b() {
        this.a = new a(getContext());
        this.a.setOnTouchListener(new View.OnTouchListener() { // from class: com.chartboost.sdk.impl.bl.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                boolean zB = bl.b(v, event);
                switch (event.getActionMasked()) {
                    case 0:
                        bl.this.a.a(zB);
                        return zB;
                    case 1:
                        if (bl.this.getVisibility() == 0 && bl.this.isEnabled() && zB) {
                            bl.this.a(event);
                        }
                        bl.this.a.a(false);
                        return true;
                    case 2:
                        bl.this.a.a(zB);
                        return true;
                    case 3:
                    case 4:
                        bl.this.a.a(false);
                        return true;
                    default:
                        return true;
                }
            }
        });
        addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(d);
        d.left += view.getPaddingLeft();
        d.top += view.getPaddingTop();
        d.right -= view.getPaddingRight();
        d.bottom -= view.getPaddingBottom();
        return d.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    public class a extends bk {
        public a(Context context) {
            super(context);
            bl.this.b = false;
        }

        protected void a(boolean z) {
            if (!bl.this.e || !z) {
                if (bl.this.b) {
                    if (getDrawable() != null) {
                        getDrawable().clearColorFilter();
                    } else if (getBackground() != null) {
                        getBackground().clearColorFilter();
                    }
                    invalidate();
                    bl.this.b = false;
                    return;
                }
                return;
            }
            if (!bl.this.b) {
                if (getDrawable() != null) {
                    getDrawable().setColorFilter(1996488704, PorterDuff.Mode.SRC_ATOP);
                } else if (getBackground() != null) {
                    getBackground().setColorFilter(1996488704, PorterDuff.Mode.SRC_ATOP);
                }
                invalidate();
                bl.this.b = true;
            }
        }

        public void a(com.chartboost.sdk.Libraries.j jVar, ViewGroup.LayoutParams layoutParams) {
            a(jVar);
            layoutParams.width = jVar.h();
            layoutParams.height = jVar.i();
        }
    }

    public void a(String str) {
        if (str != null) {
            a().setText(str);
            addView(a(), new RelativeLayout.LayoutParams(-1, -1));
            this.a.setVisibility(8);
            a(false);
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.chartboost.sdk.impl.bl.2
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    bl.this.a((MotionEvent) null);
                }
            });
            return;
        }
        if (this.c != null) {
            removeView(a());
            this.c = null;
            this.a.setVisibility(0);
            a(true);
        }
    }

    public TextView a() {
        if (this.c == null) {
            this.c = new Button(getContext());
            this.c.setGravity(17);
        }
        this.c.postInvalidate();
        return this.c;
    }

    public void a(com.chartboost.sdk.Libraries.j jVar) {
        this.a.a(jVar);
        a((String) null);
    }

    public void a(com.chartboost.sdk.Libraries.j jVar, RelativeLayout.LayoutParams layoutParams) {
        this.a.a(jVar, layoutParams);
        a((String) null);
    }

    public void a(ImageView.ScaleType scaleType) {
        this.a.setScaleType(scaleType);
    }

    public void a(boolean z) {
        this.e = z;
    }
}
