package com.example.help_package;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.util.TimeFormatException;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }
    final String LOG_TAG = "myLogs";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    void someTask(){
        for (int i=1;i<=5;i++){
            Log.d(LOG_TAG,"i = "+i);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
