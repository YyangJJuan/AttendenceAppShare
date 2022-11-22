package com.example.application2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateGroupActivity extends AppCompatActivity {
    private EditText groupName;
    private  EditText groupMember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        getSupportActionBar().setTitle("创建群组");
        ImageView imageView = findViewById(R.id.select_group_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_Group();
            }
        });
    }
    private void add_Group()
    {
        groupName = findViewById(R.id.group_name);
        groupMember = findViewById(R.id.group_mem);
        String name = groupName.getText().toString();
        String member = groupMember.getText().toString();
        String username = MainActivity.username;
        final int[] msg = {0};
        new Thread(){
            public void run ()
            {
                Connection connection = connectMysql.getConnection();
                String sql1 = "select groupName from creategroups where groupName = ?";
                if (connection != null)
                {
                    PreparedStatement ps = null;
                    PreparedStatement ps2 = null;
                    try {
                        ps = connection.prepareStatement(sql1);
                        ps.setString(1,name);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next())
                        {
                            msg[0]=2;//群组已经存在
                        }else{
                            String sql = "insert into creategroups(groupName,member) values(?,?)";
                            ps2 = connection.prepareStatement(sql);
                            ps2.setString(1,name);
                            ps2.setString(2,member);
                            int count = ps2.executeUpdate();
                            if (count > 0)
                            {
                                msg[0] = 1;//创建成功
                            }

                        }
                        if (ps2 != null)
                            ps2.close();
                        if (ps != null)
                            connectMysql.closeAll(ps,connection);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }else msg[0] = 3;//数据连接失败
                hand1.sendEmptyMessage(msg[0]);
                //返回主页面
                Intent intent = new Intent(CreateGroupActivity.this,HomePage.class);
                //进行跳转
                startActivity(intent);
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "创建失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                //Intent intent = new Intent(this,HomePage.class);
                //进行跳转
                //startActivity(intent);
                Toast.makeText(getApplicationContext(), "创建成功", Toast.LENGTH_LONG).show();
            } else if (msg.what == 2){
                Toast.makeText(getApplicationContext(), "群组已经存在，无法创建", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Log.e(TAG,"数据库连接失败");
            }
        }
    };
}