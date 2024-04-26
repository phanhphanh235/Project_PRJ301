package model;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class DAOCategories extends DBConnect {

    public Vector<Category> getCategories(String sql) {
        Vector<Category> vector = new Vector<Category>();
        try {
            Statement st = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int catid = rs.getInt("catid");
                String name = rs.getString("name");

                Category categories = new Category(catid, name);

                vector.add(categories);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public int addCategories(Category cate){
        int n=0;
        String sql = "insert into Categories(catid, name) values (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cate.getCatid());
            ps.setString(2, cate.getName());
            
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public void deleteCategoriesByCatid(String catid) {
        String xSql = "DELETE FROM Categories WHERE catid= ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(xSql);
            ps.setString(1, catid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
        Vector<Category> vector = dao.getCategories("Select * from Categories");
        for(Category cat: vector){
            System.out.println(cat);
        }
    }
}
