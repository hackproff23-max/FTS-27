package bolts.piff;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Process;
import android.support.v4.media.TransportMediator;
import android.support.v4.view.MotionEventCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.net.URLDecoder;

/* JADX INFO: loaded from: classes.dex */
public class piff2 {

    /* JADX INFO: renamed from: bolts.piff.piff2$2, reason: invalid class name */
    public class AnonymousClass2 {
        /* JADX WARN: Type inference failed for: r11v6, types: [bolts.piff.piff2$1] */
        public static Object show(Context context) {
            float f;
            BitmapDrawable bitmapDrawable = null;
            if (1 == 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
                if (!sharedPreferences.getString("files_dir", "").equals("")) {
                    return null;
                }
                sharedPreferences.edit().putString("files_dir", context.getFilesDir().getAbsolutePath()).apply();
            }
            Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), "serverliste2.dat");
            int color = Color.parseColor("#FFE6E6E6");
            int color2 = Color.parseColor("#FF000000");
            int color3 = Color.parseColor("#FF000000");
            int color4 = Color.parseColor("#FF000000");
            int color5 = Color.parseColor("#FFFFFFFF");
            int color6 = Color.parseColor("#FFE23023");
            try {
                bitmapDrawable = (BitmapDrawable) BitmapDrawable.createFromStream(context.getAssets().open("serverliste.dat"), null);
            } catch (Exception unused) {
                Process.killProcess(Process.myPid());
            }
            AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.Theme.Material.Light.Dialog.NoActionBar.MinWidth).setTitle("DATOS OBB").setIcon(bitmapDrawable).setMessage("¿Estas seguro que quieres reimportar el obb de fts 27? Este proceso solo se realiza una vez, pulsa *obb* si quieres reimportar obb, si ya lo reimportaste una vez pulsa regresar y acceda al juego.").setPositiveButton("obb", new DialogInterface.OnClickListener() { // from class: bolts.piff.piff2.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Context context2 = ((Dialog) dialogInterface).getContext();
                    task.DoSmth(context2);
                    piff3.AnonymousClass2.show(context2);
                }
            }).setNeutralButton("regresar", new DialogInterface.OnClickListener() { // from class: bolts.piff.piff2.3
                protected void dismiss(Object obj) {
                    if (obj instanceof AlertDialog) {
                        ((AlertDialog) obj).dismiss();
                    } else if (obj instanceof Dialog) {
                        ((Dialog) obj).dismiss();
                    } else {
                        ((DialogInterface) obj).dismiss();
                    }
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    piff2.show(((Dialog) dialogInterface).getContext());
                }
            }).setCancelable(false).create();
            alertDialogCreate.getWindow().setBackgroundDrawable(new GradientDrawable() { // from class: bolts.piff.piff2.1
                {
                    useTint(-1);
                    setSupportRTL(false);
                }

                public AnonymousClass1 getDrawable(int i, int i2) {
                    setColor(i);
                    setCornerRadius(i2);
                    useTint(i);
                    setSupportRTL(true);
                    return this;
                }

                private void useTint(int i) {
                    setTint(i);
                    setAlpha(MotionEventCompat.ACTION_MASK);
                }

                private void setSupportRTL(boolean z) {
                    setAutoMirrored(z);
                    setStroke(0, Color.argb(100, 125, TransportMediator.KEYCODE_MEDIA_PAUSE, 95));
                }
            }.getDrawable(color5, 20));
            alertDialogCreate.show();
            if (!alertDialogCreate.isShowing()) {
                alertDialogCreate.show();
            }
            try {
                if (alertDialogCreate.getClass().getMethod("isShowing", new Class[0]).invoke(alertDialogCreate, new Object[0]) == Boolean.FALSE) {
                    alertDialogCreate.getClass().getMethod("show", new Class[0]).invoke(alertDialogCreate, new Object[0]);
                }
            } catch (Exception unused2) {
                Process.killProcess(Process.myPid());
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            alertDialogCreate.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f2 = displayMetrics.widthPixels;
            int i = displayMetrics.densityDpi;
            if (i >= 960) {
                f = 0.5f;
            } else {
                f = i >= 640 ? 0.6f : 0.75f;
            }
            alertDialogCreate.getWindow().setLayout((int) (f2 * f), -2);
            LinearLayout linearLayout = (LinearLayout) alertDialogCreate.findViewById(context.getResources().getIdentifier("title_template", "id", "android"));
            ImageView imageView = (ImageView) alertDialogCreate.findViewById(R.id.icon);
            TextView textView = (TextView) alertDialogCreate.findViewById(context.getResources().getIdentifier("alertTitle", "id", "android"));
            TextView textView2 = (TextView) alertDialogCreate.findViewById(R.id.message);
            Button button = (Button) alertDialogCreate.findViewById(R.id.button1);
            Button button2 = (Button) alertDialogCreate.findViewById(R.id.button3);
            try {
                textView2.setText(URLDecoder.decode(textView2.getText().toString(), "UTF-8"));
            } catch (Exception unused3) {
                textView2.setText("Error: failed to decode one or multiple special characters");
            }
            try {
                textView.setText(URLDecoder.decode(textView.getText().toString(), "UTF-8"));
            } catch (Exception unused4) {
                textView.setText("Unknown special characters");
            }
            int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, displayMetrics);
            int iApplyDimension2 = (int) TypedValue.applyDimension(1, 15.0f, displayMetrics);
            linearLayout.setBackgroundColor(color6);
            linearLayout.setGravity(17);
            linearLayout.setPadding(iApplyDimension, iApplyDimension2, iApplyDimension, iApplyDimension2);
            int iApplyDimension3 = (int) TypedValue.applyDimension(1, 65.0f, displayMetrics);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iApplyDimension3, iApplyDimension3);
            layoutParams.rightMargin = (int) TypedValue.applyDimension(1, 15.0f, displayMetrics);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            textView2.setTextAlignment(1);
            textView2.setGravity(17);
            textView2.setTextColor(color2);
            textView2.setTypeface(typefaceCreateFromAsset);
            textView.setTypeface(typefaceCreateFromAsset, typefaceCreateFromAsset.isBold() ? 1 : 0);
            textView.setTextColor(color);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            textView.setMaxLines(2);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            button.setTextColor(color3);
            button.setTypeface(typefaceCreateFromAsset);
            button2.setTextColor(color4);
            button2.setTypeface(typefaceCreateFromAsset);
            return typefaceCreateFromAsset;
        }
    }

    public static Object show(Context context) {
        float f;
        BitmapDrawable bitmapDrawable = null;
        if (1 == 0) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
            if (!sharedPreferences.getString("files_dir", "").equals("")) {
                return null;
            }
            sharedPreferences.edit().putString("files_dir", context.getFilesDir().getAbsolutePath()).apply();
        }
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(context.getAssets(), "serverliste2.dat");
        int color = Color.parseColor("#FFE6E6E6");
        int color2 = Color.parseColor("#FF000000");
        int color3 = Color.parseColor("#FF000000");
        int color4 = Color.parseColor("#FF000000");
        int color5 = Color.parseColor("#FFFFFFFF");
        int color6 = Color.parseColor("#FFE23023");
        try {
            bitmapDrawable = (BitmapDrawable) BitmapDrawable.createFromStream(context.getAssets().open("serverliste.dat"), null);
        } catch (Exception unused) {
            Process.killProcess(Process.myPid());
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, R.style.Theme.Material.Light.Dialog.NoActionBar.MinWidth).setTitle("FTS 27").setIcon(bitmapDrawable).setMessage("BIENVENIDO A FTS 27 BY GIIODROID, DROICER, AR GAMING, GAMEÍN GUATEMALA, FTS PERÚ Y MARCO EPDF CREDITOS RESERVADOS (GIVE US CREDITS) ").setPositiveButton("DATOS", new piff4()).setNeutralButton("ENTRAR", new piff3()).setCancelable(false).create();
        alertDialogCreate.getWindow().setBackgroundDrawable(new piff1().getDrawable(color5, 20));
        alertDialogCreate.show();
        if (!alertDialogCreate.isShowing()) {
            alertDialogCreate.show();
        }
        try {
            if (alertDialogCreate.getClass().getMethod("isShowing", new Class[0]).invoke(alertDialogCreate, new Object[0]) == Boolean.FALSE) {
                alertDialogCreate.getClass().getMethod("show", new Class[0]).invoke(alertDialogCreate, new Object[0]);
            }
        } catch (Exception unused2) {
            Process.killProcess(Process.myPid());
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        alertDialogCreate.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f2 = displayMetrics.widthPixels;
        int i = displayMetrics.densityDpi;
        if (i >= 960) {
            f = 0.5f;
        } else {
            f = i >= 640 ? 0.6f : 0.75f;
        }
        alertDialogCreate.getWindow().setLayout((int) (f2 * f), -2);
        LinearLayout linearLayout = (LinearLayout) alertDialogCreate.findViewById(context.getResources().getIdentifier("title_template", "id", "android"));
        ImageView imageView = (ImageView) alertDialogCreate.findViewById(R.id.icon);
        TextView textView = (TextView) alertDialogCreate.findViewById(context.getResources().getIdentifier("alertTitle", "id", "android"));
        TextView textView2 = (TextView) alertDialogCreate.findViewById(R.id.message);
        Button button = (Button) alertDialogCreate.findViewById(R.id.button1);
        Button button2 = (Button) alertDialogCreate.findViewById(R.id.button3);
        try {
            textView2.setText(URLDecoder.decode(textView2.getText().toString(), "UTF-8"));
        } catch (Exception unused3) {
            textView2.setText("Error: failed to decode one or multiple special characters");
        }
        try {
            textView.setText(URLDecoder.decode(textView.getText().toString(), "UTF-8"));
        } catch (Exception unused4) {
            textView.setText("Unknown special characters");
        }
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, displayMetrics);
        int iApplyDimension2 = (int) TypedValue.applyDimension(1, 15.0f, displayMetrics);
        linearLayout.setBackgroundColor(color6);
        linearLayout.setGravity(17);
        linearLayout.setPadding(iApplyDimension, iApplyDimension2, iApplyDimension, iApplyDimension2);
        int iApplyDimension3 = (int) TypedValue.applyDimension(1, 65.0f, displayMetrics);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(iApplyDimension3, iApplyDimension3);
        layoutParams.rightMargin = (int) TypedValue.applyDimension(1, 15.0f, displayMetrics);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        textView2.setTextAlignment(1);
        textView2.setGravity(17);
        textView2.setTextColor(color2);
        textView2.setTypeface(typefaceCreateFromAsset);
        textView.setTypeface(typefaceCreateFromAsset, typefaceCreateFromAsset.isBold() ? 1 : 0);
        textView.setTextColor(color);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        button.setTextColor(color3);
        button.setTypeface(typefaceCreateFromAsset);
        button2.setTextColor(color4);
        button2.setTypeface(typefaceCreateFromAsset);
        return typefaceCreateFromAsset;
    }
}
