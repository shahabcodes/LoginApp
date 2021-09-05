package com.myApps.LoginApp.firebaselog;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "firebasedata")
public class FirebaseLogData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String text;

    private String date;

    public FirebaseLogData() {
    }

    public FirebaseLogData(int ID, String text, String date) {
        this.ID = ID;
        this.text = text;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
