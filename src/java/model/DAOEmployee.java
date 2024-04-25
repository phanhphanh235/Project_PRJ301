package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class DAOEmployee extends DBConnect{
    public boolean login(String user,String pass){
        boolean flag=false;
        try{
            PreparedStatement pre=conn.prepareStatement(
                    "select * from employees where FirstName=? and EmployeeID=?");
            pre.setString(1,user);pre.setInt(2,Integer.parseInt(pass));
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                flag=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
