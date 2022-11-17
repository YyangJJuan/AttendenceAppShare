package com.example.application2;

import static java.util.Arrays.asList;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.*;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
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
    private  final MainActivity mainActivity = this;

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
         EditText name = findViewById(R.id.name);
         EditText password = findViewById(R.id.password);
//连接数据库的请求要放在一个新的线程中，不能直接在当前线程请求连接数据库
         new Thread(){
             public void run ()
             {
                 UserDao userDao = new UserDao();
                 int msg = userDao.login(name.getText().toString(),password.getText().toString());
                 if (msg == 1)
                 {
                     Intent intent;
                     intent = new Intent(mainActivity,HomePage.class);
                     //进行跳转
                     startActivity(intent);
                 }
                 else
                    hand1.sendEmptyMessage(msg);
             }
         }.start();
    }
    //Handler是一套Android消息传递机制，主要用于线程间的通信
    /**
     * handler在主线程中创建了一个子线程，该子线程运行并生成Message,
     * Looper获取message并传递给Handler，Handler逐个获取子线程中的Message
     */
    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                //Intent intent = new Intent(this,HomePage.class);
                //进行跳转
                //startActivity(intent);
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2){
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_LONG).show();
            }
        }
    };


}