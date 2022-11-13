package mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connectMysql {
    public static int getUserSize()
    {
        final String CLS = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://192.168.43.78:3306/app";
        final String USER="local";
        final String PWD = "123456";
        int count = 0;
        try{
            Class.forName(CLS);
            Connection connection = DriverManager.getConnection(URL,USER,PWD);
            String sql = "select count(*) from user where username = '"+? and password = ?";
            Statement statement = connection.createStatement();


        }
    }
}
