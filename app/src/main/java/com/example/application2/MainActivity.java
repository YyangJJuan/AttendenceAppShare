package com.example.application2;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private EditText name;
private  EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       getSupportActionBar().setTitle("登录");
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
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        String n = name.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        //if (n.equals("") || pwd.equals(""))
        //{
            //Looper.prepare();
            //Toast toast = Toast.makeText(MainActivity.this,"输入不能为空",Toast.LENGTH_SHORT);
            //toast.show();
            //Looper.loop();
       // }
        UserDao ud = new UserDao();
        Boolean result = ud.login(n,pwd);
        //if (result == false)
        //{
           // Looper.prepare();
           // Toast toast = Toast.makeText(MainActivity.this,"用户名不存在或者密码错误！",Toast.LENGTH_SHORT);
           // toast.show();
            //Looper.loop();
        //}else {
           // Looper.prepare();
           // Toast toast = Toast.makeText(MainActivity.this,"登录成功！",Toast.LENGTH_SHORT);
            //toast.show();
            //intent设置要跳转的页面
        if (result == true)
        {
            Intent intent = new Intent(this,HomePage.class);
            //进行跳转
            startActivity(intent);
        }

            //Looper.loop();
        //}

    }

}