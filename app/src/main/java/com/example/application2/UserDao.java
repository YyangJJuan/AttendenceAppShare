package com.example.application2;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    connectMysql connectMysql = com.example.application2.connectMysql.getInstance();
    Connection conn = connectMysql.getConnection();
    //登录
    public boolean login(String name,String password)
    {
        if (conn == null)
        {
            Log.i(TAG,"register:conn is null");
            return false;
        }
        else
        {
            String sql = "select * from user where uername = ? and password ?";
            try{
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,password);
                ResultSet res = pre.executeQuery();
                boolean t = res.next();
                return  t;

            } catch (SQLException e) {
                e.printStackTrace();
                return  false;
            }
        }
    }
}
