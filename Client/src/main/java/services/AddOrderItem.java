package services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AddOrderItem {
	@WebMethod
	void addOrderItem(String id, String order);

}
