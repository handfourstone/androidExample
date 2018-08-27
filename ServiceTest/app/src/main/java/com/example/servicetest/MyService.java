package com.example.servicetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
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
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0, intent, 0 );
        Notification notification = new Notification.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
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
