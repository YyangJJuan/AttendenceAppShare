package com.example.application2;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
    }

    public void GoSecond(View v)
    {
        //intent设置要跳转的页面
        Intent intent = new Intent(this,ForgetPwd.class);
                //进行跳转
        startActivity(intent);
    }

    public void GoMain(View v)
    {
        //intent设置要跳转的页面
        Intent intent = new Intent(this,HomePage.class);
        //进行跳转
        startActivity(intent);
    }

}