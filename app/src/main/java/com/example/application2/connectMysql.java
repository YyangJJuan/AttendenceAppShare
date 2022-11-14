package com.example.application2;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectMysql {
   private static connectMysql instance;

   public static connectMysql getInstance(){
       if (instance == null)
       {
           instance = new connectMysql();
       }
       return instance;
   }
   public Connection getConnection()
   {
       try{
           Class.forName("com.mysql.jdbc.Driver");
           String url = "jdbc:mysql://localhost:3306/dbbook2?user=root&password=123456&useUnicode=true&characterEncoding=utf-8&useSSL=true";
           return DriverManager.getConnection(url,"root","123456");
       }catch (Exception e)
       {
           return null;
       }


   }
}
