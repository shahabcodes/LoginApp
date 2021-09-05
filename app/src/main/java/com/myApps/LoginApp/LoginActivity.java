package com.myApps.LoginApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.myApps.LoginApp.Logs.LogsActivity;
import com.myApps.LoginApp.Logs.MainAdapter;
import com.myApps.LoginApp.Logs.MainData;
import com.myApps.LoginApp.Logs.RoomDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    Button btnLogs;
    FirebaseAuth mAuth;

    MainAdapter adapter;
    RoomDB database;
    List<MainData> dataList = new ArrayList<>();
    String presentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogs = findViewById(R.id.btnLogs);
        mAuth = FirebaseAuth.getInstance();
        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();
        adapter = new MainAdapter(LoginActivity.this, dataList);
        btnLogin.setOnClickListener(view -> {
            loginUser();
        });

        btnLogs.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, LogsActivity.class));
        });

        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calForDate.getTime());

        Calendar calForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calForTime.getTime());

        presentTime = date+ " " +time;


        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();
        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        String title = "Login Success";
                        MainData data = new MainData();
                        data.setText(title);
                        data.setUserid(email);
                        data.setPassward(password);
                        data.setDate(presentTime);
                        database.mainDao().insert(data);
                        title = "";
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(LoginActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else{
                        Toast.makeText(LoginActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        String title = "Log in Error: " + task.getException().getMessage();
                        MainData data = new MainData();
                        data.setText(title);
                        data.setUserid(email);
                        data.setPassward(password);
                        data.setDate(presentTime);
                        database.mainDao().insert(data);
                        title = "";
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}