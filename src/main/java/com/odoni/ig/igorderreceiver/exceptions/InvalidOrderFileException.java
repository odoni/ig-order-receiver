package com.odoni.ig.igorderreceiver.exceptions;

public class InvalidOrderFileException extends Exception {
	public InvalidOrderFileException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidOrderFileException(String message) {
		super(message);
	}
}
