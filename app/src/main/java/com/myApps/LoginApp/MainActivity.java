package com.myApps.LoginApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.myApps.LoginApp.Logs.MainData;
import com.myApps.LoginApp.Logs.RoomDB;
import com.myApps.LoginApp.firebaselog.FirebaseLogData;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<ItemData> list;
    private DatabaseReference reference;
//    private Button button;
    private FirebaseAuth auth;
    private int i=0;
    String json = null;
    String presentTime;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        database = RoomDB.getInstance(this);
        reference = FirebaseDatabase.getInstance().getReference().child("items");

        recyclerView  = findViewById(R.id.recyclerView);
//        button  = findViewById(R.id.button);

        list = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
                String date = currentDate.format(calForDate.getTime());

                Calendar calForTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                String time = currentTime.format(calForTime.getTime());

                presentTime = date+ " " +time;

                FirebaseLogData data = new FirebaseLogData();
                data.setText(new Gson().toJson(snapshot.getValue(Object.class)));
                data.setDate(presentTime);
                database.firebaseDao().insert(data);


                list.clear();
                for (DataSnapshot shot : snapshot.getChildren()){
                    ItemData data1 = shot.getValue(ItemData.class);
                    list.add(data1);
                }
                if(i==0) {
                    Object object = snapshot.getValue(Object.class);
                    json = new Gson().toJson(object);
                    i++;
                }

                String finalJson = json;

                adapter = new ItemAdapter(MainActivity.this, list, finalJson);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                      intent.putExtra("json", finalJson);
//                        startActivity(intent);
//
//                    }
//                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (auth.getCurrentUser() == null){
//            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//            finish();
//        }
    }
}