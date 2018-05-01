package com.odoni.ig.igorderreceiver.broker;

import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.model.BrokerConnectorModel;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class BrokerConnectorDefault implements BrokerConnector {

	@Autowired
	MessageConverter jacksonJmsMessageConverter;

	@Override
	public JmsTemplate getBrokerConnection(BrokerConnectorModel brokerConnectorModel) throws InvalidBrokerException {
		JmsTemplate jmsTemplate;
		try {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
			factory.setBrokerURL(brokerConnectorModel.getConnectionUrl());
			factory.setUserName(brokerConnectorModel.getUser());
			factory.setPassword(brokerConnectorModel.getPassword());
			jmsTemplate = new JmsTemplate(factory);
			jmsTemplate.setDefaultDestinationName(brokerConnectorModel.getDestinationName());
			jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
		} catch (Exception e) {
			throw new InvalidBrokerException("Invalid broker data.", e);
		}
		return jmsTemplate;
	}
}
