package arjuna.am.power;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public class CoinsButton extends Button {
    private Context ctx;

    public CoinsButton(Context context) {
        super(context);
        this.ctx = context;
        init();
    }

    public CoinsButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }

    public CoinsButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        init();
    }

    private boolean getValue() {
        return this.ctx.getSharedPreferences("GAMECONFIG", 0).getBoolean("INFCOINS", false);
    }

    private void init() {
        refreshText();
        setOnClickListener(new View.OnClickListener() { // from class: arjuna.am.power.CoinsButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                CoinsButton.this.cycle();
            }
        });
    }

    private void refreshText() {
        if (getValue()) {
            setText("Monedas Infinitas: ON");
        } else {
            setText("Monedas Infinitas: OFF");
        }
    }

    public void cycle() {
        this.ctx.getSharedPreferences("GAMECONFIG", 0).edit().putBoolean("INFCOINS", !getValue()).commit();
        refreshText();
    }
}
