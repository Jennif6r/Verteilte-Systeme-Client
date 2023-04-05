package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.StartOrder;

public class StartOrderClient {
	public String startOrder() throws Exception {
		URL serviceUrl = new URL("http://localhost:9000/startOrder?wsdl");

		QName serviceQName = new QName("http://impl.services.java.main/", "StartOrderImplService");
		Service service = Service.create(serviceUrl, serviceQName);

		QName startName = new QName("http://impl.services.java.main/", "StartOrderImplPort");
		StartOrder checker = service.getPort(startName, StartOrder.class);

		return checker.startOrder();
	}

}
