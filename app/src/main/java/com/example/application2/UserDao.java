package com.example.application2;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.validation.SchemaFactoryLoader;

public class UserDao {

/**
 * function：登录
 */
public int login(String username,String password1)
{
    HashMap<String,Object> map = new HashMap<>();
    //建立数据库的连接
    Connection connection = connectMysql.getConnection();
    int msg = 0;
    try{
        String sql = "select * from user where username = ? ";
        if (connection != null)
        {
            PreparedStatement ps= connection.prepareStatement(sql);
            if (ps != null)
            {
                Log.e(TAG,"账号"+username);
                ps.setString(1,username);
                ResultSet rs = ps.executeQuery();
                if (rs.next())//用户名存在
                {
                    msg = 2;//密码不正确
                    String sql2 = "select * from user where username = ? and password = ?";
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    if (ps2 != null)
                    {
                        ps2.setString(1,username);
                        ps2.setString(2,password1);
                        ResultSet rs2 = ps2.executeQuery();
                        if (rs2.next())//密码正确
                        {
                            msg = 1;
                        }
                    }
                }else //用户名不存在
                    msg = 3;
            }
            connectMysql.closeAll(ps,connection);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        Log.d(TAG,"异常login"+e.getMessage());
    }
    String temp = ""+msg;
    Log.e(TAG,temp);
    return msg;
}
    }

