package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Adapter.SinhVienAdapter;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Calendar;

public class student extends AppCompatActivity {
    private EditText editMSSV,editTenSV,editDOB;
    private RadioGroup rdoBtnSex;
    private RadioButton radioNam, radioNu;
    private Button btnAdd,btnUpdate, btnDelete;
    private ListView lvSV;
    private DBHelper DBHelper;
    private SinhVienAdapter adapter;
    private ArrayList<SinhVienModel> listSV;


    Button btnStudentBack;
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
        init();
        createEvent();
        btnStudentBack = findViewById(R.id.btnStudentBack);
        btnStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



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

    public void createEvent(){
        btnAdd.setOnClickListener(this::onClick);
        btnUpdate.setOnClickListener(this::onClick);
        btnDelete.setOnClickListener(this::onClick);
        lvSV.setOnItemClickListener(this::onItemClick);
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SinhVienModel sinhVien = listSV.get(i);
        editMSSV.setText(String.valueOf(sinhVien.getID()));
        editTenSV.setText((sinhVien.getName()));
        if(sinhVien.isSex())
            radioNam.setChecked(true);
        else radioNu.setChecked(true);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        editMSSV.setFocusable(false);
        editMSSV.setEnabled(false);
        btnAdd.setText("Hủy");
    }

    public void onClick (View v ){
        switch (v.getId()){
            case R.id.btnAdd:
                if (btnAdd.getText().toString().equalsIgnoreCase("Thêm"))
                    addSV();
                else
                    resetForm();
                break;
            case R.id.btnUpdate:
                updateSV();
                break;
            case R.id.btnDelete:
                deleteSV();
                break;
        }
    }

    private void deleteSV() {
    }

    private void updateSV() {
    }



    private void addSV() {
        if (editMSSV.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập MSSV", Toast.LENGTH_SHORT).show();
        }
        else if(editTenSV.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên sv", Toast.LENGTH_SHORT).show();
        }
        else if (DBHelper.getSvById(editMSSV.getText().toString())!=null){
            Toast.makeText(this,"Mã số này đã được sử dụng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(DBHelper.themSinhVien(getSvInfo())) {
                Toast.makeText(this,"Thêm sv thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listSV.clear();
                listSV.addAll(DBHelper.getAllSv());
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm sv thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private SinhVienModel getSvInfo() {
        SinhVienModel sinhVien = new SinhVienModel();
        sinhVien.setName(editTenSV.getText().toString());
        sinhVien.setID(Integer.parseInt(editMSSV.getText().toString()));
        sinhVien.setSex(rdoBtnSex.getCheckedRadioButtonId()==radioNam.getId());
        sinhVien.setDob(DoB);
        return sinhVien;

    }

    public void init(){
        editMSSV = findViewById(R.id.editID);
        editTenSV = findViewById(R.id.editTen);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        rdoBtnSex = findViewById(R.id.rdgBtnSex);
        radioNu = findViewById(R.id.radNu);
        radioNam = findViewById(R.id.radNam);
        lvSV = findViewById(R.id.lvSV);
        DBHelper = new DBHelper(this);
        listSV = DBHelper.getAllSv();
        adapter = new SinhVienAdapter(this,R.layout.sv_item_lv,listSV);
        lvSV.setAdapter(adapter);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
    }

    // Empty all textview, focus to MaSoSinhVien
    private void resetForm() {
        editTenSV.setText("");
        editMSSV.setText("");
        btnAdd.setText("Thêm");
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        radioNu.setChecked(true);
        editMSSV.setEnabled(true);
        editMSSV.setFocusable(true);
        editMSSV.setFocusableInTouchMode(true);
    }
}