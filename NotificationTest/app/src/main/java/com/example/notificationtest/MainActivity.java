package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pd = PendingIntent.getActivity(this, 0, intent, 0);
                //第一步
                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                //第二步
                Notification.Builder notificationBuilder = new Notification.Builder(this);
                //第三步
                Notification notification = notificationBuilder
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pd)
                        .setAutoCancel(true)
                        //设置提示音
                        .setSound(Uri.fromFile(new File("/system/media/audio/notifications/Bongo.ogg")))
                        //设置震动
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        // 不想单独设置，也可以使用手机默认设置
                        //.setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .build();
                //第四步
                manager.notify(1, notification);
            default:
                break;
        }
    }
}
