package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.MonHocModel;
import com.example.myapplication.Models.SinhVienModel;

public class home extends AppCompatActivity {
    Button btnMngStudent, btnMngScore, btnMngCourse, btnMngTeacher, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnMngStudent = findViewById(R.id.btnToStudent);
        btnMngCourse = findViewById(R.id.btnToCourse);
        btnMngScore = findViewById(R.id.btnToScore);
        btnMngTeacher = findViewById(R.id.btnToTeacher);
        btnBack = findViewById(R.id.btnback);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnMngStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toStudentView = new Intent(home.this, student.class);
                startActivity(toStudentView);
            }
        });
        btnMngCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCourseView = new Intent(home.this, course.class);
                startActivity(toCourseView);
            }
        });
        btnMngScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toScoreView = new Intent(home.this, score.class);
                startActivity(toScoreView);
            }
        });
        btnMngTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toTeacherView = new Intent(home.this, teacher.class);
                startActivity(toTeacherView);
            }
        });
    }
}