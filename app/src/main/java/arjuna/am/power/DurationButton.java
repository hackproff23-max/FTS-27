package arjuna.am.power;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public class DurationButton extends Button {
    private Context ctx;

    public DurationButton(Context context) {
        super(context);
        this.ctx = context;
        init();
    }

    public DurationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }

    public DurationButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        init();
    }

    private int getValue() {
        return this.ctx.getSharedPreferences("GAMECONFIG", 0).getInt("DURATION", 1);
    }

    private void init() {
        refreshText();
        setOnClickListener(new View.OnClickListener() { // from class: arjuna.am.power.DurationButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DurationButton.this.cycle();
            }
        });
    }

    private void refreshText() {
        int value = getValue();
        if (value == 0) {
            setText("Duracion: 5 min");
            return;
        }
        if (value == 1) {
            setText("Duracion: 10 min");
        } else if (value == 2) {
            setText("Duracion: 15 min");
        } else {
            setText("Duracion: 20 min");
        }
    }

    public void cycle() {
        this.ctx.getSharedPreferences("GAMECONFIG", 0).edit().putInt("DURATION", (getValue() + 1) % 4).commit();
        refreshText();
    }
}
