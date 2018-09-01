package com.example.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startService = findViewById(R.id.start_service);
        Button stopService = findViewById(R.id.stop_service);
        Button bindService = findViewById(R.id.bind_service);
        Button unbindService = findViewById(R.id.unbind_service);
        Button getProgress = findViewById(R.id.get_progress);
        Button startIntentService = findViewById(R.id.start_intent_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        getProgress.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:{
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            }
            case R.id.stop_service:{
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            }
            case R.id.bind_service:{
                Intent bindIntent  = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服务
                break;
            }
            case R.id.unbind_service:{
                unbindService(connection);//解绑服务
                break;
            }
            case R.id.get_progress:{
                Log.d("MYSERVICE", "downloaded percent: "+downloadBinder.getProgress());
                break;
            }
            case R.id.start_intent_service:{
                Log.d("MYMainActivity", "Thread id is: "+Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            }
            default:
                break;
        }
    }
}