package com.odoni.ig.igorderreceiver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
	@JacksonXmlProperty(localName = "accont")
	@JsonProperty("accont")
	private String account;
	@JacksonXmlProperty(localName = "SubmittedAt")
	@JsonProperty("SubmittedAt")
	private Date submittedAt;
	@JacksonXmlProperty(localName = "ReceivedAt")
	@JsonProperty("ReceivedAt")
	private Date receivedAt;
	private String market;
	private String action;
	private Integer size;

	public Order() {}

	public Order(String account, Date submittedAt, Date receivedAt, String market, String action, Integer size) {
		this.account = account;
		this.submittedAt = submittedAt;
		this.receivedAt = receivedAt;
		this.market = market;
		this.action = action;
		this.size = size;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(Date submittedAt) {
		this.submittedAt = submittedAt;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return Objects.equals(account, order.account) &&
				Objects.equals(submittedAt, order.submittedAt) &&
				Objects.equals(receivedAt, order.receivedAt) &&
				Objects.equals(market, order.market) &&
				Objects.equals(action, order.action) &&
				Objects.equals(size, order.size);
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, submittedAt, receivedAt, market, action, size);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Order{");
		sb.append("accont='").append(account).append('\'');
		sb.append(", submittedAt=").append(submittedAt);
		sb.append(", ReceivedAt=").append(receivedAt);
		sb.append(", market='").append(market).append('\'');
		sb.append(", action='").append(action).append('\'');
		sb.append(", size=").append(size);
		sb.append('}');
		return sb.toString();
	}
}
