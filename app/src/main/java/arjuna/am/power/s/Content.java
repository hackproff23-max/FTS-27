package arjuna.am.power.s;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class Content extends LinearLayout {
    Animation Ngilang;
    Animation Nongol;
    LinearLayout toggles;

    public Content(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.toggles = (LinearLayout) findViewById(ramsiID("content19", "id"));
        this.Nongol = AnimationUtils.loadAnimation(context, ramsiID("a2m_icon_on", "anim"));
        this.Ngilang = AnimationUtils.loadAnimation(context, ramsiID("a2m_icon_off", "anim"));
        if (!new Boolean(context.getSharedPreferences("MICOFF19", 0).getBoolean("MICON19", true)).booleanValue()) {
            this.toggles.setVisibility(0);
            this.toggles.startAnimation(this.Nongol);
        } else {
            this.toggles.setVisibility(8);
            this.toggles.startAnimation(this.Ngilang);
        }
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: arjuna.am.power.s.Content.1
            private final Content this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                this.this$0.toggles.setVisibility(8);
                this.this$0.toggles.startAnimation(this.this$0.Ngilang);
            }
        };
        BroadcastReceiver broadcastReceiver2 = new BroadcastReceiver(this) { // from class: arjuna.am.power.s.Content.11
            private final Content this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                this.this$0.toggles.setVisibility(0);
                this.this$0.toggles.startAnimation(this.this$0.Nongol);
            }
        };
        context.registerReceiver(broadcastReceiver, new IntentFilter("MIC.19.OFF"));
        context.registerReceiver(broadcastReceiver2, new IntentFilter("MIC.19.ON"));
    }

    public int ramsiID(String str, String str2) {
        return getContext().getResources().getIdentifier(str, str2, getContext().getPackageName());
    }
}
