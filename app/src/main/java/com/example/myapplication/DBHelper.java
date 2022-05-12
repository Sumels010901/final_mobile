package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.Models.TaiKhoanModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String SINHVIEN_TABLE = "SINHVIEN_TABLE";
    public static final String ID_SV = "ID_SV";
    public static final String NAME = "NAME";
    public static final String GIOITINH = "SEX";
    public static final String NGAYSINH = "DOB";
    public static final String TAIKHOAN_TABLE = "TAIKHOAN_TABLE";
    public static final String PASSWORD = "PASSWORD";
    public static final String MONHOC_TABLE = "MONHOC_TABLE";
    public static final String ID_MH = "ID_MH";
    public static final String TEN_MH = "TEN_MH";
    public static final String ID_GV = "ID_GV";
    public static final String TEN_GV = "TEN_GV";
    public static final String SOLUONG_SV = "SL_SV";
    public static final String TKB_MH = "TKB_MH";
    public static final String GIANGVIEN_TABLE = "GIANGVIEN_TABLE";
    public static final String EMAIL_GV = "EMAIL_GV";
    public static final String KN_GV = "KN_GV";
    public static final String BANGDIEM_TABLE = "BANGDIEM_TABLE";
    public static final String DIEM = "DIEM";

    public DBHelper(@Nullable Context context) {
        super(context, "final_sinhvien.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSinhVienStatement = "CREATE TABLE " + SINHVIEN_TABLE + " (" + ID_SV + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + GIOITINH + " BOOL, " + NGAYSINH + " DATE)";
        db.execSQL(createTableSinhVienStatement);

        String createTableTaiKhoanStatement ="CREATE TABLE " + TAIKHOAN_TABLE + " (" + ID_SV + " INTEGER PRIMARY KEY, " + PASSWORD + " TEXT) ";
        db.execSQL(createTableTaiKhoanStatement);

        String createTableMonHocStatement ="CREATE TABLE " + MONHOC_TABLE + " (" + ID_MH + " INTEGER PRIMARY KEY, " + TEN_MH + " TEXT, " + ID_GV + " INTEGER, " + TEN_GV + " TEXT, " + SOLUONG_SV + " INT, " + TKB_MH + " DATETIME)";
        db.execSQL(createTableMonHocStatement);

        String createTableGiangVienStatement ="CREATE TABLE " + GIANGVIEN_TABLE + " (" + ID_GV + " INTEGER PRIMARY KEY, " + TEN_GV + " TEXT, " + EMAIL_GV + " TEXT, " + KN_GV + " INT)";
        db.execSQL(createTableGiangVienStatement);

        String createTableDiemStatement = "CREATE TABLE " + BANGDIEM_TABLE + " (" + ID_SV + " INTEGER PRIMARY KEY, " + ID_MH + " INT, " + DIEM + " DOUBLE)";
        db.execSQL(createTableDiemStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    // THAO TAC BANG SINH VIEN

    public boolean themSinhVien (SinhVienModel sinhvien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_SV, sinhvien.getID());
        cv.put(NAME, sinhvien.getName());
        cv.put(GIOITINH, sinhvien.isSex());
        cv.put(NGAYSINH, sinhvien.getDob());

        long insert = db.insert(SINHVIEN_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean capNhatSinhVien(SinhVienModel sinhvien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, sinhvien.getName());
        cv.put(GIOITINH, sinhvien.isSex());
        cv.put(NGAYSINH, sinhvien.getDob());

        long update = db.update(SINHVIEN_TABLE, cv, "ID ="+sinhvien.getID(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaSinhVien(SinhVienModel sinhvien){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(SINHVIEN_TABLE, "ID = "+sinhvien.getID(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<SinhVienModel> tatcaSinhVien(){
        ArrayList<SinhVienModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SINHVIEN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new sinhvien objects
            do {
                int sinhvienID = cursor.getInt(0);
                String sinhvienName = cursor.getString(1);
                boolean sinhvienSex = cursor.getInt(2) == 1? true: false;
                String sinhvienDob = cursor.getString(3);
                SinhVienModel newsinhvien = new SinhVienModel(sinhvienID, sinhvienName, sinhvienSex, sinhvienDob);
                returnList.add(newsinhvien);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // THAO TAC BANG MON HOC

    public boolean themMonHoc(MonHocModel monhoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_MH, monhoc.getID_monhoc());
        cv.put(TEN_MH, monhoc.getTen_monhoc());
        cv.put(ID_GV, monhoc.getID_giangvien());
        cv.put(TEN_GV, monhoc.getTen_giangvien());
        cv.put(SOLUONG_SV, monhoc.getSoluong_sv());
        cv.put(TKB_MH, monhoc.getTKB_monhoc());
        long insert = db.insert(MONHOC_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean capnhatMonHoc (MonHocModel monhoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TEN_MH, monhoc.getTen_monhoc());
        cv.put(ID_GV, monhoc.getID_giangvien());
        cv.put(TEN_GV, monhoc.getTen_giangvien());
        cv.put(SOLUONG_SV, monhoc.getSoluong_sv());
        cv.put(TKB_MH, monhoc.getTKB_monhoc());

        long update = db.update(MONHOC_TABLE, cv, "ID_MH ="+monhoc.getID_monhoc(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean xoaMonHoc (MonHocModel monhoc) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(MONHOC_TABLE, "ID_MH = "+monhoc.getID_monhoc(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<MonHocModel> tatcaMonHoc(){
        ArrayList<MonHocModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MONHOC_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new monhoc objects
            do {
                int monhocID = cursor.getInt(0);
                String monhocName = cursor.getString(1);
                int soluongSV = cursor.getInt(2);
                int giangvienID = cursor.getInt(3);
                String giangvienName = cursor.getString(4);
                String TKB = cursor.getString(5);
                MonHocModel newmonhoc = new MonHocModel(monhocID, monhocName, soluongSV, giangvienID, giangvienName, TKB);
                returnList.add(newmonhoc);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // THAO TAC BANG GIANG VIEN

    public boolean themGiangVien (GiangVienModel giangvien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_GV, giangvien.getID_giangvien());
        cv.put(TEN_GV, giangvien.getTen_giangvien());
        cv.put(EMAIL_GV, giangvien.getEmail_giangvien());
        cv.put(KN_GV, giangvien.getSonam_giangday());

        long insert = db.insert(GIANGVIEN_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean capnhatGiangVien (GiangVienModel giangvien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TEN_GV, giangvien.getTen_giangvien());
        cv.put(EMAIL_GV, giangvien.getEmail_giangvien());
        cv.put(KN_GV, giangvien.getSonam_giangday());

        long update = db.update(GIANGVIEN_TABLE, cv, ID_GV+" = "+giangvien.getID_giangvien(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean xoaGiangVien (GiangVienModel giangvien) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(GIANGVIEN_TABLE, ID_GV+" = "+giangvien.getID_giangvien(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<GiangVienModel> tatcaGiangVien(){
        ArrayList<GiangVienModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + GIANGVIEN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new giangvien objects
            do {
                int giangvienID = cursor.getInt(0);
                String giangvienName = cursor.getString(1);
                String giangvienEmail = cursor.getString(2);
                int giangvienKN = cursor.getInt(3);
                GiangVienModel newgiangvien = new GiangVienModel(giangvienID, giangvienName, giangvienEmail, giangvienKN);
                returnList.add(newgiangvien);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // THAO TAC BANG TAI KHOAN

    public boolean themTaiKhoan (TaiKhoanModel taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_SV, taikhoan.getSvID());
        cv.put(PASSWORD, taikhoan.getPassword());

        long insert = db.insert(TAIKHOAN_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean capNhatTaiKhoan(TaiKhoanModel taikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PASSWORD, taikhoan.getPassword());

        long update = db.update(TAIKHOAN_TABLE, cv, ID_SV+" = "+taikhoan.getSvID(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaTaiKhoan(TaiKhoanModel taikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(TAIKHOAN_TABLE, ID_SV+" = "+taikhoan.getSvID(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<TaiKhoanModel> tatcaTaiKhoan(){
        ArrayList<TaiKhoanModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TAIKHOAN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new taikhoan objects
            do {
                int taikhoanID = cursor.getInt(0);
                String taikhoanPASS = cursor.getString(1);
                TaiKhoanModel newtaikhoan = new TaiKhoanModel(taikhoanID, taikhoanPASS);
                returnList.add(newtaikhoan);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    // THAO TAC BANG DIEM

    public boolean themBangDiem (BangDiemModel bangdiem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_SV, bangdiem.getID_SV());
        cv.put(ID_MH, bangdiem.getID_MH());
        cv.put(DIEM, bangdiem.getDiem());

        long insert = db.insert(BANGDIEM_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean capNhatBangDiem(BangDiemModel bangdiem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_MH, bangdiem.getID_MH());
        cv.put(DIEM, bangdiem.getDiem());

        long update = db.update(BANGDIEM_TABLE, cv, ID_SV+" = "+bangdiem.getID_SV(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaBangDiem(BangDiemModel bangdiem){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(BANGDIEM_TABLE, ID_SV+" = "+bangdiem.getID_SV(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<BangDiemModel> BangDiem_SV(SinhVienModel sinhvien){
        ArrayList<BangDiemModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BANGDIEM_TABLE + " WHERE " + ID_SV + " = " + sinhvien.getID();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new bangdiem objects
            do {
                int sinhvienID = cursor.getInt(0);
                int monhocID = cursor.getInt(1);
                double diem = cursor.getDouble(2);
                BangDiemModel newbangdiem = new BangDiemModel(sinhvienID, monhocID, diem);
                returnList.add(newbangdiem);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public ArrayList<BangDiemModel> BangDiem_MH(MonHocModel monhoc){
        ArrayList<BangDiemModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BANGDIEM_TABLE + " WHERE " + ID_MH + " = " + monhoc.getID_monhoc();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new bangdiem objects
            do {
                int sinhvienID = cursor.getInt(0);
                int monhocID = cursor.getInt(1);
                double diem = cursor.getDouble(2);
                BangDiemModel newbangdiem = new BangDiemModel(sinhvienID, monhocID, diem);
                returnList.add(newbangdiem);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }
}

