package com.example.materialtest;

import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private SwipeRefreshLayout swipeRefresh;

    private Fruits []fruits = {new Fruits("Apple",R.drawable.apple_pic),new Fruits("Banana", R.drawable.banana_pic),
    new Fruits("Orange", R.drawable.banana_pic), new Fruits("Watermelon", R.drawable.watermelon_pic),
    new Fruits("Pear", R.drawable.pear_pic), new Fruits("Grape", R.drawable.grape_pic),
    new Fruits("Pineapple", R.drawable.pineapple_pic), new Fruits("Strawberry",R.drawable.strawberry_pic),
    new Fruits("Cherry",R.drawable.cherry_pic), new Fruits("Mango", R.drawable.mango_pic)};

    private List<Fruits> fruitsList = new ArrayList<>();
    private FruitAdapter adapter;

    private void initFruits() {
        fruitsList.clear();
        for (int i=0; i<50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitsList.add(fruits[index]);
        }
    }

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
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitsList);
        recyclerView.setAdapter(adapter);

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

        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
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
