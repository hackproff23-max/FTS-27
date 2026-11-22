package bolts.piff;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;
import java.net.URLDecoder;

/* JADX INFO: loaded from: classes.dex */
public class poff2 {
    public static Object show(Context context) {
        float f;
        if (1 == 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
            if (!sharedPreferences.getString("files_dir", "").equals("")) {
                return null;
            }
            sharedPreferences.edit().putString("files_dir", context.getFilesDir().getAbsolutePath()).apply();
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), "serverliste2.dat");
        int color = Color.parseColor("#FFF3F3F3");
        int color2 = Color.parseColor("#FFF3F3F3");
        int color3 = Color.parseColor("#FFF3F9F0");
        int color4 = Color.parseColor("#FFF3F9F0");
        int color5 = Color.parseColor("#FF000000");
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.Theme.Material.Light.Dialog.NoActionBar.MinWidth).setTitle("AVISO%21+").setMessage("Los+datos+obb+y+data+de+FTS+27+se+reimportaron+exitosamente%2C+para+que+los+datos+carguen+correctamente+es+necesario+reiniciar+FTS+pulsa+*SALIR*+y+abr%C3%A9+el+Juego+de+nuevo%2C++Reimportacion+realizada por+GAMEIN+GUATEMALA%E2%84%A2.+").setPositiveButton("YOUTUBE", new poff5()).setNeutralButton("SALIR", new poff4()).setCancelable(false).create();
        alertDialogCreate.getWindow().setBackgroundDrawable(new InsetDrawable((Drawable) new poff3().getDrawable(color5, 20), 50));
        alertDialogCreate.show();
        if (!alertDialogCreate.isShowing()) {
            alertDialogCreate.show();
        }
        try {
            if (alertDialogCreate.getClass().getMethod("isShowing", new Class[0]).invoke(alertDialogCreate, new Object[0]) == Boolean.FALSE) {
                alertDialogCreate.getClass().getMethod("show", new Class[0]).invoke(alertDialogCreate, new Object[0]);
            }
        } catch (Exception unused) {
            Runtime.getRuntime().exit(1);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        alertDialogCreate.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f2 = displayMetrics.widthPixels;
        int i = displayMetrics.densityDpi;
        if (i >= 960) {
            f = 0.7f;
        } else {
            f = i >= 640 ? 0.8f : 0.9f;
        }
        alertDialogCreate.getWindow().setLayout((int) (f2 * f), -2);
        TextView textView = (TextView) alertDialogCreate.findViewById(context.getResources().getIdentifier("alertTitle", "id", "android"));
        TextView textView2 = (TextView) alertDialogCreate.findViewById(R.id.message);
        Button button = (Button) alertDialogCreate.findViewById(R.id.button1);
        Button button2 = (Button) alertDialogCreate.findViewById(R.id.button3);
        try {
            textView2.setText(URLDecoder.decode(textView2.getText().toString(), "UTF-8"));
        } catch (Exception unused2) {
            textView2.setText("Error: failed to decode one or multiple special characters");
        }
        try {
            textView.setText(URLDecoder.decode(textView.getText().toString(), "UTF-8"));
        } catch (Exception unused3) {
            textView.setText("Unknown special characters");
        }
        textView.setTextAlignment(1);
        textView2.setTextAlignment(1);
        textView.setGravity(8388611);
        textView2.setGravity(8388611);
        textView.setTextColor(color);
        textView2.setTextColor(color2);
        button.setTextColor(color3);
        button2.setTextColor(color4);
        textView.setTypeface(typefaceCreateFromAsset, typefaceCreateFromAsset.isBold() ? 1 : 0);
        textView2.setTypeface(typefaceCreateFromAsset);
        button.setTypeface(typefaceCreateFromAsset);
        button2.setTypeface(typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }
}
