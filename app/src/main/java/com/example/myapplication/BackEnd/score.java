package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.myapplication.Adapter.ScoreAdapter;
import com.example.myapplication.Models.BangDiemModel;
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
        btnAddScore = findViewById(R.id.btnAddScore);
        btnEditScore = findViewById(R.id.btnEditScore);
        btnEditScore.setEnabled(false);
        btnDeleteScore = findViewById(R.id.btnDeleteScore);
        btnDeleteScore.setEnabled(false);
        lvScore = findViewById(R.id.lvScore);
        dbHelper = new DBHelper(this);
        listBangDiem = dbHelper.getAllBangDiem();
        adapter = new ScoreAdapter(this,R.layout.score_item_lv,listBangDiem);
        lvScore.setAdapter(adapter);

        btnScoreBack = findViewById(R.id.btnScoreBack);
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
        int id_SV = Integer.parseInt(editStudentID.getText().toString());
        int id_MH = Integer.parseInt(editIDCourse.getText().toString());
        if (editStudentID.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập thông tin sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if(editIDCourse.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập thông tin môn học", Toast.LENGTH_SHORT).show();
        }
        else if (editScore.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập điểm cho sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if(dbHelper.getSvById(id_SV+"") == null)
            Toast.makeText(this,"Sinh viên không tồn tại", Toast.LENGTH_SHORT).show();

        else if(!dbHelper.monHocIsExist(id_MH+""))
            Toast.makeText(this,"Môn học không tồn tại", Toast.LENGTH_SHORT).show();

        else {
            if(dbHelper.themBangDiem(getBangDiem())) {
                Toast.makeText(this,"Thêm bảng điểm thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listBangDiem.clear();
                listBangDiem.addAll(dbHelper.getAllBangDiem());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm bảng điểm thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void updateScore() {
        if (editStudentID.getText().toString().equals("")) {
            Toast.makeText(this,"Chọn sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if(editIDCourse.getText().toString().equals(""))   {
            Toast.makeText(this,"Hãy nhập thông tin môn học", Toast.LENGTH_SHORT).show();
        }
        else if (editScore.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập điểm cho sinh viên", Toast.LENGTH_SHORT).show();
        }

        else {
            if(dbHelper.capNhatBangDiem(getBangDiem())){
                Toast.makeText(this,"Cập nhật thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listBangDiem.clear();
                listBangDiem.addAll(dbHelper.getAllBangDiem());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void deleteScore() {
        if(editIDCourse.getText().toString().equals("")){
            Toast.makeText(this, "Vui lòng chọn điểm muốn xóa", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbHelper.xoaBangDiem(Integer.parseInt(editStudentID.getText().toString()),
                    Integer.parseInt(editIDCourse.getText().toString()))) {
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listBangDiem.clear();
                listBangDiem.addAll(dbHelper.getAllBangDiem());
                adapter.notifyDataSetChanged();
            }
        }
    }
    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        BangDiemModel bangDiem = listBangDiem.get(i);
        editStudentID.setText(String.valueOf(bangDiem.getID_SV()));
        editIDCourse.setText(String.valueOf(bangDiem.getID_MH()));
        editScore.setText(String.valueOf(bangDiem.getDiem()));
        btnEditScore.setEnabled(true);
        btnDeleteScore.setEnabled(true);
        editStudentID.setFocusable(false);
        editStudentID.setEnabled(false);
        editIDCourse.setFocusable(false);
        editIDCourse.setEnabled(false);
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
        editIDCourse.setEnabled(true);
        editIDCourse.setFocusable(true);
        editIDCourse.setFocusableInTouchMode(true);
        editStudentID.requestFocus();
    }
}