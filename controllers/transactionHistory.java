package qShop.servlet;

public class transactionHistory {
	
	int id ;
	String items;
	String price;
	
	public transactionHistory(int id,String items, String price){
		this.id = id;
		this.items = items;
		this.price = price;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	
}
