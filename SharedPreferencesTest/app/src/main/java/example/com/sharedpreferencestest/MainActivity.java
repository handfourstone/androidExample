package example.com.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button saveData = findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name", "tuolei");
                editor.putInt("age", 26);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button restoreData = findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "MainActivity";
                SharedPreferences editor = getSharedPreferences("data", MODE_PRIVATE);
                String name = editor.getString("name", "");
                boolean married = editor.getBoolean("married", false);
                int age = editor.getInt("age",20);
                Log.d(TAG, "name: " + name);
                Log.d(TAG, "married: " + married);
                Log.d(TAG, "age: " + age);
            }
        });
    }
}
