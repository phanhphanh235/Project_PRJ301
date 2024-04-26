package model;

import entity.Product;
import java.util.Vector;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.UUID;

public class DAOProduct extends DBConnect {

    public Vector<Product> getProduct(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int pid = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                float price = rs.getFloat(4);
                int quantity = rs.getInt(5);
                int catid = rs.getInt(6);
                String image = rs.getString(7);
                String status = rs.getString(8);
                Product pro = new Product(pid, name, description, price, quantity, catid, image, status);
                vector.add(pro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]([name],[description],[price],[quantity],[catid],[image],[status])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pro.getName());
            ps.setString(2, pro.getDescription());
            ps.setFloat(3, pro.getPrice());
            ps.setInt(4, pro.getQuantity());
            ps.setInt(5, pro.getCatid());
            ps.setString(6, pro.getImage());
            ps.setString(7, pro.getStatus());
  
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products] SET [name] = ?,[description] = ?,[price] = ?,"
                + "[quantity] = ?,[catid] = ?,[image] = ?, [status] = ? WHERE pid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pro.getName());
            ps.setString(2, pro.getDescription());
            ps.setFloat(3, pro.getPrice());
            ps.setInt(4, pro.getQuantity());
            ps.setInt(5, pro.getCatid());
            ps.setString(6, pro.getImage());
            ps.setString(7, pro.getStatus());
            ps.setInt(8, pro.getPid());

            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int deleteProduct(int pid) {
        int n = 0;
        String sql = "SELECT COUNT(*) FROM Bills WHERE pid = ?";
        try {
            PreparedStatement psCheck = conn.prepareStatement(sql);
            psCheck.setInt(1, pid);
            ResultSet rs = psCheck.executeQuery();
            rs.next();
            int billCount = rs.getInt(1);

            if (billCount > 0) {
                String hideSql = "UPDATE Products SET status = 'hidden' WHERE pid = ?";
                PreparedStatement psHide = conn.prepareStatement(hideSql);
                psHide.setInt(1, pid);
                n = psHide.executeUpdate();
                System.out.println("Product is hidden.");
            } else {
                String sqlDelete = "DELETE FROM Products WHERE pid = ?";
                PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
                psDelete.setInt(1, pid);
                n = psDelete.executeUpdate();
                System.out.println("Product is deleted.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void addToCart(String username, String pid, int amount){
        String xSql = "insert into Carts(username, pid, amount) values (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, pid);
            ps.setInt(3, amount);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCart(String username, String pid, int amount){
        String xSql = "update Carts set amount=? where username=? and pid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setInt(1, amount);
            ps.setString(2, username);
            ps.setString(3, pid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public HashMap<String, Integer> getCart(String username){
        
        HashMap<String, Integer> hashCart = new HashMap<>();
        String xSql = "select * from Carts where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hashCart.put(rs.getString("pid"), rs.getInt("amount"));
            }
            return hashCart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCart;
    }
    
    public void removeProductCart(String username, String pid){
        String xSql = "DELETE FROM Carts WHERE username=? and pid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, pid);
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removeAllCart(String username){
        String xSql = "DELETE FROM Carts WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, username);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addToBill(String username, String pid, int amount, float total){
        String xSql = "insert into Bills(bid, username, pid, date, amount, total) values (?, ?, ?, GETDATE(), ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, username);
            ps.setString(3, pid);
            ps.setInt(4, amount);
            ps.setFloat(5, total);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        Vector<Product> vector = dao.getProduct("Select * from Products");
        for (Product pro : vector) {
            System.out.println(pro);
        }

//        int n = dao.deleteProduct(10);
//        if (n > 0) {
//            System.out.println("deleted");
//        }
    }
}
