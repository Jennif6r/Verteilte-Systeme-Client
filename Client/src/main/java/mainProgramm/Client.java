package mainProgramm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.Order;
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
			client.id = RegistratorClient.register();
			System.out.println(client.id);
			RequestAktivOrderClient request = new RequestAktivOrderClient();
			String orderId = client.startOrder();
			List<String> activOrders = new ArrayList<String>(Arrays.asList(request.checkForAktivOrder()));
			int length = activOrders.size();
			if( length > 0 ) {
				System.out.println(activOrders.toString());
			}
			client.addOrderItem(orderId);
			client.getNumberOfOrders(orderId);
			client.getMergedOrder(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String startOrder() {
		StartOrderClient start = new StartOrderClient();
		String orderId = "";
		try {
			orderId = start.startOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("id: "+ orderId);
		return orderId;
	}
	
	public void addOrderItem(String orderId) {
		AddOrderItemClient add = new AddOrderItemClient();
		ArrayList<Product> products = getProducts();
		try {
			Order order = new Order();
			order.addProduct(products);
			add.addOrderItem(orderId, JsonParser.parseOrder(order));
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
	
	private void getMergedOrder(String orderId) {
		MergeOrderClient merge = new MergeOrderClient();
		try {
			String result = merge.getMergedOrder(orderId);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
