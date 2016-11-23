package store;

public class Item {
    
    private static int count = 0;
    
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    
    public Item(String name, String description, int quantity, double price) {
        super();
        this.id = count++;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }
}