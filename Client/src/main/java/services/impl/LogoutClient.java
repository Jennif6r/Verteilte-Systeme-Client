package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.Logout;

public class LogoutClient {
	public void logoutClient(String clientId, String username) throws Exception {
		URL serviceUrl = new URL("http://localhost:9000/logout?wsdl");
		
		QName serviceQName = new QName("http://impl.services.java.main/", "LogoutImplService");
		Service service = Service.create(serviceUrl ,serviceQName);
		
		QName logoutQName = new QName("http://impl.services.java.main/", "LogoutImplPort");
		Logout logout = service.getPort(logoutQName, Logout.class);
		logout.logoutOfClient(clientId, username);
	}
}
