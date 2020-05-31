package com.example.AppStore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.core.content.FileProvider;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestTask2 extends AsyncTask<String, Void, Void> {
   // private ProgressDialog progressDialog;
    int status = 0;
    int proverka = 1;
    //private ContentObservable MOBSInt;
   // MOBSInt.set(0);

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
    int version1=0,version2=0;
             try {
            URL url = new URL(arg0[0]);
            String packageName = arg0[1];
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

            //получение версии приложения

                 try {
                     File toInstall = new  File(sdcard, "Downloads/temp.apk");
                     PackageInfo packageInfo2 = context.getPackageManager().getPackageArchiveInfo(toInstall.getAbsolutePath(), 0);
                     //String version = packageInfo.versionName;
                     version2 = packageInfo2.versionCode;
                    // Toast.makeText(context, version2,Toast.LENGTH_LONG).show();
                 }catch (Exception e) {
                     e.printStackTrace();
                 };

            //проверка версии

                 try {
                     PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                     //String version = packageInfo.versionName;
                     version1 = packageInfo.versionCode;
                     //Toast.makeText(context, version1,Toast.LENGTH_LONG).show();
                 }catch (Exception e) {
                     version1 = -1;
                     e.printStackTrace();
                 };

                    Log.d("sfsfs",version1+": "+version2);

                // MOBSInt (new OnIntegerChangeListener())


                 if (version1 > version2) {
                     Intent intent2 = new Intent(Intent.ACTION_DELETE,
                             Uri.fromParts("package", packageName,null));
                     context.startActivity(intent2);

                     proverka +=1;
                 };
              //   File newFile = new File(new File(Environment.getExternalStorageDirectory(), "apps"), getImageNameByUrl(appUrl));
              //   Uri apkUri = getUriForFile(context, BuildConfig.APPLICATION_ID+".provider", newFile);


                 if (proverka == 1) {

                     File toInstall = new File(sdcard, "Downloads/temp.apk");
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
                 }
                 proverka = 1;

                 /*(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                     Intent intent = new Intent(Intent.ACTION_VIEW); //ACTION_INSTALL_PACKAGE
                     intent.setData(Uri.fromFile(new File(sdcard, "Android/data/temp.apk")));
                     intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                     context.startActivity(intent);*/



           /* Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(sdcard, "Android/data/temp.apk")), "application/vnd.android.package-archive");
            //Android/data/com.company.android.games/temp/temp.apk
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent); */
        } catch (IOException e) {
                 e.printStackTrace();
             }
        return null;
    }


}