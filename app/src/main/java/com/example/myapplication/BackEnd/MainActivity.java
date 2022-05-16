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
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.txtUsername);
        edtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = edtPassword.getText().toString();
                String username = edtUsername.getText().toString();
                if(username.equals("admin") && password.equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login success!", Toast.LENGTH_SHORT).show();
                    switchActivities();
                } else {
                    Toast.makeText(MainActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void switchActivities() {
        Intent toHome = new Intent(this, home.class);
        startActivity(toHome);
    }
}