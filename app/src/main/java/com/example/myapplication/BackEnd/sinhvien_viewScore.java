package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.Adapter.ScoreAdapter;
import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class sinhvien_viewScore extends AppCompatActivity {
    private ListView lvScore;
    private Button btnBack;
    private ScoreAdapter adapterScore;
    private ArrayList<BangDiemModel> listBangdiem;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinhvien_view_score);
        btnBack = findViewById(R.id.btnScoreSVBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String SV_ID = getIntent().getStringExtra("SV_ID");
        SinhVienModel sinhvien = dbHelper.getSvById(SV_ID);
        lvScore = findViewById(R.id.lvScore);
        dbHelper = new DBHelper(this);
        listBangdiem = dbHelper.BangDiem_SV(sinhvien);
        adapterScore = new ScoreAdapter(this, R.layout.score_item_lv, listBangdiem);
        lvScore.setAdapter(adapterScore);

    }
}