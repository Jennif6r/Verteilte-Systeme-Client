package models;

import java.util.ArrayList;

public class Order {
	
	
	private ArrayList<Product> order;
	
	public Order() {
		
	}
	
	public void addProduct(ArrayList<Product> pices) {
		for(Product product: pices) {
			order.add(product);
		}
	}

	public ArrayList<Product> getOrder() {
		return this.order;
	}

}
