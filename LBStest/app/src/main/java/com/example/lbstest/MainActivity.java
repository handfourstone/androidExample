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
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  LocationClient mLocationClient;
    private TextView positionText;
    private String provider;
    private int runTimes = 0;
    private MapView mapView;

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        positionText = findViewById(R.id.possition_text_view);
        mapView = findViewById(R.id.bmapView);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(final BDLocation bdLocation) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runTimes ++;
                        StringBuilder currentPosition = new StringBuilder();
                        currentPosition.append("维度："+ bdLocation.getLatitude() +  "   经度：" + bdLocation.getLongitude() + "\n");
                        currentPosition.append("定位码：" + bdLocation.getLocType()  + "   描述：" + bdLocation.getLocTypeDescription() + "\n");

                        currentPosition.append("地址：" + bdLocation.getCountry() + bdLocation.getProvince() + bdLocation.getCity() +  bdLocation.getDistrict()  + bdLocation.getStreet() + "\n");
                        currentPosition.append("方向：" + bdLocation.getDirection() + "\n");
                        currentPosition.append("描述：" + bdLocation.getLocationDescribe() + "\n");
                        currentPosition.append("\n我的附近：\n");
                        int poiTimes = 0;
                        for (Poi poi : bdLocation.getPoiList()) {
                            poiTimes++;
                            currentPosition.append("【第"+ poiTimes +"】" + "名称：" + poi.getName() + "   概率：" + poi.getRank() + "\n\n");
                        }
                        currentPosition.append("\n运行次数："+runTimes);
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
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedAltitude(true);
        //兴趣点 point of interest
        option.setIsNeedLocationPoiList(true);
        option.setNeedDeviceDirect(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
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
