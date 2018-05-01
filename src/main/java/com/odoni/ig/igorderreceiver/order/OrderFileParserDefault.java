package com.odoni.ig.igorderreceiver.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFileParserDefault implements OrderFileParser {
	private static final Logger logger = LoggerFactory.getLogger(OrderFileParserDefault.class);
	private static final String ERROR_MESSAGE_INVALID_FILE = "Invalid order file.";
	private static final String ERROR_MESSAGE_NO_ORDERS_FOUND = "Invalid order file. No orders found.";

	@Override
	public List<Order> getOrdersFromFile(MultipartFile file) throws InvalidOrderFileException {
		if (file == null) {
			logger.error(ERROR_MESSAGE_INVALID_FILE);
			throw new InvalidOrderFileException(ERROR_MESSAGE_INVALID_FILE);
		}
		List<Order> orders;
		try (InputStream resource = file.getInputStream()) {
			List<String> orderFileLines = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
			orders = parseOrderFileLines(orderFileLines);
		} catch (Exception e) {
			logger.error(ERROR_MESSAGE_INVALID_FILE, e);
			throw new InvalidOrderFileException(ERROR_MESSAGE_INVALID_FILE, e);
		}
		return orders;
	}

	protected List<Order> parseOrderFileLines(List<String> lines) throws InvalidOrderFileException {
		List<Order> orders = new ArrayList<>();
		if (lines == null || lines.size() <= 1) {
			logger.error(ERROR_MESSAGE_NO_ORDERS_FOUND);
			throw new InvalidOrderFileException(ERROR_MESSAGE_NO_ORDERS_FOUND);
		}
		removeFirstLineIfXmlHeader(lines);
		try {
			ObjectMapper xmlObjectMapper = getXmlObjectMapper();
			for (String line: lines) {
				orders.add(xmlObjectMapper.readValue(line, Order.class));
			}
		} catch (IOException e) {
			logger.error(ERROR_MESSAGE_INVALID_FILE);
			throw new InvalidOrderFileException(ERROR_MESSAGE_INVALID_FILE, e);
		}
		return orders;
	}

	private ObjectMapper getXmlObjectMapper() {
		JacksonXmlModule xmlModule = new JacksonXmlModule();
		xmlModule.setDefaultUseWrapper(false);
		return new XmlMapper(xmlModule);
	}

	protected void removeFirstLineIfXmlHeader(List<String> lines) {
		String firstLine = lines.stream().findFirst().get();
		if (firstLine.startsWith("<?xml")) {
			lines.remove(0);
		}
	}
}
