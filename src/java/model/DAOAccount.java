package model;

import entity.Account;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.PreparedStatement;
/**
 *
 * @author admin
 */
public class DAOAccount extends DBConnect {

    public Vector<Account> getAccount(String sql) {
        Vector<Account> vector = new Vector<>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String username = rs.getString(1);
                String password = rs.getString(2);
                String fullname = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String address = rs.getString(6);
                int role = rs.getInt(7);
                
                Account acc = new Account(username, password, fullname, email, phone, address, role);
                vector.add(acc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public Account checkAccount(String user, String pass) {
        
        String xSql = "select * from Accounts where username=? AND password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getInt("role"));
                ps.close();
                rs.close();
                return a;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addAccount(String username, String password, String fullname, String email, String phone, String address) {
        String xSql = "insert into Accounts(username, password, fullname, email, phone, address, role) values (?, ?, ?, ?, ?, ?, 2)";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//    public int addAccount(Account acc) {
//        int n = 0;
//        String Sql = "insert into Accounts(username, password, fullname, email, phone, address, role) values (?, ?, ?, ?, ?, ?, 2)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(Sql);
//            ps.setString(1, acc.getUsername());
//            ps.setString(2, acc.getPassword());
//            ps.setString(3, acc.getFullname());
//            ps.setString(4, acc.getEmail());
//            ps.setString(5, acc.getPhone());
//            ps.setString(6, acc.getAddress());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n;
//    }
    
     public int getRoleByUser(String username, String password ) {
        String xSql = "select role from Accounts where username=? AND password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int a = rs.getInt("role"); 
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }
}
