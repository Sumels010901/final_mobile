package com.example.myapplication.Models;

public class TaiKhoanModel {
    private int svID;
    private String password;

    public TaiKhoanModel() {
    }

    public TaiKhoanModel(int svID, String password) {
        this.svID = svID;
        this.password = password;
    }

    public int getSvID() {
        return svID;
    }

    public void setSvID(int svID) {
        this.svID = svID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
