package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.GetNumberOfOrders;

public class GetNumberOfOrderClient {
	public int getNumberOfOrdersFromOrderList(String orderId) throws Exception {

		URL serviceUrl = new URL("http://localhost:9000/getNumberOfOrders?wsdl");

		QName serviceQName = new QName("http://impl.services.java.main/", "GetNumberOfOrdersImplService");
		Service service = Service.create(serviceUrl, serviceQName);

		QName addQName = new QName("http://impl.services.java.main/", "GetNumberOfOrdersImplPort");
		GetNumberOfOrders get = service.getPort(addQName, GetNumberOfOrders.class);
		return get.getNumberOfOrdersFromOrderList(orderId);
	}
}
