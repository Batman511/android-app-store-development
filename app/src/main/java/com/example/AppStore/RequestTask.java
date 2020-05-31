package com.example.AppStore;

import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String ...uri) /*throws IOException*/ {
        String responseString = null;
        try {
            java.net.URL url = new URL(uri[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                //InputStream in = new BufferedInputStream(conn.getInputStream()/*.toString()*/);
                Reader in = new InputStreamReader(conn.getInputStream(), "UTF-8");
               //конвертировать в стрин
                int bufferSize = 1024;
                char[] buffer = new char[bufferSize];
                StringBuilder out = new StringBuilder();
                for (; ; ) {
                    int rsz = in.read(buffer, 0, buffer.length);
                    if (rsz < 0)
                        break;
                    out.append(buffer, 0, rsz);
                }

                //StringWriter writer = new StringWriter();
               // IOUtils.copy(in, writer, "UTF-8"); //поменять
                //String theString = writer.toString();
                String theString = out.toString();
                /*return theString;*/
                //readStream(in); // Do normal input or output stream reading
                return theString;
            }
            else {
                responseString = "FAILED"; // See documentation for more info on response handling
            }

        } catch (Exception e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
/* это второй асинтаск где будет качаться само приложение
@Override
    protected Void doInBackground(String... arg0) {
        try {
            URL url = new URL(arg0[0]);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            File sdcard = Environment.getExternalStorageDirectory();
            File myDir = new File(sdcard,"Android/data/com.company.android.games/temp");
            myDir.mkdirs();
            File outputFile = new File(myDir, "temp.apk");
            if(outputFile.exists()){
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
            intent.setDataAndType(Uri.fromFile(new File(sdcard,"Android/data/com.mycompany.android.games/temp/temp.apk")), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
            context.startActivity(intent);
*/