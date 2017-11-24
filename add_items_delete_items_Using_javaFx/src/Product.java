
public class Product {

    String name;
    double price;
    int quantity;

    public Product(){

        name = "No Name";
        price = 0.0;
        quantity = 0;
    }

    public Product(String name,double price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
