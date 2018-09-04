package com.example.materialtest;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:{
                Toast.makeText(getApplicationContext(),"You chicked menu Button1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button2:{
                Toast.makeText(getApplicationContext(),"You chicked menu Button2", Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                break;
        }
    }

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // 实现既使用了 ToolBar ，又让它的外观与 ActionBar 一致
        setSupportActionBar(toolbar);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup: {
                Toast.makeText(this,"You chick backup", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.delete: {
                Toast.makeText(this,"You chick delete", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.settings: {
                Toast.makeText(this,"You chick settings", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.settings2:{
                Toast.makeText(this,"You chick settings2", Toast.LENGTH_SHORT).show();
                break;
            }
            case android.R.id.home:{ // homeAsUp 按钮的 ID 永远是 android.R.id.home
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            }
            default:
                break;
        }
        return true;
    }
}
