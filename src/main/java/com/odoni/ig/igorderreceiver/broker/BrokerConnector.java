package com.odoni.ig.igorderreceiver.broker;

import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.model.BrokerConnectorModel;
import org.springframework.jms.core.JmsTemplate;

public interface BrokerConnector {
	public JmsTemplate getBrokerConnection(BrokerConnectorModel brokerConnectorModel) throws InvalidBrokerException;
}
