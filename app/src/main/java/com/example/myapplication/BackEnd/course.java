package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterCourse;
import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class course extends AppCompatActivity {
    private EditText txtIDCourse,txtCourseName,txtSL_SV,txtTeacherIDCourse,txtTeacherNameCourse;
    private Spinner spinnerDay,spinnerTietHoc;
    private Button btnAddCourse,btnDeleteCourse,btnEditCourse,btnCourseBack;
    private ListView lvCourse;
    private DBHelper dbHelper;
    private AdapterCourse adapterCourse;
    private ArrayList<MonHocModel> listMonHoc;

    String[] ngay = {"Thứ 2", "Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7"};
    String[] tiet = {"Tiết 1", "Tiết 2", "Tiết 3", "Tiết 4", "Tiết 5", "Tiết 6"
            , "Tiết 7", "Tiết 8", "Tiết 9", "Tiết 10", "Tiết 11"};
    Spinner j_spinner; //combobox của thứ
    Spinner i_spinner; //combobox của tiết
    String TKB_MH1 = "";
    String TKB_MH2 = "";
    String TKB_MH = ""; // Chuỗi TKB_MH để cho vào db nè
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        init();
        createEvent();
        btnCourseBack = findViewById(R.id.btnCourseBack);
        btnCourseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        j_spinner = findViewById(R.id.spinnerDay);

        i_spinner = findViewById(R.id.spinnerTietHoc);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ngay);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiet);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spinner.setAdapter(adapter);
        j_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                TKB_MH1 = j_spinner.getSelectedItem().toString() + " ";
                TKB_MH2 =  i_spinner.getSelectedItem().toString();
                TKB_MH = TKB_MH1 + TKB_MH2;
                //j_spinner_selected.setText(j_spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        i_spinner.setAdapter(adapter2);
        i_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                TKB_MH1 = j_spinner.getSelectedItem().toString() + " ";
                TKB_MH2 = i_spinner.getSelectedItem().toString();
                TKB_MH = TKB_MH1 + TKB_MH2;
                //i_spinner_selected.setText(i_spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void init(){
        txtIDCourse = findViewById(R.id.txtIDCourse);
        txtCourseName = findViewById(R.id.txtCourseName);
        txtSL_SV = findViewById(R.id.txtSL_SV);
        txtTeacherIDCourse = findViewById(R.id.txtTeacherIDCourse);
        txtTeacherNameCourse = findViewById(R.id.txtTeacherNameCourse);
        txtTeacherNameCourse.setEnabled(false);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnDeleteCourse = findViewById(R.id.btnDeleteCourse);
        btnEditCourse = findViewById(R.id.btnEditCourse);
        spinnerDay = findViewById(R.id.spinnerDay);
        spinnerTietHoc = findViewById(R.id.spinnerTietHoc);
        btnEditCourse.setEnabled(false);
        btnDeleteCourse.setEnabled(false);

        lvCourse = findViewById(R.id.lvCourse);
        dbHelper = new DBHelper(this);
        listMonHoc = dbHelper.tatcaMonHoc();
        adapterCourse = new AdapterCourse(this,R.layout.course_item_lv,listMonHoc);
        lvCourse.setAdapter(adapterCourse);
    }

    public void createEvent(){
        btnAddCourse.setOnClickListener(this::onClick);
        btnEditCourse.setOnClickListener(this::onClick);
        btnDeleteCourse.setOnClickListener(this::onClick);
        lvCourse.setOnItemClickListener(this::onItemClick);
        txtTeacherIDCourse.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String GV_ID = txtTeacherIDCourse.getText().toString();
                    boolean check = checkGV(GV_ID);
                    if(!check){
                        Toast.makeText(course.this, "Giảng viên không tồn tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        GiangVienModel giangvien = dbHelper.getGvById(GV_ID);
                        txtTeacherNameCourse.setText(giangvien.getTen_giangvien());
                    }
                }
            }
        });
    }

    private boolean checkGV(String GV_ID){
        GiangVienModel check = dbHelper.getGvById(GV_ID);
        if(check == null) {
            return false;
        } else {
            return true;
        }
    }


    private void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddCourse:
                if (btnAddCourse.getText().toString().equalsIgnoreCase("Thêm"))
                    addMonHoc();
                else
                    resetForm();
                break;
            case R.id.btnEditCourse:
                updateMonHoc();
                break;
            case R.id.btnDeleteCourse:
                deleteMonHoc();
                break;
        }
    }

    private void addMonHoc() {

        if (txtIDCourse.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã môn học", Toast.LENGTH_SHORT).show();
        }
        else if(txtCourseName.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên môn học", Toast.LENGTH_SHORT).show();
        }
        else if (txtSL_SV.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập số lượng sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if (txtTeacherIDCourse.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if (txtTeacherNameCourse.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập tên giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if (dbHelper.monHocIsExist(txtIDCourse.getText().toString())){
            Toast.makeText(this,"Mã môn này đã được sử dụng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themMonHoc(getMonHocInfo())) {
                Toast.makeText(this,"Thêm môn học thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listMonHoc.clear();
                listMonHoc.addAll(dbHelper.tatcaMonHoc());
                adapterCourse.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm môn học thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private MonHocModel getMonHocInfo() {
        String thu = spinnerDay.getSelectedItem().toString();
        String tietHoc = spinnerTietHoc.getSelectedItem().toString();
        String TKB = thu + ", " + tietHoc;

        MonHocModel monHoc = new MonHocModel();
        monHoc.setId_monhoc(Integer.parseInt(txtIDCourse.getText().toString()));
        monHoc.setTen_monhoc(txtCourseName.getText().toString());
        monHoc.setSoluong_sv(Integer.parseInt(txtSL_SV.getText().toString()));
        monHoc.setId_giangvien(Integer.parseInt(txtTeacherIDCourse.getText().toString()));
        monHoc.setTen_giangvien(txtTeacherNameCourse.getText().toString());
        monHoc.setTkb_monhoc(TKB);
        return monHoc;
    }

    private void updateMonHoc() {
        if(txtCourseName.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên môn học", Toast.LENGTH_SHORT).show();
        }
        else if (txtSL_SV.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập số lượng sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if (txtTeacherIDCourse.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if (txtTeacherNameCourse.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập tên giảng viên", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.capnhatMonHoc(getMonHocInfo()))  {
                Toast.makeText(this,"Cập nhật thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listMonHoc.clear();
                listMonHoc.addAll(dbHelper.tatcaMonHoc());
                adapterCourse.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteMonHoc() {
        if(txtIDCourse.getText().toString().equals("")){
            Toast.makeText(this, "Hãy chọn môn học", Toast.LENGTH_SHORT).show();
        }
        else {
            int id = Integer.parseInt(txtIDCourse.getText().toString());
            MonHocModel monHoc = new MonHocModel();
            monHoc.setId_monhoc(id);
            if (dbHelper.xoaMonHoc(monHoc)) {
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listMonHoc.clear();
                listMonHoc.addAll(dbHelper.tatcaMonHoc());
                adapterCourse.notifyDataSetChanged();
            }
        }
    }
    private void resetForm() {
        txtIDCourse.setText("");
        txtCourseName.setText("");
        txtSL_SV.setText("");
        txtTeacherIDCourse.setText("");
        txtTeacherNameCourse.setText("");
        spinnerDay.setSelection(0);
        spinnerTietHoc.setSelection(0);
        btnAddCourse.setText("Thêm");
        btnEditCourse.setEnabled(false);
        btnDeleteCourse.setEnabled(false);
        txtIDCourse.setEnabled(true);
        txtIDCourse.setFocusable(true);
        txtIDCourse.setFocusableInTouchMode(true);
        txtIDCourse.requestFocus();
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MonHocModel monHoc = listMonHoc.get(i);
        txtIDCourse.setText(String.valueOf(monHoc.getId_monhoc()));
        txtCourseName.setText(monHoc.getTen_monhoc());
        txtSL_SV.setText(String.valueOf(monHoc.getSoluong_sv()));
        txtTeacherIDCourse.setText(String.valueOf(monHoc.getId_giangvien()));
        txtTeacherNameCourse.setText(monHoc.getTen_giangvien());
        btnEditCourse.setEnabled(true);
        btnDeleteCourse.setEnabled(true);
        txtIDCourse.setFocusable(false);
        txtIDCourse.setEnabled(false);
        btnAddCourse.setText("Hủy");
    }
}