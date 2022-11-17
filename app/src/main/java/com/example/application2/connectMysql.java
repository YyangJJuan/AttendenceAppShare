package com.example.application2;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * function : 数据库工具类， 连接数据库
 * */
public class connectMysql {
   private static  final String DATA_BASE_NAME = "user";
   private static  String driver = "com.mysql.jdbc.Driver";
   private  static  String url =  "jdbc:mysql://192.168.43.78/dbbook2?user=root&password=123456&useUnicode=true&characterEncoding=utf-8&useSSL=true";
   //private  static  String user = "root";
   //private  static  String password = "123456";
   //连接数据库
   public static  Connection getConnection()
   {
       Connection connection = null;
       try{
           Class.forName(driver);
           connection = DriverManager.getConnection(url);
           Log.e("getConn","连接成功");
       }catch (Exception e)
       {
           Log.e("getConn",e.getMessage(),e);
           e.printStackTrace();
       }
    return  connection;
   }
   //关闭数据库
    public  static void closeAll(PreparedStatement ps,Connection conn)
    {
        if (ps!=null)
        {
            try{
                ps.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (conn != null)
        {
            try{
                conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public  static  void closeAll(PreparedStatement ps, ResultSet rs, Connection conn)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (ps!=null)
        {
            try
            {
                ps.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (conn != null)
        {
            try{
                conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
}
