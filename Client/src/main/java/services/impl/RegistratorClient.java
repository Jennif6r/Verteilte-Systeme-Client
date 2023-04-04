package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.Registrator;

public class RegistratorClient {
	public static String register() throws Exception {
		URL serviceUrl = new URL("http://localhost:9000/register?wsdl");
		
		QName serviceQName = new QName("http://impl.services.java.main/", "RegistratorImplService");
		Service service = Service.create(serviceUrl ,serviceQName);
		
		QName registerQName = new QName("http://impl.services.java.main/", "RegistratorImplPort");
		Registrator registrator = service.getPort(registerQName, Registrator.class);
		
		return registrator.registerClient();
	}
}
