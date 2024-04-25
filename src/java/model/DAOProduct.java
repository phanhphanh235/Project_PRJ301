package model;

import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class DAOProduct extends DBConnect {

    public Vector<Products> getProducts(String sql) {

        Vector<Products> vector = new Vector<Products>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt(3);
                int CategoryID = rs.getInt(4);
                String QuantityPerUnit = rs.getString(5);
                double UnitPrice = rs.getDouble(6);
                int UnitsInStock = rs.getInt(7);
                int UnitsOnOrder = rs.getInt(8);
                int ReorderLevel = rs.getInt(9);
                boolean Discontinued = rs.getBoolean(10);
                Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public int insertProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO Products\n"
                + "           ([ProductName],[SupplierID],[CategoryID],[QuantityPerUnit]\n"
                + "           ,[UnitPrice],[UnitsInStock],[UnitsOnOrder]\n"
                + "           ,[ReorderLevel],[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            // preState.setDataType(indexOf?,value); indexOf? start from 1
            preState.setString(1, pro.getProductName());
            preState.setInt(2, pro.getSupplierID());
            preState.setInt(3, pro.getCategoryID());
            preState.setString(4, pro.getQuantityPerUnit());
            preState.setDouble(5, pro.getUnitPrice());
            preState.setInt(6, pro.getUnitsInStock());
            preState.setInt(7, pro.getUnitsOnOrder());
            preState.setInt(8, pro.getReorderLevel());
            preState.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            n = preState.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateUnitPrice(int pid, double price) {
        int n = 0;
        String sql = "UPDATE [Products] SET [UnitPrice] = [UnitPrice]+?"
                + " WHERE ProductID=?";
        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            // preState.setDataType(indexOf?,value); indexOf? start from 1
            preState.setDouble(1, price);
            preState.setInt(2, pid);
            n = preState.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateProduct(Products pro) {
        int n = 0;
        String sql = "UPDATE [Products] SET [ProductName] = ?,[SupplierID] = ?,[CategoryID] = ?\n"
                + "      ,[QuantityPerUnit] = ?,[UnitPrice] = ?,[UnitsInStock] = ?\n"
                + "      ,[UnitsOnOrder] = ?,[ReorderLevel] = ?,[Discontinued] = ?\n"
                + " WHERE ProductID=?";
        try {
            PreparedStatement preState = conn.prepareStatement(sql);
            // preState.setDataType(indexOf?,value); indexOf? start from 1
            preState.setString(1, pro.getProductName());
            preState.setInt(2, pro.getSupplierID());
            preState.setInt(3, pro.getCategoryID());
            preState.setString(4, pro.getQuantityPerUnit());
            preState.setDouble(5, pro.getUnitPrice());
            preState.setInt(6, pro.getUnitsInStock());
            preState.setInt(7, pro.getUnitsOnOrder());
            preState.setInt(8, pro.getReorderLevel());
            preState.setInt(9, (pro.isDiscontinued() == true ? 1 : 0));
            preState.setInt(10, pro.getProductID());
            n = preState.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateDiscontinue(int pid, int discontinue) {
        int n = 0;
        String sql = "UPDATE [Products]\n"
                + "   SET [Discontinued] = ? "
                + " WHERE ProductID=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, discontinue);
            ps.setInt(2, pid);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeProduct(int pid) {
        int n = 0;
        //check foreign key [order details]
        String sql = "Select * from [Order Details] where [ProductID]=" + pid;
        ResultSet rs = this.getData(sql);
        try {
            if (rs.next()) { // primary key exist on foreign key --> don't delete
                updateDiscontinue(pid, 1);
                //return n;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String sqlDelete = "delete from products where ProductID=" + pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlDelete);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
}
