package arjuna.am.power;

import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class AnimMain extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResource("tabarjunaam_anim", "layout"));
    }

    public int setResource(String name, String Type) {
        return getBaseContext().getResources().getIdentifier(name, Type, getBaseContext().getPackageName());
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        onCreate(null);
    }
}
