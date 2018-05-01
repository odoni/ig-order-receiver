package com.odoni.ig.igorderreceiver.model;

import java.util.Objects;

public class BrokerConnectorModel {
	String connectionUrl;
	String user;
	String password;
	String destinationName;

	public BrokerConnectorModel() {
	}

	public BrokerConnectorModel(String connectionUrl, String user, String password, String destinationName) {
		this.connectionUrl = connectionUrl;
		this.user = user;
		this.password = password;
		this.destinationName = destinationName;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BrokerConnectorModel)) return false;
		BrokerConnectorModel that = (BrokerConnectorModel) o;
		return Objects.equals(connectionUrl, that.connectionUrl) &&
				Objects.equals(user, that.user) &&
				Objects.equals(password, that.password) &&
				Objects.equals(destinationName, that.destinationName);
	}

	@Override
	public int hashCode() {

		return Objects.hash(connectionUrl, user, password, destinationName);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("BrokerConnectorModel{");
		sb.append("connectionUrl='").append(connectionUrl).append('\'');
		sb.append(", user='").append(user).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append(", destinationName='").append(destinationName).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
