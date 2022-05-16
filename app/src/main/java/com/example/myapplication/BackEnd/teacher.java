package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.GiangVienAdapter;
import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class teacher extends AppCompatActivity {
    private EditText editTeacherID,editTeacherName,editTeacherMail,editSoNamGiangDay;
    private Button btnAddTeacher,btnDeleteTeacher,btnEditTeacher;
    private ListView lvGv;
    private DBHelper dbHelper;
    private GiangVienAdapter adapter;
    private ArrayList<GiangVienModel> listGv;

    Button btnTeacherBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        init();
        createEvent();
        btnTeacherBack = findViewById(R.id.btnTeacherBack);
        btnTeacherBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void init(){
        editTeacherID = findViewById(R.id.editTeacherID);
        editTeacherName = findViewById(R.id.editTeacherName);
        editTeacherMail = findViewById(R.id.editTeacherMail);
        editSoNamGiangDay = findViewById(R.id.editSoNamGiangDay);
        btnAddTeacher = findViewById(R.id.btnAddTeacher);
        btnEditTeacher = findViewById(R.id.btnEditTeacher);
        btnEditTeacher.setEnabled(false);
        btnDeleteTeacher = findViewById(R.id.btnDeleteTeacher);
        btnDeleteTeacher.setEnabled(false);
        lvGv = findViewById(R.id.lvGv);
        dbHelper = new DBHelper(this);
        listGv = dbHelper.getAllGv();
        adapter = new GiangVienAdapter(this,R.layout.gv_item_lv,listGv);
        lvGv.setAdapter(adapter);
    }
    public void createEvent(){
        btnAddTeacher.setOnClickListener(this::onClick);
        btnEditTeacher.setOnClickListener(this::onClick);
        btnDeleteTeacher.setOnClickListener(this::onClick);
        lvGv.setOnItemClickListener(this::onItemClick);
    }



    private void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddTeacher:
                if (btnAddTeacher.getText().toString().equalsIgnoreCase("Thêm"))
                    addGv();
                else
                    resetForm();
                break;
            case R.id.btnEditTeacher:
                updateGv();
                break;
            case R.id.btnDeleteTeacher:
                deleteGv();
                break;
        }
    }

    private void addGv() {

        if (editTeacherID.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if(editTeacherName.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if (editTeacherMail.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập email", Toast.LENGTH_SHORT).show();
        }
        else if (editSoNamGiangDay.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập kinh nghiệm", Toast.LENGTH_SHORT).show();
        }
        else if (dbHelper.getGvById(editTeacherID.getText().toString())!=null){
            Toast.makeText(this,"Mã số này đã được sử dụng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themGiangVien(getGvInfo())) {
                Toast.makeText(this,"Thêm gv thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listGv.clear();
                listGv.addAll(dbHelper.getAllGv());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm gv thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private GiangVienModel getGvInfo() {
        GiangVienModel giangVien = new GiangVienModel();
        giangVien.setID_giangvien(Integer.parseInt(editTeacherID.getText().toString()));
        giangVien.setTen_giangvien(editTeacherName.getText().toString());
        giangVien.setEmail_giangvien(editTeacherMail.getText().toString());
        giangVien.setSonam_giangday(Integer.parseInt(editSoNamGiangDay.getText().toString()));
        return giangVien;
    }

    private void updateGv() {
        if (editTeacherID.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if(editTeacherName.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if (editTeacherMail.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập email", Toast.LENGTH_SHORT).show();
        }
        else if (editSoNamGiangDay.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập kinh nghiệm", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.capnhatGiangVien((getGvInfo()))){
                Toast.makeText(this,"Cập nhật thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listGv.clear();
                listGv.addAll(dbHelper.getAllGv());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void deleteGv() {
        if(editTeacherID.getText().toString().equals("")){
            Toast.makeText(this, "Hãy chọn giảng viên", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbHelper.xoaGiangVien(Integer.parseInt(editTeacherID.getText().toString()))) {
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listGv.clear();
                listGv.addAll(dbHelper.getAllGv());
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void resetForm() {
        editTeacherID.setText("");
        editTeacherName.setText("");
        editTeacherMail.setText("");
        editSoNamGiangDay.setText("");
        btnAddTeacher.setText("Thêm");
        btnEditTeacher.setEnabled(false);
        btnDeleteTeacher.setEnabled(false);
        editTeacherID.setEnabled(true);
        editTeacherID.setFocusable(true);
        editTeacherID.setFocusableInTouchMode(true);
        editTeacherID.requestFocus();
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GiangVienModel giangVien = listGv.get(i);
        editTeacherID.setText(String.valueOf(giangVien.getID_giangvien()));
        editTeacherName.setText(giangVien.getTen_giangvien());
        editTeacherMail.setText(giangVien.getEmail_giangvien());
        editSoNamGiangDay.setText(String.valueOf(giangVien.getSonam_giangday()));
        btnEditTeacher.setEnabled(true);
        btnDeleteTeacher.setEnabled(true);
        editTeacherID.setFocusable(false);
        editTeacherID.setEnabled(false);
        btnAddTeacher.setText("Hủy");
    }
}