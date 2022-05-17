package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

public class sinhvien_viewInfo extends AppCompatActivity {
    TextView txtID, txtName, txtNgaysinh, txtGioitinh;
    Button btnBack;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_view_info);

        String SV_ID = getIntent().getStringExtra("SV_ID");
        dbHelper = new DBHelper(this);
        txtID = findViewById(R.id.txtMaSV);
        txtName = findViewById(R.id.txtTenSV);
        txtGioitinh = findViewById(R.id.txtGioiTinh);
        txtNgaysinh = findViewById(R.id.txtNgaySinh);
        btnBack = findViewById(R.id.btnInfoSVBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SinhVienModel sinhvien = new SinhVienModel();
        sinhvien = dbHelper.getSvById(SV_ID);

        txtID.setText("Mã sinh viên: " + sinhvien.getID());
        txtName.setText("Tên sinh viên: " + sinhvien.getName());
        txtNgaysinh.setText("Ngày sinh: " + sinhvien.getDob());
        String gioitinh_txt = "";
        if (sinhvien.isSex()) {
            gioitinh_txt = "Nam";
        } else {
            gioitinh_txt = "Nữ";
        }
        txtGioitinh.setText("Giới tính: " + gioitinh_txt);
    }
}