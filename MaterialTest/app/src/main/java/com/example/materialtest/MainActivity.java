package com.example.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // 实现既使用了 ToolBar ，又让它的外观与 ActionBar 一致
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", BaseTransientBottomBar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Data restored", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

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
