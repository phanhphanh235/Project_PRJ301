package entity;

/**
 *
 * @author admin
 */
public class Category {
    private int catid; 
    private String name; 

    public Category() {
    }

    public Category(int catid, String name) {
        this.catid = catid;
        this.name = name;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "catid=" + catid + ", name=" + name + '}';
    }
    
    
}
