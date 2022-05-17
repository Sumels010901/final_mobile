package com.example.myapplication.BackEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Models.BangDiemModel;
import com.example.myapplication.Models.SinhVienModel;
import com.example.myapplication.Models.TaiKhoanModel;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.txtUsername);
        edtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = edtPassword.getText().toString();
                String username = edtUsername.getText().toString();

                TaiKhoanModel account = dbHelper.getTaiKhoan(username);
                int loai_tk = account.getAccType();

                if(username.equals("admin") && password.equals("admin")) {
                    Toast.makeText(MainActivity.this, "Admin login success!", Toast.LENGTH_SHORT).show();
                    Intent toHome = new Intent(MainActivity.this, home.class);
                    startActivity(toHome);
                } else if (loai_tk == 1) { // Sinh Vien
                    if (password.equals(account.getPassword())){
                        Toast.makeText(MainActivity.this, "Student login success!", Toast.LENGTH_SHORT).show();
                        Intent toSVHome = new Intent(MainActivity.this, sinhvien_home.class);
                        toSVHome.putExtra("SV_ID", username);
                        startActivity(toSVHome);
                    } else
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                } else if (loai_tk == 2) { //Giang Vien
                    if (password.equals(account.getPassword())){
                        Toast.makeText(MainActivity.this, "Teacher login success!", Toast.LENGTH_SHORT).show();
                        Intent toGVHome = new Intent(MainActivity.this, sinhvien_home.class);
                        toGVHome.putExtra("GV_ID", username);
                        startActivity(toGVHome);
                    } else
                        Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}