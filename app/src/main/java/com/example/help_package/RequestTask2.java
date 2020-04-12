package com.example.help_package;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class RequestTask2 extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}

/*class RequestTask2 extends AsyncTask<String, String, String> {
    ProgressDialog progressDialog;
    int status = 0;

    private Context context;
    public void setContext(Context context, ProgressDialog progress){
        this.context = context;
        this.progressDialog = progress;
    }

    public void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... arg0) {
             try {
            URL url = new URL(arg0[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            File sdcard = Environment.getExternalStorageDirectory();
            File myDir = new File(sdcard, "Android/data/com.mycompany.android.games/temp");
            myDir.mkdirs();
            File outputFile = new File(myDir, "temp.apk");
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.flush();
            fos.close();
            is.close();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(sdcard, "Android/data/com.mycompany.android.games/temp/temp.apk")), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent);
        }
    }
}*/