package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.broker.BrokerConnector;
import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.BrokerConnectorModel;
import com.odoni.ig.igorderreceiver.model.Order;
import com.odoni.ig.igorderreceiver.model.RequestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceDefaultTest {

	@Mock
	private BrokerConnector brokerConnectorMock;

	@Mock
	private JmsTemplate jmsTemplateMock;

	@Mock
	private OrderFileParser orderFileParser;

	@InjectMocks
	private OrderServiceDefault orderServiceDefault;

	@Test
	public void getBrokerConnectionShouldReturnConnectionSuccessfully() throws InvalidBrokerException {
		BrokerConnectorModel brokerConnectorModel = new BrokerConnectorModel("connectionUrl",
				"user",
				"password",
				"broker");

		RequestModel requestModel = new RequestModel("connectionUrl",
				"user",
				"password",
				"broker",
				"destinationName");

		when(brokerConnectorMock.getBrokerConnection(brokerConnectorModel)).thenReturn(jmsTemplateMock);

		JmsTemplate jmsTemplate = orderServiceDefault.getBrokerConnection(requestModel);

		assertNotNull(jmsTemplate);
	}

	@Test(expected = InvalidBrokerException.class)
	public void getBrokerConnectionShouldThrowExceptionForInvalidBrokerData() throws InvalidBrokerException {
		BrokerConnectorModel brokerConnectorModel = new BrokerConnectorModel("connectionUrl",
				"user",
				"password",
				"broker");

		RequestModel requestModel = new RequestModel("connectionUrl",
				"user",
				"password",
				"broker",
				"destinationName");

		when(brokerConnectorMock.getBrokerConnection(brokerConnectorModel)).thenThrow(InvalidBrokerException.class);

		orderServiceDefault.getBrokerConnection(requestModel);
	}

	@Test
	public void sendOrdersToQueueShouldSendOrdersToQueueSuccessfully() throws InvalidBrokerException {
		RequestModel requestModel = new RequestModel("connectionUrl",
				"user",
				"password",
				"broker",
				"destinationName");
		List<Order> orders = Arrays.asList(new Order("account", new Date(), new Date(), "market", "action", 1));
		orderServiceDefault.sendOrdersToQueue(requestModel, jmsTemplateMock, orders);
	}

	@Test(expected = InvalidBrokerException.class)
	public void sendOrdersToQueueShouldThrowException() throws InvalidBrokerException {
		RequestModel requestModel = new RequestModel("connectionUrl",
				"user",
				"password",
				"broker",
				"destinationName");
		Order order = new Order("account", new Date(), new Date(), "market", "action", 1);
		List<Order> orders = Arrays.asList(order);

		doThrow(RuntimeException.class).when(jmsTemplateMock).convertAndSend(requestModel.getQueue(), order);

		orderServiceDefault.sendOrdersToQueue(requestModel, jmsTemplateMock, orders);
	}

	@Test
	public void processOrdersShouldProcessAllOrdersSuccessfully() throws InvalidBrokerException, InvalidOrderFileException {
		RequestModel requestModel = new RequestModel("connectionUrl",
				"user",
				"password",
				"broker",
				"destinationName");

		orderServiceDefault.processOrders(requestModel);
	}

}
