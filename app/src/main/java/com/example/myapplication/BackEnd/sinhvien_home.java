package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.Models.TaiKhoanModel;
import com.example.myapplication.R;

public class sinhvien_home extends AppCompatActivity {
    Button btnBack, btnViewInfo, btnViewCourse, btnViewScore;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_home);
        String ID = getIntent().getStringExtra("SV_ID");
        System.out.println(ID);
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
                try {
                    Intent toInfoView = new Intent(sinhvien_home.this, sinhvien_viewInfo.class);
                    toInfoView.putExtra("SV_ID", ID);
                    startActivity(toInfoView);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        btnViewCourse = findViewById(R.id.btnViewStudentCourse);
        btnViewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCourseView = new Intent(sinhvien_home.this, sinhvien_viewCourse.class);
                toCourseView.putExtra("SV_ID", ID);
                startActivity(toCourseView);
            }
        });
        btnViewScore = findViewById(R.id.btnViewStudentScore);
        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toScoreView = new Intent(sinhvien_home.this, sinhvien_viewScore.class);
                toScoreView.putExtra("SV_ID", ID);
                startActivity(toScoreView);
            }
        });
    }
}