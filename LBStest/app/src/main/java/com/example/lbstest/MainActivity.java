package com.example.lbstest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  LocationClient mLocationClient;
    private TextView positionText;
    private String provider;
    private int runTimes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionText = findViewById(R.id.possition_text_view);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(final BDLocation bdLocation) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runTimes ++;
                        StringBuilder currentPosition = new StringBuilder();
                        currentPosition.append("维度：").append(bdLocation.getLatitude()).append("\n")
                                .append("经度：").append(bdLocation.getLongitude()).append("\n");
                        currentPosition.append("定位方式：");
                        if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                            currentPosition.append("GPS\n");
                        } else if(bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                            currentPosition.append("网络\n");
                        } else {
                            currentPosition.append(bdLocation.getLocType()).append("\n");
                        }
                        currentPosition.append("运行次数："+runTimes);
                        positionText.setText(currentPosition);
                    }
                });
            }
        });

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        // 设置定位模式为仅支持GPS模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "必须同意所有权限才能使用此程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this,"发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            }
            default:
                break;
        }
    }


}
