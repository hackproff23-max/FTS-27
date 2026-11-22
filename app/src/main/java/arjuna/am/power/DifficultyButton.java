package arjuna.am.power;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public class DifficultyButton extends Button {
    private Context ctx;

    public DifficultyButton(Context context) {
        super(context);
        this.ctx = context;
        init();
    }

    public DifficultyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }

    public DifficultyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.ctx = context;
        init();
    }

    private int getValue() {
        return this.ctx.getSharedPreferences("GAMECONFIG", 0).getInt("DIFFICULTY", 1);
    }

    private void init() {
        refreshText();
        setOnClickListener(new View.OnClickListener() { // from class: arjuna.am.power.DifficultyButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DifficultyButton.this.cycle();
            }
        });
    }

    private void refreshText() {
        int value = getValue();
        if (value == 0) {
            setText("Dificultad: Facil");
        } else if (value == 1) {
            setText("Dificultad: Normal");
        } else {
            setText("Dificultad: Dificil");
        }
    }

    public void cycle() {
        this.ctx.getSharedPreferences("GAMECONFIG", 0).edit().putInt("DIFFICULTY", (getValue() + 1) % 3).commit();
        refreshText();
    }
}
