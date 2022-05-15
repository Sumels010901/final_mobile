package com.example.myapplication.TempFiles;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.SinhVienAdapter;
import com.example.myapplication.BackEnd.DBHelper;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;

public class SInhVienActivity extends AppCompatActivity {
    private EditText editMSSV,editTenSV,editDOB;
    private RadioButton rdoBtnSex;
    private Button btnAdd,btnUpdate, btnDelete;
    private ListView lvSV;
    private DBHelper sqliteSV;
    private SinhVienAdapter adapter;
    private ArrayList<SinhVienModel> listSV;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    String DoB = ""; // Trả về giá trị ngày tháng năm theo kiểu chuỗi,
    // đã tạo event để gán nên chỉ cần lấy cho vào db khi nhấn button thoi
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_student);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
    }
    //Hiển thị box chọn ngày tháng
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
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
    // Thiết lập kiểu hiển thị của tháng


    public void init(){
        editMSSV = findViewById(R.id.);
        editTenSV = findViewById(R.id.editTenSV);
        editLop = findViewById(R.id.editLop);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvSV = findViewById(R.id.lvSV);
        sqliteSV = new SqliteSV(this);
        listSV = sqliteSV.getAllSV();
        adapter = new SinhVienAdapter(this,R.layout.sv_item_lv,listSV);
        lvSV.setAdapter(adapter);


    }
}
