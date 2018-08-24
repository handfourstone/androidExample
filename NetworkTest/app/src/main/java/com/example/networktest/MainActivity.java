package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
        responseText = findViewById(R.id.response_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                sendRequestWithOkHttp();
                break;
            default:
                break;
        }
    }

    private void sendRequestWithOkHttp(){
        // 开启线程发起 HTTP 请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://www.baidu.com")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  showResponse(final  String response){
        // 安卓不允许在子线程进行 UI 操作，我们需要用 runOnUiThread() 方法将线程切换到主线程，然后再更新 UI 元素。
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行 UI 操作，将结果显示在界面上
                responseText.setText(response);
            }
        });
    }
}
