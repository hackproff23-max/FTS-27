package arjuna.am.power;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes.dex */
public class IdiomaComentariosButton extends Button {
    private Context c;

    public IdiomaComentariosButton(Context context) {
        super(context);
        this.c = context;
        init();
    }

    public IdiomaComentariosButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        init();
    }

    public IdiomaComentariosButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
        init();
    }

    private void init() {
        setOnClickListener(new View.OnClickListener() { // from class: arjuna.am.power.IdiomaComentariosButton.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.addFlags(DriveFile.MODE_READ_ONLY);
                intent.setClassName("com.FTS27.giiodroicerargaming", "arjuna.am.power.IdiomaComentariosActivity");
                IdiomaComentariosButton.this.c.startActivity(intent);
            }
        });
    }
}
