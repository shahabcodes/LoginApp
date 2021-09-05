package com.myApps.LoginApp.firebaselog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.myApps.LoginApp.Logs.LogsActivity;
import com.myApps.LoginApp.Logs.MainAdapter;
import com.myApps.LoginApp.Logs.MainData;
import com.myApps.LoginApp.Logs.RoomDB;
import com.myApps.LoginApp.R;

import java.util.ArrayList;
import java.util.List;

public class FirebaseLogActivity extends AppCompatActivity {
    TextView tvlogs;
    RecyclerView recyclerView;
    List<FirebaseLogData> dataList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    RoomDB database;
    FirebaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_log);

        tvlogs = findViewById(R.id.tvLogs);

        recyclerView = findViewById(R.id.recycler_view);

        database = RoomDB.getInstance(this);
        dataList = database.firebaseDao().getAll();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FirebaseAdapter(dataList, FirebaseLogActivity.this);
        recyclerView.setAdapter(adapter);


    }
}