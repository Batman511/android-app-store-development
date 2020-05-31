package com.example.AppStore;

import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

class RequestTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String ...uri) {
        String responseString = null;
        try {
            java.net.URL url = new URL(uri[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
                Reader in = new InputStreamReader(conn.getInputStream(), "UTF-8");

                int bufferSize = 1024;
                char[] buffer = new char[bufferSize];
                StringBuilder out = new StringBuilder();
                for (; ; ) {
                    int rsz = in.read(buffer, 0, buffer.length);
                    if (rsz < 0)
                        break;
                    out.append(buffer, 0, rsz);
                }

                String theString = out.toString();
                return theString;
            }
            else {
                responseString = "FAILED";
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
