package services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface GetNumberOfOrders {
	@WebMethod
	int getNumberOfOrdersFromOrderList(String orderId);
}
