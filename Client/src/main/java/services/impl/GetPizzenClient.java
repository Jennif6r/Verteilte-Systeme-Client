package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.GetPizzen;

public class GetPizzenClient {
	public String getPizzen() throws Exception {
		URL serviceUrl = new URL("http://localhost:9000/getPizzen?wsdl");

		QName serviceQName = new QName("http://impl.services.java.main/", "GetPizzenImplService");
		Service service = Service.create(serviceUrl, serviceQName);

		QName getQName = new QName("http://impl.services.java.main/", "GetPizzenImplPort");
		GetPizzen get = service.getPort(getQName, GetPizzen.class);
		return get.getPizzen();
	}
}
