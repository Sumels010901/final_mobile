package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.R;

public class course extends AppCompatActivity {
    Button btnCourseBack;
    String[] ngay = {"thứ 2", "thứ 3","thứ 4","thứ 5","thứ 6","thứ 7"};
    String[] tiet = {"tiết 1", "tiết 2", "tiết 3", "tiết 4", "tiết 5", "tiết 6"
            , "tiết 7", "tiết 8", "tiết 9", "tiết 10", "tiết 11"};
    Spinner j_spinner; //combobox của thứ
    Spinner i_spinner; //combobox của tiết
    String TKB_MH1 = "";
    String TKB_MH2 = "";
    String TKB_MH = ""; // Chuỗi TKB_MH để cho vào db nè
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        btnCourseBack = findViewById(R.id.btnCourseBack);
        btnCourseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //------------------------------------Cho combobox Thời khóa biểu-----------------------------------------
        j_spinner = findViewById(R.id.spinnerDay);
        i_spinner = findViewById(R.id.spinnerTietHoc);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ngay);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiet);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spinner.setAdapter(adapter);
        j_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                TKB_MH1 = j_spinner.getSelectedItem().toString() + " ";
                TKB_MH2 =  i_spinner.getSelectedItem().toString();
                TKB_MH = TKB_MH1 + TKB_MH2;
                //j_spinner_selected.setText(j_spinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        i_spinner.setAdapter(adapter2);
        i_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                TKB_MH1 = j_spinner.getSelectedItem().toString() + " ";
                TKB_MH2 = i_spinner.getSelectedItem().toString();
                TKB_MH = TKB_MH1 + TKB_MH2;
                //i_spinner_selected.setText(i_spinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //------------------------------------------------------------------------------------------------

    }
}