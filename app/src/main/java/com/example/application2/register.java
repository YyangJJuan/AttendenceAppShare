package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    /*public void go(View v)
    {
        EditText name = (EditText) findViewById(R.id.et_name);
        EditText pwd1 = (EditText) findViewById(R.id.et_password1);
        EditText pwd2 = (EditText) findViewById(R.id.et_password2);
        username = name.getText().toString();
        String password1 = pwd1.getText().toString();
        String password2 = pwd2.getText().toString();
        new Thread(){
            public void run ()
            {
                UserDao userDao = new UserDao();
                int msg = userDao.register(username,password1,password2);
                hand2.sendEmptyMessage(msg);
                if (msg == 1)
                {
                    Intent intent;
                    intent = new Intent(register.this,MainActivity.class);
                    //进行跳转
                    startActivity(intent);
                }
            }
        }.start();
    }*/

    @SuppressLint("HandlerLeak")
    final Handler hand2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                //Intent intent = new Intent(this,HomePage.class);
                //进行跳转
                //startActivity(intent);
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2){
                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Toast.makeText(getApplicationContext(), "两次密码不正确", Toast.LENGTH_LONG).show();
            }else if (msg.what == 4){
                Toast.makeText(getApplicationContext(), "填入信息不完整", Toast.LENGTH_LONG).show();
            }
        }
    };
}