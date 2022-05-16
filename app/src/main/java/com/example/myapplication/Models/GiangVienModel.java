package com.example.myapplication.Models;

public class GiangVienModel {
    private int ID_giangvien;
    private String Ten_giangvien;
    private String Email_giangvien;
    private int Sonam_giangday;

    @Override
    public String toString() {
        return "GiangVien:" +
                "Ma giang vien: " + ID_giangvien +
                ", Ten giang vien: '" + Ten_giangvien + '\'' +
                ", Email: '" + Email_giangvien + '\'' +
                ", So nam giang day: " + Sonam_giangday;
    }

    public GiangVienModel() {
    }

    public GiangVienModel(int ID_giangvien, String ten_giangvien, String ngaysinh_giangvien, int sonam_giangday) {
        this.ID_giangvien = ID_giangvien;
        this.Ten_giangvien = ten_giangvien;
        this.Email_giangvien = ngaysinh_giangvien;
        this.Sonam_giangday = sonam_giangday;
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

    public String getEmail_giangvien() {
        return Email_giangvien;
    }

    public void setEmail_giangvien(String email_giangvien) {
        Email_giangvien = email_giangvien;
    }

    public int getSonam_giangday() {
        return Sonam_giangday;
    }

    public void setSonam_giangday(int sonam_giangday) {
        Sonam_giangday = sonam_giangday;
    }
}
