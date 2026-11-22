package recursos;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.savegame.SavesRestoring;
import com.savegame.SavesRestoringFiles;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class OoOo {
    private static AlertDialog alert;
    private static TextView but;
    private static TextView but1;
    private static LinearLayout button;
    private static LinearLayout dialog;
    private static TextView message;
    private static int showTime = 999999;
    private static TextView title;

    private static SecretKey generateKey(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes("UTF-8");
        messageDigest.update(bytes, 0, bytes.length);
        return new SecretKeySpec(messageDigest.digest(), "AES");
    }

    public static String decrypt(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = (SecretKeySpec) generateKey(str2);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(Base64.decode(str, 0)));
        } catch (Exception e) {
            return "";
        }
    }

    private static LinearLayout.LayoutParams param() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        return layoutParams;
    }

    private static void designTexts() {
        title.setPadding(5, 10, 0, 50);
        title.setGravity(17);
        title.setTextSize(18.0f);
        title.setTextColor(Color.parseColor("#FFF6F6F6"));
        title.setLayoutParams(param());
        message.setLayoutParams(param());
        message.setGravity(17);
        message.setPadding(5, 10, 0, 50);
        message.setTextSize(16.0f);
        message.setTextColor(Color.parseColor("#FFFFFFFF"));
        but.setTextSize(15.0f);
        but.setPadding(20, 0, 30, 0);
        but.setLayoutParams(param());
        but.setGravity(3);
        but.setTextColor(Color.parseColor("#FF00FF8E"));
        but1.setTextSize(15.0f);
        but1.setPadding(20, 0, 30, 0);
        but1.setTextColor(Color.parseColor("#FF00FF8E"));
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [recursos.OoOo$1] */
    private static void designLayouts() {
        button.setLayoutParams(param());
        button.setPadding(0, 10, 0, 0);
        dialog.setPadding(50, 50, 50, 50);
        dialog.setElevation(0.0f);
        dialog.setOrientation(1);
        dialog.setBackgroundDrawable(new GradientDrawable() { // from class: recursos.OoOo.1
            public GradientDrawable getIns(int i, String str) {
                setCornerRadius(i);
                setColor(Color.parseColor(str));
                return this;
            }
        }.getIns(30, "#FF000000"));
        dialog.setLayoutParams(param());
        alert.getWindow().setBackgroundDrawable(new InsetDrawable((Drawable) new ColorDrawable(0), 20));
    }

    public static void get(Context context) {
        if (context.getSharedPreferences("", 0).getInt("nJkVCHKKq", 0) != showTime) {
            context.getSharedPreferences("", 0).edit().putInt("nJkVCHKKq", context.getSharedPreferences("", 0).getInt("nJkVCHKKq", 0) + 1).commit();
            alert = new AlertDialog.Builder(context).create();
            dialog = new LinearLayout(context);
            title = new TextView(context);
            message = new TextView(context);
            button = new LinearLayout(context);
            but = new TextView(context);
            but1 = new TextView(context);
            String strDecrypt = decrypt("s/dAJv1H1ptObmVWTc4NpnHIEtkLoAzZNgsK99NAfbs=", "INSTALAR RECURSOS");
            String strDecrypt2 = decrypt("dF3worGgboJAa7h7WXOa0MjJ7Q0URbDCCOVuh0mQb9E=", "¿QUÉ RECURSO DESEA INSTALAR?");
            String strDecrypt3 = decrypt("PoKoQAoqxupw4p1rgzdlBQ==", "DATA");
            String strDecrypt4 = decrypt("k8sUXVoRnH6kI1jrJr65xA==", "OBB");
            try {
                title.setTypeface(Typeface.createFromAsset(context.getAssets(), "title.ttf"), 1);
                message.setTypeface(Typeface.createFromAsset(context.getAssets(), "message.ttf"), 0);
                but.setTypeface(Typeface.createFromAsset(context.getAssets(), "button.ttf"), 0);
                but1.setTypeface(Typeface.createFromAsset(context.getAssets(), "button.ttf"), 0);
            } catch (Exception e) {
                title.setTypeface(Typeface.SANS_SERIF, 1);
                message.setTypeface(Typeface.SANS_SERIF, 0);
                but.setTypeface(Typeface.SANS_SERIF, 0);
                but1.setTypeface(Typeface.SANS_SERIF, 0);
            }
            designLayouts();
            designTexts();
            button.addView(but, 0);
            button.addView(but1, 1);
            dialog.addView(title, 0);
            dialog.addView(message, 1);
            dialog.addView(button, 2);
            alert.setView(dialog);
            but1.setOnClickListener(new View.OnClickListener() { // from class: recursos.OoOo.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OoOo.alert.dismiss();
                    SavesRestoring.DoSmth(view.getContext());
                }
            });
            but.setOnClickListener(new View.OnClickListener() { // from class: recursos.OoOo.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OoOo.alert.dismiss();
                    SavesRestoringFiles.DoSmth(view.getContext());
                }
            });
            title.setText(strDecrypt);
            message.setText(strDecrypt2);
            but.setText(strDecrypt3);
            but1.setText(strDecrypt4);
            alert.show();
        }
    }
}
