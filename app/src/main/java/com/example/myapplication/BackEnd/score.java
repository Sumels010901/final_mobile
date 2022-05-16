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
import com.example.myapplication.Adapter.ScoreAdapter;
import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class score extends AppCompatActivity {
    private EditText editStudentID,editIDCourse,editScore;
    private Button btnAddScore,btnDeleteScore,btnEditScore;
    private ListView lvScore;
    private DBHelper dbHelper;
    private ScoreAdapter adapter;
    private ArrayList<BangDiemModel> listBangDiem;


    Button btnScoreBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        init();
        createEvent();

        btnScoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void init(){


        editStudentID = findViewById(R.id.editStudentID);
        editIDCourse = findViewById(R.id.editIDCourse);
        editScore = findViewById(R.id.editScore);
        btnAddScore = findViewById(R.id.btnAddTeacher);
        btnEditScore = findViewById(R.id.btnEditTeacher);
        btnEditScore.setEnabled(false);
        btnDeleteScore = findViewById(R.id.btnDeleteTeacher);
        btnDeleteScore.setEnabled(false);
        lvScore = findViewById(R.id.lvScore);
        dbHelper = new DBHelper(this);
        listBangDiem = dbHelper.getAllBangDiem();
        adapter = new ScoreAdapter(this,R.layout.score_item_lv,listBangDiem);
        lvScore.setAdapter(adapter);
    }
    public void createEvent(){
        btnAddScore.setOnClickListener(this::onClick);
        btnEditScore.setOnClickListener(this::onClick);
        btnDeleteScore.setOnClickListener(this::onClick);
        lvScore.setOnItemClickListener(this::onItemClick);
    }

    private void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAddScore:
                if (btnAddScore.getText().toString().equalsIgnoreCase("Thêm"))
                    addScore();
                else
                    resetForm();
                break;
            case R.id.btnEditScore:
                updateScore();
                break;
            case R.id.btnDeleteScore:
                deleteScore();
                break;
        }
    }

    private void addScore() {
        if (editStudentID.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập thông tin sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if(editIDCourse.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập thông tin môn học", Toast.LENGTH_SHORT).show();
        }
        else if (editScore.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập điểm cho sinh viên", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themBangDiem(getBangDiem())) {
                Toast.makeText(this,"Thêm gv thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listBangDiem.clear();
                listBangDiem.addAll(dbHelper.getAllBangDiem());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm gv thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void updateScore() {
    }
    private void deleteScore() {
    }
    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BangDiemModel bangDiem = listBangDiem.get(i);
        editIDCourse.setText(String.valueOf(bangDiem.getID_MH()));
        editScore.setText(String.valueOf(bangDiem.getDiem()));
        btnEditScore.setEnabled(true);
        btnDeleteScore.setEnabled(true);
        editStudentID.setFocusable(false);
        editStudentID.setEnabled(false);
        btnAddScore.setText("Hủy");
    }

    private BangDiemModel getBangDiem() {
        BangDiemModel bangdiem = new BangDiemModel();
        bangdiem.setID_SV(Integer.parseInt(editStudentID.getText().toString()));
        bangdiem.setID_MH(Integer.parseInt(editIDCourse.getText().toString()));
        bangdiem.setDiem(Float.parseFloat(editScore.getText().toString()));

        return bangdiem;
    }

    private void resetForm() {
        editIDCourse.setText("");
        editScore.setText("");
        editStudentID.setText("");
        btnAddScore.setText("Thêm");
        btnEditScore.setEnabled(false);
        btnDeleteScore.setEnabled(false);
        editStudentID.setEnabled(true);
        editStudentID.setFocusable(true);
        editStudentID.setFocusableInTouchMode(true);
        editStudentID.requestFocus();
    }
}