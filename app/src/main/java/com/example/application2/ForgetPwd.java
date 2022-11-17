package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ForgetPwd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        getSupportActionBar().setTitle("找回密码");
    }
}