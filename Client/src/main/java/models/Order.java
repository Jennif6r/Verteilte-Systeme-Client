package models;

import java.util.ArrayList;

public class Order {
	
	private ArrayList<String> order;
	
	public Order(ArrayList<String> pices) {
		this.order = pices;
	}
	
	public ArrayList<String> getOrder() {
		return this.order;
	}

}
