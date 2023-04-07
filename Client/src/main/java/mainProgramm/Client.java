package mainProgramm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public static void main(String[] args) {
		Client client = new Client();
		try {
//			client.id = RegistratorClient.register();
//			System.out.println(client.id);
//			// request for aktiv order
			RequestAktivOrderClient request = new RequestAktivOrderClient();
//			client.startOrder();
			List<OrderList> activOrders = new ArrayList<OrderList>(Arrays.asList(request.checkForAktivOrder()));
			int length = activOrders.size();
			if( length > 0 ) {
				System.out.println(activOrders.toString());
			}
//			client.addOrderItem();
//			client.getNumberOfOrders();
//			while (!request.checkForAktivOrder()) {
//				// wait and do nothing
//			}
			// here code to show frame to order
//			client.getMergedOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void startOrder() {
		StartOrderClient start = new StartOrderClient();
		String orderId = "";
		try {
			orderId = start.startOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("id: "+ orderId);
	}
	
	public void addOrderItem() {
		AddOrderItemClient add = new AddOrderItemClient();
		ArrayList<Product> products = getProducts();
		try {
			Order order = new Order();
			order.addProduct(products);
			add.addOrderItem("1680778200745", JsonParser.parseOrder(order));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product("Magertia", 7.99, 2));
		products.add(new Product("Salami", 9.55, 3));
		return products;
	}
	
	private void getMergedOrder() {
		MergeOrderClient merge = new MergeOrderClient();
		try {
			String result = merge.getMergedOrder("1680778200745");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getNumberOfOrders() {
		GetNumberOfOrderClient getNumber = new GetNumberOfOrderClient();
		try {
			int number = getNumber.getNumberOfOrdersFromOrderList("1680778200745");
			System.out.println("number of orders : " + number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
