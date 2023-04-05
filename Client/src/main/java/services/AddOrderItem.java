package services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import models.Order;
import models.OrderList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AddOrderItem {
	@WebMethod
	void addOrderItem(OrderList orderList, Order order);

}
