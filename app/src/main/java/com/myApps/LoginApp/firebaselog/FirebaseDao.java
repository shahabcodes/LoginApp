package com.myApps.LoginApp.firebaselog;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.myApps.LoginApp.Logs.MainData;

import java.util.List;

@Dao
public interface FirebaseDao {
    //Insert
    @Insert(onConflict = REPLACE)
    void insert(FirebaseLogData firebaseLogData);

    //GetAllData
    @Query("SELECT * FROM firebasedata")
    List<FirebaseLogData> getAll();


}
