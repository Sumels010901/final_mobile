package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

public class sinhvien_home extends AppCompatActivity {
    Button btnBack, btnViewInfo, btnViewCourse, btnViewScore, btnViewSchedule;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_home);
        String SV_ID = getIntent().getStringExtra("SV_ID");

        btnBack = findViewById(R.id.btnSVback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnViewInfo = findViewById(R.id.btnViewStudentInfo);
        btnViewInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInfoView = new Intent(sinhvien_home.this, sinhvien_viewInfo.class);
                toInfoView.putExtra("SV_ID", SV_ID);
                startActivity(toInfoView);
            }
        });
        btnViewCourse = findViewById(R.id.btnViewStudentCourse);
        btnViewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCourseView = new Intent(sinhvien_home.this, sinhvien_viewCourse.class);
                startActivity(toCourseView);
            }
        });
        btnViewScore = findViewById(R.id.btnViewStudentScore);
        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCourseView = new Intent(sinhvien_home.this, sinhvien_viewScore.class);
                startActivity(toCourseView);
            }
        });
        btnViewSchedule = findViewById(R.id.btnViewStudentSchedule);
        btnViewSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toCourseView = new Intent(sinhvien_home.this, sinhvien_viewSchedule.class);
                startActivity(toCourseView);
            }
        });
    }
}