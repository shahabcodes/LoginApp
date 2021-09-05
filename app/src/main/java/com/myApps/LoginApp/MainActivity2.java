package com.myApps.LoginApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.myApps.LoginApp.firebaselog.FirebaseLogActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView tv = findViewById(R.id.textView);
        tv.setText(getIntent().getStringExtra("json"));

        findViewById(R.id.materialButton).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, FirebaseLogActivity.class));
        });
    }
}