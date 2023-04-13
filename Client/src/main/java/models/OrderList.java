package models;

import java.util.List;


public class OrderList {
	private List<Order> orders;
	private boolean aktiv;
	private String orderId;
	private String user; 
	
	public OrderList(String username) {
		this.aktiv = true;
		this.orderId = Long.toString(System.currentTimeMillis());
		this.user = username;
	}
	
}
