package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.EnrollAdapter;
import com.example.myapplication.Models.SinhVien_MonHoc;
import com.example.myapplication.R;

import java.util.ArrayList;

public class enroll extends AppCompatActivity {
    private Button btnBack, btnAdd, btnDelete;
    private EditText txt_SVID, txt_MHID;
    private ListView lvEnroll;
    private DBHelper dbHelper;
    private EnrollAdapter adapterEnroll;
    private ArrayList<SinhVien_MonHoc> listEnroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        init();
        createEvent();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void init(){
        txt_MHID = findViewById(R.id.txtMH_SV);
        txt_SVID = findViewById(R.id.txtSV_MH);
        btnBack = findViewById(R.id.btnEnrollBack);
        btnAdd = findViewById(R.id.btnAddSV_MH);
        btnDelete = findViewById(R.id.btnDeleteSV_MH);

        lvEnroll = findViewById(R.id.lvEnroll);
        dbHelper = new DBHelper(this);
        listEnroll = dbHelper.getAllEnroll();
        adapterEnroll = new EnrollAdapter(this,R.layout.course_item_lv, listEnroll);
        lvEnroll.setAdapter(adapterEnroll);
    }

    public void createEvent(){
        btnAdd.setOnClickListener(this::onClick);
        btnDelete.setOnClickListener(this::onClick);
        lvEnroll.setOnItemClickListener(this::onItemClick);
    }
    private void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddSV_MH:
                if (btnAdd.getText().toString().equalsIgnoreCase("Thêm"))
                    addMonHoc();
                else
                    resetForm();
                break;
            case R.id.btnDeleteSV_MH:
                deleteMonHoc();
                break;
        }
    }
    private void addMonHoc() {

        if (txt_MHID.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã môn học", Toast.LENGTH_SHORT).show();
        }
        else if(txt_SVID.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập mã sinh viên", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themLuotHoc(getEnrollInfo())) {
                Toast.makeText(this,"Thêm lượt học thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listEnroll.clear();
                listEnroll.addAll(dbHelper.getAllEnroll());
                adapterEnroll.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm môn học thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private SinhVien_MonHoc getEnrollInfo() {
        SinhVien_MonHoc svmh = new SinhVien_MonHoc();
        svmh.setID_SV(Integer.parseInt(txt_SVID.getText().toString()));
        svmh.setID_MH(Integer.parseInt(txt_MHID.getText().toString()));
        return svmh;
    }

    private void deleteMonHoc() {
        if(txt_SVID.getText().toString().equals("") || txt_MHID.getText().toString().equals("")){
            Toast.makeText(this, "Hãy chọn sinh viên/ môn học", Toast.LENGTH_SHORT).show();
        }
        else {
            int id = Integer.parseInt(txt_SVID.getText().toString());
            SinhVien_MonHoc svmh = new SinhVien_MonHoc();
            if (dbHelper.xoaLuotHoc(svmh)) {
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listEnroll.clear();
                listEnroll.addAll(dbHelper.getAllEnroll());
                adapterEnroll.notifyDataSetChanged();
            }
        }
    }
    private void resetForm() {
        txt_SVID.setText("");
        txt_MHID.setText("");
        btnAdd.setText("Thêm");
        btnDelete.setEnabled(false);
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SinhVien_MonHoc svmh = listEnroll.get(i);
        txt_SVID.setText(String.valueOf(svmh.getID_SV()));
        txt_MHID.setText(String.valueOf(svmh.getID_MH()));
        btnDelete.setEnabled(true);
        btnAdd.setText("Hủy");
    }
}