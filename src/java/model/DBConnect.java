package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
    public Connection conn=null;
    public DBConnect(String URL,String username,String password){
        try {
            //driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connection
            conn=DriverManager.getConnection(URL,username, password);
            //System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=BookStore",
                "sa","23052003");
    }
//    public static void main(String[] args) {
//        new DBConnect();
//    }
}
