package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import models.OrderList;
import services.RequestAktivOrder;

public class RequestAktivOrderClient {
	public String[][] checkForAktivOrder() throws Exception{
		URL serviceUrl = new URL("http://localhost:9000/checkForOrder?wsdl");
		
		QName serviceQName = new QName("http://impl.services.java.main/", "RequestAktivOrderImplService");
		Service service = Service.create(serviceUrl ,serviceQName);
		
		QName checkerName = new QName("http://impl.services.java.main/", "RequestAktivOrderImplPort");
		RequestAktivOrder checker = service.getPort(checkerName, RequestAktivOrder.class);
		
		return checker.isThereAnAktivOrder();
	}
}
