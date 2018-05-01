package com.odoni.ig.igorderreceiver.model;

import java.util.Objects;

public class Error {
	String message;

	public Error() {
	}

	public Error(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Error)) return false;
		Error error = (Error) o;
		return Objects.equals(message, error.message);
	}

	@Override
	public int hashCode() {

		return Objects.hash(message);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Error{");
		sb.append("message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
