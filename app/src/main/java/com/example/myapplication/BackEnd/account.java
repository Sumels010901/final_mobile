package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterCourse;
import com.example.myapplication.Models.GiangVienModel;
import com.example.myapplication.R;
import com.example.myapplication.Models.TaiKhoanModel;

import java.util.ArrayList;

public class account extends AppCompatActivity {
    private EditText txtIDStudent, txtPass;
    private Spinner spinnerAccType;
    private Button btnAddAccount,btnDeleteAccount,btnEditAccount,btnCourseBack;
    private ListView lvAccount;
    private DBHelper dbHelper;
    //private Ada adapterAccount;
    private ArrayList<TaiKhoanModel> listAccount;
    String[] acc = {"Giáo viên","Học sinh"};
    Spinner j_spinner; //combobox của thứ
    String accountType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        init();
        //createEvent();
        btnCourseBack = findViewById(R.id.btnCourseBack);
        btnCourseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        j_spinner = findViewById(R.id.spinnerAccountType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, acc);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spinner.setAdapter(adapter);
        j_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                accountType = j_spinner.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
    public void init(){
        txtIDStudent = findViewById(R.id.txtsvID);
        txtPass = findViewById(R.id.txtPassword);
        btnAddAccount = findViewById(R.id.btnAddAccount);
        btnEditAccount = findViewById(R.id.btnEditAccount);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        spinnerAccType = findViewById(R.id.spinnerAccountType);
        btnEditAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);

        lvAccount = findViewById(R.id.lvAccount);
        dbHelper = new DBHelper(this);
        listAccount = dbHelper.tatcaTaiKhoan();
        //adapterCourse = new AdapterCourse(this,R.layout.course_item_lv,listMonHoc);
        //lvCourse.setAdapter(adapterCourse);
    }


}