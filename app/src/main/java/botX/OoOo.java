package botX;

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
        title.setTextColor(Color.parseColor("#FFFFE300"));
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
        but.setTextColor(Color.parseColor("#FF91CFFF"));
        but1.setTextSize(15.0f);
        but1.setPadding(20, 0, 30, 0);
        but1.setTextColor(Color.parseColor("#FF00FF06"));
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [botX.OoOo$1] */
    private static void designLayouts() {
        button.setLayoutParams(param());
        button.setPadding(0, 10, 0, 0);
        dialog.setPadding(50, 50, 50, 50);
        dialog.setElevation(0.0f);
        dialog.setOrientation(1);
        dialog.setBackgroundDrawable(new GradientDrawable() { // from class: botX.OoOo.1
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
        if (context.getSharedPreferences("", 0).getInt("jycAMqjnJ", 0) != showTime) {
            context.getSharedPreferences("", 0).edit().putInt("jycAMqjnJ", context.getSharedPreferences("", 0).getInt("jycAMqjnJ", 0) + 1).commit();
            alert = new AlertDialog.Builder(context).create();
            dialog = new LinearLayout(context);
            title = new TextView(context);
            message = new TextView(context);
            button = new LinearLayout(context);
            but = new TextView(context);
            but1 = new TextView(context);
            String strDecrypt = decrypt("FPTs/4xmYcQ8q1v83FVJug==", "FTS 25");
            String strDecrypt2 = decrypt("Nwb24MgZuissj5s6mdWXxnXJjaz9MlF9wmZSep4Oc+KgsAxXpG3FKcNk+ubxfBkwkA3X+JD0nBQ7tUKZ5eJ8/mZBZtpVM+TRTvjmNo6m27LJfJ5P/EuyhDKK8DB022tNswlC0cYJe5nZ3wBK8yH5Zy3XDDmrog1vb1w233EtH8nyHBhLBA8b4HvgFc1DuJ2mAG/Qr/FbecbtEF9cpvm7TA==", "FTS 25 BY GIIODROID. ALFONSO, AR GAMING, FTS PERÚ, FTS LMX & DROICER. NARRACIÓN EN ESPAÑOL: USIELFRIKY VL. TODOS LOS DERECHOS RESERVADOS PARA ELLOS.");
            String strDecrypt3 = decrypt("bv93/7WTHZRYGPIY9k4jpQ==", "MAS");
            String strDecrypt4 = decrypt("nDhQ18XqFXaIH2KGIt5lbQ==", "PLAY");
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
            but1.setOnClickListener(new View.OnClickListener() { // from class: botX.OoOo.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OoOo.alert.dismiss();
                }
            });
            but.setOnClickListener(new View.OnClickListener() { // from class: botX.OoOo.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OoOo.alert.dismiss();
                    recursos.OoOo.get(view.getContext());
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
