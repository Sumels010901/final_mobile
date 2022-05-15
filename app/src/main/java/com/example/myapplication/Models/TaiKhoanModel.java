package com.example.myapplication.Models;

public class TaiKhoanModel {
    private int svID;
    private String password;
    private int accType;
    public TaiKhoanModel() {
    }

    public TaiKhoanModel(int svID, String password, int accType) {
        this.svID = svID;
        this.password = password;
        this.accType = accType;
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
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
