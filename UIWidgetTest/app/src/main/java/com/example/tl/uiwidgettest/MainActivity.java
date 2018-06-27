package com.example.tl.uiwidgettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                imageView.setImageResource(R.drawable.orange);
                break;
            default:
                break;
        }
    }
}
