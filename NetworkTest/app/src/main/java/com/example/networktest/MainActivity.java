package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView responseText;
    String TAG = "MYTAG";

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
                            .url("http://192.168.2.222/get_data.xml")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseXMLWithPull(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  parseXMLWithPull(final  String xmlData){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name="";
            String version = "";
            String a1 = "";
            String a2 = "";
            String a3 = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:{// 开始解析某个节点
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        } else if ("a1".equals(nodeName)) {
                            a1 = xmlPullParser.nextText();
                        } else if ("a2".equals(nodeName)) {
                            a2 = xmlPullParser.nextText();
                        } else if ("a3".equals(nodeName)) {
                            a3 = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: { //完成解析某个节点
                        if ("app".equals(nodeName)) {
                            Log.d(TAG, "parseXMLWithPull: id is " + id);
                            Log.d(TAG, "parseXMLWithPull: name is " + name);
                            Log.d(TAG, "parseXMLWithPull: version is " + version);
                        } else if ("a".equals(nodeName)) {
                            Log.d(TAG, "parseXMLWithPull: a1 is " + a1);
                            Log.d(TAG, "parseXMLWithPull: a2 is " + a2);
                            Log.d(TAG, "parseXMLWithPull: a3 is " + a3);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
