package com.example.myapplication.Models;

public class SinhVien_MonHoc {
    private int ID_SV;
    private int ID_MH;
    private int ENROLL_ID;

    public int getENROLL_ID() {
        return ENROLL_ID;
    }

    public void setENROLL_ID(int ENROLL_ID) {
        this.ENROLL_ID = ENROLL_ID;
    }

    public SinhVien_MonHoc() {
    }

    public SinhVien_MonHoc(int ID_SV, int ID_MH, int ENROLL_ID) {
        this.ID_SV = ID_SV;
        this.ID_MH = ID_MH;
        this.ENROLL_ID = ENROLL_ID;
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
