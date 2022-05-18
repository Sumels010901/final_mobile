package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.Adapter.AdapterCourse;
import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.Models.SinhVien_MonHoc;
import com.example.myapplication.R;

import java.util.ArrayList;

public class sinhvien_viewCourse extends AppCompatActivity {

    private ListView lvCourse;
    private DBHelper dbHelper;
    private AdapterCourse adapterCourse;
    private ArrayList<SinhVien_MonHoc> listMonHocID;
    private ArrayList<MonHocModel> listMonHoc;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_view_course);
        btnBack = findViewById(R.id.btnCourseSVBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String SV_ID = getIntent().getStringExtra("SV_ID");
        lvCourse = findViewById(R.id.lvCourse);
        dbHelper = new DBHelper(sinhvien_viewCourse.this);
        listMonHocID = dbHelper.CacMonHoc_SinhVien(SV_ID);
        listMonHoc = new ArrayList<MonHocModel>();
        for (SinhVien_MonHoc sv_mh:listMonHocID){
            MonHocModel monhoc = null;
            monhoc = dbHelper.getMHById(Integer.toString(sv_mh.getID_MH()));
            listMonHoc.add(monhoc);
        }
        adapterCourse = new AdapterCourse(sinhvien_viewCourse.this, R.layout.course_item_lv, listMonHoc);
        lvCourse.setAdapter(adapterCourse);

    }
}