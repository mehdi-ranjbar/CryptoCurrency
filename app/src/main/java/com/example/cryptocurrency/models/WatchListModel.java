package com.example.cryptocurrency.models;

public class WatchListModel {

    private int id;
    private boolean isChecked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
