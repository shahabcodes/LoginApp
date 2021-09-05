package com.myApps.LoginApp.Logs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myApps.LoginApp.MainActivity;
import com.myApps.LoginApp.R;

import java.util.ArrayList;
import java.util.List;

public class LogsActivity extends AppCompatActivity {

    TextView tvlogs;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        tvlogs = findViewById(R.id.tvLogs);

        recyclerView = findViewById(R.id.recycler_view);

        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainAdapter(LogsActivity.this, dataList);
        recyclerView.setAdapter(adapter);


    }
}