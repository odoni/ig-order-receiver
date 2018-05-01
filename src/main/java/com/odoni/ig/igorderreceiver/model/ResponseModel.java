package com.odoni.ig.igorderreceiver.model;

import java.util.Objects;

public class ResponseModel {
	String message;

	public ResponseModel() {
	}

	public ResponseModel(String message) {
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
		if (!(o instanceof ResponseModel)) return false;
		ResponseModel that = (ResponseModel) o;
		return Objects.equals(message, that.message);
	}

	@Override
	public int hashCode() {

		return Objects.hash(message);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("ResponseModel{");
		sb.append("message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
