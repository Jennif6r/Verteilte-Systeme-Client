package models;

import java.util.ArrayList;

public class Order {
	
	private ArrayList<Product> products=new ArrayList<Product>();;
	
	public Order() {
		
	}
	
	public void addProduct(ArrayList<Product> pices) {
		for(Product product: pices) {
			products.add(product);
		}
	}

	public ArrayList<Product> getOrder() {
		return this.products;
	}

}
