package services.impl;

import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import services.MergeOrder;

public class MergeOrderClient {
	public String getMergedOrder(String orderId) throws Exception{
		URL serviceUrl = new URL("http://localhost:9000/getMergedOrder?wsdl");
		
		QName serviceQName = new QName("http://impl.services.java.main/", "MergeOrderImplService");
		Service service = Service.create(serviceUrl ,serviceQName);
		
		QName mergeQName = new QName("http://impl.services.java.main/", "MergeOrderImplPort");
		MergeOrder merge = service.getPort(mergeQName, MergeOrder.class);
		return merge.getMergedOrder(orderId);
	}
	
}
