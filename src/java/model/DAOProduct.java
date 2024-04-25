package model;

import entity.Products;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class DAOProduct extends DBConnect{
    
    public Vector<Products> getProducts(String sql){
        
        Vector<Products> vector = new Vector<Products>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);
            
        while(rs.next()){
            int ProductID=rs.getInt(1);
            String ProductName=rs.getString("ProductName");
            int SupplierID =rs.getInt(3);
            int CategoryID=rs.getInt(4);
            String QuantityPerUnit=rs.getString(5);
            double UnitPrice=rs.getDouble(6);
            int UnitsInStock=rs.getInt(7);
            int UnitsOnOrder=rs.getInt(8);
            int ReoderLevel=rs.getInt(9);
            boolean Discontinued=rs.getBoolean(10);
            Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit,UnitPrice,UnitsInStock, UnitsOnOrder,ReoderLevel,Discontinued);
            vector.add(pro);
        }
}
        catch(Exception e){
            e.printStackTrace();
        }
        return vector;
    }
}
