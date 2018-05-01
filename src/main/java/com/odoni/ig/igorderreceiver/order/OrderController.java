package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.RequestModel;
import com.odoni.ig.igorderreceiver.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseModel sendOrdersToQueue(@ModelAttribute @Valid RequestModel requestModel) throws InvalidBrokerException, InvalidOrderFileException {
		logger.debug(requestModel.toString());
		orderService.processOrders(requestModel);
		return new ResponseModel("Orders successfully received!");
	}
}
