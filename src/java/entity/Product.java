
package entity;

/**
 *
 * @author admin
 */
public class Product {
    private int pid;
    private String name;
    private String description;
    private float price;
    private int quantity;
    private int catid;
    private String image;
    private String status;
    public Product() {
    }

    public Product(int pid, String name, String description, float price, int quantity, int catid, String image, String status) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.catid = catid;
        this.image = image;
        this.status = status;
    }

   

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" + "pid=" + pid + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity=" + quantity + ", catid=" + catid + ", image=" + image + '}';
    }
    
    
}
