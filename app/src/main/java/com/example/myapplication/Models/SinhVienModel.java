package com.example.myapplication.Models;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.DatePicker;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.Date;

public class SinhVienModel  {



    private int ID;
    private String name;
    private boolean sex;//true: nam; false: nữ
    private String dob;

    public SinhVienModel(int ID, String name, boolean sex, String dob) {
        this.ID = ID;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
    }


    @Override
    public String toString() {
        String gioitinh_txt = "";
        if (sex) {
            gioitinh_txt = "Nam";
        } else {
            gioitinh_txt = "Nu";
        }
        return "SinhVien:" +
                "MSSV: " + ID +
                ", Ten: '" + name + '\'' +
                ", Gioi tinh: " + gioitinh_txt +
                ", Ngay sinh:" + dob;
    }

    public SinhVienModel() {
    }
    //Lấy ngày hiện tại

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}


