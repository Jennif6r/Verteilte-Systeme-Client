package mainProgramm;

import services.impl.RegistratorClient;
import services.impl.RequestAktivOrderClient;

public class Client {
	private String id;

	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.id = RegistratorClient.register();

			// request for aktiv order
			RequestAktivOrderClient request = new RequestAktivOrderClient();
			while (!request.checkForAktivOrder()) {
				// wait and do nothing
			}
			System.out.println("aktiv Order");
			// here code to show frame to order
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
