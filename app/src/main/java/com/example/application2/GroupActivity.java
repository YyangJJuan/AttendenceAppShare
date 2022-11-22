package com.example.application2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.transform.Result;

public class GroupActivity extends AppCompatActivity {
    boolean isCreate;
    private String groupName;
    private int groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.group_tool_bar);
        //setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);

        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        isCreate = getIntent().getBooleanExtra("isCreate", false);
        groupName = getIntent().getStringExtra("group_name");
        groupId = getIntent().getIntExtra("group_id", -1);
        actionBar.setTitle(groupName);
        initView(groupName);

    }

    private void initView(String groupName)
    {
        TextView temp = (TextView) findViewById(R.id.name2);
        temp.setText(groupName);
        TextView member = findViewById(R.id.group_member);
//连接数据库的请求要放在一个新的线程中，不能直接在当前线程请求连接数据库
        new Thread(){
            public void run ()
            {
                String groupmembrs="";
                Connection connection = connectMysql.getConnection();
                String sql = "select * from creategroups where groupName = ? ";
                String sql2 = "select * from JoinedGroups where groupName = ?";
                if (connection != null)
                {
                    try {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        PreparedStatement ps2 = connection.prepareStatement(sql2);
                        Log.e(TAG,groupName);
                        ps.setString(1,groupName);
                        ps2.setString(1,groupName);
                        ResultSet rs1 = ps.executeQuery();
                        ResultSet rs2 = ps2.executeQuery();
                        if (rs1.next())
                        {
                            Log.e(TAG,"1111");
                            groupmembrs = rs1.getString(3);
                        }else{
                            if (rs2 .next())
                            {
                                Log.e(TAG,"22222");
                                groupmembrs = rs2.getString(3);
                            }
                        }
                        String[] str = groupmembrs.split(" ");
                        for (String s :str)
                        {
                            member.append(s+"\n");
                            Log.e(TAG,s);
                        }
                        if (rs2 != null) rs2.close();
                        if (rs1 != null) rs1.close();
                        if (ps2 != null) ps2.close();
                        if (ps != null) ps.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.start();
    }

}