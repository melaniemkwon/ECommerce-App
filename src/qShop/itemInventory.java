package qShop.servlet;

public class itemInventory {
	int id;
	String itemName;
	String itemImage;
	String itemDescription;
	int quantity;
	double itemPrice;
	
	public itemInventory(int id, String itemName, String itemImage, String itemDescription,int quantity, double itemPrice){
		this.id = id;
		this.itemName = itemName;
		this.itemImage = itemImage;
		this.itemDescription = itemDescription;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}

