package arjuna.am.power.q;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class Button extends ImageView {
    public Button(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ImageView imageView = (ImageView) findViewById(ramsiID("button17", "id"));
        imageView.setImageResource(ramsiID("a2m_icon_tombol", "drawable"));
        if (new Boolean(context.getSharedPreferences("MICOFF17", 0).getBoolean("MICON17", false)).booleanValue()) {
            imageView.setSelected(false);
        } else {
            imageView.setSelected(true);
        }
        imageView.setOnClickListener(new View.OnClickListener(this, context) { // from class: arjuna.am.power.q.Button.1
            private final Button this$0;
            private final Context val$context;

            {
                this.this$0 = this;
                this.val$context = context;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.isSelected()) {
                    view.setSelected(false);
                    Intent intent = new Intent();
                    intent.setAction("MIC.17.OFF");
                    this.val$context.sendBroadcast(intent);
                    SharedPreferences.Editor editorEdit = this.val$context.getSharedPreferences("MICOFF17", 0).edit();
                    editorEdit.putBoolean("MICON17", true);
                    editorEdit.commit();
                    return;
                }
                view.setSelected(true);
                Intent intent2 = new Intent();
                intent2.setAction("MIC.17.ON");
                this.val$context.sendBroadcast(intent2);
                SharedPreferences.Editor editorEdit2 = this.val$context.getSharedPreferences("MICOFF17", 0).edit();
                editorEdit2.putBoolean("MICON17", false);
                editorEdit2.commit();
            }
        });
    }

    public int ramsiID(String str, String str2) {
        return getContext().getResources().getIdentifier(str, str2, getContext().getPackageName());
    }
}
