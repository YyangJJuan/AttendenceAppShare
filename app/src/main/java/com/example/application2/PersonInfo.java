package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PersonInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        getSupportActionBar().setTitle("个人信息");
    }
}