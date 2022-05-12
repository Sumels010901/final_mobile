package com.example.myapplication.Models;

public class BangDiemModel {
    private int ID_SV;
    private int ID_MH;
    private double diem;

    @Override
    public String toString() {
        return "BangDiem:" +
                "Ma sinh vien: " + ID_SV +
                ", Ma mon hoc: " + ID_MH +
                ", Diem: " + diem;
    }

    public BangDiemModel() {
    }

    public BangDiemModel(int ID_SV, int ID_MH, double diem) {
        this.ID_SV = ID_SV;
        this.ID_MH = ID_MH;
        this.diem = diem;
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

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}
