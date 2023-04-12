package mainProgramm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gui.OrderGUI;
import models.Order;
import models.OrderList;
import models.Product;
import services.impl.AddOrderItemClient;
import services.impl.GetNumberOfOrderClient;
import services.impl.JsonParser;
import services.impl.MergeOrderClient;
import services.impl.RegistratorClient;
import services.impl.RequestAktivOrderClient;
import services.impl.StartOrderClient;

public class Client {
	private String id;
	public String orderId;

	public static void main(String[] args) {
		
//			
		Client client = new Client();
//		
		OrderGUI orderGUI = new OrderGUI(client);
//		
//		
//		try {
////			client.id = RegistratorClient.register();
////			System.out.println(client.id);
////			// request for aktiv order
//			RequestAktivOrderClient request = new RequestAktivOrderClient();
////			client.startOrder();
//			List<OrderList> activOrders = new ArrayList<OrderList>(Arrays.asList(request.checkForAktivOrder()));
//			int length = activOrders.size();
//			if( length > 0 ) {
//				System.out.println(activOrders.toString());
//			}
////			client.addOrderItem();
////			client.getNumberOfOrders();
////			while (!request.checkForAktivOrder()) {
////				// wait and do nothing
////			}
//			// here code to show frame to order
////			client.getMergedOrder();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public String startOrder() {
		StartOrderClient start = new StartOrderClient();
		String orderId = "";
		try {
			orderId = start.startOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.orderId = orderId;
//		System.out.println("id: "+ orderId);
		return orderId;
	}
	
	public void addOrderItem(Order order, String orderId) {
		AddOrderItemClient add = new AddOrderItemClient();
		try {
			add.addOrderItem(orderId, JsonParser.parseOrder(order));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("Magertia", 7.99, 2));
		products.add(new Product("Salami", 9.55, 3));
		return products;
	}
	
	public String getMergedOrder(String orderId) {
		MergeOrderClient merge = new MergeOrderClient();
		try {
			String result = merge.getMergedOrder(orderId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void getNumberOfOrders(String orderId) {
		GetNumberOfOrderClient getNumber = new GetNumberOfOrderClient();
		try {
			int number = getNumber.getNumberOfOrdersFromOrderList(orderId);
			System.out.println("number of orders : " + number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void register () throws Exception {
		this.id = RegistratorClient.register(); 
	}
	
	public List<String> getActiveOrders() throws Exception {
		RequestAktivOrderClient request = new RequestAktivOrderClient();
		List<String> activOrders = new ArrayList<String>(Arrays.asList(request.checkForAktivOrder()));
		
		return activOrders;
	}

}
