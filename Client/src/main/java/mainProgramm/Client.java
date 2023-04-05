package mainProgramm;


import java.util.List;

import models.Order;
import models.OrderList;
import services.impl.AddOrderItemClient;
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
			// request for aktiv order
			RequestAktivOrderClient request = new RequestAktivOrderClient();
			client.startOrder();
			List<OrderList> activOrders = request.checkForAktivOrder();
			int length = activOrders.size();
			if( length > 0 ) {
				System.out.println(activOrders.toString());
			}
			client.addOrderItem();
//			while (!request.checkForAktivOrder()) {
//				// wait and do nothing
//			}
			// here code to show frame to order
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
		try {
			add.addOrderItem(new OrderList(), new Order());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
