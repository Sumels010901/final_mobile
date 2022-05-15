package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.example.myapplication.R;

import java.util.Calendar;

public class student extends AppCompatActivity {
    Button btnStudentBack;
    RadioGroup rdgroup;

    //-----------------------------------Cho datetime picker-----------------------------------------
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    String DoB = ""; // Trả về giá trị ngày tháng năm theo kiểu chuỗi,
    // đã tạo event để gán nên chỉ cần lấy cho vào db khi nhấn button thoi
    //------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        btnStudentBack = findViewById(R.id.btnStudentBack);
        btnStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rdgroup = findViewById(R.id.radioGroup1);
        //-----------------------------------Cho datetime picker-----------------------------------------
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //------------------------------------------------------------------------------------------------
    }
    public void AddStudent()
    {
        int radid = rdgroup.getCheckedRadioButtonId();
        String gioitinh = "";

        if(radid == R.id.radNam){
            gioitinh = "Nam";
        }
        else
        {
            gioitinh = "Nữ";
        }
    }
    //-----------------------------------Cho datetime picker-----------------------------------------

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    //Khởi tạo khung chọn ngày
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
                DoB = makeDateString(day, month, year);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    //trả về kiểu chuỗi của ngày tháng năm đã chọn
    private String makeDateString(int day, int month, int year)
    {
        return day + "/" + month + "/" + year;
    }
    //------------------------------------------------------------------------------------------------

}