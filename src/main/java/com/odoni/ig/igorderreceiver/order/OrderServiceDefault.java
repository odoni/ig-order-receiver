package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.broker.BrokerConnector;
import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.BrokerConnectorModel;
import com.odoni.ig.igorderreceiver.model.Order;
import com.odoni.ig.igorderreceiver.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceDefault implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceDefault.class);
	public static final String ERROR_SENDING_ORDERS_TO_QUEUE = "ErrorResponse sending orders to queue.";

	@Autowired
	BrokerConnector brokerConnector;

	@Autowired
	OrderFileParser orderFileParser;

	@Override
	public void processOrders(RequestModel requestModel) throws InvalidBrokerException, InvalidOrderFileException {
		JmsTemplate jmsTemplate = getBrokerConnection(requestModel);
		List<Order> orders = orderFileParser.getOrdersFromFile(requestModel.getFile());
		sendOrdersToQueue(requestModel, jmsTemplate, orders);
	}

	protected void sendOrdersToQueue(RequestModel requestModel, JmsTemplate jmsTemplate, List<Order> orders) throws InvalidBrokerException {
		try {
			for (Order order: orders) {
				jmsTemplate.convertAndSend(requestModel.getQueue(), order);
			}
		} catch (Exception e) {
			logger.error(ERROR_SENDING_ORDERS_TO_QUEUE, e);
			throw new InvalidBrokerException(ERROR_SENDING_ORDERS_TO_QUEUE,  e);
		}
	}

	protected JmsTemplate getBrokerConnection(RequestModel requestModel) throws InvalidBrokerException {
		return brokerConnector.getBrokerConnection(new BrokerConnectorModel(requestModel.getConn(),
				requestModel.getUser(),
				requestModel.getPassword(),
				requestModel.getBroker()));
	}
}
