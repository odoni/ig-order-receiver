package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.RequestModel;

public interface OrderService {
	public void processOrders(RequestModel requestModel) throws InvalidBrokerException, InvalidOrderFileException;
}
