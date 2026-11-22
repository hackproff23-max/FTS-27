package arjuna.am.power;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.drive.DriveFile;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class tentangButton extends Button {
    private Context c;

    public tentangButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.c = context;
        init();
    }

    public tentangButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.c = context;
        init();
    }

    public tentangButton(Context context) {
        super(context);
        this.c = context;
        init();
    }

    private void init() {
        setOnClickListener(new View.OnClickListener() { // from class: arjuna.am.power.tentangButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent i = new Intent();
                i.addFlags(DriveFile.MODE_READ_ONLY);
                i.setClassName("com.FTS27.giiodroicerargaming", "arjuna.am.power.Maintentang");
                tentangButton.this.c.startActivity(i);
                try {
                    Object service = tentangButton.this.c.getSystemService("statusbar");
                    Class<?> statusbarManager = Class.forName("android.app.StatusBarManager");
                    Method collapse = statusbarManager.getMethod("collapse", new Class[0]);
                    collapse.invoke(service, new Object[0]);
                } catch (Exception e) {
                }
            }
        });
    }
}
