package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;

/* JADX INFO: loaded from: classes.dex */
public final class iz extends Drawable implements Drawable.Callback {
    private boolean LO;
    private int LV;
    private long LW;
    private int LX;
    private int LY;
    private int LZ;
    private int Ma;
    private boolean Mb;
    private b Mc;
    private Drawable Md;
    private Drawable Me;
    private boolean Mf;
    private boolean Mg;
    private boolean Mh;
    private int Mi;
    private int mFrom;

    private static final class a extends Drawable {
        private static final a Mj = new a();
        private static final C0092a Mk = new C0092a();

        /* JADX INFO: renamed from: com.google.android.gms.internal.iz$a$a, reason: collision with other inner class name */
        private static final class C0092a extends Drawable.ConstantState {
            private C0092a() {
            }

            @Override // android.graphics.drawable.Drawable.ConstantState
            public int getChangingConfigurations() {
                return 0;
            }

            @Override // android.graphics.drawable.Drawable.ConstantState
            public Drawable newDrawable() {
                return a.Mj;
            }
        }

        private a() {
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
        }

        @Override // android.graphics.drawable.Drawable
        public Drawable.ConstantState getConstantState() {
            return Mk;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int alpha) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter cf) {
        }
    }

    static final class b extends Drawable.ConstantState {
        int Ml;
        int Mm;

        b(b bVar) {
            if (bVar != null) {
                this.Ml = bVar.Ml;
                this.Mm = bVar.Mm;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.Ml;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new iz(this);
        }
    }

    public iz(Drawable drawable, Drawable drawable2) {
        this(null);
        drawable = drawable == null ? a.Mj : drawable;
        this.Md = drawable;
        drawable.setCallback(this);
        this.Mc.Mm |= drawable.getChangingConfigurations();
        drawable2 = drawable2 == null ? a.Mj : drawable2;
        this.Me = drawable2;
        drawable2.setCallback(this);
        this.Mc.Mm |= drawable2.getChangingConfigurations();
    }

    iz(b bVar) {
        this.LV = 0;
        this.LY = MotionEventCompat.ACTION_MASK;
        this.Ma = 0;
        this.LO = true;
        this.Mc = new b(bVar);
    }

    public boolean canConstantState() {
        if (!this.Mf) {
            this.Mg = (this.Md.getConstantState() == null || this.Me.getConstantState() == null) ? false : true;
            this.Mf = true;
        }
        return this.Mg;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.LV) {
            case 1:
                this.LW = SystemClock.uptimeMillis();
                this.LV = 2;
                break;
            case 2:
                if (this.LW >= 0) {
                    float fUptimeMillis = (SystemClock.uptimeMillis() - this.LW) / this.LZ;
                    z = fUptimeMillis >= 1.0f;
                    if (z) {
                        this.LV = 0;
                    }
                    this.Ma = (int) ((Math.min(fUptimeMillis, 1.0f) * (this.LX - this.mFrom)) + this.mFrom);
                    break;
                }
            default:
                z2 = z;
                break;
        }
        int i = this.Ma;
        boolean z3 = this.LO;
        Drawable drawable = this.Md;
        Drawable drawable2 = this.Me;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.LY) {
                drawable2.setAlpha(this.LY);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.LY - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.LY);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.LY);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.Mc.Ml | this.Mc.Mm;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.Mc.Ml = getChangingConfigurations();
        return this.Mc;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return Math.max(this.Md.getIntrinsicHeight(), this.Me.getIntrinsicHeight());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.max(this.Md.getIntrinsicWidth(), this.Me.getIntrinsicWidth());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        if (!this.Mh) {
            this.Mi = Drawable.resolveOpacity(this.Md.getOpacity(), this.Me.getOpacity());
            this.Mh = true;
        }
        return this.Mi;
    }

    public Drawable hh() {
        return this.Me;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        Drawable.Callback callback;
        if (!ll.ig() || (callback = getCallback()) == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.Mb && super.mutate() == this) {
            if (!canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.Md.mutate();
            this.Me.mutate();
            this.Mb = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        this.Md.setBounds(bounds);
        this.Me.setBounds(bounds);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Drawable.Callback callback;
        if (!ll.ig() || (callback = getCallback()) == null) {
            return;
        }
        callback.scheduleDrawable(this, what, when);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        if (this.Ma == this.LY) {
            this.Ma = alpha;
        }
        this.LY = alpha;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter cf) {
        this.Md.setColorFilter(cf);
        this.Me.setColorFilter(cf);
    }

    public void startTransition(int durationMillis) {
        this.mFrom = 0;
        this.LX = this.LY;
        this.Ma = 0;
        this.LZ = durationMillis;
        this.LV = 1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        Drawable.Callback callback;
        if (!ll.ig() || (callback = getCallback()) == null) {
            return;
        }
        callback.unscheduleDrawable(this, what);
    }
}
