package mainProgramm;

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
			if(request.checkForAktivOrder()) {
				System.out.println("aktiv Order");
			}
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

}
