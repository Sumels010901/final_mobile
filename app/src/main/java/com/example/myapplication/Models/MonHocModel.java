package com.example.myapplication.Models;

public class MonHocModel {
    private int ID_monhoc;
    private String Ten_monhoc;
    private int Soluong_sv;
    private int ID_giangvien;
    private String Ten_giangvien;
    private String TKB_monhoc;

    public MonHocModel() {
    }

    public MonHocModel(int ID_monhoc, String ten_monhoc, int soluong_sv, int ID_giangvien, String ten_giangvien, String TKB_monhoc) {
        this.ID_monhoc = ID_monhoc;
        Ten_monhoc = ten_monhoc;
        Soluong_sv = soluong_sv;
        this.ID_giangvien = ID_giangvien;
        Ten_giangvien = ten_giangvien;
        this.TKB_monhoc = TKB_monhoc;
    }

    public int getID_monhoc() {
        return ID_monhoc;
    }

    public void setID_monhoc(int ID_monhoc) {
        this.ID_monhoc = ID_monhoc;
    }

    public String getTen_monhoc() {
        return Ten_monhoc;
    }

    public void setTen_monhoc(String ten_monhoc) {
        Ten_monhoc = ten_monhoc;
    }

    public int getSoluong_sv() {
        return Soluong_sv;
    }

    public void setSoluong_sv(int soluong_sv) {
        Soluong_sv = soluong_sv;
    }

    public int getID_giangvien() {
        return ID_giangvien;
    }

    public void setID_giangvien(int ID_giangvien) {
        this.ID_giangvien = ID_giangvien;
    }

    public String getTen_giangvien() {
        return Ten_giangvien;
    }

    public void setTen_giangvien(String ten_giangvien) {
        Ten_giangvien = ten_giangvien;
    }

    public String getTKB_monhoc() {
        return TKB_monhoc;
    }

    public void setTKB_monhoc(String TKB_monhoc) {
        this.TKB_monhoc = TKB_monhoc;
    }
}
