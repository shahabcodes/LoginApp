package com.myApps.LoginApp;

public class ItemData {
    private String title, status,id;

    public ItemData() {
    }

    public ItemData(String title, String status, String id) {
        this.title = title;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
