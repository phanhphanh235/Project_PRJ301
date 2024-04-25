package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
    public ResultSet getData(String sql){
        ResultSet rs = null;
        Statement st;
        try{
            st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=BookStore",
                "sa","23052003");
    }
//    public static void main(String[] args) {
//        new DBConnect();
//    }
}
