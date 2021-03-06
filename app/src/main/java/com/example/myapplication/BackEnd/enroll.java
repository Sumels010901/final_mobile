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
    private EditText txt_SVID, txt_MHID, txt_EnrollID;
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
        txt_EnrollID = findViewById(R.id.txtEnrollID);
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
                if (btnAdd.getText().toString().equalsIgnoreCase("Th??m"))
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
            Toast.makeText(this,"H??y nh???p m?? m??n h???c", Toast.LENGTH_SHORT).show();
        }
        else if(txt_SVID.getText().toString().equals("")){
            Toast.makeText(this,"H??y nh???p m?? sinh vi??n", Toast.LENGTH_SHORT).show();
        }
        else if(txt_EnrollID.getText().toString().equals("")){
            Toast.makeText(this,"H??y nh???p m?? l?????t", Toast.LENGTH_SHORT).show();
        }
        else if(dbHelper.getSvById(txt_SVID.getText().toString())==null){
            Toast.makeText(this,"Sinh vi??n kh??ng t???n t???i", Toast.LENGTH_SHORT).show();
        }
        else if(dbHelper.getSvById(txt_MHID.getText().toString())==null){
            Toast.makeText(this,"M??n h???c kh??ng t???n t???i", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themLuotHoc(getEnrollInfo())) {
                Toast.makeText(this,"Th??m l?????t h???c th??nh c??ng", Toast.LENGTH_SHORT).show();
                resetForm();
                listEnroll.clear();
                listEnroll.addAll(dbHelper.getAllEnroll());
                adapterEnroll.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Th??m m??n h???c th???t b???i", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private SinhVien_MonHoc getEnrollInfo() {
        SinhVien_MonHoc svmh = new SinhVien_MonHoc();
        svmh.setENROLL_ID(Integer.parseInt(txt_EnrollID.getText().toString()));
        svmh.setID_SV(Integer.parseInt(txt_SVID.getText().toString()));
        svmh.setID_MH(Integer.parseInt(txt_MHID.getText().toString()));
        return svmh;
    }

    private void deleteMonHoc() {
        if(txt_SVID.getText().toString().equals("") || txt_MHID.getText().toString().equals("")){
            Toast.makeText(this, "H??y ch???n sinh vi??n/ m??n h???c", Toast.LENGTH_SHORT).show();
        }
        else {
            SinhVien_MonHoc svmh = getEnrollInfo();
            if (dbHelper.xoaLuotHoc(svmh)) {
                Toast.makeText(this,"X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
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
        txt_EnrollID.setText("");
        btnAdd.setText("Th??m");
        btnDelete.setEnabled(false);
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SinhVien_MonHoc svmh = listEnroll.get(i);
        txt_EnrollID.setText(String.valueOf(svmh.getENROLL_ID()));
        txt_SVID.setText(String.valueOf(svmh.getID_SV()));
        txt_MHID.setText(String.valueOf(svmh.getID_MH()));
        btnDelete.setEnabled(true);
        btnAdd.setText("H???y");
    }
}