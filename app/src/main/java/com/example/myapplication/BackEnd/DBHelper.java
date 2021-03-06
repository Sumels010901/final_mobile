package com.example.myapplication.BackEnd;

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
import com.example.myapplication.Models.SinhVien_MonHoc;
import com.example.myapplication.Models.TaiKhoanModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String SINHVIEN_TABLE = "SINHVIEN_TABLE";
    public static final String ID_SV = "ID_SV";
    public static final String NAME = "NAME";
    public static final String GIOITINH = "SEX";
    public static final String NGAYSINH = "DOB";
    public static final String TAIKHOAN_TABLE = "TAIKHOAN_TABLE";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String LOAI_TAIKHOAN = "ACC_TYPE";
    public static final String MONHOC_TABLE = "MONHOC_TABLE";
    public static final String ID_MH = "ID_MH";
    public static final String TEN_MH = "TEN_MH";
    public static final String ID_GV = "ID_GV";
    public static final String TEN_GV = "TEN_GV";
    public static final String SOLUONG_SV = "SL_SV";
    public static final String TKB_MH = "TKB_MH";//*
    public static final String GIANGVIEN_TABLE = "GIANGVIEN_TABLE";
    public static final String EMAIL_GV = "EMAIL_GV";
    public static final String KN_GV = "KN_GV";//*
    public static final String BANGDIEM_TABLE = "BANGDIEM_TABLE";
    public static final String DIEM = "DIEM";
    public static final String SV_MH_TABLE = "SV_MH_TABLE";
    public static final String ENROLL_ID = "ENROLL_ID";//*

    public DBHelper(@Nullable Context context) {
        super(context, "final_sinhvien.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSinhVienStatement = "CREATE TABLE " + SINHVIEN_TABLE + " (" + ID_SV + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + GIOITINH + " BOOL, " + NGAYSINH + " DATE)";
        db.execSQL(createTableSinhVienStatement);

        String createTableTaiKhoanStatement ="CREATE TABLE " + TAIKHOAN_TABLE + " (" + USERNAME + " INTEGER PRIMARY KEY, " + PASSWORD + " TEXT, " + LOAI_TAIKHOAN + " INTEGER)";
        db.execSQL(createTableTaiKhoanStatement);

        String createTableMonHocStatement ="CREATE TABLE " + MONHOC_TABLE + " (" + ID_MH + " INTEGER PRIMARY KEY, " + TEN_MH + " TEXT, " + ID_GV + " INTEGER, " + TEN_GV + " TEXT, " + SOLUONG_SV + " INT, " + TKB_MH + " DATETIME)";
        db.execSQL(createTableMonHocStatement);

        String createTableGiangVienStatement ="CREATE TABLE " + GIANGVIEN_TABLE + " (" + ID_GV + " INTEGER PRIMARY KEY, " + TEN_GV + " TEXT, " + EMAIL_GV + " TEXT, " + KN_GV + " INT)";
        db.execSQL(createTableGiangVienStatement);

        String createTableDiemStatement = "CREATE TABLE " + BANGDIEM_TABLE + " (" + ID_SV + " INTEGER PRIMARY KEY, " + ID_MH + " INTEGER, " + DIEM + " DOUBLE)";
        db.execSQL(createTableDiemStatement);

        String createTableSVMHStatement = "CREATE TABLE " + SV_MH_TABLE + " (" + ENROLL_ID + " INTEGER PRIMARY KEY, " + ID_SV + " INTEGER, " + ID_MH + " INTEGER, CONSTRAINT SV_hoc_MH FOREIGN KEY (" + ID_SV +") REFERENCES " + SINHVIEN_TABLE + " (" + ID_SV + "), CONSTRAINT MH_chua_SV FOREIGN KEY (" + ID_MH + ") REFERENCES " + MONHOC_TABLE + " (" + ID_MH + "))";
        db.execSQL(createTableSVMHStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    // THAO TAC BANG SINH VIEN

    public boolean themSinhVien (SinhVienModel sinhVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_SV, sinhVien.getID());
        cv.put(NAME, sinhVien.getName());
        cv.put(GIOITINH, sinhVien.isSex());
        cv.put(NGAYSINH, sinhVien.getDob());

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

        long update = db.update(SINHVIEN_TABLE, cv, "ID_SV ="+sinhvien.getID(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaSinhVien(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(SINHVIEN_TABLE, ID_SV +"=?", new String[]{String.valueOf(id)});
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<SinhVienModel> getAllSv(){
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
    public SinhVienModel getSvById(String id) {
        SinhVienModel sinhVien = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SINHVIEN_TABLE + " WHERE " + ID_SV +" = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() ||cursor.getCount() == 0){
            return null;
        }
        else {
            cursor.moveToFirst();
            sinhVien = new SinhVienModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2) > 0,cursor.getString(3));
        }

        db.close();
        return sinhVien;
    }

    // THAO TAC BANG MON HOC

    public boolean themMonHoc(MonHocModel monhoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID_MH, monhoc.getId_monhoc());
        cv.put(TEN_MH, monhoc.getTen_monhoc());
        cv.put(SOLUONG_SV, monhoc.getSoluong_sv());
        cv.put(ID_GV, monhoc.getId_giangvien());
        cv.put(TEN_GV, monhoc.getTen_giangvien());
        cv.put(TKB_MH, monhoc.getTkb_monhoc());
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
        cv.put(SOLUONG_SV, monhoc.getSoluong_sv());
        cv.put(ID_GV, monhoc.getId_giangvien());
        cv.put(TEN_GV, monhoc.getTen_giangvien());
        cv.put(TKB_MH, monhoc.getTkb_monhoc());

        long update = db.update(MONHOC_TABLE, cv, "ID_MH ="+monhoc.getId_monhoc(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean xoaMonHoc (MonHocModel monhoc) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(MONHOC_TABLE, "ID_MH = "+monhoc.getId_monhoc(), null);
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
                MonHocModel monhoc = new MonHocModel();
                monhoc.setId_monhoc(cursor.getInt(0));
                monhoc.setTen_monhoc(cursor.getString(1));
                monhoc.setSoluong_sv(cursor.getInt(4));
                monhoc.setId_giangvien(cursor.getInt(2));
                monhoc.setTen_giangvien(cursor.getString(3));
                monhoc.setTkb_monhoc(cursor.getString(5));
                returnList.add(monhoc);
            } while(cursor.moveToNext());
        }
        db.close();
        return returnList;
    }
    public ArrayList<MonHocModel> monhoc_GV(GiangVienModel giangvien) {
        ArrayList<MonHocModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MONHOC_TABLE + " WHERE " + ID_GV + " = " + giangvien.getID_giangvien();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new monhoc objects
            do {
                int monhocID = cursor.getInt(0);
                String monhocName = cursor.getString(1);
                int soluongSV = cursor.getInt(4);
                int giangvienID = cursor.getInt(2);
                String giangvienName = cursor.getString(3);
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
    public boolean monHocIsExist(String id) {
        MonHocModel monHoc = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + MONHOC_TABLE + " WHERE " + ID_MH + " = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() ||cursor.getCount() == 0){
            return false;
        }
        return true;

    }
    public MonHocModel getMHById(String id) {

        String queryString = "SELECT * FROM " + MONHOC_TABLE + " WHERE " + ID_MH + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        MonHocModel newmonhoc = new MonHocModel();
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new monhoc objects
            do {
                int monhocID = cursor.getInt(0);
                String monhocName = cursor.getString(1);
                int soluongSV = cursor.getInt(4);
                int giangvienID = cursor.getInt(2);
                String giangvienName = cursor.getString(3);
                String TKB = cursor.getString(5);
                newmonhoc = new MonHocModel(monhocID, monhocName, soluongSV, giangvienID, giangvienName, TKB);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        return newmonhoc;
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
    public boolean xoaGiangVien (int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(GIANGVIEN_TABLE, ID_GV+" = "+id, null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<GiangVienModel> getAllGv(){
        ArrayList<GiangVienModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + GIANGVIEN_TABLE+ ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new giangvien objects
            do {
                GiangVienModel newgiangvien = new GiangVienModel();
                newgiangvien.setID_giangvien(cursor.getInt(0));
                newgiangvien.setTen_giangvien(cursor.getString(1));
                newgiangvien.setEmail_giangvien(cursor.getString(2));
                newgiangvien.setSonam_giangday(cursor.getInt(3));
                returnList.add(newgiangvien);
            } while(cursor.moveToNext());
        }
        db.close();
        return returnList;
    }
    public GiangVienModel getGvById(String id) {
        GiangVienModel giangVien = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + GIANGVIEN_TABLE + " WHERE " + ID_GV +" = '" + id +"'";
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() ||cursor.getCount() == 0){
            return null;
        }
        else {
            cursor.moveToFirst();
            giangVien = new GiangVienModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
        }

        db.close();
        return giangVien;
    }

    // THAO TAC BANG TAI KHOAN

    public boolean themTaiKhoan (TaiKhoanModel taikhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tkcv = new ContentValues();

        tkcv.put(USERNAME, taikhoan.getSvID());
        tkcv.put(PASSWORD, taikhoan.getPassword());
        tkcv.put(LOAI_TAIKHOAN, taikhoan.getAccType());
        long insert = db.insert(TAIKHOAN_TABLE, null, tkcv);
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
        cv.put(LOAI_TAIKHOAN, taikhoan.getAccType());

        long update = db.update(TAIKHOAN_TABLE, cv, USERNAME+" = "+taikhoan.getSvID(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaTaiKhoan(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(TAIKHOAN_TABLE, USERNAME+" = "+id, null);
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
                int taikhoanLoai = cursor.getInt(2);
                TaiKhoanModel newtaikhoan = new TaiKhoanModel(taikhoanID, taikhoanPASS, taikhoanLoai);
                returnList.add(newtaikhoan);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public TaiKhoanModel getTaiKhoan(String id){
        TaiKhoanModel taikhoan = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TAIKHOAN_TABLE + " WHERE " + USERNAME +" = '" + id +"'";
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() ||cursor.getCount() == 0){
            return null;
        }
        else {
            cursor.moveToFirst();
            taikhoan = new TaiKhoanModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
        }

        db.close();
        return taikhoan;
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
        cv.put(DIEM, bangdiem.getDiem());

        long update = db.update(BANGDIEM_TABLE, cv, ID_SV+" = "+bangdiem.getID_SV()  +" and " + ID_MH + " = " +bangdiem.getID_MH(),null);
        if(update == -1) {
            return false;
        } else {
            return true;
        }

    }
    public boolean xoaBangDiem(int idSv, int idMonHoc){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(BANGDIEM_TABLE, ID_SV+" = "+idSv +" and " + ID_MH + " = " +idMonHoc, null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<BangDiemModel> getAllBangDiem(){
        ArrayList<BangDiemModel> list = new ArrayList<>();

        String queryString = "SELECT * FROM " + BANGDIEM_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new sinhvien objects
            do {
                BangDiemModel bangDiem = new BangDiemModel();
                bangDiem.setID_SV(cursor.getInt(0));
                bangDiem.setID_MH(cursor.getInt(1));
                bangDiem.setDiem(Float.parseFloat(String.valueOf(cursor.getDouble(2))));

                list.add(bangDiem);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return list;
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

        String queryString = "SELECT * FROM " + BANGDIEM_TABLE + " WHERE " + ID_MH + " = " + monhoc.getId_monhoc();

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

    // THAO TAC BANG SINHVIEN_MONHOC

    public boolean themLuotHoc (SinhVien_MonHoc svmh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ENROLL_ID, svmh.getENROLL_ID());
        cv.put(ID_SV, svmh.getID_SV());
        cv.put(ID_MH, svmh.getID_MH());

        long insert = db.insert(SV_MH_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean xoaLuotHoc(SinhVien_MonHoc svmh){
        SQLiteDatabase db = this.getWritableDatabase();

        long delete = db.delete(SV_MH_TABLE, ENROLL_ID+" = "+svmh.getENROLL_ID(), null);
        if(delete == -1) {
            return false;
        } else {
            return true;
        }
    }
    public ArrayList<SinhVien_MonHoc> CacMonHoc_SinhVien(String id){
        ArrayList<SinhVien_MonHoc> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SV_MH_TABLE + " WHERE " + ID_SV + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new bangdiem objects
            do {
                int enrollID = cursor.getInt(0);
                int sinhvienID = cursor.getInt(1);
                int monhocID = cursor.getInt(2);
                SinhVien_MonHoc newbangdiem = new SinhVien_MonHoc(sinhvienID, monhocID, enrollID);
                returnList.add(newbangdiem);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public ArrayList<SinhVien_MonHoc> DanhSachSV_MonHoc(MonHocModel monhoc){
        ArrayList<SinhVien_MonHoc> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + SV_MH_TABLE + " WHERE " + ID_MH + " = " + monhoc.getId_monhoc();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new bangdiem objects
            do {
                int enrollID = cursor.getInt(0);
                int sinhvienID = cursor.getInt(1);
                int monhocID = cursor.getInt(2);
                SinhVien_MonHoc newsvmh = new SinhVien_MonHoc(sinhvienID, monhocID, enrollID);
                returnList.add(newsvmh);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public SinhVien_MonHoc getsv_mh(SinhVien_MonHoc svmh){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + SV_MH_TABLE + " WHERE " + ENROLL_ID +" = " + svmh.getENROLL_ID();
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() ||cursor.getCount() == 0){
            return null;
        }
        else {
            cursor.moveToFirst();
            svmh = new SinhVien_MonHoc(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
        }

        db.close();
        return svmh;
    }
    public ArrayList<SinhVien_MonHoc> getAllEnroll(){
        ArrayList<SinhVien_MonHoc> list = new ArrayList<>();

        String queryString = "SELECT * FROM " + SV_MH_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new sinhvien objects
            do {
                SinhVien_MonHoc svmh = new SinhVien_MonHoc();
                svmh.setENROLL_ID(cursor.getInt(0));
                svmh.setID_SV(cursor.getInt(1));
                svmh.setID_MH(cursor.getInt(2));

                list.add(svmh);
            } while(cursor.moveToNext());
        } else {
            //failed, do nothing
        }
        cursor.close();
        db.close();
        return list;
    }
}
