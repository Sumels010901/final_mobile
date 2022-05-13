package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseActivity extends AppCompatActivity {
    String[] ngay = {"thu 2", "thu 3","thu 4","thu 5","thu 6","thu 7"};
    String[] tiet = {"tiet 1", "tiet 2", "tiet 3", "tiet 4", "tiet 5", "tiet 6"
            , "tiet 7", "tiet 8", "tiet 9", "tiet 10", "tiet 11"};
    Spinner j_spinner; //combobox của thứ
    Spinner i_spinner; //combobox của tiết
    String TKB_MH1 = "";
    String TKB_MH2 = "";
    String TKB_MH = ""; // Chuỗi TKB_MH để cho vào db nè
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
