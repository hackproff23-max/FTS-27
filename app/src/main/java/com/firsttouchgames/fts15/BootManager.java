package com.firsttouchgames.fts15;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BootManager {
    static final String mBootFile = "BootAttempts";
    static Context mContext;

    public static void SetContext(Context context) {
        mContext = context;
    }

    public static boolean ShouldEnterSafeMode() {
        Integer iBootNumber = 0;
        StringBuffer fileContent = new StringBuffer("");
        try {
            FileInputStream inputStream = mContext.openFileInput(mBootFile);
            if (inputStream != null) {
                while (true) {
                    int ch = inputStream.read();
                    if (ch == -1) {
                        break;
                    }
                    fileContent.append((char) ch);
                }
                String data = new String(fileContent);
                iBootNumber = Integer.valueOf(Integer.parseInt(data) + 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NumberFormatException e3) {
            e3.printStackTrace();
            iBootNumber = 1;
        }
        SetBootNumber(iBootNumber.intValue());
        return iBootNumber.intValue() >= 4;
    }

    public static void SetBootNumber(int iBootNumber) {
        Integer iBoot = Integer.valueOf(iBootNumber);
        try {
            FileOutputStream fos = mContext.openFileOutput(mBootFile, 0);
            if (fos != null) {
                fos.write(iBoot.toString().getBytes());
                fos.close();
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        } catch (IOException error2) {
            error2.printStackTrace();
        }
    }
}
