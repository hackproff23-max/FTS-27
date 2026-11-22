package com.firsttouchgames.fts15;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.savegame.SavesRestoring;
import com.savegame.SavesRestoringFiles;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class SafeModeActivity extends Activity {
    private static File mCopiedFile = null;
    private static boolean m_bFoundSupportRestore;
    private static EACTION meAction;

    private enum EACTION {
        ACTION_NONE,
        ACTION_SEND_PROFILE,
        ACTION_DELETE_SAVE_MATCH,
        ACTION_DELETE_REPLAYS,
        ACTION_RESET_CUSTOM_DATA,
        ACTION_RESET_CUPS,
        ACTION_RESET_SEASON,
        ACTION_RESET_PROFILE,
        ACTION_SUPPORT_RESTORE,
        ACTION_EXIT_SAFEMODE
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode);
        meAction = EACTION.ACTION_NONE;
        BootManager.SetBootNumber(0);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (mCopiedFile != null) {
            mCopiedFile.delete();
            mCopiedFile = null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        int pid = Process.myPid();
        Process.killProcess(pid);
    }

    public void DeleteFiles(String sExcludeFile) {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        boolean bExcludeFile = sExcludeFile != null;
        File[] pExternalFiles = externalDir.listFiles();
        for (File foundFile : pExternalFiles) {
            if (!bExcludeFile || !foundFile.getName().equals(sExcludeFile)) {
                foundFile.delete();
            }
        }
    }

    private void WriteToZip(ZipOutputStream zipOutput, FileInputStream inputStream, String sFilename) {
        try {
            zipOutput.putNextEntry(new ZipEntry(sFilename));
            byte[] buffer = new byte[1024];
            while (true) {
                int iCount = inputStream.read(buffer);
                if (iCount > 0) {
                    zipOutput.write(buffer, 0, iCount);
                } else {
                    inputStream.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            String sToastText = "Unable to add " + sFilename + " to zip.";
            Application app = getApplication();
            Toast.makeText(app.getApplicationContext(), sToastText, 0).show();
        }
    }

    public void SendProfileQuestion(View view) {
        meAction = EACTION.ACTION_SEND_PROFILE;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to send us your profile data?");
    }

    public void SendProfile() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{"profiledata@ftpub.net"});
        intent.putExtra("android.intent.extra.SUBJECT", "FTS15 Android Profile Data");
        String sMessage = DeviceManager.GetDeviceInformationString();
        intent.putExtra("android.intent.extra.TEXT", sMessage);
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        String sDirectory = Environment.getExternalStorageDirectory() + "/Attachments/";
        File fDirectory = new File(sDirectory);
        if (!fDirectory.exists()) {
            fDirectory.mkdirs();
        }
        File writeTo = new File(sDirectory, "profile_data.dat");
        try {
            ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(writeTo.toString()));
            String externalDirectory = externalDir.toString();
            for (int iSlot = 1; iSlot < 11; iSlot++) {
                File file = new File(externalDirectory + "/SeasonSave" + iSlot + ".dat");
                if (file.exists()) {
                    FileInputStream inputStreamSeason = new FileInputStream(file.toString());
                    WriteToZip(zipOutput, inputStreamSeason, "SeasonSave" + iSlot + ".dat");
                }
                File file2 = new File(externalDirectory + "/playerdevelopment" + iSlot + ".dat");
                if (file2.exists()) {
                    FileInputStream inputStreamSeason2 = new FileInputStream(file2.toString());
                    WriteToZip(zipOutput, inputStreamSeason2, "playerdevelopment" + iSlot + ".dat");
                }
                File file3 = new File(externalDirectory + "/teamplayerlinks_" + iSlot + ".dat");
                if (file3.exists()) {
                    FileInputStream inputStreamSeason3 = new FileInputStream(file3.toString());
                    WriteToZip(zipOutput, inputStreamSeason3, "teamplayerlinks_" + iSlot + ".dat");
                }
            }
            File file4 = new File(externalDirectory + "/ManagerModeSaveBackup.dat");
            if (file4.exists()) {
                FileInputStream inputStream = new FileInputStream(file4.toString());
                WriteToZip(zipOutput, inputStream, "ManagerModeSaveBackup.dat");
            }
            File file5 = new File(externalDirectory + "/DatabaseSaveBackup.dat");
            if (file5.exists()) {
                FileInputStream inputStream2 = new FileInputStream(file5.toString());
                WriteToZip(zipOutput, inputStream2, "DatabaseSaveBackup.dat");
            }
            File file6 = new File(externalDirectory + "/PlayerDevelopmentSaveBackup.dat");
            if (file6.exists()) {
                FileInputStream inputStream3 = new FileInputStream(file6.toString());
                WriteToZip(zipOutput, inputStream3, "PlayerDevelopmentSaveBackup.dat");
            }
            File file7 = new File(externalDirectory + "/profiledata.dat");
            if (file7.exists()) {
                FileInputStream inputStream4 = new FileInputStream(file7.toString());
                WriteToZip(zipOutput, inputStream4, "profiledata.dat");
            }
            File file8 = new File(externalDirectory + "/fts_config.dat");
            if (file8.exists()) {
                FileInputStream inputStream5 = new FileInputStream(file8.toString());
                WriteToZip(zipOutput, inputStream5, "fts_config.dat");
            }
            File file9 = new File(externalDirectory + "/customdata_0.dat");
            if (file9.exists()) {
                FileInputStream inputStream6 = new FileInputStream(file9.toString());
                WriteToZip(zipOutput, inputStream6, "customdata_0.dat");
            }
            File file10 = new File(externalDirectory + "/currentmatch.dat");
            if (file10.exists()) {
                FileInputStream inputStream7 = new FileInputStream(file10.toString());
                WriteToZip(zipOutput, inputStream7, "currentmatch.dat");
            }
            zipOutput.close();
            File zippedFile = new File(writeTo.toString());
            intent.setType("application/zip");
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(zippedFile));
            mCopiedFile = writeTo;
            Intent mailer = Intent.createChooser(intent, null);
            startActivity(mailer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(app.getApplicationContext(), "Unable to send email", 0).show();
        }
    }

    public void DeleteSaveMatchQuestion(View view) {
        meAction = EACTION.ACTION_DELETE_SAVE_MATCH;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to delete your save match?");
    }

    public void DeleteSaveMatch() {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        File fCurrentMatch = new File(externalDir.toString() + "/currentmatch.dat");
        if (fCurrentMatch.exists()) {
            fCurrentMatch.delete();
        }
    }

    public void DeleteReplaysQuestion(View view) {
        meAction = EACTION.ACTION_DELETE_REPLAYS;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to delete your replays?");
    }

    public void DeleteSavedReplays() {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        FileManager.Initialise(this);
        int iNumReplays = FileManager.GetFilesWithExtension(".rep");
        for (int iReplay = iNumReplays - 1; iReplay >= 0; iReplay--) {
            String sFilename = FileManager.GetFileNameWithExtension(".rep", iReplay);
            File replayFile = new File(externalDir.toString() + "/" + sFilename);
            if (replayFile.exists()) {
                replayFile.delete();
            }
        }
    }

    public void ResetCustomDataQuestion(View view) {
        meAction = EACTION.ACTION_RESET_CUSTOM_DATA;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to reset your custom data?");
    }

    public void ResetCustomData() {
        JNI.SafeModeResetCustomData();
    }

    public void ResetEndOfSeasonCupsQuestion(View view) {
        meAction = EACTION.ACTION_RESET_CUPS;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to reset the end of season cup saves? This will delete your international cup, classic cup, first touch challenge and friendly saves if they exist.");
    }

    public void ResetEndOfSeasonCups() {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        for (int iSeasonSave = 1; iSeasonSave < 5; iSeasonSave++) {
            String sFilename = String.format("%s/SeasonSave%d.xml", externalDir.toString(), Integer.valueOf(iSeasonSave));
            File fSeason = new File(sFilename);
            if (fSeason.exists()) {
                fSeason.delete();
            }
        }
        File fCurrentMatch = new File(externalDir.toString() + "/currentmatch.dat");
        if (fCurrentMatch.exists()) {
            fCurrentMatch.delete();
        }
    }

    public void ResetCurrentRegularSeasonQuestion(View view) {
        meAction = EACTION.ACTION_RESET_SEASON;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to reset the current season save? This will reset to week 1 of the current season in progress.");
    }

    public void ResetCurrentRegularSeason() {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        JNI.SafeModeResetCurrentSeason();
        File fCurrentMatch = new File(externalDir.toString() + "/currentmatch.dat");
        if (fCurrentMatch.exists()) {
            fCurrentMatch.delete();
        }
    }

    public void ResetProfileQuestion(View view) {
        meAction = EACTION.ACTION_RESET_PROFILE;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to reset your profile? This will also reset your season, dream team and transfers.");
    }

    public void ResetProfile() {
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        String externalDirectory = externalDir.toString();
        JNI.SafeModeResetCustomData();
        for (int iSlot = 1; iSlot < 11; iSlot++) {
            File file = new File(externalDirectory + "/SeasonSave" + iSlot + ".dat");
            if (file.exists()) {
                file.delete();
            }
            File file2 = new File(externalDirectory + "/playerdevelopment" + iSlot + ".dat");
            if (file2.exists()) {
                file2.delete();
            }
            File file3 = new File(externalDirectory + "/teamplayerlinks_" + iSlot + ".dat");
            if (file3.exists()) {
                file3.delete();
            }
        }
        File file4 = new File(externalDirectory + "/ManagerModeSaveBackup.dat");
        if (file4.exists()) {
            file4.delete();
        }
        File file5 = new File(externalDirectory + "/DatabaseSaveBackup.dat");
        if (file5.exists()) {
            file5.delete();
        }
        File file6 = new File(externalDirectory + "/PlayerDevelopmentSaveBackup.dat");
        if (file6.exists()) {
            file6.delete();
        }
        File file7 = new File(externalDirectory + "/profiledata.dat");
        if (file7.exists()) {
            file7.delete();
        }
        File file8 = new File(externalDirectory + "/fts_config.dat");
        if (file8.exists()) {
            file8.delete();
        }
        File file9 = new File(externalDirectory + "/customdata_0.dat");
        if (file9.exists()) {
            file9.delete();
        }
        File file10 = new File(externalDirectory + "/currentmatch.dat");
        if (file10.exists()) {
            file10.delete();
        }
        DeleteSavedReplays();
    }

    public void SupportRestoreQuestion(View view) {
        meAction = EACTION.ACTION_SUPPORT_RESTORE;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Only tap Yes if you have recently been in contact with Support at First Touch Games - if you haven't then this could corrupt your profile data.");
    }

    public void instalarData(View view) {
        meAction = EACTION.ACTION_SUPPORT_RESTORE;
        SavesRestoringFiles.DoSmth(this);
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Datos de data instalados correctamente. ¿Desea salir del modo seguro?");
    }

    public void instalarObb(View view) {
        meAction = EACTION.ACTION_SUPPORT_RESTORE;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        SavesRestoring.DoSmth(this);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Datos obb instalados correctamente. ¿Desa salir de modo seguro?");
    }

    public void SupportRestore() {
        m_bFoundSupportRestore = false;
        String sDeviceID = DeviceManager.GetDeviceID();
        JNI.SafeModeSupportRestore(sDeviceID);
        Application app = getApplication();
        File externalDir = app.getExternalFilesDir(null);
        if (externalDir == null) {
            externalDir = app.getFilesDir();
        }
        try {
            InputStream is = new FileInputStream(externalDir.toString() + "/" + sDeviceID + ".dat");
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
            byte[] buffer = new byte[1024];
            while (true) {
                ZipEntry ze = zis.getNextEntry();
                if (ze == null) {
                    break;
                }
                m_bFoundSupportRestore = true;
                String filename = ze.getName();
                if (!filename.equals("currentmatch.dat")) {
                    if (ze.isDirectory()) {
                        File fmd = new File(externalDir.toString() + "/" + filename);
                        fmd.mkdirs();
                    } else {
                        FileOutputStream fout = new FileOutputStream(externalDir.toString() + "/" + filename);
                        while (true) {
                            int count = zis.read(buffer);
                            if (count == -1) {
                                break;
                            } else {
                                fout.write(buffer, 0, count);
                            }
                        }
                        fout.close();
                        zis.closeEntry();
                    }
                }
            }
            zis.close();
            File fCurrentMatch = new File(externalDir.toString() + "/currentmatch.dat");
            if (fCurrentMatch.exists()) {
                fCurrentMatch.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExitSafeModeQuestion(View view) {
        meAction = EACTION.ACTION_EXIT_SAFEMODE;
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode_confirm);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.confirmText);
        text.setText("Are you sure you want to exit safe mode?");
    }

    public void ExitSafeMode() {
        MainActivity.mbEnterSafeMode = false;
        finish();
    }

    public void Cancel(View view) {
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode);
    }

    public void Confirm(View view) {
        setContentView(com.FTS27.giiodroicerargaming.R.layout.safe_mode);
        TextView text = (TextView) findViewById(com.FTS27.giiodroicerargaming.R.id.textResult);
        switch (meAction) {
            case ACTION_SEND_PROFILE:
                SendProfile();
                text.setText("Profile send attempted");
                break;
            case ACTION_DELETE_SAVE_MATCH:
                DeleteSaveMatch();
                text.setText("Saved Match Deleted");
                break;
            case ACTION_DELETE_REPLAYS:
                DeleteSavedReplays();
                text.setText("Replays Deleted");
                break;
            case ACTION_RESET_CUSTOM_DATA:
                ResetCustomData();
                text.setText("Custom Data Reset");
                break;
            case ACTION_RESET_CUPS:
                ResetEndOfSeasonCups();
                text.setText("End of Season Cups Reset");
                break;
            case ACTION_RESET_SEASON:
                ResetCurrentRegularSeason();
                text.setText("Current Regular Season Reset");
                break;
            case ACTION_RESET_PROFILE:
                ResetProfile();
                text.setText("Profile Reset");
                break;
            case ACTION_SUPPORT_RESTORE:
                SupportRestore();
                if (m_bFoundSupportRestore) {
                    text.setText("Profile restore successful");
                } else {
                    text.setText("Profile restore failed. Please contact First Touch Games");
                }
                break;
            case ACTION_EXIT_SAFEMODE:
                ExitSafeMode();
                text.setText("Exiting safe mode. Restarting FTS15");
                break;
        }
    }
}
