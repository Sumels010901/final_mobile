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

import com.example.myapplication.Adapter.TaiKhoanAdapter;
import com.example.myapplication.Models.TaiKhoanModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class taikhoan extends AppCompatActivity {
    private EditText txtUsername, txtPass;
    private Spinner spinnerAccType;
    private TaiKhoanAdapter adapterTK;
    private Button btnAddAccount,btnDeleteAccount,btnEditAccount,btnAccountBack;
    private ListView lvAccount;
    private DBHelper dbHelper;
    private ArrayList<TaiKhoanModel> listAccount;
    String[] acc = {"Giáo viên","Học sinh"};
    Spinner j_spinner; //combobox của thứ
    String accountType = "";
    private int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        init();
        createEvent();

        btnAccountBack.setOnClickListener(new View.OnClickListener() {
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
        txtUsername = findViewById(R.id.txtUserNameA);
        txtPass = findViewById(R.id.txtPasswordA);

        btnAddAccount = findViewById(R.id.btnAddAccount);
        btnEditAccount = findViewById(R.id.btnEditAccount);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        spinnerAccType = findViewById(R.id.spinnerAccountType);
        btnAccountBack = findViewById(R.id.btnAccountBack);
        btnEditAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);

        lvAccount = findViewById(R.id.lvAccount);
        dbHelper = new DBHelper(taikhoan.this);
        listAccount = dbHelper.tatcaTaiKhoan();
        adapterTK = new TaiKhoanAdapter(this,R.layout.taikhoan_item_lv, listAccount);
        lvAccount.setAdapter(adapterTK);
    }
    public void createEvent(){
        btnAddAccount.setOnClickListener(this::onClick);
        btnEditAccount.setOnClickListener(this::onClick);
        btnDeleteAccount.setOnClickListener(this::onClick);
        lvAccount.setOnItemClickListener(this::onItemClick);
    }

    private void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddAccount:
                if (btnAddAccount.getText().toString().equalsIgnoreCase("Thêm"))
                    addAccount();
                else
                    resetForm();
                break;
            case R.id.btnEditAccount:
                updateAccount();
                break;
            case R.id.btnDeleteAccount:
                deleteAccount();
                break;
        }
    }

    private void addAccount() {
        if (txtUsername.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã sinh viên", Toast.LENGTH_SHORT).show();
        }
        else if(txtPass.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập password", Toast.LENGTH_SHORT).show();
        }
        else if (dbHelper.getTaiKhoan(txtUsername.getText().toString())!=null){
            Toast.makeText(this,"Mã số này đã được sử dụng", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.themTaiKhoan(getTKInfo())) {
                Toast.makeText(this,"Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listAccount.clear();
                listAccount.addAll(dbHelper.tatcaTaiKhoan());
                adapterTK.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Thêm tài khoản thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private TaiKhoanModel getTKInfo() {
        String acctype = spinnerAccType.getSelectedItem().toString();
        if(acctype.equals("Giáo viên")){
            type = 2;
        } else {type=1;}
        TaiKhoanModel taikhoan = new TaiKhoanModel();
        taikhoan.setSvID(Integer.parseInt(txtUsername.getText().toString()));
        taikhoan.setPassword(txtPass.getText().toString());
        taikhoan.setAccType(type);
        System.out.println(taikhoan.toString());
        return taikhoan;
    }

    private void updateAccount() {
        if (txtUsername.getText().toString().equals("")) {
            Toast.makeText(this,"Hãy nhập mã giảng viên", Toast.LENGTH_SHORT).show();
        }
        else if(txtPass.getText().toString().equals("")){
            Toast.makeText(this,"Hãy nhập tên giảng viên", Toast.LENGTH_SHORT).show();
        }
        else {
            if(dbHelper.capNhatTaiKhoan((getTKInfo()))){
                Toast.makeText(this,"Cập nhật thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listAccount.clear();
                listAccount.addAll(dbHelper.tatcaTaiKhoan());
                adapterTK.notifyDataSetChanged();
            }
            else {
                Toast.makeText(this,"Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void deleteAccount() {
        if(txtUsername.getText().toString().equals("")){
            Toast.makeText(this, "Hãy chọn tài khoản", Toast.LENGTH_SHORT).show();
        }
        else {
            if (dbHelper.xoaTaiKhoan(txtUsername.getText().toString())) {
                Toast.makeText(this,"Xóa thành công", Toast.LENGTH_SHORT).show();
                resetForm();
                listAccount.clear();
                listAccount.addAll(dbHelper.tatcaTaiKhoan());
                adapterTK.notifyDataSetChanged();
            }
        }
    }
    private void resetForm() {
        txtUsername.setText("");
        txtPass.setText("");
        btnAddAccount.setText("Thêm");
        btnEditAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);
        txtUsername.setEnabled(true);
        txtUsername.setFocusable(true);
        txtUsername.setFocusableInTouchMode(true);
        txtUsername.requestFocus();
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TaiKhoanModel taikhoan = listAccount.get(i);
        txtUsername.setText(String.valueOf(taikhoan.getSvID()));
        txtPass.setText(taikhoan.getPassword());
        btnEditAccount.setEnabled(true);
        btnDeleteAccount.setEnabled(true);
        txtUsername.setFocusable(false);
        txtUsername.setEnabled(false);
        btnAddAccount.setText("Hủy");
    }
}