package mainProgramm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.OrderGUI;
import models.Order;
import models.Product;
import services.impl.AddOrderItemClient;
import services.impl.GetNumberOfOrderClient;
import services.impl.GetPizzenClient;
import services.impl.JsonParser;
import services.impl.LogoutClient;
import services.impl.MergeOrderClient;
import services.impl.RegistratorClient;
import services.impl.RequestAktivOrderClient;
import services.impl.StartOrderClient;

public class Client {
	private String id;
	public String orderId;
	private String username; 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static void main(String[] args) {
		
		Client client = new Client();
		new OrderGUI(client);
	}
	
	public String startOrder(String user) {
		StartOrderClient start = new StartOrderClient();
		String orderId = "";
		try {
			orderId = start.startOrder(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.orderId = orderId;
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

	public String getMergedOrder(String orderId) {
		MergeOrderClient merge = new MergeOrderClient();
		try {
			return merge.getMergedOrder(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private int getNumberOfOrders(String orderId) {
		GetNumberOfOrderClient getNumber = new GetNumberOfOrderClient();
		try {
			return getNumber.getNumberOfOrdersFromOrderList(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void register () throws Exception {
		this.id = RegistratorClient.register(); 
	}
	
	public Map<String, String> getActiveOrders() throws Exception {
		String [][] activOrders = new RequestAktivOrderClient().checkForAktivOrder();
		Map<String, String> orderIds = new HashMap<String, String>();
		for (int i=0; i<activOrders.length; i++) {
			orderIds.put(activOrders[i][0], activOrders[i][1]);
		}
		return orderIds;
	}

	public void logout() throws Exception {
		LogoutClient logout = new LogoutClient();
		if(this.id != null && this.username != null) {
			logout.logoutClient(this.id, username);
		}
	}

	public List<Product> getPizzen(){
		String pizzenStr = "";
		try {
			pizzenStr = new GetPizzenClient().getPizzen();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonParser.parseToMap(pizzenStr);
	}
}
