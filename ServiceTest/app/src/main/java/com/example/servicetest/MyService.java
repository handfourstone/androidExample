package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int downloadedPercent = 0;
    public MyService() {
    }

    class DownloadBinder extends Binder{
        public void startDownload() {
            Log.d("MYSERVICE", "startDownload executed");
        }

        public int getProgress(){
            Log.d("MYSERVICE", "getProgess executed");
            downloadedPercent++;
            return downloadedPercent;
        }
    }

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MYSERVICE", "onCreate executed");
        //服务里面任何地方可以通过下面的方式关闭服务
        //stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYSERVICE", "onStartCommand executed ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MYSERVICE", "onDestroy executed ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
