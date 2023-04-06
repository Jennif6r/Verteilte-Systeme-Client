package services;

import java.util.List;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import models.OrderList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RequestAktivOrder {
	
	@WebMethod
	OrderList[] isThereAnAktivOrder();
}
