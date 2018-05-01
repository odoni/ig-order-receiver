package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OrderFileParser {
	public List<Order> getOrdersFromFile(MultipartFile file) throws InvalidOrderFileException;
}
