package com.example.AppStore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.AppStore.SecondActivity.app;

class RequestTask3 extends AsyncTask<String, Void, Void> {
    // private ProgressDialog progressDialog;
    int status = 0;

    @SuppressLint("StaticFieldLeak")
    private Context context;
    public void setContext(Context context){
        this.context = context;
        // this.progressDialog = progress;
    }

    // public void onPreExecute() {
    // progressDialog.show();
    // }

    @Override
    protected Void doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            File sdcard = Environment.getExternalStorageDirectory();
            File myDir = new File(sdcard, "Downloads"); //Android/data/com.company.android.games/temp
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


            File toInstall = new  File(sdcard, "Downloads/temp.apk");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                Uri apkUri = Uri.fromFile(toInstall);
                Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE); //ACTION_INSTALL_PACKAGE
                intent.setData(apkUri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(intent);
            } else {
                //Uri apkUri = Uri.fromFile(toInstall);
                Uri apkUri = FileProvider.getUriForFile(context, com.example.AppStore.BuildConfig.APPLICATION_ID + ".provider", toInstall);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(apkUri);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                context.startActivity(intent);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}