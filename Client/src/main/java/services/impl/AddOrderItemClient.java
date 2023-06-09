package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.AddOrderItem;

public class AddOrderItemClient {

	public void addOrderItem(String orderId, String order) throws Exception {
			URL serviceUrl = new URL("http://localhost:9000/addOrderItem?wsdl");
			
			QName serviceQName = new QName("http://impl.services.java.main/", "AddOrderItemImplService");
			Service service = Service.create(serviceUrl ,serviceQName);
			
			QName addQName = new QName("http://impl.services.java.main/", "AddOrderItemImplPort");
			AddOrderItem add = service.getPort(addQName, AddOrderItem.class);
			add.addOrderItem(orderId, order);
	}

}
