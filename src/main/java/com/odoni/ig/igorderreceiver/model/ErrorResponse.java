package com.odoni.ig.igorderreceiver.model;

import java.util.List;
import java.util.Objects;

public class ErrorResponse {

	List<Error> errors;
	Long timestamp = System.currentTimeMillis();

	public ErrorResponse() {
	}

	public ErrorResponse(List<Error> errors) {
		this.errors = errors;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ErrorResponse)) return false;
		ErrorResponse that = (ErrorResponse) o;
		return Objects.equals(errors, that.errors) &&
				Objects.equals(timestamp, that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(errors, timestamp);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ErrorResponse{");
		sb.append("errors=").append(errors);
		sb.append(", timestamp=").append(timestamp);
		sb.append('}');
		return sb.toString();
	}
}
