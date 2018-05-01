package com.odoni.ig.igorderreceiver.order;

import com.odoni.ig.igorderreceiver.exceptions.InvalidBrokerException;
import com.odoni.ig.igorderreceiver.exceptions.InvalidOrderFileException;
import com.odoni.ig.igorderreceiver.model.Error;
import com.odoni.ig.igorderreceiver.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleInvalidBrokerException(InvalidBrokerException e) {
		logger.debug(e.getMessage());
		return new ErrorResponse(Arrays.asList(new Error(e.getMessage())));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleInvalidOrderFileException(InvalidOrderFileException e) {
		logger.debug(e.getMessage());
		return new ErrorResponse(Arrays.asList(new Error(e.getMessage())));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
		logger.warn(e.getMessage(), e);
		return new ErrorResponse(Arrays.asList(new Error(e.getMessage())));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ErrorResponse handleException(Exception e) {
		logger.error(e.getMessage(), e);
		return new ErrorResponse(Arrays.asList(new Error(e.getMessage())));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse handleBindException(BindException e) {
		logger.error(e.getMessage(), e);
		List<Error> errorList = new ArrayList<>();
		BindingResult result = e.getBindingResult();
		List<FieldError> errors = result.getFieldErrors();
		errors.stream().forEach( error -> {
			errorList.add(new Error("Field " + error.getField() + ": " + error.getDefaultMessage()));
		});
		return new ErrorResponse(errorList);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		logger.error(e.getMessage(), e);
		return new ErrorResponse(Arrays.asList(new Error(e.getMessage())));
	}
}
