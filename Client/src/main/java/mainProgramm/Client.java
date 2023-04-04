package mainProgramm;

import services.impl.RegistratorClient;

public class Client {
	private String id;
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.id = RegistratorClient.register();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
