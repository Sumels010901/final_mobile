package com.example.myapplication.Models;

public class SinhVien_MonHoc {
    private int ID_SV;
    private int ID_MH;

    public SinhVien_MonHoc() {
    }

    public SinhVien_MonHoc(int ID_SV, int ID_MH) {
        this.ID_SV = ID_SV;
        this.ID_MH = ID_MH;
    }

    public int getID_SV() {
        return ID_SV;
    }

    public void setID_SV(int ID_SV) {
        this.ID_SV = ID_SV;
    }

    public int getID_MH() {
        return ID_MH;
    }

    public void setID_MH(int ID_MH) {
        this.ID_MH = ID_MH;
    }
}
