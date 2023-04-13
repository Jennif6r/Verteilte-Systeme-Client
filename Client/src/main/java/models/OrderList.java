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
	
//	public void addOrderListToAllOrders() {
//		new AllOrders().addOrder(this);
//	}
//	
//	public void addOrder(Order order) {
//		this.orders.add(order);
//	}
//	
//	public List<Order> getOrders(){
//		return this.orders;
//	}
//	
//	public void finishOrder() {
//		this.aktiv = false;
//	}
//	
//	public boolean getAktiv() {
//		return this.aktiv;
//	}
//	
//
//	public String getOrderId() {
//		return orderId;
//	}
}
