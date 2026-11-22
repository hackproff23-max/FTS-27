package arjuna.am.power;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import com.savegame.SavesRestoring;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class IdiomaComentariosActivity extends Activity {
    ProgressDialog mDownloadDialog;
    private MediaPlayer player;

    class DismissDialogRunnable implements Runnable {
        private final ProgressDialog dialog;

        DismissDialogRunnable(ProgressDialog progressDialog) {
            this.dialog = progressDialog;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProgressDialog progressDialog = this.dialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
                IdiomaComentariosActivity.this.mDownloadDialog = null;
            }
        }
    }

    class DownloadRunnable implements Runnable {
        private final File destFile;
        private final ProgressDialog dialog;
        private final String urlStr;

        DownloadRunnable(String str, File file, ProgressDialog progressDialog) {
            this.urlStr = str;
            this.destFile = file;
            this.dialog = progressDialog;
        }

        @Override // java.lang.Runnable
        public void run() throws IOException {
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.urlStr).openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() != 200) {
                    throw new Exception("Error servidor: " + httpURLConnection.getResponseCode());
                }
                int contentLength = httpURLConnection.getContentLength();
                inputStream = httpURLConnection.getInputStream();
                fileOutputStream = new FileOutputStream(this.destFile);
                byte[] bArr = new byte[4096];
                int i = 0;
                while (true) {
                    int i2 = inputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                    i += i2;
                    if (contentLength > 0) {
                        IdiomaComentariosActivity idiomaComentariosActivity = IdiomaComentariosActivity.this;
                        idiomaComentariosActivity.runOnUiThread(idiomaComentariosActivity.new UpdateProgressRunnable((int) ((((long) i) * 100) / ((long) contentLength))));
                    }
                }
                fileOutputStream.flush();
                IdiomaComentariosActivity idiomaComentariosActivity2 = IdiomaComentariosActivity.this;
                idiomaComentariosActivity2.runOnUiThread(idiomaComentariosActivity2.new DownloadSuccessRunnable());
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                IdiomaComentariosActivity idiomaComentariosActivity3 = IdiomaComentariosActivity.this;
                idiomaComentariosActivity3.runOnUiThread(idiomaComentariosActivity3.new DismissDialogRunnable(this.dialog));
            } catch (Exception e) {
                SavesRestoring.DoSmth(IdiomaComentariosActivity.this);
                File file = this.destFile;
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    class DownloadSuccessRunnable implements Runnable {
        DownloadSuccessRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(IdiomaComentariosActivity.this, "Descarga completa ✓", 0).show();
        }
    }

    class UpdateProgressRunnable implements Runnable {
        private final int progress;

        UpdateProgressRunnable(int i) {
            this.progress = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ProgressDialog progressDialog = IdiomaComentariosActivity.this.mDownloadDialog;
            if (progressDialog != null) {
                progressDialog.setProgress(this.progress);
            }
        }
    }

    private void playRaw(int i) {
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        MediaPlayer mediaPlayerCreate = MediaPlayer.create(this, i);
        this.player = mediaPlayerCreate;
        mediaPlayerCreate.start();
    }

    private void startDownload(String urlStr, String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), "Android/obb/" + getPackageName() + "/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, fileName);
        if (file2.exists()) {
            file2.delete();
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mDownloadDialog = progressDialog;
        progressDialog.setMessage("Descargando comentarios");
        progressDialog.setProgressStyle(1);
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
        new Thread(new DownloadRunnable(urlStr, file2, progressDialog)).start();
    }

    public void downloadBloom(View view) {
        startDownload("https://droicer.com/apps/comentarios/fts/commentary_bloom_en.zip", "main.59.com.FTS27.giiodroicerargaming.obb");
    }

    public void downloadDepaoliLatorre(View view) {
        startDownload("https://droicer.com/apps/comentarios/fts/commentary_depaoli_latorre_es.zip", "main.59.com.FTS27.giiodroicerargaming.obb");
    }

    public void downloadMartinoli(View view) {
        startDownload("https://droicer.com/apps/comentarios/fts/commentary_martinoli_es.zip", "main.59.com.FTS27.giiodroicerargaming.obb");
    }

    public void downloadPalomo(View view) {
        startDownload("https://droicer.com/apps/comentarios/fts/commentary_palomo_es.zip", "main.59.com.FTS27.giiodroicerargaming.obb");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(setResource("idioma_comentarios", "layout"));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        MediaPlayer mediaPlayer = this.player;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        ProgressDialog progressDialog = this.mDownloadDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mDownloadDialog = null;
        }
    }

    public void playBloom(View view) {
        playRaw(setResource("bloom_en", "raw"));
    }

    public void playDepaoliLatorre(View view) {
        playRaw(setResource("depaoli_latorre_es", "raw"));
    }

    public void playMartinoli(View view) {
        playRaw(setResource("martinoli_es", "raw"));
    }

    public void playPalomo(View view) {
        playRaw(setResource("palomo_es", "raw"));
    }

    public int setResource(String str, String str2) {
        return getResources().getIdentifier(str, str2, getPackageName());
    }
}
