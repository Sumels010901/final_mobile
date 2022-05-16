package com.example.myapplication.Models;

public class MonHocModel {
    private int id_monhoc;
    private String ten_monhoc;
    private int soluong_sv;
    private int id_giangvien;
    private String ten_giangvien;
    private String tkb_monhoc;

    public MonHocModel() {
    }

    public MonHocModel(int id_monhoc, String ten_monhoc, int soluong_sv, int id_giangvien, String ten_giangvien, String tkb_monhoc) {
        this.id_monhoc = id_monhoc;
        this.ten_monhoc = ten_monhoc;
        this.soluong_sv = soluong_sv;
        this.id_giangvien = id_giangvien;
        this.ten_giangvien = ten_giangvien;
        this.tkb_monhoc = tkb_monhoc;
    }

    public int getId_monhoc() {
        return id_monhoc;
    }

    public void setId_monhoc(int id_monhoc) {
        this.id_monhoc = id_monhoc;
    }

    public String getTen_monhoc() {
        return ten_monhoc;
    }

    public void setTen_monhoc(String ten_monhoc) {
        this.ten_monhoc = ten_monhoc;
    }

    public int getSoluong_sv() {
        return soluong_sv;
    }

    public void setSoluong_sv(int soluong_sv) {
        this.soluong_sv = soluong_sv;
    }

    public int getId_giangvien() {
        return id_giangvien;
    }

    public void setId_giangvien(int id_giangvien) {
        this.id_giangvien = id_giangvien;
    }

    public String getTen_giangvien() {
        return ten_giangvien;
    }

    public void setTen_giangvien(String ten_giangvien) {
        this.ten_giangvien = ten_giangvien;
    }

    public String getTkb_monhoc() {
        return tkb_monhoc;
    }

    public void setTkb_monhoc(String tkb_monhoc) {
        this.tkb_monhoc = tkb_monhoc;
    }
}
