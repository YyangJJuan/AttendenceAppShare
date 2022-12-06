package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ManageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_group);
        getSupportActionBar().setTitle("群管理");
    }
}